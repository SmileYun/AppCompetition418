package com.idroid.eteach.fragment;

import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.idroid.eteach.R;
import com.idroid.eteach.activity.LoginActivity;
import com.idroid.eteach.adapter.internal.KJAdapter;
import com.idroid.eteach.controller.MyInformationController;
import com.idroid.eteach.controller.base.BaseController;
import com.idroid.eteach.fragment.base.FragmentBase;

public class MyInformationFragment extends FragmentBase<MyInformationController> implements MyInformationController.UI, OnClickListener {

	private ListView mListView;
	private ImageView portrait;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// mController.setListViewData(mListView, R.layout.item_teacher);
	}

	@Override
	protected MyInformationController getController() {
		MyInformationController controller = new MyInformationController();
		return controller;
	}

	@Override
	protected void initData() {

	}

	@Override
	protected void bindWidget(View v) {
		// mListView = (ListView) v.findViewById(R.id.ilv_listview);
		portrait  = (ImageView) v.findViewById(R.id.iiv_user_icon);
		portrait.setOnClickListener(this);
	}

	/**
	 * 初始化布局文件
	 */
	@Override
	protected View inflateLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_user_information, container, false);
	}

	/**
	 * 设置LisetView的数据适配
	 */
	public void setListAdapter(KJAdapter<HashMap<String, Object>> adapter) {
		// mListView.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		i.setClass(getActivity(), LoginActivity.class);
		getActivity().startActivity(i);
	}

}
