package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.FinishedGoodsMasterDTO;


public class FinishedGoodsMasterForm {
	private List<FinishedGoodsMasterDTO> finishedGoodsMasterList;
	private FinishedGoodsMasterDTO finishedGoodsMasterDTO;
	private String succ;
	private Integer next;
	private Integer previous;
	private String lastFinishedGoodDate;
	
	public String getLastFinishedGoodDate() {
		return lastFinishedGoodDate;
	}
	public void setLastFinishedGoodDate(String lastFinishedGoodDate) {
		this.lastFinishedGoodDate = lastFinishedGoodDate;
	}
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public List<FinishedGoodsMasterDTO> getSalesOrderMasterList() {
		return finishedGoodsMasterList;
	}
	public void setFinishedGoodsMasterList(
			List<FinishedGoodsMasterDTO> salesOrderMasterList) {
		this.finishedGoodsMasterList = finishedGoodsMasterList;
	}
	public FinishedGoodsMasterDTO getFinishedGoodsMasterDTO() {
		return finishedGoodsMasterDTO;
	}
	public void setFinishedGoodsMasterDTO(FinishedGoodsMasterDTO finishedGoodsMasterDTO) {
		this.finishedGoodsMasterDTO = finishedGoodsMasterDTO;
	}
	
	public Integer getNext() {
		return next;
	}
	public void setNext(Integer next) {
		this.next = next;
	}
	public Integer getPrevious() {
		return previous;
	}
	public void setPrevious(Integer previous) {
		this.previous = previous;
	}
	@Override
	public String toString() {
		return "FinishedGoodsMasterForm [finishedGoodsMasterDTO="
				+ finishedGoodsMasterDTO + "]";
	}
	
	
	
}
