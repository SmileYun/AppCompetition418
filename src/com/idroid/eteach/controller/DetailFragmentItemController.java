package com.idroid.eteach.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.idroid.eteach.R;
import com.idroid.eteach.activity.DetailFragmentItem;
import com.idroid.eteach.adapter.DetailQAQAdapter;
import com.idroid.eteach.controller.base.BaseController;

public class DetailFragmentItemController extends BaseController<DetailFragmentItem> {

	private ArrayList<HashMap<String, Object>> list;
	private DetailQAQAdapter adapter;

	@Override
	public void initialized() {
		
	}

	public void fetchData() {
		adapter.notifyDataSetChanged();
	}

	public void fetchData(HashMap<String, Object> item) {
		list = new ArrayList<HashMap<String, Object>>();
		list.add(item);
		adapter = new DetailQAQAdapter(getUi().getListView(), list, R.layout.activity_detail_qaq_item);
		getUi().setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
}
