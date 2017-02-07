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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;
@SuppressWarnings("serial")
@Entity
@Table(name="m_user")
public class UserMasterRoleEntity extends BaseEntity 
{
	@Id
	@GenericGenerator (name = "system-incr", strategy = "increment")
	@GeneratedValue( generator = "system-incr")
	
	@Column( name = "user_id")
	private Integer userId;
	
	@Column( name = "login_user"  )
	private String userLoginId;
	
	@Column( name = "user_password"  )
	private String userPassword;
	
	@Column( name = "user_full_name"  )
	private String userFullName;
	
	@Column( name = "active_flag"  )
	private Integer activeFlag;
	
	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinTable(name="m_user_role",
	joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="role_id"))

	private RoleMasterEntity roleMasterEntity ;
	
	
	
	public RoleMasterEntity getRoleMasterEntity() {
		return roleMasterEntity;
	}

	public void setRoleMasterEntity(RoleMasterEntity roleMasterEntity) {
		this.roleMasterEntity = roleMasterEntity;
	}

	@Override
	   public String toString()
	   {
		  return "UserMasterRoleDTO[userId="+userId+"userLoginId="+userLoginId+"userPassword="+userPassword+"userFullName="+userFullName+"activeFlag="+activeFlag+"+roleMasterEntity="+roleMasterEntity+"]"; 
	   }
	
}
