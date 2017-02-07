package com.advanz.erp.masters.model;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

public class BlanketProductionDetailLeftDTO extends BaseDTO{
	private Integer sno;
	private Integer blanketProductionId;
	private Integer rollNoLeft;
	private Double lengthLeft;
	private Double widthLeft;
	private Double thickLeft;
	private Double weightLeft;
	private Double densityLeft;
	private String remarkLeft;
	private Boolean deletedFlag=false; 
	private String blanketType;
	private Integer itemId;

	private String statusLeft;
	private Date updateDateLeft;
	
	//Blanket Masters column to show on migration form
	private String runNo;
	private Date shiftDate;
	private String shift;
	private String batchNo;
	//New Fields rejection migration
	private Integer rejRollNo;
	private Double rejLengthl;
	private Double rejWidthl;
	private Double rejThickl;
	private Double rejWeightl;
	private Double rejDensityl;
	private String rejRemarkl;
	private Integer rejItemId;
	private String rejStatus;
	private Date rejUpdateDateLeft;
	
	private Date approvedDate;
	private Integer approvedStatus=0;
	
	private Integer rejectedUserId;
	private Integer raUserId;
	private Integer approvedUserId;
	private String itemName;
	
	private Integer checkRejection=0;
	
	private String finishedGood;
	public Integer getBlanketProductionId() {
		return blanketProductionId;
	}
	public void setBlanketProductionId(Integer blanketProductionId) {
		this.blanketProductionId = blanketProductionId;
	}
	public Integer getRollNoLeft() {
		return rollNoLeft;
	}
	public void setRollNoLeft(Integer rollNoLeft) {
		this.rollNoLeft = rollNoLeft;
	}
	public Double getLengthLeft() {
		return lengthLeft;
	}
	public void setLengthLeft(Double lengthLeft) {
		this.lengthLeft = lengthLeft;
	}
	public Double getWidthLeft() {
		return widthLeft;
	}
	public void setWidthLeft(Double widthLeft) {
		this.widthLeft = widthLeft;
	}
	public Double getThickLeft() {
		return thickLeft;
	}
	public void setThickLeft(Double thickLeft) {
		this.thickLeft = thickLeft;
	}
	public Double getWeightLeft() {
		return weightLeft;
	}
	public void setWeightLeft(Double weightLeft) {
		this.weightLeft = weightLeft;
	}
	public Double getDensityLeft() {
		return densityLeft;
	}
	public void setDensityLeft(Double densityLeft) {
		this.densityLeft = densityLeft;
	}
	public String getRemarkLeft() {
		return remarkLeft;
	}
	public void setRemarkLeft(String remarkLeft) {
		this.remarkLeft = remarkLeft;
	}
	public Boolean getDeletedFlag() {
		return deletedFlag;
	}
	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public String getBlanketType() {
		return blanketType;
	}
	public void setBlanketType(String blanketType) {
		this.blanketType = blanketType;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getStatusLeft() {
		return statusLeft;
	}
	public void setStatusLeft(String statusLeft) {
		this.statusLeft = statusLeft;
	}
	public Date getUpdateDateLeft() {
		return updateDateLeft;
	}
	public void setUpdateDateLeft(Date updateDateLeft) {
		this.updateDateLeft = updateDateLeft;
	}
	
	public String getRunNo() {
		return runNo;
	}
	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}
	public Date getShiftDate() {
		return shiftDate;
	}
	public void setShiftDate(Date shiftDate) {
		this.shiftDate = shiftDate;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public Integer getRejRollNo() {
		return rejRollNo;
	}
	public void setRejRollNo(Integer rejRollNo) {
		this.rejRollNo = rejRollNo;
	}
	public Double getRejLengthl() {
		return rejLengthl;
	}
	public void setRejLengthl(Double rejLengthl) {
		this.rejLengthl = rejLengthl;
	}
	public Double getRejWidthl() {
		return rejWidthl;
	}
	public void setRejWidthl(Double rejWidthl) {
		this.rejWidthl = rejWidthl;
	}
	public Double getRejThickl() {
		return rejThickl;
	}
	public void setRejThickl(Double rejThickl) {
		this.rejThickl = rejThickl;
	}
	public Double getRejWeightl() {
		return rejWeightl;
	}
	public void setRejWeightl(Double rejWeightl) {
		this.rejWeightl = rejWeightl;
	}
	public Double getRejDensityl() {
		return rejDensityl;
	}
	public void setRejDensityl(Double rejDensityl) {
		this.rejDensityl = rejDensityl;
	}
	public String getRejRemarkl() {
		return rejRemarkl;
	}
	public void setRejRemarkl(String rejRemarkl) {
		this.rejRemarkl = rejRemarkl;
	}
	public Integer getRejItemId() {
		return rejItemId;
	}
	public void setRejItemId(Integer rejItemId) {
		this.rejItemId = rejItemId;
	}
	public String getRejStatus() {
		return rejStatus;
	}
	public void setRejStatus(String rejStatus) {
		this.rejStatus = rejStatus;
	}
	public Date getRejUpdateDateLeft() {
		return rejUpdateDateLeft;
	}
	public void setRejUpdateDateLeft(Date rejUpdateDateLeft) {
		this.rejUpdateDateLeft = rejUpdateDateLeft;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	public Integer getApprovedStatus() {
		return approvedStatus;
	}
	public void setApprovedStatus(Integer approvedStatus) {
		this.approvedStatus = approvedStatus;
	}
	public Integer getRejectedUserId() {
		return rejectedUserId;
	}
	public void setRejectedUserId(Integer rejectedUserId) {
		this.rejectedUserId = rejectedUserId;
	}
	public Integer getRaUserId() {
		return raUserId;
	}
	public void setRaUserId(Integer raUserId) {
		this.raUserId = raUserId;
	}
	public Integer getApprovedUserId() {
		return approvedUserId;
	}
	public void setApprovedUserId(Integer approvedUserId) {
		this.approvedUserId = approvedUserId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getCheckRejection() {
		return checkRejection;
	}
	public void setCheckRejection(Integer checkRejection) {
		this.checkRejection = checkRejection;
	}
	public String getFinishedGood() {
		return finishedGood;
	}
	public void setFinishedGood(String finishedGood) {
		this.finishedGood = finishedGood;
	}
	
}
