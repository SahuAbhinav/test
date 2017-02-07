package com.advanz.erp.masters.model.msg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.SalaryMasterDTO;

@SuppressWarnings("serial")
public class SalaryMasterOutputMessage extends AdvanzErpBaseOutputMessage {
	private SalaryMasterDTO salaryMasterDTO;
	private List<SalaryMasterDTO> salaryMasterDTOList;
	private Map map=new HashMap();
	public SalaryMasterDTO getSalaryMasterDTO() {
		return salaryMasterDTO;
	}
	public void setSalaryMasterDTO(SalaryMasterDTO salaryMasterDTO) {
		this.salaryMasterDTO = salaryMasterDTO;
	}
	public List<SalaryMasterDTO> getSalaryMasterDTOList() {
		return salaryMasterDTOList;
	}
	public void setSalaryMasterDTOList(List<SalaryMasterDTO> salaryMasterDTOList) {
		this.salaryMasterDTOList = salaryMasterDTOList;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	
}
