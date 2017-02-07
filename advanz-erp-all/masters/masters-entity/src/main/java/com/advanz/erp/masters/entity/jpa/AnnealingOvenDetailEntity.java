package com.advanz.erp.masters.entity.jpa;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;
@SuppressWarnings("serial")
@Entity
@Table(name = "t_annealing_oven_detail")
public class AnnealingOvenDetailEntity extends BaseEntity {

	@Id
	@GeneratedValue(generator = "system-incr")
	@GenericGenerator(name = "system-incr", strategy = "increment")
	@Column(name = "sno")
	private Integer sno;
	
	@Column(name = "oven_id")
	private Integer ovenId;
		
	@Column(name="oven_time")
	private Time ovenTime;
	
	public Time getOvenTime() {
		return ovenTime;
	}

	public void setOvenTime(Time ovenTime) {
		this.ovenTime = ovenTime;
	}

	@Column(name = "oven_temp_zone_1")
	private Double ovenTempZone1;
	
	@Column(name = "oven_temp_zone_2")
	private Double ovenTempZone2;
	
	@Column(name = "oven_temp_zone_3")
	private Double ovenTempZone3;
	
	@Column(name = "oven_temp_zone_4")
	private Double ovenTempZone4;
	
	@Column(name = "hsd_dip_reading")
	private Double hsdDipReading;
	
	@Column(name = "oven_remark")
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
