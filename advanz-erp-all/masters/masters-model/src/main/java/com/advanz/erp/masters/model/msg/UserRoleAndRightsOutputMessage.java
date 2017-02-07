package com.advanz.erp.masters.model.msg;

import java.util.ArrayList;
import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.UserRoleAndRightsDTO;
@SuppressWarnings("serial")
public class UserRoleAndRightsOutputMessage extends AdvanzErpBaseOutputMessage
{ 
 private List<Integer>  menuIdList;
 
 
public List<Integer> getMenuIdList() {
	return menuIdList;
}

public void setMenuIdList(List<Integer> menuIdList) {
	this.menuIdList = menuIdList;
}

private UserRoleAndRightsDTO  userRoleAndRightsDTO;
 private List<UserRoleAndRightsDTO> userRoleAndRightsDTOList;
 
public UserRoleAndRightsDTO getUserRoleAndRightsDTO() {
	return userRoleAndRightsDTO;
}

public void setUserRoleAndRightsDTO(UserRoleAndRightsDTO userRoleAndRightsDTO) {
	this.userRoleAndRightsDTO = userRoleAndRightsDTO;
}


public List<UserRoleAndRightsDTO> getUserRoleAndRightsDTOList() {
	return userRoleAndRightsDTOList;
}

public void setUserRoleAndRightsDTOList(
		List<UserRoleAndRightsDTO> userRoleAndRightsDTOList) {
	this.userRoleAndRightsDTOList = userRoleAndRightsDTOList;
}
 
 
}
