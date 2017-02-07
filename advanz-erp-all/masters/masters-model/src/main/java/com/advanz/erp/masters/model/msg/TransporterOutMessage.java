package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.TransporterDTO;

public class TransporterOutMessage extends AdvanzErpBaseOutputMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7652311966136682774L;
	
	private TransporterDTO transporterDTO;
	
	private List<TransporterDTO> transporterDTOList;
	
	public List<TransporterDTO> getTransporterDTOList() {
		return transporterDTOList;
	}

	public void setTransporterDTOList(List<TransporterDTO> transporterDTOList) {
		this.transporterDTOList = transporterDTOList;
	}

	public TransporterDTO getTransporterDTO() {
		return transporterDTO;
	}

	public void setTransporterDTO(TransporterDTO transporterDTO) {
		this.transporterDTO = transporterDTO;
	}
}
