package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.relation.Role;

import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserMapper implements RowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet rs) {
		try {
			Long id = rs.getLong("id");
			String userName = rs.getString("username");
			String password = rs.getString("password");
			String fullName = rs.getString("fullname");
			Integer status = rs.getInt("status");
			Long roleId = rs.getLong("roleid");
			RoleModel role = new RoleModel();
			UserModel model = new UserModel(fullName, userName, password, status, roleId);
			model.setId(id);
			model.setCreatedDate(rs.getTimestamp("createddate"));
			model.setCreatedBy(rs.getString("createdby"));
			if(model.getModifiedBy() != null && model.getModifiedDate() != null) {
				model.setModifiedBy(rs.getString("modifiedby"));
				model.setModifiedDate(rs.getTimestamp("modifieddate"));
			}
				try {
					role.setCode(rs.getString("code"));
					role.setName(rs.getString("name"));
					model.setRole(role);
				}catch (SQLException e) {
					e.printStackTrace();
				}
			return model;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
