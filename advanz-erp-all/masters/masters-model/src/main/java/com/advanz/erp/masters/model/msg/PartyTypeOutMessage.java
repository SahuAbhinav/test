package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.PartyTypeDTO;

public class PartyTypeOutMessage extends AdvanzErpBaseOutputMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7652311966136682774L;
	
	private PartyTypeDTO partyTypeDTO;	
	private List<PartyTypeDTO> partyTypeDTOList;
	
	public PartyTypeDTO getPartyTypeDTO() {
		return partyTypeDTO;
	}
	public void setPartyTypeDTO(PartyTypeDTO partyTypeDTO) {
		this.partyTypeDTO = partyTypeDTO;
	}
	public List<PartyTypeDTO> getPartyTypeDTOList() {
		return partyTypeDTOList;
	}
	public void setPartyTypeDTOList(List<PartyTypeDTO> partyTypeDTOList) {
		this.partyTypeDTOList = partyTypeDTOList;
	}
}
