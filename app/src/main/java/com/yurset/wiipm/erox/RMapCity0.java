package com.yurset.wiipm.erox;

import com.yurset.wiipm.R;
import com.yurset.wiipm.base.FPublic;
import com.yurset.wiipm.base.NiSwitch;
import com.yurset.wiipm.logi.VData;
import android.content.Context;
import android.graphics.Bitmap;

public class RMapCity0 extends RMapBase {
	private int pointer10 = 0;
	private Bitmap sprite10 = null;
	private Bitmap[] spriteOdd10 = null;
	private int pointer11 = 0;
	private Bitmap sprite11 = null;
	private Bitmap[] spriteOdd11 = null;

	private int pointer20 = 0;
	private Bitmap sprite20 = null;
	private Bitmap[] spriteOdd20 = null;
	private int pointer21 = 0;
	private Bitmap sprite21 = null;
	private Bitmap[] spriteOdd21 = null;
	private int pointer22 = 0;
	private Bitmap sprite22 = null;
	private Bitmap[] spriteOdd22 = null;
	private int pointer23 = 0;
	private Bitmap sprite23 = null;
	private Bitmap[] spriteOdd23 = null;

	public RMapCity0(Context context, boolean iniFlag) {
		super(context);
		VData.relySize(32, 32);
		logicMap = FMakeBaseCity.city00;
		imageMap0 = new Bitmap[VData.allSIZE.y + 2][VData.allSIZE.x + 2];
		imageMap1 = new Bitmap[VData.allSIZE.y + 2][VData.allSIZE.x + 2];
		imageMap2 = new Bitmap[VData.allSIZE.y + 2][VData.allSIZE.x + 2];
		if (iniFlag) {
			occFlag = true;
		}
		tempBit = new Bitmap[srcH][srcW];
		for (int yy = 0; yy < srcH; yy++) {
			for (int xx = 0; xx < srcW; xx++) {
				bitmap = FPublic.CreateBitmap(context, NiSwitch.mapX[yy][xx],
						VData.UNIT, VData.UNIT);
				tempBit[yy][xx] = Bitmap.createBitmap(bitmap, 0, 0, VData.UNIT,
						VData.UNIT);
			}
		}
		spriteOdd10 = new Bitmap[25];
		sprite10 = FPublic.CreateBitmap(context, R.drawable.spritex000,
				5 * VData.UNIT, 5 * VData.UNIT);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				spriteOdd10[5 * i + j] = Bitmap.createBitmap(sprite10,
						VData.UNIT * j, VData.UNIT * i, VData.UNIT, VData.UNIT);
			}
		}
		spriteOdd11 = new Bitmap[20];
		sprite11 = FPublic.CreateBitmap(context, R.drawable.spritex001,
				4 * VData.UNIT, 5 * VData.UNIT);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				spriteOdd11[4 * i + j] = Bitmap.createBitmap(sprite11,
						VData.UNIT * j, VData.UNIT * i, VData.UNIT, VData.UNIT);
			}
		}
		spriteOdd20 = new Bitmap[2];
		sprite20 = FPublic.CreateBitmap(context, R.drawable.sprite0020,
				2 * VData.UNIT, VData.UNIT);
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 2; j++) {
				spriteOdd20[5 * i + j] = Bitmap.createBitmap(sprite20,
						VData.UNIT * j, VData.UNIT * i, VData.UNIT, VData.UNIT);
			}
		}
		spriteOdd21 = new Bitmap[2];
		sprite21 = FPublic.CreateBitmap(context, R.drawable.sprite0021,
				2 * VData.UNIT, VData.UNIT);
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 2; j++) {
				spriteOdd21[5 * i + j] = Bitmap.createBitmap(sprite21,
						VData.UNIT * j, VData.UNIT * i, VData.UNIT, VData.UNIT);
			}
		}
		spriteOdd22 = new Bitmap[2];
		sprite22 = FPublic.CreateBitmap(context, R.drawable.sprite0022,
				2 * VData.UNIT, VData.UNIT);
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 2; j++) {
				spriteOdd22[5 * i + j] = Bitmap.createBitmap(sprite22,
						VData.UNIT * j, VData.UNIT * i, VData.UNIT, VData.UNIT);
			}
		}
		spriteOdd23 = new Bitmap[2];
		sprite23 = FPublic.CreateBitmap(context, R.drawable.sprite0023,
				2 * VData.UNIT, VData.UNIT);
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 2; j++) {
				spriteOdd23[5 * i + j] = Bitmap.createBitmap(sprite23,
						VData.UNIT * j, VData.UNIT * i, VData.UNIT, VData.UNIT);
			}
		}
		reFresh();
	}

	public void reFresh0() {
		try {
			for (int xx = 0; xx <= VData.allSIZE.x + 1; xx++) {
				imageMap0[0][xx] = tempBit[12][3];
				imageMap0[VData.allSIZE.y + 1][xx] = tempBit[12][3];
			}
			for (int yy = 0; yy <= VData.allSIZE.y + 1; yy++) {
				imageMap0[yy][0] = tempBit[12][3];
				imageMap0[yy][VData.allSIZE.x + 1] = tempBit[12][3];
			}
			for (int yy = 0; yy < VData.allSIZE.y; yy++) {
				for (int xx = 0; xx < VData.allSIZE.x; xx++) {
					if (logicMap[yy][xx] == VData.TAG_TARGET) {
						imageMap0[yy + 1][xx + 1] = tempBit[12][1];
					} else {
						imageMap0[yy + 1][xx + 1] = tempBit[11][3];
					}
				}
			}
			// fitFlower();
		} catch (Exception e) {
		}
	}

	public void reFresh1() {
		try {
			for (int yy = 0; yy < VData.allSIZE.y; yy++) {
				for (int xx = 0; xx < VData.allSIZE.x; xx++) {
					switch (logicMap[yy][xx]) {
						case 10:
						case 900:
							imageMap1[yy + 1][xx + 1] = spriteOdd10[pointer10];
							pointer10++;
							if (pointer10 == 25) {
								pointer10 = 0;
							}
							break;
						case 11:
						case 901:
							imageMap1[yy + 1][xx + 1] = spriteOdd11[pointer11];
							pointer11++;
							if (pointer11 == 20) {
								pointer11 = 0;
							}
							break;
						case 210:
							pointer10++;
							break;
						case 211:
							pointer11++;
							break;
						case 20:
							imageMap1[yy + 1][xx + 1] = spriteOdd20[pointer20];
							pointer20++;
							if (pointer20 == 2) {
								pointer20 = 0;
							}
							break;
						case 21:
							imageMap1[yy + 1][xx + 1] = spriteOdd21[pointer21];
							pointer21++;
							if (pointer21 == 2) {
								pointer21 = 0;
							}
							break;
						case 22:
							imageMap1[yy + 1][xx + 1] = spriteOdd22[pointer22];
							pointer22++;
							if (pointer22 == 2) {
								pointer22 = 0;
							}
							break;
					}
				}
			}
		} catch (Exception e) {
		}
	}

	public void reFresh2() {
		try {
			for (int yy = 0; yy < VData.allSIZE.y; yy++) {
				for (int xx = 0; xx < VData.allSIZE.x; xx++) {
					switch (logicMap[yy][xx]) {
						case VData.TAG_MASTER:// 主人
							if (occFlag) {
								if (yy >= VData.dosY / 2) {
									if (yy >= VData.allMAX.y + 1 - VData.dosY / 2) {
										VData.yIndex = VData.allMAX.y + 1
												- VData.dosY;
									} else {
										VData.yIndex = yy - VData.dosY / 2;
									}
								} else {
									VData.yIndex = 0;
								}
								if (xx >= VData.dosX / 2) {
									if (xx >= VData.allMAX.x + 1 - VData.dosX / 2) {
										VData.xIndex = VData.allMAX.x + 1
												- VData.dosX;
									} else {
										VData.xIndex = xx - VData.dosX / 2;
									}
								} else {
									VData.xIndex = 0;
								}
								VData.yOffset = 0;
								VData.xOffset = 0;
								occFlag = false;
							}
							break;
						case 210:
							imageMap2[yy + 1][xx + 1] = spriteOdd10[pointer10];
							pointer10++;
							if (pointer10 == 5) {
								pointer10 = 0;
							}
							break;
						case 211:
							imageMap2[yy + 1][xx + 1] = spriteOdd11[pointer11];
							pointer11++;
							if (pointer11 == 4) {
								pointer11 = 0;
							}
							break;
						case 223:
							imageMap2[yy + 1][xx + 1] = spriteOdd23[pointer23];
							pointer23++;
							if (pointer23 == 2) {
								pointer23 = 0;
							}
							break;
					}
				}
			}
		} catch (Exception e) {
		}
	}

	public void reFresh() {
		reFresh0();
		reFresh1();
		reFresh2();
	}
}