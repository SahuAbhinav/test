package com.advanz.erp.masters.service.business;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.GrnMasterInputMessage;
import com.advanz.erp.masters.model.msg.GrnMasterOutputMessage;

public interface IGrnMasterService extends IAdvanzErpBaseSerivce{

	
	public GrnMasterOutputMessage createGrnMaster(GrnMasterInputMessage grnInputMessage);
	
	public GrnMasterOutputMessage updateGrnMaster(GrnMasterInputMessage grnInputMessage);
	
	public GrnMasterOutputMessage deleteGrnMaster(GrnMasterInputMessage grnInputMessage);
	
	public GrnMasterOutputMessage findGrnMasterById(GrnMasterInputMessage grnInputMessage);
	
	public GrnMasterOutputMessage findAllGrnsMaster();
	
	public GrnMasterOutputMessage search(GrnMasterInputMessage grnInputMessage);
	
	public GrnMasterOutputMessage getNewGrnMasterSeriesNo(GrnMasterInputMessage grnInputMessage);
	public Date getMaxDate() ;
	public GrnMasterOutputMessage findGrnByPoNo(GrnMasterInputMessage grnInputMessage);
	public GrnMasterOutputMessage findAllGrnForAllPoNo();
	
	public List getEmailAlertData(String date);
	public GrnMasterOutputMessage findGrnMasterForPagination(GrnMasterInputMessage grnInputMessage);
	public Double getPuchaseRateByIssueItemId(Integer itemId);
	public Double getLastGrnRateForWeightedAvg(Date date ,Integer itemId);
}
