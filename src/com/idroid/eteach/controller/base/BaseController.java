package com.idroid.eteach.controller.base;

import android.content.Context;

public class BaseController<U> {
	protected U ui;
	
	public void attachedUI(U ui){
		this.ui = ui;
	}
	
	public Context getContext(){
		if(ui instanceof Context)
			return (Context) ui;
		return null;
	}
}
