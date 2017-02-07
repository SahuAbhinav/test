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
@Table(name="m_company")
public class CompanyEntity extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3908697834756178117L;
	
	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name = "comp_id", unique = true, updatable = false, length=11)
	private Integer 	companyId;
	@Column(name="comp_code", length=4)
	private String 		companyCode;
	@Column(name="comp_name", length=65)
	private String 		companyName;
	@Column(name="fin_yr_beg")
	private Date 		financialYrBeg;
	@Column(name="fin_yr_end")
	private Date 		financialYrEnd;
	@Column(name="system_locking")
	private Date 		systemLocking;
	@Column(name="comp_add", length=150)
	private String 		companyAdd;
	@Column(name="comp_city", length=25)
	private String 		companyCity;
	@Column(name="comp_state", length=25)
	private String 		companyState;
	@Column(name="comp_country", length=25)
	private String 		companyCountry;
	@Column(name="factory_phone", length=35)
	private String 		factoryPhone;
	@Column(name="phone_1_o", length=35)
	private String 		phone1;
	@Column(name="phone_2_o", length=35)
	private String 		phone2;
	@Column(name="pin_zip_code", length=11)
	private Integer     pinZipCode; 
	@Column(name="regi_office_add", length=150)
	private String      officeAdd;
	@Column(name="regi_office_phone_1", length=35)
	private String 		officePhone1;
	@Column(name="regi_office_phone_2", length=35)
	private String 		officePhone2;	
	@Column(name="regi_pin_zip_code", length=11)
	private Integer     regPinZipCode; 
	@Column(name="regi_comp_city", length=25)
	private String 		regCompanyCity;
	@Column(name="regi_comp_state", length=25)
	private String 		regCompanyState;
	@Column(name="regi_comp_country", length=25)
	private String 		regCompanyCountry;
	@Column(name="fax", length=35)
	private String 		fax;
	@Column(name="emailid", length=65)
	private String 		emailId;
	@Column(name="website", length=65)
	private String 		website;
	@Column(name="vatno", length=35)
	private String 		vatNo;
	@Column(name="vatdt")
	private Date 		vatDt;
	@Column(name="cstno", length=35)
	private String 		cstNo;
	@Column(name="cstdt")
	private Date 		cstDt;
	@Column(name="msme_code", length=35)
	private String 		msmeCode;
	@Column(name="currancy_symbol", length=4)
	private String 		currancySymbol;
	@Column(name="drug_licence_1", length=35)
	private String 		drugLicence1;
	@Column(name="drug_licence_2", length=35)
	private String 		drugLicence2;
	@Column(name="exciseeccno", length=35)
	private String 		exciseECCNo;
	@Column(name="range_add", length=35)
	private String      rangeAdd;
	@Column(name="division", length=35)
	private String      division;
	@Column(name="panno", length=25)
	private String 		panNo;
	@Column(name="pandt")
	private Date 		panDt;
	@Column(name="servtaxno")
	private String 		servTaxNo;
	@Column(name="servtaxdt", length=35)
	private Date 		servTaxDt;
	@Column(name="import_export_code", length=25)
	private String 		importExportCode;
	@Column(name="general_remark", length=250)
	private String      generalRemark;	
	
	@Column(name="commissionerate", length=25)
	private String commissionerate;
	
	@Column(name="email_flag")
	private Boolean emailFlag;
	
	
	@Column(name="salary_genarating_from_date")
	private Date 		salaryGenaratingFromDate;
	
	
	@Column(name="neftrtgs")
	private String neftrtgs;
	@Column(name="account_number")
	private String accountNumber; 
	@Column(name="bank_name")
	private String  bankName;
	
	@Column(name="insurance_policy_no")
	private String  insurancePolicyNo;
	
	@Column(name="weighting_file_path",length=250)
	private String  weightingFilePath;
	
	@Column(name="stock_lock_flag")
	private Boolean stockLockFlag;
	
	@Column(name="issue_lock_flag")
	private Boolean issueLockFlag;
	
	@Column(name="blanket_cutoff_date")
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
	public Date getSalaryGenaratingFromDate() {
		return salaryGenaratingFromDate;
	}
	public void setSalaryGenaratingFromDate(Date salaryGenaratingFromDate) {
		this.salaryGenaratingFromDate = salaryGenaratingFromDate;
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
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Date getFinancialYrBeg() {
		return financialYrBeg;
	}
	public void setFinancialYrBeg(Date financialYrBeg) {
		this.financialYrBeg = financialYrBeg;
	}
	public Date getFinancialYrEnd() {
		return financialYrEnd;
	}
	public void setFinancialYrEnd(Date financialYrEnd) {
		this.financialYrEnd = financialYrEnd;
	}
	public Date getSystemLocking() {
		return systemLocking;
	}
	public void setSystemLocking(Date systemLocking) {
		this.systemLocking = systemLocking;
	}
	public String getCompanyAdd() {
		return companyAdd;
	}
	public void setCompanyAdd(String companyAdd) {
		this.companyAdd = companyAdd;
	}
	public String getCompanyCity() {
		return companyCity;
	}
	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}
	public String getCompanyState() {
		return companyState;
	}
	public void setCompanyState(String companyState) {
		this.companyState = companyState;
	}
	public String getCompanyCountry() {
		return companyCountry;
	}
	public void setCompanyCountry(String companyCountry) {
		this.companyCountry = companyCountry;
	}
	public String getFactoryPhone() {
		return factoryPhone;
	}
	public void setFactoryPhone(String factoryPhone) {
		this.factoryPhone = factoryPhone;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public Integer getPinZipCode() {
		return pinZipCode;
	}
	public void setPinZipCode(Integer pinZipCode) {
		this.pinZipCode = pinZipCode;
	}
	public String getOfficeAdd() {
		return officeAdd;
	}
	public void setOfficeAdd(String officeAdd) {
		this.officeAdd = officeAdd;
	}
	public String getOfficePhone1() {
		return officePhone1;
	}
	public void setOfficePhone1(String officePhone1) {
		this.officePhone1 = officePhone1;
	}
	public String getOfficePhone2() {
		return officePhone2;
	}
	public void setOfficePhone2(String officePhone2) {
		this.officePhone2 = officePhone2;
	}
	public Integer getRegPinZipCode() {
		return regPinZipCode;
	}
	public void setRegPinZipCode(Integer regPinZipCode) {
		this.regPinZipCode = regPinZipCode;
	}
	public String getRegCompanyCity() {
		return regCompanyCity;
	}
	public void setRegCompanyCity(String regCompanyCity) {
		this.regCompanyCity = regCompanyCity;
	}
	public String getRegCompanyState() {
		return regCompanyState;
	}
	public void setRegCompanyState(String regCompanyState) {
		this.regCompanyState = regCompanyState;
	}
	public String getRegCompanyCountry() {
		return regCompanyCountry;
	}
	public void setRegCompanyCountry(String regCompanyCountry) {
		this.regCompanyCountry = regCompanyCountry;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getVatNo() {
		return vatNo;
	}
	public void setVatNo(String vatNo) {
		this.vatNo = vatNo;
	}
	public Date getVatDt() {
		return vatDt;
	}
	public void setVatDt(Date vatDt) {
		this.vatDt = vatDt;
	}
	public String getCstNo() {
		return cstNo;
	}
	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}
	public Date getCstDt() {
		return cstDt;
	}
	public void setCstDt(Date cstDt) {
		this.cstDt = cstDt;
	}
	public String getMsmeCode() {
		return msmeCode;
	}
	public void setMsmeCode(String msmeCode) {
		this.msmeCode = msmeCode;
	}
	public String getCurrancySymbol() {
		return currancySymbol;
	}
	public void setCurrancySymbol(String currancySymbol) {
		this.currancySymbol = currancySymbol;
	}
	public String getDrugLicence1() {
		return drugLicence1;
	}
	public void setDrugLicence1(String drugLicence1) {
		this.drugLicence1 = drugLicence1;
	}
	public String getDrugLicence2() {
		return drugLicence2;
	}
	public void setDrugLicence2(String drugLicence2) {
		this.drugLicence2 = drugLicence2;
	}
	public String getExciseECCNo() {
		return exciseECCNo;
	}
	public void setExciseECCNo(String exciseECCNo) {
		this.exciseECCNo = exciseECCNo;
	}
	public String getRangeAdd() {
		return rangeAdd;
	}
	public void setRangeAdd(String rangeAdd) {
		this.rangeAdd = rangeAdd;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public Date getPanDt() {
		return panDt;
	}
	public void setPanDt(Date panDt) {
		this.panDt = panDt;
	}
	public String getServTaxNo() {
		return servTaxNo;
	}
	public void setServTaxNo(String servTaxNo) {
		this.servTaxNo = servTaxNo;
	}
	public Date getServTaxDt() {
		return servTaxDt;
	}
	public void setServTaxDt(Date servTaxDt) {
		this.servTaxDt = servTaxDt;
	}
	public String getImportExportCode() {
		return importExportCode;
	}
	public void setImportExportCode(String importExportCode) {
		this.importExportCode = importExportCode;
	}
	public String getGeneralRemark() {
		return generalRemark;
	}
	public void setGeneralRemark(String generalRemark) {
		this.generalRemark = generalRemark;
	}
	public Boolean getEmailFlag() {
		return emailFlag;
	}
	public void setEmailFlag(Boolean emailFlag) {
		this.emailFlag = emailFlag;
	}
	public String getNeftrtgs() {
		return neftrtgs;
	}
	public void setNeftrtgs(String neftrtgs) {
		this.neftrtgs = neftrtgs;
	}
	
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getInsurancePolicyNo() {
		return insurancePolicyNo;
	}
	public void setInsurancePolicyNo(String insurancePolicyNo) {
		this.insurancePolicyNo = insurancePolicyNo;
	}	
	
}
