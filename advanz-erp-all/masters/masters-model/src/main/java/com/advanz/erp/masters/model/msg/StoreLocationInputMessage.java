package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.StoreLocationDTO;

public class StoreLocationInputMessage  extends AdvanzErpBaseInputMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6834437445914261143L;
	/**
	 * 
	 */
	
	
	private StoreLocationDTO storeLocationDTO;	
	

	public StoreLocationDTO getStoreLocationDTO() {
		return storeLocationDTO;
	}

	public void setStoreLocationDTO(StoreLocationDTO storeLocationDTO) {
		this.storeLocationDTO = storeLocationDTO;
	}

}
