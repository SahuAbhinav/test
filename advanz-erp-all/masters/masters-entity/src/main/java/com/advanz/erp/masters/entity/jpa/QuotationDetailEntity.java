package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name="t_quotation_detail")
public class QuotationDetailEntity extends BaseEntity {
	
	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="sno")
		private Integer sno;
	
	@Column(name="quotation_auto_id")
	private Integer quotationAutoId;
	
	@Column(name="transaction_series")
	private String transactionSeries;
	
	@Column(name="quotation_number")
	private String quotationNumber;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private ItemEntity itemEntity;
	
	@ManyToOne
	@JoinColumn(name="measurement_Unit_id")
	private MastersEntity measurementUnitMasterEntity;
	
	@Column(name="quantity")
		private Double quantity;
	
	@Column(name="sales_rate")
		private Double salesRate;
	
	@Column(name="item_value")
	private Double itemValue;
	
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
		private Double discountPerc;
	
	@Column(name="discount_amount")
		private Double discountAmount;
	
	@Column(name="net_amount")
		private Double netAmount;	
	
	@Column(name="packing_detail")
	private String packingDetail;
	
	@Column(name="delivery_days")
	private Integer deliveryDays;

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public Integer getQuotationAutoId() {
		return quotationAutoId;
	}

	public void setQuotationAutoId(Integer quotationAutoId) {
		this.quotationAutoId = quotationAutoId;
	}

	public String getTransactionSeries() {
		return transactionSeries;
	}

	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	public ItemEntity getItemEntity() {
		return itemEntity;
	}

	public void setItemEntity(ItemEntity itemEntity) {
		this.itemEntity = itemEntity;
	}

	public MastersEntity getMeasurementUnitMasterEntity() {
		return measurementUnitMasterEntity;
	}

	public void setMeasurementUnitMasterEntity(
			MastersEntity measurementUnitMasterEntity) {
		this.measurementUnitMasterEntity = measurementUnitMasterEntity;
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

	public Double getItemValue() {
		return itemValue;
	}

	public void setItemValue(Double itemValue) {
		this.itemValue = itemValue;
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

	public String getQuotationNumber() {
		return quotationNumber;
	}

	public void setQuotationNumber(String quotationNumber) {
		this.quotationNumber = quotationNumber;
	}
	

		
	}


