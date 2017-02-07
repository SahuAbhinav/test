package com.advanz.erp.masters.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class IssueReturnMasterDTO extends BaseDTO {

	private String transactionSeries;

	private String finYear;

	private Integer issueReturnAutoId;

	private String issueReturnNumber;

	private Integer issueReturnId;

	private Date issueReturnDate;

	private String issuedReturnBy;

	private Time issueReturnTime;

	private BranchDTO branchDTO;

	private MastersDTO mastersDTO;

	private String issueReturnRemark;

	private Integer approved;

	private Date approvedDate;

	private String orderSeries;

	private Date toDate;
	private Date fromDate;
	
	private Integer next;
	private Integer previous;
	//Use in get issue list to issue return
	private String itemName;
	private String itemCode;
	private String operationFlage;
	private String issueNumber;
	
	//To issue search
	private String risedBy;
	private String loanType;
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getOrderSeries() {
		return orderSeries;
	}

	public void setOrderSeries(String orderSeries) {
		this.orderSeries = orderSeries;
	}

	public String getTransactionSeries() {
		return transactionSeries;
	}

	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}

	private List<IssueReturnDetailDTO> issueReturnDetailDTOList;

	public List<IssueReturnDetailDTO> getIssueReturnDetailDTOList() {
		return issueReturnDetailDTOList;
	}

	public void setIssueReturnDetailDTOList(
			List<IssueReturnDetailDTO> issueReturnDetailDTOList) {
		this.issueReturnDetailDTOList = issueReturnDetailDTOList;
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

	public Integer getIssueReturnId() {
		return issueReturnId;
	}

	public void setIssueReturnId(Integer issueReturnId) {
		this.issueReturnId = issueReturnId;
	}

	public Date getIssueReturnDate() {
		return issueReturnDate;
	}

	public void setIssueReturnDate(Date issueReturnDate) {
		this.issueReturnDate = issueReturnDate;
	}

	public String getIssuedReturnBy() {
		return issuedReturnBy;
	}

	public void setIssuedReturnBy(String issuedReturnBy) {
		this.issuedReturnBy = issuedReturnBy;
	}

	public Time getIssueReturnTime() {
		return issueReturnTime;
	}

	public void setIssueReturnTime(Time issueReturnTime) {
		this.issueReturnTime = issueReturnTime;
	}

	public BranchDTO getBranchDTO() {
		return branchDTO;
	}

	public void setBranchDTO(BranchDTO branchDTO) {
		this.branchDTO = branchDTO;
	}

	public MastersDTO getMastersDTO() {
		return mastersDTO;
	}

	public void setMastersDTO(MastersDTO mastersDTO) {
		this.mastersDTO = mastersDTO;
	}

	public String getIssueReturnRemark() {
		return issueReturnRemark;
	}

	public void setIssueReturnRemark(String issueReturnRemark) {
		this.issueReturnRemark = issueReturnRemark;
	}

	public Integer getApproved() {
		return approved;
	}

	public void setApproved(Integer approved) {
		this.approved = approved;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Integer getNext() {
		return next;
	}

	public void setNext(Integer next) {
		this.next = next;
	}

	public Integer getPrevious() {
		return previous;
	}

	public void setPrevious(Integer previous) {
		this.previous = previous;
	}

	public String getOperationFlage() {
		return operationFlage;
	}

	public void setOperationFlage(String operationFlage) {
		this.operationFlage = operationFlage;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	public String getRisedBy() {
		return risedBy;
	}

	public void setRisedBy(String risedBy) {
		this.risedBy = risedBy;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

}
