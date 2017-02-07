package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;


@Entity
@Table(name = "M_STATE")
public class StateEntity  extends BaseEntity {
	
	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="state_id")
	private Integer stateId;
	
	@Column(name="state_code")
	private String stateCode;
	
	@Column(name="state_name")
	private String stateName;
	
	
	@ManyToOne
	@JoinColumn(name="zone_id")
	private ZoneEntity zoneEntity;
	
	@Column(name="description")
	private String description;
	
	
	
//	@Override
	public Integer getUid() {
		// TODO Auto-generated method stub
	  return stateId;
	}
	
//	@Override
	public void setUid(Integer uid) {
		// TODO Auto-generated method stub
		stateId=uid;
		
		
	}

	public ZoneEntity getZoneEntity() {
		return zoneEntity;
	}

	public void setZoneEntity(ZoneEntity zoneEntity) {
		this.zoneEntity = zoneEntity;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
