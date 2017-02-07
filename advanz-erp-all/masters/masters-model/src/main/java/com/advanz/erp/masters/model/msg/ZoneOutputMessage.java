package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.ZoneDTO;

public class ZoneOutputMessage extends AdvanzErpBaseOutputMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7652311966136682774L;
	
	private ZoneDTO zoneDTO;
	
	private List<ZoneDTO> zoneDTOList;
	
	public List<ZoneDTO> getZoneDTOList() {
		return zoneDTOList;
	}

	public void setZoneDTOList(List<ZoneDTO> zoneDTOList) {
		this.zoneDTOList = zoneDTOList;
	}

	public ZoneDTO getCompanyDTO() {
		return zoneDTO;
	}

	public void setZoneDTO(ZoneDTO zoneDTO) {
		this.zoneDTO = zoneDTO;
	}
}
