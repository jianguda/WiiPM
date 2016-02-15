package com.yurset.wiipm.erox;

import com.yurset.wiipm.R;
import com.yurset.wiipm.base.FPublic;
import com.yurset.wiipm.base.NiSwitch;
import com.yurset.wiipm.logi.VData;
import android.content.Context;
import android.graphics.Bitmap;

public class UMapRisoFf extends RMapBase {
	private int pointer10 = 0;
	private Bitmap sprite10 = null;
	private Bitmap[] spriteOdd10 = null;

	private Bitmap spriteDark = null;

	public UMapRisoFf(Context context, boolean iniFlag) {
		super(context);
		VData.relySize(19, 25);
		logicMap = FNodeBase.risoff;
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
		spriteOdd10 = new Bitmap[357];
		sprite10 = FPublic.CreateBitmap(context, R.drawable.roomff0,
				21 * VData.UNIT, 17 * VData.UNIT);
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 21; j++) {
				spriteOdd10[21 * i + j] = Bitmap.createBitmap(sprite10,
						VData.UNIT * j, VData.UNIT * i, VData.UNIT, VData.UNIT);
			}
		}
		spriteDark = FPublic.CreateBitmap(context, R.drawable.alldark,
				VData.UNIT, VData.UNIT);
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
					// imageMap0[yy + 1][xx + 1] = tempBit[11][3];
					imageMap0[yy + 1][xx + 1] = spriteDark;
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
						case 6:
						case 10:
						case 100:
						case 610:
						case 611:
						case 612:
						case 613:
							imageMap1[yy + 1][xx + 1] = spriteOdd10[pointer10];
							pointer10++;
							if (pointer10 == 357) {
								pointer10 = 0;
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