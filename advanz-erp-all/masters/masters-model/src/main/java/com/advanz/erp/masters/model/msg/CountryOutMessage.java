package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.CountryDTO;

public class CountryOutMessage extends AdvanzErpBaseOutputMessage{



	private CountryDTO countryDTO;
	
	private List<CountryDTO> countryDTOList;

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

	/**
	 * @return the countryDTO
	 */
	public List<CountryDTO> getCountryDTOList() {
		return countryDTOList;
	}

	/**
	 * @param countryDTOList the countryDTOList to set
	 */
	public void setCountryDTOList(List<CountryDTO> countryDTOList) {
		this.countryDTOList = countryDTOList;
	}
	
	
}
