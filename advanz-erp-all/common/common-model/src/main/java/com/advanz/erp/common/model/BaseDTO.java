package com.advanz.erp.common.model;

import java.io.Serializable;
import java.util.Date;

public class BaseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8487615917188210556L;
	private Integer createdUserId;
	private Integer modifiedUserId;
	private Date creationDate;
	public Integer getModifiedUserId() {
		return modifiedUserId;
	}
	public void setModifiedUserId(Integer modifiedUserId) {
		this.modifiedUserId = modifiedUserId;
	}
	public Integer getCreatedUserId() {
		return createdUserId;
	}
	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
