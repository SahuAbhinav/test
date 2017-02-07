package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

public class PartyTypeDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8671171515090109172L;
	
	private Integer partyTypeId;
	private String partyTypeDesc;
	private String partyTypeCode;
	private String partyTypeFlag;
	
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
}
