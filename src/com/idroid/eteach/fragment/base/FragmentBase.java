package com.idroid.eteach.fragment.base;

import com.idroid.eteach.controller.ControllerDemo;
import com.idroid.eteach.controller.base.BaseController;

import android.support.v4.app.Fragment;

public abstract class FragmentBase extends Fragment{
	protected BaseController mController;
	
	@SuppressWarnings("unchecked")
	public FragmentBase(){
		getController().attachedUI(this);
	}
		
	protected abstract <T extends BaseController> T getController();
}
