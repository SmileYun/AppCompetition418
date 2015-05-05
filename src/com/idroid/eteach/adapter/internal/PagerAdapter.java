package com.idroid.eteach.adapter.internal;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * <br>����TabIndicator ͼ���ѡ��ʱ��ͼ��</br>
 * {@code
 * 		map.put("content", View);
		map.put("icon", int_TabIcons[i]);
 * }
 * 
 * @author SmileYun
 *
 */
public abstract class PagerAdapter extends FragmentPagerAdapter/*FragmentStatePagerAdapter*/{
	
	public PagerAdapter(FragmentManager fm) {
		super(fm);
	}

	public abstract int getTabIndicatorIcon(int position);
	
	/** �������Ҫ�򷵻�-1*/
	public int getSelectedTabIndicatorIcon(int position){return -1;}
	
}
