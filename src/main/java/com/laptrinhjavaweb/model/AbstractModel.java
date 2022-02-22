package com.laptrinhjavaweb.model;

import java.sql.Timestamp;
import java.util.List;

public abstract class AbstractModel<T> {
	private Long id;
	private Timestamp createdDate;
	private String createdBy;
	private Timestamp modifiedDate;
	private String modifiedBy;
	private Long[] ids;
	private List<T> list;
	private int page;
	private int pageItem;
	private int totalPage;
	private int totalItem;
	private String sortBy;
	private String sortName;
	private String type;
	
	public Long getId() {
		return id;
	}
	

	public void setId(Long id) {
		this.id = id;
	}


	public Timestamp getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Timestamp getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public Long[] getIds() {
		return ids;
	}


	public void setIds(Long[] ids) {
		this.ids = ids;
	}


	public List<T> getList() {
		return list;
	}


	public void setList(List<T> list) {
		this.list = list;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getPageItem() {
		return pageItem;
	}


	public void setPageItem(int pageItem) {
		this.pageItem = pageItem;
	}


	public int getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(int totalItem, int pageItem) {
		this.totalPage = (int)Math.ceil((double) this.totalItem / this.pageItem);
	}


	public int getTotalItem() {
		return totalItem;
	}


	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}


	public String getSortBy() {
		return sortBy;
	}


	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}


	public String getSortName() {
		return sortName;
	}


	public void setSortName(String sortName) {
		this.sortName = sortName;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	
	
	
	
 
}
