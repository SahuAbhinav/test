package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

import java.util.Date;


@SuppressWarnings("serial")
public class CityDTO extends BaseDTO {
	
	private Integer cityId;
	
	private String cityCode;
	
	private String cityName;
	
	//private Integer areaId;
	
	private AreaDTO areaDTO;
	
	private String description;

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

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AreaDTO getAreaDTO() {
		return areaDTO;
	}

	public void setAreaDTO(AreaDTO areaDTO) {
		this.areaDTO = areaDTO;
	}
	
	

}
