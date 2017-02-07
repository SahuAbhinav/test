package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.SalesOrderMasterDTO;

public class SalesOrderMasterForm {
	private List<SalesOrderMasterDTO> salesOrderMasterList;
	private SalesOrderMasterDTO salesOrderMasterDTO;
	private String succ;

	private String consigneeCityName;
	private String consigneeState;
	private String consigneeBillingAddress;
	private String consigneePhonNo;
	private String consigneeContactPerson;

	private Integer previous;
	private Integer next;
	private String lastSalesOrderDate;

	public String getLastSalesOrderDate() {
		return lastSalesOrderDate;
	}

	public void setLastSalesOrderDate(String lastSalesOrderDate) {
		this.lastSalesOrderDate = lastSalesOrderDate;
	}

	public String getSucc() {
		return succ;
	}

	public void setSucc(String succ) {
		this.succ = succ;
	}

	public List<SalesOrderMasterDTO> getSalesOrderMasterList() {
		return salesOrderMasterList;
	}

	public void setSalesOrderMasterList(
			List<SalesOrderMasterDTO> salesOrderMasterList) {
		this.salesOrderMasterList = salesOrderMasterList;
	}

	public SalesOrderMasterDTO getSalesOrderMasterDTO() {
		return salesOrderMasterDTO;
	}

	public void setSalesOrderMasterDTO(SalesOrderMasterDTO salesOrderMasterDTO) {
		this.salesOrderMasterDTO = salesOrderMasterDTO;
	}

	public String getConsigneeCityName() {
		return consigneeCityName;
	}

	public void setConsigneeCityName(String consigneeCityName) {
		this.consigneeCityName = consigneeCityName;
	}

	public String getConsigneeState() {
		return consigneeState;
	}

	public void setConsigneeState(String consigneeState) {
		this.consigneeState = consigneeState;
	}

	public String getConsigneeBillingAddress() {
		return consigneeBillingAddress;
	}

	public void setConsigneeBillingAddress(String consigneeBillingAddress) {
		this.consigneeBillingAddress = consigneeBillingAddress;
	}

	public String getConsigneePhonNo() {
		return consigneePhonNo;
	}

	public void setConsigneePhonNo(String consigneePhonNo) {
		this.consigneePhonNo = consigneePhonNo;
	}

	public String getConsigneeContactPerson() {
		return consigneeContactPerson;
	}

	public void setConsigneeContactPerson(String consigneeContactPerson) {
		this.consigneeContactPerson = consigneeContactPerson;
	}

	@Override
	public String toString() {
		return "SalesOrderMasterForm [salesOrderMasterDTO="
				+ salesOrderMasterDTO + "]";
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

}
