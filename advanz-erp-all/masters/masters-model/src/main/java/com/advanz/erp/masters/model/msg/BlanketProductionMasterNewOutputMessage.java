package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.BlanketProductionMasterNewDTO;

public class BlanketProductionMasterNewOutputMessage extends
		AdvanzErpBaseOutputMessage {

	private List<BlanketProductionMasterNewDTO> BlanketProductionMasterNewDTOList;

	public List<BlanketProductionMasterNewDTO> getBlanketProductionMasterNewDTOList() {
		return BlanketProductionMasterNewDTOList;
	}

	public void setBlanketProductionMasterNewDTOList(
			List<BlanketProductionMasterNewDTO> blanketProductionMasterNewDTOList) {
		BlanketProductionMasterNewDTOList = blanketProductionMasterNewDTOList;
	}

}
