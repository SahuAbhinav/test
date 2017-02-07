package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.ProfessionalTaxDTO;
import com.advanz.erp.masters.model.TdsMasterDTO;
@SuppressWarnings("serial")
public class TdsMasterOutputMessage extends AdvanzErpBaseOutputMessage{
	TdsMasterDTO tdsMasterDTO;
	List<TdsMasterDTO> TdsMasterDTOList;
	public TdsMasterDTO getTdsMasterDTO() {
		return tdsMasterDTO;
	}
	public void setTdsMasterDTO(TdsMasterDTO tdsMasterDTO) {
		this.tdsMasterDTO = tdsMasterDTO;
	}
	public List<TdsMasterDTO> getTdsMasterDTOList() {
		return TdsMasterDTOList;
	}
	public void setTdsMasterDTOList(List<TdsMasterDTO> tdsMasterDTOList) {
		TdsMasterDTOList = tdsMasterDTOList;
	}

	

}
