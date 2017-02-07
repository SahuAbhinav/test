package com.advanz.erp.client.http.controller.form;

import java.util.Date;
import java.util.List;

import com.advanz.erp.masters.model.MelterLogBookDTO;

public class MelterLogBookFrom {
	private MelterLogBookDTO melterLogBookDTO;
	private List<MelterLogBookDTO> melterLogBookDTOList;
    private Date logDate;
    private Date fromDate;
	private Date toDate;
    private String runNo;
    private String operatorName;
    private String succ;
    private String lastRecordDate;
    
	public String getLastRecordDate() {
		return lastRecordDate;
	}

	public void setLastRecordDate(String lastRecordDate) {
		this.lastRecordDate = lastRecordDate;
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

	public String getRunNo() {
		return runNo;
	}

	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public MelterLogBookDTO getMelterLogBookDTO() {
		return melterLogBookDTO;
	}

	public void setMelterLogBookDTO(MelterLogBookDTO melterLogBookDTO) {
		this.melterLogBookDTO = melterLogBookDTO;
	}

	public List<MelterLogBookDTO> getMelterLogBookDTOList() {
		return melterLogBookDTOList;
	}

	public void setMelterLogBookDTOList(
			List<MelterLogBookDTO> melterLogBookDTOList) {
		this.melterLogBookDTOList = melterLogBookDTOList;
	}
 }
