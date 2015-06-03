package com.idroid.eteach.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.ResetPasswordListener;

import com.idroid.eteach.R;
import com.idroid.eteach.controller.base.BaseController;
import com.idroid.eteach.fragment.base.FragmentBase;

public class LoginFindPwFrag extends FragmentBase<LoginFindPwFragController> implements OnClickListener {

	private EditText email;
	private Button commit;

	@Override
	protected void initData() {

	}

	@Override
	protected void bindWidget(View v) {
		email = (EditText) v.findViewById(R.id.find_pw_email);
		commit = (Button) v.findViewById(R.id.find_pw_btn);
		commit.setOnClickListener(this);
	}

	@Override
	protected View inflateLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_login_find_pw, container, false);
	}

	@Override
	protected LoginFindPwFragController getController() {
		return new LoginFindPwFragController();
	}

	@Override
	public void onClick(View v) {
		mController.findPw();
	}

	public String getEmail() {
		return email.getText().toString().trim();
	}

}

class LoginFindPwFragController extends BaseController<LoginFindPwFrag> {

	@Override
	public void initialized() {

	}

	public void findPw() {
		if (!"".equals(getUi().getEmail())) {
			BmobUser.resetPassword(getContext(), getUi().getEmail(), new ResetPasswordListener() {
				@Override
				public void onSuccess() {
					toast("÷ÿ÷√√‹¬Î«Î«Û≥…π¶£¨«ÎµΩ" + getUi().getEmail() + "” œ‰Ω¯––√‹¬Î÷ÿ÷√≤Ÿ◊˜");
				}

				@Override
				public void onFailure(int code, String e) {
					toast("÷ÿ÷√√‹¬Î ß∞‹:" + e);
				}
			});
		}else {
			toast("«Î ‰»Î” œ‰:" );
		}
	}
}