package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class SalesOrderDetailDTO extends BaseDTO{
	private Integer sno;
	private String transactionSeries;
//	private String salesOrderNumber;
	private Integer salesOrderAutoId;
	private String salesOrderNumber;
//	private ItemDTO itemDTO;
	private Integer itemId;
	private String itemName;
	private Integer measurementUnitId;
	private MastersDTO masterUnit;
	private Double quantity;
	private Double salesRate=0.0;
	private Double excisePerc=0.0;
	private Double exciseAmount=0.0;
	private Double vatPerc=0.0;
	private Double vatAmount=0.0;
	private Double cstPerc=0.0;
	private Double cstAmount=0.0;
	private Double discountPerc=0.0;
	private Double discountAmount=0.0;
	private Double netAmount=0.0;
	private String itemRemark;
	private String packingDetail;
	private Integer deliveryDays;
	private Boolean deletedFlag=false;
	
	//private SalesOrderMasterDTO salesOrderMasterDTO;
	
	
	// Extra Fields
	
	private Double itemValue; 
	private Double taxPerc;
	private Double taxAmount;
	private String partyInvoiceType;
	private Double supplyQty;
	private Double pendingQty;
	private String invoiceNumber;
	private Integer activeStatus=1;
	private Integer fullFillByInvoice=0;
	public Double getSupplyQty() {
		return supplyQty;
	}
	public void setSupplyQty(Double supplyQty) {
		this.supplyQty = supplyQty;
	}
	public Double getPendingQty() {
		return pendingQty;
	}
	public void setPendingQty(Double pendingQty) {
		this.pendingQty = pendingQty;
	}
	
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}


private boolean transientObject;

private Double educationCessAmount;
private Double highEducationCessAmount;
	
private Double educationCessPerc;
private Double highEducationCessPerc;

//CR
private Double qtyPerPacket=1.0;
private Double noOfPacket=1.0;
private Integer bookedBy=1;
private Integer primaryUOM;
private Double primaryUnit;
private Double secondaryConvUnit;
private Double primaryPendingQty;
private Double primarySupplyQty;
private String unitName;


	public String getUnitName() {
	return unitName;
}
public void setUnitName(String unitName) {
	this.unitName = unitName;
}
	public Double getPrimarySupplyQty() {
	return primarySupplyQty;
}
public void setPrimarySupplyQty(Double primarySupplyQty) {
	this.primarySupplyQty = primarySupplyQty;
}
	public Double getPrimaryPendingQty() {
	return primaryPendingQty;
}
public void setPrimaryPendingQty(Double primaryPendingQty) {
	this.primaryPendingQty = primaryPendingQty;
}
	public Double getSecondaryConvUnit() {
	return secondaryConvUnit;
}
public void setSecondaryConvUnit(Double secondaryConvUnit) {
	this.secondaryConvUnit = secondaryConvUnit;
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
	public Integer getBookedBy() {
	return bookedBy;
}
public void setBookedBy(Integer bookedBy) {
	this.bookedBy = bookedBy;
}
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
//	public String getSalesOrderNumber() {
//		return salesOrderNumber;
//	}
//	public void setSalesOrderNumber(String salesOrderNumber) {
//		this.salesOrderNumber = salesOrderNumber;
//	}
	
	
	public Integer getItemId() {
		return itemId;
	}
	
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
	public Integer getSalesOrderAutoId() {
		return salesOrderAutoId;
	}
	public void setSalesOrderAutoId(Integer salesOrderAutoId) {
		this.salesOrderAutoId = salesOrderAutoId;
	}
	
	public Integer getMeasurementUnitId() {
		return measurementUnitId;
	}
	public void setMeasurementUnitId(Integer measurementUnitId) {
		this.measurementUnitId = measurementUnitId;
	}
	
	public MastersDTO getMasterUnit() {
		return masterUnit;
	}
	public void setMasterUnit(MastersDTO masterUnit) {
		this.masterUnit = masterUnit;
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
	/*	double itemValue=getItemValue();
		System.out.println("Sales Order DTO............Item Value "+itemValue);
		double vatPerc=getVatPerc();
		vatAmount=itemValue*vatPerc/100.0;*/
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
		/*double itemValue=getItemValue();
		double cstPerc=getCstPerc();
		cstAmount=itemValue*cstPerc/100.0;*/
		
		return cstAmount;
	}
	public void setCstAmount(Double cstAmount) {
		this.cstAmount = cstAmount;
	}
	public Double getDiscountPerc() {		
		return getDiscountPer();
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
	//Amount = (Item Value - Discount Amt + Excise Amt + Tax Amt) (non editable)
	public Double getNetAmount() {
		if(netAmount==null)
			netAmount=0.0;		
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
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	
	
	public Double getItemValue() {
	
		if(itemValue==null)
			itemValue=0.0;
		return itemValue;
		
	}
	public void setItemValue(Double itemValue) {
		this.itemValue = itemValue;
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

//		if("saleswithInState".equals(partyInvoiceType))
//			taxPerc=vatPerc;
//		if(!"saleswithInState".equals(partyInvoiceType))
//			taxPerc=cstPerc;		
		
		if(taxPerc==null)
			taxPerc=0.0;
		return taxPerc;
	}
	public void setTaxPerc(Double taxPerc) {
		this.taxPerc = taxPerc;
	}
	
	// Tax Amount = (Item Value+ Excise Amt - Discount Amt ) * Tax % 
	public Double getTaxAmount() {		
		if(taxAmount==null)
			taxAmount=0.0;
		return taxAmount;
	}
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;	
		
	}
	public String getPartyInvoiceType() {
		return partyInvoiceType;
	}
	public void setPartyInvoiceType(String partyInvoiceType) {
		this.partyInvoiceType = partyInvoiceType;
	}
	public String getTransactionSeries() {
		return transactionSeries;
	}
	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}
	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}
	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
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
	
	public Integer getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}
	
	public Integer getFullFillByInvoice() {
		return fullFillByInvoice;
	}
	public void setFullFillByInvoice(Integer fullFillByInvoice) {
		this.fullFillByInvoice = fullFillByInvoice;
	}
	/*@Override
	public String toString() {
		return "SalesOrderDetailDTO [itemId=" + itemId + ", itemName="
				+ itemName + ", vatPerc=" + vatPerc + ", cstPerc=" + cstPerc
				+ ", taxPerc=" + taxPerc + "]";
	}*/
	@Override
	public String toString() {
		return "SalesOrderDetailDTO [sno=" + sno + ", transactionSeries="
				+ transactionSeries + ", salesOrderAutoId=" + salesOrderAutoId
				+ ", salesOrderNumber=" + salesOrderNumber + ", itemId="
				+ itemId + ", itemName=" + itemName + ", masterUnit="
				+ masterUnit + ", quantity=" + quantity + ", salesRate="
				+ salesRate + ", excisePerc=" + excisePerc + ", exciseAmount="
				+ exciseAmount + ", vatPerc=" + vatPerc + ", vatAmount="
				+ vatAmount + ", cstPerc=" + cstPerc + ", cstAmount="
				+ cstAmount + ", discountPerc=" + discountPerc
				+ ", discountAmount=" + discountAmount + ", netAmount="
				+ netAmount + ", itemRemark=" + itemRemark + ", packingDetail="
				+ packingDetail + ", deliveryDays=" + deliveryDays
				+ ", deletedFlag=" + deletedFlag + ", itemValue=" + itemValue
				+ ", taxPerc=" + taxPerc + ", taxAmount=" + taxAmount
				+ ", partyInvoiceType=" + partyInvoiceType + ", supplyQty="
				+ supplyQty + ", pendingQty=" + pendingQty + ", invoiceNumber="
				+ invoiceNumber + ", activeStatus=" + activeStatus
				+ ", fullFillByInvoice=" + fullFillByInvoice
				+ ", transientObject=" + transientObject
				+ ", educationCessAmount=" + educationCessAmount
				+ ", highEducationCessAmount=" + highEducationCessAmount
				+ ", educationCessPerc=" + educationCessPerc
				+ ", highEducationCessPerc=" + highEducationCessPerc
				+ ", qtyPerPacket=" + qtyPerPacket + ", noOfPacket="
				+ noOfPacket + ", bookedBy=" + bookedBy + ", primaryUOM="
				+ primaryUOM + ", primaryUnit=" + primaryUnit
				+ ", secondaryConvUnit=" + secondaryConvUnit + "]";
	}
	
	
	
}
