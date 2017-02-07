package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.IssueReturnMasterDTO;

public class IssueReturnMasterForm {
	private IssueReturnMasterDTO issueReturnMasterDTO;
	private List<IssueReturnMasterDTO> issueReturnMasterDTOList;
	private String opr;
	private String itemName;
	private String itemCode;
	private String operationFlage;
	private String issueNo;
	private Integer previous;
	private Integer next;
	private String raisedBy;
	private String loanType;
	private String lastIssueReturnDate;

	public String getLastIssueReturnDate() {
		return lastIssueReturnDate;
	}

	public void setLastIssueReturnDate(String lastIssueReturnDate) {
		this.lastIssueReturnDate = lastIssueReturnDate;
	}

	public String getOpr() {
		return opr;
	}

	public void setOpr(String opr) {
		this.opr = opr;
	}

	public IssueReturnMasterDTO getIssueReturnMasterDTO() {
		return issueReturnMasterDTO;
	}

	public void setIssueReturnMasterDTO(
			IssueReturnMasterDTO issueReturnMasterDTO) {
		this.issueReturnMasterDTO = issueReturnMasterDTO;
	}

	public List<IssueReturnMasterDTO> getIssueReturnMasterDTOList() {
		return issueReturnMasterDTOList;
	}

	public void setIssueReturnMasterDTOList(
			List<IssueReturnMasterDTO> issueReturnMasterDTOList) {
		this.issueReturnMasterDTOList = issueReturnMasterDTOList;
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

	public String getOperationFlage() {
		return operationFlage;
	}

	public void setOperationFlage(String operationFlage) {
		this.operationFlage = operationFlage;
	}

	public String getIssueNo() {
		return issueNo;
	}

	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}

	public Integer getPrevious() {
		return previous;
	}

	public void setPrevious(Integer previous) {
		this.previous = previous;
	}

	public Integer getNext() {
		return next;
	}

	public void setNext(Integer next) {
		this.next = next;
	}

	public String getRaisedBy() {
		return raisedBy;
	}

	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

}
