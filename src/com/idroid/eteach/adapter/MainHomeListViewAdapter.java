package com.idroid.eteach.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.view.View;
import android.widget.AbsListView;

import com.idroid.eteach.R;
import com.idroid.eteach.adapter.internal.AdapterViewHolder;
import com.idroid.eteach.adapter.internal.KJAdapter;

public class MainHomeListViewAdapter extends KJAdapter<HashMap<String, Object>>{
	
	

	public MainHomeListViewAdapter(AbsListView listView, ArrayList<HashMap<String, Object>> list, int itemLayoutId) {
		super(listView, list, itemLayoutId);
	}

	@Override
	public void convert(AdapterViewHolder helper, HashMap<String, Object> item, boolean isScrolling) {
		//R.layout.listview_item_home 对应视图
//		helper.setText(R.id.item_name_tv, "yun");
//		helper.setText(R.id.item_posttime_tv, "1分钟前");
//		helper.setText(R.id.item_subcontent_tv, "对问题的描述为....");
//		helper.setText(R.id.item_title_tv, "设置标题");
//		helper.setImageResource(R.id.item_portain_img, R.drawable.default_icon);
//		helper.setImageResource(R.id.item_content_img, R.drawable.friends);
//		helper.setImageResource(R.id.item_like_img, R.drawable.actionbar_like);
		
		
		
	}
}