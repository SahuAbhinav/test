package com.advanz.erp.client.http.controller.form;

import java.util.Date;
import java.util.List;

import com.advanz.erp.masters.model.MelterTrollyLogDTO;

public class MelterTrollyLogForm {
	private MelterTrollyLogDTO melterTrollyLogDTO;
    private List<MelterTrollyLogDTO> melterTrollyLogDTOList;
	private Integer sno;
	private String succ;
	
	private String trollyNumber;
	private Date logDate;
	
	private Date fromDate;
	private Date toDate;
    public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getTrollyNumber() {
		return trollyNumber;
	}

	public void setTrollyNumber(String trollyNumber) {
		this.trollyNumber = trollyNumber;
	}

	public String getSucc() {
		return succ;
	}

	public void setSucc(String succ) {
		this.succ = succ;
	}

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public List<MelterTrollyLogDTO> getMelterTrollyLogDTOList() {
		return melterTrollyLogDTOList;
	}

	public void setMelterTrollyLogDTOList(
			List<MelterTrollyLogDTO> melterTrollyLogDTOList) {
		this.melterTrollyLogDTOList = melterTrollyLogDTOList;
	}

	public MelterTrollyLogDTO getMelterTrollyLogDTO() {
		return melterTrollyLogDTO;
	}

	public void setMelterTrollyLogDTO(MelterTrollyLogDTO melterTrollyLogDTO) {
		this.melterTrollyLogDTO = melterTrollyLogDTO;
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
	
}
