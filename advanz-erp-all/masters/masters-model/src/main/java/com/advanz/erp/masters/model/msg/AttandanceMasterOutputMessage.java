package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.AttandanceMasterDTO;

public class AttandanceMasterOutputMessage extends AdvanzErpBaseOutputMessage {

//	private BlanketProductionMasterDTO BlanketProductionMasterDTO;
	
	private List<AttandanceMasterDTO> attandanceMasterDTOList;

	public List<AttandanceMasterDTO> getAttandanceMasterDTOList() {
		return attandanceMasterDTOList;
	}

	public void setAttandanceMasterDTOList(
			List<AttandanceMasterDTO> attandanceMasterDTOList) {
		this.attandanceMasterDTOList = attandanceMasterDTOList;
	}


}
