package com.advanz.erp.masters.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_blanket_production_detail_new")
public class BlanketProductionDetailNewEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sno")
	private Integer sno;

	@Column(name = "blanket_prod_id")
	private Integer blanketProductionId;

	@Column(name = "roll_no")
	private Integer rollNo;

	@Column(name = "length")
	private Double length;

	@Column(name = "width")
	private Double width;

	@Column(name = "thick")
	private Double thick;

	@Column(name = "weight")
	private Double weight;

	@Column(name = "density")
	private Double density;

	@Column(name = "remark")
	private String remark;

	@Column(name = "blanket_type")
	private String blanketType;

	@Column(name = "item_id")
	private Integer itemId;

	@Column(name = "status")
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date")
	private Date updateDate;
	// New fields for rejection migration
	@Column(name = "rej_roll_no")
	private Integer rejRollNo;

	@Column(name = "rej_length")
	private Double rejLength;

	@Column(name = "rej_width")
	private Double rejWidth;

	@Column(name = "rej_thick")
	private Double rejThick;

	@Column(name = "rej_weight")
	private Double rejWeight;

	@Column(name = "rej_density")
	private Double rejDensity;

	@Column(name = "rej_remark")
	private String rejRemark;

	@Column(name = "rej_item_id")
	private Integer rejItemId;

	@Column(name = "rej_status")
	private String rejStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "rej_update_date")
	private Date rejUpdateDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "approved_date")
	private Date approvedDate;

	@Column(name = "approved_status")
	private Integer approvedStatus;

	@Column(name = "rejected_user_id")
	private Integer rejectedUserId;

	@Column(name = "ra_user_id")
	private Integer raUserId;

	@Column(name = "approved_user_id")
	private Integer approvedUserId;

	@Column(name = "finished_good")
	private String finishedGood;
	
	@Column(name = "spliter_type")
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

	public String getFinishedGood() {
		return finishedGood;
	}

	public void setFinishedGood(String finishedGood) {
		this.finishedGood = finishedGood;
	}

}
