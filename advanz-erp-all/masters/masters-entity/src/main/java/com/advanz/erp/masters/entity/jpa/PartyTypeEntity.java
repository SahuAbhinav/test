package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name="m_party_type")
public class PartyTypeEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7732743896497028690L;

	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name = "party_type_id", unique = true, updatable = false, length=11)
	private Integer partyTypeId;
	@Column(name="party_type_desc", length=55)
	private String partyTypeDesc;
	@Column(name="party_type_code", length=4)
	private String partyTypeCode;
	@Column(name="party_type_flag")
	private String partyTypeFlag;
	
	/**
	 * @return the partyTypeId
	 */
	public Integer getPartyTypeId() {
		return partyTypeId;
	}
	/**
	 * @param partyTypeId the partyTypeId to set
	 */
	public void setPartyTypeId(Integer partyTypeId) {
		this.partyTypeId = partyTypeId;
	}
	public String getPartyTypeDesc() {
		return partyTypeDesc;
	}
	public void setPartyTypeDesc(String partyTypeDesc) {
		this.partyTypeDesc = partyTypeDesc;
	}
	public String getPartyTypeCode() {
		return partyTypeCode;
	}
	public void setPartyTypeCode(String partyTypeCode) {
		this.partyTypeCode = partyTypeCode;
	}
	public String getPartyTypeFlag() {
		return partyTypeFlag;
	}
	public void setPartyTypeFlag(String partyTypeFlag) {
		this.partyTypeFlag = partyTypeFlag;
	}
}
