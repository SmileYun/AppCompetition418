package com.idroid.eteach.adapter;

import java.util.Collection;
import java.util.HashMap;

import android.widget.AbsListView;

import com.idroid.eteach.R;
import com.idroid.eteach.adapter.internal.AdapterViewHolder;
import com.idroid.eteach.adapter.internal.KJAdapter;

public class MyInformationAdapter extends KJAdapter<HashMap<String, Object>> {

	public MyInformationAdapter(AbsListView listView,
			Collection<HashMap<String, Object>> datas, int itemLayoutId) {
		super(listView, datas, itemLayoutId);
	}

	@Override
	public void convert(AdapterViewHolder helper, HashMap<String, Object> item,
			boolean isScrolling) {
//		helper.setText(R.id.itv_teacher_name, "宋宇");
//		helper.setText(R.id.itv_teacher_answ, "23回复");
//		helper.setText(R.id.itv_teacher_date, "2015-05-20");
//		
//		helper.setText(R.id.itv_teacher_info, "我周六上午有空，有需要补习数学、物理、化学的同学可以联系我哦~");
//		helper.setText(R.id.itv_teacher_time, "时间：2015-05-22 上午09:00");
//		helper.setText(R.id.itv_teacher_addr, "地点：重庆邮电大学");
//		helper.setText(R.id.itv_teacher_class, "科目：数学 | 物理 | 化学");
	}

}
