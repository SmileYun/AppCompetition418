package com.idroid.eteach.util;

import java.lang.reflect.Method;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class SystemBarConfig {
	private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";
	private final int mStatusBarHeight;
	private static String sNavBarOverride;

	boolean mStatusBarAvailable = false;
	boolean mNavBarAvailable = false;

	static {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			try {
				Class c = Class.forName("android.os.SystemProperties");
				Method m = c.getDeclaredMethod("get", String.class);
				m.setAccessible(true);
				sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
			} catch (Throwable e) {
				sNavBarOverride = null;
			}
		}
	}

	public SystemBarConfig(Context context) {
		mStatusBarHeight = getInternalDimensionSize(context.getResources(), STATUS_BAR_HEIGHT_RES_NAME);
		init(context);
	}

	private int getInternalDimensionSize(Resources res, String key) {
		int result = 0;
		int resourceId = res.getIdentifier(key, "dimen", "android");
		if (resourceId > 0) {
			result = res.getDimensionPixelSize(resourceId);
		}
		return result;
	}

	public int getStatusBarHeight() {
		return mStatusBarHeight;
	}

	private void init(Context activity) {
		Window win;
		if (!(activity instanceof Activity))
			return;
		win = ((Activity) activity).getWindow();
		ViewGroup decorViewGroup = (ViewGroup) win.getDecorView();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// check theme attrs
			int[] attrs = { android.R.attr.windowTranslucentStatus, android.R.attr.windowTranslucentNavigation };
			TypedArray a = activity.obtainStyledAttributes(attrs);
			try {
				mStatusBarAvailable = a.getBoolean(0, false);
				mNavBarAvailable = a.getBoolean(1, false);
			} finally {
				a.recycle();
			}

			// check window flags
			WindowManager.LayoutParams winParams = win.getAttributes();
			int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
			if ((winParams.flags & bits) != 0) {
				mStatusBarAvailable = true;
			}
			bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
			if ((winParams.flags & bits) != 0) {
				mNavBarAvailable = true;
			}
		}

	}

	public boolean isStatusBarAvailable() {
		return mStatusBarAvailable;
	}

	public boolean isNavBarAvailable() {
		return mNavBarAvailable;
	}

}
