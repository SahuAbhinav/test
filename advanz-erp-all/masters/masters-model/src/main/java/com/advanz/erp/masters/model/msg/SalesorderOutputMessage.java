package com.advanz.erp.masters.model.msg;


import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.SalesOrderDTO;

public class SalesorderOutputMessage extends AdvanzErpBaseOutputMessage{

	private SalesOrderDTO salesOrderDTO;
	private List<SalesOrderDTO> salesOrderDTOList;
	public SalesOrderDTO getSalesOrderDTO() {
		return salesOrderDTO;
	}
	public void setSalesOrderDTO(SalesOrderDTO salesOrderDTO) {
		this.salesOrderDTO = salesOrderDTO;
	}
	public List<SalesOrderDTO> getSalesOrderDTOList() {
		return salesOrderDTOList;
	}
	public void setSalesOrderDTOList(List<SalesOrderDTO> salesOrderDTOList) {
		this.salesOrderDTOList = salesOrderDTOList;
	}

}
