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
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.entity.jpa.PartyEntity;
import com.advanz.erp.masters.entity.jpa.QuotationDetailEntity;
import com.advanz.erp.masters.entity.jpa.QuotationMasterEntity;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.QuotationDetailDTO;
import com.advanz.erp.masters.model.QuotationMasterDTO;
import com.advanz.erp.masters.model.criteria.QuotationMasterSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.QuotationMasterInputMessage;
import com.advanz.erp.masters.model.msg.QuotationMasterOutputMessage;
import com.advanz.erp.masters.service.business.IQuotationMasterService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageQuotationMasterDAO;

public class QuotationMasterServiceImpl implements IQuotationMasterService {

	public static final String CREATE_QUOTATION_MASTER = "CreateQuotationMaster";
	public static final String UPDATE_QUOTATION_MASTER = "UpdateQuotationMaster";
	public static final String DELETE_QUOTATION_MASTER = "DeleteQuotationMaster";
	public static final String FIND_QUOTATION_MASTER_BY_ID = "FindQuotationMasterById";
	public static final String FIND_ALL_QUOTATION_MASTER = "FindAllQuotationMasters";
	public static final String SEARCH_QUOTATION_MASTER = "SearchQuotationMasters";
	public static final String NEW_QUOTATION_MASTER_SERIES_NO = "NewQuotationMastersSeriesNo";
	public static final String PRE_REMOVE_CHECK = "PreRemoveCheck";
	public static final String FIND_QUOTATION_WITH_PAGINATION = "FindQuotationWithPagination";
	public String flowId = "";
	private static final Logger logger = LoggerFactory
			.getLogger(QuotationMasterServiceImpl.class);
	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageQuotationMasterDAO storageQuotationMasterDAO;

	public QuotationMasterInputMessage quotationMasterInputMessage;

	public QuotationMasterOutputMessage quotationMasterOutputMessage;

	@Autowired
	public IZoneService zoneService;

	@Override
	public QuotationMasterOutputMessage createQuotationMaster(
			QuotationMasterInputMessage quotationMasterInputMessage) {

		flowId = CREATE_QUOTATION_MASTER;
		// assign the message to the class level variable.
		this.quotationMasterInputMessage = quotationMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return quotationMasterOutputMessage;
	}

	@Override
	public QuotationMasterOutputMessage updateQuotationMaster(
			QuotationMasterInputMessage quotationMasterInputMessage) {

		flowId = UPDATE_QUOTATION_MASTER;
		// assign the message to the class level variable.
		this.quotationMasterInputMessage = quotationMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return quotationMasterOutputMessage;
	}

	@Override
	public QuotationMasterOutputMessage deleteQuotationMaster(
			QuotationMasterInputMessage quotationMasterInputMessage) {
		flowId = DELETE_QUOTATION_MASTER;
		// assign the message to the class level variable.
		this.quotationMasterInputMessage = quotationMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return quotationMasterOutputMessage;

	}

	@Override
	public QuotationMasterOutputMessage findQuotationMasterById(
			QuotationMasterInputMessage quotationMasterInputMessage) {
		flowId = FIND_QUOTATION_MASTER_BY_ID;
		// assign the message to the class level variable.
		this.quotationMasterInputMessage = quotationMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return quotationMasterOutputMessage;

	}

	@Override
	public QuotationMasterOutputMessage findAllQuotationMasters() {
		flowId = FIND_ALL_QUOTATION_MASTER;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return quotationMasterOutputMessage;
	}

	@Override
	public QuotationMasterOutputMessage search(
			QuotationMasterInputMessage quotationMasterInputMessage) {
		flowId = SEARCH_QUOTATION_MASTER;
		this.quotationMasterInputMessage = quotationMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return quotationMasterOutputMessage;

	}

	@Override
	public QuotationMasterOutputMessage getNewQuotationSeriesNo(
			QuotationMasterInputMessage quotationMasterInputMessage) {
		flowId = NEW_QUOTATION_MASTER_SERIES_NO;
		this.quotationMasterInputMessage = quotationMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return quotationMasterOutputMessage;
	}

	@Override
	public QuotationMasterOutputMessage checkBeforeRemove(
			QuotationMasterInputMessage quotationMasterInputMessage) {

		flowId = PRE_REMOVE_CHECK;
		// assign the message to the class level variable.
		this.quotationMasterInputMessage = quotationMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return quotationMasterOutputMessage;
	}

	@Override
	public QuotationMasterOutputMessage findQuotationForPagination(
			QuotationMasterInputMessage quotationMasterInputMessage) {
		flowId = FIND_QUOTATION_WITH_PAGINATION;
		// assign the message to the class level variable.
		this.quotationMasterInputMessage = quotationMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return quotationMasterOutputMessage;
	}

	@Override
	public boolean validateInput() {

		if (CREATE_QUOTATION_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_QUOTATION_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_QUOTATION_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_QUOTATION_MASTER_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_QUOTATION_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_QUOTATION_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (NEW_QUOTATION_MASTER_SERIES_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (PRE_REMOVE_CHECK.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_QUOTATION_WITH_PAGINATION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		return false;
	}

	@Override
	public void performBusinessLogic() {

		QuotationMasterEntity quotationMasterEntity = new QuotationMasterEntity();

		List<QuotationDetailDTO> quotationDetailDTOList = null;
		QuotationMasterDTO quotationMasterDTO = null;

		if (quotationMasterInputMessage != null) {
			quotationMasterDTO = quotationMasterInputMessage
					.getQuotationMasterDTO();
			if (quotationMasterDTO != null) {
				BeanUtils.copyProperties(quotationMasterDTO,
						quotationMasterEntity);
				PartyDTO partyDTO = quotationMasterDTO.getPartyDTO();
				logger.info("PArty = " + partyDTO);
				if (partyDTO != null) {
					PartyEntity partyEntity = new PartyEntity();
					copyObject(partyDTO, partyEntity);
					quotationMasterEntity.setPartyEntity(partyEntity);
				}

				BranchDTO branchDTO = quotationMasterDTO.getBranchDTO();
				if (branchDTO != null) {
					BranchEntity branchEntity = new BranchEntity();
					copyObject(branchDTO, branchEntity);
					quotationMasterEntity.setBranchEntity(branchEntity);
				}

				quotationDetailDTOList = quotationMasterDTO
						.getQuotationDetailDTOList();

				logger.info("Deatil List = " + quotationDetailDTOList);
				if (quotationDetailDTOList != null
						&& quotationDetailDTOList.size() > 0) {
					List<QuotationDetailEntity> quotationDetailEntities = convertQuotationDetailDtoTOQuotationDetailEntity(
							quotationDetailDTOList, quotationMasterDTO);
					quotationMasterEntity
							.setQuotationDetailEntities(quotationDetailEntities);
				}
			}
		}

		if (CREATE_QUOTATION_MASTER.equals(flowId)) {
			// check duplicate quotationMaster name
			List<QuotationMasterEntity> list = storageQuotationMasterDAO
					.findByQutationNumber(quotationMasterEntity
							.getQuotationNumber());

			quotationMasterOutputMessage = new QuotationMasterOutputMessage();
			if (list != null && list.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("1", "Quotation number "
						+ quotationMasterEntity.getQuotationNumber()
						+ " already exit ,it can not be duplicate");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				quotationMasterOutputMessage.setErrorListDTO(errorListDTO);
			} else {

				// quotationMasterOutputMessage = new
				// QuotationMasterOutputMessage();
				storageQuotationMasterDAO.create(quotationMasterEntity);
				quotationMasterOutputMessage.setErrorListDTO(null);
			}

		} else if (UPDATE_QUOTATION_MASTER.equals(flowId)) {
			storageQuotationMasterDAO.update(quotationMasterEntity);

		} else if (DELETE_QUOTATION_MASTER.equals(flowId)) {
			try {
				storageQuotationMasterDAO.delete(quotationMasterEntity);
			} catch (Exception ex) {
				quotationMasterOutputMessage = new QuotationMasterOutputMessage();
				ErrorDTO errorDTO = new ErrorDTO("1", ex.getMessage());
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				quotationMasterOutputMessage.setErrorListDTO(errorListDTO);
			}
		} else if (FIND_QUOTATION_MASTER_BY_ID.equals(flowId)) {
			List<QuotationMasterEntity> list = storageQuotationMasterDAO
					.findById(quotationMasterEntity.getQuotationAutoId());
			logger.info("QuotationMasterEntity for id("
					+ quotationMasterEntity.getQuotationAutoId() + ") :" + list);
			if (list != null && list.size() > 0) {
				logger.info("party Id"
						+ list.get(0).getPartyEntity().getPartyId());
				logger.info("Detail List"
						+ list.get(0).getQuotationDetailEntities());
			}
			popUpList(list);

		} else if (FIND_ALL_QUOTATION_MASTER.equals(flowId)) {
			List<QuotationMasterEntity> list = storageQuotationMasterDAO.load();
			popUpList(list);
		} else if (FIND_QUOTATION_WITH_PAGINATION.equals(flowId)) {
			List<QuotationMasterEntity> list = storageQuotationMasterDAO
					.FindQuotationPagination(quotationMasterInputMessage
							.getNext());
			popUpList(list);
		}

		else if (SEARCH_QUOTATION_MASTER.equals(flowId)) {
			QuotationMasterSearchCriteriaDTO searchCriteria = quotationMasterInputMessage
					.getSearchCriteria();
			List<QuotationMasterEntity> list = storageQuotationMasterDAO
					.search(searchCriteria.getQoNumber(),
							searchCriteria.getFromDate(),
							searchCriteria.getToDate(),
							searchCriteria.getPartyName(),
							searchCriteria.getItemName());
			popUpList(list);
		} else if (NEW_QUOTATION_MASTER_SERIES_NO.equals(flowId)) {
			Integer seriesNo = 0;
			Timestamp date = zoneService.getFirstDayOfFinYear();
			List list = storageQuotationMasterDAO
					.getNewSeriesNo(quotationMasterEntity.getFinYear());

			if (list != null && list.size() > 0) {
				Object[] obj = (Object[]) list.get(0);

				Number n = (Number) obj[0];
				if (n != null)
					seriesNo = n.intValue();
				if (obj[1] != null && obj[1] != "")
					date = (Timestamp) obj[1];
			}
			seriesNo++;

			quotationMasterOutputMessage = new QuotationMasterOutputMessage();
			quotationMasterOutputMessage.setQuotationSeriesNo(seriesNo);
			quotationMasterOutputMessage.setQuotationSeriesDate(date);

		}
		if (PRE_REMOVE_CHECK.equals(flowId)) {
			if (storageQuotationMasterDAO.isInUsed(quotationMasterInputMessage
					.getQuotationMasterDTO().getQuotationAutoId())) {
				logger.info("TRUE");
				quotationMasterOutputMessage = new QuotationMasterOutputMessage();
				ErrorDTO errorDTO = new ErrorDTO("1",
						"Quotation can not be Removed,Used in SalesOrder");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				quotationMasterOutputMessage.setErrorListDTO(errorListDTO);
			} else {
				quotationMasterOutputMessage = null;
			}
		}
	}

	void popUpList(List<QuotationMasterEntity> list) {
		logger.info("SOM Entity List  :" + list);
		quotationMasterOutputMessage = new QuotationMasterOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<QuotationMasterDTO> resultList = new ArrayList<QuotationMasterDTO>();
			QuotationMasterDTO quotationMasterDTO;
			for (QuotationMasterEntity entity : list) {

				quotationMasterDTO = new QuotationMasterDTO();
				// Spring
				BeanUtils.copyProperties(entity, quotationMasterDTO);

				PartyEntity partyEntity = entity.getPartyEntity();
				if (partyEntity != null) {
					PartyDTO partyDTO = new PartyDTO();
					copyObject(partyEntity, partyDTO);
					quotationMasterDTO.setPartyDTO(partyDTO);
				}

				BranchEntity branchEntity = entity.getBranchEntity();
				if (branchEntity != null) {
					BranchDTO branchDTO = new BranchDTO();
					copyObject(branchEntity, branchDTO);
					quotationMasterDTO.setBranchDTO(branchDTO);
				}

				List<QuotationDetailEntity> quotationDetailEntityList = entity
						.getQuotationDetailEntities();
				List<QuotationDetailDTO> quotationDetailDTOList = new ArrayList<QuotationDetailDTO>();
				if (quotationDetailEntityList != null
						&& quotationDetailEntityList.size() > 0) {
					double qty = 0;
					for (QuotationDetailEntity quotationDetailEntity : quotationDetailEntityList) {
						if (quotationDetailEntity.getDeletedFlag())
							continue;

						QuotationDetailDTO quotationDetailDTO = new QuotationDetailDTO();
						copyObject(quotationDetailEntity, quotationDetailDTO);
						qty += quotationDetailEntity.getQuantity();
						if (quotationDetailEntity.getItemEntity() != null) {
							quotationDetailDTO.setItemId(quotationDetailEntity
									.getItemEntity().getItemId());
							quotationDetailDTO
									.setItemName(quotationDetailEntity
											.getItemEntity().getInvoiceName());
							// quotationDetailDTO.setPartyInvoiceType(partyEntity.getInvoiceType());
						}
						if (quotationDetailEntity
								.getMeasurementUnitMasterEntity() != null) {
							MastersDTO measurementUnitMasterDTO = new MastersDTO();
							copyObject(
									quotationDetailEntity
											.getMeasurementUnitMasterEntity(),
									measurementUnitMasterDTO);
							quotationDetailDTO
									.setMeasurementUnitMasterDTO(measurementUnitMasterDTO);
						}
						quotationDetailDTOList.add(quotationDetailDTO);
					}
					quotationMasterDTO.setTotalQuantity(qty);
				}
				quotationMasterDTO
						.setQuotationDetailDTOList(quotationDetailDTOList);
				resultList.add(quotationMasterDTO);
			}
			quotationMasterOutputMessage.setQuotationMasterDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (CREATE_QUOTATION_MASTER.equals(flowId)) {

		} else if (UPDATE_QUOTATION_MASTER.equals(flowId)) {

		} else if (DELETE_QUOTATION_MASTER.equals(flowId)) {

		} else if (FIND_QUOTATION_MASTER_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_QUOTATION_MASTER.equals(flowId)) {

		} else if (SEARCH_QUOTATION_MASTER.equals(flowId)) {

		} else if (NEW_QUOTATION_MASTER_SERIES_NO.equals(flowId)) {

		}

	}

	private List<QuotationDetailEntity> convertQuotationDetailDtoTOQuotationDetailEntity(
			List<QuotationDetailDTO> dtoList,
			QuotationMasterDTO quotationMasterDTO) {
		List<QuotationDetailEntity> entityList = new ArrayList<QuotationDetailEntity>();
		for (QuotationDetailDTO dto : dtoList) {
			QuotationDetailEntity entity = new QuotationDetailEntity();
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
			if (quotationMasterDTO.getCreatedUserId() != null) {
				entity.setCreatedUserId(quotationMasterDTO.getCreatedUserId());
			}
			if (quotationMasterDTO.getModifiedUserId() != null) {
				entity.setModifiedUserId(quotationMasterDTO.getModifiedUserId());
			}
			entityList.add(entity);
		}
		return entityList;

	}

	private void copyObject(Object qource, Object target) {

		try {
			BeanUtils.copyProperties(qource, target);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
