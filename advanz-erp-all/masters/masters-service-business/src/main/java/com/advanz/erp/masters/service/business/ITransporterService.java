package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.TransporterInputMessage;
import com.advanz.erp.masters.model.msg.TransporterOutMessage;

public interface ITransporterService extends IAdvanzErpBaseSerivce{

	
	public TransporterOutMessage createTransporter(TransporterInputMessage transporterInputMessage);
	
	public TransporterOutMessage updateTransporter(TransporterInputMessage transporterInputMessage);
	
	public TransporterOutMessage deleteTransporter(TransporterInputMessage transporterInputMessage);
	
	public TransporterOutMessage findTransporterById(TransporterInputMessage transporterInputMessage);
	
	public TransporterOutMessage findAllTransporters();
	
	public TransporterOutMessage findTransporter(TransporterInputMessage transporterInputMessage);
	
	public TransporterOutMessage checkBeforeRemove(TransporterInputMessage transporterInputMessage);
	public TransporterOutMessage preload();
}
