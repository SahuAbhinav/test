package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.BillDetailDTO;
import com.advanz.erp.masters.model.ProformaDetailDTO;

public class ProformaDetailOutMessage extends AdvanzErpBaseOutputMessage{
	private ProformaDetailDTO proformaDetailDTO;
private List<ProformaDetailDTO> proformaDetailDTOList;
public ProformaDetailDTO getProformaDetailDTO() {
	return proformaDetailDTO;
}
public void setProformaDetailDTO(ProformaDetailDTO proformaDetailDTO) {
	this.proformaDetailDTO = proformaDetailDTO;
}
public List<ProformaDetailDTO> getProformaDetailDTOList() {
	return proformaDetailDTOList;
}
public void setProformaDetailDTOList(
		List<ProformaDetailDTO> proformaDetailDTOList) {
	this.proformaDetailDTOList = proformaDetailDTOList;
}




}
