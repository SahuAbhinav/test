package com.advanz.erp.masters.model;

import java.sql.Time;
import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class MelterLogSummaryDTO extends BaseDTO {
	private Integer sno;
	private Date logDate;
	private Time logTime;
	private Double logKwh;
	private Double waterTemp;
	private Double regulerTemp;
	private Double transformerTemp;
	private String melterLogRemark;

	private String trollyNumber;
	private Double quantity;
	
	private Date fromDate;
	private Date toDate;
	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public Time getLogTime() {
		return logTime;
	}

	public void setLogTime(Time logTime) {
		this.logTime = logTime;
	}

	public Double getLogKwh() {
		return logKwh;
	}

	public void setLogKwh(Double logKwh) {
		this.logKwh = logKwh;
	}

	public Double getWaterTemp() {
		return waterTemp;
	}

	public void setWaterTemp(Double waterTemp) {
		this.waterTemp = waterTemp;
	}

	public Double getRegulerTemp() {
		return regulerTemp;
	}

	public void setRegulerTemp(Double regulerTemp) {
		this.regulerTemp = regulerTemp;
	}

	public Double getTransformerTemp() {
		return transformerTemp;
	}

	public void setTransformerTemp(Double transformerTemp) {
		this.transformerTemp = transformerTemp;
	}

	public String getMelterLogRemark() {
		return melterLogRemark;
	}

	public void setMelterLogRemark(String melterLogRemark) {
		this.melterLogRemark = melterLogRemark;
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

	public String getTrollyNumber() {
		return trollyNumber;
	}

	public void setTrollyNumber(String trollyNumber) {
		this.trollyNumber = trollyNumber;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public  String toString()
	   {
		 return "MelterLogSummaryEntity=[sno"+sno+"logDate"+logDate+"logTime"+logTime+"logKwh"+logKwh+"waterTemp"+waterTemp+"regulerTemp"+regulerTemp+"transformerTemp"+transformerTemp+"melterLogRemark"+melterLogRemark+"]";  
	   }
}
