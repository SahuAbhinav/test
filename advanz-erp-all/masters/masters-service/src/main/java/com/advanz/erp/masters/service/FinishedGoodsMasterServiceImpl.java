package com.advanz.erp.masters.service;

import java.sql.Timestamp;
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
import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.entity.jpa.FinishedGoodsDetailEntity;
import com.advanz.erp.masters.entity.jpa.FinishedGoodsMasterEntity;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.CompanyDTO;
import com.advanz.erp.masters.model.FinishedGoodsDetailDTO;
import com.advanz.erp.masters.model.FinishedGoodsMasterDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.criteria.FinishedGoodsMasterSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.CompanyOutMessage;
import com.advanz.erp.masters.model.msg.FinishedGoodsMasterInputMessage;
import com.advanz.erp.masters.model.msg.FinishedGoodsMasterOutputMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.service.business.ICompanyService;
import com.advanz.erp.masters.service.business.IFinishedGoodsMasterService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageCompanyDAO;
import com.advanz.erp.masters.storage.IStorageFinishedGoodsMasterDAO;

public class FinishedGoodsMasterServiceImpl implements
		IFinishedGoodsMasterService {

	public static final String CREATE_FINISHED_GOODS_MASTER = "CreateFinishedGoodsMaster";
	public static final String UPDATE_FINISHED_GOODS_MASTER = "UpdateFinishedGoodsMaster";
	public static final String DELETE_FINISHED_GOODS_MASTER = "DeleteFinishedGoodsMaster";
	public static final String FIND_FINISHED_GOODS_MASTER_BY_ID = "FindFinishedGoodsMasterById";
	public static final String FIND_ALL_FINISHED_GOODS_MASTER = "FindAllFinishedGoodsMasters";
	public static final String SEARCH_FINISHED_GOODS_MASTER = "SearchFinishedGoodsMasters";
	public static final String NEW_FINISHED_GOODS_MASTER_SERIES_NO = "NewFinishedGoodsMastersSeriesNo";
	public static final String FINISHED_GOODS_WITH_PAGINATION = "FinishedGoodsWithPagination";
	public static final String FINISHED_GOODS_BY_NUMBER_AND_DATE = "FinishedGoodsByNumberAndDate";
	public String flowId = "";
	private static final Logger logger = LoggerFactory
			.getLogger(FinishedGoodsMasterServiceImpl.class);
	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring
	@Autowired
	public IStorageFinishedGoodsMasterDAO storageFinishedGoodsMasterDAO;

	@Autowired
	public IStorageCompanyDAO storageCompanyDAO;

	@Autowired
	public IZoneService zoneService;

	public FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage;

	public FinishedGoodsMasterOutputMessage finishedGoodsMasterOutputMessage;

	@Override
	public FinishedGoodsMasterOutputMessage createFinishedGoodsMaster(
			FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage) {

		flowId = CREATE_FINISHED_GOODS_MASTER;
		// assign the message to the class level variable.
		this.finishedGoodsMasterInputMessage = finishedGoodsMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return finishedGoodsMasterOutputMessage;
	}

	@Override
	public FinishedGoodsMasterOutputMessage updateFinishedGoodsMaster(
			FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage) {

		flowId = UPDATE_FINISHED_GOODS_MASTER;
		// assign the message to the class level variable.
		this.finishedGoodsMasterInputMessage = finishedGoodsMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return finishedGoodsMasterOutputMessage;
	}

	@Override
	public FinishedGoodsMasterOutputMessage deleteFinishedGoodsMaster(
			FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage) {
		flowId = DELETE_FINISHED_GOODS_MASTER;
		// assign the message to the class level variable.
		this.finishedGoodsMasterInputMessage = finishedGoodsMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return finishedGoodsMasterOutputMessage;

	}

	@Override
	public FinishedGoodsMasterOutputMessage findFinishedGoodsMasterById(
			FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage) {
		flowId = FIND_FINISHED_GOODS_MASTER_BY_ID;
		// assign the message to the class level variable.
		this.finishedGoodsMasterInputMessage = finishedGoodsMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return finishedGoodsMasterOutputMessage;

	}

	@Override
	public FinishedGoodsMasterOutputMessage findAllFinishedGoodsMasters() {
		flowId = FIND_ALL_FINISHED_GOODS_MASTER;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return finishedGoodsMasterOutputMessage;
	}

	@Override
	public FinishedGoodsMasterOutputMessage search(
			FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage) {
		flowId = SEARCH_FINISHED_GOODS_MASTER;
		this.finishedGoodsMasterInputMessage = finishedGoodsMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return finishedGoodsMasterOutputMessage;

	}

	@Override
	public FinishedGoodsMasterOutputMessage getNewFinishedGoodsSeriesNo(
			FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage) {
		flowId = NEW_FINISHED_GOODS_MASTER_SERIES_NO;
		this.finishedGoodsMasterInputMessage = finishedGoodsMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return finishedGoodsMasterOutputMessage;
	}

	@Override
	public FinishedGoodsMasterOutputMessage findFinishedGoodsForPagination(
			FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage) {
		flowId = FINISHED_GOODS_WITH_PAGINATION;
		this.finishedGoodsMasterInputMessage = finishedGoodsMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return finishedGoodsMasterOutputMessage;
	}

	@Override
	public FinishedGoodsMasterOutputMessage findByFinishGoodByNumberAndDate(
			FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage) {

		flowId = FINISHED_GOODS_BY_NUMBER_AND_DATE;
		this.finishedGoodsMasterInputMessage = finishedGoodsMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return finishedGoodsMasterOutputMessage;
	}

	@Override
	public boolean validateInput() {

		if (CREATE_FINISHED_GOODS_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_FINISHED_GOODS_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_FINISHED_GOODS_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_FINISHED_GOODS_MASTER_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_FINISHED_GOODS_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_FINISHED_GOODS_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (NEW_FINISHED_GOODS_MASTER_SERIES_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FINISHED_GOODS_WITH_PAGINATION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FINISHED_GOODS_BY_NUMBER_AND_DATE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {

		FinishedGoodsMasterEntity finishedGoodsMasterEntity = new FinishedGoodsMasterEntity();
		if (finishedGoodsMasterInputMessage != null) {
			FinishedGoodsMasterDTO finishedGoodsMasterDTO = finishedGoodsMasterInputMessage
					.getFinishedGoodsMasterDTO();
			if (finishedGoodsMasterDTO != null) {
				BeanUtils.copyProperties(finishedGoodsMasterDTO,
						finishedGoodsMasterEntity);
				// PartyDTO partyDTO=finishedGoodsMasterDTO.getPartyDTO();

				MastersEntity mastersEntity = new MastersEntity();
				MastersDTO mastersDTO = finishedGoodsMasterDTO.getMastersDTO();
				if (finishedGoodsMasterDTO.getMastersDTO() != null) {
					// mastersEntity.setMastersId(finishedGoodsMasterDTO.getMastersDTO().getMastersId());
					copyObject(mastersDTO, mastersEntity);
					finishedGoodsMasterEntity.setMastersEntity(mastersEntity);
				}

				BranchDTO branchDTO = finishedGoodsMasterDTO.getBranchDTO();
				if (branchDTO != null) {
					BranchEntity branchEntity = new BranchEntity();
					copyObject(branchDTO, branchEntity);
					finishedGoodsMasterEntity.setBranchEntity(branchEntity);
				}

				List<FinishedGoodsDetailDTO> finishedGoodsDetailDTOList = finishedGoodsMasterDTO
						.getFinishedGoodsDetailDTOList();

				if (finishedGoodsDetailDTOList != null
						&& finishedGoodsDetailDTOList.size() > 0) {
					List<FinishedGoodsDetailEntity> finishedGoodsDetailEntities = convertFinishedGoodsDetailDtoTOFinishedGoodsDetailEntity(finishedGoodsDetailDTOList);
					finishedGoodsMasterEntity
							.setFinishedGoodsDetailEntities(finishedGoodsDetailEntities);
				}
			}
		}

		if (CREATE_FINISHED_GOODS_MASTER.equals(flowId)) {
			// check duplicate finishedGoodsMaster name
			List<FinishedGoodsMasterEntity> list = storageFinishedGoodsMasterDAO
					.findByFinishGoodNumber(
							finishedGoodsMasterEntity.getFinishedGoodsNumber(),
							finishedGoodsMasterEntity.getFinishGoodDate());
			if (list != null && list.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO(
						"1",
						" Finish Good Number OR Finish Good Date is already exist,it can't be duplicate ");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				finishedGoodsMasterOutputMessage = new FinishedGoodsMasterOutputMessage();
				finishedGoodsMasterOutputMessage.setErrorListDTO(errorListDTO);

			} else {
				storageFinishedGoodsMasterDAO.create(finishedGoodsMasterEntity);
				finishedGoodsMasterOutputMessage = new FinishedGoodsMasterOutputMessage();

				/* storageCompanyDAO.callWeightedSP(); */
			}
		} else if (UPDATE_FINISHED_GOODS_MASTER.equals(flowId)) {

			storageFinishedGoodsMasterDAO.update(finishedGoodsMasterEntity);

		} else if (DELETE_FINISHED_GOODS_MASTER.equals(flowId)) {
			storageFinishedGoodsMasterDAO.delete(finishedGoodsMasterEntity);
		} else if (FIND_FINISHED_GOODS_MASTER_BY_ID.equals(flowId)) {
			logger.info("finishedGoodsMasterEntity.getFinishedGoodsAutoId() >>>>>>>>>>>> : "
					+ finishedGoodsMasterEntity.getFinishedGoodsAutoId());
			List<FinishedGoodsMasterEntity> list = storageFinishedGoodsMasterDAO
					.findById(finishedGoodsMasterEntity
							.getFinishedGoodsAutoId());
			logger.info("FinishedGoodsMasterEntity for id("
					+ finishedGoodsMasterEntity.getFinishedGoodsAutoId()
					+ ") :" + list);
			if (list != null && list.size() > 0) {
				// logger.info("party Id"
				// +list.get(0).getPartyEntity().getPartyId());
				logger.info("Detail List"
						+ list.get(0).getFinishedGoodsDetailEntities());
			}
			popUpList(list);

		} else if (FIND_ALL_FINISHED_GOODS_MASTER.equals(flowId)) {
			List<FinishedGoodsMasterEntity> list = storageFinishedGoodsMasterDAO
					.load();
			popUpList(list);
		} else if (FINISHED_GOODS_WITH_PAGINATION.equals(flowId)) {
			List<FinishedGoodsMasterEntity> list = storageFinishedGoodsMasterDAO
					.FindFinishedGoodPagination(finishedGoodsMasterInputMessage
							.getNext());
			popUpList(list);
		} else if (SEARCH_FINISHED_GOODS_MASTER.equals(flowId)) {
			FinishedGoodsMasterSearchCriteriaDTO searchCriteria = finishedGoodsMasterInputMessage
					.getSearchCriteria();
			List<FinishedGoodsMasterEntity> list = storageFinishedGoodsMasterDAO
					.search(searchCriteria.getFinishedGoodsNumber(),
							searchCriteria.getFromDate(),
							searchCriteria.getToDate(),
							searchCriteria.getItemName());
			popUpList(list);
		} else if (NEW_FINISHED_GOODS_MASTER_SERIES_NO.equals(flowId)) {
			Integer seriesNo = 0;
			// DateUtill dateUtill= new DateUtill();
			// System.out.println(dateUtill);
			Timestamp date = zoneService.getFirstDayOfFinYear();
			List list = storageFinishedGoodsMasterDAO
					.getNewSeriesNo(finishedGoodsMasterEntity.getFinYear());
			if (list != null && list.size() > 0) {
				Object[] obj = (Object[]) list.get(0);

				Number n = (Number) obj[0];
				if (n != null)
					seriesNo = n.intValue();
				if (obj[1] != null && obj[1] != "")
					date = (Timestamp) obj[1];
			}
			seriesNo++;

			finishedGoodsMasterOutputMessage = new FinishedGoodsMasterOutputMessage();
			finishedGoodsMasterOutputMessage.setFinishedGoodsSeriesNo(seriesNo);
			finishedGoodsMasterOutputMessage.setFinishedGoodsSeriesDate(date);
		}

		else if (FINISHED_GOODS_BY_NUMBER_AND_DATE.equals(flowId)) {
			List<FinishedGoodsMasterEntity> list = storageFinishedGoodsMasterDAO
					.findByFinishGoodNumber(
							finishedGoodsMasterEntity.getFinishedGoodsNumber(),
							finishedGoodsMasterEntity.getFinishGoodDate());
			popUpList(list);
		}

	}

	void popUpList(List<FinishedGoodsMasterEntity> list) {
		logger.info("SOM Entity List  :" + list);
		finishedGoodsMasterOutputMessage = new FinishedGoodsMasterOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<FinishedGoodsMasterDTO> resultList = new ArrayList<FinishedGoodsMasterDTO>();
			FinishedGoodsMasterDTO finishedGoodsMasterDTO;
			for (FinishedGoodsMasterEntity entity : list) {
				finishedGoodsMasterDTO = new FinishedGoodsMasterDTO();
				// Spring
				BeanUtils.copyProperties(entity, finishedGoodsMasterDTO);

				// PartyEntity partyEntity=entity.getPartyEntity();
				// if(partyEntity!=null){
				// PartyDTO partyDTO=new PartyDTO();
				// copyObject(partyEntity, partyDTO);
				// finishedGoodsMasterDTO.setPartyDTO(partyDTO);
				// }

				MastersEntity mastersEntity = entity.getMastersEntity();
				if (mastersEntity != null) {
					MastersDTO mastersDTO = new MastersDTO();
					copyObject(mastersEntity, mastersDTO);
					finishedGoodsMasterDTO.setMastersDTO(mastersDTO);
				}

				BranchEntity branchEntity = entity.getBranchEntity();
				if (branchEntity != null) {
					BranchDTO branchDTO = new BranchDTO();
					copyObject(branchEntity, branchDTO);
					finishedGoodsMasterDTO.setBranchDTO(branchDTO);
				}

				List<FinishedGoodsDetailEntity> finishedGoodsDetailEntityList = entity
						.getFinishedGoodsDetailEntities();
				List<FinishedGoodsDetailDTO> finishedGoodsDetailDTOList = new ArrayList<FinishedGoodsDetailDTO>();
				if (finishedGoodsDetailEntityList != null
						&& finishedGoodsDetailEntityList.size() > 0) {
					double qty = 0;
					for (FinishedGoodsDetailEntity finishedGoodsDetailEntity : finishedGoodsDetailEntityList) {
						FinishedGoodsDetailDTO finishedGoodsDetailDTO = new FinishedGoodsDetailDTO();
						copyObject(finishedGoodsDetailEntity,
								finishedGoodsDetailDTO);
						qty += finishedGoodsDetailDTO.getQuantity();
						if (finishedGoodsDetailEntity.getItemEntity() != null) {
							finishedGoodsDetailDTO
									.setItemId(finishedGoodsDetailEntity
											.getItemEntity().getItemId());
							finishedGoodsDetailDTO
									.setItemName(finishedGoodsDetailEntity
											.getItemEntity().getItemName());
							logger.info("SOM Entity Listgswgs  :");
							if (finishedGoodsDetailEntity
									.getMeasurementUnitMasterEntity() != null) {
								MastersDTO measurementUnitMasterDTO = new MastersDTO();
								logger.info("SOM Entity List get name :"
										+ finishedGoodsDetailEntity
												.getMeasurementUnitMasterEntity()
												.getName());
								copyObject(
										finishedGoodsDetailEntity
												.getMeasurementUnitMasterEntity(),
										measurementUnitMasterDTO);
								finishedGoodsDetailDTO
										.setMeasurementUnitMasterDTO(measurementUnitMasterDTO);
							}
							// finishedGoodsDetailDTO.setPartyInvoiceType(partyEntity.getInvoiceType());
							finishedGoodsDetailDTOList
									.add(finishedGoodsDetailDTO);
						}
						finishedGoodsMasterDTO.setTotalQuantity(qty);
					}
				}
				finishedGoodsMasterDTO
						.setFinishedGoodsDetailDTOList(finishedGoodsDetailDTOList);
				resultList.add(finishedGoodsMasterDTO);
			}
			finishedGoodsMasterOutputMessage
					.setFinishedGoodsMasterDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (CREATE_FINISHED_GOODS_MASTER.equals(flowId)) {

		} else if (UPDATE_FINISHED_GOODS_MASTER.equals(flowId)) {

		} else if (DELETE_FINISHED_GOODS_MASTER.equals(flowId)) {

		} else if (FIND_FINISHED_GOODS_MASTER_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_FINISHED_GOODS_MASTER.equals(flowId)) {

		} else if (SEARCH_FINISHED_GOODS_MASTER.equals(flowId)) {

		} else if (NEW_FINISHED_GOODS_MASTER_SERIES_NO.equals(flowId)) {

		}

	}

	private List<FinishedGoodsDetailEntity> convertFinishedGoodsDetailDtoTOFinishedGoodsDetailEntity(
			List<FinishedGoodsDetailDTO> dtoList) {
		List<FinishedGoodsDetailEntity> entityList = new ArrayList<FinishedGoodsDetailEntity>();
		for (FinishedGoodsDetailDTO dto : dtoList) {
			FinishedGoodsDetailEntity entity = new FinishedGoodsDetailEntity();
			logger.debug("DTO Value :---" + dto.getFinishedGoodsNumber());
			copyObject(dto, entity);
			if (dto != null) {
				ItemEntity itemEntity = new ItemEntity();
				itemEntity.setItemId(dto.getItemId());
				entity.setItemEntity(itemEntity);

			}
			if (dto.getMeasurementUnitMasterDTO() != null) {
				MastersEntity measurementUnitMasterEntity = new MastersEntity();
				copyObject(dto.getMeasurementUnitMasterDTO(),
						measurementUnitMasterEntity);
				entity.setMeasurementUnitMasterEntity(measurementUnitMasterEntity);
			}
			entityList.add(entity);
		}
		return entityList;

	}

	private void copyObject(Object source, Object target) {

		try {
			BeanUtils.copyProperties(source, target);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List getFinishGoodInfoToEmail(String date) {
		List list = storageFinishedGoodsMasterDAO
				.getFinishGoodInfoToEmail(date);
		return list;
	}

	@Override
	public Integer findLastFinishedGoodDetail(Integer finishGoodId,
			Integer itemId) {

		return storageFinishedGoodsMasterDAO.findLastFinishedGoodDetail(
				finishGoodId, itemId);
	}

}
