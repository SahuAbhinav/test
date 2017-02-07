package com.advanz.erp.masters.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.entity.jpa.ShiftConsumedDetailEntity;
import com.advanz.erp.masters.entity.jpa.ShiftEngInterruptionDetailEntity;
import com.advanz.erp.masters.entity.jpa.ShiftReportMasterEntity;
import com.advanz.erp.masters.entity.jpa.ShiftSpinInterruptionDetailEntity;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.ShiftConsumedDetailDTO;
import com.advanz.erp.masters.model.ShiftEngInterruptionDetailDTO;
import com.advanz.erp.masters.model.ShiftReportMasterDTO;
import com.advanz.erp.masters.model.ShiftSpinInterruptionDetailDTO;
import com.advanz.erp.masters.model.criteria.ShiftReportMasterSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.ShiftReportMasterInputMessage;
import com.advanz.erp.masters.model.msg.ShiftReportMasterOutputMessage;
import com.advanz.erp.masters.service.business.IShiftReportMasterService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageBlanketProductionMasterDAO;
import com.advanz.erp.masters.storage.IStorageShiftReportMasterDAO;

public class ShiftReportMasterServiceImpl implements IShiftReportMasterService {

	public static final String CREATE_SHIFT_REPORT_MASTER = "CreateShiftReportMaster";
	public static final String UPDATE_SHIFT_REPORT_MASTER = "UpdateShiftReportMaster";
	public static final String DELETE_SHIFT_REPORT_MASTER = "DeleteShiftReportMaster";
	public static final String FIND_SHIFT_REPORT_MASTER_BY_ID = "FindShiftReportMasterById";
	public static final String FIND_ALL_SHIFT_REPORT_MASTER = "FindAllShiftReportMasters";
	public static final String SEARCH_SHIFT_REPORT_MASTER = "SearchShiftReportMasters";
	public static final String NEW_SHIFT_REPORT_MASTER_SERIES_NO = "NewShiftReportMastersSeriesNo";
	public static final String DUPLICATE_CHECK_LIST = "DuplicateCheckList";
	public static final String CHECK_DUPLICATE_ENTRY = "CheckDuplicateEntry";

	public static final String FIND_SHIFT_REPORT_MASTER_PAGINATION = "FindShiftReportMasterPagination";
	public static final String LAST_SHIFT_DATE = "LastShiftDate";
	public String flowId = "";
	private static final Logger logger = LoggerFactory
			.getLogger(ShiftReportMasterServiceImpl.class);
	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
	@Autowired
	public IStorageBlanketProductionMasterDAO storageBlanketProductionMasterDAO;
	// autowiring

	@Autowired
	public IStorageShiftReportMasterDAO storageShiftReportMasterDAO;

	public ShiftReportMasterInputMessage shiftReportMasterInputMessage;

	public ShiftReportMasterOutputMessage shiftReportMasterOutputMessage;
	
	@Autowired
	public IZoneService zoneService;
	
	@Override
	public ShiftReportMasterOutputMessage createShiftReportMaster(
			ShiftReportMasterInputMessage shiftReportMasterInputMessage) {

		flowId = CREATE_SHIFT_REPORT_MASTER;
		// assign the message to the class level variable.
		this.shiftReportMasterInputMessage = shiftReportMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return shiftReportMasterOutputMessage;
	}

	@Override
	public ShiftReportMasterOutputMessage updateShiftReportMaster(
			ShiftReportMasterInputMessage shiftReportMasterInputMessage) {

		flowId = UPDATE_SHIFT_REPORT_MASTER;
		// assign the message to the class level variable.
		this.shiftReportMasterInputMessage = shiftReportMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return shiftReportMasterOutputMessage;
	}

	@Override
	public ShiftReportMasterOutputMessage deleteShiftReportMaster(
			ShiftReportMasterInputMessage shiftReportMasterInputMessage) {
		flowId = DELETE_SHIFT_REPORT_MASTER;
		// assign the message to the class level variable.
		this.shiftReportMasterInputMessage = shiftReportMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return shiftReportMasterOutputMessage;

	}

	@Override
	public ShiftReportMasterOutputMessage findShiftReportMasterById(
			ShiftReportMasterInputMessage shiftReportMasterInputMessage) {
		flowId = FIND_SHIFT_REPORT_MASTER_BY_ID;
		// assign the message to the class level variable.
		this.shiftReportMasterInputMessage = shiftReportMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return shiftReportMasterOutputMessage;

	}

	@Override
	public ShiftReportMasterOutputMessage findAllShiftReportMasters() {
		flowId = FIND_ALL_SHIFT_REPORT_MASTER;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return shiftReportMasterOutputMessage;
	}

	@Override
	public ShiftReportMasterOutputMessage search(
			ShiftReportMasterInputMessage shiftReportMasterInputMessage) {
		flowId = SEARCH_SHIFT_REPORT_MASTER;
		this.shiftReportMasterInputMessage = shiftReportMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return shiftReportMasterOutputMessage;
	}

	@Override
	public ShiftReportMasterOutputMessage getDuplicateCheckList() {
		flowId = DUPLICATE_CHECK_LIST;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return shiftReportMasterOutputMessage;
	}

	@Override
	public ShiftReportMasterOutputMessage checkDuplicateEntry(
			ShiftReportMasterInputMessage shiftReportMasterInputMessage) {

		flowId = CHECK_DUPLICATE_ENTRY;
		this.shiftReportMasterInputMessage = shiftReportMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return shiftReportMasterOutputMessage;
	}

	@Override
	public ShiftReportMasterOutputMessage findIShiftReportPagination(
			ShiftReportMasterInputMessage shiftReportMasterInputMessage) {
		flowId = FIND_SHIFT_REPORT_MASTER_PAGINATION;
		this.shiftReportMasterInputMessage = shiftReportMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return shiftReportMasterOutputMessage;
	}

	@Override
	public ShiftReportMasterOutputMessage getLastShiftDate(
			ShiftReportMasterInputMessage shiftReportMasterInputMessage) {
		flowId = LAST_SHIFT_DATE;
		this.shiftReportMasterInputMessage = shiftReportMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return shiftReportMasterOutputMessage;
	}

	// @Override
	// public ShiftReportMasterOutputMessage
	// getNewShiftReportSeriesNo(ShiftReportMasterInputMessage
	// ShiftReportMasterInputMessage) {
	// flowId = NEW_SHIFT_REPORT_MASTER_SERIES_NO;
	// this.ShiftReportMasterInputMessage=ShiftReportMasterInputMessage;
	// // call the template method
	// advanzErpServiceTemplate.execute(this);
	// return finishedGoodsMasterOutputMessage;
	// }

	@Override
	public boolean validateInput() {

		if (CREATE_SHIFT_REPORT_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_SHIFT_REPORT_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_SHIFT_REPORT_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_SHIFT_REPORT_MASTER_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_SHIFT_REPORT_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_SHIFT_REPORT_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (NEW_SHIFT_REPORT_MASTER_SERIES_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DUPLICATE_CHECK_LIST.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (CHECK_DUPLICATE_ENTRY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_SHIFT_REPORT_MASTER_PAGINATION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (LAST_SHIFT_DATE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {

		ShiftReportMasterEntity shiftReportMasterEntity = new ShiftReportMasterEntity();
		if (shiftReportMasterInputMessage != null) {
			ShiftReportMasterDTO shiftReportMasterDTO = shiftReportMasterInputMessage
					.getShiftReportMasterDTO();

			if (shiftReportMasterDTO != null) {
				BeanUtils.copyProperties(shiftReportMasterDTO,
						shiftReportMasterEntity);

				MastersEntity mastersEntity = new MastersEntity();
				if (shiftReportMasterDTO.getMastersDTO() != null) {
					mastersEntity.setMastersId(shiftReportMasterDTO
							.getMastersDTO().getMastersId());
					shiftReportMasterEntity.setMastersEntity(mastersEntity);
				}

				List<ShiftConsumedDetailDTO> shiftConsumedDetailDTOList = shiftReportMasterDTO
						.getShiftConsumedDetailDTOList();
				// logger.info("Deatil List = "+ shiftConsumedDetailDTOList);
				if (shiftConsumedDetailDTOList != null
						&& shiftConsumedDetailDTOList.size() > 0) {
					List<ShiftConsumedDetailEntity> shiftConsumedDetailEntity = convertShiftConsumedDetailDtoTOShiftConsumedDetailEntity(shiftConsumedDetailDTOList);

					if (DELETE_SHIFT_REPORT_MASTER.equals(flowId)) {
						for (int i = 0; i < shiftConsumedDetailEntity.size(); i++) {
							ShiftConsumedDetailEntity shiftConsumedDetailList = shiftConsumedDetailEntity
									.get(i);
							storageShiftReportMasterDAO
									.deleteConsumedDetail(shiftConsumedDetailList);
						}
					}
					shiftReportMasterEntity
							.setShiftConsumedDetailEntityList(shiftConsumedDetailEntity);
				}

				List<ShiftEngInterruptionDetailDTO> shiftEngInterruptionDetailDTOList = shiftReportMasterDTO
						.getShiftEngInterruptionDetailDTOList();
				// logger.info("Deatil List = "+
				// shiftEngInterruptionDetailDTOList);
				if (shiftEngInterruptionDetailDTOList != null
						&& shiftEngInterruptionDetailDTOList.size() > 0) {

					List<ShiftEngInterruptionDetailEntity> shiftEngInterruptionDetailEntity = convertShiftInterruptionDetailDtoTOShiftInterruptionDetailEntity(shiftEngInterruptionDetailDTOList);
					if (DELETE_SHIFT_REPORT_MASTER.equals(flowId)) {
						for (int i = 0; i < shiftEngInterruptionDetailEntity
								.size(); i++) {
							ShiftEngInterruptionDetailEntity shiftEngEntityList = shiftEngInterruptionDetailEntity
									.get(i);
							storageShiftReportMasterDAO
									.deleteShiftEng(shiftEngEntityList);
						}
					}
					shiftReportMasterEntity
							.setShiftEngInterruptionDetailEntityList(shiftEngInterruptionDetailEntity);
				}

				List<ShiftSpinInterruptionDetailDTO> shiftSpinInterruptionDetailDTOList = shiftReportMasterDTO
						.getShiftSpinInterruptionDetailDTOList();
				// logger.info("Deatil List = "+
				// shiftSpinInterruptionDetailDTOList);
				if (shiftSpinInterruptionDetailDTOList != null
						&& shiftSpinInterruptionDetailDTOList.size() > 0) {
					List<ShiftSpinInterruptionDetailEntity> shiftSpinInterruptionDetailEntity = convertShiftSpinInterruptionDetailDtoTOShiftSpinInterruptionDetailEntity(shiftSpinInterruptionDetailDTOList);

					if (DELETE_SHIFT_REPORT_MASTER.equals(flowId)) {
						for (int i = 0; i < shiftSpinInterruptionDetailEntity
								.size(); i++) {
							ShiftSpinInterruptionDetailEntity shiftSpinEntityList = shiftSpinInterruptionDetailEntity
									.get(i);
							storageShiftReportMasterDAO
									.deleteShiftSpin(shiftSpinEntityList);
						}
					}

					shiftReportMasterEntity
							.setShiftSpinInterruptionDetailEntityList(shiftSpinInterruptionDetailEntity);
				}

			} else
				logger.info("shiftReportMasterDTO is null ");
		}

		if (CREATE_SHIFT_REPORT_MASTER.equals(flowId)) {
			List<ShiftConsumedDetailEntity> entity = shiftReportMasterEntity
					.getShiftConsumedDetailEntityList();
			try {
				Integer itemGroupFlagId = shiftReportMasterInputMessage
						.getShiftReportMasterDTO().getShiftConsumedDetailDTO()
						.getItemGroupFlagId();
				for (int i = 0; i < entity.size(); i++) {
					ShiftConsumedDetailEntity consumed = entity.get(i);
					consumed.setItemGroupFlagId(itemGroupFlagId);
					entity.set(i, consumed);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			shiftReportMasterEntity.setShiftConsumedDetailEntityList(entity);
			storageShiftReportMasterDAO.update(shiftReportMasterEntity);

		} else if (UPDATE_SHIFT_REPORT_MASTER.equals(flowId)) {
			List<ShiftConsumedDetailEntity> entity = shiftReportMasterEntity
					.getShiftConsumedDetailEntityList();
			Integer itemGroupFlagId = shiftReportMasterInputMessage
					.getShiftReportMasterDTO().getShiftConsumedDetailDTO()
					.getItemGroupFlagId();
			try {
				for (int i = 0; i < entity.size(); i++) {
					ShiftConsumedDetailEntity consumed = entity.get(i);
					consumed.setItemGroupFlagId(itemGroupFlagId);
					entity.set(i, consumed);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			shiftReportMasterEntity.setShiftConsumedDetailEntityList(entity);
			storageShiftReportMasterDAO.update(shiftReportMasterEntity);

		} else if (DELETE_SHIFT_REPORT_MASTER.equals(flowId)) {

			storageShiftReportMasterDAO.delete(shiftReportMasterEntity);

		} else if (FIND_SHIFT_REPORT_MASTER_BY_ID.equals(flowId)) {
			// logger.info("shiftReportMasterEntity.getShiftReportId() >>>>>>>>>>>> : "+
			// shiftReportMasterEntity.getShiftReportId());
			List<ShiftReportMasterEntity> list = storageShiftReportMasterDAO
					.findById(shiftReportMasterEntity.getShiftReportId());
			// logger.info("ShiftReportMasterEntity for id("+shiftReportMasterEntity.getShiftReportId()+") :"+list);
			if (list != null && list.size() > 0) {
				// logger.info("party Id"
				// +list.get(0).getPartyEntity().getPartyId());
				logger.info("ShiftConsumedDetailEntity List :-"
						+ list.get(0).getShiftConsumedDetailEntityList());
				// logger.info("ShiftEngInterruptionDetailEntity List :-"+list.get(0).getShiftEngInterruptionDetailEntityList());
			}
			popUpList(list);

		} else if (FIND_ALL_SHIFT_REPORT_MASTER.equals(flowId)) {

			List<ShiftReportMasterEntity> list = storageShiftReportMasterDAO
					.load();
			popUpList(list);
		} else if (CHECK_DUPLICATE_ENTRY.equals(flowId)) {
			ShiftReportMasterDTO shiftReportMasterDTO = shiftReportMasterInputMessage
					.getShiftReportMasterDTO();
			List<ShiftReportMasterEntity> list = storageShiftReportMasterDAO
					.checkDuplicateEntry(
							shiftReportMasterDTO.getShifReportDate(),
							shiftReportMasterDTO.getRunNo(),
							shiftReportMasterDTO.getMastersDTO().getMastersId());
			popUpList(list);
		}

		else if (DUPLICATE_CHECK_LIST.equals(flowId)) {
			List list = storageShiftReportMasterDAO.getDuplicateCheckList();
			shiftReportMasterOutputMessage = new ShiftReportMasterOutputMessage();
			if (list != null && list.size() > 0) {
				List<ShiftReportMasterDTO> shiftList = new ArrayList<ShiftReportMasterDTO>();
				for (int i = 0; i < list.size(); i++) {
					Object[] object = (Object[]) list.get(i);
					int shiftId = (Integer) object[0];
					Date shifReportDate = (Date) object[1];
					String runNo = (String) object[2];
					ShiftReportMasterDTO shiftDTO = new ShiftReportMasterDTO();
					MastersDTO dto = new MastersDTO();
					dto.setMastersId(shiftId);
					shiftDTO.setMastersDTO(dto);
					shiftDTO.setShifReportDate(shifReportDate);
					shiftDTO.setRunNo(runNo);
					shiftList.add(shiftDTO);
				}
				shiftReportMasterOutputMessage
						.setShiftReportMasterDTOList(shiftList);
			}
		}

		else if (SEARCH_SHIFT_REPORT_MASTER.equals(flowId)) {
			ShiftReportMasterSearchCriteriaDTO searchCriteria = shiftReportMasterInputMessage
					.getSearchCriteria();
			List<ShiftReportMasterEntity> list = storageShiftReportMasterDAO
					.search(searchCriteria.getRunNo(),
							searchCriteria.getFromDate(),
							searchCriteria.getToDate());
			popUpList(list);
		} else if (FIND_SHIFT_REPORT_MASTER_PAGINATION.equals(flowId)) {
			storageBlanketProductionMasterDAO.cleanDuplicateEntry();
			System.out.println("Calling before loading shif report list");
			List<ShiftReportMasterEntity> list = storageShiftReportMasterDAO
					.findIShiftReportPagination(shiftReportMasterInputMessage
							.getNext());
			popUpList(list);
		} else if (LAST_SHIFT_DATE.equals(flowId)) {
			
			Timestamp date = zoneService.getFirstDayOfFinYear();
			Timestamp date1=storageShiftReportMasterDAO.getLastShiftDate();
			if (date1 != null) {
				date=date1;
			}
			
			shiftReportMasterOutputMessage = new ShiftReportMasterOutputMessage();
			shiftReportMasterOutputMessage.setLastShiftDate(date);

		}

	}

	void popUpList(List<ShiftReportMasterEntity> list) {
		logger.info("SOM Entity List  :" + list);
		shiftReportMasterOutputMessage = new ShiftReportMasterOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<ShiftReportMasterDTO> resultList = new ArrayList<ShiftReportMasterDTO>();
			ShiftReportMasterDTO shiftReportMasterDTO;
			for (ShiftReportMasterEntity entity : list) {
				shiftReportMasterDTO = new ShiftReportMasterDTO();
				shiftReportMasterDTO.setMastersDTO(new MastersDTO());
				// Spring
				BeanUtils.copyProperties(entity, shiftReportMasterDTO);
				try {

					if (entity.getMastersEntity() != null) {
						BeanUtils.copyProperties(entity.getMastersEntity(),
								shiftReportMasterDTO.getMastersDTO());
					}
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}

				List<ShiftConsumedDetailEntity> shiftConsumedDetailEntityList = entity
						.getShiftConsumedDetailEntityList();

				List<ShiftConsumedDetailDTO> shiftConsumedDetailDTOList = new ArrayList<ShiftConsumedDetailDTO>();
				if (shiftConsumedDetailEntityList != null
						&& shiftConsumedDetailEntityList.size() > 0) {
					for (ShiftConsumedDetailEntity finishedGoodsDetailEntity : shiftConsumedDetailEntityList) {

						ShiftConsumedDetailDTO shiftConsumedDetailDTO = new ShiftConsumedDetailDTO();
						ItemEntity itemEntity = finishedGoodsDetailEntity
								.getItemEntity();
						if (itemEntity != null) {
							ItemDTO itemDTO = new ItemDTO();
							copyObject(itemEntity, itemDTO);
							shiftConsumedDetailDTO.setItemDTO(itemDTO);
						}

						copyObject(finishedGoodsDetailEntity,
								shiftConsumedDetailDTO);
						shiftConsumedDetailDTOList.add(shiftConsumedDetailDTO);
					}

				}
				List<ShiftEngInterruptionDetailEntity> shiftEngInterruptionDetailEntityList = entity
						.getShiftEngInterruptionDetailEntityList();
				List<ShiftEngInterruptionDetailDTO> shiftEngInterruptionDetailDTOList = new ArrayList<ShiftEngInterruptionDetailDTO>();
				if (shiftEngInterruptionDetailEntityList != null
						&& shiftEngInterruptionDetailEntityList.size() > 0) {
					for (ShiftEngInterruptionDetailEntity shiftEngInterruptionDetailEntity : shiftEngInterruptionDetailEntityList) {
						ShiftEngInterruptionDetailDTO shiftEngInterruptionDetailDTO = new ShiftEngInterruptionDetailDTO();
						copyObject(shiftEngInterruptionDetailEntity,
								shiftEngInterruptionDetailDTO);
						shiftEngInterruptionDetailDTOList
								.add(shiftEngInterruptionDetailDTO);
					}
				}
				List<ShiftSpinInterruptionDetailEntity> shiftSpinInterruptionDetailEntityList = entity
						.getShiftSpinInterruptionDetailEntityList();
				List<ShiftSpinInterruptionDetailDTO> shiftSpinInterruptionDetailDTOList = new ArrayList<ShiftSpinInterruptionDetailDTO>();
				if (shiftSpinInterruptionDetailEntityList != null
						&& shiftSpinInterruptionDetailEntityList.size() > 0) {
					for (ShiftSpinInterruptionDetailEntity shiftSpinInterruptionDetailEntity : shiftSpinInterruptionDetailEntityList) {
						ShiftSpinInterruptionDetailDTO shiftSpinInterruptionDetailDTO = new ShiftSpinInterruptionDetailDTO();
						copyObject(shiftSpinInterruptionDetailEntity,
								shiftSpinInterruptionDetailDTO);
						shiftSpinInterruptionDetailDTOList
								.add(shiftSpinInterruptionDetailDTO);
					}
				}
				shiftReportMasterDTO
						.setShiftConsumedDetailDTOList(shiftConsumedDetailDTOList);
				shiftReportMasterDTO
						.setShiftEngInterruptionDetailDTOList(shiftEngInterruptionDetailDTOList);
				shiftReportMasterDTO
						.setShiftSpinInterruptionDetailDTOList(shiftSpinInterruptionDetailDTOList);
				resultList.add(shiftReportMasterDTO);
			}
			shiftReportMasterOutputMessage
					.setShiftReportMasterDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (CREATE_SHIFT_REPORT_MASTER.equals(flowId)) {

		} else if (UPDATE_SHIFT_REPORT_MASTER.equals(flowId)) {

		} else if (DELETE_SHIFT_REPORT_MASTER.equals(flowId)) {

		} else if (FIND_SHIFT_REPORT_MASTER_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_SHIFT_REPORT_MASTER.equals(flowId)) {

		} else if (SEARCH_SHIFT_REPORT_MASTER.equals(flowId)) {

		} else if (NEW_SHIFT_REPORT_MASTER_SERIES_NO.equals(flowId)) {

		}

	}

	private List<ShiftConsumedDetailEntity> convertShiftConsumedDetailDtoTOShiftConsumedDetailEntity(
			List<ShiftConsumedDetailDTO> dtoList) {
		Integer flgId = dtoList.get(0).getItemGroupFlagId();
		List<ShiftConsumedDetailEntity> entityList = new ArrayList<ShiftConsumedDetailEntity>();
		for (ShiftConsumedDetailDTO dto : dtoList) {
			ShiftConsumedDetailEntity entity = new ShiftConsumedDetailEntity();
			logger.debug("DTO Value :---" + dto.getMeasurementUnitId());
			dto.setItemGroupFlagId(flgId);

			copyObject(dto, entity);
			if (dto != null) {
				ItemEntity itemEntity = new ItemEntity();
				itemEntity.setItemId(dto.getItemId());
				entity.setItemEntity(itemEntity);

			}
			entityList.add(entity);
		}
		return entityList;

	}

	private List<ShiftEngInterruptionDetailEntity> convertShiftInterruptionDetailDtoTOShiftInterruptionDetailEntity(
			List<ShiftEngInterruptionDetailDTO> dtoList) {
		List<ShiftEngInterruptionDetailEntity> entityList = new ArrayList<ShiftEngInterruptionDetailEntity>();
		for (ShiftEngInterruptionDetailDTO dto : dtoList) {
			ShiftEngInterruptionDetailEntity entity = new ShiftEngInterruptionDetailEntity();

			copyObject(dto, entity);

			entityList.add(entity);
		}
		return entityList;

	}

	private List<ShiftSpinInterruptionDetailEntity> convertShiftSpinInterruptionDetailDtoTOShiftSpinInterruptionDetailEntity(
			List<ShiftSpinInterruptionDetailDTO> dtoList) {
		List<ShiftSpinInterruptionDetailEntity> entityList = new ArrayList<ShiftSpinInterruptionDetailEntity>();
		for (ShiftSpinInterruptionDetailDTO dto : dtoList) {
			ShiftSpinInterruptionDetailEntity entity = new ShiftSpinInterruptionDetailEntity();

			copyObject(dto, entity);

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

}
