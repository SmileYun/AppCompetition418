package com.idroid.eteach.ui.base;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.idroid.eteach.R;
import com.idroid.eteach.fragment.base.DepthPageTransformer;
import com.idroid.eteach.widget.SlidingTabLayout;

public class ActivityFrame extends ActivityBase{
	/**
	 * �����Զ���Fragment
	 */
	protected ViewPager mViewPager;

	// public SwipeRefreshLayout mSwipeRefreshLayout;

	protected SlidingTabLayout mSlidingTabLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		
		setTabStripBackgroundColor(getResources().getColor(R.color.colorPrimary/*_R*/));
		setTabSelectedColor(getResources().getColor(R.color.colorAccent/*_R*/));
	}

	private void init() {
		View v = getLayoutInflater().inflate(R.layout.demo_content, null);

		mViewPager = (ViewPager) v.findViewById(R.id.vp);
		mViewPager.setPageTransformer(true, new DepthPageTransformer());
		
		mSlidingTabLayout = (SlidingTabLayout) v.findViewById(R.id.sliding_tab_layout);
		
		mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary/*_R*/));
		
		setContentView(v);
	}
	
	protected void setSlidingTabBackgroundColor(int color){
		mSlidingTabLayout.setBackgroundColor(color);
	}
	
	protected void setTabStripBackgroundColor(int color){
		mSlidingTabLayout.setTabStripBackground(color);
	}
	
	protected void setTabSelectedColor(int color){
		mSlidingTabLayout.setTabStripColor(color);
	}
	
}
