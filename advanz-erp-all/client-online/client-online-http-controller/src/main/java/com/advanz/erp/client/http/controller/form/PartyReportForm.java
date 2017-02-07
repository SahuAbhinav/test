package com.advanz.erp.client.http.controller.form;

import com.advanz.erp.masters.model.CityDTO;
import com.advanz.erp.masters.model.PartyTypeDTO;
import com.advanz.erp.masters.model.StateDTO;

public class PartyReportForm {
	private CityDTO cityDTO;
	private StateDTO stateDTO;
	private PartyTypeDTO partyTypeDTO;

	public CityDTO getCityDTO() {
		return cityDTO;
	}

	public void setCityDTO(CityDTO cityDTO) {
		this.cityDTO = cityDTO;
	}

	public StateDTO getStateDTO() {
		return stateDTO;
	}

	public void setStateDTO(StateDTO stateDTO) {
		this.stateDTO = stateDTO;
	}

	public PartyTypeDTO getPartyTypeDTO() {
		return partyTypeDTO;
	}

	public void setPartyTypeDTO(PartyTypeDTO partyTypeDTO) {
		this.partyTypeDTO = partyTypeDTO;
	}

}
