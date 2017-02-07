package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Time;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name="t_finished_goods_detail")
public class FinishedGoodsDetailEntity extends BaseEntity {
	
	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="sno")
	private Integer sno;
	
	@Column(name="transaction_series")
	private String transactionSeries;
	
	@Column(name="finish_auto_id")
	private Integer finishAutoId;
	
	
	
	@Column(name="finished_goods_number")
	private String finishedGoodsNumber;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private ItemEntity itemEntity;
	
//	@Column(name="measurement_Unit_id")
//	private Integer measurementUnitId;
	
	@ManyToOne
	@JoinColumn(name="measurement_Unit_id")
	private MastersEntity measurementUnitMasterEntity;
	
	@Column(name="batch_no")
	private String batchNo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="mfg_date")
	private Date mfgDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="expiry_date")
	private Date expiryDate;
	
	
	
	@Column(name="quantity")
		private Double quantity;
	
	@Column(name="item_entry_seq_no")
		private Integer itemEntrySeqNo;
	
	@Column(name="packing_details")
		private String packingDetails;
	
	@Column(name="store_location_id")
		private Integer storeLocationId;
	
	@Column(name="entry_status")
	private String entryStatus;
	
//	@ManyToOne
//	@JoinColumn(name="branch_id")
//	private BranchEntity branchEntity;
	
//	@ManyToOne
//	@JoinColumn(name="transaction_number")
//	private StockLedgerEntity StockLedgerEntity;
	
	
	
	
//	public BranchEntity getBranchEntity() {
//		return branchEntity;
//	}
//
//	public void setBranchEntity(BranchEntity branchEntity) {
//		this.branchEntity = branchEntity;
//	}

//	public StockLedgerEntity getStockLedgerEntity() {
//		return StockLedgerEntity;
//	}
//
//	public void setStockLedgerEntity(StockLedgerEntity stockLedgerEntity) {
//		StockLedgerEntity = stockLedgerEntity;
//	}

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

	public Integer getFinishAutoId() {
		return finishAutoId;
	}

	public void setFinishAutoId(Integer finishAutoId) {
		this.finishAutoId = finishAutoId;
	}

	public String getFinishedGoodsNumber() {
		return finishedGoodsNumber;
	}

	public void setFinishedGoodsNumber(String finishedGoodsNumber) {
		this.finishedGoodsNumber = finishedGoodsNumber;
	}

	public ItemEntity getItemEntity() {
		return itemEntity;
	}

	public void setItemEntity(ItemEntity itemEntity) {
		this.itemEntity = itemEntity;
	}

//	public Integer getMeasurementUnitId() {
//		return measurementUnitId;
//	}
//
//	public void setMeasurementUnitId(Integer measurementUnitId) {
//		this.measurementUnitId = measurementUnitId;
//	}

	
	public String getBatchNo() {
		return batchNo;
	}

	public MastersEntity getMeasurementUnitMasterEntity() {
		return measurementUnitMasterEntity;
	}

	public void setMeasurementUnitMasterEntity(
			MastersEntity measurementUnitMasterEntity) {
		this.measurementUnitMasterEntity = measurementUnitMasterEntity;
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
	
	


}


