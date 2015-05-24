package com.idroid.eteach.fragment;

import java.lang.ref.WeakReference;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.idroid.eteach.R;
import com.idroid.eteach.controller.ControllerDemo;
import com.idroid.eteach.controller.base.BaseController;
import com.idroid.eteach.fragment.base.FragmentBase;
import com.idroid.eteach.ui.base.ActivityBase;
import com.idroid.eteach.widget.DynamicViewPager;

public class FragmentDemo extends FragmentBase implements OnClickListener, ControllerDemo.DemoUi, OnRefreshListener {
	int i;

	public FragmentDemo(int index) {
		i = index;
	}

	private SwipeRefreshLayout mSwipeRefreshLayout;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		h = new MyHandler((ActivityBase) getActivity());
		if (mSwipeRefreshLayout != null) {
			mSwipeRefreshLayout.setColorSchemeColors(R.color.slidingTabLayout_background,
					R.color.slidingTabLayout_selectedColor);
			mSwipeRefreshLayout.setOnRefreshListener(this);
		}
	}

	View v;
	DynamicViewPager dy;
	int[] imageLists = { R.drawable.bg_dredge_vip, R.drawable.bg_game_sso, R.drawable.bg_live_head_room };

	@Override
	public void onClick(View v) {
		((ControllerDemo) getController()).doOnclick(v);
	}

	@Override
	public void updateTextView(View v, String result) {
		((TextView) v).setText(result);
	}

	@Override
	public void onRefresh() {
		Toast.makeText(getActivity(), "refreshing...", Toast.LENGTH_SHORT).show();
		h.sendEmptyMessageDelayed(0, 2500);
	}

	MyHandler h;

	class MyHandler extends Handler {
		private WeakReference<ActivityBase> a;

		public MyHandler(ActivityBase a) {
			this.a = new WeakReference<ActivityBase>(a);
		}

		@Override
		public void handleMessage(Message msg) {
			if (a.get() != null) {
				mSwipeRefreshLayout.setRefreshing(false);
				((ControllerDemo) getController()).doRefresh();
			}
		}

	}

	@Override
	public void refreshed(String result) {
		((TextView) v.findViewById(R.id.page)).setText(result);
	}

	@Override
	protected BaseController getController() {
		BaseController c = new ControllerDemo();
		c.attachedUI(this);
		return c;
	}

	@Override
	protected void initData() {

	}

	@Override
	protected void bindWidget(View v) {
		if (i == 3) {
			dy = (DynamicViewPager) v.findViewById(R.id.testdy);
			dy.setPagerAdapter(new PagerAdapter() {

				@Override
				public boolean isViewFromObject(View arg0, Object arg1) {
					// TODO Auto-generated method stub
					return arg0 == arg1;
				}

				@Override
				public int getCount() {
					return 3;
				}

				@Override
				public void destroyItem(ViewGroup container, int position, Object object) {
					container.removeView((View) object);
				}

				@Override
				public Object instantiateItem(ViewGroup container, int position) {
					View v = new ImageView(getActivity());
					v.setBackgroundResource(imageLists[position]);
					container.addView(v);
					return v;
				}
			});
		} else {
			((TextView) v.findViewById(R.id.page)).setText("page" + i);
			((TextView) v.findViewById(R.id.page)).setOnClickListener(this);
			mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeLayout);
		}
	}

	@Override
	protected View inflateLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = null;
		if (i == 3) {
			v = getActivity().getLayoutInflater().inflate(R.layout.testdy, null);

		} else {
			v = getActivity().getLayoutInflater().inflate(R.layout.demo_page, null);
		}
		return v;
	}
}
