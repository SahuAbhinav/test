package com.advanz.erp.common.model;

import java.util.ArrayList;
import java.util.List;

public class ErrorListDTO extends BaseDTO {

	List<ErrorDTO> errorList;

	public List<ErrorDTO> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<ErrorDTO> errorList) {
		this.errorList = errorList;
	}
	
	public void addError(ErrorDTO errorDTO){
		if(errorList==null)
			errorList=new ArrayList<ErrorDTO>();
		errorList.add(errorDTO);
	}
	public boolean hasErrors(){
		if(errorList!=null && errorList.size()>0)
			return true;
		return false;
	}
	
	
}
