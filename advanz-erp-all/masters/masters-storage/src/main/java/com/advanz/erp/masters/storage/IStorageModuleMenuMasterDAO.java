package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.BatchEntity;
import com.advanz.erp.masters.entity.jpa.BillEntity;
import com.advanz.erp.masters.entity.jpa.ModuleMenuMasterEntity;

public interface IStorageModuleMenuMasterDAO extends IStorageDAO<ModuleMenuMasterEntity>{
	 	public List<ModuleMenuMasterEntity> load();

	    public List<ModuleMenuMasterEntity> findModuleMasterByName(String moduleName,String loginUserName);
	    
	    public List<ModuleMenuMasterEntity> findModuleMasterBySubMenuName(String subModuleName,String loginUserName);
	    public List<ModuleMenuMasterEntity> getReportLinkList(String loginUserName);
	    
	    public List<ModuleMenuMasterEntity> search(String loginUserName,String menuName,String subMenuName,String description);
}
