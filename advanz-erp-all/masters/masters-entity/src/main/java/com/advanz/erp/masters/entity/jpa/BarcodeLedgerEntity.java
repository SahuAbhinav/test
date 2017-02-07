package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

/**
 * @author Abhinav Sahu
 *
 */
@Entity
@Table(name = "t_barcode_ledger")
public class BarcodeLedgerEntity extends BaseEntity {

	public static final int BARCODE_SNO = 10000;

	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name = "sno")
	private Integer id;

	@Column(name = "item_id")
	private Integer itemId;

	@Column(name = "barcode")
	private String barcode;

	@Column(name = "barcode_sno")
	private String barcodeSno;

	@Column(name = "transaction_type")
	private String transactionType;

	@Column(name = "transaction_id")
	private Integer transactionId;

	@Column(name = "quantity")
	private Double quantity;
	
	@Transient
	private Integer noOfBarcode;
	
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

	@Override
	public String toString() {
		return "BarcodeLedgerEntity [id=" + id + ", itemId=" + itemId
				+ ", barcode=" + barcode + ", barcodeSno=" + barcodeSno
				+ ", transactionType=" + transactionType + ", transactionId="
				+ transactionId + ", quantity=" + quantity + ", noOfBarcode="
				+ noOfBarcode + "]";
	}

	

}
