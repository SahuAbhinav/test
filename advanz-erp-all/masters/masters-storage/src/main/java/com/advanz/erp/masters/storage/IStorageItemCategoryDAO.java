package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.ItemCategoryEntity;

public interface IStorageItemCategoryDAO extends IStorageDAO<ItemCategoryEntity>{
    public List<ItemCategoryEntity> load();
    public List<ItemCategoryEntity> findById(Integer id);
    public List<ItemCategoryEntity> search(Integer id, String code, String name);
    public List<ItemCategoryEntity> findByNameAndCode(String name,String code);
    public List<ItemCategoryEntity> findByGroupId(Integer id);
    public boolean isInUsed(Integer id);
}
