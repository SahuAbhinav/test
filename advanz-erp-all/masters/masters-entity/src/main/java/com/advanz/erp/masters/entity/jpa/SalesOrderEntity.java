package com.advanz.erp.masters.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "t_sales_order_mast")
public class SalesOrderEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "transaction_series", length = 2)
	private String transactionSeries;
	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name = "sales_order_auto_id", unique = true, updatable = false, length = 11)
	private Integer salesOrderAutoId;
	
	@Column(name = "finyr", length = 4)
	private String finyr;
	
	@Column(name = "sales_order_number", length = 15)
	private String salesOrderNumber;
	
	

	@Column(name = "sales_order_id", length = 11)
	private Integer salesOrderId;
	
	@Column(name = "sales_order_date")
	private Date salesOrderDate;
	
	@Column(name = "branch_id", length = 11)
	private Integer branchId;
	
	@Column(name = "order_receipt_date")
	private Date orderReceiptDate;
	
	@Column(name = "quotation_number", length = 15)
	private String quotationNumber;
	
	@Column(name = "party_id", length = 11)
	private Integer partyId;
	
	@Column(name = "order_taken_by", length = 35)
	private String orderTakenBy;
	
	@Column(name = "order_time")
	private Date orderTime;
	
	@Column(name = "desire_delivery_date")
	private Date desireDeliveryDate;
	
	@Column(name = "planned_deliver_date")
	private Date plannedDeliverDate;
	
	@Column(name = "sales_order_remark", length = 500)
	private String salesOrderRemark;
	
	@Column(name = "item_value")
	private Double itemValue;
	
	@Column(name = "excise_duty_amount")
	private Double exciseDutyAmount;
	
	@Column(name = "education_cess_amount")
	private Double educationCessAmount;
	
	@Column(name = "high_education_cess_amount")
	private Double highEducationCessAmount;
	
	@Column(name = "discount_amount")
	private Double discountAmount;
	
	@Column(name = "vat_amount")
	private Double vatAmount;
	
	@Column(name = "cst_amount")
	private Double cstAmount;
	
	@Column(name = "other_amount")
	private Double otherAmount;
	
	@Column(name = "so_net_amount")
	private Double soNetAmount;
	
	@Column(name = "taxable_amount")
	private Double taxableAmount;
	
	@Column(name = "party_po_no", length = 25)
	private String partyPoNo;
	
	@Column(name = "party_po_date")
	private Date partyPoDate;
	
	@Column(name = "others_detail", length = 25)
	private String othersDetail;
	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}
	public String getTransactionSeries() {
		return transactionSeries;
	}

	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	public Integer getSalesOrderAutoId() {
		return salesOrderAutoId;
	}

	public void setSalesOrderAutoId(Integer salesOrderAutoId) {
		this.salesOrderAutoId = salesOrderAutoId;
	}

	public String getFinyr() {
		return finyr;
	}

	public void setFinyr(String finyr) {
		this.finyr = finyr;
	}

	

	public Integer getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(Integer salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public Date getSalesOrderDate() {
		return salesOrderDate;
	}

	public void setSalesOrderDate(Date salesOrderDate) {
		this.salesOrderDate = salesOrderDate;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Date getOrderReceiptDate() {
		return orderReceiptDate;
	}

	public void setOrderReceiptDate(Date orderReceiptDate) {
		this.orderReceiptDate = orderReceiptDate;
	}

	public String getQuotationNumber() {
		return quotationNumber;
	}

	public void setQuotationNumber(String quotationNumber) {
		this.quotationNumber = quotationNumber;
	}

	public Integer getPartyId() {
		return partyId;
	}

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	public String getOrderTakenBy() {
		return orderTakenBy;
	}

	public void setOrderTakenBy(String orderTakenBy) {
		this.orderTakenBy = orderTakenBy;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getDesireDeliveryDate() {
		return desireDeliveryDate;
	}

	public void setDesireDeliveryDate(Date desireDeliveryDate) {
		this.desireDeliveryDate = desireDeliveryDate;
	}

	public Date getPlannedDeliverDate() {
		return plannedDeliverDate;
	}

	public void setPlannedDeliverDate(Date plannedDeliverDate) {
		this.plannedDeliverDate = plannedDeliverDate;
	}

	public String getSalesOrderRemark() {
		return salesOrderRemark;
	}

	public void setSalesOrderRemark(String salesOrderRemark) {
		this.salesOrderRemark = salesOrderRemark;
	}

	

	

	public Double getItemValue() {
		return itemValue;
	}

	public void setItemValue(Double itemValue) {
		this.itemValue = itemValue;
	}

	public Double getExciseDutyAmount() {
		return exciseDutyAmount;
	}

	public void setExciseDutyAmount(Double exciseDutyAmount) {
		this.exciseDutyAmount = exciseDutyAmount;
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

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(Double vatAmount) {
		this.vatAmount = vatAmount;
	}

	public Double getCstAmount() {
		return cstAmount;
	}

	public void setCstAmount(Double cstAmount) {
		this.cstAmount = cstAmount;
	}

	public Double getOtherAmount() {
		return otherAmount;
	}

	public void setOtherAmount(Double otherAmount) {
		this.otherAmount = otherAmount;
	}

	public Double getSoNetAmount() {
		return soNetAmount;
	}

	public void setSoNetAmount(Double soNetAmount) {
		this.soNetAmount = soNetAmount;
	}

	public Double getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(Double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	public String getPartyPoNo() {
		return partyPoNo;
	}

	public void setPartyPoNo(String partyPoNo) {
		this.partyPoNo = partyPoNo;
	}

	public Date getPartyPoDate() {
		return partyPoDate;
	}

	public void setPartyPoDate(Date partyPoDate) {
		this.partyPoDate = partyPoDate;
	}

	public String getOthersDetail() {
		return othersDetail;
	}

	public void setOthersDetail(String othersDetail) {
		this.othersDetail = othersDetail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
