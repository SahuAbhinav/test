package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.CapativeConsuptionInputMessage;
import com.advanz.erp.masters.model.msg.CapativeConsuptionOutputMessage;

public interface ICapativeConsuptionService extends IAdvanzErpBaseSerivce{
	public CapativeConsuptionOutputMessage createCapativeConsuption(CapativeConsuptionInputMessage capativeConsuptionInputMessage);
	public CapativeConsuptionOutputMessage updateCapativeConsuption(CapativeConsuptionInputMessage capativeConsuptionInputMessage);
	public CapativeConsuptionOutputMessage deleteCapativeConsuption(CapativeConsuptionInputMessage capativeConsuptionInputMessage);
	public CapativeConsuptionOutputMessage findCapativeConsuptionById(CapativeConsuptionInputMessage capativeConsuptionInputMessage);
	public CapativeConsuptionOutputMessage findAllcapativeConsuption();
	public CapativeConsuptionOutputMessage search(CapativeConsuptionInputMessage capativeConsuptionInputMessage);
	public Integer getNewSeriesNo();
	public CapativeConsuptionOutputMessage findCapativeConsuptionByPagination(CapativeConsuptionInputMessage capativeConsuptionInputMessage);
}
