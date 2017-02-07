package com.advanz.erp.masters.model.criteria;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

public class BlanketProductionSearchCriteriaDTO extends BaseDTO{
private Date productionDate;
private String runNo;
private Integer gradeId;
private Date fromDate;
private Date toDate;
public Date getProductionDate() {
	return productionDate;
}
public void setProductionDate(Date productionDate) {
	this.productionDate = productionDate;
}
public String getRunNo() {
	return runNo;
}
public void setRunNo(String runNo) {
	this.runNo = runNo;
}
public Integer getGradeId() {
	return gradeId;
}
public void setGradeId(Integer gradeId) {
	this.gradeId = gradeId;
}
public Date getFromDate() {
	return fromDate;
}
public void setFromDate(Date fromDate) {
	this.fromDate = fromDate;
}
public Date getToDate() {
	return toDate;
}
public void setToDate(Date toDate) {
	this.toDate = toDate;
}



}
