package com.idroid.eteach.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.idroid.eteach.R;
import com.idroid.eteach.activity.ScaleBitmapActivity;
import com.idroid.eteach.adapter.StudentAdapter.BitmapCache;
import com.idroid.eteach.adapter.internal.AdapterViewHolder;
import com.idroid.eteach.adapter.internal.KJAdapter;

public class DetailQAQAdapter extends KJAdapter<HashMap<String, Object>> {

	public static final String CONTEXT = "context";
	public static final String QAQIMGRESID = "imgs";
	public static final String QAQIMGPATH = "imgspath";
	private RequestQueue mQueue;
	private ImageLoader loader;
	
	public DetailQAQAdapter(AbsListView listView, Collection<HashMap<String, Object>> datas, int itemLayoutId) {
		super(listView, datas, itemLayoutId);
//		simulateData();
		mQueue = Volley.newRequestQueue(listView.getContext());
		loader = new ImageLoader(mQueue, new StudentAdapter.BitmapCache());
	}

	@Override
	@SuppressWarnings("unchecked")
	public void convert(AdapterViewHolder helper, HashMap<String, Object> item, boolean isScrolling) {
//		helper.setText(R.id.detail_context_text, (String) item.get(CONTEXT));
		helper.setText(R.id.detail_context_text, (String) item.get("Content"));

		ArrayList<Integer> imgs = (ArrayList<Integer>) item.get(QAQIMGRESID);
		ArrayList<String> imgspath = (ArrayList<String>) item.get(QAQIMGPATH);
		
		if(item.get("ContentImg") != null){
			LinearLayout container = (LinearLayout) helper.getView(R.id.detail_context_img_wrap);
			addImgs(container, (String) item.get("ContentImg"));
			return;
		}

		if (imgs != null && imgs.size() > 0)
			addImgs((LinearLayout) helper.getView(R.id.detail_context_img_wrap), imgs);

		if (imgspath != null && imgspath.size() > 0)
			addImgsPath((LinearLayout) helper.getView(R.id.detail_context_img_wrap), imgspath);
	}

	private void addImgsPath(LinearLayout container, ArrayList<String> imgspath) {
		container.removeAllViews();
		for (String path : imgspath) {
			ImageView img = new ImageView(mListView.getContext());
			Bitmap bm = BitmapFactory.decodeFile(path, new Options());
			img.setImageBitmap(bm);
			img.setTag(bm);
			container.addView(img);
		}
	}

	private void addImgs(LinearLayout container, ArrayList<Integer> resDatas) {
		container.removeAllViews();
		for (int i : resDatas) {
			ImageView img = new ImageView(mListView.getContext());
			img.setOnClickListener(this);
//			img.setImageBitmap(BitmapFactory.decodeResource(mListView.getContext().getResources(), i));
			img.setBackgroundResource(i);
			img.setTag(i);
			LayoutParams p = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			container.addView(img,p);
		}
	}
	
	private void addImgs(LinearLayout container, String url) {
		container.removeAllViews();
			ImageView img = new ImageView(mListView.getContext());
			img.setOnClickListener(this);
			ImageListener listener = ImageLoader.getImageListener(img,
					R.drawable.default_student_quess, R.drawable.default_student_quess);
			loader.get(url , listener);
			img.setTag(url);
			LayoutParams p = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			container.addView(img,p);
	}


	void simulateData() {
		String s = "如图所示，求解方程组的解，请问学长学姐这个问题有几种解答？如果不用线性方程组，还能使用哪些方法呢？" ;
		for (int i = 0; i < 8; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(CONTEXT, s);
			ArrayList<Integer> imgs = new ArrayList<Integer>();
			if(i % 2 == 0){
				imgs.add(R.drawable.bg_dredge_vip);
				imgs.add(R.drawable.bg_game_sso);
			}else {
				imgs.add(R.drawable.bg_live_head_room);
				imgs.add(R.drawable.actionbar_liked);
			}
			
			map.put(QAQIMGRESID, imgs);
			mDatas.add(map);
		}
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		
		Intent i = new Intent();
		i.setClass(mListView.getContext(), ScaleBitmapActivity.class);
		Object o = v.getTag();
		if(o instanceof Integer)
			i.putExtra("res", (Integer)o);
		else if (o instanceof Bitmap)
			i.putExtra("path", (Bitmap)o);
		else
			i.putExtra("url", (String)o);
		
		mListView.getContext().startActivity(i);
	}
	
}
