package com.advanz.erp.masters.service.business;

import java.sql.Timestamp;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.ZoneInputMessage;
import com.advanz.erp.masters.model.msg.ZoneOutputMessage;

public interface IZoneService extends IAdvanzErpBaseSerivce {

	public ZoneOutputMessage createZone(ZoneInputMessage zoneInputMessage);

	public ZoneOutputMessage updateZone(ZoneInputMessage zoneInputMessage);

	public ZoneOutputMessage deleteZone(ZoneInputMessage zoneInputMessage);

	public ZoneOutputMessage findZoneById(ZoneInputMessage zoneInputMessage);

	public ZoneOutputMessage findAllZones();

	public ZoneOutputMessage search(ZoneInputMessage zoneInputMessage);

	public ZoneOutputMessage checkBeforeRemove(ZoneInputMessage zoneInputMessage);

	public Timestamp getFirstDayOfFinYear();
}
