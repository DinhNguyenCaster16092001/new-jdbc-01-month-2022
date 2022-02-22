package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.model.UserModel;

public interface IUserService {
	UserModel save(UserModel userModel);
	UserModel findOneByUserNameAndPassword(String userName, String passWord); 
}
