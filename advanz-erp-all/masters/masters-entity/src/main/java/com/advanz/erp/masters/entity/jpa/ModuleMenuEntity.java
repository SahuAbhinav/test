/*package com.advanz.erp.masters.entity.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table( name = "m_module_menu_master" )
@Access(AccessType.FIELD)
public class ModuleMenuEntity implements Serializable{


	@Id
	@GenericGenerator( name = "menuIdGenerator"  ,strategy = "increment")
	@GeneratedValue ( generator = "menuIdGenerator")
	@Column( name = "menu_id")
	private int menuId ;
	
	@OneToMany(mappedBy="pk.moduleMenu")
    private List<MenuRoleEntity> menuRoleEntities;
	
	@Column( name = "module_name" )
	private String moduleName ;
	
	@Column( name = "sub_module_name" )
	private String subModuleName ;
	
	@Column( name = "menu_name" )
	private String menuName ;
	
	
	public List<MenuRoleEntity> getMenuRoleEntities() {
		return menuRoleEntities;
	}

	public void setMenuRoleEntities(List<MenuRoleEntity> menuRoleEntities) {
		this.menuRoleEntities = menuRoleEntities;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
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
*/