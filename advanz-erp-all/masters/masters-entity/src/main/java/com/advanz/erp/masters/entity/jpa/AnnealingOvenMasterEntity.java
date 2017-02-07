package com.advanz.erp.masters.entity.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_annealing_oven_master")
public class AnnealingOvenMasterEntity extends BaseEntity {
	@Id
	@GeneratedValue(generator = "system-incr")
	@GenericGenerator(name = "system-incr", strategy = "increment")
	@Column(name = "oven_id")
	private Integer ovenId;

	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "oven_date")
	private Date ovenDate;
	
	@ManyToOne
	@JoinColumn(name = "shift_id")
	private MastersEntity shiftMasters;
	
	@Column(name = "lpg_reading")
	private Double lpgReading;
	
	@Column(name = "shift_engineer_name")
	private String shiftEngineerName;
	
	@Column(name = "initial_reading")
	private Double initialReading;
	
	@Column(name = "final_reading")
	private Double finalReading;
	
	@Column(name = "total_reading")
	private Double totalReading;
	
	@Column(name = "refilling")
	private Double refilling;
	
	@Column(name = "initial_reading1")
	private Double initialReading1;
	
	@Column(name = "final_reading1")
	private Double finalReading1;
	
	@Column(name = "total_reading1")
	private Double totalReading1;
	
	@Column(name = "hsd_dip_reading")
	private Double hsdDipReading;

	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="oven_id")
	private List<AnnealingOvenDetailEntity> annealingOvenDetailEntity;
	
	public List<AnnealingOvenDetailEntity> getAnnealingOvenDetailEntity() {
		return annealingOvenDetailEntity;
	}

	public void setAnnealingOvenDetailEntity(
			List<AnnealingOvenDetailEntity> annealingOvenDetailEntity) {
		this.annealingOvenDetailEntity = annealingOvenDetailEntity;
	}

	public Integer getOvenId() {
		return ovenId;
	}

	public void setOvenId(Integer ovenId) {
		this.ovenId = ovenId;
	}

	public Date getOvenDate() {
		return ovenDate;
	}

	public void setOvenDate(Date ovenDate) {
		this.ovenDate = ovenDate;
	}

	public MastersEntity getShiftMasters() {
		return shiftMasters;
	}

	public void setShiftMasters(MastersEntity shiftMasters) {
		this.shiftMasters = shiftMasters;
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

	public Double getinitialReading() {
		return initialReading;
	}

	public void setinitialReading(Double initialReading) {
		this.initialReading = initialReading;
	}

	public Double getFinalReading() {
		return finalReading;
	}

	public void setFinalReading(Double finalReading) {
		this.finalReading = finalReading;
	}

	public Double getTotalReading() {
		return totalReading;
	}

	public void setTotalReading(Double totalReading) {
		this.totalReading = totalReading;
	}

	public Double getRefilling() {
		return refilling;
	}

	public void setRefilling(Double refilling) {
		this.refilling = refilling;
	}

	

	public Double getinitialReading1() {
		return initialReading1;
	}

	public void setinitialReading1(Double initialReading1) {
		this.initialReading1 = initialReading1;
	}

	public Double getFinalReading1() {
		return finalReading1;
	}

	public void setFinalReading1(Double finalReading1) {
		this.finalReading1 = finalReading1;
	}

	public Double getTotalReading1() {
		return totalReading1;
	}

	public void setTotalReading1(Double totalReading1) {
		this.totalReading1 = totalReading1;
	}

	public Double getHsdDipReading() {
		return hsdDipReading;
	}

	public void setHsdDipReading(Double hsdDipReading) {
		this.hsdDipReading = hsdDipReading;
	}
	
	
}
