package com.yurset.wiipm.base;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class APath {
	Path path = null;
	Paint paint = null;

	public APath(float x, float y) {
		path = new Path();

		paint = new Paint();
		paint.setStrokeWidth(16);
		paint.setColor(Color.GRAY);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeJoin(Paint.Join.ROUND);

		path.moveTo(x, y);
		path.lineTo(x, y);
	}

	public void move(float mx, float my) {
		path.lineTo(mx, my);
	}

	public void draw(Canvas canvas) {
		canvas.drawPath(path, paint);
	}
}