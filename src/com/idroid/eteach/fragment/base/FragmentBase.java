package com.idroid.eteach.fragment.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.idroid.eteach.controller.base.BaseController;

public abstract class FragmentBase<T extends BaseController> extends Fragment {

	protected T mController;

	@SuppressWarnings("unchecked")
	public FragmentBase() {
		mController = getController();
		if (mController != null)
			mController.attachedUI(this);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mController.initialized();
	}

	/**
	 * 记得写
	 * <p>
	 * {@code controller.attachedUI(ui);}
	 * <p>
	 * {@code controller.initialized();}
	 * 
	 * @return
	 */
	protected abstract T getController();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflateLayout(inflater, container, savedInstanceState);
		initData();
		bindWidget(v);
		return v;
	}

	protected abstract void initData();

	/**
	 * 该布局的视图
	 * 
	 * @param v
	 */
	protected abstract void bindWidget(View v);

	protected abstract View inflateLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

	protected void toast(String string) {
		Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
	}
}
