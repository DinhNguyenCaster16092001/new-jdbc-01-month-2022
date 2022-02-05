package com.laptrinhjavaweb.model;

import java.sql.Timestamp;

public abstract class AbstractModel {
	private Long id;
	private Timestamp createdDate;
	private String createdBy;
	private Timestamp modifiedDate;
	private String modifiedBy;
	
	
	public AbstractModel(Timestamp createdDate, String createdBy) {
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}


	public Long getId() {
		return id;
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
	
	
}
