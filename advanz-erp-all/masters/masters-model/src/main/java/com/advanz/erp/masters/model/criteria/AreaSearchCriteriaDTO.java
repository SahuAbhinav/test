package com.advanz.erp.masters.model.criteria;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class AreaSearchCriteriaDTO extends BaseDTO {
private String areaName;
private String areaCode;
private String regionName;

public AreaSearchCriteriaDTO() {
	super();
}

public AreaSearchCriteriaDTO(String areaName, String areaCode, String regionName) {
	super();
	this.areaName = areaName;
	this.areaCode = areaCode;
	this.regionName = regionName;
}

public String getAreaName() {
	return areaName;
}

public void setAreaName(String areaName) {
	this.areaName = areaName;
}

public String getAreaCode() {
	return areaCode;
}

public void setAreaCode(String areaCode) {
	this.areaCode = areaCode;
}

public String getRegionName() {
	return regionName;
}

public void setRegionName(String regionName) {
	this.regionName = regionName;
}

}