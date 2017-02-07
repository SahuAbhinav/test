package com.advanz.erp.masters.model;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class DebitDutyMasterDTO extends BaseDTO{
	private Integer debitDutyAutoId;
	private String transactionSeries;
	private String finyr;
	private String debitDutyNumber;
	private Integer debitDutyId;
	private Date debitDutyDate;
	private BranchDTO branchDTO;
	private Double exciseAmount;
	private Double eduCessAmount;
	private Double hEduCessAmount;
	private String narration;
	private Integer approvedFlag;
	private Date approvedDate;
	
	private Date fromDate;
	private Date toDate;
	

	public Integer getDebitDutyAutoId() {
		return debitDutyAutoId;
	}
	public void setDebitDutyAutoId(Integer debitDutyAutoId) {
		this.debitDutyAutoId = debitDutyAutoId;
	}
	public String getTransactionSeries() {
		return transactionSeries;
	}
	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}
	
	public String getFinyr() {
		return finyr;
	}
	public void setFinyr(String finyr) {
		this.finyr = finyr;
	}
	public String getDebitDutyNumber() {
		return debitDutyNumber;
	}
	public void setDebitDutyNumber(String debitDutyNumber) {
		this.debitDutyNumber = debitDutyNumber;
	}
	public Integer getDebitDutyId() {
		return debitDutyId;
	}
	public void setDebitDutyId(Integer debitDutyId) {
		this.debitDutyId = debitDutyId;
	}
	public Date getDebitDutyDate() {
		return debitDutyDate;
	}
	public void setDebitDutyDate(Date debitDutyDate) {
		this.debitDutyDate = debitDutyDate;
	}
	public BranchDTO getBranchDTO() {
		return branchDTO;
	}
	public void setBranchDTO(BranchDTO branchDTO) {
		this.branchDTO = branchDTO;
	}
	public Double getExciseAmount() {
		return exciseAmount;
	}
	public void setExciseAmount(Double exciseAmount) {
		this.exciseAmount = exciseAmount;
	}
	public Double getEduCessAmount() {
		return eduCessAmount;
	}
	public void setEduCessAmount(Double eduCessAmount) {
		this.eduCessAmount = eduCessAmount;
	}
	public Double gethEduCessAmount() {
		return hEduCessAmount;
	}
	public void sethEduCessAmount(Double hEduCessAmount) {
		this.hEduCessAmount = hEduCessAmount;
	}
	public Integer getApprovedFlag() {
		return approvedFlag;
	}
	public void setApprovedFlag(Integer approvedFlag) {
		this.approvedFlag = approvedFlag;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	
}
