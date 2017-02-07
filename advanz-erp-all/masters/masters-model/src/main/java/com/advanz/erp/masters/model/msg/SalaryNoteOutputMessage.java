package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.SalaryNoteDTO;

@SuppressWarnings("serial")
public class SalaryNoteOutputMessage extends AdvanzErpBaseOutputMessage {
	
	private SalaryNoteDTO salaryNoteDTO;
	List<SalaryNoteDTO> salaryNoteDTOList;

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

}
