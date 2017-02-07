package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.BarcodeLedgerDTO;

public class BarcodeLedgerOutputMessage extends AdvanzErpBaseOutputMessage{

	private BarcodeLedgerDTO barcodeLedgerDTO;
	
	private List<BarcodeLedgerDTO> barcodeLedgerDTOsList;

	/**
	 * @return the barcodeLedgerDTO
	 */
	public BarcodeLedgerDTO getBarcodeLedgerDTO() {
		return barcodeLedgerDTO;
	}

	/**
	 * @param barcodeLedgerDTO the barcodeLedgerDTO to set
	 */
	public void setBarcodeLedgerDTO(BarcodeLedgerDTO barcodeLedgerDTO) {
		this.barcodeLedgerDTO = barcodeLedgerDTO;
	}

	/**
	 * @return the barcodeLedgerDTOsList
	 */
	public List<BarcodeLedgerDTO> getBarcodeLedgerDTOsList() {
		return barcodeLedgerDTOsList;
	}

	/**
	 * @param barcodeLedgerDTOsList the barcodeLedgerDTOsList to set
	 */
	public void setBarcodeLedgerDTOsList(
			List<BarcodeLedgerDTO> barcodeLedgerDTOsList) {
		this.barcodeLedgerDTOsList = barcodeLedgerDTOsList;
	}
	

	
	

}
