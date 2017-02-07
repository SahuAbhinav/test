package com.advanz.erp.client.http.controller.form;

import java.util.List;


import com.advanz.erp.masters.model.SalesOrderDTO;

public class SalesOrderForm {
	private SalesOrderDTO salesOrderDTO;
	private List<SalesOrderDTO> rows;
	private String salesOrderNumber;
	private String partyName;
	private String succ;
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}
	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}
	public SalesOrderDTO getSalesOrderDTO() {
		return salesOrderDTO;
	}
	public void setSalesOrderDTO(SalesOrderDTO salesOrderDTO) {
		this.salesOrderDTO = salesOrderDTO;
	}
	public List<SalesOrderDTO> getRows() {
		return rows;
	}
	public void setRows(List<SalesOrderDTO> rows) {
		this.rows = rows;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	
	
	
}
