package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

public class CityStateCountryDTO extends BaseDTO{
	private Integer cityId;
	private String cityName;
	private String  stateName;
	private String countryName;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	@Override
	public String toString() {
		return "CityStateCountryDTO [cityId=" + cityId + ", cityName="
				+ cityName + ", stateName=" + stateName + ", countryName="
				+ countryName + "]";
	}
	
	

}
