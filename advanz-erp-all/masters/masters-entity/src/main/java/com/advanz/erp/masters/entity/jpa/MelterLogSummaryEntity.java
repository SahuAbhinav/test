package com.advanz.erp.masters.entity.jpa;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@SuppressWarnings("serial")

@Entity
@Table(name = "t_melter_log_summ")
public class MelterLogSummaryEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sno")
	private Integer sno;
	@Column(name = "log_date")
	private Date logDate;
	@Column(name = "log_time")
	private Time logTime;
	@Column(name = "log_kwh")
	private Double logKwh;
	@Column(name = "water_temp")
	private Double waterTemp;
	@Column(name = "regulator_temp")
	private Double regulerTemp;
	@Column(name = "transformer_temp")
	private Double transformerTemp;
	@Column(name = "melter_log_remark")
	private String melterLogRemark;
	
	@Column(name = "trolly_number")
	private String trollyNumber;
	
	@Column(name = "quantity")
	private Double quantity;
	
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
