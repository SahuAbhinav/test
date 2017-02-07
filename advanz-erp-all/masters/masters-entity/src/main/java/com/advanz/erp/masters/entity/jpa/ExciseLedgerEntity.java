package com.advanz.erp.masters.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.advanz.erp.common.entity.jpa.BaseEntity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "t_excise_ledger")
public class ExciseLedgerEntity extends BaseEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sno")
	private Integer sno;
	@Column(name = "transaction_sr_no")
	private Integer transactionSrNo;
	
	@Column(name = "branch_id")
	private Integer branchId;
	
	@Column(name = "item_id")
	private Integer itemId;
	
	@Column(name = "party_id")
	private Integer partyId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "transaction_date")
	private Date transactionDate;
	
	@Column(name = "transaction_series")
	private String transactionSeries;
	
	@Column(name = "grn_number")
	private String grnNumber;
	
	@Column(name = "grn_qty")
	private Double grnQty;
	
	@Column(name = "issue_number")
	private String issueNumber;
	
	@Column(name = "issue_qty")
	private Double issueQty;
	
	@Column(name = "invoice_number")
	private String invoiceNumber;
	
	@Column(name = "invoice_qty")
	private Double invoiceQty;
	
	@Column(name = "ret_invoice_number")
	private String retInvoiceNumber;
	
	@Column(name = "ret_invoice_qty")
	private Double retInvoiceQty;
	
	@Column(name = "excise_amount")
	private Double exciseAmount;
	
	@Column(name = "cess_amount")
	private Double cessAmount;
	
	@Column(name = "h_edu_cess_amount")
	private Double hEduCessAmount;
	
	@Column(name = "narration")
	private String narration;
   
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "approved_date")
	private Date approvedDate;
	
	@Column(name = "received_bill_excise_amt")
	private Double receivedBillExciseAmount;
	
	@Column(name = "received_education_cess_amount")
	private Double receivedEducationCessAmount;
	
	@Column(name = "received_high_education_cess_amount")
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
