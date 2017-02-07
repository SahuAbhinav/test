package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

public class DispatchDetailDTO extends BaseDTO {
	private Integer sno;
	private String transactionSeries;
	private Integer dispatchAutoId;
	private String dispatchNumber;
	private String invoiceNumber;
	private Integer itemId;
	private String batchNo;
	private Double quantity;
    private Double qtyPerPacket;
    private Double noOfPacket;
	private Double packetWeight;
	
	// Add by me
	private String umoName;
	
	public String getUmoName() {
		return umoName;
	}
	public void setUmoName(String umoName) {
		this.umoName = umoName;
	}
	private String itemName;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
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
