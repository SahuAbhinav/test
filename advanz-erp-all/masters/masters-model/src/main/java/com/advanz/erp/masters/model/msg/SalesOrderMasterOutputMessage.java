package com.advanz.erp.masters.model.msg;

import java.sql.Timestamp;
import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.SalesOrderMasterDTO;

@SuppressWarnings("serial")
public class SalesOrderMasterOutputMessage extends AdvanzErpBaseOutputMessage {

	private SalesOrderMasterDTO salesOrderMasterDTO;

	private List<SalesOrderMasterDTO> salesOrderMasterDTOList;

	private Integer salesOrderSeriesNo;

	private Timestamp salesOrderSeriesDate;

	public Timestamp getSalesOrderSeriesDate() {
		return salesOrderSeriesDate;
	}

	public void setSalesOrderSeriesDate(Timestamp salesOrderSeriesDate) {
		this.salesOrderSeriesDate = salesOrderSeriesDate;
	}

	public Integer getSalesOrderSeriesNo() {
		return salesOrderSeriesNo;
	}

	public void setSalesOrderSeriesNo(Integer salesOrderSeriesNo) {
		this.salesOrderSeriesNo = salesOrderSeriesNo;
	}

	public SalesOrderMasterDTO getSalesOrderMasterDTO() {
		return salesOrderMasterDTO;
	}

	public void setSalesOrderMasterDTO(SalesOrderMasterDTO salesOrderMasterDTO) {
		this.salesOrderMasterDTO = salesOrderMasterDTO;
	}

	public List<SalesOrderMasterDTO> getSalesOrderMasterDTOList() {
		return salesOrderMasterDTOList;
	}

	public void setSalesOrderMasterDTOList(
			List<SalesOrderMasterDTO> salesOrderMasterDTOList) {
		this.salesOrderMasterDTOList = salesOrderMasterDTOList;
	}

}
