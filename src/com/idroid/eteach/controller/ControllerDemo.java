package com.idroid.eteach.controller;

import android.view.View;

import com.idroid.eteach.controller.base.BaseController;

public class ControllerDemo extends BaseController {

	public interface DemoUi {
		void updateTextView(View v, String result);
	}

	public void doOnclick(View v) {
		String res = "经过处理的Str";
		((DemoUi) ui).updateTextView(v, res);
	}
}
