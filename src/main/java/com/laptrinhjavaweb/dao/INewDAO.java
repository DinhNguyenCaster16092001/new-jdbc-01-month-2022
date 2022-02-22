package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.PageRequest;

public interface INewDAO extends GenericDAO<NewModel>{
	List<NewModel> findAll(PageRequest pageRequest);
	Long save(NewModel newModel);
	NewModel findOneById(Long id);
	void update(NewModel updateNewModel);
	void delete(Long id);
	int count();
}
