package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.ProformaMasterInputMessage;
import com.advanz.erp.masters.model.msg.ProformaMasterOutMessage;

public interface IProformaMasterService extends IAdvanzErpBaseSerivce {

	public ProformaMasterOutMessage createBill(ProformaMasterInputMessage proformaMasterInputMessage);

	public ProformaMasterOutMessage updateBill(ProformaMasterInputMessage proformaMasterInputMessage);

	public ProformaMasterOutMessage deleteBill(ProformaMasterInputMessage proformaMasterInputMessage);

	public ProformaMasterOutMessage findBillById(ProformaMasterInputMessage proformaMasterInputMessage);

	public ProformaMasterOutMessage findAllBills();
	
	public ProformaMasterOutMessage getLastByInvoiceId();
	
	public ProformaMasterOutMessage searchInvoice(ProformaMasterInputMessage proformaMasterInputMessage);
	public ProformaMasterOutMessage getMaxInvoiceId();
	
	public ProformaMasterOutMessage findBillByInvoiceNo(ProformaMasterInputMessage proformaMasterInputMessage);
	
	public ProformaMasterOutMessage getFinacialYear();
	
	public ProformaMasterOutMessage getNewProformaMasterSeriesNo(ProformaMasterInputMessage proformaMasterInputMessage);
	public ProformaMasterOutMessage getProformaListWithPagination(ProformaMasterInputMessage proformaMasterInputMessage);
}
