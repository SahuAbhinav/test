package com.advanz.erp.client.http.controller.form;

import java.util.Date;
import java.util.List;

import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.SalaryNoteDTO;

public class SalaryNoteForm {
	private SalaryNoteDTO salaryNoteDTO;
	private List<SalaryNoteDTO> salaryNoteDTOList;
	private Date nDate;
	private Integer nStatus;
	private String succ;

	public SalaryNoteDTO getSalaryNoteDTO() {
		return salaryNoteDTO;
	}

	public void setSalaryNoteDTO(SalaryNoteDTO salaryNoteDTO) {
		this.salaryNoteDTO = salaryNoteDTO;
	}

	public List<SalaryNoteDTO> getSalaryNoteDTOList() {
		return salaryNoteDTOList;
	}

	public void setSalaryNoteDTOList(List<SalaryNoteDTO> salaryNoteDTOList) {
		this.salaryNoteDTOList = salaryNoteDTOList;
	}

	public String getSucc() {
		return succ;
	}

	public void setSucc(String succ) {
		this.succ = succ;
	}

	public Date getnDate() {
		return nDate;
	}

	public void setnDate(Date nDate) {
		this.nDate = nDate;
	}

	public Integer getnStatus() {
		return nStatus;
	}

	public void setnStatus(Integer nStatus) {
		this.nStatus = nStatus;
	}
	
}
