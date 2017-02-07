package com.advanz.erp.masters.model;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

public class GrnDetailDTO extends BaseDTO {

	/**
	 * 
	 */

	private Integer sno;

	private Integer grnAutoId;

	private String transactionSeries;

	private ItemDTO itemDTO;

	private Integer measurementUnitId;

	private String measurementUnitName;

	public String getMeasurementUnitName() {
		return measurementUnitName;
	}

	public void setMeasurementUnitName(String measurementUnitName) {
		this.measurementUnitName = measurementUnitName;
	}

	private Double billQty;

	private Double billTareWeight;

	private Double billNetWeight;

	private Double billPacketTot;

	private Double poQty;

	private Double receivedQty;

	private Double recTareWeight;

	private Double recNetWeight;

	private Double recPacketTot;

	private Double tarePerPacket;

	private String containerDescription;

	private String grnNumber;
	
	private Double shrotQty;
	private Double approvedQty;
	private Double rejectedQty;
	
	private Double itemValue;
	//21-May-2013
	
	private Double purchaseRate;
	private Double discountPer;
	private Double discountAmount;
	private Double vatPerc;
	private Double vatAmount;
	private Double cstPerc;
	private Double cstAmount;
	private Double netAmount;
	
	private Double vatCstPerc;
	private Double vatCstAmount;
	private Double tempReciveQty;
	
	private Double recioeveExiciseAmt;
	private Double recioeveCessAmt;
	private Double recioeveHCessAmt;
	// New Column For Multiple GRN
	private String poNumber;
	

	private Double receivedBillExciseAmt;
	private Double receivedEducationCessAmount;
	private Double receivedHighEducationCessAmount;
	
	private Double receivedItemVatAmount;
	
	
	public Double getItemBasicAmount() {
		return itemBasicAmount;
	}

	public void setItemBasicAmount(Double itemBasicAmount) {
		this.itemBasicAmount = itemBasicAmount;
	}
	private Double itemBasicAmount;
	private Double itemBillExciseAmt;
	private Date aprovedDate;
	private Integer grnApprovalFlag;
	private Double itemHighEducationCessAmount;
	private Double itemEducationCessAmount;
	private Double itemVatAmount;
	
	private Double grossAmount;
	private Integer departmentId;
	public Double getTempReciveQty() {
		return tempReciveQty;
	}

	public void setTempReciveQty(Double tempReciveQty) {
		this.tempReciveQty = tempReciveQty;
	}

	public Double getVatCstAmount() {
		return vatCstAmount;
	}

	public void setVatCstAmount(Double vatCstAmount) {
		this.vatCstAmount = vatCstAmount;
	}

	public Double getVatCstPerc() {
		return vatCstPerc;
	}

	public void setVatCstPerc(Double vatCstPerc) {
		this.vatCstPerc = vatCstPerc;
	}

	public Double getItemValue() {
		return itemValue;
	}

	public void setItemValue(Double itemValue) {
		this.itemValue = itemValue;
	}

	/**
	 * @return the grnNumber
	 */
	public String getGrnNumber() {
		return grnNumber;
	}

	/**
	 * @param grnNumber
	 *            the grnNumber to set
	 */
	public void setGrnNumber(String grnNumber) {
		this.grnNumber = grnNumber;
	}

	/**
	 * @return the sno
	 */
	public Integer getSno() {
		return sno;
	}

	/**
	 * @param sno
	 *            the sno to set
	 */
	public void setSno(Integer sno) {
		this.sno = sno;
	}

	
	

	public Integer getGrnAutoId() {
		return grnAutoId;
	}

	public void setGrnAutoId(Integer grnAutoId) {
		this.grnAutoId = grnAutoId;
	}

	/**
	 * @return the transactionSeries
	 */
	public String getTransactionSeries() {
		return transactionSeries;
	}

	/**
	 * @param transactionSeries
	 *            the transactionSeries to set
	 */
	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	/**
	 * @return the itemDTO
	 */
	public ItemDTO getItemDTO() {
		return itemDTO;
	}

	/**
	 * @param itemDTO
	 *            the itemDTO to set
	 */
	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}

	/**
	 * @return the measurementUnitId
	 */
	public Integer getMeasurementUnitId() {
		return measurementUnitId;
	}

	/**
	 * @param measurementUnitId
	 *            the measurementUnitId to set
	 */
	public void setMeasurementUnitId(Integer measurementUnitId) {
		this.measurementUnitId = measurementUnitId;
	}

	/**
	 * @return the billQty
	 */
	public Double getBillQty() {
		return billQty;
	}

	/**
	 * @param billQty
	 *            the billQty to set
	 */
	public void setBillQty(Double billQty) {
		this.billQty = billQty;
	}

	/**
	 * @return the billTareWeight
	 */
	public Double getBillTareWeight() {
		return billTareWeight;
	}

	/**
	 * @param billTareWeight
	 *            the billTareWeight to set
	 */
	public void setBillTareWeight(Double billTareWeight) {
		this.billTareWeight = billTareWeight;
	}

	/**
	 * @return the billNetWeight
	 */
	public Double getBillNetWeight() {
		return billNetWeight;
	}

	/**
	 * @param billNetWeight
	 *            the billNetWeight to set
	 */
	public void setBillNetWeight(Double billNetWeight) {
		this.billNetWeight = billNetWeight;
	}

	/**
	 * @return the billPacketTot
	 */
	public Double getBillPacketTot() {
		return billPacketTot;
	}

	/**
	 * @param billPacketTot
	 *            the billPacketTot to set
	 */
	public void setBillPacketTot(Double billPacketTot) {
		this.billPacketTot = billPacketTot;
	}

	/**
	 * @return the poQty
	 */
	public Double getPoQty() {
		return poQty;
	}

	/**
	 * @param poQty
	 *            the poQty to set
	 */
	public void setPoQty(Double poQty) {
		this.poQty = poQty;
	}

	/**
	 * @return the receivedQty
	 */
	public Double getReceivedQty() {
		return receivedQty;
	}

	/**
	 * @param receivedQty
	 *            the receivedQty to set
	 */
	public void setReceivedQty(Double receivedQty) {
		this.receivedQty = receivedQty;
	}

	/**
	 * @return the recTareWeight
	 */
	public Double getRecTareWeight() {
		return recTareWeight;
	}

	/**
	 * @param recTareWeight
	 *            the recTareWeight to set
	 */
	public void setRecTareWeight(Double recTareWeight) {
		this.recTareWeight = recTareWeight;
	}

	/**
	 * @return the recNetWeight
	 */
	public Double getRecNetWeight() {
		return recNetWeight;
	}

	/**
	 * @param recNetWeight
	 *            the recNetWeight to set
	 */
	public void setRecNetWeight(Double recNetWeight) {
		this.recNetWeight = recNetWeight;
	}

	/**
	 * @return the recPacketTot
	 */
	public Double getRecPacketTot() {
		return recPacketTot;
	}

	/**
	 * @param recPacketTot
	 *            the recPacketTot to set
	 */
	public void setRecPacketTot(Double recPacketTot) {
		this.recPacketTot = recPacketTot;
	}

	/**
	 * @return the tarePerPacket
	 */
	public Double getTarePerPacket() {
		return tarePerPacket;
	}

	/**
	 * @param tarePerPacket
	 *            the tarePerPacket to set
	 */
	public void setTarePerPacket(Double tarePerPacket) {
		this.tarePerPacket = tarePerPacket;
	}

	/**
	 * @return the containerDescription
	 */
	public String getContainerDescription() {
		return containerDescription;
	}

	/**
	 * @param containerDescription
	 *            the containerDescription to set
	 */
	public void setContainerDescription(String containerDescription) {
		this.containerDescription = containerDescription;
	}

	public Double getShrotQty() {
		return shrotQty;
	}

	public void setShrotQty(Double shrotQty) {
		this.shrotQty = shrotQty;
	}

	public Double getApprovedQty() {
		return approvedQty;
	}

	public void setApprovedQty(Double approvedQty) {
		this.approvedQty = approvedQty;
	}

	public Double getRejectedQty() {
		return rejectedQty;
	}

	public void setRejectedQty(Double rejectedQty) {
		this.rejectedQty = rejectedQty;
	}

	public Double getPurchaseRate() {
		return purchaseRate;
	}

	public void setPurchaseRate(Double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}

	public Double getDiscountPer() {
		return discountPer;
	}

	public void setDiscountPer(Double discountPer) {
		this.discountPer = discountPer;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getVatPerc() {
		return vatPerc;
	}

	public void setVatPerc(Double vatPerc) {
		this.vatPerc = vatPerc;
	}

	public Double getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(Double vatAmount) {
		this.vatAmount = vatAmount;
	}

	public Double getCstPerc() {
		return cstPerc;
	}

	public void setCstPerc(Double cstPerc) {
		this.cstPerc = cstPerc;
	}

	public Double getCstAmount() {
		return cstAmount;
	}

	public void setCstAmount(Double cstAmount) {
		this.cstAmount = cstAmount;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public Double getRecioeveExiciseAmt() {
		return recioeveExiciseAmt;
	}

	public void setRecioeveExiciseAmt(Double recioeveExiciseAmt) {
		this.recioeveExiciseAmt = recioeveExiciseAmt;
	}

	public Double getRecioeveCessAmt() {
		return recioeveCessAmt;
	}

	public void setRecioeveCessAmt(Double recioeveCessAmt) {
		this.recioeveCessAmt = recioeveCessAmt;
	}

	public Double getRecioeveHCessAmt() {
		return recioeveHCessAmt;
	}

	public void setRecioeveHCessAmt(Double recioeveHCessAmt) {
		this.recioeveHCessAmt = recioeveHCessAmt;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Double getReceivedBillExciseAmt() {
		return receivedBillExciseAmt;
	}

	public void setReceivedBillExciseAmt(Double receivedBillExciseAmt) {
		this.receivedBillExciseAmt = receivedBillExciseAmt;
	}

	public Double getReceivedEducationCessAmount() {
		return receivedEducationCessAmount;
	}

	public void setReceivedEducationCessAmount(Double receivedEducationCessAmount) {
		this.receivedEducationCessAmount = receivedEducationCessAmount;
	}

	public Double getReceivedHighEducationCessAmount() {
		return receivedHighEducationCessAmount;
	}

	public void setReceivedHighEducationCessAmount(
			Double receivedHighEducationCessAmount) {
		this.receivedHighEducationCessAmount = receivedHighEducationCessAmount;
	}

	public Double getItemBillExciseAmt() {
		return itemBillExciseAmt;
	}

	public void setItemBillExciseAmt(Double itemBillExciseAmt) {
		this.itemBillExciseAmt = itemBillExciseAmt;
	}

	public Date getAprovedDate() {
		return aprovedDate;
	}

	public void setAprovedDate(Date aprovedDate) {
		this.aprovedDate = aprovedDate;
	}

	public Integer getGrnApprovalFlag() {
		return grnApprovalFlag;
	}

	public void setGrnApprovalFlag(Integer grnApprovalFlag) {
		this.grnApprovalFlag = grnApprovalFlag;
	}

	public Double getItemHighEducationCessAmount() {
		return itemHighEducationCessAmount;
	}

	public void setItemHighEducationCessAmount(Double itemHighEducationCessAmount) {
		this.itemHighEducationCessAmount = itemHighEducationCessAmount;
	}

	public Double getItemEducationCessAmount() {
		return itemEducationCessAmount;
	}

	public void setItemEducationCessAmount(Double itemEducationCessAmount) {
		this.itemEducationCessAmount = itemEducationCessAmount;
	}

	public Double getReceivedItemVatAmount() {
		return receivedItemVatAmount;
	}

	public void setReceivedItemVatAmount(Double receivedItemVatAmount) {
		this.receivedItemVatAmount = receivedItemVatAmount;
	}

	public Double getItemVatAmount() {
		return itemVatAmount;
	}

	public void setItemVatAmount(Double itemVatAmount) {
		this.itemVatAmount = itemVatAmount;
	}

	public Double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(Double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

}
