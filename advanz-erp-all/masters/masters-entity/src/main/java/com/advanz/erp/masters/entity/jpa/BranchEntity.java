package com.advanz.erp.masters.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

/**
 * @author ct20268
 *
 */

@Entity
@Table(name = "m_branch")
public class BranchEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7883666338946845254L;
	
	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="branch_id", unique=true)
	private Integer branchId;
	
	@Column(name="branch_name", length=35)
	private String branch;
	
	@Column(name="invoice_code", length=4)
	private String invoiceCode;	
	
	@Column(name="branch_address", length=150)
	private String address;
	
	@Column(name="branch_city", length=25)
	private String city;
	
	@Column(name="branch_state", length=25)
	private String state;
	
	@Column(name="branch_country", length=25)
	private String country;
	
	@Column(name="pin_zip_code", length=11) 
	private String pinZip;
	
	@Column(name="branch_phone_1_o", length=35)
	private String phone1;
	
	@Column(name="branch_phone_2_o", length=35)
	private String phone2;
	
	@Column(name="branch_fax", length=11)
	private String fax;
	
	@Column(name = "branch_vatno")
	private String vatNo;
	
	@Temporal( TemporalType.DATE)
	@Column(name="branch_vatdt")
	private Date vatDate;
	
	@Column(name="branch_cstno", length=35)
	private String cstNo;
	
	@Temporal( TemporalType.DATE)
	@Column(name="branch_cstdt")
	private Date cstDate;
	
	@Column(name="stock_restrict_flag")
	private int stockRestrictFlag;
	
	@Column(name="credit_limit")
	private Double creditLimit;
	
	@Column(name = "credit_days")
	private Integer creditDays;
	
	@Column(name="branch_mail_id", length=65)
	private String emailId;
	
	@Column(name="branch_web_site", length=65)
	private String website;
	
	@Column(name="servtaxno", length=25)
	private String servTaxNo;
	
	@Temporal( TemporalType.DATE)
	@Column(name="servtaxdt")
	private Date servTaxDate;
	
	@Column(name="msme_code", length=35)
	private String MSMECode;	
	
	@Column(name="branch_exciseeccno", length=35)
	private String exciseECCNo;
	
	@Column(name="branch_range_add", length=35)
	private String rangAdd;
	
	@Column(name="branch_division", length=35)
	private String division;	
	
	@Column(name="general_desc", length=255)
	private String generalDesc;

	@Column(name="commissionerate", length=25)
	private String commissionerate;
	
	@Column(name="duty_vide_notification", length=1)
	private Integer dutyVideNotification;
	  
	
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
	
}
