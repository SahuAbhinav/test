package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.PartyTypeEntity;
import com.advanz.erp.masters.entity.jpa.PartyTypeEntity;
import com.advanz.erp.masters.model.PartyTypeDTO;
import com.advanz.erp.masters.model.msg.PartyTypeOutMessage;
import com.advanz.erp.masters.model.msg.PartyTypeInputMessage;
import com.advanz.erp.masters.model.msg.PartyTypeOutMessage;
import com.advanz.erp.masters.service.business.IPartyTypeService;
import com.advanz.erp.masters.storage.IStoragePartyTypeDAO;

public class PartyTypeServiceImpl implements IPartyTypeService {

	public static final String CREATE_PARTYTYPE = "PartyType";
	public static final String UPDATE_PARTYTYPE = "UpdatePartyType";
	public static final String ADD_PARTYTYPE = "AddPartyType";
	public static final String DELETE_PARTYTYPE = "DeletePartyType";
	public static final String FIND_PARTYTYPE_BY_ID = "FindPartyTypeById";
	public static final String FIND_ALL_PARTYTYPES = "FindAllPartyTypes";
	public static final String FIND_PARTYTYPES = "FindPartyTypes";
	public static final String GET_ERROR_IF_USED="GetErrorIfUsed";
	

	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStoragePartyTypeDAO storagePartyTypeDAO;

	public PartyTypeInputMessage partyTypeInputMessage;

	public PartyTypeOutMessage partyTypeOutMessage;

	private static final Logger logger = LoggerFactory
			.getLogger(PartyTypeServiceImpl.class);

	@Override
	public PartyTypeOutMessage createPartyType(
			PartyTypeInputMessage partyTypeInputMessage) {

		flowId = ADD_PARTYTYPE;
		// assign the message to the class level variable.
		this.partyTypeInputMessage = partyTypeInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return partyTypeOutMessage;
	}

	@Override
	public PartyTypeOutMessage updatePartyType(
			PartyTypeInputMessage partyTypeInputMessage) {

		flowId = UPDATE_PARTYTYPE;
		// assign the message to the class level variable.
		this.partyTypeInputMessage = partyTypeInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return partyTypeOutMessage;
	}

	@Override
	public PartyTypeOutMessage deletePartyType(
			PartyTypeInputMessage partyTypeInputMessage) {
		flowId = DELETE_PARTYTYPE;
		// assign the message to the class level variable.
		this.partyTypeInputMessage = partyTypeInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return partyTypeOutMessage;

	}

	@Override
	public PartyTypeOutMessage findPartyTypeById(
			PartyTypeInputMessage partyTypeInputMessage) {
		flowId = FIND_PARTYTYPE_BY_ID;
		// assign the message to the class level variable.
		this.partyTypeInputMessage = partyTypeInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return partyTypeOutMessage;

	}

	@Override
	public PartyTypeOutMessage findAllPartyTypes() {
		flowId = FIND_ALL_PARTYTYPES;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return partyTypeOutMessage;
	}

	@Override
	public PartyTypeOutMessage findPartyType(
			PartyTypeInputMessage partyTypeInputMessage) {
		flowId = FIND_PARTYTYPES;
		this.partyTypeInputMessage = partyTypeInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return partyTypeOutMessage;
	}

	@Override
	public PartyTypeOutMessage getErrorIfUsed(
			PartyTypeInputMessage partyTypeInputMessage) {
		
		flowId = GET_ERROR_IF_USED;
		this.partyTypeInputMessage = partyTypeInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return partyTypeOutMessage;
	}
	
	
	
	@Override
	public boolean validateInput() {

		if (ADD_PARTYTYPE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_PARTYTYPE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_PARTYTYPE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_PARTYTYPE_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_PARTYTYPES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_PARTYTYPES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (GET_ERROR_IF_USED.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		

		return false;
	}

	@Override
	public void performBusinessLogic() {
		PartyTypeEntity partyTypeEntity = new PartyTypeEntity();
				if (ADD_PARTYTYPE.equals(flowId)) {
					try {
				BeanUtils.copyProperties(
						partyTypeInputMessage.getPartyTypeDTO(),
						partyTypeEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			List<PartyTypeEntity> nameList = storagePartyTypeDAO.findByName(partyTypeEntity.getPartyTypeDesc());
			List<PartyTypeEntity> codeList = storagePartyTypeDAO.findByCode(partyTypeEntity.getPartyTypeCode());
		//	logger.info(flowId + ": " + nameList);
		//	logger.info(flowId + ": " + codeList);
			partyTypeOutMessage = new PartyTypeOutMessage();
			if ((nameList != null && nameList.size() > 0) || (codeList != null && codeList.size() > 0)) {
				ErrorDTO errorDTO = new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				partyTypeOutMessage.setErrorListDTO(errorListDTO);
			} else {
				partyTypeOutMessage.setErrorListDTO(null);
				storagePartyTypeDAO.create(partyTypeEntity);
			}
		} else if (UPDATE_PARTYTYPE.equals(flowId)) {
			try {
				BeanUtils.copyProperties(partyTypeInputMessage.getPartyTypeDTO(),partyTypeEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<PartyTypeEntity> nameList = storagePartyTypeDAO.findByName(partyTypeEntity.getPartyTypeDesc());
			List<PartyTypeEntity> codeList = storagePartyTypeDAO.findByCode(partyTypeEntity.getPartyTypeCode());
		//	logger.info(flowId + ": " + nameList);
		//	logger.info(flowId + ": " + codeList);
			partyTypeOutMessage = new PartyTypeOutMessage();
			if ((nameList != null && nameList.size() > 0 && !nameList.get(0)
					.getPartyTypeId().equals(partyTypeEntity.getPartyTypeId()))
					|| (codeList != null && codeList.size() > 0 && !codeList.get(0).getPartyTypeId()
							.equals(partyTypeEntity.getPartyTypeId()))) {
				ErrorDTO errorDTO = new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed. ");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				partyTypeOutMessage.setErrorListDTO(errorListDTO);
			} else {
				partyTypeOutMessage.setErrorListDTO(null);
				storagePartyTypeDAO.update(partyTypeEntity);
			}
		} else if (DELETE_PARTYTYPE.equals(flowId)) {
			try {
				BeanUtils.copyProperties(partyTypeInputMessage.getPartyTypeDTO(),partyTypeEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				storagePartyTypeDAO.delete(partyTypeEntity);
			} catch (Exception ex) {
				partyTypeOutMessage = new PartyTypeOutMessage();
				ErrorDTO errorDTO = new ErrorDTO("1", ex.getMessage());
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				partyTypeOutMessage.setErrorListDTO(errorListDTO);
			}
		} else if (FIND_PARTYTYPE_BY_ID.equals(flowId)) {
			PartyTypeDTO partyTypeDto = partyTypeInputMessage.getPartyTypeDTO();
			List<PartyTypeEntity> list = storagePartyTypeDAO
					.findById(partyTypeDto.getPartyTypeId());
			partyTypeOutMessage = new PartyTypeOutMessage();
			if (list != null) {
				List<PartyTypeDTO> resultList = convertPartyTypeEntityListTOPartyTypeDTOList(list);
				System.out.println("resultList : " + resultList.size());
				partyTypeOutMessage.setPartyTypeDTOList(resultList);
			}
		} else if (FIND_ALL_PARTYTYPES.equals(flowId)) {
			List<PartyTypeEntity> list = storagePartyTypeDAO.load();
			partyTypeOutMessage = new PartyTypeOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<PartyTypeDTO> resultList = convertPartyTypeEntityListTOPartyTypeDTOList(list);
				partyTypeOutMessage.setPartyTypeDTOList(resultList);
			}
		} else if (FIND_PARTYTYPES.equals(flowId)) {
			PartyTypeDTO partyTypeDto = partyTypeInputMessage.getPartyTypeDTO();
			List<PartyTypeEntity> list = storagePartyTypeDAO.search(
					partyTypeDto.getPartyTypeDesc(),
					partyTypeDto.getPartyTypeCode());
			partyTypeOutMessage = new PartyTypeOutMessage();
			if (list != null) {
				List<PartyTypeDTO> resultList = convertPartyTypeEntityListTOPartyTypeDTOList(list);
				partyTypeOutMessage.setPartyTypeDTOList(resultList);
			}
		} 
		else if (GET_ERROR_IF_USED.equals(flowId)) {
				Integer id=partyTypeInputMessage.getPartyTypeDTO().getPartyTypeId();
				if(storagePartyTypeDAO.isInUsed(id)){
				partyTypeOutMessage = new PartyTypeOutMessage();
				ErrorDTO errorDTO = new ErrorDTO("1","Cannot Delete PartyType,Used in Party Setup");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				partyTypeOutMessage.setErrorListDTO(errorListDTO);
				}			
		}
	}
	

	@Override
	public void formatOutput() {

		if (ADD_PARTYTYPE.equals(flowId)) {

		} else if (UPDATE_PARTYTYPE.equals(flowId)) {

		} else if (DELETE_PARTYTYPE.equals(flowId)) {

		} else if (FIND_PARTYTYPE_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_PARTYTYPES.equals(flowId)) {

		} else if (GET_ERROR_IF_USED.equals(flowId)) {

		}
	
	}

	private List<PartyTypeDTO> convertPartyTypeEntityListTOPartyTypeDTOList(
			List<PartyTypeEntity> list) {
		List<PartyTypeDTO> resultList = new ArrayList<PartyTypeDTO>();
		PartyTypeDTO partyTypeDto;
		for (PartyTypeEntity entity : list) {
			partyTypeDto = new PartyTypeDTO();
			try {
				BeanUtils.copyProperties(entity, partyTypeDto);
			} catch (BeansException e) {
				e.printStackTrace();
			}
			resultList.add(partyTypeDto);
			/*
			 * try { BeanUtils.copyProperties(partyTypeDto, entity);
			 * 
			 * 
			 * resultList.add(partyTypeDto); } catch (IllegalAccessException e)
			 * { // TODO Auto-generated catch block e.printStackTrace(); } catch
			 * (InvocationTargetException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }
			 */
		}
		return resultList;
	}

	


}
