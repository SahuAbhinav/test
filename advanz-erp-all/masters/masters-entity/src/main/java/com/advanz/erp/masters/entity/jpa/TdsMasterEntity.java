package com.advanz.erp.masters.entity.jpa;
import java.util.Date;

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
@Table(name="m_tds_master")
public class TdsMasterEntity extends BaseEntity
{

	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="sno")
	private Integer sno;
	@Column(name="slab_name")
	private String slabName;
	@Column(name="code")
	private String code;
	@Column(name="from_date")
	private Date fromDate;
	@Column(name="to_date")
	private Date toDate;
	
	

	@Column(name="deduct_type")
	private String deductType;
	
	
	@Column(name="tds_perc")
	private Double tdsPerc;
	
	@Column(name="eduction_cess_amnt")
	private Double eductionCessAmnt;
	
	@Column(name="hcess_perc")
	private Double hcessPerc;

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public String getSlabName() {
		return slabName;
	}

	public void setSlabName(String slabName) {
		this.slabName = slabName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getDeductType() {
		return deductType;
	}

	public void setDeductType(String deductType) {
		this.deductType = deductType;
	}

	public Double getTdsPerc() {
		return tdsPerc;
	}

	public void setTdsPerc(Double tdsPerc) {
		this.tdsPerc = tdsPerc;
	}

	public Double getEductionCessAmnt() {
		return eductionCessAmnt;
	}

	public void setEductionCessAmnt(Double eductionCessAmnt) {
		this.eductionCessAmnt = eductionCessAmnt;
	}

	public Double getHcessPerc() {
		return hcessPerc;
	}

	public void setHcessPerc(Double hcessPerc) {
		this.hcessPerc = hcessPerc;
	}
	
	
}
