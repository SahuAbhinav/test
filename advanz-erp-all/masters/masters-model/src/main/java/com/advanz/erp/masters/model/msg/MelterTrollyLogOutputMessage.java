package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.MelterTrollyLogDTO;

@SuppressWarnings("serial")
public class MelterTrollyLogOutputMessage extends AdvanzErpBaseOutputMessage {
	private MelterTrollyLogDTO melterTrollyLogDTO;
	List<MelterTrollyLogDTO> melterTrollyLogDTOList;

	public MelterTrollyLogDTO getMelterTrollyLogDTO() {
		return melterTrollyLogDTO;
	}

	public void setMelterTrollyLogDTO(MelterTrollyLogDTO melterTrollyLogDTO) {
		this.melterTrollyLogDTO = melterTrollyLogDTO;
	}

	public List<MelterTrollyLogDTO> getMelterTrollyLogDTOList() {
		return melterTrollyLogDTOList;
	}

	public void setMelterTrollyLogDTOList(
			List<MelterTrollyLogDTO> melterTrollyLogDTOList) {
		this.melterTrollyLogDTOList = melterTrollyLogDTOList;
	}

}
