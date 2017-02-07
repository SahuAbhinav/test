package com.advanz.erp.client.http.authentication.util;

public class UserMenuOptions {

	private String moduleName;
	private String subModuleName;
	private String menuName;
	private boolean visibleFlag;
	private boolean addFlag;
	private boolean editFlag;
	private boolean deleteFlag;
	
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getSubModuleName() {
		return subModuleName;
	}
	public void setSubModuleName(String subModleName) {
		this.subModuleName = subModleName;
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
	
}
