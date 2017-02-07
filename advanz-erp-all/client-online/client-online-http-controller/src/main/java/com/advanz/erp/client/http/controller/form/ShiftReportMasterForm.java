package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.ShiftReportMasterDTO;

public class ShiftReportMasterForm {
	private List<ShiftReportMasterDTO> shiftReportMasterList;
	private ShiftReportMasterDTO shiftReportMasterDTO;
	private Integer indexNo;
	private Integer next;
	private Integer previous;
	private String lastShiftDate;

	public String getLastShiftDate() {
		return lastShiftDate;
	}

	public void setLastShiftDate(String lastShiftDate) {
		this.lastShiftDate = lastShiftDate;
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

	public List<ShiftReportMasterDTO> getShiftReportMasterList() {
		return shiftReportMasterList;
	}

	public void setShiftReportMasterList(
			List<ShiftReportMasterDTO> shiftReportMasterList) {
		this.shiftReportMasterList = shiftReportMasterList;
	}

	public ShiftReportMasterDTO getShiftReportMasterDTO() {
		return shiftReportMasterDTO;
	}

	public void setShiftReportMasterDTO(
			ShiftReportMasterDTO shiftReportMasterDTO) {
		this.shiftReportMasterDTO = shiftReportMasterDTO;
	}

	private String operation;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "ShiftReportMasterForm [shiftReportMasterList="
				+ shiftReportMasterList + ", shiftReportMasterDTO="
				+ shiftReportMasterDTO + "]";
	}

	public Integer getIndexNo() {
		return indexNo;
	}

	public void setIndexNo(Integer indexNo) {
		this.indexNo = indexNo;
	}

}
