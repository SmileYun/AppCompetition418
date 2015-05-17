package com.idroid.eteach.util;

import com.idroid.eteach.app.ActivityStackManager;

import android.content.Context;
import android.widget.Toast;

public class UiUtils {

	public static int px2dp(Context c, int value) {
		float density = c.getResources().getDisplayMetrics().density;
		return (int) (value / density + 0.5f);
	}

	public static int dp2px(Context c, int value) {
		float density = c.getResources().getDisplayMetrics().density;
		return (int) (value * density + 0.5f);
	}

	public static void toast(String msg) {
		try {
			toast(ActivityStackManager.getInstance().getTopActivity(), msg);
		} catch (Exception e) {
		}
	}

	public static void longToast(String msg) {
		try {
			longToast(ActivityStackManager.getInstance().getTopActivity(), msg);
		} catch (Exception e) {
		}
	}

	public static void longToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

	public static void toast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	
	public static int getScreenHeight(Context context){
		return context.getResources().getDisplayMetrics().heightPixels;
	}
	
	public static int getScreenWidth(Context context){
		return context.getResources().getDisplayMetrics().widthPixels;
	}
}
