package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.UserMasterRoleEntity;

public interface IStorageUserMasterRoleDAO extends IStorageDAO<UserMasterRoleEntity>
{
  public List<UserMasterRoleEntity> load();
  public List<UserMasterRoleEntity> searchUserMaster(String fullName,String roleName);
  public List<UserMasterRoleEntity> findLastUserMasterId();
  public List<UserMasterRoleEntity> getUserMasterRoleById(Integer userId);
  public List<UserMasterRoleEntity> checkUserDuplicateEntry(String userFullName);
  public List<UserMasterRoleEntity> getUserInfo(String userName);
}
