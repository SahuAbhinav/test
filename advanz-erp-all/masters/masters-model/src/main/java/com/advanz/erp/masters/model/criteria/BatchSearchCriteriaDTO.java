package com.advanz.erp.masters.model.criteria;

import com.advanz.erp.common.model.BaseDTO;

public class BatchSearchCriteriaDTO extends BaseDTO{
private String batchNo;
private String itemName;
private String itemCategory;

public String getBatchNo() {
	return batchNo;
}
public void setBatchNo(String batchNo) {
	this.batchNo = batchNo;
}
public String getItemName() {
	return itemName;
}
public void setItemName(String itemName) {
	this.itemName = itemName;
}
public String getItemCategory() {
	return itemCategory;
}
public void setItemCategory(String itemCategory) {
	this.itemCategory = itemCategory;
}


}
