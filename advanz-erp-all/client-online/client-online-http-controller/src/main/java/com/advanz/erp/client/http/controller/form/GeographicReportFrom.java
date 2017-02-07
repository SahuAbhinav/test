package com.advanz.erp.client.http.controller.form;

import java.util.ArrayList;
import java.util.List;

import com.advanz.erp.masters.model.AreaDTO;
import com.advanz.erp.masters.model.RegionDTO;
import com.advanz.erp.masters.model.StateDTO;
import com.advanz.erp.masters.model.ZoneDTO;

public class GeographicReportFrom {

	private ZoneDTO zone;
	/*private List<ZoneDTO> zones;
	
	private StateDTO state;
	private List<StateDTO> states;
	
	private RegionDTO region;
	private List<RegionDTO> regions;
	
	private AreaDTO area;
	private List<AreaDTO> areas;*/
	
	/*public GeographicReportFrom(){
		zones = new ArrayList<ZoneDTO>();
		states = new ArrayList<StateDTO>();
		regions = new ArrayList<RegionDTO>();
		areas = new ArrayList<AreaDTO>();		
		
	}*/
	public ZoneDTO getZone() {
		return zone;
	}
	public void setZone(ZoneDTO zone) {
		this.zone = zone;
	}
	/*public List<ZoneDTO> getZones() {
		return zones;
	}
	public void setZones(List<ZoneDTO> zones) {
		this.zones = zones;
	}
	public StateDTO getState() {
		return state;
	}
	public void setState(StateDTO state) {
		this.state = state;
	}
	public List<StateDTO> getStates() {
		return states;
	}
	public void setStates(List<StateDTO> states) {
		this.states = states;
	}
	public RegionDTO getRegion() {
		return region;
	}
	public void setRegion(RegionDTO region) {
		this.region = region;
	}
	public List<RegionDTO> getRegions() {
		return regions;
	}
	public void setRegions(List<RegionDTO> regions) {
		this.regions = regions;
	}
	public AreaDTO getArea() {
		return area;
	}
	public void setArea(AreaDTO area) {
		this.area = area;
	}
	public List<AreaDTO> getAreas() {
		return areas;
	}
	public void setAreas(List<AreaDTO> areas) {
		this.areas = areas;
	}
	*/
	
}
