package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.SalaryMasterDTO;

@SuppressWarnings("serial")
public class SalaryMasterInputMessage extends AdvanzErpBaseInputMessage{
	private SalaryMasterDTO salaryMasterDTO;
	private Integer next;
	private Integer previous;
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
	public SalaryMasterDTO getSalaryMasterDTO() {
		return salaryMasterDTO;
	}
	public void setSalaryMasterDTO(SalaryMasterDTO salaryMasterDTO) {
		this.salaryMasterDTO = salaryMasterDTO;
	}
}
