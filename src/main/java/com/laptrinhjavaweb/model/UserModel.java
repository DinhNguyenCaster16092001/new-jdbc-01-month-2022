package com.laptrinhjavaweb.model;

public class UserModel extends AbstractModel<UserModel>{
	
	private String fullName;
	private String userName;
	private String passWord;
	private Integer status;
	private Long roleId;
	private RoleModel role;

	public UserModel() {
		
	}
	
	public UserModel( String fullName, String userName, String passWord,
			Integer status, Long roleId) {
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


	public void setStatus(Integer status) {
		this.status = status;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}

	
	
	
}
