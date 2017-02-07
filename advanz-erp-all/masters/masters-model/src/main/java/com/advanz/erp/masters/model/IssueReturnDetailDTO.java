package com.advanz.erp.masters.model;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;
@SuppressWarnings("serial")
public class IssueReturnDetailDTO extends BaseDTO{
	
	
	private Integer sno;
	
	private String transactionSeries;
	
	private Integer issueReturnAutoId;
	
	private String issueReturnNumber;
	
	private Integer itemId;
	
	private Integer measurmentUnitId;
	
	private String measurementUnitName;
	
	private Double issueReturnQuantity;
	
	private Integer storeLocationId;
	
	private String issueNumber;
	private String itemName;
	private Boolean deletedFlag=false;
	// TO Show
	private Double issueQuantity;
	private Date issueDate;
	public Boolean getDeletedFlag() {
		return deletedFlag;
	}
	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	public Integer getIssueReturnAutoId() {
		return issueReturnAutoId;
	}
	public void setIssueReturnAutoId(Integer issueReturnAutoId) {
		this.issueReturnAutoId = issueReturnAutoId;
	}
	public String getIssueReturnNumber() {
		return issueReturnNumber;
	}
	public void setIssueReturnNumber(String issueReturnNumber) {
		this.issueReturnNumber = issueReturnNumber;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
	public Double getIssueReturnQuantity() {
		return issueReturnQuantity;
	}
	
	
	public Integer getMeasurmentUnitId() {
		return measurmentUnitId;
	}
	public void setMeasurmentUnitId(Integer measurmentUnitId) {
		this.measurmentUnitId = measurmentUnitId;
	}
	public String getMeasurementUnitName() {
		return measurementUnitName;
	}
	public void setMeasurementUnitName(String measurementUnitName) {
		this.measurementUnitName = measurementUnitName;
	}
	public void setIssueReturnQuantity(Double issueReturnQuantity) {
		this.issueReturnQuantity = issueReturnQuantity;
	}
	public Integer getStoreLocationId() {
		return storeLocationId;
	}
	public void setStoreLocationId(Integer storeLocationId) {
		this.storeLocationId = storeLocationId;
	}
	public String getIssueNumber() {
		return issueNumber;
	}
	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}
	public Double getIssueQuantity() {
		return issueQuantity;
	}
	public void setIssueQuantity(Double issueQuantity) {
		this.issueQuantity = issueQuantity;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	

}
