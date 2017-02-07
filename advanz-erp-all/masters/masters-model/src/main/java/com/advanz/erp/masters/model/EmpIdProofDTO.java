package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

public class EmpIdProofDTO extends BaseDTO {

	private Integer idProofTypeId;
	
	private String idProofType;

	public Integer getIdProofTypeId() {
		return idProofTypeId;
	}

	public void setIdProofTypeId(Integer idProofTypeId) {
		this.idProofTypeId = idProofTypeId;
	}

	public String getIdProofType() {
		return idProofType;
	}

	public void setIdProofType(String idProofType) {
		this.idProofType = idProofType;
	}

}
