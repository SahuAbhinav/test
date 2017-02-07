package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.BillDetailDTO;

public class BillDetailOutMessage extends AdvanzErpBaseOutputMessage{
private BillDetailDTO billDetailDTO;
private List<BillDetailDTO> billDetailDTOList;
public BillDetailDTO getBillDetailDTO() {
	return billDetailDTO;
}
public void setBillDetailDTO(BillDetailDTO billDetailDTO) {
	this.billDetailDTO = billDetailDTO;
}
public List<BillDetailDTO> getBillDetailDTOList() {
	return billDetailDTOList;
}
public void setBillDetailDTOList(List<BillDetailDTO> billDetailDTOList) {
	this.billDetailDTOList = billDetailDTOList;
}



}
