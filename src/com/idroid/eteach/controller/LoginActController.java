package com.idroid.eteach.controller;

import com.idroid.eteach.activity.LoginActivity;
import com.idroid.eteach.controller.base.BaseController;
import com.idroid.eteach.fragment.LoginFindPwFrag;
import com.idroid.eteach.fragment.LoginFragment;
import com.idroid.eteach.fragment.LoginRegistFrag;

public class LoginActController extends BaseController<LoginActivity> {

	@Override
	public void initialized() {
		getUi().replaceFragment(new LoginFragment());
	}

	public void handleEvent(LoginActivity.EVENTS f) {
		System.out.println(getUi());
		if (f == LoginActivity.EVENTS.REGISTION)
			getUi().replaceFragment(new LoginRegistFrag());
		else if (f == LoginActivity.EVENTS.FINDPW)
			getUi().replaceFragment(new LoginFindPwFrag());
	}
	
}
