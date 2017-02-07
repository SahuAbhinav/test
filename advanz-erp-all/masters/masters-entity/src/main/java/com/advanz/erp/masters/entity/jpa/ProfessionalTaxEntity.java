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
@Table(name="m_professional_tax")
public class ProfessionalTaxEntity extends BaseEntity
{

	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="ptax_Id")
	private Integer ptaxId;
	@Column(name="slab_Name")
	private String slabName;
	@Column(name="ptax_Code")
	private String ptaxCode;
	@Column(name="from_Amount")
	private Double fromAmount;
	@Column(name="to_Amount")
	private Double toAmount;
	
	@ManyToOne
	@JoinColumn(name="ptax_deduct_type_id")
	private ProfessionalTaxDeductTypeEntity professionalTaxDeductTypeEntity ;
	
	public ProfessionalTaxDeductTypeEntity getProfessionalTaxDeductTypeEntity() {
		return professionalTaxDeductTypeEntity;
	}

	public void setProfessionalTaxDeductTypeEntity(
			ProfessionalTaxDeductTypeEntity professionalTaxDeductTypeEntity) {
		this.professionalTaxDeductTypeEntity = professionalTaxDeductTypeEntity;
	}

	@Column(name="deduct_Amount")
	private String deductAmount;
	
	public Integer getPtaxId() {
		return ptaxId;
	}

	public void setPtaxId(Integer ptaxId) {
		this.ptaxId = ptaxId;
	}

	public String getSlabName() {
		return slabName;
	}

	public void setSlabName(String slabName) {
		this.slabName = slabName;
	}

	public String getPtaxCode() {
		return ptaxCode;
	}

	public void setPtaxCode(String ptaxCode) {
		this.ptaxCode = ptaxCode;
	}

	public Double getFromAmount() {
		return fromAmount;
	}

	public void setFromAmount(Double fromAmount) {
		this.fromAmount = fromAmount;
	}

	public Double getToAmount() {
		return toAmount;
	}

	public void setToAmount(Double toAmount) {
		this.toAmount = toAmount;
	}


	public String getDeductAmount() {
		return deductAmount;
	}

	public void setDeductAmount(String deductAmount) {
		this.deductAmount = deductAmount;
	}

	@Override
	public String toString() {
		return "ProfessionalTaxDTO [ptaxId=" + ptaxId + ", slabName=" + slabName + ", ptaxDeductType=" + ptaxCode + ", fromAmount=" + fromAmount + ", toAmount=" + toAmount + ",professionalTaxDeductTypeEntity="+professionalTaxDeductTypeEntity+"]";
	}
	
	
	
	
	
	
	
	
	
}
