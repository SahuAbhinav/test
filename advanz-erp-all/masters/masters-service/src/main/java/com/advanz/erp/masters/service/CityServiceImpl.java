package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.AreaEntity;
import com.advanz.erp.masters.entity.jpa.CityEntity;
import com.advanz.erp.masters.entity.jpa.CountryEntity;
import com.advanz.erp.masters.entity.jpa.RegionEntity;
import com.advanz.erp.masters.entity.jpa.StateEntity;
import com.advanz.erp.masters.entity.jpa.ZoneEntity;
import com.advanz.erp.masters.model.AreaDTO;
import com.advanz.erp.masters.model.CityDTO;
import com.advanz.erp.masters.model.CityStateCountryDTO;
import com.advanz.erp.masters.model.CountryDTO;
import com.advanz.erp.masters.model.RegionDTO;
import com.advanz.erp.masters.model.StateDTO;
import com.advanz.erp.masters.model.ZoneDTO;
import com.advanz.erp.masters.model.criteria.CitySearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.CityInputMessage;
import com.advanz.erp.masters.model.msg.CityOutputMessage;
import com.advanz.erp.masters.model.msg.SalesOrderMasterOutputMessage;
import com.advanz.erp.masters.service.business.ICityService;
import com.advanz.erp.masters.storage.IStorageCityDAO;
@Service
public class CityServiceImpl implements ICityService {
	private static final Logger logger = LoggerFactory
	.getLogger(CityServiceImpl.class);

	
	public static final String CREATE_CITY = "CreateCity";
	public static final String UPDATE_CITY = "UpdateCity";	
	public static final String DELETE_CITY = "DeleteCity";
	public static final String FIND_CITY_BY_NO = "FindCityByNo";
	public static final String FIND_ALL_CITY = "FindAllCityes";
	public static final String SEARCH_CITY = "SearchCity";
	public static final String FIND_CITY_STATE_COUNTRY_BY_ID = "FindCityStateCountryById";
	public static final String PRE_REMOVE_CHECK="PreRemoveCheck";

	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageCityDAO storageCityDAO;

	public CityInputMessage cityInputMessage;

	public CityOutputMessage cityOutputMessage;

	@Override
	public CityOutputMessage createCity(CityInputMessage cityInputMessage) {

		flowId = CREATE_CITY;
		// assign the message to the class level variable.

		// call the template method
		this.cityInputMessage=cityInputMessage;
		advanzErpServiceTemplate.execute(this);
		

		return cityOutputMessage;
	}

	@Override
	public CityOutputMessage updateCity(CityInputMessage cityInputMessage) {

		flowId = UPDATE_CITY;
		
		this.cityInputMessage=cityInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return cityOutputMessage;
	}

	@Override
	public CityOutputMessage deleteCity(CityInputMessage cityInputMessage) {
		flowId = DELETE_CITY;
		
		this.cityInputMessage=cityInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return cityOutputMessage;

	}

	@Override
	public CityOutputMessage findCityById(CityInputMessage cityInputMessage) {
		flowId = FIND_CITY_BY_NO;
		
		this.cityInputMessage=cityInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return cityOutputMessage;

	}

	@Override
	public CityOutputMessage findAllCityes() {
		flowId = FIND_ALL_CITY;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return cityOutputMessage;
	}
	@Override
	public CityOutputMessage search(CityInputMessage cityInputMessage) {
		flowId = SEARCH_CITY;
		this.cityInputMessage=cityInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return cityOutputMessage;

	}
	@Override
	public CityOutputMessage checkBeforeRemove(CityInputMessage cityInputMessage) {
		flowId = PRE_REMOVE_CHECK;
		this.cityInputMessage=cityInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return cityOutputMessage;

	}
	@Override  
	public boolean validateInput() {

		if (CREATE_CITY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_CITY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_CITY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_CITY_BY_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_CITY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (SEARCH_CITY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_CITY_STATE_COUNTRY_BY_ID .equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (PRE_REMOVE_CHECK .equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		return false;
	}
	
	@Override
	public void performBusinessLogic() {
		
		CityEntity cityEntity = new CityEntity();
		if (cityInputMessage != null) {
			CityDTO cityDTO = cityInputMessage.getCityDTO();
			if (cityDTO != null) {
				BeanUtils.copyProperties(cityDTO, cityEntity);
				AreaEntity areaEntity = new AreaEntity();
				if (cityDTO.getAreaDTO() != null)
					areaEntity.setAreaId(cityDTO.getAreaDTO().getAreaId());
				cityEntity.setAreaEntity(areaEntity);
			}
		}
		
		
//		BeanUtils.copyProperties(batchInputMessage.getBatchDTO(), batchEntity);

		if (CREATE_CITY.equals(flowId)) {
			// check duplicate area name
			List<CityEntity> list1 = storageCityDAO.findByNameAndCode(cityEntity.getCityName(), null);
			// check duplicate areaCode
			List<CityEntity> list2 = storageCityDAO.findByNameAndCode(null, cityEntity.getCityCode());
			cityOutputMessage = new CityOutputMessage();
			boolean errors = false;
			if (list1 != null && list1.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("1", "Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				cityOutputMessage.setErrorListDTO(errorListDTO);
				errors = true;
			}
			if (list2 != null && list2.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("2", "Sorry, Record already exist, Duplicate entries are not allowed.");
				if (!errors) {
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					cityOutputMessage.setErrorListDTO(errorListDTO);
				} else {
					cityOutputMessage.getErrorListDTO().addError(errorDTO);
				}
				errors = true;
			}
			if (!errors) {
				cityOutputMessage.setErrorListDTO(null);
				storageCityDAO.create(cityEntity);
			}
		} else if (UPDATE_CITY.equals(flowId)) {
			// check duplicate area name
			List<CityEntity> list1 = storageCityDAO.findByNameAndCode(cityEntity.getCityName(), null);
			// check duplicate areaCode
			//List<CityEntity> list2 = storageCityDAO.findByNameAndCode(null, cityEntity.getCityCode());
			cityOutputMessage = new CityOutputMessage();
			boolean errors = false;
			if (list1 != null && list1.size() > 0 && !list1.get(0).getCityId().equals(cityEntity.getCityId())) {
				ErrorDTO errorDTO = new ErrorDTO("1", "Duplicate Area Name ");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				cityOutputMessage.setErrorListDTO(errorListDTO);
				errors = true;
			}

			if (!errors) {
				cityOutputMessage.setErrorListDTO(null);
				storageCityDAO.update(cityEntity);
			}
		} else if (DELETE_CITY.equals(flowId)) {
			storageCityDAO.delete(cityEntity);
		} else if (FIND_CITY_BY_NO.equals(flowId)) {
			List<CityEntity>list=	storageCityDAO.findById(cityEntity.getCityId());
			popUpList(list);
			} else if (FIND_ALL_CITY.equals(flowId)) {
				List<CityEntity> list = storageCityDAO.load();
				popUpList(list);			
			}else if (SEARCH_CITY.equals(flowId)) {
				CitySearchCriteriaDTO searchCriteria=cityInputMessage.getSearchCriteria();			
				List<CityEntity> list = storageCityDAO.search(searchCriteria.getCityName(),searchCriteria.getCityCode(),searchCriteria.getAreaName());
				popUpList(list);
			}else if (FIND_CITY_STATE_COUNTRY_BY_ID.equals(flowId)) {
				List<CityEntity>list=	storageCityDAO.findById(cityEntity.getCityId());
				//logger.info("List : "+list);
				if(list!=null && list.size()>0){					 
					cityEntity = list.get(0);
					CityStateCountryDTO cityStateCountryDTO = new CityStateCountryDTO();
					cityStateCountryDTO.setCityName(cityEntity.getCityName());
					AreaEntity areaEntity = cityEntity.getAreaEntity();
					if (areaEntity != null) {
						RegionEntity regionEntity = areaEntity.getRegionEntity();
						if (regionEntity != null) {
							StateEntity stateEntity = regionEntity.getStateEntity();
							if(stateEntity!=null){
								cityStateCountryDTO.setStateName(stateEntity.getStateName());
								ZoneEntity zoneEntity=stateEntity.getZoneEntity();
								if(zoneEntity!=null){
									CountryEntity countryEntity=zoneEntity.getCountryEntity();
									cityStateCountryDTO.setCountryName(countryEntity.getCountryName());
								}
							}
						}
					}
					cityOutputMessage = new CityOutputMessage();
					cityOutputMessage.setCityStateCountryDTO(cityStateCountryDTO);
				}
				} if(PRE_REMOVE_CHECK.equals(flowId)){
					if(storageCityDAO.isInUsed(cityInputMessage.getCityDTO().getCityId())){
					//	logger.info("TRUE");		
						cityOutputMessage=new CityOutputMessage();
						ErrorDTO errorDTO = new ErrorDTO("1", "City can not be Removed,Used in Employee or Transpoter or Party");
						ErrorListDTO errorListDTO = new ErrorListDTO();
						errorListDTO.addError(errorDTO);
						cityOutputMessage.setErrorListDTO(errorListDTO);
					}else{
						cityOutputMessage=null;
					}
				}
		
		
			//storageCityDAO.findById(cityEntity.getUid());
		 
	}
	void popUpList(List<CityEntity> list) {
		cityOutputMessage = new CityOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<CityDTO> resultList = new ArrayList<CityDTO>();
			CityDTO cityDTO;
			for (CityEntity entity : list) {
				cityDTO = new CityDTO();
				// Spring
				cityDTO.setAreaDTO(new AreaDTO());
				BeanUtils.copyProperties(entity, cityDTO);
				if (entity.getAreaEntity() != null) {
					BeanUtils.copyProperties(entity.getAreaEntity(),cityDTO.getAreaDTO());
				}
				if (entity.getAreaEntity().getRegionEntity() != null) {
					cityDTO.getAreaDTO().setRegionDTO(new RegionDTO());
					BeanUtils.copyProperties(entity.getAreaEntity().getRegionEntity(),cityDTO.getAreaDTO().getRegionDTO());
				}
				if (entity.getAreaEntity().getRegionEntity().getStateEntity() != null) {
					cityDTO.getAreaDTO().getRegionDTO().setStateDTO(new StateDTO());
					BeanUtils.copyProperties(entity.getAreaEntity().getRegionEntity().getStateEntity(),cityDTO.getAreaDTO().getRegionDTO().getStateDTO());
				}
				if (entity.getAreaEntity().getRegionEntity().getStateEntity().getZoneEntity() != null) {
					cityDTO.getAreaDTO().getRegionDTO().getStateDTO().setZoneDTO(new ZoneDTO());
					BeanUtils.copyProperties(entity.getAreaEntity().getRegionEntity().getStateEntity().getZoneEntity(),cityDTO.getAreaDTO().getRegionDTO().getStateDTO().getZoneDTO());
				}
				
				if (entity.getAreaEntity().getRegionEntity().getStateEntity().getZoneEntity().getCountryEntity() != null) {
					cityDTO.getAreaDTO().getRegionDTO().getStateDTO().getZoneDTO().setCountryDTO(new CountryDTO());
					BeanUtils.copyProperties(entity.getAreaEntity().getRegionEntity().getStateEntity().getZoneEntity().getCountryEntity(),cityDTO.getAreaDTO().getRegionDTO().getStateDTO().getZoneDTO().getCountryDTO());
				}
				
				resultList.add(cityDTO);

			}
			cityOutputMessage.setCityDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (SEARCH_CITY.equals(flowId)) {

		} else if (UPDATE_CITY.equals(flowId)) {

		} else if (DELETE_CITY.equals(flowId)) {

		} else if (FIND_CITY_BY_NO.equals(flowId)) {

		} else if (FIND_ALL_CITY.equals(flowId)) {

		}else if (FIND_CITY_STATE_COUNTRY_BY_ID.equals(flowId)) {

		}
	}

	@Override
	public CityOutputMessage findCityStateCountryByCityId(
			CityInputMessage cityInputMessage) {
		flowId = FIND_CITY_STATE_COUNTRY_BY_ID;
		// assign the message to the class level variable.

		// call the template method
		this.cityInputMessage=cityInputMessage;
		advanzErpServiceTemplate.execute(this);		

		return cityOutputMessage;
	}

}
