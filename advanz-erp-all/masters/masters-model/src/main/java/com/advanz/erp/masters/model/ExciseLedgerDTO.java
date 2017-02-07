package com.advanz.erp.masters.model;

import java.util.Date;
import com.advanz.erp.common.model.BaseDTO;

public class ExciseLedgerDTO extends BaseDTO
{
	private Integer sno;
	private Integer transactionSrNo;
	private Integer branchId;
	private Integer itemId;
	private Integer partyId;
	private Date transactionDate;
	private String transactionSeries;
	private String grnNumber;
	private Double grnQty;
	private String issueNumber;
	private Double issueQty;
	private String invoiceNumber;
	private Double invoiceQty;
	private String retInvoiceNumber;
	private Double retInvoiceQty;
	private Double exciseAmount;
	private Double cessAmount;
	private Double hEduCessAmount;
	private String narration;
	private Date approvedDate;

	private Double receivedBillExciseAmount;
	private Double receivedEducationCessAmount;
	private Double receivedHighEducationCessAmount;
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public Integer getTransactionSrNo() {
		return transactionSrNo;
	}
	public void setTransactionSrNo(Integer transactionSrNo) {
		this.transactionSrNo = transactionSrNo;
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
	public Integer getPartyId() {
		return partyId;
	}
	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionSeries() {
		return transactionSeries;
	}
	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}
	public String getGrnNumber() {
		return grnNumber;
	}
	public void setGrnNumber(String grnNumber) {
		this.grnNumber = grnNumber;
	}
	public Double getGrnQty() {
		return grnQty;
	}
	public void setGrnQty(Double grnQty) {
		this.grnQty = grnQty;
	}
	public String getIssueNumber() {
		return issueNumber;
	}
	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}
	public Double getIssueQty() {
		return issueQty;
	}
	public void setIssueQty(Double issueQty) {
		this.issueQty = issueQty;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public Double getInvoiceQty() {
		return invoiceQty;
	}
	public void setInvoiceQty(Double invoiceQty) {
		this.invoiceQty = invoiceQty;
	}
	public String getRetInvoiceNumber() {
		return retInvoiceNumber;
	}
	public void setRetInvoiceNumber(String retInvoiceNumber) {
		this.retInvoiceNumber = retInvoiceNumber;
	}
	public Double getRetInvoiceQty() {
		return retInvoiceQty;
	}
	public void setRetInvoiceQty(Double retInvoiceQty) {
		this.retInvoiceQty = retInvoiceQty;
	}
	public Double getExciseAmount() {
		return exciseAmount;
	}
	public void setExciseAmount(Double exciseAmount) {
		this.exciseAmount = exciseAmount;
	}
	public Double getCessAmount() {
		return cessAmount;
	}
	public void setCessAmount(Double cessAmount) {
		this.cessAmount = cessAmount;
	}
	public Double gethEduCessAmount() {
		return hEduCessAmount;
	}
	public void sethEduCessAmount(Double hEduCessAmount) {
		this.hEduCessAmount = hEduCessAmount;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	
	public Double getReceivedBillExciseAmount() {
		return receivedBillExciseAmount;
	}
	public void setReceivedBillExciseAmount(Double receivedBillExciseAmount) {
		this.receivedBillExciseAmount = receivedBillExciseAmount;
	}
	public Double getReceivedEducationCessAmount() {
		return receivedEducationCessAmount;
	}
	public void setReceivedEducationCessAmount(Double receivedEducationCessAmount) {
		this.receivedEducationCessAmount = receivedEducationCessAmount;
	}
	public Double getReceivedHighEducationCessAmount() {
		return receivedHighEducationCessAmount;
	}
	public void setReceivedHighEducationCessAmount(
			Double receivedHighEducationCessAmount) {
		this.receivedHighEducationCessAmount = receivedHighEducationCessAmount;
	}
	public String toString()
	   {
		   return "ExciseLedgerEntity-->[sno="+sno+",transactionSrNo="+transactionSrNo+",branchId="+branchId+",itemId="+itemId+
		   ",partyId="+partyId+",transactionDate="+transactionDate+ ",transactionSeries="+transactionSeries+",grnNumber="+grnNumber+
		   ",grnQty="+grnQty+",issueNumber="+issueNumber+",issueQty="+issueQty+",invoiceNumber="+invoiceNumber+",invoiceQty="+invoiceQty+
		   ",retInvoiceNumber="+retInvoiceNumber+",retInvoiceQty="+retInvoiceQty+",exciseAmount="+exciseAmount+
		   ",cessAmount="+cessAmount+",hEduCessAmount="+hEduCessAmount+",narration="+narration+"]";
	   }
}
