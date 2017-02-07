package com.advanz.erp.masters.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "t_stock_ledger")
public class StockLedgerEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sno")
	private Integer sno;

	@Column(name = "transaction_series")
	private String transactionSeries;

	@Column(name = "transaction_id")
	private Integer transactionId;

	@Column(name = "transaction_number")
	private String transactionNumber;

	// @Column(name="finish_good_id")
	// private Integer finishGoodId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "transaction_date")
	private Date transactionDate;

	@Column(name = "branch_id")
	private Integer branchId;

	@Column(name = "item_id")
	private Integer itemId;

	@Column(name = "batch_no")
	private String batchNo;

	@Column(name = "quantity")
	private Double quantity;

	@Column(name = "sales_rate")
	private Double salesRate;

	@Column(name = "sales_value")
	private Double salesValue;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "approved_date")
	private Date approvedDate;
	
	@Column(name = "approved_quantity")
	private Double approvedQuantity;
	
	@Column(name = "closing_balance")
	private Double closingBalance;
	
	@Column(name = "transaction_amt")
	private Double transactionAmount;
	
	@Column(name = "weighted_amt")
	private Double weightedAmount;
	
	@Column(name = "weighted_rate")
	private Double weightedrate;
	
	@Column(name = "rate_for_weighed")
	private Double rateForWeighted;
	
	@Column(name = "master_rate")
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

	public String getTransactionSeries() {
		return transactionSeries;
	}

	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	/*
	 * public Integer getFinishGoodId() { return finishGoodId; }
	 * 
	 * public void setFinishGoodId(Integer finishGoodId) { this.finishGoodId =
	 * finishGoodId; }
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
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

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
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
		return "StockLedgerEntity [sno=" + sno + ", transactionSeries="
				+ transactionSeries + ", transactionId=" + transactionId
				+ ", transactionNumber=" + transactionNumber
				+ ", transactionDate=" + transactionDate + ", branchId="
				+ branchId + ", itemId=" + itemId + ", batchNo=" + batchNo
				+ ", quantity=" + quantity + ", salesRate=" + salesRate
				+ ", salesValue=" + salesValue + ", approvedDate="
				+ approvedDate + ", approvedQuantity=" + approvedQuantity
				+ ", closingBalance=" + closingBalance + ", transactionAmount="
				+ transactionAmount + ", weightedAmount=" + weightedAmount
				+ ", weightedrate=" + weightedrate + "]";
	}

	
}
