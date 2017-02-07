package com.advanz.erp.masters.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;
@Entity
@Table(name = "t_dispatch_detail")
public class DispatchDetailEntity extends BaseEntity{

	@Id
	@GeneratedValue(generator = "system-incr")
	@GenericGenerator(name = "system-incr", strategy = "increment")
	@Column(name = "sno")
	private Integer sno;
	
	@Column(name = "transaction_series")
	private String transactionSeries;
	
	@Column(name = "dispatch_auto_id")
	private Integer dispatchAutoId;
	
	
	@Column(name = "dispatch_number")
	private String dispatchNumber;
	
	@Column(name = "invoice_number")
	private String invoiceNumber;
	
	@Column(name = "item_id")
	private Integer itemId;
	
	@Column(name = "batch_no")
	private String batchNo;
	
	@Column(name = "quantity")
	private Double quantity;
	
	@Column(name = "qty_per_packet")
    private Double qtyPerPacket;
	
	@Column(name = "no_of_packet")
    private Double noOfPacket;
	
	@Column(name = "packet_weight")
	private Double packetWeight;

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public String getTransactionSeries() {
		return transactionSeries;
	}

	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	public Integer getDispatchAutoId() {
		return dispatchAutoId;
	}

	public void setDispatchAutoId(Integer dispatchAutoId) {
		this.dispatchAutoId = dispatchAutoId;
	}

	public String getDispatchNumber() {
		return dispatchNumber;
	}

	public void setDispatchNumber(String dispatchNumber) {
		this.dispatchNumber = dispatchNumber;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getQtyPerPacket() {
		return qtyPerPacket;
	}

	public void setQtyPerPacket(Double qtyPerPacket) {
		this.qtyPerPacket = qtyPerPacket;
	}

	public Double getNoOfPacket() {
		return noOfPacket;
	}

	public void setNoOfPacket(Double noOfPacket) {
		this.noOfPacket = noOfPacket;
	}

	public Double getPacketWeight() {
		return packetWeight;
	}

	public void setPacketWeight(Double packetWeight) {
		this.packetWeight = packetWeight;
	}
	
	
	
}
