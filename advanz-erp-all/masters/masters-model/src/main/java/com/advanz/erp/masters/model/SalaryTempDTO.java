package com.advanz.erp.masters.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SalaryTempDTO  implements Serializable{
	List<Double> tempList = new ArrayList<Double>();
	List<Double> salaryHeadPayableList = new ArrayList<Double>();
	List<Double> salaryDeductPayableList = new ArrayList<Double>();
	List<Double> tempDedectList = new ArrayList<Double>();
	public List<Double> getTempList() {
		return tempList;
	}

	public void setTempList(List<Double> tempList) {
		this.tempList = tempList;
	}

	public List<Double> getTempDedectList() {
		return tempDedectList;
	}

	public void setTempDedectList(List<Double> tempDedectList) {
		this.tempDedectList = tempDedectList;
	}

	public List<Double> getSalaryHeadPayableList() {
		return salaryHeadPayableList;
	}

	public void setSalaryHeadPayableList(List<Double> salaryHeadPayableList) {
		this.salaryHeadPayableList = salaryHeadPayableList;
	}

	public List<Double> getSalaryDeductPayableList() {
		return salaryDeductPayableList;
	}

	public void setSalaryDeductPayableList(List<Double> salaryDeductPayableList) {
		this.salaryDeductPayableList = salaryDeductPayableList;
	}
	
	
}
