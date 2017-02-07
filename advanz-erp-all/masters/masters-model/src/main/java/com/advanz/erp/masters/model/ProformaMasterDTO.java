package com.advanz.erp.masters.model;

import java.sql.Time;
import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class ProformaMasterDTO extends BaseDTO {

	public static final String EXCISABLE="excisable";
	public static final String EXEMPTED="exempted";
	private String transactionSeries;
	private Integer invoiceAutoId;
	private String finyr;
	private String invoiceNumber;

	private Integer invoiceId;
	private Date invoiceDate;
	private Integer branchId;
	private String salesOrderNumber;
	private Integer partyId;
	private Integer employeeId;
	private Integer cityId;
	private Integer transportId;
	private Integer formReqFlag;
	private Integer formTypeId;
	private Date formDate;
	private String salesType;
	private Double itemValue = 0.00;
	private Double mrpValue = 0.00;
	private String freightType;
	private Double freightAmt = 0.00;
	private Double discountAmount = 0.00;

	private Double exciseDutyAmount = 0.00;
	private Double educationCessAmount = 0.00;
	private Double highEducationCessAmount = 0.00;
	private Double highEducationCessPerc = 0.0;
	private Double educationCessperc = 0.0;

	private String vatCstTaxType;

	private String roadPermitNo;
	private Date roadPermitDate;
	private String buyerPoNo;
	private Date buyerPoDate;
	private Double billNetAmountRoundOf = 0.00;

	private String itemName;

	private String finyearTransactionSeris;

	private Integer next;
	private Integer previous;
	private Double assessableValue = 0.0;
	private String exciseType;

	public String getExciseType() {
		return exciseType;
	}

	public void setExciseType(String exciseType) {
		this.exciseType = exciseType;
	}

	public String getFinyearTransactionSeris() {
		return finyearTransactionSeris;
	}

	public void setFinyearTransactionSeris(String finyearTransactionSeris) {
		this.finyearTransactionSeris = finyearTransactionSeris;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getBillNetAmountRoundOf() {
		return billNetAmountRoundOf;
	}

	public void setBillNetAmountRoundOf(Double billNetAmountRoundOf) {
		this.billNetAmountRoundOf = billNetAmountRoundOf;
	}

	public String getRoadPermitNo() {
		return roadPermitNo;
	}

	public void setRoadPermitNo(String roadPermitNo) {
		this.roadPermitNo = roadPermitNo;
	}

	public Date getRoadPermitDate() {
		return roadPermitDate;
	}

	public void setRoadPermitDate(Date roadPermitDate) {
		this.roadPermitDate = roadPermitDate;
	}

	public String getBuyerPoNo() {
		return buyerPoNo;
	}

	public void setBuyerPoNo(String buyerPoNo) {
		this.buyerPoNo = buyerPoNo;
	}

	public Date getBuyerPoDate() {
		return buyerPoDate;
	}

	public void setBuyerPoDate(Date buyerPoDate) {
		this.buyerPoDate = buyerPoDate;
	}

	public String getVatCstTaxType() {
		return vatCstTaxType;
	}

	public void setVatCstTaxType(String vatCstTaxType) {
		this.vatCstTaxType = vatCstTaxType;
	}

	// use by me
	private Integer totalItem;

	public Integer getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

	public Double getEducationCessperc() {
		return educationCessperc;
	}

	public void setEducationCessperc(Double educationCessperc) {
		this.educationCessperc = educationCessperc;
	}

	public Double getHighEducationCessPerc() {
		return highEducationCessPerc;
	}

	public void setHighEducationCessPerc(Double highEducationCessPerc) {
		this.highEducationCessPerc = highEducationCessPerc;
	}

	private Double vatAmount = 0.00;
	private Double cstAmount = 0.00;
	private Double otherAmount = 0.00;
	private String billRemark;
	private String formNo;
	private String lrNo;
	private Date lrDate;
	private Double billNetAmount = 0.00;
	private Double advanceFreight = 0.00;
	private Double taxableAmount = 0.00;
	private Double packetTotal = 0.00;
	private String othersDetail;
	private PartyDTO partyDTO;
	private String partyName;
	private EmployeeDTO employeeDTO;
	private String employeeName;

	// CR

	private Integer consigneeId;
	private String deliveryNoteNo;
	private Date deliveryNoteDate;
	private String despatchDocumentNo;
	private Date dateOfRemoval;
	private Time timeOfRemoval;
	private String despatchThrough;
	private String motorVehicleNo;
	private String termsOfPayment;
	private Double balanceFreightAmount = 0.00;
	private Double packingForwarding = 0.00;
	public BranchDTO branchDTO;

	private Date salesOrderDate;

	private String exciseDutyAmountInwords;
	private String cessAmountInwords;
	private String billNetAmountInwords;
	private String highEducationCessAmountInwords;
	private String exciseInvoiceNo;

	private Date fromDate;
	private Date toDate;
	private Integer itemGroupFlagId;

	public Integer getItemGroupFlagId() {
		return itemGroupFlagId;
	}

	public void setItemGroupFlagId(Integer itemGroupFlagId) {
		this.itemGroupFlagId = itemGroupFlagId;
	}

	public String getExciseInvoiceNo() {
		return exciseInvoiceNo;
	}

	public void setExciseInvoiceNo(String exciseInvoiceNo) {
		this.exciseInvoiceNo = exciseInvoiceNo;
	}

	public Date getSalesOrderDate() {
		return salesOrderDate;
	}

	public void setSalesOrderDate(Date salesOrderDate) {
		this.salesOrderDate = salesOrderDate;
	}

	public BranchDTO getBranchDTO() {
		return branchDTO;
	}

	public void setBranchDTO(BranchDTO branchDTO) {
		this.branchDTO = branchDTO;
	}

	public EmployeeDTO getEmployeeDTO() {
		return employeeDTO;
	}

	public void setEmployeeDTO(EmployeeDTO employeeDTO) {
		this.employeeDTO = employeeDTO;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public PartyDTO getPartyDTO() {
		return partyDTO;
	}

	public void setPartyDTO(PartyDTO partyDTO) {
		this.partyDTO = partyDTO;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getTransactionSeries() {
		return transactionSeries;
	}

	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	public Integer getInvoiceAutoId() {
		return invoiceAutoId;
	}

	public void setInvoiceAutoId(Integer invoiceAutoId) {
		this.invoiceAutoId = invoiceAutoId;
	}

	public String getFinyr() {
		return finyr;
	}

	public void setFinyr(String finyr) {
		this.finyr = finyr;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	public Integer getPartyId() {
		return partyId;
	}

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getTransportId() {
		return transportId;
	}

	public void setTransportId(Integer transportId) {
		this.transportId = transportId;
	}

	public Integer getFormReqFlag() {
		return formReqFlag;
	}

	public void setFormReqFlag(Integer formReqFlag) {
		this.formReqFlag = formReqFlag;
	}

	public Integer getFormTypeId() {
		return formTypeId;
	}

	public void setFormTypeId(Integer formTypeId) {
		this.formTypeId = formTypeId;
	}

	public Date getFormDate() {
		return formDate;
	}

	public void setFormDate(Date formDate) {
		this.formDate = formDate;
	}

	public String getSalesType() {
		return salesType;
	}

	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}

	public Double getItemValue() {
		return itemValue;
	}

	public void setItemValue(Double itemValue) {
		this.itemValue = itemValue;
	}

	public Double getMrpValue() {
		return mrpValue;
	}

	public void setMrpValue(Double mrpValue) {
		this.mrpValue = mrpValue;
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

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
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

	public String getBillRemark() {
		return billRemark;
	}

	public void setBillRemark(String billRemark) {
		this.billRemark = billRemark;
	}

	public String getFormNo() {
		return formNo;
	}

	public void setFormNo(String formNo) {
		this.formNo = formNo;
	}

	public String getLrNo() {
		return lrNo;
	}

	public void setLrNo(String lrNo) {
		this.lrNo = lrNo;
	}

	public Date getLrDate() {
		return lrDate;
	}

	public void setLrDate(Date lrDate) {
		this.lrDate = lrDate;
	}

	public Double getBillNetAmount() {
		return billNetAmount;
	}

	public void setBillNetAmount(Double billNetAmount) {
		this.billNetAmount = billNetAmount;
	}

	public Double getAdvanceFreight() {
		return advanceFreight;
	}

	public void setAdvanceFreight(Double advanceFreight) {
		this.advanceFreight = advanceFreight;
	}

	public Double getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(Double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	public Double getPacketTotal() {
		return packetTotal;
	}

	public void setPacketTotal(Double packetTotal) {
		this.packetTotal = packetTotal;
	}

	public String getOthersDetail() {
		return othersDetail;
	}

	public void setOthersDetail(String othersDetail) {
		this.othersDetail = othersDetail;
	}

	public Integer getConsigneeId() {
		return consigneeId;
	}

	public void setConsigneeId(Integer consigneeId) {
		this.consigneeId = consigneeId;
	}

	public String getDeliveryNoteNo() {
		return deliveryNoteNo;
	}

	public void setDeliveryNoteNo(String deliveryNoteNo) {
		this.deliveryNoteNo = deliveryNoteNo;
	}

	public Date getDeliveryNoteDate() {
		return deliveryNoteDate;
	}

	public void setDeliveryNoteDate(Date deliveryNoteDate) {
		this.deliveryNoteDate = deliveryNoteDate;
	}

	public String getDespatchDocumentNo() {
		return despatchDocumentNo;
	}

	public void setDespatchDocumentNo(String despatchDocumentNo) {
		this.despatchDocumentNo = despatchDocumentNo;
	}

	public Date getDateOfRemoval() {
		return dateOfRemoval;
	}

	public void setDateOfRemoval(Date dateOfRemoval) {
		this.dateOfRemoval = dateOfRemoval;
	}

	public Time getTimeOfRemoval() {
		return timeOfRemoval;
	}

	public void setTimeOfRemoval(Time timeOfRemoval) {
		this.timeOfRemoval = timeOfRemoval;
	}

	public String getDespatchThrough() {
		return despatchThrough;
	}

	public void setDespatchThrough(String despatchThrough) {
		this.despatchThrough = despatchThrough;
	}

	public String getMotorVehicleNo() {
		return motorVehicleNo;
	}

	public void setMotorVehicleNo(String motorVehicleNo) {
		this.motorVehicleNo = motorVehicleNo;
	}

	public String getTermsOfPayment() {
		return termsOfPayment;
	}

	public void setTermsOfPayment(String termsOfPayment) {
		this.termsOfPayment = termsOfPayment;
	}

	public Double getBalanceFreightAmount() {
		return balanceFreightAmount;
	}

	public void setBalanceFreightAmount(Double balanceFreightAmount) {
		this.balanceFreightAmount = balanceFreightAmount;
	}

	public Double getPackingForwarding() {
		return packingForwarding;
	}

	public void setPackingForwarding(Double packingForwarding) {
		this.packingForwarding = packingForwarding;
	}

	public String getExciseDutyAmountInwords() {
		return exciseDutyAmountInwords;
	}

	public void setExciseDutyAmountInwords(String exciseDutyAmountInwords) {
		this.exciseDutyAmountInwords = exciseDutyAmountInwords;
	}

	public String getCessAmountInwords() {
		return cessAmountInwords;
	}

	public void setCessAmountInwords(String cessAmountInwords) {
		this.cessAmountInwords = cessAmountInwords;
	}

	public String getBillNetAmountInwords() {
		return billNetAmountInwords;
	}

	public void setBillNetAmountInwords(String billNetAmountInwords) {
		this.billNetAmountInwords = billNetAmountInwords;
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

	public Double getAssessableValue() {
		return assessableValue;
	}

	public void setAssessableValue(Double assessableValue) {
		this.assessableValue = assessableValue;
	}

	public String getHighEducationCessAmountInwords() {
		return highEducationCessAmountInwords;
	}

	public void setHighEducationCessAmountInwords(
			String highEducationCessAmountInwords) {
		this.highEducationCessAmountInwords = highEducationCessAmountInwords;
	}

	@Override
	public String toString() {
		return "ProformaMasterDTO [invoiceDate=" + invoiceDate + ",formDate="
				+ formDate + ",lrDate=" + lrDate + ", partyDTO=" + partyDTO
				+ ", employeeDTO=" + employeeDTO + ", employeeName="
				+ employeeName + ", employeeId=" + employeeId + ", branchDTO="
				+ branchDTO + ",fromDate=" + fromDate + ",toDate=" + toDate
				+ "]";
	}

}
