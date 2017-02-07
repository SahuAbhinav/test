package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.ModuleMenuMasterDTO;

public class ModuleMenuMasterInputMessage extends AdvanzErpBaseInputMessage{
	private ModuleMenuMasterDTO moduleMenuMasterDTO;

	public ModuleMenuMasterDTO getModuleMenuMasterDTO() {
		return moduleMenuMasterDTO;
	}

	public void setModuleMenuMasterDTO(ModuleMenuMasterDTO moduleMenuMasterDTO) {
		this.moduleMenuMasterDTO = moduleMenuMasterDTO;
	}

}
