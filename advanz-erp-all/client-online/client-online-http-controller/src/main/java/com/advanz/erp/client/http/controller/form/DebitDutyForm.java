package com.advanz.erp.client.http.controller.form;

import java.util.ArrayList;
import java.util.List;

import com.advanz.erp.masters.model.DebitDutyMasterDTO;

public class DebitDutyForm {
	private List<DebitDutyMasterDTO> debitDutyList = new ArrayList<DebitDutyMasterDTO>();

	private DebitDutyMasterDTO debitDutyMasterDTO;
	private Integer debitDutyAutoId;
	private String approveStatus;
	private String lastDebitDate;

	public String getLastDebitDate() {
		return lastDebitDate;
	}

	public void setLastDebitDate(String lastDebitDate) {
		this.lastDebitDate = lastDebitDate;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public List<DebitDutyMasterDTO> getDebitDutyList() {
		return debitDutyList;
	}

	public void setDebitDutyList(List<DebitDutyMasterDTO> debitDutyList) {
		this.debitDutyList = debitDutyList;
	}

	public DebitDutyMasterDTO getDebitDutyMasterDTO() {
		return debitDutyMasterDTO;
	}

	public void setDebitDutyMasterDTO(DebitDutyMasterDTO debitDutyMasterDTO) {
		this.debitDutyMasterDTO = debitDutyMasterDTO;
	}

	public Integer getDebitDutyAutoId() {
		return debitDutyAutoId;
	}

	public void setDebitDutyAutoId(Integer debitDutyAutoId) {
		this.debitDutyAutoId = debitDutyAutoId;
	}

	@Override
	public String toString() {
		return "DebitDutyForm [debitDutyMasterDTO=" + debitDutyMasterDTO + "]";
	}

}
