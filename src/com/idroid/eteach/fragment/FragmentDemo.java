package com.idroid.eteach.fragment;

import com.idroid.eteach.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentDemo extends Fragment {
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
		return v;
	}

}
