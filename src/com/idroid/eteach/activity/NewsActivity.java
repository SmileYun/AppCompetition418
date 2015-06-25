package com.idroid.eteach.activity;

import com.idroid.eteach.R;
import com.idroid.eteach.ui.base.ActivityBase;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class NewsActivity extends ActivityBase {

	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);

		init();
	}

	/**
	 * ��ʼ������
	 */
	private void init() {
		webView = (WebView) this.findViewById(R.id.webView);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://cul.qq.com/a/20150601/009270.htm");

		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
	}

}
