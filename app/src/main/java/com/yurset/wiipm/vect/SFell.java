package com.yurset.wiipm.vect;

import com.yurset.wiipm.R;
import com.yurset.wiipm.base.FPublic;
import com.yurset.wiipm.base.NoSwitch;
import com.yurset.wiipm.fight.XInfo;
import com.yurset.wiipm.logi.VData;
import com.yurset.wiipm.main.IGame;
import android.content.Context;
import android.graphics.Bitmap;

public class SFell {
	// ========================== 成员变量 ===============================
	public int markS = 0; // 状态

	private int currS = 0;
	private int limitT = 0;
	private int imageId = R.drawable.ac025;
	private final int imageId2 = R.drawable.mood;

	private Bitmap bitmap = null;
	private Bitmap bitMood = null;
	private Bitmap[] bitMove = null;

	private Context mContext = null;

	private boolean lisp = true;
	public static int size = 3 * VData.UNIT / 2;

	// ==================================================================
	// ========================== 成员函数 ===============================
	public SFell(Context context) {
		mContext = context;
		bitMove = new Bitmap[16];
		bitMood = FPublic.CreateBitmap(mContext, imageId2, VData.UNIT,
				VData.UNIT);
		markS = VData.STAY_FACE;
		reFresh();
	}

	public void reFresh() {
		imageId = NoSwitch.acXXX[XInfo.teamHIndex[IGame.pmSeq]];
		bitmap = FPublic.CreateBitmap(mContext, imageId, size * 4, size * 4);
		// release();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				bitMove[4 * i + j] = Bitmap.createBitmap(bitmap, j * size, i
						* size, size, size);
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
		if (limitT++ > 2) {
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

	public Bitmap getMoodBitmap() {
		return bitMood;
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