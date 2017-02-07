package com.advanz.erp.masters.model.criteria;

import com.advanz.erp.common.model.BaseDTO;
import com.advanz.erp.masters.model.PartyTypeDTO;

public class PartySearchCriteriaDTO extends BaseDTO{
	private String partyName;
	
	private String balanceType;
	private PartyTypeDTO partyTypeDTO;
	
	
	public PartyTypeDTO getPartyTypeDTO() {
		return partyTypeDTO;
	}
	public void setPartyTypeDTO(PartyTypeDTO partyTypeDTO) {
		this.partyTypeDTO = partyTypeDTO;
	}
	public String getBalanceType() {
		return balanceType;
	}
	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	
	

}
