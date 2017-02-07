package com.advanz.erp.masters.model.msg;

import java.sql.Timestamp;
import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.ProformaMasterDTO;

public class ProformaMasterOutMessage extends AdvanzErpBaseOutputMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProformaMasterDTO proformaMasterDTO;
	private List<ProformaMasterDTO> proformaMasterDTOList;
	private Integer proformaSeries;

	private Timestamp proformaSeriesDate;

	public Timestamp getProformaSeriesDate() {
		return proformaSeriesDate;
	}

	public void setProformaSeriesDate(Timestamp proformaSeriesDate) {
		this.proformaSeriesDate = proformaSeriesDate;
	}

	public Integer getProformaSeries() {
		return proformaSeries;
	}

	public void setProformaSeries(Integer proformaSeries) {
		this.proformaSeries = proformaSeries;
	}

	public ProformaMasterDTO getProformaMasterDTO() {
		return proformaMasterDTO;
	}

	public void setProformaMasterDTO(ProformaMasterDTO proformaMasterDTO) {
		this.proformaMasterDTO = proformaMasterDTO;
	}

	public List<ProformaMasterDTO> getProformaMasterDTOList() {
		return proformaMasterDTOList;
	}

	public void setProformaMasterDTOList(
			List<ProformaMasterDTO> proformaMasterDTOList) {
		this.proformaMasterDTOList = proformaMasterDTOList;
	}

	private List<String> list;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	private String finyear;

	public String getFinyear() {
		return finyear;
	}

	public void setFinyear(String finyear) {
		this.finyear = finyear;
	}
}
