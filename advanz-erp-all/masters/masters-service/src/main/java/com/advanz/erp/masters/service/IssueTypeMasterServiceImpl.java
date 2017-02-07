package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.BillEntity;
import com.advanz.erp.masters.entity.jpa.IssueTypeMasterEntity;
import com.advanz.erp.masters.model.IssueTypeMasterDTO;
import com.advanz.erp.masters.model.msg.IssueTypeMasterInputMessage;
import com.advanz.erp.masters.model.msg.IssueTypeMasterOutputMessage;
import com.advanz.erp.masters.service.business.IIssueTypeMasterService;
import com.advanz.erp.masters.storage.IStorageIssueTypeMasterDAO;

public class IssueTypeMasterServiceImpl implements IIssueTypeMasterService {

	public static final String CREATE_ISSUE_TYPE = "CreateIssueTypeMaster";
	public static final String UPDATE_ISSUE_TYPE = "UpdateIssueTypeMaster";
	public static final String DELETE_ISSUE_TYPE = "DeleteIssueTypeMaster";
	public static final String FIND_ALL_ISSUE_TYPE = "FindAllIssueTypeMasters";	
	
	public static final String FIND_ALL_ISSUE_TYPE_BY_SNO = "FindAllIssueTypeMastersBySno";	
	
	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// autowiring

	@Autowired
	public IStorageIssueTypeMasterDAO storageIssueTypeMasterDAO;

	public IssueTypeMasterInputMessage issueTypeMasterInputMessage;

	public IssueTypeMasterOutputMessage issueTypeMasterOutputMessage;

	

	

	
	@Override
	public boolean validateInput() {
		if (CREATE_ISSUE_TYPE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_ISSUE_TYPE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_ISSUE_TYPE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}  else if (FIND_ALL_ISSUE_TYPE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} 
		 else if (FIND_ALL_ISSUE_TYPE_BY_SNO.equals(flowId)) {
				// TODO add business validation on the input.
				return true;
			} 
		
		return false;
	}

	@Override
	public void performBusinessLogic() {
		IssueTypeMasterEntity issueTypeMasterEntity = new IssueTypeMasterEntity();
		if (issueTypeMasterInputMessage != null) {
			IssueTypeMasterDTO issueTypeMasterDTO = issueTypeMasterInputMessage
					.getIssueTypeMasterDTO();
			if (issueTypeMasterDTO != null) {
				BeanUtils.copyProperties(issueTypeMasterDTO,
						issueTypeMasterEntity);
			}
		}
		
		
		if (FIND_ALL_ISSUE_TYPE.equals(flowId)) {
			List<IssueTypeMasterEntity> list = storageIssueTypeMasterDAO.load();					
			popUpList(list);
		}
		
		if(FIND_ALL_ISSUE_TYPE_BY_SNO.equals(flowId)){
			IssueTypeMasterDTO issueTypeMasterDTO =issueTypeMasterInputMessage.getIssueTypeMasterDTO();
			List<IssueTypeMasterEntity> list =storageIssueTypeMasterDAO.getIssueBySno(issueTypeMasterDTO.getSno());
			popUpList(list);
		}
		
	}

	void popUpList(List<IssueTypeMasterEntity> list) {
		issueTypeMasterOutputMessage = new IssueTypeMasterOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<IssueTypeMasterDTO> resultList = new ArrayList<IssueTypeMasterDTO>();
			IssueTypeMasterDTO issueTypeMasterDTO;
			for (IssueTypeMasterEntity entity : list) {
				issueTypeMasterDTO = new IssueTypeMasterDTO();
				BeanUtils.copyProperties(entity, issueTypeMasterDTO);
				resultList.add(issueTypeMasterDTO);
			}
			issueTypeMasterOutputMessage.setIssueTypeMasterDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (CREATE_ISSUE_TYPE.equals(flowId)) {

		} else if (UPDATE_ISSUE_TYPE.equals(flowId)) {

		} else if (DELETE_ISSUE_TYPE.equals(flowId)) {

		}else if (FIND_ALL_ISSUE_TYPE.equals(flowId)) {

		} 
	}

	@Override
	public IssueTypeMasterOutputMessage findAllIssueTypeMasters() {		
		flowId = FIND_ALL_ISSUE_TYPE;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return issueTypeMasterOutputMessage;
	}

	@Override
	public IssueTypeMasterOutputMessage findIssuesBySno(
			IssueTypeMasterInputMessage typeMasterInputMessage) {
		flowId = FIND_ALL_ISSUE_TYPE_BY_SNO;
		this.issueTypeMasterInputMessage = typeMasterInputMessage;
		advanzErpServiceTemplate.execute(this);
		return issueTypeMasterOutputMessage;
	}

}
