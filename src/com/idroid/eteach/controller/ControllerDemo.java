package com.idroid.eteach.controller;

import android.view.View;

import com.idroid.eteach.controller.base.BaseController;

public class ControllerDemo extends BaseController<ControllerDemo> {

	public interface DemoUi {
		void updateTextView(View v, String result);
		void refreshed(String result);
	}

//	public ControllerDemo(){
//		attachedUI(this);
//	}
	
	public void doOnclick(View v) {
		String res = "经过处理的Str";
		((DemoUi) ui).updateTextView(v, res);
	}
	
	public void doRefresh() {
		String res = "Refresh Complete!";
		((DemoUi) ui).refreshed(res);
	}

	@Override
	public void initialized() {
		
	}
}
