package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.CityDTO;
import com.advanz.erp.masters.model.CityStateCountryDTO;

public class CityOutputMessage extends AdvanzErpBaseOutputMessage{

	private CityStateCountryDTO cityStateCountryDTO;


	private CityDTO cityDTO;
	
	private List<CityDTO> cityDTOList;

	/**
	 * @return the cityDTO
	 */
	public CityDTO getCityDTO() {
		return cityDTO;
	}

	/**
	 * @param batchDTO the batchDTO to set
	 */
	public void setCityDTO(CityDTO cityDTO) {
		this.cityDTO = cityDTO;
	}

	/**
	 * @return the batchDTOList
	 */
	public List<CityDTO> getCityDTOList() {
		return cityDTOList;
	}

	/**
	 * @param batchDTOList the batchDTOList to set
	 */
	public void setCityDTOList(List<CityDTO> cityDTOList) {
		this.cityDTOList = cityDTOList;
	}

	public CityStateCountryDTO getCityStateCountryDTO() {
		return cityStateCountryDTO;
	}

	public void setCityStateCountryDTO(CityStateCountryDTO cityStateCountryDTO) {
		this.cityStateCountryDTO = cityStateCountryDTO;
	}
	
	
	
	
}
