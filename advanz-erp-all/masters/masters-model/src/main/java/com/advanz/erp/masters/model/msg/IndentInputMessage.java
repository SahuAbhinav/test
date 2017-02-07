package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.IndentMasterDTO;
import com.advanz.erp.masters.model.IssueMasterDTO;
import com.advanz.erp.masters.model.criteria.IssueMasterSearchCriteriaDTO;

@SuppressWarnings("serial")
public class IndentInputMessage extends AdvanzErpBaseInputMessage {

	private IndentMasterDTO indentMasterDTO;

	public IndentMasterDTO getIndentMasterDTO() {
		return indentMasterDTO;
	}

	public void setIndentMasterDTO(IndentMasterDTO indentMasterDTO) {
		this.indentMasterDTO = indentMasterDTO;
	}

	

}
