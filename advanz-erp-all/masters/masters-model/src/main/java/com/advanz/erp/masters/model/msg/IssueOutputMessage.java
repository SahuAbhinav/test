package com.advanz.erp.masters.model.msg;

import java.sql.Timestamp;
import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.IssueMasterDTO;

public class IssueOutputMessage extends AdvanzErpBaseOutputMessage {

	private IssueMasterDTO issueMasterDTO;

	private List<IssueMasterDTO> issueMasterDTOList;

	private Integer issueSeriesNo;

	private Timestamp issueSeriesDate;

	public Timestamp getIssueSeriesDate() {
		return issueSeriesDate;
	}

	public void setIssueSeriesDate(Timestamp issueSeriesDate) {
		this.issueSeriesDate = issueSeriesDate;
	}

	public IssueMasterDTO getIssueMasterDTO() {
		return issueMasterDTO;
	}

	public void setIssueMasterDTO(IssueMasterDTO issueMasterDTO) {
		this.issueMasterDTO = issueMasterDTO;
	}

	public List<IssueMasterDTO> getIssueMasterDTOList() {
		return issueMasterDTOList;
	}

	public void setIssueMasterDTOList(List<IssueMasterDTO> issueMasterDTOList) {
		this.issueMasterDTOList = issueMasterDTOList;
	}

	public Integer getIssueSeriesNo() {
		return issueSeriesNo;
	}

	public void setIssueSeriesNo(Integer issueSeriesNo) {
		this.issueSeriesNo = issueSeriesNo;
	}

}
