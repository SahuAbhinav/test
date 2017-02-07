package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.advanz.erp.common.entity.jpa.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="t_issue_return_detail")
public class IssueReturnDetailEntity extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sno")
	private Integer sno;
	
	@Column(name="transaction_series")
	private String transactionSeries;
	
	@Column(name="issue_return_auto_id")
	private Integer issueReturnAutoId;
	
	@Column(name="issue_return_number")
	private String issueReturnNumber;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private ItemEntity itemEntity;
	
	
	@Column(name="measurement_Unit_id")
	private Integer measurmentUnitId;
	
	@Column(name="issue_return_quantity")
	private Double issueReturnQuantity;
	
	@Column(name="store_location_id")
	private Integer storeLocationId;
	
	@Column(name="issue_number")
	private String issueNumber;
	
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
	public Integer getIssueReturnAutoId() {
		return issueReturnAutoId;
	}
	public void setIssueReturnAutoId(Integer issueReturnAutoId) {
		this.issueReturnAutoId = issueReturnAutoId;
	}
	public String getIssueReturnNumber() {
		return issueReturnNumber;
	}
	public void setIssueReturnNumber(String issueReturnNumber) {
		this.issueReturnNumber = issueReturnNumber;
	}
	
	
	public ItemEntity getItemEntity() {
		return itemEntity;
	}
	public void setItemEntity(ItemEntity itemEntity) {
		this.itemEntity = itemEntity;
	}
	
	public Integer getMeasurmentUnitId() {
		return measurmentUnitId;
	}
	public void setMeasurmentUnitId(Integer measurmentUnitId) {
		this.measurmentUnitId = measurmentUnitId;
	}
	public Double getIssueReturnQuantity() {
		return issueReturnQuantity;
	}
	public void setIssueReturnQuantity(Double issueReturnQuantity) {
		this.issueReturnQuantity = issueReturnQuantity;
	}
	
	
	public Integer getStoreLocationId() {
		return storeLocationId;
	}
	public void setStoreLocationId(Integer storeLocationId) {
		this.storeLocationId = storeLocationId;
	}
	public String getIssueNumber() {
		return issueNumber;
	}
	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}
	
	

}
