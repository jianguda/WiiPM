package com.yurset.wiipm.thread;

import com.yurset.wiipm.fight.XInfo;
import com.yurset.wiipm.main.IGame;

import android.content.Context;

public class FInfoThread extends Thread {

	private Context m_context = null;

	public void setContext(Context context) {
		m_context = context;
	}

	@Override
	public void run() {
		IGame._info = new XInfo(m_context);
	}
}