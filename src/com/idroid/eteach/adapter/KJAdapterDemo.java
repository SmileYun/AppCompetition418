package com.idroid.eteach.adapter;

import java.util.Collection;

import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.idroid.eteach.R;
import com.idroid.eteach.R.drawable;
import com.idroid.eteach.R.id;
import com.idroid.eteach.adapter.internal.AdapterViewHolder;
import com.idroid.eteach.adapter.internal.KJAdapter;

/**
 *在Activity  或者 Fragment 中，创建  KJAdapterDemo 实例 <p>
 *
 *	{@code ListView mList.setAdapter(new DemoAdapter(mList, datas, R.layout.list_item));}
 *
 * @author SmileYun
 */
public class KJAdapterDemo extends KJAdapter<DataBean>{

	public KJAdapterDemo(AbsListView listView, Collection<DataBean> datas, int itemLayoutId) {
		super(listView, datas, itemLayoutId);
	}

	@Override
	public void convert(AdapterViewHolder helper, DataBean item, boolean isScrolling) {
		 helper.setText(R.id.list_item_tv, item.text);
		 helper.setImageResource(R.id.list_item_img, item.id);
	}
}

class DataBean {
    String text;
    int id;
}

class ViewHolder {
    ImageView img;
    TextView tv;
}