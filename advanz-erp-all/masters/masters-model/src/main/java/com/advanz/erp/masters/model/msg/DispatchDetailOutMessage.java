package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.BillDTO;
import com.advanz.erp.masters.model.DispatchDetailDTO;

public class DispatchDetailOutMessage extends AdvanzErpBaseOutputMessage{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DispatchDetailDTO dispatchDetailDTO;
	private List<DispatchDetailDTO> dispatchDetailDTOList;
	public DispatchDetailDTO getDispatchDetailDTO() {
		return dispatchDetailDTO;
	}
	public void setDispatchDetailDTO(DispatchDetailDTO dispatchDetailDTO) {
		this.dispatchDetailDTO = dispatchDetailDTO;
	}
	public List<DispatchDetailDTO> getDispatchDetailDTOList() {
		return dispatchDetailDTOList;
	}
	public void setDispatchDetailDTOList(
			List<DispatchDetailDTO> dispatchDetailDTOList) {
		this.dispatchDetailDTOList = dispatchDetailDTOList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
