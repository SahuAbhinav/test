package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.UserRoleEntity;

public interface IStorageUserRoleDAO extends IStorageDAO<UserRoleEntity>
{
 public List<UserRoleEntity> load();
 public List<UserRoleEntity> findUserRoleByRoleID(Integer roleId);
}
