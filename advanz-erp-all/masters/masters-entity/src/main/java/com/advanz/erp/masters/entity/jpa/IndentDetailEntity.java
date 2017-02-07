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

@Entity
@Table(name="t_indent_detail")
public class IndentDetailEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sno")
	private Integer sno;
	
	@Column(name="transaction_series")
	private String transactionSeries;
	
	@Column(name="indent_auto_id")
	private Integer indentAutoId;
	
	@Column(name="indent_number")
	private String indentNumber;
	
	@ManyToOne
	@JoinColumn(name="measurement_Unit_id")
	private MastersEntity measurementUnitMasterEntity;
	
	@Column(name="department_id")
	private Integer departmentId;
	
	@Column(name="indent_qty")
	private Double indentQty;
	
	@Column(name="pending_qty")
	private Double pendingQty;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private ItemEntity itemEntity;
	
	@Column(name="indent_remark")
	private String indentRemark;
	
	@Column(name="full_fill")
	private Integer fullFill;
	
	@Column(name="item_make")
	private String itemMake;
	
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


	public Integer getIndentAutoId() {
		return indentAutoId;
	}


	public void setIndentAutoId(Integer indentAutoId) {
		this.indentAutoId = indentAutoId;
	}


	public String getIndentNumber() {
		return indentNumber;
	}


	public void setIndentNumber(String indentNumber) {
		this.indentNumber = indentNumber;
	}


	public Integer getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}


	public Double getIndentQty() {
		return indentQty;
	}


	public void setIndentQty(Double indentQty) {
		this.indentQty = indentQty;
	}


	public String getIndentRemark() {
		return indentRemark;
	}


	public void setIndentRemark(String indentRemark) {
		this.indentRemark = indentRemark;
	}


	public Double getPendingQty() {
		return pendingQty;
	}


	public void setPendingQty(Double pendingQty) {
		this.pendingQty = pendingQty;
	}


	public Integer getFullFill() {
		return fullFill;
	}


	public void setFullFill(Integer fullFill) {
		this.fullFill = fullFill;
	}


	public String getItemMake() {
		return itemMake;
	}


	public void setItemMake(String itemMake) {
		this.itemMake = itemMake;
	}



}
