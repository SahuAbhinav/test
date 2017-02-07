package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.SalesOrderDTO;
import com.advanz.erp.masters.model.criteria.SalesOrderSearchCriteriaDTO;

public class SalesOrderInputMessage extends AdvanzErpBaseInputMessage {

	private SalesOrderDTO salesOrderDTO;
	private SalesOrderSearchCriteriaDTO salesOrderSearchCriteriaDTO;

	public SalesOrderDTO getSalesOrderDTO() {
		return salesOrderDTO;
	}

	public void setSalesOrderDTO(SalesOrderDTO salesOrderDTO) {
		this.salesOrderDTO = salesOrderDTO;
	}

	public SalesOrderSearchCriteriaDTO getSalesOrderSearchCriteriaDTO() {
		return salesOrderSearchCriteriaDTO;
	}

	public void setSalesOrderSearchCriteriaDTO(
			SalesOrderSearchCriteriaDTO salesOrderSearchCriteriaDTO) {
		this.salesOrderSearchCriteriaDTO = salesOrderSearchCriteriaDTO;
	}

}
