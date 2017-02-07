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
import com.advanz.erp.masters.entity.jpa.CityEntity;
import com.advanz.erp.masters.entity.jpa.TransporterEntity;
import com.advanz.erp.masters.model.CityDTO;
import com.advanz.erp.masters.model.TransporterDTO;
import com.advanz.erp.masters.model.msg.TransporterInputMessage;
import com.advanz.erp.masters.model.msg.TransporterOutMessage;
import com.advanz.erp.masters.service.business.ITransporterService;
import com.advanz.erp.masters.storage.IStorageCityDAO;
import com.advanz.erp.masters.storage.IStorageTransporterDAO;

@Service
public class TransporterServiceImpl implements ITransporterService {

	public static final String CREATE_TRANSPORTER = "Transporter";
	public static final String UPDATE_TRANSPORTER = "UpdateTransporter";
	public static final String ADD_TRANSPORTER = "AddTransporter";
	public static final String DELETE_TRANSPORTER = "DeleteTransporter";
	public static final String FIND_TRANSPORTER_BY_ID = "FindTransporterById";
	public static final String FIND_ALL_TRANSPORTERS = "FindAllCompnies";
	public static final String FIND_TRANSPORTERS = "FindTransporters";
    public static final String PRE_REMOVE_CHECK="PreRemoveCheck";
    public static final String PRE_LOADED="Preloaded";
	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageTransporterDAO storageTransporterDAO;
	@Autowired
	public IStorageCityDAO storageCityDAO;

	public TransporterInputMessage transporterInputMessage;

	private static final Logger logger = LoggerFactory.getLogger(TransporterServiceImpl.class);

	public TransporterOutMessage transporterOutMessage;

	@Override
	public TransporterOutMessage createTransporter(
			TransporterInputMessage transporterInputMessage) {

		flowId = ADD_TRANSPORTER;
		// assign the message to the class level variable.
		this.transporterInputMessage = transporterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return transporterOutMessage;
	}

	@Override
	public TransporterOutMessage updateTransporter(
			TransporterInputMessage transporterInputMessage) {

		flowId = UPDATE_TRANSPORTER;
		// assign the message to the class level variable.
		this.transporterInputMessage = transporterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return transporterOutMessage;
	}

	@Override
	public TransporterOutMessage deleteTransporter(
			TransporterInputMessage transporterInputMessage) {
		flowId = DELETE_TRANSPORTER;
		// assign the message to the class level variable.
		this.transporterInputMessage = transporterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return transporterOutMessage;

	}

	@Override
	public TransporterOutMessage findTransporterById(
			TransporterInputMessage transporterInputMessage) {
		flowId = FIND_TRANSPORTER_BY_ID;
		this.transporterInputMessage = transporterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return transporterOutMessage;

	}

	@Override
	public TransporterOutMessage findAllTransporters() {
		flowId = FIND_ALL_TRANSPORTERS;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return transporterOutMessage;
	}
	public TransporterOutMessage preload(){
		flowId = PRE_LOADED;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return transporterOutMessage;
		
	}
	@Override
	public TransporterOutMessage findTransporter(TransporterInputMessage transporterInputMessage) {
		flowId = FIND_TRANSPORTERS;
		this.transporterInputMessage = transporterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return transporterOutMessage;
	}
	@Override
	public TransporterOutMessage checkBeforeRemove(TransporterInputMessage transporterInputMessage) {
		flowId = PRE_REMOVE_CHECK;
		this.transporterInputMessage = transporterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return transporterOutMessage;
	}
	@Override
	public boolean validateInput() {

		if (ADD_TRANSPORTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_TRANSPORTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_TRANSPORTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_TRANSPORTER_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_TRANSPORTERS.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_TRANSPORTERS.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (PRE_REMOVE_CHECK.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (PRE_LOADED.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		
		return false;
	}

	@Override
	public void performBusinessLogic() {
		TransporterEntity transporterEntity = new TransporterEntity();
		

		if (ADD_TRANSPORTER.equals(flowId)) {
				try {
				BeanUtils.copyProperties(transporterInputMessage.getTransporterDTO(),
						transporterEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			

			List<TransporterEntity> nameList = storageTransporterDAO.findByName(transporterEntity.getTransName());
			List<TransporterEntity> codeList = storageTransporterDAO.findByCode(transporterEntity.getTransCode());
			transporterOutMessage = new TransporterOutMessage();
			if ((nameList != null && nameList.size() > 0) || (codeList != null && codeList.size() > 0)) {
				ErrorListDTO errorListDTO = new ErrorListDTO();
				if(nameList.size()>0){
				ErrorDTO errorDTO = new ErrorDTO("1",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				errorListDTO.addError(errorDTO);
				}
				if(codeList.size()>0){
					ErrorDTO errorDTO = new ErrorDTO("2",
							"Sorry, Record already exist, Duplicate entries are not allowed.");
					errorListDTO.addError(errorDTO);
					}
				transporterOutMessage.setErrorListDTO(errorListDTO);
			} else {
				transporterOutMessage.setErrorListDTO(null);
				storageTransporterDAO.create(transporterEntity);
			}
		} else if (UPDATE_TRANSPORTER.equals(flowId)) {
			try {
				BeanUtils.copyProperties(transporterInputMessage.getTransporterDTO(),
						transporterEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<TransporterEntity> nameList = storageTransporterDAO.findByName(transporterEntity.getTransName());
		//	List<TransporterEntity> codeList = storageTransporterDAO.findByCode(transporterEntity.getTransCode());
			
			transporterOutMessage = new TransporterOutMessage();
			if(nameList!=null && nameList.size()>0 && !nameList.get(0).getTransporterId().equals(transporterEntity.getTransporterId())){				
				ErrorDTO errorDTO = new ErrorDTO("1",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				transporterOutMessage.setErrorListDTO(errorListDTO);
			} else {
				transporterOutMessage.setErrorListDTO(null);
				storageTransporterDAO.update(transporterEntity);
			}
			
		} else if (DELETE_TRANSPORTER.equals(flowId)) {
			try {
				BeanUtils.copyProperties(transporterInputMessage.getTransporterDTO(),
						transporterEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			storageTransporterDAO.delete(transporterEntity);
		} else if (FIND_TRANSPORTER_BY_ID.equals(flowId)) {
			TransporterDTO transporterDto = transporterInputMessage.getTransporterDTO();
			List<TransporterEntity> list = storageTransporterDAO.findById(transporterDto
					.getTransporterId());
			transporterOutMessage = new TransporterOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<TransporterDTO> resultList = convertTransporterEntityListTOTransporterDtoList(list);
				transporterOutMessage.setTransporterDTOList(resultList);
			}
		} else if (FIND_ALL_TRANSPORTERS.equals(flowId)) {
			List<TransporterEntity> list = storageTransporterDAO.load();
			transporterOutMessage = new TransporterOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<TransporterDTO> resultList = convertTransporterEntityListTOTransporterDtoList(list);
				transporterOutMessage.setTransporterDTOList(resultList);
			}
		} 
		else if (PRE_LOADED.equals(flowId)) {
			List list = storageTransporterDAO.preload();
			transporterOutMessage = new TransporterOutMessage();
			// set the data to the output message.
			if (list != null && list.size()>0) {
				ArrayList<TransporterDTO> resultList = new ArrayList<TransporterDTO>();
				for(int i=0;i<list.size();i++){
					TransporterDTO transporterDTO = new TransporterDTO();
					Object[] objects =(Object[])list.get(i);
					int tronspoeterId= (Integer)objects[0];
					String tronspoeterName= (String)objects[1];
					transporterDTO.setTransporterId(tronspoeterId);
					transporterDTO.setTransName(tronspoeterName);
					resultList.add(transporterDTO);
				}
				transporterOutMessage.setTransporterDTOList(resultList);
			}
		    } 
		
		
		else if (FIND_TRANSPORTERS.equals(flowId)) {
			TransporterDTO transporterDto = transporterInputMessage.getTransporterDTO();
			//System.out.println("################## Transporter Service "	+ transporterDto.toString());
			List<TransporterEntity> list = storageTransporterDAO.search(
					transporterDto.getTransName(), transporterDto.getTransAddress(),
					transporterDto.getTransCode());
			transporterOutMessage = new TransporterOutMessage();
			if (list != null) {
				List<TransporterDTO> resultList = convertTransporterEntityListTOTransporterDtoList(list);
				transporterOutMessage.setTransporterDTOList(resultList);
			}
		}else if(PRE_REMOVE_CHECK.equals(flowId)){
			if(storageTransporterDAO.isInUsed(transporterInputMessage.getTransporterDTO().getTransporterId())){
				logger.info("TRUE");
				transporterOutMessage=new TransporterOutMessage();
				ErrorDTO errorDTO=new ErrorDTO("1","Transporter Can Not Be Removed");
				ErrorListDTO errorListDto=new ErrorListDTO();
				 errorListDto.addError(errorDTO);
				 transporterOutMessage.setErrorListDTO(errorListDto);
			}else{
				transporterOutMessage=null;
			}
		}
		
	}

	@Override
	public void formatOutput() {

		if (ADD_TRANSPORTER.equals(flowId)) {

		} else if (UPDATE_TRANSPORTER.equals(flowId)) {

		} else if (DELETE_TRANSPORTER.equals(flowId)) {

		} else if (FIND_TRANSPORTER_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_TRANSPORTERS.equals(flowId)) {

		}
	}

	private List<TransporterDTO> convertTransporterEntityListTOTransporterDtoList(
			List<TransporterEntity> list) {
		List<TransporterDTO> resultList = new ArrayList<TransporterDTO>();
		TransporterDTO transporterDto;
		for (TransporterEntity entity : list) {
			transporterDto = new TransporterDTO();
			try {
				BeanUtils.copyProperties(entity, transporterDto);
				List<CityEntity> cityList = storageCityDAO.findById(transporterDto.getCityId());
				if(cityList!=null && cityList.size()==1){
					CityEntity cityEntity  = cityList.get(0);
					transporterDto.setCityName(cityEntity.getCityName());
				}
			} catch (BeansException e) {
				e.printStackTrace();
			}
			resultList.add(transporterDto);
			/*
			 * try { BeanUtils.copyProperties(transporterDto, entity);
			 * 
			 * 
			 * resultList.add(transporterDto); } catch (IllegalAccessException e) {
			 * // TODO Auto-generated catch block e.printStackTrace(); } catch
			 * (InvocationTargetException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }
			 */
		}
		return resultList;
	}

}
