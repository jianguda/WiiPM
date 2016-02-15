package com.yurset.wiipm.logi;

import com.yurset.wiipm.base.APoint;
import com.yurset.wiipm.base.FPublic;
import com.yurset.wiipm.erox.CLandMap;
import com.yurset.wiipm.erox.SFairyRent;
import com.yurset.wiipm.fight.XBattle;
import com.yurset.wiipm.main.IGame;
import com.yurset.wiipm.vect.SHero;
import com.yurset.wiipm.vect.SFell;

import android.content.Context;
import android.util.Log;

public class VData {
	// ========================= 成员变量 ================================
	public static SHero _hero = null;
	public static SFell _fell = null;
	public static SFairyRent _rent = null;
	public static XBattle xBattle = null;

	public static final int ETHER = 99;

	public static int CORNER = ETHER;

	public static APoint so = null;
	public static APoint pe = null;

	public static final int UPPER = 0; // 点击向上
	public static final int RIGHT = 1; // 点击向右
	public static final int DOWN = 2; // 点击向下
	public static final int LEFT = 3; // 点击向左

	public static final int UNIT = 80;// FPublic.SCREEN.heightPixels / 9;
	public static final int STEP = 16;// 行动步数
	public static final int MOVE = UNIT / STEP;// 移动单位
	public static final int roleSize = 3 * UNIT / 2;

	public static APoint allMAX = new APoint(31, 31);// 随机的尺寸是32*48
	public static APoint allSIZE = new APoint(32, 32);// 对应的是33*49

	public static int xIndex = 0; // 屏幕-1-1对应起点X
	public static int yIndex = 0; // 屏幕-1-1对应起点Y

	public static int xOffset = 0; // 屏幕起点X后置偏移
	public static int yOffset = 0; // 屏幕起点Y后置偏移

	public static final int MOVE_BACK = 0; // 向后
	public static final int MOVE_RIGHT = 1; // 向右
	public static final int MOVE_FACE = 2; // 正面
	public static final int MOVE_LEFT = 3; // 向左
	public static final int STAY_BACK = 10;
	public static final int STAY_RIGHT = 11;
	public static final int STAY_FACE = 12;
	public static final int STAY_LEFT = 13;

	public static final int TAG_UNUSE = 0; // 阻碍
	public static final int TAG_WALLE = 1; // 木墙
	public static final int TAG_STONE = 2; // 石墙

	public static final int TAG_EMPTY = 3; // 空地
	public static final int TAG_FIGHT = 4; // 对战
	public static final int TAG_GRAZZ = 5; // 遭遇

	public static final int TAG_MASTER = 6; // 主人
	public static final int TAG_FELLOW = 7; // 跟随
	public static final int TAG_AFFAIR = 8; // 事件
	public static final int TAG_TARGET = 9; // 目的

	public static int dosX = FPublic.SCREEN.widthPixels / UNIT;// 16
	public static int dosY = FPublic.SCREEN.heightPixels / UNIT;// 9

	public int springHX = 0;
	public int springHY = 0;
	public int springPX = 0;
	public int springPY = 0;

	public int springMX = -UNIT;
	public int springMY = -UNIT;

	private boolean synch = true;
	// 选用空地的标志
	public static int noteHero = 3;
	public static int noteFell = 3;
	// 支持小地图功能
	private boolean hereHero = true;
	private boolean hereFell = true;

	// 当前超大地图坐标
	public static int locY = 0;
	public static int locX = 0;
	// 初始地点，加上存档后修改为当前城市
	public static int iniLocate = 0;
	public static CLandMap landMap = null;

	// 进入空间的标记
	public static boolean siteFlag = false;

	// ==================================================================
	// ========================= 成员函数 ================================
	public VData(Context context) {
		// 0.07s
		_hero = new SHero(context);
		// 0.17s
		_fell = new SFell(context);
		//
		_rent = new SFairyRent(context);
		landMap = new CLandMap();
		xBattle = new XBattle(context);
		reBase(iniLocate);
	}

	public void reFresh() {
		_hero.reFresh();
		_fell.reFresh();
		_rent.reFresh();
	}

	public void reBase(int node) {
		if (node >= 0 && node <= 10) {
			// 使用静态变量记录以便处理新生对象的情况
			iniLocate = node;
			// 修正父母地图块的偏移
			_rent.halfDirect();
			locY = landMap.baseCity[iniLocate] / landMap.baseX;
			locX = landMap.baseCity[iniLocate] - locY * landMap.baseX;
		} else if (node == -100 || node >= 100 && node <= 199) {
			siteFlag = true;
		}
		_rent.flyLocate(node, true);
		_rent.iniRole(true);
		MDraw.reMapInfo();
	}

	public void majorMove(int index) {
		if (hereHero) {
			MDraw.logicMap[so.y][so.x] = noteHero;
			hereHero = false;
		}
		switch (index) {
			case UPPER:
				_hero.markS = MOVE_BACK;
				if (ableMove(so.x, so.y - 1)) {
					if (so.y - yIndex <= dosY / 2 && yIndex > 0) {
						if (springMY + MOVE == 0) {
							springMY = -UNIT;
							yIndex--;
							yOffset--;
							so.y--;
						} else {
							springMY += MOVE;
						}
					} else {
						// 向上无法移动地图块
						// 生成偏上的母地图域
						if (_rent.rogueFlag || _rent.roomFlag) {
							if (springHY + UNIT == MOVE) {
								so.y--;
								springHY = 0;
							} else {
								springHY -= MOVE;
							}
						} else {
							if (_rent.upperEdge
									|| (_rent.downEdge && so.y >= allSIZE.y / 2)) {
								if (springHY + UNIT == MOVE) {
									so.y--;
									springHY = 0;
								} else {
									springHY -= MOVE;
								}
							} else {
								_rent.generate(UPPER);
								so.y += allSIZE.y / 2;
								pe.y += allSIZE.y / 2;
								yIndex = so.y - dosY / 2;
								if (springMY + MOVE == 0) {
									springMY = -UNIT;
									yIndex--;
									yOffset--;
									so.y--;
								} else {
									springMY += MOVE;
								}
							}
						}
					}
				}
				break;
			case RIGHT:
				_hero.markS = MOVE_RIGHT;
				if (ableMove(so.x + 1, so.y)) {
					if (so.x - xIndex >= dosX / 2 && allMAX.x - xIndex >= dosX) {
						if (springMX + 2 * UNIT == MOVE) {
							springMX = -UNIT;
							xIndex++;
							xOffset++;
							so.x++;
						} else {
							springMX -= MOVE;
						}
					} else {
						// 向右无法移动地图块
						// 生成偏右的母地图域
						if (_rent.rogueFlag || _rent.roomFlag) {
							if (springHX + MOVE == UNIT) {
								so.x++;
								springHX = 0;
							} else {
								springHX += MOVE;
							}
						} else {
							if (_rent.rightEdge
									|| (_rent.leftEdge && so.x < allSIZE.x / 2)) {
								if (springHX + MOVE == UNIT) {
									so.x++;
									springHX = 0;
								} else {
									springHX += MOVE;
								}
							} else {
								_rent.generate(RIGHT);
								so.x -= allSIZE.x / 2;
								pe.x -= allSIZE.x / 2;
								xIndex = so.x - dosX / 2;
								if (springMX + 2 * UNIT == MOVE) {
									springMX = -UNIT;
									xIndex++;
									xOffset++;
									so.x++;
								} else {
									springMX -= MOVE;
								}
							}
						}
					}
				}
				break;
			case DOWN:
				_hero.markS = MOVE_FACE;
				if (ableMove(so.x, so.y + 1)) {
					if (so.y - yIndex >= dosY / 2 && allMAX.y - yIndex >= dosY) {
						if (springMY + 2 * UNIT == MOVE) {
							springMY = -UNIT;
							yIndex++;
							yOffset++;
							so.y++;
						} else {
							springMY -= MOVE;
						}
					} else {
						// 向下无法移动地图块
						// 生成偏下的母地图域
						if (_rent.rogueFlag || _rent.roomFlag) {
							if (springHY + MOVE == UNIT) {
								so.y++;
								springHY = 0;
							} else {
								springHY += MOVE;
							}
						} else {
							if (_rent.downEdge
									|| (_rent.upperEdge && so.y < allSIZE.y / 2)) {
								if (springHY + MOVE == UNIT) {
									so.y++;
									springHY = 0;
								} else {
									springHY += MOVE;
								}
							} else {
								_rent.generate(DOWN);
								so.y -= allSIZE.y / 2;
								pe.y -= allSIZE.y / 2;
								yIndex = so.y - dosY / 2;
								if (springMY + 2 * UNIT == MOVE) {
									springMY = -UNIT;
									yIndex++;
									yOffset++;
									so.y++;
								} else {
									springMY -= MOVE;
								}
							}
						}
					}
				}
				break;
			case LEFT:
				_hero.markS = MOVE_LEFT;
				if (ableMove(so.x - 1, so.y)) {
					if (so.x - xIndex <= dosX / 2 && xIndex > 0) {
						if (springMX + MOVE == 0) {
							springMX = -UNIT;
							xIndex--;
							xOffset--;
							so.x--;
						} else {
							springMX += MOVE;
						}
					} else {
						// 向左无法移动地图块
						// 生成偏左的母地图域
						if (_rent.rogueFlag || _rent.roomFlag) {
							if (springHX + UNIT == MOVE) {
								so.x--;
								springHX = 0;
							} else {
								springHX -= MOVE;
							}
						} else {
							if (_rent.leftEdge
									|| (_rent.rightEdge && so.x >= allSIZE.x / 2)) {
								if (springHX + UNIT == MOVE) {
									so.x--;
									springHX = 0;
								} else {
									springHX -= MOVE;
								}
							} else {
								_rent.generate(LEFT);
								so.x += allSIZE.x / 2;
								pe.x += allSIZE.x / 2;
								xIndex = so.x - dosX / 2;
								if (springMX + MOVE == 0) {
									springMX = -UNIT;
									xIndex--;
									xOffset--;
									so.x--;
								} else {
									springMX += MOVE;
								}
							}
						}
					}
				}
				break;
		}
		if (MDraw.logicMap[so.y][so.x] != TAG_MASTER) {
			noteHero = MDraw.logicMap[so.y][so.x];
			if (noteHero == TAG_FELLOW)
				noteHero = noteFell;
			MDraw.logicMap[so.y][so.x] = TAG_MASTER;
			hereHero = true;
		}
	}

	public void minorMove(int index) {
		if (!synch) {
			return;
		}
		if (hereFell) {
			MDraw.logicMap[pe.y][pe.x] = noteFell;
			hereFell = false;
		}
		switch (index) {
			case UPPER:
				_fell.markS = MOVE_BACK;
				if (springPY + UNIT == MOVE) {
					pe.y--;
					springPY = 0;
					if (IGame.flowerFlag) {
						_rent.mapBase.fitFlower();
					}
				} else {
					springPY -= MOVE;
				}
				break;
			case RIGHT:
				_fell.markS = MOVE_RIGHT;
				if (springPX + MOVE == UNIT) {
					pe.x++;
					springPX = 0;
					if (IGame.flowerFlag) {
						_rent.mapBase.fitFlower();
					}
				} else {
					springPX += MOVE;
				}
				break;
			case DOWN:
				_fell.markS = MOVE_FACE;
				if (springPY + MOVE == UNIT) {
					pe.y++;
					springPY = 0;
					if (IGame.flowerFlag) {
						_rent.mapBase.fitFlower();
					}
				} else {
					springPY += MOVE;
				}
				break;
			case LEFT:
				_fell.markS = MOVE_LEFT;
				if (springPX + UNIT == MOVE) {
					pe.x--;
					springPX = 0;
					if (IGame.flowerFlag) {
						_rent.mapBase.fitFlower();
					}
				} else {
					springPX -= MOVE;
				}
				break;
		}

		if (MDraw.logicMap[pe.y][pe.x] != TAG_FELLOW) {
			noteFell = MDraw.logicMap[pe.y][pe.x];
			if (noteFell == TAG_MASTER)
				noteFell = noteHero;
			MDraw.logicMap[pe.y][pe.x] = TAG_FELLOW;
			hereFell = true;
		}
		synch = true;
	}

	private boolean ableMove(int x, int y) {
		boolean temp = false;
		if (x >= 0 && x <= allMAX.x && y >= 0 && y <= allMAX.y) {
			if (MDraw.logicMap[y][x] > 2 && MDraw.logicMap[y][x] < 10
					|| MDraw.logicMap[y][x] > 99) {
				temp = true;
			}
		}
		synch = temp;
		return synch;
	}

	public static void relySize(int y, int x) {
		allMAX = new APoint(y - 1, x - 1);
		allSIZE = new APoint(y, x);
	}
}