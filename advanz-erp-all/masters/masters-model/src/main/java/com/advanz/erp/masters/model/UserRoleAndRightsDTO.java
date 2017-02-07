package com.advanz.erp.masters.model;

import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class UserRoleAndRightsDTO  extends BaseDTO {
	private Integer sno;
	
	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}
	private String loginUserName;
		
	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	private Integer roleId;
	
	private String roleName;
	
	private String moduleName;
	
	private String subModuleName ;
	
	private String menuName ;
	
	private boolean visibleFlag;
	
	private boolean addFlag ; 
	
	private boolean editFlag ;
	
	private boolean deleteFlag ;

	private List<UserDTO> users;

	private Integer menuId;
		
	
	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	private UserMasterRoleDTO userMasterRoleDTO;
	
	public UserMasterRoleDTO getUserMasterRoleDTO() {
		return userMasterRoleDTO;
	}

	public void setUserMasterRoleDTO(UserMasterRoleDTO userMasterRoleDTO) {
		this.userMasterRoleDTO = userMasterRoleDTO;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getSubModuleName() {
		return subModuleName;
	}

	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public boolean isVisibleFlag() {
		return visibleFlag;
	}

	public void setVisibleFlag(boolean visibleFlag) {
		this.visibleFlag = visibleFlag;
	}

	public boolean isAddFlag() {
		return addFlag;
	}

	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}

	public boolean isEditFlag() {
		return editFlag;
	}

	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}
	@Override 
	public String toString() {
	return "USER_ROLE_ENTITY=[menuId="+menuId+"roleId"+roleId+"deleteFlag="+deleteFlag+"editFlag"+editFlag+"addFlag"+addFlag+"]";
}
}
