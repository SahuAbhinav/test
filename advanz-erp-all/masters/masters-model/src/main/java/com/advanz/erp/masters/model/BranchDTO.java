package com.advanz.erp.masters.model;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class BranchDTO extends BaseDTO{
	
	private Integer branchId;
	private String branch;
	private String invoiceCode;	
	private String address;
	private String city;
	private String state;
	private String country;
	private String pinZip;
	private String phone1;
	private String phone2;
	private String fax;
	private String vatNo;
	private Date vatDate;
	private String cstNo;
	private Date cstDate;
	private int stockRestrictFlag;
	private Double creditLimit;
	private Integer creditDays;
	private String emailId;
	private String website;
	private String servTaxNo;
	private Date servTaxDate;
	private String MSMECode;	
	private String exciseECCNo;
	private String rangAdd;
	private String division;	
	private String generalDesc;

	
	private String commissionerate;
	private Integer dutyVideNotification=0;
	/**
	 * @return the branchId
	 */
	public Integer getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}
	/**
	 * @return the invoiceCode
	 */
	public String getInvoiceCode() {
		return invoiceCode;
	}
	/**
	 * @param invoiceCode the invoiceCode to set
	 */
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the pinZip
	 */
	public String getPinZip() {
		return pinZip;
	}
	/**
	 * @param pinZip the pinZip to set
	 */
	public void setPinZip(String pinZip) {
		this.pinZip = pinZip;
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
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
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
	 * @return the vatDate
	 */
	public Date getVatDate() {
		return vatDate;
	}
	/**
	 * @param vatDate the vatDate to set
	 */
	public void setVatDate(Date vatDate) {
		this.vatDate = vatDate;
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
	 * @return the cstDate
	 */
	public Date getCstDate() {
		return cstDate;
	}
	/**
	 * @param cstDate the cstDate to set
	 */
	public void setCstDate(Date cstDate) {
		this.cstDate = cstDate;
	}
	/**
	 * @return the stockRestrictFlag
	 */
	public int getStockRestrictFlag() {
		return stockRestrictFlag;
	}
	/**
	 * @param stockRestrictFlag the stockRestrictFlag to set
	 */
	public void setStockRestrictFlag(int stockRestrictFlag) {
		this.stockRestrictFlag = stockRestrictFlag;
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
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	 * @return the servTaxDate
	 */
	public Date getServTaxDate() {
		return servTaxDate;
	}
	/**
	 * @param servTaxDate the servTaxDate to set
	 */
	public void setServTaxDate(Date servTaxDate) {
		this.servTaxDate = servTaxDate;
	}
	/**
	 * @return the mSMECode
	 */
	public String getMSMECode() {
		return MSMECode;
	}
	/**
	 * @param mSMECode the mSMECode to set
	 */
	public void setMSMECode(String mSMECode) {
		MSMECode = mSMECode;
	}
	/**
	 * @return the exciseECCNo
	 */
	public String getExciseECCNo() {
		return exciseECCNo;
	}
	/**
	 * @param exciseECCNo the exciseECCNo to set
	 */
	public void setExciseECCNo(String exciseECCNo) {
		this.exciseECCNo = exciseECCNo;
	}
	/**
	 * @return the rangAdd
	 */
	public String getRangAdd() {
		return rangAdd;
	}
	/**
	 * @param rangAdd the rangAdd to set
	 */
	public void setRangAdd(String rangAdd) {
		this.rangAdd = rangAdd;
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
	 * @return the generalDesc
	 */
	public String getGeneralDesc() {
		return generalDesc;
	}
	/**
	 * @param generalDesc the generalDesc to set
	 */
	public void setGeneralDesc(String generalDesc) {
		this.generalDesc = generalDesc;
	}
	
	
	public String getCommissionerate() {
		return commissionerate;
	}
	public void setCommissionerate(String commissionerate) {
		this.commissionerate = commissionerate;
	}
	public Integer getDutyVideNotification() {
		return dutyVideNotification;
	}
	public void setDutyVideNotification(Integer dutyVideNotification) {
		this.dutyVideNotification = dutyVideNotification;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BranchDTO [branchId=" + branchId + ", branch=" + branch
				+ ", invoiceCode=" + invoiceCode + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", country="
				+ country + ", pinZip=" + pinZip + ", phone1=" + phone1
				+ ", phone2=" + phone2 + ", fax=" + fax + ", vatNo=" + vatNo
				+ ", vatDate=" + vatDate + ", cstNo=" + cstNo + ", cstDate="
				+ cstDate + ", stockRestrictFlag=" + stockRestrictFlag
				+ ", creditLimit=" + creditLimit + ", creditDays=" + creditDays
				+ ", emailId=" + emailId + ", website=" + website
				+ ", servTaxNo=" + servTaxNo + ", servTaxDate=" + servTaxDate
				+ ", MSMECode=" + MSMECode + ", exciseECCNo=" + exciseECCNo
				+ ", rangAdd=" + rangAdd + ", division=" + division
				+ ", generalDesc=" + generalDesc + "]";
	}	
}
