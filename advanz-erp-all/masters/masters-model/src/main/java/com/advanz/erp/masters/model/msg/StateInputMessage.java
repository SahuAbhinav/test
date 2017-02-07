package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.StateDTO;

public class StateInputMessage extends AdvanzErpBaseInputMessage{

	private StateDTO stateDTO;

	/**
	 * @return the stateDTO
	 */
	public StateDTO getStateDTO() {
		return stateDTO;
	}

	/**
	 * @param countryDTO the countryDTO to set
	 */
	public void setStateDTO(StateDTO stateDTO) {
		this.stateDTO = stateDTO;
	}
	

}
