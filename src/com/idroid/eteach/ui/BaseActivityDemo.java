package com.idroid.eteach.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.idroid.eteach.R;
import com.idroid.eteach.adapter.FragmentPagerAdapterDemo;
import com.idroid.eteach.fragment.FragmentDemo;
import com.idroid.eteach.fragment.MainHomeFragment;
import com.idroid.eteach.ui.base.ActivityFrame;

public class BaseActivityDemo extends ActivityFrame{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mViewPager.setAdapter(new FragmentPagerAdapterDemo(getSupportFragmentManager(), initData()));
		mViewPager.setCurrentItem(1);
		mSlidingTabLayout.setViewPager(mViewPager);
	}

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
	}

	private ArrayList<HashMap<String, Object>> initData() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		HashMap<String, Object> m = new HashMap<String, Object>();
		Fragment v1= new MainHomeFragment();
		m.put("content", v1);
		m.put("icon", TabIcons[0]);
		list.add(m);
		
		
		for (int i = 1; i < 4; i++) {
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
