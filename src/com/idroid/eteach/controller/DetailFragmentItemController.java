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
	
	String as = "（1）杠杆AB的B端受到的力是D的重力：FB=GD=mg=3kg×10N/kg=30N，根据杠杆平衡条件得：FA×OA=FB×OB，因为OA：OB=3：1，所以FA：FB=1：3，FA=10N，GC=m′g=1.5kg×10N/kg=15N，GQ=m″g=0.2kg×10N/kg=2N，根据重物C静止，2T′=GQ+GC-FA，代入数据得：T′=3.5N．（2）自动拉开开关刚好被拉开，说明拉力T为6N，2T=GQ+GC-FA′，FA′=GQ+GC-2T=2N+15N-2×6N=5N，根据杠杆平衡条件，FA′×OA=FB′×OB，因为OA：OB=3：1，所以FA′：FB′=1：3，FB′=15N，根据重物D静止，F引+FB′=GD，所以，F引=15N，由图象可此时电流为：0.6A．电路总电阻：R=UI=12V0.6A=20Ω，所以R0的电阻值为：20Ω-10Ω=10Ω．答：（1）与机械自动拉开开关相连的细绳上的拉力是3.5N．（2）保护电阻R0的电阻值是10Ω．";
}
