package com.advanz.erp.masters.entity.jpa;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name="t_sales_order_mast")
public class SalesOrderMasterEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sales_order_auto_id")
	private Integer salesOrderAutoId;
	
	@Column(name="transaction_series")
	private String transactionSeries;
	
	
	@Column(name="finyr")
	private String finYear;
	
	
	@Column(name="sales_order_number",unique=true)
	private String salesOrderNumber;
	
	@Column(name="sales_order_id")
	private Integer salesOrderId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="sales_order_date")
	private Date salesOrderDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="order_receipt_date")
	private Date orderReceiptDate;
	
	@Column(name="quotation_number")
	private String quotationNumber;
	
	@ManyToOne
	@JoinColumn(name="party_id")
	private PartyEntity partyEntity;
	
	@Column(name="order_taken_by")
	private String orderTakenBy;
	

	@Column(name="order_time")
	private Time orderTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="desire_delivery_date")
	private Date desireDeliveryDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="planned_deliver_date")
	private Date plannedDeliveryDate;
	
	@Column(name="sales_order_remark")
	private String salesOrderRemark;
	
	@Column(name="item_value")
	private Double itemValue;
	
	@Column(name="excise_duty_amount")
	private Double exciseDutyAmount;
	
	@Column(name="education_cess_perc")
	private Double educationCessPerc;
	
	@Column(name="education_cess_amount")
	private Double educationCessAmount;
	
	@Column(name="high_education_cess_perc")
	private Double highEducationCessPerc;
	
	@Column(name="high_education_cess_amount")
	private Double highEducationCessAmount;
	
	@Column(name="discount_amount")
	private Double discountAmount;
	
	@Column(name="vat_amount")
	private Double vatAmount;
	
	@Column(name="cst_amount")
	private Double cstAmount;
	
	@Column(name="other_amount")
	private Double otherAmount;
	
	@Column(name="so_net_amount")
	private Double soNetAmount;
	
	@Column(name="taxable_amount")
	private Double taxableAmount;
	
	@Column(name="party_po_no")
	private String patyPoNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="party_po_date")
	private Date partyPoDate;
	
	
	@Column(name="so_valid_upto_date")
	private Date soValidUptoDate;
	
	
	
	
	@Column(name="others_detail")
	private String othersDetail;
	
	
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="sales_order_auto_id")
	private List<SalesOrderDetailEntity> salesOrderDetailEntities;
	
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	private BranchEntity branchEntity;
	
	@Column(name="consignee_id")
	private Integer consigneeId;
	
	@Column(name="freight_type")
	private String freightType;
	
	@Column(name="freight_amt")
	private Double freightAmt;
	
	@Column(name="packing_forwarding")
	private Double packingForwarding;
	
	@Column(name="terms_of_payment")
	private String termsOfPayment;
	
	@Column(name="terms_of_delivery")
	private String termsOfDelivery;
	
	@Column(name="ship_to_address")
	private String shipToAddress;
	
	@Column(name="item_group_flag_id")
	private Integer itemGroupFlagId;

	
	public Integer getItemGroupFlagId() {
		return itemGroupFlagId;
	}
	public void setItemGroupFlagId(Integer itemGroupFlagId) {
		this.itemGroupFlagId = itemGroupFlagId;
	}
	public Date getSoValidUptoDate() {
		return soValidUptoDate;
	}
	public void setSoValidUptoDate(Date soValidUptoDate) {
		this.soValidUptoDate = soValidUptoDate;
	}
	public String getTransactionSeries() {
		return transactionSeries;
	}
	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}
	public String getFinYear() {
		return finYear;
	}
	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}
	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}
	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
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
	
	
	public PartyEntity getPartyEntity() {
		return partyEntity;
	}
	public void setPartyEntity(PartyEntity partyEntity) {
		this.partyEntity = partyEntity;
	}
	public String getOrderTakenBy() {
		return orderTakenBy;
	}
	public void setOrderTakenBy(String orderTakenBy) {
		this.orderTakenBy = orderTakenBy;
	}
	public Time getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Time orderTime) {
		this.orderTime = orderTime;
	}
	public Date getDesireDeliveryDate() {
		return desireDeliveryDate;
	}
	public void setDesireDeliveryDate(Date desireDeliveryDate) {
		this.desireDeliveryDate = desireDeliveryDate;
	}
	public Date getPlannedDeliveryDate() {
		return plannedDeliveryDate;
	}
	public void setPlannedDeliveryDate(Date plannedDeliveryDate) {
		this.plannedDeliveryDate = plannedDeliveryDate;
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
	public String getPatyPoNumber() {
		return patyPoNumber;
	}
	public void setPatyPoNumber(String patyPoNumber) {
		this.patyPoNumber = patyPoNumber;
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
	public List<SalesOrderDetailEntity> getSalesOrderDetailEntities() {
		return salesOrderDetailEntities;
	}
	public void setSalesOrderDetailEntities(
			List<SalesOrderDetailEntity> salesOrderDetailEntities) {
		this.salesOrderDetailEntities = salesOrderDetailEntities;
	}
	public BranchEntity getBranchEntity() {
		return branchEntity;
	}
	public void setBranchEntity(BranchEntity branchEntity) {
		this.branchEntity = branchEntity;
	}
	public Integer getSalesOrderAutoId() {
		return salesOrderAutoId;
	}
	public void setSalesOrderAutoId(Integer salesOrderAutoId) {
		this.salesOrderAutoId = salesOrderAutoId;
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
	
	
	public Integer getConsigneeId() {
		return consigneeId;
	}
	public void setConsigneeId(Integer consigneeId) {
		this.consigneeId = consigneeId;
	}
	@Override
	public String toString() {
		return "SalesOrderMasterEntity [salesOrderAutoId=" + salesOrderAutoId
				+ ", itemValue=" + itemValue + ", exciseDutyAmount="
				+ exciseDutyAmount + ", educationCessAmount="
				+ educationCessAmount + ", highEducationCessAmount="
				+ highEducationCessAmount + ", discountAmount="
				+ discountAmount + ", vatAmount=" + vatAmount + ", cstAmount="
				+ cstAmount + ", otherAmount=" + otherAmount + ", soNetAmount="
				+ soNetAmount + ", taxableAmount=" + taxableAmount + "]";
	}
	public String getFreightType() {
		return freightType;
	}
	public void setFreightType(String freightType) {
		this.freightType = freightType;
	}
	public Double getFreightAmt() {
		return freightAmt;
	}
	public void setFreightAmt(Double freightAmt) {
		this.freightAmt = freightAmt;
	}
	public Double getPackingForwarding() {
		return packingForwarding;
	}
	public void setPackingForwarding(Double packingForwarding) {
		this.packingForwarding = packingForwarding;
	}
	public String getTermsOfPayment() {
		return termsOfPayment;
	}
	public void setTermsOfPayment(String termsOfPayment) {
		this.termsOfPayment = termsOfPayment;
	}
	public String getTermsOfDelivery() {
		return termsOfDelivery;
	}
	public void setTermsOfDelivery(String termsOfDelivery) {
		this.termsOfDelivery = termsOfDelivery;
	}
	public String getShipToAddress() {
		return shipToAddress;
	}
	public void setShipToAddress(String shipToAddress) {
		this.shipToAddress = shipToAddress;
	}
	

}
