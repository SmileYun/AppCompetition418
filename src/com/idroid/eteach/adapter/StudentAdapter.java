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
		helper.setText(R.id.itv_student_name, "��Ф");
		helper.setText(R.id.itv_student_answ, "12�ظ�");
		helper.setText(R.id.itv_student_date, "2015-05-24");
		
		helper.setText(R.id.itv_student_ques_text, "��ͼ��ʾ����ⷽ����Ľ⣬����ѧ��ѧ����������м��ֽ��");
		helper.setText(R.id.itv_student_class, "��ѧ");
		helper.setText(R.id.itv_student_addr, "�������ϰ���������");
	}
	
	

}
