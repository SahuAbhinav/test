package com.advanz.erp.masters.model;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

public class CapativeConsuptionDTO extends BaseDTO{
	private Integer sno;
	private Integer sourceItemId;
	private Double sourceQuantity;
	private Integer targetItemId;
	private Double targetQuantity;
	private Date enteredDate;
	private Integer capativeConsumptionId;
	private String capativeConsumptionNumber;
	
	
	private String sourceItemName;
	private String targetItemName;
	
	private String sourceItemCode;
	private String targetItemCode;
	
	
	
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public Integer getSourceItemId() {
		return sourceItemId;
	}
	public void setSourceItemId(Integer sourceItemId) {
		this.sourceItemId = sourceItemId;
	}
	public Double getSourceQuantity() {
		return sourceQuantity;
	}
	public void setSourceQuantity(Double sourceQuantity) {
		this.sourceQuantity = sourceQuantity;
	}
	public Integer getTargetItemId() {
		return targetItemId;
	}
	public void setTargetItemId(Integer targetItemId) {
		this.targetItemId = targetItemId;
	}
	public Double getTargetQuantity() {
		return targetQuantity;
	}
	public void setTargetQuantity(Double targetQuantity) {
		this.targetQuantity = targetQuantity;
	}
	public Date getEnteredDate() {
		return enteredDate;
	}
	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}
	public Integer getCapativeConsumptionId() {
		return capativeConsumptionId;
	}
	public void setCapativeConsumptionId(Integer capativeConsumptionId) {
		this.capativeConsumptionId = capativeConsumptionId;
	}
	public String getCapativeConsumptionNumber() {
		return capativeConsumptionNumber;
	}
	public void setCapativeConsumptionNumber(String capativeConsumptionNumber) {
		this.capativeConsumptionNumber = capativeConsumptionNumber;
	}
	public String getSourceItemName() {
		return sourceItemName;
	}
	public void setSourceItemName(String sourceItemName) {
		this.sourceItemName = sourceItemName;
	}
	public String getTargetItemName() {
		return targetItemName;
	}
	public void setTargetItemName(String targetItemName) {
		this.targetItemName = targetItemName;
	}
	public String getSourceItemCode() {
		return sourceItemCode;
	}
	public void setSourceItemCode(String sourceItemCode) {
		this.sourceItemCode = sourceItemCode;
	}
	public String getTargetItemCode() {
		return targetItemCode;
	}
	public void setTargetItemCode(String targetItemCode) {
		this.targetItemCode = targetItemCode;
	}
	
}
