package com.yurset.wiipm.fight;

import java.util.HashMap;
import java.util.Random;

import android.content.Context;

import com.yurset.wiipm.base.NoSwitch;
import com.yurset.wiipm.main.IGame;

public class XBattle {// 战斗系统远远没有完成
	// 针对偶然情况下的同次计算两阶段伤害
	private int delayH = 1;
	private int delayZ = 1;
	private boolean fightOS = false;
	public boolean fighting = false;
	public int fightingHZ = 0;
	public int fightingZH = 0;
	// 记录HP
	public int hMissHP[] = new int[6];
	public int zMissHP[] = new int[6];
	public static int hTeamHP[] = new int[6];
	public static int zTeamHP[] = new int[6];
	// 记录PP同时与上述的Miss和Team的意义不一样
	public int hMissPP[][] = new int[6][4];
	public int zMissPP[][] = new int[6][4];
	public int hTeamPP[][] = new int[6][4];
	public int zTeamPP[][] = new int[6][4];
	// 技能优先度
	public static int priH = 0;
	public static int priZ = 0;
	// 技能编号
	private int moveDexH = 0;
	private int moveDexZ = 0;
	// 双方濒死情况
	public boolean deadH = false;
	public boolean deadZ = false;
	public boolean deadHAll = false;
	public boolean deadZAll = false;
	// 记录基础能力[HP/WG/WF/SD/TG/TF]
	public static int hTeamV[][] = new int[6][6];
	public static int zTeamV[][] = new int[6][6];

	// 记录战斗能力[SD/WG/WF/TG/TF]
	public static int hTeamS[] = new int[5];
	public static int zTeamS[] = new int[5];

	// 正常0、烧伤1、冰冻2、麻痹3、中毒4、睡眠5
	public static int abnormalH[] = { 0, 0, 0, 0, 0, 0 };
	public static int abnormalZ[] = { 0, 0, 0, 0, 0, 0 };
	public static String abnormalInfo[] = { "", "烧", "冻", "麻", "毒", "眠" };

	private Context m_context = null;
	private int moveH, moveZ;
	private int valueHP, valueWG, valueWF, valueSD, valueTG, valueTF;

	private boolean struggleH = false;
	private boolean struggleZ = false;

	public char kidneyV[][] = { { '+', '-', ' ', ' ', ' ' },
			{ '+', ' ', '-', ' ', ' ' }, { '+', ' ', ' ', '-', ' ' },
			{ '+', ' ', ' ', ' ', '-' }, { '-', '+', ' ', ' ', ' ' },
			{ ' ', '+', '-', ' ', ' ' }, { ' ', '+', ' ', '-', ' ' },
			{ ' ', '+', ' ', ' ', '-' }, { '-', ' ', '+', ' ', ' ' },
			{ ' ', '-', '+', ' ', ' ' }, { ' ', ' ', '+', '-', ' ' },
			{ ' ', ' ', '+', ' ', '-' }, { '-', ' ', ' ', '+', ' ' },
			{ ' ', '-', ' ', '+', ' ' }, { ' ', ' ', '-', '+', ' ' },
			{ ' ', ' ', ' ', '+', '-' }, { '-', ' ', ' ', ' ', '+' },
			{ ' ', '-', ' ', ' ', '+' }, { ' ', ' ', '-', ' ', '+' },
			{ ' ', ' ', ' ', '-', '+' }, { ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ' }, { ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ' }, { ' ', ' ', ' ', ' ', ' ' } };
	public String kidneyS[] = { "孤僻", "固执", "调皮", "勇敢", "大胆", "淘气", "无虑", "悠闲",
			"保守", "稳重", "马虎", "冷静", "沉着", "温顺", "慎重", "狂妄", "胆小", "急噪", "开朗",
			"天真", "坦率", "害羞", "认真", "实干", "浮躁" };

	// 正常0、晴天1、下雨2、沙暴3、冰雹4、大雾5
	public static int weather = 0;
	public static int weatherClick = 0;
	public static String weatherInfo[] = { "正常", "晴天", "下雨", "沙暴", "冰雹", "大雾" };

	private int noteFP = 0;
	public HashMap<Integer, String> fightNOTE = null;

	public XBattle(Context v_context) {
		m_context = v_context;
		fightNOTE = new HashMap<Integer, String>();
		refreshHTeam();
	}

	public void refreshHTeam() {
		for (int i = 0; i < XInfo.hTeamC; i++) {
			int kidney = XInfo.teamHKidney[i];
			valueHP = (XInfo.teamHIndex[i] != 292 ? ((31 + 2 * XInfo.teamHStats[i][0] + 0 / 4)
					* XInfo.teamHLevel[i] / 100 + 10 + XInfo.teamHLevel[i])
					: 1);
			valueSD = ((31 + 2 * XInfo.teamHStats[i][3] + 0 / 4)
					* XInfo.teamHLevel[i] / 100 + 5);
			switch (kidneyV[kidney][4]) {
				case '+':
					valueSD += valueSD / 10;
					break;
				case '-':
					valueSD -= valueSD / 10;
					break;
			}
			valueWG = ((31 + 2 * XInfo.teamHStats[i][1] + 0 / 4)
					* XInfo.teamHLevel[i] / 100 + 5);
			switch (kidneyV[kidney][0]) {
				case '+':
					valueWG += valueWG / 10;
					break;
				case '-':
					valueWG -= valueWG / 10;
					break;
			}
			valueTG = ((31 + 2 * XInfo.teamHStats[i][4] + 0 / 4)
					* XInfo.teamHLevel[i] / 100 + 5);
			switch (kidneyV[kidney][2]) {
				case '+':
					valueTG += valueTG / 10;
					break;
				case '-':
					valueTG -= valueTG / 10;
					break;
			}
			valueWF = ((XInfo.teamHStats[i][2] + 0 / 4) * XInfo.teamHLevel[i]
					/ 100 + 5);
			switch (kidneyV[kidney][1]) {
				case '+':
					valueWF += valueWF / 10;
					break;
				case '-':
					valueWF -= valueWF / 10;
					break;
			}
			valueTF = ((XInfo.teamHStats[i][5] + 0 / 4) * XInfo.teamHLevel[i]
					/ 100 + 5);
			switch (kidneyV[kidney][3]) {
				case '+':
					valueTF += valueTF / 10;
					break;
				case '-':
					valueTF -= valueTF / 10;
					break;
			}
			hTeamV[i][0] = valueHP;
			hTeamV[i][1] = valueWG;
			hTeamV[i][2] = valueWF;
			hTeamV[i][3] = valueSD;
			hTeamV[i][4] = valueTG;
			hTeamV[i][5] = valueTF;
			hTeamHP[i] = hTeamV[i][0];
		}
		for (int i = 0; i < XInfo.hTeamC; i++) {
			hTeamPP[i][0] = XInfo.teamHMovePP[i][0];
			hTeamPP[i][1] = XInfo.teamHMovePP[i][1];
			hTeamPP[i][2] = XInfo.teamHMovePP[i][2];
			hTeamPP[i][3] = XInfo.teamHMovePP[i][3];
			hMissPP[i][0] = hTeamPP[i][0];
			hMissPP[i][1] = hTeamPP[i][1];
			hMissPP[i][2] = hTeamPP[i][2];
			hMissPP[i][3] = hTeamPP[i][3];
		}
		for (int i = 0; i < XInfo.hTeamC; i++) {
			hMissHP[i] = hTeamHP[i];
		}
	}

	public void refreshZTeam() {
		for (int i = 0; i < XInfo.zTeamC; i++) {
			valueHP = (IGame.oopDex[i] != 292 ? ((31 + 2 * XInfo.teamZStats[i][0] + 0 / 4)
					* IGame.oopLv[i] / 100 + 10 + IGame.oopLv[i])
					: 1);
			valueSD = ((31 + 2 * XInfo.teamZStats[i][3] + 0 / 4)
					* IGame.oopLv[i] / 100 + 5);
			switch (kidneyV[IGame.oopKidney[i]][4]) {
				case '+':
					valueSD += valueSD / 10;
					break;
				case '-':
					valueSD -= valueSD / 10;
					break;
			}
			valueWG = ((31 + 2 * XInfo.teamZStats[i][1] + 0 / 4)
					* IGame.oopLv[i] / 100 + 5);
			switch (kidneyV[IGame.oopKidney[i]][0]) {
				case '+':
					valueWG += valueWG / 10;
					break;
				case '-':
					valueWG -= valueWG / 10;
					break;
			}
			valueTG = ((31 + 2 * XInfo.teamZStats[i][4] + 0 / 4)
					* IGame.oopLv[i] / 100 + 5);
			switch (kidneyV[IGame.oopKidney[i]][2]) {
				case '+':
					valueTG += valueTG / 10;
					break;
				case '-':
					valueTG -= valueTG / 10;
					break;
			}
			valueWF = ((31 + 2 * XInfo.teamZStats[i][2] + 0 / 4)
					* IGame.oopLv[i] / 100 + 5);
			switch (kidneyV[IGame.oopKidney[i]][1]) {
				case '+':
					valueWF += valueWF / 10;
					break;
				case '-':
					valueWF -= valueWF / 10;
					break;
			}
			valueTF = ((31 + 2 * XInfo.teamZStats[i][5] + 0 / 4)
					* IGame.oopLv[i] / 100 + 5);
			switch (kidneyV[IGame.oopKidney[i]][3]) {
				case '+':
					valueTF += valueTF / 10;
					break;
				case '-':
					valueTF -= valueTF / 10;
					break;
			}
			zTeamV[i][0] = valueHP;
			zTeamV[i][1] = valueWG;
			zTeamV[i][2] = valueWF;
			zTeamV[i][3] = valueSD;
			zTeamV[i][4] = valueTG;
			zTeamV[i][5] = valueTF;
			zTeamHP[i] = zTeamV[i][0];
		}
		for (int i = 0; i < XInfo.zTeamC; i++) {
			zTeamPP[i][0] = XInfo.teamZMovePP[i][0];
			zTeamPP[i][1] = XInfo.teamZMovePP[i][1];
			zTeamPP[i][2] = XInfo.teamZMovePP[i][2];
			zTeamPP[i][3] = XInfo.teamZMovePP[i][3];
			zMissPP[i][0] = zTeamPP[i][0];
			zMissPP[i][1] = zTeamPP[i][1];
			zMissPP[i][2] = zTeamPP[i][2];
			zMissPP[i][3] = zTeamPP[i][3];
		}
		for (int i = 0; i < XInfo.zTeamC; i++) {
			zMissHP[i] = zTeamHP[i];
		}
	}

	public void fightIni() {
		noteFP = 0;
		fightNOTE.clear();
	}

	public void fightCode(int moveH) {
		if (fighting)
			return;
		fighting = true;
		priH = 0;
		priZ = 0;
		moveDexH = 175;
		moveDexZ = 175;
		if (moveH >= 0 && moveH <= 3) {
			this.moveH = moveH;
			priH = XInfo.teamHMovePri[IGame.pmSeq][moveH];
			moveDexH = XInfo.teamHMoveIndex[IGame.pmSeq][moveH];
		}
		if (zMissPP[IGame.currPM][0] > 0 || zMissPP[IGame.currPM][1] > 0
				|| zMissPP[IGame.currPM][2] > 0 || zMissPP[IGame.currPM][3] > 0) {
			moveZ = new Random().nextInt(4);
			while (zMissPP[IGame.currPM][moveZ] == 0) {
				moveZ = new Random().nextInt(4);
			}
			priZ = XInfo.teamZMovePri[IGame.currPM][moveZ];
			moveDexZ = XInfo.teamZMoveIndex[IGame.currPM][moveZ];
		}
		if (priH > priZ) {
			// 我方技能优先
			fightHZ();
		} else if (priH < priZ) {
			// 对方技能优先
			fightZH();
		} else {
			if (hTeamV[IGame.pmSeq][3] < zTeamV[IGame.currPM][3]) {
				// 我方速度<对方速度
				fightZH();
			} else {
				// 我方速度>=对方速度
				fightHZ();
			}
		}
	}

	public String getHDamage() {
		if (XInfo.teamZMoveCate[IGame.currPM][moveZ].equals("变化")) {
			delayH = 0;
		}
		if (hTeamHP[IGame.pmSeq] < hMissHP[IGame.pmSeq]) {
			hMissHP[IGame.pmSeq]--;
			delayH = 0;
		} else if (hTeamHP[IGame.pmSeq] > hMissHP[IGame.pmSeq]) {
			hMissHP[IGame.pmSeq]++;
			delayH = 0;
		} else if (fighting && !fightOS) {
			if (priH < priZ || priH == priZ
					&& hTeamV[IGame.pmSeq][3] < zTeamV[IGame.currPM][3]) {
				if (delayH == 0) {
					if (hMissHP[IGame.pmSeq] > 0) {
						if (struggleH) {
							struggleH = false;
						} else {
							fightHZ();
							if (weatherClick-- == 0) {
								weather = 0;
							}
						}
					}
					delayH = 1;
					fightOS = true;
				}
			}
		} else if (fightOS && zTeamHP[IGame.currPM] == zMissHP[IGame.currPM]) {
			fightOS = false;
			fighting = false;
		} else if (hMissHP[IGame.pmSeq] == 0) {
			deadH = true;
			deadHAll = true;
			for (int i = 0; i < XInfo.hTeamC; i++) {
				if (hTeamHP[i] > 0) {
					deadHAll = false;
					break;
				}
			}
		}
		return hMissHP[IGame.pmSeq] + "/" + hTeamV[IGame.pmSeq][0];
	}

	public String getZDamage() {
		if (XInfo.teamHMoveCate[IGame.pmSeq][moveH].equals("变化")) {
			delayZ = 0;
		}
		if (zTeamHP[IGame.currPM] < zMissHP[IGame.currPM]) {
			zMissHP[IGame.currPM]--;
			delayZ = 0;
		} else if (zTeamHP[IGame.currPM] > zMissHP[IGame.currPM]) {
			zMissHP[IGame.currPM]++;
			delayZ = 0;
		} else if (fighting && !fightOS) {
			if (priH > priZ || priH == priZ
					&& hTeamV[IGame.pmSeq][3] >= zTeamV[IGame.currPM][3]) {
				if (delayZ == 0) {
					if (zMissHP[IGame.currPM] > 0) {
						if (struggleZ) {
							struggleZ = false;
						} else {
							fightZH();
							if (weatherClick-- == 0) {
								weather = 0;
							}
						}
					}
					delayZ = 1;
					fightOS = true;
				}
			}
		} else if (fightOS && hTeamHP[IGame.pmSeq] == hMissHP[IGame.pmSeq]) {
			fightOS = false;
			fighting = false;
		} else if (zMissHP[IGame.currPM] == 0) {
			deadZ = true;
			deadZAll = true;
			for (int i = 0; i < XInfo.zTeamC; i++) {
				if (zTeamHP[i] > 0) {
					deadZAll = false;
					break;
				}
			}
		}
		return zMissHP[IGame.currPM] + "/" + zTeamV[IGame.currPM][0];
	}

	public void restoreHP() {
		for (int i = 0; i < XInfo.hTeamC; i++) {
			hTeamHP[i] = hTeamV[i][0] / 2;
			hMissHP[i] = hTeamV[i][0] / 2;
		}
	}

	public void restorePP() {
		for (int i = 0; i < XInfo.hTeamC; i++) {
			for (int j = 0; j < 4; j++)
				hMissPP[i][j] = hTeamPP[i][j] / 2;
		}
	}

	public int findNextH() {
		int value = 0;
		for (int i = 0; i < XInfo.hTeamC; i++) {
			if (hMissHP[i] > 0) {
				value = i;
				break;
			}
		}
		return value;
	}

	public int findNextZ() {
		int value = 0;
		for (int i = 0; i < XInfo.zTeamC; i++) {
			if (zMissHP[i] > 0) {
				value = i;
				break;
			}
		}
		return value;
	}

	private void fightHZ() {
		fightingHZ = 3;
		IGame.currCry = IGame.soundPool.load(m_context,
				NoSwitch.cryXXX[IGame.oopDex[IGame.currPM]], 1);
		IGame.cryBool = true;
		String info = "from H to Z";
		if (hMissPP[IGame.pmSeq][0] > 0 || hMissPP[IGame.pmSeq][1] > 0
				|| hMissPP[IGame.pmSeq][2] > 0 || hMissPP[IGame.pmSeq][3] > 0) {
			info = XInfo.teamHName[IGame.pmSeq] + "使用"
					+ XInfo.teamHMoveName[IGame.pmSeq][moveH] + "!";
			fightNOTE.put(noteFP++, info);
			if (hMissPP[IGame.pmSeq][moveH] > 0) {
				hMissPP[IGame.pmSeq][moveH]--;
			}
			if (XInfo.teamHMoveCate[IGame.pmSeq][moveH].equals("变化")) {
				YVary.callEffect(0, moveH, moveZ);
				// 可能也应该要核算HP
			} else if (XInfo.teamHMoveCate[IGame.pmSeq][moveH].equals("特殊")) {
				YSpec.callEffect(0, moveH, moveZ);
				int damage = XCost.callSpecDamage(0, moveH, moveZ);
				if (zTeamHP[IGame.currPM] > damage) {
					zTeamHP[IGame.currPM] -= damage;
				} else {
					// 对方濒死
					zTeamHP[IGame.currPM] = 0;
				}
			} else if (XInfo.teamHMoveCate[IGame.pmSeq][moveH].equals("物理")) {
				YPsys.callEffect(0, moveH, moveZ);
				int damage = XCost.callPsysDamage(0, moveH, moveZ);
				if (zTeamHP[IGame.currPM] > damage) {
					zTeamHP[IGame.currPM] -= damage;
				} else {
					// 对方濒死
					zTeamHP[IGame.currPM] = 0;
				}
			}
		} else {
			info = XInfo.teamHName[IGame.pmSeq] + "使用拼命!";
			fightNOTE.put(noteFP++, info);
			struggleH = true;
			int damage = (((XInfo.teamHLevel[IGame.pmSeq] * 2 / 5 + 2) * 50
					* hTeamV[IGame.pmSeq][1] / zTeamV[IGame.currPM][2]) / 50) + 2;
			if (zTeamHP[IGame.currPM] > damage) {
				zTeamHP[IGame.currPM] -= damage;
			} else {
				// 对方濒死
				zTeamHP[IGame.currPM] = 0;
			}
			// if (hTeamHP[IGame.pmSeq] > XBattle.hTeamV[IGame.pmSeq][0] / 4) {
			// hTeamHP[IGame.pmSeq] -= XBattle.hTeamV[IGame.pmSeq][0] / 4;
			// } else {
			// // 我方濒死
			// hTeamHP[IGame.pmSeq] = 0;
			// }
			delayZ = 0;
		}
	}

	private void fightZH() {
		fightingZH = 3;
		IGame.currCry = IGame.soundPoolMap.get(IGame.pmSeq);
		IGame.cryBool = true;
		String info = "from Z to H";
		if (zMissPP[IGame.currPM][0] > 0 || zMissPP[IGame.currPM][1] > 0
				|| zMissPP[IGame.currPM][2] > 0 || zMissPP[IGame.currPM][3] > 0) {
			info = XInfo.teamZName[IGame.currPM] + "使用"
					+ XInfo.teamZMoveName[IGame.currPM][moveZ] + "!";
			fightNOTE.put(noteFP++, info);
			if (zMissPP[IGame.currPM][moveZ] > 0) {
				zMissPP[IGame.currPM][moveZ]--;
			}
			if (XInfo.teamZMoveCate[IGame.currPM][moveZ].equals("变化")) {
				YVary.callEffect(1, moveH, moveZ);
				// 可能也应该要核算HP
			} else if (XInfo.teamZMoveCate[IGame.currPM][moveZ].equals("特殊")) {
				YSpec.callEffect(1, moveH, moveZ);
				int damage = XCost.callSpecDamage(1, moveH, moveZ);
				if (hTeamHP[IGame.pmSeq] > damage) {
					hTeamHP[IGame.pmSeq] -= damage;
				} else {
					// 我方濒死
					hTeamHP[IGame.pmSeq] = 0;
				}
			} else if (XInfo.teamZMoveCate[IGame.currPM][moveZ].equals("物理")) {
				YPsys.callEffect(1, moveH, moveZ);
				int damage = XCost.callPsysDamage(1, moveH, moveZ);
				if (hTeamHP[IGame.pmSeq] > damage) {
					hTeamHP[IGame.pmSeq] -= damage;
				} else {
					// 我方濒死
					hTeamHP[IGame.pmSeq] = 0;
				}
			}
		} else {
			info = XInfo.teamZName[IGame.currPM] + "使用拼命!";
			fightNOTE.put(noteFP++, info);
			struggleZ = true;
			int damage = (((XInfo.teamZLevel[IGame.currPM] * 2 / 5 + 2) * 50
					* zTeamV[IGame.currPM][1] / hTeamV[IGame.pmSeq][2]) / 50) + 2;
			if (hTeamHP[IGame.pmSeq] > damage) {
				hTeamHP[IGame.pmSeq] -= damage;
			} else {
				// 我方濒死
				hTeamHP[IGame.pmSeq] = 0;
			}
			// if (zTeamHP[IGame.currPM] > XBattle.zTeamV[IGame.currPM][0] / 4)
			// {
			// zTeamHP[IGame.currPM] -= XBattle.zTeamV[IGame.currPM][0] / 4;
			// } else {
			// // 对方濒死
			// zTeamHP[IGame.currPM] = 0;
			// }
			delayH = 0;
		}
	}

	public void conveyInfo(String info) {
		fightNOTE.put(noteFP++, info);
	}
}