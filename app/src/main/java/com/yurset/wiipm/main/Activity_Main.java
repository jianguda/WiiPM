package com.yurset.wiipm.main;

import com.yurset.wiipm.R;
import com.yurset.wiipm.base.IView;
import com.yurset.wiipm.base.FPublic;
import com.yurset.wiipm.base.UGifView;
import com.yurset.wiipm.thread.FDrawThread;
import com.yurset.wiipm.thread.FInfoThread;

import android.os.Bundle;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ActivityInfo;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class Activity_Main extends Activity {
	// ========================== 成员变量 ===============================
	private IGame _game = null;
	private UGifView gifViewB = null;
	private UGifView gifViewF = null;
	private RelativeLayout relative = null;
	private int m_indexView = 0;
	private IView m_contentView = null;
	private FDrawThread threadDraw = null;
	private FInfoThread threadInfo = null;

	// ==================================================================
	// ========================== 成员函数 ===============================
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gifViewB = new UGifView(this, 0);
		gifViewF = new UGifView(this, 1);
		relative = new RelativeLayout(this);
		relative.addView(gifViewB);
		relative.addView(gifViewF);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (_game != null) {
			_game.killMedia();
		}
		threadDraw.setRunFlag(false);
	}

	@Override
	protected void onResume() {
		super.onResume();

		FPublic.InitData(this);
		threadDraw = new FDrawThread();
		threadDraw.setRunFlag(true);
		FPublic.ThreadPool.execute(threadDraw);
		threadInfo = new FInfoThread();
		threadInfo.setContext(this);
		Thread info = new Thread(threadInfo);
		info.start();

		FPublic.ViewHandler = new FPublic.MHandler(this) {
			@Override
			public void handleMessage(Message v_msg) {
				try {
					m_indexView = v_msg.what;
					Activity_Main.this.handleView(m_indexView);
				} catch (NullPointerException e) {
				}
			}
		};
		FPublic.ViewHandler.sendEmptyMessage(FPublic.DEX_MAIN);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (_game != null) {
			_game.killMedia();
		}
	}

	private void handleView(int v_index) {
		if (v_index == FPublic.DEX_MAIN) {
			m_contentView = new IMain(this);
		} else if (v_index == FPublic.DEX_GAME) {
			m_contentView = new IDetail(this);
			// 分散一部分时间
			_game = new IGame(this, gifViewB, gifViewF);
		} else if (v_index == FPublic.DEX_LOAD) {
			while (IGame._info == null) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
			_game.playerF.pause();
			_game.playerN.start();
			IGame.dexValue[IDetail.iniFell] = 2;
			m_contentView = _game;
		} else if (v_index == FPublic.DEX_EXIT) {
			threadDraw.setRunFlag(false);
			this.finish();
		}
		if (m_contentView == null)
			return;

		threadDraw.setContentView(m_contentView);
		setContentView(m_contentView);
	}

	@Override
	public boolean onKeyDown(int v_keyCode, KeyEvent v_event) {
		if (v_keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder builder = new Builder(this);
			builder.setTitle("提示");
			builder.setMessage("是否确认退出游戏？");
			builder.setNegativeButton("取消", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.setPositiveButton("确认", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					System.exit(0);
				}
			});
			builder.create().show();
			return false;
		}
		return super.onKeyDown(v_keyCode, v_event);
	}
	// ==================================================================
}