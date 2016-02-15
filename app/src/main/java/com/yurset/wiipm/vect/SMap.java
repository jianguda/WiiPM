package com.yurset.wiipm.vect;

import com.yurset.wiipm.base.FPublic;
import com.yurset.wiipm.base.NiSwitch;
import com.yurset.wiipm.erox.RMapBase;
import com.yurset.wiipm.logi.SDapter;
import com.yurset.wiipm.logi.VData;

import android.content.Context;
import android.graphics.Bitmap;

public class SMap extends RMapBase {
	public SMap(Context context) {
		super(context);
		VData.relySize(32, 48);
		logicMap = new SDapter(VData.allSIZE.y, VData.allSIZE.x, 64)
				.getMatrix();
		imageMap0 = new Bitmap[VData.allSIZE.y + 2][VData.allSIZE.x + 2];
		imageMap1 = new Bitmap[VData.allSIZE.y + 2][VData.allSIZE.x + 2];
		imageMap2 = new Bitmap[VData.allSIZE.y + 2][VData.allSIZE.x + 2];
		occFlag = true;
		tempBit = new Bitmap[srcH][srcW];
		for (int yy = 0; yy < srcH; yy++) {
			for (int xx = 0; xx < srcW; xx++) {
				bitmap = FPublic.CreateBitmap(context, NiSwitch.mapX[yy][xx],
						VData.UNIT, VData.UNIT);
				tempBit[yy][xx] = Bitmap.createBitmap(bitmap, 0, 0, VData.UNIT,
						VData.UNIT);
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
					imageMap0[yy + 1][xx + 1] = tempBit[11][3];
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
						case VData.TAG_UNUSE:// 阻碍
							// 最外围是石墙因而可以去掉两种边界，并且加减1不会越界
							imageMap0[yy + 1][xx + 1] = bitEvolute(yy, xx);// tempBit[0][3];
							break;
						case VData.TAG_WALLE:// 木墙
							imageMap0[yy + 1][xx + 1] = tempBit[12][2];
							break;
						case VData.TAG_STONE:// 石墙
							imageMap0[yy + 1][xx + 1] = tempBit[12][3];
							break;
						case VData.TAG_TARGET:// 目的
							imageMap0[yy + 1][xx + 1] = tempBit[12][1];
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
										VData.yIndex = yy - VData.dosY / 2;// 偏下
									}
								} else {
									VData.yIndex = 0;
								}
								if (xx >= VData.dosX / 2) {
									if (xx >= VData.allMAX.x + 1 - VData.dosX / 2) {
										VData.xIndex = VData.allMAX.x + 1
												- VData.dosX;
									} else {
										VData.xIndex = xx - VData.dosX / 2;// 偏右
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

	private Bitmap bitEvolute(int yy, int xx) {
		Bitmap bitValue = null;
		if (logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] == VData.TAG_UNUSE) {
			bitValue = tempBit[0][0];
		} else if (logicMap[yy - 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE) {
			bitValue = tempBit[0][1];
		} else if (logicMap[yy - 1][xx] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] == VData.TAG_UNUSE) {
			bitValue = tempBit[0][2];
		} else if (logicMap[yy - 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] != VData.TAG_UNUSE) {
			bitValue = tempBit[0][3];
		} else if (logicMap[yy - 1][xx] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] != VData.TAG_UNUSE) {
			bitValue = tempBit[1][0];
		} else if (logicMap[yy - 1][xx] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] != VData.TAG_UNUSE) {
			bitValue = tempBit[1][1];
		} else if (logicMap[yy - 1][xx] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE) {
			bitValue = tempBit[1][2];
		} else if (logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] != VData.TAG_UNUSE) {
			bitValue = tempBit[1][3];
		} else if (logicMap[yy - 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] == VData.TAG_UNUSE) {
			bitValue = tempBit[2][0];
		} else if (logicMap[yy - 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] != VData.TAG_UNUSE) {
			bitValue = tempBit[2][1];
		} else if (logicMap[yy - 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] == VData.TAG_UNUSE) {
			bitValue = tempBit[2][2];
		} else if (logicMap[yy - 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] != VData.TAG_UNUSE) {
			bitValue = tempBit[2][3];
		} else if (logicMap[yy - 1][xx] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] == VData.TAG_UNUSE) {
			bitValue = tempBit[3][0];
		} else if (logicMap[yy - 1][xx] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE) {
			bitValue = tempBit[3][1];
		} else if (logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] != VData.TAG_UNUSE) {
			bitValue = tempBit[3][2];
		} else if (logicMap[yy - 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] != VData.TAG_UNUSE) {
			bitValue = tempBit[3][3];
		} else if (logicMap[yy - 1][xx] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] != VData.TAG_UNUSE) {
			bitValue = tempBit[4][0];
		} else if (logicMap[yy - 1][xx] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE) {
			bitValue = tempBit[4][1];
		} else if (logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] != VData.TAG_UNUSE) {
			bitValue = tempBit[4][2];
		} else if (logicMap[yy - 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] != VData.TAG_UNUSE) {
			bitValue = tempBit[4][3];
		} else if (logicMap[yy - 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] == VData.TAG_UNUSE) {
			bitValue = tempBit[5][0];
		} else if (logicMap[yy - 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] == VData.TAG_UNUSE) {
			bitValue = tempBit[5][1];
		} else if (logicMap[yy - 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] == VData.TAG_UNUSE) {
			bitValue = tempBit[5][2];
		} else if (logicMap[yy - 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] != VData.TAG_UNUSE) {
			bitValue = tempBit[5][3];
		} else if (logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE) {
			bitValue = tempBit[6][0];
		} else if (logicMap[yy - 1][xx] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] != VData.TAG_UNUSE) {
			bitValue = tempBit[6][1];
		} else if (logicMap[yy - 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] != VData.TAG_UNUSE) {
			bitValue = tempBit[6][2];
		} else if (logicMap[yy - 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] == VData.TAG_UNUSE) {
			bitValue = tempBit[6][3];
		} else if (logicMap[yy - 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] == VData.TAG_UNUSE) {
			bitValue = tempBit[7][0];
		} else if (logicMap[yy - 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] != VData.TAG_UNUSE) {
			bitValue = tempBit[7][1];
		} else if (logicMap[yy - 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] != VData.TAG_UNUSE) {
			bitValue = tempBit[7][2];
		} else if (logicMap[yy - 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] != VData.TAG_UNUSE) {
			bitValue = tempBit[7][3];
		} else if (logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] == VData.TAG_UNUSE) {
			bitValue = tempBit[8][0];
		} else if (logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] != VData.TAG_UNUSE) {
			bitValue = tempBit[8][1];
		} else if (logicMap[yy - 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE) {
			bitValue = tempBit[8][2];
		} else if (logicMap[yy - 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE) {
			bitValue = tempBit[8][3];
		} else if (logicMap[yy - 1][xx] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] == VData.TAG_UNUSE) {
			bitValue = tempBit[9][0];
		} else if (logicMap[yy - 1][xx] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] != VData.TAG_UNUSE) {
			bitValue = tempBit[9][1];
		} else if (logicMap[yy - 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] != VData.TAG_UNUSE) {
			bitValue = tempBit[9][2];
		} else if (logicMap[yy - 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] != VData.TAG_UNUSE) {
			bitValue = tempBit[9][3];
		} else if (logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] != VData.TAG_UNUSE) {
			bitValue = tempBit[10][0];
		} else if (logicMap[yy - 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE) {
			bitValue = tempBit[10][1];
		} else if (logicMap[yy - 1][xx] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] != VData.TAG_UNUSE) {
			bitValue = tempBit[10][2];
		} else if (logicMap[yy - 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] != VData.TAG_UNUSE) {
			bitValue = tempBit[10][3];
		} else if (logicMap[yy - 1][xx] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] != VData.TAG_UNUSE) {
			bitValue = tempBit[11][0];
		} else if (logicMap[yy - 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] != VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] != VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] != VData.TAG_UNUSE) {
			bitValue = tempBit[11][1];
		} else if (logicMap[yy - 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy - 1][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy][xx + 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx - 1] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx] == VData.TAG_UNUSE
				&& logicMap[yy + 1][xx + 1] == VData.TAG_UNUSE) {
			bitValue = tempBit[11][2];
		}
		return bitValue;
	}

	// public void release() {
	// for (int r = 0; imageMap0 != null && r < imageMap0.length; r++) {
	// for (int c = 0; imageMap0[r] != null && c < imageMap0[r].length; c++) {
	// if (imageMap0[r][c] == null)
	// continue;
	// if (imageMap0[r][c].isRecycled())
	// continue;
	// imageMap0[r][c].recycle();
	// }
	// }
	// imageMap0 = new Bitmap[VData.allSIZE.y + 2][VData.allSIZE.x + 2];
	// }
	// ==================================================================
}