package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.ItemGroupEntity;

public interface IStorageItemGroupDAO extends IStorageDAO<ItemGroupEntity>{
    public List<ItemGroupEntity> load();
    public List<ItemGroupEntity> findById(Integer id);
    public List<ItemGroupEntity> search(String name, String code);
    public List<ItemGroupEntity> loadNameIdForItemCategory();
    public List<ItemGroupEntity> findByItemGroupFlagId(Integer itemGroupFlagId);
}
