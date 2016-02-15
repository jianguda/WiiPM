package com.yurset.wiipm.erox;

import java.util.Random;

import com.yurset.wiipm.R;
import com.yurset.wiipm.base.FPublic;
import com.yurset.wiipm.logi.VData;
import android.content.Context;
import android.graphics.Bitmap;

public class RMapBase {
	// ========================== 成员变量 ===============================
	public int srcW = 4;
	public int srcH = 14;
	public boolean occFlag = false;

	public Bitmap bitmap = null;
	public Bitmap spriteo = null;
	public int[][] logicMap = null;
	// 草地、砖块等
	public Bitmap[][] imageMap0 = null;
	// 建筑的下层被遮挡
	public Bitmap[][] imageMap1 = null;
	// 建筑的上层能遮挡
	public Bitmap[][] imageMap2 = null;
	public Bitmap[][] tempBit = null;

	// ==================================================================
	// ========================== 成员变量 ===============================
	public RMapBase(Context context) {
		spriteo = FPublic.CreateBitmap(context, R.drawable.spriteo, VData.UNIT,
				VData.UNIT);
	}

	public void reFresh() {
	}

	public void fitFlower() {
		// _base.logicMap[y][x] > 2 && _base.logicMap[y][x] < 10 ||
		// _base.logicMap[y][x] > 199
		if (logicMap[VData.pe.y][VData.pe.x] > 2
				&& logicMap[VData.pe.y][VData.pe.x] < 9
				|| logicMap[VData.pe.y][VData.pe.x] > 199) {
			imageMap0[VData.pe.y + 1][VData.pe.x + 1] = tempBit[13][new Random()
					.nextInt(4)];
		}
	}

	// ==================================================================
}