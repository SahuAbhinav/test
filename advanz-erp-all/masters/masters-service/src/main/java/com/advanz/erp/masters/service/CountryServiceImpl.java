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
import com.advanz.erp.masters.entity.jpa.CountryEntity;
import com.advanz.erp.masters.entity.jpa.ZoneEntity;
import com.advanz.erp.masters.model.CountryDTO;
import com.advanz.erp.masters.model.criteria.CountrySearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.CountryInputMessage;
import com.advanz.erp.masters.model.msg.CountryOutMessage;
import com.advanz.erp.masters.model.msg.SalesOrderMasterOutputMessage;
import com.advanz.erp.masters.service.business.ICountryService;
import com.advanz.erp.masters.storage.IStorageCountryDAO;
import com.advanz.erp.masters.storage.IStorageZoneDAO;

@Service
public class CountryServiceImpl implements ICountryService {

	public static final String CREATE_COUNTRY = "CreateCountry";
	public static final String UPDATE_COUNTRY = "UpdateCountry";
	public static final String ADD_COUNTRY = "AddCountry";
	public static final String DELETE_COUNTRY = "DeleteCountry";
	public static final String FIND_COUNTRY_BY_NO = "FindCountryByNo";
	public static final String FIND_ALL_COUNTRYS = "FindAllCountrys";
	public static final String SEARCH_COUNTRY = "SearchCountries";
	public static final String PRE_REMOVE_CHECK="PreRemoveCheck";

	public String flowId = "";
	private String countryName = "";
	private String countryCode = "";
	
	private static final Logger logger = LoggerFactory
	.getLogger(CountryServiceImpl.class);
	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageCountryDAO storageCountryDAO;
	
	@Autowired
	public IStorageZoneDAO storageZoneDAO;

	public CountryInputMessage countryInputMessage;

	public CountryOutMessage countryOutMessage;

	@Override
	public CountryOutMessage createCountry(
			CountryInputMessage countryInputMessage) {

		flowId = ADD_COUNTRY;
		// assign the message to the class level variable.

		// call the template method
		this.countryInputMessage = countryInputMessage;
		advanzErpServiceTemplate.execute(this);

		return countryOutMessage;
	}

	@Override
	public CountryOutMessage updateCountry(
			CountryInputMessage countryInputMessage) {

		flowId = UPDATE_COUNTRY;
		// call the template method
		this.countryInputMessage = countryInputMessage;
		advanzErpServiceTemplate.execute(this);

		return countryOutMessage;
	}

	@Override
	public CountryOutMessage deleteCountry(
			CountryInputMessage countryInputMessage) {
		flowId = DELETE_COUNTRY;
		// call the template method
		this.countryInputMessage = countryInputMessage;
		advanzErpServiceTemplate.execute(this);

		return countryOutMessage;

	}

	@Override
	public CountryOutMessage findCountryById(
			CountryInputMessage countryInputMessage) {
		flowId = FIND_COUNTRY_BY_NO;
		// call the template method
		this.countryInputMessage = countryInputMessage;
		advanzErpServiceTemplate.execute(this);
		return countryOutMessage;

	}

	@Override
	public CountryOutMessage findAllCountrys() {
		flowId = FIND_ALL_COUNTRYS;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return countryOutMessage;
	}

	@Override
	public CountryOutMessage search(CountryInputMessage countryInputMessage) {
		flowId = SEARCH_COUNTRY;
		this.countryInputMessage = countryInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return countryOutMessage;

	}@Override
	public CountryOutMessage checkBeforeRemove(CountryInputMessage countryInputMessage) {
		flowId = PRE_REMOVE_CHECK;
		this.countryInputMessage = countryInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return countryOutMessage;

	}
	
	@Override
	public boolean validateInput() {

		if (ADD_COUNTRY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_COUNTRY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_COUNTRY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_COUNTRY_BY_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_ALL_COUNTRYS.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_COUNTRY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (PRE_REMOVE_CHECK.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		return false;
	}
	
	@Override
	public void performBusinessLogic() {

		CountryEntity countryEntity = new CountryEntity();

		if (countryInputMessage != null) {
			CountryDTO countryDTO = countryInputMessage.getCountryDTO();
			if (countryDTO != null) {
				BeanUtils.copyProperties(countryDTO, countryEntity);

			}
		}

		if (ADD_COUNTRY.equals(flowId)) {
			List<CountryEntity> list1 = storageCountryDAO.findByNameAndCode(
					countryEntity.getCountryName(), null);
			// check duplicate areaCode
			List<CountryEntity> list2 = storageCountryDAO.findByNameAndCode(
					null, countryEntity.getCountryCode());
			countryOutMessage = new CountryOutMessage();
			boolean errors = false;
			if (list1 != null && list1.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("1", "Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				countryOutMessage.setErrorListDTO(errorListDTO);
				errors = true;
			}
			if (list2 != null && list2.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("2", "Sorry, Record already exist, Duplicate entries are not allowed.");
				if (!errors) {
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					countryOutMessage.setErrorListDTO(errorListDTO);
				} else {
					countryOutMessage.getErrorListDTO().addError(errorDTO);
				}
				errors = true;
			}
			if (!errors) {
				countryOutMessage.setErrorListDTO(null);
				storageCountryDAO.create(countryEntity);
			}

		} else if (UPDATE_COUNTRY.equals(flowId)) {
			List<CountryEntity> list1 = storageCountryDAO.findByNameAndCode(
					countryEntity.getCountryName(), null);
			// check duplicate areaCode
//			List<CountryEntity> list2 = storageCountryDAO.findByNameAndCode(
//					null, countryEntity.getCountryCode());
			countryOutMessage = new CountryOutMessage();
			boolean errors = false;
			if (list1 != null
					&& list1.size() > 0
					&& !list1.get(0).getCountryId()
							.equals(countryEntity.getCountryId())) {
				ErrorDTO errorDTO = new ErrorDTO("1", "Sorry, Record already exist, Duplicate entries are not allowed. ");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				countryOutMessage.setErrorListDTO(errorListDTO);
				errors = true;
			}

			if (!errors) {
				countryOutMessage.setErrorListDTO(null);
				storageCountryDAO.update(countryEntity);
			}
		} else if (DELETE_COUNTRY.equals(flowId)) {
			countryOutMessage = new CountryOutMessage();	
		List<ZoneEntity> list=storageZoneDAO.findZoneByCountryId(countryEntity.getCountryId());
		if((list!=null && list.size()>0 )){				
			ErrorDTO errorDTO=new ErrorDTO("1","Country use in Zone. You can not delete.");
			ErrorListDTO errorListDTO=new ErrorListDTO();
			errorListDTO.addError(errorDTO);				
			countryOutMessage.setErrorListDTO(errorListDTO);
		}else{
			
			countryOutMessage.setErrorListDTO(null);
			storageCountryDAO.delete(countryEntity);
		}
		} else if (FIND_COUNTRY_BY_NO.equals(flowId)) {
			List<CountryEntity> list = storageCountryDAO.findById(countryEntity
					.getCountryId());
			popUpList(list);

		} else if (FIND_ALL_COUNTRYS.equals(flowId)) {
			List<CountryEntity> list = storageCountryDAO.findAllCountry();
			countryOutMessage = new CountryOutMessage();
			// set the data to the output message.
		//	System.out.println(" service data :-"+list);
			
			if (list != null) {
				List<CountryDTO> resultList = new ArrayList<CountryDTO>();
				CountryDTO countryDTO;
				for (CountryEntity entity : list) {				
					countryDTO = new CountryDTO();
					BeanUtils.copyProperties(entity,countryDTO);
					resultList.add(countryDTO);

				}
				countryOutMessage.setCountryDTOList(resultList);
			}
		}else if (SEARCH_COUNTRY.equals(flowId)) {
			CountrySearchCriteriaDTO searchCriteria = countryInputMessage.getSearchCriteria();
			List<CountryEntity> list = storageCountryDAO.search(searchCriteria.getCountryName(),searchCriteria.getCountryCode());
			popUpList(list);
		}else if(PRE_REMOVE_CHECK.equals(flowId)){
			if(storageCountryDAO.isInUsed(countryInputMessage.getCountryDTO().getCountryId())){
				logger.info("TRUE");		
				countryOutMessage=new CountryOutMessage();
				ErrorDTO errorDTO = new ErrorDTO("1", "Country can not be Removed,Used in Zone");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				countryOutMessage.setErrorListDTO(errorListDTO);
			}else{
				countryOutMessage=null;
			}
		}

	}

	void popUpList(List<CountryEntity> list) {
		countryOutMessage = new CountryOutMessage();
		// set the data to the output message.
		if (list != null) {
			List<CountryDTO> resultList = new ArrayList<CountryDTO>();
			CountryDTO countryDTO;
			for (CountryEntity entity : list) {
				countryDTO = new CountryDTO();
				// Spring
				countryDTO.setCountryDTO(new CountryDTO());
				BeanUtils.copyProperties(entity, countryDTO);

				resultList.add(countryDTO);

			}
			countryOutMessage.setCountryDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (ADD_COUNTRY.equals(flowId)) {

		} else if (UPDATE_COUNTRY.equals(flowId)) {

		} else if (DELETE_COUNTRY.equals(flowId)) {

		} else if (FIND_COUNTRY_BY_NO.equals(flowId)) {

		}
	}

}
