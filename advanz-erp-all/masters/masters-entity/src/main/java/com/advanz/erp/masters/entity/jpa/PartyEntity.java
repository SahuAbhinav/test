package com.advanz.erp.masters.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;
@Entity
@Table(name="m_party")
public class PartyEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8028734482797332L;

	
	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name = "party_id", unique = true, updatable = false, length=11)
	private Integer partyId;	
	
	@Column(name="party_code", length=4)
	private String partyCode;
	
	@Column(name="party_name", length=85)
	private String partyName;
	@ManyToOne
	@JoinColumn(name="party_type_id")
	
	private PartyTypeEntity partyTypeEntity;
	
	public PartyTypeEntity getPartyTypeEntity() {
		return partyTypeEntity;
	}

	public void setPartyTypeEntity(PartyTypeEntity partyTypeEntity) {
		this.partyTypeEntity = partyTypeEntity;
	}

	@Column(name="opening_balance")
	private Double openingBalance;
	
	@Column(name="active_status", length=1)
	private int activeStatus;
	
	@Column(name="billing_address", length=150)
	private String billingAddress;
	
	@Column(name="billing_city_id", length=11)
	private Integer billingCityId;
	
	@Column(name="billing_zipcode", length=11)
	private String billingZipCode;
	
	@Column(name="phone_o_1", length=35)
	private String phoneO1;
	
	@Column(name="phone_w_1", length=35)
	private String phoneW1;
	
	@Column(name="contact_person_1", length=35)
	private String contactPerson1;
	
	@Column(name="mobile_1", length=35)
	private String mobile1;
	
	@Column(name="fax_1", length=35)
	private String fax1;
	
	@Column(name="contact_1_e_mail", length=35)
	private String email1;
	
	@Column(name="shipping_address", length=150)
	private String shippingAddress;
	
	@Column(name="shipping_city_id", length=11)
	private Integer shippingCityId;
	
	@Column(name="shipping_zipcode", length=11)
	private String shippingZipCode;
	
	@Column(name="phone_o_2", length=35)
	private String phoneO2;
	
	@Column(name="phone_w_2", length=35)
	private String phoneW2;
	
	@Column(name="contact_person_2", length=35)
	private String contactPerson2;
	
	@Column(name="mobile_2", length=35)
	private String mobile2;
	
	@Column(name="fax_2", length=35)
	private String fax2;
	
	@Column(name="contact_2_e_mail", length=35)
	private String email2;
	
	@Column(name="e_mail", length=65)
	private String email;
	
	@Column(name="website", length=35)
	private String website;
	
	@Column(name="shipping_add_flag", length=6)
	private int shippingAddFlag;
	
	@Column(name="invoice_type", length=25)
	private String invoiceType;
	
	@Column(name="payment_type", length=25)
	private String paymentType;
	
	@Column(name="drug_licence_no", length=25)
	private String drugLicenceNo;
	
	@Column(name="vat_no", length=35)
	private String vatNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="vat_dt")
	private Date vatDt;
	
	@Column(name="cst_no", length=35)
	private String cstNo;
	
	@Column(name="cst_dt")
	private Date cstDt;
	
	@Column(name="party_rating")
	private String partyRating;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_assessment")
	private Date assementDate;
	
	@Column(name="import_export_code", length=25)
	private String ieCode;
	
	@Column(name="msme_code", length=25)
	private String msmeCode;
	
	@Column(name="panno", length=25)
	private String panNo;
	
	@Column(name="banker_acno_1", length=35)
	private String bankAcct1;
	
	@Column(name="bank_name_1", length=35)
	private String bankName1;
	
	@Column(name="branch_name_1", length=65)
	private String branchName1;
	
	@Column(name="ifsc_code_1", length=25)
	private String ifscCode1;	
	
	@Column(name="banker_acno_2", length=35)
	private String bankAcct2;
	
	@Column(name="bank_name_2", length=35)
	private String bankName2;
	
	@Column(name="branch_name_2", length=65)
	private String branchName2;
	
	@Column(name="ifsc_code_2", length=25)
	private String ifscCode2;
	
	@Column(name="credit_days", length=11)
	private Integer creditDays;
	
	@Column(name="credit_limit")
	private Double creditLimit;
	
	@Column(name="overdue_days", length=4)
	private Integer overdueDays;
	
	@Column(name="transporter_id", length=11)
	private Integer transporterId;
	
	@Column(name="form_id", length=11)
	private Integer formId;
	
	@Column(name="tdsper_flag", length=6)
	private int tdsFlag;
	
	@Column(name="tds_per")
	private Double tds;	
	
	@Column(name="servtaxno", length=35)
	private String servTaxNo;
	
	@Column(name="servtaxdt")
	private Date servTaxDt;
	
	@Column(name="debit_amount")
	private Double debitAmount;
	
	@Column(name="credit_amount")
	private Double creditAmount;	
	
	@Column(name="balance_amount")
	private Double balanceAmount;
	
	@Column(name="exciseeccno", length=35)
	private String exciseEccNo;
	
	@Column(name="range_add", length=35)
	private String rangeAdd;
	
	@Column(name="division", length=35)
	private String division;
	
	@Column(name="general_remark", length=4)
	private String genRemark;

	
	@Column(name="vat_cst_tax_type", length=15)
	private String vatCstTaxType;
	
	
	public String getVatCstTaxType() {
		return vatCstTaxType;
	}

	public void setVatCstTaxType(String vatCstTaxType) {
		this.vatCstTaxType = vatCstTaxType;
	}

	@Column(name="commissionerate", length=25)
	private String commissionerate;
	public String getCommissionerate() {
		return commissionerate;
	}

	public void setCommissionerate(String commissionerate) {
		this.commissionerate = commissionerate;
	}

	/**
	 * @return the partyId
	 */
	public Integer getPartyId() {
		return partyId;
	}

	/**
	 * @param partyId the partyId to set
	 */
	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	/**
	 * @return the partyCode
	 */
	public String getPartyCode() {
		return partyCode;
	}

	/**
	 * @param partyCode the partyCode to set
	 */
	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	/**
	 * @return the partyName
	 */
	public String getPartyName() {
		return partyName;
	}

	/**
	 * @param partyName the partyName to set
	 */
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public Double getOpeningBalance() {
		return openingBalance;
	}

	/**
	 * @param openingBalance the openingBalance to set
	 */
	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
	}

	/**
	 * @return the activeStatus
	 */
	public int getActiveStatus() {
		return activeStatus;
	}

	/**
	 * @param activeStatus the activeStatus to set
	 */
	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	/**
	 * @return the billingAddress
	 */
	public String getBillingAddress() {
		return billingAddress;
	}

	/**
	 * @param billingAddress the billingAddress to set
	 */
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	/**
	 * @return the billingCityId
	 */
	public Integer getBillingCityId() {
		return billingCityId;
	}

	/**
	 * @param billingCityId the billingCityId to set
	 */
	public void setBillingCityId(Integer billingCityId) {
		this.billingCityId = billingCityId;
	}

	/**
	 * @return the billingZipCode
	 */
	public String getBillingZipCode() {
		return billingZipCode;
	}

	/**
	 * @param billingZipCode the billingZipCode to set
	 */
	public void setBillingZipCode(String billingZipCode) {
		this.billingZipCode = billingZipCode;
	}

	/**
	 * @return the phoneO1
	 */
	public String getPhoneO1() {
		return phoneO1;
	}

	/**
	 * @param phoneO1 the phoneO1 to set
	 */
	public void setPhoneO1(String phoneO1) {
		this.phoneO1 = phoneO1;
	}

	/**
	 * @return the phoneW1
	 */
	public String getPhoneW1() {
		return phoneW1;
	}

	/**
	 * @param phoneW1 the phoneW1 to set
	 */
	public void setPhoneW1(String phoneW1) {
		this.phoneW1 = phoneW1;
	}

	/**
	 * @return the contactPerson1
	 */
	public String getContactPerson1() {
		return contactPerson1;
	}

	/**
	 * @param contactPerson1 the contactPerson1 to set
	 */
	public void setContactPerson1(String contactPerson1) {
		this.contactPerson1 = contactPerson1;
	}

	/**
	 * @return the mobile1
	 */
	public String getMobile1() {
		return mobile1;
	}

	/**
	 * @param mobile1 the mobile1 to set
	 */
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	/**
	 * @return the fax1
	 */
	public String getFax1() {
		return fax1;
	}

	/**
	 * @param fax1 the fax1 to set
	 */
	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}

	/**
	 * @return the email1
	 */
	public String getEmail1() {
		return email1;
	}

	/**
	 * @param email1 the email1 to set
	 */
	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	/**
	 * @return the shippingAddress
	 */
	public String getShippingAddress() {
		return shippingAddress;
	}

	/**
	 * @param shippingAddress the shippingAddress to set
	 */
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	/**
	 * @return the shippingCityId
	 */
	public Integer getShippingCityId() {
		return shippingCityId;
	}

	/**
	 * @param shippingCityId the shippingCityId to set
	 */
	public void setShippingCityId(Integer shippingCityId) {
		this.shippingCityId = shippingCityId;
	}

	/**
	 * @return the shippingZipCode
	 */
	public String getShippingZipCode() {
		return shippingZipCode;
	}

	/**
	 * @param shippingZipCode the shippingZipCode to set
	 */
	public void setShippingZipCode(String shippingZipCode) {
		this.shippingZipCode = shippingZipCode;
	}

	/**
	 * @return the phoneO2
	 */
	public String getPhoneO2() {
		return phoneO2;
	}

	/**
	 * @param phoneO2 the phoneO2 to set
	 */
	public void setPhoneO2(String phoneO2) {
		this.phoneO2 = phoneO2;
	}

	/**
	 * @return the phoneW2
	 */
	public String getPhoneW2() {
		return phoneW2;
	}

	/**
	 * @param phoneW2 the phoneW2 to set
	 */
	public void setPhoneW2(String phoneW2) {
		this.phoneW2 = phoneW2;
	}

	/**
	 * @return the contactPerson2
	 */
	public String getContactPerson2() {
		return contactPerson2;
	}

	/**
	 * @param contactPerson2 the contactPerson2 to set
	 */
	public void setContactPerson2(String contactPerson2) {
		this.contactPerson2 = contactPerson2;
	}

	/**
	 * @return the mobile2
	 */
	public String getMobile2() {
		return mobile2;
	}

	/**
	 * @param mobile2 the mobile2 to set
	 */
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	/**
	 * @return the fax2
	 */
	public String getFax2() {
		return fax2;
	}

	/**
	 * @param fax2 the fax2 to set
	 */
	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}

	/**
	 * @return the email2
	 */
	public String getEmail2() {
		return email2;
	}

	/**
	 * @param email2 the email2 to set
	 */
	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return the shippingAddFlag
	 */
	public int getShippingAddFlag() {
		return shippingAddFlag;
	}

	/**
	 * @param shippingAddFlag the shippingAddFlag to set
	 */
	public void setShippingAddFlag(int shippingAddFlag) {
		this.shippingAddFlag = shippingAddFlag;
	}

	/**
	 * @return the invoiceType
	 */
	public String getInvoiceType() {
		return invoiceType;
	}

	/**
	 * @param invoiceType the invoiceType to set
	 */
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @return the drugLicenceNo
	 */
	public String getDrugLicenceNo() {
		return drugLicenceNo;
	}

	/**
	 * @param drugLicenceNo the drugLicenceNo to set
	 */
	public void setDrugLicenceNo(String drugLicenceNo) {
		this.drugLicenceNo = drugLicenceNo;
	}

	/**
	 * @return the vatNo
	 */
	public String getVatNo() {
		return vatNo;
	}

	/**
	 * @param vatNo the vatNo to set
	 */
	public void setVatNo(String vatNo) {
		this.vatNo = vatNo;
	}

	/**
	 * @return the vatDt
	 */
	public Date getVatDt() {
		return vatDt;
	}

	/**
	 * @param vatDt the vatDt to set
	 */
	public void setVatDt(Date vatDt) {
		this.vatDt = vatDt;
	}

	/**
	 * @return the cstNo
	 */
	public String getCstNo() {
		return cstNo;
	}

	/**
	 * @param cstNo the cstNo to set
	 */
	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}

	/**
	 * @return the cstDt
	 */
	public Date getCstDt() {
		return cstDt;
	}

	/**
	 * @param cstDt the cstDt to set
	 */
	public void setCstDt(Date cstDt) {
		this.cstDt = cstDt;
	}

	/**
	 * @return the partyRating
	 */
	public String getPartyRating() {
		return partyRating;
	}

	/**
	 * @param partyRating the partyRating to set
	 */
	public void setPartyRating(String partyRating) {
		this.partyRating = partyRating;
	}

	/**
	 * @return the assementDate
	 */
	public Date getAssementDate() {
		return assementDate;
	}

	/**
	 * @param assementDate the assementDate to set
	 */
	public void setAssementDate(Date assementDate) {
		this.assementDate = assementDate;
	}

	/**
	 * @return the ieCode
	 */
	public String getIeCode() {
		return ieCode;
	}

	/**
	 * @param ieCode the ieCode to set
	 */
	public void setIeCode(String ieCode) {
		this.ieCode = ieCode;
	}

	/**
	 * @return the msmeCode
	 */
	public String getMsmeCode() {
		return msmeCode;
	}

	/**
	 * @param msmeCode the msmeCode to set
	 */
	public void setMsmeCode(String msmeCode) {
		this.msmeCode = msmeCode;
	}

	/**
	 * @return the panNo
	 */
	public String getPanNo() {
		return panNo;
	}

	/**
	 * @param panNo the panNo to set
	 */
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	/**
	 * @return the bankAcct1
	 */
	public String getBankAcct1() {
		return bankAcct1;
	}

	/**
	 * @param bankAcct1 the bankAcct1 to set
	 */
	public void setBankAcct1(String bankAcct1) {
		this.bankAcct1 = bankAcct1;
	}

	/**
	 * @return the bankName1
	 */
	public String getBankName1() {
		return bankName1;
	}

	/**
	 * @param bankName1 the bankName1 to set
	 */
	public void setBankName1(String bankName1) {
		this.bankName1 = bankName1;
	}

	/**
	 * @return the branchName1
	 */
	public String getBranchName1() {
		return branchName1;
	}

	/**
	 * @param branchName1 the branchName1 to set
	 */
	public void setBranchName1(String branchName1) {
		this.branchName1 = branchName1;
	}

	/**
	 * @return the ifscCode1
	 */
	public String getIfscCode1() {
		return ifscCode1;
	}

	/**
	 * @param ifscCode1 the ifscCode1 to set
	 */
	public void setIfscCode1(String ifscCode1) {
		this.ifscCode1 = ifscCode1;
	}

	/**
	 * @return the bankAcct2
	 */
	public String getBankAcct2() {
		return bankAcct2;
	}

	/**
	 * @param bankAcct2 the bankAcct2 to set
	 */
	public void setBankAcct2(String bankAcct2) {
		this.bankAcct2 = bankAcct2;
	}

	/**
	 * @return the bankName2
	 */
	public String getBankName2() {
		return bankName2;
	}

	/**
	 * @param bankName2 the bankName2 to set
	 */
	public void setBankName2(String bankName2) {
		this.bankName2 = bankName2;
	}

	/**
	 * @return the branchName2
	 */
	public String getBranchName2() {
		return branchName2;
	}

	/**
	 * @param branchName2 the branchName2 to set
	 */
	public void setBranchName2(String branchName2) {
		this.branchName2 = branchName2;
	}

	/**
	 * @return the ifscCode2
	 */
	public String getIfscCode2() {
		return ifscCode2;
	}

	/**
	 * @param ifscCode2 the ifscCode2 to set
	 */
	public void setIfscCode2(String ifscCode2) {
		this.ifscCode2 = ifscCode2;
	}

	/**
	 * @return the creditDays
	 */
	public Integer getCreditDays() {
		return creditDays;
	}

	/**
	 * @param creditDays the creditDays to set
	 */
	public void setCreditDays(Integer creditDays) {
		this.creditDays = creditDays;
	}

	/**
	 * @return the creditLimit
	 */
	public Double getCreditLimit() {
		return creditLimit;
	}

	/**
	 * @param creditLimit the creditLimit to set
	 */
	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	/**
	 * @return the overdueDays
	 */
	public Integer getOverdueDays() {
		return overdueDays;
	}

	/**
	 * @param overdueDays the overdueDays to set
	 */
	public void setOverdueDays(Integer overdueDays) {
		this.overdueDays = overdueDays;
	}

	/**
	 * @return the transporterId
	 */
	public Integer getTransporterId() {
		return transporterId;
	}

	/**
	 * @param transporterId the transporterId to set
	 */
	public void setTransporterId(Integer transporterId) {
		this.transporterId = transporterId;
	}

	/**
	 * @return the formId
	 */
	public Integer getFormId() {
		return formId;
	}

	/**
	 * @param formId the formId to set
	 */
	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	/**
	 * @return the tdsFlag
	 */
	public int getTdsFlag() {
		return tdsFlag;
	}

	/**
	 * @param tdsFlag the tdsFlag to set
	 */
	public void setTdsFlag(int tdsFlag) {
		this.tdsFlag = tdsFlag;
	}

	/**
	 * @return the tds
	 */
	public Double getTds() {
		return tds;
	}

	/**
	 * @param tds the tds to set
	 */
	public void setTds(Double tds) {
		this.tds = tds;
	}

	/**
	 * @return the servTaxNo
	 */
	public String getServTaxNo() {
		return servTaxNo;
	}

	/**
	 * @param servTaxNo the servTaxNo to set
	 */
	public void setServTaxNo(String servTaxNo) {
		this.servTaxNo = servTaxNo;
	}

	/**
	 * @return the servTaxDt
	 */
	public Date getServTaxDt() {
		return servTaxDt;
	}

	/**
	 * @param servTaxDt the servTaxDt to set
	 */
	public void setServTaxDt(Date servTaxDt) {
		this.servTaxDt = servTaxDt;
	}

	/**
	 * @return the debitAmount
	 */
	public Double getDebitAmount() {
		return debitAmount;
	}
	
	/**
	 * @param debitAmount the debitAmount to set
	 */
	public void setDebitAmount(Double debitAmount) {
		this.debitAmount = debitAmount;
	}
	/**
	 * @return the creditAmount
	 */
	public Double getCreditAmount() {
		return creditAmount;
	}	
	/**
	 * @param creditAmount the creditAmount to set
	 */
	public void setCreditAmount(Double creditAmount) {
		this.creditAmount = creditAmount;
	}

	/**
	 * @return the balanceAmount
	 */
	public Double getBalanceAmount() {
		return balanceAmount;
	}

	/**
	 * @param balanceAmount the balanceAmount to set
	 */
	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	/**
	 * @return the exciseEccNo
	 */
	public String getExciseEccNo() {
		return exciseEccNo;
	}

	/**
	 * @param exciseEccNo the exciseEccNo to set
	 */
	public void setExciseEccNo(String exciseEccNo) {
		this.exciseEccNo = exciseEccNo;
	}

	/**
	 * @return the rangeAdd
	 */
	public String getRangeAdd() {
		return rangeAdd;
	}

	/**
	 * @param rangeAdd the rangeAdd to set
	 */
	public void setRangeAdd(String rangeAdd) {
		this.rangeAdd = rangeAdd;
	}

	/**
	 * @return the division
	 */
	public String getDivision() {
		return division;
	}

	/**
	 * @param division the division to set
	 */
	public void setDivision(String division) {
		this.division = division;
	}

	/**
	 * @return the genRemark
	 */
	public String getGenRemark() {
		return genRemark;
	}

	/**
	 * @param genRemark the genRemark to set
	 */
	public void setGenRemark(String genRemark) {
		this.genRemark = genRemark;
	}

	public PartyEntity() {
		super();
	}

	public PartyEntity(Integer partyId,String partyName){
		super();
		this.partyId = partyId;
		this.partyName = partyName;
	}
}
