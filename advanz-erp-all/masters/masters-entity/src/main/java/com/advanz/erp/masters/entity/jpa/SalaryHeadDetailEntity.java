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
@Table(name = "m_salary_head_detail")

public class SalaryHeadDetailEntity extends BaseEntity {
	
	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="sno")
	private Integer sno;
	
//	@Column(name="salary_head_id")
//	private String salaryHeadId;
	
	@Column(name="base_head_id")
	private String baseHeadId;
	

	
	@Column(name="salary_head_id")
	private Integer salaryHeadEntityId;

	public Integer getSno() {
		return sno;
	}

	

	public Integer getSalaryHeadEntityId() {
		return salaryHeadEntityId;
	}



	public void setSalaryHeadEntityId(Integer salaryHeadEntityId) {
		this.salaryHeadEntityId = salaryHeadEntityId;
	}



	public void setSno(Integer sno) {
		this.sno = sno;
	}

	

	public String getBaseHeadId() {
		return baseHeadId;
	}

	public void setBaseHeadId(String baseHeadId) {
		this.baseHeadId = baseHeadId;
	}
	
	
//	
//	@Column(name="description")
//	private String description;
//	
	



	
}
