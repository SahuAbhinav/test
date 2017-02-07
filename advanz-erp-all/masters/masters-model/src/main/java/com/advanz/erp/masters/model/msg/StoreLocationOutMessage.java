package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.StoreLocationDTO;

public class StoreLocationOutMessage extends AdvanzErpBaseOutputMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8475942246939986396L;

	/**
	 * 
	 */
	
	
	private StoreLocationDTO storeLocationDTO;
	
	private List<StoreLocationDTO> storeLocationDTOList;
	
	public List<StoreLocationDTO> getStoreLocationDTOList() {
		return storeLocationDTOList;
	}

	public void setStoreLocationDTOList(List<StoreLocationDTO> storeLocationDTOList) {
		this.storeLocationDTOList = storeLocationDTOList;
	}

	public StoreLocationDTO getStoreLocationDTO() {
		return storeLocationDTO;
	}

	public void setStoreLocationDTO(StoreLocationDTO storeLocationDTO) {
		this.storeLocationDTO = storeLocationDTO;
	}
}
