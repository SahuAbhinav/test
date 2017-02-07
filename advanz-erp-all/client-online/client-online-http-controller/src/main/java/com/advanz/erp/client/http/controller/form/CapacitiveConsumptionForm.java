package com.advanz.erp.client.http.controller.form;

import java.util.Date;
import java.util.List;

import com.advanz.erp.masters.model.CapativeConsuptionDTO;

public class CapacitiveConsumptionForm {
public CapativeConsuptionDTO capativeConsuptionDTO;
public List<CapativeConsuptionDTO> capativeConsuptionDTOList;
private Integer next;
private Integer previous;
private String sourceItemCode;
private String targetItemCode;
private Date enteredDate;
private String succ;

public String getSucc() {
	return succ;
}

public void setSucc(String succ) {
	this.succ = succ;
}

public List<CapativeConsuptionDTO> getCapativeConsuptionDTOList() {
	return capativeConsuptionDTOList;
}

public void setCapativeConsuptionDTOList(
		List<CapativeConsuptionDTO> capativeConsuptionDTOList) {
	this.capativeConsuptionDTOList = capativeConsuptionDTOList;
}

public CapativeConsuptionDTO getCapativeConsuptionDTO() {
	return capativeConsuptionDTO;
}

public void setCapativeConsuptionDTO(CapativeConsuptionDTO capativeConsuptionDTO) {
	this.capativeConsuptionDTO = capativeConsuptionDTO;
}

public Integer getNext() {
	return next;
}

public void setNext(Integer next) {
	this.next = next;
}

public Integer getPrevious() {
	return previous;
}

public void setPrevious(Integer previous) {
	this.previous = previous;
}

public String getSourceItemCode() {
	return sourceItemCode;
}

public void setSourceItemCode(String sourceItemCode) {
	this.sourceItemCode = sourceItemCode;
}

public String getTargetItemCode() {
	return targetItemCode;
}

public void setTargetItemCode(String targetItemCode) {
	this.targetItemCode = targetItemCode;
}

public Date getEnteredDate() {
	return enteredDate;
}

public void setEnteredDate(Date enteredDate) {
	this.enteredDate = enteredDate;
}

}
