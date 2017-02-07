package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.AnnealingOvenMasterDTO;

public class AnnealingOvenForm {

private List<AnnealingOvenMasterDTO> annealingOvenMasterDTOList;
private AnnealingOvenMasterDTO annealingOvenMasterDTO;
private String succ;
private String lastAnnealiingLastDate;

public String getLastAnnealiingLastDate() {
	return lastAnnealiingLastDate;
}

public void setLastAnnealiingLastDate(String lastAnnealiingLastDate) {
	this.lastAnnealiingLastDate = lastAnnealiingLastDate;
}

public String getSucc() {
return succ;
}

public void setSucc(String succ) {
this.succ = succ;
}
public List<AnnealingOvenMasterDTO> getAnnealingOvenMasterDTOList() {
return annealingOvenMasterDTOList;
}

public void setAnnealingOvenMasterDTOList(
List<AnnealingOvenMasterDTO> annealingOvenMasterDTOList) {
this.annealingOvenMasterDTOList = annealingOvenMasterDTOList;
}

public AnnealingOvenMasterDTO getAnnealingOvenMasterDTO() {
return annealingOvenMasterDTO;
}

public void setAnnealingOvenMasterDTO(
AnnealingOvenMasterDTO annealingOvenMasterDTO) {
this.annealingOvenMasterDTO = annealingOvenMasterDTO;
}

@Override
public String toString() {
return " AnnealingOverForm [annealingOvenDTO=" + annealingOvenMasterDTO + "]";
}

}

