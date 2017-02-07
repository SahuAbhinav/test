package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class QuotationDetailDTO extends BaseDTO {
	private Integer sno;
	private String transactionSeries;
	private Integer quotationAutoId;
	private String quotationNumber;
	private Integer itemId;
	private String itemName;
	private MastersDTO measurementUnitMasterDTO;
	private Double quantity;
	private Double salesRate = 0.0;
	private Double itemValue;
	private Double excisePerc = 0.0;
	private Double exciseAmount = 0.0;
	private Double vatPerc = 0.0;
	private Double vatAmount = 0.0;
	private Double cstPerc = 0.0;
	private Double cstAmount = 0.0;
	private Double discountPerc = 0.0;
	private Double discountAmount = 0.0;
	private Double netAmount = 0.0;
	private String packingDetail;
	private Integer deliveryDays;
	private Boolean deletedFlag=false;

	// Extra Fields

	private Double taxPerc;
	private Double taxAmount;
	
	private boolean transientObject;

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

	public Integer getQuotationAutoId() {
		return quotationAutoId;
	}

	public void setQuotationAutoId(Integer quotationAutoId) {
		this.quotationAutoId = quotationAutoId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public MastersDTO getMeasurementUnitMasterDTO() {
		return measurementUnitMasterDTO;
	}

	public void setMeasurementUnitMasterDTO(MastersDTO measurementUnitMasterDTO) {
		this.measurementUnitMasterDTO = measurementUnitMasterDTO;
	}

	public Double getQuantity() {
		if(quantity==null)
			quantity=0.0;
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getSalesRate() {
		if(salesRate==null)
			salesRate=0.0;
		return salesRate;
	}

	public void setSalesRate(Double salesRate) {
		this.salesRate = salesRate;
	}

	public Double getItemValue() {
		if(itemValue==null)
			itemValue=0.0;
		return itemValue;
	}

	public void setItemValue(Double itemValue) {
		this.itemValue = itemValue;
	}

	public Double getExcisePerc() {
		if(excisePerc==null)
			excisePerc=0.0;
		return excisePerc;
	}

	public void setExcisePerc(Double excisePerc) {
		this.excisePerc = excisePerc;
	}

	public Double getExciseAmount() {
		if(exciseAmount==null)
			exciseAmount=0.0;
		return exciseAmount;
	}

	public void setExciseAmount(Double exciseAmount) {
		this.exciseAmount = exciseAmount;
	}

	public Double getVatPerc() {
		if(vatPerc==null)
			vatPerc=0.0;
		return vatPerc;
	}

	public void setVatPerc(Double vatPerc) {
		this.vatPerc = vatPerc;
	}

	public Double getVatAmount() {
		if(vatAmount==null)
			vatAmount=0.0;
		return vatAmount;
	}

	public void setVatAmount(Double vatAmount) {
		this.vatAmount = vatAmount;
	}

	public Double getCstPerc() {
		if(cstPerc==null)
			cstPerc=0.0;
		return cstPerc;
	}

	public void setCstPerc(Double cstPerc) {
		this.cstPerc = cstPerc;
	}

	public Double getCstAmount() {
		if(cstAmount==null)
			cstAmount=0.0;
		return cstAmount;
	}

	public void setCstAmount(Double cstAmount) {
		this.cstAmount = cstAmount;
	}

	public Double getDiscountPerc() {
		if(discountPerc==null)
			discountPerc=0.0;
		return discountPerc;
	}

	public void setDiscountPerc(Double discountPerc) {
		this.discountPerc = discountPerc;
	}
	public Double getDiscountPer() {
		if(discountPerc==null)
			discountPerc=0.0;
		return discountPerc;
	}
	public void setDiscountPer(Double discountPerc) {
		this.discountPerc = discountPerc;
	}
	public Double getDiscountAmount() {
		if(discountAmount==null)
			discountAmount=0.0;
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

	public String getPackingDetail() {
		return packingDetail;
	}

	public void setPackingDetail(String packingDetail) {
		this.packingDetail = packingDetail;
	}

	public Integer getDeliveryDays() {
		return deliveryDays;
	}

	public void setDeliveryDays(Integer deliveryDays) {
		this.deliveryDays = deliveryDays;
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

	public String getQuotationNumber() {
		return quotationNumber;
	}

	public void setQuotationNumber(String quotationNumber) {
		this.quotationNumber = quotationNumber;
	}

	public Boolean getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public boolean isTransientObject() {
		return transientObject;
	}

	public void setTransientObject(boolean transientObject) {
		this.transientObject = transientObject;
	}
	
	
	
	

}
