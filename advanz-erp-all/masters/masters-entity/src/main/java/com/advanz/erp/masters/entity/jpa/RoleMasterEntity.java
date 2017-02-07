package com.advanz.erp.masters.entity.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;
@SuppressWarnings("serial")
@Entity
@Table(name = "m_role_master")
public class RoleMasterEntity extends BaseEntity implements Serializable{

	@Id
	@GenericGenerator(name = "roleIdGenerator", strategy = "increment")
	@GeneratedValue(generator = "roleIdGenerator")
	@Column(name = "role_id")
	private Integer roleId;
	@Column(name = "role_name")
	private String roleName;
	
	@Column(name = "hot_key_active")
	private Boolean hotKeyActive=false; 
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

	public Boolean getHotKeyActive() {
		return hotKeyActive;
	}

	public void setHotKeyActive(Boolean hotKeyActive) {
		this.hotKeyActive = hotKeyActive;
	}

	@Override
	public String toString()
	 {
	  return "RoleMasterDTO[roleId="+roleId+"roleName="+roleName+"]"; 
	 }
	}
