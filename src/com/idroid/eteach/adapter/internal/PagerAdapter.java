package com.idroid.eteach.adapter.internal;

import android.support.v4.app.FragmentManager;

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
public abstract class PagerAdapter extends  android.support.v4.view.PagerAdapter/*FragmentStatePagerAdapter*/{
	
	public PagerAdapter() {
	}

	public abstract int getTabIndicatorIcon(int position);
	
	/** �������Ҫ�򷵻�-1*/
	public int getSelectedTabIndicatorIcon(int position){return -1;}
	
}
