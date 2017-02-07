package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;
@SuppressWarnings("serial")
@Entity
@Table(name = "M_ITEM_CATEGORY")
public class ItemCategoryEntity extends BaseEntity {
	@Id @GeneratedValue(generator="system-incr")
	@GenericGenerator(name="system-incr", strategy = "increment")
	@Column(name="ITEM_CATEGORY_ID")
	private Integer itemCategoryId;
	
	@Column(name="ITEM_CATEGORY_CODE")
	private String itemCategoryCode;
	
	@Column(name="ITEM_CATEGORY_NAME")
	private String itemCategoryName;
	
	@ManyToOne
	@JoinColumn(name="ITEM_GROUP_ID")
	private ItemGroupEntity itemGroupEntity;
	
	
//	@Column(name="ITEM_GROUP_ID")
//	private Integer itemGroupId;

	public ItemGroupEntity getItemGroupEntity() {
		return itemGroupEntity;
	}

	public void setItemGroupEntity(ItemGroupEntity itemGroupEntity) {
		this.itemGroupEntity = itemGroupEntity;
	}

	public Integer getItemCategoryId() {
		return itemCategoryId;
	}

	public void setItemCategoryId(Integer itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}

	/**
	 * @return the itemCategoryCode
	 */
	public String getItemCategoryCode() {
		return itemCategoryCode;
	}

	/**
	 * @param itemCategoryCode the itemCategoryCode to set
	 */
	public void setItemCategoryCode(String itemCategoryCode) {
		this.itemCategoryCode = itemCategoryCode;
	}

	/**
	 * @return the itemCategoryName
	 */
	public String getItemCategoryName() {
		return itemCategoryName;
	}

	/**
	 * @param itemCategoryName the itemCategoryName to set
	 */
	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}

	

	
	@Override
	public String toString() {
		return "ItemCategoryEntity [itemCategoryId=" + itemCategoryId
				+ ", itemCategoryCode=" + itemCategoryCode
				+ ", itemCategoryName=" + itemCategoryName + "]";
	}
	
	
	
}
