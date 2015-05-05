package com.idroid.eteach.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idroid.eteach.R;
import com.idroid.eteach.adapter.internal.PagerAdapter;
import com.idroid.eteach.fragment.FragmentDemo;
import com.idroid.eteach.ui.base.ActivityBase;
import com.idroid.eteach.widget.SlidingTabLayout;

public class BaseActivityDemo extends ActivityBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mViewPager.setAdapter(new Adapter(getSupportFragmentManager(), initData()));
		mViewPager.setCurrentItem(1);
		mSlidingTabLayout.setViewPager(mViewPager);
	}

	private ArrayList<HashMap<String, Object>> initData() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 4; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			Fragment v = new FragmentDemo(i);
			map.put("content", v);
			map.put("icon", TabIcons[i]);
			list.add(map);
		}
		return list;
	}

	int[] TabIcons = { R.drawable.abc_ic_menu_paste_mtrl_am_alpha, R.drawable.abc_ic_menu_share_mtrl_alpha,
			R.drawable.abc_ic_menu_paste_mtrl_am_alpha, R.drawable.abc_ic_menu_moreoverflow_mtrl_alpha };
}

class Adapter extends PagerAdapter {

	private ArrayList<HashMap<String, Object>> list;

	public Adapter(FragmentManager fm, ArrayList<HashMap<String, Object>> list) {
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

	/*@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}*/

	@Override
	public Fragment getItem(int arg0) {
		Fragment item = (Fragment) list.get(arg0).get("content");
		return item;
	}
	
	
}