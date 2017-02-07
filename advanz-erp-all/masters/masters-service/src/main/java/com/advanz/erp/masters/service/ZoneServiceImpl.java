package com.advanz.erp.masters.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.CompanyEntity;
import com.advanz.erp.masters.entity.jpa.CountryEntity;
import com.advanz.erp.masters.entity.jpa.StateEntity;
import com.advanz.erp.masters.entity.jpa.ZoneEntity;
import com.advanz.erp.masters.model.CompanyDTO;
import com.advanz.erp.masters.model.CountryDTO;
import com.advanz.erp.masters.model.ZoneDTO;
import com.advanz.erp.masters.model.criteria.ZoneSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.ZoneInputMessage;
import com.advanz.erp.masters.model.msg.ZoneOutputMessage;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageCompanyDAO;
import com.advanz.erp.masters.storage.IStorageCountryDAO;
import com.advanz.erp.masters.storage.IStorageStateDAO;
import com.advanz.erp.masters.storage.IStorageZoneDAO;

@Service
public class ZoneServiceImpl implements IZoneService {

	public static final String CREATE_ZONE = "CreateZone";
	public static final String UPDATE_ZONE = "UpdateZone";
	public static final String ADD_ZONE = "AddZone";
	public static final String DELETE_ZONE = "DeleteZone";
	public static final String FIND_ZONE_BY_NO = "FindZoneByNo";
	public static final String FIND_ALL_ZONE = "FindAllZones";
	public static final String SEARCH_ZONE = "SearchZones";
	public static final String PRE_REMOVE_CHECK = "PreRemoveCheck";

	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageZoneDAO storageZoneDAO;

	@Autowired
	public IStorageStateDAO storageStateDAO;

	@Autowired
	public IStorageCountryDAO storageCountryDAO;

	@Autowired
	public IStorageCompanyDAO storageCompanyDAO;

	public ZoneInputMessage zoneInputMessage;

	public ZoneOutputMessage zoneOutputMessage;

	@Override
	public ZoneOutputMessage createZone(ZoneInputMessage zoneInputMessage) {

		flowId = ADD_ZONE;
		// assign the message to the class level variable.

		// call the template method
		this.zoneInputMessage = zoneInputMessage;
		advanzErpServiceTemplate.execute(this);

		return zoneOutputMessage;
	}

	@Override
	public ZoneOutputMessage updateZone(ZoneInputMessage zoneInputMessage) {

		flowId = UPDATE_ZONE;
		// call the template method
		this.zoneInputMessage = zoneInputMessage;
		advanzErpServiceTemplate.execute(this);

		return zoneOutputMessage;
	}

	@Override
	public ZoneOutputMessage deleteZone(ZoneInputMessage zoneInputMessage) {
		flowId = DELETE_ZONE;
		// call the template method
		this.zoneInputMessage = zoneInputMessage;
		advanzErpServiceTemplate.execute(this);

		return zoneOutputMessage;

	}

	@Override
	public ZoneOutputMessage findZoneById(ZoneInputMessage zoneInputMessage) {
		flowId = FIND_ZONE_BY_NO;
		// call the template method
		this.zoneInputMessage = zoneInputMessage;
		advanzErpServiceTemplate.execute(this);

		return zoneOutputMessage;

	}

	@Override
	public ZoneOutputMessage findAllZones() {
		flowId = FIND_ALL_ZONE;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return zoneOutputMessage;
	}

	@Override
	public ZoneOutputMessage search(ZoneInputMessage zoneInputMessage) {
		flowId = SEARCH_ZONE;
		// System.out.print("--------------=search-------------"+zoneInputMessage.getZoneDTO());
		this.zoneInputMessage = zoneInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return zoneOutputMessage;

	}

	@Override
	public ZoneOutputMessage checkBeforeRemove(ZoneInputMessage zoneInputMessage) {
		flowId = PRE_REMOVE_CHECK;
		this.zoneInputMessage = zoneInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return zoneOutputMessage;

	}

	@Override
	public boolean validateInput() {

		if (ADD_ZONE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_ZONE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_ZONE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ZONE_BY_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_ZONE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_ZONE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (PRE_REMOVE_CHECK.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {

		ZoneEntity zoneEntity = new ZoneEntity();
		// BeanUtils.copyProperties(batchInputMessage.getBatchDTO(),
		// batchEntity);
		if (zoneInputMessage != null) {
			ZoneDTO zoneDTO = zoneInputMessage.getZoneDTO();
			if (zoneDTO != null) {
				BeanUtils.copyProperties(zoneDTO, zoneEntity);
				CountryEntity countryEntity = new CountryEntity();
				if (zoneDTO.getCountryDTO() != null)
					countryEntity.setCountryId(zoneDTO.getCountryDTO()
							.getCountryId());
				zoneEntity.setCountryEntity(countryEntity);
			}
		}
		if (ADD_ZONE.equals(flowId)) {
			// ZoneDTO zoneDTO = zoneInputMessage.getZoneDTO();
			// BeanUtils.copyProperties(zoneDTO, zoneEntity);
			// System.out.print("------------entituttyutggbgbg--------------------------"+zoneEntity.getZoneId());
			// storageZoneDAO.create(zoneEntity);
			List<ZoneEntity> list1 = storageZoneDAO.findByNameAndCode(
					zoneEntity.getZoneName(), null);
			// check duplicate areaCode
			List<ZoneEntity> list2 = storageZoneDAO.findByNameAndCode(null,
					zoneEntity.getZoneName());
			zoneOutputMessage = new ZoneOutputMessage();
			boolean errors = false;
			if (list1 != null && list1.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("1",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				zoneOutputMessage.setErrorListDTO(errorListDTO);
				errors = true;
			}
			if (list2 != null && list2.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("2",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				if (!errors) {
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					zoneOutputMessage.setErrorListDTO(errorListDTO);
				} else {
					zoneOutputMessage.getErrorListDTO().addError(errorDTO);
				}
				errors = true;
			}
			if (!errors) {
				zoneOutputMessage.setErrorListDTO(null);
				storageZoneDAO.create(zoneEntity);
			}
		} else if (UPDATE_ZONE.equals(flowId)) {
			// ZoneDTO zoneDTO = zoneInputMessage.getZoneDTO();
			// BeanUtils.copyProperties(zoneDTO, zoneEntity);
			// storageZoneDAO.update(zoneEntity);
			List<ZoneEntity> list1 = storageZoneDAO.findByNameAndCode(
					zoneEntity.getZoneName(), null);
			// check duplicate areaCode
			List<ZoneEntity> list2 = storageZoneDAO.findByNameAndCode(null,
					zoneEntity.getZoneName());
			zoneOutputMessage = new ZoneOutputMessage();
			boolean errors = false;
			if (list1 != null && list1.size() > 0
					&& !list1.get(0).getZoneId().equals(zoneEntity.getZoneId())) {
				ErrorDTO errorDTO = new ErrorDTO("1",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				zoneOutputMessage.setErrorListDTO(errorListDTO);
				errors = true;
			}
			if (list2 != null && list2.size() > 0
					&& !list2.get(0).getZoneId().equals(zoneEntity.getZoneId())) {
				ErrorDTO errorDTO = new ErrorDTO("2",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				if (!errors) {
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					zoneOutputMessage.setErrorListDTO(errorListDTO);
				} else {
					zoneOutputMessage.getErrorListDTO().addError(errorDTO);
				}
				errors = true;
			}
			if (!errors) {
				zoneOutputMessage.setErrorListDTO(null);
				storageZoneDAO.update(zoneEntity);
			}
		} else if (DELETE_ZONE.equals(flowId)) {
			ZoneDTO zoneDTO = zoneInputMessage.getZoneDTO();
			BeanUtils.copyProperties(zoneDTO, zoneEntity);
			// / zoneEntity.setDeletedFlag(true);

			try {
				List<StateEntity> list = storageStateDAO
						.findStateByZoneId(zoneEntity.getZoneId());
				if ((list != null && list.size() > 0)) {
					zoneOutputMessage = new ZoneOutputMessage();
					ErrorDTO errorDTO = new ErrorDTO("1",
							"Zone use in State. You can not delete.");
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					if (errorListDTO != null) {
						zoneOutputMessage.setErrorListDTO(errorListDTO);
						System.out.print("----gggggggggggggggg----"
								+ zoneEntity.getZoneId());
					} else {
						System.out
								.print("----FIND_ZONE_BY_NO--------uhfhtttt---------nullllllll-----------------"
										+ zoneEntity.getZoneId());
					}
				} else {
					zoneOutputMessage = new ZoneOutputMessage();
					zoneOutputMessage.setErrorListDTO(null);
					storageZoneDAO.delete(zoneEntity);
				}
			} catch (BeansException e) {
				e.printStackTrace();
			}

		} else if (FIND_ZONE_BY_NO.equals(flowId)) {
			ZoneDTO zoneDTO = zoneInputMessage.getZoneDTO();
			BeanUtils.copyProperties(zoneDTO, zoneEntity);
			System.out
					.print("----FIND_ZONE_BY_NO--------entituttyutggbgbg--------------------------"
							+ zoneEntity.getZoneId());
			List<ZoneEntity> list = storageZoneDAO.findById(zoneEntity
					.getZoneId());
			System.out
					.print("----FIND_ZONE_BY_NO--------entituttyutggbgbg--------------------------"
							+ zoneEntity.getZoneId());
			popUpList(list);

		} else if (FIND_ALL_ZONE.equals(flowId)) {
			List<ZoneEntity> list = storageZoneDAO.findAllZones();
			zoneOutputMessage = new ZoneOutputMessage();
			// set the data to the output message.
			if (list != null) {
				List<ZoneDTO> resultList = new ArrayList<ZoneDTO>();
				ZoneDTO zoneDTO;
				for (ZoneEntity entity : list) {
					zoneDTO = new ZoneDTO();
					zoneDTO.setCountryDTO(new CountryDTO());
					BeanUtils.copyProperties(entity, zoneDTO);
					if (entity.getCountryEntity() != null) {
						BeanUtils.copyProperties(entity.getCountryEntity(),
								zoneDTO.getCountryDTO());
					}
					// List<CountryEntity>
					// countryList=storageCountryDAO.findById(zoneDTO.getCountryId());
					// if(countryList !=null){
					// CountryEntity cc=(CountryEntity)countryList.get(0);
					// zoneDTO.setCountryName(cc.getCountryName());
					// }

					resultList.add(zoneDTO);
				}
				zoneOutputMessage.setZoneDTOList(resultList);
			}
		} else if (SEARCH_ZONE.equals(flowId)) {
			ZoneSearchCriteriaDTO searchCriteria = zoneInputMessage
					.getSearchCriteria();
			List<ZoneEntity> list = storageZoneDAO.search(
					searchCriteria.getZoneName(), searchCriteria.getZoneCode(),
					searchCriteria.getCountryName());
			popUpList(list);
			// System.out.print("List----- :----asdsa");
			// ZoneDTO zoneDTO1=zoneInputMessage.getZoneDTO();
			// List<ZoneEntity> list =
			// storageZoneDAO.search(zoneDTO1.getZoneName(),zoneDTO1.getZoneCode(),zoneDTO1.getCountryDTO().getCountryName());
			// System.out.print("List----- :----"+list.size());
			// if (list != null) {
			// List<ZoneDTO> resultList = new ArrayList<ZoneDTO>();
			// ZoneDTO zoneDTO;
			// for (ZoneEntity entity : list) {
			// zoneDTO = new ZoneDTO();
			// zoneDTO.setCountryDTO(new CountryDTO());
			// BeanUtils.copyProperties(entity,zoneDTO);
			// if(entity.getCountryEntity()!=null){
			// BeanUtils.copyProperties(entity.getCountryEntity(),zoneDTO.getCountryDTO());
			// }
			// resultList.add(zoneDTO);
			// }
			// zoneOutputMessage.setZoneDTOList(resultList);
			// }
		} else if (PRE_REMOVE_CHECK.equals(flowId)) {
			if (storageZoneDAO.isInUsed(zoneInputMessage.getZoneDTO()
					.getZoneId())) {
				// logger.info("TRUE");
				zoneOutputMessage = new ZoneOutputMessage();
				ErrorDTO errorDTO = new ErrorDTO("1",
						"Zone can not be Removed,Used in State");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				zoneOutputMessage.setErrorListDTO(errorListDTO);
			} else {
				zoneOutputMessage = null;
			}
		}

	}

	void popUpList(List<ZoneEntity> list) {
		zoneOutputMessage = new ZoneOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<ZoneDTO> resultList = new ArrayList<ZoneDTO>();
			ZoneDTO zoneDTO;
			for (ZoneEntity entity : list) {
				zoneDTO = new ZoneDTO();
				// Spring
				zoneDTO.setCountryDTO(new CountryDTO());
				BeanUtils.copyProperties(entity, zoneDTO);
				if (entity.getCountryEntity() != null) {
					BeanUtils.copyProperties(entity.getCountryEntity(),
							zoneDTO.getCountryDTO());
				}
				resultList.add(zoneDTO);

			}
			zoneOutputMessage.setZoneDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (ADD_ZONE.equals(flowId)) {

		} else if (UPDATE_ZONE.equals(flowId)) {

		} else if (DELETE_ZONE.equals(flowId)) {

		} else if (FIND_ZONE_BY_NO.equals(flowId)) {

		} else if (FIND_ALL_ZONE.equals(flowId)) {

		} else if (SEARCH_ZONE.equals(flowId)) {

		} else if (PRE_REMOVE_CHECK.equals(flowId)) {

		}
	}

	@Override
	public Timestamp getFirstDayOfFinYear() {
		Calendar calendar = Calendar.getInstance();
		
		  calendar.set(Calendar.MONTH, Calendar.APRIL);
		  calendar.set(Calendar.DATE, 1);
		 

		Date dt = null;
		try {
			dt = getFinDate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Calendar calendar1 = Calendar.getInstance();

		if (dt != null) {
			calendar1.setTime(dt);
			calendar.set(Calendar.MONTH, calendar1.get(Calendar.MONTH));
			calendar.set(Calendar.DATE, calendar1.get(Calendar.DATE));
		}

		Date d = calendar.getTime();
		Timestamp Timestamp = new Timestamp(d.getTime());

		return Timestamp;
	}

	public Date getFinDate() {
		CompanyDTO companyDTO = null;
		Date date = null;
		List<CompanyDTO> resultList = null;
		// System.out.println("companyService....... " + companyService);
		List<CompanyEntity> list = storageCompanyDAO.load();
		// set the data to the output message.
		if (list != null) {
			resultList = convertCompanyEntityListTOCompanyDtoList(list);
		}

		if (resultList != null && resultList.size() > 0) {
			companyDTO = new CompanyDTO();
			companyDTO = (CompanyDTO) resultList.get(0);
		}
		if (companyDTO != null) {
			date = companyDTO.getFinancialYrBeg();

		}
		//System.out.println("date" + date);
		return date;
	}

	private List<CompanyDTO> convertCompanyEntityListTOCompanyDtoList(
			List<CompanyEntity> list) {
		List<CompanyDTO> resultList = new ArrayList<CompanyDTO>();
		CompanyDTO companyDto;
		for (CompanyEntity entity : list) {
			companyDto = new CompanyDTO();
			try {
				BeanUtils.copyProperties(entity, companyDto);
			} catch (BeansException e) {
				e.printStackTrace();
			}
			resultList.add(companyDto);

		}
		return resultList;
	}
}
