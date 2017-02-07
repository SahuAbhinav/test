package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.AnnealingOvenMasterDTO;
import com.advanz.erp.masters.model.CapativeConsuptionDTO;

@SuppressWarnings("serial")
public class CapativeConsuptionOutputMessage extends AdvanzErpBaseOutputMessage {
private CapativeConsuptionDTO capativeConsuptionDTO;
private List<CapativeConsuptionDTO> capativeConsuptionDTOList;
public CapativeConsuptionDTO getCapativeConsuptionDTO() {
	return capativeConsuptionDTO;
}
public void setCapativeConsuptionDTO(CapativeConsuptionDTO capativeConsuptionDTO) {
	this.capativeConsuptionDTO = capativeConsuptionDTO;
}
public List<CapativeConsuptionDTO> getCapativeConsuptionDTOList() {
	return capativeConsuptionDTOList;
}
public void setCapativeConsuptionDTOList(
		List<CapativeConsuptionDTO> capativeConsuptionDTOList) {
	this.capativeConsuptionDTOList = capativeConsuptionDTOList;
}

}

