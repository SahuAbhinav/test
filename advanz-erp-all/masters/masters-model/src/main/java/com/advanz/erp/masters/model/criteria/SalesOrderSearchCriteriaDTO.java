package com.advanz.erp.masters.model.criteria;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

public class SalesOrderSearchCriteriaDTO extends BaseDTO{
	private String salesOrderNumber;
	private Date salesOrderDate;
	private Integer partyId;
	
	
	public SalesOrderSearchCriteriaDTO() {
		super();
	}

	public SalesOrderSearchCriteriaDTO(String salesOrderNumber, Date salesOrderDate, Integer partyId) {
		super();
		this.salesOrderNumber = salesOrderNumber;
		this.salesOrderDate = salesOrderDate;
		this.partyId = partyId;
	}

	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	public Date getSalesOrderDate() {
		return salesOrderDate;
	}

	public void setSalesOrderDate(Date salesOrderDate) {
		this.salesOrderDate = salesOrderDate;
	}

	public Integer getPartyId() {
		return partyId;
	}

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}


	
	
}
