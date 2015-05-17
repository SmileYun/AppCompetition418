package com.idroid.eteach.fragment.base;

import com.idroid.eteach.controller.ControllerDemo;
import com.idroid.eteach.controller.base.BaseController;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class FragmentBase extends Fragment {
	protected BaseController mController = new ControllerDemo();

	public FragmentBase() {
		mController.attachedUI(this);
	}

	protected BaseController getController() {
		return mController;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflateView(inflater, container, savedInstanceState);
		initData();
		initWidget(v);
		return v;
	}

	protected abstract void initData();

	protected abstract void initWidget(View v);

	protected abstract View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

}
