package com.advanz.erp.common.model.msg;

import java.io.Serializable;

import com.advanz.erp.common.model.ErrorListDTO;

public class AdvanzErpBaseOutputMessage  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4714845982053161236L;
	
	private ErrorListDTO errorListDTO;

	public ErrorListDTO getErrorListDTO() {
		return errorListDTO;
	}

	public void setErrorListDTO(ErrorListDTO errorListDTO) {
		this.errorListDTO = errorListDTO;
	}
	
	
}
