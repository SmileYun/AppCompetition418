package com.idroid.eteach.controller.base;

public class BaseController<U> {
	protected U ui;
	
	public void attachedUI(U ui){
		this.ui = ui;
	}
}
