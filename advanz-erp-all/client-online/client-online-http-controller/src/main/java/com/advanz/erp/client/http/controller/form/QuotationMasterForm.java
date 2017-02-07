package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.QuotationMasterDTO;

public class QuotationMasterForm {
	private List<QuotationMasterDTO> quotationMasterList;
	private QuotationMasterDTO quotationMasterDTO;
	private String succ;
	private Integer next;
	private Integer previous;
	private String lastQuotationDate;
	
	
	public String getLastQuotationDate() {
		return lastQuotationDate;
	}
	public void setLastQuotationDate(String lastQuotationDate) {
		this.lastQuotationDate = lastQuotationDate;
	}
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public List<QuotationMasterDTO> getQuotationMasterList() {
		return quotationMasterList;
	}
	public void setQuotationMasterList(List<QuotationMasterDTO> quotationMasterList) {
		this.quotationMasterList = quotationMasterList;
	}
	public QuotationMasterDTO getQuotationMasterDTO() {
		return quotationMasterDTO;
	}
	public void setQuotationMasterDTO(QuotationMasterDTO quotationMasterDTO) {
		this.quotationMasterDTO = quotationMasterDTO;
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
	
}
