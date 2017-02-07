package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

public class ReturnGetPassDetailDTO extends BaseDTO {
	
	private Integer sno;
	private String transactionSeries;
	private Integer returnGatePassAutoId;
	private Integer measurementUnitId;
	private String measurementUnitName;
	private String returnGatePassNumber;
	private Double returnGatePassQuantity;
	private ItemDTO itemDTO;
	private String itemName;
	private String remark;
	public String getMeasurementUnitName() {
		return measurementUnitName;
	}
	public void setMeasurementUnitName(String measurementUnitName) {
		this.measurementUnitName = measurementUnitName;
	}

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
	
	public Integer getReturnGatePassAutoId() {
		return returnGatePassAutoId;
	}
	public void setReturnGatePassAutoId(Integer returnGatePassAutoId) {
		this.returnGatePassAutoId = returnGatePassAutoId;
	}
	public String getReturnGatePassNumber() {
		return returnGatePassNumber;
	}
	public void setReturnGatePassNumber(String returnGatePassNumber) {
		this.returnGatePassNumber = returnGatePassNumber;
	}
	public Double getReturnGatePassQuantity() {
		return returnGatePassQuantity;
	}
	public void setReturnGatePassQuantity(Double returnGatePassQuantity) {
		this.returnGatePassQuantity = returnGatePassQuantity;
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
}
