package com.yurset.wiipm.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.content.Context;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

public class FPublic {
	// ========================= 全局变量 ================================
	public static final ExecutorService ThreadPool = Executors
			.newSingleThreadExecutor(); // 线程池

	public static MHandler ViewHandler = null;

	public static final int DEX_MAIN = 0;
	public static final int DEX_GAME = 1;
	public static final int DEX_LOAD = 2;
	public static final int DEX_EXIT = 6;

	public static final DisplayMetrics SCREEN = new DisplayMetrics();

	// ==================================================================
	// ========================= 全局函数 ================================
	// 初始化必须数据
	public static void InitData(Context context) {
		if (context == null)
			return;
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(SCREEN);
	}

	// 返回对应横分辨率下的像素值
	public static int agetWidth(int v_width) {
		return (int) (v_width * (float) FPublic.SCREEN.widthPixels / 1280);
	}

	// 返回对应竖分辨率下的像素值
	public static int getHeight(int v_height) {
		return (int) (v_height * (float) FPublic.SCREEN.heightPixels / 720);
	}

	// 获取指定大小的图片Bitmap
	public static Bitmap CreateBitmap(Context v_Context, int v_SrcID,
									  int v_DesWidth, int v_DesHeight) {
		try {
			Options t_option = new Options();
			t_option.inJustDecodeBounds = true;
			BitmapFactory.decodeResource(v_Context.getResources(), v_SrcID,
					t_option);

			int t_Width = t_option.outWidth;
			int t_Height = t_option.outHeight;

			boolean t_bWrap = false;
			if (v_DesWidth == 0 || v_DesHeight == 0)
				t_bWrap = true;

			v_DesWidth = (v_DesWidth == 0) ? t_Width : v_DesWidth;
			v_DesHeight = (v_DesHeight == 0) ? t_Height : v_DesHeight;

			float t_ScaleW = (float) (t_Width) / v_DesWidth;
			float t_ScaleH = (float) (t_Height) / v_DesHeight;
			float t_Scale = Math.max(t_ScaleW, t_ScaleH);

			t_option.inJustDecodeBounds = false;
			t_option.inSampleSize = (int) t_Scale;
			// ---- 弱引用 ----
			Bitmap bitmap = BitmapFactory.decodeResource(
					v_Context.getResources(), v_SrcID, t_option);
			if (t_bWrap == true)
				return bitmap;
			else
				return Bitmap.createScaledBitmap(bitmap, v_DesWidth,
						v_DesHeight, true);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ==================================================================
	// ========================= 自定义类 ================================
	public static class MHandler extends Handler {
		private Context context = null;

		/** 将Activity类对象指针入参，加入到弱引用，通过getContext()函数获取 */
		public MHandler(Context v_context) {
			context = v_context;
		}

		public Context getContext() {
			return context;
		}
	}
	// ==================================================================
}