package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.MasterFormulaMasterDTO;
@SuppressWarnings("serial")
public class MasterFormulaMasterOutputMessage extends AdvanzErpBaseOutputMessage {
	private List<MasterFormulaMasterDTO> formulaMasterDTOList;
	private MasterFormulaMasterDTO masterFormulaMasterDTO;

	public List<MasterFormulaMasterDTO> getFormulaMasterDTOList() {
		return formulaMasterDTOList;
	}

	public void setFormulaMasterDTOList(
			List<MasterFormulaMasterDTO> formulaMasterDTOList) {
		this.formulaMasterDTOList = formulaMasterDTOList;
	}

	public MasterFormulaMasterDTO getMasterFormulaMasterDTO() {
		return masterFormulaMasterDTO;
	}

	public void setMasterFormulaMasterDTO(
			MasterFormulaMasterDTO masterFormulaMasterDTO) {
		this.masterFormulaMasterDTO = masterFormulaMasterDTO;
	}

}
