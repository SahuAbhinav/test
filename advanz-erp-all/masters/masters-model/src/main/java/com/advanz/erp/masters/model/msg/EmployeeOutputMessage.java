package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.EmployeeDTO;

@SuppressWarnings("serial")
public class EmployeeOutputMessage extends AdvanzErpBaseOutputMessage {
private EmployeeDTO employeeDTO;
	
	private List<EmployeeDTO> employeeDTOList;

	/**
	 * @return the employeeDTO
	 */
	public EmployeeDTO getEmployeeDTO() {
		return employeeDTO;
	}

	/**
	 * @param employeeDTO the employeeDTO to set
	 */
	public void setEmployeeDTO(EmployeeDTO employeeDTO) {
		this.employeeDTO = employeeDTO;
	}

	/**
	 * @return the employeeDTOList
	 */
	public List<EmployeeDTO> getEmployeeDTOList() {
		return employeeDTOList;
	}

	/**
	 * @param employeeDTOList the employeeDTOList to set
	 */
	public void setEmployeeDTOList(List<EmployeeDTO> employeeDTOList) {
		this.employeeDTOList = employeeDTOList;
	}

}
