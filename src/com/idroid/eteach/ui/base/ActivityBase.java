package com.idroid.eteach.ui.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

import com.idroid.eteach.R;
import com.idroid.eteach.app.ActivityStackManager;
import com.idroid.eteach.fragment.base.FragmentBase;
import com.idroid.eteach.util.SystemBarConfig;

public class ActivityBase extends ActionBarActivity {
	/**
	 * The default system bar tint color value.
	 */
	public static final int DEFAULT_TINT_COLOR = 0xfa91a7ff;

	private ViewGroup mDecorViewGroup;

	private View mStatusBarTintView;

	private ViewGroup mRoot;

	private ViewGroup mContent;

	/** Activity״̬ */
	public ActivityState activityState = ActivityState.DESTROY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityStackManager.getInstance().addActivity(this);
		super.setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		mDecorViewGroup = (ViewGroup) getWindow().getDecorView();
		mRoot = (ViewGroup) findViewById(R.id.root);
		mContent = (ViewGroup) findViewById(R.id.content);
		mContent.setBackgroundColor(Color.TRANSPARENT);
		
		setupStatusBarView(this, mDecorViewGroup);
		setTranslucentStatus(true);
	}

	public void setContentView(View v) {
		FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mContent.addView(v, p);
	}
	
	public void setContentView(int resId) {
		FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mContent.addView(getLayoutInflater().inflate(resId, null));
	}

	private void setupStatusBarView(Context context, ViewGroup decorViewGroup) {
		mStatusBarTintView = new View(context);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, new SystemBarConfig(context).getStatusBarHeight());
		params.gravity = Gravity.TOP;
		mStatusBarTintView.setLayoutParams(params);
		mStatusBarTintView.setBackgroundColor(DEFAULT_TINT_COLOR);
		mStatusBarTintView.setVisibility(View.VISIBLE);
		decorViewGroup.addView(mStatusBarTintView);
	}

	@TargetApi(19)
	private void setTranslucentStatus(boolean on) {
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		// winParams.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
		// getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

		if (on) {
			winParams.flags |= bits;
			// winParams.flags |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}

	public void changeFragment(int viewResId, FragmentBase fragment){
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(viewResId,fragment);
		ft.commit();
	}
	
	
	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		activityState = ActivityState.RESUME;
	}

	@Override
	protected void onPause() {
		super.onPause();
		activityState = ActivityState.PAUSE;
	}

	@Override
	protected void onStop() {
		super.onStop();
		activityState = ActivityState.STOP;
	}

	@Override
	protected void onDestroy() {
		activityState = ActivityState.DESTROY;
		super.onDestroy();
		ActivityStackManager.getInstance().finishActivity(this);
	}

	public void showActivity(Activity activity, Class<?> clazz) {
		Intent i = new Intent();
		i.setClass(activity, clazz);
		activity.startActivity(i);
	}

	public void showActivity(Activity activity, Intent i) {
		activity.startActivity(i);
	}

	public void showActivity(Activity activity, Class<?> clazz, Bundle extras) {
		Intent intent = new Intent();
		intent.putExtras(extras);
		intent.setClass(activity, clazz);
		activity.startActivity(intent);
	}

	public void skipActivity(Activity aty, Class<?> cls) {
		showActivity(aty, cls);
	}

	public void skipActivity(Activity aty, Intent it) {
		showActivity(aty, it);
	}

	public void skipActivity(Activity aty, Class<?> cls, Bundle extras) {
		showActivity(aty, cls, extras);
	}

	public void skipActivityAndFinishSelf(Activity aty, Class<?> cls) {
		showActivity(aty, cls);
		aty.finish();
	}

	public void skipActivityAndFinishSelf(Activity aty, Intent it) {
		showActivity(aty, it);
		aty.finish();
	}

	public void skipActivityAndFinishSelf(Activity aty, Class<?> cls, Bundle extras) {
		showActivity(aty, cls, extras);
		aty.finish();
	}

	/**
	 * ��ǰActivity״̬
	 */
	public static enum ActivityState {
		RESUME, PAUSE, STOP, DESTROY
	}
}
