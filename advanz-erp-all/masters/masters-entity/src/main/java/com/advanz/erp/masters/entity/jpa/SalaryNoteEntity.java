package com.advanz.erp.masters.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "t_salary_note")
public class SalaryNoteEntity extends BaseEntity {
	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name = "sno")
	private Integer sno;
	
	@Column(name = "note_id")
	private Integer noteId;
	
	@Column(name = "assign_to_emp")
	private Integer assignToEmp;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "note_date")
	private Date noteDate;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "salary_note")
	private String salaryNote;

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


	
}
