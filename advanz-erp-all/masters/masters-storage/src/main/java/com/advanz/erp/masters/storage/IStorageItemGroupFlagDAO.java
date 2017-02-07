package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.BatchEntity;
import com.advanz.erp.masters.entity.jpa.ItemGroupFlagEntity;

public interface IStorageItemGroupFlagDAO extends IStorageDAO<ItemGroupFlagEntity>{
	  public List<ItemGroupFlagEntity> load();
	  public List<ItemGroupFlagEntity> findIdByName(String itemGroupFlagName) ;
	  public List<ItemGroupFlagEntity> findGroupFlagByItemId(Integer itemId);
}
