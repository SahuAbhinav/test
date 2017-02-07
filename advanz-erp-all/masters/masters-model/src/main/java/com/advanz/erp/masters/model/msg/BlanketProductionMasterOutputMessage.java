package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.BlanketProductionMasterDTO;

public class BlanketProductionMasterOutputMessage extends AdvanzErpBaseOutputMessage {

//	private BlanketProductionMasterDTO BlanketProductionMasterDTO;
	
	private List<BlanketProductionMasterDTO> BlanketProductionMasterDTOList;

	

	public List<BlanketProductionMasterDTO> getBlanketProductionMasterDTOList() {
		return BlanketProductionMasterDTOList;
	}

	public void setBlanketProductionMasterDTOList(
			List<BlanketProductionMasterDTO> blanketProductionMasterDTOList) {
		BlanketProductionMasterDTOList = blanketProductionMasterDTOList;
	}


}
