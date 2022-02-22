package com.laptrinhjavaweb.service.impl;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.BcryptPasswordUtil;

public class UserService implements IUserService{

	@Inject
	private IUserDAO iUserDAO;
	
	@Override
	public UserModel save(UserModel userModel) {
		userModel.setCreatedBy("");
		userModel.setPassWord(BcryptPasswordUtil.hashingPassword(userModel.getPassWord()));
		Long id =  iUserDAO.save(userModel);
		return iUserDAO.findOneById(id);
	}

	@Override
	public UserModel findOneByUserNameAndPassword(String userName, String passWord) {
		return iUserDAO.findOneByUserNameAndPassword(userName, passWord);
	}

}
