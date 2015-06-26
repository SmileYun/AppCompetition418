package com.idroid.eteach.model.bean;


import java.util.Date;

import cn.bmob.v3.BmobObject;

public class Questions extends BmobObject {

	User user;

	Answers answerId;

	String Content;

	String ContentImg;

	String UserName;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Answers getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Answers answerId) {
		this.answerId = answerId;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getContentImg() {
		return ContentImg;
	}

	public void setContentImg(String contentImg) {
		ContentImg = contentImg;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	@Override
	public String toString() {
		return "Questions [user=" + user + ", answerId=" + answerId + ", Content=" + Content + ", ContentImg=" + ContentImg
				+ ", UserName=" + UserName + "]";
	}

}
