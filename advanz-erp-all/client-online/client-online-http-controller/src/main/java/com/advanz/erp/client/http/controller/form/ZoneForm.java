package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.ZoneDTO;

public class ZoneForm {
	private ZoneDTO zoneDTO;
	private List<ZoneDTO> rows;
	private String succ;
	
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public ZoneDTO getZoneDTO() {
		
		return zoneDTO;
	}
	public void setZoneDTO(ZoneDTO zoneDTO) {
		this.zoneDTO = zoneDTO;
	}
	public List<ZoneDTO> getRows() {
		return rows;
	}
	public void setRows(List<ZoneDTO> listZone) {
		this.rows = listZone;
	}
	
	private String zoneName;
	
	private String zoneCode;
	
	private String countryName;
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public String getZoneCode() {
		return zoneCode;
	}
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}
