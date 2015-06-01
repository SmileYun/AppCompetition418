package com.idroid.eteach.adapter.internal;

import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 通用ViewHolder
 */
public class AdapterViewHolder {
	private final SparseArray<View> mViews;
	private View mConvertView;
	private int mPosition;

	private AdapterViewHolder(ViewGroup parent, int resId, int position) {
		mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
		this.mPosition = position;
		mConvertView.setTag(this);
	}

	/**
	 * 根据绑定的ITEM 获取VIewHolder
	 * 
	 * @param contentView
	 * @param parent
	 * @param layoutId
	 * @param position
	 * @return
	 */
	public static AdapterViewHolder get(View convertView, ViewGroup parent, int layoutId, int position) {
		if (convertView == null)
			return new AdapterViewHolder(parent, layoutId, position);
		else
			return (AdapterViewHolder) convertView.getTag();
	}

	public SparseArray<View> getAllViews() {
		return mViews;
	}

	public View getConvertView() {
		return mConvertView;
	}

	/**
	 * 根据ID， 获取对应对象
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int id) {
		View view = mViews.get(id);
		if (view == null) {
			view = mConvertView.findViewById(id);
			mViews.put(id, view);
		}
		return (T) view;
	}

	/**
	 * 为TextView 设置内容
	 */
	public AdapterViewHolder setText(int id, String text) {
		TextView tv = (TextView) getView(id);
		tv.setText(text);
		return this;
	}

	/**
	 * 为ImageView 设置资源Id
	 */
	public AdapterViewHolder setImageResource(int id, int resourceId) {
		ImageView iv = (ImageView) getView(id);
		iv.setImageResource(resourceId);
		return this;
	}

	/**
	 * 为ImageView 设置Bitmap
	 */
	public AdapterViewHolder setImageBitmap(int id, Bitmap bm) {
		ImageView iv = (ImageView)getView(id);
		iv.setImageBitmap(bm);
		return this;
	}

	/**
	 * 为View 设置ClickListener
	 */
	public AdapterViewHolder setViewClickListener(int id, OnClickListener listener) {
		View iv = getView(id);
		iv.setOnClickListener(listener);
		return this;
	}
	
	public int getPosition() {
		return mPosition;
	}
}
