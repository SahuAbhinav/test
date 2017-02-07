package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.AreaDTO;
import com.advanz.erp.masters.model.LeaveTypeMastDTO;

public class LeaveTypeMastOutputMessage extends AdvanzErpBaseOutputMessage{



	private LeaveTypeMastDTO leaveTypeMastDTO;
	
	private List<LeaveTypeMastDTO> leaveTypeMastDTOList;

	private List<String> list;
	
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	/**
	 * @return the leaveTypeMastDTO
	 */
	public LeaveTypeMastDTO getLeaveTypeMastDTO() {
		return leaveTypeMastDTO;
	}

	/**
	 * @param batchDTO the leaveTypeMastDTO to set
	 */
	public void setLeaveTypeMastDTO(LeaveTypeMastDTO leaveTypeMastDTO) {
		this.leaveTypeMastDTO = leaveTypeMastDTO;
	}

	/**
	 * @return the leaveTypeMastDTOList
	 */
	public List<LeaveTypeMastDTO> getLeaveTypeMastDTOList() {
		return leaveTypeMastDTOList;
	}

	/**
	 * @param batchDTOList the leaveTypeMastDTOList to set
	 */
	public void setLeaveTypeMastDTOList(List<LeaveTypeMastDTO> leaveTypeMastDTOList) {
		this.leaveTypeMastDTOList = leaveTypeMastDTOList;
	}
	

}
