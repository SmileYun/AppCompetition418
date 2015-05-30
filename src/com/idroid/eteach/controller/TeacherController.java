package com.idroid.eteach.controller;

import java.util.ArrayList;
import java.util.HashMap;

import android.widget.AbsListView;

import com.idroid.eteach.adapter.TeacherAdapter;
import com.idroid.eteach.controller.base.BaseController;
import com.idroid.eteach.fragment.TeacherFragment;
import com.idroid.eteach.fragment.base.FragmentBase;

public class TeacherController extends BaseController<FragmentBase> {

	public interface UI{
		
	}
	
	/**
	 * ListViewµÄ³õÊ¼»¯
	 */
	public void setListViewData(AbsListView listView, int ResId){
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		for(int i=0; i<6; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			list.add(map);
		}
		
		((TeacherFragment)ui).setListAdapter(new TeacherAdapter(listView, list, ResId));
	}
	
}
