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
@Table(name = "t_return_gate_pass_detail")
public class ReturnGetPassDetailEntity extends BaseEntity {

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
	
	@Column(name = "return_gate_pass_auto_id")
	private Integer returnGatePassAutoId;

	@Column(name = "return_gate_pass_number")
	private String returnGatePassNumber;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private ItemEntity itemEntity;

	@Column(name = "measurement_Unit_id")
	private Integer measurementUnitId;

	@Column(name = "return_gate_pass_quantity")
	private Double returnGatePassQuantity;

	
	@Column(name = "remark")
	private String remark;

	
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


	public Integer getReturnGatePassAutoId() {
		return returnGatePassAutoId;
	}


	public void setReturnGatePassAutoId(Integer returnGatePassAutoId) {
		this.returnGatePassAutoId = returnGatePassAutoId;
	}


	public String getReturnGatePassNumber() {
		return returnGatePassNumber;
	}


	public void setReturnGatePassNumber(String returnGatePassNumber) {
		this.returnGatePassNumber = returnGatePassNumber;
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


	public Double getReturnGatePassQuantity() {
		return returnGatePassQuantity;
	}


	public void setReturnGatePassQuantity(Double returnGatePassQuantity) {
		this.returnGatePassQuantity = returnGatePassQuantity;
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



}
