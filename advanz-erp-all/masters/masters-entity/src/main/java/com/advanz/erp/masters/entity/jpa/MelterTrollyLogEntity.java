package com.advanz.erp.masters.entity.jpa;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@SuppressWarnings("serial")

@Entity
@Table(name = "t_melter_trolly_log")
public class MelterTrollyLogEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sno")
	private Integer sno;
	@Column(name = "log_date")
	private Date logDate;
	

	@Column(name = "log_time")
	private Time logTime;
	@Column(name="trolly_number")
	private String trollyNumber;
	@Column(name="melter_trolly_remark")
	private String melterTrollyRemark;

	


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

   
	public  String toString()
	   {
		 return "MelterLogSummaryEntity=[sno"+sno+"logDate"+logDate+"logTime"+logTime+"trollyNumber"+melterTrollyRemark+"waterTemp"+melterTrollyRemark+"]";  
	   }


}
