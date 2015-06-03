package com.idroid.eteach.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idroid.eteach.R;
import com.idroid.eteach.controller.base.BaseController;
import com.idroid.eteach.fragment.base.FragmentBase;

public class LoginFindPwFrag extends FragmentBase<LoginFindPwFragController>{


	@Override
	protected void initData() {
		
	}

	@Override
	protected void bindWidget(View v) {
		
	}

	@Override
	protected View inflateLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_login_find_pw, container, false);
	}

	@Override
	protected LoginFindPwFragController getController() {
		return new LoginFindPwFragController();
	}

	
}
class LoginFindPwFragController extends BaseController<LoginFindPwFrag>{

	@Override
	public void initialized() {
		
	}
	
}