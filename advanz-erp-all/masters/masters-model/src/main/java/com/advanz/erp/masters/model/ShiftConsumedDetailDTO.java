package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class ShiftConsumedDetailDTO extends BaseDTO {

	private Integer sno;

	private Integer shiftReportId;

	private Integer itemGroupFlagId;

	private Integer itemId;
	
	private String itemName;

	private Integer measurementUnitId;
	private String measurementUnitName;
	public String getMeasurementUnitName() {
		return measurementUnitName;
	}

	public void setMeasurementUnitName(String measurementUnitName) {
		this.measurementUnitName = measurementUnitName;
	}

	private Double quantity;
	
	private String remark;
	private ItemDTO itemDTO;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

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

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

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

	public ItemDTO getItemDTO() {
		return itemDTO;
	}

	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}

	@Override
	public String toString() {
		return "ShiftConsumedDetailDTO [sno=" + sno + ", shiftReportId="
				+ shiftReportId + ", itemGroupFlagId=" + itemGroupFlagId
				+ ", itemId=" + itemId + ", measurementUnitId="
				+ measurementUnitId + ", quantity=" + quantity + ", remark="
				+ remark + "]";
	}

	
	

}
