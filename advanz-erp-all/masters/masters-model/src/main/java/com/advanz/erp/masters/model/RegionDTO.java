package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class RegionDTO extends BaseDTO {
	
	private Integer regionId;
	
	private String regionCode;
	
	private String regionName;
	
	private Integer stateId;
	
	private String description;
	
	private Integer deletedFlag;
	
	
	private StateDTO stateDTO;

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public StateDTO getStateDTO() {
		return stateDTO;
	}

	public void setStateDTO(StateDTO stateDTO) {
		this.stateDTO = stateDTO;
	}

}
