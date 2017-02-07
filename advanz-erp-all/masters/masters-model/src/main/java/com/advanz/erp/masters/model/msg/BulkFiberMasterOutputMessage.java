package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.BlanketProductionMasterDTO;
import com.advanz.erp.masters.model.BulkFiberMasterDTO;

public class BulkFiberMasterOutputMessage extends AdvanzErpBaseOutputMessage {

//	private BlanketProductionMasterDTO BlanketProductionMasterDTO;
	
	private List<BulkFiberMasterDTO> bulkFiberMasterDTOList;

	public List<BulkFiberMasterDTO> getBulkFiberMasterDTOList() {
		return bulkFiberMasterDTOList;
	}

	public void setBulkFiberMasterDTOList(
			List<BulkFiberMasterDTO> bulkFiberMasterDTOList) {
		this.bulkFiberMasterDTOList = bulkFiberMasterDTOList;
	}

	

	

}
