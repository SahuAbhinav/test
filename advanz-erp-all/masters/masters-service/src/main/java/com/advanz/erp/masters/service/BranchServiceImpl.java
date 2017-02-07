package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.msg.BranchInputMessage;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.storage.IStorageBranchDAO;

@Service
public class BranchServiceImpl implements IBranchService {
	
public static final String CREATE_BRANCH = "Branch";
public static final String UPDATE_BRANCH = "UpdateBranch";
public static final String ADD_BRANCH = "AddBranch";
public static final String DELETE_BRANCH = "DeleteBranch";
public static final String FIND_BRANCH_BY_ID = "FindBranchById";
public static final String FIND_ALL_BRANCHES = "FindAllBranches";
public static final String FIND_BRANCHES = "FindBranches";
public static final String PRE_REMOVE_CHECK="PreRemoveCheck";
public static final String PRELOADED="Preloaded";
public String flowId = "";

//@Autowired
public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO do autowiring 

@Autowired
public IStorageBranchDAO storageBranchDAO;

public BranchInputMessage branchInputMessage;

public BranchOutMessage branchOutMessage;

private static final Logger logger = LoggerFactory.getLogger(BranchServiceImpl.class);

	@Override
	public BranchOutMessage createBranch(BranchInputMessage branchInputMessage) {
		
		flowId = ADD_BRANCH;
		// assign the message to the class level variable.
		this.branchInputMessage = branchInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		
		return branchOutMessage;
	}
	
	@Override
	public BranchOutMessage updateBranch(BranchInputMessage branchInputMessage) {
		
		flowId = UPDATE_BRANCH;
		this.branchInputMessage = branchInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
				
		return branchOutMessage;
	}
	
	@Override
	public BranchOutMessage deleteBranch(BranchInputMessage branchInputMessage) {
		flowId = DELETE_BRANCH;
		this.branchInputMessage = branchInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
				
		return branchOutMessage;
		
	}
	
	@Override
	public BranchOutMessage findBranchById(BranchInputMessage branchInputMessage) {
		flowId = FIND_BRANCH_BY_ID;
		this.branchInputMessage = branchInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return branchOutMessage;
		
	}
	
	@Override
	public BranchOutMessage findAllBranches() {
		flowId = FIND_ALL_BRANCHES;
		
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return branchOutMessage;
	}
	@Override
	public BranchOutMessage preloaded(){
     flowId = PRELOADED;
		
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return branchOutMessage;	
	}
	@Override
	public BranchOutMessage findBranch(BranchInputMessage branchInputMessage) {
		flowId = FIND_BRANCHES;
		this.branchInputMessage = branchInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return branchOutMessage;
	}
	

	@Override
	public BranchOutMessage checkBeforeRemove(
			BranchInputMessage branchInputMessage) {
		flowId = PRE_REMOVE_CHECK;
		this.branchInputMessage = branchInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return branchOutMessage;
	}
	
	@Override
	public boolean validateInput() {
		
		if(ADD_BRANCH.equals(flowId)){
			// TODO add business validation on the input.
			return true;
		} else if(UPDATE_BRANCH.equals(flowId)){
			// TODO add business validation on the input.
			return true;
		} else if(DELETE_BRANCH.equals(flowId)){
			// TODO add business validation on the input.
			return true;
		} else if(FIND_BRANCH_BY_ID.equals(flowId)){
			// TODO add business validation on the input.
			return true;
		}else if(FIND_ALL_BRANCHES.equals(flowId)){
			// TODO add business validation on the input.
			return true;
		}else if(FIND_BRANCHES.equals(flowId)){
			// TODO add business validation on the input.
			return true;
		}else if(PRE_REMOVE_CHECK.equals(flowId)){
			// TODO add business validation on the input.
			return true;
		}else if(PRELOADED.equals(flowId)){
			// TODO add business validation on the input.
			return true;
		}
		
		return false;
	}

	@Override
	public void performBusinessLogic() {
		BranchEntity branchEntity = new BranchEntity();
		String ignoreProperties[]= {"servTaxDate,vatDate,cstDate"};
		
		if(ADD_BRANCH.equals(flowId)){
			try {
				BranchDTO branchDto = branchInputMessage.getBranchDTO();
				branchEntity.setVatDate(branchDto.getVatDate());
				branchEntity.setCstDate(branchDto.getCstDate());
				branchEntity.setServTaxDate(branchDto.getServTaxDate());
				BeanUtils.copyProperties(branchDto,branchEntity,ignoreProperties);
				
			} catch (BeansException e) {
				e.printStackTrace();
			}
			
			List<BranchEntity> nameList=storageBranchDAO.findByName(branchEntity.getBranch());
			List<BranchEntity> codeList=storageBranchDAO.findByCode(branchEntity.getInvoiceCode());
			//logger.info(flowId +": "+nameList);
			//logger.info(flowId +": "+codeList);
			branchOutMessage = new BranchOutMessage();
			if((nameList!=null && nameList.size()>0) || (codeList!=null && codeList.size()>0)){				
				ErrorDTO errorDTO=new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO=new ErrorListDTO();
				errorListDTO.addError(errorDTO);				
				branchOutMessage.setErrorListDTO(errorListDTO);
			}else{
				branchOutMessage.setErrorListDTO(null);
				storageBranchDAO.create(branchEntity);
			}			
		} else if(UPDATE_BRANCH.equals(flowId)){
			try {
				BranchDTO branchDto = branchInputMessage.getBranchDTO();
				branchEntity.setVatDate(branchDto.getVatDate());
				branchEntity.setCstDate(branchDto.getCstDate());
				branchEntity.setServTaxDate(branchDto.getServTaxDate());
				BeanUtils.copyProperties(branchDto,branchEntity,ignoreProperties);
			} catch (BeansException e) {
				e.printStackTrace();
			}
			List<BranchEntity> nameList=storageBranchDAO.findByName(branchEntity.getBranch());
			List<BranchEntity> codeList=storageBranchDAO.findByCode(branchEntity.getInvoiceCode());
			//logger.info(flowId +": "+nameList);
		//	logger.info(flowId +": "+codeList);
			branchOutMessage = new BranchOutMessage();
			if((nameList!=null && nameList.size()>0 && !nameList.get(0).getBranchId().equals(branchEntity.getBranchId())) 
					|| (codeList!=null && codeList.size()>0 && !codeList.get(0).getBranchId().equals(branchEntity.getBranchId()))){				
				ErrorDTO errorDTO=new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO=new ErrorListDTO();
				errorListDTO.addError(errorDTO);				
				branchOutMessage.setErrorListDTO(errorListDTO);
			}else{
				branchOutMessage.setErrorListDTO(null);
				storageBranchDAO.update(branchEntity);
			}				
		} else if(DELETE_BRANCH.equals(flowId)){
			try {
				BranchDTO branchDto = branchInputMessage.getBranchDTO();
				branchEntity.setVatDate(branchDto.getVatDate());
				branchEntity.setCstDate(branchDto.getCstDate());
				branchEntity.setServTaxDate(branchDto.getServTaxDate());
				BeanUtils.copyProperties(branchDto,branchEntity,ignoreProperties);
			} catch (BeansException e) {
				e.printStackTrace();
			}
			storageBranchDAO.delete(branchEntity);
		} else if(FIND_BRANCH_BY_ID.equals(flowId)){
			BranchDTO branchDto = branchInputMessage.getBranchDTO();
			try {
				branchEntity.setVatDate(branchDto.getVatDate());
				branchEntity.setCstDate(branchDto.getCstDate());
				branchEntity.setServTaxDate(branchDto.getServTaxDate());
				BeanUtils.copyProperties(branchDto,branchEntity,ignoreProperties);
			} catch (BeansException e) {
				e.printStackTrace();
			}
			List<BranchEntity> list = storageBranchDAO.findById(branchDto.getBranchId());
			branchOutMessage = new BranchOutMessage();
			if ( list != null ) {
				List<BranchDTO> resultList = convertBranchEntityListTOBranchDtoList(list);
				branchOutMessage.setBranchDTOList(resultList);
			}
		}else if(FIND_ALL_BRANCHES.equals(flowId)){
			List<BranchEntity> list = storageBranchDAO.load();
			branchOutMessage = new BranchOutMessage();
			// set the data to the output message.
			if ( list != null ) {
				List<BranchDTO> resultList = convertBranchEntityListTOBranchDtoList(list);
				branchOutMessage.setBranchDTOList(resultList);
			}
		}
		
		else if(PRELOADED.equals(flowId)){
			List list = storageBranchDAO.preloaded();
			branchOutMessage = new BranchOutMessage();
			// set the data to the output message.
			ArrayList<BranchDTO> resultList = new ArrayList<BranchDTO>();
			if ( list != null ) {
				for(int i=0;i<list.size();i++){
					BranchDTO branchDTO = new BranchDTO();
					Object[] objects =(Object[])list.get(i);
					int branchId=(Integer)objects[0];
					String branch=(String)objects[1];
					branchDTO.setBranchId(branchId);
					branchDTO.setBranch(branch);
					resultList.add(branchDTO);
				}
				branchOutMessage.setBranchDTOList(resultList);
			}
		}
		else if(FIND_BRANCHES.equals(flowId)){
			BranchDTO branchDto = branchInputMessage.getBranchDTO();
			List<BranchEntity> list = storageBranchDAO.search(
					branchDto.getBranch(),					
					branchDto.getInvoiceCode());
			branchOutMessage = new BranchOutMessage();
			if ( list != null ) {
				List<BranchDTO> resultList = convertBranchEntityListTOBranchDtoList(list);
				branchOutMessage.setBranchDTOList(resultList);
			}
		}if(PRE_REMOVE_CHECK.equals(flowId)){
			logger.info(flowId);
			if(storageBranchDAO.isInUsed( branchInputMessage.getBranchDTO().getBranchId())){
				logger.info("TRUE");		
				branchOutMessage = new BranchOutMessage();
				ErrorDTO errorDTO = new ErrorDTO("1", "Branch can not be Removed");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				branchOutMessage.setErrorListDTO(errorListDTO);
			}else{
				branchOutMessage=null;
			}
		}
	}

	@Override
	public void formatOutput() {
		
		
		
		if(ADD_BRANCH.equals(flowId)){
			
		} else if(UPDATE_BRANCH.equals(flowId)){
			
		} else if(DELETE_BRANCH.equals(flowId)){
			
		} else if(FIND_BRANCH_BY_ID.equals(flowId)){
			
		}else if(FIND_ALL_BRANCHES.equals(flowId)){
			
		}
	}

	private List<BranchDTO> convertBranchEntityListTOBranchDtoList(List<BranchEntity> list){

		List<BranchDTO> resultList = new ArrayList<BranchDTO>();
		BranchDTO branchDto ;
		for (BranchEntity entity : list ) {
			branchDto = new BranchDTO();
			try {
				BeanUtils.copyProperties(entity,branchDto);
			} catch (BeansException e) {
				e.printStackTrace();
			}
			resultList.add(branchDto);
			
		}
		return resultList;
	}

}
