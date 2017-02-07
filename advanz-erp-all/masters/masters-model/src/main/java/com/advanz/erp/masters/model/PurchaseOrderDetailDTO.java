package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

public class PurchaseOrderDetailDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer sno;

	
	private String purchaseOrderNumber;

	private ItemDTO itemDTO;

	private Double purchaseRate = 0.0;
	
	private Double itemValue = 0.0;
	
	private Integer poAutoId;
	
	private String transactionSeries;
	
	private String measurementUnitName;

	

	

	
	private Integer measurementUnitId;

	private Double itemQuantity=0.0;
	
	

	private Double excisePerc = 0.0;

	private Double exciseAmount = 0.0;

	private Double vatAmount = 0.0;

	

	private Double vatPerc = 0.0;

	private Double cstPerc = 0.0;

	private Double discountPerc = 0.0;

	private Double discountAmount = 0.0;

	private Double netAmount = 0.0;

	private String itemRemark;

	private Double itemUnderDeliveryTolerancePer;

	private Double overDeliveryTolerancePer;

	//private String partyInvoiceType;

	private Double taxPerc=0.0;

	private Double taxAmount = 0.0;

	private Double pendingQuantity;
	
	private Double educationCessPerc=0.0;
	private Double educationCessAmount=0.0;
	private Double highEducationCessAmount=0.0;
	private Double highEducationCessPerc=0.0;
	
	private String indentNumber;
	public Double getPendingQuantity() {
		return pendingQuantity;
	}

	public void setPendingQuantity(Double pendingQuantity) {
		this.pendingQuantity = pendingQuantity;
	}
	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public ItemDTO getItemDTO() {
		return itemDTO;
	}

	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}

	public Double getPurchaseRate() {
		return purchaseRate;
	}

	public void setPurchaseRate(Double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}

	public Double getItemValue() {
		return itemValue;
	}

	public void setItemValue(Double itemValue) {
		this.itemValue = itemValue;
	}

	public Integer getPoAutoId() {
		return poAutoId;
	}

	public void setPoAutoId(Integer poAutoId) {
		this.poAutoId = poAutoId;
	}

	public String getTransactionSeries() {
		return transactionSeries;
	}

	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	public String getMeasurementUnitName() {
		return measurementUnitName;
	}

	public void setMeasurementUnitName(String measurementUnitName) {
		this.measurementUnitName = measurementUnitName;
	}

	public Integer getMeasurementUnitId() {
		return measurementUnitId;
	}

	public void setMeasurementUnitId(Integer measurementUnitId) {
		this.measurementUnitId = measurementUnitId;
	}

	public Double getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Double itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public Double getExcisePerc() {
		return excisePerc;
	}

	public void setExcisePerc(Double excisePerc) {
		this.excisePerc = excisePerc;
	}

	public Double getExciseAmount() {
		return exciseAmount;
	}

	public void setExciseAmount(Double exciseAmount) {
		this.exciseAmount = exciseAmount;
	}

	public Double getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(Double vatAmount) {
		this.vatAmount = vatAmount;
	}

	public Double getVatPerc() {
		return vatPerc;
	}

	public void setVatPerc(Double vatPerc) {
		this.vatPerc = vatPerc;
	}

	public Double getCstPerc() {
		return cstPerc;
	}

	public void setCstPerc(Double cstPerc) {
		this.cstPerc = cstPerc;
	}

	public Double getDiscountPerc() {
		return discountPerc;
	}

	public void setDiscountPerc(Double discountPerc) {
		this.discountPerc = discountPerc;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public String getItemRemark() {
		return itemRemark;
	}

	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
	}

	public Double getItemUnderDeliveryTolerancePer() {
		return itemUnderDeliveryTolerancePer;
	}

	public void setItemUnderDeliveryTolerancePer(
			Double itemUnderDeliveryTolerancePer) {
		this.itemUnderDeliveryTolerancePer = itemUnderDeliveryTolerancePer;
	}

	public Double getOverDeliveryTolerancePer() {
		return overDeliveryTolerancePer;
	}

	public void setOverDeliveryTolerancePer(Double overDeliveryTolerancePer) {
		this.overDeliveryTolerancePer = overDeliveryTolerancePer;
	}

	public Double getTaxPerc() {
		return taxPerc;
	}

	public void setTaxPerc(Double taxPerc) {
		this.taxPerc = taxPerc;
	}

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getEducationCessAmount() {
		return educationCessAmount;
	}

	public void setEducationCessAmount(Double educationCessAmount) {
		this.educationCessAmount = educationCessAmount;
	}

	public Double getHighEducationCessAmount() {
		return highEducationCessAmount;
	}

	public void setHighEducationCessAmount(Double highEducationCessAmount) {
		this.highEducationCessAmount = highEducationCessAmount;
	}

	public Double getEducationCessPerc() {
		return educationCessPerc;
	}

	public void setEducationCessPerc(Double educationCessPerc) {
		this.educationCessPerc = educationCessPerc;
	}

	public Double getHighEducationCessPerc() {
		return highEducationCessPerc;
	}

	public void setHighEducationCessPerc(Double highEducationCessPerc) {
		this.highEducationCessPerc = highEducationCessPerc;
	}

	public String getIndentNumber() {
		return indentNumber;
	}

	public void setIndentNumber(String indentNumber) {
		this.indentNumber = indentNumber;
	}

}
