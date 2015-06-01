package com.idroid.eteach.adapter.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;

/**
 * 适配器基类
 * 
 * @author SmileYun
 * @param <T>
 */
public abstract class KJAdapter<T> extends BaseAdapter implements OnScrollListener, OnClickListener, OnItemSelectedListener,
		OnItemLongClickListener, OnItemClickListener {

	protected LayoutInflater mInflater;
	protected Collection<T> mDatas;
	protected final int mItemLayoutId;
	protected AbsListView mListView;
	protected boolean isScrolling;
	protected AdapterViewHolder viewHolder;

	private AbsListView.OnScrollListener listener;
	private OnClickListener viewListener;
	private OnItemClickListener itemClickListener;
	private OnItemLongClickListener itemLongClickListener;
	private OnItemSelectedListener itemSelectedListener;

	public KJAdapter(AbsListView listView, Collection<T> datas, int itemLayoutId) {
		mListView = listView;
		mItemLayoutId = itemLayoutId;
		mInflater = LayoutInflater.from(listView.getContext());
		if (datas == null)
			mDatas = new ArrayList<T>(0);
		else
			mDatas = datas;
		mListView.setOnScrollListener(this);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
		mListView.setOnItemSelectedListener(this);
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	public void refresh(Collection<T> datas) {
		if (datas == null)
			datas = new ArrayList<T>(0);
		this.mDatas = datas;
		notifyDataSetChanged();
	}

	@Override
	public T getItem(int position) {
		if (mDatas instanceof List)
			return ((List<T>) mDatas).get(position);
		else if (mDatas instanceof Set)
			return new ArrayList<T>(mDatas).get(position);
		else
			return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		viewHolder = getViewHolder(position, convertView, parent);

		convert(viewHolder, getItem(position), isScrolling);

		return viewHolder.getConvertView();
	}

	public abstract void convert(AdapterViewHolder helper, T item, boolean isScrolling);

	private AdapterViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
		return AdapterViewHolder.get(convertView, parent, mItemLayoutId, position);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// 设置是否滚动的状态
		if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
			isScrolling = false;
			this.notifyDataSetChanged();
		} else {
			isScrolling = true;
		}
		if (listener != null) {
			listener.onScrollStateChanged(view, scrollState);
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		if (listener != null) {
			listener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
		}
	}

	public void setOnScrollListener(AbsListView.OnScrollListener listener) {
		this.listener = listener;
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		itemClickListener = listener;
	}

	public void setOnItemLongClickListener(OnItemLongClickListener listener) {
		itemLongClickListener = listener;
	}

	public void setOnItemSelectedListener(OnItemSelectedListener listener) {
		itemSelectedListener = listener;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (itemClickListener != null)
			itemClickListener.onItemClick(parent, view, position, id);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		if (itemSelectedListener != null)
			itemSelectedListener.onItemSelected(parent, view, position, id);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		if (itemLongClickListener != null)
			itemLongClickListener.onItemLongClick(parent, view, position, id);
		return false;
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		if (itemSelectedListener != null)
			itemSelectedListener.onNothingSelected(parent);
	}
	

	@Override
	public void onClick(View v) {
		if (viewListener != null)
			viewListener.onClick(v);
	}
	
	/**
	 * 为View 设置ClickListener
	 */
	public void setViewClickListener(int id, OnClickListener listener) {
		View iv = viewHolder.getView(id);
		iv.setOnClickListener(this);
	}


	public void setListener(AbsListView.OnScrollListener listener) {
		this.listener = listener;
	}


	public void setItemClickListener(OnItemClickListener itemClickListener) {
		this.itemClickListener = itemClickListener;
	}


	public void setItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
		this.itemLongClickListener = itemLongClickListener;
	}

	public void setItemSelectedListener(OnItemSelectedListener itemSelectedListener) {
		this.itemSelectedListener = itemSelectedListener;
	}
}
