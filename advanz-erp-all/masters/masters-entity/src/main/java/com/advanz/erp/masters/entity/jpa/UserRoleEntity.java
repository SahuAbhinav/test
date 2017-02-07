package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;


@Entity
@Table( name = "m_user_role" )
@SuppressWarnings("serial")
public class UserRoleEntity extends BaseEntity {
		
		@Id
		@GenericGenerator( name = "snoGenerator"  ,strategy = "increment")
		@GeneratedValue ( generator = "snoGenerator")
		@Column ( name = "sno" )
		private Integer sno;
		
		@Column ( name = "role_id" )
		private Integer roleId;
		
		@Column ( name = "user_id" )
		private Integer userId;
		
		
		
		
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

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		
	

		@Override 
		public String toString() {
		return "USER_ROLE_ENTITY=[sno="+sno+"roleId"+roleId+"userId"+userId+"]";
	}
}
