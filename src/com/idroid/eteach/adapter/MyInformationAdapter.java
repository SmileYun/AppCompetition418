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
//		helper.setText(R.id.itv_teacher_name, "����");
//		helper.setText(R.id.itv_teacher_answ, "23�ظ�");
//		helper.setText(R.id.itv_teacher_date, "2015-05-20");
//		
//		helper.setText(R.id.itv_teacher_info, "�����������пգ�����Ҫ��ϰ��ѧ��������ѧ��ͬѧ������ϵ��Ŷ~");
//		helper.setText(R.id.itv_teacher_time, "ʱ�䣺2015-05-22 ����09:00");
//		helper.setText(R.id.itv_teacher_addr, "�ص㣺�����ʵ��ѧ");
//		helper.setText(R.id.itv_teacher_class, "��Ŀ����ѧ | ���� | ��ѧ");
	}

}
