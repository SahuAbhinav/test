package com.advanz.erp.masters.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class IndentMasterDTO extends BaseDTO {

	private Integer indentAutoId;

	private String transactionSeries;

	private String finYear;

	private String indentNumber;

	private Integer indentId;
	
	private Date indentDate;

	private String indentRemark;
	
	private String raisedBy;
	
	
	private Integer departmentId;
	private String itemName;
	private Date fromDate;
	private Date toDate;
    private List<IndentDetailDTO> indentDetailDTO;
	
	private BranchDTO branchDTO;
	
	private ItemDTO itemDTO;
	
	private String orderSeries;
	private String departmentName;
	private MastersDTO mastersDTO;
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


	public MastersDTO getMastersDTO() {
		return mastersDTO;
	}

	public void setMastersDTO(MastersDTO mastersDTO) {
		this.mastersDTO = mastersDTO;
	}


	public String getOrderSeries() {
		orderSeries=getTransactionSeries()+"/"+getFinYear();
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



	public Integer getIndentAutoId() {
		return indentAutoId;
	}

	public void setIndentAutoId(Integer indentAutoId) {
		this.indentAutoId = indentAutoId;
	}

	public String getIndentNumber() {
		return indentNumber;
	}

	public void setIndentNumber(String indentNumber) {
		this.indentNumber = indentNumber;
	}

	public Integer getIndentId() {
		return indentId;
	}

	public void setIndentId(Integer indentId) {
		this.indentId = indentId;
	}

	public String getIndentRemark() {
		return indentRemark;
	}

	public void setIndentRemark(String indentRemark) {
		this.indentRemark = indentRemark;
	}

	public List<IndentDetailDTO> getIndentDetailDTO() {
		return indentDetailDTO;
	}

	public void setIndentDetailDTO(List<IndentDetailDTO> indentDetailDTO) {
		this.indentDetailDTO = indentDetailDTO;
	}

	public ItemDTO getItemDTO() {
		return itemDTO;
	}

	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
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

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}




	public BranchDTO getBranchDTO() {
		return branchDTO;
	}

	public void setBranchDTO(BranchDTO branchDTO) {
		this.branchDTO = branchDTO;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}




}
