package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.StateDTO;

public class StateOutputMessage extends AdvanzErpBaseOutputMessage{


	private StateDTO stateDTO;
	
	private List<StateDTO> stateDTOList;

	/**
	 * @return the batchDTO
	 */
	public StateDTO getStateDTO() {
		return stateDTO;
	}

	/**
	 * @param batchDTO the batchDTO to set
	 */
	public void setStateDTO(StateDTO stateDTO) {
		this.stateDTO = stateDTO;
	}

	/**
	 * @return the batchDTOList
	 */
	public List<StateDTO> getStateDTOList() {
		return stateDTOList;
	}

	/**
	 * @param batchDTOList the batchDTOList to set
	 */
	public void setStateDTOList(List<StateDTO> stateDTOList) {
		this.stateDTOList = stateDTOList;
	}
}
