package com.idroid.eteach.ui.base;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.idroid.eteach.R;
import com.idroid.eteach.fragment.base.DepthPageTransformer;
import com.idroid.eteach.widget.SlidingTabLayout;

public class ActivityFrame extends ActivityBase{
	/**
	 * 设置自定义Fragment
	 */
	protected ViewPager mViewPager;

	// public SwipeRefreshLayout mSwipeRefreshLayout;

	protected SlidingTabLayout mSlidingTabLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		View v = getLayoutInflater().inflate(R.layout.demo_content, null);

		mViewPager = (ViewPager) v.findViewById(R.id.vp);
		mViewPager.setPageTransformer(true, new DepthPageTransformer());
		
		mSlidingTabLayout = (SlidingTabLayout) v.findViewById(R.id.sliding_tab_layout);
		
		setContentView(v);
	}
	
}
