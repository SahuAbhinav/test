package com.advanz.erp.masters.entity.jpa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "M_SALARY_HEAD")
public class SalaryHeadEntity extends BaseEntity{

	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="salary_head_id")
	private Integer salaryHeadId;
	
	@Column(name="salary_head_code")
	private String salaryHeadCode;
	
	@Column(name="salary_head_name")
	private String salaryHeadName;
	
	@Column(name="head_type")
	private String headType;
	
	@Column(name="type")
	private String type;
	
	@Column(name="payable_type")
	private String payableType;
	
	@Column(name="payable_month")
	private String payableMonth;
	
	@Column(name="professional_tax_flag")
	private Integer professionalTaxFlag;
	
	@Column(name="basic_salary_flag")
	private Integer basicSalaryFlag;
	
	@Column(name="description")
	private String description;
	
	@Column(name="base_head_per")
	private Double baseHeadPer;
	
	@Column(name="salary_head_sequence")
	private Integer salaryHeadSequence;
	
	
	 public Double getBaseHeadPer() {
		return baseHeadPer;
	}

	public void setBaseHeadPer(Double baseHeadPer) {
		this.baseHeadPer = baseHeadPer;
	}

	@ManyToMany(fetch=FetchType.EAGER) 
	@JoinTable(name="m_salary_head_detail", joinColumns = {@JoinColumn(name="salary_head_id")}, inverseJoinColumns={@JoinColumn(name="base_head_id")})  
	public List<SalaryHeadEntity> baseHeadsList;
	
	 
	@Column(name="pf_employee_flag")
	private Integer pfEmployeeFlag;
	
	@Column(name="esi_employee_flag")
	private Integer esiEmployeeFlag;
	
	public List<SalaryHeadEntity> getBaseHeadsList() {
		return baseHeadsList;
	}

	public void setBaseHeadsList(List<SalaryHeadEntity> baseHeadsList) {
		this.baseHeadsList = baseHeadsList;
	}

	public Integer getSalaryHeadId() {
		return salaryHeadId;
	}

	public void setSalaryHeadId(Integer salaryHeadId) {
		this.salaryHeadId = salaryHeadId;
	}

	public String getSalaryHeadCode() {
		return salaryHeadCode;
	}

	public void setSalaryHeadCode(String salaryHeadCode) {
		this.salaryHeadCode = salaryHeadCode;
	}

	public String getSalaryHeadName() {
		return salaryHeadName;
	}

	public void setSalaryHeadName(String salaryHeadName) {
		this.salaryHeadName = salaryHeadName;
	}

	public String getHeadType() {
		return headType;
	}

	public void setHeadType(String headType) {
		this.headType = headType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPayableType() {
		return payableType;
	}

	public void setPayableType(String payableType) {
		this.payableType = payableType;
	}

	public String getPayableMonth() {
		return payableMonth;
	}

	public void setPayableMonth(String payableMonth) {
		this.payableMonth = payableMonth;
	}

	public Integer getProfessionalTaxFlag() {
		return professionalTaxFlag;
	}

	public void setProfessionalTaxFlag(Integer professionalTaxFlag) {
		this.professionalTaxFlag = professionalTaxFlag;
	}

	public Integer getBasicSalaryFlag() {
		return basicSalaryFlag;
	}

	public void setBasicSalaryFlag(Integer basicSalaryFlag) {
		this.basicSalaryFlag = basicSalaryFlag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSalaryHeadSequence() {
		return salaryHeadSequence;
	}

	public void setSalaryHeadSequence(Integer salaryHeadSequence) {
		this.salaryHeadSequence = salaryHeadSequence;
	}

	public Integer getPfEmployeeFlag() {
		return pfEmployeeFlag;
	}

	public void setPfEmployeeFlag(Integer pfEmployeeFlag) {
		this.pfEmployeeFlag = pfEmployeeFlag;
	}

	public Integer getEsiEmployeeFlag() {
		return esiEmployeeFlag;
	}

	public void setEsiEmployeeFlag(Integer esiEmployeeFlag) {
		this.esiEmployeeFlag = esiEmployeeFlag;
	}
	
	
//	@Override
//	public Integer getUid() {
//		// TODO Auto-generated method stub
//	  return salaryHeadId;
//	}
//	
//	@Override
//	public void setUid(Integer uid) {
//		// TODO Auto-generated method stub
//		salaryHeadId=uid;		
//	}

	
	
	
	
}
