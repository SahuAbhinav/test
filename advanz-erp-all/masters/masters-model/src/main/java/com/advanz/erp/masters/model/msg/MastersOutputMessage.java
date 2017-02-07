package com.advanz.erp.masters.model.msg;

import java.util.List;
import java.util.Map;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.MastersDTO;

@SuppressWarnings("serial")
public class MastersOutputMessage extends AdvanzErpBaseOutputMessage{



	private MastersDTO mastersDTO;
	
	private List<MastersDTO> mastersDTOList;
	
	private Map<Integer,String>mastersIdNameMap;

	private List<String> list;
	
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	/**
	 * @return the batchDTO
	 */
	public MastersDTO getMastersDTO() {
		return mastersDTO;
	}

	/**
	 * @param batchDTO the batchDTO to set
	 */
	public void setMastersDTO(MastersDTO mastersDTO) {
		this.mastersDTO = mastersDTO;
	}

	/**
	 * @return the batchDTOList
	 */
	public List<MastersDTO> getMastersDTOList() {
		return mastersDTOList;
	}

	/**
	 * @param batchDTOList the batchDTOList to set
	 */
	public void setMastersDTOList(List<MastersDTO> mastersDTOList) {
		this.mastersDTOList = mastersDTOList;
	}

	/**
	 * @return the mastersIdNameMap
	 */
	public Map<Integer, String> getMastersIdNameMap() {
		return mastersIdNameMap;
	}

	/**
	 * @param mastersIdNameMap the mastersIdNameMap to set
	 */
	public void setMastersIdNameMap(Map<Integer, String> mastersIdNameMap) {
		this.mastersIdNameMap = mastersIdNameMap;
	}
	

}
