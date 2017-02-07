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
@Table(name="t_blanket_production_detail_right")
public class BlanketProductionDetailRightEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sno")
	private Integer sno;
	
	@Column(name="blanket_prod_id")
	private Integer blanketProductionId;
	
	@Column(name="roll_no_r")
	private Integer rollNoRight;
	
	@Column(name="length_r")
	private Double lengthRight;
	
	@Column(name="width_r")
	private Double widthRight;
	
	@Column(name="thick_r")
	private Double thickRight;
	
	@Column(name="weight_r")
	private Double weightRight;
	
	@Column(name="density_r")
	private Double densityRight;
	
	@Column(name="remark_r")
	private String remarkRight;

	@Column(name="blanket_type")
	private String blanketType;
	
	@Column(name="item_id")
	private Integer itemId;
	
	

	@Column(name="status_right")
	private String statusRight;
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_date_right")
	private Date updateDateRight;
	// New frields for rejection migration........
	
	@Column(name="rej_roll_no_r")
	private Integer rejRollNo;
	
	@Column(name="rej_length_r")
	private Double rejLengthr;
	@Column(name="rej_width_r")
	private Double rejWidthr;
	@Column(name="rej_thick_r")
	private Double rejThickr;
	
	@Column(name="rej_weight_r")
	private Double rejWeightr;
	
	@Column(name="rej_density_r")
	private Double rejDensityr;
	@Column(name="rej_remark_r")
	private String rejRemarkr;
	@Column(name="rej_item_id")
	private Integer rejItemId;
	@Column(name="rej_status_right")
	private String rejStatusRight;
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="rej_update_date_right")
	private Date rejUpdateDateRight;
	
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

	@Override
	public boolean equals(Object ob) {
		BlanketProductionDetailRightEntity e=(BlanketProductionDetailRightEntity)ob;
		return e.getSno()==sno;	
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

	public String getFinishedGood() {
		return finishedGood;
	}

	public void setFinishedGood(String finishedGood) {
		this.finishedGood = finishedGood;
	}

	

}
