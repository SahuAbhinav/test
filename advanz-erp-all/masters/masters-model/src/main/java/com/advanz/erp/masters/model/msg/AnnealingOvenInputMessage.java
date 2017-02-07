package com.advanz.erp.masters.model.msg;

import com.advanz.erp.common.model.msg.AdvanzErpBaseInputMessage;
import com.advanz.erp.masters.model.AnnealingOvenMasterDTO;

@SuppressWarnings("serial")
public class AnnealingOvenInputMessage extends AdvanzErpBaseInputMessage {

private AnnealingOvenMasterDTO annealingOvenMasterDTO;

public AnnealingOvenMasterDTO getAnnealingOvenMasterDTO() {
return annealingOvenMasterDTO;
}

public void setAnnealingOvenMasterDTO(
AnnealingOvenMasterDTO annealingOvenMasterDTO) {
this.annealingOvenMasterDTO = annealingOvenMasterDTO;
}

}

