package com.advanz.erp.masters.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class IssueMasterDTO extends BaseDTO {

	private Integer issueAutoId;

	private String transactionSeries;

	private String finYear;

	private String issueNumber;

	private Integer issueId;
	
	private Date issueDate;

	private String issuedBy;
	
	private Time issueTime;
	
	private String indentnumber;
	
	private Date indentDate;
	
	private String raisedBy;
	
	private Integer itemGroupFlagId;
	
	private Integer departmentId;
	
	private Integer issueTypeId;
	
	private String issueRemark;
	
	private String issueTypeName;
	
	private String departmentName; 
	
	private Integer approved=0;
	private Date approvedDate;
	
// Add new column to display list to issue return form
	private String itemName;
	private String itemCode;
	private Integer itemId;
	private Double issueQuantity;
	private Double pendingQuantity;
	
	private String loanType;
	
	
    public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getIssueTypeName() {
		return issueTypeName;
	}

	public void setIssueTypeName(String issueTypeName) {
		this.issueTypeName = issueTypeName;
	}

    private List<IssueDetailMasterDTO> issueDetailMasterDTOList;
	
	private BranchDTO branchDTO;
	
	private ItemGroupFlagDTO itemGroupDTO;
	
	private String orderSeries;
	
	private MastersDTO mastersDTO;
	
	private IssueTypeMasterDTO issueTypeMasterDTO;
	
	private Integer next;
	private Integer previous;

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

	public IssueTypeMasterDTO getIssueTypeMasterDTO() {
		return issueTypeMasterDTO;
	}

	public void setIssueTypeMasterDTO(IssueTypeMasterDTO issueTypeMasterDTO) {
		this.issueTypeMasterDTO = issueTypeMasterDTO;
	}

	public MastersDTO getMastersDTO() {
		return mastersDTO;
	}

	public void setMastersDTO(MastersDTO mastersDTO) {
		this.mastersDTO = mastersDTO;
	}

	public ItemGroupFlagDTO getItemGroupDTO() {
		return itemGroupDTO;
	}

	public void setItemGroupDTO(ItemGroupFlagDTO itemGroupDTO) {
		this.itemGroupDTO = itemGroupDTO;
	}

	public String getOrderSeries() {
		orderSeries=getTransactionSeries()+"/"+getFinYear();
		return orderSeries;
	}

	public void setOrderSeries(String orderSeries) {
		this.orderSeries = orderSeries;
	}

	public Integer getIssueAutoId() {
		return issueAutoId;
	}

	public void setIssueAutoId(Integer issueAutoId) {
		this.issueAutoId = issueAutoId;
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

	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	public Integer getIssueId() {
		return issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public Time getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(Time issueTime) {
		this.issueTime = issueTime;
	}

	public String getIndentnumber() {
		return indentnumber;
	}

	public void setIndentnumber(String indentnumber) {
		this.indentnumber = indentnumber;
	}

	public Date getIndentDate() {
		return indentDate;
	}

	public void setIndentDate(Date indentDate) {
		this.indentDate = indentDate;
	}

	public String getRaisedBy() {
		return raisedBy;
	}

	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
	}

	public Integer getItemGroupFlagId() {
		return itemGroupFlagId;
	}

	public void setItemGroupFlagId(Integer itemGroupFlagId) {
		this.itemGroupFlagId = itemGroupFlagId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getIssueTypeId() {
		return issueTypeId;
	}

	public void setIssueTypeId(Integer issueTypeId) {
		this.issueTypeId = issueTypeId;
	}

	public String getIssueRemark() {
		return issueRemark;
	}

	public void setIssueRemark(String issueRemark) {
		this.issueRemark = issueRemark;
	}
	
	

	public List<IssueDetailMasterDTO> getIssueDetailMasterDTOList() {
		return issueDetailMasterDTOList;
	}

	public void setIssueDetailMasterDTOList(
			List<IssueDetailMasterDTO> issueDetailMasterDTOList) {
		this.issueDetailMasterDTOList = issueDetailMasterDTOList;
	}

	public BranchDTO getBranchDTO() {
		return branchDTO;
	}

	public void setBranchDTO(BranchDTO branchDTO) {
		this.branchDTO = branchDTO;
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

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Double getIssueQuantity() {
		return issueQuantity;
	}

	public void setIssueQuantity(Double issueQuantity) {
		this.issueQuantity = issueQuantity;
	}

	public Double getPendingQuantity() {
		return pendingQuantity;
	}

	public void setPendingQuantity(Double pendingQuantity) {
		this.pendingQuantity = pendingQuantity;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	@Override
	public String toString() {
		return "IssueMasterDTO [issueAutoId=" + issueAutoId
				+ ", transactionSeries=" + transactionSeries + ", finYear="
				+ finYear + ", issueNumber=" + issueNumber + ", issueId="
				+ issueId + ", issueDate=" + issueDate + ", issuedBy="
				+ issuedBy + ", issueTime=" + issueTime + ", indentnumber="
				+ indentnumber + ", indentDate=" + indentDate + ", raisedBy="
				+ raisedBy + ", itemGroupFlagId=" + itemGroupFlagId
				+ ", departmentId=" + departmentId + ", issueTypeId="
				+ issueTypeId + ", issueRemark=" + issueRemark + "]";
	}
	
	
}
