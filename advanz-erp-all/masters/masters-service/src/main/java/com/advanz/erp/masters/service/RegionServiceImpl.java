package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.RegionEntity;
import com.advanz.erp.masters.entity.jpa.StateEntity;
import com.advanz.erp.masters.model.RegionDTO;
import com.advanz.erp.masters.model.StateDTO;
import com.advanz.erp.masters.model.criteria.RegionSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.AreaOutputMessage;
import com.advanz.erp.masters.model.msg.BatchInputMessage;
import com.advanz.erp.masters.model.msg.BatchOutMessage;
import com.advanz.erp.masters.model.msg.RegionInputMessage;
import com.advanz.erp.masters.model.msg.RegionOutputMessage;
import com.advanz.erp.masters.service.business.IRegionService;
import com.advanz.erp.masters.storage.IStorageRegionDAO;

public class RegionServiceImpl implements IRegionService {
	private static final Logger logger = LoggerFactory
			.getLogger(RegionServiceImpl.class);
	public static final String CREATE_REGION = "CreateRegion";
	public static final String UPDATE_REGION = "UpdateRegion";
	public static final String DELETE_REGION = "DeleteRegion";
	public static final String FIND_REGION_BY_ID = "FindRegionByNo";
	public static final String FIND_ALL_REGION = "FindAllRegiones";
	public static final String SEARCH_REGION = "SearchRegiones";
	public static final String FIND_BY_STATE_ID = "FindByStateId";
	public static final String PRE_REMOVE_CHECK="PreRemoveCheck";
	
	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageRegionDAO storageRegionDAO;

	public RegionInputMessage regionInputMessage;

	public RegionOutputMessage regionOutputMessage;

	@Override
	public RegionOutputMessage createRegion(
			RegionInputMessage regionInputMessage) {

		flowId = CREATE_REGION;
		// assign the message to the class level variable.
		this.regionInputMessage = regionInputMessage;
		// call the template method

		advanzErpServiceTemplate.execute(this);

		return regionOutputMessage;
	}

	@Override
	public RegionOutputMessage updateRegion(
			RegionInputMessage regionInputMessage) {

		flowId = UPDATE_REGION;
		// assign the message to the class level variable.
		this.regionInputMessage = regionInputMessage;

		// call the template method
		advanzErpServiceTemplate.execute(this);

		return regionOutputMessage;
	}

	@Override
	public RegionOutputMessage deleteRegion(
			RegionInputMessage regionInputMessage) {
		flowId = DELETE_REGION;
		// assign the message to the class level variable.
		this.regionInputMessage = regionInputMessage;

		// call the template method
		advanzErpServiceTemplate.execute(this);

		return regionOutputMessage;

	}

	@Override
	public RegionOutputMessage findRegionById(
			RegionInputMessage regionInputMessage) {
		flowId = FIND_REGION_BY_ID;
		// assign the message to the class level variable.
		this.regionInputMessage = regionInputMessage;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return regionOutputMessage;

	}

	@Override
	public RegionOutputMessage findAllRegions() {
		flowId = FIND_ALL_REGION;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return regionOutputMessage;
	}

	@Override
	public RegionOutputMessage search(RegionInputMessage regionInputMessage) {
		flowId = SEARCH_REGION;
		this.regionInputMessage=regionInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return regionOutputMessage;

	}

	@Override
	public RegionOutputMessage findByStateId(RegionInputMessage regionInputMessage) {
		flowId = FIND_BY_STATE_ID;
		this.regionInputMessage=regionInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return regionOutputMessage;
	}
	
	@Override
	public RegionOutputMessage checkBeforeRemove(
			RegionInputMessage regionInputMessage) {
		flowId = PRE_REMOVE_CHECK;
		this.regionInputMessage=regionInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return regionOutputMessage;
	}
	
	@Override
	public boolean validateInput() {

		if (CREATE_REGION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_REGION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_REGION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_REGION_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_REGION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_REGION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if(FIND_BY_STATE_ID.equals(flowId)) {
			return true; //no business validation
		}else if(PRE_REMOVE_CHECK.equals(flowId)) {
			return true; //no business validation
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {

		RegionEntity regionEntity = new RegionEntity();
		if (regionInputMessage != null) {
			RegionDTO regionDTO = regionInputMessage.getRegionDTO();
			if (regionDTO != null) {
				BeanUtils.copyProperties(regionDTO, regionEntity);
				StateEntity stateEntity = new StateEntity();
				if (regionDTO.getStateDTO() != null)
					stateEntity
							.setStateId(regionDTO.getStateDTO().getStateId());
				regionEntity.setStateEntity(stateEntity);
			}
		}

		if (CREATE_REGION.equals(flowId)) {
			// check duplicate region name
			List<RegionEntity> list1 = storageRegionDAO.findByNameAndCode(regionEntity.getRegionName(), null);
			// check duplicate regionCode
			List<RegionEntity> list2 = storageRegionDAO.findByNameAndCode(
					null, regionEntity.getRegionCode());
			regionOutputMessage = new RegionOutputMessage();
			boolean errors = false;
			if (list1 != null && list1.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("1", "Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				regionOutputMessage.setErrorListDTO(errorListDTO);
				errors = true;
			}
			if (list2 != null && list2.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("1", "Sorry, Record already exist, Duplicate entries are not allowed.");
				if (!errors) {
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					regionOutputMessage.setErrorListDTO(errorListDTO);
				} else {
					regionOutputMessage.getErrorListDTO().addError(errorDTO);
				}
				errors = true;
			}
			if (!errors) {
				regionOutputMessage.setErrorListDTO(null);
				storageRegionDAO.create(regionEntity);
			}

		} else if (UPDATE_REGION.equals(flowId)) {
			// check duplicate region name
			List<RegionEntity> list1 = storageRegionDAO.findByNameAndCode(regionEntity.getRegionName(), null);
			// check duplicate regionCode
			List<RegionEntity> list2 = storageRegionDAO.findByNameAndCode(
					null, regionEntity.getRegionCode());
			regionOutputMessage = new RegionOutputMessage();
			boolean errors = false;
			if (list1 != null && list1.size() > 0 && !list1.get(0).getRegionId().equals(regionEntity.getRegionId())) {
				ErrorDTO errorDTO = new ErrorDTO("1", "Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				regionOutputMessage.setErrorListDTO(errorListDTO);
				errors = true;
			}
			if (list2 != null && list2.size() > 0 && !list2.get(0).getRegionId().equals(regionEntity.getRegionId())) {
				ErrorDTO errorDTO = new ErrorDTO("2", "Sorry, Record already exist, Duplicate entries are not allowed.");
				if (!errors) {
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					regionOutputMessage.setErrorListDTO(errorListDTO);
				} else {
					regionOutputMessage.getErrorListDTO().addError(errorDTO);
				}
				errors = true;
			}
			if (!errors) {
				regionOutputMessage.setErrorListDTO(null);
				storageRegionDAO.update(regionEntity);
			}
		} else if (DELETE_REGION.equals(flowId)) {
			storageRegionDAO.delete(regionEntity);
		} else if (FIND_REGION_BY_ID.equals(flowId)) {
			List<RegionEntity> list = storageRegionDAO.findById(regionEntity.getRegionId());
			popUpList(list);
		} else if (FIND_ALL_REGION.equals(flowId)) {
			List<RegionEntity> list = storageRegionDAO.load();
			popUpList(list);
		}else if (SEARCH_REGION.equals(flowId)) {
			RegionSearchCriteriaDTO searchCriteria=regionInputMessage.getSearchCriteria();
			List<RegionEntity> list = storageRegionDAO.search(searchCriteria.getRegionName(),searchCriteria.getRegionCode(),searchCriteria.getStateName());
			popUpList(list);
		} else if(FIND_BY_STATE_ID.equals(flowId)) {
			Integer stateId = regionInputMessage.getRegionDTO().getStateId();
			List<RegionEntity> list = storageRegionDAO.findByStateId(stateId);
			popUpList(list);
		}if(PRE_REMOVE_CHECK.equals(flowId)){
			logger.info(flowId);
			if(storageRegionDAO.isInUsed(regionInputMessage.getRegionDTO().getRegionId())){
				logger.info("TRUE");		
				regionOutputMessage=new RegionOutputMessage();
				ErrorDTO errorDTO = new ErrorDTO("1", "Region can not be Removed");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				regionOutputMessage.setErrorListDTO(errorListDTO);
			}else{
				regionOutputMessage=null;
			}
		}
	}

	void popUpList(List<RegionEntity> list) {
		regionOutputMessage = new RegionOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<RegionDTO> resultList = new ArrayList<RegionDTO>();
			RegionDTO regionDTO;
			for (RegionEntity entity : list) {
				regionDTO = new RegionDTO();
				// Spring
				regionDTO.setStateDTO(new StateDTO());
				BeanUtils.copyProperties(entity, regionDTO);
				if (entity.getStateEntity() != null) {
					BeanUtils.copyProperties(entity.getStateEntity(),
							regionDTO.getStateDTO());
				}
				resultList.add(regionDTO);

			}
			regionOutputMessage.setRegionDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (CREATE_REGION.equals(flowId)) {

		} else if (UPDATE_REGION.equals(flowId)) {

		} else if (DELETE_REGION.equals(flowId)) {

		} else if (FIND_REGION_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_REGION.equals(flowId)) {

		} else if (SEARCH_REGION.equals(flowId)) {

		}
	}

	
}
