package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class MenuRoleDTO  extends BaseDTO {

	private boolean visibleFlag;
	
	private boolean addFlag ; 
	
	private boolean editFlag ;
	
	private boolean deleteFlag ;

	private RoleDTO role;
	
	private ModuleMenuDTO moduleMenu;
	

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

	public ModuleMenuDTO getModuleMenu() {
		return moduleMenu;
	}

	public void setModuleMenu(ModuleMenuDTO moduleMenu) {
		this.moduleMenu = moduleMenu;
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



}
