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
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.RegionEntity;
import com.advanz.erp.masters.entity.jpa.StateEntity;
import com.advanz.erp.masters.entity.jpa.ZoneEntity;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.StateDTO;
import com.advanz.erp.masters.model.ZoneDTO;
import com.advanz.erp.masters.model.msg.CountryOutMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.StateInputMessage;
import com.advanz.erp.masters.model.msg.StateOutputMessage;
import com.advanz.erp.masters.service.business.IStateService;
import com.advanz.erp.masters.storage.IStorageRegionDAO;
import com.advanz.erp.masters.storage.IStorageStateDAO;
import com.advanz.erp.masters.storage.IStorageZoneDAO;
@Service
public class StateServiceImpl implements IStateService {

	public static final String CREATE_STATE = "State";
	public static final String UPDATE_STATE = "UpdateState";
	public static final String ADD_STATE = "AddState";
	public static final String DELETE_STATE = "DeleteState";
	public static final String FIND_STATE_BY_NO = "FindStateByNo";
	public static final String FIND_ALL_STATEES = "FindAllCompnies";
	public static final String FIND_STATE = "FindItemes";
	public static final String FIND_STATE_BY_ZONE_ID = "findStateByZoneId";
	
	
	private static final Logger logger = LoggerFactory
	.getLogger(StateServiceImpl.class);	

	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageStateDAO storageStateDAO;
	
	@Autowired
	public IStorageRegionDAO storageRegionDAO;


	public StateInputMessage stateInputMessage;

	public StateOutputMessage stateOutputMessage;

	@Override
	public StateOutputMessage createState(StateInputMessage stateInputMessage) {

		flowId = ADD_STATE;

		// assign the message to the class level variable.
		this.stateInputMessage=stateInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return stateOutputMessage;
	}

	@Override
	public StateOutputMessage updateState(StateInputMessage stateInputMessage) {

		flowId = UPDATE_STATE;
		// call the template method
		this.stateInputMessage=stateInputMessage;
		advanzErpServiceTemplate.execute(this);

		return stateOutputMessage;
	}

	@Override
	public StateOutputMessage deleteState(StateInputMessage stateInputMessage) {
		flowId = DELETE_STATE;
		// call the template method
		this.stateInputMessage=stateInputMessage;
		advanzErpServiceTemplate.execute(this);

		return stateOutputMessage;

	}

	@Override
	public StateOutputMessage findStateById(StateInputMessage stateInputMessage) {
		flowId = FIND_STATE_BY_NO;
		// call the template method
		this.stateInputMessage=stateInputMessage;
		advanzErpServiceTemplate.execute(this);
		return stateOutputMessage;

	}

	@Override
	public StateOutputMessage findAllStates() {
		flowId = FIND_ALL_STATEES;
		// call the template method
		
		advanzErpServiceTemplate.execute(this);
		return stateOutputMessage;
	}
	
	public StateOutputMessage findState(StateInputMessage stateInputMessage) {
		flowId = FIND_STATE;
		this.stateInputMessage = stateInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return stateOutputMessage;
	}

	@Override
	public StateOutputMessage findStatesByZoneId(StateInputMessage stateInputMessage){
		flowId = FIND_STATE_BY_ZONE_ID;
		this.stateInputMessage = stateInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return stateOutputMessage;
	}
	
	@Override
	public boolean validateInput() {

		if (ADD_STATE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_STATE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_STATE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_STATE_BY_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_STATEES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_STATE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_STATE_BY_ZONE_ID.equals(FIND_STATE_BY_ZONE_ID)) {
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {
		
	StateEntity stateEntity = new StateEntity();
//		BeanUtils.copyProperties(stateInputMessage.getStateDTO(), stateEntity);
	if (stateInputMessage != null) {
		StateDTO stateDTO = stateInputMessage.getStateDTO();
		if (stateDTO != null) {
			BeanUtils.copyProperties(stateDTO, stateEntity);
			ZoneEntity zoneEntity =new ZoneEntity();
			if(stateDTO.getZoneDTO()!=null)
				zoneEntity .setZoneId(stateDTO.getZoneDTO().getZoneId());
			stateEntity.setZoneEntity(zoneEntity);			
		}
	}
		if (ADD_STATE.equals(flowId)) {
			try {
				StateDTO stateDTO = stateInputMessage.getStateDTO();
				List<StateEntity> list=storageStateDAO.search(stateDTO.getStateName(), null, null);
				List<StateEntity> list1=storageStateDAO.search(null, stateDTO.getStateCode(), null);
				logger.info(flowId +": "+list);
				stateOutputMessage = new StateOutputMessage();
				if((list!=null && list.size()>0) || (list1!=null && list1.size()>0)){				
					ErrorDTO errorDTO=new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed.");
					ErrorListDTO errorListDTO=new ErrorListDTO();
					errorListDTO.addError(errorDTO);				
					stateOutputMessage.setErrorListDTO(errorListDTO);
				}else{
					stateOutputMessage.setErrorListDTO(null);
					stateEntity.setDeletedFlag(false);
					storageStateDAO.create(stateEntity);
				}
				
			} catch (BeansException e) {
				e.printStackTrace();
			}
		} else if (UPDATE_STATE.equals(flowId)) {
			try{
			StateDTO stateDTO = stateInputMessage.getStateDTO();
			List<StateEntity> list=storageStateDAO.search(stateDTO.getStateName(), null, null);
			List<StateEntity> list1=storageStateDAO.search(null, stateDTO.getStateCode(), null);
			logger.info(flowId +": "+list);
			stateOutputMessage = new StateOutputMessage();
			if((list!=null && list.size()>0 && !list.get(0).getUid().equals(stateEntity.getStateId())) || (list1!=null && list1.size()>0 && !list1.get(0).getUid().equals(stateEntity.getStateId()))){				
				ErrorDTO errorDTO=new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO=new ErrorListDTO();
				errorListDTO.addError(errorDTO);				
				stateOutputMessage.setErrorListDTO(errorListDTO);
			}else{
				stateOutputMessage.setErrorListDTO(null);
				stateEntity.setDeletedFlag(false);
				storageStateDAO.update(stateEntity);
			}
		} catch (BeansException e) {
			e.printStackTrace();
		}
		} else if (DELETE_STATE.equals(flowId)) {
			stateOutputMessage = new StateOutputMessage();
			try {
				StateDTO stateDTO = stateInputMessage.getStateDTO();
			
					BeanUtils.copyProperties(stateDTO,stateEntity);
					
					List<RegionEntity> list=storageRegionDAO.findReginByStateId(stateEntity.getStateId());
					if((list!=null && list.size()>0 )){				
						ErrorDTO errorDTO=new ErrorDTO("1","State use in Region. You can not delete.");
						ErrorListDTO errorListDTO=new ErrorListDTO();
						errorListDTO.addError(errorDTO);				
						stateOutputMessage.setErrorListDTO(errorListDTO);
					}else{
						stateOutputMessage.setErrorListDTO(null);
						storageStateDAO.delete(stateEntity);
					}
				
				
			} catch (BeansException e) {
				e.printStackTrace();
			}
			
		} else if (FIND_STATE_BY_NO.equals(flowId)) {
			stateOutputMessage = new StateOutputMessage();
			StateDTO stateDTO = stateInputMessage.getStateDTO();
			List<StateEntity> list  =storageStateDAO.findById(stateDTO.getStateId());
			if ( list != null ) {
				List<StateDTO> resultList = convertStateEntityListTOStateDtoList(list);
				stateOutputMessage.setStateDTOList(resultList);
			}
		} else if (FIND_ALL_STATEES.equals(flowId)) {
			
			List<StateEntity> list = storageStateDAO.load();
			stateOutputMessage = new StateOutputMessage();
			// set the data to the output message.
			if ( list != null ) {
				List<StateDTO> resultList = convertStateEntityListTOStateDtoList(list);
				stateOutputMessage.setStateDTOList(resultList);
			}
		}else if(FIND_STATE.equals(flowId)){
			StateDTO stateDTO = stateInputMessage.getStateDTO();
			System.out.println(stateDTO.toString());
			List<StateEntity> list = storageStateDAO.search(
					stateDTO.getStateName(),
					stateDTO.getStateCode(),
					stateDTO.getZoneDTO().getZoneName()
					);
			stateOutputMessage = new StateOutputMessage();
			if ( list != null ) {
				List<StateDTO> resultList = convertStateEntityListTOStateDtoList(list);
				stateOutputMessage.setStateDTOList(resultList);
			}
		} else if (FIND_STATE_BY_ZONE_ID.equals(FIND_STATE_BY_ZONE_ID)) {
				StateDTO stateDTO = stateInputMessage.getStateDTO();
				List<StateEntity> list = storageStateDAO.findStateByZoneId(stateDTO.getZoneDTO().getZoneId());
				stateOutputMessage = new StateOutputMessage();
				if ( list != null ) {
					List<StateDTO> resultList = convertStateEntityListTOStateDtoList(list);
					stateOutputMessage.setStateDTOList(resultList);
			}
		}
	}
	@Override
	public void formatOutput() {

		if (ADD_STATE.equals(flowId)) {

		} else if (UPDATE_STATE.equals(flowId)) {

		} else if (DELETE_STATE.equals(flowId)) {

		} else if (FIND_STATE_BY_NO.equals(flowId)) {

		} else if (FIND_ALL_STATEES.equals(flowId)) {

		}
	}
	
	private List<StateDTO> convertStateEntityListTOStateDtoList(List<StateEntity> list){

		stateOutputMessage = new StateOutputMessage();
		List<StateDTO> resultList = null;
		// set the data to the output message.
		if (list != null) {
			
			StateDTO stateDTO;
			resultList = new ArrayList<StateDTO>();
			for (StateEntity entity : list) {
				stateDTO = new StateDTO();
				//Spring
				stateDTO.setZoneDTO(new ZoneDTO());
				BeanUtils.copyProperties(entity, stateDTO);
				if(entity.getZoneEntity()!=null){
				BeanUtils.copyProperties(entity.getZoneEntity(),stateDTO.getZoneDTO());
				}
				
				resultList.add(stateDTO);

			}
		}

		return resultList;
	}
	
}
