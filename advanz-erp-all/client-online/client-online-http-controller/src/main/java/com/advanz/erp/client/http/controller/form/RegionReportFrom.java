package com.advanz.erp.client.http.controller.form;

import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.StateDTO;
import com.advanz.erp.masters.model.ZoneDTO;

public class RegionReportFrom {

	private ZoneDTO zoneDTO;
	private StateDTO stateDTO;
	
	public ZoneDTO getZoneDTO() {
		return zoneDTO;
	}
	public void setZoneDTO(ZoneDTO zoneDTO) {
		this.zoneDTO = zoneDTO;
	}
	public StateDTO getStateDTO() {
		return stateDTO;
	}
	public void setStateDTO(StateDTO stateDTO) {
		this.stateDTO = stateDTO;
	}
	
	
	
}
