package com.advanz.erp.masters.model;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class QuotationMasterDTO extends BaseDTO {
	private Integer quotationAutoId;
	private String transactionSeries;
	private String finYear;
	private Integer quotationId;

	private String quotationNumber;
	private Date quotationDate;
	private BranchDTO branchDTO;
	private String referenceNo;
	private Date referenceDate;
	private PartyDTO partyDTO;
	private String paymentTerms;
	private Date validUpTo;
	private String quotationRemark;
	private Double itemValue=0.0;
	private Double exciseDutyAmount=0.0;
	private Double educationCessAmount=0.0;
	private Double highEducationCessAmount=0.0;
	private Double discountAmount=0.0;
	private Double taxableAmount=0.0;
	private Double vatAmount=0.0;
	private Double cstAmount=0.0;
	private Double otherAmount=0.0;
	private String othersDetail;
	private Double qoNetAmount=0.0;
	private Double packetTotal=0.0;
	private Boolean salesOrderFlag;
	private String SalesOrderNumber;
	private Double educationCessPerc;
	private Double highEducationCessPerc;

	// extra
	private String taxType; // CST or VAT
	private Double taxTotal;
	private String quotationSeries;
	private Double itemCount;
	List<QuotationDetailDTO> quotationDetailDTOList;
	private Double totalQuantity;
	
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
	public Integer getQuotationId() {
		return quotationId;
	}
	public void setQuotationId(Integer quotationId) {
		this.quotationId = quotationId;
	}
	public String getQuotationNumber() {
		return quotationNumber;
	}
	public void setQuotationNumber(String quotationNumber) {
		this.quotationNumber = quotationNumber;
	}
	public Date getQuotationDate() {
		return quotationDate;
	}
	public void setQuotationDate(Date quotationDate) {
		this.quotationDate = quotationDate;
	}
	public BranchDTO getBranchDTO() {
		return branchDTO;
	}
	public void setBranchDTO(BranchDTO branchDTO) {
		this.branchDTO = branchDTO;
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
	public PartyDTO getPartyDTO() {
		return partyDTO;
	}
	public void setPartyDTO(PartyDTO partyDTO) {
				this.partyDTO = partyDTO;
				if(partyDTO!=null){
					if("saleswithInState".equals(partyDTO.getInvoiceType())){
						setTaxType("VAT");
						if(quotationDetailDTOList!=null){
							for(QuotationDetailDTO detailDTO:quotationDetailDTOList){
								detailDTO.setTaxPerc(detailDTO.getVatPerc());
							}
						}						
					}
					else{
						setTaxType("CST");
						if(quotationDetailDTOList!=null){
							for(QuotationDetailDTO detailDTO:quotationDetailDTOList){
								detailDTO.setTaxPerc(detailDTO.getCstPerc());
							}
						}	
						
					}
				}
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
	public Double getEducationCessAmount() {
		return educationCessAmount;
	}
	public void setEducationCessAmount(Double educationCessAmount) {
		this.educationCessAmount = educationCessAmount;
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
	public Double getTaxableAmount() {
		if(taxableAmount==null)
			taxableAmount=0.0;
		return taxableAmount;
	}
	public void setTaxableAmount(Double taxableAmount) {
		this.taxableAmount = taxableAmount;
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
	public String getOthersDetail() {
		return othersDetail;
	}
	public void setOthersDetail(String othersDetail) {
		this.othersDetail = othersDetail;
	}
	public Double getQoNetAmount() {
		return qoNetAmount;
	}
	public void setQoNetAmount(Double qoNetAmount) {
		this.qoNetAmount = qoNetAmount;
	}
	public Double getPacketTotal() {
		return packetTotal;
	}
	public void setPacketTotal(Double packetTotal) {
		this.packetTotal = packetTotal;
	}
	public Boolean getSalesOrderFlag() {
		return salesOrderFlag;
	}
	public void setSalesOrderFlag(Boolean salesOrderFlag) {
		this.salesOrderFlag = salesOrderFlag;
	}
	public String getSalesOrderNumber() {
		return SalesOrderNumber;
	}
	public void setSalesOrderNumber(String salesOrderNumber) {
		SalesOrderNumber = salesOrderNumber;
	}
	public Double getEducationCessPerc() {
		if(educationCessPerc==null)
			educationCessPerc=0.0;
		return educationCessPerc;
	}
	public void setEducationCessPerc(Double educationCessPerc) {
		this.educationCessPerc = educationCessPerc;
	}
	public Double getHighEducationCessPerc() {
		if(highEducationCessPerc==null)
			highEducationCessPerc=0.0;
		return highEducationCessPerc;
	}
	public void setHighEducationCessPerc(Double highEducationCessPerc) {
		this.highEducationCessPerc = highEducationCessPerc;
	}
	public String getTaxType() {
		return taxType;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	public Double getTaxTotal() {
		return taxTotal;
	}
	public void setTaxTotal(Double taxTotal) {
		this.taxTotal = taxTotal;
	}
	public String getQuotationSeries() {
		quotationSeries = getTransactionSeries() + "/" + getFinYear();
		return quotationSeries;
	}
	public void setQuotationSeries(String quotationSeries) {
		this.quotationSeries = quotationSeries;
	}
	public Double getItemCount() {
		return itemCount;
	}
	public void setItemCount(Double itemCount) {
		this.itemCount = itemCount;
	}
	public List<QuotationDetailDTO> getQuotationDetailDTOList() {
		return quotationDetailDTOList;
	}
	public void setQuotationDetailDTOList(
			List<QuotationDetailDTO> quotationDetailDTOList) {
		this.quotationDetailDTOList = quotationDetailDTOList;
	}
	

	public Double getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(Double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	
	
	
	
	
//	public Integer getQuotationAutoId() {
//		return quotationAutoId;
//	}
//
//	public void setQuotationAutoId(Integer quotationAutoId) {
//		this.quotationAutoId = quotationAutoId;
//	}
//
//	public String getTransactionSeries() {
//		return transactionSeries;
//	}
//
//	public void setTransactionSeries(String transactionSeries) {
//		this.transactionSeries = transactionSeries;
//	}
//
//	public String getFinYear() {
//		return finYear;
//	}
//
//	public void setFinYear(String finYear) {
//		this.finYear = finYear;
//	}
//
//	public Integer getQuotationId() {
//		return quotationId;
//	}
//
//	public void setQuotationId(Integer quotationId) {
//		this.quotationId = quotationId;
//	}
//
//	public String getQuotationNumber() {
//		return quotationNumber;
//	}
//
//	public void setQuotationNumber(String quotationNumber) {
//		this.quotationNumber = quotationNumber;
//	}
//
//	public Date getQuotationDate() {
//		return quotationDate;
//	}
//
//	public void setQuotationDate(Date quotationDate) {
//		this.quotationDate = quotationDate;
//	}
//
//	public BranchDTO getBranchDTO() {
//		return branchDTO;
//	}
//
//	public void setBranchDTO(BranchDTO branchDTO) {
//		this.branchDTO = branchDTO;
//	}
//
//	public String getReferenceNo() {
//		return referenceNo;
//	}
//
//	public void setReferenceNo(String referenceNo) {
//		this.referenceNo = referenceNo;
//	}
//
//	public Date getReferenceDate() {
//		return referenceDate;
//	}
//
//	public void setReferenceDate(Date referenceDate) {
//		this.referenceDate = referenceDate;
//	}
//
//	public PartyDTO getPartyDTO() {
//		return partyDTO;
//	}
//
//	public void setPartyDTO(PartyDTO partyDTO) {
//		this.partyDTO = partyDTO;
//	}
//
//	public String getPaymentTerms() {
//		return paymentTerms;
//	}
//
//	public void setPaymentTerms(String paymentTerms) {
//		this.paymentTerms = paymentTerms;
//	}
//
//	public Date getValidUpTo() {
//		return validUpTo;
//	}
//
//	public void setValidUpTo(Date validUpTo) {
//		this.validUpTo = validUpTo;
//	}
//
//	public String getQuotationRemark() {
//		return quotationRemark;
//	}
//
//	public void setQuotationRemark(String quotationRemark) {
//		this.quotationRemark = quotationRemark;
//	}
//
//	public Double getItemValue() {
//		if(itemValue==null)
//			itemValue=0.0;
//		return itemValue;
//	}
//
//	public void setItemValue(Double itemValue) {
//		this.itemValue = itemValue;
//	}
//
//	public Double getExciseDutyAmount() {
//		if(exciseDutyAmount==null)
//			exciseDutyAmount=0.0;
//		return exciseDutyAmount;
//	}
//
//	public void setExciseDutyAmount(Double exciseDutyAmount) {
//		this.exciseDutyAmount = exciseDutyAmount;
//	}
//
//	public Double getEducationCessAmount() {
//		if(educationCessAmount==null)
//			educationCessAmount=0.0;
//		return educationCessAmount;
//	}
//
//	public void setEducationCessAmount(Double educationCessAmount) {
//		this.educationCessAmount = educationCessAmount;
//	}
//
//	public Double getHighEducationCessAmount() {
//		if(highEducationCessAmount==null)
//			highEducationCessAmount=0.0;
//		return highEducationCessAmount;
//	}
//
//	public void setHighEducationCessAmount(Double highEducationCessAmount) {
//		this.highEducationCessAmount = highEducationCessAmount;
//	}
//
//	public Double getDiscountAmount() {
//		if(discountAmount==null)
//			discountAmount=0.0;
//		return discountAmount;
//	}
//
//	public void setDiscountAmount(Double discountAmount) {
//		this.discountAmount = discountAmount;
//	}
//
//	public Double getTaxableAmount() {
//		if(taxableAmount==null)
//		taxableAmount	=0.0;
//		return taxableAmount;
//	}
//
//	public void setTaxableAmount(Double taxableAmount) {
//		this.taxableAmount = taxableAmount;
//	}
//
//	public Double getVatAmount() {
//		if(vatAmount==null)
//			vatAmount=0.0;
//		return vatAmount;
//	}
//
//	public void setVatAmount(Double vatAmount) {
//		this.vatAmount = vatAmount;
//	}
//
//	public Double getCstAmount() {
//		if(cstAmount==null)
//			cstAmount=0.0;
//		return cstAmount;
//	}
//
//	public void setCstAmount(Double cstAmount) {
//		this.cstAmount = cstAmount;
//	}
//
//	public Double getOtherAmount() {
//		if(otherAmount==null)
//			otherAmount=0.0;
//		return otherAmount;
//	}
//
//	public void setOtherAmount(Double otherAmount) {
//		this.otherAmount = otherAmount;
//	}
//
//	public String getOthersDetail() {
//		return othersDetail;
//	}
//
//	public void setOthersDetail(String othersDetail) {
//		this.othersDetail = othersDetail;
//	}
//
//	public Double getQoNetAmount() {
//		if(qoNetAmount==null)
//			qoNetAmount=0.0;
//		return qoNetAmount;
//	}
//
//	public void setQoNetAmount(Double qoNetAmount) {
//		this.qoNetAmount = qoNetAmount;
//	}
//
//	public Double getPacketTotal() {
//		if(packetTotal==null)
//			packetTotal=0.0;
//		return packetTotal;
//	}
//
//	public void setPacketTotal(Double packetTotal) {
//		this.packetTotal = packetTotal;
//	}
//
//	public Boolean getSalesOrderFlag() {
//		return salesOrderFlag;
//	}
//
//	public void setSalesOrderFlag(Boolean salesOrderFlag) {
//		this.salesOrderFlag = salesOrderFlag;
//	}
//
//	public String getSalesOrderNumber() {
//		return SalesOrderNumber;
//	}
//
//	public void setSalesOrderNumber(String salesOrderNumber) {
//		SalesOrderNumber = salesOrderNumber;
//	}
//
//	public Double getEducationCessPerc() {
//		if(educationCessPerc==null)
//			educationCessPerc=0.0;
//		return educationCessPerc;
//	}
//
//	public void setEducationCessPerc(Double educationCessPerc) {
//		this.educationCessPerc = educationCessPerc;
//	}
//
//	public Double getHighEducationCessPerc() {
//		if(highEducationCessPerc==null)
//			highEducationCessPerc=0.0;
//		return highEducationCessPerc;
//	}
//
//	public void setHighEducationCessPerc(Double highEducationCessPerc) {
//		this.highEducationCessPerc = highEducationCessPerc;
//	}
//
//	public String getTaxType() {
//		return taxType;
//	}
//
//	public void setTaxType(String taxType) {
//		this.taxType = taxType;
//	}
//
//	public Double getTaxTotal() {
//		if(taxTotal==null)
//			taxTotal=0.0;
//		return taxTotal;
//	}
//
//	public void setTaxTotal(Double taxTotal) {
//		this.taxTotal = taxTotal;
//	}
//
//	public String getQuotationSeries() {
//		quotationSeries = getTransactionSeries() + "-" + getFinYear();
//		return quotationSeries;
//	}
//
//	public void setQuotationSeries(String quotationSeries) {
//		this.quotationSeries = quotationSeries;
//	}
//
//	public List<QuotationDetailDTO> getQuotationDetailDTOList() {
//		return quotationDetailDTOList;
//	}
//
//	public void setQuotationDetailDTOList(
//			List<QuotationDetailDTO> quotationDetailDTOList) {
//		this.quotationDetailDTOList = quotationDetailDTOList;
//	}
//
//	public Double getItemCount() {
//		if(itemCount==null)
//			itemCount=0.0;
//		return itemCount;
//	}
//
//	public void setItemCount(Double itemCount) {
//		this.itemCount = itemCount;
//	}

}
