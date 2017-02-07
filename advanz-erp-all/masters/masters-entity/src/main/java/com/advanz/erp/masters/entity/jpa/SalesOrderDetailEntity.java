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
@Table(name="t_sales_order_detail")
public class SalesOrderDetailEntity extends BaseEntity {
	
	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="sno")
		private Integer sno;
	
	@Column(name="sales_order_auto_id")
	private Integer salesOrderAutoId;
	
	@Column(name="sales_order_number")
	private String salesOrderNumber;
	
	@Column(name="transaction_series")
	private String transactionSeries;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private ItemEntity itemEntity;
	
	//@Column(name="measurement_Unit_id")
	//private Integer measurementUnitId;
	@ManyToOne
	@JoinColumn(name="measurement_Unit_id")
	private MastersEntity measurementUnitMasterEntity;
	
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
		private Double discountPerc;
	
	@Column(name="discount_amount")
		private Double discountAmount;
	
	@Column(name="net_amount")
		private Double netAmount;
	
	@Column(name="Item_remark")
		private String itemRemark;
	
	@Column(name="packing_detail")
	private String packingDetail;
	
	@Column(name="delivery_days")
	private Integer deliveryDays;
	
	@Column(name="education_cess_amount")
	private Double educationCessAmount;
	
	@Column(name="high_education_cess_amount")
	private Double highEducationCessAmount;
	
	
	@Column(name="supply_qty")
	private Double supplyQty;
	
	@Column(name="pending_qty")
	private Double pendingQty;
	
	@Column(name="invoice_number")
	private String invoiceNumber;
	
	@Column(name="qty_per_packet")
	private Double qtyPerPacket;
	
	@Column(name="no_of_packet")
	private Double noOfPacket;
	
	@Column(name="active_status")
	private Integer activeStatus;
	
	@Column(name="full_fill_by_invoice")
	private Integer fullFillByInvoice;
	
	@Column(name="booked_by")
	private Integer bookedBy;
	
	@Column(name="primary_uom")
	private Integer primaryUOM;
	
	@Column(name="primary_unit")
	private Double primaryUnit;
	
	@Column(name="secondary_conv_unit")
	private Double secondaryConvUnit;
	
	@Column(name="primary_pending_qty")
	private Double primaryPendingQty;
	
	@Column(name="primary_supply_qty")
	private Double primarySupplyQty;
	
	
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
		public String getItemRemark() {
			return itemRemark;
		}
		public void setItemRemark(String itemRemark) {
			this.itemRemark = itemRemark;
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
		public Integer getSalesOrderAutoId() {
			return salesOrderAutoId;
		}
		public void setSalesOrderAutoId(Integer salesOrderAutoId) {
			this.salesOrderAutoId = salesOrderAutoId;
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
		
		
	}


