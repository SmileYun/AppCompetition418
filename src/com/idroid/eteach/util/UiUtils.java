package com.idroid.eteach.util;

import android.content.Context;

public class UiUtils {

	public static int px2dp(Context c, int value){
		float density = c.getResources().getDisplayMetrics().density;
		return (int) (value / density + 0.5f);
	}
	
	public static int dp2px(Context c, int value){
		float density = c.getResources().getDisplayMetrics().density;
		return (int) (value * density + 0.5f);
	}
}
