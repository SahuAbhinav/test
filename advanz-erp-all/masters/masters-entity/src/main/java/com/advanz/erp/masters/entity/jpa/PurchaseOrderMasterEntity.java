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
@Table(name="t_purchase_order_mast")
public class PurchaseOrderMasterEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Column(name="transaction_series")
	private String transactionSeries;
	
	@Id
	@GeneratedValue(generator = "system-incr")
	@GenericGenerator(name = "system-incr", strategy = "increment")
	@Column(name = "po_auto_id")
	private Integer poAutoId;
	
	
	@Column(name="finyr")
	private String finYear;
	
	@Column(name="po_number")
	private String purchaseOrderNumber;
	
	@Column(name="po_id")
	private Integer purchaseOrderId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="po_date")
	private Date purchaseOrderDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="indent_date")
	private Date indentDate;
	
	@Column(name="indent_number")
	private String indentNumber;
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	private BranchEntity branchEntity;
	
	@ManyToOne
	@JoinColumn(name="supplier_id")
	private PartyEntity partyEntity;
	
	@Column(name="city_id")
	private Integer cityId;
	
	@Column(name="phone_office")
	private String phoneOffice;
	
	@Column(name="contact_person")
	private String contactPerson;
	
	@ManyToOne
	@JoinColumn(name="transport_id")
	private TransporterEntity transportEnttity;
	
	@ManyToOne
	@JoinColumn(name="item_group_flag_id")
	private ItemGroupFlagEntity itemGroupFlagEntity;
	
	@Column(name="supplier_reference")
	private String supplierReference;
	
	@Column(name="our_reference")
	private String ourReference;
	
	@Column(name="payment_terms")
	private String paymentTerms;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="desire_delivery_date")
	private Date desireDeliveryDate;
	
	@Column(name="delivery_terms")
	private String delivery_terms;
	
	@Column(name="form_req_flag")
	private Integer formReqFlag;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="form_date")
	private Date formDate;
	
	@Column(name="form_no")
	private String formnumber;
	

	@Column(name="form_type_id")
	private Integer formTypeId;
	
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

	@Column(name="item_value")
	private Double itemValue;
	
	@Column(name="discount_amount")
	private Double discountAmount;
	
	@Column(name="po_net_amount")
	private Double poNetAmount;
	
	@Column(name="po_remark")
	private String poRemark;
	
	@Column(name="po_valid_upto_date")
	private Date poValidUptoDate;
	
	   
	
	
	public Date getPoValidUptoDate() {
		return poValidUptoDate;
	}

	public void setPoValidUptoDate(Date poValidUptoDate) {
		this.poValidUptoDate = poValidUptoDate;
	}
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="po_auto_id")
	private List<PurchaseOrderDetailEntity> purchaseOrderDetailEntity;
	
	@Column(name="excise_duty_amount")
	private Double exciseDutyAmount;
	
	@Column(name="vat_amount")
	private Double vatAmount;
	
	@Column(name="cst_amount")
	private Double cstAmount;
	
	@Column(name="taxable_amount")
	private Double taxableAmount;
	
	
	@Column(name="education_cess_perc")
	private Double educationCessPerc;
	
	 @Column(name="education_cess_amount")
	 private Double educationCessAmount;
	
	 @Column(name="high_education_cess_perc")
	 private Double highEducationCessPerc;
	
	 @Column(name="high_education_cess_amount")
	 private Double highEducationCessAmount;
	
	/**
	 * @return the poAutoId
	 */
	public Integer getPoAutoId() {
		return poAutoId;
	}

	/**
	 * @param poAutoId the poAutoId to set
	 */
	public void setPoAutoId(Integer poAutoId) {
		this.poAutoId = poAutoId;
	}

	/**
	 * @return the purchaseOrderDetailEntity
	 */
	public List<PurchaseOrderDetailEntity> getPurchaseOrderDetailEntity() {
		return purchaseOrderDetailEntity;
	}

	/**
	 * @param purchaseOrderDetailEntity the purchaseOrderDetailEntity to set
	 */
	public void setPurchaseOrderDetailEntity(
			List<PurchaseOrderDetailEntity> purchaseOrderDetailEntity) {
		this.purchaseOrderDetailEntity = purchaseOrderDetailEntity;
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

	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public Integer getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Integer purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public Date getPurchaseOrderDate() {
		return purchaseOrderDate;
	}

	public void setPurchaseOrderDate(Date purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}

	public Date getIndentDate() {
		return indentDate;
	}

	public void setIndentDate(Date indentDate) {
		this.indentDate = indentDate;
	}

	public String getIndentNumber() {
		return indentNumber;
	}

	public void setIndentNumber(String indentNumber) {
		this.indentNumber = indentNumber;
	}

	public BranchEntity getBranchEntity() {
		return branchEntity;
	}

	public void setBranchEntity(BranchEntity branchEntity) {
		this.branchEntity = branchEntity;
	}

	public PartyEntity getPartyEntity() {
		return partyEntity;
	}

	public void setPartyEntity(PartyEntity partyEntity) {
		this.partyEntity = partyEntity;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getPhoneOffice() {
		return phoneOffice;
	}

	public void setPhoneOffice(String phoneOffice) {
		this.phoneOffice = phoneOffice;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public TransporterEntity getTransportEnttity() {
		return transportEnttity;
	}

	public void setTransportEnttity(TransporterEntity transportEnttity) {
		this.transportEnttity = transportEnttity;
	}

	public ItemGroupFlagEntity getItemGroupFlagEntity() {
		return itemGroupFlagEntity;
	}

	public void setItemGroupFlagEntity(ItemGroupFlagEntity itemGroupFlagEntity) {
		this.itemGroupFlagEntity = itemGroupFlagEntity;
	}

	public String getSupplierReference() {
		return supplierReference;
	}

	public void setSupplierReference(String supplierReference) {
		this.supplierReference = supplierReference;
	}

	public String getOurReference() {
		return ourReference;
	}

	public void setOurReference(String ourReference) {
		this.ourReference = ourReference;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public Date getDesireDeliveryDate() {
		return desireDeliveryDate;
	}

	public void setDesireDeliveryDate(Date desireDeliveryDate) {
		this.desireDeliveryDate = desireDeliveryDate;
	}

	public String getDelivery_terms() {
		return delivery_terms;
	}

	public void setDelivery_terms(String delivery_terms) {
		this.delivery_terms = delivery_terms;
	}

	public Integer getFormReqFlag() {
		return formReqFlag;
	}

	public void setFormReqFlag(Integer formReqFlag) {
		this.formReqFlag = formReqFlag;
	}

	public Date getFormDate() {
		return formDate;
	}

	public void setFormDate(Date formDate) {
		this.formDate = formDate;
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

	public Double getPoNetAmount() {
		return poNetAmount;
	}

	public void setPoNetAmount(Double poNetAmount) {
		this.poNetAmount = poNetAmount;
	}

	public String getPoRemark() {
		return poRemark;
	}

	public void setPoRemark(String poRemark) {
		this.poRemark = poRemark;
	}

	public Double getExciseDutyAmount() {
		return exciseDutyAmount;
	}

	public void setExciseDutyAmount(Double exciseDutyAmount) {
		this.exciseDutyAmount = exciseDutyAmount;
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

	public Double getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(Double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	public Double getEducationCessPerc() {
		return educationCessPerc;
	}

	public void setEducationCessPerc(Double educationCessPerc) {
		this.educationCessPerc = educationCessPerc;
	}

	public Double getEducationCessAmount() {
		return educationCessAmount;
	}

	public void setEducationCessAmount(Double educationCessAmount) {
		this.educationCessAmount = educationCessAmount;
	}

	public Double getHighEducationCessPerc() {
		return highEducationCessPerc;
	}

	public void setHighEducationCessPerc(Double highEducationCessPerc) {
		this.highEducationCessPerc = highEducationCessPerc;
	}

	public Double getHighEducationCessAmount() {
		return highEducationCessAmount;
	}

	public void setHighEducationCessAmount(Double highEducationCessAmount) {
		this.highEducationCessAmount = highEducationCessAmount;
	}

	
	
	
	

}
