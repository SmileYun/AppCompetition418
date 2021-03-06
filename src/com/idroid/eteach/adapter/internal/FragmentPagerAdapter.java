package com.idroid.eteach.adapter.internal;

import android.support.v4.app.FragmentManager;

/**
 * <br>设置TabIndicator 图标和选中时的图标</br>
 * {@code
 * 		map.put("content", View);
		map.put("icon", int_TabIcons[i]);
 * }
 * 
 * @author SmileYun
 *
 */
public abstract class FragmentPagerAdapter extends  android.support.v4.app.FragmentPagerAdapter/*FragmentStatePagerAdapter*/{
	
	public FragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	public abstract int getTabIndicatorIcon(int position);
	
	/** 如果不需要则返回-1*/
	public int getSelectedTabIndicatorIcon(int position){return -1;}
	
}
