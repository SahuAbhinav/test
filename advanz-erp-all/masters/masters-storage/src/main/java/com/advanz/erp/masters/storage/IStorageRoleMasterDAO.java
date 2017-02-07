package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;

import com.advanz.erp.masters.entity.jpa.RoleMasterEntity;

public interface IStorageRoleMasterDAO extends IStorageDAO<RoleMasterEntity> 
 {
	public List<RoleMasterEntity> load();
	public List<RoleMasterEntity> searchByName(String name);
	public List<RoleMasterEntity> findByRoleNames(String roleName);
	public List<RoleMasterEntity> findByLastId();

 }
