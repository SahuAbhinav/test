package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.BillDTO;

public class BillOutMessage extends AdvanzErpBaseOutputMessage{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BillDTO billDTO;
	private List<BillDTO> billDTOList;
	private Integer billSeries;
	public Integer getBillSeries() {
		return billSeries;
	}
	public void setBillSeries(Integer billSeries) {
		this.billSeries = billSeries;
	}
	public BillDTO getBillDTO() {
		return billDTO;
	}
	public void setBillDTO(BillDTO billDTO) {
		this.billDTO = billDTO;
	}
	public List<BillDTO> getBillDTOList() {
		return billDTOList;
	}
	public void setBillDTOList(List<BillDTO> billDTOList) {
		this.billDTOList = billDTOList;
	} 
	private List<String> list;
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	
}
