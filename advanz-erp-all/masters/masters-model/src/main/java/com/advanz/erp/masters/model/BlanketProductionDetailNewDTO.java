package com.advanz.erp.masters.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

public class BlanketProductionDetailNewDTO extends BaseDTO {

	public static final Character SPLITER_TYPE_A = 'A';
	public static final Character SPLITER_TYPE_B = 'B';
	public static final Character SPLITER_TYPE_C = 'C';
	public static final Character SPLITER_TYPE_D = 'D';
	public static final Character SPLITER_TYPE_E = 'E';
	public static final Character SPLITER_TYPE_F = 'F';

	public static final List<Character> SPLITER_LIST = new ArrayList<Character>(
			Arrays.asList(SPLITER_TYPE_A, SPLITER_TYPE_B, SPLITER_TYPE_C,
					SPLITER_TYPE_D, SPLITER_TYPE_E, SPLITER_TYPE_F));

	private Integer sno;
	private Integer blanketProductionId;
	private Integer rollNo;
	private Double length;
	private Double width;
	private Double thick;
	private Double weight;
	private Double density;
	private String remark;
	private Boolean deletedFlag = false;
	private String blanketType;
	private Integer itemId;
	private String status;
	private Date updateDate;
	// Blanket Masters column to show on migration form
	private String runNo;
	private Date shiftDate;
	private String shift;
	private String batchNo;
	// New Fields rejection migration
	private Integer rejRollNo;
	private Double rejLength;
	private Double rejWidth;
	private Double rejThick;
	private Double rejWeight;
	private Double rejDensity;
	private String rejRemark;
	private Integer rejItemId;
	private String rejStatus;
	private Date rejUpdateDate;
	private Date approvedDate;
	private Integer approvedStatus = 0;
	private Integer rejectedUserId;
	private Integer raUserId;
	private Integer approvedUserId;
	private String itemName;
	private Integer checkRejection = 0;
	private String finishedGood;
	private Character spliterType;

	public Character getSpliterType() {
		return spliterType;
	}

	public void setSpliterType(Character spliterType) {
		this.spliterType = spliterType;
	}

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public Integer getBlanketProductionId() {
		return blanketProductionId;
	}

	public void setBlanketProductionId(Integer blanketProductionId) {
		this.blanketProductionId = blanketProductionId;
	}

	public Integer getRollNo() {
		return rollNo;
	}

	public void setRollNo(Integer rollNo) {
		this.rollNo = rollNo;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getThick() {
		return thick;
	}

	public void setThick(Double thick) {
		this.thick = thick;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getDensity() {
		return density;
	}

	public void setDensity(Double density) {
		this.density = density;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

	public Double getRejLength() {
		return rejLength;
	}

	public void setRejLength(Double rejLength) {
		this.rejLength = rejLength;
	}

	public Double getRejWidth() {
		return rejWidth;
	}

	public void setRejWidth(Double rejWidth) {
		this.rejWidth = rejWidth;
	}

	public Double getRejThick() {
		return rejThick;
	}

	public void setRejThick(Double rejThick) {
		this.rejThick = rejThick;
	}

	public Double getRejWeight() {
		return rejWeight;
	}

	public void setRejWeight(Double rejWeight) {
		this.rejWeight = rejWeight;
	}

	public Double getRejDensity() {
		return rejDensity;
	}

	public void setRejDensity(Double rejDensity) {
		this.rejDensity = rejDensity;
	}

	public String getRejRemark() {
		return rejRemark;
	}

	public void setRejRemark(String rejRemark) {
		this.rejRemark = rejRemark;
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

	public Date getRejUpdateDate() {
		return rejUpdateDate;
	}

	public void setRejUpdateDate(Date rejUpdateDate) {
		this.rejUpdateDate = rejUpdateDate;
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

	@Override
	public String toString() {
		return "BlanketProductionDetailNewDTO [sno=" + sno
				+ ", blanketProductionId=" + blanketProductionId + ", rollNo="
				+ rollNo + ", length=" + length + ", width=" + width
				+ ", thick=" + thick + ", weight=" + weight + ", density="
				+ density + ", remark=" + remark + ", deletedFlag="
				+ deletedFlag + ", blanketType=" + blanketType + ", itemId="
				+ itemId + ", status=" + status + ", updateDate=" + updateDate
				+ ", runNo=" + runNo + ", shiftDate=" + shiftDate + ", shift="
				+ shift + ", batchNo=" + batchNo + ", rejRollNo=" + rejRollNo
				+ ", rejLength=" + rejLength + ", rejWidth=" + rejWidth
				+ ", rejThick=" + rejThick + ", rejWeight=" + rejWeight
				+ ", rejDensity=" + rejDensity + ", rejRemark=" + rejRemark
				+ ", rejItemId=" + rejItemId + ", rejStatus=" + rejStatus
				+ ", rejUpdateDate=" + rejUpdateDate + ", approvedDate="
				+ approvedDate + ", approvedStatus=" + approvedStatus
				+ ", rejectedUserId=" + rejectedUserId + ", raUserId="
				+ raUserId + ", approvedUserId=" + approvedUserId
				+ ", itemName=" + itemName + ", checkRejection="
				+ checkRejection + ", finishedGood=" + finishedGood
				+ ", spliterType=" + spliterType + "]";
	}

	
}
