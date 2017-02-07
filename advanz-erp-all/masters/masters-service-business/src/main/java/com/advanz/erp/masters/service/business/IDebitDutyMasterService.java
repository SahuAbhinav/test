package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.DebitDutyMasterInputMessage;
import com.advanz.erp.masters.model.msg.DebitDutyMasterOutPutMessage;

public interface IDebitDutyMasterService extends IAdvanzErpBaseSerivce {

	public DebitDutyMasterOutPutMessage createDebitDuty(DebitDutyMasterInputMessage debitDutyMasterInputMessage);
	public DebitDutyMasterOutPutMessage updateDebitDuty(DebitDutyMasterInputMessage debitDutyMasterInputMessage);
	public DebitDutyMasterOutPutMessage deleteDebitDuty(DebitDutyMasterInputMessage debitDutyMasterInputMessage);
	public DebitDutyMasterOutPutMessage findDebitDutyById(DebitDutyMasterInputMessage debitDutyMasterInputMessage);
	public DebitDutyMasterOutPutMessage findAllDebit();
	public DebitDutyMasterOutPutMessage getLastByDebitDutyId();
	public DebitDutyMasterOutPutMessage search(DebitDutyMasterInputMessage debitDutyMasterInputMessage);
	public DebitDutyMasterOutPutMessage getMaxDebitId();
	public DebitDutyMasterOutPutMessage getFinacialYear();
	public DebitDutyMasterOutPutMessage getNewDebitDutyMasterSeriesNo(DebitDutyMasterInputMessage debitDutyMasterInputMessage);
}
