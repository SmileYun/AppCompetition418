package com.idroid.eteach.activity;

import java.util.HashMap;

import android.os.Bundle;
import android.widget.ListView;

import com.idroid.eteach.R;
import com.idroid.eteach.adapter.internal.KJAdapter;
import com.idroid.eteach.controller.DetailFragmentItemController;
import com.idroid.eteach.controller.base.BaseController;
import com.idroid.eteach.ui.base.ActivityBase;

public class DetailFragmentItem extends ActivityBase {
	
	private ListView mListView;
	private DetailFragmentItemController mController;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_qaq);
		init();
	}

	private void init() {
		mListView = (ListView) findViewById(R.id.detail_listview);
		mController = getController();
		mController.attachedUI(this);
		mController.initialized();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected <T extends BaseController> T getController() {
		return (T) new DetailFragmentItemController();
	}
	
	public void setAdapter(KJAdapter<HashMap<String, Object>> adapter){
		mListView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	
	public ListView getListView(){
		return mListView;
	}
}