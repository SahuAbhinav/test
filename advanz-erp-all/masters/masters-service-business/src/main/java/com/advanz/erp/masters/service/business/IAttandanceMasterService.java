package com.advanz.erp.masters.service.business;

import java.util.Date;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.AttandanceMasterInputMessage;
import com.advanz.erp.masters.model.msg.AttandanceMasterOutputMessage;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterInputMessage;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterOutputMessage;
import com.advanz.erp.masters.model.msg.BulkFiberMasterInputMessage;
import com.advanz.erp.masters.model.msg.BulkFiberMasterOutputMessage;

public interface IAttandanceMasterService extends IAdvanzErpBaseSerivce{	
	public AttandanceMasterOutputMessage createAttandanceMaster(AttandanceMasterInputMessage attandanceMasterInputMessage);
	
	public AttandanceMasterOutputMessage updateAttandanceMaster(AttandanceMasterInputMessage attandanceMasterInputMessage);
	
	public AttandanceMasterOutputMessage deleteAttandanceMaster(AttandanceMasterInputMessage attandanceMasterInputMessage);
	
	public AttandanceMasterOutputMessage findAttandanceMasterById(AttandanceMasterInputMessage attandanceMasterInputMessage);
	
	public AttandanceMasterOutputMessage findAllAttandanceMasters();
	
	public AttandanceMasterOutputMessage search(AttandanceMasterInputMessage attandanceMasterInputMessage);
	public Double coutLeaves(Integer employeeId,String leaveType,Date fromDate,Date toDate);
	public Double countByFullDay(Integer employeeId,String leaveType,Date fromDate,Date toDate);
	public Double countByHalfDay(Integer employeeId,String leaveType,Date fromDate,Date toDate);
	public AttandanceMasterOutputMessage deleteAttandanceBYEmp(AttandanceMasterInputMessage attandanceMasterInputMessage);
	public AttandanceMasterOutputMessage findAttandance(AttandanceMasterInputMessage attandanceMasterInputMessage);
}
