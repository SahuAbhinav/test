package com.advanz.erp.masters.service.business;

import java.util.List;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.BillInputMessage;
import com.advanz.erp.masters.model.msg.BillOutMessage;

public interface IBillService extends IAdvanzErpBaseSerivce {

	public BillOutMessage createBill(BillInputMessage billInputMessage);

	public BillOutMessage updateBill(BillInputMessage billInputMessage);

	public BillOutMessage deleteBill(BillInputMessage billInputMessage);

	public BillOutMessage findBillById(BillInputMessage billInputMessage);

	public BillOutMessage findAllBills();
	
	public BillOutMessage getLastByInvoiceId();
	
	public BillOutMessage searchInvoice(BillInputMessage billInputMessage);
	
	public BillOutMessage getMaxInvoiceId();
	
	public BillOutMessage findBillByEmployeeId(BillInputMessage billInputMessage);
	public BillOutMessage getFinacialYear();
	public BillOutMessage getNewBillMasterSeriesNo(BillInputMessage billInputMessage);
	public BillOutMessage findInvoiceForPagination(BillInputMessage billInputMessage);
	public List getCountInvoiceToEmail(String startDate,String endDate);
	
	public boolean checkDuplicateProformaNum(String proformaNumer);
	public BillOutMessage checkDuplicateInvoiceNum(BillInputMessage billInputMessage);
	
}
