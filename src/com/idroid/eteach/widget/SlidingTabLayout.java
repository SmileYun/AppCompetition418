package com.idroid.eteach.widget;

import android.R.integer;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.idroid.eteach.R;
import com.idroid.eteach.adapter.internal.FragmentPagerAdapter;
import com.idroid.eteach.util.UiUtils;

public class SlidingTabLayout extends HorizontalScrollView {

	public interface TabListener {
		/** 点击其他选中tab */
		void onTabSelected(int pos);

		/** 再次点击已选中tab */
		void onTabReSelected(int pos);
	}

	private TabListener mTabListener;

	private static final int TITLE_OFFSET_DIPS = 24;
	private static final int TAB_VIEW_PADDING_DIPS = 16;
	private static final int TAB_VIEW_TEXT_SIZE_SP = 12;
	private static final int INDICATOR_DEFAULT_BGCOLOR = Color.TRANSPARENT;

	private int mTitleOffset;

	private int mTabViewLayoutId;
	private int mTabViewTextViewId;

	private int mSelectedBackground;
	private int mTabStripBackground;
	private int mTabStripIndicatorColor;
	
	private int mTabViewTextAppearance;

	private int mTabHeight;

	private ViewPager mViewPager;
	private FragmentPagerAdapter mPagerAdapter;
	private ViewPager.OnPageChangeListener mViewPagerPageChangeListener;

	private LinearLayout mTitleLayout;

	private LinearLayout mIconLayout;

	private SlidingTabStrip mTabStrip;

	public SlidingTabLayout(Context context) {
		this(context, null);
	}

	public SlidingTabLayout(Context context, AttributeSet attrs) {
		super(context, attrs);

		setHorizontalScrollBarEnabled(false);

		setFillViewport(true);

		mTitleLayout = new LinearLayout(context);
		mTitleLayout.setOrientation(LinearLayout.VERTICAL);

		mIconLayout = new LinearLayout(context);
		mIconLayout.setOrientation(LinearLayout.HORIZONTAL);
		// mIconLayout.setPadding(UIUtil.dp2px(context, 3),
		// UIUtil.dp2px(context, 7), UIUtil.dp2px(context, 2),
		// UIUtil.dp2px(context, 7));

		LayoutParams titleIconParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		titleIconParams.setMargins(UiUtils.dp2px(context, 3), UiUtils.dp2px(context, 7), UiUtils.dp2px(context, 2),
				UiUtils.dp2px(context, 7));

		mTitleLayout.addView(mIconLayout, titleIconParams);

		addView(mTitleLayout, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		mTitleOffset = (int) (context.getResources().getDisplayMetrics().density * TITLE_OFFSET_DIPS);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlidingTabLayout);

		mSelectedBackground = a.getColor(R.styleable.SlidingTabLayout_selectedColor, -1);
		mTabHeight = a.getDimensionPixelSize(R.styleable.SlidingTabLayout_indicatorHeight,
				UiUtils.dp2px(context, SlidingTabStrip.DEFAULT_INDICATOR_HEIGHT_DIPS));

		// TODO: Set TabStrip

		a.recycle();
	}

	public void setTabListener(TabListener tabListener) {
		this.mTabListener = tabListener;
	}

	public void setViewPager(ViewPager vp) {
		this.mViewPager = vp;

		if (this.mViewPager != null)
			this.mViewPager.setOnPageChangeListener(new InternalViewPagerListener());

		mPagerAdapter = (FragmentPagerAdapter) mViewPager.getAdapter();

		mTabStrip = new SlidingTabStrip(getContext());
		
		setTabStripIndicatorColor(mTabStripIndicatorColor);
		
		LayoutParams titleStripParams = (LayoutParams) mTabStrip.getLayoutParams();
		if (titleStripParams == null)
			titleStripParams = new LayoutParams(LayoutParams.MATCH_PARENT, mTabHeight);
		titleStripParams.height = mTabHeight;
		mTitleLayout.addView(mTabStrip, titleStripParams);

		notifyDataSetChanged();
	}

	public void notifyDataSetChanged() {
		if (mViewPager != null)
			populateTabStrip();
	}

	private void populateTabStrip() {
		final View.OnClickListener tabClickListener = new TabClickListener();

		int count = mPagerAdapter.getCount();
		for (int i = 0; i < count; i++) {
			// View v = getChildAt(i);
			View v = null;
			v = createTabView(i);
			v.setOnClickListener(tabClickListener);
			mIconLayout.addView(v, getResources().getDisplayMetrics().widthPixels / count, LayoutParams.MATCH_PARENT);
		}
	}

	private View createTabView(int position) {
		Context context = getContext();
		ImageView v = new ImageView(context);
		v.setBackgroundColor(INDICATOR_DEFAULT_BGCOLOR);
		v.setPadding(UiUtils.dp2px(context, 3), UiUtils.dp2px(context, 9), UiUtils.dp2px(context, 3),
				UiUtils.dp2px(context, 9));
		v.setImageDrawable(getResources().getDrawable(mPagerAdapter.getTabIndicatorIcon(position)));
		return v;
	}

	class InternalViewPagerListener implements ViewPager.OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			if (mViewPagerPageChangeListener != null)
				mViewPagerPageChangeListener.onPageScrollStateChanged(arg0);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

			scrollToTab(arg0, arg1);

			if (mViewPagerPageChangeListener != null)
				mViewPagerPageChangeListener.onPageScrolled(arg0, arg1, arg2);
		}

		@Override
		public void onPageSelected(int arg0) {
			swipeSelected();

			// scrollToTab(arg0, 0);

			ImageView selectedPosition = (ImageView) mIconLayout.getChildAt(arg0);

			if (mSelectedBackground != -1)
				selectedPosition.setBackgroundColor(mSelectedBackground);

			if (mPagerAdapter.getSelectedTabIndicatorIcon(arg0) != -1)
				selectedPosition.setImageDrawable(getResources().getDrawable(mSelectedBackground));

			if (mViewPagerPageChangeListener != null)
				mViewPagerPageChangeListener.onPageSelected(arg0);

			if (mTabListener != null)
				mTabListener.onTabSelected(arg0);
		}
	}

	private void swipeSelected() {
		int count = mViewPager.getAdapter().getCount();
		for (int i = 0; i < count; i++) {
			mIconLayout.getChildAt(i).setBackgroundColor(INDICATOR_DEFAULT_BGCOLOR);
		}
	}

	private class TabClickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			for (int i = 0; i < mPagerAdapter.getCount(); i++) {
				if (v == mIconLayout.getChildAt(i)) {
					final int previousPos = mViewPager.getCurrentItem();
					if (mTabListener != null) {
						if (previousPos != i) {
							mTabListener.onTabSelected(i);
						} else {
							mTabListener.onTabReSelected(i);
						}
					}
					mViewPager.setCurrentItem(i);
					return;
				}
			}
		}
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

		if (mViewPager != null) {
			if (mSelectedBackground != -1)
				((ImageView) mIconLayout.getChildAt(mViewPager.getCurrentItem())).setBackgroundColor(mSelectedBackground);

			if (mPagerAdapter.getSelectedTabIndicatorIcon(mViewPager.getCurrentItem()) != -1)
				((ImageView) mIconLayout.getChildAt(mViewPager.getCurrentItem())).setImageDrawable(getResources()
						.getDrawable(mSelectedBackground));
			scrollToTab(mViewPager.getCurrentItem(), 0);
		}
	}

	private void scrollToTab(int tabIndex, float positionOffset) {
		if (tabIndex < 0)
			return;

		// setSelectedTab(tabIndex, positionOffset);
		mTabStrip.onViewPagerPageChanged(tabIndex, positionOffset);
	}

	private  void setTabStripIndicatorColor(int mSelectedBackground) {
		if (mTabStrip != null)
			mTabStrip.setSelectedIndicatorColor(mSelectedBackground);
	}
	
	public  void setTabStripColor(int tabStripIndicatorColor) {
		mTabStripIndicatorColor =	tabStripIndicatorColor;
	}

	public void setTabSelectedColor(int mSelectedBackground) {
		this.mSelectedBackground = mSelectedBackground;
	}

	public void setTabStripBackground(int mTabStripBackground) {
		this.mTabStripBackground = mTabStripBackground;
	}

	private void setSelectedTab(int position, float positionOffset) {
	}

	private class SlidingTabStrip extends View {
		public static final int DEFAULT_INDICATOR_HEIGHT_DIPS = 4;

		private int mSelectedIndicatorHeight;
		private int mIndicatorWidth;
		private final Paint mSelectedIndicatorPaint;

		private int mSelectedPosition;
		private float mSelectionOffset;
		private int mSelectedColor;

		SlidingTabStrip(Context context) {
			this(context, null);
		}

		SlidingTabStrip(Context context, AttributeSet attrs) {
			super(context, attrs);
			setWillNotDraw(false);

			final float density = getResources().getDisplayMetrics().density;
			mIndicatorWidth = getResources().getDisplayMetrics().widthPixels / mPagerAdapter.getCount();
			mSelectedIndicatorHeight = (int) (DEFAULT_INDICATOR_HEIGHT_DIPS * density);
			setBackgroundColor(mTabStripBackground);
			mSelectedIndicatorPaint = new Paint();
			mSelectedColor = getResources().getColor(R.color.colorAccent);
		}

		public void setSelectedIndicatorColor(int color) {
			mSelectedColor = color;
			invalidate();
		}

		void setSelectedIndicatorHeight(int height) {
			mSelectedIndicatorHeight = height;
			invalidate();
		}

		void onViewPagerPageChanged(int position, float positionOffset) {
			mSelectedPosition = position;
			mSelectionOffset = positionOffset;
			invalidate();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			final int height = mSelectedIndicatorHeight;

			mSelectedIndicatorPaint.setColor(mTabStripBackground);
			canvas.drawRect(0, 0, getResources().getDisplayMetrics().widthPixels, mSelectedIndicatorHeight,
					mSelectedIndicatorPaint);

			float left = mSelectedPosition * mIndicatorWidth + mSelectionOffset * mIndicatorWidth;
			float right = left + mIndicatorWidth;
			float top = 0;
			float bottom = mSelectedIndicatorHeight;

			mSelectedIndicatorPaint.setColor(mSelectedColor);
			canvas.drawRect(left, top, right, bottom, mSelectedIndicatorPaint);
		}
	}
}
