package com.yurset.wiipm.vect;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import android.content.Context;
import com.yurset.wiipm.base.XPokemon;
import com.yurset.wiipm.logi.XPMReader;

public class XPMDex {
	private Context mContext = null;
	public List<XPokemon> pmDex = null;

	public XPMDex(Context context) {
		mContext = context;
		reFresh();
	}

	public void reFresh() {
		InputStream inStream = null;
		try {
			inStream = mContext.getResources().getAssets().open("pminfo.xml");
			pmDex = XPMReader.readXML(inStream);
			inStream.close();
		} catch (IOException e) {
		}
	}
}