package com.advanz.erp.masters.model.msg;

import java.sql.Timestamp;
import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.FinishedGoodsMasterDTO;

@SuppressWarnings("serial")
public class FinishedGoodsMasterOutputMessage extends
		AdvanzErpBaseOutputMessage {

	private FinishedGoodsMasterDTO finishedGoodsMasterDTO;

	private List<FinishedGoodsMasterDTO> finishedGoodsMasterDTOList;

	private Integer finishedGoodsSeriesNo;

	private Timestamp finishedGoodsSeriesDate;

	public Timestamp getFinishedGoodsSeriesDate() {
		return finishedGoodsSeriesDate;
	}

	public void setFinishedGoodsSeriesDate(Timestamp finishedGoodsSeriesDate) {
		this.finishedGoodsSeriesDate = finishedGoodsSeriesDate;
	}

	public FinishedGoodsMasterDTO getFinishedGoodsMasterDTO() {
		return finishedGoodsMasterDTO;
	}

	public void setFinishedGoodsMasterDTO(
			FinishedGoodsMasterDTO finishedGoodsMasterDTO) {
		this.finishedGoodsMasterDTO = finishedGoodsMasterDTO;
	}

	public List<FinishedGoodsMasterDTO> getFinishedGoodsMasterDTOList() {
		return finishedGoodsMasterDTOList;
	}

	public void setFinishedGoodsMasterDTOList(
			List<FinishedGoodsMasterDTO> finishedGoodsMasterDTOList) {
		this.finishedGoodsMasterDTOList = finishedGoodsMasterDTOList;
	}

	public Integer getFinishedGoodsSeriesNo() {
		return finishedGoodsSeriesNo;
	}

	public void setFinishedGoodsSeriesNo(Integer finishedGoodsSeriesNo) {
		this.finishedGoodsSeriesNo = finishedGoodsSeriesNo;
	}

}
