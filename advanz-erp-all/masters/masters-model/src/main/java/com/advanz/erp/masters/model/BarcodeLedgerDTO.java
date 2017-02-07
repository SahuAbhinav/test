package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

/**
 * @author Abhinav Sahu
 *
 */
@SuppressWarnings("serial")
public class BarcodeLedgerDTO extends BaseDTO {

	public static final String BLANKET = "BL";
	public static final String FINISH_GOODS = "FG";

	private Integer id;

	/**
	 * This field contains the ItemId for which item this barcode is generated.
	 */
	private Integer itemId;

	/**
	 * This field contains the barcode
	 */
	private String barcode;

	/**
	 * This transaction Type contains the Which Transaction Series generate this
	 * Barcode may be Finished Good or Blanket Production.
	 */
	private String transactionType;

	/**
	 * This transaction Id contains the primary key of Which Transaction Series
	 * generate this Barcode may be Finished Good or Blanket Production.
	 */
	private Integer transactionId;

	private String barcodeSno;

	private Double quantity = 1.0;

	private Integer noOfBarcode = 1;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the itemId
	 */
	public Integer getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the barcode
	 */
	public String getBarcode() {
		return barcode;
	}

	/**
	 * @param barcode
	 *            the barcode to set
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType
	 *            the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * @return the transactionId
	 */
	public Integer getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId
	 *            the transactionId to set
	 */
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getBarcodeSno() {
		return barcodeSno;
	}

	public void setBarcodeSno(String barcodeSno) {
		this.barcodeSno = barcodeSno;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Integer getNoOfBarcode() {
		return noOfBarcode;
	}

	public void setNoOfBarcode(Integer noOfBarcode) {
		this.noOfBarcode = noOfBarcode;
	}

}
