package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "M_AREA")

public class AreaEntity extends BaseEntity {
	
	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="area_id")
	private Integer areaId;
	
	@Column(name="area_code")
	private String areaCode;
	
	@Column(name="area_name")
	private String areaName;
	
	
	@ManyToOne
	@JoinColumn(name="region_id")
	private RegionEntity regionEntity;
	
	@Column(name="description")
	private String description;
	
	


	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	
	public RegionEntity getRegionEntity() {
		return regionEntity;
	}

	public void setRegionEntity(RegionEntity regionEntity) {
		this.regionEntity = regionEntity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
