package com.advanz.erp.masters.model;

import java.util.Date;
@SuppressWarnings("serial")
public class EmailDetailDTO {
	private String emailDetail;
	private Date emailDate;
	public String getEmailDetail() {
		return emailDetail;
	}
	public void setEmailDetail(String emailDetail) {
		this.emailDetail = emailDetail;
	}
	public Date getEmailDate() {
		return emailDate;
	}
	public void setEmailDate(Date emailDate) {
		this.emailDate = emailDate;
	}
	
}
