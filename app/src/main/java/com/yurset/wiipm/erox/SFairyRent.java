package com.yurset.wiipm.erox;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.yurset.wiipm.base.APoint;
import com.yurset.wiipm.logi.MDraw;
import com.yurset.wiipm.logi.VData;
import com.yurset.wiipm.main.IGame;
import com.yurset.wiipm.vect.SMap;

public class SFairyRent {
	private Context m_context = null;
	// 表示母地图占据某父地图域的两种情况
	private boolean halfUpperY = false;
	private boolean halfRightX = false;
	private boolean halfDownY = false;
	private boolean halfLeftX = false;
	// 返回母地图域的逻辑矩阵和位图矩阵
	// 600-699
	public APoint[] npcss = null;
	public int[] whinpcss = null;
	// 800-899和900-999和9
	public APoint[] target = null;
	public int[] whitarget = null;
	private int[][] logicMap = null;
	private Bitmap[][] imageMap0 = null;
	private Bitmap[][] imageMap1 = null;
	private Bitmap[][] imageMap2 = null;
	// 用来提供logicMap和imageMap的对象
	public RMapBase mapBase = null;
	// 考虑landMap结合起来判断
	public boolean upperEdge = false;
	public boolean rightEdge = false;
	public boolean downEdge = false;
	public boolean leftEdge = false;
	// 是否进入建筑物内部
	public boolean roomFlag = false;
	// 是否进入RogueLike世界
	public boolean rogueFlag = false;
	// 标记相反方向交叉的情况
	private boolean directFlag = true;

	public SFairyRent(Context context) {
		m_context = context;
	}

	public void iniRole(boolean occFlag) {
		boolean untake = true;
		for (int y = 0; y < VData.allSIZE.y; y++) {
			for (int x = 0; x < VData.allSIZE.x; x++) {
				if (logicMap[y][x] == 6) {
					if (occFlag) {
						VData.so = new APoint(y, x);
						if (y > 0 && untake) {
							if (logicMap[y - 1][x] == 3
									|| logicMap[y - 1][x] == 800) {// 上
								// logicMap[y - 1][x] = 7;
								VData.pe = new APoint(y - 1, x);
								VData._fell.markS = VData.STAY_FACE;
								VData._hero.markS = VData.STAY_FACE;
								untake = false;
							}
						}
						if (x + 1 < VData.allSIZE.x && untake) {
							if (logicMap[y][x + 1] == 3
									|| logicMap[y][x + 1] == 800) {// 右
								// logicMap[y][x + 1] = 7;
								VData.pe = new APoint(y, x + 1);
								VData._fell.markS = VData.STAY_LEFT;
								VData._hero.markS = VData.STAY_LEFT;
								untake = false;
							}
						}
						if (x > 0 && untake) {
							if (logicMap[y][x - 1] == 3
									|| logicMap[y][x - 1] == 800) {// 左
								// logicMap[y][x - 1] = 7;
								VData.pe = new APoint(y, x - 1);
								VData._fell.markS = VData.STAY_RIGHT;
								VData._hero.markS = VData.STAY_RIGHT;
								untake = false;
							}
						}
						if (y + 1 < VData.allSIZE.y && untake) {
							if (logicMap[y + 1][x] == 3
									|| logicMap[y + 1][x] == 800) {// 下
								// logicMap[y + 1][x] = 7;
								VData.pe = new APoint(y + 1, x);
								VData._fell.markS = VData.STAY_BACK;
								VData._hero.markS = VData.STAY_BACK;
								untake = false;
							}
						}
					} else {
						logicMap[y][x] = 3;
					}
				} else if (logicMap[y][x] >= 900) {// 第N号建筑
					target[logicMap[y][x] - 900] = new APoint(y, x);
					whitarget[logicMap[y][x] - 900] = logicMap[y][x];
				} else if (logicMap[y][x] >= 800) {// 室内跳转
					target[logicMap[y][x] - 800] = new APoint(y, x);
					whitarget[logicMap[y][x] - 800] = logicMap[y][x];
				} else if (logicMap[y][x] == 9) {// 暂时用最后一个记录RL蘑菇
					target[11] = new APoint(y, x);
					whitarget[11] = 9;
				} else if (logicMap[y][x] >= 610) {// 610之NPC事件
					npcss[logicMap[y][x] - 610] = new APoint(y, x);
					whinpcss[logicMap[y][x] - 610] = logicMap[y][x];
				} else if (logicMap[y][x] >= 600) {// 600之NPC事件
					npcss[logicMap[y][x] - 600] = new APoint(y, x);
					whinpcss[logicMap[y][x] - 600] = logicMap[y][x];
				}
			}
		}
	}

	public void runLocate(int nodeY, int nodeX, boolean iniFlag) {
		// 先保留0-9的情况，考虑通过flyLocate直接跳转先不改
		// 10000及以上为使用新地图序号，取代原先的0-99的情况
		// 还有边界情况采用99的地图块
		if (nodeY == 0 || nodeY == 15 || nodeX == 0 || nodeX == 17) {
			mapBase = new RMapFull(m_context, iniFlag, 9);
		} else {
			nodeY--;
			nodeX--;
			mapBase = new RMapXerox(m_context, iniFlag, nodeY, nodeX);
		}
	}

	public void runLocate(int node, boolean iniFlag) {
		switch (node) {
			case -1: // 道路
				mapBase = new RMapNull(m_context, iniFlag);
				break;
			case 0:
				mapBase = new RMapCity0(m_context, iniFlag);
				break;
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				mapBase = new RMapCityF(m_context, iniFlag);
				break;
			case 9:
				mapBase = new RMapCity9(m_context, iniFlag);
				break;
			case 80: // 郊野
				mapBase = new RMapFull(m_context, iniFlag, 0);
				break;
			case 81: // 郊野
				mapBase = new RMapFull(m_context, iniFlag, 1);
				break;
			case 82: // 郊野
				mapBase = new RMapFull(m_context, iniFlag, 2);
				break;
			case 83: // 郊野
				mapBase = new RMapFull(m_context, iniFlag, 3);
				break;
			case 84: // 郊野
				mapBase = new RMapFull(m_context, iniFlag, 4);
				break;
			case 85: // 郊野
				mapBase = new RMapFull(m_context, iniFlag, 5);
				break;
			case 86: // 郊野
				mapBase = new RMapFull(m_context, iniFlag, 6);
				break;
			case 87: // 郊野
				mapBase = new RMapFull(m_context, iniFlag, 7);
				break;
			case 88: // 郊野
				mapBase = new RMapFull(m_context, iniFlag, 8);
				break;
			case 99: // 森林
				mapBase = new RMapFull(m_context, iniFlag, 9);
				break;
			case 100:// 医院
				roomFlag = true;
				mapBase = new UMapRisoF0(m_context, iniFlag);
				break;
			case 101:// 商店
				roomFlag = true;
				mapBase = new UMapRisoF1(m_context, iniFlag);
				break;
			case 102:// 道馆
				roomFlag = true;
				mapBase = new UMapRisoF2(m_context, iniFlag);
				break;
			case 199:// 联盟
				roomFlag = true;
				mapBase = new UMapRisoFf(m_context, iniFlag);
				break;
			default:// 现在处理的是-100的情况
				rogueFlag = true;
				mapBase = new SMap(m_context);
				break;
		}
		npcss = new APoint[12];
		whinpcss = new int[12];
		target = new APoint[12];
		whitarget = new int[12];
	}

	public void flyLocate(int node, boolean iniFlag) {
		runLocate(node, iniFlag);
		switch (node) {
			case 0:
				IGame.guide = "家乡";
				IGame.guideFlag = true;
				IGame.guideClick = 128;
				break;
			case 1:
				IGame.guide = "一城";
				IGame.guideFlag = true;
				IGame.guideClick = 128;
				break;
			case 2:
				IGame.guide = "二城";
				IGame.guideFlag = true;
				IGame.guideClick = 128;
				break;
			case 3:
				IGame.guide = "三城";
				IGame.guideFlag = true;
				IGame.guideClick = 128;
				break;
			case 4:
				IGame.guide = "四城";
				IGame.guideFlag = true;
				IGame.guideClick = 128;
				break;
			case 5:
				IGame.guide = "五城";
				IGame.guideFlag = true;
				IGame.guideClick = 128;
				break;
			case 6:
				IGame.guide = "六城";
				IGame.guideFlag = true;
				IGame.guideClick = 128;
				break;
			case 7:
				IGame.guide = "七城";
				IGame.guideFlag = true;
				IGame.guideClick = 128;
				break;
			case 8:
				IGame.guide = "八城";
				IGame.guideFlag = true;
				IGame.guideClick = 128;
				break;
			case 9:
				IGame.guide = "联盟";
				IGame.guideFlag = true;
				IGame.guideClick = 128;
				break;
			case -100:
				IGame.guide = "RL空间";
				IGame.guideFlag = true;
				IGame.guideClick = 128;
				break;
			default:
				IGame.guideFlag = false;
		}
		logicMap = new int[VData.allSIZE.y][VData.allSIZE.x];

		for (int i = 0; i < VData.allSIZE.y; i++) {
			for (int j = 0; j < VData.allSIZE.x; j++) {
				logicMap[i][j] = mapBase.logicMap[i][j];
			}
		}
		imageMap0 = mapBase.imageMap0;
		imageMap1 = mapBase.imageMap1;
		imageMap2 = mapBase.imageMap2;
	}

	// 根据四个方向的移动生成母地图域
	// 判断是否到边界和创建哪个父地图域
	public void generate(int direct) {
		switch (direct) {
			case 0:
				// 表示向上
				downEdge = false;
				if (halfDownY) {
					halfDownY = false;
					directFlag = false;
				} else {
					halfUpperY = !halfUpperY;
				}
				if (halfUpperY) {
					if (halfLeftX || halfRightX) {
						Log.i("wiiPM", "正下-->中央");
						// 左半侧
						if (halfLeftX) {
							runLocate(
									VData.landMap.baseMap[VData.locY - 1][VData.locX - 1],
									false);
						} else if (halfRightX) {
							runLocate(
									VData.landMap.baseMap[VData.locY - 1][VData.locX],
									false);
						}
						// runLocate(2000, false);
						// mapBase = new RMapNull(m_context, false);
						for (int i = 0; i < VData.allSIZE.y / 2; i++) {
							for (int j = 0; j < VData.allSIZE.x / 2; j++) {
								logicMap[i + VData.allSIZE.y / 2][j] = logicMap[i][j];
								logicMap[i][j] = mapBase.logicMap[i
										+ VData.allSIZE.y / 2][j + VData.allSIZE.x
										/ 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap0[i + VData.allSIZE.y / 2][j] = imageMap0[i][j];
								imageMap0[i][j] = mapBase.imageMap0[i
										+ VData.allSIZE.y / 2][j + VData.allSIZE.x
										/ 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap1[i + VData.allSIZE.y / 2][j] = imageMap1[i][j];
								imageMap1[i][j] = mapBase.imageMap1[i
										+ VData.allSIZE.y / 2][j + VData.allSIZE.x
										/ 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap2[i + VData.allSIZE.y / 2][j] = imageMap2[i][j];
								imageMap2[i][j] = mapBase.imageMap2[i
										+ VData.allSIZE.y / 2][j + VData.allSIZE.x
										/ 2];
							}
						}
						// 右半侧
						if (halfLeftX) {
							runLocate(
									VData.landMap.baseMap[VData.locY - 1][VData.locX],
									false);
						} else if (halfRightX) {
							runLocate(
									VData.landMap.baseMap[VData.locY - 1][VData.locX + 1],
									false);
						}
						// runLocate(2010, false);
						// mapBase = new RMapCity9(m_context, false);
						for (int i = 0; i < VData.allSIZE.y / 2; i++) {
							for (int j = VData.allSIZE.x / 2; j < VData.allSIZE.x; j++) {
								logicMap[i + VData.allSIZE.y / 2][j] = logicMap[i][j];
								logicMap[i][j] = mapBase.logicMap[i
										+ VData.allSIZE.y / 2][j - VData.allSIZE.x
										/ 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = VData.allSIZE.x / 2 + 1; j < VData.allSIZE.x + 1; j++) {
								imageMap0[i + VData.allSIZE.y / 2][j] = imageMap0[i][j];
								imageMap0[i][j] = mapBase.imageMap0[i
										+ VData.allSIZE.y / 2][j - VData.allSIZE.x
										/ 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = VData.allSIZE.x / 2 + 1; j < VData.allSIZE.x + 1; j++) {
								imageMap1[i + VData.allSIZE.y / 2][j] = imageMap1[i][j];
								imageMap1[i][j] = mapBase.imageMap1[i
										+ VData.allSIZE.y / 2][j - VData.allSIZE.x
										/ 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = VData.allSIZE.x / 2 + 1; j < VData.allSIZE.x + 1; j++) {
								imageMap2[i + VData.allSIZE.y / 2][j] = imageMap2[i][j];
								imageMap2[i][j] = mapBase.imageMap2[i
										+ VData.allSIZE.y / 2][j - VData.allSIZE.x
										/ 2];
							}
						}
						iniRole(false);
					} else {
						// if (mapBase.holyidX == 0) {
						// Log.i("wiiPM", "左下-->正左");
						// runLocate(2000, false);
						// // mapBase = new RMapNull(m_context, false);
						// } else if (mapBase.holyidX == 1) {
						// Log.i("wiiPM", "右下-->正右");
						// runLocate(2010, false);
						// // mapBase = new RMapCity9(m_context, false);
						// }
						runLocate(
								VData.landMap.baseMap[VData.locY - 1][VData.locX],
								false);
						for (int i = 0; i < VData.allSIZE.y / 2; i++) {
							for (int j = 0; j < VData.allSIZE.x; j++) {
								logicMap[i + VData.allSIZE.y / 2][j] = logicMap[i][j];
								logicMap[i][j] = mapBase.logicMap[i
										+ VData.allSIZE.y / 2][j];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x + 1; j++) {
								imageMap0[i + VData.allSIZE.y / 2][j] = imageMap0[i][j];
								imageMap0[i][j] = mapBase.imageMap0[i
										+ VData.allSIZE.y / 2][j];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x + 1; j++) {
								imageMap1[i + VData.allSIZE.y / 2][j] = imageMap1[i][j];
								imageMap1[i][j] = mapBase.imageMap1[i
										+ VData.allSIZE.y / 2][j];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x + 1; j++) {
								imageMap2[i + VData.allSIZE.y / 2][j] = imageMap2[i][j];
								imageMap2[i][j] = mapBase.imageMap2[i
										+ VData.allSIZE.y / 2][j];
							}
						}
						iniRole(false);
					}
				} else {
					if (directFlag) {
						VData.locY--;
						if (VData.locY == 0) {
							upperEdge = true;
						}
					} else {
						directFlag = true;
					}
					if (halfLeftX || halfRightX) {
						// 左半侧
						Log.i("wiiPM", "中央-->正上");
						if (halfLeftX) {
							runLocate(
									VData.landMap.baseMap[VData.locY][VData.locX - 1],
									false);
						} else if (halfRightX) {
							runLocate(
									VData.landMap.baseMap[VData.locY][VData.locX],
									false);
						}
						// runLocate(2000, false);
						// mapBase = new RMapNull(m_context, false);
						for (int i = 0; i < VData.allSIZE.y; i++) {
							for (int j = 0; j < VData.allSIZE.x / 2; j++) {
								logicMap[i][j] = mapBase.logicMap[i][j
										+ VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap0[i][j] = mapBase.imageMap0[i][j
										+ VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap1[i][j] = mapBase.imageMap1[i][j
										+ VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap2[i][j] = mapBase.imageMap2[i][j
										+ VData.allSIZE.x / 2];
							}
						}
						// 右半侧
						if (halfLeftX) {
							runLocate(
									VData.landMap.baseMap[VData.locY][VData.locX],
									false);
						} else if (halfRightX) {
							runLocate(
									VData.landMap.baseMap[VData.locY][VData.locX + 1],
									false);
						}
						// runLocate(2010, false);
						// mapBase = new RMapCity9(m_context, false);
						for (int i = 0; i < VData.allSIZE.y; i++) {
							for (int j = VData.allSIZE.x / 2; j < VData.allSIZE.x; j++) {
								logicMap[i][j] = mapBase.logicMap[i][j
										- VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = VData.allSIZE.x / 2 + 1; j < VData.allSIZE.x + 1; j++) {
								imageMap0[i][j] = mapBase.imageMap0[i][j
										- VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = VData.allSIZE.x / 2 + 1; j < VData.allSIZE.x + 1; j++) {
								imageMap1[i][j] = mapBase.imageMap1[i][j
										- VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = VData.allSIZE.x / 2 + 1; j < VData.allSIZE.x + 1; j++) {
								imageMap2[i][j] = mapBase.imageMap2[i][j
										- VData.allSIZE.x / 2];
							}
						}
						iniRole(false);
					} else {
						// if (mapBase.holyidX == 0) {
						// Log.i("wiiPM", "正左-->左上");
						// flyLocate(2000, false);
						// } else if (mapBase.holyidX == 1) {
						// Log.i("wiiPM", "正右-->右上");
						// flyLocate(2010, false);
						// }
						// 向上正式走动一格
						flyLocate(VData.landMap.baseMap[VData.locY][VData.locX],
								false);
						iniRole(false);
					}
				}
				break;
			case 1:
				// 表示向右
				leftEdge = false;
				if (halfLeftX) {
					halfLeftX = false;
					directFlag = false;
				} else {
					halfRightX = !halfRightX;
				}
				if (halfRightX) {
					if (halfUpperY || halfDownY) {
						Log.i("wiiPM", "正左-->中央");
						// 上半侧
						if (halfUpperY) {
							runLocate(
									VData.landMap.baseMap[VData.locY - 1][VData.locX + 1],
									false);
						} else if (halfDownY) {
							runLocate(
									VData.landMap.baseMap[VData.locY][VData.locX + 1],
									false);
						}
						// runLocate(2010, false);
						// mapBase = new RMapCity9(m_context, false);
						for (int i = 0; i < VData.allSIZE.y / 2; i++) {
							for (int j = 0; j < VData.allSIZE.x / 2; j++) {
								logicMap[i][j] = logicMap[i][j + VData.allSIZE.x
										/ 2];
								logicMap[i][j + VData.allSIZE.x / 2] = mapBase.logicMap[i
										+ VData.allSIZE.y / 2][j];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap0[i][j] = imageMap0[i][j + VData.allSIZE.x
										/ 2];
								imageMap0[i][j + VData.allSIZE.x / 2] = mapBase.imageMap0[i
										+ VData.allSIZE.y / 2][j];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap1[i][j] = imageMap1[i][j + VData.allSIZE.x
										/ 2];
								imageMap1[i][j + VData.allSIZE.x / 2] = mapBase.imageMap1[i
										+ VData.allSIZE.y / 2][j];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap2[i][j] = imageMap2[i][j + VData.allSIZE.x
										/ 2];
								imageMap2[i][j + VData.allSIZE.x / 2] = mapBase.imageMap2[i
										+ VData.allSIZE.y / 2][j];
							}
						}
						// 下半侧
						if (halfUpperY) {
							runLocate(
									VData.landMap.baseMap[VData.locY][VData.locX + 1],
									false);
						} else if (halfDownY) {
							runLocate(
									VData.landMap.baseMap[VData.locY + 1][VData.locX + 1],
									false);
						}
						// runLocate(2011, false);
						// mapBase = new RMapCity0(m_context, false);
						for (int i = VData.allSIZE.y / 2; i < VData.allSIZE.y; i++) {
							for (int j = 0; j < VData.allSIZE.x / 2; j++) {
								logicMap[i][j] = logicMap[i][j + VData.allSIZE.x
										/ 2];
								logicMap[i][j + VData.allSIZE.x / 2] = mapBase.logicMap[i
										- VData.allSIZE.y / 2][j];
							}
						}
						for (int i = VData.allSIZE.y / 2 + 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap0[i][j] = imageMap0[i][j + VData.allSIZE.x
										/ 2];
								imageMap0[i][j + VData.allSIZE.x / 2] = mapBase.imageMap0[i
										- VData.allSIZE.y / 2][j];
							}
						}
						for (int i = VData.allSIZE.y / 2 + 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap1[i][j] = imageMap1[i][j + VData.allSIZE.x
										/ 2];
								imageMap1[i][j + VData.allSIZE.x / 2] = mapBase.imageMap1[i
										- VData.allSIZE.y / 2][j];
							}
						}
						for (int i = VData.allSIZE.y / 2 + 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap2[i][j] = imageMap2[i][j + VData.allSIZE.x
										/ 2];
								imageMap2[i][j + VData.allSIZE.x / 2] = mapBase.imageMap2[i
										- VData.allSIZE.y / 2][j];
							}
						}
						iniRole(false);
					} else {
						// if (mapBase.holyidY == 0) {
						// Log.i("wiiPM", "左上-->正上");
						// runLocate(2010, false);
						// // mapBase = new RMapCity9(m_context, false);
						// } else if (mapBase.holyidY == 1) {
						// Log.i("wiiPM", "左下-->正下");
						// runLocate(2011, false);
						// // mapBase = new RMapCity0(m_context, false);
						// }
						runLocate(
								VData.landMap.baseMap[VData.locY][VData.locX + 1],
								false);
						for (int i = 0; i < VData.allSIZE.y; i++) {
							for (int j = 0; j < VData.allSIZE.x / 2; j++) {
								logicMap[i][j] = logicMap[i][j + VData.allSIZE.x
										/ 2];
								logicMap[i][j + VData.allSIZE.x / 2] = mapBase.logicMap[i][j];
							}
						}
						for (int i = 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap0[i][j] = imageMap0[i][j + VData.allSIZE.x
										/ 2];
								imageMap0[i][j + VData.allSIZE.x / 2] = mapBase.imageMap0[i][j];
							}
						}
						for (int i = 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap1[i][j] = imageMap1[i][j + VData.allSIZE.x
										/ 2];
								imageMap1[i][j + VData.allSIZE.x / 2] = mapBase.imageMap1[i][j];
							}
						}
						for (int i = 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap2[i][j] = imageMap2[i][j + VData.allSIZE.x
										/ 2];
								imageMap2[i][j + VData.allSIZE.x / 2] = mapBase.imageMap2[i][j];
							}
						}
						iniRole(false);
					}
				} else {
					if (directFlag) {
						VData.locX++;
						if (VData.locX + 1 == VData.landMap.baseX) {
							rightEdge = true;
						}
					} else {
						directFlag = true;
					}
					if (halfUpperY || halfDownY) {
						// 上半侧
						Log.i("wiiPM", "中央-->正右");
						if (halfUpperY) {
							runLocate(
									VData.landMap.baseMap[VData.locY - 1][VData.locX],
									false);
						} else if (halfDownY) {
							runLocate(
									VData.landMap.baseMap[VData.locY][VData.locX],
									false);
						}
						// runLocate(2010, false);
						// mapBase = new RMapCity9(m_context, false);
						for (int i = 0; i < VData.allSIZE.y / 2; i++) {
							for (int j = 0; j < VData.allSIZE.x; j++) {
								logicMap[i][j] = mapBase.logicMap[i
										+ VData.allSIZE.y / 2][j];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x + 1; j++) {
								imageMap0[i][j] = mapBase.imageMap0[i
										+ VData.allSIZE.y / 2][j];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x + 1; j++) {
								imageMap1[i][j] = mapBase.imageMap1[i
										+ VData.allSIZE.y / 2][j];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x + 1; j++) {
								imageMap2[i][j] = mapBase.imageMap2[i
										+ VData.allSIZE.y / 2][j];
							}
						}
						// 下半侧
						if (halfUpperY) {
							runLocate(
									VData.landMap.baseMap[VData.locY][VData.locX],
									false);
						} else if (halfDownY) {
							runLocate(
									VData.landMap.baseMap[VData.locY + 1][VData.locX],
									false);
						}
						// runLocate(2011, false);
						// mapBase = new RMapCity0(m_context, false);
						for (int i = VData.allSIZE.y / 2; i < VData.allSIZE.y; i++) {
							for (int j = 0; j < VData.allSIZE.x; j++) {
								logicMap[i][j] = mapBase.logicMap[i
										- VData.allSIZE.y / 2][j];
							}
						}
						for (int i = VData.allSIZE.y / 2 + 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x + 1; j++) {
								imageMap0[i][j] = mapBase.imageMap0[i
										- VData.allSIZE.y / 2][j];
							}
						}
						for (int i = VData.allSIZE.y / 2 + 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x + 1; j++) {
								imageMap1[i][j] = mapBase.imageMap1[i
										- VData.allSIZE.y / 2][j];
							}
						}
						for (int i = VData.allSIZE.y / 2 + 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x + 1; j++) {
								imageMap2[i][j] = mapBase.imageMap2[i
										- VData.allSIZE.y / 2][j];
							}
						}
						iniRole(false);
					} else {
						// if (mapBase.holyidY == 0) {
						// Log.i("wiiPM", "正上-->右上");
						// flyLocate(2010, false);
						// } else if (mapBase.holyidY == 1) {
						// Log.i("wiiPM", "正下-->右下");
						// flyLocate(2011, false);
						// }
						// 向右正式走动一格
						flyLocate(VData.landMap.baseMap[VData.locY][VData.locX],
								false);
						iniRole(false);
					}
				}
				break;
			case 2:
				// 表示向下
				upperEdge = false;
				if (halfUpperY) {
					halfUpperY = false;
					directFlag = false;
				} else {
					halfDownY = !halfDownY;
				}
				if (halfDownY) {
					if (halfLeftX || halfRightX) {
						// 左半侧
						Log.i("wiiPM", "正上-->中央");
						if (halfLeftX) {
							runLocate(
									VData.landMap.baseMap[VData.locY + 1][VData.locX - 1],
									false);
						} else if (halfRightX) {
							runLocate(
									VData.landMap.baseMap[VData.locY + 1][VData.locX],
									false);
						}
						// runLocate(2001, false);
						// mapBase = new RMapFull(m_context, false);
						for (int i = 0; i < VData.allSIZE.y / 2; i++) {
							for (int j = 0; j < VData.allSIZE.x / 2; j++) {
								logicMap[i][j] = logicMap[i + VData.allSIZE.y / 2][j];
								logicMap[i + VData.allSIZE.y / 2][j] = mapBase.logicMap[i][j
										+ VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap0[i][j] = imageMap0[i + VData.allSIZE.y / 2][j];
								imageMap0[i + VData.allSIZE.y / 2][j] = mapBase.imageMap0[i][j
										+ VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap1[i][j] = imageMap1[i + VData.allSIZE.y / 2][j];
								imageMap1[i + VData.allSIZE.y / 2][j] = mapBase.imageMap1[i][j
										+ VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap2[i][j] = imageMap2[i + VData.allSIZE.y / 2][j];
								imageMap2[i + VData.allSIZE.y / 2][j] = mapBase.imageMap2[i][j
										+ VData.allSIZE.x / 2];
							}
						}
						// 右半侧
						if (halfLeftX) {
							runLocate(
									VData.landMap.baseMap[VData.locY + 1][VData.locX],
									false);
						} else if (halfRightX) {
							runLocate(
									VData.landMap.baseMap[VData.locY + 1][VData.locX + 1],
									false);
						}
						// runLocate(2011, false);
						// mapBase = new RMapCity0(m_context, false);
						for (int i = 0; i < VData.allSIZE.y / 2; i++) {
							for (int j = VData.allSIZE.x / 2; j < VData.allSIZE.x; j++) {
								logicMap[i][j] = logicMap[i + VData.allSIZE.y / 2][j];
								logicMap[i + VData.allSIZE.y / 2][j] = mapBase.logicMap[i][j
										- VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = VData.allSIZE.x / 2 + 1; j < VData.allSIZE.x + 1; j++) {
								imageMap0[i][j] = imageMap0[i + VData.allSIZE.y / 2][j];
								imageMap0[i + VData.allSIZE.y / 2][j] = mapBase.imageMap0[i][j
										- VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = VData.allSIZE.x / 2 + 1; j < VData.allSIZE.x + 1; j++) {
								imageMap1[i][j] = imageMap1[i + VData.allSIZE.y / 2][j];
								imageMap1[i + VData.allSIZE.y / 2][j] = mapBase.imageMap1[i][j
										- VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = VData.allSIZE.x / 2 + 1; j < VData.allSIZE.x + 1; j++) {
								imageMap2[i][j] = imageMap2[i + VData.allSIZE.y / 2][j];
								imageMap2[i + VData.allSIZE.y / 2][j] = mapBase.imageMap2[i][j
										- VData.allSIZE.x / 2];
							}
						}
						iniRole(false);
					} else {
						// if (mapBase.holyidX == 0) {
						// Log.i("wiiPM", "左上-->正左");
						// runLocate(2001, false);
						// // mapBase = new RMapFull(m_context, false);
						// } else if (mapBase.holyidX == 1) {
						// Log.i("wiiPM", "右上-->正右");
						// runLocate(2011, false);
						// // mapBase = new RMapCity0(m_context, false);
						// }
						runLocate(
								VData.landMap.baseMap[VData.locY + 1][VData.locX],
								false);
						for (int i = 0; i < VData.allSIZE.y / 2; i++) {
							for (int j = 0; j < VData.allSIZE.x; j++) {
								logicMap[i][j] = logicMap[i + VData.allSIZE.y / 2][j];
								logicMap[i + VData.allSIZE.y / 2][j] = mapBase.logicMap[i][j];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x + 1; j++) {
								imageMap0[i][j] = imageMap0[i + VData.allSIZE.y / 2][j];
								imageMap0[i + VData.allSIZE.y / 2][j] = mapBase.imageMap0[i][j];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x + 1; j++) {
								imageMap1[i][j] = imageMap1[i + VData.allSIZE.y / 2][j];
								imageMap1[i + VData.allSIZE.y / 2][j] = mapBase.imageMap1[i][j];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x + 1; j++) {
								imageMap2[i][j] = imageMap2[i + VData.allSIZE.y / 2][j];
								imageMap2[i + VData.allSIZE.y / 2][j] = mapBase.imageMap2[i][j];
							}
						}
						iniRole(false);
					}
				} else {
					if (directFlag) {
						VData.locY++;
						if (VData.locY + 1 == VData.landMap.baseY) {
							downEdge = true;
						}
					} else {
						directFlag = true;
					}
					if (halfLeftX || halfRightX) {
						// 左半侧
						Log.i("wiiPM", "中央-->正下");
						if (halfLeftX) {
							runLocate(
									VData.landMap.baseMap[VData.locY][VData.locX - 1],
									false);
						} else if (halfRightX) {
							runLocate(
									VData.landMap.baseMap[VData.locY][VData.locX],
									false);
						}
						// runLocate(2001, false);
						// mapBase = new RMapFull(m_context, false);
						for (int i = 0; i < VData.allSIZE.y; i++) {
							for (int j = 0; j < VData.allSIZE.x / 2; j++) {
								logicMap[i][j] = mapBase.logicMap[i][j
										+ VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap0[i][j] = mapBase.imageMap0[i][j
										+ VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap1[i][j] = mapBase.imageMap1[i][j
										+ VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap2[i][j] = mapBase.imageMap2[i][j
										+ VData.allSIZE.x / 2];
							}
						}
						// 右半侧
						if (halfLeftX) {
							runLocate(
									VData.landMap.baseMap[VData.locY][VData.locX],
									false);
						} else if (halfRightX) {
							runLocate(
									VData.landMap.baseMap[VData.locY][VData.locX + 1],
									false);
						}
						// runLocate(2011, false);
						// mapBase = new RMapCity0(m_context, false);
						for (int i = 0; i < VData.allSIZE.y; i++) {
							for (int j = VData.allSIZE.x / 2; j < VData.allSIZE.x; j++) {
								logicMap[i][j] = mapBase.logicMap[i][j
										- VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = VData.allSIZE.x / 2 + 1; j < VData.allSIZE.x + 1; j++) {
								imageMap0[i][j] = mapBase.imageMap0[i][j
										- VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = VData.allSIZE.x / 2 + 1; j < VData.allSIZE.x + 1; j++) {
								imageMap1[i][j] = mapBase.imageMap1[i][j
										- VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = VData.allSIZE.x / 2 + 1; j < VData.allSIZE.x + 1; j++) {
								imageMap2[i][j] = mapBase.imageMap2[i][j
										- VData.allSIZE.x / 2];
							}
						}
						iniRole(false);
					} else {
						// if (mapBase.holyidX == 0) {
						// Log.i("wiiPM", "正左-->左下");
						// flyLocate(2001, false);
						// } else if (mapBase.holyidX == 1) {
						// Log.i("wiiPM", "正右-->右下");
						// flyLocate(2011, false);
						// }
						// 向下正式走动一格
						flyLocate(VData.landMap.baseMap[VData.locY][VData.locX],
								false);
						iniRole(false);
					}
				}
				break;
			case 3:
				// 表示向左
				rightEdge = false;
				if (halfRightX) {
					halfRightX = false;
					directFlag = false;
				} else {
					halfLeftX = !halfLeftX;
				}
				if (halfLeftX) {
					if (halfUpperY || halfDownY) {
						// 上半侧
						Log.i("wiiPM", "正右-->中央");
						if (halfUpperY) {
							runLocate(
									VData.landMap.baseMap[VData.locY - 1][VData.locX - 1],
									false);
						} else if (halfDownY) {
							runLocate(
									VData.landMap.baseMap[VData.locY][VData.locX - 1],
									false);
						}
						// runLocate(2000, false);
						// mapBase = new RMapNull(m_context, false);
						for (int i = 0; i < VData.allSIZE.y / 2; i++) {
							for (int j = 0; j < VData.allSIZE.x / 2; j++) {
								logicMap[i][j + VData.allSIZE.x / 2] = logicMap[i][j];
								logicMap[i][j] = mapBase.logicMap[i
										+ VData.allSIZE.y / 2][j + VData.allSIZE.x
										/ 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap0[i][j + VData.allSIZE.x / 2] = imageMap0[i][j];
								imageMap0[i][j] = mapBase.imageMap0[i
										+ VData.allSIZE.y / 2][j + VData.allSIZE.x
										/ 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap1[i][j + VData.allSIZE.x / 2] = imageMap1[i][j];
								imageMap1[i][j] = mapBase.imageMap1[i
										+ VData.allSIZE.y / 2][j + VData.allSIZE.x
										/ 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap2[i][j + VData.allSIZE.x / 2] = imageMap2[i][j];
								imageMap2[i][j] = mapBase.imageMap2[i
										+ VData.allSIZE.y / 2][j + VData.allSIZE.x
										/ 2];
							}
						}
						// 下半侧
						if (halfUpperY) {
							runLocate(
									VData.landMap.baseMap[VData.locY][VData.locX - 1],
									false);
						} else if (halfDownY) {
							runLocate(
									VData.landMap.baseMap[VData.locY + 1][VData.locX - 1],
									false);
						}
						// runLocate(2001, false);
						// mapBase = new RMapFull(m_context, false);
						for (int i = VData.allSIZE.y / 2; i < VData.allSIZE.y; i++) {
							for (int j = 0; j < VData.allSIZE.x / 2; j++) {
								logicMap[i][j + VData.allSIZE.x / 2] = logicMap[i][j];
								logicMap[i][j] = mapBase.logicMap[i
										- VData.allSIZE.y / 2][j + VData.allSIZE.x
										/ 2];
							}
						}
						for (int i = VData.allSIZE.y / 2 + 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap0[i][j + VData.allSIZE.x / 2] = imageMap0[i][j];
								imageMap0[i][j] = mapBase.imageMap0[i
										- VData.allSIZE.y / 2][j + VData.allSIZE.x
										/ 2];
							}
						}
						for (int i = VData.allSIZE.y / 2 + 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap1[i][j + VData.allSIZE.x / 2] = imageMap1[i][j];
								imageMap1[i][j] = mapBase.imageMap1[i
										- VData.allSIZE.y / 2][j + VData.allSIZE.x
										/ 2];
							}
						}
						for (int i = VData.allSIZE.y / 2 + 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap2[i][j + VData.allSIZE.x / 2] = imageMap2[i][j];
								imageMap2[i][j] = mapBase.imageMap2[i
										- VData.allSIZE.y / 2][j + VData.allSIZE.x
										/ 2];
							}
						}
						iniRole(false);
					} else {
						// if (mapBase.holyidY == 0) {
						// Log.i("wiiPM", "右上-->正上");
						// runLocate(2000, false);
						// // mapBase = new RMapNull(m_context, false);
						// } else if (mapBase.holyidY == 1) {
						// Log.i("wiiPM", "右下-->正下");
						// runLocate(2001, false);
						// // mapBase = new RMapFull(m_context, false);
						// }
						runLocate(
								VData.landMap.baseMap[VData.locY][VData.locX - 1],
								false);
						for (int i = 0; i < VData.allSIZE.y; i++) {
							for (int j = 0; j < VData.allSIZE.x / 2; j++) {
								logicMap[i][j + VData.allSIZE.x / 2] = logicMap[i][j];
								logicMap[i][j] = mapBase.logicMap[i][j
										+ VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap0[i][j + VData.allSIZE.x / 2] = imageMap0[i][j];
								imageMap0[i][j] = mapBase.imageMap0[i][j
										+ VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap1[i][j + VData.allSIZE.x / 2] = imageMap1[i][j];
								imageMap1[i][j] = mapBase.imageMap1[i][j
										+ VData.allSIZE.x / 2];
							}
						}
						for (int i = 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x / 2 + 1; j++) {
								imageMap2[i][j + VData.allSIZE.x / 2] = imageMap2[i][j];
								imageMap2[i][j] = mapBase.imageMap2[i][j
										+ VData.allSIZE.x / 2];
							}
						}
						iniRole(false);
					}
				} else {
					if (directFlag) {
						VData.locX--;
						if (VData.locX == 0) {
							leftEdge = true;
						}
					} else {
						directFlag = true;
					}
					if (halfUpperY || halfDownY) {
						// 上半侧
						Log.i("wiiPM", "中央-->正左");
						if (halfUpperY) {
							runLocate(
									VData.landMap.baseMap[VData.locY - 1][VData.locX],
									false);
						} else if (halfDownY) {
							runLocate(
									VData.landMap.baseMap[VData.locY][VData.locX],
									false);
						}
						// runLocate(2000, false);
						// mapBase = new RMapNull(m_context, false);
						for (int i = 0; i < VData.allSIZE.y / 2; i++) {
							for (int j = 0; j < VData.allSIZE.x; j++) {
								logicMap[i][j] = mapBase.logicMap[i
										+ VData.allSIZE.y / 2][j];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x + 1; j++) {
								imageMap0[i][j] = mapBase.imageMap0[i
										+ VData.allSIZE.y / 2][j];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x + 1; j++) {
								imageMap1[i][j] = mapBase.imageMap1[i
										+ VData.allSIZE.y / 2][j];
							}
						}
						for (int i = 1; i < VData.allSIZE.y / 2 + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x + 1; j++) {
								imageMap2[i][j] = mapBase.imageMap2[i
										+ VData.allSIZE.y / 2][j];
							}
						}
						// 下半侧
						if (halfUpperY) {
							runLocate(
									VData.landMap.baseMap[VData.locY][VData.locX],
									false);
						} else if (halfDownY) {
							runLocate(
									VData.landMap.baseMap[VData.locY + 1][VData.locX],
									false);
						}
						// runLocate(2001, false);
						// mapBase = new RMapFull(m_context, false);
						for (int i = VData.allSIZE.y / 2; i < VData.allSIZE.y; i++) {
							for (int j = 0; j < VData.allSIZE.x; j++) {
								logicMap[i][j] = mapBase.logicMap[i
										- VData.allSIZE.y / 2][j];
							}
						}
						for (int i = VData.allSIZE.y / 2 + 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x + 1; j++) {
								imageMap0[i][j] = mapBase.imageMap0[i
										- VData.allSIZE.y / 2][j];
							}
						}
						for (int i = VData.allSIZE.y / 2 + 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x + 1; j++) {
								imageMap1[i][j] = mapBase.imageMap1[i
										- VData.allSIZE.y / 2][j];
							}
						}
						for (int i = VData.allSIZE.y / 2 + 1; i < VData.allSIZE.y + 1; i++) {
							for (int j = 1; j < VData.allSIZE.x + 1; j++) {
								imageMap2[i][j] = mapBase.imageMap2[i
										- VData.allSIZE.y / 2][j];
							}
						}
						iniRole(false);
					} else {
						// if (mapBase.holyidY == 0) {
						// Log.i("wiiPM", "正上-->左上");
						// flyLocate(2000, false);
						// } else if (mapBase.holyidY == 1) {
						// Log.i("wiiPM", "正下-->左下");
						// flyLocate(2001, false);
						// }
						// 向左正式走动一格
						flyLocate(VData.landMap.baseMap[VData.locY][VData.locX],
								false);
						iniRole(false);
					}
				}
				break;
		}
		MDraw.reMapInfo();
	}

	/****************************/
	public void reFresh() {
		mapBase.reFresh();
	}

	public int[][] getLogic() {
		return logicMap;
	}

	public Bitmap[][] getImage0() {
		return imageMap0;
	}

	public Bitmap[][] getImage1() {
		return imageMap1;
	}

	public Bitmap[][] getImage2() {
		return imageMap2;
	}

	public void halfDirect() {
		halfUpperY = false;
		halfRightX = false;
		halfDownY = false;
		halfLeftX = false;
	}
}