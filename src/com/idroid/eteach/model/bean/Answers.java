package com.idroid.eteach.model.bean;

import android.graphics.Bitmap;
import android.provider.ContactsContract.Contacts.Data;
import cn.bmob.v3.BmobObject;

public class Answers extends BmobObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6948392924821061955L;
	String userId;
	Bitmap contentImg;
	String content;
	Data cratedAt;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Bitmap getContentImg() {
		return contentImg;
	}

	public void setContentImg(Bitmap contentImg) {
		this.contentImg = contentImg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
