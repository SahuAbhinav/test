package com.advanz.erp.masters.entity.jpa;

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
@Table(name = "t_dispatch_master")
public class DispatchMasterEntity extends BaseEntity{

	
	@Id
	@GeneratedValue(generator = "system-incr")
	@GenericGenerator(name = "system-incr", strategy = "increment")
	@Column(name = "dispatch_auto_id")
	 private Integer dispatchAutoId;
	
	@Column(name = "transaction_series")
	private String transactionSeries;

	
	@Column(name = "finyr")
	private String finyr;
	
	@Column(name = "dispatch_number")
	private String dispatchNumber;
	
	@Column(name = "dispatch_id")
	private Integer dispatchId;
	
	@Column(name = "dispatch_date")
	private Date dispatchDate;
	
	
	@Column(name = "branch_id")
	private Integer branchId;
	
	@ManyToOne
	@JoinColumn(name="party_id")
	private PartyEntity partyEntity;
	
	/*@Column(name = "party_id")
	private Integer partyId;*/
	
	
	
	@Column(name = "vehicle_no")
	private String vehicleNo;
	
	public PartyEntity getPartyEntity() {
		return partyEntity;
	}

	public void setPartyEntity(PartyEntity partyEntity) {
		this.partyEntity = partyEntity;
	}

	@Column(name = "driver_name")
	private String driverName;
	
	@Column(name = "driver_mobile")
	private String driverMobile;
	
	@Column(name = "total_packet")
	private Double totalPacket;
	
	@Column(name = "total_weight")
	private Double totalWeight;
	
	@Column(name = "delivery_status")
	private String deliveryStatus;
	
	@Column(name = "dispatch_remark")
	private String dispatchRemark;

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

	/*public Integer getPartyId() {
		return partyId;
	}

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}*/

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
	
	
	
}
