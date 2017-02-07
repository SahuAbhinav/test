package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.BatchEntity;
import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.entity.jpa.ItemCategoryEntity;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.ItemGroupEntity;
import com.advanz.erp.masters.entity.jpa.ProfessionalTaxDeductTypeEntity;
import com.advanz.erp.masters.entity.jpa.ProfessionalTaxEntity;
import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ProfessionalTaxDTO;
import com.advanz.erp.masters.model.ProfessionalTaxDeductTypeDTO;
import com.advanz.erp.masters.model.msg.BatchInputMessage;
import com.advanz.erp.masters.model.msg.BatchOutMessage;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.model.msg.ItemGroupInputMessage;
import com.advanz.erp.masters.model.msg.ItemGroupOutMessage;
import com.advanz.erp.masters.model.msg.ProfessionalTaxInputMessage;
import com.advanz.erp.masters.model.msg.ProfessionalTaxOutputMessage;
import com.advanz.erp.masters.service.business.IProfessionalTaxService;
import com.advanz.erp.masters.storage.IStorageBatchDAO;
import com.advanz.erp.masters.storage.IStorageProfessionalTaxDAO;

public class ProfessionalTaxServiceImpl implements IProfessionalTaxService
{
	
	public static final String CREATE_PROFESSIONAL_TAX = "ProfessionalTax";
	public static final String UPDATE_PROFESSIONAL_TAX = "UpdateProfessionalTax";
	public static final String ADD_PROFESSIONAL= "AddProfessionalTax";
	public static final String DELETE_PROFESSIONAL_TAX = "DeleteProfessionalTax";
	public static final String FIND_PROFESSIONAL_TAX_BY_NO = "FindProfessionalTaxByNo";
	public static final String FIND_ALL_PROFESSIONAL_TAXS = "FindAllCompnies";
	public static final String FIND_PROFESSIONAL_TAX = "FindProfessionalTax";
	public static final String FIND_PROFESSIONAL_TAX_BY_ID = "FindProfessionalTaxByID";
	public static final String SEARCH_PROFESSIONAL_TAX = "SearchProfessionalTax";
	public static final String FIND_PROFESSIONAL_TAX_BY_NAME = "FindProfessionalTaxByName";
	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO

	private static final Logger logger = LoggerFactory
			.getLogger(ProfessionalTaxServiceImpl.class); // do
	// autowiring

	@Autowired
	public IStorageProfessionalTaxDAO storageProfessionalTaxDAO;

	public ProfessionalTaxInputMessage professionalTaxInputMessage;

	public ProfessionalTaxOutputMessage professionalTaxOutMessage;

	@Override
	public ProfessionalTaxOutputMessage createProfessionalTax(ProfessionalTaxInputMessage professionalTaxInputMessage) {

		flowId = ADD_PROFESSIONAL;
		// assign the message to the class level variable.
		this.professionalTaxInputMessage = professionalTaxInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return professionalTaxOutMessage;
	}

	@Override
	public ProfessionalTaxOutputMessage updateProfessionalTax(ProfessionalTaxInputMessage professionalTaxInputMessage) {

		flowId = UPDATE_PROFESSIONAL_TAX;
		this.professionalTaxInputMessage = professionalTaxInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return professionalTaxOutMessage;
	}

	@Override
	public ProfessionalTaxOutputMessage deleteProfessionalTax(ProfessionalTaxInputMessage professionalTaxInputMessage) {
		flowId = DELETE_PROFESSIONAL_TAX;
		this.professionalTaxInputMessage = professionalTaxInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return professionalTaxOutMessage;

	}

	@Override
		public ProfessionalTaxOutputMessage findProfessionalTaxById(ProfessionalTaxInputMessage professionalTaxInputMessage) {
			flowId = FIND_PROFESSIONAL_TAX_BY_ID;
			this.professionalTaxInputMessage = professionalTaxInputMessage;
			// call the template method
			advanzErpServiceTemplate.execute(this);
			return professionalTaxOutMessage;
			
		}
		
		@Override
		public ProfessionalTaxOutputMessage findAllProfessionalTax() {
			flowId = FIND_ALL_PROFESSIONAL_TAXS;
			
			// call the template method
			advanzErpServiceTemplate.execute(this);
			return professionalTaxOutMessage;
		}
		
	//@Override
		public ProfessionalTaxOutputMessage findProfessionalTax(ProfessionalTaxInputMessage professionalTaxInputMessage) {
			flowId = SEARCH_PROFESSIONAL_TAX;
			this.professionalTaxInputMessage = professionalTaxInputMessage;
			// call the template method
			advanzErpServiceTemplate.execute(this);
			return professionalTaxOutMessage;
		}

	@Override
	public boolean validateInput() {

		if (ADD_PROFESSIONAL.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_PROFESSIONAL_TAX.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_PROFESSIONAL_TAX.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_PROFESSIONAL_TAX_BY_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_PROFESSIONAL_TAX_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_PROFESSIONAL_TAXS.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_PROFESSIONAL_TAX.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		return false;
	}
	
	@Override
	public void performBusinessLogic() {
		ProfessionalTaxEntity professionalTaxEntity = new ProfessionalTaxEntity();

		if (professionalTaxInputMessage != null) {
			ProfessionalTaxDTO professionalTaxDto = professionalTaxInputMessage.getProfessionalTaxDTO();
			if (professionalTaxDto != null) {
				BeanUtils.copyProperties(professionalTaxDto, professionalTaxEntity);
				ProfessionalTaxDeductTypeEntity professionalTaxDeductTypeEntity =new ProfessionalTaxDeductTypeEntity();
				if(professionalTaxDto.getProfessionalTaxDeductTypeDto()!=null)
				professionalTaxDeductTypeEntity.setPtaxDeductTypeId(professionalTaxDto.getProfessionalTaxDeductTypeDto().getPtaxDeductTypeId());
				professionalTaxEntity.setProfessionalTaxDeductTypeEntity(professionalTaxDeductTypeEntity);			
			}
		}
		
		
		
		 if(UPDATE_PROFESSIONAL_TAX.equals(flowId)){
			 List<ProfessionalTaxEntity> listByName=storageProfessionalTaxDAO.findBySlabName(professionalTaxEntity.getSlabName());
				List<ProfessionalTaxEntity> listByCode=storageProfessionalTaxDAO.findByCode(professionalTaxEntity.getPtaxCode());
			    
				List<ProfessionalTaxEntity> list=storageProfessionalTaxDAO.findByNameAndCode(professionalTaxEntity.getSlabName(),professionalTaxEntity.getPtaxCode());
				//logger.info(flowId +": "+list);
				professionalTaxOutMessage = new ProfessionalTaxOutputMessage();
				
		if(listByName != null && listByName.size() > 0 &&
				!listByName.get(0).getPtaxId().equals(professionalTaxEntity.getPtaxId())){				
					ErrorDTO errorDTO=new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed");
					ErrorListDTO errorListDTO=new ErrorListDTO();
					errorListDTO.addError(errorDTO);				
					professionalTaxOutMessage.setErrorListDTO(errorListDTO);
				}
				else if(listByCode != null && listByCode.size() > 0 && !listByCode
						.get(0).getPtaxId()
						.equals(professionalTaxEntity.getPtaxId())){				
					ErrorDTO errorDTO=new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed");
					ErrorListDTO errorListDTO=new ErrorListDTO();
					errorListDTO.addError(errorDTO);				
					professionalTaxOutMessage.setErrorListDTO(errorListDTO);
				}
				else{

			 professionalTaxOutMessage = new ProfessionalTaxOutputMessage();
			// logger.info("Updating ProfessionalTaxEntity "+professionalTaxEntity);
			 storageProfessionalTaxDAO.update(professionalTaxEntity);
			 professionalTaxOutMessage.setErrorListDTO(null);
			}
		 }
	 else if(ADD_PROFESSIONAL.equals(flowId)){
			try {
			List<ProfessionalTaxEntity> listByName=storageProfessionalTaxDAO.findBySlabName(professionalTaxEntity.getSlabName());
			List<ProfessionalTaxEntity> listByCode=storageProfessionalTaxDAO.findByCode(professionalTaxEntity.getPtaxCode());
		    
			List<ProfessionalTaxEntity> list=storageProfessionalTaxDAO.findByNameAndCode(professionalTaxEntity.getSlabName(),professionalTaxEntity.getPtaxCode());
			//logger.info(flowId +": "+list);
			professionalTaxOutMessage = new ProfessionalTaxOutputMessage();
			if(listByName!=null && listByName.size()>0){				
				ErrorDTO errorDTO=new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed");
				ErrorListDTO errorListDTO=new ErrorListDTO();
				errorListDTO.addError(errorDTO);				
				professionalTaxOutMessage.setErrorListDTO(errorListDTO);
			}
			else if(listByCode!=null && listByCode.size()>0){				
				ErrorDTO errorDTO=new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed");
				ErrorListDTO errorListDTO=new ErrorListDTO();
				errorListDTO.addError(errorDTO);				
				professionalTaxOutMessage.setErrorListDTO(errorListDTO);
			}
			else{
				professionalTaxOutMessage.setErrorListDTO(null);
				storageProfessionalTaxDAO.create(professionalTaxEntity);
			 }	
		 }
		catch (BeansException e) {
				e.printStackTrace();
			}
		}	
		
	    else if(DELETE_PROFESSIONAL_TAX.equals(flowId)){
			// logger.info("Updating ProfessionalTaxEntity "+professionalTaxEntity);
			 
			 storageProfessionalTaxDAO.delete(professionalTaxEntity);
			}
		
		else if(SEARCH_PROFESSIONAL_TAX.equals(flowId)){
			ProfessionalTaxDTO professionalTaxDTO = professionalTaxInputMessage.getProfessionalTaxDTO();
			//System.out.println(professionalTaxDTO.toString());
			List<ProfessionalTaxEntity> list = storageProfessionalTaxDAO.search(professionalTaxDTO.getSlabName(),professionalTaxDTO.getDeductAmount());
			professionalTaxOutMessage = new ProfessionalTaxOutputMessage();
			if ( list != null ) {
				List<ProfessionalTaxDTO> resultList = convertProfessionalTaxEntityListTOProfessionalTaxDTOList(list);
				professionalTaxOutMessage.setProfessionalTaxDTOList(resultList);
			}
		}
		
		else if(FIND_ALL_PROFESSIONAL_TAXS.equals(flowId)){
			List<ProfessionalTaxEntity> list = storageProfessionalTaxDAO.load();
			professionalTaxOutMessage = new ProfessionalTaxOutputMessage();
		   
			// set the data to the output message.
			if ( list != null ) {
				List<ProfessionalTaxDTO> resultList = convertProfessionalTaxEntityListTOProfessionalTaxDTOList(list);
				professionalTaxOutMessage.setProfessionalTaxDTOList(resultList);
			}
		}
		
		else if(FIND_PROFESSIONAL_TAX_BY_ID.equals(flowId)){
			ProfessionalTaxDTO professionalTaxDTO = professionalTaxInputMessage.getProfessionalTaxDTO();
			try {
				professionalTaxEntity.setSlabName(professionalTaxDTO.getSlabName());
				professionalTaxEntity.setPtaxCode(professionalTaxDTO.getPtaxCode());
				
				BeanUtils.copyProperties(professionalTaxDTO,professionalTaxEntity);
			} catch (BeansException e) {
				e.printStackTrace();
			}
			List<ProfessionalTaxEntity> list = storageProfessionalTaxDAO.findById(professionalTaxDTO.getPtaxId());
			professionalTaxOutMessage = new ProfessionalTaxOutputMessage();
			if ( list != null ) {
				List<ProfessionalTaxDTO> resultList = convertProfessionalTaxEntityListTOProfessionalTaxDTOList(list);
				professionalTaxOutMessage.setProfessionalTaxDTOList(resultList);
			}
		}
	}
		
		
	@Override
	public void formatOutput() {

		if (ADD_PROFESSIONAL.equals(flowId)) {

		} else if (UPDATE_PROFESSIONAL_TAX.equals(flowId)) {

		} else if (DELETE_PROFESSIONAL_TAX.equals(flowId)) {

		} else if (FIND_PROFESSIONAL_TAX_BY_NO.equals(flowId)) {

		} else if (FIND_ALL_PROFESSIONAL_TAXS.equals(flowId)) {

		}
	}
	private List<ProfessionalTaxDTO> convertProfessionalTaxEntityListTOProfessionalTaxDTOList(List<ProfessionalTaxEntity> list){

		List<ProfessionalTaxDTO> resultList = new ArrayList<ProfessionalTaxDTO>();
		ProfessionalTaxDTO professionalTaxDTO ;
		for (ProfessionalTaxEntity entity : list ) {
			professionalTaxDTO = new ProfessionalTaxDTO();
			professionalTaxDTO.setProfessionalTaxDeductTypeDto(new ProfessionalTaxDeductTypeDTO());
			try {
				BeanUtils.copyProperties(entity,professionalTaxDTO);
				if(entity.getProfessionalTaxDeductTypeEntity()!=null){
					BeanUtils.copyProperties(entity.getProfessionalTaxDeductTypeEntity(),professionalTaxDTO.getProfessionalTaxDeductTypeDto());
					}
			} catch (BeansException e) {
				e.printStackTrace();
			}
			resultList.add(professionalTaxDTO);
			
		}
		return resultList;
	}

	}
