package com.idroid.eteach.controller.base;

import com.idroid.eteach.fragment.base.FragmentBase;
import com.idroid.eteach.ui.base.ActivityBase;

import android.content.Context;

public abstract class BaseController<U> {
	protected U ui;
	
	public void attachedUI(U ui){
		this.ui = ui;
	}
	
	public abstract void initialized();
	
	public Context getContext(){
		if(ui instanceof ActivityBase)
			return (Context) ui;
		else if(ui instanceof FragmentBase)
			return ((FragmentBase) ui).getActivity();
		return null;
	}
}
