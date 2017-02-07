package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.BatchDTO;

public class BatchOutMessage extends AdvanzErpBaseOutputMessage{



	private BatchDTO batchDTO;
	
	private List<BatchDTO> batchDTOList;

	/**
	 * @return the batchDTO
	 */
	public BatchDTO getBatchDTO() {
		return batchDTO;
	}

	/**
	 * @param batchDTO the batchDTO to set
	 */
	public void setBatchDTO(BatchDTO batchDTO) {
		this.batchDTO = batchDTO;
	}

	/**
	 * @return the batchDTOList
	 */
	public List<BatchDTO> getBatchDTOList() {
		return batchDTOList;
	}

	/**
	 * @param batchDTOList the batchDTOList to set
	 */
	public void setBatchDTOList(List<BatchDTO> batchDTOList) {
		this.batchDTOList = batchDTOList;
	}
	
	
}
