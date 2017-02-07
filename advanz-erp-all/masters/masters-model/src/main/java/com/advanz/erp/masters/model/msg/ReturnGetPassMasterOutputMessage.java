package com.advanz.erp.masters.model.msg;

import java.sql.Timestamp;
import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.GetPassMasterDTO;
import com.advanz.erp.masters.model.GrnMasterDTO;
import com.advanz.erp.masters.model.ReturnGetPassMasterDTO;

@SuppressWarnings("serial")
public class ReturnGetPassMasterOutputMessage extends
		AdvanzErpBaseOutputMessage {

	private ReturnGetPassMasterDTO returnGetPassMasterDTO;

	private List<ReturnGetPassMasterDTO> returnGetPassMasterDTOList;
	private Integer returnGetPassSeriesNo;
	private Timestamp returnGetPassSeriesDate;

	public Timestamp getReturnGetPassSeriesDate() {
		return returnGetPassSeriesDate;
	}

	public void setReturnGetPassSeriesDate(Timestamp returnGetPassSeriesDate) {
		this.returnGetPassSeriesDate = returnGetPassSeriesDate;
	}

	public ReturnGetPassMasterDTO getReturnGetPassMasterDTO() {
		return returnGetPassMasterDTO;
	}

	public void setReturnGetPassMasterDTO(
			ReturnGetPassMasterDTO returnGetPassMasterDTO) {
		this.returnGetPassMasterDTO = returnGetPassMasterDTO;
	}

	public List<ReturnGetPassMasterDTO> getReturnGetPassMasterDTOList() {
		return returnGetPassMasterDTOList;
	}

	public void setReturnGetPassMasterDTOList(
			List<ReturnGetPassMasterDTO> returnGetPassMasterDTOList) {
		this.returnGetPassMasterDTOList = returnGetPassMasterDTOList;
	}

	public Integer getReturnGetPassSeriesNo() {
		return returnGetPassSeriesNo;
	}

	public void setReturnGetPassSeriesNo(Integer returnGetPassSeriesNo) {
		this.returnGetPassSeriesNo = returnGetPassSeriesNo;
	}

}
