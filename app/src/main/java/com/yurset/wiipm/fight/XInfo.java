package com.yurset.wiipm.fight;

import android.content.Context;

import com.yurset.wiipm.main.IGame;
import com.yurset.wiipm.vect.VTeam;
import com.yurset.wiipm.vect.XMoveDex;
import com.yurset.wiipm.vect.XPMDex;

public class XInfo {

	public static int hTeamC = 0;
	public static int zTeamC = 0;

	public static XPMDex pmDex = null;
	public static XMoveDex moveDex = null;
	public static VTeam _team = null;
	private Context m_context = null;

	public static int teamHIndex[] = new int[6];
	public static int teamHLevel[] = new int[6];
	public static String teamHName[] = new String[6];
	public static String teamHItem[] = new String[6];
	public static int teamHKidney[] = new int[6];
	public static String teamHAbility[] = new String[6];
	public static int teamHMove[][] = new int[6][4];
	public static String teamHType[][] = new String[6][2];
	public static int teamHStats[][] = new int[6][6];
	public static int teamHMoveIndex[][] = new int[6][4];
	public static String teamHMoveName[][] = new String[6][4];
	public static String teamHMoveType[][] = new String[6][4];
	public static String teamHMoveCate[][] = new String[6][4];
	public static int teamHMovePower[][] = new int[6][4];
	public static int teamHMovePP[][] = new int[6][4];
	public static int teamHMovePri[][] = new int[6][4];
	public static String teamHMoveInfo[][] = new String[6][4];

	public static int teamZIndex[] = new int[6];
	public static int teamZLevel[] = new int[6];
	public static String teamZName[] = new String[6];
	public static int teamZKidney[] = new int[6];
	public static String teamZAbility[][] = new String[6][3];
	public static String teamZType[][] = new String[6][2];
	public static int teamZStats[][] = new int[6][6];
	public static int teamZMove[][] = new int[6][4];
	public static int teamZMoveIndex[][] = new int[6][4];
	public static String teamZMoveName[][] = new String[6][4];
	public static String teamZMoveType[][] = new String[6][4];
	public static String teamZMoveCate[][] = new String[6][4];
	public static int teamZMovePower[][] = new int[6][4];
	public static int teamZMovePP[][] = new int[6][4];
	public static int teamZMovePri[][] = new int[6][4];

	// 图鉴信息
	public static String dexName[] = new String[649];
	public static float dexHeight[] = new float[649];
	public static float dexWeight[] = new float[649];
	public static String dexType[][] = new String[649][2];
	public static String dexEggcate[][] = new String[649][2];

	// 通信信息（以后改成即时读，读写前后箱子的）
	public static int viaBoxIndex[][] = new int[24][35];
	public static int viaBoxLevel[][] = new int[24][35];
	public static int viaBoxKidney[][] = new int[24][35];

	public XInfo(Context context) {
		m_context = context;
		// 2.2s
		pmDex = new XPMDex(m_context);
		// 1.2s
		moveDex = new XMoveDex(m_context);
		// 2.3s
		_team = new VTeam(m_context);
		// 初始化图鉴内容
		IniDexInfo();
	}

	public void IniDexInfo() {
		for (int i = 0; i < 649; i++) {
			dexName[i] = pmDex.pmDex.get(i).getName();
		}
		for (int i = 0; i < 649; i++) {
			dexHeight[i] = pmDex.pmDex.get(i).getHeight();
		}
		for (int i = 0; i < 649; i++) {
			dexWeight[i] = pmDex.pmDex.get(i).getWeight();
		}
		for (int i = 0; i < 649; i++) {
			dexType[i][0] = pmDex.pmDex.get(i).getType1();
			dexType[i][1] = pmDex.pmDex.get(i).getType2();
		}
		for (int i = 0; i < 649; i++) {
			dexEggcate[i][0] = pmDex.pmDex.get(i).getEggcate1();
			dexEggcate[i][1] = pmDex.pmDex.get(i).getEggcate2();
		}
	}

	// 初始化我方队伍信息
	public static void IniHInfo() {
		// home表
		for (int i = 0; i < hTeamC; i++) {
			teamHIndex[i] = _team.home.get(_team.teamHash[i] - 1).getIndex();
		}
		// 等级变为固定
		// for (int i = 0; i < hTeamC; i++) {
		// teamHLevel[i] = _team.home.get(teamHIndex[i] - 1).getLevel();
		// }
		for (int i = 0; i < hTeamC; i++) {
			teamHName[i] = _team.home.get(teamHIndex[i] - 1).getName();
		}
		// 性格变为固定
		// for (int i = 0; i < hTeamC; i++) {
		// teamHKidney[i] = _team.home.get(teamHIndex[i] - 1).getKidney();
		// }
		for (int i = 0; i < hTeamC; i++) {
			teamHItem[i] = _team.home.get(teamHIndex[i] - 1).getItem();
		}
		for (int i = 0; i < hTeamC; i++) {
			teamHAbility[i] = _team.home.get(teamHIndex[i] - 1).getAbility1();
		}
		for (int i = 0; i < hTeamC; i++) {
			teamHMove[i][0] = _team.home.get(teamHIndex[i] - 1).getMove1();
			teamHMove[i][1] = _team.home.get(teamHIndex[i] - 1).getMove2();
			teamHMove[i][2] = _team.home.get(teamHIndex[i] - 1).getMove3();
			teamHMove[i][3] = _team.home.get(teamHIndex[i] - 1).getMove4();
		}
		// PMinfo表
		for (int i = 0; i < hTeamC; i++) {
			teamHType[i][0] = pmDex.pmDex.get(teamHIndex[i] - 1).getType1();
			teamHType[i][1] = pmDex.pmDex.get(teamHIndex[i] - 1).getType2();
		}
		for (int i = 0; i < hTeamC; i++) {
			teamHStats[i][0] = pmDex.pmDex.get(teamHIndex[i] - 1).getHP();
			teamHStats[i][1] = pmDex.pmDex.get(teamHIndex[i] - 1).getWG();
			teamHStats[i][2] = pmDex.pmDex.get(teamHIndex[i] - 1).getWF();
			teamHStats[i][3] = pmDex.pmDex.get(teamHIndex[i] - 1).getSD();
			teamHStats[i][4] = pmDex.pmDex.get(teamHIndex[i] - 1).getTG();
			teamHStats[i][5] = pmDex.pmDex.get(teamHIndex[i] - 1).getTF();
		}
		for (int i = 0; i < hTeamC; i++) {
			teamHMoveIndex[i][0] = moveDex.moveDex.get(teamHMove[i][0] - 1)
					.getIndex();
			teamHMoveIndex[i][1] = moveDex.moveDex.get(teamHMove[i][1] - 1)
					.getIndex();
			teamHMoveIndex[i][2] = moveDex.moveDex.get(teamHMove[i][2] - 1)
					.getIndex();
			teamHMoveIndex[i][3] = moveDex.moveDex.get(teamHMove[i][3] - 1)
					.getIndex();
		}
		for (int i = 0; i < hTeamC; i++) {
			teamHMoveName[i][0] = moveDex.moveDex.get(teamHMove[i][0] - 1)
					.getName();
			teamHMoveName[i][1] = moveDex.moveDex.get(teamHMove[i][1] - 1)
					.getName();
			teamHMoveName[i][2] = moveDex.moveDex.get(teamHMove[i][2] - 1)
					.getName();
			teamHMoveName[i][3] = moveDex.moveDex.get(teamHMove[i][3] - 1)
					.getName();
		}
		for (int i = 0; i < hTeamC; i++) {
			teamHMoveType[i][0] = moveDex.moveDex.get(teamHMove[i][0] - 1)
					.getType();
			teamHMoveType[i][1] = moveDex.moveDex.get(teamHMove[i][1] - 1)
					.getType();
			teamHMoveType[i][2] = moveDex.moveDex.get(teamHMove[i][2] - 1)
					.getType();
			teamHMoveType[i][3] = moveDex.moveDex.get(teamHMove[i][3] - 1)
					.getType();
		}
		for (int i = 0; i < hTeamC; i++) {
			teamHMoveCate[i][0] = moveDex.moveDex.get(teamHMove[i][0] - 1)
					.getCate();
			teamHMoveCate[i][1] = moveDex.moveDex.get(teamHMove[i][1] - 1)
					.getCate();
			teamHMoveCate[i][2] = moveDex.moveDex.get(teamHMove[i][2] - 1)
					.getCate();
			teamHMoveCate[i][3] = moveDex.moveDex.get(teamHMove[i][3] - 1)
					.getCate();
		}
		for (int i = 0; i < hTeamC; i++) {
			teamHMovePower[i][0] = moveDex.moveDex.get(teamHMove[i][0] - 1)
					.getPower();
			teamHMovePower[i][1] = moveDex.moveDex.get(teamHMove[i][1] - 1)
					.getPower();
			teamHMovePower[i][2] = moveDex.moveDex.get(teamHMove[i][2] - 1)
					.getPower();
			teamHMovePower[i][3] = moveDex.moveDex.get(teamHMove[i][3] - 1)
					.getPower();
		}
		for (int i = 0; i < hTeamC; i++) {
			teamHMovePP[i][0] = moveDex.moveDex.get(teamHMove[i][0] - 1)
					.getPP();
			teamHMovePP[i][1] = moveDex.moveDex.get(teamHMove[i][1] - 1)
					.getPP();
			teamHMovePP[i][2] = moveDex.moveDex.get(teamHMove[i][2] - 1)
					.getPP();
			teamHMovePP[i][3] = moveDex.moveDex.get(teamHMove[i][3] - 1)
					.getPP();
		}
		for (int i = 0; i < hTeamC; i++) {
			teamHMovePri[i][0] = moveDex.moveDex.get(teamHMove[i][0] - 1)
					.getPri();
			teamHMovePri[i][1] = moveDex.moveDex.get(teamHMove[i][1] - 1)
					.getPri();
			teamHMovePri[i][2] = moveDex.moveDex.get(teamHMove[i][2] - 1)
					.getPri();
			teamHMovePri[i][3] = moveDex.moveDex.get(teamHMove[i][3] - 1)
					.getPri();
		}
		for (int i = 0; i < hTeamC; i++) {
			teamHMoveInfo[i][0] = moveDex.moveDex.get(teamHMove[i][0] - 1)
					.getInfo();
			teamHMoveInfo[i][1] = moveDex.moveDex.get(teamHMove[i][1] - 1)
					.getInfo();
			teamHMoveInfo[i][2] = moveDex.moveDex.get(teamHMove[i][2] - 1)
					.getInfo();
			teamHMoveInfo[i][3] = moveDex.moveDex.get(teamHMove[i][3] - 1)
					.getInfo();
		}
	}

	// 初始化对方队伍信息
	public void IniZInfo() {
		// 随机设定
		for (int i = 0; i < zTeamC; i++) {
			teamZIndex[i] = IGame.oopDex[i];
		}
		for (int i = 0; i < zTeamC; i++) {
			teamZMove[i][0] = IGame.oopMove[i][0];
			teamZMove[i][1] = IGame.oopMove[i][1];
			teamZMove[i][2] = IGame.oopMove[i][2];
			teamZMove[i][3] = IGame.oopMove[i][3];
		}
		// PMinfo表
		for (int i = 0; i < zTeamC; i++) {
			teamZName[i] = pmDex.pmDex.get(teamZIndex[i] - 1).getName();
		}
		for (int i = 0; i < zTeamC; i++) {
			teamZType[i][0] = pmDex.pmDex.get(teamZIndex[i] - 1).getType1();
			teamZType[i][1] = pmDex.pmDex.get(teamZIndex[i] - 1).getType2();
		}
		for (int i = 0; i < zTeamC; i++) {
			teamZAbility[i][0] = pmDex.pmDex.get(teamZIndex[i] - 1)
					.getAbility1();
			teamZAbility[i][1] = pmDex.pmDex.get(teamZIndex[i] - 1)
					.getAbility2();
			teamZAbility[i][2] = pmDex.pmDex.get(teamZIndex[i] - 1)
					.getAbility3();
		}
		for (int i = 0; i < zTeamC; i++) {
			teamZStats[i][0] = pmDex.pmDex.get(teamZIndex[i] - 1).getHP();
			teamZStats[i][1] = pmDex.pmDex.get(teamZIndex[i] - 1).getWG();
			teamZStats[i][2] = pmDex.pmDex.get(teamZIndex[i] - 1).getWF();
			teamZStats[i][3] = pmDex.pmDex.get(teamZIndex[i] - 1).getSD();
			teamZStats[i][4] = pmDex.pmDex.get(teamZIndex[i] - 1).getTG();
			teamZStats[i][5] = pmDex.pmDex.get(teamZIndex[i] - 1).getTF();
		}
		for (int i = 0; i < zTeamC; i++) {
			teamZMoveIndex[i][0] = moveDex.moveDex.get(teamZMove[i][0] - 1)
					.getIndex();
			teamZMoveIndex[i][1] = moveDex.moveDex.get(teamZMove[i][1] - 1)
					.getIndex();
			teamZMoveIndex[i][2] = moveDex.moveDex.get(teamZMove[i][2] - 1)
					.getIndex();
			teamZMoveIndex[i][3] = moveDex.moveDex.get(teamZMove[i][3] - 1)
					.getIndex();
		}
		for (int i = 0; i < zTeamC; i++) {
			teamZMoveName[i][0] = moveDex.moveDex.get(teamZMove[i][0] - 1)
					.getName();
			teamZMoveName[i][1] = moveDex.moveDex.get(teamZMove[i][1] - 1)
					.getName();
			teamZMoveName[i][2] = moveDex.moveDex.get(teamZMove[i][2] - 1)
					.getName();
			teamZMoveName[i][3] = moveDex.moveDex.get(teamZMove[i][3] - 1)
					.getName();
		}
		for (int i = 0; i < zTeamC; i++) {
			teamZMoveType[i][0] = moveDex.moveDex.get(teamZMove[i][0] - 1)
					.getType();
			teamZMoveType[i][1] = moveDex.moveDex.get(teamZMove[i][1] - 1)
					.getType();
			teamZMoveType[i][2] = moveDex.moveDex.get(teamZMove[i][2] - 1)
					.getType();
			teamZMoveType[i][3] = moveDex.moveDex.get(teamZMove[i][3] - 1)
					.getType();
		}
		for (int i = 0; i < zTeamC; i++) {
			teamZMoveCate[i][0] = moveDex.moveDex.get(teamZMove[i][0] - 1)
					.getCate();
			teamZMoveCate[i][1] = moveDex.moveDex.get(teamZMove[i][1] - 1)
					.getCate();
			teamZMoveCate[i][2] = moveDex.moveDex.get(teamZMove[i][2] - 1)
					.getCate();
			teamZMoveCate[i][3] = moveDex.moveDex.get(teamZMove[i][3] - 1)
					.getCate();
		}
		for (int i = 0; i < zTeamC; i++) {
			teamZMovePower[i][0] = moveDex.moveDex.get(teamZMove[i][0] - 1)
					.getPower();
			teamZMovePower[i][1] = moveDex.moveDex.get(teamZMove[i][1] - 1)
					.getPower();
			teamZMovePower[i][2] = moveDex.moveDex.get(teamZMove[i][2] - 1)
					.getPower();
			teamZMovePower[i][3] = moveDex.moveDex.get(teamZMove[i][3] - 1)
					.getPower();
		}
		for (int i = 0; i < zTeamC; i++) {
			teamZMovePP[i][0] = moveDex.moveDex.get(teamZMove[i][0] - 1)
					.getPP();
			teamZMovePP[i][1] = moveDex.moveDex.get(teamZMove[i][1] - 1)
					.getPP();
			teamZMovePP[i][2] = moveDex.moveDex.get(teamZMove[i][2] - 1)
					.getPP();
			teamZMovePP[i][3] = moveDex.moveDex.get(teamZMove[i][3] - 1)
					.getPP();
		}
		for (int i = 0; i < zTeamC; i++) {
			teamZMovePri[i][0] = moveDex.moveDex.get(teamZMove[i][0] - 1)
					.getPri();
			teamZMovePri[i][1] = moveDex.moveDex.get(teamZMove[i][1] - 1)
					.getPri();
			teamZMovePri[i][2] = moveDex.moveDex.get(teamZMove[i][2] - 1)
					.getPri();
			teamZMovePri[i][3] = moveDex.moveDex.get(teamZMove[i][3] - 1)
					.getPri();
		}
	}

	public void reFresh() {
		_team.reFresh();
		pmDex.reFresh();
		moveDex.reFresh();
	}
}