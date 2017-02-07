package com.advanz.erp.masters.storage;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.MasterFormulaMasterEntity;

public interface IStorageMasterFormulaDAO extends IStorageDAO<MasterFormulaMasterEntity>
{
 public List<MasterFormulaMasterEntity> load();  
 public List<MasterFormulaMasterEntity> search(String itemName, String itemCode);
 public List<MasterFormulaMasterEntity> checkDuplicateEntry(Integer finishedItemId);
}
