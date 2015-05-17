package com.idroid.eteach.widget;

import java.lang.reflect.Field;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * @author Yun
 */
public class ViewPagerCustomDuration extends ViewPager {
	public ViewPagerCustomDuration(Context context) {
		super(context);
		postInitViewPager();
	}

	public ViewPagerCustomDuration(Context context, AttributeSet attrs) {
		super(context, attrs);
		postInitViewPager();
	}

	private ScrollerCustomDuration mScroller = null;

	private void postInitViewPager() {
		try {
			Class<?> viewpager = ViewPager.class;
			Field scroller = viewpager.getDeclaredField("mScroller");
			scroller.setAccessible(true);
			Field interpolator = viewpager.getDeclaredField("sInterpolator");
			interpolator.setAccessible(true);

			mScroller = new ScrollerCustomDuration(getContext(), (Interpolator) interpolator.get(this));
			scroller.set(this, mScroller);
		} catch (Exception e) {
		}
	}

	public void setScrollDurationFactor(double scrollFactor) {
		mScroller.setScrollDurationFactor(scrollFactor);
	}

	private final class ScrollerCustomDuration extends Scroller {
		private double mScrollFactor = 1;

		public ScrollerCustomDuration(Context context) {
			super(context);
		}

		public ScrollerCustomDuration(Context context, Interpolator interpolator) {
			super(context, interpolator);
		}

		@SuppressLint("NewApi")
		public ScrollerCustomDuration(Context context, Interpolator interpolator, boolean flywheel) {
			super(context, interpolator, flywheel);
		}

		public void setScrollDurationFactor(double scrollFactor) {
			mScrollFactor = scrollFactor;
		}

		/**
		 * Ä¬ÈÏduration = 250
		 */
		public void startScroll(int startX, int startY, int dx, int dy, int duration) {
			super.startScroll(startX, startY, dx, dy, (int) (duration * mScrollFactor));
		}
	}
}
