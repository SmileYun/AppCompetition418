package com.idroid.eteach.adapter;

import java.util.Collection;
import java.util.HashMap;

import android.widget.AbsListView;

import com.idroid.eteach.R;
import com.idroid.eteach.adapter.internal.AdapterViewHolder;
import com.idroid.eteach.adapter.internal.KJAdapter;

public class StudentAdapter extends KJAdapter<HashMap<String, Object>> {

	public StudentAdapter(AbsListView listView,
			Collection<HashMap<String, Object>> datas, int itemLayoutId) {
		super(listView, datas, itemLayoutId);
	}

	@Override
	public void convert(AdapterViewHolder helper, HashMap<String, Object> item,
			boolean isScrolling) {
		helper.setText(R.id.itv_student_name, "蒋肖");
		helper.setText(R.id.itv_student_answ, "12回复");
		helper.setText(R.id.itv_student_date, "2015-05-24");
		
		helper.setText(R.id.itv_student_ques_text, "如图所示，求解方程组的解，请问学长学姐这个问题有几种解答？");
		helper.setText(R.id.itv_student_class, "数学");
		helper.setText(R.id.itv_student_addr, "重庆市南岸区黄桷垭");
	}
	
	

}
