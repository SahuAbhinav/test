package com.advanz.erp.masters.model;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

public class BlanketProductionDetailRightDTO extends BaseDTO{
	private Integer sno;
	private Integer blanketProductionId;
	private Integer rollNoRight;
	private Double lengthRight;
	private Double widthRight;
	private Double thickRight;
	private Double weightRight;
	private Double densityRight;
	private String remarkRight;
	private Boolean deletedFlag=false; 
	private String blanketType;
	private Integer itemId;
	private String statusRight;
	private Date updateDateRight;
	
	//Blanket Masters column to show on migration form
	private String runNo;
	private Date shiftDate;
	private String shift;
	private String batchNo;
	//New fields for rejection migration
	private Integer rejRollNo;
	private Double rejLengthr;
	private Double rejWidthr;
	private Double rejThickr;
	private Double rejWeightr;
	private Double rejDensityr;
	private String rejRemarkr;
	private Integer rejItemId;
	private String rejStatusRight;
	private Date rejUpdateDateRight;
	
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
	public Integer getRollNoRight() {
		return rollNoRight;
	}
	public void setRollNoRight(Integer rollNoRight) {
		this.rollNoRight = rollNoRight;
	}
	public Double getLengthRight() {
		return lengthRight;
	}
	public void setLengthRight(Double lengthRight) {
		this.lengthRight = lengthRight;
	}
	public Double getWidthRight() {
		return widthRight;
	}
	public void setWidthRight(Double widthRight) {
		this.widthRight = widthRight;
	}
	public Double getThickRight() {
		return thickRight;
	}
	public void setThickRight(Double thickRight) {
		this.thickRight = thickRight;
	}
	public Double getWeightRight() {
		return weightRight;
	}
	public void setWeightRight(Double weightRight) {
		this.weightRight = weightRight;
	}
	public Double getDensityRight() {
		return densityRight;
	}
	public void setDensityRight(Double densityRight) {
		this.densityRight = densityRight;
	}
	public String getRemarkRight() {
		return remarkRight;
	}
	public void setRemarkRight(String remarkRight) {
		this.remarkRight = remarkRight;
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
	public String getStatusRight() {
		return statusRight;
	}
	public void setStatusRight(String statusRight) {
		this.statusRight = statusRight;
	}
	public Date getUpdateDateRight() {
		return updateDateRight;
	}
	public void setUpdateDateRight(Date updateDateRight) {
		this.updateDateRight = updateDateRight;
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
	public Double getRejLengthr() {
		return rejLengthr;
	}
	public void setRejLengthr(Double rejLengthr) {
		this.rejLengthr = rejLengthr;
	}
	public Double getRejWidthr() {
		return rejWidthr;
	}
	public void setRejWidthr(Double rejWidthr) {
		this.rejWidthr = rejWidthr;
	}
	public Double getRejThickr() {
		return rejThickr;
	}
	public void setRejThickr(Double rejThickr) {
		this.rejThickr = rejThickr;
	}
	public Double getRejDensityr() {
		return rejDensityr;
	}
	public void setRejDensityr(Double rejDensityr) {
		this.rejDensityr = rejDensityr;
	}
	public String getRejRemarkr() {
		return rejRemarkr;
	}
	public void setRejRemarkr(String rejRemarkr) {
		this.rejRemarkr = rejRemarkr;
	}
	public Integer getRejItemId() {
		return rejItemId;
	}
	public void setRejItemId(Integer rejItemId) {
		this.rejItemId = rejItemId;
	}
	public String getRejStatusRight() {
		return rejStatusRight;
	}
	public void setRejStatusRight(String rejStatusRight) {
		this.rejStatusRight = rejStatusRight;
	}
	public Double getRejWeightr() {
		return rejWeightr;
	}
	public void setRejWeightr(Double rejWeightr) {
		this.rejWeightr = rejWeightr;
	}
	public Date getRejUpdateDateRight() {
		return rejUpdateDateRight;
	}
	public void setRejUpdateDateRight(Date rejUpdateDateRight) {
		this.rejUpdateDateRight = rejUpdateDateRight;
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
