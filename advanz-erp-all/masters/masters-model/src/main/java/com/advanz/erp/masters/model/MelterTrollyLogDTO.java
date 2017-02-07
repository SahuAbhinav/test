package com.advanz.erp.masters.model;

import java.sql.Time;
import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;
@SuppressWarnings("serial")
public class MelterTrollyLogDTO extends BaseDTO {
	private Integer sno;
	private Date logDate;
	private Time logTime;
	private String trollyNumber;
	private String melterTrollyRemark;
	
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
	public String getTrollyNumber() {
		return trollyNumber;
	}
	public void setTrollyNumber(String trollyNumber) {
		this.trollyNumber = trollyNumber;
	}
	public String getMelterTrollyRemark() {
		return melterTrollyRemark;
	}
	public void setMelterTrollyRemark(String melterTrollyRemark) {
		this.melterTrollyRemark = melterTrollyRemark;
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
public  String toString()
   {
	return "MelterLogSummaryEntity=[sno"+sno+"logDate"+logDate+"logTime"+logTime+"trollyNumber"+melterTrollyRemark+"waterTemp"+melterTrollyRemark+"]";  
  }

	

}
