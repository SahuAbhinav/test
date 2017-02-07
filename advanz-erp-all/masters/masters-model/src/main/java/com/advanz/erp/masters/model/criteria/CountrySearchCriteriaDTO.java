package com.advanz.erp.masters.model.criteria;

import com.advanz.erp.common.model.BaseDTO;

public class CountrySearchCriteriaDTO extends BaseDTO {
private String countryName;
private String countryCode;


public CountrySearchCriteriaDTO() {
	super();
}



public String getCountryName() {
	return countryName;
}

public void setCountryName(String countryName) {
	this.countryName = countryName;
}

public CountrySearchCriteriaDTO(String countryName,String countryCode) {
	super();
	
	this.countryCode = countryCode;
	this.countryName = countryName;
}



public String getCountryCode() {
	return countryCode;
}



public void setCountryCode(String countryCode) {
	this.countryCode = countryCode;
}

}
