package com.idroid.eteach.controller;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;

import com.idroid.eteach.R;
import com.idroid.eteach.activity.ContactActivity;
import com.idroid.eteach.adapter.StudentAdapter;
import com.idroid.eteach.adapter.TeacherAdapter;
import com.idroid.eteach.adapter.internal.KJAdapter;
import com.idroid.eteach.controller.base.BaseController;
import com.idroid.eteach.fragment.StudentFragment;
import com.idroid.eteach.fragment.TeacherFragment;
import com.idroid.eteach.fragment.base.FragmentBase;

public class StudentController extends BaseController<FragmentBase> implements OnClickListener {

	private KJAdapter<HashMap<String,Object>> adapter;
	
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
		
		adapter = new StudentAdapter(listView, list, ResId);
		((StudentFragment)ui).setListAdapter(adapter);
		adapter.setViewClickListener(R.id.ibtn_student_contact, this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(getContext(), ContactActivity.class);
		getContext().startActivity(intent);
	}
	
	
	
}
