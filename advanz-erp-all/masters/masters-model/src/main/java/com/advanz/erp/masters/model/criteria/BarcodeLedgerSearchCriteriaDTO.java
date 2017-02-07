package com.advanz.erp.masters.model.criteria;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class BarcodeLedgerSearchCriteriaDTO extends BaseDTO {
	private Integer itemId;
	private String barcode;
	private String transactionType;
	private Integer transactionId;

	public BarcodeLedgerSearchCriteriaDTO() {
		super();
	}

	public BarcodeLedgerSearchCriteriaDTO(Integer itemId, String barcode,
			String transactionType, Integer transactionId) {
		super();
		this.itemId = itemId;
		this.barcode = barcode;
		this.transactionType = transactionType;
		this.transactionId = transactionId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

}