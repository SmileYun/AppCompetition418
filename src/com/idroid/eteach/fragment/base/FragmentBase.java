package com.idroid.eteach.fragment.base;

import com.idroid.eteach.controller.ControllerDemo;
import com.idroid.eteach.controller.base.BaseController;

import android.support.v4.app.Fragment;

public class FragmentBase extends Fragment{
	protected BaseController mController = new ControllerDemo();
	
	public FragmentBase(){
		mController.attachedUI(this);
	}
		
	protected BaseController getController(){
		return mController;
	}
}
