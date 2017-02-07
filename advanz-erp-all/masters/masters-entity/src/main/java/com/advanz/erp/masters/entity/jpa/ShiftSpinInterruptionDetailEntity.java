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
@Table(name="t_shift_spin_interruption_detail")
public class ShiftSpinInterruptionDetailEntity extends BaseEntity {
	
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sno")
		private Integer sno;
	

	@Column(name="shift_report_id")
	private Integer shiftReportId;
	
	@Column(name="spin_from_time")
	private Time spinFromTime;
	
	@Column(name="spin_to_time")
	private Time spinToTime;
	
	@Column(name="spin_duration")
	private Time spinDuration;
	
	
	@Column(name="spin_reason")
	private String spinReason;


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


	public Time getSpinFromTime() {
		return spinFromTime;
	}


	public void setSpinFromTime(Time spinFromTime) {
		this.spinFromTime = spinFromTime;
	}


	public Time getSpinToTime() {
		return spinToTime;
	}


	public void setSpinToTime(Time spinToTime) {
		this.spinToTime = spinToTime;
	}


	public Time getSpinDuration() {
		return spinDuration;
	}


	public void setSpinDuration(Time spinDuration) {
		this.spinDuration = spinDuration;
	}


	public String getSpinReason() {
		return spinReason;
	}


	public void setSpinReason(String spinReason) {
		this.spinReason = spinReason;
	}


	@Override
	public String toString() {
		return "ShiftSpinInterruptionDetailEntity [sno=" + sno
				+ ", shiftReportId=" + shiftReportId + ", spinFromTime="
				+ spinFromTime + ", spinToTime=" + spinToTime + ", spinDuration="
				+ spinDuration + ", spinReason=" + spinReason + "]";
	}



	
	
	
	
	

}
