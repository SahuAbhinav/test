package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

public class GetPassDetailDTO extends BaseDTO {

	private Integer sno;
	private String transactionSeries;
	private Integer gatePassAutoId;
	private String gatePassNumber;
	private ItemDTO itemDTO;
	private Integer measurementUnitId;
	private String measurementUnitName;
	private Boolean deletedFlag=false;
	private String itemName;
	public String getMeasurementUnitName() {
		return measurementUnitName;
	}
	public void setMeasurementUnitName(String measurementUnitName) {
		this.measurementUnitName = measurementUnitName;
	}
	private Double gatePassQuantity;
	private Double gatePassReturnQuantity;
	private String remark;
	private Double pendingQuantity;
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
	public Integer getGatePassAutoId() {
		return gatePassAutoId;
	}
	public void setGatePassAutoId(Integer gatePassAutoId) {
		this.gatePassAutoId = gatePassAutoId;
	}
	public String getGatePassNumber() {
		return gatePassNumber;
	}
	public void setGatePassNumber(String gatePassNumber) {
		this.gatePassNumber = gatePassNumber;
	}
	public ItemDTO getItemDTO() {
		return itemDTO;
	}
	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}
	public Integer getMeasurementUnitId() {
		return measurementUnitId;
	}
	public void setMeasurementUnitId(Integer measurementUnitId) {
		this.measurementUnitId = measurementUnitId;
	}
	public Double getGatePassQuantity() {
		return gatePassQuantity;
	}
	public void setGatePassQuantity(Double gatePassQuantity) {
		this.gatePassQuantity = gatePassQuantity;
	}
	public Double getGatePassReturnQuantity() {
		return gatePassReturnQuantity;
	}
	public void setGatePassReturnQuantity(Double gatePassReturnQuantity) {
		this.gatePassReturnQuantity = gatePassReturnQuantity;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Double getPendingQuantity() {
		return pendingQuantity;
	}
	public void setPendingQuantity(Double pendingQuantity) {
		this.pendingQuantity = pendingQuantity;
	}
	public Boolean getDeletedFlag() {
		return deletedFlag;
	}
	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}
	
	
}
