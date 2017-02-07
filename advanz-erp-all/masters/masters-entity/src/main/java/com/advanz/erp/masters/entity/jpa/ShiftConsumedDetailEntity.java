package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name="t_shift_consumed_detail ")
public class ShiftConsumedDetailEntity extends BaseEntity {
	
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sno")
		private Integer sno;
	

	@Column(name="shift_report_id")
	private Integer shiftReportId;
	
	@Column(name="item_group_flag_id")
	private Integer itemGroupFlagId;
	
//	@Column(name="item_id")
//	private Integer itemId;
	
	@Column(name="measurement_Unit_id")
	private Integer measurementUnitId;
	

	@Column(name="quantity")
	private Double quantity;
	
	
	@Column(name="remark")
	private String remark;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private ItemEntity itemEntity;


	public Integer getSno() {
		return sno;
	}


	public void setSno(Integer sno) {
		this.sno = sno;
	}


	public Integer getShiftReportId() {
		return shiftReportId;
	}


	public void setShiftReportId(Integer shiftReportId) {
		this.shiftReportId = shiftReportId;
	}


	public Integer getItemGroupFlagId() {
		return itemGroupFlagId;
	}


	public void setItemGroupFlagId(Integer itemGroupFlagId) {
		this.itemGroupFlagId = itemGroupFlagId;
	}


//	public Integer getItemId() {
//		return itemId;
//	}
//
//
//	public void setItemId(Integer itemId) {
//		this.itemId = itemId;
//	}


	public Integer getMeasurementUnitId() {
		return measurementUnitId;
	}


	public void setMeasurementUnitId(Integer measurementUnitId) {
		this.measurementUnitId = measurementUnitId;
	}


	public Double getQuantity() {
		return quantity;
	}


	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public ItemEntity getItemEntity() {
		return itemEntity;
	}


	public void setItemEntity(ItemEntity itemEntity) {
		this.itemEntity = itemEntity;
	}


	@Override
	public String toString() {
		return "ShiftConsumedDetailEntity [sno=" + sno + ", shiftReportId="
				+ shiftReportId + ", itemGroupFlagId=" + itemGroupFlagId
				+ ", measurementUnitId=" + measurementUnitId + ", quantity="
				+ quantity + ", remark=" + remark + ", itemEntity="
				+ itemEntity + "]";
	}


	

	
	
	
	
	
	

}
