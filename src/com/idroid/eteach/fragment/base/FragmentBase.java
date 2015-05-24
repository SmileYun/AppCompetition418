package com.idroid.eteach.fragment.base;

import com.idroid.eteach.controller.ControllerDemo;
import com.idroid.eteach.controller.base.BaseController;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class FragmentBase extends Fragment{
	protected BaseController mController;
	
	@SuppressWarnings("unchecked")
	public FragmentBase(){
		getController().attachedUI(this);
	}
		
	protected abstract <T extends BaseController> T getController();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v =  inflateLayout(inflater, container, savedInstanceState);
		initData();
		bindWidget(v);
		return v;
	}
	
	protected abstract void initData();
	
	protected abstract void bindWidget(View v);
	
	protected abstract View inflateLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}
