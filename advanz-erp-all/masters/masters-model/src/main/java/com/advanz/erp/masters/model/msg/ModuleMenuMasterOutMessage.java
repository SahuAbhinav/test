package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.BillDetailDTO;
import com.advanz.erp.masters.model.ModuleMenuMasterDTO;

public class ModuleMenuMasterOutMessage extends AdvanzErpBaseOutputMessage {
	private ModuleMenuMasterDTO moduleMenuMasterDTO;
	private List<ModuleMenuMasterDTO> moduleMenuMasterDTOList;

	public ModuleMenuMasterDTO getModuleMenuMasterDTO() {
		return moduleMenuMasterDTO;
	}

	public void setModuleMenuMasterDTO(ModuleMenuMasterDTO moduleMenuMasterDTO) {
		this.moduleMenuMasterDTO = moduleMenuMasterDTO;
	}

	public List<ModuleMenuMasterDTO> getModuleMenuMasterDTOList() {
		return moduleMenuMasterDTOList;
	}

	public void setModuleMenuMasterDTOList(
			List<ModuleMenuMasterDTO> moduleMenuMasterDTOList) {
		this.moduleMenuMasterDTOList = moduleMenuMasterDTOList;
	}
}
