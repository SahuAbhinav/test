package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "t_gate_pass_detail")
public class GetPassDetailEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name = "sno")
	private Integer sno;

	@Column(name = "transaction_series")
	private String transactionSeries;
	
	@Column(name = "gate_pass_auto_id")
	private Integer gatePassAutoId;

	@Column(name = "gate_pass_number")
	private String gatePassNumber;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private ItemEntity itemEntity;

	@Column(name = "measurement_Unit_id")
	private Integer measurementUnitId;

	@Column(name = "gate_pass_quantity")
	private Double gatePassQuantity;

	@Column(name = "gate_pass_return_quantity")
	private Double gatePassReturnQuantity;

	@Column(name = "remark")
	private String remark;

	@Column(name = "pending_quantity")
	private Double pendingQuantity;
	
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

	public Integer getGatePassAutoId() {
		return gatePassAutoId;
	}

	public void setGatePassAutoId(Integer gatePassAutoId) {
		this.gatePassAutoId = gatePassAutoId;
	}

	public String getGatePassNumber() {
		return gatePassNumber;
	}

	public void setGatePassNumber(String gatePassNumber) {
		this.gatePassNumber = gatePassNumber;
	}

	public ItemEntity getItemEntity() {
		return itemEntity;
	}

	public void setItemEntity(ItemEntity itemEntity) {
		this.itemEntity = itemEntity;
	}

	public Integer getMeasurementUnitId() {
		return measurementUnitId;
	}

	public void setMeasurementUnitId(Integer measurementUnitId) {
		this.measurementUnitId = measurementUnitId;
	}

	public Double getGatePassQuantity() {
		return gatePassQuantity;
	}

	public void setGatePassQuantity(Double gatePassQuantity) {
		this.gatePassQuantity = gatePassQuantity;
	}

	public Double getGatePassReturnQuantity() {
		return gatePassReturnQuantity;
	}

	public void setGatePassReturnQuantity(Double gatePassReturnQuantity) {
		this.gatePassReturnQuantity = gatePassReturnQuantity;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getPendingQuantity() {
		return pendingQuantity;
	}

	public void setPendingQuantity(Double pendingQuantity) {
		this.pendingQuantity = pendingQuantity;
	}

	
	
}
