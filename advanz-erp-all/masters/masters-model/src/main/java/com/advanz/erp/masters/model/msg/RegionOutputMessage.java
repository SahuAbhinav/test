package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.RegionDTO;

public class RegionOutputMessage extends AdvanzErpBaseOutputMessage{



	private RegionDTO regionDTO;
	
	private List<RegionDTO> regionDTOList;

	/**
	 * @return the regionDTO
	 */
	public RegionDTO getRegionDTO() {
		return regionDTO;
	}

	/**
	 * @param batchDTO the batchDTO to set
	 */
	public void setRegionDTO(RegionDTO regionDTO) {
		this.regionDTO = regionDTO;
	}

	/**
	 * @return the batchDTOList
	 */
	public List<RegionDTO> getRegionDTOList() {
		return regionDTOList;
	}

	/**
	 * @param batchDTOList the batchDTOList to set
	 */
	public void setRegionDTOList(List<RegionDTO> regionDTOList) {
		this.regionDTOList = regionDTOList;
	}
	
}
