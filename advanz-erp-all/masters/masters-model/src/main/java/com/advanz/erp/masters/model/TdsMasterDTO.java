package com.advanz.erp.masters.model;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;
@SuppressWarnings("serial")
public class TdsMasterDTO extends BaseDTO {
	private Integer sno;
	private String slabName;
	private String code;
	private Date fromDate;
	private Date toDate;
	private String deductType;
	private Double tdsPerc;
	private Double eductionCessAmnt;
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