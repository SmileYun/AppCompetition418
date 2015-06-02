package com.idroid.eteach.controller;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.idroid.eteach.R;
import com.idroid.eteach.activity.NewsActivity;
import com.idroid.eteach.adapter.MainHomeListViewAdapter;
import com.idroid.eteach.adapter.internal.KJAdapter;
import com.idroid.eteach.controller.base.BaseController;
import com.idroid.eteach.fragment.MainHomeFragment;
import com.idroid.eteach.fragment.base.FragmentBase;

public class MainHomeController extends BaseController<FragmentBase> implements OnItemClickListener{
	
	public interface UI{
		
	}
	
	private int[] imageLists = { R.drawable.bg_dredge_vip, R.drawable.bg_game_sso, R.drawable.bg_live_head_room };
	private KJAdapter<HashMap<String,Object>> adapter;
	
	public void initialized() {
		((MainHomeFragment) ui).setViewPageAdapter(new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}
			
			@Override
			public int getCount() {
				return imageLists.length;
			}

			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				container.removeView((View) object);
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View v = new ImageView(ui.getActivity());
				v.setBackgroundResource(imageLists[position]);
				container.addView(v);
				return v;
			}
		});
	}
	
	public void setListViewData(AbsListView listView, int resId){
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 5; i++) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			list.add(m);
		}
		
		adapter = new MainHomeListViewAdapter(listView, list, resId);
//		adapter.setViewClickListener(R.id.item_like_img, this);
		adapter.setItemClickListener(this);
		((MainHomeFragment) ui).setListAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(getContext(), NewsActivity.class);
		getContext().startActivity(intent);
	}
	
	
}
