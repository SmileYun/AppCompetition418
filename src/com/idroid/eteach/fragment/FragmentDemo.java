package com.idroid.eteach.fragment;

import java.lang.ref.WeakReference;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.idroid.eteach.R;
import com.idroid.eteach.controller.ControllerDemo;
import com.idroid.eteach.fragment.base.FragmentBase;
import com.idroid.eteach.ui.base.ActivityBase;

public class FragmentDemo extends FragmentBase implements OnClickListener, ControllerDemo.DemoUi, OnRefreshListener {
	int i;

	public FragmentDemo(int index) {
		i = index;
	}

	private SwipeRefreshLayout mSwipeRefreshLayout;
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		h = new MyHandler((ActivityBase) getActivity());
		mSwipeRefreshLayout.setColorSchemeColors(R.color.slidingTabLayout_background, R.color.slidingTabLayout_selectedColor);
		mSwipeRefreshLayout.setOnRefreshListener(this);

	}
	View v;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		v = getActivity().getLayoutInflater().inflate(R.layout.page, null);
		((TextView) v.findViewById(R.id.page)).setText("page" + i);
		((TextView) v.findViewById(R.id.page)).setOnClickListener(this);
		mSwipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipeLayout);
		return v;
	}

	View v;
	@Override
	public void onClick(View v) {
		this.v = v;
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
	public void refreshed(String result) {
		((TextView) v.findViewById(R.id.page)).setText(result);
	}
}
