package com.idroid.eteach.fragment;

import java.util.HashMap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.idroid.eteach.R;
import com.idroid.eteach.adapter.internal.KJAdapter;
import com.idroid.eteach.controller.TeacherController;
import com.idroid.eteach.controller.base.BaseController;
import com.idroid.eteach.fragment.base.FragmentBase;

public class TeacherFragment extends FragmentBase<TeacherController> implements TeacherController.UI {

	private ListView mListView;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mController.setListViewData(mListView, R.layout.item_teacher);
	}

	@Override
	protected TeacherController getController() {
		TeacherController controller = new TeacherController();
		controller.attachedUI(this);
		return controller;
	}

	@Override
	protected void initData() {

	}

	@Override
	protected void bindWidget(View v) {
		mListView = (ListView) v.findViewById(R.id.ilv_listview);
	}

	/**
	 * 初始化布局文件
	 */
	@Override
	protected View inflateLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_teacher, container, false);
	}

	/**
	 * 设置LisetView的数据适配
	 */
	public void setListAdapter(KJAdapter<HashMap<String, Object>> adapter) {
		mListView.setAdapter(adapter);
	}

}
