package com.idroid.eteach.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.idroid.eteach.adapter.internal.FragmentPagerAdapter;

public class FragmentPagerAdapterDemo extends FragmentPagerAdapter {
	private ArrayList<HashMap<String, Object>> list;

	public FragmentPagerAdapterDemo(FragmentManager fm, ArrayList<HashMap<String, Object>> list) {
		super(fm);
		this.list = list;
	}

	@Override
	public int getTabIndicatorIcon(int position) {
		return (Integer) list.get(position).get("icon");
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment item = (Fragment) list.get(arg0).get("content");
		return item;
	}
}
