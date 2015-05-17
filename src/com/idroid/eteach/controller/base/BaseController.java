package com.idroid.eteach.controller.base;

import android.app.Activity;

public class BaseController<U> {
	protected U ui;
	
	public void attachedUI(U ui){
		this.ui = ui;
	}
}
