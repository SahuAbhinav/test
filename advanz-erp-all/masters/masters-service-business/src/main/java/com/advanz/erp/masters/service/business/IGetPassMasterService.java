package com.advanz.erp.masters.service.business;

import java.util.List;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.GetPassDetailDTO;
import com.advanz.erp.masters.model.msg.GetPassMasterInputMessage;
import com.advanz.erp.masters.model.msg.GetPassMasterOutputMessage;

public interface IGetPassMasterService extends IAdvanzErpBaseSerivce{

	
	public GetPassMasterOutputMessage createGetPassMaster(GetPassMasterInputMessage getPassInputMessage);
	
	public GetPassMasterOutputMessage updateGetPassMaster(GetPassMasterInputMessage getPassInputMessage);
	
	public GetPassMasterOutputMessage deleteGetPassMaster(GetPassMasterInputMessage getPassInputMessage);
	
	public GetPassMasterOutputMessage findGetPassMasterById(GetPassMasterInputMessage getPassInputMessage);
	
	public GetPassMasterOutputMessage findAllGetPassMaster();
	
	public GetPassMasterOutputMessage search(GetPassMasterInputMessage getPassInputMessage);
	
	public GetPassMasterOutputMessage getNewGetPassMasterSeriesNo(GetPassMasterInputMessage getPassInputMessage);
	
	public List getPassDetailList(String PassNumber);
	public GetPassMasterOutputMessage findByGetPassType(GetPassMasterInputMessage getPassInputMessage);
	public GetPassMasterOutputMessage findGetPassPagination(GetPassMasterInputMessage getPassInputMessage);

public void updateGatePassDetail(GetPassDetailDTO gpdetailDTO,String operation);
}
