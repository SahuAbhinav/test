package com.advanz.erp.masters.model;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class FinishedGoodsDetailDTO extends BaseDTO{
	private Integer sno;
	private String transactionSeries;
//	private String salesOrderNumber;
	private String finishedGoodsNumber;
	//private Integer measurementUnitId;
//	private ItemDTO itemDTO;
	private Integer itemId;
	private String itemName;
	private Integer finishAutoId;
	private String batchNo;
	private Date mfgDate;
	private Date expiryDate;	
	private Double quantity;
	private Integer itemEntrySeqNo;
	private String packingDetails;
	private Integer storeLocationId;
	private String storeLocationName;
	private Boolean deletedFlag=false;
	private MastersDTO measurementUnitMasterDTO;
	private boolean transientObject;
	
	private String entryStatus;
	
	public boolean isTransientObject() {
		return transientObject;
	}
	public void setTransientObject(boolean transientObject) {
		this.transientObject = transientObject;
	}
	public MastersDTO getMeasurementUnitMasterDTO() {
		return measurementUnitMasterDTO;
	}
	public void setMeasurementUnitMasterDTO(MastersDTO measurementUnitMasterDTO) {
		this.measurementUnitMasterDTO = measurementUnitMasterDTO;
	}
	public Boolean getDeletedFlag() {
		return deletedFlag;
	}
	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}
	public String getStoreLocationName() {
		return storeLocationName;
	}
	public void setStoreLocationName(String storeLocationName) {
		this.storeLocationName = storeLocationName;
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
	public String getFinishedGoodsNumber() {
		return finishedGoodsNumber;
	}
	public void setFinishedGoodsNumber(String finishedGoodsNumber) {
		this.finishedGoodsNumber = finishedGoodsNumber;
	}
//	public Integer getMeasurementUnitId() {
//		return measurementUnitId;
//	}
//	public void setMeasurementUnitId(Integer measurementUnitId) {
//		this.measurementUnitId = measurementUnitId;
//	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getFinishAutoId() {
		return finishAutoId;
	}
	public void setFinishAutoId(Integer finishAutoId) {
		this.finishAutoId = finishAutoId;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public Date getMfgDate() {
		return mfgDate;
	}
	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Integer getItemEntrySeqNo() {
		return itemEntrySeqNo;
	}
	public void setItemEntrySeqNo(Integer itemEntrySeqNo) {
		this.itemEntrySeqNo = itemEntrySeqNo;
	}
	public String getPackingDetails() {
		return packingDetails;
	}
	public void setPackingDetails(String packingDetails) {
		this.packingDetails = packingDetails;
	}
	public Integer getStoreLocationId() {
		return storeLocationId;
	}
	public void setStoreLocationId(Integer storeLocationId) {
		this.storeLocationId = storeLocationId;
	}
	public String getEntryStatus() {
		return entryStatus;
	}
	public void setEntryStatus(String entryStatus) {
		this.entryStatus = entryStatus;
	}


	/* Add by Abhinav
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FinishedGoodsDetailDTO other = (FinishedGoodsDetailDTO) obj;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FinishedGoodsDetailDTO [sno=" + sno + ", transactionSeries="
				+ transactionSeries + ", finishedGoodsNumber="
				+ finishedGoodsNumber + ", itemId=" + itemId + ", itemName="
				+ itemName + ", finishAutoId=" + finishAutoId + ", batchNo="
				+ batchNo + ", mfgDate=" + mfgDate + ", expiryDate="
				+ expiryDate + ", quantity=" + quantity + ", itemEntrySeqNo="
				+ itemEntrySeqNo + ", packingDetails=" + packingDetails
				+ ", storeLocationId=" + storeLocationId
				+ ", storeLocationName=" + storeLocationName + ", deletedFlag="
				+ deletedFlag + ", measurementUnitMasterDTO="
				+ measurementUnitMasterDTO + ", transientObject="
				+ transientObject + ", entryStatus=" + entryStatus + "]";
	}
	
	
	
	
	}
