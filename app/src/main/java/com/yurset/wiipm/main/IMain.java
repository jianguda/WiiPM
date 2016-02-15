package com.yurset.wiipm.main;

import com.yurset.wiipm.R;
import com.yurset.wiipm.base.IView;
import com.yurset.wiipm.base.FPublic;
import com.yurset.wiipm.logi.VData;

import android.content.Context;
import android.view.MotionEvent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;

public class IMain extends IView {
	// ========================= 成员变量 ================================
	private int boardX, boardY;
	private int selected = -1;
	private int selectClick = 2;
	private final int GAME = 0;
	private final int EXIT = 1;
	private final String[] MENU = { "GAME", "EXIT" };

	private Rect rect = null;
	private Typeface mFace0 = null;
	private Typeface mFace1 = null;
	private Context xcontext = null;
	private Point[] start_MenuItem = null;
	private Point[] end_MenuItem = null;

	// ==================================================================
	// ========================= 成员函数 ================================
	public IMain(Context context) {
		super(context);
		xcontext = context;
		mFace0 = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/lewime.ttf");
		mFace1 = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/moonlight.ttf");
	}

	@Override
	public void DrawView(Canvas vCanvas) {
		try {
			Paint mPaint = new Paint();
			mPaint.setTypeface(mFace0);
			mPaint.setColor(Color.DKGRAY);
			mPaint.setTextSize(100);
			vCanvas.drawBitmap(FPublic.CreateBitmap(xcontext, R.drawable.main,
					FPublic.SCREEN.widthPixels, FPublic.SCREEN.heightPixels),
					0, 0, mPaint);
			for (int i = 0; i < MENU.length; i++) {
				vCanvas.drawText(MENU[i], start_MenuItem[i].x,
						start_MenuItem[i].y, mPaint);
			}
			if (selected >= 0) {
				mPaint.setColor(Color.LTGRAY);
				vCanvas.drawText(MENU[selected], start_MenuItem[selected].x,
						start_MenuItem[selected].y, mPaint);
				if (selectClick-- == 0) {
					selected = -1;
				}
			}
			mPaint.setTypeface(mFace1);
			mPaint.setColor(Color.GRAY);
			mPaint.setTextSize(200);
			vCanvas.drawText("wiiPM", boardX, boardY, mPaint);

		} catch (NullPointerException e) {
		}
	}

	@Override
	protected boolean HandleTouchEvent(MotionEvent v_event) {
		if (v_event.getAction() == MotionEvent.ACTION_UP) {

			int t_x = (int) v_event.getX();
			int t_y = (int) v_event.getY() + rect.height();

			for (int i = 0; i < MENU.length; i++) {
				if ((t_x >= start_MenuItem[i].x && t_x <= end_MenuItem[i].x)
						&& (t_y >= start_MenuItem[i].y && t_y <= end_MenuItem[i].y)) {
					selected = i;
					HandleEvent(i);
					break;
				}
			}
			return false;
		}
		return true;
	}

	private void HandleEvent(int i) {
		switch (i) {
			case GAME:
				FPublic.ViewHandler.sendEmptyMessage(FPublic.DEX_GAME);
				break;
			case EXIT:
				FPublic.ViewHandler.sendEmptyMessage(FPublic.DEX_EXIT);
				break;
			default:
				break;
		}
		VData.CORNER = i;
	}

	@Override
	protected void InitData() {
		boardX = boardY = FPublic.SCREEN.heightPixels * 3 / 7;

		start_MenuItem = new Point[MENU.length];
		end_MenuItem = new Point[MENU.length];

		Paint mPaint = new Paint();
		mPaint.setColor(Color.WHITE);
		mPaint.setTextSize(100);

		int t_x = FPublic.SCREEN.widthPixels * 3 / 8;
		int t_y = boardY + 169;

		rect = new Rect();
		for (int i = 0; i < MENU.length; i++) {
			mPaint.getTextBounds(MENU[i], 0, MENU[i].length(), rect);
			start_MenuItem[i] = new Point(t_x, t_y);
			end_MenuItem[i] = new Point(t_x + rect.width(), t_y + rect.height());
			t_y += rect.height() + 60;
		}
	}

	@Override
	protected void ReleaseData() {
		start_MenuItem = null;
		end_MenuItem = null;
	}
	// ==================================================================
}