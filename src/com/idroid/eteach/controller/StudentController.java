package com.idroid.eteach.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.integer;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.idroid.eteach.R;
import com.idroid.eteach.activity.ContactActivity;
import com.idroid.eteach.activity.DetailFragmentItem;
import com.idroid.eteach.adapter.StudentAdapter;
import com.idroid.eteach.adapter.TeacherAdapter;
import com.idroid.eteach.adapter.internal.KJAdapter;
import com.idroid.eteach.controller.base.BaseController;
import com.idroid.eteach.fragment.StudentFragment;
import com.idroid.eteach.fragment.TeacherFragment;
import com.idroid.eteach.fragment.base.FragmentBase;
import com.idroid.eteach.model.bean.Questions;

public class StudentController extends BaseController<FragmentBase> implements OnClickListener, OnItemClickListener {

	private KJAdapter<HashMap<String, Object>> adapter;

	public interface UI {
	}

	/**
	 * ListViewµÄ³õÊ¼»¯
	 */
	public void setListViewData(final AbsListView listView, final int ResId) {
		BmobQuery<Questions> bmobQuery = new BmobQuery<Questions>();
		final ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		bmobQuery.findObjects(getUi().getActivity(), new FindListener<Questions>() {
			@Override
			public void onError(int arg0, String arg1) {
			}

			@Override
			public void onSuccess(List<Questions> lists) {
				for (Questions a : lists) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("Content", a.getContent());
					String path = a.getContentImg();
					int start = path.indexOf("\"");
					int end = path.indexOf("\"", start + 1);

					if (path != null && start > 0 && path.length() > end)
						map.put("ContentImg", path.substring(start + 1, end));
					map.put("AnswerId", a.getAnswerId());
					map.put("CreatedAt", a.getCreatedAt());
					map.put("ObjectId", a.getObjectId());
					map.put("UserName", a.getUserName());
					map.put("User", a.getUser());
					list.add(map);
					findSuccess(listView, list, ResId);
				}
			}
		});

	}

	private void findSuccess(AbsListView listView, ArrayList<HashMap<String, Object>> list, int ResId) {
		adapter = new StudentAdapter(listView, list, ResId);
		adapter.setOnItemClickListener(this);
		adapter.setViewClickListener(R.id.ibtn_student_contact, this);
		((StudentFragment) ui).setListAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(getContext(), ContactActivity.class);
		getContext().startActivity(intent);
	}

	@Override
	public void initialized() {
		Bmob.initialize(getUi().getActivity(), "60ef1a964113e7a753dbabbf6d265087");
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent i = new Intent();
		i.putExtra("item", (HashMap<String, Object>)parent.getItemAtPosition(position));
		i.setClass(getContext(), DetailFragmentItem.class);
		getContext().startActivity(i);
	}

}
