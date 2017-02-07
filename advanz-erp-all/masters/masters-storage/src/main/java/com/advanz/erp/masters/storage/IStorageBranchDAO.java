package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.entity.jpa.ItemCategoryEntity;

public interface IStorageBranchDAO extends IStorageDAO<BranchEntity>{
    public List<BranchEntity> load();
    public List<BranchEntity> findById(Integer uid);
    public List<BranchEntity> search(String branchName, String invoice);
    public List<BranchEntity> findByName(String branch);
    public List<BranchEntity> findByCode(String invoiceCode);
    public boolean isInUsed(Integer id);
    public List preloaded();
}
