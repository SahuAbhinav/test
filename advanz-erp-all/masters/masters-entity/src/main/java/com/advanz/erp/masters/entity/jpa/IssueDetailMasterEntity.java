package com.advanz.erp.masters.entity.jpa;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name="t_issue_detail")
public class IssueDetailMasterEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sno")
	private Integer sno;
	
	@Column(name="transaction_series")
	private String transactionSeries;
	
	@Column(name="issue_auto_id")
	private Integer issueAutoId;
	
	@Column(name="issue_number")
	private String issueNumber;
	
//	@Column(name="measurement_Unit_id")
//	private Integer measurementUnitId;
	
	@ManyToOne
	@JoinColumn(name="measurement_Unit_id")
	private MastersEntity measurementUnitMasterEntity;
	
	@Column(name="indent_quantity")
	private Double indentQuantity;
	

	@Column(name="required_quantity")
	private Double requiredQuantity;
	
	
	@Column(name="issue_quantity")
	private Double issueQuantity;
	
	
	@Column(name="store_location_id")
	private Integer storeLocationId;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private ItemEntity itemEntity;
	
	@Column(name="issue_type")
	private String issueType;
	
	@Column(name="pending_quantity")
	private Double pendingQuantity;
	
	@Column(name="master_head_id")
	private Integer masterHeadId;
	
	@Column(name="master_section_id")
	private Integer masterSectionId;
	
	@Column(name="grn_rate")
	private Double grnRate;
	
	@Column(name="issue_value")
	private Double issueValue;
	
	public MastersEntity getMeasurementUnitMasterEntity() {
		return measurementUnitMasterEntity;
	}


	public void setMeasurementUnitMasterEntity(
			MastersEntity measurementUnitMasterEntity) {
		this.measurementUnitMasterEntity = measurementUnitMasterEntity;
	}


	public ItemEntity getItemEntity() {
		return itemEntity;
	}


	public void setItemEntity(ItemEntity itemEntity) {
		this.itemEntity = itemEntity;
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


	public Integer getIssueAutoId() {
		return issueAutoId;
	}


	public void setIssueAutoId(Integer issueAutoId) {
		this.issueAutoId = issueAutoId;
	}


	public String getIssueNumber() {
		return issueNumber;
	}


	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}


	
	


//	public Integer getMeasurementUnitId() {
//		return measurementUnitId;
//	}
//
//
//	public void setMeasurementUnitId(Integer measurementUnitId) {
//		this.measurementUnitId = measurementUnitId;
//	}


	public Double getIndentQuantity() {
		return indentQuantity;
	}


	public void setIndentQuantity(Double indentQuantity) {
		this.indentQuantity = indentQuantity;
	}


	public Double getRequiredQuantity() {
		return requiredQuantity;
	}


	public void setRequiredQuantity(Double requiredQuantity) {
		this.requiredQuantity = requiredQuantity;
	}


	public Double getIssueQuantity() {
		return issueQuantity;
	}


	public void setIssueQuantity(Double issueQuantity) {
		this.issueQuantity = issueQuantity;
	}


	public Integer getStoreLocationId() {
		return storeLocationId;
	}


	public void setStoreLocationId(Integer storeLocationId) {
		this.storeLocationId = storeLocationId;
	}


	public String getIssueType() {
		return issueType;
	}


	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}


	public Double getPendingQuantity() {
		return pendingQuantity;
	}


	public void setPendingQuantity(Double pendingQuantity) {
		this.pendingQuantity = pendingQuantity;
	}


	public Integer getMasterHeadId() {
		return masterHeadId;
	}


	public void setMasterHeadId(Integer masterHeadId) {
		this.masterHeadId = masterHeadId;
	}


	

	public Integer getMasterSectionId() {
		return masterSectionId;
	}


	public Double getGrnRate() {
		return grnRate;
	}


	public void setGrnRate(Double grnRate) {
		this.grnRate = grnRate;
	}


	public Double getIssueValue() {
		return issueValue;
	}


	public void setIssueValue(Double issueValue) {
		this.issueValue = issueValue;
	}


	public void setMasterSectionId(Integer masterSectionId) {
		this.masterSectionId = masterSectionId;
	}


	@Override
	public String toString() {
		return "IssueDetailMasterEntity [sno=" + sno + ", transactionSeries="
				+ transactionSeries + ", issueAutoId=" + issueAutoId
				+ ", issueNumber=" + issueNumber + ", indentQuantity=" + indentQuantity + ", requiredQuantity="
				+ requiredQuantity + ", issueQuantity=" + issueQuantity
				+ ", storeLocationId=" + storeLocationId + "]";
	}


	


	
	

}
