package com.advanz.erp.masters.model.criteria;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class CitySearchCriteriaDTO extends BaseDTO {
private String cityName;
private String cityCode;
private String areaName;

public CitySearchCriteriaDTO() {
	super();
}

public CitySearchCriteriaDTO(String cityName, String cityCode, String regionName) {
	super();
	this.cityName = cityName;
	this.cityCode = cityCode;
	this.areaName = areaName;
}

public String getCityName() {
	return cityName;
}

public void setCityName(String cityName) {
	this.cityName = cityName;
}

public String getCityCode() {
	return cityCode;
}

public void setCityCode(String cityCode) {
	this.cityCode = cityCode;
}

public String getAreaName() {
	return areaName;
}

public void setAreaName(String areaName) {
	this.areaName = areaName;
}

}