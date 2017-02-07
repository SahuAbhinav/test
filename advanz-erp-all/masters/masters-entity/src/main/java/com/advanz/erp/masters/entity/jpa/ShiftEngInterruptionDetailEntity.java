package com.advanz.erp.masters.entity.jpa;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name="t_shift_eng_interruption_detail")
public class ShiftEngInterruptionDetailEntity extends BaseEntity {
	
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sno")
		private Integer sno;
	

	@Column(name="shift_report_id")
	private Integer shiftReportId;
	
	@Column(name="eng_from_time")
	private Time engFromTime;
	
	@Column(name="eng_to_time")
	private Time engToTime;
	
	@Column(name="eng_duration")
	private Time engDuration;
	
	
	@Column(name="eng_reason")
	private String engReason;


	public Integer getSno() {
		return sno;
	}


	public void setSno(Integer sno) {
		this.sno = sno;
	}


	public Integer getShiftReportId() {
		return shiftReportId;
	}


	public void setShiftReportId(Integer shiftReportId) {
		this.shiftReportId = shiftReportId;
	}


	public Time getEngFromTime() {
		return engFromTime;
	}


	public void setEngFromTime(Time engFromTime) {
		this.engFromTime = engFromTime;
	}


	public Time getEngToTime() {
		return engToTime;
	}


	public void setEngToTime(Time engToTime) {
		this.engToTime = engToTime;
	}


	public Time getEngDuration() {
		return engDuration;
	}


	public void setEngDuration(Time engDuration) {
		this.engDuration = engDuration;
	}


	public String getEngReason() {
		return engReason;
	}


	public void setEngReason(String engReason) {
		this.engReason = engReason;
	}


	@Override
	public String toString() {
		return "ShiftEngInterruptionDetailEntity [sno=" + sno
				+ ", shiftReportId=" + shiftReportId + ", engFromTime="
				+ engFromTime + ", engToTime=" + engToTime + ", engDuration="
				+ engDuration + ", engReason=" + engReason + "]";
	}


	
	
	
	
	

}
