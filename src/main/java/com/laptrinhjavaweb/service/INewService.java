package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.PageRequest;

public interface INewService {
	List<NewModel> findByCategoryId(Long id);
	NewModel save(NewModel newModel);
	NewModel update(NewModel updateNewModel);
	void delete(Long[] ids); 
	List<NewModel> findAll(PageRequest pageRequest);
	int count();
	NewModel findOneById(Long id);
}
