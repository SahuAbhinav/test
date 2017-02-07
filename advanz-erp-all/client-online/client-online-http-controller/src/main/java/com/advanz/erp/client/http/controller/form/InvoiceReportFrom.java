package com.advanz.erp.client.http.controller.form;

import com.advanz.erp.masters.model.BillDTO;

public class InvoiceReportFrom {

	private BillDTO bill;

	public BillDTO getBill() {
		return bill;
	}

	public void setBill(BillDTO bill) {
		this.bill = bill;
	}
	
private Integer invoiceId;

public Integer getInvoiceId() {
	return invoiceId;
}

public void setInvoiceId(Integer invoiceId) {
	this.invoiceId = invoiceId;
}
	
}
