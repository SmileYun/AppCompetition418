package com.idroid.eteach.model.bean;

import java.sql.Blob;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Volunteer extends BmobObject{

	private String realName;
	private String cell;
	private String school;
	private BmobFile collegeCard;
	
	public BmobFile getCollegeCard() {
		return collegeCard;
	}
	public void setCollegeCard(BmobFile collegeCard) {
		this.collegeCard = collegeCard;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}	
}
