package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.RoleInputMessage;
import com.advanz.erp.masters.model.msg.RoleOutMessage;

public interface IRoleService  extends IAdvanzErpBaseSerivce{

	public RoleOutMessage addRole(RoleInputMessage RoleInputMessage);
	
	public RoleOutMessage updateRole(RoleInputMessage RoleInputMessage);
	
	public RoleOutMessage deleteRole(RoleInputMessage RoleInputMessage);
	
	public RoleOutMessage findRoleById(RoleInputMessage RoleInputMessage);
	
	public RoleOutMessage findRoleLastId();
	
	public RoleOutMessage findAllRoles();
	
	public RoleOutMessage findRoleBySearchCriteria(RoleInputMessage RoleInputMessage);

	public RoleOutMessage findAllModuleMenuData();
}
