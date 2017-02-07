package com.advanz.erp.masters.model.msg;

import java.sql.Timestamp;
import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.IndentMasterDTO;

public class IndentOutputMessage extends AdvanzErpBaseOutputMessage {

	private IndentMasterDTO indentMasterDTO;

	private List<IndentMasterDTO> indentMasterDTOList;

	private Integer indentSeriesNo;

	private Timestamp indentSeriesDate;

	public Timestamp getIndentSeriesDate() {
		return indentSeriesDate;
	}

	public void setIndentSeriesDate(Timestamp indentSeriesDate) {
		this.indentSeriesDate = indentSeriesDate;
	}

	public IndentMasterDTO getIndentMasterDTO() {
		return indentMasterDTO;
	}

	public void setIndentMasterDTO(IndentMasterDTO indentMasterDTO) {
		this.indentMasterDTO = indentMasterDTO;
	}

	public List<IndentMasterDTO> getIndentMasterDTOList() {
		return indentMasterDTOList;
	}

	public void setIndentMasterDTOList(List<IndentMasterDTO> indentMasterDTOList) {
		this.indentMasterDTOList = indentMasterDTOList;
	}

	public Integer getIndentSeriesNo() {
		return indentSeriesNo;
	}

	public void setIndentSeriesNo(Integer indentSeriesNo) {
		this.indentSeriesNo = indentSeriesNo;
	}

}
