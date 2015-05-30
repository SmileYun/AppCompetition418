package com.idroid.eteach.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.idroid.eteach.R;
import com.idroid.eteach.adapter.FragmentPagerAdapterDemo;
import com.idroid.eteach.fragment.FragmentDemo;
import com.idroid.eteach.fragment.MainHomeFragment;
import com.idroid.eteach.fragment.MyInformationFragment;
import com.idroid.eteach.fragment.StudentFragment;
import com.idroid.eteach.fragment.TeacherFragment;
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
		
		
		HashMap<String, Object> m2 = new HashMap<String, Object>();
		Fragment v2= new TeacherFragment();
		m2.put("content", v2);
		m2.put("icon", TabIcons[1]);
		list.add(m2);
		
		HashMap<String, Object> m3 = new HashMap<String, Object>();
		Fragment v3= new StudentFragment();
		m3.put("content", v3);
		m3.put("icon", TabIcons[2]);
		list.add(m3);
		
		HashMap<String, Object> m4 = new HashMap<String, Object>();
		Fragment v4= new MyInformationFragment();
		m4.put("content", v4);
		m4.put("icon", TabIcons[3]);
		list.add(m4);
		
		return list;
	}

	int[] TabIcons = { R.drawable.abc_ic_menu_paste_mtrl_am_alpha, R.drawable.abc_ic_menu_share_mtrl_alpha,
			R.drawable.abc_ic_menu_paste_mtrl_am_alpha, R.drawable.abc_ic_menu_moreoverflow_mtrl_alpha };
}
