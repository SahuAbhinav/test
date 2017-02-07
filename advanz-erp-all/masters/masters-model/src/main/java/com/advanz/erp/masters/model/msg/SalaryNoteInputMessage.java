package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.SalaryNoteDTO;

@SuppressWarnings("serial")
public class SalaryNoteInputMessage extends AdvanzErpBaseInputMessage {
	private SalaryNoteDTO salaryNoteDTO;

	public SalaryNoteDTO getSalaryNoteDTO() {
		return salaryNoteDTO;
	}

	public void setSalaryNoteDTO(SalaryNoteDTO salaryNoteDTO) {
		this.salaryNoteDTO = salaryNoteDTO;
	}

}
