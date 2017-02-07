package com.advanz.erp.masters.model.criteria;

import com.advanz.erp.common.model.BaseDTO;

public class MastersSearchCriteriaDTO extends BaseDTO {
private String formName;
private String name;
private String code;
/**
 * @return the formName
 */
public String getFormName() {
	return formName;
}
/**
 * @param formName the formName to set
 */
public void setFormName(String formName) {
	this.formName = formName;
}
/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}
/**
 * @return the code
 */
public String getCode() {
	return code;
}
/**
 * @param code the code to set
 */
public void setCode(String code) {
	this.code = code;
}




}
