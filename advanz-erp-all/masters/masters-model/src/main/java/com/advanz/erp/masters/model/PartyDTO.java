package com.advanz.erp.masters.model;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

public class PartyDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5253144860444287306L;
	
	private Integer partyId;	
	private String partyCode;
	private String partyName;
	private Integer partyTypeId;
	private Integer pTypeIdForDrCr;
	public Integer getpTypeIdForDrCr() {
		return pTypeIdForDrCr;
	}
	public void setpTypeIdForDrCr(Integer pTypeIdForDrCr) {
		this.pTypeIdForDrCr = pTypeIdForDrCr;
	}

	private String partyGroup;
	private Double openingBalance;
	private Double closingBalance;
	private String balanceType="Dr";
	private int activeStatus=1;
	private String billingAddress;
	private Integer billingCityId;
	private String billingZipCode;
	private String phoneO1;
	private String phoneW1;
	private String contactPerson1;
	private String mobile1;
	private String fax1;
	private String email1;
	private String shippingAddress;
	private Integer shippingCityId;
	private String shippingZipCode;
	private String phoneO2;
	private String phoneW2;
	private String contactPerson2;
	private String mobile2;
	private String fax2;
	private String email2;
	private String email;
	private String website;
	private int shippingAddFlag;
	private String invoiceType;
	private String paymentType;
	private String drugLicenceNo;
	private String vatNo;
	private Date vatDt;
	private String cstNo;
	private Date cstDt;
	private String partyRating;
	private Date assementDate;
	private String ieCode;
	private String msmeCode;
	private String panNo;
	private String bankAcct1;
	private String bankName1;
	private String branchName1;
	private String ifscCode1;	
	private String bankAcct2;
	private String bankName2;
	private String branchName2;
	private String ifscCode2;
	private Integer creditDays;
	private Double creditLimit;
	private Integer overdueDays;
	private Integer transporterId;
	private Integer formId;
	private int tdsFlag;
	private Double tds;	
	private String servTaxNo;
	private Date servTaxDt;
	private Double debitAmount;
	private Double creditAmount;	
	private Double balanceAmount;
	private String exciseEccNo;
	private String rangeAdd;
	private String division;
	private String genRemark;

	
	private CityDTO cityDTO;
	
	private String cityName; 
	private String state;
	private String country;
	private String shippingState;
	private String shippingCountry;
	
	private PartyTypeDTO partyTypeDTO;
	private String partyTypeDesc;	
	
	private String vatCstTaxType;
	
	public String getVatCstTaxType() {
		return vatCstTaxType;
	}
	public void setVatCstTaxType(String vatCstTaxType) {
		this.vatCstTaxType = vatCstTaxType;
	}

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
	
	public String getPartyNameCity() {
		return partyName + "-" + cityName;
	}
	
	/**
	 * @return the partyTypeId
	 */
	public Integer getPartyTypeId() {
		return partyTypeId;
	}
	/**
	 * @param partyTypeId the partyTypeId to set
	 */
	public void setPartyTypeId(Integer partyTypeId) {
		this.partyTypeId = partyTypeId;
	}
	
	
	public String getBalanceType() {
		return balanceType;
	}
	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
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
	
	/**
	 * @return the partyGroup
	 */
	public String getPartyGroup() {
		return partyGroup;
	}
	/**
	 * @param partyGroup the partyGroup to set
	 */
	public void setPartyGroup(String partyGroup) {
		this.partyGroup = partyGroup;
	}
	/**
	 * @return the closingBalance
	 */
	public Double getClosingBalance() {
		return closingBalance;
	}
	/**
	 * @param closingBalance the closingBalance to set
	 */
	public void setClosingBalance(Double closingBalance) {
		this.closingBalance = closingBalance;
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
	 * @return the shippingState
	 */
	public String getShippingState() {
		return shippingState;
	}
	/**
	 * @param shippingState the shippingState to set
	 */
	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}
	/**
	 * @return the shippingCountry
	 */
	public String getShippingCountry() {
		return shippingCountry;
	}
	/**
	 * @param shippingCountry the shippingCountry to set
	 */
	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}
	/**
	 * @return the cityDTO
	 */
	public CityDTO getCityDTO() {
		return cityDTO;
	}
	/**
	 * @param cityDTO the cityDTO to set
	 */
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
	/**
	 * @return the partyTypeDTO
	 */
	public PartyTypeDTO getPartyTypeDTO() {
		return partyTypeDTO;
	}
	/**
	 * @param partyTypeDTO the partyTypeDTO to set
	 */
	public void setPartyTypeDTO(PartyTypeDTO partyTypeDTO) {
		this.partyTypeDTO = partyTypeDTO;
	}
	/**
	 * @return the partyTypeDesc
	 */
	public String getPartyTypeDesc() {
		return partyTypeDesc;
	}
	/**
	 * @param partyTypeDesc the partyTypeDesc to set
	 */
	public void setPartyTypeDesc(String partyTypeDesc) {
		this.partyTypeDesc = partyTypeDesc;
	}
	@Override
	public String toString() {
		return "PartyDTO [partyId=" + partyId + ", partyCode=" + partyCode
				+ ", partyName=" + partyName + ", partyTypeId=" + partyTypeId
				+ ", partyGroup=" + partyGroup + ", openingBalance="
				+ openingBalance + ", closingBalance=" + closingBalance
				+ ", balanceType=" + balanceType + ", activeStatus="
				+ activeStatus + ", billingAddress=" + billingAddress
				+ ", billingCityId=" + billingCityId + ", billingZipCode="
				+ billingZipCode + ", phoneO1=" + phoneO1 + ", phoneW1="
				+ phoneW1 + ", contactPerson1=" + contactPerson1 + ", mobile1="
				+ mobile1 + ", fax1=" + fax1 + ", email1=" + email1
				+ ", shippingAddress=" + shippingAddress + ", shippingCityId="
				+ shippingCityId + ", shippingZipCode=" + shippingZipCode
				+ ", phoneO2=" + phoneO2 + ", phoneW2=" + phoneW2
				+ ", contactPerson2=" + contactPerson2 + ", mobile2=" + mobile2
				+ ", fax2=" + fax2 + ", email2=" + email2 + ", email=" + email
				+ ", website=" + website + ", shippingAddFlag="
				+ shippingAddFlag + ", invoiceType=" + invoiceType
				+ ", paymentType=" + paymentType + ", drugLicenceNo="
				+ drugLicenceNo + ", vatNo=" + vatNo + ", vatDt=" + vatDt
				+ ", cstNo=" + cstNo + ", cstDt=" + cstDt + ", partyRating="
				+ partyRating + ", assementDate=" + assementDate + ", ieCode="
				+ ieCode + ", msmeCode=" + msmeCode + ", panNo=" + panNo
				+ ", bankAcct1=" + bankAcct1 + ", bankName1=" + bankName1
				+ ", branchName1=" + branchName1 + ", ifscCode1=" + ifscCode1
				+ ", bankAcct2=" + bankAcct2 + ", bankName2=" + bankName2
				+ ", branchName2=" + branchName2 + ", ifscCode2=" + ifscCode2
				+ ", creditDays=" + creditDays + ", creditLimit=" + creditLimit
				+ ", overdueDays=" + overdueDays + ", transporterId="
				+ transporterId + ", formId=" + formId + ", tdsFlag=" + tdsFlag
				+ ", tds=" + tds + ", servTaxNo=" + servTaxNo + ", servTaxDt="
				+ servTaxDt + ", debitAmount=" + debitAmount
				+ ", creditAmount=" + creditAmount + ", balanceAmount="
				+ balanceAmount + ", exciseEccNo=" + exciseEccNo
				+ ", rangeAdd=" + rangeAdd + ", division=" + division
				+ ", genRemark=" + genRemark + ", state=" + state
				+ ", country=" + country + ", shippingState=" + shippingState
				+ ", shippingCountry=" + shippingCountry + ", cityDTO="
				+ cityDTO + ", cityName=" + cityName + ", partyTypeDTO="
				+ partyTypeDTO + ", partyTypeDesc=" + partyTypeDesc + "]";
	}
	public Double getOpeningBalance() {
		
		return openingBalance;
	}
	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
		
	}
	
	
}
