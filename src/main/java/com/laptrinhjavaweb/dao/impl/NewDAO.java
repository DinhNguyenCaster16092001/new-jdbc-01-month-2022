package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.PageRequest;

public class NewDAO extends GenericDAOImpl<NewModel> implements INewDAO {

	@Override
	public List<NewModel> findAll(PageRequest pageRequest) {
		StringBuilder sql = new StringBuilder("SELECT * FROM news ");
		if(pageRequest.getSorter().getSortName() != null && pageRequest.getSorter().getSortBy() != null) {
			sql.append(" ORDER BY "+pageRequest.getSorter().getSortName()+" " +pageRequest.getSorter().getSortBy());
		}
		if(pageRequest.getOffset() != null && pageRequest.getPageItem()!=null) {
			sql.append(" LIMIT "+pageRequest.getOffset()+", "+pageRequest.getPageItem());
		}
		return query(sql.toString(), new NewMapper(), pageRequest);
	}

	@Override
	public Long save(NewModel newModel) {
		String sql = "INSERT INTO news (title, content, categoryid, thumbnail, shortdescription, createdby) VALUES (?,?,?,?,?,?)";
		return insert(sql, newModel.getTitle(), newModel.getContent(), newModel.getCategoryId(), 
							newModel.getThumbnail(), newModel.getShortDescription(), newModel.getCreatedBy());
	}

	@Override
	public NewModel findOneById(Long id) {
		String sql = "SELECT * FROM news WHERE id = ?";
		List<NewModel> result = query(sql, new NewMapper(), id);
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public void update(NewModel updateNewModel) {
		StringBuilder sql = new StringBuilder("UPDATE news SET ");
		sql.append("title = ?, thumbnail = ?, shortdescription = ?, ");
		sql.append("content= ?, categoryid= ?, modifieddate=?, modifiedby=?");
		sql.append("WHERE id = ?");
		update(sql.toString(), updateNewModel.getTitle(), updateNewModel.getThumbnail(),
								updateNewModel.getShortDescription(),updateNewModel.getContent(),
					           updateNewModel.getCategoryId(),updateNewModel.getModifiedDate(),
					           updateNewModel.getModifiedBy(),updateNewModel.getId());
					           
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql, id);
	}

	@Override
	public int count() {
		String sql = "SELECT COUNT(*) FROM news";
		return count(sql);
	}

}
