package com.advanz.erp.masters.model.criteria;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class ZoneSearchCriteriaDTO extends BaseDTO {
private String zoneName;
private String zoneCode;
private String countryName;

public ZoneSearchCriteriaDTO() {
	super();
}

public String getZoneName() {
	return zoneName;
}

public void setZoneName(String zoneName) {
	this.zoneName = zoneName;
}

public String getZoneCode() {
	return zoneCode;
}

public void setZoneCode(String zoneCode) {
	this.zoneCode = zoneCode;
}

public String getCountryName() {
	return countryName;
}

public void setCountryName(String countryName) {
	this.countryName = countryName;
}

public ZoneSearchCriteriaDTO(String zoneName, String zoneCode,
		String countryName) {
	super();
	this.zoneName = zoneName;
	this.zoneCode = zoneCode;
	this.countryName = countryName;
}

}
