package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.StateEntity;

public interface IStorageStateDAO extends IStorageDAO<StateEntity>{
    public List<StateEntity> load();
    public <E> List<StateEntity> findById(E id);
    public List<StateEntity> search(String stateName,String stateCode,String zoneName);
	List<StateEntity> findStateByZoneId(Integer zoneId);
	

}
