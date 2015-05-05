package com.idroid.eteach.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.idroid.eteach.R;
import com.idroid.eteach.adapter.AdapterDemo;
import com.idroid.eteach.fragment.FragmentDemo;
import com.idroid.eteach.ui.base.ActivityBase;

public class BaseActivityDemo extends ActivityBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mViewPager.setAdapter(new AdapterDemo(getSupportFragmentManager(), initData()));
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
