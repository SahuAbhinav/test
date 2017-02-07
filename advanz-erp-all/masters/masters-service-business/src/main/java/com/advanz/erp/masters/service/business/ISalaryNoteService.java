package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.SalaryNoteInputMessage;
import com.advanz.erp.masters.model.msg.SalaryNoteOutputMessage;

public interface ISalaryNoteService extends IAdvanzErpBaseSerivce{
	public SalaryNoteOutputMessage loadSalaryNote();
	public SalaryNoteOutputMessage autoGeneratedNoteID();
	public SalaryNoteOutputMessage createSalaryNote(SalaryNoteInputMessage salaryNoteInputMessage);
	public SalaryNoteOutputMessage findByNoteID(SalaryNoteInputMessage salaryNoteInputMessage);
	public SalaryNoteOutputMessage removeNote(SalaryNoteInputMessage salaryNoteInputMessage);
	public SalaryNoteOutputMessage searchNote(SalaryNoteInputMessage salaryNoteInputMessage);
	public SalaryNoteOutputMessage updateSalaryNote(SalaryNoteInputMessage salaryNoteInputMessage);
}
