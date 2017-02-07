package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.MelterLogSummaryDTO;

@SuppressWarnings("serial")
public class MelterLogSummaryInputMessage extends AdvanzErpBaseInputMessage {
	private MelterLogSummaryDTO melterLogSummaryDTO;

	public MelterLogSummaryDTO getMelterLogSummaryDTO() {
		return melterLogSummaryDTO;
	}

	public void setMelterLogSummaryDTO(MelterLogSummaryDTO melterLogSummaryDTO) {
		this.melterLogSummaryDTO = melterLogSummaryDTO;
	}

	

}
