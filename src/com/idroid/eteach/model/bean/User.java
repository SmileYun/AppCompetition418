package com.idroid.eteach.model.bean;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4651719957690039352L;
	private String identify;
	private Number questionTimes;
	private Number answerTimes;
	private Number loginTimes;
	//private BmobFile userHead;

	public String getIdentify() {
		return identify;
	}

//	public BmobFile getUserHead() {
//		return userHead;
//	}
//
//	public void setUserHead(BmobFile userHead) {
//		this.userHead = userHead;
//	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public Number getQuestionTimes() {
		return questionTimes;
	}

	public void setQuestionTimes(Number questionTimes) {
		this.questionTimes = questionTimes;
	}

	public Number getAnswerTimes() {
		return answerTimes;
	}

	public void setAnswerTimes(Number answerTimes) {
		this.answerTimes = answerTimes;
	}

	public Number getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Number loginTimes) {
		this.loginTimes = loginTimes;
	}

}
