package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.AnnealingOvenMasterDTO;
import com.advanz.erp.masters.model.CapativeConsuptionDTO;

@SuppressWarnings("serial")
public class CapativeConsuptionInputMessage extends AdvanzErpBaseInputMessage {

private CapativeConsuptionDTO capativeConsuptionDTO;
private Integer next;
public CapativeConsuptionDTO getCapativeConsuptionDTO() {
	return capativeConsuptionDTO;
}

public void setCapativeConsuptionDTO(CapativeConsuptionDTO capativeConsuptionDTO) {
	this.capativeConsuptionDTO = capativeConsuptionDTO;
}

public Integer getNext() {
	return next;
}

public void setNext(Integer next) {
	this.next = next;
}


}

