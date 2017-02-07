package com.advanz.erp.masters.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.PartyEntity;
import com.advanz.erp.masters.entity.jpa.ReturnGetPassDetailEntity;
import com.advanz.erp.masters.entity.jpa.ReturnGetPassMasterEntity;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.GetPassDetailDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.ReturnGetPassDetailDTO;
import com.advanz.erp.masters.model.ReturnGetPassMasterDTO;
import com.advanz.erp.masters.model.msg.ReturnGetPassMasterInputMessage;
import com.advanz.erp.masters.model.msg.ReturnGetPassMasterOutputMessage;
import com.advanz.erp.masters.service.business.IGetPassMasterService;
import com.advanz.erp.masters.service.business.IReturnGetPassMasterService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageReturnGetPassMasterDAO;

public class ReturnGetPassMasterServiceImpl implements
		IReturnGetPassMasterService {

	public static final String CREATE_GET_PASS_MASTER = "CreateGetPassMaster";
	public static final String UPDATE_GET_PASS_MASTER = "UpdateGetPassMaster";
	public static final String DELETE_GET_PASS_MASTER = "DeleteGetPassMaster";
	public static final String FIND_GET_PASS_MASTER_BY_ID = "FindGetPassMasterById";
	public static final String FIND_ALL_GET_PASS_MASTER = "FindAllGetPassMasters";
	public static final String SEARCH_GET_PASS_MASTER = "SearchGetPassMasters";
	public static final String NEW_GET_PASS_MASTER_SERIES_NO = "NewGetPassMastersSeriesNo";
	public static final String FIND_RETURN_GET_PASS_PAGINATION = "FindReturnGetPassPagination";
	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do

	@Autowired
	public IStorageReturnGetPassMasterDAO storageReturnGetPassMasterDAO;

	public ReturnGetPassMasterInputMessage returnGetPassInputMessage;

	public ReturnGetPassMasterOutputMessage returnGetPassOutputMessage;

	@Autowired
	public IZoneService zoneService;

	@Autowired
	public IGetPassMasterService getPassMasterService;

	@Override
	public ReturnGetPassMasterOutputMessage createReturnGetPassMaster(
			ReturnGetPassMasterInputMessage returnGetPassInputMessage) {

		flowId = CREATE_GET_PASS_MASTER;
		// assign the message to the class level variable.
		this.returnGetPassInputMessage = returnGetPassInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return returnGetPassOutputMessage;
	}

	@Override
	public ReturnGetPassMasterOutputMessage updateReturnGetPassMaster(
			ReturnGetPassMasterInputMessage returnGetPassInputMessage) {
		flowId = UPDATE_GET_PASS_MASTER;
		// assign the message to the class level variable.
		this.returnGetPassInputMessage = returnGetPassInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return returnGetPassOutputMessage;
	}

	@Override
	public ReturnGetPassMasterOutputMessage deleteReturnGetPassMaster(
			ReturnGetPassMasterInputMessage returnGetPassInputMessage) {
		flowId = DELETE_GET_PASS_MASTER;
		// assign the message to the class level variable.
		this.returnGetPassInputMessage = returnGetPassInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return returnGetPassOutputMessage;

	}

	@Override
	public ReturnGetPassMasterOutputMessage findReturnGetPassMasterById(
			ReturnGetPassMasterInputMessage returnGetPassInputMessage) {
		flowId = FIND_GET_PASS_MASTER_BY_ID;
		// assign the message to the class level variable.
		this.returnGetPassInputMessage = returnGetPassInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return returnGetPassOutputMessage;

	}

	@Override
	public ReturnGetPassMasterOutputMessage findAllReturnGetPassMaster() {
		flowId = FIND_ALL_GET_PASS_MASTER;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return returnGetPassOutputMessage;
	}

	@Override
	public ReturnGetPassMasterOutputMessage search(
			ReturnGetPassMasterInputMessage returnGetPassInputMessage) {
		flowId = SEARCH_GET_PASS_MASTER;
		this.returnGetPassInputMessage = returnGetPassInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return returnGetPassOutputMessage;
	}

	@Override
	public ReturnGetPassMasterOutputMessage getNewReturnGetPassMasterSeriesNo(
			ReturnGetPassMasterInputMessage returnGetPassInputMessage) {
		flowId = NEW_GET_PASS_MASTER_SERIES_NO;
		this.returnGetPassInputMessage = returnGetPassInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return returnGetPassOutputMessage;
	}

	@Override
	public ReturnGetPassMasterOutputMessage findReturnGetPassPagination(
			ReturnGetPassMasterInputMessage returnGetPassInputMessage) {

		flowId = FIND_RETURN_GET_PASS_PAGINATION;
		this.returnGetPassInputMessage = returnGetPassInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return returnGetPassOutputMessage;
	}

	@Override
	public boolean validateInput() {
		if (CREATE_GET_PASS_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_GET_PASS_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_GET_PASS_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_GET_PASS_MASTER_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_GET_PASS_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_GET_PASS_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (NEW_GET_PASS_MASTER_SERIES_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_RETURN_GET_PASS_PAGINATION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {
		ReturnGetPassMasterEntity getPassMasterEntity = new ReturnGetPassMasterEntity();
		returnGetPassOutputMessage = new ReturnGetPassMasterOutputMessage();

		ReturnGetPassMasterDTO getPassMasterDTO = null;
		List<ReturnGetPassDetailDTO> getPassDetailDTO = null;
		if (returnGetPassInputMessage != null) {
			getPassMasterDTO = returnGetPassInputMessage
					.getReturnGetPassMasterDTO();
			if (getPassMasterDTO != null) {
				BeanUtils.copyProperties(getPassMasterDTO, getPassMasterEntity);
				PartyDTO partyDTO = getPassMasterDTO.getPartyDTO();
				if (partyDTO != null) {
					PartyEntity partyEntity = new PartyEntity();
					copyObject(partyDTO, partyEntity);
					getPassMasterEntity.setPartyEntity(partyEntity);
				}

				BranchDTO branchDTO = getPassMasterDTO.getBranchDTO();
				if (branchDTO != null) {
					BranchEntity branchEntity = new BranchEntity();
					copyObject(branchDTO, branchEntity);
					getPassMasterEntity.setBranchEntity(branchEntity);
				}

				getPassDetailDTO = getPassMasterDTO
						.getReturnGetPassDetailDTOList();
				if (getPassDetailDTO != null && getPassDetailDTO.size() > 0) {
					List<ReturnGetPassDetailEntity> getPassDetailEntity = convertGetPassDetailDtoTOEntity(
							getPassDetailDTO,
							getPassMasterDTO.getReturnGatePassNumber(),
							getPassMasterDTO.getTransactionSeries(),
							getPassMasterDTO);
					getPassMasterEntity
							.setReturnGetPassDetailEntitiesList(getPassDetailEntity);
				}

			}
		}

		if (CREATE_GET_PASS_MASTER.equals(flowId)) {
			// if (grnMasterEntity.getGrnDetailEntity() != null &&
			// grnMasterEntity.getGrnDetailEntity().size() > 0) {
			List<ReturnGetPassMasterEntity> list = storageReturnGetPassMasterDAO
					.findByReturnGetPassNumber(getPassMasterEntity
							.getReturnGatePassNumber());
			if (list != null && list.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO(
						"1",
						getPassMasterEntity.getGatePassNumber()
								+ " Gate Pass Number is already exist,it can't be duplicate ");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				returnGetPassOutputMessage.setErrorListDTO(errorListDTO);
			} else {
				returnGetPassOutputMessage.setErrorListDTO(null);

				// To update gate pass detail
				updateGatePassDetail(getPassMasterEntity, null);
				storageReturnGetPassMasterDAO.create(getPassMasterEntity);
			}
		}
		if (UPDATE_GET_PASS_MASTER.equals(flowId)) {
			// To update gate pass detail
			updateGatePassDetail(getPassMasterEntity, null);
			storageReturnGetPassMasterDAO.update(getPassMasterEntity);
		} else if (DELETE_GET_PASS_MASTER.equals(flowId)) {
			List<ReturnGetPassMasterEntity> l = storageReturnGetPassMasterDAO
					.findById(getPassMasterEntity.getReturnGatePassAutoId());
			if (l != null && l.size() > 0) {
				ReturnGetPassMasterEntity remEntity = l.get(0);
				updateGatePassDetail(remEntity, "delete");
			}

			storageReturnGetPassMasterDAO.delete(getPassMasterEntity);
		} else if (FIND_GET_PASS_MASTER_BY_ID.equals(flowId)) {
			List<ReturnGetPassMasterEntity> list = storageReturnGetPassMasterDAO
					.findById(getPassMasterEntity.getReturnGatePassAutoId());
			popUpList(list);
		} else if (FIND_ALL_GET_PASS_MASTER.equals(flowId)) {
			List<ReturnGetPassMasterEntity> list = storageReturnGetPassMasterDAO
					.load();
			popUpList(list);
		} else if (SEARCH_GET_PASS_MASTER.equals(flowId)) {
			String itemName = null;
			String gatePassNumber = null;
			Date gatePassDate = null;
			String partyName = null;
			if (getPassMasterDTO.getReturnGatePassNumber() != null) {
				gatePassNumber = getPassMasterDTO.getReturnGatePassNumber();
			}
			if (getPassMasterDTO.getReturnGatePassDate() != null) {
				gatePassDate = getPassMasterDTO.getReturnGatePassDate();
			}
			if (getPassMasterDTO.getItemName() != null) {
				itemName = getPassMasterDTO.getItemName();
			}
			if (getPassMasterDTO.getPartyDTO().getPartyName() != null) {
				partyName = getPassMasterDTO.getPartyDTO().getPartyName();
			}
			List<ReturnGetPassMasterEntity> list = storageReturnGetPassMasterDAO
					.search(gatePassNumber, gatePassDate, partyName, itemName);
			popUpList(list);
		} else if (NEW_GET_PASS_MASTER_SERIES_NO.equals(flowId)) {

			Integer seriesNo = 0;
			Timestamp date = zoneService.getFirstDayOfFinYear();
			List list = storageReturnGetPassMasterDAO
					.getNewSeriesNo(getPassMasterEntity.getFinyr());
			if (list != null && list.size() > 0) {
				Object[] obj = (Object[]) list.get(0);

				Number n = (Number) obj[0];
				if (n != null)
					seriesNo = n.intValue();
				if (obj[1] != null && obj[1] != "")
					date = (Timestamp) obj[1];
			}
			seriesNo++;

			returnGetPassOutputMessage = new ReturnGetPassMasterOutputMessage();
			returnGetPassOutputMessage.setReturnGetPassSeriesNo(seriesNo);
			returnGetPassOutputMessage.setReturnGetPassSeriesDate(date);
		} else if (FIND_RETURN_GET_PASS_PAGINATION.equals(flowId)) {
			List<ReturnGetPassMasterEntity> list = storageReturnGetPassMasterDAO
					.findReturnGetPassPagination(returnGetPassInputMessage
							.getNext());
			popUpList(list);
		}

	}

	@Override
	public void formatOutput() {
		// TODO Auto-generated method stub

	}

	private List<ReturnGetPassDetailEntity> convertGetPassDetailDtoTOEntity(
			List<ReturnGetPassDetailDTO> dtoList, String getPassNumber,
			String transactionSeries, ReturnGetPassMasterDTO getPassMasterDTO) {
		List<ReturnGetPassDetailEntity> entityList = new ArrayList<ReturnGetPassDetailEntity>();
		for (ReturnGetPassDetailDTO dto : dtoList) {
			ReturnGetPassDetailEntity entity = new ReturnGetPassDetailEntity();

			ItemDTO item = dto.getItemDTO();
			if (item != null) {
				ItemEntity itemEntity = new ItemEntity();
				copyObject(item, itemEntity);
				entity.setItemEntity(itemEntity);
			}
			copyObject(dto, entity);
			entity.setTransactionSeries(transactionSeries);
			entity.setReturnGatePassNumber(getPassNumber);
			if (getPassMasterDTO.getCreatedUserId() != null) {
				entity.setCreatedUserId(getPassMasterDTO.getCreatedUserId());
			}
			if (getPassMasterDTO.getModifiedUserId() != null) {
				entity.setModifiedUserId(getPassMasterDTO.getModifiedUserId());
			}
			entityList.add(entity);
		}
		return entityList;
	}

	private List<ReturnGetPassDetailDTO> convertGetPassDetailEntityToDto(
			List<ReturnGetPassDetailEntity> entityList) {
		List<ReturnGetPassDetailDTO> dtoList = new ArrayList<ReturnGetPassDetailDTO>();
		for (ReturnGetPassDetailEntity entity : entityList) {
			ReturnGetPassDetailDTO dto = new ReturnGetPassDetailDTO();

			ItemEntity itemEntity = entity.getItemEntity();
			if (itemEntity != null) {
				ItemDTO itemDTO = new ItemDTO();
				copyObject(itemEntity, dto);
				copyObject(itemEntity, itemDTO);
				dto.setItemDTO(itemDTO);
				if (itemEntity.getMasterUnitEntity() != null) {
					dto.setMeasurementUnitId(itemEntity.getMasterUnitEntity()
							.getMastersId());
					dto.setMeasurementUnitName(itemEntity.getMasterUnitEntity()
							.getName());
				}
			}

			copyObject(entity, dto);
			dtoList.add(dto);
		}
		return dtoList;

	}

	void popUpList(List<ReturnGetPassMasterEntity> list) {
		returnGetPassOutputMessage = new ReturnGetPassMasterOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<ReturnGetPassMasterDTO> resultList = new ArrayList<ReturnGetPassMasterDTO>();
			ReturnGetPassMasterDTO getPassMasterDTO;
			for (ReturnGetPassMasterEntity getPassMasterEntity : list) {
				getPassMasterDTO = new ReturnGetPassMasterDTO();
				// Spring

				if (getPassMasterEntity != null) {
					BeanUtils.copyProperties(getPassMasterEntity,
							getPassMasterDTO);
					PartyEntity partyEntity = getPassMasterEntity
							.getPartyEntity();
					if (partyEntity != null) {
						PartyDTO partyDTO = new PartyDTO();
						copyObject(partyEntity, partyDTO);
						getPassMasterDTO.setPartyDTO(partyDTO);
					}

					BranchEntity branchEntity = getPassMasterEntity
							.getBranchEntity();
					if (branchEntity != null) {
						BranchDTO branchDTO = new BranchDTO();
						copyObject(branchEntity, branchDTO);
						getPassMasterDTO.setBranchDTO(branchDTO);
					}
					List<ReturnGetPassDetailEntity> getPassDetailEntity = getPassMasterEntity
							.getReturnGetPassDetailEntitiesList();
					if (getPassDetailEntity != null
							&& getPassDetailEntity.size() > 0) {
						List<ReturnGetPassDetailDTO> getPassDetailDTO = convertGetPassDetailEntityToDto(getPassDetailEntity);
						getPassMasterDTO
								.setReturnGetPassDetailDTOList(getPassDetailDTO);
					}
				}
				resultList.add(getPassMasterDTO);
			}
			returnGetPassOutputMessage
					.setReturnGetPassMasterDTOList(resultList);
		}
	}

	private void copyObject(Object source, Object target) {
		try {
			BeanUtils.copyProperties(source, target);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List getPendingQty(String passNumber, Integer itemId) {
		// TODO Auto-generated method stub
		return storageReturnGetPassMasterDAO.getPendingQty(passNumber, itemId);
	}

	public void updateGatePassDetail(
			ReturnGetPassMasterEntity getPassMasterEntity, String operation) {
		List retunList = getPassMasterEntity
				.getReturnGetPassDetailEntitiesList();
		for (int i = 0; i < retunList.size(); i++) {
			ReturnGetPassDetailEntity rgdEntity = (ReturnGetPassDetailEntity) retunList
					.get(i);
			GetPassDetailDTO detailDTO = new GetPassDetailDTO();
			List l = storageReturnGetPassMasterDAO.sumPendingQty(
					getPassMasterEntity.getGatePassNumber(), rgdEntity
							.getItemEntity().getItemId());
			Double totalQty = 0.0;
			Double qty = rgdEntity.getReturnGatePassQuantity();
			if (operation != null && "delete".equalsIgnoreCase(operation)) {
				qty = 0 - qty;
			} else {
				if (l != null && l.size() > 0) {
					totalQty = (Double) l.get(0);
					if (totalQty != null) {
						qty = qty + totalQty;
					}
				}
			}
			detailDTO.setGatePassQuantity(qty);
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setItemId(rgdEntity.getItemEntity().getItemId());
			detailDTO.setItemDTO(itemDTO);
			detailDTO
					.setGatePassNumber(getPassMasterEntity.getGatePassNumber());
			getPassMasterService.updateGatePassDetail(detailDTO, operation);
		}

	}

}