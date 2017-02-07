package com.advanz.erp.masters.entity.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_employee_id_proof")
public class EmpIdProofEntity  implements Serializable{
	
	@Id @GeneratedValue(generator="system-incr")
	@GenericGenerator(name="system-incr", strategy = "increment")
	@Column(name="ID_PROOF_TYPE_ID")
	private Integer idProofTypeId;
	
	@Column(name="ID_PROOF_TYPE")
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
