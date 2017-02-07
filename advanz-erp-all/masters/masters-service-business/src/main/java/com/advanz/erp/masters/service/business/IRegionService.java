package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.RegionInputMessage;
import com.advanz.erp.masters.model.msg.RegionOutputMessage;

public interface IRegionService extends IAdvanzErpBaseSerivce{

	
	public RegionOutputMessage createRegion(RegionInputMessage areaInputMessage);
	
	public RegionOutputMessage updateRegion(RegionInputMessage areaInputMessage);
	
	public RegionOutputMessage deleteRegion(RegionInputMessage areaInputMessage);
	
	public RegionOutputMessage findRegionById(RegionInputMessage areaInputMessage);
	
	public RegionOutputMessage search(RegionInputMessage regionInputMessage) ;
	
	public RegionOutputMessage findAllRegions();

	RegionOutputMessage findByStateId(RegionInputMessage regionInputMessage);
	
	public RegionOutputMessage checkBeforeRemove(RegionInputMessage regionInputMessage);
}
