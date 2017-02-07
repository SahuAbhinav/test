package com.advanz.erp.masters.model.msg;

import java.sql.Timestamp;
import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.BillDTO;
import com.advanz.erp.masters.model.DebitDutyMasterDTO;

public class DebitDutyMasterOutPutMessage extends AdvanzErpBaseOutputMessage{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DebitDutyMasterDTO debitDutyMasterDTO;
	private List<DebitDutyMasterDTO> debitDutyMasterDTOList;
	private Integer debitDutySeries;
	
	private List<String> list;
	private Timestamp debitDutySeriesDate;
	
	public Timestamp getDebitDutySeriesDate() {
		return debitDutySeriesDate;
	}
	public void setDebitDutySeriesDate(Timestamp debitDutySeriesDate) {
		this.debitDutySeriesDate = debitDutySeriesDate;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public DebitDutyMasterDTO getDebitDutyMasterDTO() {
		return debitDutyMasterDTO;
	}
	public void setDebitDutyMasterDTO(DebitDutyMasterDTO debitDutyMasterDTO) {
		this.debitDutyMasterDTO = debitDutyMasterDTO;
	}
	public List<DebitDutyMasterDTO> getDebitDutyMasterDTOList() {
		return debitDutyMasterDTOList;
	}
	public void setDebitDutyMasterDTOList(
			List<DebitDutyMasterDTO> debitDutyMasterDTOList) {
		this.debitDutyMasterDTOList = debitDutyMasterDTOList;
	}
	public Integer getDebitDutySeries() {
		return debitDutySeries;
	}
	public void setDebitDutySeries(Integer debitDutySeries) {
		this.debitDutySeries = debitDutySeries;
	}
	
}
