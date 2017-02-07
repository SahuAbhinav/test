package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;


import com.advanz.erp.masters.entity.jpa.UserRoleAndRightsEntity;

public interface IStorageRoleAndRightsDAO extends IStorageDAO<UserRoleAndRightsEntity>{

	List<UserRoleAndRightsEntity> findByRoleName(String roleName);

	List<UserRoleAndRightsEntity> findAllRoleAndRights();

	List<Integer> findRoleAndRightsById(Integer id);
	
	List<UserRoleAndRightsEntity> findRoleAndRightsByRoleId(Integer id);
	
	
	List<Integer> findSnoFromRoleAndRightsByRoleId(Integer id);
	
	public List<UserRoleAndRightsEntity> findRoleAndRightsByUserName(String userName);
}
