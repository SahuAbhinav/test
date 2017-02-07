package com.advanz.erp.masters.entity.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name="t_quotation_mast")
public class QuotationMasterEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="quotation_auto_id")
	private Integer quotationAutoId;
	
	@Column(name="transaction_series")
	private String transactionSeries;
	
	
	@Column(name="finyr")
	private String finYear;
	
	
	@Column(name="quotation_number",unique=true)
	private String quotationNumber;
	
	@Column(name="quotation_id")
	private Integer quotationId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="quotation_date")
	private Date quotationDate;

	@Column(name="reference_no")
	private String referenceNo;
	
	@Column(name="reference_date")
	private Date referenceDate;
	
	@ManyToOne
	@JoinColumn(name="party_id")
	private PartyEntity partyEntity;
	
	@Column(name="payment_terms")
	private String paymentTerms;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="valid_upto")
	private Date validUpTo;
	
	@Column(name="quotation_remark")
	private String quotationRemark;
		
	@Column(name="item_value")
	private Double itemValue;
	
	@Column(name="excise_duty_amount")
	private Double exciseDutyAmount;
	
	@Column(name="education_cess_perc")
	private Double educationCessPerc;
	
	@Column(name="education_cess_amount")
	private Double educationCessAmount;
	
	@Column(name="high_education_cess_perc")
	private Double highEducationCessPerc;
	
	@Column(name="high_education_cess_amount")
	private Double highEducationCessAmount;
	
	@Column(name="discount_amount")
	private Double discountAmount;
	
	@Column(name="vat_amount")
	private Double vatAmount;
	
	@Column(name="cst_amount")
	private Double cstAmount;
	
	@Column(name="other_amount")
	private Double otherAmount;
	
	@Column(name="qo_net_amount")
	private Double qoNetAmount;
	
	@Column(name="taxable_amount")
	private Double taxableAmount;
		
	
	@Column(name="others_detail")
	private String othersDetail;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="quotation_auto_id")
	private List<QuotationDetailEntity> quotationDetailEntities;
	
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	private BranchEntity branchEntity;
	
	@Column(name="packet_total")
	private Double packetTotal=0.0;


	public Integer getQuotationAutoId() {
		return quotationAutoId;
	}


	public void setQuotationAutoId(Integer quotationAutoId) {
		this.quotationAutoId = quotationAutoId;
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


	public String getQuotationNumber() {
		return quotationNumber;
	}


	public void setQuotationNumber(String quotationNumber) {
		this.quotationNumber = quotationNumber;
	}


	public Integer getQuotationId() {
		return quotationId;
	}


	public void setQuotationId(Integer quotationId) {
		this.quotationId = quotationId;
	}


	public Date getQuotationDate() {
		return quotationDate;
	}


	public void setQuotationDate(Date quotationDate) {
		this.quotationDate = quotationDate;
	}


	public String getReferenceNo() {
		return referenceNo;
	}


	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}


	public Date getReferenceDate() {
		return referenceDate;
	}


	public void setReferenceDate(Date referenceDate) {
		this.referenceDate = referenceDate;
	}


	public PartyEntity getPartyEntity() {
		return partyEntity;
	}


	public void setPartyEntity(PartyEntity partyEntity) {
		this.partyEntity = partyEntity;
	}


	public String getPaymentTerms() {
		return paymentTerms;
	}


	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}


	public Date getValidUpTo() {
		return validUpTo;
	}


	public void setValidUpTo(Date validUpTo) {
		this.validUpTo = validUpTo;
	}


	public String getQuotationRemark() {
		return quotationRemark;
	}


	public void setQuotationRemark(String quotationRemark) {
		this.quotationRemark = quotationRemark;
	}


	public Double getItemValue() {
		return itemValue;
	}


	public void setItemValue(Double itemValue) {
		this.itemValue = itemValue;
	}


	public Double getExciseDutyAmount() {
		return exciseDutyAmount;
	}


	public void setExciseDutyAmount(Double exciseDutyAmount) {
		this.exciseDutyAmount = exciseDutyAmount;
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


	public Double getOtherAmount() {
		return otherAmount;
	}


	public void setOtherAmount(Double otherAmount) {
		this.otherAmount = otherAmount;
	}


	public Double getQoNetAmount() {
		return qoNetAmount;
	}


	public void setQoNetAmount(Double qoNetAmount) {
		this.qoNetAmount = qoNetAmount;
	}


	public Double getTaxableAmount() {
		return taxableAmount;
	}


	public void setTaxableAmount(Double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}


	public String getOthersDetail() {
		return othersDetail;
	}


	public void setOthersDetail(String othersDetail) {
		this.othersDetail = othersDetail;
	}


	public List<QuotationDetailEntity> getQuotationDetailEntities() {
		return quotationDetailEntities;
	}


	public void setQuotationDetailEntities(
			List<QuotationDetailEntity> quotationDetailEntities) {
		this.quotationDetailEntities = quotationDetailEntities;
	}


	public BranchEntity getBranchEntity() {
		return branchEntity;
	}


	public void setBranchEntity(BranchEntity branchEntity) {
		this.branchEntity = branchEntity;
	}


	public Double getPacketTotal() {
		return packetTotal;
	}


	public void setPacketTotal(Double packetTotal) {
		this.packetTotal = packetTotal;
	}
	
	
	

}
