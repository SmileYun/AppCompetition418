package com.idroid.eteach.controller;

import java.util.ArrayList;
import java.util.HashMap;


import com.idroid.eteach.R;
import com.idroid.eteach.adapter.DetailQAQAdapter;
import com.idroid.eteach.controller.base.BaseController;
import com.idroid.eteach.ui.DetailFragmentItem;

public class DetailFragmentItemController extends BaseController<DetailFragmentItem>{

	private ArrayList<HashMap<String, Object>> list;
	private DetailQAQAdapter adapter;
	
	@Override
	public void initialized() {
		list = new ArrayList<HashMap<String,Object>>();
		adapter = new DetailQAQAdapter(getUi().getListView(), list, R.layout.activity_detail_qaq_item);
		getUi().setAdapter(adapter);
//		fetchData();
	}

	private void fetchData() {
		adapter.notifyDataSetChanged();
	}
	
	
	
}
