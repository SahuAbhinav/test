package com.advanz.erp.masters.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name="m_transporter")
public class TransporterEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8558874863072458056L;
	
	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name = "transporter_id", unique = true, updatable = false, length=11)
	private Integer transporterId;	
	@Column(name="transporter_code", length=4)
	private String transCode;
	@Column(name="transporter_name", length=4)
	private String transName;
	@Column(name="transporter_address", length=4)
	private String transAddress;
	@Column(name="city_id", length=4)
	private Integer cityId;
	@Column(name="state", length=4)
	private Integer state;
	@Column(name="country", length=4)
	private Integer country;
	@Column(name="zipcode", length=4)
	private Integer zipCode;
	@Column(name="mobile", length=4)
	private String mobile;
	@Column(name="phone_1_o", length=4)
	private String phone1;
	@Column(name="phone_2_o", length=4)
	private String phone2;
	@Column(name="phone_work", length=4)
	private String phoneWork;
	@Column(name="contact_person", length=4)
	private String contactPerson;
	@Column(name="contact_mobile", length=4)
	private String contactMobile;
	@Column(name="contact_email", length=4)
	private String contactEmail;
	@Column(name="active_status", length=4)
	private Integer activeStatus;
	@Column(name="e_mail", length=4)
	private String email;
	@Column(name="website", length=4)
	private String website;
	@Column(name="vat_no", length=4)
	private String vatNo;
	@Temporal(value=TemporalType.DATE)
	@Column(name="vat_dt", length=4)
	private Date vatDt;
	@Column(name="transporter_rating", length=4)
	private Integer transRating;
	@Temporal(value=TemporalType.DATE)
	@Column(name="date_assessment", length=4)
	private Date assessmentDt;
	@Column(name="banker_acno_1", length=4)
	private String bankAccount1;
	@Column(name="bank_name_1", length=4)
	private String bankName1;
	@Column(name="branch_name_1", length=4)
	private String branchName1;
	@Column(name="ifsc_code_1", length=4)
	private String ifscCode1;
	@Column(name="banker_acno_2", length=4)
	private String bankAccount2;
	@Column(name="bank_name_2", length=4)
	private String bankName2;
	@Column(name="branch_name_2", length=4)
	private String branchName2;
	@Column(name="ifsc_code_2", length=4)
	private String ifscCode2;
	@Column(name="servtaxno", length=4)
	private String servTaxNo;
	@Temporal(value=TemporalType.DATE)
	@Column(name="servtaxdt", length=4)
	private Date servTaxDt;
	@Column(name="general_remark", length=4)
	private String generalRemark;
	
	
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
	 * @return the transCode
	 */
	public String getTransCode() {
		return transCode;
	}
	/**
	 * @param transCode the transCode to set
	 */
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	/**
	 * @return the transName
	 */
	public String getTransName() {
		return transName;
	}
	/**
	 * @param transName the transName to set
	 */
	public void setTransName(String transName) {
		this.transName = transName;
	}
	/**
	 * @return the transAddress
	 */
	public String getTransAddress() {
		return transAddress;
	}
	/**
	 * @param transAddress the transAddress to set
	 */
	public void setTransAddress(String transAddress) {
		this.transAddress = transAddress;
	}
	/**
	 * @return the cityId
	 */
	public Integer getCityId() {
		return cityId;
	}
	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * @return the country
	 */
	public Integer getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(Integer country) {
		this.country = country;
	}
	/**
	 * @return the zipCode
	 */
	public Integer getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the phone1
	 */
	public String getPhone1() {
		return phone1;
	}
	/**
	 * @param phone1 the phone1 to set
	 */
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	/**
	 * @return the phone2
	 */
	public String getPhone2() {
		return phone2;
	}
	/**
	 * @param phone2 the phone2 to set
	 */
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	/**
	 * @return the phoneWork
	 */
	public String getPhoneWork() {
		return phoneWork;
	}
	/**
	 * @param phoneWork the phoneWork to set
	 */
	public void setPhoneWork(String phoneWork) {
		this.phoneWork = phoneWork;
	}
	/**
	 * @return the contactPerson
	 */
	public String getContactPerson() {
		return contactPerson;
	}
	/**
	 * @param contactPerson the contactPerson to set
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	/**
	 * @return the contactMobile
	 */
	public String getContactMobile() {
		return contactMobile;
	}
	/**
	 * @param contactMobile the contactMobile to set
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	/**
	 * @return the contactEmail
	 */
	public String getContactEmail() {
		return contactEmail;
	}
	/**
	 * @param contactEmail the contactEmail to set
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	/**
	 * @return the activeStatus
	 */
	public Integer getActiveStatus() {
		return activeStatus;
	}
	/**
	 * @param activeStatus the activeStatus to set
	 */
	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
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
	 * @return the transRating
	 */
	public Integer getTransRating() {
		return transRating;
	}
	/**
	 * @param transRating the transRating to set
	 */
	public void setTransRating(Integer transRating) {
		this.transRating = transRating;
	}
	/**
	 * @return the assessmentDt
	 */
	public Date getAssessmentDt() {
		return assessmentDt;
	}
	/**
	 * @param assessmentDt the assessmentDt to set
	 */
	public void setAssessmentDt(Date assessmentDt) {
		this.assessmentDt = assessmentDt;
	}
	/**
	 * @return the bankAccount1
	 */
	public String getBankAccount1() {
		return bankAccount1;
	}
	/**
	 * @param bankAccount1 the bankAccount1 to set
	 */
	public void setBankAccount1(String bankAccount1) {
		this.bankAccount1 = bankAccount1;
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
	 * @return the branchNAme1
	 */
	public String getBranchName1() {
		return branchName1;
	}
	/**
	 * @param branchNAme1 the branchNAme1 to set
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
	 * @return the bankAccount2
	 */
	public String getBankAccount2() {
		return bankAccount2;
	}
	/**
	 * @param bankAccount2 the bankAccount2 to set
	 */
	public void setBankAccount2(String bankAccount2) {
		this.bankAccount2 = bankAccount2;
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
	 * @return the branchNAme2
	 */
	public String getBranchName2() {
		return branchName2;
	}
	/**
	 * @param branchNAme2 the branchNAme2 to set
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
	 * @return the generalRemark
	 */
	public String getGeneralRemark() {
		return generalRemark;
	}
	/**
	 * @param generalRemark the generalRemark to set
	 */
	public void setGeneralRemark(String generalRemark) {
		this.generalRemark = generalRemark;
	}
	
}

