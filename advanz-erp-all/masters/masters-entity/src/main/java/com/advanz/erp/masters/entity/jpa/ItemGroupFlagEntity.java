package com.advanz.erp.masters.entity.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "M_ITEM_GROUP_FLAG")
public class ItemGroupFlagEntity  implements Serializable{
	
	@Id @GeneratedValue(generator="system-incr")
	@GenericGenerator(name="system-incr", strategy = "increment")
	@Column(name="ITEM_GROUP_FLAG_ID")
	private Integer itemGroupFlagId;
	
	@Column(name="ITEM_GROUP_FLAG_NAME")
	private String itemGroupFlagName;
	
	

	
	@Override
	public String toString() {
		return "ItemCategoryEntity [itemGroupFlagId=" + itemGroupFlagId
				+ ", itemGroupFlagId=" + itemGroupFlagId
				+ ", itemGroupFlagName=" + itemGroupFlagName + "]";
	}

	public Integer getItemGroupFlagId() {
		return itemGroupFlagId;
	}

	public void setItemGroupFlagId(Integer itemGroupFlagId) {
		this.itemGroupFlagId = itemGroupFlagId;
	}

	public String getItemGroupFlagName() {
		return itemGroupFlagName;
	}

	public void setItemGroupFlagName(String itemGroupFlagName) {
		this.itemGroupFlagName = itemGroupFlagName;
	}

}
