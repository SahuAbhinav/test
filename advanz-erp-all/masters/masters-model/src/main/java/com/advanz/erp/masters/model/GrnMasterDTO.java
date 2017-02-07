package com.advanz.erp.masters.model;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

public class GrnMasterDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String transactionSeries;

	private Integer grnAutoId;

	private String finYear;

	private String grnNumber;

	private Integer grnId;

	private Date grnDate;

	private BranchDTO branchDTO;

	private PartyDTO partyDTO;

	private PurchaseOrderMasterDTO purchaseOrderDTO;

	private ItemGroupFlagDTO itemGroupFlagDTO;

	private String supplierBillNo;

	private Date supplierBillDate;

	private Double supplierBillAmount;

	private TransporterDTO transportDTO;

	private Double supplierBillExciseAmt;

	private String goodsReceiveType;

	private String vehicleNumber;

	private Date lrDate;

	private String lrNo;

	private Integer formReqFlag;

	private Date formDate;

	private String formnumber;

	private Integer formTypeId;

	private String freightYype;

	private Double freightAmount;

	private Integer qaCheckRequired;

	private Integer grnApprovalFlag;

	private String grnRemark;

	private List<GrnDetailDTO> GrnDetailDTOList;

	private Integer count;

	private Double tolRecQty = 0.00;

	private Double tolTareWt = 0.00;

	private Double tolNetWt = 0.00;

	private Double highEducationCessAmount;
	private Double educationCessAmount;
	private String itemName;
	
	private Date fromDate;
	private Date toDate;
	private Date goodsReceiveDate;
	
	private Date grnMaxDate;
	//21-May-2013
	private Double itemValue;
	private Double discountAmount;
	private Double vatAmount;
	private Double cstAmount;
	private Double grnNetAmount;
	private Integer aproved=0;
	private Date aprovedDate;
	private Integer showAllPartyFlag;
	
	private Double receivedBillExciseAmount;
	private Double receivedEducationCessAmount;
	private Double receivedHighEducationCessAmount;
	private Double receivedItemVatAmount;
	private Double entryTax;
	private Double otherAmount;
	private String otherDetail;
	public Date getGoodsReceiveDate() {
		return goodsReceiveDate;
	}

	public void setGoodsReceiveDate(Date goodsReceiveDate) {
		this.goodsReceiveDate = goodsReceiveDate;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getTolRecQty() {
		if (GrnDetailDTOList != null) {
			for (GrnDetailDTO obj : GrnDetailDTOList) {
				if (obj.getReceivedQty() != null)
					tolRecQty = tolRecQty + obj.getReceivedQty();
			}
		}
		return tolRecQty;
	}

	public void setTolRecQty(Double tolRecQty) {
		this.tolRecQty = tolRecQty;
	}

	public Double getTolTareWt() {
		if (GrnDetailDTOList != null) {
			for (GrnDetailDTO obj : GrnDetailDTOList) {
				if (obj.getRecTareWeight() != null)
					tolTareWt = tolTareWt + obj.getRecTareWeight();
			}
		}
		return tolTareWt;
	}

	public void setTolTareWt(Double tolTareWt) {
		this.tolTareWt = tolTareWt;
	}

	public Double getTolNetWt() {
		if (GrnDetailDTOList != null) {
			for (GrnDetailDTO obj : GrnDetailDTOList) {
				if (obj.getRecNetWeight() != null)
					tolNetWt = tolNetWt + obj.getRecNetWeight();
			}
		}
		return tolNetWt;
	}

	public void setTolNetWt(Double tolNetWt) {
		this.tolNetWt = tolNetWt;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * @return the grnDetailDTOList
	 */
	public List<GrnDetailDTO> getGrnDetailDTOList() {
		return GrnDetailDTOList;
	}

	/**
	 * @param grnDetailDTOList
	 *            the grnDetailDTOList to set
	 */
	public void setGrnDetailDTOList(List<GrnDetailDTO> grnDetailDTOList) {
		GrnDetailDTOList = grnDetailDTOList;
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
	 * @return the grnAutoId
	 */
	public Integer getGrnAutoId() {
		return grnAutoId;
	}

	/**
	 * @param grnAutoId
	 *            the grnAutoId to set
	 */
	public void setGrnAutoId(Integer grnAutoId) {
		this.grnAutoId = grnAutoId;
	}

	/**
	 * @return the finYear
	 */
	public String getFinYear() {
		return finYear;
	}

	/**
	 * @param finYear
	 *            the finYear to set
	 */
	public void setFinYear(String finYear) {
		this.finYear = finYear;
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
	 * @return the grnId
	 */
	public Integer getGrnId() {
		return grnId;
	}

	/**
	 * @param grnId
	 *            the grnId to set
	 */
	public void setGrnId(Integer grnId) {
		this.grnId = grnId;
	}

	/**
	 * @return the grnDate
	 */
	public Date getGrnDate() {
		return grnDate;
	}

	/**
	 * @param grnDate
	 *            the grnDate to set
	 */
	public void setGrnDate(Date grnDate) {
		this.grnDate = grnDate;
	}

	/**
	 * @return the branchDTO
	 */
	public BranchDTO getBranchDTO() {
		return branchDTO;
	}

	/**
	 * @param branchDTO
	 *            the branchDTO to set
	 */
	public void setBranchDTO(BranchDTO branchDTO) {
		this.branchDTO = branchDTO;
	}

	/**
	 * @return the partyDTO
	 */
	public PartyDTO getPartyDTO() {
		return partyDTO;
	}

	/**
	 * @param partyDTO
	 *            the partyDTO to set
	 */
	public void setPartyDTO(PartyDTO partyDTO) {
		this.partyDTO = partyDTO;
	}

	/**
	 * @return the purchaseOrderDTO
	 */
	public PurchaseOrderMasterDTO getPurchaseOrderDTO() {
		return purchaseOrderDTO;
	}

	/**
	 * @param purchaseOrderDTO
	 *            the purchaseOrderDTO to set
	 */
	public void setPurchaseOrderDTO(PurchaseOrderMasterDTO purchaseOrderDTO) {
		this.purchaseOrderDTO = purchaseOrderDTO;
	}

	/**
	 * @return the itemGroupFlagDTO
	 */
	public ItemGroupFlagDTO getItemGroupFlagDTO() {
		return itemGroupFlagDTO;
	}

	/**
	 * @param itemGroupFlagDTO
	 *            the itemGroupFlagDTO to set
	 */
	public void setItemGroupFlagDTO(ItemGroupFlagDTO itemGroupFlagDTO) {
		this.itemGroupFlagDTO = itemGroupFlagDTO;
	}

	/**
	 * @return the supplierBillNo
	 */
	public String getSupplierBillNo() {
		return supplierBillNo;
	}

	/**
	 * @param supplierBillNo
	 *            the supplierBillNo to set
	 */
	public void setSupplierBillNo(String supplierBillNo) {
		this.supplierBillNo = supplierBillNo;
	}

	/**
	 * @return the supplierBillDate
	 */
	public Date getSupplierBillDate() {
		return supplierBillDate;
	}

	/**
	 * @param supplierBillDate
	 *            the supplierBillDate to set
	 */
	public void setSupplierBillDate(Date supplierBillDate) {
		this.supplierBillDate = supplierBillDate;
	}

	/**
	 * @return the supplierBillAmount
	 */
	public Double getSupplierBillAmount() {
		return supplierBillAmount;
	}

	/**
	 * @param supplierBillAmount
	 *            the supplierBillAmount to set
	 */
	public void setSupplierBillAmount(Double supplierBillAmount) {
		this.supplierBillAmount = supplierBillAmount;
	}

	/**
	 * @return the transportDTO
	 */
	public TransporterDTO getTransportDTO() {
		return transportDTO;
	}

	/**
	 * @param transportDTO
	 *            the transportDTO to set
	 */
	public void setTransportDTO(TransporterDTO transportDTO) {
		this.transportDTO = transportDTO;
	}

	/**
	 * @return the supplierBillExciseAmt
	 */
	public Double getSupplierBillExciseAmt() {
		return supplierBillExciseAmt;
	}

	/**
	 * @param supplierBillExciseAmt
	 *            the supplierBillExciseAmt to set
	 */
	public void setSupplierBillExciseAmt(Double supplierBillExciseAmt) {
		this.supplierBillExciseAmt = supplierBillExciseAmt;
	}

	/**
	 * @return the goodsReceiveType
	 */
	public String getGoodsReceiveType() {
		return goodsReceiveType;
	}

	/**
	 * @param goodsReceiveType
	 *            the goodsReceiveType to set
	 */
	public void setGoodsReceiveType(String goodsReceiveType) {
		this.goodsReceiveType = goodsReceiveType;
	}

	/**
	 * @return the vehicleNumber
	 */
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	/**
	 * @param vehicleNumber
	 *            the vehicleNumber to set
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	/**
	 * @return the lrDate
	 */
	public Date getLrDate() {
		return lrDate;
	}

	/**
	 * @param lrDate
	 *            the lrDate to set
	 */
	public void setLrDate(Date lrDate) {
		this.lrDate = lrDate;
	}

	/**
	 * @return the lrNo
	 */
	public String getLrNo() {
		return lrNo;
	}

	/**
	 * @param lrNo
	 *            the lrNo to set
	 */
	public void setLrNo(String lrNo) {
		this.lrNo = lrNo;
	}

	/**
	 * @return the formReqFlag
	 */
	public Integer getFormReqFlag() {
		return formReqFlag;
	}

	/**
	 * @param formReqFlag
	 *            the formReqFlag to set
	 */
	public void setFormReqFlag(Integer formReqFlag) {
		this.formReqFlag = formReqFlag;
	}

	/**
	 * @return the formDate
	 */
	public Date getFormDate() {
		return formDate;
	}

	/**
	 * @param formDate
	 *            the formDate to set
	 */
	public void setFormDate(Date formDate) {
		this.formDate = formDate;
	}

	/**
	 * @return the formnumber
	 */
	public String getFormnumber() {
		return formnumber;
	}

	/**
	 * @param formnumber
	 *            the formnumber to set
	 */
	public void setFormnumber(String formnumber) {
		this.formnumber = formnumber;
	}

	/**
	 * @return the formTypeId
	 */
	public Integer getFormTypeId() {
		return formTypeId;
	}

	/**
	 * @param formTypeId
	 *            the formTypeId to set
	 */
	public void setFormTypeId(Integer formTypeId) {
		this.formTypeId = formTypeId;
	}

	/**
	 * @return the freightYype
	 */
	public String getFreightYype() {
		return freightYype;
	}

	/**
	 * @param freightYype
	 *            the freightYype to set
	 */
	public void setFreightYype(String freightYype) {
		this.freightYype = freightYype;
	}

	/**
	 * @return the freightAmount
	 */
	public Double getFreightAmount() {
		return freightAmount;
	}

	/**
	 * @param freightAmount
	 *            the freightAmount to set
	 */
	public void setFreightAmount(Double freightAmount) {
		this.freightAmount = freightAmount;
	}

	/**
	 * @return the qaCheckRequired
	 */
	public Integer getQaCheckRequired() {
		return qaCheckRequired;
	}

	/**
	 * @param qaCheckRequired
	 *            the qaCheckRequired to set
	 */
	public void setQaCheckRequired(Integer qaCheckRequired) {
		this.qaCheckRequired = qaCheckRequired;
	}

	/**
	 * @return the grnApprovalFlag
	 */
	public Integer getGrnApprovalFlag() {
		return grnApprovalFlag;
	}

	/**
	 * @param grnApprovalFlag
	 *            the grnApprovalFlag to set
	 */
	public void setGrnApprovalFlag(Integer grnApprovalFlag) {
		this.grnApprovalFlag = grnApprovalFlag;
	}

	/**
	 * @return the grnRemark
	 */
	public String getGrnRemark() {
		return grnRemark;
	}

	/**
	 * @param grnRemark
	 *            the grnRemark to set
	 */
	public void setGrnRemark(String grnRemark) {
		this.grnRemark = grnRemark;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getHighEducationCessAmount() {
		return highEducationCessAmount;
	}

	public void setHighEducationCessAmount(Double highEducationCessAmount) {
		this.highEducationCessAmount = highEducationCessAmount;
	}

	public Double getEducationCessAmount() {
		return educationCessAmount;
	}

	public void setEducationCessAmount(Double educationCessAmount) {
		this.educationCessAmount = educationCessAmount;
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
	
public Date getGrnMaxDate() {
		return grnMaxDate;
	}

	public void setGrnMaxDate(Date grnMaxDate) {
		this.grnMaxDate = grnMaxDate;
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

	public Double getGrnNetAmount() {
		return grnNetAmount;
	}

	public void setGrnNetAmount(Double grnNetAmount) {
		this.grnNetAmount = grnNetAmount;
	}

public Integer getAproved() {
		return aproved;
	}

	public void setAproved(Integer aproved) {
		this.aproved = aproved;
	}

public Date getAprovedDate() {
		return aprovedDate;
	}

	public void setAprovedDate(Date aprovedDate) {
		this.aprovedDate = aprovedDate;
	}

public Integer getShowAllPartyFlag() {
		return showAllPartyFlag;
	}

	public void setShowAllPartyFlag(Integer showAllPartyFlag) {
		this.showAllPartyFlag = showAllPartyFlag;
	}

public Double getReceivedBillExciseAmount() {
		return receivedBillExciseAmount;
	}

	public void setReceivedBillExciseAmount(Double receivedBillExciseAmount) {
		this.receivedBillExciseAmount = receivedBillExciseAmount;
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

public Double getReceivedItemVatAmount() {
		return receivedItemVatAmount;
	}

	public void setReceivedItemVatAmount(Double receivedItemVatAmount) {
		this.receivedItemVatAmount = receivedItemVatAmount;
	}

public Double getEntryTax() {
		return entryTax;
	}

	public void setEntryTax(Double entryTax) {
		this.entryTax = entryTax;
	}

public Double getOtherAmount() {
		return otherAmount;
	}

	public void setOtherAmount(Double otherAmount) {
		this.otherAmount = otherAmount;
	}

	public String getOtherDetail() {
		return otherDetail;
	}

	public void setOtherDetail(String otherDetail) {
		this.otherDetail = otherDetail;
	}

@Override
public String toString() {
	// TODO Auto-generated method stub
	return "GrnMasterDTO [toDate=" + toDate
	+ ",goodsReceiveDate=" + goodsReceiveDate +"]";
}
}
