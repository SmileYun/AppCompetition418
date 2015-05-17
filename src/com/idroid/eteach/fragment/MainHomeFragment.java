package com.idroid.eteach.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.idroid.eteach.R;
import com.idroid.eteach.controller.MainHomeController;
import com.idroid.eteach.fragment.base.FragmentBase;
import com.idroid.eteach.widget.ViewPagerCustomDuration;

public class MainHomeFragment extends FragmentBase implements MainHomeController.UI {
	private MainHomeController controller;

	private ViewPagerCustomDuration mViewPager;
	private ListView mListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_mainhome, null);
		mViewPager = (ViewPagerCustomDuration) v.findViewById(R.id.viewpager);
		mListView = (ListView) v.findViewById(R.id.listview);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getController().initialized();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
		super.onInflate(activity, attrs, savedInstanceState);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected MainHomeController getController() {
		MainHomeController controller = new MainHomeController();
		controller.attachedUI(this);
		return controller;
	}

	public void setViewPageAdapter(PagerAdapter a) {
		mViewPager.setAdapter(a);
	}
}
