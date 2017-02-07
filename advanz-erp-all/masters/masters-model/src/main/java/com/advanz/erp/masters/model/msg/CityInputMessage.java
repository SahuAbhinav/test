package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.CityDTO;
import com.advanz.erp.masters.model.criteria.CitySearchCriteriaDTO;

public class CityInputMessage extends AdvanzErpBaseInputMessage{

	private CityDTO cityDTO;
	
	private CitySearchCriteriaDTO searchCriteria;

	/**
	 * @return the cityDTO
	 */
	public CityDTO getCityDTO() {
		return cityDTO;
	}

	public CitySearchCriteriaDTO getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(CitySearchCriteriaDTO searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	/**
	 * @param batchDTO the batchDTO to set
	 */
	public void setCityDTO(CityDTO cityDTO) {
		this.cityDTO = cityDTO;
	}
}
