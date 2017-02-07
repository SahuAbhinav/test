package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.RegionDTO;
import com.advanz.erp.masters.model.criteria.RegionSearchCriteriaDTO;

public class RegionInputMessage extends AdvanzErpBaseInputMessage{

	private RegionDTO regionDTO;
	private RegionSearchCriteriaDTO searchCriteria;

	/**
	 * @return the zoneDTO
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

	public RegionSearchCriteriaDTO getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(RegionSearchCriteriaDTO searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	
	
	
	
}
