package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class IndentDetailDTO extends BaseDTO{
	private Integer sno;
	private String transactionSeries;
	private Integer indentAutoId;
	private String indentNumber;
	private Integer itemId;
	private MastersDTO measurementUnitId;
	private ItemDTO itemDTO;
	private Double indentQty;
	private Double pendingQty;
	private Integer departmentId;
	private String itemName;
	private String indentRemark;
	private Boolean deletedFlag=false;
	private Integer fullFill=1;
	private String itemMake;
	
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
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public MastersDTO getMeasurementUnitId() {
		return measurementUnitId;
	}
	public void setMeasurementUnitId(MastersDTO measurementUnitId) {
		this.measurementUnitId = measurementUnitId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	public ItemDTO getItemDTO() {
		return itemDTO;
	}
	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}
	public Double getIndentQty() {
		return indentQty;
	}
	public void setIndentQty(Double indentQty) {
		this.indentQty = indentQty;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getIndentRemark() {
		return indentRemark;
	}
	public void setIndentRemark(String indentRemark) {
		this.indentRemark = indentRemark;
	}
	public Boolean getDeletedFlag() {
		return deletedFlag;
	}
	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}
	public Double getPendingQty() {
		return pendingQty;
	}
	public void setPendingQty(Double pendingQty) {
		this.pendingQty = pendingQty;
	}
	public Integer getFullFill() {
		return fullFill;
	}
	public void setFullFill(Integer fullFill) {
		this.fullFill = fullFill;
	}
	public String getItemMake() {
		return itemMake;
	}
	public void setItemMake(String itemMake) {
		this.itemMake = itemMake;
	}
	
}
