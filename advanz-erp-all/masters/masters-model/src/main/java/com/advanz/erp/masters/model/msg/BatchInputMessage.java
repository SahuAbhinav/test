package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.criteria.BatchSearchCriteriaDTO;


public class BatchInputMessage extends AdvanzErpBaseInputMessage{

	private BatchDTO batchDTO;
	private BatchSearchCriteriaDTO batchSearchCritecia;
	private Integer itemId;

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

	public BatchSearchCriteriaDTO getBatchSearchCritecia() {
		return batchSearchCritecia;
	}

	public void setBatchSearchCritecia(BatchSearchCriteriaDTO batchSearchCritecia) {
		this.batchSearchCritecia = batchSearchCritecia;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	

}
