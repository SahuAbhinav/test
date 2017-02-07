package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.AreaDTO;

public class AreaOutputMessage extends AdvanzErpBaseOutputMessage{



	private AreaDTO areaDTO;
	
	private List<AreaDTO> areaDTOList;

	/**
	 * @return the batchDTO
	 */
	public AreaDTO getAreaDTO() {
		return areaDTO;
	}

	/**
	 * @param batchDTO the batchDTO to set
	 */
	public void setAreaDTO(AreaDTO areaDTO) {
		this.areaDTO = areaDTO;
	}

	/**
	 * @return the batchDTOList
	 */
	public List<AreaDTO> getAreaDTOList() {
		return areaDTOList;
	}

	/**
	 * @param batchDTOList the batchDTOList to set
	 */
	public void setAreaDTOList(List<AreaDTO> areaDTOList) {
		this.areaDTOList = areaDTOList;
	}
	

}
