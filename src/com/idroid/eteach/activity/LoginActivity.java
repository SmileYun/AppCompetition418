package com.idroid.eteach.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import cn.bmob.v3.Bmob;

import com.idroid.eteach.controller.LoginActController;
import com.idroid.eteach.controller.base.BaseController;
import com.idroid.eteach.fragment.base.FragmentBase;
import com.idroid.eteach.ui.base.ActivityBase;

public class LoginActivity extends ActivityBase {
	private final int CONTAINER = 0x01;

	private FrameLayout container;
	private FragmentTransaction fragmentMangerTransaction;

	private LoginActController mController;

	public static enum EVENTS {
		REGISTION, FINDPW
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		container = new FrameLayout(this);
		container.setId(CONTAINER);
		FrameLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		setContentView(container, params);

		mController = getController();
		mController.attachedUI(this);
		mController.initialized();
	}

	public void replaceFragment(FragmentBase f) {
		fragmentMangerTransaction = getSupportFragmentManager().beginTransaction();
		fragmentMangerTransaction.replace(CONTAINER, f);
		fragmentMangerTransaction.commit();
	}

	@SuppressWarnings("unchecked")
	public void handleEvent(EVENTS f) {
		mController.handleEvent(f);
	}

	@Override
	protected LoginActController getController() {
		if (mController == null)
			return new LoginActController();
		else
			return mController;
	}
}
