package com.yurset.wiipm.main;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import com.yurset.wiipm.R;
import com.yurset.wiipm.base.APath;
import com.yurset.wiipm.base.FPublic;
import com.yurset.wiipm.base.IView;
import com.yurset.wiipm.base.APoint;
import com.yurset.wiipm.base.LoChatss;
import com.yurset.wiipm.base.McVisety;
import com.yurset.wiipm.base.NoSwitch;
import com.yurset.wiipm.base.UGifView;
import com.yurset.wiipm.fight.XBattle;
import com.yurset.wiipm.fight.XInfo;
import com.yurset.wiipm.logi.MDraw;
import com.yurset.wiipm.logi.SAstar;
import com.yurset.wiipm.logi.VData;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class IGame extends IView {
	// ========================= 成员变量 ================================
	public VData _data = null;
	public MDraw _draw = null;
	public static XInfo _info = null;
	public Point[] tileBoard = null;// y*x tile
	public Vector<Integer> heroTouch = null;
	public Vector<Integer> petTouch = null;

	public int maskH, maskP;
	private int touchY, touchX;
	public static int tthY, tthX;
	public static boolean guideFlag = false;
	private boolean hintFlag = false;
	private boolean miniFlag = false;
	private boolean heroFlag = false;
	private boolean dexFlag = false;
	private boolean mapFlag = false;
	private boolean viaFlag = false;
	private boolean penFlag = false;
	private boolean teamFlag = false;
	private boolean homeFlag = false;
	private boolean fightFlag = false;
	private boolean fsFightFlag = false;
	private boolean fsTeamFlag = false;
	private boolean fsItemFlag = false;
	private boolean battleFlag = false;
	private int gymChatTurns[] = { 1, 1, 1, 1, 1, 1, 1, 1 };
	private int eliteChatTurns[] = { 1, 1, 1, 1 };
	private int chatCurrs = 0;
	private int npcssCurr = 0;
	public static boolean bfightFlag = false;
	private boolean ignoreFlag = false;
	public static boolean doorFlag = false;
	public static int buildId = 0;
	// 检查捕捉次数
	private boolean captureOnce = true;

	private int mazz = 0;
	private int px, py;
	private int cx, cy;
	private float disX, disY;
	private int pLen, cLen;

	private long pt, ct;
	private int unit = 8;
	private int deadDelay = 10;

	private float initX, initY;
	private boolean initFlag = false;

	private APath mPath0 = null;
	private APath mPath1 = null;
	private SurfaceHolder mSurfaceHolder = null;

	private int zoneX = 0, zoneY = 0;
	private int fightX = 0, fightY = 0;
	public int affairX = 0, affairY = 0;
	public int npcssX = 0, npcssY = 0;

	// 遇到PM数，打开图鉴面板时计算
	public static int dexV1 = 0;
	// 捕获PM数，打开图鉴面板时计算
	public static int dexV2 = 0;
	// 0未遇到 1未捕获 2捕获
	public static int[] dexValue = null;

	public static int currPM = 0;
	public static int currCry = 0;
	public static boolean cryBool = false;
	public static int mode = 0;
	public static int pmSeq = 0;
	public static int oopDex[] = new int[6];
	public static int oopLv[] = new int[6];
	public static int oopKidney[] = new int[6];
	public static int oopMove[][] = new int[6][4];
	public static int moveH = -1;
	public static int moveHClick = 8;
	public static int itemH = -1;
	public static int itemHClick = 8;
	public static int chatRole = 0;
	public static int chatLevel = 0;
	public static String chatStr[][] = null;
	public int battleTeam[] = { 0, 0, 0, 0, 0, 0 };
	// 记录挑战道馆的情况【0未挑战 1挑战成功 2挑战失败 3挑战结束 4冠军客套】
	public static int gymProgress[] = { 0, 0, 0, 0, 0, 0, 0, 0 };
	public static int eliteProgress[] = { 0, 0, 0, 0 };

	public static int moodClick = 0;
	public static boolean drawFlag = false;
	public static boolean fellFlag = false;
	public static UGifView gifViewB = null;
	public static UGifView gifViewF = null;
	public static boolean flowerFlag = false;
	public MediaPlayer playerF = null;
	public MediaPlayer playerN = null;
	public MediaPlayer playerS = null;
	public static SoundPool soundPool = null;
	public static HashMap<Integer, Integer> soundPoolMap = null;

	public static boolean dexUpper = false;
	public static boolean dexDown = false;

	private boolean moveFightFlag = true;
	private boolean teamChanged = false;

	public static String guide = "";
	public static int guideClick = 128;
	private String hint = "";
	private int hintClick = 64;

	// ==================================================================
	// ========================= 成员函数 ================================
	public IGame(Context v_context, UGifView gifViewB, UGifView gifViewF) {
		super(v_context);
		this.gifViewB = gifViewB;
		this.gifViewF = gifViewF;
		// 1.2s
		_data = new VData(m_context);
		_draw = new MDraw(m_context);

		zoneX = FPublic.SCREEN.widthPixels * 27 / 32;
		zoneY = FPublic.SCREEN.heightPixels * 5 / 18;
		mSurfaceHolder = this.getHolder();
		mSurfaceHolder.addCallback(this);

		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		soundPoolMap = new HashMap<Integer, Integer>();
		playerF = MediaPlayer.create(m_context, R.raw.frontier);
		playerN = MediaPlayer.create(m_context, R.raw.normal);
		playerS = MediaPlayer.create(m_context, R.raw.special);
		playerF.setLooping(true);
		playerN.setLooping(true);
		playerS.setLooping(true);
		playerF.start();

		for (int i = 0; i < XInfo.hTeamC; i++)
			soundPoolMap.put(i, soundPool.load(m_context,
					NoSwitch.cryXXX[XInfo.teamHIndex[i]], 1));
		InitData();
		this.setOnTouchListener(new IView.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction() & MotionEvent.ACTION_MASK) {
					case MotionEvent.ACTION_DOWN:// 单点触摸动作
						mode = 1;
						px = (int) event.getX();
						py = (int) event.getY();
						mPath0 = new APath(px, py);
						mPath0.move(px - 2, py - 2);
						mPath0.move(px - 2, py + 2);
						mPath0.move(px + 2, py + 2);
						mPath0.move(px + 2, py - 2);
						pt = event.getDownTime(); // 按下开始时间
						initFlag = true;
						// 单点触摸时响应
						if (battleFlag) {
							if (ignoreFlag) {
								battleFlag = false;
								ignoreFlag = false;
							} else {
								if (!bfightFlag) {
									if (chatRole == 0) {
										chatRole = 1;
									} else {
										chatRole = 0;
										chatLevel++;
										if (chatLevel >= chatStr[0].length) {
											chatLevel = 0;
											switch (VData.landMap.baseMap[VData.locY][VData.locX]) {
												case 0:
													break;
												case 1:
													switch (gymProgress[0]) {
														case 1:
															gymProgress[0] = 3;
															chatStr = LoChatss.gymd00;
															gymChatTurns[0] = 0;
															break;
														case 2:
															gymProgress[0] = 0;
															chatStr = LoChatss.gyma00;
															break;
													}
													if (chatCurrs < gymChatTurns[0]) {
														chatCurrs++;
														if (chatCurrs == 1) {
															bfightFlag = true;
														}
													} else {
														chatCurrs = 0;
														ignoreFlag = true;
													}
													break;
												case 2:
													switch (gymProgress[1]) {
														case 1:
															gymProgress[1] = 3;
															chatStr = LoChatss.gymd01;
															gymChatTurns[1] = 0;
															break;
														case 2:
															gymProgress[1] = 0;
															chatStr = LoChatss.gyma01;
															break;
													}
													if (chatCurrs < gymChatTurns[1]) {
														chatCurrs++;
														if (chatCurrs == 1) {
															bfightFlag = true;
														}
													} else {
														chatCurrs = 0;
														ignoreFlag = true;
													}
													break;
												case 3:
													switch (gymProgress[2]) {
														case 1:
															gymProgress[2] = 3;
															chatStr = LoChatss.gymd02;
															gymChatTurns[2] = 0;
															break;
														case 2:
															gymProgress[2] = 0;
															chatStr = LoChatss.gyma02;
															break;
													}
													if (chatCurrs < gymChatTurns[2]) {
														chatCurrs++;
														if (chatCurrs == 1) {
															bfightFlag = true;
														}
													} else {
														chatCurrs = 0;
														ignoreFlag = true;
													}
													break;
												case 4:
													switch (gymProgress[3]) {
														case 1:
															gymProgress[3] = 3;
															chatStr = LoChatss.gymd03;
															gymChatTurns[3] = 0;
															break;
														case 2:
															gymProgress[3] = 0;
															chatStr = LoChatss.gyma03;
															break;
													}
													if (chatCurrs < gymChatTurns[3]) {
														chatCurrs++;
														if (chatCurrs == 1) {
															bfightFlag = true;
														}
													} else {
														chatCurrs = 0;
														ignoreFlag = true;
													}
													break;
												case 5:
													switch (gymProgress[4]) {
														case 1:
															gymProgress[4] = 3;
															chatStr = LoChatss.gymd04;
															gymChatTurns[4] = 0;
															break;
														case 2:
															gymProgress[4] = 0;
															chatStr = LoChatss.gyma04;
															break;
													}
													if (chatCurrs < gymChatTurns[4]) {
														chatCurrs++;
														if (chatCurrs == 1) {
															bfightFlag = true;
														}
													} else {
														chatCurrs = 0;
														ignoreFlag = true;
													}
													break;
												case 6:
													switch (gymProgress[5]) {
														case 1:
															gymProgress[5] = 3;
															chatStr = LoChatss.gymd05;
															gymChatTurns[5] = 0;
															break;
														case 2:
															gymProgress[5] = 0;
															chatStr = LoChatss.gyma05;
															break;
													}
													if (chatCurrs < gymChatTurns[5]) {
														chatCurrs++;
														if (chatCurrs == 1) {
															bfightFlag = true;
														}
													} else {
														chatCurrs = 0;
														ignoreFlag = true;
													}
													break;
												case 7:
													switch (gymProgress[6]) {
														case 1:
															gymProgress[6] = 3;
															chatStr = LoChatss.gymd06;
															gymChatTurns[6] = 0;
															break;
														case 2:
															gymProgress[6] = 0;
															chatStr = LoChatss.gyma06;
															break;
													}
													if (chatCurrs < gymChatTurns[6]) {
														chatCurrs++;
														if (chatCurrs == 1) {
															bfightFlag = true;
														}
													} else {
														chatCurrs = 0;
														ignoreFlag = true;
													}
													break;
												case 8:
													switch (gymProgress[7]) {
														case 1:
															gymProgress[7] = 3;
															chatStr = LoChatss.gymd07;
															gymChatTurns[7] = 0;
															break;
														case 2:
															gymProgress[7] = 0;
															chatStr = LoChatss.gyma07;
															break;
													}
													if (chatCurrs < gymChatTurns[7]) {
														chatCurrs++;
														if (chatCurrs == 1) {
															bfightFlag = true;
														}
													} else {
														chatCurrs = 0;
														ignoreFlag = true;
													}
													break;
												case 9:
													switch (npcssCurr) {
														case 610:
															switch (eliteProgress[0]) {
																case 1:
																	eliteProgress[0] = 3;
																	chatStr = LoChatss.elited00;
																	eliteChatTurns[0] = 0;
																	break;
																case 2:
																	eliteProgress[0] = 0;
																	chatStr = LoChatss.elitea00;
																	break;
															}
															if (chatCurrs < eliteChatTurns[0]) {
																chatCurrs++;
																if (chatCurrs == 1) {
																	bfightFlag = true;
																}
															} else {
																chatCurrs = 0;
																ignoreFlag = true;
															}
															break;
														case 611:
															switch (eliteProgress[1]) {
																case 1:
																	eliteProgress[1] = 3;
																	chatStr = LoChatss.elited01;
																	eliteChatTurns[1] = 0;
																	break;
																case 2:
																	eliteProgress[1] = 0;
																	chatStr = LoChatss.elitea01;
																	break;
															}
															if (chatCurrs < eliteChatTurns[1]) {
																chatCurrs++;
																if (chatCurrs == 1) {
																	bfightFlag = true;
																}
															} else {
																chatCurrs = 0;
																ignoreFlag = true;
															}
															break;
														case 612:
															switch (eliteProgress[2]) {
																case 1:
																	eliteProgress[2] = 3;
																	chatStr = LoChatss.elited02;
																	eliteChatTurns[2] = 0;
																	break;
																case 2:
																	eliteProgress[2] = 0;
																	chatStr = LoChatss.elitea02;
																	break;
															}
															if (chatCurrs < eliteChatTurns[2]) {
																chatCurrs++;
																if (chatCurrs == 1) {
																	bfightFlag = true;
																}
															} else {
																chatCurrs = 0;
																ignoreFlag = true;
															}
															break;
														case 613:
															switch (eliteProgress[3]) {
																case 1:
																	eliteProgress[3] = 3;
																	chatStr = LoChatss.elited03;
																	eliteChatTurns[3] = 0;
																	break;
																case 2:
																	eliteProgress[3] = 0;
																	chatStr = LoChatss.elitea03;
																	break;
															}
															if (chatCurrs < eliteChatTurns[3]) {
																chatCurrs++;
																if (chatCurrs == 1) {
																	bfightFlag = true;
																}
															} else {
																chatCurrs = 0;
																ignoreFlag = true;
															}
															break;
													}
													break;
											}
										}
									}
								}
							}
						} else {
							if (dexFlag) {
								// 图鉴面板打开时
								int x = (int) event.getX();
								int y = (int) event.getY();
								if (x > 10 * VData.UNIT
										&& x < 15 * VData.UNIT + VData.UNIT / 4) {
									if (y > VData.UNIT / 2 + VData.UNIT / 4
											&& y < VData.UNIT + 63 * 4) {
										dexUpper = true;
										dexDown = false;
										MDraw.dexSpeed = 0;
										MDraw.dexDelay = 64;
									} else if (y > VData.UNIT + 63 * 4 + 56
											&& y < 8 * VData.UNIT + VData.UNIT / 4) {
										dexUpper = false;
										dexDown = true;
										MDraw.dexSpeed = 0;
										MDraw.dexDelay = 64;
									}
								}
							} else if (viaFlag) {
								if (px >= 13 * VData.UNIT / 2 - 108
										&& px <= 13 * VData.UNIT / 2 - 2) {
									for (int i = 0; i < XInfo.hTeamC; i++) {
										if (py >= VData.UNIT / 2 + 106 * i + 2
												&& py <= VData.UNIT / 2 + 106
												* (i + 1) + 2) {
											MDraw.boxZ = i;
											MDraw.boxY = -1;
											MDraw.boxX = -1;
											break;
										}
									}
								} else {
									int tempY = 0;
									int tempX = 0;
									boolean validY = false;
									boolean validX = false;

									for (int sy = 0; sy < 5; sy++) {
										if (py > VData.UNIT / 2 + 130 + 96 * sy
												&& py < VData.UNIT / 2 + 130 + 96
												* (sy + 1)) {
											tempY = sy;
											validY = true;
											break;
										}
									}
									for (int sx = 0; sx < 7; sx++) {
										if (px > 13 * VData.UNIT / 2 + 24 + 96 * sx
												&& px < 13 * VData.UNIT / 2 + 24
												+ 96 * (sx + 1)) {
											tempX = sx;
											validX = true;
											break;
										}
									}
									if (validY && validX) {
										MDraw.boxZ = -1;
										MDraw.boxY = tempY;
										MDraw.boxX = tempX;
									}
								}
							}
						}
						break;
					case MotionEvent.ACTION_UP:// 单点触摸离开动作
						mode = 0;
						mPath0 = null;
						mPath1 = null;
						cx = (int) event.getX();
						cy = (int) event.getY();
						ct = event.getEventTime(); // 事件结束时间
						if (mazz > 0) {
							mazz = 0;
						} else {
							onTouchEvent(event);
						}
						if (viaFlag) {
							if (cx >= 13 * VData.UNIT / 2 - 108
									&& cx <= 13 * VData.UNIT / 2 - 2) {
								for (int i = 0; i < 6; i++) {
									if (cy >= VData.UNIT / 2 + 106 * i + 2
											&& cy <= VData.UNIT / 2 + 106 * (i + 1)
											+ 2) {
										MDraw.swapZ = i;
										MDraw.swapY = -1;
										MDraw.swapX = -1;
										break;
									}
								}
								// 离开在队伍中的情况
								if (MDraw.swapZ >= 0) {
									if (MDraw.boxZ >= 0) {
										// 按下时在队伍中
										if (XInfo._team.teamHash[MDraw.boxZ] > 0) {
											if (XInfo._team.teamHash[MDraw.swapZ] > 0) {
												int temp = XInfo._team.teamHash[MDraw.swapZ];
												XInfo._team.teamHash[MDraw.swapZ] = XInfo._team.teamHash[MDraw.boxZ];
												XInfo._team.teamHash[MDraw.boxZ] = temp;
												temp = XInfo.teamHLevel[MDraw.swapZ];
												XInfo.teamHLevel[MDraw.swapZ] = XInfo.teamHLevel[MDraw.boxZ];
												XInfo.teamHLevel[MDraw.boxZ] = temp;
												temp = XInfo.teamHKidney[MDraw.swapZ];
												XInfo.teamHKidney[MDraw.swapZ] = XInfo.teamHKidney[MDraw.boxZ];
												XInfo.teamHKidney[MDraw.boxZ] = temp;
											} else {
												MDraw.swapZ = XInfo.hTeamC - 1;
												int temp = XInfo._team.teamHash[MDraw.swapZ];
												XInfo._team.teamHash[MDraw.swapZ] = XInfo._team.teamHash[MDraw.boxZ];
												XInfo._team.teamHash[MDraw.boxZ] = temp;
												temp = XInfo.teamHLevel[MDraw.swapZ];
												XInfo.teamHLevel[MDraw.swapZ] = XInfo.teamHLevel[MDraw.boxZ];
												XInfo.teamHLevel[MDraw.boxZ] = temp;
												temp = XInfo.teamHKidney[MDraw.swapZ];
												XInfo.teamHKidney[MDraw.swapZ] = XInfo.teamHKidney[MDraw.boxZ];
												XInfo.teamHKidney[MDraw.boxZ] = temp;
											}
											MDraw.boxZ = MDraw.swapZ;
											XInfo._team.reFresh();
											teamChanged = true;
										}
									} else if (MDraw.boxY >= 0 && MDraw.boxX >= 0) {
										// 按下时在箱子中
										if (XInfo.viaBoxIndex[MDraw.currBox][MDraw.boxX
												+ 7 * MDraw.boxY] > 0) {
											if (XInfo._team.teamHash[MDraw.swapZ] > 0) {
												int temp = XInfo._team.teamHash[MDraw.swapZ];
												XInfo._team.teamHash[MDraw.swapZ] = XInfo.viaBoxIndex[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY];
												XInfo.viaBoxIndex[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY] = temp;
												temp = XInfo.teamHLevel[MDraw.swapZ];
												XInfo.teamHLevel[MDraw.swapZ] = XInfo.viaBoxLevel[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY];
												XInfo.viaBoxLevel[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY] = temp;
												temp = XInfo.teamHKidney[MDraw.swapZ];
												XInfo.teamHKidney[MDraw.swapZ] = XInfo.viaBoxKidney[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY];
												XInfo.viaBoxKidney[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY] = temp;
											} else {
												MDraw.swapZ = XInfo.hTeamC;
												XInfo._team.teamHash[MDraw.swapZ] = XInfo.viaBoxIndex[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY];
												XInfo.viaBoxIndex[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY] = 0;
												XInfo.teamHLevel[MDraw.swapZ] = XInfo.viaBoxLevel[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY];
												XInfo.viaBoxLevel[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY] = 0;
												XInfo.teamHKidney[MDraw.swapZ] = XInfo.viaBoxKidney[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY];
												XInfo.viaBoxKidney[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY] = 0;
												XInfo.hTeamC++;
											}
											MDraw.boxZ = MDraw.swapZ;
											MDraw.boxY = -1;
											MDraw.boxX = -1;
											XInfo._team.reFresh();
											teamChanged = true;
										}
									}
								}
							} else {
								int tempY = 0;
								int tempX = 0;
								boolean validY = false;
								boolean validX = false;

								for (int sy = 0; sy < 5; sy++) {
									if (cy > VData.UNIT / 2 + 130 + 96 * sy
											&& cy < VData.UNIT / 2 + 130 + 96
											* (sy + 1)) {
										tempY = sy;
										validY = true;
										break;
									}
								}
								for (int sx = 0; sx < 7; sx++) {
									if (cx > 13 * VData.UNIT / 2 + 24 + 96 * sx
											&& cx < 13 * VData.UNIT / 2 + 24 + 96
											* (sx + 1)) {
										tempX = sx;
										validX = true;
										break;
									}
								}
								if (validY && validX) {
									MDraw.swapZ = -1;
									MDraw.swapY = tempY;
									MDraw.swapX = tempX;
								}
								// 离开在箱子中的情况
								if (MDraw.swapY >= 0 && MDraw.swapX >= 0) {
									if (MDraw.boxZ >= 0) {
										// 按下时在队伍中
										if (XInfo._team.teamHash[MDraw.boxZ] > 0) {
											if (XInfo.viaBoxIndex[MDraw.currBox][MDraw.swapX
													+ 7 * MDraw.swapY] > 0) {
												int temp = XInfo.viaBoxIndex[MDraw.currBox][MDraw.swapX
														+ 7 * MDraw.swapY];
												XInfo.viaBoxIndex[MDraw.currBox][MDraw.swapX
														+ 7 * MDraw.swapY] = XInfo._team.teamHash[MDraw.boxZ];
												XInfo._team.teamHash[MDraw.boxZ] = temp;
												temp = XInfo.viaBoxLevel[MDraw.currBox][MDraw.swapX
														+ 7 * MDraw.swapY];
												XInfo.viaBoxLevel[MDraw.currBox][MDraw.swapX
														+ 7 * MDraw.swapY] = XInfo.teamHLevel[MDraw.boxZ];
												XInfo.teamHLevel[MDraw.boxZ] = temp;
												temp = XInfo.viaBoxKidney[MDraw.currBox][MDraw.swapX
														+ 7 * MDraw.swapY];
												XInfo.viaBoxKidney[MDraw.currBox][MDraw.swapX
														+ 7 * MDraw.swapY] = XInfo.teamHKidney[MDraw.boxZ];
												XInfo.teamHKidney[MDraw.boxZ] = temp;
											} else {
												if (XInfo.hTeamC > 1) {
													XInfo.viaBoxIndex[MDraw.currBox][MDraw.swapX
															+ 7 * MDraw.swapY] = XInfo._team.teamHash[MDraw.boxZ];
													XInfo.viaBoxLevel[MDraw.currBox][MDraw.swapX
															+ 7 * MDraw.swapY] = XInfo.teamHLevel[MDraw.boxZ];
													XInfo.viaBoxKidney[MDraw.currBox][MDraw.swapX
															+ 7 * MDraw.swapY] = XInfo.teamHKidney[MDraw.boxZ];
													for (int i = MDraw.boxZ; i < XInfo.hTeamC - 1; i++) {
														XInfo._team.teamHash[i] = XInfo._team.teamHash[i + 1];
														XInfo.teamHLevel[i] = XInfo.teamHLevel[i + 1];
														XInfo.teamHKidney[i] = XInfo.teamHKidney[i + 1];
													}
													XInfo._team.teamHash[XInfo.hTeamC - 1] = 0;
													XInfo.teamHLevel[XInfo.hTeamC - 1] = 0;
													XInfo.teamHKidney[XInfo.hTeamC - 1] = 0;

													XInfo.hTeamC--;// 注意这里已经自减
													if (pmSeq >= MDraw.boxZ
															&& pmSeq == XInfo.hTeamC) {
														pmSeq--;
													}
												}
											}
											MDraw.boxZ = -1;
											MDraw.boxY = MDraw.swapY;
											MDraw.boxX = MDraw.swapX;
											XInfo._team.reFresh();
											teamChanged = true;
										}
									} else if (MDraw.boxY >= 0 && MDraw.boxX >= 0) {
										// 按下时在箱子中
										if (XInfo.viaBoxIndex[MDraw.currBox][MDraw.boxX
												+ 7 * MDraw.boxY] > 0) {
											if (XInfo.viaBoxIndex[MDraw.currBox][MDraw.swapX
													+ 7 * MDraw.swapY] > 0) {
												int temp = XInfo.viaBoxIndex[MDraw.currBox][MDraw.swapX
														+ 7 * MDraw.swapY];
												XInfo.viaBoxIndex[MDraw.currBox][MDraw.swapX
														+ 7 * MDraw.swapY] = XInfo.viaBoxIndex[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY];
												XInfo.viaBoxIndex[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY] = temp;
												temp = XInfo.viaBoxLevel[MDraw.currBox][MDraw.swapX
														+ 7 * MDraw.swapY];
												XInfo.viaBoxLevel[MDraw.currBox][MDraw.swapX
														+ 7 * MDraw.swapY] = XInfo.viaBoxLevel[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY];
												XInfo.viaBoxLevel[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY] = temp;
												temp = XInfo.viaBoxKidney[MDraw.currBox][MDraw.swapX
														+ 7 * MDraw.swapY];
												XInfo.viaBoxKidney[MDraw.currBox][MDraw.swapX
														+ 7 * MDraw.swapY] = XInfo.viaBoxKidney[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY];
												XInfo.viaBoxKidney[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY] = temp;
											} else {
												XInfo.viaBoxIndex[MDraw.currBox][MDraw.swapX
														+ 7 * MDraw.swapY] = XInfo.viaBoxIndex[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY];
												XInfo.viaBoxIndex[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY] = 0;
												XInfo.viaBoxLevel[MDraw.currBox][MDraw.swapX
														+ 7 * MDraw.swapY] = XInfo.viaBoxLevel[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY];
												XInfo.viaBoxLevel[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY] = 0;
												XInfo.viaBoxKidney[MDraw.currBox][MDraw.swapX
														+ 7 * MDraw.swapY] = XInfo.viaBoxKidney[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY];
												XInfo.viaBoxKidney[MDraw.currBox][MDraw.boxX
														+ 7 * MDraw.boxY] = 0;
											}
											MDraw.boxY = MDraw.swapY;
											MDraw.boxX = MDraw.swapX;
										}
									}
								}
							}
						}
						break;
					case MotionEvent.ACTION_MOVE:// 触摸点移动动作
						if (mode == 2) {
							mPath0.move(event.getX(0), event.getY(0));
							mPath1.move(event.getX(1), event.getY(1));
							if (initFlag) {
								initX = event.getX(0) > event.getX(1) ? event
										.getX(0) : event.getX(1);
								initY = event.getY(0) < event.getY(1) ? event
										.getY(0) : event.getY(1);
								initFlag = false;
							}
							if (initX >= zoneX && initY <= zoneY) {
								disX = event.getX(0) - event.getX(1);
								disY = event.getY(0) - event.getY(1);
								cLen = (int) Math.sqrt(disX * disX + disY * disY);
								if (cLen > pLen + 40) {
									pLen = cLen;
									if (unit < 16 && !heroFlag)
										unit++;
								}
								if (cLen < pLen - 40) {
									pLen = cLen;
									if (unit > 8 && !heroFlag)
										unit--;
								}
							}
						} else {
							cx = (int) event.getX();
							cy = (int) event.getY();
							if (mPath1 == null && mPath0 != null) {
								mPath0.move(cx, cy);
							} else if (mPath0 == null && mPath1 != null) {
								mPath1.move(cx, cy);
							}
							switch (mazz) {
								case 0:
									if (cy - py < -160
											&& cx - px > 160
											&& px < (VData.pe.x - VData.xIndex)
											* VData.UNIT) {
										mazz++;
										py = cy;
										px = cx;
									}
									break;
								case 1:
									if (cy - py > 160
											&& cx - px > 160
											&& py < (VData.pe.y - VData.yIndex)
											* VData.UNIT) {
										mazz++;
										py = cy;
										px = cx;
									}
									break;
								case 2:
									if (cy - py > 160
											&& cx - px < -160
											&& px > (VData.pe.x - VData.xIndex)
											* VData.UNIT) {
										mazz++;
										py = cy;
										px = cx;
									}
									break;
								case 3:
									if (cy - py < -160
											&& cx - px < -160
											&& py > (VData.pe.y - VData.yIndex)
											* VData.UNIT) {
										mazz++;
										py = cy;
										px = cx;
									}
									break;
								default:
									moodClick = 60;
									soundPool.play(soundPoolMap.get(pmSeq), 1, 1, 0, 0,
											1);
									break;
							}
						}
						break;
					case MotionEvent.ACTION_POINTER_DOWN:// 多点触摸动作
						if (event.getActionIndex() < 2) {
							mode += 1;
							if (mPath0 == null) {
								mPath0 = new APath(event.getX(0), event.getY(0));
							} else {
								mPath1 = new APath(event.getX(1), event.getY(1));
							}
							disX = event.getX(0) - event.getX(1);
							disY = event.getY(0) - event.getY(1);
							pLen = (int) Math.sqrt(disX * disX + disY * disY);
						}
						break;
					case MotionEvent.ACTION_POINTER_UP:// 多点触摸离开动作
						if (event.getActionIndex() < 2) {
							mode -= 1;
							if (event.getActionIndex() == 0) {
								mPath0 = null;
							} else if (event.getActionIndex() == 1) {
								mPath1 = null;
							}
						}
						break;
				}
				return true;
			}
		});
	}

	@Override
	public void DrawView(Canvas v_canvas) {
		if (heroTouch != null && petTouch != null) {
			if (heroTouch.size() == 0 && petTouch.size() == 0) {
				for (int i = 0; i < VData._rent.target.length; i++) {
					if (VData._rent.target[i] != null) {
						if (VData.so.y == VData._rent.target[i].y
								&& VData.so.x == VData._rent.target[i].x) {
							// 此处不美观，不用新对象会出现瑕疵，应细化到函数调用
							switch (VData._rent.whitarget[i]) {
								case 800:// 室内开关
									_data = new VData(m_context);
									VData.siteFlag = false;// 室内外用到siteFlag
									_data.reBase(VData.landMap.baseMap[VData.locY][VData.locX]);
									InitData();
									doorFlag = false;
									break;
								case 900:
									buildId = 900;
									switch (VData.landMap.baseMap[VData.locY][VData.locX]) {
										case 0:// 初始城镇
											_data = new VData(m_context);
											_data.reBase(100);
											InitData();
											doorFlag = true;
											MDraw.fineRole = true;
											break;
										case 1:// 1城
										case 2:// 2城
										case 3:// 3城
										case 4:// 4城
										case 5:// 5城
										case 6:// 6城
										case 7:// 7城
										case 8:// 8城
											_data = new VData(m_context);
											_data.reBase(100);
											InitData();
											doorFlag = true;
											MDraw.fineRole = true;
											break;
										case 9:// 联盟
											_data = new VData(m_context);
											_data.reBase(199);
											InitData();
											doorFlag = true;
											MDraw.fineRole = true;
											break;
										default:
											break;
									}
									break;
								case 901:
									buildId = 901;
									switch (VData.landMap.baseMap[VData.locY][VData.locX]) {
										case 0:// 初始城镇
											_data = new VData(m_context);
											_data.reBase(101);
											InitData();
											doorFlag = true;
											MDraw.fineRole = true;
											break;
										case 1:// 1城
										case 2:// 2城
										case 3:// 3城
										case 4:// 4城
										case 5:// 5城
										case 6:// 6城
										case 7:// 7城
										case 8:// 8城
											_data = new VData(m_context);
											_data.reBase(101);
											InitData();
											doorFlag = true;
											MDraw.fineRole = true;
											break;
										case 9:// 联盟
											break;
										default:
											break;
									}
									break;
								case 902:
									buildId = 902;
									switch (VData.landMap.baseMap[VData.locY][VData.locX]) {
										case 0:// 初始城镇
											break;
										case 1:// 1城
										case 2:// 2城
										case 3:// 3城
										case 4:// 4城
										case 5:// 5城
										case 6:// 6城
										case 7:// 7城
										case 8:// 8城
											// 更换道馆馆主形象
											_data = new VData(m_context);
											_data.reBase(102);
											InitData();
											doorFlag = true;
											MDraw.fineRole = true;
											break;
										case 9:// 联盟
											break;
										default:
											break;
									}
									break;
								default:// 未指定的以及踩到蘑菇的情况
									if (VData.siteFlag) {// 踩到蘑菇
										_data = new VData(m_context);
										VData.siteFlag = false;// roguelike用到siteFlag
										_data.reBase(VData.landMap.baseMap[VData.locY][VData.locX]);
										InitData();
										miniFlag = false;// 离开roguelike无小地图
										flowerFlag = false;// 离开roguelike不可种花
									} else {// 未指定时进入roguelike
										_data = new VData(m_context);
										_data.reBase(-100);// -100表示roguelike
										InitData();
										miniFlag = true;// 进入roguelike有小地图
										flowerFlag = true;// 进入roguelike可种花
									}
									break;
							}
							break;
						}
					}
				}
				if (VData.noteHero == VData.TAG_GRAZZ && !fightFlag
						&& moveFightFlag) {
					// 遇敌概率为100%，需要计算步数或者中断行走修改概率
					moveFightFlag = false;
					playerN.pause();
					playerS.seekTo(0);
					playerS.start();
					XInfo.zTeamC = 1;
					for (int k = 0; k < XInfo.zTeamC; k++) {
						oopDex[k] = new Random().nextInt(649) + 1;// 随机出怪的对象
						while (oopDex[k] == 208 || oopDex[k] == 249
								|| oopDex[k] == 250 || oopDex[k] == 321
								|| oopDex[k] == 382 || oopDex[k] == 383
								|| oopDex[k] == 384 || oopDex[k] == 483
								|| oopDex[k] == 484 || oopDex[k] == 486
								|| oopDex[k] == 487 || oopDex[k] == 493
								|| oopDex[k] == 579 || oopDex[k] == 598
								|| oopDex[k] == 643 || oopDex[k] == 644
								|| oopDex[k] == 646) {
							oopDex[k] = new Random().nextInt(649) + 1;
						}
						oopLv[k] = new Random().nextInt(10)
								+ XInfo.teamHLevel[pmSeq];// 随机出怪的等级（领队等级+0-9）
						oopKidney[k] = new Random().nextInt(25);// 随机出怪的性格
						for (int i = 0; i < 4; i++) {
							oopMove[k][i] = 0;
						}
						for (int i = 0; i < 4; i++) {
							int candi = new Random().nextInt(559) + 1;
							for (int j = 0; j < i; j++) {
								if (candi == oopMove[k][j]) {
									candi = new Random().nextInt(559) + 1;
									j = 0;
								}
							}
							oopMove[k][i] = candi;
						}
					}
					_info.IniZInfo();
					fightFlag = true;
					currPM = 0;
					currCry = soundPool.load(m_context,
							NoSwitch.cryXXX[oopDex[currPM]], 1);
					cryBool = true;
					if (dexValue[oopDex[currPM] - 1] < 1) {
						dexValue[oopDex[currPM] - 1] = 1;
					}
					VData.xBattle.fightIni();
					VData.xBattle.refreshZTeam();
				}
				if (bfightFlag && !fightFlag) {
					moveFightFlag = false;
					playerN.pause();
					playerS.seekTo(0);
					playerS.start();
					switch (VData.landMap.baseMap[VData.locY][VData.locX]) {
						case 0:
							break;
						case 1:
							battleTeam = McVisety.gym00;
							break;
						case 2:
							battleTeam = McVisety.gym01;
							break;
						case 3:
							battleTeam = McVisety.gym02;
							break;
						case 4:
							battleTeam = McVisety.gym03;
							break;
						case 5:
							battleTeam = McVisety.gym04;
							break;
						case 6:
							battleTeam = McVisety.gym05;
							break;
						case 7:
							battleTeam = McVisety.gym06;
							break;
						case 8:
							battleTeam = McVisety.gym07;
							break;
						case 9:
							switch (npcssCurr) {
								case 610:
									battleTeam = McVisety.elite00;
									break;
								case 611:
									battleTeam = McVisety.elite01;
									break;
								case 612:
									battleTeam = McVisety.elite02;
									break;
								case 613:
									battleTeam = McVisety.elite03;
									break;
							}
							break;
					}
					XInfo.zTeamC = battleTeam.length;
					for (int k = 0; k < XInfo.zTeamC; k++) {
						oopDex[k] = battleTeam[k];// 随机出怪的对象
						if (VData.landMap.baseMap[VData.locY][VData.locX] == 9) {
							oopLv[k] = new Random().nextInt(20) + 60;// 随机出怪的等级60-79
						} else {
							oopLv[k] = new Random().nextInt(20) + 40;// 随机出怪的等级40-59
						}
						oopKidney[k] = new Random().nextInt(25);// 随机出怪的性格
						for (int i = 0; i < 4; i++) {
							oopMove[k][i] = 0;
						}
						for (int i = 0; i < 4; i++) {
							int candi = new Random().nextInt(559) + 1;
							for (int j = 0; j < i; j++) {
								if (candi == oopMove[k][j]) {
									candi = new Random().nextInt(559) + 1;
									j = 0;
								}
							}
							oopMove[k][i] = candi;
						}
					}
					_info.IniZInfo();
					fightFlag = true;
					currPM = 0;
					currCry = soundPool.load(m_context,
							NoSwitch.cryXXX[oopDex[currPM]], 1);
					cryBool = true;
					if (dexValue[oopDex[currPM] - 1] < 1) {
						dexValue[oopDex[currPM] - 1] = 1;
					}
					VData.xBattle.fightIni();
					VData.xBattle.refreshZTeam();
				}
			} else {
				moveFightFlag = true;
				if (heroTouch.size() != 0) {
					synchronized (heroTouch) {
						maskH = heroTouch.get(0);
						_data.majorMove(maskH);
						heroTouch.remove(0);
					}
				}
				if (petTouch.size() != 0) {
					synchronized (petTouch) {
						maskP = petTouch.get(0);
						_data.minorMove(maskP);
						petTouch.remove(0);
					}
				}
			}
		}

		if (fellFlag) {
			VData._fell.reFresh();
			fellFlag = false;
		}

		_draw.drawView(v_canvas, this);

		if (battleFlag && !bfightFlag && !ignoreFlag) {
			_draw.drawBattle(v_canvas, this);
		}
		if (guideFlag) {
			if (guideClick-- == 0) {
				guideClick = 64;
				guideFlag = false;
			}
			_draw.drawGuide(v_canvas, this, guide);
		}
		if (hintFlag) {
			if (hintClick-- == 0) {
				hintClick = 64;
				hintFlag = false;
			}
			_draw.drawHint(v_canvas, this, hint);
		}
		if (miniFlag) {
			_draw.drawMini(v_canvas, unit);
		}
		if (fsFightFlag) {
			_draw.drawFSfight(v_canvas, false);
		}
		if (fsTeamFlag) {
			_draw.drawFSteam(v_canvas, fsFightFlag);
		}
		if (fsItemFlag) {
			_draw.drawFSitem(v_canvas, fsFightFlag || fsTeamFlag);
		}
		if (fightFlag) {
			_draw.drawFight(v_canvas, fsFightFlag || fsTeamFlag || fsItemFlag);
			if (cryBool) {
				if (soundPool.play(currCry, 1, 1, 0, 0, 1) > 0) {
					cryBool = false;
				}
			}
		}
		if (heroFlag) {
			_draw.drawState(v_canvas);
		}
		if (dexFlag) {
			dexV1 = 0;
			dexV2 = 0;
			for (int i = 0; i < 649; i++) {
				if (dexValue[i] >= 1) {
					dexV1++;
					if (dexValue[i] >= 2) {
						dexV2++;
					}
				}
			}
			_draw.drawDex(v_canvas);
		}
		if (mapFlag) {
			_draw.drawMap(v_canvas);
		}
		if (viaFlag) {
			_draw.drawVia(v_canvas);
		}
		if (penFlag) {
			_draw.drawPen(v_canvas);
		}
		if (teamFlag) {
			_draw.drawTeam(v_canvas);
		}
		if (homeFlag) {
			_draw.drawHome(v_canvas);
		}
		_draw.drawPath(v_canvas, mPath0);
		_draw.drawPath(v_canvas, mPath1);
		if (fightFlag) {
			if (VData.xBattle.deadZ && deadDelay-- == 0) {
				if (VData.xBattle.deadZAll) {
					fsFightFlag = false;
					fsTeamFlag = false;
					fsItemFlag = false;
					fightFlag = false;
					if (bfightFlag) {
						bfightFlag = false;
						switch (VData.landMap.baseMap[VData.locY][VData.locX]) {
							case 0:
								break;
							case 1:
								gymProgress[0] = 1;
								chatStr = LoChatss.gymb00;
								break;
							case 2:
								gymProgress[1] = 1;
								chatStr = LoChatss.gymb01;
								break;
							case 3:
								gymProgress[2] = 1;
								chatStr = LoChatss.gymb02;
								break;
							case 4:
								gymProgress[3] = 1;
								chatStr = LoChatss.gymb03;
								break;
							case 5:
								gymProgress[4] = 1;
								chatStr = LoChatss.gymb04;
								break;
							case 6:
								gymProgress[5] = 1;
								chatStr = LoChatss.gymb05;
								break;
							case 7:
								gymProgress[6] = 1;
								chatStr = LoChatss.gymb06;
								break;
							case 8:
								gymProgress[7] = 1;
								chatStr = LoChatss.gymb07;
								break;
							case 9:
								switch (npcssCurr) {
									case 610:
										eliteProgress[0] = 1;
										chatStr = LoChatss.eliteb00;
										break;
									case 611:
										eliteProgress[1] = 1;
										chatStr = LoChatss.eliteb01;
										break;
									case 612:
										eliteProgress[2] = 1;
										chatStr = LoChatss.eliteb02;
										break;
									case 613:
										eliteProgress[3] = 1;
										chatStr = LoChatss.eliteb03;
										break;
								}
								break;
						}
					}
					playerS.pause();
					playerN.start();
					VData.xBattle.deadZAll = false;
					MDraw.logicMap[fightY][fightX] = VData.TAG_EMPTY;
				} else {
					fsFightFlag = false;
					fsTeamFlag = false;
					fsItemFlag = false;
					currPM = VData.xBattle.findNextZ();
					currCry = soundPool.load(m_context,
							NoSwitch.cryXXX[oopDex[currPM]], 1);
					cryBool = true;
					if (dexValue[oopDex[currPM] - 1] < 1) {
						dexValue[oopDex[currPM] - 1] = 1;
					}
				}
				deadDelay = 10;
				captureOnce = true;
				VData.xBattle.deadZ = false;
			}
			if (VData.xBattle.deadH && deadDelay-- == 0) {
				if (VData.xBattle.deadHAll) {
					fsFightFlag = false;
					fsTeamFlag = false;
					fsItemFlag = false;
					fightFlag = false;
					if (bfightFlag) {
						bfightFlag = false;
						switch (VData.landMap.baseMap[VData.locY][VData.locX]) {
							case 0:
								break;
							case 1:
								gymProgress[0] = 2;
								chatStr = LoChatss.gymc00;
								break;
							case 2:
								gymProgress[1] = 2;
								chatStr = LoChatss.gymc01;
								break;
							case 3:
								gymProgress[2] = 2;
								chatStr = LoChatss.gymc02;
								break;
							case 4:
								gymProgress[3] = 2;
								chatStr = LoChatss.gymc03;
								break;
							case 5:
								gymProgress[4] = 2;
								chatStr = LoChatss.gymc04;
								break;
							case 6:
								gymProgress[5] = 2;
								chatStr = LoChatss.gymc05;
								break;
							case 7:
								gymProgress[6] = 2;
								chatStr = LoChatss.gymc06;
								break;
							case 8:
								gymProgress[7] = 2;
								chatStr = LoChatss.gymc07;
								break;
							case 9:
								switch (npcssCurr) {
									case 610:
										eliteProgress[0] = 2;
										chatStr = LoChatss.elitec00;
										break;
									case 611:
										eliteProgress[1] = 2;
										chatStr = LoChatss.elitec01;
										break;
									case 612:
										eliteProgress[2] = 2;
										chatStr = LoChatss.elitec02;
										break;
									case 613:
										eliteProgress[3] = 2;
										chatStr = LoChatss.elitec03;
										break;
								}
								break;
						}
					}
					playerS.pause();
					playerN.start();
					VData.xBattle.deadHAll = false;
					VData.xBattle.restoreHP();
					VData.xBattle.restorePP();
				} else {
					fsFightFlag = false;
					fsTeamFlag = true;
					fsItemFlag = false;
					pmSeq = VData.xBattle.findNextH();
					soundPool.play(soundPoolMap.get(pmSeq), 1, 1, 0, 0, 1);
				}
				deadDelay = 10;
				moveH = -1;
				moveHClick = 8;
				itemH = -1;
				itemHClick = 8;
				VData.xBattle.deadH = false;
			}
		}
	}

	@Override
	protected boolean HandleTouchEvent(MotionEvent vEvent) {
		if (heroTouch != null && heroTouch.size() > 0)
			return false;
		if (petTouch != null && petTouch.size() > 0)
			return false;
		if (VData.xBattle.fighting)
			return false;
		int x = (int) vEvent.getX();
		int y = (int) vEvent.getY();
		if (fightFlag) {
			// 战斗面板打开时
			if (x >= 49 * VData.UNIT / 4 && x <= 63 * VData.UNIT / 4) {
				if (fsFightFlag) {
					for (int i = 0; i < 4; i++) {
						if (y >= (7 * i + 1) * VData.UNIT / 4
								&& y <= 7 * (i + 1) * VData.UNIT / 4) {
							if (VData.xBattle.hMissPP[pmSeq][0] == 0
									&& VData.xBattle.hMissPP[pmSeq][1] == 0
									&& VData.xBattle.hMissPP[pmSeq][2] == 0
									&& VData.xBattle.hMissPP[pmSeq][3] == 0) {
								VData.xBattle.fightCode(-1);
								break;
							} else if (VData.xBattle.hMissPP[pmSeq][i] > 0) {
								moveH = i;
								VData.xBattle.fightCode(moveH);
								break;
							}
						}
					}
				} else if (fsTeamFlag) {
					for (int i = 0; i < 6; i++) {
						if (y >= 20 + i * 92 && y <= 80 + i * 92) {
							if (pmSeq != i && XBattle.hTeamHP[i] > 0) {
								pmSeq = i;
								soundPool.play(soundPoolMap.get(pmSeq), 1, 1,
										0, 0, 1);
								fellFlag = true;
							}
							break;
						}
					}
				} else if (fsItemFlag) {
					if (y >= 1 * VData.UNIT / 4 && y <= 7 * VData.UNIT / 4) {
						// 体力回复选项
					} else if (y >= 8 * VData.UNIT / 4
							&& y <= 14 * VData.UNIT / 4) {
						// 状态回复选项
					} else if (y >= 15 * VData.UNIT / 4
							&& y <= 21 * VData.UNIT / 4) {
						// 精灵球选项
						if (captureOnce && !bfightFlag) {
							itemH = 2;
							VData.xBattle.zMissHP[currPM] = 0;
							VData.xBattle.zTeamHP[currPM] = 0;
							VData.xBattle.conveyInfo("成功捕获"
									+ XInfo.teamZName[currPM] + "!");
							if (dexValue[oopDex[currPM] - 1] < 2) {
								dexValue[oopDex[currPM] - 1] = 2;
							}
							// 检查队伍是否有空位
							if (XInfo.hTeamC < 6) {
								// 队伍有位置则直接带入
								XInfo._team.teamHash[XInfo.hTeamC] = oopDex[currPM];
								// 队伍全部刷新是为了方便增减和替换
								XInfo.teamHLevel[XInfo.hTeamC] = oopLv[currPM];
								XInfo.teamHKidney[XInfo.hTeamC] = oopKidney[currPM];
								XInfo.hTeamC++;
								XInfo._team.reFresh();
								XInfo.IniHInfo();
								for (int i = 0; i < XInfo.hTeamC; i++) {
									soundPoolMap
											.put(i,
													soundPool
															.load(m_context,
																	NoSwitch.cryXXX[XInfo.teamHIndex[i]],
																	1));
								}
								VData.xBattle.refreshHTeam();
							} else {
								// 队伍满则放入PokeBox
								for (int i = 0; i < 24; i++) {
									for (int j = 0; j < 35; j++) {
										if (XInfo.viaBoxIndex[i][j] == 0) {
											XInfo.viaBoxIndex[i][j] = oopDex[currPM];
											XInfo.viaBoxLevel[i][j] = oopLv[currPM];
											XInfo.viaBoxKidney[i][j] = oopKidney[currPM];
											i = 24;
											j = 35;
										}
									}
								}
							}
							// 成功捕获后，不可再次捕捉
							captureOnce = false;
						}
					} else if (y >= 22 * VData.UNIT / 4
							&& y <= 28 * VData.UNIT / 4) {
						// 战斗用品选项
					}
				}
			}
			if (y >= 29 * VData.UNIT / 4 && y <= 35 * VData.UNIT / 4) {
				if (x >= VData.UNIT / 4 && x <= 4 * VData.UNIT) {
					// 战斗选项
					fsFightFlag = true;
					fsTeamFlag = false;
					fsItemFlag = false;
				} else if (x >= 17 * VData.UNIT / 4 && x <= 8 * VData.UNIT) {
					// 队伍选项
					fsFightFlag = false;
					fsTeamFlag = true;
					fsItemFlag = false;
				} else if (x >= 33 * VData.UNIT / 4 && x <= 12 * VData.UNIT) {
					// 物品选项
					fsFightFlag = false;
					fsTeamFlag = false;
					fsItemFlag = true;
				} else if (x >= 49 * VData.UNIT / 4 && x <= 63 * VData.UNIT / 4) {
					// 逃脱选项
					if (fsFightFlag || fsTeamFlag || fsItemFlag) {
						fsFightFlag = false;
						fsTeamFlag = false;
						fsItemFlag = false;
					} else {
						fightFlag = false;
						if (bfightFlag) {
							bfightFlag = false;
							switch (VData.landMap.baseMap[VData.locY][VData.locX]) {
								case 0:
									break;
								case 1:
									gymProgress[0] = 2;
									chatStr = LoChatss.gymc00;
									break;
								case 2:
									gymProgress[1] = 2;
									chatStr = LoChatss.gymc01;
									break;
								case 3:
									gymProgress[2] = 2;
									chatStr = LoChatss.gymc02;
									break;
								case 4:
									gymProgress[3] = 2;
									chatStr = LoChatss.gymc03;
									break;
								case 5:
									gymProgress[4] = 2;
									chatStr = LoChatss.gymc04;
									break;
								case 6:
									gymProgress[5] = 2;
									chatStr = LoChatss.gymc05;
									break;
								case 7:
									gymProgress[6] = 2;
									chatStr = LoChatss.gymc06;
									break;
								case 8:
									gymProgress[7] = 2;
									chatStr = LoChatss.gymc07;
									break;
								case 9:
									switch (npcssCurr) {
										case 610:
											eliteProgress[0] = 2;
											chatStr = LoChatss.elitec00;
											break;
										case 611:
											eliteProgress[1] = 2;
											chatStr = LoChatss.elitec01;
											break;
										case 612:
											eliteProgress[2] = 2;
											chatStr = LoChatss.elitec02;
											break;
										case 613:
											eliteProgress[3] = 2;
											chatStr = LoChatss.elitec03;
											break;
									}
									break;
							}
						}
						playerS.pause();
						playerN.start();
					}
				}
			}
		} else if (heroFlag) {
			// 主人公面板打开时
			if (mapFlag) {
				// 超大地图打开时
				int tempY = 0;
				int tempX = 0;
				boolean validY = false;
				boolean validX = false;
				for (int sy = 0; sy < VData.landMap.baseY; sy++) {
					if (y > (1 + sy) * VData.UNIT / 2
							&& y < (2 + sy) * VData.UNIT / 2) {
						tempY = sy;
						validY = true;
						break;
					}
				}
				for (int sx = 0; sx < VData.landMap.baseX; sx++) {
					if (x > (13 + sx) * VData.UNIT / 2
							&& x < (14 + sx) * VData.UNIT / 2) {
						tempX = sx;
						validX = true;
						break;
					}
				}
				if (validY && validX) {
					MDraw.infoY = tempY;
					MDraw.infoX = tempX;
				}
				// 不在RL世界时可以使用飞行功能（10个地点）
				if (!VData.siteFlag && ct - pt > 1000) {
					int infoTarget = VData.landMap.baseMap[MDraw.infoY][MDraw.infoX];
					if (infoTarget >= 0 && infoTarget <= 10) {
						_data.reBase(infoTarget);
						ct = pt;
					}
				}
			} else if (viaFlag) {
				// 通信建立时
				if (y >= VData.UNIT / 2 + 3 * 4 && y <= VData.UNIT / 2 + 24 * 4) {
					if (x >= 13 * VData.UNIT / 2 + 3 * 4
							&& x <= 13 * VData.UNIT / 2 + 28 * 4) {
						if (MDraw.currBox - 1 < 0) {
							MDraw.currBox = 23;
						} else {
							MDraw.currBox--;
						}
						MDraw.boxY = -1;
						MDraw.boxX = -1;
					} else if (x >= 13 * VData.UNIT / 2 + 152 * 4
							&& x <= 13 * VData.UNIT / 2 + 177 * 4) {
						if (MDraw.currBox + 1 > 23) {
							MDraw.currBox = 0;
						} else {
							MDraw.currBox++;
						}
						MDraw.boxY = -1;
						MDraw.boxX = -1;
					}
				}
				int tempY = 0;
				int tempX = 0;
				boolean validY = false;
				boolean validX = false;

				for (int sy = 0; sy < 5; sy++) {
					if (y > VData.UNIT / 2 + 130 + 96 * sy
							&& y < VData.UNIT / 2 + 130 + 96 * (sy + 1)) {
						tempY = sy;
						validY = true;
						break;
					}
				}
				for (int sx = 0; sx < 7; sx++) {
					if (x > 13 * VData.UNIT / 2 + 24 + 96 * sx
							&& x < 13 * VData.UNIT / 2 + 24 + 96 * (sx + 1)) {
						tempX = sx;
						validX = true;
						break;
					}
				}
				if (validY && validX) {
					MDraw.boxZ = -1;
					MDraw.boxY = tempY;
					MDraw.boxX = tempX;
				}
			} else if (penFlag) {
				// 记录面板打开时
				if (y > 15 * VData.UNIT / 2 && y < 17 * VData.UNIT / 2
						&& x > 27 * VData.UNIT / 2 && x < 31 * VData.UNIT / 2) {
					MDraw.saveDelay = 16;
				}
			}
			if (y >= 11 * VData.UNIT / 2 && y <= 13 * VData.UNIT / 2) {
				if (x >= VData.UNIT && x <= 13 * VData.UNIT / 4) {
					// 图鉴选项
					if (ct - pt > 100) {
						if (mapFlag) {
							mapFlag = false;
						}
						if (viaFlag) {
							viaFlag = false;
						}
						if (penFlag) {
							penFlag = false;
						}
						dexFlag = !dexFlag;
					}
				} else if (x >= 15 * VData.UNIT / 4 && x <= 6 * VData.UNIT) {
					// 地图选项
					if (!viaFlag) {
						// 考虑到通信面板的队伍遮挡
						if (ct - pt > 100) {
							if (dexFlag) {
								dexFlag = false;
							}
							if (viaFlag) {
								viaFlag = false;
							}
							if (penFlag) {
								penFlag = false;
							}
							mapFlag = !mapFlag;
							if (mapFlag) {
								MDraw.infoY = VData.locY;
								MDraw.infoX = VData.locX;
							}
							// 检测超大地图的布局算法
							// _draw.onceLand();
						}
					}
				}
			} else if (y >= 7 * VData.UNIT && y <= 8 * VData.UNIT) {
				if (x >= VData.UNIT && x <= 13 * VData.UNIT / 4) {
					// 通信选项
					if (ct - pt > 100) {
						if (dexFlag) {
							dexFlag = false;
						}
						if (mapFlag) {
							mapFlag = false;
						}
						if (penFlag) {
							penFlag = false;
						}
						viaFlag = !viaFlag;
						// 关闭通信时
						if (!viaFlag) {
							if (teamChanged) {
								XInfo.IniHInfo();
								for (int i = 0; i < XInfo.hTeamC; i++) {
									soundPoolMap
											.put(i,
													soundPool
															.load(m_context,
																	NoSwitch.cryXXX[XInfo.teamHIndex[i]],
																	1));
								}
								VData._fell.reFresh();
								VData.xBattle.refreshHTeam();
								teamChanged = false;
							}
						}
					}
				} else if (x >= 15 * VData.UNIT / 4 && x <= 6 * VData.UNIT) {
					// 记录选项
					if (!viaFlag) {
						// 考虑到通信面板的队伍遮挡
						if (ct - pt > 100) {
							if (dexFlag) {
								dexFlag = false;
							}
							if (mapFlag) {
								mapFlag = false;
							}
							if (viaFlag) {
								viaFlag = false;
							}
							penFlag = !penFlag;
						}
					}
				}
			}
			// 关闭主人公面板
			if (!(dexFlag || mapFlag || viaFlag || penFlag)) {
				for (int it = 0; it < tileBoard.length; it++) {
					if (x >= tileBoard[it].x
							&& x <= (tileBoard[it].x + VData.UNIT)
							&& y >= tileBoard[it].y
							&& y <= (tileBoard[it].y + VData.UNIT)) {
						touchY = it / VData.dosX;
						touchX = it % VData.dosX;
						if (MDraw.logicMap[touchY + VData.yIndex][touchX
								+ VData.xIndex] == VData.TAG_MASTER) {
							if (ct - pt > 100) {
								heroFlag = false;
							}
							break;
						}
					}
				}
			}
		} else if (teamFlag) {
			if (ct - pt > 100) {
				for (int i = 0; i < XInfo.hTeamC; i++) {
					if (cx >= VData.UNIT / 2 + VData.UNIT / 4
							&& cx <= 13 * VData.UNIT / 2 - VData.UNIT / 4
							&& cy >= VData.UNIT / 2 + 16 + 104 * i
							&& cy <= VData.UNIT / 2 + 104 + 104 * i) {
						if (pmSeq != i) {
							pmSeq = i;
							soundPool.play(soundPoolMap.get(pmSeq), 1, 1, 0, 0,
									1);
							fellFlag = true;
						}
					}
				}
			}
		} else {
			// 所有面板均未打开
			for (int it = 0; it < tileBoard.length; it++) {
				if (x >= tileBoard[it].x && x <= (tileBoard[it].x + VData.UNIT)
						&& y >= tileBoard[it].y
						&& y <= (tileBoard[it].y + VData.UNIT)) {
					touchY = it / VData.dosX;
					touchX = it % VData.dosX;
					// 事件处理
					if (!bfightFlag) {
						for (int i = 0; i < VData._rent.npcss.length; i++) {
							if (VData._rent.npcss[i] != null) {
								if (MDraw.logicMap[touchY + VData.yIndex][touchX
										+ VData.xIndex] == VData._rent.whinpcss[i]) {
									int tempX = touchX + VData.xIndex;
									int tempY = touchY + VData.yIndex;
									if ((VData.so.x + 1 == tempX || VData.so.x - 1 == tempX)
											&& VData.so.y == tempY
											|| (VData.so.y + 1 == tempY || VData.so.y - 1 == tempY)
											&& VData.so.x == tempX) {
										npcssX = tempX;
										npcssY = tempY;
										if (ct - pt > 100 && !heroFlag) {
											if (battleFlag) {
												chatLevel = 0;
												battleFlag = false;
											} else {
												battleFlag = true;
											}
											npcssCurr = VData._rent.whinpcss[i];
											switch (npcssCurr) {
												case 600:// 当前是按照城镇区分内容，即一片的编号上限为100
													switch (VData.landMap.baseMap[VData.locY][VData.locX]) {
														case 0:// 初始城镇
															break;
														case 1:// 1城
															switch (gymProgress[0]) {
																case 0:
																	chatStr = LoChatss.gyma00;
																	break;
																case 3:
																	chatStr = LoChatss.gymd00;
																	break;
																case 4:
																	chatStr = LoChatss.gyme00;
																	break;
															}
															break;
														case 2:// 2城
															switch (gymProgress[1]) {
																case 0:
																	chatStr = LoChatss.gyma01;
																	break;
																case 3:
																	chatStr = LoChatss.gymd01;
																	break;
																case 4:
																	chatStr = LoChatss.gyme01;
																	break;
															}
															break;
														case 3:// 3城
															switch (gymProgress[2]) {
																case 0:
																	chatStr = LoChatss.gyma02;
																	break;
																case 3:
																	chatStr = LoChatss.gymd02;
																	break;
																case 4:
																	chatStr = LoChatss.gyme02;
																	break;
															}
															break;
														case 4:// 4城
															switch (gymProgress[3]) {
																case 0:
																	chatStr = LoChatss.gyma03;
																	break;
																case 3:
																	chatStr = LoChatss.gymd03;
																	break;
																case 4:
																	chatStr = LoChatss.gyme03;
																	break;
															}
															break;
														case 5:// 5城
															switch (gymProgress[4]) {
																case 0:
																	chatStr = LoChatss.gyma04;
																	break;
																case 3:
																	chatStr = LoChatss.gymd04;
																	break;
																case 4:
																	chatStr = LoChatss.gyme04;
																	break;
															}
															break;
														case 6:// 6城
															switch (gymProgress[5]) {
																case 0:
																	chatStr = LoChatss.gyma05;
																	break;
																case 3:
																	chatStr = LoChatss.gymd05;
																	break;
																case 4:
																	chatStr = LoChatss.gyme05;
																	break;
															}
															break;
														case 7:// 7城
															switch (gymProgress[6]) {
																case 0:
																	chatStr = LoChatss.gyma06;
																	break;
																case 3:
																	chatStr = LoChatss.gymd06;
																	break;
																case 4:
																	chatStr = LoChatss.gyme06;
																	break;
															}
															break;
														case 8:// 8城
															switch (gymProgress[7]) {
																case 0:
																	chatStr = LoChatss.gyma07;
																	break;
																case 3:
																	chatStr = LoChatss.gymd07;
																	break;
																case 4:
																	chatStr = LoChatss.gyme07;
																	break;
															}
															break;
														case 9:// 联盟
															break;
														default:
															break;
													}
													break;
												case 610:
													switch (VData.landMap.baseMap[VData.locY][VData.locX]) {
														case 0:
															break;
														case 1:
														case 2:
														case 3:
														case 4:
														case 5:
														case 6:
														case 7:
														case 8:
															break;
														case 9:// 联盟
															switch (eliteProgress[0]) {
																case 0:
																	chatStr = LoChatss.elitea00;
																	break;
																case 3:
																	chatStr = LoChatss.elited00;
																	break;
																case 4:
																	chatStr = LoChatss.elitee00;
																	break;
															}
															break;
														default:
															break;
													}
													break;
												case 611:
													switch (VData.landMap.baseMap[VData.locY][VData.locX]) {
														case 0:
															break;
														case 1:
														case 2:
														case 3:
														case 4:
														case 5:
														case 6:
														case 7:
														case 8:
															break;
														case 9:// 联盟
															switch (eliteProgress[1]) {
																case 0:
																	chatStr = LoChatss.elitea01;
																	break;
																case 3:
																	chatStr = LoChatss.elited01;
																	break;
																case 4:
																	chatStr = LoChatss.elitee01;
																	break;
															}
															break;
														default:
															break;
													}
													break;
												case 612:
													switch (VData.landMap.baseMap[VData.locY][VData.locX]) {
														case 0:
															break;
														case 1:
														case 2:
														case 3:
														case 4:
														case 5:
														case 6:
														case 7:
														case 8:
															break;
														case 9:// 联盟
															switch (eliteProgress[2]) {
																case 0:
																	chatStr = LoChatss.elitea02;
																	break;
																case 3:
																	chatStr = LoChatss.elited02;
																	break;
																case 4:
																	chatStr = LoChatss.elitee02;
																	break;
															}
															break;
														default:
															break;
													}
													break;
												case 613:
													switch (VData.landMap.baseMap[VData.locY][VData.locX]) {
														case 0:
															break;
														case 1:
														case 2:
														case 3:
														case 4:
														case 5:
														case 6:
														case 7:
														case 8:
															break;
														case 9:// 联盟
															switch (eliteProgress[3]) {
																case 0:
																	chatStr = LoChatss.elitea03;
																	break;
																case 3:
																	chatStr = LoChatss.elited03;
																	break;
																case 4:
																	chatStr = LoChatss.elitee03;
																	break;
															}
															break;
														default:
															break;
													}
													break;
												default:// 未指定的情况
													break;
											}
											break;
										}
									}
								}
							}
						}
					}
					if (!battleFlag) {
						if (MDraw.logicMap[touchY + VData.yIndex][touchX
								+ VData.xIndex] == VData.TAG_MASTER) {
							if (!battleFlag) {
								if (ct - pt > 100) {
									heroFlag = true;
								}
							}
						} else if (MDraw.logicMap[touchY + VData.yIndex][touchX
								+ VData.xIndex] == VData.TAG_FIGHT) {
							int tempX = touchX + VData.xIndex;
							int tempY = touchY + VData.yIndex;
							if ((VData.so.x + 1 == tempX || VData.so.x - 1 == tempX)
									&& VData.so.y == tempY
									|| (VData.so.y + 1 == tempY || VData.so.y - 1 == tempY)
									&& VData.so.x == tempX) {
								fightX = tempX;
								fightY = tempY;
								if (ct - pt > 100 && !heroFlag) {
									playerN.pause();
									playerS.seekTo(0);
									playerS.start();
									XInfo.zTeamC = new Random().nextInt(6) + 1;
									for (int k = 0; k < XInfo.zTeamC; k++) {
										oopDex[k] = new Random().nextInt(649) + 1;// 随机出怪的对象
										while (oopDex[k] == 208
												|| oopDex[k] == 249
												|| oopDex[k] == 250
												|| oopDex[k] == 321
												|| oopDex[k] == 382
												|| oopDex[k] == 383
												|| oopDex[k] == 384
												|| oopDex[k] == 483
												|| oopDex[k] == 484
												|| oopDex[k] == 486
												|| oopDex[k] == 487
												|| oopDex[k] == 493
												|| oopDex[k] == 579
												|| oopDex[k] == 598
												|| oopDex[k] == 643
												|| oopDex[k] == 644
												|| oopDex[k] == 646) {
											oopDex[k] = new Random()
													.nextInt(649) + 1;
										}
										oopLv[k] = new Random().nextInt(100) + 1;// 随机出怪的等级1-100
										oopKidney[k] = new Random().nextInt(25);// 随机出怪的性格
										for (int i = 0; i < 4; i++) {
											oopMove[k][i] = 0;
										}
										for (int i = 0; i < 4; i++) {
											int candi = new Random()
													.nextInt(559) + 1;
											for (int j = 0; j < i; j++) {
												if (candi == oopMove[k][j]) {
													candi = new Random()
															.nextInt(559) + 1;
													j = 0;
												}
											}
											oopMove[k][i] = candi;
										}
									}
									_info.IniZInfo();
									fightFlag = true;
									currPM = 0;
									currCry = soundPool.load(m_context,
											NoSwitch.cryXXX[oopDex[currPM]], 1);
									cryBool = true;
									if (dexValue[oopDex[currPM] - 1] < 1) {
										dexValue[oopDex[currPM] - 1] = 1;
									}
									VData.xBattle.fightIni();
									VData.xBattle.refreshZTeam();
								}
							}
						} else if (MDraw.logicMap[touchY + VData.yIndex][touchX
								+ VData.xIndex] == VData.TAG_AFFAIR) {
							int tempX = touchX + VData.xIndex;
							int tempY = touchY + VData.yIndex;
							if ((VData.so.x + 1 == tempX || VData.so.x - 1 == tempX)
									&& VData.so.y == tempY
									|| (VData.so.y + 1 == tempY || VData.so.y - 1 == tempY)
									&& VData.so.x == tempX) {
								affairX = tempX;
								affairY = tempY;
								if (ct - pt > 100 && !heroFlag) {
									if (hintFlag) { // hintFlag通过对停留时间倒计时置否
										hintClick = 64;
									} else {
										hintFlag = true;
									}
									hint = "美丽的盆景树！";
								}
							}
						} else {
							// 行走基本事件
							APoint be = new APoint(VData.so.x, VData.so.y);
							APoint ed = new APoint(VData.xIndex + touchX,
									VData.yIndex + touchY);
							Vector<Integer> touchHelper = new SAstar(
									MDraw.logicMap).search(be, ed);

							int size = touchHelper.size();

							if (heroTouch != null) {
								synchronized (heroTouch) {
									for (int xt = 0; xt < size; xt++) {
										int temp = touchHelper.get(xt);
										for (int yt = 0; yt < VData.STEP; yt++) {
											heroTouch.add(temp);
										}
									}
								}
							}
							if (petTouch != null) {
								synchronized (petTouch) {
									if (size > 0) {
										for (int yt = 0; yt < VData.STEP; yt++) {
											if (VData._fell.markS >= 10)
												VData._fell.markS -= 10;
											petTouch.add(VData._fell.markS);
										}
									}
									for (int xt = 0; xt < size - 1; xt++) {
										int temp = touchHelper.get(xt);
										for (int yt = 0; yt < VData.STEP; yt++) {
											petTouch.add(temp);
										}
									}
								}
							}
							tthY = (touchY - VData.so.y + VData.yIndex)
									* VData.UNIT;
							tthX = (touchX - VData.so.x + VData.xIndex)
									* VData.UNIT;
						}
					}
					break;
				}
			}
		}
		return false;
	}

	@Override
	protected void InitData() {
		heroTouch = new Vector<Integer>();
		petTouch = new Vector<Integer>();
		MDraw.reMapInfo();

		tileBoard = new Point[VData.dosX * VData.dosY];
		for (int y = 0; y < VData.dosY; y++) {
			for (int x = 0; x < VData.dosX; x++) {
				tileBoard[y * VData.dosX + x] = new Point(x * VData.UNIT, y
						* VData.UNIT);
			}
		}
	}

	@Override
	protected void ReleaseData() {
		_data.reFresh();
		_info.reFresh();
		MDraw.reMapInfo();

		tileBoard = new Point[VData.dosX * VData.dosY];
		for (int y = 0; y < VData.dosY; y++) {
			for (int x = 0; x < VData.dosX; x++) {
				tileBoard[y * VData.dosX + x] = new Point(x * VData.UNIT, y
						* VData.UNIT);
			}
		}

		if (heroTouch != null)
			heroTouch.clear();
		heroTouch = null;
		if (petTouch != null)
			petTouch.clear();
		petTouch = null;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (heroTouch != null && heroTouch.size() > 0)
			return false;
		if (petTouch != null && petTouch.size() > 0)
			return false;
		if (!battleFlag) {
			if (!(heroFlag || fightFlag)) {
				if (cx - px > 600) {
					if (!teamFlag)
						teamFlag = true;
					else
						homeFlag = true;
				}
				if (px - cx > 600) {
					if (homeFlag)
						homeFlag = false;
					else
						teamFlag = false;
				}
			}
		}
		if (px - cx < 100 && cx - px < 100 && py - cy < 100 && cy - py < 100) {
			HandleTouchEvent(event);
		}
		return true;
	}

	public void killMedia() {
		playerF.release();
		playerN.release();
		playerS.release();
	}
	// ==================================================================
}