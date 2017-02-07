package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.BillDetailDTO;
import com.advanz.erp.masters.model.ProformaDetailDTO;

public class ProformaDetailInputMessage extends AdvanzErpBaseInputMessage{
	private ProformaDetailDTO proformaDetailDTO;

	public ProformaDetailDTO getProformaDetailDTO() {
		return proformaDetailDTO;
	}

	public void setProformaDetailDTO(ProformaDetailDTO proformaDetailDTO) {
		this.proformaDetailDTO = proformaDetailDTO;
	} 

	
}
