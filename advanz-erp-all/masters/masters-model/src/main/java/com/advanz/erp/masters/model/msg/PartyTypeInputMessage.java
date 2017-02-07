package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.PartyTypeDTO;

public class PartyTypeInputMessage  extends AdvanzErpBaseInputMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6010217276398805424L;
	
	private PartyTypeDTO partyTypeDTO;
	private String sortColumn;
	private String orderColumn;
	private Integer offset;
	private Integer rows;
	
	public PartyTypeDTO getPartyTypeDTO() {
		return partyTypeDTO;
	}
	public void setPartyTypeDTO(PartyTypeDTO partyTypeDTO) {
		this.partyTypeDTO = partyTypeDTO;
	}
	public String getSortColumn() {
		return sortColumn;
	}
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
	public String getOrderColumn() {
		return orderColumn;
	}
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}	
}
