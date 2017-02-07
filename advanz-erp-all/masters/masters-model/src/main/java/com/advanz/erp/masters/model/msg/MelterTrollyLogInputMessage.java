package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.MelterTrollyLogDTO;

@SuppressWarnings("serial")
public class MelterTrollyLogInputMessage extends AdvanzErpBaseInputMessage {
	private MelterTrollyLogDTO melterTrollyLogDTO;

	public MelterTrollyLogDTO getMelterTrollyLogDTO() {
		return melterTrollyLogDTO;
	}

	public void setMelterTrollyLogDTO(MelterTrollyLogDTO melterTrollyLogDTO) {
		this.melterTrollyLogDTO = melterTrollyLogDTO;
	}

}
