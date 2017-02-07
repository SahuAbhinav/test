package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;



public class IssueTypeMasterDTO extends BaseDTO{	
	private Integer sno;	
	private String issueType;
	
	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	
	
}
