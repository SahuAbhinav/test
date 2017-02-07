package com.advanz.erp.masters.model;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

public class StockLedgerDTO extends BaseDTO {
	private Integer sno;

	private String transactionSeries;
	private Integer transactionId;
	private String transactionNumber;
	private Date transactionDate;
	private Integer itemId;
	private Integer branchId;
	private String batchNo;
	private Double quantity;
	private Double salesRate;
	private Double salesValue;
	private Date approvedDate;
	private Double approvedQuantity;

	private Double closingBalance;
	private Double transactionAmount;
	private Double weightedAmount;
	private Double weightedrate;
	private Double rateForWeighted;
	private Double masterRate;

	public Double getRateForWeighted() {
		return rateForWeighted;
	}

	public void setRateForWeighted(Double rateForWeighted) {
		this.rateForWeighted = rateForWeighted;
	}

	public Double getMasterRate() {
		return masterRate;
	}

	public void setMasterRate(Double masterRate) {
		this.masterRate = masterRate;
	}

	public Double getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(Double closingBalance) {
		this.closingBalance = closingBalance;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Double getWeightedAmount() {
		return weightedAmount;
	}

	public void setWeightedAmount(Double weightedAmount) {
		this.weightedAmount = weightedAmount;
	}

	public Double getWeightedrate() {
		return weightedrate;
	}

	public void setWeightedrate(Double weightedrate) {
		this.weightedrate = weightedrate;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
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

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getSalesRate() {
		return salesRate;
	}

	public void setSalesRate(Double salesRate) {
		this.salesRate = salesRate;
	}

	public Double getSalesValue() {
		return salesValue;
	}

	public void setSalesValue(Double salesValue) {
		this.salesValue = salesValue;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Double getApprovedQuantity() {
		return approvedQuantity;
	}

	public void setApprovedQuantity(Double approvedQuantity) {
		this.approvedQuantity = approvedQuantity;
	}

	@Override
	public String toString() {
		return "StockLedgerDTO [sno=" + sno + ", transactionSeries="
				+ transactionSeries + ", transactionId=" + transactionId
				+ ", transactionNumber=" + transactionNumber
				+ ", transactionDate=" + transactionDate + ", itemId=" + itemId
				+ ", branchId=" + branchId + ", batchNo=" + batchNo
				+ ", quantity=" + quantity + ", salesRate=" + salesRate
				+ ", salesValue=" + salesValue + ", approvedDate="
				+ approvedDate + ", approvedQuantity=" + approvedQuantity
				+ ", closingBalance=" + closingBalance + ", transactionAmount="
				+ transactionAmount + ", weightedAmount=" + weightedAmount
				+ ", weightedrate=" + weightedrate + "]";
	}
	
	

}
