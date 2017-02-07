package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.BillDetailDTO;
import com.advanz.erp.masters.model.StockLedgerDTO;

public class StockLedgerInputMessage extends AdvanzErpBaseInputMessage{
	private StockLedgerDTO stockLedgerDTO;
	private String batchNo;

	public StockLedgerDTO getStockLedgerDTO() {
		return stockLedgerDTO;
	}

	public void setStockLedgerDTO(StockLedgerDTO stockLedgerDTO) {
		this.stockLedgerDTO = stockLedgerDTO;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	

}
