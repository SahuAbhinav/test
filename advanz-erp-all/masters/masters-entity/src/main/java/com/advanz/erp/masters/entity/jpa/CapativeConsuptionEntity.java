package com.advanz.erp.masters.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.advanz.erp.common.entity.jpa.BaseEntity;
@SuppressWarnings("serial")
@Entity
@Table(name="t_capative_consuption")
public class CapativeConsuptionEntity extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sno")
	private Integer sno;
	
	@Column(name="source_item_id")
	private Integer sourceItemId;
	
	@Column(name="source_quantity")
	private Double sourceQuantity;
	
	@Column(name="target_item_id")
	private Integer targetItemId;
	
	@Column(name="target_quantity")
	private Double targetQuantity;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="entered_date")
	private Date enteredDate;
	
	@Column(name="capative_consumption_id")
	private Integer capativeConsumptionId;

	
	@Column(name="capative_consumption_number")
	private String capativeConsumptionNumber;

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public Integer getSourceItemId() {
		return sourceItemId;
	}

	public void setSourceItemId(Integer sourceItemId) {
		this.sourceItemId = sourceItemId;
	}

	public Double getSourceQuantity() {
		return sourceQuantity;
	}

	public void setSourceQuantity(Double sourceQuantity) {
		this.sourceQuantity = sourceQuantity;
	}

	public Integer getTargetItemId() {
		return targetItemId;
	}

	public void setTargetItemId(Integer targetItemId) {
		this.targetItemId = targetItemId;
	}

	public Double getTargetQuantity() {
		return targetQuantity;
	}

	public void setTargetQuantity(Double targetQuantity) {
		this.targetQuantity = targetQuantity;
	}

	public Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	public Integer getCapativeConsumptionId() {
		return capativeConsumptionId;
	}

	public void setCapativeConsumptionId(Integer capativeConsumptionId) {
		this.capativeConsumptionId = capativeConsumptionId;
	}

	public String getCapativeConsumptionNumber() {
		return capativeConsumptionNumber;
	}

	public void setCapativeConsumptionNumber(String capativeConsumptionNumber) {
		this.capativeConsumptionNumber = capativeConsumptionNumber;
	}
	
	
}
