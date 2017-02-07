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
@Table(name = "t_purchase_order_detail")
public class PurchaseOrderDetailEntity extends BaseEntity {

	public Double getExcisePerc() {
		return excisePerc;
	}

	public void setExcisePerc(Double excisePerc) {
		this.excisePerc = excisePerc;
	}

	public Double getItemQuantity() {
		return itemQuantity;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name = "sno")
	private Integer sno;

	@Column(name = "po_auto_id")
	private Integer poAutoId;

	@Column(name = "transaction_series")
	private String transactionSeries;

	/**
	 * @return the poAutoId
	 */
	@Column(name = "purchase_rate")
	private Double purchaseRate;
	
	
	@Column(name="po_number")
	private String purchaseOrderNumber;
	
	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public Integer getPoAutoId() {
		return poAutoId;
	}

	/**
	 * @param poAutoId
	 *            the poAutoId to set
	 */
	public void setPoAutoId(Integer poAutoId) {
		this.poAutoId = poAutoId;
	}

	public Double getPurchaseRate() {
		return purchaseRate;
	}

	public void setPurchaseRate(Double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}

	/**
	 * @return the transactionSeries
	 */
	public String getTransactionSeries() {
		return transactionSeries;
	}

	/**
	 * @param transactionSeries
	 *            the transactionSeries to set
	 */
	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	@ManyToOne
	@JoinColumn(name = "item_id")
	private ItemEntity itemEntity;

	@Column(name = "measurement_Unit_id")
	private Integer measurementUnitId;

	@Column(name = "quantity")
	private Double itemQuantity;

	
	@Column(name = "pending_quantity")
	private Double pendingQuantity;
	

	public Double getPendingQuantity() {
		return pendingQuantity;
	}

	public void setPendingQuantity(Double pendingQuantity) {
		this.pendingQuantity = pendingQuantity;
	}

	@Column(name = "excise_perc")
	private Double excisePerc;

	@Column(name = "vat_perc")
	private Double vatPerc;

	@Column(name = "cst_perc")
	private Double cstPerc;

	@Column(name = "discount_per")
	private Double discountPerc;

	@Column(name = "discount_amount")
	private Double discountAmount;

	@Column(name = "net_amount")
	private Double netAmount;

	@Column(name = "Item_remark")
	private String itemRemark;

	@Column(name = "under_delivery_tolerance_per")
	private Double itemUnderDeliveryTolerancePer;

	@Column(name = "over_delivery_tolerance_per")
	private Double overDeliveryTolerancePer;

	@Column(name = "education_cess_amount")
	private Double educationCessAmount;

	@Column(name = "high_education_cess_amount")
	private Double highEducationCessAmount;
	
	@Column(name = "indent_number")
	private String indentNumber;
	
	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public ItemEntity getItemEntity() {
		return itemEntity;
	}

	public void setItemEntity(ItemEntity itemEntity) {
		this.itemEntity = itemEntity;
	}

	public Integer getMeasurementUnitId() {
		return measurementUnitId;
	}

	public void setMeasurementUnitId(Integer measurementUnitId) {
		this.measurementUnitId = measurementUnitId;
	}

	public Double getQuantityItem() {
		return itemQuantity;
	}

	public void setItemQuantity(Double itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public Double getExciseAmount() {
		return excisePerc;
	}

	public void setExciseAmount(Double excisePerc) {
		this.excisePerc = excisePerc;
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

	public void setItemUnderDeliveryTolerancePer(Double itemUnderDeliveryTolerancePer) {
		this.itemUnderDeliveryTolerancePer = itemUnderDeliveryTolerancePer;
	}

	public Double getOverDeliveryTolerancePer() {
		return overDeliveryTolerancePer;
	}

	public void setOverDeliveryTolerancePer(Double overDeliveryTolerancePer) {
		this.overDeliveryTolerancePer = overDeliveryTolerancePer;
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

	public String getIndentNumber() {
		return indentNumber;
	}

	public void setIndentNumber(String indentNumber) {
		this.indentNumber = indentNumber;
	}

}
