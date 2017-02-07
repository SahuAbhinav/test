package com.advanz.erp.client.http.controller.form;

import java.util.Date;
import java.util.List;

import com.advanz.erp.masters.model.MasterFormulaMasterDTO;

public class MasterFormulaForm  {
 private List<MasterFormulaMasterDTO> formulaMasterDTOList;
 private MasterFormulaMasterDTO masterFormulaMasterDTO;
  
 	private String itemName;
 	private String itemCode;
 	private Date modifiedDate;
 	
 	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	private String opr;
 	
 	public String getOpr() {
	return opr;
 	}

 	public void setOpr(String opr) {
	this.opr = opr;
}

	public String getItemName() {
	return itemName;
}

public void setItemName(String itemName) {
	this.itemName = itemName;
}

public String getItemCode() {
	return itemCode;
}

public void setItemCode(String itemCode) {
	this.itemCode = itemCode;
}

	public List<MasterFormulaMasterDTO> getFormulaMasterDTOList() {
		return formulaMasterDTOList;
	}

	public void setFormulaMasterDTOList(
			List<MasterFormulaMasterDTO> formulaMasterDTOList) {
		this.formulaMasterDTOList = formulaMasterDTOList;
	}

	public MasterFormulaMasterDTO getMasterFormulaMasterDTO() {
		return masterFormulaMasterDTO;
	}

	public void setMasterFormulaMasterDTO(
			MasterFormulaMasterDTO masterFormulaMasterDTO) {
		this.masterFormulaMasterDTO = masterFormulaMasterDTO;
	}

}
