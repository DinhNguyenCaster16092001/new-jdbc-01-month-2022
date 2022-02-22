package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel>{

	@Override
	public CategoryModel mapRow(ResultSet rs) {
		try {
			Long id = rs.getLong("id");
			String code = rs.getString("code");
			String name = rs.getNString("name");
			CategoryModel model = new CategoryModel(name, code);
			model.setId(id);
			model.setCreatedDate(rs.getTimestamp("createddate"));
			model.setCreatedBy(rs.getString("createdby"));
			if(model.getModifiedBy() != null) {
				model.setModifiedBy(rs.getString("modifiedby"));
				model.setModifiedDate(rs.getTimestamp("modifieddate"));
			}
			return model;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
	}

}
