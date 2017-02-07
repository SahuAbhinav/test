/*package com.advanz.erp.masters.entity.jpa;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.advanz.erp.common.entity.jpa.BaseEntity;
import com.advanz.erp.masters.entity.jpa.pk.MenuRolePk;

@Entity
@Table(name = "m_role_rights")
@AssociationOverrides({
@AssociationOverride(name = "pk.role", joinColumns = @JoinColumn(name = "role_id")),
@AssociationOverride(name = "pk.moduleMenu", joinColumns = @JoinColumn(name = "menu_id"))
        })
@Access(AccessType.FIELD)
public class MenuRoleEntity extends BaseEntity {
	
	private MenuRolePk pk = new MenuRolePk(); 

	private boolean visibleFlag;

	private boolean addFlag;

	private boolean editFlag;

	private boolean deleteFlag;

	public MenuRoleEntity(){}
	
	@Access(AccessType.PROPERTY)
	@Column(name = "visible_flag" ,columnDefinition="default 1")
	public boolean getVisibleFlag() {
		return visibleFlag;
	}

	public void setVisibleFlag(boolean visibleFlag) {
		this.visibleFlag = visibleFlag;
	}

	@Access(AccessType.PROPERTY)
	@Column(name = "add_flag" ,columnDefinition="default 1")
	public boolean getAddFlag() {
		return addFlag;
	}

	@Access(AccessType.PROPERTY)
	@EmbeddedId
	public MenuRolePk getPk() {
		return pk;
	}

	public void setPk(MenuRolePk pk) {
		this.pk = pk;
	}

	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}

	@Access(AccessType.PROPERTY)
	@Column(name = "edit_flag" ,columnDefinition="default 1")
	public boolean getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	@Access(AccessType.PROPERTY)
	@Column(name = "delete_flag" ,columnDefinition="default 1")
	public boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	
	@Transient
	public ModuleMenuEntity getModuleMenu() {
		return pk.getModuleMenu();
	}

	public void setModuleMenu(ModuleMenuEntity moduleMenu) {
		this.pk.setModuleMenu(moduleMenu);
	}
	
	@Transient
	public RoleEntity getRole() {
		return pk.getRole();
	}

	public void setRole(RoleEntity role) {
		this.pk.setRole(role);
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}



}
*/