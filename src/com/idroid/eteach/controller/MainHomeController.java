package com.idroid.eteach.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.idroid.eteach.R;
import com.idroid.eteach.activity.DetailFragmentItem;
import com.idroid.eteach.activity.NewsActivity;
import com.idroid.eteach.adapter.MainHomeListViewAdapter;
import com.idroid.eteach.adapter.internal.KJAdapter;
import com.idroid.eteach.controller.base.BaseController;
import com.idroid.eteach.fragment.MainHomeFragment;
import com.idroid.eteach.fragment.base.FragmentBase;

public class MainHomeController extends BaseController<MainHomeFragment>
		implements OnItemClickListener {

	public interface UI {

	}

	private int[] imageLists = { R.drawable.bg_dredge_vip,
			R.drawable.bg_game_sso, R.drawable.bg_live_head_room };
	private KJAdapter<HashMap<String, Object>> adapter;

	// 新闻页面初始化数据
	// 标题
	private int[] newsTitle = { R.string.news_title_0, R.string.news_title_1,
			R.string.news_title_2, R.string.news_title_3,
			R.string.news_title_4, R.string.news_title_5 };
	// 摘要
	private int[] newsDigest = { R.string.news_digest_0,
			R.string.news_digest_1, R.string.news_digest_2,
			R.string.news_digest_3, R.string.news_digest_4,
			R.string.news_digest_5 };
	// 详细内容链接
	private String[] newsUrl = {
			"http://mp.weixin.qq.com/s?__biz=MzA4MzQxMTAwNA==&mid=207200530&idx=5&sn=ef071fd35e86e8f3f41b74115d14892f&key=af154fdc40fed003b04a6e63cd1d1e5695effdd6b762d4e8f4acc6bf82dc5a439710b782195e760224c644729213a9af&ascene=7&uin=MjYwMjA0NDUwMA%3D%3D&devicetype=android-19&version=26020034&nettype=WIFI&pass_ticket=vrpHHZtqo2kibosAh9dFz19xjrd%2FA5KE8Txx4vwZlKwECQ7oJKamwxQvL3SDibQz",
			"http://mp.weixin.qq.com/s?__biz=MjM5ODM4NTI4MA==&mid=238193358&idx=2&sn=02e629c4ce274231e7f51aed61ddf01a#rd",
			"http://mp.weixin.qq.com/s?__biz=MzA4MzQxMTAwNA==&mid=207165078&idx=3&sn=c45f2c371082bc8fe7ee0a98b37ce3e2#rd",
			"http://mp.weixin.qq.com/s?__biz=MzA4MzQxMTAwNA==&mid=206949079&idx=2&sn=a98a1d198e4f8fbca3421b7c0b3fd861&scene=4#wechat_redirect",
			"http://mp.weixin.qq.com/s?__biz=MzA4MzQxMTAwNA==&mid=206868111&idx=3&sn=d5b8600a8124dfbbf5fa0a859897b8d1&scene=4#wechat_redirect",
			"http://mp.weixin.qq.com/s?__biz=MzA4MzQxMTAwNA==&mid=206697404&idx=2&sn=296bb7789a68fc614a603027530d9408&scene=4#wechat_redirect" };

	// 图片资源
	private int[] icoId = { R.drawable.icon_0, R.drawable.icon_1,
			R.drawable.icon_2, R.drawable.icon_3, R.drawable.icon_4,
			R.drawable.icon_5 };

	public void initialized() {
		getUi().setViewPageAdapter(new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return imageLists.length;
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView((View) object);
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View v = new ImageView(ui.getActivity());
				v.setBackgroundResource(imageLists[position]);
				container.addView(v);
				return v;
			}
		});
	}

	public void setListViewData(AbsListView listView, int resId) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 6; i++) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.clear();
			m.put("title", newsTitle[i]);
			m.put("digest", newsDigest[i]);
			m.put("iconId", icoId[i]);
			list.add(m);
		}

		adapter = new MainHomeListViewAdapter(listView, list, resId);
		adapter.setItemClickListener(this);
		((MainHomeFragment) ui).setListAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent i = new Intent(getContext(), NewsActivity.class);
		i.putExtra("newsUrl", newsUrl[position]);
		getContext().startActivity(i);
	}

}
