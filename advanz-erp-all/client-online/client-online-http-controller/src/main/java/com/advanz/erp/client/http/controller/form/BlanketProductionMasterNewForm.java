package com.advanz.erp.client.http.controller.form;

import java.util.Date;
import java.util.List;

import com.advanz.erp.masters.model.BlanketProductionDetailNewDTO;
import com.advanz.erp.masters.model.BlanketProductionMasterNewDTO;

public class BlanketProductionMasterNewForm {
	private List<BlanketProductionMasterNewDTO> blanketProductionMasterNewList;
	private BlanketProductionMasterNewDTO blanketProductionMasterNewDTO;
	private List<BlanketProductionDetailNewDTO> blanketProductionDetailNewList;
	private Integer previous;
	private Integer next;
	private String succ;
	private Date onDate;
	private String lastBlanketEntryDate;
	private String blanketCutoffDate;

	public String getBlanketCutoffDate() {
		return blanketCutoffDate;
	}

	public void setBlanketCutoffDate(String blanketCutoffDate) {
		this.blanketCutoffDate = blanketCutoffDate;
	}

	public List<BlanketProductionMasterNewDTO> getBlanketProductionMasterNewList() {
		return blanketProductionMasterNewList;
	}

	public void setBlanketProductionMasterNewList(
			List<BlanketProductionMasterNewDTO> blanketProductionMasterNewList) {
		this.blanketProductionMasterNewList = blanketProductionMasterNewList;
	}

	public BlanketProductionMasterNewDTO getBlanketProductionMasterNewDTO() {
		return blanketProductionMasterNewDTO;
	}

	public void setBlanketProductionMasterNewDTO(
			BlanketProductionMasterNewDTO blanketProductionMasterNewDTO) {
		this.blanketProductionMasterNewDTO = blanketProductionMasterNewDTO;
	}

	public List<BlanketProductionDetailNewDTO> getBlanketProductionDetailNewList() {
		return blanketProductionDetailNewList;
	}

	public void setBlanketProductionDetailNewList(
			List<BlanketProductionDetailNewDTO> blanketProductionDetailNewList) {
		this.blanketProductionDetailNewList = blanketProductionDetailNewList;
	}

	public Integer getPrevious() {
		return previous;
	}

	public void setPrevious(Integer previous) {
		this.previous = previous;
	}

	public Integer getNext() {
		return next;
	}

	public void setNext(Integer next) {
		this.next = next;
	}

	public String getSucc() {
		return succ;
	}

	public void setSucc(String succ) {
		this.succ = succ;
	}

	public Date getOnDate() {
		return onDate;
	}

	public void setOnDate(Date onDate) {
		this.onDate = onDate;
	}

	public String getLastBlanketEntryDate() {
		return lastBlanketEntryDate;
	}

	public void setLastBlanketEntryDate(String lastBlanketEntryDate) {
		this.lastBlanketEntryDate = lastBlanketEntryDate;
	}

}
