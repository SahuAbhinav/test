package com.advanz.erp.masters.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
import com.advanz.erp.masters.entity.jpa.DispatchMasterEntity;
import com.advanz.erp.masters.entity.jpa.PartyEntity;
import com.advanz.erp.masters.model.DispatchMasterDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.msg.DispatchMasterInputMessage;
import com.advanz.erp.masters.model.msg.DispatchMasterOutMessage;
import com.advanz.erp.masters.service.business.IDispatchMasterService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageDispatchMasterDAO;

public class DispatchMasterServiceImpl implements IDispatchMasterService {

	public static final String UPDATE_DISPATCH_MASTER = "UpdateDispatchMaster";
	public static final String ADD_DISPATCH_MASTER = "AddDispatchMaster";
	public static final String DELETE_DISPATCH_MASTER = "DeleteDispatchMaster";
	public static final String FIND_BY_ID = "FindById";
	public static final String FIND_ALL_DISPATCH_MASTER = "FindAllDispatchMaster";
	public static final String FIND_CATEGORIES = "FindCategories";
	public static final String FIND_DISPATCH_MASTER_BY_NAME = "FindDispatchMasterByName";
	public static final String FIND_DISPATCH_MASTER = "FindDispatchMaster";
	public static final String FIND_MAX_ID = "FindMaxId";
	public static final String SEARCH_DISPATCH = "SearchDispatch";
	public static final String NEW_DISPATCH_MASTER_SERIES_NO = "NewDispatchMastersSeriesNo";
	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
	private static final Logger logger = LoggerFactory
			.getLogger(DispatchMasterServiceImpl.class);

	@Autowired
	public IStorageDispatchMasterDAO storageDispatchMasterDAO;
	public DispatchMasterInputMessage dispatchMasterInputMessage;
	public DispatchMasterOutMessage dispatchMasterOutMessage;
	@Autowired
	public IZoneService zoneService;

	@Override
	public boolean validateInput() {
		if (UPDATE_DISPATCH_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (ADD_DISPATCH_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_DISPATCH_MASTER.equals(flowId)) {

			return true;
		} else if (FIND_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_DISPATCH_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_CATEGORIES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_DISPATCH_MASTER_BY_NAME.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_DISPATCH_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_MAX_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_DISPATCH.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (NEW_DISPATCH_MASTER_SERIES_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {

		DispatchMasterEntity masterEntity = new DispatchMasterEntity();
		if (dispatchMasterInputMessage != null) {
			DispatchMasterDTO dispatchMasterDTO = dispatchMasterInputMessage
					.getDispatchMasterDTO();
			if (dispatchMasterDTO != null) {
				BeanUtils.copyProperties(
						dispatchMasterInputMessage.getDispatchMasterDTO(),
						masterEntity);

				PartyDTO partyDTO = dispatchMasterDTO.getPartyDTO();
				logger.info("PArty = " + partyDTO);
				if (partyDTO != null) {
					PartyEntity partyEntity = new PartyEntity();
					copyObject(partyDTO, partyEntity);
					masterEntity.setPartyEntity(partyEntity);
				}
			}
		}

		if (ADD_DISPATCH_MASTER.equals(flowId)) {

			List<DispatchMasterEntity> list = storageDispatchMasterDAO
					.findByDispatchNumber(masterEntity.getDispatchNumber());
			dispatchMasterOutMessage = new DispatchMasterOutMessage();

			if (list != null && list.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO(
						"1",
						masterEntity.getDispatchNumber()
								+ " Sorry, Record already exist, Duplicate entries are not allowed.");
				errorDTO.setErrorCode(masterEntity.getDispatchNumber());
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				dispatchMasterOutMessage.setErrorListDTO(errorListDTO);
			} else {
				dispatchMasterOutMessage.setErrorListDTO(null);
				storageDispatchMasterDAO.create(masterEntity);
			}
		} else if (UPDATE_DISPATCH_MASTER.equals(flowId)) {
			try {
				BeanUtils.copyProperties(
						dispatchMasterInputMessage.getDispatchMasterDTO(),
						masterEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			storageDispatchMasterDAO.update(masterEntity);

		} else if (DELETE_DISPATCH_MASTER.equals(flowId)) {
			try {
				BeanUtils.copyProperties(
						dispatchMasterInputMessage.getDispatchMasterDTO(),
						masterEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			storageDispatchMasterDAO.delete(masterEntity);
		} else if (FIND_BY_ID.equals(flowId)) {
			DispatchMasterDTO dispatchMasterDTO = dispatchMasterInputMessage
					.getDispatchMasterDTO();
			List<DispatchMasterEntity> list = storageDispatchMasterDAO
					.findById(dispatchMasterDTO.getDispatchAutoId());
			popUpList(list);

		} else if (FIND_ALL_DISPATCH_MASTER.equals(flowId)) {
			List<DispatchMasterEntity> list = storageDispatchMasterDAO.load();
			popUpList(list);

		} else if (FIND_MAX_ID.equals(flowId)) {
			List<DispatchMasterEntity> list = storageDispatchMasterDAO
					.getMaxId();
			dispatchMasterOutMessage = new DispatchMasterOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<DispatchMasterDTO> resultList = convertDispatchMasterEntityListTODispatchMasterDtoList(list);
				dispatchMasterOutMessage.setDispatchMasterDTOList(resultList);
			}

		}

		else if (FIND_DISPATCH_MASTER.equals(flowId)) {
			DispatchMasterDTO dispatchMasterDTO = dispatchMasterInputMessage
					.getDispatchMasterDTO();

			List<DispatchMasterEntity> list = storageDispatchMasterDAO.load();
			popUpList(list);

		} else if (NEW_DISPATCH_MASTER_SERIES_NO.equals(flowId)) {

			Integer seriesNo = 0;
			Timestamp date = zoneService.getFirstDayOfFinYear();
			List list = storageDispatchMasterDAO.getNewSeriesNo(masterEntity
					.getFinyr());
			if (list != null && list.size() > 0) {
				Object[] obj = (Object[]) list.get(0);

				Number n = (Number) obj[0];
				if (n != null)
					seriesNo = n.intValue();
				if (obj[1] != null && obj[1] != "")
					date = (Timestamp) obj[1];
			}
			seriesNo++;

			dispatchMasterOutMessage = new DispatchMasterOutMessage();
			dispatchMasterOutMessage.setDispatchSeries(seriesNo);
			dispatchMasterOutMessage.setDispatchSeriesDate(date);
		} else if (SEARCH_DISPATCH.equals(flowId)) {

			// billOutMessage = new BillOutMessage();
			DispatchMasterDTO masterDTO = dispatchMasterInputMessage
					.getDispatchMasterDTO();

			String dispatchNumber = null;
			Date dispatchDate = null;
			Date fromDate = null;
			Date toDate = null;
			String partyName = null;
			String itemName = null;
			try {
				if (masterDTO.getDispatchNumber() != null) {
					dispatchNumber = masterDTO.getDispatchNumber();
				}
				if (masterDTO.getDispatchDate() != null) {
					dispatchDate = masterDTO.getDispatchDate();

				}
				if (masterDTO.getItemName() != null) {
					itemName = masterDTO.getItemName();
				}
				if (masterDTO.getFromDate() != null) {
					fromDate = masterDTO.getFromDate();
				}
				if (masterDTO.getToDate() != null) {
					toDate = masterDTO.getToDate();
				}

			} catch (Exception e) {

			}
			// System.out.println("------------------------------------Search :invoice no:"+
			// dispatchNumber);
			List<DispatchMasterEntity> list = storageDispatchMasterDAO.search(
					dispatchNumber, partyName, fromDate, toDate, itemName);

			popUpList(list);

		}
	}

	@Override
	public void formatOutput() {
		// TODO Auto-generated method stub

	}

	@Override
	public DispatchMasterOutMessage createDispatchMaster(
			DispatchMasterInputMessage dispatchMasterInputMessage) {
		flowId = ADD_DISPATCH_MASTER;

		// assign the message to the class level variable.
		this.dispatchMasterInputMessage = dispatchMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return dispatchMasterOutMessage;
	}

	@Override
	public DispatchMasterOutMessage updateDispatchMaster(
			DispatchMasterInputMessage dispatchMasterInputMessage) {
		flowId = UPDATE_DISPATCH_MASTER;
		this.dispatchMasterInputMessage = dispatchMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return dispatchMasterOutMessage;
	}

	@Override
	public DispatchMasterOutMessage deleteDispatchMaster(
			DispatchMasterInputMessage dispatchMasterInputMessage) {
		flowId = DELETE_DISPATCH_MASTER;
		this.dispatchMasterInputMessage = dispatchMasterInputMessage;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return dispatchMasterOutMessage;
	}

	@Override
	public DispatchMasterOutMessage findById(
			DispatchMasterInputMessage dispatchMasterInputMessage) {
		flowId = FIND_BY_ID;
		this.dispatchMasterInputMessage = dispatchMasterInputMessage;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return dispatchMasterOutMessage;
	}

	@Override
	public DispatchMasterOutMessage findAllDispatchMaster() {
		flowId = FIND_ALL_DISPATCH_MASTER;
		this.dispatchMasterInputMessage = dispatchMasterInputMessage;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return dispatchMasterOutMessage;
	}

	@Override
	public DispatchMasterOutMessage findDispatchMaster(
			DispatchMasterInputMessage dispatchMasterInputMessage) {
		flowId = FIND_DISPATCH_MASTER;
		this.dispatchMasterInputMessage = dispatchMasterInputMessage;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return dispatchMasterOutMessage;
	}

	@Override
	public DispatchMasterOutMessage getMaxId() {
		flowId = FIND_MAX_ID;

		this.dispatchMasterInputMessage = dispatchMasterInputMessage;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return dispatchMasterOutMessage;
	}

	@Override
	public DispatchMasterOutMessage searchDispatch(
			DispatchMasterInputMessage dispatchMasterInputMessage) {

		flowId = SEARCH_DISPATCH;

		this.dispatchMasterInputMessage = dispatchMasterInputMessage;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return dispatchMasterOutMessage;
	}

	@Override
	public DispatchMasterOutMessage getNewDispatchMasterSeriesNo(
			DispatchMasterInputMessage dispatchMasterInputMessage) {
		flowId = NEW_DISPATCH_MASTER_SERIES_NO;

		this.dispatchMasterInputMessage = dispatchMasterInputMessage;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return dispatchMasterOutMessage;
	}

	@Override
	public DispatchMasterOutMessage findBillByBillName(
			DispatchMasterInputMessage dispatchMasterInputMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	void popUpList(List<DispatchMasterEntity> list) {
		logger.info("SOM Entity List  :" + list);
		dispatchMasterOutMessage = new DispatchMasterOutMessage();
		// set the data to the output message.
		if (list != null) {
			List<DispatchMasterDTO> resultList = new ArrayList<DispatchMasterDTO>();
			DispatchMasterDTO dispatchMasterDTO;
			for (DispatchMasterEntity entity : list) {
				dispatchMasterDTO = new DispatchMasterDTO();
				// Spring
				BeanUtils.copyProperties(entity, dispatchMasterDTO);

				PartyEntity partyEntity = entity.getPartyEntity();
				if (partyEntity != null) {
					PartyDTO partyDTO = new PartyDTO();
					copyObject(partyEntity, partyDTO);
					dispatchMasterDTO.setPartyDTO(partyDTO);
				}
				resultList.add(dispatchMasterDTO);
			}
			dispatchMasterOutMessage.setDispatchMasterDTOList(resultList);
		}
	}

	private List<DispatchMasterDTO> convertDispatchMasterEntityListTODispatchMasterDtoList(
			List<DispatchMasterEntity> list) {

		dispatchMasterOutMessage = new DispatchMasterOutMessage();
		List<DispatchMasterDTO> resultList = null;
		// set the data to the outputput message.
		if (list != null) {
			DispatchMasterDTO dispatchMasterDetailDTO;
			resultList = new ArrayList<DispatchMasterDTO>();
			for (DispatchMasterEntity dispatchMasterEntity : list) {
				dispatchMasterDetailDTO = new DispatchMasterDTO();

				if (dispatchMasterEntity != null) {
					BeanUtils.copyProperties(dispatchMasterEntity,
							dispatchMasterDetailDTO);
					resultList.add(dispatchMasterDetailDTO);
				}
			}
		}
		return resultList;
	}

	private void copyObject(Object source, Object target) {
		try {
			BeanUtils.copyProperties(source, target);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}