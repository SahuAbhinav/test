package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.AreaEntity;
import com.advanz.erp.masters.entity.jpa.RegionEntity;
import com.advanz.erp.masters.model.AreaDTO;
import com.advanz.erp.masters.model.RegionDTO;
import com.advanz.erp.masters.model.criteria.AreaSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.AreaInputMessage;
import com.advanz.erp.masters.model.msg.AreaOutputMessage;
import com.advanz.erp.masters.service.business.IAreaService;
import com.advanz.erp.masters.storage.IStorageAreaDAO;

public class AreaServiceImpl implements IAreaService {
	private static final Logger logger = LoggerFactory
	.getLogger(AreaServiceImpl.class);
	
	public static final String CREATE_AREA = "CreateArea";
	public static final String UPDATE_AREA = "UpdateArea";
	public static final String DELETE_AREA = "DeleteArea";
	public static final String FIND_AREA_BY_ID = "FindAreaById";
	public static final String FIND_ALL_AREA = "FindAllAreas";
	public static final String SEARCH_AREA = "SearchAreas";
	public static final String FIND_BY_REGION_ID = "FindByRegionId";
	public static final String PRE_REMOVE_CHECK="PreRemoveCheck";
	
	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageAreaDAO storageAreaDAO;

	public AreaInputMessage areaInputMessage;

	public AreaOutputMessage areaOutputMessage;

	@Override
	public AreaOutputMessage createArea(AreaInputMessage areaInputMessage) {

		flowId = CREATE_AREA;
		// assign the message to the class level variable.
		this.areaInputMessage = areaInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return areaOutputMessage;
	}

	@Override
	public AreaOutputMessage updateArea(AreaInputMessage areaInputMessage) {

		flowId = UPDATE_AREA;
		// assign the message to the class level variable.
		this.areaInputMessage = areaInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return areaOutputMessage;
	}

	@Override
	public AreaOutputMessage deleteArea(AreaInputMessage areaInputMessage) {
		flowId = DELETE_AREA;
		// assign the message to the class level variable.
		this.areaInputMessage = areaInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return areaOutputMessage;

	}

	@Override
	public AreaOutputMessage findAreaById(AreaInputMessage areaInputMessage) {
		flowId = FIND_AREA_BY_ID;
		// assign the message to the class level variable.
		this.areaInputMessage = areaInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return areaOutputMessage;

	}

	@Override
	public AreaOutputMessage findAllAreas() {
		flowId = FIND_ALL_AREA;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return areaOutputMessage;
	}

	@Override
	public AreaOutputMessage search(AreaInputMessage areaInputMessage) {
		flowId = SEARCH_AREA;
		this.areaInputMessage=areaInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return areaOutputMessage;

	}
	
	@Override
	public AreaOutputMessage findAreaByRegionId(AreaInputMessage areaInputMessage) {
		flowId = FIND_BY_REGION_ID;
		this.areaInputMessage=areaInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return areaOutputMessage;
	}
	
	@Override
	public AreaOutputMessage checkBeforeRemove(AreaInputMessage areaInputMessage) {
		flowId = PRE_REMOVE_CHECK;
		this.areaInputMessage=areaInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return areaOutputMessage;
	}
	
	@Override
	public boolean validateInput() {

		if (CREATE_AREA.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_AREA.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_AREA.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_AREA_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_AREA.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_AREA.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_BY_REGION_ID.equals(flowId)) {
			return true ; // no business rules
		}else if (PRE_REMOVE_CHECK.equals(flowId)) {
			return true ; // no business rules
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {

		AreaEntity areaEntity = new AreaEntity();
		if (areaInputMessage != null) {
			AreaDTO areaDTO = areaInputMessage.getAreaDTO();
			if (areaDTO != null) {
				BeanUtils.copyProperties(areaDTO, areaEntity);
				RegionEntity regionEntity = new RegionEntity();
				if (areaDTO.getRegionDTO() != null)
					regionEntity.setRegionId(areaDTO.getRegionDTO().getRegionId());
				areaEntity.setRegionEntity(regionEntity);
			}
		}

		if (CREATE_AREA.equals(flowId)) {
			// check duplicate area name
			List<AreaEntity> list1 = storageAreaDAO.findByNameAndCode(areaEntity.getAreaName(), null);
			// check duplicate areaCode
			List<AreaEntity> list2 = storageAreaDAO.findByNameAndCode(
					null, areaEntity.getAreaCode());
			areaOutputMessage = new AreaOutputMessage();
			boolean errors = false;
			if (list1 != null && list1.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("1", "Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				areaOutputMessage.setErrorListDTO(errorListDTO);
				errors = true;
			}
			if (list2 != null && list2.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("2", "Sorry, Record already exist, Duplicate entries are not allowed.");
				if (!errors) {
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					areaOutputMessage.setErrorListDTO(errorListDTO);
				} else {
					areaOutputMessage.getErrorListDTO().addError(errorDTO);
				}
				errors = true;
			}
			if (!errors) {
				areaOutputMessage.setErrorListDTO(null);
				storageAreaDAO.create(areaEntity);
			}
			
		} else if (UPDATE_AREA.equals(flowId)) {
			// check duplicate area name
			List<AreaEntity> list1 = storageAreaDAO.findByNameAndCode(areaEntity.getAreaName(), null);
			// check duplicate areaCode
			List<AreaEntity> list2 = storageAreaDAO.findByNameAndCode(
					null, areaEntity.getAreaCode());
			areaOutputMessage = new AreaOutputMessage();
			boolean errors = false;
			if (list1 != null && list1.size() > 0 && !list1.get(0).getAreaId().equals(areaEntity.getAreaId())) {
				ErrorDTO errorDTO = new ErrorDTO("1", "Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				areaOutputMessage.setErrorListDTO(errorListDTO);
				errors = true;
			}
			if (list2 != null && list2.size() > 0 && !list2.get(0).getAreaId().equals(areaEntity.getAreaId())) {
				ErrorDTO errorDTO = new ErrorDTO("2", "Sorry, Record already exist, Duplicate entries are not allowed.");
				if (!errors) {
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					areaOutputMessage.setErrorListDTO(errorListDTO);
				} else {
					areaOutputMessage.getErrorListDTO().addError(errorDTO);
				}
				errors = true;
			}
			if (!errors) {
				areaOutputMessage.setErrorListDTO(null);
				storageAreaDAO.update(areaEntity);
			}
		} else if (DELETE_AREA.equals(flowId)) {
			storageAreaDAO.delete(areaEntity);
		} else if (FIND_AREA_BY_ID.equals(flowId)) {
		List<AreaEntity>list=	storageAreaDAO.findById(areaEntity.getAreaId());
		popUpList(list);
		} else if (FIND_ALL_AREA.equals(flowId)) {
			List<AreaEntity> list = storageAreaDAO.load();
			popUpList(list);			
		}else if (SEARCH_AREA.equals(flowId)) {
			AreaSearchCriteriaDTO searchCriteria=areaInputMessage.getSearchCriteria();			
			List<AreaEntity> list = storageAreaDAO.search(searchCriteria.getAreaName(),searchCriteria.getAreaCode(),searchCriteria.getRegionName());
			popUpList(list);
		}  else if (FIND_BY_REGION_ID.equals(flowId)) {
			List<AreaEntity> list = storageAreaDAO.findByRegionId(areaInputMessage.getAreaDTO().getRegionDTO().getRegionId());
			popUpList(list);			
		}if(PRE_REMOVE_CHECK.equals(flowId)){
			logger.info(flowId);
			if(storageAreaDAO.isInUsed(areaInputMessage.getAreaDTO().getAreaId())){
			//	logger.info("TRUE");		
				areaOutputMessage=new AreaOutputMessage();
				ErrorDTO errorDTO = new ErrorDTO("1", "Area can not be Removed");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				areaOutputMessage.setErrorListDTO(errorListDTO);
			}else{
				areaOutputMessage=null;
			}
		}
	}
	void popUpList(List<AreaEntity> list) {
		areaOutputMessage = new AreaOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<AreaDTO> resultList = new ArrayList<AreaDTO>();
			AreaDTO areaDTO;
			for (AreaEntity entity : list) {
				areaDTO = new AreaDTO();
				// Spring
				areaDTO.setRegionDTO(new RegionDTO());
				BeanUtils.copyProperties(entity, areaDTO);
				if (entity.getRegionEntity() != null) {
					BeanUtils.copyProperties(entity.getRegionEntity(),
							areaDTO.getRegionDTO());
				}
				resultList.add(areaDTO);

			}
			areaOutputMessage.setAreaDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (CREATE_AREA.equals(flowId)) {

		} else if (UPDATE_AREA.equals(flowId)) {

		} else if (DELETE_AREA.equals(flowId)) {

		} else if (FIND_AREA_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_AREA.equals(flowId)) {

		} else if (SEARCH_AREA.equals(flowId)) {

		}
	}

	

}
