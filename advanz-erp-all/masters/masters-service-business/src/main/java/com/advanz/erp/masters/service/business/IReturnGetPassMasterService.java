package com.advanz.erp.masters.service.business;

import java.util.List;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.ReturnGetPassMasterInputMessage;
import com.advanz.erp.masters.model.msg.ReturnGetPassMasterOutputMessage;

public interface IReturnGetPassMasterService extends IAdvanzErpBaseSerivce{

	
	public ReturnGetPassMasterOutputMessage createReturnGetPassMaster(ReturnGetPassMasterInputMessage returnGetPassInputMessage);
	
	public ReturnGetPassMasterOutputMessage updateReturnGetPassMaster(ReturnGetPassMasterInputMessage returnGetPassInputMessage);
	
	public ReturnGetPassMasterOutputMessage deleteReturnGetPassMaster(ReturnGetPassMasterInputMessage returnGetPassInputMessage);
	
	public ReturnGetPassMasterOutputMessage findReturnGetPassMasterById(ReturnGetPassMasterInputMessage returnGetPassInputMessage);
	
	public ReturnGetPassMasterOutputMessage findAllReturnGetPassMaster();
	
	public ReturnGetPassMasterOutputMessage search(ReturnGetPassMasterInputMessage returnGetPassInputMessage);
	
	public ReturnGetPassMasterOutputMessage getNewReturnGetPassMasterSeriesNo(ReturnGetPassMasterInputMessage returnGetPassInputMessage);
	public ReturnGetPassMasterOutputMessage findReturnGetPassPagination(ReturnGetPassMasterInputMessage returnGetPassInputMessage);
	public List getPendingQty(String passNumber, Integer itemId);
}
