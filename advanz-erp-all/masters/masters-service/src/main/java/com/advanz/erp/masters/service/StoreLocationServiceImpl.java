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
import com.advanz.erp.masters.entity.jpa.StoreLocationEntity;
import com.advanz.erp.masters.model.StoreLocationDTO;
import com.advanz.erp.masters.model.msg.StoreLocationInputMessage;
import com.advanz.erp.masters.model.msg.StoreLocationOutMessage;
import com.advanz.erp.masters.model.msg.ItemCategoryOutMessage;
import com.advanz.erp.masters.service.business.IStoreLocationService;
import com.advanz.erp.masters.storage.IStorageStoreLocationDAO;

@Service
public class StoreLocationServiceImpl implements IStoreLocationService {

	public static final String CREATE_STORELOCATION = "StoreLocation";
	public static final String UPDATE_STORELOCATION = "UpdateStoreLocation";
	public static final String ADD_STORELOCATION = "AddStoreLocation";
	public static final String DELETE_STORELOCATION = "DeleteStoreLocation";
	public static final String FIND_STORELOCATION_BY_ID = "FindStoreLocationById";
	public static final String FIND_ALL_STORELOCATIONS = "FindAllCompnies";
	public static final String FIND_STORELOCATIONS = "FindCompanies";
	public static final String PRE_REMOVE_CHECK="PreRemoveCheck";
	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageStoreLocationDAO storageStoreLocationDAO;

	public StoreLocationInputMessage storeLocationInputMessage;

	private static final Logger logger = LoggerFactory.getLogger(StoreLocationServiceImpl.class);

	public StoreLocationOutMessage storeLocationOutMessage;

	@Override
	public StoreLocationOutMessage createStoreLocation(
			StoreLocationInputMessage storeLocationInputMessage) {

		flowId = ADD_STORELOCATION;
		// assign the message to the class level variable.
		this.storeLocationInputMessage = storeLocationInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return storeLocationOutMessage;
	}

	@Override
	public StoreLocationOutMessage updateStoreLocation(
			StoreLocationInputMessage storeLocationInputMessage) {

		flowId = UPDATE_STORELOCATION;
		// assign the message to the class level variable.
		this.storeLocationInputMessage = storeLocationInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return storeLocationOutMessage;
	}

	@Override
	public StoreLocationOutMessage deleteStoreLocation(
			StoreLocationInputMessage storeLocationInputMessage) {
		flowId = DELETE_STORELOCATION;
		// assign the message to the class level variable.
		this.storeLocationInputMessage = storeLocationInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return storeLocationOutMessage;

	}

	@Override
	public StoreLocationOutMessage findStoreLocationById(
			StoreLocationInputMessage storeLocationInputMessage) {
		flowId = FIND_STORELOCATION_BY_ID;
		this.storeLocationInputMessage = storeLocationInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return storeLocationOutMessage;

	}

	@Override
	public StoreLocationOutMessage findAllStoreLocations() {
		flowId = FIND_ALL_STORELOCATIONS;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return storeLocationOutMessage;
	}

	@Override
	public StoreLocationOutMessage findStoreLocation(StoreLocationInputMessage storeLocationInputMessage) {
		flowId = FIND_STORELOCATIONS;
		this.storeLocationInputMessage = storeLocationInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return storeLocationOutMessage;
	}
     
	@Override
	public StoreLocationOutMessage checkBeforeRemove(
			StoreLocationInputMessage storeLocationInputMessage) {
		flowId = PRE_REMOVE_CHECK;
		this.storeLocationInputMessage = storeLocationInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return storeLocationOutMessage;
	}

	@Override
	public boolean validateInput() {

		if (ADD_STORELOCATION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_STORELOCATION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_STORELOCATION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_STORELOCATION_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_STORELOCATIONS.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_STORELOCATIONS.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (PRE_REMOVE_CHECK.equals(flowId)){
			return true;
			}

		return false;
	}

	@Override
	public void performBusinessLogic() {
		StoreLocationEntity storeLocationEntity = new StoreLocationEntity();
				if (ADD_STORELOCATION.equals(flowId)) {
					try {
				BeanUtils.copyProperties(storeLocationInputMessage.getStoreLocationDTO(),
						storeLocationEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			List<StoreLocationEntity> nameList = storageStoreLocationDAO.findByName(storeLocationEntity.getStoreLocation());
			List<StoreLocationEntity> codeList = storageStoreLocationDAO.findByCode(storeLocationEntity.getLocationCode());
			logger.info(flowId + ": " + nameList);
			logger.info(flowId + ": " + codeList);
			storeLocationOutMessage = new StoreLocationOutMessage();
			if ((nameList != null && nameList.size() > 0) || (codeList != null && codeList.size() > 0)) {
				ErrorDTO errorDTO = new ErrorDTO("1",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				storeLocationOutMessage.setErrorListDTO(errorListDTO);
			} else {
				storeLocationOutMessage.setErrorListDTO(null);
				storageStoreLocationDAO.create(storeLocationEntity);
			}
		} else if (UPDATE_STORELOCATION.equals(flowId)) {
			try {
				BeanUtils.copyProperties(storeLocationInputMessage.getStoreLocationDTO(),
						storeLocationEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<StoreLocationEntity> nameList = storageStoreLocationDAO.findByName(storeLocationEntity.getStoreLocation());
			List<StoreLocationEntity> codeList = storageStoreLocationDAO.findByCode(storeLocationEntity.getLocationCode());
			logger.info(flowId + ": " + nameList);
			logger.info(flowId + ": " + codeList);
			storeLocationOutMessage = new StoreLocationOutMessage();
			if((nameList!=null && nameList.size()>0 && !nameList.get(0).getStoreLocationId().equals(storeLocationEntity.getStoreLocationId())) || (codeList!=null && codeList.size()>0 && !codeList.get(0).getStoreLocationId().equals(storeLocationEntity.getStoreLocationId()))){
				ErrorDTO errorDTO = new ErrorDTO("1",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				storeLocationOutMessage.setErrorListDTO(errorListDTO);
			} else {
				storeLocationOutMessage.setErrorListDTO(null);
				storageStoreLocationDAO.update(storeLocationEntity);
			}
			
		} else if (DELETE_STORELOCATION.equals(flowId)) {
			try {
				BeanUtils.copyProperties(storeLocationInputMessage.getStoreLocationDTO(),
						storeLocationEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			storageStoreLocationDAO.delete(storeLocationEntity);
		} else if (FIND_STORELOCATION_BY_ID.equals(flowId)) {
			StoreLocationDTO storeLocationDto = storeLocationInputMessage.getStoreLocationDTO();
			List<StoreLocationEntity> list = storageStoreLocationDAO.findById(storeLocationDto
					.getStoreLocationId());
			storeLocationOutMessage = new StoreLocationOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<StoreLocationDTO> resultList = convertStoreLocationEntityListTOStoreLocationDtoList(list);
				storeLocationOutMessage.setStoreLocationDTOList(resultList);
			}
		} else if (FIND_ALL_STORELOCATIONS.equals(flowId)) {
			List<StoreLocationEntity> list = storageStoreLocationDAO.load();
			storeLocationOutMessage = new StoreLocationOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<StoreLocationDTO> resultList = convertStoreLocationEntityListTOStoreLocationDtoList(list);
				storeLocationOutMessage.setStoreLocationDTOList(resultList);
			}
		} else if (FIND_STORELOCATIONS.equals(flowId)) {
			StoreLocationDTO storeLocationDto = storeLocationInputMessage.getStoreLocationDTO();
			System.out.println("################## StoreLocation Service "
					+ storeLocationDto.toString());
			List<StoreLocationEntity> list = storageStoreLocationDAO.search(
					storeLocationDto.getStoreLocation(), storeLocationDto.getLocationCode());
			storeLocationOutMessage = new StoreLocationOutMessage();
			if (list != null) {
				List<StoreLocationDTO> resultList = convertStoreLocationEntityListTOStoreLocationDtoList(list);
				storeLocationOutMessage.setStoreLocationDTOList(resultList);
			}
		}else if(PRE_REMOVE_CHECK.equals(flowId)){
			if(storageStoreLocationDAO.isInUsed(storeLocationInputMessage.getStoreLocationDTO().getStoreLocationId())){
				logger.info("TRUE");
					storeLocationOutMessage=new StoreLocationOutMessage();
					ErrorDTO errorDTO=new ErrorDTO("1","Store Location can not be Removed");
					ErrorListDTO errorListDTO=new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					storeLocationOutMessage.setErrorListDTO(errorListDTO);
			}else{
			storeLocationOutMessage=null;
			}
		}
	}

	@Override
	public void formatOutput() {

		if (ADD_STORELOCATION.equals(flowId)) {

		} else if (UPDATE_STORELOCATION.equals(flowId)) {

		} else if (DELETE_STORELOCATION.equals(flowId)) {

		} else if (FIND_STORELOCATION_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_STORELOCATIONS.equals(flowId)) {

		}
	}

	private List<StoreLocationDTO> convertStoreLocationEntityListTOStoreLocationDtoList(
			List<StoreLocationEntity> list) {
		List<StoreLocationDTO> resultList = new ArrayList<StoreLocationDTO>();
		StoreLocationDTO storeLocationDto;
		for (StoreLocationEntity entity : list) {
			storeLocationDto = new StoreLocationDTO();
			try {
				BeanUtils.copyProperties(entity, storeLocationDto);
			} catch (BeansException e) {
				e.printStackTrace();
			}
			resultList.add(storeLocationDto);
			/*
			 * try { BeanUtils.copyProperties(storeLocationDto, entity);
			 * 
			 * 
			 * resultList.add(storeLocationDto); } catch (IllegalAccessException e) {
			 * // TODO Auto-generated catch block e.printStackTrace(); } catch
			 * (InvocationTargetException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }
			 */
		}
		return resultList;
	}

	

}
