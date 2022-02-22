package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.mapper.CategoryMapper;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewModel;

public class CategoryDAO extends GenericDAOImpl<CategoryModel> implements ICategoryDAO{
	
	@Override
	public List<CategoryModel> findAll() {
		String sql = "SELECT * FROM category";
		return query(sql, new CategoryMapper()); 
	}

	@Override
	public CategoryModel findOneById(Long id) {
		String sql = "SELECT * FROM category WHERE id = ?";
		List<CategoryModel> result = query(sql, new CategoryMapper(), id);
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		String sql = "SELECT * FROM category WHERE code = ?";
		List<CategoryModel> result = query(sql, new CategoryMapper(), code);
		return result.isEmpty() ? null : result.get(0);
	}

	
	
	
}
