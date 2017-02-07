package com.advanz.erp.masters.model.msg;

import java.sql.Timestamp;
import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.AnnealingOvenMasterDTO;

@SuppressWarnings("serial")
public class AnnealingOvenOutputMessage extends AdvanzErpBaseOutputMessage {
	private AnnealingOvenMasterDTO annealingOvenMasterDTO;
	private List<AnnealingOvenMasterDTO> annealingOvenMasterDTOList;
	private Timestamp lastAnnealingOvenDate;

	public Timestamp getLastAnnealingOvenDate() {
		return lastAnnealingOvenDate;
	}

	public void setLastAnnealingOvenDate(Timestamp lastAnnealingOvenDate) {
		this.lastAnnealingOvenDate = lastAnnealingOvenDate;
	}

	public AnnealingOvenMasterDTO getAnnealingOvenMasterDTO() {
		return annealingOvenMasterDTO;
	}

	public void setAnnealingOvenMasterDTO(
			AnnealingOvenMasterDTO annealingOvenMasterDTO) {
		this.annealingOvenMasterDTO = annealingOvenMasterDTO;
	}

	public List<AnnealingOvenMasterDTO> getAnnealingOvenMasterDTOList() {
		return annealingOvenMasterDTOList;
	}

	public void setAnnealingOvenMasterDTOList(
			List<AnnealingOvenMasterDTO> annealingOvenMasterDTOList) {
		this.annealingOvenMasterDTOList = annealingOvenMasterDTOList;
	}

}
