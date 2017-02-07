package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.UserRoleInputMessage;
import com.advanz.erp.masters.model.msg.UserRoleOutputMessage;

public interface IUserRoleService extends IAdvanzErpBaseSerivce {
	public UserRoleOutputMessage findAllRecord();

	public UserRoleOutputMessage addNewUserRecord(UserRoleInputMessage userRoleInputMessage);

	public UserRoleOutputMessage updateUserRecord(UserRoleInputMessage userRoleInputMessage);
	
	public UserRoleOutputMessage deleteUserRecord(UserRoleInputMessage userRoleInputMessage);
	
	public UserRoleOutputMessage findUerRoleByRoleId(UserRoleInputMessage userRoleInputMessage);
}
