package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.UserMasterRoleInputMassage;
import com.advanz.erp.masters.model.msg.UserMasterRoleOutputMessage;

public interface IUserMasterRoleService extends IAdvanzErpBaseSerivce
{
  public UserMasterRoleOutputMessage findAllUserMaster();
  public UserMasterRoleOutputMessage addUserMasterForm(UserMasterRoleInputMassage userMasterRoleInputMassage);
  public UserMasterRoleOutputMessage searchUserMasterForm(UserMasterRoleInputMassage userMasterRoleInputMassage);
  public UserMasterRoleOutputMessage findUserRoleId();
  public UserMasterRoleOutputMessage updateUserMasterRole(UserMasterRoleInputMassage userMasterRoleInputMassage);
  public UserMasterRoleOutputMessage findUserMasterRoleById(UserMasterRoleInputMassage userMasterRoleInputMassage);
  public UserMasterRoleOutputMessage deleteUserMasterRole(UserMasterRoleInputMassage userMasterRoleInputMassage);
  public UserMasterRoleOutputMessage getUserInfo(UserMasterRoleInputMassage userMasterRoleInputMassage);
}
