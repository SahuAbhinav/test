package com.advanz.erp.masters.model;

import java.sql.Time;
import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class ShiftEngInterruptionDetailDTO extends BaseDTO {

	private Integer sno;

	private Integer shiftReportId;

	private Time engFromTime;

	private Time engToTime;

	private Time engDuration;

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
		return "ShiftEngInterruptionDetailDTO [sno=" + sno + ", shiftReportId="
				+ shiftReportId + ", engFromTime=" + engFromTime
				+ ", engToTime=" + engToTime + ", engDuration=" + engDuration
				+ ", engReason=" + engReason + "]";
	}

	

}
