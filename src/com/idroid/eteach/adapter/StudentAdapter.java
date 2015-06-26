package com.idroid.eteach.adapter;

import java.util.Collection;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import com.idroid.eteach.R;
import com.idroid.eteach.adapter.internal.AdapterViewHolder;
import com.idroid.eteach.adapter.internal.KJAdapter;
import com.idroid.eteach.model.bean.Answers;
import com.idroid.eteach.model.bean.User;

public class StudentAdapter extends KJAdapter<HashMap<String, Object>> {

	private RequestQueue mQueue;
	private ImageLoader loader;

	public StudentAdapter(AbsListView listView, Collection<HashMap<String, Object>> datas, int itemLayoutId) {
		super(listView, datas, itemLayoutId);
		mQueue = Volley.newRequestQueue(listView.getContext());
		loader = new ImageLoader(mQueue, new BitmapCache());
	}

	@Override
	public void convert(AdapterViewHolder helper, HashMap<String, Object> item, boolean isScrolling) {
		String Content = (String) item.get("Content");
		String ContentImg = (String) item.get("ContentImg");
		Answers answerId = (Answers) item.get("AnswerId");
		item.get("CreatedAt");
		item.get("ObjectId");
		String UserName = (String) item.get("UserName");
		User user = (User) item.get("User");

		if (ContentImg != null) {
			ImageListener listener = ImageLoader.getImageListener((ImageView) helper.getView(R.id.iiv_student_ques_icon),
					R.drawable.default_student_quess, R.drawable.default_student_quess);
			loader.get(ContentImg, listener);
		}
		helper.setText(R.id.itv_student_name, UserName);
		helper.setText(R.id.itv_student_answ, "12回复");
		helper.setText(R.id.itv_student_date, "2015-05-24");

		helper.setText(R.id.itv_student_ques_text, Content);
		helper.setText(R.id.itv_student_class, "数学");
		helper.setText(R.id.itv_student_addr, "重庆市南岸区黄桷垭");
	}

	public static class BitmapCache implements ImageCache {
		private LruCache<String, Bitmap> mCache;

		public BitmapCache() {
			int maxSize = 10 * 1024 * 1024;
			mCache = new LruCache<String, Bitmap>(maxSize) {
				@Override
				protected int sizeOf(String key, Bitmap value) {
					return value.getRowBytes() * value.getHeight();
				}
			};
		}

		@Override
		public Bitmap getBitmap(String url) {
			return mCache.get(url);
		}

		@Override
		public void putBitmap(String url, Bitmap bitmap) {
			mCache.put(url, bitmap);
		}
	}
}
