package com.advanz.erp.masters.model.msg;

import java.sql.Timestamp;
import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.QuotationMasterDTO;

@SuppressWarnings("serial")
public class QuotationMasterOutputMessage extends AdvanzErpBaseOutputMessage {

	// private QuotationMasterDTO QuotationMasterDTO;

	private List<QuotationMasterDTO> QuotationMasterDTOList;

	private Integer QuotationSeriesNo;
	private Timestamp QuotationSeriesDate;

	public Timestamp getQuotationSeriesDate() {
		return QuotationSeriesDate;
	}

	public void setQuotationSeriesDate(Timestamp quotationSeriesDate) {
		QuotationSeriesDate = quotationSeriesDate;
	}

	public Integer getQuotationSeriesNo() {
		return QuotationSeriesNo;
	}

	public void setQuotationSeriesNo(Integer QuotationSeriesNo) {
		this.QuotationSeriesNo = QuotationSeriesNo;
	}

	// public QuotationMasterDTO getQuotationMasterDTO() {
	// return QuotationMasterDTO;
	// }
	//
	// public void setQuotationMasterDTO(QuotationMasterDTO QuotationMasterDTO)
	// {
	// this.QuotationMasterDTO = QuotationMasterDTO;
	// }

	public List<QuotationMasterDTO> getQuotationMasterDTOList() {
		return QuotationMasterDTOList;
	}

	public void setQuotationMasterDTOList(
			List<QuotationMasterDTO> QuotationMasterDTOList) {
		this.QuotationMasterDTOList = QuotationMasterDTOList;
	}

}
