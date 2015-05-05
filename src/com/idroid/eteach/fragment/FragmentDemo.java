package com.idroid.eteach.fragment;

import com.idroid.eteach.R;
import com.idroid.eteach.controller.ControllerDemo;
import com.idroid.eteach.fragment.base.FragmentBase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentDemo extends FragmentBase implements OnClickListener, ControllerDemo.DemoUi {
	int i;

	public FragmentDemo(int index) {
		i = index;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = getActivity().getLayoutInflater().inflate(R.layout.page, null);
		((TextView) v.findViewById(R.id.page)).setText("page" + i);
		((TextView) v.findViewById(R.id.page)).setOnClickListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {
		((ControllerDemo) getController()).doOnclick(v);
	}

	@Override
	public void updateTextView(View v, String result) {
		((TextView) v).setText(result);
	}

}
