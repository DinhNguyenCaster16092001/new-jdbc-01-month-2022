package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel>{
	Long save(UserModel userModel);
	UserModel findOneById(Long id);
	UserModel findOneByUserNameAndPassword(String userName, String passWord);
}
