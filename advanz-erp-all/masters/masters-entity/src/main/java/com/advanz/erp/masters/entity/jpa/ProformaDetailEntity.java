package com.advanz.erp.masters.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;
@javax.persistence.Entity
@Table(name = "t_proforma_detail")
public class ProformaDetailEntity extends BaseEntity{
	@Id 
	@GeneratedValue(generator="system-incr")
	@GenericGenerator(name="system-incr", strategy = "increment")
	@Column(name="sno")
	private Integer sno;
	
	@Column(name="transaction_series") 
	private String transactionSeries;
	
	@Column(name="invoice_number") 
	private String invoiceNumber;
	
	
	
	@Column(name="invoice_auto_id")
	private Integer invoiceAutoId;
	
	
	@Column(name="item_id")
	private Integer itemId;
	
	@Column(name="measurement_Unit_id")
	private Integer measurementUnitId;
	
	@Column(name="batch_no")
	private String batchNo;	
	
	@Column(name="expiry_date")
	private Date expiryDate;
	
	@Column(name="quantity")
	private Double quantity;
	
	@Column(name="sales_rate")
	private Double salesRate;
	
	@Column(name="excise_perc")
	private Double excisePerc;
	
	@Column(name="excise_amount")
	private Double exciseAmount;
	
	@Column(name="vat_perc")
	private Double vatPerc;
	
	@Column(name="vat_amount")
	private Double vatAmount;
	
	@Column(name="cst_perc")
	private Double cstPerc;
	
	@Column(name="cst_amount")
	private Double cstAmount;
	
	@Column(name="discount_per")
	private Double discountPer;
	
	@Column(name="discount_amount")
	private Double discountAmount;
	
	@Column(name="packing_detail")
	private String packingDetail;
	
	@Column(name="net_amount")
	private Double netAmount;

	@Column(name="item_value")
	private Double itemValue;
	
	//@Column(name="item_name")
	//private String itemName;
	
     // CR
	
	@Column(name="qty_per_packet")
	private Double qtyPerPacket;
	
	@Column(name="no_of_packet")
	private Double noOfPacket;
	
	@Column(name="edu_cess_amount")
	private Double eduCessAmount;
	
	@Column(name="h_edu_cess_amount")
	private Double heduCessAmount;
	
	@Column(name="assessable_value")
	private Double assessableValue;
	
	@Column(name="booked_by")
	private Integer bookedBy;
	
	@Column(name="primary_uom")
	private Integer primaryUOM;
	
	@Column(name="primary_unit")
	private Double primaryUnit;
	
	@Column(name="sales_order_item")
	private Integer salesOrderItem;
	
	@Column(name="secondary_conv_unit")
	private Double secondaryConvUnit;
	
	
	
	public Double getSecondaryConvUnit() {
		return secondaryConvUnit;
	}
	public void setSecondaryConvUnit(Double secondaryConvUnit) {
		this.secondaryConvUnit = secondaryConvUnit;
	}
	public Integer getSalesOrderItem() {
		return salesOrderItem;
	}
	public void setSalesOrderItem(Integer salesOrderItem) {
		this.salesOrderItem = salesOrderItem;
	}
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
	public String getTransactionSeries() {
		return transactionSeries;
	}
	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}
	/*public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}*/
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
	

}
