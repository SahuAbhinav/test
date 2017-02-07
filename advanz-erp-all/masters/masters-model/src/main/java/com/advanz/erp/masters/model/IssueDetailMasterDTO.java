package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class IssueDetailMasterDTO extends BaseDTO{
	private Integer sno;
	private String transactionSeries;
	private Integer issueAutoId;
	private String issueNumber;
	private Integer itemId;
	private MastersDTO measurementUnitId;
	private Double indentQuantity;
	private Double requiredQuantity=0.0;
	private Double issueQuantity=0.0;
	private String measurementUnitName;
	private Integer storeLocationId;
	private Boolean deletedFlag=false;
	
	private String issueType;
	
	private String itemName;
	private Double pendingQuantity;
    private boolean transientObject;
	private String operationFlag;
	
	private Integer masterHeadId;
	private Integer masterSectionId;
	
	private Double grnRate;
	private Double issueValue;
	public boolean isTransientObject() {
		return transientObject;
	}
	public void setTransientObject(boolean transientObject) {
		this.transientObject = transientObject;
	}

	
	public Boolean getDeletedFlag() {
		return deletedFlag;
	}
	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public String getTransactionSeries() {
		return transactionSeries;
	}
	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}
	public Integer getIssueAutoId() {
		return issueAutoId;
	}
	public void setIssueAutoId(Integer issueAutoId) {
		this.issueAutoId = issueAutoId;
	}
	public String getIssueNumber() {
		return issueNumber;
	}
	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public MastersDTO getMeasurementUnitId() {
		return measurementUnitId;
	}
	public void setMeasurementUnitId(MastersDTO measurementUnitId) {
		this.measurementUnitId = measurementUnitId;
	}
	public Double getIndentQuantity() {
		return indentQuantity;
	}
	public void setIndentQuantity(Double indentQuantity) {
		this.indentQuantity = indentQuantity;
	}
	public Double getRequiredQuantity() {
		return requiredQuantity;
	}
	public void setRequiredQuantity(Double requiredQuantity) {
		this.requiredQuantity = requiredQuantity;
	}
	public Double getIssueQuantity() {
		return issueQuantity;
	}
	public void setIssueQuantity(Double issueQuantity) {
		this.issueQuantity = issueQuantity;
	}
	public Integer getStoreLocationId() {
		return storeLocationId;
	}
	public void setStoreLocationId(Integer storeLocationId) {
		this.storeLocationId = storeLocationId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public Double getPendingQuantity() {
		return pendingQuantity;
	}
	public void setPendingQuantity(Double pendingQuantity) {
		this.pendingQuantity = pendingQuantity;
	}
	
	public String getOperationFlag() {
		return operationFlag;
	}
	public void setOperationFlag(String operationFlag) {
		this.operationFlag = operationFlag;
	}
	
	
	
	public Integer getMasterHeadId() {
		return masterHeadId;
	}
	public void setMasterHeadId(Integer masterHeadId) {
		this.masterHeadId = masterHeadId;
	}
	public Integer getMasterSectionId() {
		return masterSectionId;
	}
	public void setMasterSectionId(Integer masterSectionId) {
		this.masterSectionId = masterSectionId;
	}
	
	public Double getGrnRate() {
		return grnRate;
	}
	public void setGrnRate(Double grnRate) {
		this.grnRate = grnRate;
	}
	public Double getIssueValue() {
		return issueValue;
	}
	public void setIssueValue(Double issueValue) {
		this.issueValue = issueValue;
	}
	
	public String getMeasurementUnitName() {
		return measurementUnitName;
	}
	public void setMeasurementUnitName(String measurementUnitName) {
		this.measurementUnitName = measurementUnitName;
	}
	@Override
	public String toString() {
		return "IssueDetailMasterDTO [sno=" + sno + ", transactionSeries="
				+ transactionSeries + ", issueAutoId=" + issueAutoId
				+ ", issueNumber=" + issueNumber + ", itemId=" + itemId
				+ ", measurementUnitId=" + measurementUnitId
				+ ", indentQuantity=" + indentQuantity + ", requiredQuantity="
				+ requiredQuantity + ", issueQuantity=" + issueQuantity
				+ ", storeLocationId=" + storeLocationId + ", itemName="
				+ itemName + "]";
	}
	
	
	
	
	
	
}
