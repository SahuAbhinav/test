package com.advanz.erp.masters.model;

import java.sql.Time;
import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;


@SuppressWarnings("serial")
public class BlanketWeightRecordDTO extends BaseDTO {
	
	private Integer weightId;
	private Date weightDate;
	private Time weightTime;
	private Double weight;
	private Character blanketType;

	public Character getBlanketType() {
		return blanketType;
	}

	public void setBlanketType(Character blanketType) {
		this.blanketType = blanketType;
	}

	public Integer getWeightId() {
		return weightId;
	}

	public void setWeightId(Integer weightId) {
		this.weightId = weightId;
	}

	public Date getWeightDate() {
		return weightDate;
	}

	public void setWeightDate(Date weightDate) {
		this.weightDate = weightDate;
	}

	public Time getWeightTime() {
		return weightTime;
	}

	public void setWeightTime(Time weightTime) {
		this.weightTime = weightTime;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	
	@Override
	public String toString() {
		return "BlanketWeightRecordDTO [weightId=" + weightId + ", weightDate="
				+ weightDate + ", weightTime=" + weightTime + ", weight="
				+ weight + "]";
	}
	
	
	

}
