package com.advanz.erp.masters.model;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;
@SuppressWarnings("serial")
public class SalaryNoteDTO extends BaseDTO {
	private Integer sno;
	private Integer noteId;
	private Integer assignToEmp;
	private Date noteDate;
	private Integer status;
	private String salaryNote;
	private List<Integer> empIdList;

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public Integer getAssignToEmp() {
		return assignToEmp;
	}

	public void setAssignToEmp(Integer assignToEmp) {
		this.assignToEmp = assignToEmp;
	}

	public Date getNoteDate() {
		return noteDate;
	}

	public void setNoteDate(Date noteDate) {
		this.noteDate = noteDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSalaryNote() {
		return salaryNote;
	}

	public void setSalaryNote(String salaryNote) {
		this.salaryNote = salaryNote;
	}


	public List<Integer> getEmpIdList() {
		return empIdList;
	}

	public void setEmpIdList(List<Integer> empIdList) {
		this.empIdList = empIdList;
	}
	

}
