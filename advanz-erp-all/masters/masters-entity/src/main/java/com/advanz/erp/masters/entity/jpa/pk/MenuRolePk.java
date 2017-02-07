/*package com.advanz.erp.masters.entity.jpa.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.advanz.erp.masters.entity.jpa.ModuleMenuEntity;
import com.advanz.erp.masters.entity.jpa.RoleEntity;


@Embeddable
//@SuppressWarnings("serial")
public class MenuRolePk  implements Serializable {
	
	private RoleEntity role;
	
	private ModuleMenuEntity moduleMenu;
	
	@ManyToOne
	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}
	
	@ManyToOne
	public ModuleMenuEntity getModuleMenu() {
		return moduleMenu;
	}

	public void setModuleMenu(ModuleMenuEntity moduleMenu) {
		this.moduleMenu = moduleMenu;
	}
	
	
	
	
	private Integer roleId;
	
	
	private Integer menuId;
	
	public MenuRolePk() {
		
	}
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		
		if(!(obj instanceof MenuRolePk)){
		
			MenuRolePk userRoleAndRightsPk = (MenuRolePk) obj;

			if (!userRoleAndRightsPk.getRoleId().equals(roleId)) {
				return false;
			}

			if (!userRoleAndRightsPk.getMenuId().equals(menuId)) {
				return false;
			}

			return true;
    }

    return false;
    }
	
	public int hashCode() { 
        return roleId + menuId; 
    }
}
*/