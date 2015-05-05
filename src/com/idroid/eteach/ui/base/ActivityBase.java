package com.idroid.eteach.ui.base;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

import com.idroid.eteach.R;
import com.idroid.eteach.widget.SlidingTabLayout;

public class ActivityBase extends ActionBarActivity {

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
		mRoot = (ViewGroup) findViewById(R.id.root);
		mContent = (ViewGroup) findViewById(R.id.content);
		View v = getLayoutInflater().inflate(R.layout.content, null);
		mViewPager = (ViewPager) v.findViewById(R.id.vp);
		mSlidingTabLayout = (SlidingTabLayout) v.findViewById(R.id.sliding_tab_layout);
		setContentView(v);
	}

	public void setContentView(View v) {
		FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mContent.addView(v, p);
	}

}
