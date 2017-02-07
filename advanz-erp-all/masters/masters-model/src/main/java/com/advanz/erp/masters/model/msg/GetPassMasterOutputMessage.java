package com.advanz.erp.masters.model.msg;

import java.sql.Timestamp;
import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.GetPassMasterDTO;
import com.advanz.erp.masters.model.GrnMasterDTO;

@SuppressWarnings("serial")
public class GetPassMasterOutputMessage extends AdvanzErpBaseOutputMessage {

	private GetPassMasterDTO getPassMasterDTO;

	private List<GetPassMasterDTO> getPassMasterDTOList;
	private Integer getPassSeriesNo;
	private Timestamp getPassSeriesDate;

	public Timestamp getGetPassSeriesDate() {
		return getPassSeriesDate;
	}

	public void setGetPassSeriesDate(Timestamp getPassSeriesDate) {
		this.getPassSeriesDate = getPassSeriesDate;
	}

	public Integer getGetPassSeriesNo() {
		return getPassSeriesNo;
	}

	public void setGetPassSeriesNo(Integer getPassSeriesNo) {
		this.getPassSeriesNo = getPassSeriesNo;
	}

	public GetPassMasterDTO getGetPassMasterDTO() {
		return getPassMasterDTO;
	}

	public void setGetPassMasterDTO(GetPassMasterDTO getPassMasterDTO) {
		this.getPassMasterDTO = getPassMasterDTO;
	}

	public List<GetPassMasterDTO> getGetPassMasterDTOList() {
		return getPassMasterDTOList;
	}

	public void setGetPassMasterDTOList(
			List<GetPassMasterDTO> getPassMasterDTOList) {
		this.getPassMasterDTOList = getPassMasterDTOList;
	}

}
