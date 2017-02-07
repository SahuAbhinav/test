package com.advanz.erp.masters.model.msg;

import com.advanz.erp.masters.model.CountryDTO;
import com.advanz.erp.masters.model.criteria.CountrySearchCriteriaDTO;
import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;

public class CountryInputMessage  extends AdvanzErpBaseInputMessage{

	private CountryDTO countryDTO;

	private CountrySearchCriteriaDTO searchCriteria;
	
	public CountrySearchCriteriaDTO getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(CountrySearchCriteriaDTO searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	/**
	 * @return the countryDTO
	 */
	public CountryDTO getCountryDTO() {
		return countryDTO;
	}

	/**
	 * @param countryDTO the countryDTO to set
	 */
	public void setCountryDTO(CountryDTO countryDTO) {
		this.countryDTO = countryDTO;
	}
	

}
