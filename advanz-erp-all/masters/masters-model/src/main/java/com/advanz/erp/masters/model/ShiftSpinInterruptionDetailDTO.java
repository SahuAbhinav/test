package com.advanz.erp.masters.model;

import java.sql.Time;
import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class ShiftSpinInterruptionDetailDTO extends BaseDTO {

	private Integer sno;

	private Integer shiftReportId;

	private Time spinFromTime;

	private Time spinToTime;

	private Time spinDuration;

	private String spinReason;

	public Time getSpinToTime() {
		return spinToTime;
	}

	public void setSpinToTime(Time spinToTime) {
		this.spinToTime = spinToTime;
	}

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
		return "ShiftSpinInterruptionDetailDTO [sno=" + sno
				+ ", shiftReportId=" + shiftReportId + ", spinFromTime="
				+ spinFromTime + ", spinDuration="
				+ spinDuration + ", spinReason=" + spinReason + "]";
	}

}
