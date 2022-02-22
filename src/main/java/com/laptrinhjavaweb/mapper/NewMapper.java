package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewModel;

public class NewMapper implements RowMapper<NewModel>{

	@Override
	public NewModel mapRow(ResultSet rs) {
		try {
			Long id = rs.getLong("id");
			String title = rs.getString("title");
			String thumbnail = rs.getNString("thumbnail");
			String shortDescription = rs.getString("shortdescription");
			String content = rs.getString("content");
			Long categoryId = rs.getLong("categoryid");
			NewModel model = new NewModel(title, thumbnail, shortDescription, content, categoryId);
			model.setId(id);
			model.setCreatedDate(rs.getTimestamp("createddate"));
			model.setCreatedBy(rs.getString("createdby"));
			if(model.getModifiedBy() != null && model.getModifiedDate() != null) {
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
