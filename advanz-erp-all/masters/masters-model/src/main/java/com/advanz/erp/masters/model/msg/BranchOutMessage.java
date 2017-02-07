package com.advanz.erp.masters.model.msg;

import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.BranchDTO;

public class BranchOutMessage extends AdvanzErpBaseOutputMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7652311966136682774L;
	
	private BranchDTO branchDTO;
	
	private List<BranchDTO> branchDTOList;
	
	public List<BranchDTO> getBranchDTOList() {
		return branchDTOList;
	}

	public void setBranchDTOList(List<BranchDTO> branchDTOList) {
		this.branchDTOList = branchDTOList;
	}

	public BranchDTO getCompanyDTO() {
		return branchDTO;
	}

	public void setCompanyDTO(BranchDTO branchDTO) {
		this.branchDTO = branchDTO;
	}
}
