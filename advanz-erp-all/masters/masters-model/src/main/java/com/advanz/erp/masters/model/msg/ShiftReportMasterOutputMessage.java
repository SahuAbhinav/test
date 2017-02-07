package com.advanz.erp.masters.model.msg;

import java.sql.Timestamp;
import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.FinishedGoodsMasterDTO;
import com.advanz.erp.masters.model.SalesOrderMasterDTO;
import com.advanz.erp.masters.model.ShiftReportMasterDTO;

@SuppressWarnings("serial")
public class ShiftReportMasterOutputMessage extends AdvanzErpBaseOutputMessage {

	private ShiftReportMasterDTO shiftReportMasterDTO;
	private Timestamp lastShiftDate;

	public Timestamp getLastShiftDate() {
		return lastShiftDate;
	}

	public void setLastShiftDate(Timestamp lastShiftDate) {
		this.lastShiftDate = lastShiftDate;
	}

	private List<ShiftReportMasterDTO> shiftReportMasterDTOList;

	public ShiftReportMasterDTO getShiftReportMasterDTO() {
		return shiftReportMasterDTO;
	}

	public void setShiftReportMasterDTO(
			ShiftReportMasterDTO shiftReportMasterDTO) {
		this.shiftReportMasterDTO = shiftReportMasterDTO;
	}

	public List<ShiftReportMasterDTO> getShiftReportMasterDTOList() {
		return shiftReportMasterDTOList;
	}

	public void setShiftReportMasterDTOList(
			List<ShiftReportMasterDTO> shiftReportMasterDTOList) {
		this.shiftReportMasterDTOList = shiftReportMasterDTOList;
	}

}
