package com.idroid.eteach.util;

import java.lang.reflect.Method;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;

public class SystemBarConfig {
	private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";
	private final int mStatusBarHeight;

	static {
        // Android allows a system property to override the presence of the navigation bar.
        // Used by the emulator.
        // See https://github.com/android/platform_frameworks_base/blob/master/policy/src/com/android/internal/policy/impl/PhoneWindowManager.java#L1076
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                String sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
            	String  sNavBarOverride = null;
            }
        }
    }
	
	public SystemBarConfig(Context context) {
		mStatusBarHeight = getInternalDimensionSize(context.getResources(), STATUS_BAR_HEIGHT_RES_NAME);
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
}
