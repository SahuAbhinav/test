package com.advanz.erp.masters.model.msg;

import java.sql.Timestamp;
import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.MelterLogBookDTO;

public class MelterLogBookOutputMessage extends AdvanzErpBaseOutputMessage {
	private MelterLogBookDTO melterLogBookDTO;
	private List<MelterLogBookDTO> melterLogBookDTOList;

	private Timestamp LastRecordDate;

	public Timestamp getLastRecordDate() {
		return LastRecordDate;
	}

	public void setLastRecordDate(Timestamp lastRecordDate) {
		LastRecordDate = lastRecordDate;
	}

	public MelterLogBookDTO getMasterLogBookDTO() {
		return melterLogBookDTO;
	}

	public void setMelterLogBookDTO(MelterLogBookDTO melterLogBookDTO) {
		this.melterLogBookDTO = melterLogBookDTO;
	}

	public List<MelterLogBookDTO> getMelterLogBookDTOList() {
		return melterLogBookDTOList;
	}

	public void setMelterLogBookDTOList(
			List<MelterLogBookDTO> melterLogBookDTOList) {
		this.melterLogBookDTOList = melterLogBookDTOList;
	}

}
