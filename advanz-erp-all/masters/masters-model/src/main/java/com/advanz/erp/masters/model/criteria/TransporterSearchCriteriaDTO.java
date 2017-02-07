package com.advanz.erp.masters.model.criteria;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class TransporterSearchCriteriaDTO extends BaseDTO {
private String name;
private String code;
private String address;

public TransporterSearchCriteriaDTO() {
	super();
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

}