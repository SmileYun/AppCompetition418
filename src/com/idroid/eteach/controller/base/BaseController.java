package com.idroid.eteach.controller.base;

import android.content.Context;
import android.widget.Toast;

import com.idroid.eteach.fragment.base.FragmentBase;
import com.idroid.eteach.ui.base.ActivityBase;

public abstract class BaseController<U> {
	protected U ui;

	public void attachedUI(U ui) {
		this.ui = ui;
	}

	public abstract void initialized();

	public Context getContext() {
		if (ui instanceof ActivityBase)
			return (Context) ui;
		else if (ui instanceof FragmentBase)
			return ((FragmentBase) ui).getActivity();
		return null;
	}

	public U getUi() {
		return ui;
	}

	protected void toast(String string) {
		Toast.makeText(getContext(), string, Toast.LENGTH_LONG).show();
	}
}
