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

		situmulateAs();
		
		adapter = new DetailQAQAdapter(getUi().getListView(), list, R.layout.activity_detail_qaq_item);
		getUi().setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	
	void situmulateAs(){
		HashMap<String, Object> m =new HashMap<String, Object>();
		m.put("type", "answer");
		m.put("name", "jiang");
		m.put("Content", as);
		list.add(m);
	}
	
	String as = "��1���ܸ�AB��B���ܵ�������D��������FB=GD=mg=3kg��10N/kg=30N�����ݸܸ�ƽ�������ã�FA��OA=FB��OB����ΪOA��OB=3��1������FA��FB=1��3��FA=10N��GC=m��g=1.5kg��10N/kg=15N��GQ=m��g=0.2kg��10N/kg=2N����������C��ֹ��2T��=GQ+GC-FA���������ݵã�T��=3.5N����2���Զ��������ظպñ�������˵������TΪ6N��2T=GQ+GC-FA�䣬FA��=GQ+GC-2T=2N+15N-2��6N=5N�����ݸܸ�ƽ��������FA���OA=FB���OB����ΪOA��OB=3��1������FA�䣺FB��=1��3��FB��=15N����������D��ֹ��F��+FB��=GD�����ԣ�F��=15N����ͼ��ɴ�ʱ����Ϊ��0.6A����·�ܵ��裺R=UI=12V0.6A=20��������R0�ĵ���ֵΪ��20��-10��=10�����𣺣�1�����е�Զ���������������ϸ���ϵ�������3.5N����2����������R0�ĵ���ֵ��10����";
}
