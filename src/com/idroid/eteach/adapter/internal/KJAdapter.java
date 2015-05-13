package com.idroid.eteach.adapter.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;

/**
 * 适配器基类
 * 
 * @author SmileYun
 * @param <T>
 */
public abstract class KJAdapter<T> extends BaseAdapter implements OnScrollListener {

	protected LayoutInflater mInflater;
	protected Collection<T> mDatas;
	protected final int mItemLayoutId;
	protected AbsListView mListView;
	protected boolean isScrolling;

	private AbsListView.OnScrollListener listener;

	public KJAdapter(AbsListView listView, Collection<T> datas, int itemLayoutId) {
		mListView = listView;
		mItemLayoutId = itemLayoutId;
		mInflater = LayoutInflater.from(listView.getContext());
		if (datas == null)
			mDatas = new ArrayList<T>(0);
		mListView.setOnScrollListener(this);
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
		final AdapterViewHolder viewHolder = getViewHolder(position, convertView, parent);
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

	public void addOnScrollListener(AbsListView.OnScrollListener l) {
		this.listener = l;
	}
}
