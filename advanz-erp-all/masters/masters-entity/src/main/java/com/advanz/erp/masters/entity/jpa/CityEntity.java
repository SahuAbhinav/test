package com.advanz.erp.masters.entity.jpa;

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
@Table(name = "M_CITY")
public class CityEntity extends BaseEntity {
	

	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="city_id")
	private Integer cityId;
	
	@Column(name="city_code")
	private String cityCode;
	
	@Column(name="city_name")
	private String cityName;
	
	
//	@Column(name="area_id")
//	private Integer areaId;
	
	@ManyToOne
	@JoinColumn(name="area_id")
	private AreaEntity areaEntity;
	
	@Column(name="description")
	private String description;
	
	
	

	public Integer getUid() {
		// TODO Auto-generated method stub
	  return cityId;
	}
	

	public void setUid(Integer uid) {
		// TODO Auto-generated method stub
		cityId=uid;
		
		
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

//	public Integer getAreaId() {
//		return areaId;
//	}
//
//	public void setAreaId(Integer areaId) {
//		this.areaId = areaId;
//	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AreaEntity getAreaEntity() {
		return areaEntity;
	}

	public void setAreaEntity(AreaEntity areaEntity) {
		this.areaEntity = areaEntity;
	}

}
