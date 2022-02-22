package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.service.INewService;

public class NewService implements INewService{

	@Inject
	private INewDAO iNewDAO;
	
	@Inject ICategoryDAO iCategoryDAO;	
	@Override
	public List<NewModel> findByCategoryId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NewModel save(NewModel newModel) {
		CategoryModel category = iCategoryDAO.findOneByCode(newModel.getCategoryCode());
		newModel.setCategoryId(category.getId());
		Long newId = iNewDAO.save(newModel);
		System.out.println("The Id Of News: " + newId);
		return iNewDAO.findOneById(newId);
	}

	@Override
	public NewModel update(NewModel updateNewModel) {
		NewModel oldNew = iNewDAO.findOneById(updateNewModel.getId());
		CategoryModel category = iCategoryDAO.findOneByCode(updateNewModel.getCategoryCode());
		updateNewModel.setCategoryId(category.getId());
		updateNewModel.setCreatedBy(oldNew.getCreatedBy());
		updateNewModel.setCreatedDate(oldNew.getCreatedDate());
		updateNewModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		iNewDAO.update(updateNewModel);
		return iNewDAO.findOneById(updateNewModel.getId());
	}
	

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			iNewDAO.delete(id);
		}
	}

	@Override
	public List<NewModel> findAll(PageRequest pageRequest) {
		return iNewDAO.findAll(pageRequest);
	}

	@Override
	public int count() {
		return iNewDAO.count();
	}

	@Override
	public NewModel findOneById(Long id) {
		NewModel newModel = iNewDAO.findOneById(id);
		CategoryModel categoryModel = iCategoryDAO.findOneById(newModel.getCategoryId());
		newModel.setCategoryCode(categoryModel.getCode());
		return newModel;
	}

	


}
