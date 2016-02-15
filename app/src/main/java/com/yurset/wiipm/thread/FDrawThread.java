package com.yurset.wiipm.thread;

import com.yurset.wiipm.base.IView;

import android.view.SurfaceHolder;
import android.graphics.Canvas;

public class FDrawThread extends Thread {
	// ========================== 成员函数 ===============================
	private IView contentView = null;
	private boolean runFlag = false;

	// ==================================================================
	// ========================== 成员函数 ===============================
	// 打开线程任务开关
	public void setRunFlag(boolean vbRunFlag) {
		runFlag = vbRunFlag;
	}

	// 根据界面的索引值，设置线程要显示刷新的界面View
	public void setContentView(IView v_contentView) {
		contentView = v_contentView;
	}

	@Override
	public void run() {
		SurfaceHolder holder = null;
		Canvas canvas = null;
		while (runFlag) {
			try {
				holder = contentView.getHolder();
				canvas = holder.lockCanvas();
				synchronized (holder) {
					contentView.DrawView(canvas);
				}
			} catch (NullPointerException e) {
			} finally {
				if (canvas != null)
					holder.unlockCanvasAndPost(canvas);
			}
		}
	}
	// =================================================================
}