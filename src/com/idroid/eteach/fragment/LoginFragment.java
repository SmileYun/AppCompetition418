package com.idroid.eteach.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

import com.idroid.eteach.R;
import com.idroid.eteach.activity.LoginActivity;
import com.idroid.eteach.activity.LoginActivity.EVENTS;
import com.idroid.eteach.controller.base.BaseController;
import com.idroid.eteach.fragment.base.FragmentBase;

public class LoginFragment extends FragmentBase<LoginFragmentCotroller> implements OnClickListener {

	private Button login;
	private EditText userName, userPW;
	private TextView registUser, findPw;
	private LoginActivity parent;

	@Override
	protected void initData() {
	}

	@Override
	protected void bindWidget(View v) {
		login = (Button) v.findViewById(R.id.login_btn);
		userName = (EditText) v.findViewById(R.id.login_username_edt);
		userPW = (EditText) v.findViewById(R.id.login_userpassword_edt);
		registUser = (TextView) v.findViewById(R.id.login_registration);
		findPw = (TextView) v.findViewById(R.id.login_forget_pw);

		login.setOnClickListener(this);
		registUser.setOnClickListener(this);
		findPw.setOnClickListener(this);
	}

	@Override
	protected View inflateLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_login, container, false);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_btn:
			mController.login();
			break;

		case R.id.login_registration:
			if (getActivity() instanceof LoginActivity)
				parent = (LoginActivity) getActivity();
			parent.handleEvent(EVENTS.REGISTION);
			break;
		case R.id.login_forget_pw:
			if (getActivity() instanceof LoginActivity)
				parent = (LoginActivity) getActivity();
			parent.handleEvent(EVENTS.FINDPW);
			break;
		}
	}

	public String getUserName() {
		return userName.getText().toString().trim();
	}

	public String getUserPw() {
		return userPW.getText().toString().trim();
	}

	@Override
	protected LoginFragmentCotroller getController() {
		return new LoginFragmentCotroller();
	}

}

class LoginFragmentCotroller extends BaseController<LoginFragment> {

	@Override
	public void initialized() {

	}

	public void login() {
		final BmobUser user = new BmobUser();
		user.setUsername(getUi().getUserName());
		user.setPassword(getUi().getUserPw());
		user.login(getContext(), new SaveListener() {

			@Override
			public void onSuccess() {
				Toast.makeText(getContext(), "Ok, welcome " + getUi().getUserName() + "!", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onFailure(int code, String msg) {
				Toast.makeText(getContext(), "Sorry, " + msg, Toast.LENGTH_LONG).show();
			}
		});
	}

}