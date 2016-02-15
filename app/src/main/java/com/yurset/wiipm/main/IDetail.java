package com.yurset.wiipm.main;

import java.util.Random;

import com.yurset.wiipm.base.IView;
import com.yurset.wiipm.base.FPublic;
import com.yurset.wiipm.base.NoSwitch;
import com.yurset.wiipm.fight.XInfo;
import com.yurset.wiipm.logi.VData;

import android.content.Context;
import android.view.MotionEvent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Style;

public class IDetail extends IView {
	// ========================= 成员变量 ================================
	private Typeface mFace0 = null;
	private Typeface mFace1 = null;
	private Context xcontext = null;

	private int r = 224, g = 32, b = 32;
	private int rgbTarget = 0;
	private int guideClick = 64;
	private int detailStep = 0;
	private int fellNode = 0;
	private boolean selected = false;

	private Bitmap tempBit = null;
	private Bitmap fellBit[][] = new Bitmap[15][2];
	public static int iniFell = 0;
	private int fellDex[] = { 1, 4, 7, 152, 155, 158, 252, 255, 258, 387, 390,
			393, 495, 498, 501 };
	private String fellName[] = { "妙蛙种子", "小火龙", "杰尼龟", "菊草叶", "火球鼠", "小锯鳄",
			"木守宫", "火稚鸡", "水跃鱼", "草苗龟", "小火焰猴", "波加曼", "藤藤蛇", "暖暖猪", "水水獭" };
	private int infoLv = 0;
	private String info0[] = { "您是神奥地区的Cynthia？", "非常荣幸接待您！",
			"如您所知，关都、城都、丰缘、神奥、合众的优秀训练师都来了！",
			"这片新世界，邀请到了尽可能多的杰出训练师，包括其他冠军、开拓首脑、四大天王......",
			"根据联盟总部的特别规定，不允许携带PokeMon，也无法传送，但是可以选择初始精灵......",
			"对了，这里生活着超过600种PokeMon，相信您到时候也会大吃一惊的！",
			"初始精灵同样是来自关都、城都、丰缘、神奥、合众，您一定已经很熟悉了！", "现在就选择一只PokeMon成为您在新世界的伙伴吧！" };

	private String info1[] = { "您选择了Y系的X！", "还有一件事要提醒您，请您务必注意！",
			"这里同样引来了一些非法组织，他们或许在策划一些阴谋，务必小心！",
			"此外，请在熟悉这片新世界之后去往联盟报道，联盟期待您在这里大有作为！",
			"届时联盟将对您的实力做出评估，同样需要集齐道馆勋章作为实力的凭证！", "非常期待您的表现！",
			"再见，神奥地区的Cynthia！" };

	// ==================================================================
	// ========================= 成员函数 ================================
	public IDetail(Context context) {
		super(context);
		xcontext = context;
		mFace0 = Typeface.createFromAsset(context.getAssets(),
				"fonts/lewime.ttf");
		mFace1 = Typeface.createFromAsset(context.getAssets(),
				"fonts/jinghei.ttf");
		for (int i = 0; i < 15; i++) {
			tempBit = FPublic.CreateBitmap(xcontext,
					NoSwitch.pmXXX[fellDex[i]], 2 * 128, 128);
			fellBit[i][0] = Bitmap.createBitmap(tempBit, 0, 0, 128, 128);
			fellBit[i][1] = Bitmap.createBitmap(tempBit, 128, 0, 128, 128);
		}
	}

	@Override
	public void DrawView(Canvas vCanvas) {
		try {
			Paint mPaint = new Paint();
			switch (detailStep) {
				case 0:
					mPaint.setTypeface(mFace0);
					mPaint.setColor(Color.argb(192, 247, 238, 214));
					vCanvas.drawRect(0, 0, 16 * VData.UNIT, 9 * VData.UNIT, mPaint);
					mPaint.setTextSize(48);
					mPaint.setColor(Color.DKGRAY);
					if (info0[infoLv].length() > 22) {
						int lvStr = 0;
						String subStr = info0[infoLv];
						while (subStr.length() > 22) {
							vCanvas.drawText(subStr.substring(0, 22), VData.UNIT,
									(1 + lvStr++) * VData.UNIT, mPaint);
							subStr = subStr.substring(22);
						}
						vCanvas.drawText(subStr, VData.UNIT, (1 + lvStr++)
								* VData.UNIT, mPaint);
					} else {
						vCanvas.drawText(info0[infoLv], VData.UNIT, VData.UNIT,
								mPaint);
					}
					break;
				case 1:
					// 数值变化
					switch (rgbTarget) {
						case 0:
							r--;
							g++;
							if (g == 224) {
								rgbTarget = 1;
							}
							break;
						case 1:
							g--;
							b++;
							if (b == 224) {
								rgbTarget = 2;
							}
							break;
						case 2:
							b--;
							r++;
							if (r == 224) {
								rgbTarget = 0;
							}
							break;
					}
					mPaint.setColor(Color.argb(255, r, g, b));
					vCanvas.drawRect(0, 0, 16 * VData.UNIT, 9 * VData.UNIT, mPaint);
					mPaint.setColor(Color.argb(224, 224, 192, 32));
					vCanvas.drawRect(100, 75, 16 * VData.UNIT - 100,
							9 * VData.UNIT - 75, mPaint);
					mPaint.setTypeface(mFace1);
					mPaint.setTextSize(40);
					mPaint.setColor(Color.DKGRAY);
					for (int x = 0; x < 5; x++) {
						// 五个世代
						for (int y = 0; y < 3; y++) {
							// 御三家
							vCanvas.drawBitmap(fellBit[3 * x + y][0], 160
									+ (128 + 80) * x, 120 + (128 + 48) * y, null);
							vCanvas.drawText(fellName[3 * x + y], 160 + (128 + 80)
									* x, 120 + (128 + 48) * y + 40, mPaint);
						}
					}
					if (fellNode > 0) {
						mPaint.setStyle(Style.STROKE);
						mPaint.setStrokeWidth(8);
						mPaint.setColor(Color.argb(192, 224, 224, 32));
						int x = 0, y = 0;
						for (int i = 0; i < 15; i++) {
							if (fellNode == fellDex[i]) {
								x = i / 3;
								y = i - 3 * x;
							}
						}
						vCanvas.drawRect(160 + (128 + 80) * x - 10, 120
								+ (128 + 48) * y - 10, 160 + (128 + 80) * x + 128
								+ 10, 120 + (128 + 48) * y + 128 + 10, mPaint);
						if (guideClick-- == 0) {
							infoLv = 0;
							detailStep = 2;
						}
					}
					break;
				case 2:
					mPaint.setTypeface(mFace0);
					mPaint.setColor(Color.argb(192, 247, 238, 214));
					vCanvas.drawRect(0, 0, 16 * VData.UNIT, 9 * VData.UNIT, mPaint);
					mPaint.setTextSize(48);
					mPaint.setColor(Color.DKGRAY);
					if (infoLv == 0) {
						int x = 0, y = 0, z = 0;
						String strTemp = "";
						for (int i = 0; i < 15; i++) {
							if (fellNode == fellDex[i]) {
								x = i / 3;
								y = i - 3 * x;
								z = i;
								iniFell = fellDex[z] - 1;
							}
						}
						switch (y) {
							case 0:
								strTemp = info1[infoLv].replaceAll("Y", "草");
								break;
							case 1:
								strTemp = info1[infoLv].replaceAll("Y", "火");
								break;
							case 2:
								strTemp = info1[infoLv].replaceAll("Y", "水");
								break;
						}
						info1[infoLv] = strTemp.replaceAll("X", fellName[z]);
					}
					if (info1[infoLv].length() > 22) {
						int lvStr = 0;
						String subStr = info1[infoLv];
						while (subStr.length() > 22) {
							vCanvas.drawText(subStr.substring(0, 22), VData.UNIT,
									(1 + lvStr++) * VData.UNIT, mPaint);
							subStr = subStr.substring(22);
						}
						vCanvas.drawText(subStr, VData.UNIT, (1 + lvStr++)
								* VData.UNIT, mPaint);
					} else {
						vCanvas.drawText(info1[infoLv], VData.UNIT, VData.UNIT,
								mPaint);
					}
					break;
			}

		} catch (NullPointerException e) {
		}
	}

	@Override
	protected boolean HandleTouchEvent(MotionEvent v_event) {
		switch (detailStep) {
			case 0:
				switch (v_event.getAction() & MotionEvent.ACTION_MASK) {
					case MotionEvent.ACTION_DOWN:
						break;
					case MotionEvent.ACTION_UP:
						infoLv++;
						if (infoLv == info0.length) {
							infoLv = 0;
							detailStep = 1;
						}
						break;
				}
				break;
			case 1:
				if (v_event.getAction() == MotionEvent.ACTION_UP) {
					if (!selected) {
						int t_x = (int) v_event.getX();
						int t_y = (int) v_event.getY();
						for (int x = 0; x < 5; x++) {
							// 五个世代
							for (int y = 0; y < 3; y++) {
								// 御三家
								if (t_x >= 160 + (128 + 80) * x
										&& t_x <= 160 + (128 + 80) * x + 128) {
									if (t_y >= 120 + (128 + 48) * y
											&& t_y <= 120 + (128 + 48) * y + 128) {
										fellNode = fellDex[3 * x + y];
										y = 3;
										x = 5;
										selected = true;
										XInfo.hTeamC = 1;
										XInfo._team.teamHash[0] = fellNode;
										XInfo._team.reFresh();
										// 初始精灵的等级为10级，性格为25种性格中随机
										XInfo.teamHLevel[0] = 10;
										XInfo.teamHKidney[0] = new Random()
												.nextInt(25);
										XInfo.IniHInfo();
										for (int i = 0; i < XInfo.hTeamC; i++) {
											IGame.soundPoolMap
													.put(i,
															IGame.soundPool
																	.load(m_context,
																			NoSwitch.cryXXX[XInfo.teamHIndex[i]],
																			1));
										}
										VData.xBattle.refreshHTeam();
										VData._fell.reFresh();
									}
								}
							}
						}
					}
					return false;
				}
				break;
			case 2:
				switch (v_event.getAction() & MotionEvent.ACTION_MASK) {
					case MotionEvent.ACTION_DOWN:
						break;
					case MotionEvent.ACTION_UP:
						infoLv++;
						if (infoLv == info1.length) {
							infoLv = 0;
							FPublic.ViewHandler.sendEmptyMessage(FPublic.DEX_LOAD);
						}
						break;
				}
				break;
		}
		return true;
	}
	// ==================================================================
}