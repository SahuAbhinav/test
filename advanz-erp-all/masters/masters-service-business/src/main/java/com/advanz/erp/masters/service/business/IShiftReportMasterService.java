package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.ShiftReportMasterInputMessage;
import com.advanz.erp.masters.model.msg.ShiftReportMasterOutputMessage;

public interface IShiftReportMasterService extends IAdvanzErpBaseSerivce{

	
	public ShiftReportMasterOutputMessage createShiftReportMaster(ShiftReportMasterInputMessage shiftReportMasterInputMessage);
	
	public ShiftReportMasterOutputMessage updateShiftReportMaster(ShiftReportMasterInputMessage shiftReportMasterInputMessage);
	
	public ShiftReportMasterOutputMessage deleteShiftReportMaster(ShiftReportMasterInputMessage shiftReportMasterInputMessage);
	
	public ShiftReportMasterOutputMessage findShiftReportMasterById(ShiftReportMasterInputMessage shiftReportMasterInputMessage);
	
	public ShiftReportMasterOutputMessage findAllShiftReportMasters();
	
	public ShiftReportMasterOutputMessage search(ShiftReportMasterInputMessage shiftReportMasterInputMessage);
	
	//public ShiftReportMasterOutputMessage getNewFinishedGoodsSeriesNo(ShiftReportMasterInputMessage shiftReportMasterInputMessage);
	
	public ShiftReportMasterOutputMessage getDuplicateCheckList();
	public ShiftReportMasterOutputMessage checkDuplicateEntry(ShiftReportMasterInputMessage shiftReportMasterInputMessage);
	public ShiftReportMasterOutputMessage findIShiftReportPagination(ShiftReportMasterInputMessage shiftReportMasterInputMessage);
	public ShiftReportMasterOutputMessage getLastShiftDate(ShiftReportMasterInputMessage shiftReportMasterInputMessage);
	
	
}