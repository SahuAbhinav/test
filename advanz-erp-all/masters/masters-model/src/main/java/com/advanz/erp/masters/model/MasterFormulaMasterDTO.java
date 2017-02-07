package com.advanz.erp.masters.model;


import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;
@SuppressWarnings("serial")
public class MasterFormulaMasterDTO extends BaseDTO{

	private Integer masterFormulaAutoId;

	private ItemDTO itemDTO;

	private Double standardBatchSize;

	private Double formulaBatchSize;

	private Integer activeStatus;

	private String formulaRemark;
	
	private Date creationDate;
	
	private Date modifiedDate;
	
	private String createdBy;
	
	private String approvedBy;
	
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	private List<MasterFormulaDetailDTO> masterFormulaDetailDTOList;
	
	
	

	public List<MasterFormulaDetailDTO> getMasterFormulaDetailDTOList() {
		return masterFormulaDetailDTOList;
	}

	public void setMasterFormulaDetailDTOList(
			List<MasterFormulaDetailDTO> masterFormulaDetailDTOList) {
		this.masterFormulaDetailDTOList = masterFormulaDetailDTOList;
	}

	public Integer getMasterFormulaAutoId() {
		return masterFormulaAutoId;
	}

	public void setMasterFormulaAutoId(Integer masterFormulaAutoId) {
		this.masterFormulaAutoId = masterFormulaAutoId;
	}

	public ItemDTO getItemDTO() {
		return itemDTO;
	}

	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
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

	public String getFormulaRemark() {
		return formulaRemark;
	}

	public void setFormulaRemark(String formulaRemark) {
		this.formulaRemark = formulaRemark;
	}

}
