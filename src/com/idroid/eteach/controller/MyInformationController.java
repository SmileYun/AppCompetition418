package com.idroid.eteach.controller;

import java.util.ArrayList;
import java.util.HashMap;

import android.widget.AbsListView;

import com.idroid.eteach.adapter.MyInformationAdapter;
import com.idroid.eteach.adapter.TeacherAdapter;
import com.idroid.eteach.controller.base.BaseController;
import com.idroid.eteach.fragment.MyInformationFragment;
import com.idroid.eteach.fragment.TeacherFragment;
import com.idroid.eteach.fragment.base.FragmentBase;

public class MyInformationController extends BaseController<FragmentBase> {

	public interface UI {

	}

	/**
	 * ListViewµÄ³õÊ¼»¯
	 */
	public void setListViewData(AbsListView listView, int ResId) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		list.add(map);

		((MyInformationFragment) ui).setListAdapter(new MyInformationAdapter(listView, list, ResId));
	}

	@Override
	public void initialized() {
		
	}
}
