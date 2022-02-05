package com.laptrinhjavaweb.model;

import java.sql.Timestamp;

public class UserModel extends AbstractModel{
	
	private String fullName;
	private String userName;
	private String passWord;
	private int status;
	private long roleId;

	public UserModel(Timestamp createdDate, String createdBy, String fullName, String userName, String passWord,
			int status, long roleId) {
		super(createdDate, createdBy);
		this.fullName = fullName;
		this.userName = userName;
		this.passWord = passWord;
		this.status = status;
		this.roleId = roleId;
	}
	

	
	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassWord() {
		return passWord;
	}


	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}


}
