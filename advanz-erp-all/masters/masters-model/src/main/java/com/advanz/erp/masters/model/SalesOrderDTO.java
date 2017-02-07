package com.advanz.erp.masters.model;

import java.util.Date;


import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class SalesOrderDTO extends BaseDTO {

	private String transactionSeries;

	private Integer salesOrderAutoId;

	private String finyr;

	private String salesOrderNumber;;

	
	private Integer salesOrderId;

	private Date salesOrderDate; 

	private Integer branchId;

	private Date orderReceiptDate;

	private String quotationNumber;

	private Integer partyId;

	private String orderTakenBy;

	private Date orderTime;

	private Date desireDeliveryDate;

	private Date plannedDeliverDate;

	private String salesOrderRemark;

	private Boolean deletedFlag;

	private Date createdDate;

	private Date modifiedDate;

	private Integer createdUserId;

	private Integer modifiedUserId;

	private Double itemValue;

	private Double exciseDutyAmount;

	private Double educationCessAmount;

	private Double highEducationCessAmount;

	private Double discountAmount;

	private Double vatAmount;

	private Double cstAmount;

	private Double otherAmount;

	private Double soNetAmount;

	private Double taxableAmount;

	private String partyPoNo;

	private Date partyPoDate;

	private String othersDetail;
	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	public String getTransactionSeries() {
		return transactionSeries;
	}

	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	public Integer getSalesOrderAutoId() {
		return salesOrderAutoId;
	}

	public void setSalesOrderAutoId(Integer salesOrderAutoId) {
		this.salesOrderAutoId = salesOrderAutoId;
	}

	public String getFinyr() {
		return finyr;
	}

	public void setFinyr(String finyr) {
		this.finyr = finyr;
	}

	

	public Integer getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(Integer salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public Date getSalesOrderDate() {
		return salesOrderDate;
	}

	public void setSalesOrderDate(Date salesOrderDate) {
		this.salesOrderDate = salesOrderDate;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Date getOrderReceiptDate() {
		return orderReceiptDate;
	}

	public void setOrderReceiptDate(Date orderReceiptDate) {
		this.orderReceiptDate = orderReceiptDate;
	}

	public String getQuotationNumber() {
		return quotationNumber;
	}

	public void setQuotationNumber(String quotationNumber) {
		this.quotationNumber = quotationNumber;
	}

	public Integer getPartyId() {
		return partyId;
	}

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	public String getOrderTakenBy() {
		return orderTakenBy;
	}

	public void setOrderTakenBy(String orderTakenBy) {
		this.orderTakenBy = orderTakenBy;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getDesireDeliveryDate() {
		return desireDeliveryDate;
	}

	public void setDesireDeliveryDate(Date desireDeliveryDate) {
		this.desireDeliveryDate = desireDeliveryDate;
	}

	public Date getPlannedDeliverDate() {
		return plannedDeliverDate;
	}

	public void setPlannedDeliverDate(Date plannedDeliverDate) {
		this.plannedDeliverDate = plannedDeliverDate;
	}

	public String getSalesOrderRemark() {
		return salesOrderRemark;
	}

	public void setSalesOrderRemark(String salesOrderRemark) {
		this.salesOrderRemark = salesOrderRemark;
	}

	public Boolean getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Integer getModifiedUserId() {
		return modifiedUserId;
	}

	public void setModifiedUserId(Integer modifiedUserId) {
		this.modifiedUserId = modifiedUserId;
	}

	public Double getItemValue() {
		return itemValue;
	}

	public void setItemValue(Double itemValue) {
		this.itemValue = itemValue;
	}

	public Double getExciseDutyAmount() {
		return exciseDutyAmount;
	}

	public void setExciseDutyAmount(Double exciseDutyAmount) {
		this.exciseDutyAmount = exciseDutyAmount;
	}

	public Double getEducationCessAmount() {
		return educationCessAmount;
	}

	public void setEducationCessAmount(Double educationCessAmount) {
		this.educationCessAmount = educationCessAmount;
	}

	public Double getHighEducationCessAmount() {
		return highEducationCessAmount;
	}

	public void setHighEducationCessAmount(Double highEducationCessAmount) {
		this.highEducationCessAmount = highEducationCessAmount;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(Double vatAmount) {
		this.vatAmount = vatAmount;
	}

	public Double getCstAmount() {
		return cstAmount;
	}

	public void setCstAmount(Double cstAmount) {
		this.cstAmount = cstAmount;
	}

	public Double getOtherAmount() {
		return otherAmount;
	}

	public void setOtherAmount(Double otherAmount) {
		this.otherAmount = otherAmount;
	}

	public Double getSoNetAmount() {
		return soNetAmount;
	}

	public void setSoNetAmount(Double soNetAmount) {
		this.soNetAmount = soNetAmount;
	}

	public Double getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(Double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	public String getPartyPoNo() {
		return partyPoNo;
	}

	public void setPartyPoNo(String partyPoNo) {
		this.partyPoNo = partyPoNo;
	}

	public Date getPartyPoDate() {
		return partyPoDate;
	}

	public void setPartyPoDate(Date partyPoDate) {
		this.partyPoDate = partyPoDate;
	}

	public String getOthersDetail() {
		return othersDetail;
	}

	public void setOthersDetail(String othersDetail) {
		this.othersDetail = othersDetail;
	}

	
	
}
