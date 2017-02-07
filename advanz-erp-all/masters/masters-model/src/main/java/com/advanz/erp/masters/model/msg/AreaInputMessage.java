package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.AreaDTO;
import com.advanz.erp.masters.model.criteria.AreaSearchCriteriaDTO;

public class AreaInputMessage extends AdvanzErpBaseInputMessage{

	private AreaDTO areaDTO;

	private AreaSearchCriteriaDTO searchCriteria;
	
	public AreaDTO getAreaDTO() {
		return areaDTO;
	}

	public void setAreaDTO(AreaDTO areaDTO) {
		this.areaDTO = areaDTO;
	}

	public AreaSearchCriteriaDTO getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(AreaSearchCriteriaDTO searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	
	
	
}
