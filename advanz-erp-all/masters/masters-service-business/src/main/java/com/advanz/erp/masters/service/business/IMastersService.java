package com.advanz.erp.masters.service.business;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;

public interface IMastersService extends IAdvanzErpBaseSerivce{

	
	public MastersOutputMessage createMasters(MastersInputMessage mastersInputMessage);
	
	public MastersOutputMessage updateMasters(MastersInputMessage mastersInputMessage);
	
	public MastersOutputMessage deleteMasters(MastersInputMessage mastersInputMessage);
	
	public MastersOutputMessage findMastersById(MastersInputMessage mastersInputMessage);
	
	public MastersOutputMessage findAllMasterss();
	
	public MastersOutputMessage searchMasters(MastersInputMessage mastersInputMessage);
	
	public MastersOutputMessage findFormById(MastersInputMessage mastersInputMessage);
	
	public MastersOutputMessage findMapForIdName(MastersInputMessage mastersInputMessage);
	
	public MastersOutputMessage findSubdepartment(MastersInputMessage mastersInputMessage);
	
	public MastersOutputMessage checkBeforeRemove(MastersInputMessage mastersInputMessage);
	
	public MastersOutputMessage findFormByIdForMelterLog(MastersInputMessage mastersInputMessage);
	
	public MastersOutputMessage getFormName();
	
	public MastersOutputMessage findMastersByIdAndFormId(MastersInputMessage mastersInputMessage);
	public List findByDate(Date date);
	 public String getMasterNameById(Integer masterId);
}
