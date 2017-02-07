package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.AreaDTO;
import com.advanz.erp.masters.model.SalaryHeadDTO;

public class SalaryHeadOutputMessage extends AdvanzErpBaseOutputMessage{



	private SalaryHeadDTO salaryHeadDTO;
	
	private List<SalaryHeadDTO> salaryHeadDTOList;
	
	private List<String> list;
	
	public List<String> getList() {
		return list;
	}
	
	public void setList(List<String> list) {
		this.list = list;
	}

	/**
	 * @return the salaryHeadDTO
	 */
	public SalaryHeadDTO getSalaryHeadDTO() {
		return salaryHeadDTO;
	}

	/**
	 * @param batchDTO the salaryHeadDTO to set
	 */
	public void setSalaryHeadDTO(SalaryHeadDTO salaryHeadDTO) {
		this.salaryHeadDTO = salaryHeadDTO;
	}

	/**
	 * @return the salaryHeadDTOList
	 */
	public List<SalaryHeadDTO> getSalaryHeadDTOList() {
		return salaryHeadDTOList;
	}

	/**
	 * @param batchDTOList the salaryHeadDTOList to set
	 */
	public void setSalaryHeadDTOList(List<SalaryHeadDTO> salaryHeadDTOList) {
		this.salaryHeadDTOList = salaryHeadDTOList;
	}
	

}
