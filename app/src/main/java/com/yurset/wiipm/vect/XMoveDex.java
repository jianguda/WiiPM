package com.yurset.wiipm.vect;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.yurset.wiipm.base.XMove;
import com.yurset.wiipm.logi.XMoveReader;

import android.content.Context;

public class XMoveDex {
	private Context mContext = null;
	public List<XMove> moveDex = null;

	public XMoveDex(Context context) {
		mContext = context;
		reFresh();
	}

	public void reFresh() {
		InputStream inStream = null;
		try {
			inStream = mContext.getResources().getAssets().open("move.xml");
			moveDex = XMoveReader.readXML(inStream);
			inStream.close();
		} catch (IOException e) {
		}
	}
}