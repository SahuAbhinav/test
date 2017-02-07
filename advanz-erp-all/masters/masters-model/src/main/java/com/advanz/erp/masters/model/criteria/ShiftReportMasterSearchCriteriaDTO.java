package com.advanz.erp.masters.model.criteria;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class ShiftReportMasterSearchCriteriaDTO extends BaseDTO {
	
	
	private String runNo;
	private Date shifReportDate;
	private Date fromDate;
	private Date toDate;
	
	private Integer previous;
	private Integer next;
	public String getRunNo() {
		return runNo;
	}
	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}
	public Date getShifReportDate() {
		return shifReportDate;
	}
	public void setShifReportDate(Date shifReportDate) {
		this.shifReportDate = shifReportDate;
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
	
	public Integer getPrevious() {
		return previous;
	}
	public void setPrevious(Integer previous) {
		this.previous = previous;
	}
	public Integer getNext() {
		return next;
	}
	public void setNext(Integer next) {
		this.next = next;
	}
	@Override
	public String toString() {
		return "ShiftReportMasterSearchCriteriaDTO [runNo=" + runNo + ", shifReportDate=" + shifReportDate
				+ "]";
	}
	
	
	
	
	

}
