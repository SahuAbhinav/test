package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.PartyEntity;

public interface IStoragePartyDAO extends IStorageDAO<PartyEntity>{
	public List<PartyEntity> load();
    public List<PartyEntity> findById(Integer id);
    public List<PartyEntity> search(String partyDesc, String partyCode,Integer partyTypeId,String balanceType); 
    public List<PartyEntity> findByName(String partyDesc);
    public List<PartyEntity> findByCode(String partyCode);
    public boolean isInUsed(Integer id);
    public List<PartyEntity> findDebtorPartyList();
    public List<PartyEntity> findDebtorPartyShortInfoList();
    public List<PartyEntity> findCreditorPartyList();
    public List<PartyEntity> loadPartyNameAndId();
    
    public List<PartyEntity> preLoad();
    
    public String getEmailId(String billNo,String flag);
}
