package com.advanz.erp.client.http.controller.form;

import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.MastersDTO;

public class IssueDetailReportFrom {

	private ItemGroupFlagDTO flagDTO;
	private MastersDTO mastersDTO;
	
	public ItemGroupFlagDTO getFlagDTO() {
		return flagDTO;
	}
	public void setFlagDTO(ItemGroupFlagDTO flagDTO) {
		this.flagDTO = flagDTO;
	}
	public MastersDTO getMastersDTO() {
		return mastersDTO;
	}
	public void setMastersDTO(MastersDTO mastersDTO) {
	this.mastersDTO = mastersDTO;
	}

	
	
}
