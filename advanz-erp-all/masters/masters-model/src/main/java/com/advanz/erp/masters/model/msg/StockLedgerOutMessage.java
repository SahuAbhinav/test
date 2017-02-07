package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.BillDetailDTO;
import com.advanz.erp.masters.model.StockLedgerDTO;

public class StockLedgerOutMessage extends AdvanzErpBaseOutputMessage{
private StockLedgerDTO stockLedgerDTO;

private Double stockCount;

private List<StockLedgerDTO> stockLedgerDTOList;

private Double quantityCount;

public Double getQuantityCount() {
	return quantityCount;
}
public void setQuantityCount(Double quantityCount) {
	this.quantityCount = quantityCount;
}
public Double getStockCount() {
	return stockCount;
}
public void setStockCount(Double stockCount) {
	this.stockCount = stockCount;
}
public StockLedgerDTO getStockLedgerDTO() {
	return stockLedgerDTO;
}
public void setStockLedgerDTO(StockLedgerDTO stockLedgerDTO) {
	this.stockLedgerDTO = stockLedgerDTO;
}
public List<StockLedgerDTO> getStockLedgerDTOList() {
	return stockLedgerDTOList;
}
public void setStockLedgerDTOList(List<StockLedgerDTO> stockLedgerDTOList) {
	this.stockLedgerDTOList = stockLedgerDTOList;
}

}
