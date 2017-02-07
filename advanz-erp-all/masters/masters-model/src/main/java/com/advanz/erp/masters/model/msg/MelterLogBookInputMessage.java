package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.MelterLogBookDTO;

public class MelterLogBookInputMessage extends AdvanzErpBaseInputMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MelterLogBookDTO melterLogBookDTO;
	public MelterLogBookDTO getMelterLogBookDTO() {
		return melterLogBookDTO;
	}
	public void setMelterLogBookDTO(MelterLogBookDTO melterLogBookDTO) {
		this.melterLogBookDTO = melterLogBookDTO;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
