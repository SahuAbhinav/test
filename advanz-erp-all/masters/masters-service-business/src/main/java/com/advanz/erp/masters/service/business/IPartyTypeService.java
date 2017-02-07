package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.PartyTypeInputMessage;
import com.advanz.erp.masters.model.msg.PartyTypeOutMessage;

public interface IPartyTypeService extends IAdvanzErpBaseSerivce{

	
	public PartyTypeOutMessage createPartyType(PartyTypeInputMessage partyTypeInputMessage);
	
	public PartyTypeOutMessage updatePartyType(PartyTypeInputMessage partyTypeInputMessage);
	
	public PartyTypeOutMessage deletePartyType(PartyTypeInputMessage partyTypeInputMessage);
	
	public PartyTypeOutMessage findPartyTypeById(PartyTypeInputMessage partyTypeInputMessage);
	
	public PartyTypeOutMessage findAllPartyTypes();
	
	public PartyTypeOutMessage findPartyType(PartyTypeInputMessage partyTypeInputMessage);
	
	public PartyTypeOutMessage getErrorIfUsed(PartyTypeInputMessage partyTypeInputMessage);
	
}
