package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "M_ITEM_GROUP")
public class ItemGroupEntity extends BaseEntity {
	@Id
	@GeneratedValue(generator = "system-incr")
	@GenericGenerator(name = "system-incr", strategy = "increment")
	@Column(name = "ITEM_GROUP_ID")
	private Integer itemGroupId;

	public Integer getItemGroupId() {
		return itemGroupId;
	}

	public void setItemGroupId(Integer itemGroupId) {
		this.itemGroupId = itemGroupId;
	}

	@Column(name = "ITEM_GROUP_CODE")
	private String itemGroupCode;

	@Column(name = "ITEM_GROUP_NAME")
	private String itemGroupName;

	@Column(name = "ITEM_GROUP_FLAG_ID")
	private Integer itemGroupFlagId;

	/*@Column(name = "deleted_flag")
	private Boolean deleteFlag;

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}*/


	public Integer getUid() {
		// TODO Auto-generated method stub
		return itemGroupId;
	}


	public void setUid(Integer uid) {
		// TODO Auto-generated method stub
		itemGroupId = uid;

	}

	/**
	 * @return the itemGroupCode
	 */
	public String getItemGroupCode() {
		return itemGroupCode;
	}

	/**
	 * @param itemGroupCode
	 *            the itemGroupCode to set
	 */
	public void setItemGroupCode(String itemGroupCode) {
		this.itemGroupCode = itemGroupCode;
	}

	/**
	 * @return the itemGroupName
	 */
	public String getItemGroupName() {
		return itemGroupName;
	}

	/**
	 * @param itemGroupName
	 *            the itemGroupName to set
	 */
	public void setItemGroupName(String itemGroupName) {
		this.itemGroupName = itemGroupName;
	}

	/**
	 * @return the itemGroupFlagId
	 */
	public Integer getItemGroupFlagId() {
		return itemGroupFlagId;
	}

	/**
	 * @param itemGroupFlagId
	 *            the itemGroupFlagId to set
	 */
	public void setItemGroupFlagId(Integer itemGroupFlagId) {
		this.itemGroupFlagId = itemGroupFlagId;
	}
	
	
}
