package com.advanz.erp.masters.model.criteria;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class ItemSearchCriteriaDTO extends BaseDTO{
	private String invoiceName;
	private String itemCode;
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	
	
	

}
