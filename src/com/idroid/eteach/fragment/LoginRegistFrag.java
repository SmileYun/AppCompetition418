package com.idroid.eteach.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

import com.idroid.eteach.R;
import com.idroid.eteach.controller.base.BaseController;
import com.idroid.eteach.fragment.base.FragmentBase;
import com.idroid.eteach.model.bean.User;
import com.idroid.eteach.util.SystemBarConfig;

public class LoginRegistFrag extends FragmentBase<LoginRegistFragController> implements OnClickListener {

	private EditText userName, userEmail, userPw, userPwRep;
	private Button reisgtBtn;

	@Override
	protected void initData() {
		Bmob.initialize(getActivity(), "60ef1a964113e7a753dbabbf6d265087");
	}

	@Override
	protected void bindWidget(View v) {
		reisgtBtn = (Button) v.findViewById(R.id.register_btn);
		userName = (EditText) v.findViewById(R.id.register_user_name);
		userEmail = (EditText) v.findViewById(R.id.register_email);
		userPw = (EditText) v.findViewById(R.id.password_first);
		userPwRep = (EditText) v.findViewById(R.id.password_sec);

		reisgtBtn.setOnClickListener(this);
	}

	public String getName() {
		return userName.getText().toString().trim();
	}

	public String getEmail() {
		return userEmail.getText().toString().trim();
	}

	public String getPw() {
		return userPw.getText().toString().trim();
	}

	public String getPwRep() {
		return userPwRep.getText().toString().trim();
	}

	@Override
	protected View inflateLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		int top = new SystemBarConfig(getActivity()).getStatusBarHeight();
		View view = inflater.inflate(R.layout.fragment_login_register, container, false);
		// view.setPadding(0, top, 0, 0);
		return view;
	}

	@Override
	public void onClick(View v) {
		mController.registUser();
	}

	@Override
	protected LoginRegistFragController getController() {
		return new LoginRegistFragController();
	}
}

class LoginRegistFragController extends BaseController<LoginRegistFrag> {

	@Override
	public void initialized() {

	}

	public void registUser() {
		if ("".equals(getUi().getPw()) || "".equals(getUi().getPwRep()) || "".equals(getUi().getEmail())
				|| "".equals(getUi().getName())) {
			Toast.makeText(getContext(), "请完善注册信息！", Toast.LENGTH_LONG).show();
			return;
		}
		if (!getUi().getPw().equals(getUi().getPwRep())) {
			Toast.makeText(getContext(), "两次密码不一致！", Toast.LENGTH_LONG).show();
			return;
		}
		registerUser();
	}

	private void registerUser() {
		final User user = new User();
		user.setEmail(getUi().getEmail());
		user.setPassword(getUi().getPw());
		user.setIdentify("Student");
		// user.setUserHead(R.drawable.default_icon);
		user.setQuestionTimes(0);
		user.setAnswerTimes(0);
		user.setUsername(getUi().getName());
		user.setLoginTimes(1);
		user.signUp(getContext(), new SaveListener() {
			@Override
			public void onSuccess() {
				Toast.makeText(getContext(), "Ok, welcome " + getUi().getName() + "!", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				Toast.makeText(getContext(), "Sorry, " + arg1, Toast.LENGTH_LONG).show();
			}
		});
	}
}