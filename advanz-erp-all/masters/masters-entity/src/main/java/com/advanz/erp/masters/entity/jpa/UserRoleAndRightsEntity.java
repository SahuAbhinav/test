package com.advanz.erp.masters.entity.jpa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table( name = "m_role_rights" )
public class UserRoleAndRightsEntity extends BaseEntity{
	
	@Id
	@GenericGenerator( name = "roleIdGenerator"  ,strategy = "increment")
	@GeneratedValue ( generator = "roleIdGenerator")
	@Column ( name = "sno" )
	private Integer sno;
	
	@Column ( name = "role_id" )
	private Integer roleId;
	
	@Column ( name = "menu_id" )
	private Integer menuId;
	
	
	@Column ( name = "visible_flag" )
	//@Enumerated(EnumType.ORDINAL)
	private boolean visibleFlag;
	
	@Column ( name = "add_flag" )
	//@Enumerated(EnumType.ORDINAL)
	private boolean addFlag ; 
	
	@Column ( name = "edit_flag" )
	//@Enumerated(EnumType.ORDINAL)
	private boolean editFlag ;
	
	@Column ( name = "delete_flag" )
	//@Enumerated(EnumType.ORDINAL)
	private boolean deleteFlag ;

	
	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public boolean getVisibleFlag() {
		return visibleFlag;
	}

	public void setVisibleFlag(boolean visibleFlag) {
		this.visibleFlag = visibleFlag;
	}

	public boolean getAddFlag() {
		return addFlag;
	}

	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}

	public boolean getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	public boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	//@Override
	public Integer getUid() {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public void setUid(Integer uid) {
		// TODO Auto-generated method stub
		
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	@Override 
	public String toString() {
	return "USER_ROLE_ENTITY=[menuId="+menuId+"roleId"+roleId+"]";
}
}
