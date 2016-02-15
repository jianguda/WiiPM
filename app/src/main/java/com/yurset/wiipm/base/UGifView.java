package com.yurset.wiipm.base;

import java.io.InputStream;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.SystemClock;
import android.widget.ImageView;
import com.yurset.wiipm.R;

public class UGifView extends ImageView {

	private Movie mMovie;
	private int duration;
	private long mMovieStart;
	private int width = 0;
	private int height = 0;
	private int resId, locX, locY;

	public UGifView(Context context, int locBF) {
		super(context);
		if (locBF == 0) {
			this.locY = 70 * 5;
			this.locX = 34 * 5;
		} else if (locBF == 1) {
			this.locY = 45 * 5;
			this.locX = 94 * 5;
		}
		resId = R.drawable.xxegg;
		InputStream is = getResources().openRawResource(resId);
		mMovie = Movie.decodeStream(is);
		duration = mMovie.duration();
		if (mMovie != null) {
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			bitmap.recycle();
		}
	}

	public int getResource() {
		return resId;
	}

	public void setResource(int res) {
		this.resId = res;
		InputStream is = getResources().openRawResource(res);
		mMovie = Movie.decodeStream(is);
		duration = mMovie.duration();
		if (mMovie != null) {
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			bitmap.recycle();
		}
		width = mMovie.width();
		height = mMovie.height();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		long now = SystemClock.uptimeMillis();
		if (now - mMovieStart >= duration) {
			mMovieStart = now;
		}
		int relTime = (int) (now - mMovieStart);
		canvas.scale(1.6f, 1.6f);
		mMovie.setTime(relTime);
		mMovie.draw(canvas, locX - width / 2, locY - height);
		canvas.scale(0.625f, 0.625f);
		invalidate();
	}
}