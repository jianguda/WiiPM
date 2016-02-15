package com.yurset.wiipm.base;

import java.util.Vector;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.MotionEvent;
import android.graphics.Bitmap;

public class IView extends SurfaceView implements SurfaceHolder.Callback {
	protected Context m_context = null;
	public static Vector<Bitmap> vectBMP = null;

	// ========================= 成员函数 ================================
	public IView(Context v_context) {
		super(v_context);
		m_context = v_context;
		vectBMP = new Vector<Bitmap>();
		this.getHolder().addCallback(this);
	}

	public IView(Context v_context, AttributeSet v_attr) {
		super(v_context, v_attr);
		m_context = v_context;
		this.getHolder().addCallback(this);
	}

	public IView(Context v_context, AttributeSet v_attr, int v_style) {
		super(v_context, v_attr, v_style);
		m_context = v_context;
		this.getHolder().addCallback(this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent v_event) {
		return HandleTouchEvent(v_event);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
							   int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		InitData();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		ReleaseData();
	}

	// ==================================================================
	// ========================== 虚函数 =================================
	public void DrawView(Canvas v_canvas) {
		Paint paint = new Paint();
		if (v_canvas != null) {
			v_canvas.drawColor(Color.BLACK);
			if (vectBMP != null && vectBMP.size() != 0) {
				for (int i = 0; i < vectBMP.size(); i++) {
					Bitmap bitmap = vectBMP.elementAt(i);
					paint.setColor(Color.RED);
					paint.setStyle(Style.STROKE);
					v_canvas.drawBitmap(bitmap,
							(i % (FPublic.SCREEN.heightPixels / 100)) * 100,
							(i / (FPublic.SCREEN.widthPixels / 100)) * 100,
							paint);
				}
			}
		}
	}

	protected boolean HandleTouchEvent(MotionEvent v_event) {
		return false;
	}

	protected void InitData() {
	}

	protected void ReleaseData() {
	}
	// ==================================================================
	// ==================================================================
}