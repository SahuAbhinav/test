package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.ZoneDTO;
import com.advanz.erp.masters.model.criteria.AreaSearchCriteriaDTO;
import com.advanz.erp.masters.model.criteria.ZoneSearchCriteriaDTO;

public class ZoneInputMessage extends AdvanzErpBaseInputMessage{

	private ZoneDTO zoneDTO;

	private ZoneSearchCriteriaDTO searchCriteria;
	public ZoneSearchCriteriaDTO getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(ZoneSearchCriteriaDTO searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	/**
	 * @return the zoneDTO
	 */
	public ZoneDTO getZoneDTO() {
		return zoneDTO;
	}

	/**
	 * @param batchDTO the batchDTO to set
	 */
	public void setZoneDTO(ZoneDTO zoneDTO) {
		this.zoneDTO = zoneDTO;
	}
	
}
