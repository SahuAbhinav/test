package com.advanz.erp.masters.entity.jpa;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "t_proforma_mast")
public class ProformaMasterEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String transactionSeries;
	private Integer invoiceAutoId;
	private String finyr;
	private String invoiceNumber;
	
	private Integer invoiceId;
	private Date invoiceDate;
	//private Integer branchId;
	
	private BranchEntity branchEntity;
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	public BranchEntity getBranchEntity() {
		return branchEntity;
	}

	public void setBranchEntity(BranchEntity branchEntity) {
		this.branchEntity = branchEntity;
	}

	private String salesOrderNumber;
	//private Integer partyId;
	
	//private Integer employeeId;
	private EmployeeEntity employeeEntity;
	

	private Integer cityId;
	private Integer transportId;
	private Integer formReqFlag;
	private Integer formTypeId;
	private Date formDate;
	private String salesType;
	private Double itemValue;
	private Double mrpValue;
	private String freightType;
	private Double freightAmt;
	private Double discountAmount;
	private Double exciseDutyAmount;
	private Double educationCessAmount;
	private Double highEducationCessAmount;
	private Double vatAmount;
	private Double cstAmount;
	private Double otherAmount;
	private String billRemark;
	private String formNo;
	private String lrNo;
	private Date lrDate;
	private Double billNetAmount;
	private Double advanceFreight;
	private Double taxableAmount;
	private Double packetTotal;
	private String othersDetail;

	
	private Double highEducationCessPerc;  
	
	private Double educationCessperc;
	
	
	

	private PartyEntity partyEntity;
	
	
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
	private Double balanceFreightAmount;
	private Double packingForwarding;
	private Date salesOrderDate;
	
	private String exciseDutyAmountInwords;
	private String cessAmountInwords;
	private String billNetAmountInwords;
	private String highEducationCessAmountInwords;
	
	private String vatCstTaxType;
	
	private String roadPermitNo;
	private Date roadPermitDate;
	private String buyerPoNo;
	private Date buyerPoDate; 
	
	private Double billNetAmountRoundOf;
	private String exciseInvoiceNo;
	
	private Double assessableValue;
	
	
	private Integer itemGroupFlagId;
	
	private String exciseType;
	
	
	@Column(name="excise_type")
	public String getExciseType() {
		return exciseType;
	}

	public void setExciseType(String exciseType) {
		this.exciseType = exciseType;
	}

	@Column(name="item_group_flag_id")
	public Integer getItemGroupFlagId() {
		return itemGroupFlagId;
	}

	public void setItemGroupFlagId(Integer itemGroupFlagId) {
		this.itemGroupFlagId = itemGroupFlagId;
	}

	@Column(name = "transaction_series")
	public String getTransactionSeries() {
		return transactionSeries;
	}

	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name = "invoice_auto_id", unique = true)
	public Integer getInvoiceAutoId() {
		return invoiceAutoId;
	}

	public void setInvoiceAutoId(Integer invoiceAutoId) {
		this.invoiceAutoId = invoiceAutoId;
	}

	@Column(name = "high_education_cess_perc")
	public Double getHighEducationCessPerc() {
		return highEducationCessPerc;
	}

	public void setHighEducationCessPerc(Double highEducationCessPerc) {
		this.highEducationCessPerc = highEducationCessPerc;
	}

	@Column(name = "education_cess_perc")
	public Double getEducationCessperc() {
		return educationCessperc;
	}

	public void setEducationCessperc(Double educationCessperc) {
		this.educationCessperc = educationCessperc;
	}
	
	@Column(name = "finyr")
	public String getFinyr() {
		return finyr;
	}

	public void setFinyr(String finyr) {
		this.finyr = finyr;
	}

	@Column(name = "invoice_number")
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	@Column(name = "invoice_id")
	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	@Column(name = "invoice_date")
	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	/*@Column(name = "branch_id")
	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}*/

	@Column(name = "sales_order_number")
	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	/*@Column(name = "party_id")
	public Integer getPartyId() {
		return partyId;
	}

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}*/

/*	@Column(name = "employee_id")
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}*/
	
	
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	public EmployeeEntity getEmployeeEntity() {
		return employeeEntity;
	}

	public void setEmployeeEntity(EmployeeEntity employeeEntity) {
		this.employeeEntity = employeeEntity;
	}
	
	@ManyToOne
	@JoinColumn(name="party_id")
	public PartyEntity getPartyEntity() {
		return partyEntity;
	}

	public void setPartyEntity(PartyEntity partyEntity) {
		this.partyEntity = partyEntity;
	}

	

	@Column(name = "city_id")
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Column(name = "transport_id")
	public Integer getTransportId() {
		return transportId;
	}

	public void setTransportId(Integer transportId) {
		this.transportId = transportId;
	}

	@Column(name = "form_req_flag")
	public Integer getFormReqFlag() {
		return formReqFlag;
	}

	public void setFormReqFlag(Integer formReqFlag) {
		this.formReqFlag = formReqFlag;
	}

	@Column(name = "form_type_id")
	public Integer getFormTypeId() {
		return formTypeId;
	}

	public void setFormTypeId(Integer formTypeId) {
		this.formTypeId = formTypeId;
	}

	@Column(name = "form_date")
	public Date getFormDate() {
		return formDate;
	}

	public void setFormDate(Date formDate) {
		this.formDate = formDate;
	}

	@Column(name = "sales_type")
	public String getSalesType() {
		return salesType;
	}

	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}

	@Column(name = "item_value")
	public Double getItemValue() {
		return itemValue;
	}

	public void setItemValue(Double itemValue) {
		this.itemValue = itemValue;
	}

	@Column(name = "mrp_value")
	public Double getMrpValue() {
		return mrpValue;
	}

	public void setMrpValue(Double mrpValue) {
		this.mrpValue = mrpValue;
	}

	@Column(name = "freight_type")
	public String getFreightType() {
		return freightType;
	}

	public void setFreightType(String freightType) {
		this.freightType = freightType;
	}

	@Column(name = "freight_amt")
	public Double getFreightAmt() {
		return freightAmt;
	}

	public void setFreightAmt(Double freightAmt) {
		this.freightAmt = freightAmt;
	}

	@Column(name = "discount_amount")
	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	@Column(name = "excise_duty_amount")
	public Double getExciseDutyAmount() {
		return exciseDutyAmount;
	}

	public void setExciseDutyAmount(Double exciseDutyAmount) {
		this.exciseDutyAmount = exciseDutyAmount;
	}

	@Column(name = "education_cess_amount")
	public Double getEducationCessAmount() {
		return educationCessAmount;
	}

	public void setEducationCessAmount(Double educationCessAmount) {
		this.educationCessAmount = educationCessAmount;
	}

	@Column(name = "high_education_cess_amount")
	public Double getHighEducationCessAmount() {
		return highEducationCessAmount;
	}

	public void setHighEducationCessAmount(Double highEducationCessAmount) {
		this.highEducationCessAmount = highEducationCessAmount;
	}

	@Column(name = "vat_amount")
	public Double getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(Double vatAmount) {
		this.vatAmount = vatAmount;
	}

	@Column(name = "cst_amount")
	public Double getCstAmount() {
		return cstAmount;
	}

	public void setCstAmount(Double cstAmount) {
		this.cstAmount = cstAmount;
	}

	@Column(name = "other_amount")
	public Double getOtherAmount() {
		return otherAmount;
	}

	public void setOtherAmount(Double otherAmount) {
		this.otherAmount = otherAmount;
	}

	@Column(name = "bill_remark")
	public String getBillRemark() {
		return billRemark;
	}

	public void setBillRemark(String billRemark) {
		this.billRemark = billRemark;
	}

	@Column(name = "form_no")
	public String getFormNo() {
		return formNo;
	}

	public void setFormNo(String formNo) {
		this.formNo = formNo;
	}

	@Column(name = "lr_no")
	public String getLrNo() {
		return lrNo;
	}

	public void setLrNo(String lrNo) {
		this.lrNo = lrNo;
	}

	@Column(name = "lr_date")
	public Date getLrDate() {
		return lrDate;
	}

	public void setLrDate(Date lrDate) {
		this.lrDate = lrDate;
	}

	@Column(name = "bill_net_amount")
	public Double getBillNetAmount() {
		return billNetAmount;
	}

	public void setBillNetAmount(Double billNetAmount) {
		this.billNetAmount = billNetAmount;
	}

	@Column(name = "advance_freight")
	public Double getAdvanceFreight() {
		return advanceFreight;
	}

	public void setAdvanceFreight(Double advanceFreight) {
		this.advanceFreight = advanceFreight;
	}

	@Column(name = "taxable_amount")
	public Double getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(Double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	@Column(name = "packet_total")
	public Double getPacketTotal() {
		return packetTotal;
	}

	public void setPacketTotal(Double packetTotal) {
		this.packetTotal = packetTotal;
	}

	@Column(name = "others_detail")
	public String getOthersDetail() {
		return othersDetail;
	}

	public void setOthersDetail(String othersDetail) {
		this.othersDetail = othersDetail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Column(name = "consignee_id")
	public Integer getConsigneeId() {
		return consigneeId;
	}

	public void setConsigneeId(Integer consigneeId) {
		this.consigneeId = consigneeId;
	}

	@Column(name = "delivery_note_no")
	public String getDeliveryNoteNo() {
		return deliveryNoteNo;
	}

	public void setDeliveryNoteNo(String deliveryNoteNo) {
		this.deliveryNoteNo = deliveryNoteNo;
	}

	@Column(name = "delivery_note_date")
	public Date getDeliveryNoteDate() {
		return deliveryNoteDate;
	}

	public void setDeliveryNoteDate(Date deliveryNoteDate) {
		this.deliveryNoteDate = deliveryNoteDate;
	}

	@Column(name = "despatch_document_no")
	public String getDespatchDocumentNo() {
		return despatchDocumentNo;
	}

	public void setDespatchDocumentNo(String despatchDocumentNo) {
		this.despatchDocumentNo = despatchDocumentNo;
	}

	@Column(name = "date_of_removal")
	public Date getDateOfRemoval() {
		return dateOfRemoval;
	}

	public void setDateOfRemoval(Date dateOfRemoval) {
		this.dateOfRemoval = dateOfRemoval;
	}

	@Column(name = "time_of_removal")
	public Time getTimeOfRemoval() {
		return timeOfRemoval;
	}

	public void setTimeOfRemoval(Time timeOfRemoval) {
		this.timeOfRemoval = timeOfRemoval;
	}

	@Column(name = "despatch_through")
	public String getDespatchThrough() {
		return despatchThrough;
	}

	public void setDespatchThrough(String despatchThrough) {
		this.despatchThrough = despatchThrough;
	}

	@Column(name = "motor_vehicle_no")
	public String getMotorVehicleNo() {
		return motorVehicleNo;
	}

	public void setMotorVehicleNo(String motorVehicleNo) {
		this.motorVehicleNo = motorVehicleNo;
	}

	@Column(name = "terms_of_payment")
	public String getTermsOfPayment() {
		return termsOfPayment;
	}

	public void setTermsOfPayment(String termsOfPayment) {
		this.termsOfPayment = termsOfPayment;
	}

	@Column(name = "balance_freight_amount")
	public Double getBalanceFreightAmount() {
		return balanceFreightAmount;
	}

	public void setBalanceFreightAmount(Double balanceFreightAmount) {
		this.balanceFreightAmount = balanceFreightAmount;
	}
	@Column(name = "packing_forwarding")
	public Double getPackingForwarding() {
		return packingForwarding;
	}

	public void setPackingForwarding(Double packingForwarding) {
		this.packingForwarding = packingForwarding;
	}
	@Column(name = "sales_order_date")
	public Date getSalesOrderDate() {
		return salesOrderDate;
	}

	public void setSalesOrderDate(Date salesOrderDate) {
		this.salesOrderDate = salesOrderDate;
	}

	@Column(name = "excise_duty_amount_inwords")
	public String getExciseDutyAmountInwords() {
		return exciseDutyAmountInwords;
	}

	public void setExciseDutyAmountInwords(String exciseDutyAmountInwords) {
		this.exciseDutyAmountInwords = exciseDutyAmountInwords;
	}

	@Column(name = "cess_amount_inwords")
	public String getCessAmountInwords() {
		return cessAmountInwords;
	}

	public void setCessAmountInwords(String cessAmountInwords) {
		this.cessAmountInwords = cessAmountInwords;
	}

	@Column(name = "bill_net_amount_inwords")
	public String getBillNetAmountInwords() {
		return billNetAmountInwords;
	}

	public void setBillNetAmountInwords(String billNetAmountInwords) {
		this.billNetAmountInwords = billNetAmountInwords;
	}
	
	@Column(name="vat_cst_tax_type", length=15)
	public String getVatCstTaxType() {
		return vatCstTaxType;
	}

	public void setVatCstTaxType(String vatCstTaxType) {
	this.vatCstTaxType = vatCstTaxType;
	}

	
	@Column(name = "road_permit_no")
	public String getRoadPermitNo() {
		return roadPermitNo;
	}

	public void setRoadPermitNo(String roadPermitNo) {
		this.roadPermitNo = roadPermitNo;
	}

	@Column(name = "road_permit_date")
	public Date getRoadPermitDate() {
		return roadPermitDate;
	}

	public void setRoadPermitDate(Date roadPermitDate) {
		this.roadPermitDate = roadPermitDate;
	}
	
	@Column(name = "buyer_po_no")
	public String getBuyerPoNo() {
		return buyerPoNo;
	}

	public void setBuyerPoNo(String buyerPoNo) {
		this.buyerPoNo = buyerPoNo;
	}

	@Column(name = "buyer_po_date")
	public Date getBuyerPoDate() {
		return buyerPoDate;
	}

	public void setBuyerPoDate(Date buyerPoDate) {
		this.buyerPoDate = buyerPoDate;
	}
	
	@Column(name = "bill_net_amount_round_of")
	public Double getBillNetAmountRoundOf() {
		return billNetAmountRoundOf;
	}

	public void setBillNetAmountRoundOf(Double billNetAmountRoundOf) {
		this.billNetAmountRoundOf = billNetAmountRoundOf;
	}

	@Column(name = "excise_invoice_no")
	public String getExciseInvoiceNo() {
		return exciseInvoiceNo;
	}

	public void setExciseInvoiceNo(String exciseInvoiceNo) {
		this.exciseInvoiceNo = exciseInvoiceNo;
	}
	
	
	@Column(name = "assessable_value")
	public Double getAssessableValue() {
		return assessableValue;
	}

	public void setAssessableValue(Double assessableValue) {
		this.assessableValue = assessableValue;
	}

	@Column(name = "high_education_cess_amount_inwords")
	public String getHighEducationCessAmountInwords() {
		return highEducationCessAmountInwords;
	}

	public void setHighEducationCessAmountInwords(
			String highEducationCessAmountInwords) {
		this.highEducationCessAmountInwords = highEducationCessAmountInwords;
	}

	public ProformaMasterEntity(){
		super();
	}
			
			
	public ProformaMasterEntity(Integer invoiceAutoId,String invoiceNumber,Object invoiceDate,PartyEntity partyEntity,String salesOrderNumber,String exciseInvoiceNo,EmployeeEntity employeeEntity,Double packetTotal,Double billNetAmount){
		super();
		this.invoiceAutoId=invoiceAutoId;
		this.invoiceNumber=invoiceNumber;
		this.invoiceDate=(Date)invoiceDate;
		this.partyEntity=partyEntity;
		this.salesOrderNumber=salesOrderNumber;
		this.employeeEntity=employeeEntity;
		this.exciseInvoiceNo=exciseInvoiceNo;
		this.packetTotal=packetTotal;
		this.billNetAmount=billNetAmount;
	}   

}
