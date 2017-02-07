/**
 * 
 */
package com.advanz.erp.common.entity.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Parikshit Patel
 *
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public  abstract  class  BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7678560902814855880L;

	/**
	 * 
	 */

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_date",updatable=false)
	private Date creationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;
	
	@Column(name="created_user_id",updatable=false)
	private Integer createdUserId;

	@Column(name="modified_user_id")
	private Integer modifiedUserId;
	
	@Column(name = "deleted_flag")
	private Boolean deletedFlag=false; 
	
	public Boolean getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	/**
	 * 
	 */
	public BaseEntity() {
		super();
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}
	
	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Integer getCreatedUserId() {
		return createdUserId;
	}

	public void setModifiedUserId(Integer modifiedUserId) {
		this.modifiedUserId = modifiedUserId;
	}

	public Integer getModifiedUserId() {
		return modifiedUserId;
	}

	@PrePersist
	protected void onPersist() {
	    //Date currentDate = DateUtils.toGmtDate(new Date());
		Date currentDate = new Date();
	    creationDate = currentDate;
	    modifiedDate = currentDate;
	    deletedFlag=false;
	}
	
	@PreUpdate
	protected void onUpdate() {
		modifiedDate = new Date();
	    //modifiedDate = DateUtils.toGmtDate(new Date());
		
	}
	
	
}
