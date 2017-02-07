package com.advanz.erp.masters.model;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class DispatchMasterDTO extends BaseDTO {
	private Integer dispatchAutoId;
	private String transactionSeries;
	private String finyr;
	private String dispatchNumber;
	private Integer dispatchId;
	private Date dispatchDate;
	private Integer branchId;
	private Integer partyId;
	private String vehicleNo;
	private String driverName;
	private String driverMobile;
	private Double totalPacket;
	private Double totalWeight;
	private String deliveryStatus;
	private String dispatchRemark;
	private PartyDTO partyDTO;
	private String partyName;

	//use by me
	private Integer totalItem;
	private String itemName;
	  private Date fromDate;
	   private Date toDate;
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public PartyDTO getPartyDTO() {

		return partyDTO;
	   }

	public void setPartyDTO(PartyDTO partyDTO) {
		
		this.partyDTO = partyDTO;
	}

	public Integer getDispatchAutoId() {
		return dispatchAutoId;
	}

	public void setDispatchAutoId(Integer dispatchAutoId) {
		this.dispatchAutoId = dispatchAutoId;
	}

	public String getTransactionSeries() {
		return transactionSeries;
	}

	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	public String getFinyr() {
		return finyr;
	}

	public void setFinyr(String finyr) {
		this.finyr = finyr;
	}

	public String getDispatchNumber() {
		return dispatchNumber;
	}

	public void setDispatchNumber(String dispatchNumber) {
		this.dispatchNumber = dispatchNumber;
	}

	public Integer getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(Integer dispatchId) {
		this.dispatchId = dispatchId;
	}

	public Date getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Integer getPartyId() {
		return partyId;
	}

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverMobile() {
		return driverMobile;
	}

	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}

	public Double getTotalPacket() {
		return totalPacket;
	}

	public void setTotalPacket(Double totalPacket) {
		this.totalPacket = totalPacket;
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getDispatchRemark() {
		return dispatchRemark;
	}

	public void setDispatchRemark(String dispatchRemark) {
		this.dispatchRemark = dispatchRemark;
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

	@Override
	public String toString() {
		return "DispatchMasterDTO [dispatchDate=" + dispatchDate
				+ ", partyDTO=" + partyDTO + ", partyName=" + partyName
				+ ", partyId=" + partyId + ",fromDate=" + fromDate +",toDate=" + toDate +"]";
	}

}