package com.advanz.erp.masters.entity.jpa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.advanz.erp.common.entity.jpa.BaseEntity;
@SuppressWarnings("serial")
@Entity
@Table(name = "m_master_formula")
public class MasterFormulaMasterEntity extends BaseEntity {
	
	@Id
	@GeneratedValue(generator = "system-incr")
	@GenericGenerator(name = "system-incr", strategy = "increment")
	@Column(name = "master_formula_auto_id")
	private Integer masterFormulaAutoId;
	@ManyToOne
	@JoinColumn(name = "item_id")
	//@Column(name = "item_id")
	private ItemEntity itemEntity;
	@Column(name = "standard_batch_size")
	private Double standardBatchSize;
	@Column(name = "formula_batch_size")
	private Double formulaBatchSize;
	@Column(name = "active_status")
	private Integer activeStatus;
	
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "approved_by")
	private String approvedBy;
	
	@Column(name = "formula_remark")
	private String formulaRemark;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="master_formula_auto_id")
	private List<MasterFormulaDetailEntity> masterFormulaDetailEntity;
	

	public List<MasterFormulaDetailEntity> getMasterFormulaDetailEntity() {
		return masterFormulaDetailEntity;
	}

	public void setMasterFormulaDetailEntity(
			List<MasterFormulaDetailEntity> masterFormulaDetailEntity) {
		this.masterFormulaDetailEntity = masterFormulaDetailEntity;
	}

	public Integer getMasterFormulaAutoId() {
		return masterFormulaAutoId;
	}

	public void setMasterFormulaAutoId(Integer masterFormulaAutoId) {
		this.masterFormulaAutoId = masterFormulaAutoId;
	}

	public ItemEntity getItemEntity() {
		return itemEntity;
	}

	public void setItemEntity(ItemEntity itemEntity) {
		this.itemEntity = itemEntity;
	}

	public Double getStandardBatchSize() {
		return standardBatchSize;
	}

	public void setStandardBatchSize(Double standardBatchSize) {
		this.standardBatchSize = standardBatchSize;
	}

	public Double getFormulaBatchSize() {
		return formulaBatchSize;
	}

	public void setFormulaBatchSize(Double formulaBatchSize) {
		this.formulaBatchSize = formulaBatchSize;
	}

	public Integer getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}
	

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getFormulaRemark() {
		return formulaRemark;
	}

	public void setFormulaRemark(String formulaRemark) {
		this.formulaRemark = formulaRemark;
	}

}
