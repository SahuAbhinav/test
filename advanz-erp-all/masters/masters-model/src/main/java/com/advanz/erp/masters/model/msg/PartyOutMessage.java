package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.PartyDTO;

public class PartyOutMessage extends AdvanzErpBaseOutputMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7652311966136682774L;
	
	private PartyDTO partyDTO;	
	private List<PartyDTO> partyDTOList;
	
	public PartyDTO getPartyDTO() {
		return partyDTO;
	}
	public void setPartyDTO(PartyDTO partyDTO) {
		this.partyDTO = partyDTO;
	}
	public List<PartyDTO> getPartyDTOList() {
		return partyDTOList;
	}
	public void setPartyDTOList(List<PartyDTO> partyDTOList) {
		this.partyDTOList = partyDTOList;
	}
}
