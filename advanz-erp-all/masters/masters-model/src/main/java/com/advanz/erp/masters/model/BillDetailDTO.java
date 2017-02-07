package com.advanz.erp.masters.model;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

public class BillDetailDTO extends BaseDTO {
	private Integer sno;
	private String invoiceNumber;
	private String transactionSeries;

	private Integer itemId;
	private Integer measurementUnitId;
	private Double itemValue;

	private String batchNo;
	private Date expiryDate;
	// add by me
	private Date mfgDate;
    private Double taxTotal;
    private String umoName;
    private Double tax;
    private Double taxAmoForVatOrCst;
    
	
	private Double eduCessAmount;
	private Double heduCessAmount;
	private Double assessableValue;
   public Double getTaxAmoForVatOrCst() {
		return taxAmoForVatOrCst;
	}

	public void setTaxAmoForVatOrCst(Double taxAmoForVatOrCst) {
		this.taxAmoForVatOrCst = taxAmoForVatOrCst;
	}

	// CR
	private Double qtyPerPacket=1.0;
	private Double noOfPacket=1.0;
	
	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public String getUmoName() {
		return umoName;
	}

	public void setUmoName(String umoName) {
		this.umoName = umoName;
	}

	public Double getTaxTotal() {
		return taxTotal;
	}

	public void setTaxTotal(Double taxTotal) {
		this.taxTotal = taxTotal;
	}

	public Date getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}

	private Double quantity;
	private Double salesRate;
	private Double excisePerc;
	private Double exciseAmount;
	private Double vatPerc;
	private Double vatAmount;
	private Double cstPerc;
	private Double cstAmount;
	private Double discountPer;
	private Double discountAmount;
	
	private Double discountAmountPerToShow;
	
	
	private String packingDetail;
	private Double netAmount;
	private Integer invoiceAutoId;
	private String itemName;
	private Integer bookedBy=1;
	private Integer primaryUOM;
	private Double primaryUnit;
	private Double secondaryConvUnit;
	

	public Integer getBookedBy() {
		return bookedBy;
	}

	public void setBookedBy(Integer bookedBy) {
		this.bookedBy = bookedBy;
	}

	public Integer getPrimaryUOM() {
		return primaryUOM;
	}

	public void setPrimaryUOM(Integer primaryUOM) {
		this.primaryUOM = primaryUOM;
	}

	public Double getPrimaryUnit() {
		return primaryUnit;
	}

	public void setPrimaryUnit(Double primaryUnit) {
		this.primaryUnit = primaryUnit;
	}

	public Double getSecondaryConvUnit() {
		return secondaryConvUnit;
	}

	public void setSecondaryConvUnit(Double secondaryConvUnit) {
		this.secondaryConvUnit = secondaryConvUnit;
	}

	public String getTransactionSeries() {
		return transactionSeries;
	}

	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getItemValue() {
		return itemValue;
	}

	public void setItemValue(Double itemValue) {
		this.itemValue = itemValue;
	}

	public Integer getInvoiceAutoId() {
		return invoiceAutoId;
	}

	public void setInvoiceAutoId(Integer invoiceAutoId) {
		this.invoiceAutoId = invoiceAutoId;
	}

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getMeasurementUnitId() {
		return measurementUnitId;
	}

	public void setMeasurementUnitId(Integer measurementUnitId) {
		this.measurementUnitId = measurementUnitId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getSalesRate() {
		return salesRate;
	}

	public void setSalesRate(Double salesRate) {
		this.salesRate = salesRate;
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

	public Double getVatPerc() {
		return vatPerc;
	}

	public void setVatPerc(Double vatPerc) {
		this.vatPerc = vatPerc;
	}

	public Double getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(Double vatAmount) {
		this.vatAmount = vatAmount;
	}

	public Double getCstPerc() {
		return cstPerc;
	}

	public void setCstPerc(Double cstPerc) {
		this.cstPerc = cstPerc;
	}

	public Double getCstAmount() {
		return cstAmount;
	}

	public void setCstAmount(Double cstAmount) {
		this.cstAmount = cstAmount;
	}

	public Double getDiscountPer() {
		return discountPer;
	}

	public void setDiscountPer(Double discountPer) {
		this.discountPer = discountPer;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getPackingDetail() {
		return packingDetail;
	}

	public void setPackingDetail(String packingDetail) {
		this.packingDetail = packingDetail;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	
	public Double getQtyPerPacket() {
		return qtyPerPacket;
	}

	public void setQtyPerPacket(Double qtyPerPacket) {
		this.qtyPerPacket = qtyPerPacket;
	}

	public Double getNoOfPacket() {
		return noOfPacket;
	}

	public void setNoOfPacket(Double noOfPacket) {
		this.noOfPacket = noOfPacket;
	}


	public Double getDiscountAmountPerToShow() {
		return discountAmountPerToShow;
	}

	public void setDiscountAmountPerToShow(Double discountAmountPerToShow) {
		this.discountAmountPerToShow = discountAmountPerToShow;
	}

	
	public Double getEduCessAmount() {
		return eduCessAmount;
	}

	public void setEduCessAmount(Double eduCessAmount) {
		this.eduCessAmount = eduCessAmount;
	}

	public Double getHeduCessAmount() {
		return heduCessAmount;
	}

	public void setHeduCessAmount(Double heduCessAmount) {
		this.heduCessAmount = heduCessAmount;
	}

	public Double getAssessableValue() {
		return assessableValue;
	}

	public void setAssessableValue(Double assessableValue) {
		this.assessableValue = assessableValue;
	}

	@Override
	public String toString() {
		return "BillDetailDTO [expiryDate=" + expiryDate + "]";
	}
}
