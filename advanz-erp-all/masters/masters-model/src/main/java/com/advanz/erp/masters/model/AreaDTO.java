package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;


@SuppressWarnings("serial")
public class AreaDTO extends BaseDTO {
	
	private Integer areaId;
	
	private String areaCode;
	
	private String areaName;
	
	//private Integer regionId;
	
	private RegionDTO regionDTO;
	private String description;
	


	

	public Integer getAreaId() {
		return areaId;
	}



	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}



	public String getAreaCode() {
		return areaCode;
	}



	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}



	public String getAreaName() {
		return areaName;
	}



	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}



	



	public RegionDTO getRegionDTO() {
		return regionDTO;
	}



	public void setRegionDTO(RegionDTO regionDTO) {
		this.regionDTO = regionDTO;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}






	@Override
	public String toString() {
		return "AreaDTO [areaId=" + areaId + ", areaCode=" + areaCode
				+ ", areaName=" + areaName + "]";
	}
	
	

}
