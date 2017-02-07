package com.advanz.erp.masters.model;

import java.sql.Time;
import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;
@SuppressWarnings("serial")
public class AnnealingOvenDetailDTO extends BaseDTO{
	private Integer sno;
	private Integer ovenId;
	private Time ovenTime;
	private Double ovenTempZone1;
	private Double ovenTempZone2;
	private Double ovenTempZone3;
	private Double ovenTempZone4;
	private Double hsdDipReading;
	private String ovenRemark;
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public Integer getOvenId() {
		return ovenId;
	}
	public void setOvenId(Integer ovenId) {
		this.ovenId = ovenId;
	}
	
	public Time getOvenTime() {
		return ovenTime;
	}
	public void setOvenTime(Time ovenTime) {
		this.ovenTime = ovenTime;
	}
	public Double getOvenTempZone1() {
		return ovenTempZone1;
	}
	public void setOvenTempZone1(Double ovenTempZone1) {
		this.ovenTempZone1 = ovenTempZone1;
	}
	public Double getOvenTempZone2() {
		return ovenTempZone2;
	}
	public void setOvenTempZone2(Double ovenTempZone2) {
		this.ovenTempZone2 = ovenTempZone2;
	}
	public Double getOvenTempZone3() {
		return ovenTempZone3;
	}
	public void setOvenTempZone3(Double ovenTempZone3) {
		this.ovenTempZone3 = ovenTempZone3;
	}
	public Double getOvenTempZone4() {
		return ovenTempZone4;
	}
	public void setOvenTempZone4(Double ovenTempZone4) {
		this.ovenTempZone4 = ovenTempZone4;
	}
	public Double getHsdDipReading() {
		return hsdDipReading;
	}
	public void setHsdDipReading(Double hsdDipReading) {
		this.hsdDipReading = hsdDipReading;
	}
	public String getOvenRemark() {
		return ovenRemark;
	}
	public void setOvenRemark(String ovenRemark) {
		this.ovenRemark = ovenRemark;
	}
	

	
}
