package com.advanz.erp.client.http.controller.form;

import java.util.Date;
import java.util.List;

import com.advanz.erp.masters.model.MelterLogSummaryDTO;

public class MelterLogSummaryForm {
	private MelterLogSummaryDTO melterLogSummaryDTO;
	private List<MelterLogSummaryDTO> melterLogSummaryDTOList;
	private Date logDate;
	private String succ;
	
	private Date fromDate;
	private Date toDate;
	private String lastMelterLogDate;
	
	public String getLastMelterLogDate() {
		return lastMelterLogDate;
	}

	public void setLastMelterLogDate(String lastMelterLogDate) {
		this.lastMelterLogDate = lastMelterLogDate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getSucc() {
		return succ;
	}

	public void setSucc(String succ) {
		this.succ = succ;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public MelterLogSummaryDTO getMelterLogSummaryDTO() {
		return melterLogSummaryDTO;
	}

	public void setMelterLogSummaryDTO(MelterLogSummaryDTO melterLogSummaryDTO) {
		this.melterLogSummaryDTO = melterLogSummaryDTO;
	}

	public List<MelterLogSummaryDTO> getMelterLogSummaryDTOList() {
		return melterLogSummaryDTOList;
	}

	public void setMelterLogSummaryDTOList(
			List<MelterLogSummaryDTO> melterLogSummaryDTOList) {
		this.melterLogSummaryDTOList = melterLogSummaryDTOList;
	}

}
