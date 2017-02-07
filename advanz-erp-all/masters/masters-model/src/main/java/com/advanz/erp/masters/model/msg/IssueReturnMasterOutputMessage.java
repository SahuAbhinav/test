package com.advanz.erp.masters.model.msg;

import java.sql.Timestamp;
import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.IssueReturnMasterDTO;

@SuppressWarnings("serial")
public class IssueReturnMasterOutputMessage extends AdvanzErpBaseOutputMessage {
	private IssueReturnMasterDTO issueReturnMasterDTO;
	private List<IssueReturnMasterDTO> issueReturnMasterDTOList;
	private Integer issueReturnSeriesNo;
	private Timestamp issueReturnSeriesDate;

	public Timestamp getIssueReturnSeriesDate() {
		return issueReturnSeriesDate;
	}

	public void setIssueReturnSeriesDate(Timestamp issueReturnSeriesDate) {
		this.issueReturnSeriesDate = issueReturnSeriesDate;
	}

	public Integer getIssueReturnSeriesNo() {
		return issueReturnSeriesNo;
	}

	public void setIssueReturnSeriesNo(Integer issueReturnSeriesNo) {
		this.issueReturnSeriesNo = issueReturnSeriesNo;
	}

	public List<IssueReturnMasterDTO> getIssueReturnMasterDTOList() {
		return issueReturnMasterDTOList;
	}

	public void setIssueReturnMasterDTOList(
			List<IssueReturnMasterDTO> issueReturnMasterDTOList) {
		this.issueReturnMasterDTOList = issueReturnMasterDTOList;
	}

	public IssueReturnMasterDTO getIssueReturnMasterDTO() {
		return issueReturnMasterDTO;
	}

	public void setIssueReturnMasterDTO(
			IssueReturnMasterDTO issueReturnMasterDTO) {
		this.issueReturnMasterDTO = issueReturnMasterDTO;
	}

}
