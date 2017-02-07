package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.BlanketProductionMasterDTO;
import com.advanz.erp.masters.model.BulkFiberMasterDTO;
import com.advanz.erp.masters.model.criteria.BlanketProductionSearchCriteriaDTO;

@SuppressWarnings("serial")
public class BulkFiberMasterInputMessage extends AdvanzErpBaseInputMessage{
	private BulkFiberMasterDTO bulkFiberMasterDTO;

	public BulkFiberMasterDTO getBulkFiberMasterDTO() {
		return bulkFiberMasterDTO;
	}

	public void setBulkFiberMasterDTO(BulkFiberMasterDTO bulkFiberMasterDTO) {
		this.bulkFiberMasterDTO = bulkFiberMasterDTO;
	}
	
	
	
	
	

}
