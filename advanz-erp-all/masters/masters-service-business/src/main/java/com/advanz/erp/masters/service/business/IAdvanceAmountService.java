package com.advanz.erp.masters.service.business;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.AdvanceAmountDTO;
import com.advanz.erp.masters.model.msg.AdvanceAmountInputMessage;
import com.advanz.erp.masters.model.msg.AdvanceAmountOutputMessage;

public interface IAdvanceAmountService extends IAdvanzErpBaseSerivce {
	public AdvanceAmountOutputMessage loadAllList();
	public AdvanceAmountOutputMessage search(AdvanceAmountInputMessage advanceAmountInputMessage);
	public AdvanceAmountOutputMessage save(AdvanceAmountInputMessage advanceAmountInputMessage);
	public AdvanceAmountOutputMessage findById(AdvanceAmountInputMessage advanceAmountInputMessage);
	public AdvanceAmountOutputMessage update(AdvanceAmountInputMessage advanceAmountInputMessage);
	public AdvanceAmountOutputMessage delete(AdvanceAmountInputMessage advanceAmountInputMessage);
	public AdvanceAmountOutputMessage findByEmployeeId(AdvanceAmountInputMessage advanceAmountInputMessage);
	public AdvanceAmountOutputMessage getTransactionId();
	public AdvanceAmountOutputMessage getByLastTransactionDate(AdvanceAmountInputMessage advanceAmountInputMessage);
	public AdvanceAmountOutputMessage findbyEmployeeIdAndCurrntMonth(AdvanceAmountInputMessage advanceAmountInputMessage);
	

}
