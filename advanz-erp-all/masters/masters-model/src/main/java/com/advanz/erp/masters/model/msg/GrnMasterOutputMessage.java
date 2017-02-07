package com.advanz.erp.masters.model.msg;

import java.sql.Timestamp;
import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.GrnMasterDTO;

@SuppressWarnings("serial")
public class GrnMasterOutputMessage extends AdvanzErpBaseOutputMessage {

	private GrnMasterDTO grnMasterDTO;

	private List<GrnMasterDTO> grnMasterDTOList;

	private Integer grnSeriesNo;

	private Timestamp grnSeriesDate;

	public Timestamp getGrnSeriesDate() {
		return grnSeriesDate;
	}

	public void setGrnSeriesDate(Timestamp grnSeriesDate) {
		this.grnSeriesDate = grnSeriesDate;
	}

	public Integer getGrnSeriesNo() {
		return grnSeriesNo;
	}

	public void setGrnSeriesNo(Integer grnSeriesNo) {
		this.grnSeriesNo = grnSeriesNo;
	}

	public GrnMasterDTO getGrnMasterDTO() {
		return grnMasterDTO;
	}

	public void setGrnMasterDTO(GrnMasterDTO grnMasterDTO) {
		this.grnMasterDTO = grnMasterDTO;
	}

	public List<GrnMasterDTO> getGrnMasterDTOList() {
		return grnMasterDTOList;
	}

	public void setGrnMasterDTOList(List<GrnMasterDTO> grnMasterDTOList) {
		this.grnMasterDTOList = grnMasterDTOList;
	}

}
