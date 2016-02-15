package com.yurset.wiipm.vect;

import com.yurset.wiipm.R;
import com.yurset.wiipm.base.FPublic;
import com.yurset.wiipm.logi.VData;
import android.content.Context;
import android.graphics.Bitmap;

public class SHero {
	// ========================== 成员变量 ===============================

	public int markS = 0; // 状态

	private int currS = 0;
	private int limitT = 0;
	private final int imageId = R.drawable.cynthia;

	private Bitmap bitmap = null;
	private Bitmap[] bitMove = null;

	private boolean lisp = true;

	// ==================================================================
	// ========================== 成员函数 ===============================
	public SHero(Context context) {
		bitMove = new Bitmap[16];
		bitmap = FPublic.CreateBitmap(context, imageId, VData.roleSize * 4,
				VData.roleSize * 4);
		markS = VData.STAY_FACE;
		reFresh();
	}

	public void reFresh() {
		// release();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				bitMove[4 * i + j] = Bitmap.createBitmap(bitmap, j
								* VData.roleSize, i * VData.roleSize, VData.roleSize,
						VData.roleSize);
			}
		}
	}

	public Bitmap getStatusBitmap(int status) {
		Bitmap bitStatus = null;
		switch (status) {
			case VData.MOVE_FACE:
				bitStatus = bitMove[currS + 1];
				break;
			case VData.MOVE_LEFT:
				bitStatus = bitMove[currS + 5];
				break;
			case VData.MOVE_RIGHT:
				bitStatus = bitMove[currS + 9];
				break;
			case VData.MOVE_BACK:
				bitStatus = bitMove[currS + 13];
				break;
			case VData.STAY_FACE:
				bitStatus = bitMove[0];
				break;
			case VData.STAY_LEFT:
				bitStatus = bitMove[4];
				break;
			case VData.STAY_RIGHT:
				bitStatus = bitMove[8];
				break;
			case VData.STAY_BACK:
				bitStatus = bitMove[12];
				break;
		}
		if (limitT++ > 3) {
			limitT = 0;
			switch (currS) {
				case 0:
					currS++;
					lisp = true;
					break;
				case 1:
					if (lisp) {
						currS++;
					} else {
						currS--;
					}
					break;
				case 2:
					currS--;
					lisp = false;
					break;
			}
		}
		return bitStatus;
	}

	// public void release() {
	// for (int r = 0; bitMove != null && r < bitMove.length; r++) {
	// if (bitMove[r] == null)
	// continue;
	// if (bitMove[r].isRecycled())
	// continue;
	// bitMove[r].recycle();
	// }
	// bitMove = new Bitmap[16];
	// }
	// ==================================================================
}