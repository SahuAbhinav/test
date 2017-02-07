package com.advanz.erp.client.http.controller.form;

import java.util.ArrayList;
import java.util.List;

import com.advanz.erp.masters.model.SalaryHeadDTO;


public class SalaryHeadForm {

	private SalaryHeadDTO salaryHeadDTO;
	private List<SalaryHeadDTO> rows;
	private ArrayList boxList;
	private String succ;
	
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public ArrayList getBoxList() {
		return boxList;
	}
	public void setBoxList(ArrayList boxList) {
		this.boxList = boxList;
	}
	public SalaryHeadDTO getSalaryHeadDTO() {
		return salaryHeadDTO;
	}
	public void setSalaryHeadDTO( SalaryHeadDTO salaryHeadDTO) {
		this.salaryHeadDTO = salaryHeadDTO;
	}
	public List<SalaryHeadDTO> getRows() {
		return rows;
	}
	public void setRows(List<SalaryHeadDTO> listSalaryHead) {
		this.rows = listSalaryHead;
	}
}
