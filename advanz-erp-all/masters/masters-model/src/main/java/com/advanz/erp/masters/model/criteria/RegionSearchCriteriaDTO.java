package com.advanz.erp.masters.model.criteria;

import com.advanz.erp.common.model.BaseDTO;

public class RegionSearchCriteriaDTO extends BaseDTO {
private String regionName;
private String regionCode;
private String stateName;

public RegionSearchCriteriaDTO() {
	super();
}
public RegionSearchCriteriaDTO(String regionName, String regionCode,
		String stateName) {
	super();
	this.regionName = regionName;
	this.regionCode = regionCode;
	this.stateName = stateName;
}
public String getRegionName() {
	return regionName;
}
public void setRegionName(String regionName) {
	this.regionName = regionName;
}
public String getRegionCode() {
	return regionCode;
}
public void setRegionCode(String regionCode) {
	this.regionCode = regionCode;
}
public String getStateName() {
	return stateName;
}
public void setStateName(String stateName) {
	this.stateName = stateName;
}


}
