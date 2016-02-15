package com.yurset.wiipm.logi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import com.yurset.wiipm.R;
import com.yurset.wiipm.base.APath;
import com.yurset.wiipm.base.FPublic;
import com.yurset.wiipm.base.NoSwitch;
import com.yurset.wiipm.fight.XBattle;
import com.yurset.wiipm.fight.XInfo;
import com.yurset.wiipm.main.IGame;
import com.yurset.wiipm.vect.SFell;

public class MDraw {
	private int iconClick = 4;
	private boolean iconBool = true;
	private int icon2Click = 8;
	private boolean icon2Bool = true;
	private long it = 0, ot = 0;
	private int missDex = 0;
	public static int currDex = 0;
	public static int dexSpeed = 0;
	public static int dexDelay = 64;
	public static int infoY = 0;
	public static int infoX = 0;
	public static int currBox = 0;
	public static int boxY = -1;
	public static int boxX = -1;
	public static int boxZ = -1;
	public static int swapY = -1;
	public static int swapX = -1;
	public static int swapZ = -1;
	public static int saveDelay = 0;
	private Context mContext = null;
	private Bitmap tempBit = null;
	private Bitmap monsterBit = null;
	private Bitmap leaderLic = null;
	private Bitmap leaderBit = null;
	private Bitmap eliteLic[][] = null;
	private Bitmap eliteBit[] = null;
	private int eliteNow = 0;
	private boolean roleFlag = false;
	public static boolean fineRole = false;
	private int directRole = 0;// 0下1左2右3上
	private boolean turnRole = false;
	private Bitmap badgeBase = null;
	private Bitmap[] badgeBit = null;
	public static int[][] logicMap = null;
	private static Bitmap[][] imageMap0 = null;
	private static Bitmap[][] imageMap1 = null;
	private static Bitmap[][] imageMap2 = null;

	Paint spPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

	public MDraw(Context context) {
		mContext = context;
		spPaint.setTypeface(Typeface.createFromAsset(mContext.getAssets(),
				"fonts/jinghei.ttf"));
		/**********************/
		IGame.dexValue = new int[649];
		monsterBit = Bitmap.createBitmap(FPublic.CreateBitmap(mContext,
				R.drawable.substitute, VData.UNIT, VData.UNIT), 0, 0,
				VData.UNIT, VData.UNIT);
		leaderLic = FPublic.CreateBitmap(mContext, NoSwitch.char1XX[0],
				4 * VData.roleSize, 4 * VData.roleSize);
		leaderBit = Bitmap.createBitmap(leaderLic, 0, 0, VData.roleSize,
				VData.roleSize);
		// 四天王的四个朝向
		eliteLic = new Bitmap[4][4];
		eliteBit = new Bitmap[4];
		for (int i = 0; i < 4; i++) {
			tempBit = FPublic.CreateBitmap(mContext, NoSwitch.char2XX[i],
					4 * VData.roleSize, 4 * VData.roleSize);
			for (int j = 0; j < 4; j++) {
				eliteLic[i][j] = Bitmap.createBitmap(tempBit, 0, j
						* VData.roleSize, VData.roleSize, VData.roleSize);
			}
		}
		for (int i = 0; i < 4; i++) {
			eliteBit[i] = eliteLic[i][0];
		}
		badgeBase = FPublic.CreateBitmap(mContext, R.drawable.badge,
				8 * VData.UNIT, 4 * VData.UNIT);
		badgeBit = new Bitmap[8];
		for (int yy = 0; yy < 2; yy++) {
			for (int xx = 0; xx < 4; xx++) {
				badgeBit[4 * yy + xx] = FPublic.CreateBitmap(mContext,
						NoSwitch.badgeXX[4 * yy + xx], 3 * VData.UNIT / 2,
						3 * VData.UNIT / 2);
			}
		}
	}

	public static void reMapInfo() {
		logicMap = VData._rent.getLogic();
		imageMap0 = VData._rent.getImage0();
		imageMap1 = VData._rent.getImage1();
		imageMap2 = VData._rent.getImage2();
	}

	public void drawState(Canvas canvas) {
		try {
			Paint mPaint = new Paint();
			mPaint.setColor(Color.argb(224, 192, 224, 255));

			canvas.drawRect(VData.UNIT / 2, VData.UNIT / 2,
					13 * VData.UNIT / 2, 17 * VData.UNIT / 2, mPaint);
			canvas.drawBitmap(FPublic.CreateBitmap(mContext,
					R.drawable.trainer, 12 * VData.UNIT / 5, 4 * VData.UNIT),
					19 * VData.UNIT / 5, VData.UNIT, null);

			mPaint.setTextSize(58);
			mPaint.setColor(Color.YELLOW);
			mPaint.setTypeface(Typeface.createFromAsset(mContext.getAssets(),
					"fonts/bahamas.ttf"));
			canvas.drawText("Cynthia ♀", VData.UNIT, VData.UNIT * 3 / 2, mPaint);

			drawText(canvas, new Point(VData.UNIT, 20 * VData.UNIT / 8),
					11 * VData.UNIT / 4, "编号", Color.argb(224, 32, 32, 192),
					"#1024", Color.RED, 44);
			drawText(canvas, new Point(VData.UNIT, 27 * VData.UNIT / 8),
					11 * VData.UNIT / 4, "职业", Color.argb(224, 32, 32, 192),
					"训练师", Color.RED, 44);
			drawText(canvas, new Point(VData.UNIT, 34 * VData.UNIT / 8),
					11 * VData.UNIT / 4, "地区", Color.argb(224, 32, 32, 192),
					"神奥", Color.RED, 44);
			drawText(canvas, new Point(VData.UNIT, 41 * VData.UNIT / 8),
					11 * VData.UNIT / 4, "身份", Color.argb(224, 32, 32, 192),
					"冠军", Color.RED, 44);

			mPaint.setTextSize(56);
			mPaint.setColor(Color.argb(192, 247, 238, 214));
			canvas.drawRect(VData.UNIT, 11 * VData.UNIT / 2,
					27 * VData.UNIT / 8, 53 * VData.UNIT / 8, mPaint);
			canvas.drawRect(29 * VData.UNIT / 8, 11 * VData.UNIT / 2,
					6 * VData.UNIT, 53 * VData.UNIT / 8, mPaint);
			canvas.drawRect(VData.UNIT, 55 * VData.UNIT / 8,
					27 * VData.UNIT / 8, 8 * VData.UNIT, mPaint);
			canvas.drawRect(29 * VData.UNIT / 8, 55 * VData.UNIT / 8,
					6 * VData.UNIT, 8 * VData.UNIT, mPaint);
			drawText(canvas, new Point(VData.UNIT, 11 * VData.UNIT / 2),
					new Point(13 * VData.UNIT / 4, 13 * VData.UNIT / 2), "图 鉴",
					Color.argb(192, 128, 0, 64), 3 * VData.UNIT / 4);
			drawText(canvas,
					new Point(15 * VData.UNIT / 4, 11 * VData.UNIT / 2),
					new Point(6 * VData.UNIT, 13 * VData.UNIT / 2), "地 图",
					Color.argb(192, 128, 0, 64), 3 * VData.UNIT / 4);
			drawText(canvas, new Point(VData.UNIT, 7 * VData.UNIT), new Point(
					13 * VData.UNIT / 4, 8 * VData.UNIT), "通 信", Color.argb(
					192, 128, 0, 64), 3 * VData.UNIT / 4);
			drawText(canvas, new Point(15 * VData.UNIT / 4, 7 * VData.UNIT),
					new Point(6 * VData.UNIT, 8 * VData.UNIT), "记 录",
					Color.argb(192, 128, 0, 64), 3 * VData.UNIT / 4);
		} catch (NullPointerException e) {
		}
	}

	public void drawDex(Canvas canvas) {
		Paint mPaint = new Paint();
		mPaint.setColor(Color.argb(224, 224, 224, 192));
		canvas.drawRect(13 * VData.UNIT / 2, VData.UNIT / 2,
				31 * VData.UNIT / 2, 17 * VData.UNIT / 2, mPaint);
		// PM图片
		mPaint.setColor(Color.argb(224, 192, 160, 255));
		canvas.drawRect(13 * VData.UNIT / 2 + VData.UNIT / 4, VData.UNIT / 2
				+ VData.UNIT / 4, 19 * VData.UNIT / 2 + VData.UNIT / 4, 7
				* VData.UNIT / 2 + VData.UNIT / 4, mPaint);
		if (IGame.dexValue[currDex + missDex] == 0) {
			tempBit = FPublic.CreateBitmap(mContext, NoSwitch.hdXXX[0],
					3 * VData.UNIT, 3 * VData.UNIT);
		} else {
			tempBit = FPublic.CreateBitmap(mContext, NoSwitch.hdXXX[currDex
					+ missDex + 1], 3 * VData.UNIT, 3 * VData.UNIT);
		}
		canvas.drawBitmap(tempBit, 13 * VData.UNIT / 2 + VData.UNIT / 4,
				VData.UNIT / 2 + VData.UNIT / 4, null);
		// 完成情况
		drawText(canvas, new Point(13 * VData.UNIT / 2 + VData.UNIT / 4,
				8 * VData.UNIT / 2), new Point(15 * VData.UNIT / 2,
				9 * VData.UNIT / 2), "长", Color.argb(224, 160, 64, 224), 44);
		if (IGame.dexValue[currDex + missDex] < 2) {
			drawText(canvas,
					new Point(15 * VData.UNIT / 2, 8 * VData.UNIT / 2),
					new Point(19 * VData.UNIT / 2 + VData.UNIT / 4,
							9 * VData.UNIT / 2), "???m", Color.argb(224, 160,
							64, 224), 44);
		} else {
			drawText(canvas,
					new Point(15 * VData.UNIT / 2, 8 * VData.UNIT / 2),
					new Point(19 * VData.UNIT / 2 + VData.UNIT / 4,
							9 * VData.UNIT / 2), XInfo.dexHeight[currDex
							+ missDex]
							+ "m", Color.argb(224, 160, 64, 224), 44);
		}
		drawText(canvas, new Point(13 * VData.UNIT / 2 + VData.UNIT / 4, 9
						* VData.UNIT / 2 + VData.UNIT / 4), new Point(
						15 * VData.UNIT / 2, 10 * VData.UNIT / 2 + VData.UNIT / 4),
				"重", Color.argb(224, 160, 64, 224), 44);
		if (IGame.dexValue[currDex + missDex] < 2) {
			drawText(canvas, new Point(15 * VData.UNIT / 2, 9 * VData.UNIT / 2
							+ VData.UNIT / 4), new Point(19 * VData.UNIT / 2
							+ VData.UNIT / 4, 10 * VData.UNIT / 2 + VData.UNIT / 4),
					"???kg", Color.argb(224, 160, 64, 224), 44);
		} else {
			drawText(canvas, new Point(15 * VData.UNIT / 2, 9 * VData.UNIT / 2
							+ VData.UNIT / 4), new Point(19 * VData.UNIT / 2
							+ VData.UNIT / 4, 10 * VData.UNIT / 2 + VData.UNIT / 4),
					XInfo.dexWeight[currDex + missDex] + "kg",
					Color.argb(224, 160, 64, 224), 44);
		}
		drawText(canvas, new Point(13 * VData.UNIT / 2 + VData.UNIT / 4,
				11 * VData.UNIT / 2), new Point(15 * VData.UNIT / 2,
				12 * VData.UNIT / 2), "蛋", Color.argb(224, 160, 64, 224), 44);
		if (IGame.dexValue[currDex + missDex] < 2) {
			drawText(canvas,
					new Point(15 * VData.UNIT / 2, 11 * VData.UNIT / 2),
					new Point(19 * VData.UNIT / 2 + VData.UNIT / 4,
							12 * VData.UNIT / 2), "无资料", Color.argb(224, 160,
							64, 224), 44);
		} else {
			drawText(canvas,
					new Point(15 * VData.UNIT / 2, 11 * VData.UNIT / 2),
					new Point(19 * VData.UNIT / 2 + VData.UNIT / 4,
							12 * VData.UNIT / 2), XInfo.dexEggcate[currDex
							+ missDex][0], Color.argb(224, 160, 64, 224), 44);
			if (!XInfo.dexEggcate[currDex + missDex][0]
					.equals(XInfo.dexEggcate[currDex + missDex][1])) {
				drawText(canvas, new Point(15 * VData.UNIT / 2, 12 * VData.UNIT
								/ 2 + VData.UNIT / 4),
						new Point(19 * VData.UNIT / 2 + VData.UNIT / 4, 13
								* VData.UNIT / 2 + VData.UNIT / 4),
						XInfo.dexEggcate[currDex + missDex][1],
						Color.argb(224, 160, 64, 224), 44);
			}
		}
		drawText(canvas, new Point(13 * VData.UNIT / 2 + VData.UNIT / 4,
				14 * VData.UNIT / 2), new Point(15 * VData.UNIT / 2,
				15 * VData.UNIT / 2), "遇", Color.argb(224, 64, 32, 160), 44);
		drawText(canvas, new Point(15 * VData.UNIT / 2, 14 * VData.UNIT / 2),
				new Point(19 * VData.UNIT / 2 + VData.UNIT / 4,
						15 * VData.UNIT / 2), IGame.dexV1 + "/" + 649,
				Color.argb(224, 64, 32, 160), 44);
		drawText(canvas, new Point(13 * VData.UNIT / 2 + VData.UNIT / 4, 15
						* VData.UNIT / 2 + VData.UNIT / 4), new Point(
						15 * VData.UNIT / 2, 16 * VData.UNIT / 2 + VData.UNIT / 4),
				"捕", Color.argb(224, 64, 32, 160), 44);
		drawText(canvas, new Point(15 * VData.UNIT / 2, 15 * VData.UNIT / 2
				+ VData.UNIT / 4), new Point(19 * VData.UNIT / 2 + VData.UNIT
				/ 4, 16 * VData.UNIT / 2 + VData.UNIT / 4), IGame.dexV2 + "/"
				+ 649, Color.argb(224, 64, 32, 160), 44);
		// 索引变化函数
		if (IGame.mode == 0) {
			dexSpeed = 0;
			dexDelay = 64;
			IGame.dexUpper = false;
			IGame.dexDown = false;
		} else {
			if (dexDelay == 64) {
				if (dexSpeed++ > 128) {
					dexDelay = 16;
				}
			}
		}
		if (IGame.dexUpper) {
			ot = System.currentTimeMillis();
			if (ot - it > dexDelay) {
				it = System.currentTimeMillis();
				if (currDex >= 1) {
					if (missDex > 4) {
						missDex--;
					} else {
						currDex -= 1;
					}
				} else {
					currDex = 0;
					if (missDex > 0) {
						missDex--;
					}
				}
			}
		}
		if (IGame.dexDown) {
			ot = System.currentTimeMillis();
			if (ot - it > dexDelay) {
				it = System.currentTimeMillis();
				if (currDex <= 639) {
					if (missDex < 4) {
						missDex++;
					} else {
						currDex += 1;
					}
				} else {
					currDex = 640;
					if (missDex < 8) {
						missDex++;
					}
				}
			}
		}
		// 索引 9 * 56 + 8 * 7 = 560
		canvas.drawRect(10 * VData.UNIT, VData.UNIT / 2 + VData.UNIT / 4, 15
						* VData.UNIT + VData.UNIT / 4, 8 * VData.UNIT + VData.UNIT / 4,
				mPaint);
		// 当前项目
		// mPaint.setStyle(Style.STROKE);
		// mPaint.setStrokeWidth(4);
		mPaint.setColor(Color.argb(224, 255, 255, 0));
		canvas.drawRect(10 * VData.UNIT, VData.UNIT + 63 * missDex,
				61 * VData.UNIT / 4, VData.UNIT + 63 * missDex + 56, mPaint);
		for (int i = 0; i < 9; i++) {
			String strDex;
			int intDex = currDex + 1 + i;
			if (intDex < 10) {
				strDex = "00" + intDex;
			} else if (intDex < 100) {
				strDex = "0" + intDex;
			} else {
				strDex = "" + intDex;
			}
			drawText(canvas, new Point(10 * VData.UNIT, VData.UNIT + 63 * i),
					new Point(11 * VData.UNIT, VData.UNIT + 63 * i + 56),
					strDex, Color.argb(224, 32, 0, 32), 40);
			if (IGame.dexValue[currDex + i] < 1) {
				drawText(
						canvas,
						new Point(11 * VData.UNIT, VData.UNIT + 63 * i),
						new Point(55 * VData.UNIT / 4, VData.UNIT + 63 * i + 56),
						"----", Color.argb(224, 32, 0, 32), 40);
				drawText(canvas, new Point(55 * VData.UNIT / 4, VData.UNIT + 63
						* i), new Point(61 * VData.UNIT / 4, VData.UNIT + 63
						* i + 56), "--", Color.argb(224, 32, 0, 32), 40);
			} else {
				String strName = XInfo.dexName[currDex + i];
				drawText(
						canvas,
						new Point(11 * VData.UNIT, VData.UNIT + 63 * i),
						new Point(55 * VData.UNIT / 4, VData.UNIT + 63 * i + 56),
						strName, Color.argb(224, 32, 0, 32), 40);
				String strType, type1, type2;
				type1 = XInfo.dexType[currDex + i][0];
				type2 = XInfo.dexType[currDex + i][1];
				if (type1.equals(type2))
					strType = type1;
				else
					strType = type1 + "+" + type2;
				drawText(canvas, new Point(55 * VData.UNIT / 4, VData.UNIT + 63
						* i), new Point(61 * VData.UNIT / 4, VData.UNIT + 63
						* i + 56), strType, Color.argb(224, 32, 0, 32), 40);
			}
		}
	}

	public void drawMap(Canvas canvas) {
		Paint mPaint = new Paint();
		for (int y = 0; y < VData.landMap.baseY; y++) {
			for (int x = 0; x < VData.landMap.baseX; x++) {
				if (VData.landMap.baseMap[y][x] == -1) {
					// -1道路
					mPaint.setColor(Color.argb(224, 255, 255, 224));
				} else if (VData.landMap.baseMap[y][x] <= 10) {
					// 0-9城市
					mPaint.setColor(Color.argb(224, 255, 255, 0));
				} else if (VData.landMap.baseMap[y][x] <= 89) {
					// 80-87郊野
					mPaint.setColor(Color.argb(224, 128, 192, 255));
				} else if (VData.landMap.baseMap[y][x] == 99) {
					// 99森林
					mPaint.setColor(Color.argb(224, 224, 224, 192));
				} else if (VData.landMap.baseMap[y][x] == 100) {
					// 100山区
					mPaint.setColor(Color.argb(224, 192, 160, 128));
				}
				canvas.drawRect((13 + x) * VData.UNIT / 2, (1 + y) * VData.UNIT
						/ 2, (14 + x) * VData.UNIT / 2, (2 + y) * VData.UNIT
						/ 2, mPaint);
			}
		}
		// 简单提示说明
		String simInfo = "";
		switch (VData.landMap.baseMap[infoY][infoX]) {
			case -1:
				simInfo = "道路";
				break;
			case 0:
				simInfo = "家乡";
				break;
			case 1:
				simInfo = "一城";
				break;
			case 2:
				simInfo = "二城";
				break;
			case 3:
				simInfo = "三城";
				break;
			case 4:
				simInfo = "四城";
				break;
			case 5:
				simInfo = "五城";
				break;
			case 6:
				simInfo = "六城";
				break;
			case 7:
				simInfo = "七城";
				break;
			case 8:
				simInfo = "八城";
				break;
			case 9:
				simInfo = "联盟";
				break;
			case 80:
			case 81:
			case 82:
			case 83:
			case 84:
			case 85:
			case 86:
			case 87:
				simInfo = "郊野";
				break;
			case 99:
				simInfo = "森林";
				break;
			case 100:
				simInfo = "山区";
				break;
			default:
				simInfo = "未知";
				break;
		}
		drawText(canvas, new Point((12 + infoX) * VData.UNIT / 2, infoY
				* VData.UNIT / 2), new Point((15 + infoX) * VData.UNIT / 2,
				(1 + infoY) * VData.UNIT / 2), simInfo, Color.argb(224, 32, 0,
				32), 38);
		// 标记说明位置
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(6);
		mPaint.setColor(Color.argb(192, 32, 0, 32));
		canvas.drawRect((13 + infoX) * VData.UNIT / 2, (1 + infoY) * VData.UNIT
				/ 2, (14 + infoX) * VData.UNIT / 2, (2 + infoY) * VData.UNIT
				/ 2, mPaint);
		// 标记当前位置
		mPaint.setColor(Color.argb(192, 255, 0, 32));
		canvas.drawRect((13 + VData.locX) * VData.UNIT / 2, (1 + VData.locY)
						* VData.UNIT / 2, (14 + VData.locX) * VData.UNIT / 2,
				(2 + VData.locY) * VData.UNIT / 2, mPaint);
	}

	public void drawVia(Canvas canvas) {
		Paint mPaint = new Paint();
		// 队伍情况
		mPaint.setColor(Color.argb(248, 255, 224, 224));
		canvas.drawRect(13 * VData.UNIT / 2 - 110, VData.UNIT / 2,
				13 * VData.UNIT / 2, 17 * VData.UNIT / 2, mPaint);
		mPaint.setColor(Color.argb(160, 224, 160, 224));
		canvas.drawRect(13 * VData.UNIT / 2 - 110, VData.UNIT / 2 + 106
				* IGame.pmSeq, 13 * VData.UNIT / 2, VData.UNIT / 2 + 106
				* IGame.pmSeq + 110, mPaint);
		for (int i = 0; i < XInfo.hTeamC; i++) {
			// 因为是获取88*88的Bitmap，所以微调位置
			canvas.drawBitmap(XInfo._team.getBitmap0(i),
					13 * VData.UNIT / 2 - 100, VData.UNIT / 2 + 106 * i + 4,
					null);
		}
		// 盒子墙纸
		mPaint.setColor(Color.argb(224, 224, 224, 192));
		canvas.drawRect(13 * VData.UNIT / 2, VData.UNIT / 2,
				31 * VData.UNIT / 2, 17 * VData.UNIT / 2, mPaint);
		tempBit = FPublic.CreateBitmap(mContext,
				NoSwitch.boxXXX[currBox >= 12 ? currBox - 12 : currBox],
				9 * VData.UNIT, 8 * VData.UNIT);
		canvas.drawBitmap(tempBit, 13 * VData.UNIT / 2, VData.UNIT / 2, null);
		// 指示文字
		String boxInfo = "";
		if (currBox == 0) {
			boxInfo = "24";
		} else if (currBox <= 9) {
			boxInfo = "0" + currBox;
		} else {
			boxInfo = "" + currBox;
		}
		drawText(canvas, new Point(13 * VData.UNIT / 2 + 3 * 4,
						VData.UNIT / 2 + 3 * 4), new Point(
						13 * VData.UNIT / 2 + 28 * 4, VData.UNIT / 2 + 24 * 4),
				boxInfo, Color.argb(224, 32, 0, 32), 52);
		if (currBox == 23) {
			boxInfo = "01";
		} else if (currBox <= 7) {
			boxInfo = "0" + (currBox + 2);
		} else {
			boxInfo = "" + (currBox + 2);
		}
		drawText(canvas, new Point(13 * VData.UNIT / 2 + 152 * 4,
				VData.UNIT / 2 + 3 * 4), new Point(13 * VData.UNIT / 2 + 177
				* 4, VData.UNIT / 2 + 24 * 4), "" + boxInfo, Color.argb(224,
				32, 0, 32), 52);
		if (currBox <= 8) {
			boxInfo = "0" + (currBox + 1);
		} else {
			boxInfo = "" + (currBox + 1);
		}
		drawText(canvas, new Point(13 * VData.UNIT / 2 + 28 * 4,
				VData.UNIT / 2 + 3 * 4), new Point(13 * VData.UNIT / 2 + 152
				* 4, VData.UNIT / 2 + 24 * 4), "PokeBox" + boxInfo, Color.argb(
				224, 32, 0, 32), 52);
		// 口袋妖怪
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				// 0是蛋此外对应编号
				if (XInfo.viaBoxIndex[currBox][7 * i + j] > 0) {
					Bitmap temp = FPublic
							.CreateBitmap(mContext,
									NoSwitch.pmXXX[XInfo.viaBoxIndex[currBox][7
											* i + j]], 2 * 96, 96);
					tempBit = Bitmap.createBitmap(temp, 0, 0, 96, 96);
					// pmXXX偏下所以减去10美化
					canvas.drawBitmap(tempBit, 13 * VData.UNIT / 2 + 24 + 96
							* j, VData.UNIT / 2 + 120 + 96 * i, null);
				}
			}
		}
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(4);
		mPaint.setColor(Color.argb(192, 224, 224, 32));
		// 调整队伍
		if (boxZ >= 0) {
			canvas.drawRect(13 * VData.UNIT / 2 - 108, VData.UNIT / 2 + 106
					* boxZ + 2, 13 * VData.UNIT / 2 - 2, VData.UNIT / 2 + 106
					* (boxZ + 1) + 2, mPaint);
		}
		if (boxY >= 0 && boxX >= 0) {
			// 调整箱子
			canvas.drawRect(13 * VData.UNIT / 2 + 24 + 96 * boxX, VData.UNIT
							/ 2 + 130 + 96 * boxY, 13 * VData.UNIT / 2 + 24 + 96
							* (boxX + 1), VData.UNIT / 2 + 130 + 96 * (boxY + 1),
					mPaint);
		}
	}

	public void drawPen(Canvas canvas) {
		Paint mPaint = new Paint();
		mPaint.setColor(Color.argb(224, 224, 224, 192));
		canvas.drawRect(13 * VData.UNIT / 2, VData.UNIT / 2,
				31 * VData.UNIT / 2, 17 * VData.UNIT / 2, mPaint);
		// 徽章
		canvas.drawBitmap(badgeBase, 7 * VData.UNIT, VData.UNIT, null);
		for (int yy = 0; yy < 2; yy++) {
			for (int xx = 0; xx < 4; xx++) {
				if (IGame.gymProgress[4 * yy + xx] >= 3) {
					canvas.drawBitmap(badgeBit[4 * yy + xx], (7 + 2 * xx)
							* VData.UNIT + VData.UNIT / 4, (1 + 2 * yy)
							* VData.UNIT + VData.UNIT / 4, null);
				}
			}
		}

		// 存盘操作
		if (saveDelay > 0) {
			saveDelay--;
			drawText(canvas,
					new Point(27 * VData.UNIT / 2, 15 * VData.UNIT / 2),
					new Point(31 * VData.UNIT / 2, 17 * VData.UNIT / 2),
					"save", Color.argb(224, 224, 64, 32), 52);
		} else {
			drawText(canvas,
					new Point(27 * VData.UNIT / 2, 15 * VData.UNIT / 2),
					new Point(31 * VData.UNIT / 2, 17 * VData.UNIT / 2),
					"save", Color.argb(224, 32, 0, 32), 52);
		}
	}

	public void drawTeam(Canvas canvas) {
		try {
			Paint mPaint = new Paint();

			mPaint.setColor(Color.argb(224, 160, 160, 255));
			canvas.drawRect(VData.UNIT / 2, VData.UNIT / 2,
					13 * VData.UNIT / 2, 17 * VData.UNIT / 2, mPaint);

			// mPaint.setStyle(Style.STROKE);
			// mPaint.setStrokeWidth(VData.UNIT / 10);
			mPaint.setColor(Color.argb(224, 255, 255, 0));
			canvas.drawRect(VData.UNIT / 2 + VData.UNIT / 4, VData.UNIT / 2
					+ 16 + 104 * IGame.pmSeq, 13 * VData.UNIT / 2 - VData.UNIT
					/ 4, VData.UNIT / 2 + 104 + 104 * IGame.pmSeq, mPaint);

			for (int i = 0; i < XInfo.hTeamC; i++) {
				drawText(canvas, new Point(VData.UNIT, VData.UNIT + 24 + 104
								* i), new Point(VData.UNIT, VData.UNIT + 24 + 104 * i),
						XInfo.teamHName[i], Color.argb(224, 128, 0, 64), 40);
				drawText(canvas, new Point(4 * VData.UNIT, VData.UNIT + 24
								+ 104 * i), new Point(4 * VData.UNIT, VData.UNIT + 24
								+ 104 * i), "Lv" + XInfo.teamHLevel[i],
						Color.argb(224, 128, 0, 64), 40);
				if (i == IGame.pmSeq) {
					if (iconBool) {
						canvas.drawBitmap(XInfo._team.getBitmap0(i),
								5 * VData.UNIT, VData.UNIT + 8 + 104 * i - 40,
								null);
					} else {
						canvas.drawBitmap(XInfo._team.getBitmap0(i),
								5 * VData.UNIT, VData.UNIT + 8 + 104 * i - 48,
								null);
					}
					if (iconClick-- == 0) {
						iconClick = 4;
						if (iconBool) {
							iconBool = false;
						} else {
							iconBool = true;
						}
					}
					if (icon2Click-- == 0) {
						icon2Click = 8;
						if (icon2Bool) {
							icon2Bool = false;
						} else {
							icon2Bool = true;
						}
					}
				} else {
					if (icon2Bool) {
						canvas.drawBitmap(XInfo._team.getBitmap0(i),
								5 * VData.UNIT, VData.UNIT + 8 + 104 * i - 44,
								null);
					} else {
						canvas.drawBitmap(XInfo._team.getBitmap1(i),
								5 * VData.UNIT, VData.UNIT + 8 + 104 * i - 44,
								null);
					}
				}
			}
		} catch (NullPointerException e) {
		}
	}

	public void drawHome(Canvas canvas) {
		try {
			Paint mPaint = new Paint();
			mPaint.setColor(Color.argb(224, 224, 192, 255));
			canvas.drawRect(13 * VData.UNIT / 2, VData.UNIT / 2,
					31 * VData.UNIT / 2, 17 * VData.UNIT / 2, mPaint);
			// 内容
			String type, type1, type2;
			type1 = XInfo.teamHType[IGame.pmSeq][0];
			type2 = XInfo.teamHType[IGame.pmSeq][1];
			if (type1.equals(type2))
				type = type1;
			else
				type = type1 + "+" + type2;
			drawText(canvas, new Point(13 * VData.UNIT / 2 + VData.UNIT / 4, 2
							* VData.UNIT / 4 + VData.UNIT / 8), new Point(13
							* VData.UNIT / 2 + VData.UNIT / 4, 5 * VData.UNIT / 4),
					"属性", Color.argb(224, 32, 0, 32), 44);
			drawText(canvas, new Point(8 * VData.UNIT + VData.UNIT / 8, 2
							* VData.UNIT / 4 + VData.UNIT / 8), new Point(8
							* VData.UNIT + VData.UNIT / 8, 5 * VData.UNIT / 4), type,
					Color.argb(224, 32, 0, 32), 44);
			drawText(canvas, new Point(13 * VData.UNIT / 2 + VData.UNIT / 4, 5
							* VData.UNIT / 4 + VData.UNIT / 8), new Point(13
							* VData.UNIT / 2 + VData.UNIT / 4, 8 * VData.UNIT / 4),
					"特性", Color.argb(224, 32, 0, 32), 44);
			drawText(canvas, new Point(8 * VData.UNIT + VData.UNIT / 8, 5
							* VData.UNIT / 4 + VData.UNIT / 8), new Point(8
							* VData.UNIT + VData.UNIT / 8, 8 * VData.UNIT / 4),
					XInfo.teamHAbility[IGame.pmSeq],
					Color.argb(224, 32, 0, 32), 44);
			drawText(canvas, new Point(13 * VData.UNIT / 2 + VData.UNIT / 4, 8
							* VData.UNIT / 4 + VData.UNIT / 8), new Point(13
							* VData.UNIT / 2 + VData.UNIT / 4, 11 * VData.UNIT / 4),
					"性格", Color.argb(224, 32, 0, 32), 44);
			drawText(canvas, new Point(8 * VData.UNIT + VData.UNIT / 8, 8
							* VData.UNIT / 4 + VData.UNIT / 8), new Point(8
							* VData.UNIT + VData.UNIT / 8, 11 * VData.UNIT / 4),
					VData.xBattle.kidneyS[XInfo.teamHKidney[IGame.pmSeq]],
					Color.argb(224, 32, 0, 32), 44);
			// 六围
			drawText(canvas, new Point(11 * VData.UNIT, 2 * VData.UNIT / 4
							+ VData.UNIT / 8), new Point(11 * VData.UNIT,
							5 * VData.UNIT / 4), "生命" + XBattle.hTeamV[IGame.pmSeq][0],
					Color.argb(224, 32, 0, 32), 44);
			spPaint.setTextSize(40);
			spPaint.setColor(Color.argb(224, 32, 0, 32));
			int kidney = XInfo.teamHKidney[IGame.pmSeq];
			switch (VData.xBattle.kidneyV[kidney][4]) {
				case '+':
					drawText(canvas, new Point(53 * VData.UNIT / 4, 2 * VData.UNIT
							/ 4 + VData.UNIT / 8), new Point(53 * VData.UNIT / 4,
							5 * VData.UNIT / 4), "速度"
							+ XBattle.hTeamV[IGame.pmSeq][3], Color.argb(224, 32,
							32, 255), 44);
					break;
				case '-':
					drawText(canvas, new Point(53 * VData.UNIT / 4, 2 * VData.UNIT
							/ 4 + VData.UNIT / 8), new Point(53 * VData.UNIT / 4,
							5 * VData.UNIT / 4), "速度"
							+ XBattle.hTeamV[IGame.pmSeq][3], Color.argb(224, 255,
							32, 32), 44);
					break;
				default:
					drawText(canvas, new Point(53 * VData.UNIT / 4, 2 * VData.UNIT
							/ 4 + VData.UNIT / 8), new Point(53 * VData.UNIT / 4,
							5 * VData.UNIT / 4), "速度"
							+ XBattle.hTeamV[IGame.pmSeq][3], Color.argb(224, 32,
							0, 32), 44);
					break;
			}
			switch (VData.xBattle.kidneyV[kidney][0]) {
				case '+':
					drawText(canvas, new Point(11 * VData.UNIT, 5 * VData.UNIT / 4
							+ VData.UNIT / 8), new Point(11 * VData.UNIT,
							8 * VData.UNIT / 4), "物攻"
							+ XBattle.hTeamV[IGame.pmSeq][1], Color.argb(224, 32,
							32, 255), 44);
					break;
				case '-':
					drawText(canvas, new Point(11 * VData.UNIT, 5 * VData.UNIT / 4
							+ VData.UNIT / 8), new Point(11 * VData.UNIT,
							8 * VData.UNIT / 4), "物攻"
							+ XBattle.hTeamV[IGame.pmSeq][1], Color.argb(224, 255,
							32, 32), 44);
					break;
				default:
					drawText(canvas, new Point(11 * VData.UNIT, 5 * VData.UNIT / 4
							+ VData.UNIT / 8), new Point(11 * VData.UNIT,
							8 * VData.UNIT / 4), "物攻"
							+ XBattle.hTeamV[IGame.pmSeq][1], Color.argb(224, 32,
							0, 32), 44);
					break;
			}
			switch (VData.xBattle.kidneyV[kidney][2]) {
				case '+':
					drawText(canvas, new Point(53 * VData.UNIT / 4, 5 * VData.UNIT
							/ 4 + +VData.UNIT / 8), new Point(53 * VData.UNIT / 4,
							8 * VData.UNIT / 4), "特攻"
							+ XBattle.hTeamV[IGame.pmSeq][4], Color.argb(224, 32,
							32, 255), 44);
					break;
				case '-':
					drawText(canvas, new Point(53 * VData.UNIT / 4, 5 * VData.UNIT
							/ 4 + VData.UNIT / 8), new Point(53 * VData.UNIT / 4,
							8 * VData.UNIT / 4), "特攻"
							+ XBattle.hTeamV[IGame.pmSeq][4], Color.argb(224, 255,
							32, 32), 44);
					break;
				default:
					drawText(canvas, new Point(53 * VData.UNIT / 4, 5 * VData.UNIT
							/ 4 + VData.UNIT / 8), new Point(53 * VData.UNIT / 4,
							8 * VData.UNIT / 4), "特攻"
							+ XBattle.hTeamV[IGame.pmSeq][4], Color.argb(224, 32,
							0, 32), 44);
					break;
			}
			switch (VData.xBattle.kidneyV[kidney][1]) {
				case '+':
					drawText(canvas, new Point(11 * VData.UNIT, 8 * VData.UNIT / 4
							+ VData.UNIT / 8), new Point(11 * VData.UNIT,
							11 * VData.UNIT / 4), "物防"
							+ XBattle.hTeamV[IGame.pmSeq][2], Color.argb(224, 32,
							32, 255), 44);
					break;
				case '-':
					drawText(canvas, new Point(11 * VData.UNIT, 8 * VData.UNIT / 4
							+ VData.UNIT / 8), new Point(11 * VData.UNIT,
							11 * VData.UNIT / 4), "物防"
							+ XBattle.hTeamV[IGame.pmSeq][2], Color.argb(224, 255,
							32, 32), 44);
					break;
				default:
					drawText(canvas, new Point(11 * VData.UNIT, 8 * VData.UNIT / 4
							+ VData.UNIT / 8), new Point(11 * VData.UNIT,
							11 * VData.UNIT / 4), "物防"
							+ XBattle.hTeamV[IGame.pmSeq][2], Color.argb(224, 32,
							0, 32), 44);
					break;
			}
			switch (VData.xBattle.kidneyV[kidney][3]) {
				case '+':
					drawText(canvas, new Point(53 * VData.UNIT / 4, 8 * VData.UNIT
							/ 4 + VData.UNIT / 8), new Point(53 * VData.UNIT / 4,
							11 * VData.UNIT / 4), "特防"
							+ XBattle.hTeamV[IGame.pmSeq][5], Color.argb(224, 32,
							32, 255), 44);
					break;
				case '-':
					drawText(canvas, new Point(53 * VData.UNIT / 4, 8 * VData.UNIT
							/ 4 + VData.UNIT / 8), new Point(53 * VData.UNIT / 4,
							11 * VData.UNIT / 4), "特防"
							+ XBattle.hTeamV[IGame.pmSeq][5], Color.argb(224, 255,
							32, 32), 44);
					break;
				default:
					drawText(canvas, new Point(53 * VData.UNIT / 4, 8 * VData.UNIT
							/ 4 + VData.UNIT / 8), new Point(53 * VData.UNIT / 4,
							11 * VData.UNIT / 4), "特防"
							+ XBattle.hTeamV[IGame.pmSeq][5], Color.argb(224, 32,
							0, 32), 44);
					break;
			}
			// 技能格子
			mPaint.setColor(Color.argb(160, 224, 160, 224));
			for (int i = 0; i < 4; i++) {
				// 信息
				canvas.drawRect(27 * VData.UNIT / 4, 11 * VData.UNIT / 4
								+ VData.UNIT / 8 + 11 * i * VData.UNIT / 8,
						40 * VData.UNIT / 4, 17 * VData.UNIT / 4 - VData.UNIT
								/ 8 + 11 * i * VData.UNIT / 8, mPaint);
				// 说明
				canvas.drawRect(41 * VData.UNIT / 4, 11 * VData.UNIT / 4
								+ VData.UNIT / 8 + 11 * i * VData.UNIT / 8,
						61 * VData.UNIT / 4, 17 * VData.UNIT / 4 - VData.UNIT
								/ 8 + 11 * i * VData.UNIT / 8, mPaint);

				String strName = XInfo.teamHMoveName[IGame.pmSeq][i];
				String strType = XInfo.teamHMoveType[IGame.pmSeq][i];
				String strCate = XInfo.teamHMoveCate[IGame.pmSeq][i];
				String strInfo = XInfo.teamHMoveInfo[IGame.pmSeq][i];
				// 信息
				drawText(canvas, new Point(27 * VData.UNIT / 4, 11 * VData.UNIT
								/ 4 + VData.UNIT / 8 + 11 * i * VData.UNIT / 8),
						new Point(40 * VData.UNIT / 4, 15 * VData.UNIT / 4
								- VData.UNIT / 8 + 11 * i * VData.UNIT / 8),
						strName, Color.argb(224, 64, 0, 32), 48);
				drawText(canvas, new Point(27 * VData.UNIT / 4, 14 * VData.UNIT
								/ 4 + VData.UNIT / 8 + 11 * i * VData.UNIT / 8),
						new Point(32 * VData.UNIT / 4, 17 * VData.UNIT / 4
								- VData.UNIT / 8 + 11 * i * VData.UNIT / 8),
						strType, Color.argb(224, 32, 0, 32), 36);
				drawText(canvas, new Point(32 * VData.UNIT / 4, 14 * VData.UNIT
								/ 4 + VData.UNIT / 8 + 11 * i * VData.UNIT / 8),
						new Point(40 * VData.UNIT / 4, 17 * VData.UNIT / 4
								- VData.UNIT / 8 + 11 * i * VData.UNIT / 8),
						strCate, Color.argb(224, 32, 0, 32), 36);
				// 说明，最长3 * 12 = 36个字符
				if (strInfo.length() > 12) {
					drawText(
							canvas,
							new Point(41 * VData.UNIT / 4 + VData.UNIT / 8, 47
									* VData.UNIT / 16 + 11 * i * VData.UNIT / 8),
							new Point(41 * VData.UNIT / 4 + VData.UNIT / 8, 52
									* VData.UNIT / 16 + 11 * i * VData.UNIT / 8),
							strInfo.substring(0, 12),
							Color.argb(224, 64, 128, 255), 32);
					strInfo = strInfo.substring(12);
					if (strInfo.length() > 12) {
						drawText(canvas, new Point(41 * VData.UNIT / 4
										+ VData.UNIT / 8, 53 * VData.UNIT / 16 + 11 * i
										* VData.UNIT / 8), new Point(41 * VData.UNIT
										/ 4 + VData.UNIT / 8, 58 * VData.UNIT / 16 + 11
										* i * VData.UNIT / 8),
								strInfo.substring(0, 12),
								Color.argb(224, 64, 128, 255), 32);
						strInfo = strInfo.substring(12);
						if (strInfo.length() > 12) {
							drawText(canvas, new Point(41 * VData.UNIT / 4
											+ VData.UNIT / 8, 59 * VData.UNIT / 16 + 11
											* i * VData.UNIT / 8),
									new Point(41 * VData.UNIT / 4 + VData.UNIT
											/ 8, 64 * VData.UNIT / 16 + 11 * i
											* VData.UNIT / 8),
									strInfo.substring(0, 12),
									Color.argb(224, 64, 128, 255), 32);
						} else {
							drawText(canvas, new Point(41 * VData.UNIT / 4
											+ VData.UNIT / 8, 59 * VData.UNIT / 16 + 11
											* i * VData.UNIT / 8),
									new Point(41 * VData.UNIT / 4 + VData.UNIT
											/ 8, 64 * VData.UNIT / 16 + 11 * i
											* VData.UNIT / 8), strInfo,
									Color.argb(224, 64, 128, 255), 32);
						}
					} else {
						drawText(canvas, new Point(41 * VData.UNIT / 4
										+ VData.UNIT / 8, 53 * VData.UNIT / 16 + 11 * i
										* VData.UNIT / 8), new Point(41 * VData.UNIT
										/ 4 + VData.UNIT / 8, 58 * VData.UNIT / 16 + 11
										* i * VData.UNIT / 8), strInfo,
								Color.argb(224, 64, 128, 255), 32);
					}
				} else {
					drawText(
							canvas,
							new Point(41 * VData.UNIT / 4 + VData.UNIT / 8, 47
									* VData.UNIT / 16 + 11 * i * VData.UNIT / 8),
							new Point(41 * VData.UNIT / 4 + VData.UNIT / 8, 52
									* VData.UNIT / 16 + 11 * i * VData.UNIT / 8),
							strInfo, Color.argb(224, 64, 128, 255), 32);
				}
			}
		} catch (NullPointerException e) {
		}
	}

	public void drawFight(Canvas canvas, boolean noInfo) {
		try {
			Paint mPaint = new Paint();
			mPaint.setColor(Color.argb(192, 247, 238, 168));
			if (!noInfo) {
				mPaint.setColor(Color.argb(192, 247, 238, 214));
				canvas.drawRect(0, 0, 16 * VData.UNIT, 9 * VData.UNIT, mPaint);
			}
			spPaint.setTextSize(VData.UNIT);
			spPaint.setColor(Color.argb(224, 64, 128, 255));
			canvas.drawRect(VData.UNIT / 4, 29 * VData.UNIT / 4,
					4 * VData.UNIT, 35 * VData.UNIT / 4, mPaint);
			canvas.drawText("战 斗", VData.UNIT, 67 * VData.UNIT / 8, spPaint);
			canvas.drawRect(17 * VData.UNIT / 4, 29 * VData.UNIT / 4,
					8 * VData.UNIT, 35 * VData.UNIT / 4, mPaint);
			canvas.drawText("队 伍", 5 * VData.UNIT, 67 * VData.UNIT / 8, spPaint);
			canvas.drawRect(33 * VData.UNIT / 4, 29 * VData.UNIT / 4,
					12 * VData.UNIT, 35 * VData.UNIT / 4, mPaint);
			canvas.drawText("物 品", 9 * VData.UNIT, 67 * VData.UNIT / 8, spPaint);
			canvas.drawRect(49 * VData.UNIT / 4, 29 * VData.UNIT / 4,
					63 * VData.UNIT / 4, 35 * VData.UNIT / 4, mPaint);
			if (noInfo)
				canvas.drawText("返 回", 103 * VData.UNIT / 8,
						67 * VData.UNIT / 8, spPaint);
			else
				canvas.drawText("逃 跑", 103 * VData.UNIT / 8,
						67 * VData.UNIT / 8, spPaint);
			mPaint.setAlpha(240);
			if (IGame.bfightFlag) {
				tempBit = FPublic.CreateBitmap(mContext, R.drawable.bx07,
						12 * VData.UNIT, 27 * VData.UNIT / 4);
			} else {
				tempBit = FPublic.CreateBitmap(mContext, R.drawable.bx06,
						12 * VData.UNIT, 27 * VData.UNIT / 4);
			}
			canvas.drawBitmap(tempBit, 20, 20, mPaint);

			if (IGame.bfightFlag) {
				tempBit = Bitmap.createBitmap(FPublic.CreateBitmap(mContext,
						R.drawable.bb07, 832, 112), 208, 0, 624, 112);
			} else {
				tempBit = Bitmap.createBitmap(FPublic.CreateBitmap(mContext,
						R.drawable.bb06, 832, 112), 208, 0, 624, 112);
			}
			canvas.drawBitmap(tempBit, 20, 448, mPaint);

			if (IGame.bfightFlag) {
				tempBit = FPublic.CreateBitmap(mContext, R.drawable.bf07, 416,
						224);
			} else {
				tempBit = FPublic.CreateBitmap(mContext, R.drawable.bf06, 416,
						224);
			}
			canvas.drawBitmap(tempBit, 544, 224, mPaint);

			if (VData.xBattle.fightingZH == 0) {
				if (!VData.xBattle.deadH) {
					// int resId =
					// NoSwitch.xbXXX[XInfo.teamHIndex[IGame.pmSeq]];
					// if (IGame.gifViewB.getResource() != resId) {
					// IGame.gifViewB.setResource(resId);
					// }
					// IGame.gifViewB.draw(canvas);
				}
			} else {
				VData.xBattle.fightingZH--;
			}
			if (VData.xBattle.fightingHZ == 0) {
				if (!VData.xBattle.deadZ) {
					// int resId = NoSwitch.xfXXX[IGame.oopDex[IGame.currPM]];
					// if (IGame.gifViewF.getResource() != resId) {
					// IGame.gifViewF.setResource(resId);
					// }
					// IGame.gifViewF.draw(canvas);
				}
			} else {
				VData.xBattle.fightingHZ--;
			}
			drawText(canvas,
					new Point(15 * VData.UNIT / 2, 1 * VData.UNIT / 4),
					new Point(15 * VData.UNIT / 2, 4 * VData.UNIT / 4), "Lv:"
							+ IGame.oopLv[IGame.currPM] + " "
							+ XInfo.teamZName[IGame.currPM],
					Color.argb(224, 32, 32, 192), VData.UNIT * 5 / 8);
			String type, type1, type2;
			type1 = XInfo.teamZType[IGame.currPM][0];
			type2 = XInfo.teamZType[IGame.currPM][1];
			if (type1.equals(type2))
				type = type1;
			else
				type = type1 + "+" + type2;
			drawText(canvas,
					new Point(15 * VData.UNIT / 2, 4 * VData.UNIT / 4),
					new Point(15 * VData.UNIT / 2, 7 * VData.UNIT / 4), type,
					Color.argb(224, 32, 32, 192), VData.UNIT * 5 / 8);
			drawText(canvas,
					new Point(37 * VData.UNIT / 4, 4 * VData.UNIT / 4),
					new Point(37 * VData.UNIT / 4, 7 * VData.UNIT / 4),
					VData.xBattle.getZDamage(), Color.argb(224, 32, 32, 192),
					VData.UNIT * 5 / 8);
			drawText(canvas,
					new Point(45 * VData.UNIT / 4, 4 * VData.UNIT / 4),
					new Point(45 * VData.UNIT / 4, 7 * VData.UNIT / 4),
					XBattle.abnormalInfo[XBattle.abnormalZ[IGame.currPM]],
					Color.argb(224, 32, 32, 192), VData.UNIT * 5 / 8);
			drawText(canvas, new Point(15 * VData.UNIT / 2, 21 * VData.UNIT / 4
							- VData.UNIT / 8), new Point(15 * VData.UNIT / 2, 24
							* VData.UNIT / 4 - VData.UNIT / 8), "Lv:"
							+ XInfo.teamHLevel[IGame.pmSeq] + " "
							+ XInfo.teamHName[IGame.pmSeq],
					Color.argb(224, 192, 32, 32), VData.UNIT * 5 / 8);
			type1 = XInfo.teamHType[IGame.pmSeq][0];
			type2 = XInfo.teamHType[IGame.pmSeq][1];
			if (type1.equals(type2))
				type = type1;
			else
				type = type1 + "+" + type2;
			drawText(canvas, new Point(15 * VData.UNIT / 2, 24 * VData.UNIT / 4
							- VData.UNIT / 8), new Point(15 * VData.UNIT / 2, 27
							* VData.UNIT / 4 - VData.UNIT / 8), type,
					Color.argb(224, 192, 32, 32), VData.UNIT * 5 / 8);
			drawText(canvas, new Point(37 * VData.UNIT / 4, 24 * VData.UNIT / 4
							- VData.UNIT / 8), new Point(37 * VData.UNIT / 4, 27
							* VData.UNIT / 4 - VData.UNIT / 8),
					VData.xBattle.getHDamage(), Color.argb(224, 192, 32, 32),
					VData.UNIT * 5 / 8);
			drawText(canvas, new Point(45 * VData.UNIT / 4, 24 * VData.UNIT / 4
							- VData.UNIT / 8), new Point(45 * VData.UNIT / 4, 27
							* VData.UNIT / 4 - VData.UNIT / 8),
					XBattle.abnormalInfo[XBattle.abnormalH[IGame.pmSeq]],
					Color.argb(224, 192, 32, 32), VData.UNIT * 5 / 8);
			mPaint.setColor(Color.argb(192, 255, 128, 80));
			canvas.drawRect(15 * VData.UNIT / 2, 27 * VData.UNIT / 4
					- VData.UNIT / 8, 24 * VData.UNIT / 2, 28 * VData.UNIT / 4
					- VData.UNIT / 8, mPaint);
			// 战报区
			if (!VData.xBattle.fightNOTE.isEmpty()) {
				int size = VData.xBattle.fightNOTE.size();
				mPaint.setColor(Color.argb(192, 224, 224, 224));
				if (size < 5) {
					canvas.drawRect(VData.UNIT / 4 + VData.UNIT / 20,
							VData.UNIT / 4 + VData.UNIT / 20, 55 * VData.UNIT
									/ 8 + VData.UNIT / 20, (2 + 5 * size)
									* VData.UNIT / 8 + VData.UNIT / 20, mPaint);
					for (int i = 0; i < size; i++) {
						drawText(canvas, new Point(VData.UNIT / 4 + VData.UNIT
										/ 20, (2 + 5 * i) * VData.UNIT / 8 + VData.UNIT
										/ 20), new Point(VData.UNIT / 4 + VData.UNIT
										/ 20, (7 + 5 * i) * VData.UNIT / 8 + VData.UNIT
										/ 20), VData.xBattle.fightNOTE.get(i),
								Color.argb(224, 32, 0, 32), VData.UNIT / 2);
					}
				} else {
					canvas.drawRect(VData.UNIT / 4 + VData.UNIT / 20,
							VData.UNIT / 4 + VData.UNIT / 20, 55 * VData.UNIT
									/ 8 + VData.UNIT / 20, 27 * VData.UNIT / 8
									+ VData.UNIT / 20, mPaint);
					for (int i = size - 5, j = 0; i < size; i++, j++) {
						drawText(canvas, new Point(VData.UNIT / 4 + VData.UNIT
										/ 20, (2 + 5 * j) * VData.UNIT / 8 + VData.UNIT
										/ 20), new Point(VData.UNIT / 4 + VData.UNIT
										/ 20, (7 + 5 * j) * VData.UNIT / 8 + VData.UNIT
										/ 20), VData.xBattle.fightNOTE.get(i),
								Color.argb(224, 32, 0, 32), VData.UNIT / 2);
					}
				}
			}
			// 右侧信息栏目
			if (!noInfo) {
				mPaint.setColor(Color.argb(192, 128, 192, 255));
				canvas.drawRect(49 * VData.UNIT / 4, VData.UNIT / 4,
						63 * VData.UNIT / 4, 7 * VData.UNIT, mPaint);
				mPaint.setAlpha(255);
				mPaint.setColor(Color.argb(224, 32, 32, 192));
				for (int i = 0; i < XInfo.zTeamC; i++) {
					if (VData.xBattle.zMissHP[i] == 0) {
						// 表示濒死
						drawText(canvas, new Point((25 + i) * VData.UNIT / 2,
										1 * VData.UNIT / 4), new Point((26 + i)
										* VData.UNIT / 2, 4 * VData.UNIT / 4), "()",
								Color.argb(224, 32, 32, 192),
								VData.UNIT * 3 / 4);
					} else {
						// 表示存活
						drawText(canvas, new Point((25 + i) * VData.UNIT / 2,
										1 * VData.UNIT / 4), new Point((26 + i)
										* VData.UNIT / 2, 4 * VData.UNIT / 4), "[]",
								Color.argb(224, 32, 32, 192),
								VData.UNIT * 3 / 4);
					}
				}
				drawText(canvas, new Point(49 * VData.UNIT / 4,
						4 * VData.UNIT / 4), new Point(63 * VData.UNIT / 4,
						7 * VData.UNIT / 4), "天气："
						+ XBattle.weatherInfo[XBattle.weather], Color.argb(224,
						32, 0, 32), VData.UNIT / 2);
				String tempInfo = XInfo.teamZAbility[IGame.currPM][2];
				if (tempInfo.equals("无"))
					tempInfo = XInfo.teamZAbility[IGame.currPM][1];
				drawText(canvas, new Point(49 * VData.UNIT / 4,
						7 * VData.UNIT / 4), new Point(63 * VData.UNIT / 4,
						10 * VData.UNIT / 4), "[" + tempInfo + "]", Color.argb(
						224, 32, 32, 192), VData.UNIT / 2);
				tempInfo = XInfo.teamHAbility[IGame.pmSeq];
				drawText(canvas, new Point(49 * VData.UNIT / 4,
						10 * VData.UNIT / 4), new Point(63 * VData.UNIT / 4,
						13 * VData.UNIT / 4), "[" + tempInfo + "]", Color.argb(
						224, 192, 32, 32), VData.UNIT / 2);
				drawText(canvas, new Point(49 * VData.UNIT / 4,
								13 * VData.UNIT / 4), new Point(55 * VData.UNIT / 4,
								16 * VData.UNIT / 4), "SD：",
						Color.argb(224, 32, 0, 32), VData.UNIT / 2);
				drawText(canvas, new Point(49 * VData.UNIT / 4,
								16 * VData.UNIT / 4), new Point(55 * VData.UNIT / 4,
								19 * VData.UNIT / 4), "WG：",
						Color.argb(224, 32, 0, 32), VData.UNIT / 2);
				drawText(canvas, new Point(49 * VData.UNIT / 4,
								19 * VData.UNIT / 4), new Point(55 * VData.UNIT / 4,
								22 * VData.UNIT / 4), "WF：",
						Color.argb(224, 32, 0, 32), VData.UNIT / 2);
				drawText(canvas, new Point(49 * VData.UNIT / 4,
								22 * VData.UNIT / 4), new Point(55 * VData.UNIT / 4,
								25 * VData.UNIT / 4), "TG：",
						Color.argb(224, 32, 0, 32), VData.UNIT / 2);
				drawText(canvas, new Point(49 * VData.UNIT / 4,
								25 * VData.UNIT / 4), new Point(55 * VData.UNIT / 4,
								28 * VData.UNIT / 4), "TF：",
						Color.argb(224, 32, 0, 32), VData.UNIT / 2);

				tempInfo = "" + XBattle.zTeamV[IGame.currPM][3];
				drawText(canvas, new Point(55 * VData.UNIT / 4,
						13 * VData.UNIT / 4), new Point(59 * VData.UNIT / 4,
						16 * VData.UNIT / 4), tempInfo, Color.argb(224, 32, 32,
						192), VData.UNIT / 2);
				tempInfo = "" + XBattle.zTeamV[IGame.currPM][1];
				drawText(canvas, new Point(55 * VData.UNIT / 4,
						16 * VData.UNIT / 4), new Point(59 * VData.UNIT / 4,
						19 * VData.UNIT / 4), tempInfo, Color.argb(224, 32, 32,
						192), VData.UNIT / 2);
				tempInfo = "" + XBattle.zTeamV[IGame.currPM][2];
				drawText(canvas, new Point(55 * VData.UNIT / 4,
						19 * VData.UNIT / 4), new Point(59 * VData.UNIT / 4,
						22 * VData.UNIT / 4), tempInfo, Color.argb(224, 32, 32,
						192), VData.UNIT / 2);
				tempInfo = "" + XBattle.zTeamV[IGame.currPM][4];
				drawText(canvas, new Point(55 * VData.UNIT / 4,
						22 * VData.UNIT / 4), new Point(59 * VData.UNIT / 4,
						25 * VData.UNIT / 4), tempInfo, Color.argb(224, 32, 32,
						192), VData.UNIT / 2);
				tempInfo = "" + XBattle.zTeamV[IGame.currPM][5];
				drawText(canvas, new Point(55 * VData.UNIT / 4,
						25 * VData.UNIT / 4), new Point(59 * VData.UNIT / 4,
						28 * VData.UNIT / 4), tempInfo, Color.argb(224, 32, 32,
						192), VData.UNIT / 2);

				tempInfo = "" + XBattle.hTeamV[IGame.pmSeq][3];
				drawText(canvas, new Point(59 * VData.UNIT / 4,
						13 * VData.UNIT / 4), new Point(63 * VData.UNIT / 4,
						16 * VData.UNIT / 4), tempInfo, Color.argb(224, 192,
						32, 32), VData.UNIT / 2);
				tempInfo = "" + XBattle.hTeamV[IGame.pmSeq][1];
				drawText(canvas, new Point(59 * VData.UNIT / 4,
						16 * VData.UNIT / 4), new Point(63 * VData.UNIT / 4,
						19 * VData.UNIT / 4), tempInfo, Color.argb(224, 192,
						32, 32), VData.UNIT / 2);
				tempInfo = "" + XBattle.hTeamV[IGame.pmSeq][2];
				drawText(canvas, new Point(59 * VData.UNIT / 4,
						19 * VData.UNIT / 4), new Point(63 * VData.UNIT / 4,
						22 * VData.UNIT / 4), tempInfo, Color.argb(224, 192,
						32, 32), VData.UNIT / 2);
				tempInfo = "" + XBattle.hTeamV[IGame.pmSeq][4];
				drawText(canvas, new Point(59 * VData.UNIT / 4,
						22 * VData.UNIT / 4), new Point(63 * VData.UNIT / 4,
						25 * VData.UNIT / 4), tempInfo, Color.argb(224, 192,
						32, 32), VData.UNIT / 2);
				tempInfo = "" + XBattle.hTeamV[IGame.pmSeq][5];
				drawText(canvas, new Point(59 * VData.UNIT / 4,
						25 * VData.UNIT / 4), new Point(63 * VData.UNIT / 4,
						28 * VData.UNIT / 4), tempInfo, Color.argb(224, 192,
						32, 32), VData.UNIT / 2);
			}
		} catch (NullPointerException e) {
		}
	}

	public void drawFSfight(Canvas canvas, boolean noInfo) {
		try {
			Paint mPaint = new Paint();
			if (!noInfo) {
				mPaint.setColor(Color.argb(192, 247, 238, 214));
				canvas.drawRect(0, 0, 16 * VData.UNIT, 9 * VData.UNIT, mPaint);
			}
			mPaint.setColor(Color.argb(192, 128, 192, 255));
			for (int i = 0; i < 4; i++) {
				canvas.drawRect(49 * VData.UNIT / 4, (7 * i + 1) * VData.UNIT
								/ 4, 63 * VData.UNIT / 4, 7 * (i + 1) * VData.UNIT / 4,
						mPaint);
				String strName = XInfo.teamHMoveName[IGame.pmSeq][i];
				String strType = XInfo.teamHMoveType[IGame.pmSeq][i];
				String strCate = XInfo.teamHMoveCate[IGame.pmSeq][i];
				drawText(canvas, new Point(49 * VData.UNIT / 4, (7 * i + 1)
						* VData.UNIT / 4), new Point(59 * VData.UNIT / 4,
						(7 * i + 4) * VData.UNIT / 4), strName, Color.argb(224,
						32, 0, 32), VData.UNIT / 2);
				drawText(canvas, new Point(49 * VData.UNIT / 4, (7 * i + 4)
								* VData.UNIT / 4), new Point(53 * VData.UNIT / 4, 7
								* (i + 1) * VData.UNIT / 4), strType,
						Color.argb(224, 32, 0, 32), VData.UNIT / 2);
				drawText(canvas, new Point(53 * VData.UNIT / 4, (7 * i + 4)
								* VData.UNIT / 4), new Point(59 * VData.UNIT / 4, 7
								* (i + 1) * VData.UNIT / 4), strCate,
						Color.argb(224, 32, 0, 32), VData.UNIT / 2);
				drawText(canvas, new Point(59 * VData.UNIT / 4, (7 * i + 1)
								* VData.UNIT / 4), new Point(63 * VData.UNIT / 4, 7
								* (i + 1) * VData.UNIT / 4), ""
								+ VData.xBattle.hMissPP[IGame.pmSeq][i],
						Color.argb(224, 32, 0, 32), VData.UNIT / 2);
			}
			mPaint.setStyle(Style.STROKE);
			mPaint.setStrokeWidth(VData.UNIT / 10);
			canvas.drawRect(VData.UNIT / 4, 29 * VData.UNIT / 4,
					4 * VData.UNIT, 35 * VData.UNIT / 4, mPaint);
			mPaint.setStrokeWidth(VData.UNIT / 20);
			if (IGame.moveH >= 0) {
				mPaint.setColor(Color.argb(224, 192, 32, 32));
				canvas.drawRect(49 * VData.UNIT / 4 - 2, (7 * IGame.moveH + 1)
						* VData.UNIT / 4 + 2, 63 * VData.UNIT / 4 - 2, 7
						* (IGame.moveH + 1) * VData.UNIT / 4 - 2, mPaint);
				if (IGame.moveHClick-- == 0) {
					IGame.moveH = -1;
					IGame.moveHClick = 8;
				}
			}
		} catch (NullPointerException e) {
		}
	}

	public void drawFSteam(Canvas canvas, boolean noInfo) {
		try {
			Paint mPaint = new Paint();
			if (!noInfo) {
				mPaint.setColor(Color.argb(192, 247, 238, 214));
				canvas.drawRect(0, 0, 16 * VData.UNIT, 9 * VData.UNIT, mPaint);
			}
			for (int i = 0; i < XInfo.hTeamC; i++) {
				if (XBattle.hTeamHP[i] == 0) {
					mPaint.setColor(Color.argb(128, 224, 0, 32));
					canvas.drawRect(49 * VData.UNIT / 4, 20 + i * 92,
							63 * VData.UNIT / 4, 100 + i * 92, mPaint);
					drawText(canvas,
							new Point(49 * VData.UNIT / 4, 10 + i * 92),
							new Point(63 * VData.UNIT / 4, 100 + i * 92),
							XInfo.teamHName[i], Color.argb(224, 32, 224, 32),
							VData.UNIT / 2);
				} else if (VData.xBattle.hMissPP[i][0] == 0
						&& VData.xBattle.hMissPP[i][1] == 0
						&& VData.xBattle.hMissPP[i][2] == 0
						&& VData.xBattle.hMissPP[i][3] == 0) {
					mPaint.setColor(Color.argb(192, 247, 238, 214));
					canvas.drawRect(49 * VData.UNIT / 4, 20 + i * 92,
							63 * VData.UNIT / 4, 100 + i * 92, mPaint);
					drawText(canvas,
							new Point(49 * VData.UNIT / 4, 10 + i * 92),
							new Point(63 * VData.UNIT / 4, 100 + i * 92),
							XInfo.teamHName[i], Color.argb(224, 32, 224, 32),
							VData.UNIT / 2);
				} else {
					mPaint.setColor(Color.argb(192, 128, 192, 255));
					canvas.drawRect(49 * VData.UNIT / 4, 20 + i * 92,
							63 * VData.UNIT / 4, 100 + i * 92, mPaint);
					drawText(canvas,
							new Point(49 * VData.UNIT / 4, 10 + i * 92),
							new Point(63 * VData.UNIT / 4, 100 + i * 92),
							XInfo.teamHName[i], Color.argb(224, 32, 0, 32),
							VData.UNIT / 2);
				}
			}
			mPaint.setStyle(Style.STROKE);
			mPaint.setColor(Color.argb(224, 128, 192, 255));
			mPaint.setStrokeWidth(VData.UNIT / 10);
			canvas.drawRect(17 * VData.UNIT / 4, 29 * VData.UNIT / 4,
					8 * VData.UNIT, 35 * VData.UNIT / 4, mPaint);
			mPaint.setStrokeWidth(VData.UNIT / 20);
			mPaint.setColor(Color.argb(224, 192, 32, 32));
			canvas.drawRect(49 * VData.UNIT / 4 - 2, 20 + IGame.pmSeq * 92 + 2,
					63 * VData.UNIT / 4 - 2, 100 + IGame.pmSeq * 92 - 2, mPaint);
		} catch (NullPointerException e) {
		}
	}

	public void drawFSitem(Canvas canvas, boolean noInfo) {
		try {
			Paint mPaint = new Paint();
			if (!noInfo) {
				mPaint.setColor(Color.argb(192, 247, 238, 214));
				canvas.drawRect(0, 0, 16 * VData.UNIT, 9 * VData.UNIT, mPaint);
			}
			mPaint.setColor(Color.argb(192, 128, 192, 255));
			canvas.drawRect(49 * VData.UNIT / 4, VData.UNIT / 4,
					63 * VData.UNIT / 4, 7 * VData.UNIT / 4, mPaint);
			drawText(canvas, new Point(49 * VData.UNIT / 4, VData.UNIT / 4),
					new Point(63 * VData.UNIT / 4, 7 * VData.UNIT / 4), "体力回复",
					Color.argb(224, 192, 32, 32), VData.UNIT * 3 / 4);
			canvas.drawRect(49 * VData.UNIT / 4, 2 * VData.UNIT,
					63 * VData.UNIT / 4, 7 * VData.UNIT / 2, mPaint);
			drawText(canvas, new Point(49 * VData.UNIT / 4, 2 * VData.UNIT),
					new Point(63 * VData.UNIT / 4, 7 * VData.UNIT / 2), "状态回复",
					Color.argb(224, 192, 32, 32), VData.UNIT * 3 / 4);
			canvas.drawRect(49 * VData.UNIT / 4, 15 * VData.UNIT / 4,
					63 * VData.UNIT / 4, 21 * VData.UNIT / 4, mPaint);
			drawText(canvas,
					new Point(49 * VData.UNIT / 4, 15 * VData.UNIT / 4),
					new Point(63 * VData.UNIT / 4, 21 * VData.UNIT / 4), "精灵球",
					Color.argb(224, 32, 0, 32), VData.UNIT * 3 / 4);
			canvas.drawRect(49 * VData.UNIT / 4, 11 * VData.UNIT / 2,
					63 * VData.UNIT / 4, 7 * VData.UNIT, mPaint);
			drawText(canvas,
					new Point(49 * VData.UNIT / 4, 11 * VData.UNIT / 2),
					new Point(63 * VData.UNIT / 4, 7 * VData.UNIT), "战斗用品",
					Color.argb(224, 192, 32, 32), VData.UNIT * 3 / 4);
			mPaint.setStyle(Style.STROKE);
			mPaint.setStrokeWidth(VData.UNIT / 10);
			canvas.drawRect(33 * VData.UNIT / 4, 29 * VData.UNIT / 4,
					12 * VData.UNIT, 35 * VData.UNIT / 4, mPaint);
			mPaint.setStrokeWidth(VData.UNIT / 20);
			if (IGame.itemH >= 0) {
				mPaint.setColor(Color.argb(224, 192, 32, 32));
				canvas.drawRect(49 * VData.UNIT / 4 - 2, (7 * IGame.itemH + 1)
						* VData.UNIT / 4 + 2, 63 * VData.UNIT / 4 - 2, 7
						* (IGame.itemH + 1) * VData.UNIT / 4 - 2, mPaint);
				if (IGame.itemHClick-- == 0) {
					IGame.itemH = -1;
					IGame.itemHClick = 8;
				}
			}
		} catch (NullPointerException e) {
		}
	}

	private void drawText(Canvas canvas, Point point1, Point point2,
						  String str, int color, int size) {
		spPaint.setColor(color);
		spPaint.setTextSize(FPublic.getHeight(size));
		Rect rect = new Rect();
		spPaint.getTextBounds(str, 0, str.length(), rect);
		int missX = (point2.x - point1.x - rect.width()) / 2;
		int missY = (point2.y - point1.y + rect.height()) / 2;
		missX = missX > 0 ? missX : 0;
		missY = missY > 0 ? missY : 0;
		canvas.drawText(str, point1.x + missX, point1.y + missY, spPaint);
	}

	private void drawText(Canvas canvas, Point point, int width, String title,
						  int lColor, String str, int rColor, int size) {
		// ---- 绘制标题：最左端对齐 ----
		spPaint.setColor(lColor);
		spPaint.setTextSize(FPublic.getHeight(size));
		canvas.drawText(title, point.x, point.y, spPaint);

		// ---- 绘制内容：最右端对齐 ----
		Rect rect = new Rect();
		spPaint.getTextBounds(str, 0, str.length(), rect);
		spPaint.setColor(rColor);
		canvas.drawText(str, point.x + width - rect.width(), point.y, spPaint);
	}

	public void drawBattle(Canvas canvas, IGame _game) {
		String tempStr = IGame.chatStr[IGame.chatRole][IGame.chatLevel];
		Paint mPaint = new Paint();
		mPaint.setTextSize(FPublic.getHeight(38));
		Rect rect = new Rect();
		mPaint.getTextBounds(tempStr, 0, tempStr.length(), rect);
		int strOffset = rect.width() / 2;
		if (IGame.chatRole == 0) {
			mPaint.setColor(Color.argb(192, 128, 160, 192));
			int startX = _game._data.springPX + _game._data.springMX
					+ VData.UNIT;
			int startY = _game._data.springPY + _game._data.springMY
					+ VData.UNIT;
			canvas.drawRect(startX + (_game.npcssX - VData.xIndex) * VData.UNIT
					- strOffset, startY + (_game.npcssY - VData.yIndex - 1)
					* VData.UNIT, startX + (_game.npcssX - VData.xIndex + 1)
					* VData.UNIT + strOffset, startY
					+ (_game.npcssY - VData.yIndex) * VData.UNIT, mPaint);
			drawText(canvas, new Point(startX + (_game.npcssX - VData.xIndex)
							* VData.UNIT - strOffset, startY
							+ (_game.npcssY - VData.yIndex - 1) * VData.UNIT),
					new Point(startX + (_game.npcssX - VData.xIndex + 1)
							* VData.UNIT + strOffset, startY
							+ (_game.npcssY - VData.yIndex) * VData.UNIT),
					tempStr, Color.argb(224, 32, 0, 32), 38);
		} else {
			mPaint.setColor(Color.argb(192, 224, 160, 224));
			int startX = _game._data.springPX + _game._data.springMX
					+ VData.UNIT;
			int startY = _game._data.springPY + _game._data.springMY
					+ VData.UNIT;
			canvas.drawRect(startX + (VData.so.x - VData.xIndex) * VData.UNIT
					- strOffset, startY + (VData.so.y - VData.yIndex - 1)
					* VData.UNIT, startX + (VData.so.x - VData.xIndex + 1)
					* VData.UNIT + strOffset, startY
					+ (VData.so.y - VData.yIndex) * VData.UNIT, mPaint);
			drawText(canvas, new Point(startX + (VData.so.x - VData.xIndex)
							* VData.UNIT - strOffset, startY
							+ (VData.so.y - VData.yIndex - 1) * VData.UNIT), new Point(
							startX + (VData.so.x - VData.xIndex + 1) * VData.UNIT
									+ strOffset, startY + (VData.so.y - VData.yIndex)
							* VData.UNIT), tempStr, Color.argb(224, 32, 0, 32),
					38);
		}
	}

	public void drawGuide(Canvas canvas, IGame _game, String guide) {
		guide = "欢迎来到" + guide + "！";
		Paint mPaint = new Paint();
		mPaint.setTextSize(FPublic.getHeight(52));
		Rect rect = new Rect();
		mPaint.getTextBounds(guide, 0, guide.length(), rect);
		int strOffset = rect.width() / 2;
		mPaint.setColor(Color.argb(192, 224, 160, 224));
		int startX = VData.UNIT / 2 + strOffset;
		int startY = VData.UNIT / 2;
		canvas.drawRect(startX - strOffset, startY, startX + strOffset, startY,
				mPaint);
		drawText(canvas, new Point(startX - strOffset, startY), new Point(
						startX + strOffset, startY), guide, Color.argb(224, 32, 0, 32),
				52);
	}

	public void drawHint(Canvas canvas, IGame _game, String hint) {
		Paint mPaint = new Paint();
		mPaint.setTextSize(FPublic.getHeight(38));
		Rect rect = new Rect();
		mPaint.getTextBounds(hint, 0, hint.length(), rect);
		int strOffset = rect.width() / 2;
		mPaint.setColor(Color.argb(192, 128, 160, 192));
		int startX = _game.affairX * VData.UNIT + _game._data.springMX
				+ VData.UNIT;
		int startY = _game.affairY * VData.UNIT + _game._data.springMY
				+ VData.UNIT;
		canvas.drawRect(startX - VData.xIndex * VData.UNIT - strOffset, startY
						- (VData.yIndex + 1) * VData.UNIT, startX - (VData.xIndex - 1)
						* VData.UNIT + strOffset, startY - VData.yIndex * VData.UNIT,
				mPaint);
		drawText(canvas, new Point(startX - VData.xIndex * VData.UNIT
						- strOffset, startY - (VData.yIndex + 1) * VData.UNIT),
				new Point(startX - (VData.xIndex - 1) * VData.UNIT + strOffset,
						startY - VData.yIndex * VData.UNIT), hint, Color.argb(
						224, 32, 0, 32), 38);
	}

	public void drawMini(Canvas canvas, int unit) {
		try {
			Paint mPaint = new Paint();
			int tempY = VData.UNIT / 2;
			for (int yy = 0; yy < VData.allSIZE.y; yy++) {
				int tempX = FPublic.SCREEN.widthPixels - VData.UNIT / 2 - unit
						* VData.allSIZE.x;
				for (int xx = 0; xx < VData.allSIZE.x; xx++) {
					switch (logicMap[yy][xx]) {
						case VData.TAG_UNUSE:// 阻碍
							mPaint.setColor(Color.argb(192, 96, 192, 255));
							break;
						case VData.TAG_WALLE:// 木墙
							mPaint.setColor(Color.argb(192, 192, 192, 192));
							break;
						case VData.TAG_STONE:// 石墙
							mPaint.setColor(Color.argb(192, 255, 255, 255));
							break;
						case VData.TAG_EMPTY:// 空地
							mPaint.setColor(Color.argb(192, 160, 128, 224));
							break;
						case VData.TAG_FIGHT:// 对战
						case VData.TAG_AFFAIR:// 事件
							mPaint.setColor(Color.argb(192, 96, 255, 128));
							break;
						case VData.TAG_GRAZZ:// 遭遇
							mPaint.setColor(Color.argb(192, 224, 192, 224));
							break;
						case VData.TAG_MASTER:// 主人
						case VData.TAG_FELLOW:// 跟随
							mPaint.setColor(Color.argb(192, 255, 0, 0));
							break;
						case VData.TAG_TARGET:// 目的
							mPaint.setColor(Color.argb(192, 255, 255, 0));
							break;
						default:// 表示建筑物等
							mPaint.setColor(Color.argb(192, 128, 160, 192));
							break;
					}
					canvas.drawRect(tempX, tempY, tempX + unit, tempY + unit,
							mPaint);
					tempX += unit;
				}
				tempY += unit;
			}
		} catch (NullPointerException e) {
		}
	}

	public void drawView(Canvas canvas, IGame _game) {
		if (_game._data == null)
			return;
		if (VData._rent == null)
			return;
		if (imageMap0 == null || imageMap1 == null || imageMap2 == null)
			return;

		int startX, startY;

		// 地图第一层
		startX = _game._data.springMX;
		startY = _game._data.springMY;

		for (int yy = VData.yIndex; startY < FPublic.SCREEN.heightPixels
				&& yy <= VData.allSIZE.y + 1; yy++) {
			for (int xx = VData.xIndex; startX < FPublic.SCREEN.widthPixels
					&& xx <= VData.allSIZE.x + 1; xx++) {
				canvas.drawBitmap(imageMap0[yy][xx], startX, startY, null);
				startX += VData.UNIT;
			}
			startX = _game._data.springMX;
			startY += VData.UNIT;
		}

		// 指示标记
		if (_game.heroTouch != null && _game.heroTouch.size() != 0) {
			switch (_game.maskH) {
				case VData.UPPER:
					IGame.tthY += VData.MOVE;
					break;
				case VData.RIGHT:
					IGame.tthX -= VData.MOVE;
					break;
				case VData.DOWN:
					IGame.tthY -= VData.MOVE;
					break;
				case VData.LEFT:
					IGame.tthX += VData.MOVE;
					break;
			}

			int tempY = IGame.tthY + _game._data.springHY
					+ (VData.so.y - VData.yIndex) * VData.UNIT;
			int tempX = IGame.tthX + _game._data.springHX
					+ (VData.so.x - VData.xIndex) * VData.UNIT;
			Paint mPaint = new Paint();
			mPaint.setColor(Color.argb(127, 64, 127, 192));
			canvas.drawRect(tempX, tempY, tempX + VData.UNIT, tempY
					+ VData.UNIT, mPaint);
		} else {
			_game.maskH = VData.ETHER;
		}

		if (IGame.doorFlag) {
			if (fineRole) {
				if (VData.landMap.baseMap[VData.locY][VData.locX] == 9) {
					eliteBit[eliteNow] = eliteLic[eliteNow][0];
				} else {
					if (IGame.buildId == 900) {
						leaderLic = FPublic.CreateBitmap(mContext,
								NoSwitch.char4XX[0], 4 * VData.roleSize,
								4 * VData.roleSize);
					} else if (IGame.buildId == 901) {
						leaderLic = FPublic.CreateBitmap(mContext,
								NoSwitch.char4XX[1], 4 * VData.roleSize,
								4 * VData.roleSize);
					} else {
						leaderLic = FPublic
								.CreateBitmap(
										mContext,
										NoSwitch.char1XX[VData.landMap.baseMap[VData.locY][VData.locX] - 1],
										4 * VData.roleSize, 4 * VData.roleSize);
					}
					leaderBit = Bitmap.createBitmap(leaderLic, 0, 0,
							VData.roleSize, VData.roleSize);
				}
				fineRole = false;
			}
			if (turnRole) {
				if (VData.landMap.baseMap[VData.locY][VData.locX] == 9) {
					eliteBit[eliteNow] = eliteLic[eliteNow][directRole];
				} else {
					leaderBit = Bitmap.createBitmap(leaderLic, 0, directRole
							* VData.roleSize, VData.roleSize, VData.roleSize);
				}
				turnRole = false;
			}
		}

		// 地图第二层
		startX = _game._data.springMX;
		startY = _game._data.springMY;
		for (int yy = VData.yIndex; startY < FPublic.SCREEN.heightPixels
				&& yy <= VData.allSIZE.y + 1; yy++) {
			for (int xx = VData.xIndex; startX < FPublic.SCREEN.widthPixels
					&& xx <= VData.allSIZE.x + 1; xx++) {
				if (imageMap1[yy][xx] != null)
					canvas.drawBitmap(imageMap1[yy][xx], startX, startY, null);
				if (yy > 0 && xx > 0) {
					if (logicMap[yy - 1][xx - 1] == VData.TAG_FIGHT) {
						canvas.drawBitmap(monsterBit, startX, startY, null);
					}
					if (IGame.doorFlag) {
						if (logicMap[yy - 1][xx - 1] >= 600
								&& logicMap[yy - 1][xx - 1] <= 699) {
							if (yy - 1 > VData.so.y) {
								roleFlag = true;
							} else {
								if (roleFlag)
									roleFlag = false;
								if (logicMap[yy - 1][xx - 1] >= 610
										&& logicMap[yy - 1][xx - 1] <= 619) {
									eliteNow = logicMap[yy - 1][xx - 1] - 610;
									canvas.drawBitmap(
											eliteBit[eliteNow],
											startX
													- (VData.roleSize - VData.UNIT)
													/ 2,
											startY
													- (VData.roleSize - VData.UNIT),
											null);
								} else if (logicMap[yy - 1][xx - 1] >= 600
										&& logicMap[yy - 1][xx - 1] <= 609) {
									canvas.drawBitmap(
											leaderBit,
											startX
													- (VData.roleSize - VData.UNIT)
													/ 2,
											startY
													- (VData.roleSize - VData.UNIT),
											null);
								}
							}
							// 计算hero相对role的方向
							int dirY = (yy - 1) - VData.so.y;// 为正则role在hero下
							int dirX = (xx - 1) - VData.so.x;// 为正则role在hero右
							if ((dirY <= 3 && dirY >= -3)
									&& (dirX <= 3 && dirX >= -3)) {
								int tempDir = directRole;
								if (dirY == 0 || dirX == 0) {// 0下1左2右3上
									if (dirY > 0) {
										tempDir = 3;
									} else if (dirY < 0) {
										tempDir = 0;
									}
									if (dirX > 0) {
										tempDir = 1;
									} else if (dirX < 0) {
										tempDir = 2;
									}
								} else {
									int tempY = dirY;
									int tempX = dirX;
									if (dirY < 0) {
										tempY *= -1;
									}
									if (dirX < 0) {
										tempX *= -1;
									}
									if (tempY > tempX) {
										if (dirY > 0) {
											tempDir = 3;
										} else {
											tempDir = 0;
										}
									} else if (tempY < tempX) {
										if (dirX > 0) {
											tempDir = 1;
										} else {
											tempDir = 2;
										}
									}
								}
								if (tempDir != directRole) {
									directRole = tempDir;
									turnRole = true;
								}
							} else {
								if (directRole != 0) {
									directRole = 0;
									turnRole = true;
								}
							}
						}
					}
				}
				startX += VData.UNIT;
			}
			startX = _game._data.springMX;
			startY += VData.UNIT;
		}

		if (VData.so.y < VData.pe.y) {
			startX = _game._data.springHX;
			startY = _game._data.springHY;
			// 人物
			canvas.drawBitmap(VData._hero.getStatusBitmap(VData._hero.markS),
					startX + (VData.so.x - VData.xIndex) * VData.UNIT
							- (VData.roleSize - VData.UNIT) / 2, startY
							+ (VData.so.y - VData.yIndex) * VData.UNIT
							- (VData.roleSize - VData.UNIT), null);
			startX = _game._data.springPX + _game._data.springMX + VData.UNIT;
			startY = _game._data.springPY + _game._data.springMY + VData.UNIT;
			// 宠物
			canvas.drawBitmap(VData._fell.getStatusBitmap(VData._fell.markS),
					startX + (VData.pe.x - VData.xIndex) * VData.UNIT
							- (SFell.size - VData.UNIT) / 2, startY
							+ (VData.pe.y - VData.yIndex) * VData.UNIT
							- (SFell.size - VData.UNIT), null);
		} else {
			startX = _game._data.springPX + _game._data.springMX + VData.UNIT;
			startY = _game._data.springPY + _game._data.springMY + VData.UNIT;
			// 宠物
			canvas.drawBitmap(VData._fell.getStatusBitmap(VData._fell.markS),
					startX + (VData.pe.x - VData.xIndex) * VData.UNIT
							- (SFell.size - VData.UNIT) / 2, startY
							+ (VData.pe.y - VData.yIndex) * VData.UNIT
							- (SFell.size - VData.UNIT), null);
			startX = _game._data.springHX;
			startY = _game._data.springHY;
			// 人物
			canvas.drawBitmap(VData._hero.getStatusBitmap(VData._hero.markS),
					startX + (VData.so.x - VData.xIndex) * VData.UNIT
							- (VData.roleSize - VData.UNIT) / 2, startY
							+ (VData.so.y - VData.yIndex) * VData.UNIT
							- (VData.roleSize - VData.UNIT), null);
		}
		if (IGame.doorFlag) {
			if (roleFlag) {
				startX = _game._data.springMX;
				startY = _game._data.springMY;
				for (int yy = VData.yIndex; startY < FPublic.SCREEN.heightPixels
						&& yy <= VData.allSIZE.y + 1; yy++) {
					for (int xx = VData.xIndex; startX < FPublic.SCREEN.widthPixels
							&& xx <= VData.allSIZE.x + 1; xx++) {
						if (yy > 0 && xx > 0) {
							if (logicMap[yy - 1][xx - 1] >= 610
									&& logicMap[yy - 1][xx - 1] <= 619) {
								eliteNow = logicMap[yy - 1][xx - 1] - 610;
								canvas.drawBitmap(eliteBit[eliteNow], startX
												- (VData.roleSize - VData.UNIT) / 2,
										startY - (VData.roleSize - VData.UNIT),
										null);
							} else if (logicMap[yy - 1][xx - 1] >= 600
									&& logicMap[yy - 1][xx - 1] <= 609) {
								canvas.drawBitmap(leaderBit, startX
												- (VData.roleSize - VData.UNIT) / 2,
										startY - (VData.roleSize - VData.UNIT),
										null);
							}
						}
						startX += VData.UNIT;
					}
					startX = _game._data.springMX;
					startY += VData.UNIT;
				}
			}
		}

		// 地图第三层
		startX = _game._data.springMX;
		startY = _game._data.springMY;

		for (int yy = VData.yIndex; startY < FPublic.SCREEN.heightPixels
				&& yy <= VData.allSIZE.y + 1; yy++) {
			for (int xx = VData.xIndex; startX < FPublic.SCREEN.widthPixels
					&& xx <= VData.allSIZE.x + 1; xx++) {
				if (imageMap2[yy][xx] != null)
					canvas.drawBitmap(imageMap2[yy][xx], startX, startY, null);
				startX += VData.UNIT;
			}
			startX = _game._data.springMX;
			startY += VData.UNIT;
		}

		if (IGame.moodClick > 0) {
			IGame.moodClick--;
			startX = _game._data.springPX + _game._data.springMX + VData.UNIT;
			startY = _game._data.springPY + _game._data.springMY + VData.UNIT;
			// 心情
			canvas.drawBitmap(VData._fell.getMoodBitmap(), startX
					+ (VData.pe.x - VData.xIndex) * VData.UNIT, startY
					+ (VData.pe.y - VData.yIndex - 1) * VData.UNIT, null);
		}

		if (VData.so.y == VData.pe.y) {// 左右关系
			if (VData.pe.x < VData.so.x) {// P在S左边
				VData._fell.markS = VData.STAY_RIGHT;
			}
			if (VData.so.x < VData.pe.x) {// P在S右边
				VData._fell.markS = VData.STAY_LEFT;
			}
		}
		if (VData.so.x == VData.pe.x) {// 上下关系
			if (VData.pe.y < VData.so.y) {// P在S上边
				VData._fell.markS = VData.STAY_FACE;
			}
			if (VData.so.y < VData.pe.y) {// P在S下边
				VData._fell.markS = VData.STAY_BACK;
			}
		}

		if (VData._fell.markS < 4)
			VData._fell.markS += 10;

		if (VData._hero.markS < 4)
			VData._hero.markS += 10;
	}

	public void drawPath(Canvas canvas, APath mPaths) {
		if (mPaths != null)
			mPaths.draw(canvas);
	}
}