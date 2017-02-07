package com.advanz.erp.client.http.controller.form;

import java.util.Date;
import java.util.List;

import com.advanz.erp.masters.model.BlanketProductionDetailLeftDTO;
import com.advanz.erp.masters.model.BlanketProductionDetailRightDTO;
import com.advanz.erp.masters.model.BlanketProductionMasterDTO;

public class BlanketProductionMasterForm {
	private List<BlanketProductionMasterDTO> blanketProductionMasterList;
	private BlanketProductionMasterDTO blanketProductionMasterDTO;
	private List<BlanketProductionDetailLeftDTO> blanketProductionLeftList;
	private List<BlanketProductionDetailRightDTO> blanketProductionRightList;
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

	public String getLastBlanketEntryDate() {
		return lastBlanketEntryDate;
	}

	public void setLastBlanketEntryDate(String lastBlanketEntryDate) {
		this.lastBlanketEntryDate = lastBlanketEntryDate;
	}

	public String getSucc() {
		return succ;
	}

	public void setSucc(String succ) {
		this.succ = succ;
	}

	public List<BlanketProductionMasterDTO> getBlanketProductionMasterList() {
		return blanketProductionMasterList;
	}

	public void setBlanketProductionMasterList(
			List<BlanketProductionMasterDTO> blanketProductionMasterList) {
		this.blanketProductionMasterList = blanketProductionMasterList;
	}

	public BlanketProductionMasterDTO getBlanketProductionMasterDTO() {
		return blanketProductionMasterDTO;
	}

	public void setBlanketProductionMasterDTO(
			BlanketProductionMasterDTO blanketProductionMasterDTO) {
		this.blanketProductionMasterDTO = blanketProductionMasterDTO;
	}

	public List<BlanketProductionDetailLeftDTO> getBlanketProductionLeftList() {
		return blanketProductionLeftList;
	}

	public void setBlanketProductionLeftList(
			List<BlanketProductionDetailLeftDTO> blanketProductionLeftList) {
		this.blanketProductionLeftList = blanketProductionLeftList;
	}

	public List<BlanketProductionDetailRightDTO> getBlanketProductionRightList() {
		return blanketProductionRightList;
	}

	public void setBlanketProductionRightList(
			List<BlanketProductionDetailRightDTO> blanketProductionRightList) {
		this.blanketProductionRightList = blanketProductionRightList;
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

	public Date getOnDate() {
		return onDate;
	}

	public void setOnDate(Date onDate) {
		this.onDate = onDate;
	}

}
