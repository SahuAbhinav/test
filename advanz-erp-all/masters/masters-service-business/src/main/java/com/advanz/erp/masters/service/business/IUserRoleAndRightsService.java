package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.UserRoleAndRightsInputMessage;
import com.advanz.erp.masters.model.msg.UserRoleAndRightsOutputMessage;

public interface IUserRoleAndRightsService extends IAdvanzErpBaseSerivce
{

	public UserRoleAndRightsOutputMessage createUserRoleAndRight(UserRoleAndRightsInputMessage userRoleAndRightsInputMessage);
	
	public UserRoleAndRightsOutputMessage updateUserRoleAndRight(UserRoleAndRightsInputMessage userRoleAndRightsInputMessage);
	
	public UserRoleAndRightsOutputMessage deleteUserRoleAndRight(UserRoleAndRightsInputMessage userRoleAndRightsInputMessage);
	
	public UserRoleAndRightsOutputMessage findUserRoleAndRightById(UserRoleAndRightsInputMessage userRoleAndRightsInputMessage);
	
	public UserRoleAndRightsOutputMessage findAllUserRoleAndRight();
	
	public UserRoleAndRightsOutputMessage search(UserRoleAndRightsInputMessage userRoleAndRightsInputMessage);
	
	public UserRoleAndRightsOutputMessage findRoleAndRightByRoleId(UserRoleAndRightsInputMessage userRoleAndRightsInputMessage);
	
	public UserRoleAndRightsOutputMessage findSnoFromRoleAndRightByRoleId(UserRoleAndRightsInputMessage userRoleAndRightsInputMessage);
	
	public UserRoleAndRightsOutputMessage findRoleAndRightsByUserName(UserRoleAndRightsInputMessage userRoleAndRightsInputMessage);
	
//	public UserRoleAndRightsOutputMessage getNewUserRoleAndRightSeriesNo(UserRoleAndRightsInputMessage userRoleAndRightsInputMessage);

}
