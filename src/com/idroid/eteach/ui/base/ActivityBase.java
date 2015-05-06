package com.idroid.eteach.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

import com.idroid.eteach.R;
import com.idroid.eteach.util.SystemBarConfig;
import com.idroid.eteach.widget.SlidingTabLayout;

public class ActivityBase extends ActionBarActivity {
	/**
     * The default system bar tint color value.
     */
    public static final int DEFAULT_TINT_COLOR = 0xf991a7ff;
    
	private ViewGroup mDecorViewGroup;
	
	private View mStatusBarTintView;
	
	private ViewGroup mRoot;
	
	private ViewGroup mContent;

	/**
	 * 设置自定义Fragment
	 */
	protected ViewPager mViewPager;

	protected SlidingTabLayout mSlidingTabLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		mDecorViewGroup = (ViewGroup) getWindow().getDecorView();
		mRoot = (ViewGroup) findViewById(R.id.root);
		mContent = (ViewGroup) findViewById(R.id.content);
		mContent.setBackgroundColor(Color.TRANSPARENT);
		View v = getLayoutInflater().inflate(R.layout.content, null);
		mViewPager = (ViewPager) v.findViewById(R.id.vp);
		mSlidingTabLayout = (SlidingTabLayout) v.findViewById(R.id.sliding_tab_layout);
		setupStatusBarView(this, mDecorViewGroup);
		setTranslucentStatus(true);
		setContentView(v);
	}

	public void setContentView(View v) {
		FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mContent.addView(v, p);
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
//		winParams.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
//         getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		
		if (on) {
			winParams.flags |= bits;
//			winParams.flags |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}
}
