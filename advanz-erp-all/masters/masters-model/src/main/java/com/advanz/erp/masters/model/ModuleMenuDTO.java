package com.advanz.erp.masters.model;

import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class ModuleMenuDTO extends BaseDTO {

	private int menuId ;
	
	private List<MenuRoleDTO> menuRoles;
	
	private String moduleName ;
	
	private String subModuleName ;

	private String menuName ;

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public List<MenuRoleDTO> getMenuRoles() {
		return menuRoles;
	}

	public void setMenuRoles(List<MenuRoleDTO> menuRoles) {
		this.menuRoles = menuRoles;
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

}
