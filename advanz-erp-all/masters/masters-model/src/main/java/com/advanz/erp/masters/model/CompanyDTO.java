package com.advanz.erp.masters.model;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;
public class CompanyDTO extends BaseDTO{

		/**
	 * 
	 */
		private static final long serialVersionUID = -1593797619622527630L;
	
		private Integer 	companyId;
		private String 		companyCode;
		private String 		companyName;
		private Date 		financialYrBeg;
		private Date 		financialYrEnd;
		private Date 		systemLocking;
		private String 		companyAdd;
		private String 		companyCity;
		private String 		companyState;
		private String 		companyCountry;
		private String 		factoryPhone;
		private String 		phone1;
		private String 		phone2;
		private Integer     pinZipCode; 
		private String      officeAdd;
		private String 		officePhone1;
		private String 		officePhone2;	
		private Integer     regPinZipCode; 
		private String 		regCompanyCity;
		private String 		regCompanyState;
		private String 		regCompanyCountry;
		private String 		fax;
		private String 		emailId;
		private String 		website;
		private String 		vatNo;
		private Date 		vatDt;
		private String 		cstNo;
		private Date 		cstDt;
		private String 		msmeCode;
		private String 		currancySymbol;
		private String 		drugLicence1;
		private String 		drugLicence2;
		private String 		exciseECCNo;
		private String      rangeAdd;
		private String      division;
		private String 		panNo;
		private Date 		panDt;
		private String 		servTaxNo;
		private Date 		servTaxDt;
		private String 		importExportCode;
		private String      generalRemark;
		
		private String commissionerate;
		private Boolean emailFlag;
		private Date 		salaryGenaratingFromDate;
		
		private String neftrtgs;
		private String accountNumber; 
		private String  bankName;
		private String  insurancePolicyNo;
		private String weightingFilePath;
		private Boolean stockLockFlag;
		private Boolean issueLockFlag;
		private Date 	blanketCutoffDate;
		
		
		public Date getBlanketCutoffDate() {
			return blanketCutoffDate;
		}
		public void setBlanketCutoffDate(Date blanketCutoffDate) {
			this.blanketCutoffDate = blanketCutoffDate;
		}
		public Boolean getStockLockFlag() {
			return stockLockFlag;
		}
		public void setStockLockFlag(Boolean stockLockFlag) {
			this.stockLockFlag = stockLockFlag;
		}
		public Boolean getIssueLockFlag() {
			return issueLockFlag;
		}
		public void setIssueLockFlag(Boolean issueLockFlag) {
			this.issueLockFlag = issueLockFlag;
		}
		public String getWeightingFilePath() {
			return weightingFilePath;
		}
		public void setWeightingFilePath(String weightingFilePath) {
			this.weightingFilePath = weightingFilePath;
		}
		public String getCommissionerate() {
			return commissionerate;
		}
		public void setCommissionerate(String commissionerate) {
			this.commissionerate = commissionerate;
		}
		/**
		 * @return the companyId
		 */
		public Integer getCompanyId() {
			return companyId;
		}
		/**
		 * @param companyId the companyId to set
		 */
		public void setCompanyId(Integer companyId) {
			this.companyId = companyId;
		}
		/**
		 * @return the companyCode
		 */
		public String getCompanyCode() {
			return companyCode;
		}
		/**
		 * @param companyCode the companyCode to set
		 */
		public void setCompanyCode(String companyCode) {
			this.companyCode = companyCode;
		}
		/**
		 * @return the companyName
		 */
		public String getCompanyName() {
			return companyName;
		}
		/**
		 * @param companyName the companyName to set
		 */
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		/**
		 * @return the financialYrBeg
		 */
		public Date getFinancialYrBeg() {
			return financialYrBeg;
		}
		/**
		 * @param financialYrBeg the financialYrBeg to set
		 */
		public void setFinancialYrBeg(Date financialYrBeg) {
			this.financialYrBeg = financialYrBeg;
		}
		/**
		 * @return the financialYrEnd
		 */
		public Date getFinancialYrEnd() {
			return financialYrEnd;
		}
		/**
		 * @param financialYrEnd the financialYrEnd to set
		 */
		public void setFinancialYrEnd(Date financialYrEnd) {
			this.financialYrEnd = financialYrEnd;
		}
		/**
		 * @return the systemLocking
		 */
		public Date getSystemLocking() {
			return systemLocking;
		}
		/**
		 * @param systemLocking the systemLocking to set
		 */
		public void setSystemLocking(Date systemLocking) {
			this.systemLocking = systemLocking;
		}
		/**
		 * @return the companyAdd
		 */
		public String getCompanyAdd() {
			return companyAdd;
		}
		/**
		 * @param companyAdd the companyAdd to set
		 */
		public void setCompanyAdd(String companyAdd) {
			this.companyAdd = companyAdd;
		}
		/**
		 * @return the companyCity
		 */
		public String getCompanyCity() {
			return companyCity;
		}
		/**
		 * @param companyCity the companyCity to set
		 */
		public void setCompanyCity(String companyCity) {
			this.companyCity = companyCity;
		}
		/**
		 * @return the companyState
		 */
		public String getCompanyState() {
			return companyState;
		}
		/**
		 * @param companyState the companyState to set
		 */
		public void setCompanyState(String companyState) {
			this.companyState = companyState;
		}
		/**
		 * @return the companyCountry
		 */
		public String getCompanyCountry() {
			return companyCountry;
		}
		/**
		 * @param companyCountry the companyCountry to set
		 */
		public void setCompanyCountry(String companyCountry) {
			this.companyCountry = companyCountry;
		}
		/**
		 * @return the factoryPhone
		 */
		public String getFactoryPhone() {
			return factoryPhone;
		}
		/**
		 * @param factoryPhone the factoryPhone to set
		 */
		public void setFactoryPhone(String factoryPhone) {
			this.factoryPhone = factoryPhone;
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
		 * @return the pinZipCode
		 */
		public Integer getPinZipCode() {
			return pinZipCode;
		}
		/**
		 * @param pinZipCode the pinZipCode to set
		 */
		public void setPinZipCode(Integer pinZipCode) {
			this.pinZipCode = pinZipCode;
		}
		/**
		 * @return the officeAdd
		 */
		public String getOfficeAdd() {
			return officeAdd;
		}
		/**
		 * @param officeAdd the officeAdd to set
		 */
		public void setOfficeAdd(String officeAdd) {
			this.officeAdd = officeAdd;
		}
		/**
		 * @return the officePhone1
		 */
		public String getOfficePhone1() {
			return officePhone1;
		}
		/**
		 * @param officePhone1 the officePhone1 to set
		 */
		public void setOfficePhone1(String officePhone1) {
			this.officePhone1 = officePhone1;
		}
		/**
		 * @return the officePhone2
		 */
		public String getOfficePhone2() {
			return officePhone2;
		}
		/**
		 * @param officePhone2 the officePhone2 to set
		 */
		public void setOfficePhone2(String officePhone2) {
			this.officePhone2 = officePhone2;
		}
		/**
		 * @return the regPinZipCode
		 */
		public Integer getRegPinZipCode() {
			return regPinZipCode;
		}
		/**
		 * @param regPinZipCode the regPinZipCode to set
		 */
		public void setRegPinZipCode(Integer regPinZipCode) {
			this.regPinZipCode = regPinZipCode;
		}
		/**
		 * @return the regCompanyCity
		 */
		public String getRegCompanyCity() {
			return regCompanyCity;
		}
		/**
		 * @param regCompanyCity the regCompanyCity to set
		 */
		public void setRegCompanyCity(String regCompanyCity) {
			this.regCompanyCity = regCompanyCity;
		}
		/**
		 * @return the regCompanyState
		 */
		public String getRegCompanyState() {
			return regCompanyState;
		}
		/**
		 * @param regCompanyState the regCompanyState to set
		 */
		public void setRegCompanyState(String regCompanyState) {
			this.regCompanyState = regCompanyState;
		}
		/**
		 * @return the regCompanyCountry
		 */
		public String getRegCompanyCountry() {
			return regCompanyCountry;
		}
		/**
		 * @param regCompanyCountry the regCompanyCountry to set
		 */
		public void setRegCompanyCountry(String regCompanyCountry) {
			this.regCompanyCountry = regCompanyCountry;
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
		 * @return the currancySymbol
		 */
		public String getCurrancySymbol() {
			return currancySymbol;
		}
		/**
		 * @param currancySymbol the currancySymbol to set
		 */
		public void setCurrancySymbol(String currancySymbol) {
			this.currancySymbol = currancySymbol;
		}
		/**
		 * @return the drugLicence1
		 */
		public String getDrugLicence1() {
			return drugLicence1;
		}
		/**
		 * @param drugLicence1 the drugLicence1 to set
		 */
		public void setDrugLicence1(String drugLicence1) {
			this.drugLicence1 = drugLicence1;
		}
		/**
		 * @return the drugLicence2
		 */
		public String getDrugLicence2() {
			return drugLicence2;
		}
		/**
		 * @param drugLicence2 the drugLicence2 to set
		 */
		public void setDrugLicence2(String drugLicence2) {
			this.drugLicence2 = drugLicence2;
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
		 * @return the panDt
		 */
		public Date getPanDt() {
			return panDt;
		}
		/**
		 * @param panDt the panDt to set
		 */
		public void setPanDt(Date panDt) {
			this.panDt = panDt;
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
		 * @return the importExportCode
		 */
		public String getImportExportCode() {
			return importExportCode;
		}
		/**
		 * @param importExportCode the importExportCode to set
		 */
		public void setImportExportCode(String importExportCode) {
			this.importExportCode = importExportCode;
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
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		
		@Override
		public String toString() {
			return "CompanyDTO [companyId=" + companyId + ", companyCode="
					+ companyCode + ", companyName=" + companyName
					+ ", financialYrBeg=" + financialYrBeg
					+ ", financialYrEnd=" + financialYrEnd + ", systemLocking="
					+ systemLocking + ", companyAdd=" + companyAdd
					+ ", companyCity=" + companyCity + ", companyState="
					+ companyState + ", companyCountry=" + companyCountry
					+ ", factoryPhone=" + factoryPhone + ", phone1=" + phone1
					+ ", phone2=" + phone2 + ", pinZipCode=" + pinZipCode
					+ ", officeAdd=" + officeAdd + ", officePhone1="
					+ officePhone1 + ", officePhone2=" + officePhone2
					+ ", regPinZipCode=" + regPinZipCode + ", regCompanyCity="
					+ regCompanyCity + ", regCompanyState=" + regCompanyState
					+ ", regCompanyCountry=" + regCompanyCountry + ", fax="
					+ fax + ", emailId=" + emailId + ", website=" + website
					+ ", vatNo=" + vatNo + ", vatDt=" + vatDt + ", cstNo="
					+ cstNo + ", cstDt=" + cstDt + ", msmeCode=" + msmeCode
					+ ", currancySymbol=" + currancySymbol + ", drugLicence1="
					+ drugLicence1 + ", drugLicence2=" + drugLicence2
					+ ", exciseECCNo=" + exciseECCNo + ", rangeAdd=" + rangeAdd
					+ ", division=" + division + ", panNo=" + panNo
					+ ", panDt=" + panDt + ", servTaxNo=" + servTaxNo
					+ ", servTaxDt=" + servTaxDt + ", importExportCode="
					+ importExportCode + ", generalRemark=" + generalRemark
					+ "]";
		}
		public Boolean getEmailFlag() {
			return emailFlag;
		}
		public void setEmailFlag(Boolean emailFlag) {
			this.emailFlag = emailFlag;
		}
		public Date getSalaryGenaratingFromDate() {
			return salaryGenaratingFromDate;
		}
		public void setSalaryGenaratingFromDate(Date salaryGenaratingFromDate) {
			this.salaryGenaratingFromDate = salaryGenaratingFromDate;
		}
		public String getNeftrtgs() {
			return neftrtgs;
		}
		public void setNeftrtgs(String neftrtgs) {
			this.neftrtgs = neftrtgs;
		}
		
		public String getBankName() {
			return bankName;
		}
		public void setBankName(String bankName) {
			this.bankName = bankName;
		}
		public String getAccountNumber() {
			return accountNumber;
		}
		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}
		public String getInsurancePolicyNo() {
			return insurancePolicyNo;
		}
		public void setInsurancePolicyNo(String insurancePolicyNo) {
			this.insurancePolicyNo = insurancePolicyNo;
		}

}