package com.advanz.erp.masters.entity.jpa;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "t_annealing_oven")
public class AnnealingOvenEntity extends BaseEntity {
	@Id
	@GeneratedValue(generator = "system-incr")
	@GenericGenerator(name = "system-incr", strategy = "increment")
	@Column(name = "sno")
	private Integer sno;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "oven_date")
	private Date ovenDate;

	@Column(name = "oven_time")
	private Time ovenTime;

	@ManyToOne
	@JoinColumn(name = "shift_id")
	private MastersEntity shiftMasters;
	
	
	@Column(name = "hsd_dip_reading")
	private Double hsdDipReading;

	@Column(name = "lpg_reading")
	private Double lpgReading;

	@Column(name = "shift_engineer_name")
	private String shiftEngineerName;

	@Column(name = "transformer_temp")
	private Double transformerTemp;

	@Column(name = "oven_temp_zone_2")
	private Double ovenTempZone2;

	@Column(name = "oven_temp_zone_3")
	private Double ovenTempZone3;

	@Column(name = "oven_temp_zone_4")
	private Double ovenTempZone4;

	@Column(name = "oven_temp_zone_1")
	private Double ovenTempZone1;

	@Column(name = "total_fuel_used")
	private Double totalFuelUsed;

	@Column(name = "initial_reading")
	private Double initialReading;

	@Column(name = "final_reading")
	private Double finalReading;

	@Column(name = "actual_reading")
	private Double actualReading;

	@Column(name = "fuel_used_type")
	private String fuelUsedType;

	@Column(name = "oven_remark")
	private String ovenRemark;

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public Date getOvenDate() {
		return ovenDate;
	}

	public void setOvenDate(Date ovenDate) {
		this.ovenDate = ovenDate;
	}

	public Time getOvenTime() {
		return ovenTime;
	}

	public void setOvenTime(Time ovenTime) {
		this.ovenTime = ovenTime;
	}

	public MastersEntity getShiftMasters() {
		return shiftMasters;
	}

	public void setShiftMasters(MastersEntity shiftMasters) {
		this.shiftMasters = shiftMasters;
	}

	public Double getHsdDipReading() {
		return hsdDipReading;
	}

	public void setHsdDipReading(Double hsdDipReading) {
		this.hsdDipReading = hsdDipReading;
	}

	public Double getLpgReading() {
		return lpgReading;
	}

	public void setLpgReading(Double lpgReading) {
		this.lpgReading = lpgReading;
	}

	public String getShiftEngineerName() {
		return shiftEngineerName;
	}

	public void setShiftEngineerName(String shiftEngineerName) {
		this.shiftEngineerName = shiftEngineerName;
	}

	public Double getTransformerTemp() {
		return transformerTemp;
	}

	public void setTransformerTemp(Double transformerTemp) {
		this.transformerTemp = transformerTemp;
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

	public Double getOvenTempZone1() {
		return ovenTempZone1;
	}

	public void setOvenTempZone1(Double ovenTempZone1) {
		this.ovenTempZone1 = ovenTempZone1;
	}

	public Double getTotalFuelUsed() {
		return totalFuelUsed;
	}

	public void setTotalFuelUsed(Double totalFuelUsed) {
		this.totalFuelUsed = totalFuelUsed;
	}

	public Double getInitialReading() {
		return initialReading;
	}

	public void setInitialReading(Double initialReading) {
		this.initialReading = initialReading;
	}

	public Double getFinalReading() {
		return finalReading;
	}

	public void setFinalReading(Double finalReading) {
		this.finalReading = finalReading;
	}

	public Double getActualReading() {
		return actualReading;
	}

	public void setActualReading(Double actualReading) {
		this.actualReading = actualReading;
	}

	public String getFuelUsedType() {
		return fuelUsedType;
	}

	public void setFuelUsedType(String fuelUsedType) {
		this.fuelUsedType = fuelUsedType;
	}

	public String getOvenRemark() {
		return ovenRemark;
	}

	public void setOvenRemark(String ovenRemark) {
		this.ovenRemark = ovenRemark;
	}

}
