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
import com.advanz.erp.masters.entity.jpa.CityEntity;
import com.advanz.erp.masters.entity.jpa.PartyEntity;
import com.advanz.erp.masters.entity.jpa.PartyTypeEntity;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.PartyTypeDTO;
import com.advanz.erp.masters.model.msg.PartyInputMessage;
import com.advanz.erp.masters.model.msg.PartyOutMessage;
import com.advanz.erp.masters.service.business.IPartyService;
import com.advanz.erp.masters.storage.IStorageCityDAO;
import com.advanz.erp.masters.storage.IStoragePartyDAO;
import com.advanz.erp.masters.storage.IStoragePartyTypeDAO;

public  class PartyServiceImpl implements IPartyService {

	public static final String CREATE_PARTY = "Party";
	public static final String UPDATE_PARTY = "UpdateParty";
	public static final String ADD_PARTY = "AddParty";
	public static final String DELETE_PARTY = "DeleteParty";
	public static final String FIND_PARTY_BY_ID = "FindPartyById";
	public static final String FIND_ALL_PARTYS = "FindAllPartys";
	public static final String FIND_PARTYS = "FindPartys";
	public static final String PRE_REMOVE_CHECK="PreRemoveCheck";
	public static final String FIND_CREDITOR_LIST = "FindCreditorPartys";
	
	public static final String FIND_DEBTOR_PARTYS = "FindDebtorPartys";
	public static final String FIND_DEBTOR_SHORT_INFO = "FindDebtorShortInfo";
	public static final String FIND_PARTY_NAME_AND_ID="FindPartyNameAndId";
	public String flowId = "";

	public static final String PRE_LOADED_PARTYS = "PreLoadePartys";
	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStoragePartyDAO storagePartyDAO;
	@Autowired
	public IStoragePartyTypeDAO storagePartyTypeDAO;
	@Autowired
	public IStorageCityDAO storageCityDAO;

	public PartyInputMessage partyInputMessage;

	public PartyOutMessage partyOutMessage;

	private static final Logger logger = LoggerFactory
			.getLogger(PartyServiceImpl.class);

	@Override
	public PartyOutMessage createParty(PartyInputMessage partyInputMessage) {

		flowId = ADD_PARTY;
		// assign the message to the class level variable.
		this.partyInputMessage = partyInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return partyOutMessage;
	}

	@Override
	public PartyOutMessage updateParty(PartyInputMessage partyInputMessage) {

		flowId = UPDATE_PARTY;
		// assign the message to the class level variable.
		this.partyInputMessage = partyInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return partyOutMessage;
	}

	@Override
	public PartyOutMessage deleteParty(PartyInputMessage partyInputMessage) {
		flowId = DELETE_PARTY;
		// assign the message to the class level variable.
		this.partyInputMessage = partyInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return partyOutMessage;

	}

	@Override
	public PartyOutMessage findPartyById(PartyInputMessage partyInputMessage) {
		flowId = FIND_PARTY_BY_ID;
		// assign the message to the class level variable.
		this.partyInputMessage = partyInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return partyOutMessage;

	}

	@Override
	public PartyOutMessage findAllPartys() {
		flowId = FIND_ALL_PARTYS;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return partyOutMessage;
	}

	@Override
	public PartyOutMessage findParty(PartyInputMessage partyInputMessage) {
		flowId = FIND_PARTYS;
		this.partyInputMessage = partyInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return partyOutMessage;
	}
	
	@Override
	public PartyOutMessage checkBeforeRemove(PartyInputMessage partyInputMessage) {
		flowId = PRE_REMOVE_CHECK;
		this.partyInputMessage = partyInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return partyOutMessage;
	}
	
	@Override
	public PartyOutMessage findDebtorPartyList() {
		flowId = FIND_DEBTOR_PARTYS;
		this.partyInputMessage = partyInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return partyOutMessage;
	}

	@Override
	public PartyOutMessage findDebtorPartyShortInfoList() {
		
		flowId = FIND_DEBTOR_SHORT_INFO;
		this.partyInputMessage = partyInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return partyOutMessage;
	}
	
	@Override
	public PartyOutMessage findPartyNameAndId() {
		flowId = FIND_PARTY_NAME_AND_ID;
		this.partyInputMessage = partyInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return partyOutMessage;
	}

	@Override
	public PartyOutMessage preloadedPartys() {
		// TODO Auto-generated method stub
		flowId = PRE_LOADED_PARTYS;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return partyOutMessage;
	}

	@Override
	public boolean validateInput() {

		if (ADD_PARTY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_PARTY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_PARTY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_PARTY_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_PARTYS.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_PARTYS.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (PRE_REMOVE_CHECK.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_DEBTOR_PARTYS.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_DEBTOR_SHORT_INFO .equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_CREDITOR_LIST .equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_PARTY_NAME_AND_ID .equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (PRE_LOADED_PARTYS .equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		
		return false;
	}

	@Override
	public void performBusinessLogic() {
		PartyEntity partyEntity = new PartyEntity();
		PartyTypeEntity partyTypeEntity=new PartyTypeEntity();
		if(partyInputMessage!=null){
		try {
			PartyDTO partyDTO=partyInputMessage.getPartyDTO();
			BeanUtils.copyProperties(partyDTO,partyEntity);
			if(partyDTO.getPartyTypeDTO()!=null)
			{
				partyTypeEntity.setPartyTypeId(partyDTO.getPartyTypeDTO().getPartyTypeId());
				partyEntity.setPartyTypeEntity(partyTypeEntity);
			}
			if("Dr".equals(partyDTO.getBalanceType())){
				if(partyDTO.getOpeningBalance()!=null)
					partyEntity.setOpeningBalance(partyDTO.getOpeningBalance()*-1);
			}
			
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
		if (ADD_PARTY.equals(flowId)) {
			// PartyDTO compDto = partyInputMessage.getPartyDTO();
			// partyEntity.setPartyName(compDto.getPartyName());

			

			List<PartyEntity> nameList = storagePartyDAO.findByName(partyEntity
					.getPartyName());
			List<PartyEntity> codeList = storagePartyDAO.findByCode(partyEntity
					.getPartyCode());
			logger.info(flowId + ": " + nameList);
			logger.info(flowId + ": " + codeList);
			partyOutMessage = new PartyOutMessage();
			if ((nameList != null && nameList.size() > 0)
					|| (codeList != null && codeList.size() > 0)) {
				ErrorDTO errorDTO = new ErrorDTO("1",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				partyOutMessage.setErrorListDTO(errorListDTO);
			} else {
				partyOutMessage.setErrorListDTO(null);
				storagePartyDAO.create(partyEntity);
			}
		} else if (UPDATE_PARTY.equals(flowId)) {

			List<PartyEntity> nameList = storagePartyDAO.findByName(partyEntity
					.getPartyName());
			List<PartyEntity> codeList = storagePartyDAO.findByCode(partyEntity
					.getPartyCode());
			logger.info(flowId + ": " + nameList);
			logger.info(flowId + ": " + codeList);
			partyOutMessage = new PartyOutMessage();
			if ((nameList != null && nameList.size() > 0 && !nameList.get(0)
					.getPartyId().equals(partyEntity.getPartyId()))
					|| (codeList != null && codeList.size() > 0 && !codeList
							.get(0).getPartyId()
							.equals(partyEntity.getPartyId()))) {
				ErrorDTO errorDTO = new ErrorDTO("1",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				partyOutMessage.setErrorListDTO(errorListDTO);
			} else {
				partyOutMessage.setErrorListDTO(null);
				storagePartyDAO.update(partyEntity);
			}
		} else if (DELETE_PARTY.equals(flowId)) {

			storagePartyDAO.delete(partyEntity);
		} else if (FIND_PARTY_BY_ID.equals(flowId)) {
			PartyDTO partyDto = partyInputMessage.getPartyDTO();
			List<PartyEntity> list = storagePartyDAO.findById(partyDto
					.getPartyId());
			partyOutMessage = new PartyOutMessage();
			if (list != null) {
				List<PartyDTO> resultList = convertPartyEntityListTOPartyDTOList(list);
				System.out.println("resultList : " + resultList.size());
				partyOutMessage.setPartyDTOList(resultList);
			}
		} else if (FIND_ALL_PARTYS.equals(flowId)) {
			List<PartyEntity> list = storagePartyDAO.load();
			partyOutMessage = new PartyOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<PartyDTO> resultList = convertPartyEntityListTOPartyDTOList(list);
				partyOutMessage.setPartyDTOList(resultList);
			}
		}
		else if (PRE_LOADED_PARTYS.equals(flowId)) {
			List<PartyEntity> list = storagePartyDAO.preLoad();
			partyOutMessage = new PartyOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<PartyDTO> resultList = convertPartyEntityListTOPartyDTOList(list);
				partyOutMessage.setPartyDTOList(resultList);
			}
		}
		
			else if (FIND_PARTY_NAME_AND_ID.equals(flowId)) {
				List<PartyEntity> list = storagePartyDAO.loadPartyNameAndId();
				partyOutMessage = new PartyOutMessage();
				// set the data to the output message.
				if (list != null) {
					List<PartyDTO> resultList = convertPartyEntityListTOPartyDTOList(list);
					partyOutMessage.setPartyDTOList(resultList);
				}

			
		} else if (FIND_PARTYS.equals(flowId)) {
			PartyDTO partyDto = partyInputMessage.getPartyDTO();
			List<PartyEntity> list = storagePartyDAO.search(partyDto.getPartyName(), null,partyDto.getPartyTypeDTO().getPartyTypeId(),partyDto.getPartyTypeDTO().getPartyTypeFlag());
			partyOutMessage = new PartyOutMessage();
			if (list != null) {
				List<PartyDTO> resultList = convertPartyEntityListTOPartyDTOList(list);
				partyOutMessage.setPartyDTOList(resultList);
			}
		}if(PRE_REMOVE_CHECK.equals(flowId)){
			logger.info(flowId);
			if(storagePartyDAO.isInUsed(partyInputMessage.getPartyDTO().getPartyId())){
				logger.info("TRUE");		
				partyOutMessage=new PartyOutMessage();
				ErrorDTO errorDTO = new ErrorDTO("1", "Party can not be Removed");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				partyOutMessage.setErrorListDTO(errorListDTO);
			}else{
				partyOutMessage=null;
			}
		}
		else if (FIND_DEBTOR_PARTYS.equals(flowId)) {
			List<PartyEntity> list = storagePartyDAO.findDebtorPartyList();

			//	 List<PartyEntity> list = storagePartyDAO.getDebtorPartyShortInfoList();

			partyOutMessage = new PartyOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<PartyDTO> resultList = convertPartyEntityListTOPartyDTOList(list);
				partyOutMessage.setPartyDTOList(resultList);
			}
		}else if (FIND_DEBTOR_SHORT_INFO .equals(flowId)) {
				 List<PartyEntity> list = storagePartyDAO.findDebtorPartyShortInfoList();

			partyOutMessage = new PartyOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<PartyDTO> resultList = convertPartyEntityListTOPartyDTOList(list);
				partyOutMessage.setPartyDTOList(resultList);
			}
		}else if (FIND_CREDITOR_LIST.equals(flowId)) {
			List<PartyEntity> list = storagePartyDAO.findCreditorPartyList();

			//	 List<PartyEntity> list = storagePartyDAO.getDebtorPartyShortInfoList();

			partyOutMessage = new PartyOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<PartyDTO> resultList = convertPartyEntityListTOPartyDTOList(list);
				partyOutMessage.setPartyDTOList(resultList);
			}
		}
	}

	@Override
	public void formatOutput() {

		if (ADD_PARTY.equals(flowId)) {

		} else if (UPDATE_PARTY.equals(flowId)) {

		} else if (DELETE_PARTY.equals(flowId)) {

		} else if (FIND_PARTY_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_PARTYS.equals(flowId)) {

		}
	}

	private List<PartyDTO> convertPartyEntityListTOPartyDTOList(
			List<PartyEntity> list) {
		List<PartyDTO> resultList = new ArrayList<PartyDTO>();
		PartyDTO partyDto;
		for (PartyEntity entity : list) {
			partyDto = new PartyDTO();
			partyDto.setPartyTypeDTO(new PartyTypeDTO());
			try {
				BeanUtils.copyProperties(entity, partyDto);
				if(entity.getPartyTypeEntity()!=null)
				{
				  BeanUtils.copyProperties(entity.getPartyTypeEntity(), partyDto.getPartyTypeDTO());
				}
				if(entity.getOpeningBalance()!=null && entity.getOpeningBalance()<0)
				{
					partyDto.setOpeningBalance(Math.abs(entity.getOpeningBalance()));
					partyDto.setBalanceType("Dr");
				}
				if(entity.getOpeningBalance()!=null && entity.getOpeningBalance()>0)
				{
					partyDto.setBalanceType("Cr");
				}
				
				// Set City
				
				List<CityEntity> cityList = storageCityDAO.findById(partyDto
						.getBillingCityId());
				if (cityList != null && cityList.size() == 1) {
					CityEntity cityEntity = cityList.get(0);
					if (cityEntity != null) {
						partyDto.setCityName(cityEntity.getCityName());
						if (cityEntity.getAreaEntity() != null) {
							if (cityEntity.getAreaEntity().getRegionEntity() != null) {
								if (cityEntity.getAreaEntity().getRegionEntity().getStateEntity() != null) {
									partyDto.setState(cityEntity.getAreaEntity().getRegionEntity().getStateEntity().getStateName());
									if (cityEntity.getAreaEntity()
											.getRegionEntity().getStateEntity()
											.getZoneEntity() != null) {
										if (cityEntity.getAreaEntity()
												.getRegionEntity()
												.getStateEntity()
												.getZoneEntity()
												.getCountryEntity() != null) {
											partyDto.setCountry(cityEntity
													.getAreaEntity()
													.getRegionEntity()
													.getStateEntity()
													.getZoneEntity()
													.getCountryEntity()
													.getCountryName());

										}
									}

								}
							}
						}
					}
				}
				// Set Transporter
				List<PartyTypeEntity> partyTypeList = storagePartyTypeDAO
						.findById(partyDto.getPartyTypeDTO().getPartyTypeId());
				if (partyTypeList != null && partyTypeList.size() == 1) {
					PartyTypeEntity partyTypeEntity = partyTypeList.get(0);
					partyDto.setPartyTypeDesc(partyTypeEntity
							.getPartyTypeDesc());
				}
			} catch (BeansException e) {
				e.printStackTrace();
			}
			resultList.add(partyDto);
			
		}
		return resultList;
	}

	@Override
	public PartyOutMessage findCreditorPartyList() {
		flowId = FIND_CREDITOR_LIST;
		this.partyInputMessage = partyInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return partyOutMessage;
	}

	@Override
	public String getEmailId(String billNo, String flag) {
		
		String emailId=storagePartyDAO.getEmailId(billNo, flag);
		return emailId;
	}

	
	
	

	
	

}
