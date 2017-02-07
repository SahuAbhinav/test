package com.advanz.erp.masters.model;
import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class ItemGroupDTO extends BaseDTO {
	private Integer itemGroupId;
	private String itemGroupCode;
	private String itemGroupName;
	private Integer itemGroupFlagId;
	private Boolean deleteFlag;
	
	public Boolean getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getItemGroupCode() {
		return itemGroupCode;
	}
	public void setItemGroupCode(String itemGroupCode) {
		this.itemGroupCode = itemGroupCode;
	}
	public String getItemGroupName() {
		return itemGroupName;
	}
	public void setItemGroupName(String itemGroupName) {
		this.itemGroupName = itemGroupName;
	}
	public Integer getItemGroupFlagId() {
		return itemGroupFlagId;
	}
	public void setItemGroupFlagId(Integer itemGroupFlagId) {
		this.itemGroupFlagId = itemGroupFlagId;
	}
	
	
	
	public Integer getItemGroupId() {
		return itemGroupId;
	}
	public void setItemGroupId(Integer itemGroupId) {
		this.itemGroupId = itemGroupId;
	}
	@Override
	public String toString() {
		return "ItemGroupDTO [itemGroupId=" + itemGroupId + ", itemGroupCode=" + itemGroupCode + "]";
	}

}
