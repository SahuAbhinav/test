package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.IssueTypeMasterEntity;

public interface IStorageIssueTypeMasterDAO extends IStorageDAO<IssueTypeMasterEntity>{
	  public List<IssueTypeMasterEntity> load();
	
	  
	  public List<IssueTypeMasterEntity> getIssueBySno(Integer sno);
}
