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
@SuppressWarnings("serial")
@Entity
@Table(name = "m_master_formula_detail")
public class MasterFormulaDetailEntity extends BaseEntity {
	/**
	 * 
	 */
	
	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name = "sno")
	private Integer sno;
	@Column(name = "master_formula_auto_id")
	private Integer masterFormulaAutoId;
	@Column(name = "item_group_flag_id")
	private Integer itemGroupFlagId;
	@Column(name = "item_sequence_number")
	private Integer itemSequenceNumber;

	@ManyToOne
	@JoinColumn(name = "item_id")
	//@Column(name = "item_id")
	private ItemEntity itemEntity;
	@Column(name = "quantity")
	private Double quantity;
	@Column(name = "item_remark")
	private String itemRemark;

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public Integer getMasterFormulaAutoId() {
		return masterFormulaAutoId;
	}

	public void setMasterFormulaAutoId(Integer masterFormulaAutoId) {
		this.masterFormulaAutoId = masterFormulaAutoId;
	}

	public Integer getItemGroupFlagId() {
		return itemGroupFlagId;
	}

	public void setItemGroupFlagId(Integer itemGroupFlagId) {
		this.itemGroupFlagId = itemGroupFlagId;
	}

	public Integer getItemSequenceNumber() {
		return itemSequenceNumber;
	}

	public void setItemSequenceNumber(Integer itemSequenceNumber) {
		this.itemSequenceNumber = itemSequenceNumber;
	}

	public ItemEntity getItemEntity() {
		return itemEntity;
	}

	public void setItemEntity(ItemEntity itemEntity) {
		this.itemEntity = itemEntity;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getItemRemark() {
		return itemRemark;
	}

	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
	}

}
