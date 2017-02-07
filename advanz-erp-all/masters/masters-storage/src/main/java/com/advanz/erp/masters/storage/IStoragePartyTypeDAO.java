package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.PartyTypeEntity;

public interface IStoragePartyTypeDAO extends IStorageDAO<PartyTypeEntity>{
	public List<PartyTypeEntity> load();
    public List<PartyTypeEntity> findById(Integer id);
    public List<PartyTypeEntity> search(String partyTypeDesc, String partyTypeCode); 
    public List<PartyTypeEntity> findByName(String partyTypeDesc);
    public List<PartyTypeEntity> findByCode(String partyTypeCode);
    public boolean isInUsed(Integer id);
    
}
