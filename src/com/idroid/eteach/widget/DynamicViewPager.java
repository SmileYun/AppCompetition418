package com.idroid.eteach.widget;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.idroid.eteach.adapter.internal.FragmentPagerAdapter;
import com.idroid.eteach.util.UiUtils;

public class DynamicViewPager extends FrameLayout implements OnPageChangeListener {

	private final int DEFAULT_SWITCH_TIME = 3000;
	private ViewPagerCustomDuration mViewPager;

	private RelativeLayout mIndicator;

	private OnPageChangeListener mPageListener;
	
	private Timer mDynamicTimer;
	
	private int mSelectedItem = 0;
	

	public void setOnPageListener(OnPageChangeListener mPageListener) {
		this.mPageListener = mPageListener;
	}

	private int dotCount = 0;

	public DynamicViewPager(Context context) {
		this(context, null);
	}

	public DynamicViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	private void initView(Context context) {
		mViewPager = new ViewPagerCustomDuration(context);
		ViewGroup.LayoutParams pagerParm = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				UiUtils.getScreenHeight(context) >> 2);
		mViewPager.setOnPageChangeListener(this);
		mViewPager.setScrollDurationFactor(2.5f);
		addView(mViewPager, pagerParm);

		mIndicator = new RelativeLayout(context);
		FrameLayout.LayoutParams indicateParm = new LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
				UiUtils.getScreenHeight(context) >> 4);
		indicateParm.height = UiUtils.getScreenHeight(context) >> 4;
		indicateParm.topMargin = (UiUtils.getScreenHeight(context) >> 2) - (indicateParm.height);
		mIndicator.setLayoutParams(indicateParm);
//		mIndicator.setBackgroundColor(Color.parseColor("#80565656"));
		addView(mIndicator);
	}

	public void setPagerAdapter(PagerAdapter adapter) {
		mViewPager.setAdapter(adapter);
		adapterSet();
	}

	public void setFragmentPagerAdapter(FragmentPagerAdapter adapter) {
		mViewPager.setAdapter(adapter);
		adapterSet();
	}

	private void adapterSet() {
		PagerAdapter adapter = mViewPager.getAdapter();
		dotCount = adapter.getCount();
		int leftMargin = UiUtils.getScreenWidth(getContext()) / 9 * 7 + 33;
		int topMargin = (UiUtils.getScreenHeight(getContext()) >> 4) / 4 * 3;

		for (int i = 0; i < dotCount; i++) {
			DotView v = new DotView(getContext());
			v.setColor(Color.CYAN);
			v.setPosition(leftMargin + i * (v.getRadius() + 33), topMargin - v.getRadius() / 2);
			mIndicator.addView(v);
		}
		
		dotViewSelected(mViewPager.getCurrentItem());
		startScrollViewPagerAutomic(DEFAULT_SWITCH_TIME);
	}

	public void setIndicatorBackground(int color){
		mIndicator.setBackgroundColor(color);
	}
	
	public void setIndicatorBackground(Drawable drawable){
		mIndicator.setBackground(drawable);
	}
	
	public void setIndicatorBackgroundResource(int resId){
		mIndicator.setBackgroundResource(resId);
	}
	
	public class DotView extends View {
		private int mColor;
		private Paint mPaint;
		private final int DEFAULTRADIUS = 10;
		private int mRadius = -1;
		private int x, y;

		public DotView(Context context) {
			super(context);
			mPaint = new Paint();
			mPaint.setAntiAlias(true);

		}

		public void setColor(int color) {
			this.mColor = color;
			mPaint.setColor(mColor);
		}

		public void setRadius(int radius) {
			this.mRadius = radius;
		}

		public int getRadius() {
			if (mRadius != -1)
				return mRadius;
			else
				return DEFAULTRADIUS;
		}

		public void setAlpha(int alpha) {
			mPaint.setAlpha(alpha);
		}

		//TODO : 将设置alpha通道换成图片或者颜色
		public void setSelected(boolean selected) {
			if (selected)
				setAlpha(1f);
			else
				setAlpha(0.4f);
		}

		public void setPosition(int x, int y) {
			this.x = x;
			this.y = y;
		}

		//TODO : 选中与为选中的点对Custom 制定drawable的支持
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvas.drawColor(Color.TRANSPARENT);

			if (mRadius == -1)
				canvas.drawCircle(x, y, DEFAULTRADIUS, mPaint);
			else
				canvas.drawCircle(x, y, mRadius, mPaint);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		if (mPageListener != null)
			mPageListener.onPageScrollStateChanged(arg0);
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		if (mPageListener != null)
			mPageListener.onPageScrolled(arg0, arg1, arg2);
	}

	@Override
	public void onPageSelected(int arg0) {
		dotViewSelected(arg0);
		if (mPageListener != null)
			mPageListener.onPageSelected(arg0);
	}

	public void dotViewSelected(int position) {
		DotView view = (DotView) mIndicator.getChildAt(position);
		resetIndicator();
		view.setSelected(true);
	}

	private void resetIndicator() {
		int count = mIndicator.getChildCount();
		for (int index = 0; index < count; index++) {
			DotView view = (DotView) mIndicator.getChildAt(index);
			view.setSelected(false);
		}
	}
	
	public void startScrollViewPagerAutomic(long period){
		if(mDynamicTimer == null)
			mDynamicTimer = new Timer();
		mDynamicTimer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				post(new Runnable() {
					
					@Override
					public void run() {
						mViewPager.setCurrentItem(mSelectedItem%mViewPager.getAdapter().getCount());
					}
				});
				mSelectedItem ++;
			}
		}, period, period);
	}
}