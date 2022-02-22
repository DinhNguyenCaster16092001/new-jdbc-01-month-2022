package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.mapper.UserMapper;
import com.laptrinhjavaweb.model.UserModel;

public class UserDAO extends GenericDAOImpl<UserModel> implements IUserDAO{


	@Override
	public Long save(UserModel userModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO user (username, password, fullname, status,roleid, createdby) ");
		              sql.append("VALUES (?,?,?,?,?,?)");
		return insert(sql.toString(), userModel.getUserName(), userModel.getPassWord(), 
						userModel.getFullName(), userModel.getStatus(), userModel.getRoleId(), userModel.getCreatedBy());
	}

	@Override
	public UserModel findOneById(Long id) {
		String sql = "SELECT * FROM user WHERE id = ?";
		List<UserModel> list = query(sql, new UserMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public UserModel findOneByUserNameAndPassword(String userName, String passWord) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user u JOIN role r ON u.roleid = r.id ");
		                    sql.append("WHERE username = ? AND  password = ? AND status = 1");
		List<UserModel> list = query(sql.toString(), new UserMapper(), userName, passWord);
		return list.isEmpty() ? null : list.get(0);
	}

}
