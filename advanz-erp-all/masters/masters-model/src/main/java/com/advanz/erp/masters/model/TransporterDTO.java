package com.advanz.erp.masters.model;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

public class TransporterDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -686982364707391518L;
	
	private Integer transporterId;
	private String transCode;
	private String transName;
	private String transAddress;
	private Integer cityId;
	private Integer state;
	private Integer country;
	private Integer zipCode;
	private String mobile;
	private String phone1;
	private String phone2;
	private String phoneWork;
	private String contactPerson;
	private String contactMobile;
	private String contactEmail;
	private Integer activeStatus;
	private String email;
	private String website;
	private String vatNo;
	private Date vatDt;
	private Integer transRating;
	private Date assessmentDt;
	private String bankAccount1;
	private String bankName1;
	private String branchName1;
	private String ifscCode1;
	private String bankAccount2;
	private String bankName2;
	private String branchName2;
	private String ifscCode2;
	private String servTaxNo;
	private Date servTaxDt;
	private String generalRemark;
	
	private CityDTO cityDTO;
	private String cityName; 

	
	
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
	public CityDTO getCityDTO() {
		return cityDTO;
	}
	public void setCityDTO(CityDTO cityDTO) {
		this.cityDTO = cityDTO;
	}
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransporterDTO [transporterId=" + transporterId + ", transCode=" + transCode
				+ ", transName=" + transName + ", transAddress=" + transAddress
				+ ", cityId=" + cityId + ", state=" + state + ", country="
				+ country + ", zipCode=" + zipCode + ", mobile=" + mobile
				+ ", phone1=" + phone1 + ", phone2=" + phone2 + ", phoneWork="
				+ phoneWork + ", contactPerson=" + contactPerson
				+ ", contactMobile=" + contactMobile + ", contactEmail="
				+ contactEmail + ", activeStatus=" + activeStatus + ", email="
				+ email + ", website=" + website + ", vatNo=" + vatNo
				+ ", vatDt=" + vatDt + ", transRating=" + transRating
				+ ", assessmentDt=" + assessmentDt + ", bankAccount1="
				+ bankAccount1 + ", bankName1=" + bankName1 + ", branchName1="
				+ branchName1 + ", ifscCode1=" + ifscCode1 + ", bankAccount2="
				+ bankAccount2 + ", bankName2=" + bankName2 + ", branchName2="
				+ branchName2 + ", ifscCode2=" + ifscCode2 + ", servTaxNo="
				+ servTaxNo + ", servTaxDt=" + servTaxDt + ", generalRemark="
				+ generalRemark + "]";
	}
	
	
	
}
