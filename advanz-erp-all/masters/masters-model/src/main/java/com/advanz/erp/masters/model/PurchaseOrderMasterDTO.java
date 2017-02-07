package com.advanz.erp.masters.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

public class PurchaseOrderMasterDTO extends BaseDTO{

private Integer count;
	
	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	
	private String succ;
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}

	private String transactionSeries;
	
	
	private String finYear;
	
	private String purchaseOrderNumber;
	
	private Integer purchaseOrderId;
	
	private Date purchaseOrderDate;
	private String purchaseOrderDateStr;
	
	private String purchaseOrderIdSeries;
	
	private Double balanceQuantity;
	
	private int itemCount;
	
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	public Double getBalanceQuantity() {
		return balanceQuantity;
	}
	public void setBalanceQuantity(Double balanceQuantity) {
		this.balanceQuantity = balanceQuantity;
	}
	public String getPurchaseOrderIdSeries() {
		return purchaseOrderIdSeries;
	}
	public void setPurchaseOrderIdSeries(String purchaseOrderIdSeries) {
		this.purchaseOrderIdSeries = purchaseOrderIdSeries;
	}
	/**
	 * @return the purchaseOrderDateStr
	 */
	public String getPurchaseOrderDateStr() {
		if(purchaseOrderDate!=null){
			SimpleDateFormat obj=new SimpleDateFormat("dd-MM-yyyy");
			return obj.format(purchaseOrderDate);
		}
		return purchaseOrderDateStr;
	}
	/**
	 * @param purchaseOrderDateStr the purchaseOrderDateStr to set
	 */
	public void setPurchaseOrderDateStr(String purchaseOrderDateStr) {
		this.purchaseOrderDateStr = purchaseOrderDateStr;
	}
	private Date indentDate;
	
	private String indentNumber;
	
	private BranchDTO branchDTO;
	
	private PartyDTO partyDTO;
	
	private Integer cityId;
	
	private String phoneOffice;
	
	private String contactPerson;
	
	private TransporterDTO transportDTO;
	
	private ItemGroupFlagDTO itemGroupFlagDTO;
	
	private String supplierReference;
	
	private String ourReference;
	
	private String paymentTerms;
	
	private Date desireDeliveryDate;
	
	private String desireDeliveryDateStr;
	
	private String itemName;
	private Date fromDate;
	private Date toDate;
	
	private Integer next;
	private Integer previous;
	
	private Date poValidUptoDate;
	
	public Date getPoValidUptoDate() {
		return poValidUptoDate;
	}

	public void setPoValidUptoDate(Date poValidUptoDate) {
		this.poValidUptoDate = poValidUptoDate;
	}
	public Integer getNext() {
		return next;
	}
	public void setNext(Integer next) {
		this.next = next;
	}
	public Integer getPrevious() {
		return previous;
	}
	public void setPrevious(Integer previous) {
		this.previous = previous;
	}

	private Integer itemId;
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @param desireDeliveryDateStr the desireDeliveryDateStr to set
	 */
	public void setDesireDeliveryDateStr(String desireDeliveryDateStr) {
		this.desireDeliveryDateStr = desireDeliveryDateStr;
	}
	public String getDesireDeliveryDateStr() {
		if(desireDeliveryDate!=null){
			SimpleDateFormat obj=new SimpleDateFormat("dd-MM-yyyy");
			return obj.format(purchaseOrderDate);
		}
		return desireDeliveryDateStr;
	}
	
	private String delivery_terms;
	
	private Integer formReqFlag;
	
	private Date formDate;
	
	private String formnumber;
	
	private Integer poAutoId;
	
	//
	private Double exciseDutyAmount;
	private Double vatAmount;
	private Double cstAmount;
	private Double taxableAmount;
	private String vatCstType;

	private Double taxVatCstTotal;
	public Double getTaxVatCstTotal() {
		return taxVatCstTotal;
	}
	public void setTaxVatCstTotal(Double taxVatCstTotal) {
		this.taxVatCstTotal = taxVatCstTotal;
	}
	/**
	 * @return the formTypeId
	 */
	public Integer getFormTypeId() {
		return formTypeId;
	}
	/**
	 * @param formTypeId the formTypeId to set
	 */
	public void setFormTypeId(Integer formTypeId) {
		this.formTypeId = formTypeId;
	}
	private Integer formTypeId;
	
	/**
	 * @return the poAutoId
	 */
	public Integer getPoAutoId() {
		return poAutoId;
	}

	/**
	 * @param poAutoId the poAutoId to set
	 */
	public void setPoAutoId(Integer poAutoId) {
		this.poAutoId = poAutoId;
	}

	/**
	 * @return the formnumber
	 */
	public String getFormnumber() {
		return formnumber;
	}

	/**
	 * @param formnumber the formnumber to set
	 */
	public void setFormnumber(String formnumber) {
		this.formnumber = formnumber;
	}

	private Double itemValue=0.0;
	
	private Double discountAmount=0.0;
	
	private Double poNetAmount=0.0;
	
	private String poRemark;

	private List<PurchaseOrderDetailDTO> purchaseOrderDetailDTOList;

	
	 private Double educationCessPerc=0.0;
	 private Double educationCessAmount=0.0;
	 private Double highEducationCessPerc=0.0;
	 private Double highEducationCessAmount=0.0;
	
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

	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public Integer getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Integer purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public Date getPurchaseOrderDate() {
		return purchaseOrderDate;
	}

	public void setPurchaseOrderDate(Date purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}

	public Date getIndentDate() {
		return indentDate;
	}

	public void setIndentDate(Date indentDate) {
		this.indentDate = indentDate;
	}

	public String getIndentNumber() {
		return indentNumber;
	}

	public void setIndentNumber(String indentNumber) {
		this.indentNumber = indentNumber;
	}

	public BranchDTO getBranchDTO() {
		return branchDTO;
	}

	public void setBranchDTO(BranchDTO branchDTO) {
		this.branchDTO = branchDTO;
	}

	public PartyDTO getPartyDTO() {
		return partyDTO;
	}

	public void setPartyDTO(PartyDTO partyDTO) {
		this.partyDTO = partyDTO;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getPhoneOffice() {
		return phoneOffice;
	}

	public void setPhoneOffice(String phoneOffice) {
		this.phoneOffice = phoneOffice;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public TransporterDTO getTransportDTO() {
		return transportDTO;
	}

	public void setTransportDTO(TransporterDTO transportDTO) {
		this.transportDTO = transportDTO;
	}

	public ItemGroupFlagDTO getItemGroupFlagDTO() {
		return itemGroupFlagDTO;
	}

	public void setItemGroupFlagDTO(ItemGroupFlagDTO itemGroupFlagDTO) {
		this.itemGroupFlagDTO = itemGroupFlagDTO;
	}

	public String getSupplierReference() {
		return supplierReference;
	}

	public void setSupplierReference(String supplierReference) {
		this.supplierReference = supplierReference;
	}

	public String getOurReference() {
		return ourReference;
	}

	public void setOurReference(String ourReference) {
		this.ourReference = ourReference;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public Date getDesireDeliveryDate() {
		return desireDeliveryDate;
	}

	public void setDesireDeliveryDate(Date desireDeliveryDate) {
		this.desireDeliveryDate = desireDeliveryDate;
	}

	public String getDelivery_terms() {
		return delivery_terms;
	}

	public void setDelivery_terms(String delivery_terms) {
		this.delivery_terms = delivery_terms;
	}

	public Integer getFormReqFlag() {
		return formReqFlag;
	}

	public void setFormReqFlag(Integer formReqFlag) {
		this.formReqFlag = formReqFlag;
	}

	public Date getFormDate() {
		return formDate;
	}

	public void setFormDate(Date formDate) {
		this.formDate = formDate;
	}

	public Double getItemValue() {
		return itemValue;
	}

	public void setItemValue(Double itemValue) {
		this.itemValue = itemValue;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getPoNetAmount() {
		return poNetAmount;
	}

	public void setPoNetAmount(Double poNetAmount) {
		this.poNetAmount = poNetAmount;
	}

	public String getPoRemark() {
		return poRemark;
	}

	public void setPoRemark(String poRemark) {
		this.poRemark = poRemark;
	}

	public List<PurchaseOrderDetailDTO> getPurchaseOrderDetailDTOList() {
		return purchaseOrderDetailDTOList;
	}

	public void setPurchaseOrderDetailDTOList(
			List<PurchaseOrderDetailDTO> purchaseOrderDetailDTOList) {
		this.purchaseOrderDetailDTOList = purchaseOrderDetailDTOList;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Double getExciseDutyAmount() {
		return exciseDutyAmount;
	}
	public void setExciseDutyAmount(Double exciseDutyAmount) {
		this.exciseDutyAmount = exciseDutyAmount;
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
	public Double getTaxableAmount() {
		return taxableAmount;
	}
	public void setTaxableAmount(Double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}
	public String getVatCstType() {
		return vatCstType;
	}
	public void setVatCstType(String vatCstType) {
		this.vatCstType = vatCstType;
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
	
	
	
}
