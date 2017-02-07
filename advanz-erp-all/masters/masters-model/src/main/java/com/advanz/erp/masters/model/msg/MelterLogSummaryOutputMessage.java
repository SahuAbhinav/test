package com.advanz.erp.masters.model.msg;

import java.sql.Timestamp;
import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.MelterLogSummaryDTO;

public class MelterLogSummaryOutputMessage extends AdvanzErpBaseOutputMessage {

	private static final long serialVersionUID = 1L;
	private List<MelterLogSummaryDTO> melterLogSummaryDTOList;
	private MelterLogSummaryDTO melterLogSummaryDTO;
	private Timestamp lastMelterLogSummaryDate;

	public Timestamp getLastMelterLogSummaryDate() {
		return lastMelterLogSummaryDate;
	}

	public void setLastMelterLogSummaryDate(Timestamp lastMelterLogSummaryDate) {
		this.lastMelterLogSummaryDate = lastMelterLogSummaryDate;
	}

	public MelterLogSummaryDTO getMelterLogSummaryDTO() {
		return melterLogSummaryDTO;
	}

	public void setMelterLogSummaryDTO(MelterLogSummaryDTO melterLogSummaryDTO) {
		this.melterLogSummaryDTO = melterLogSummaryDTO;
	}

	public List<MelterLogSummaryDTO> getMelterLogSummaryDTOList() {
		return melterLogSummaryDTOList;
	}

	public void setMelterLogSummaryDTOList(
			List<MelterLogSummaryDTO> melterLogSummaryDTOList) {
		this.melterLogSummaryDTOList = melterLogSummaryDTOList;
	}

}
