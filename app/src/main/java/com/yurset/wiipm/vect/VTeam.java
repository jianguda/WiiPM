package com.yurset.wiipm.vect;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.yurset.wiipm.base.FPublic;
import com.yurset.wiipm.base.NoSwitch;
import com.yurset.wiipm.base.XPokemon;
import com.yurset.wiipm.fight.XInfo;
import com.yurset.wiipm.logi.XPMReader;

import android.content.Context;
import android.graphics.Bitmap;

public class VTeam {
	// ========================== 成员变量 ===============================
	private Bitmap[] bitRole0 = null;
	private Bitmap[] bitRole1 = null;
	private Context mContext = null;
	private int[] imageId = null;
	public List<XPokemon> home = null;
	public int[] teamHash = { 0, 0, 0, 0, 0, 0 };

	// ==================================================================
	// ========================== 成员函数 ===============================
	public VTeam(Context context) {
		mContext = context;
		bitRole0 = new Bitmap[6];
		bitRole1 = new Bitmap[6];
		imageId = new int[6];

		InputStream inStream = null;
		try {
			inStream = mContext.getResources().getAssets().open("home.xml");
			home = XPMReader.readXML(inStream);
			inStream.close();
		} catch (IOException e) {
		}

		reFresh();
	}

	public void reFresh() {
		// release();
		for (int i = 0; i < XInfo.hTeamC; i++) {
			imageId[i] = NoSwitch.pmXXX[home.get(teamHash[i] - 1).getIndex()];
		}
		for (int i = 0; i < XInfo.hTeamC; i++) {
			Bitmap temp = FPublic
					.CreateBitmap(mContext, imageId[i], 2 * 88, 88);
			bitRole0[i] = Bitmap.createBitmap(temp, 0, 0, 88, 88);
			bitRole1[i] = Bitmap.createBitmap(temp, 88, 0, 88, 88);
		}
	}

	public Bitmap getBitmap0(int role) {
		return bitRole0[role];
	}

	public Bitmap getBitmap1(int role) {
		return bitRole1[role];
	}

	// public void release() {
	// for (int r = 0; bitRole0 != null && r < bitRole0.length; r++) {
	// if (bitRole0[r] == null)
	// continue;
	// if (bitRole0[r].isRecycled())
	// continue;
	// bitRole0[r].recycle();
	// }
	// bitRole0 = new Bitmap[6];
	// for (int r = 0; bitRole1 != null && r < bitRole1.length; r++) {
	// if (bitRole1[r] == null)
	// continue;
	// if (bitRole1[r].isRecycled())
	// continue;
	// bitRole1[r].recycle();
	// }
	// bitRole1 = new Bitmap[6];
	// }
	// ==================================================================
}