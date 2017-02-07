package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.PartyInputMessage;
import com.advanz.erp.masters.model.msg.PartyOutMessage;

public interface IPartyService extends IAdvanzErpBaseSerivce{

	
	public PartyOutMessage createParty(PartyInputMessage partyInputMessage);
	
	public PartyOutMessage updateParty(PartyInputMessage partyInputMessage);
	
	public PartyOutMessage deleteParty(PartyInputMessage partyInputMessage);
	
	public PartyOutMessage findPartyById(PartyInputMessage partyInputMessage);
	
	public PartyOutMessage findAllPartys();
	
	public PartyOutMessage findParty(PartyInputMessage partyInputMessage);
	
	public PartyOutMessage checkBeforeRemove(PartyInputMessage partyInputMessage);
	
	public PartyOutMessage findDebtorPartyList();
	public PartyOutMessage findDebtorPartyShortInfoList();
	
	public PartyOutMessage findCreditorPartyList();
	public PartyOutMessage findPartyNameAndId();
	public PartyOutMessage preloadedPartys();
	public String getEmailId(String billNo, String flag);
}
