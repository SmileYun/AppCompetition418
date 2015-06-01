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
 * ͨ��ViewHolder
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
	 * ���ݰ󶨵�ITEM ��ȡVIewHolder
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
	 * ����ID�� ��ȡ��Ӧ����
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
	 * ΪTextView ��������
	 */
	public AdapterViewHolder setText(int id, String text) {
		TextView tv = (TextView) getView(id);
		tv.setText(text);
		return this;
	}

	/**
	 * ΪImageView ������ԴId
	 */
	public AdapterViewHolder setImageResource(int id, int resourceId) {
		ImageView iv = (ImageView) getView(id);
		iv.setImageResource(resourceId);
		return this;
	}

	/**
	 * ΪImageView ����Bitmap
	 */
	public AdapterViewHolder setImageBitmap(int id, Bitmap bm) {
		ImageView iv = (ImageView)getView(id);
		iv.setImageBitmap(bm);
		return this;
	}

	/**
	 * ΪView ����ClickListener
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
