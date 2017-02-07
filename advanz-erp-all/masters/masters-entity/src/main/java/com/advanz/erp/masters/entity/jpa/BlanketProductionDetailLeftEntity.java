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
@Table(name="t_blanket_production_detail_left")
public class BlanketProductionDetailLeftEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sno")
	private Integer sno;
	
	@Column(name="blanket_prod_id")
	private Integer blanketProductionId;
	
	@Column(name="roll_no_l")
	private Integer rollNoLeft;
	
	@Column(name="length_l")
	private Double lengthLeft;
	
	@Column(name="width_l")
	private Double widthLeft;
	
	@Column(name="thick_l")
	private Double thickLeft;
	
	@Column(name="weight_l")
	private Double weightLeft;
	
	@Column(name="density_l")
	private Double densityLeft;
	
	@Column(name="remark_l")
	private String remarkLeft;

	@Column(name="blanket_type")
	private String blanketType;
	
	@Column(name="item_id")
	private Integer itemId;
	
	@Column(name="status_left")
	private String statusLeft;
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_date_left")
	private Date updateDateLeft;
	//New fields for rejection migration
	@Column(name="rej_roll_no")
	private Integer rejRollNo;
	
	@Column(name="rej_length_l")
	private Double rejLengthl;
	
	@Column(name="rej_width_l")
	private Double rejWidthl;
	
	@Column(name="rej_thick_l")
	private Double rejThickl;
	
	@Column(name="rej_weight_l")
	private Double rejWeightl;
	@Column(name="rej_density_l")
	private Double rejDensityl;
	@Column(name="rej_remark_l")
	private String rejRemarkl;
	@Column(name="rej_item_id")
	private Integer rejItemId;
	@Column(name="rej_status")
	private String rejStatus;
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="rej_update_date_left")
	private Date rejUpdateDateLeft;
	
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="approved_date")
	private Date approvedDate;
	
	@Column(name="approved_status")
	private Integer approvedStatus;
	
	@Column(name="rejected_user_id")
	private Integer rejectedUserId;
	
	@Column(name="ra_user_id")
	private Integer raUserId;
	
	@Column(name="approved_user_id")
	private Integer approvedUserId;
	
	@Column(name="finished_good")
	private String finishedGood;
	
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

	@Override
	public boolean equals(Object ob) {
		BlanketProductionDetailLeftEntity e=(BlanketProductionDetailLeftEntity)ob;
		return e.getSno()==sno;	
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

	public String getFinishedGood() {
		return finishedGood;
	}

	public void setFinishedGood(String finishedGood) {
		this.finishedGood = finishedGood;
	}

}
