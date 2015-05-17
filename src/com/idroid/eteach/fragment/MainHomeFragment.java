package com.idroid.eteach.fragment;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;

import com.idroid.eteach.R;
import com.idroid.eteach.adapter.internal.FragmentPagerAdapter;
import com.idroid.eteach.adapter.internal.KJAdapter;
import com.idroid.eteach.controller.MainHomeController;
import com.idroid.eteach.fragment.base.FragmentBase;
import com.idroid.eteach.widget.DynamicViewPager;

public class MainHomeFragment extends FragmentBase implements MainHomeController.UI {
	private DynamicViewPager mViewPager;
	private ListView mListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_mainhome, container, false);
		mViewPager = (DynamicViewPager) v.findViewById(R.id.viewpager);
		mListView = (ListView) v.findViewById(R.id.listview);
		LayoutParams p =new LayoutParams(LayoutParams.MATCH_PARENT, 300);
		mListView.setLayoutParams(p);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getController().initialized();
		getController().setListViewData(mListView, R.layout.listview_item_home);
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
		mViewPager.setPagerAdapter(a);
	}
	
	public void setFragmentPagerAdapter(FragmentPagerAdapter a) {
		mViewPager.setFragmentPagerAdapter(a);
	}
	
	
	public void setListAdapter(KJAdapter<HashMap<String, Object>> adapter){
		mListView.setAdapter(adapter);
	}
}
