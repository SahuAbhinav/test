package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.AreaInputMessage;
import com.advanz.erp.masters.model.msg.AreaOutputMessage;

public interface IAreaService extends IAdvanzErpBaseSerivce{

	
	public AreaOutputMessage createArea(AreaInputMessage areaInputMessage);
	
	public AreaOutputMessage updateArea(AreaInputMessage areaInputMessage);
	
	public AreaOutputMessage deleteArea(AreaInputMessage areaInputMessage);
	
	public AreaOutputMessage findAreaById(AreaInputMessage areaInputMessage);
	
	public AreaOutputMessage findAllAreas();
	
	public AreaOutputMessage search(AreaInputMessage areaInputMessage);

	public AreaOutputMessage findAreaByRegionId(AreaInputMessage areaInputMessage);
	
	public AreaOutputMessage checkBeforeRemove(AreaInputMessage areaInputMessage);
}
