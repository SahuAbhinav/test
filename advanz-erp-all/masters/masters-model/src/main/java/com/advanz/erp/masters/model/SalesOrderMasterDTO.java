package com.advanz.erp.masters.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;


@SuppressWarnings("serial")
public class SalesOrderMasterDTO  extends BaseDTO {
	private Integer salesOrderAutoId;
	private String transactionSeries;
	private String finYear;
	private Integer salesOrderId;
	private String salesOrderNumber;	
	private Date salesOrderDate;
	private Date orderReceiptDate;
	private String quotationNumber;
	private Integer partiId;
	private String orderTakenBy;
	private Time orderTime;
	private Date desireDeliveryDate;
	private Date plannedDeliveryDate;
	private String salesOrderRemark;
	private Double itemValue;
	private Double exciseDutyAmount;
	private Double educationCessAmount;
	private Double highEducationCessAmount;
	private Double discountAmount;
	private Double vatAmount;
	private Double cstAmount;
	private Double otherAmount;
	private Double soNetAmount;
	private Double taxableAmount;
	private String patyPoNumber;
	private Date partyPoDate;
	private String othersDetail;
	
	private PartyDTO partyDTO;
	
	private List<SalesOrderDetailDTO> salesOrderDetailDTOList;
	
	private BranchDTO branchDTO;
	
	//extra
	private Double educationCessPerc=0.0;	
	private Double highEducationCessPerc=0.0;	
	private String taxType; 	//CST or VAT	
	private Double taxTotal;
	private String orderSeries;
	private Double totalQuantity;
	
	
	private Date soValidUptoDate;
	
	
	private Integer consigneeId;
	
	private Boolean isUsedInInvoice;
	private String freightType;
	private Double freightAmt;
	private Double packingForwarding;
	private String termsOfPayment;
	private String termsOfDelivery;
	private String shipToAddress;
	private Integer itemGroupFlagId;
	
	
	public Integer getItemGroupFlagId() {
		return itemGroupFlagId;
	}
	public void setItemGroupFlagId(Integer itemGroupFlagId) {
		this.itemGroupFlagId = itemGroupFlagId;
	}
	public Integer getConsigneeId() {
		return consigneeId;
	}
	public void setConsigneeId(Integer consigneeId) {
		this.consigneeId = consigneeId;
	}
	public Date getSoValidUptoDate() {
		return soValidUptoDate;
	}
	public void setSoValidUptoDate(Date soValidUptoDate) {
		this.soValidUptoDate = soValidUptoDate;
	}
	public Double getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(Double totalQuantity) {
		this.totalQuantity = totalQuantity;
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
	public String getSalesOrderNumber() {
		
		return salesOrderNumber;
	}
	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}
	public Integer getSalesOrderId() {
		return salesOrderId;
	}
	public void setSalesOrderId(Integer salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
	public Date getSalesOrderDate() {
		return salesOrderDate;
	}
	public void setSalesOrderDate(Date salesOrderDate) {
		this.salesOrderDate = salesOrderDate;
	}
	public Date getOrderReceiptDate() {
		return orderReceiptDate;
	}
	public void setOrderReceiptDate(Date orderReceiptDate) {
		this.orderReceiptDate = orderReceiptDate;
	}
	public String getQuotationNumber() {
		return quotationNumber;
	}
	public void setQuotationNumber(String quotationNumber) {
		this.quotationNumber = quotationNumber;
	}
	public Integer getPartiId() {
		return partiId;
	}
	public void setPartiId(Integer partiId) {
		this.partiId = partiId;
	}
	public String getOrderTakenBy() {
		return orderTakenBy;
	}
	public void setOrderTakenBy(String orderTakenBy) {
		this.orderTakenBy = orderTakenBy;
	}
	public Time getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Time orderTime) {
		this.orderTime = orderTime;
	}
	public Date getDesireDeliveryDate() {
		return desireDeliveryDate;
	}
	public void setDesireDeliveryDate(Date desireDeliveryDate) {
		this.desireDeliveryDate = desireDeliveryDate;
	}
	public Date getPlannedDeliveryDate() {
		return plannedDeliveryDate;
	}
	public void setPlannedDeliveryDate(Date plannedDeliveryDate) {
		this.plannedDeliveryDate = plannedDeliveryDate;
	}
	public String getSalesOrderRemark() {
		return salesOrderRemark;
	}
	public void setSalesOrderRemark(String salesOrderRemark) {
		this.salesOrderRemark = salesOrderRemark;
	}
	public Double getItemValue() {
		if(itemValue==null)
			itemValue=0.00;
		return itemValue;
	}
	public void setItemValue(Double itemValue) {
		this.itemValue = itemValue;
	}
	public Double getExciseDutyAmount() {
		if(exciseDutyAmount==null)
			exciseDutyAmount=0.00;
		return exciseDutyAmount;
	}
	public void setExciseDutyAmount(Double exciseDutyAmount) {
		this.exciseDutyAmount = exciseDutyAmount;
	}
	public Double getEducationCessAmount() {
		//educationCessAmount=getExciseDutyAmount() *getEducationCessPerc()/100;
		return educationCessAmount;
	}
	public void setEducationCessAmount(Double educationCessAmount) {
		this.educationCessAmount = educationCessAmount;
	}
	public Double getHighEducationCessAmount() {
	//	highEducationCessAmount=getExciseDutyAmount()*getHighEducationCessPerc()/100;
		return highEducationCessAmount;
	}
	public void setHighEducationCessAmount(Double highEducationCessAmount) {
		this.highEducationCessAmount = highEducationCessAmount;
	}
	public Double getDiscountAmount() {
		if(discountAmount==null)
			discountAmount=0.00;
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Double getVatAmount() {
		if(vatAmount==null)
			vatAmount=0.00;
		return vatAmount;
	}
	public void setVatAmount(Double vatAmount) {
		this.vatAmount = vatAmount;
	}
	public Double getCstAmount() {
		if(cstAmount==null)
			cstAmount=0.00;
		return cstAmount;
	}
	public void setCstAmount(Double cstAmount) {
		this.cstAmount = cstAmount;
	}
	public Double getOtherAmount() {
		if(otherAmount==null)
			otherAmount=0.00;		
		return otherAmount;
	}
	public void setOtherAmount(Double otherAmount) {
		this.otherAmount = otherAmount;
	}
	public Double getSoNetAmount() {
		if(soNetAmount==null)
			soNetAmount=0.00;
		return soNetAmount;
	}
	public void setSoNetAmount(Double soNetAmount) {
		this.soNetAmount = soNetAmount;
	}
	public Double getTaxableAmount() {
		if(taxableAmount==null)
			taxableAmount=0.00;
		
		return taxableAmount;
	}
	public void setTaxableAmount(Double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}
	public String getPatyPoNumber() {
		return patyPoNumber;
	}
	public void setPatyPoNumber(String patyPoNumber) {
		this.patyPoNumber = patyPoNumber;
	}
	public Date getPartyPoDate() {
		return partyPoDate;
	}
	public void setPartyPoDate(Date partyPoDate) {
		this.partyPoDate = partyPoDate;
	}
	public String getOthersDetail() {
		return othersDetail;
	}
	public void setOthersDetail(String othersDetail) {
		this.othersDetail = othersDetail;
	}

	
	public PartyDTO getPartyDTO() {
		return partyDTO;
	}
	public void setPartyDTO(PartyDTO partyDTO) {
		this.partyDTO = partyDTO;
		if(partyDTO!=null){
			if("saleswithInState".equals(partyDTO.getInvoiceType())){
				setTaxType("VAT");
				if(salesOrderDetailDTOList!=null){
					for(SalesOrderDetailDTO detailDTO:salesOrderDetailDTOList){
						detailDTO.setTaxPerc(detailDTO.getVatPerc());
						detailDTO.setPartyInvoiceType(partyDTO.getInvoiceType());
					}
				}						
			}
			else{
				setTaxType("CST");
				if(salesOrderDetailDTOList!=null){
					for(SalesOrderDetailDTO detailDTO:salesOrderDetailDTOList){
						detailDTO.setTaxPerc(detailDTO.getCstPerc());
						detailDTO.setPartyInvoiceType(partyDTO.getInvoiceType());
					}
				}	
				
			}
		}
	}
	public List<SalesOrderDetailDTO> getSalesOrderDetailDTOList() {
		return salesOrderDetailDTOList;
	}
	public void setSalesOrderDetailDTOList(
			List<SalesOrderDetailDTO> salesOrderDetailDTOList) {
		this.salesOrderDetailDTOList = salesOrderDetailDTOList;
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
			highEducationCessPerc=0.00;
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
		if(taxTotal==null){
		if("VAT".equals(getTaxType()))
			taxTotal=getVatAmount();	
			
		if("CST".equals(getTaxType()))
			taxTotal=getCstAmount();
		}		
		
		return taxTotal;
	}
	public void setTaxTotal(Double taxTotal) {
		this.taxTotal = taxTotal;			
	}	
	
	public String getOrderSeries() {
		orderSeries=getTransactionSeries()+"/"+getFinYear();
		return orderSeries;
	}
	public void setOrderSeries(String orderSeries) {
		this.orderSeries = orderSeries;
	}
	
	
	
	
	
	public Integer getSalesOrderAutoId() {
		return salesOrderAutoId;
	}
	public void setSalesOrderAutoId(Integer salesOrderAutoId) {
		this.salesOrderAutoId = salesOrderAutoId;
	}
	public BranchDTO getBranchDTO() {
		return branchDTO;
	}
	public void setBranchDTO(BranchDTO branchDTO) {
		this.branchDTO = branchDTO;
	}
	@Override
	public String toString() {
		return "SalesOrderMasterDTO [partyDTO=" + partyDTO
				+ ", salesOrderDetailDTOList=" + salesOrderDetailDTOList + "]";
	}
	public Boolean getIsUsedInInvoice() {
		return isUsedInInvoice;
	}
	public void setIsUsedInInvoice(Boolean isUsedInInvoice) {
		this.isUsedInInvoice = isUsedInInvoice;
	}
	public String getFreightType() {
		return freightType;
	}
	public void setFreightType(String freightType) {
		this.freightType = freightType;
	}
	public Double getFreightAmt() {
		return freightAmt;
	}
	public void setFreightAmt(Double freightAmt) {
		this.freightAmt = freightAmt;
	}
	public Double getPackingForwarding() {
		return packingForwarding;
	}
	public void setPackingForwarding(Double packingForwarding) {
		this.packingForwarding = packingForwarding;
	}
	public String getTermsOfPayment() {
		return termsOfPayment;
	}
	public void setTermsOfPayment(String termsOfPayment) {
		this.termsOfPayment = termsOfPayment;
	}
	public String getTermsOfDelivery() {
		return termsOfDelivery;
	}
	public void setTermsOfDelivery(String termsOfDelivery) {
		this.termsOfDelivery = termsOfDelivery;
	}
	public String getShipToAddress() {
		return shipToAddress;
	}
	public void setShipToAddress(String shipToAddress) {
		this.shipToAddress = shipToAddress;
	}

}
