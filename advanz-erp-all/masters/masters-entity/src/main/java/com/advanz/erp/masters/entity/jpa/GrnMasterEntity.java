package com.advanz.erp.masters.entity.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name="t_grn_mast")
public class GrnMasterEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Column(name="transaction_series")
	private String transactionSeries;
	
	@Id
	@GeneratedValue(generator = "system-incr")
	@GenericGenerator(name = "system-incr", strategy = "increment")
	@Column(name = "grn_auto_id")
	private Integer grnAutoId;
	
	
	@Column(name="finyr")
	private String finYear;
	
	@Column(name="grn_number")
	private String grnNumber;
	
	@Column(name="grn_id")
	private Integer grnId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="grn_date")
	private Date grnDate;
	
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	private BranchEntity branchEntity;
	
	@ManyToOne
	@JoinColumn(name="supplier_id")
	private PartyEntity partyEntity;
	
	@ManyToOne
	@JoinColumn(name="purchase_order_number")
	private PurchaseOrderMasterEntity purchaseOrderEntity;
	
	@ManyToOne
	@JoinColumn(name="item_group_flag_id")
	private ItemGroupFlagEntity itemGroupFlagEntity;
	
	@Column(name="supplier_bill_no")
	private String supplierBillNo;
	
	@Column(name="supplier_bill_date")
	private Date supplierBillDate;
	
	@Column(name="supplier_bill_amount")
	private Double supplierBillAmount;
	
	@ManyToOne
	@JoinColumn(name="transport_id")
	private TransporterEntity transportEnttity;
	
	
	
	@Column(name="supplier_bill_excise_amt")
	private Double supplierBillExciseAmt;
	
	@Column(name="goods_receive_type")
	private String goodsReceiveType;
	
	@Column(name="vehicle_number")
	private String vehicleNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="lr_Date")
	private Date lrDate;
	
	@Column(name="lr_no")
	private String lrNo;
	
	@Column(name="form_req_flag")
	private Integer formReqFlag;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="form_date")
	private Date formDate;
	
	@Column(name="form_no")
	private String formnumber;
	

	@Column(name="form_type_id")
	private Integer formTypeId;
	
	@Column(name="freight_type")
	private String freightYype;
	
	@Column(name="freight_amount")
	private Double freightAmount;
	
	@Column(name="qa_check_required")
	private Integer qaCheckRequired;
	
	@Column(name="grn_approval_flag")
	private Integer grnApprovalFlag;
	
	@Column(name="education_cess_amount")
	private Double educationCessAmount;
	
	@Column(name="high_education_cess_amount")
	private Double highEducationCessAmount;
	 
	
	
	@Column(name="grn_remark")
	private String grnRemark;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="grn_auto_id")
	private List<GrnDetailEntity> grnDetailEntity;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="goods_receive_date")
	private Date goodsReceiveDate;
	
	//21_may-2013
	@Column(name="item_value")
	private Double itemValue;
	
	@Column(name="discount_amount")
	private Double discountAmount;
	
	@Column(name="vat_amount")
	private Double vatAmount;
	
	@Column(name="cst_amount")
	private Double cstAmount;
	
	@Column(name="grn_net_amount")
	private Double grnNetAmount;

	@Column(name="aproved")
	private Integer aproved;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="aproved_date")
	private Date aprovedDate;
	
	@Column(name="show_all_party_flag")
	private Integer showAllPartyFlag;
	
	
	@Column(name="received_bill_excise_amt")
	private Double receivedBillExciseAmount;
	
	@Column(name="received_education_cess_amount")
	private Double receivedEducationCessAmount;
	
	@Column(name="received_high_education_cess_amount")
	private Double receivedHighEducationCessAmount;
	
	@Column(name="received_item_vat_amount")
	private Double receivedItemVatAmount;
	
	@Column(name="entry_tax")
	private Double entryTax;
	
	
	@Column(name="other_detail")
	private String otherDetail;

	@Column(name="other_amount")
	private Double otherAmount;


	
	
	
	public String getOtherDetail() {
		return otherDetail;
	}


	public void setOtherDetail(String otherDetail) {
		this.otherDetail = otherDetail;
	}


	public Double getOtherAmount() {
		return otherAmount;
	}


	public void setOtherAmount(Double otherAmount) {
		this.otherAmount = otherAmount;
	}


	public Double getItemValue() {
		return itemValue;
	}


	public void setItemValue(Double itemValue) {
		this.itemValue = itemValue;
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


	public Double getGrnNetAmount() {
		return grnNetAmount;
	}


	public void setGrnNetAmount(Double grnNetAmount) {
		this.grnNetAmount = grnNetAmount;
	}


	public Date getGoodsReceiveDate() {
		return goodsReceiveDate;
	}


	public void setGoodsReceiveDate(Date goodsReceiveDate) {
		this.goodsReceiveDate = goodsReceiveDate;
	}


	/**
	 * @return the grnDetailEntity
	 */
	public List<GrnDetailEntity> getGrnDetailEntity() {
		return grnDetailEntity;
	}


	/**
	 * @param grnDetailEntity the grnDetailEntity to set
	 */
	public void setGrnDetailEntity(List<GrnDetailEntity> grnDetailEntity) {
		this.grnDetailEntity = grnDetailEntity;
	}


	/**
	 * @return the transactionSeries
	 */
	public String getTransactionSeries() {
		return transactionSeries;
	}


	/**
	 * @param transactionSeries the transactionSeries to set
	 */
	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}


	/**
	 * @return the grnAutoId
	 */
	public Integer getGrnAutoId() {
		return grnAutoId;
	}


	/**
	 * @param grnAutoId the grnAutoId to set
	 */
	public void setGrnAutoId(Integer grnAutoId) {
		this.grnAutoId = grnAutoId;
	}


	/**
	 * @return the finYear
	 */
	public String getFinYear() {
		return finYear;
	}


	/**
	 * @param finYear the finYear to set
	 */
	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}


	/**
	 * @return the grnNumber
	 */
	public String getGrnNumber() {
		return grnNumber;
	}


	/**
	 * @param grnNumber the grnNumber to set
	 */
	public void setGrnNumber(String grnNumber) {
		this.grnNumber = grnNumber;
	}


	/**
	 * @return the grnId
	 */
	public Integer getGrnId() {
		return grnId;
	}


	/**
	 * @param grnId the grnId to set
	 */
	public void setGrnId(Integer grnId) {
		this.grnId = grnId;
	}


	/**
	 * @return the grnDate
	 */
	public Date getGrnDate() {
		return grnDate;
	}


	/**
	 * @param grnDate the grnDate to set
	 */
	public void setGrnDate(Date grnDate) {
		this.grnDate = grnDate;
	}


	/**
	 * @return the branchEntity
	 */
	public BranchEntity getBranchEntity() {
		return branchEntity;
	}


	/**
	 * @param branchEntity the branchEntity to set
	 */
	public void setBranchEntity(BranchEntity branchEntity) {
		this.branchEntity = branchEntity;
	}


	/**
	 * @return the partyEntity
	 */
	public PartyEntity getPartyEntity() {
		return partyEntity;
	}


	/**
	 * @param partyEntity the partyEntity to set
	 */
	public void setPartyEntity(PartyEntity partyEntity) {
		this.partyEntity = partyEntity;
	}


	/**
	 * @return the purchaseOrderEntity
	 */
	public PurchaseOrderMasterEntity getPurchaseOrderEntity() {
		return purchaseOrderEntity;
	}


	/**
	 * @param purchaseOrderEntity the purchaseOrderEntity to set
	 */
	public void setPurchaseOrderEntity(PurchaseOrderMasterEntity purchaseOrderEntity) {
		this.purchaseOrderEntity = purchaseOrderEntity;
	}


	/**
	 * @return the itemGroupFlagEntity
	 */
	public ItemGroupFlagEntity getItemGroupFlagEntity() {
		return itemGroupFlagEntity;
	}


	/**
	 * @param itemGroupFlagEntity the itemGroupFlagEntity to set
	 */
	public void setItemGroupFlagEntity(ItemGroupFlagEntity itemGroupFlagEntity) {
		this.itemGroupFlagEntity = itemGroupFlagEntity;
	}


	/**
	 * @return the supplierBillNo
	 */
	public String getSupplierBillNo() {
		return supplierBillNo;
	}


	/**
	 * @param supplierBillNo the supplierBillNo to set
	 */
	public void setSupplierBillNo(String supplierBillNo) {
		this.supplierBillNo = supplierBillNo;
	}


	/**
	 * @return the supplierBillDate
	 */
	public Date getSupplierBillDate() {
		return supplierBillDate;
	}


	/**
	 * @param supplierBillDate the supplierBillDate to set
	 */
	public void setSupplierBillDate(Date supplierBillDate) {
		this.supplierBillDate = supplierBillDate;
	}


	/**
	 * @return the supplierBillAmount
	 */
	public Double getSupplierBillAmount() {
		return supplierBillAmount;
	}


	/**
	 * @param supplierBillAmount the supplierBillAmount to set
	 */
	public void setSupplierBillAmount(Double supplierBillAmount) {
		this.supplierBillAmount = supplierBillAmount;
	}


	/**
	 * @return the transportEnttity
	 */
	public TransporterEntity getTransportEnttity() {
		return transportEnttity;
	}


	/**
	 * @param transportEnttity the transportEnttity to set
	 */
	public void setTransportEnttity(TransporterEntity transportEnttity) {
		this.transportEnttity = transportEnttity;
	}


	/**
	 * @return the supplierBillExciseAmt
	 */
	public Double getSupplierBillExciseAmt() {
		return supplierBillExciseAmt;
	}


	/**
	 * @param supplierBillExciseAmt the supplierBillExciseAmt to set
	 */
	public void setSupplierBillExciseAmt(Double supplierBillExciseAmt) {
		this.supplierBillExciseAmt = supplierBillExciseAmt;
	}


	/**
	 * @return the goodsReceiveType
	 */
	public String getGoodsReceiveType() {
		return goodsReceiveType;
	}


	/**
	 * @param goodsReceiveType the goodsReceiveType to set
	 */
	public void setGoodsReceiveType(String goodsReceiveType) {
		this.goodsReceiveType = goodsReceiveType;
	}


	/**
	 * @return the vehicleNumber
	 */
	public String getVehicleNumber() {
		return vehicleNumber;
	}


	/**
	 * @param vehicleNumber the vehicleNumber to set
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}


	/**
	 * @return the lrDate
	 */
	public Date getLrDate() {
		return lrDate;
	}


	/**
	 * @param lrDate the lrDate to set
	 */
	public void setLrDate(Date lrDate) {
		this.lrDate = lrDate;
	}


	/**
	 * @return the lrNo
	 */
	public String getLrNo() {
		return lrNo;
	}


	/**
	 * @param lrNo the lrNo to set
	 */
	public void setLrNo(String lrNo) {
		this.lrNo = lrNo;
	}


	/**
	 * @return the formReqFlag
	 */
	public Integer getFormReqFlag() {
		return formReqFlag;
	}


	/**
	 * @param formReqFlag the formReqFlag to set
	 */
	public void setFormReqFlag(Integer formReqFlag) {
		this.formReqFlag = formReqFlag;
	}


	/**
	 * @return the formDate
	 */
	public Date getFormDate() {
		return formDate;
	}


	/**
	 * @param formDate the formDate to set
	 */
	public void setFormDate(Date formDate) {
		this.formDate = formDate;
	}


	/**
	 * @return the formnumber
	 */
	public String getFormnumber() {
		return formnumber;
	}


	/**
	 * @param formnumber the formnumber to set
	 */
	public void setFormnumber(String formnumber) {
		this.formnumber = formnumber;
	}


	/**
	 * @return the formTypeId
	 */
	public Integer getFormTypeId() {
		return formTypeId;
	}


	/**
	 * @param formTypeId the formTypeId to set
	 */
	public void setFormTypeId(Integer formTypeId) {
		this.formTypeId = formTypeId;
	}


	/**
	 * @return the freightYype
	 */
	public String getFreightYype() {
		return freightYype;
	}


	/**
	 * @param freightYype the freightYype to set
	 */
	public void setFreightYype(String freightYype) {
		this.freightYype = freightYype;
	}


	/**
	 * @return the freightAmount
	 */
	public Double getFreightAmount() {
		return freightAmount;
	}


	/**
	 * @param freightAmount the freightAmount to set
	 */
	public void setFreightAmount(Double freightAmount) {
		this.freightAmount = freightAmount;
	}


	/**
	 * @return the qaCheckRequired
	 */
	public Integer getQaCheckRequired() {
		return qaCheckRequired;
	}


	/**
	 * @param qaCheckRequired the qaCheckRequired to set
	 */
	public void setQaCheckRequired(Integer qaCheckRequired) {
		this.qaCheckRequired = qaCheckRequired;
	}


	/**
	 * @return the grnApprovalFlag
	 */
	public Integer getGrnApprovalFlag() {
		return grnApprovalFlag;
	}


	/**
	 * @param grnApprovalFlag the grnApprovalFlag to set
	 */
	public void setGrnApprovalFlag(Integer grnApprovalFlag) {
		this.grnApprovalFlag = grnApprovalFlag;
	}


	/**
	 * @return the grnRemark
	 */
	public String getGrnRemark() {
		return grnRemark;
	}


	/**
	 * @param grnRemark the grnRemark to set
	 */
	public void setGrnRemark(String grnRemark) {
		this.grnRemark = grnRemark;
	}


	/**
	 * @return the serialversionuid
	 */
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


	public Integer getAproved() {
		return aproved;
	}


	public void setAproved(Integer aproved) {
		this.aproved = aproved;
	}


	public Date getAprovedDate() {
		return aprovedDate;
	}


	public void setAprovedDate(Date aprovedDate) {
		this.aprovedDate = aprovedDate;
	}


	public Integer getShowAllPartyFlag() {
		return showAllPartyFlag;
	}


	public void setShowAllPartyFlag(Integer showAllPartyFlag) {
		this.showAllPartyFlag = showAllPartyFlag;
	}


	public Double getReceivedBillExciseAmount() {
		return receivedBillExciseAmount;
	}


	public void setReceivedBillExciseAmount(Double receivedBillExciseAmount) {
		this.receivedBillExciseAmount = receivedBillExciseAmount;
	}


	public Double getReceivedEducationCessAmount() {
		return receivedEducationCessAmount;
	}


	public void setReceivedEducationCessAmount(Double receivedEducationCessAmount) {
		this.receivedEducationCessAmount = receivedEducationCessAmount;
	}


	public Double getReceivedHighEducationCessAmount() {
		return receivedHighEducationCessAmount;
	}


	public void setReceivedHighEducationCessAmount(
			Double receivedHighEducationCessAmount) {
		this.receivedHighEducationCessAmount = receivedHighEducationCessAmount;
	}


	public Double getReceivedItemVatAmount() {
		return receivedItemVatAmount;
	}


	public void setReceivedItemVatAmount(Double receivedItemVatAmount) {
		this.receivedItemVatAmount = receivedItemVatAmount;
	}


	public Double getEntryTax() {
		return entryTax;
	}


	public void setEntryTax(Double entryTax) {
		this.entryTax = entryTax;
	}
	
	
	

}
