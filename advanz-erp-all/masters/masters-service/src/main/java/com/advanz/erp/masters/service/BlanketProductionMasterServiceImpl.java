package com.advanz.erp.masters.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.BlanketProductionDetailLeftEntity;
import com.advanz.erp.masters.entity.jpa.BlanketProductionDetailRightEntity;
import com.advanz.erp.masters.entity.jpa.BlanketProductionMasterEntity;
import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.entity.jpa.FinishedGoodsDetailEntity;
import com.advanz.erp.masters.entity.jpa.FinishedGoodsMasterEntity;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.model.BlanketProductionDetailLeftDTO;
import com.advanz.erp.masters.model.BlanketProductionDetailRightDTO;
import com.advanz.erp.masters.model.BlanketProductionMasterDTO;
import com.advanz.erp.masters.model.FinishedGoodsMasterDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.criteria.BlanketProductionSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterInputMessage;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterOutputMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.service.business.IBlanketProductionMasterService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageBlanketProductionMasterDAO;
import com.advanz.erp.masters.storage.IStorageFinishedGoodsMasterDAO;
import com.advanz.erp.masters.storage.IStorageItemDAO;

public class BlanketProductionMasterServiceImpl implements
		IBlanketProductionMasterService {
	private static final Logger logger = LoggerFactory
			.getLogger(BlanketProductionMasterServiceImpl.class);

	public static final String CREATE_BPM = "CreateBlanketProductionMaster";
	public static final String UPDATE_BPM = "UpdateBlanketProductionMaster";
	public static final String DELETE_BPM = "DeleteBlanketProductionMaster";
	public static final String FIND_BPM_BY_ID = "FindBlanketProductionMasterById";
	public static final String FIND_ALL_BPM = "FindAllBlanketProductionMasters";
	public static final String SEARCH_BPM = "SearchBlanketProductionMasters";

	public static final String CREATE_BPL = "CreateBlanketProductionLeft";
	public static final String CREATE_BPR = "CreateBlanketProductionRight";
	public static final String FIND_BPM_PAGINATION = "FindBlanketProductionMasterPagination";
	public static final String FIND_BPM_FOR_SHIFT_REPORT = "FindBlanketProductionForShiftReport";
	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
	@Autowired
	private IItemService itemService; // autowiring
	@Autowired
	private IStorageItemDAO itemDAO;
	@Autowired
	public IStorageBlanketProductionMasterDAO storageBlanketProductionMasterDAO;

	public BlanketProductionMasterInputMessage blanketProductionMasterInputMessage;

	public BlanketProductionMasterOutputMessage blanketProductionMasterOutputMessage;

	@Autowired
	public IZoneService zoneService;

	@Autowired
	public IStorageFinishedGoodsMasterDAO finishedGoodsMasterDAO;

	@Override
	public BlanketProductionMasterOutputMessage createBlanketProductionMaster(
			BlanketProductionMasterInputMessage blanketProductionMasterInputMessage) {

		flowId = CREATE_BPM;
		// assign the message to the class level variable.
		this.blanketProductionMasterInputMessage = blanketProductionMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return blanketProductionMasterOutputMessage;
	}

	@Override
	public BlanketProductionMasterOutputMessage updateBlanketProductionMaster(
			BlanketProductionMasterInputMessage blanketProductionMasterInputMessage) {

		flowId = UPDATE_BPM;
		// assign the message to the class level variable.
		this.blanketProductionMasterInputMessage = blanketProductionMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return blanketProductionMasterOutputMessage;
	}

	@Override
	public BlanketProductionMasterOutputMessage deleteBlanketProductionMaster(
			BlanketProductionMasterInputMessage blanketProductionMasterInputMessage) {
		flowId = DELETE_BPM;
		// assign the message to the class level variable.
		this.blanketProductionMasterInputMessage = blanketProductionMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return blanketProductionMasterOutputMessage;

	}

	@Override
	public BlanketProductionMasterOutputMessage findBlanketProductionMasterById(
			BlanketProductionMasterInputMessage blanketProductionMasterInputMessage) {
		flowId = FIND_BPM_BY_ID;
		// assign the message to the class level variable.
		this.blanketProductionMasterInputMessage = blanketProductionMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return blanketProductionMasterOutputMessage;

	}

	@Override
	public BlanketProductionMasterOutputMessage findAllBlanketProductionMasters() {
		flowId = FIND_ALL_BPM;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return blanketProductionMasterOutputMessage;
	}

	@Override
	public BlanketProductionMasterOutputMessage search(
			BlanketProductionMasterInputMessage blanketProductionMasterInputMessage) {
		flowId = SEARCH_BPM;
		this.blanketProductionMasterInputMessage = blanketProductionMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return blanketProductionMasterOutputMessage;

	}

	@Override
	public BlanketProductionMasterOutputMessage createBlanketProductionLeft(
			BlanketProductionMasterInputMessage blanketProductionMasterInputMessage) {
		flowId = CREATE_BPL;
		this.blanketProductionMasterInputMessage = blanketProductionMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return blanketProductionMasterOutputMessage;
	}

	@Override
	public BlanketProductionMasterOutputMessage createBlanketProductionRight(
			BlanketProductionMasterInputMessage blanketProductionMasterInputMessage) {
		flowId = CREATE_BPR;
		this.blanketProductionMasterInputMessage = blanketProductionMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return blanketProductionMasterOutputMessage;
	}

	@Override
	public BlanketProductionMasterOutputMessage findBlanketProductionPagination(
			BlanketProductionMasterInputMessage blanketProductionMasterInputMessage) {
		flowId = FIND_BPM_PAGINATION;
		this.blanketProductionMasterInputMessage = blanketProductionMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return blanketProductionMasterOutputMessage;
	}

	@Override
	public BlanketProductionMasterOutputMessage getDataForShiftReport(
			BlanketProductionMasterInputMessage blanketProductionMasterInputMessage) {
		flowId = FIND_BPM_FOR_SHIFT_REPORT;
		this.blanketProductionMasterInputMessage = blanketProductionMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return blanketProductionMasterOutputMessage;
	}

	@Override
	public boolean validateInput() {

		if (CREATE_BPM.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_BPM.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_BPM.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_BPM_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_BPM.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_BPM.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		if (CREATE_BPL.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		if (CREATE_BPR.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		if (FIND_BPM_PAGINATION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		if (FIND_BPM_FOR_SHIFT_REPORT.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {

		BlanketProductionMasterEntity blanketProductionMasterEntity = new BlanketProductionMasterEntity();
		if (blanketProductionMasterInputMessage != null) {
			BlanketProductionMasterDTO blanketProductionMasterDTO = blanketProductionMasterInputMessage
					.getBlanketProductionMasterDTO();
			if (blanketProductionMasterDTO != null) {
				BeanUtils.copyProperties(blanketProductionMasterDTO,
						blanketProductionMasterEntity);
				if (blanketProductionMasterDTO.getGradeMasterDTO() != null) {
					MastersEntity gradeEntity = new MastersEntity();
					gradeEntity.setMastersId(blanketProductionMasterDTO
							.getGradeMasterDTO().getMastersId());
					blanketProductionMasterEntity
							.setGradeMasterEntity(gradeEntity);
				}
				if (blanketProductionMasterDTO.getShiftMasterDTO() != null) {
					MastersEntity shiftEntity = new MastersEntity();
					shiftEntity.setMastersId(blanketProductionMasterDTO
							.getShiftMasterDTO().getMastersId());
					blanketProductionMasterEntity
							.setShiftMasterEntity(shiftEntity);
				}
				List<BlanketProductionDetailLeftDTO> blanketProductionDetailLeftDTOList = blanketProductionMasterDTO
						.getBlanketProductionDetailLeftDTOList();
				if (blanketProductionDetailLeftDTOList != null
						&& blanketProductionDetailLeftDTOList.size() > 0) {
					List<BlanketProductionDetailLeftEntity> blanketProductionDetailLeftEntityList = new ArrayList<BlanketProductionDetailLeftEntity>();
					for (BlanketProductionDetailLeftDTO dto : blanketProductionDetailLeftDTOList) {
						BlanketProductionDetailLeftEntity entity = new BlanketProductionDetailLeftEntity();
						copyObject(dto, entity);
						entity.setModifiedUserId(blanketProductionMasterDTO
								.getModifiedUserId());
						blanketProductionDetailLeftEntityList.add(entity);
					}
					blanketProductionMasterEntity
							.setBlanketProductionDetailLeftEntityList(blanketProductionDetailLeftEntityList);
				}

				List<BlanketProductionDetailRightDTO> blanketProductionDetailRightDTOList = blanketProductionMasterDTO
						.getBlanketProductionDetailRightDTOList();
				if (blanketProductionDetailRightDTOList != null
						&& blanketProductionDetailRightDTOList.size() > 0) {
					List<BlanketProductionDetailRightEntity> blanketProductionDetailRightEntityList = new ArrayList<BlanketProductionDetailRightEntity>();
					for (BlanketProductionDetailRightDTO dto : blanketProductionDetailRightDTOList) {
						BlanketProductionDetailRightEntity entity = new BlanketProductionDetailRightEntity();
						copyObject(dto, entity);
						entity.setModifiedUserId(blanketProductionMasterDTO
								.getModifiedUserId());
						blanketProductionDetailRightEntityList.add(entity);
					}
					blanketProductionMasterEntity
							.setBlanketProductionDetailRightEntityList(blanketProductionDetailRightEntityList);
				}

			}
		}

		if (CREATE_BPM.equals(flowId)) {

			List<BlanketProductionMasterEntity> list = storageBlanketProductionMasterDAO
					.findByDateAndRunNo(blanketProductionMasterEntity
							.getBlanketProductionDate(),
							blanketProductionMasterEntity.getRunNo(),
							blanketProductionMasterEntity
									.getShiftMasterEntity().getMastersId(),
							blanketProductionMasterEntity.getBatchNumber(),
							null);

			blanketProductionMasterOutputMessage = new BlanketProductionMasterOutputMessage();
			if (list != null && list.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("1",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				blanketProductionMasterOutputMessage
						.setErrorListDTO(errorListDTO);
			} else {
				blanketProductionMasterOutputMessage.setErrorListDTO(null);
				blanketProductionMasterEntity
						.setBlanketProductionDetailLeftEntityList(null);
				blanketProductionMasterEntity
						.setBlanketProductionDetailRightEntityList(null);
				storageBlanketProductionMasterDAO
						.create(blanketProductionMasterEntity);
			}
		}
		if (CREATE_BPL.equals(flowId)) {
			BlanketProductionDetailLeftDTO blanketProductionDetailLeftDTO = blanketProductionMasterInputMessage
					.getBlanketProductionDetailLeftDTO();
			BlanketProductionDetailLeftEntity blanketProductionDetailLeftEntity = new BlanketProductionDetailLeftEntity();
			copyObject(blanketProductionDetailLeftDTO,
					blanketProductionDetailLeftEntity);
			storageBlanketProductionMasterDAO
					.createLeftBlanketProduction(blanketProductionDetailLeftEntity);
		}
		if (CREATE_BPR.equals(flowId)) {
			BlanketProductionDetailRightDTO blanketProductionDetailRightDTO = blanketProductionMasterInputMessage
					.getBlanketProductionDetailRightDTO();
			BlanketProductionDetailRightEntity blanketProductionDetailRightEntity = new BlanketProductionDetailRightEntity();
			copyObject(blanketProductionDetailRightDTO,
					blanketProductionDetailRightEntity);
			storageBlanketProductionMasterDAO
					.createRightBlanketProduction(blanketProductionDetailRightEntity);
		} else if (UPDATE_BPM.equals(flowId)) {
			// blanketProductionMasterEntity.setBlanketProductionDetailLeftEntityList(null);
			// blanketProductionMasterEntity.setBlanketProductionDetailRightEntityList(null);
			List<BlanketProductionMasterEntity> list = storageBlanketProductionMasterDAO
					.findByDateAndRunNo(blanketProductionMasterEntity
							.getBlanketProductionDate(),
							blanketProductionMasterEntity.getRunNo(),
							blanketProductionMasterEntity
									.getShiftMasterEntity().getMastersId(),
							blanketProductionMasterEntity.getBatchNumber(),
							null);

			blanketProductionMasterOutputMessage = new BlanketProductionMasterOutputMessage();
			if (list != null
					&& list.size() > 0
					&& !list.get(0)
							.getBlanketProductionId()
							.equals(blanketProductionMasterEntity
									.getBlanketProductionId())) {
				ErrorDTO errorDTO = new ErrorDTO("1",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				blanketProductionMasterOutputMessage
						.setErrorListDTO(errorListDTO);
			} else {
				BlanketProductionMasterEntity me = storageBlanketProductionMasterDAO
						.findById(
								blanketProductionMasterEntity
										.getBlanketProductionId()).get(0);
				List<BlanketProductionDetailLeftEntity> lde = me
						.getBlanketProductionDetailLeftEntityList();
				blanketProductionMasterEntity
						.getBlanketProductionDetailLeftEntityList().clear();
				blanketProductionMasterEntity
						.setBlanketProductionDetailLeftEntityList(lde);
				/*
				 * if(lde!=null && blanketProductionMasterEntity.
				 * getBlanketProductionDetailLeftEntityList()!=null ){ for(int
				 * i=0;i<lde.size();i++){ if(!blanketProductionMasterEntity.
				 * getBlanketProductionDetailLeftEntityList
				 * ().contains(lde.get(i))){ lde.get(i).setDeletedFlag(true);
				 * 
				 * blanketProductionMasterEntity.
				 * getBlanketProductionDetailLeftEntityList().add(i,
				 * lde.get(i)); }}}
				 */

				List<BlanketProductionDetailRightEntity> rde = me
						.getBlanketProductionDetailRightEntityList();
				blanketProductionMasterEntity
						.getBlanketProductionDetailRightEntityList().clear();
				blanketProductionMasterEntity
						.setBlanketProductionDetailRightEntityList(rde);
				/*
				 * if(rde!=null && blanketProductionMasterEntity.
				 * getBlanketProductionDetailRightEntityList()!=null ){ for(int
				 * i=0;i<rde.size();i++){ if(!blanketProductionMasterEntity.
				 * getBlanketProductionDetailRightEntityList
				 * ().contains(rde.get(i))){ rde.get(i).setDeletedFlag(true);
				 * blanketProductionMasterEntity
				 * .getBlanketProductionDetailRightEntityList().add(i,
				 * rde.get(i)); }}}
				 */

				storageBlanketProductionMasterDAO
						.update(blanketProductionMasterEntity);
			}
		} else if (DELETE_BPM.equals(flowId)) {
			storageBlanketProductionMasterDAO
					.delete(blanketProductionMasterEntity);
		} else if (FIND_BPM_BY_ID.equals(flowId)) {
			List<BlanketProductionMasterEntity> list = storageBlanketProductionMasterDAO
					.findById(blanketProductionMasterEntity
							.getBlanketProductionId());
			logger.info("BlanketProductionMasterEntity for id("
					+ blanketProductionMasterEntity.getBlanketProductionId()
					+ ") :" + list);
			popUpList(list);
		} else if (FIND_ALL_BPM.equals(flowId)) {

			List<BlanketProductionMasterEntity> list = storageBlanketProductionMasterDAO
					.load();
			popUpList(list);
		} else if (SEARCH_BPM.equals(flowId)) {
			BlanketProductionSearchCriteriaDTO searchCriteria = blanketProductionMasterInputMessage
					.getSearchCriteria();
			List<BlanketProductionMasterEntity> list = storageBlanketProductionMasterDAO
					.search(searchCriteria.getFromDate(),
							searchCriteria.getToDate(),
							searchCriteria.getRunNo(),
							searchCriteria.getGradeId());
			popUpList(list);
		} else if (FIND_BPM_PAGINATION.equals(flowId)) {
			// Before Load List cleane blanket production
			storageBlanketProductionMasterDAO.cleanDuplicateEntry();
			System.out
					.println("Calling before uploading complete blanket list list");
			List<BlanketProductionMasterEntity> list = storageBlanketProductionMasterDAO
					.findBlanketProductionPagination(blanketProductionMasterInputMessage
							.getNext());
			popUpList(list);
		} else if (FIND_BPM_FOR_SHIFT_REPORT.equals(flowId)) {
			BlanketProductionMasterDTO bpmDTO = blanketProductionMasterInputMessage
					.getBlanketProductionMasterDTO();
			List<BlanketProductionMasterEntity> list = storageBlanketProductionMasterDAO
					.getDataForShiftReport(bpmDTO.getBlanketProductionDate(),
							bpmDTO.getRunNo(), bpmDTO.getShiftMasterDTO()
									.getMastersId());
			popUpList(list);
		}
	}

	void popUpList(List<BlanketProductionMasterEntity> list) {
		logger.info("BPM Entity List  :" + list);
		blanketProductionMasterOutputMessage = new BlanketProductionMasterOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<BlanketProductionMasterDTO> resultList = new ArrayList<BlanketProductionMasterDTO>();
			BlanketProductionMasterDTO blanketProductionMasterDTO;
			for (BlanketProductionMasterEntity entity : list) {
				blanketProductionMasterDTO = new BlanketProductionMasterDTO();
				// Spring
				BeanUtils.copyProperties(entity, blanketProductionMasterDTO);
				if (entity.getShiftMasterEntity() != null) {
					MastersDTO shiftMasterDTO = new MastersDTO();
					BeanUtils.copyProperties(entity.getShiftMasterEntity(),
							shiftMasterDTO);
					blanketProductionMasterDTO
							.setShiftMasterDTO(shiftMasterDTO);
				}
				if (entity.getGradeMasterEntity() != null) {
					MastersDTO gradeMasterDTO = new MastersDTO();
					BeanUtils.copyProperties(entity.getGradeMasterEntity(),
							gradeMasterDTO);
					blanketProductionMasterDTO
							.setGradeMasterDTO(gradeMasterDTO);
				}

				List<BlanketProductionDetailLeftEntity> blanketProductionDetailLeftEntityList = entity
						.getBlanketProductionDetailLeftEntityList();
				List<BlanketProductionDetailLeftDTO> blanketProductionDetailLeftDTOList = new ArrayList<BlanketProductionDetailLeftDTO>();
				if (blanketProductionDetailLeftEntityList != null
						&& blanketProductionDetailLeftEntityList.size() > 0) {
					for (BlanketProductionDetailLeftEntity blanketProductionDetailLeftEntity : blanketProductionDetailLeftEntityList) {
						if (blanketProductionDetailLeftEntity.getDeletedFlag())
							continue;
						BlanketProductionDetailLeftDTO blanketProductionDetailLeftDTO = new BlanketProductionDetailLeftDTO();
						copyObject(blanketProductionDetailLeftEntity,
								blanketProductionDetailLeftDTO);
						blanketProductionDetailLeftDTOList
								.add(blanketProductionDetailLeftDTO);
					}
				}
				blanketProductionMasterDTO
						.setBlanketProductionDetailLeftDTOList(blanketProductionDetailLeftDTOList);

				List<BlanketProductionDetailRightEntity> blanketProductionDetailRightEntityList = entity
						.getBlanketProductionDetailRightEntityList();
				List<BlanketProductionDetailRightDTO> blanketProductionDetailRightDTOList = new ArrayList<BlanketProductionDetailRightDTO>();
				if (blanketProductionDetailRightEntityList != null
						&& blanketProductionDetailRightEntityList.size() > 0) {
					for (BlanketProductionDetailRightEntity blanketProductionDetailRightEntity : blanketProductionDetailRightEntityList) {
						if (blanketProductionDetailRightEntity.getDeletedFlag())
							continue;
						BlanketProductionDetailRightDTO blanketProductionDetailRightDTO = new BlanketProductionDetailRightDTO();
						copyObject(blanketProductionDetailRightEntity,
								blanketProductionDetailRightDTO);
						blanketProductionDetailRightDTOList
								.add(blanketProductionDetailRightDTO);
					}
				}
				blanketProductionMasterDTO
						.setBlanketProductionDetailRightDTOList(blanketProductionDetailRightDTOList);

				resultList.add(blanketProductionMasterDTO);
			}
			blanketProductionMasterOutputMessage
					.setBlanketProductionMasterDTOList(resultList);
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
	public void formatOutput() {

		if (CREATE_BPM.equals(flowId)) {

		} else if (UPDATE_BPM.equals(flowId)) {

		} else if (DELETE_BPM.equals(flowId)) {

		} else if (FIND_BPM_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_BPM.equals(flowId)) {

		} else if (SEARCH_BPM.equals(flowId)) {

		}
	}

	@Override
	public Integer getMaxBlanketProdId() {
		Integer blanketProdId = storageBlanketProductionMasterDAO
				.getMaxBlanketProdId();
		return blanketProdId;
	}

	@Override
	public String checkDuplicateRecordInBPMaster(
			BlanketProductionMasterDTO blanketProductionMasterDTO) {
		String flag = null;
		List list = storageBlanketProductionMasterDAO.findByDateAndRunNo(
				blanketProductionMasterDTO.getBlanketProductionDate(),
				blanketProductionMasterDTO.getRunNo(),
				blanketProductionMasterDTO.getShiftMasterDTO().getMastersId(),
				blanketProductionMasterDTO.getBatchNumber(), null);
		if (list != null && list.size() > 0) {
			flag = "Duplicate";
		}
		return flag;
	}

	@Override
	public ArrayList<Integer> getMaxRollNoL(String batchNo) {
		return storageBlanketProductionMasterDAO.getMaxRollNoL(batchNo);
	}

	@Override
	public ArrayList<Integer> getMaxRollNoR(String batchNo) {
		return storageBlanketProductionMasterDAO.getMaxRollNoR(batchNo);
	}

	@Override
	public Boolean deleteLeftBlanketProduction(
			BlanketProductionDetailLeftDTO dto) {
		BlanketProductionDetailLeftEntity blanketProductionDetailLefttEntity = new BlanketProductionDetailLeftEntity();
		copyObject(dto, blanketProductionDetailLefttEntity);
		return storageBlanketProductionMasterDAO
				.deleteLeftBlanketProduction(blanketProductionDetailLefttEntity);

	}

	@Override
	public Boolean deleteRightBlanketProduction(
			BlanketProductionDetailRightDTO dto) {
		BlanketProductionDetailRightEntity blanketProductionDetailRightEntity = new BlanketProductionDetailRightEntity();
		copyObject(dto, blanketProductionDetailRightEntity);
		return storageBlanketProductionMasterDAO
				.deleteRightBlanketProduction(blanketProductionDetailRightEntity);

	}

	@Override
	public List getBlanketProductionLeft(Date date, Integer shiftId) {
		List list = storageBlanketProductionMasterDAO.getBlanketProductionLeft(
				date, shiftId);
		ArrayList<BlanketProductionDetailLeftDTO> arrayList = new ArrayList<BlanketProductionDetailLeftDTO>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				BlanketProductionDetailLeftDTO leftDTO = new BlanketProductionDetailLeftDTO();
				Object[] obj = (Object[]) list.get(i);
				Integer sno = (Integer) obj[0];
				String runNo = (String) obj[1];
				// for date
				String shift = (String) obj[3];
				String batchNo = (String) obj[4];
				Integer rollNo = (Integer) obj[5];
				String blanketType = (String) obj[6];
				String statusLeft = (String) obj[7];

				leftDTO.setSno(sno);
				leftDTO.setRunNo(runNo);
				leftDTO.setShift(shift);
				leftDTO.setBatchNo(batchNo);
				leftDTO.setBlanketType(blanketType);
				leftDTO.setRollNoLeft(rollNo);
				leftDTO.setStatusLeft(statusLeft);
				arrayList.add(leftDTO);

			}
		}

		return arrayList;
	}

	@Override
	public List getBlanketProductionRight(Date date, Integer shiftId) {
		List list = storageBlanketProductionMasterDAO
				.getBlanketProductionRight(date, shiftId);
		ArrayList<BlanketProductionDetailRightDTO> arrayList = new ArrayList<BlanketProductionDetailRightDTO>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				BlanketProductionDetailRightDTO rightDTO = new BlanketProductionDetailRightDTO();
				Object[] obj = (Object[]) list.get(i);
				Integer sno = (Integer) obj[0];
				String runNo = (String) obj[1];
				// for date
				String shift = (String) obj[3];
				String batchNo = (String) obj[4];
				Integer rollNo = (Integer) obj[5];
				String blanketType = (String) obj[6];
				String statusRight = (String) obj[7];

				rightDTO.setSno(sno);
				rightDTO.setRunNo(runNo);
				rightDTO.setShift(shift);
				rightDTO.setBatchNo(batchNo);
				rightDTO.setBlanketType(blanketType);
				rightDTO.setRollNoRight(rollNo);
				rightDTO.setStatusRight(statusRight);
				arrayList.add(rightDTO);
			}
		}

		return arrayList;
	}

	@Override
	public List getRejBlanketProductionLeftList(String status, Date date,
			Integer shiftId) {
		List<BlanketProductionDetailLeftEntity> list = null;
		try {
			list = storageBlanketProductionMasterDAO
					.getRejBlanketProductionLeft(status, date, shiftId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<BlanketProductionDetailLeftDTO> leftList = null;
		try {
			leftList = new ArrayList<BlanketProductionDetailLeftDTO>();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (BlanketProductionDetailLeftEntity entity : list) {
			BlanketProductionDetailLeftDTO leftDTO = new BlanketProductionDetailLeftDTO();
			BeanUtils.copyProperties(entity, leftDTO);
			leftList.add(leftDTO);
		}

		return leftList;
	}

	@Override
	public List getRejBlanketProductionRightList(String status, Date date,
			Integer shiftId) {
		List<BlanketProductionDetailRightEntity> list = storageBlanketProductionMasterDAO
				.getRejBlanketProductionRight(status, date, shiftId);

		List<BlanketProductionDetailRightDTO> rightList = new ArrayList<BlanketProductionDetailRightDTO>();
		for (BlanketProductionDetailRightEntity entity : list) {
			BlanketProductionDetailRightDTO rightDTO = new BlanketProductionDetailRightDTO();
			BeanUtils.copyProperties(entity, rightDTO);
			rightList.add(rightDTO);
		}
		return rightList;
	}

	@Override
	public List updateBlanketProductionLeftRight(
			List<BlanketProductionDetailLeftDTO> left,
			List<BlanketProductionDetailRightDTO> right, Integer userId) {
		try {
			if (left != null && left.size() > 0) {
				for (int i = 0; i < left.size(); i++) {
					BlanketProductionDetailLeftDTO pbl = left.get(i);
					BlanketProductionDetailLeftEntity leftEntity = new BlanketProductionDetailLeftEntity();
					leftEntity.setSno(pbl.getSno());
					leftEntity.setStatusLeft(pbl.getStatusLeft());
					leftEntity.setRaUserId(userId);
					storageBlanketProductionMasterDAO
							.updateBlanketProductionLeft(leftEntity, "R.A.");

				}
			}
		} catch (Exception e) {
		}

		try {
			if (right != null && right.size() > 0) {
				for (int i = 0; i < right.size(); i++) {
					BlanketProductionDetailRightDTO pbr = right.get(i);
					BlanketProductionDetailRightEntity rightEntity = new BlanketProductionDetailRightEntity();
					rightEntity.setSno(pbr.getSno());
					rightEntity.setStatusRight(pbr.getStatusRight());
					rightEntity.setRaUserId(userId);
					storageBlanketProductionMasterDAO
							.updateBlanketProductionRight(rightEntity, "R.A.");

				}
			}
		} catch (Exception e) {
		}

		return null;
	}

	@Override
	public List updateApprovedBlanketProduction(
			List<BlanketProductionDetailLeftDTO> left,
			List<BlanketProductionDetailRightDTO> right, Integer userId,
			FinishedGoodsMasterDTO finishedGoodsMasterDTO) {
		List<FinishedGoodsDetailEntity> fDetailList = null;
		String finishedGoodNumber = null;
		Integer finishedGoodAutoId = null;
		List<FinishedGoodsMasterEntity> list = null;

		try {
			list = finishedGoodsMasterDAO.findByFinishGoodNumber(null,
					finishedGoodsMasterDTO.getFinishGoodDate());
			if (list != null && list.size() > 0) {
				FinishedGoodsMasterEntity mastreEntity = list.get(0);
				fDetailList = mastreEntity.getFinishedGoodsDetailEntities();
				finishedGoodNumber = mastreEntity.getFinishedGoodsNumber();
				finishedGoodAutoId = mastreEntity.getFinishedGoodsAutoId();
			} else {
				fDetailList = new ArrayList<FinishedGoodsDetailEntity>();
			}
		} catch (Exception e) {
		}

		try {
			if (left != null && left.size() > 0) {

				for (int j = 0; j < left.size(); j++) {

					BlanketProductionDetailLeftDTO pbl = left.get(j);
					BlanketProductionDetailLeftEntity leftEntity = new BlanketProductionDetailLeftEntity();
					leftEntity.setSno(pbl.getSno());
					leftEntity.setApprovedUserId(userId);
					if (pbl.getApprovedStatus() != null
							&& pbl.getApprovedStatus() > 0) {
						leftEntity.setApprovedStatus(pbl.getApprovedStatus());
					} else {
						leftEntity.setApprovedStatus(0);
					}

					if (pbl.getCheckRejection() != null
							&& pbl.getCheckRejection() > 0) {
						leftEntity.setStatusLeft("Rejection");
						leftEntity.setApprovedStatus(0);
					} else {
						leftEntity.setStatusLeft("OK");
					}

					try {
						System.out.println("STATUS IS........."
								+ pbl.getApprovedStatus()
								+ " FINISHED GOODS NUMBERS................ "
								+ finishedGoodsMasterDTO
										.getFinishedGoodsNumber());
						if (pbl.getApprovedStatus()!=null && pbl.getApprovedStatus() == 1) {
							leftEntity.setFinishedGood(finishedGoodsMasterDTO
									.getFinishedGoodsNumber());
							FinishedGoodsDetailEntity fgdetailEntity = new FinishedGoodsDetailEntity();

							fgdetailEntity = new FinishedGoodsDetailEntity();
							ItemEntity itemEntity = new ItemEntity();
							itemEntity.setItemId(pbl.getItemId());
							fgdetailEntity.setQuantity(1.0);
							fgdetailEntity.setItemEntity(itemEntity);
							fgdetailEntity.setEntryStatus("Auto");

							if (list != null && list.size() > 0) {
								fgdetailEntity
										.setFinishedGoodsNumber(finishedGoodNumber);
								fgdetailEntity
										.setFinishAutoId(finishedGoodAutoId);

							} else {
								fgdetailEntity
										.setFinishedGoodsNumber(finishedGoodsMasterDTO
												.getFinishedGoodsNumber());
							}

							fgdetailEntity
									.setTransactionSeries(finishedGoodsMasterDTO
											.getTransactionSeries());

							fDetailList.add(fgdetailEntity);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					storageBlanketProductionMasterDAO
							.updateBlanketProductionLeft(leftEntity, "Approved");
				}
			}
		} catch (Exception e) {
		}

		try {
			if (right != null && right.size() > 0) {
				for (int i = 0; i < right.size(); i++) {
					BlanketProductionDetailRightDTO pbr = right.get(i);
					BlanketProductionDetailRightEntity rightEntity = new BlanketProductionDetailRightEntity();
					rightEntity.setSno(pbr.getSno());
					rightEntity.setApprovedUserId(userId);
					if (pbr.getApprovedStatus() != null
							&& pbr.getApprovedStatus() > 0) {
						rightEntity.setApprovedStatus(pbr.getApprovedStatus());
					} else {
						rightEntity.setApprovedStatus(0);
					}
					if (pbr.getCheckRejection() != null
							&& pbr.getCheckRejection() > 0) {
						rightEntity.setStatusRight("Rejection");
						rightEntity.setApprovedStatus(0);
					} else {
						rightEntity.setStatusRight("OK");
					}

					try {
						if (pbr.getApprovedStatus() != null && pbr.getApprovedStatus() == 1) {
							rightEntity.setFinishedGood(finishedGoodsMasterDTO
									.getFinishedGoodsNumber());
							FinishedGoodsDetailEntity fgdetailEntity = new FinishedGoodsDetailEntity();

							ItemEntity itemEntity = new ItemEntity();
							itemEntity.setItemId(pbr.getItemId());
							fgdetailEntity.setQuantity(1.0);
							fgdetailEntity.setItemEntity(itemEntity);
							fgdetailEntity.setEntryStatus("Auto");
							/*
							 * try{ MastersEntity mentity=new MastersEntity();
							 * List<ItemEntity> itemList=
							 * itemDAO.findById(pbr.getItemId()); ItemEntity
							 * ien=itemList.get(0);
							 * mentity=ien.getMasterUnitEntity();
							 * fgdetailEntity.
							 * setMeasurementUnitMasterEntity(mentity); }catch
							 * (Exception e) { }
							 */

							if (list != null && list.size() > 0) {
								fgdetailEntity
										.setFinishedGoodsNumber(finishedGoodNumber);
								fgdetailEntity
										.setFinishAutoId(finishedGoodAutoId);
							} else {
								fgdetailEntity
										.setFinishedGoodsNumber(finishedGoodsMasterDTO
												.getFinishedGoodsNumber());
							}

							fgdetailEntity
									.setTransactionSeries(finishedGoodsMasterDTO
											.getTransactionSeries());
							fDetailList.add(fgdetailEntity);

						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					storageBlanketProductionMasterDAO
							.updateBlanketProductionRight(rightEntity,
									"Approved");

				}
			}
		} catch (Exception e) {
		}
		// ......To prevent insert duplicate items
		Map<Integer, FinishedGoodsDetailEntity> mp = new HashMap<Integer, FinishedGoodsDetailEntity>();

		for (int i = 0; i < fDetailList.size(); i++) {
			FinishedGoodsDetailEntity detailEntity = fDetailList.get(i);
			if (mp.containsKey(detailEntity.getItemEntity().getItemId())) {
				detailEntity = mp.get(detailEntity.getItemEntity().getItemId());
				Double d = detailEntity.getQuantity();
				d = d + 1;
				detailEntity.setQuantity(d);

				try {
					MastersEntity mentity = new MastersEntity();
					List<ItemEntity> itemList = itemDAO.findById(detailEntity
							.getItemEntity().getItemId());
					ItemEntity ien = itemList.get(0);
					mentity = ien.getMasterUnitEntity();
					detailEntity.setMeasurementUnitMasterEntity(mentity);
				} catch (Exception e) {
				}

				mp.put(detailEntity.getItemEntity().getItemId(), detailEntity);
			} else {

				try {
					MastersEntity mentity = new MastersEntity();
					List<ItemEntity> itemList = itemDAO.findById(detailEntity
							.getItemEntity().getItemId());
					ItemEntity ien = itemList.get(0);
					mentity = ien.getMasterUnitEntity();
					detailEntity.setMeasurementUnitMasterEntity(mentity);
				} catch (Exception e) {
				}

				mp.put(detailEntity.getItemEntity().getItemId(), detailEntity);
			}
		}
		// ..end...To prevent insert duplicate items.......
		List<FinishedGoodsDetailEntity> finishedGoodDetailEntityList = new ArrayList<FinishedGoodsDetailEntity>(
				mp.values());

		try {
			FinishedGoodsMasterEntity finishedGoodsMasterEntity = new FinishedGoodsMasterEntity();

			finishedGoodsMasterEntity
					.setTransactionSeries(finishedGoodsMasterDTO
							.getTransactionSeries());
			finishedGoodsMasterEntity.setFinishGoodDate(finishedGoodsMasterDTO
					.getFinishGoodDate());

			finishedGoodsMasterEntity.setFinishGoodId(finishedGoodsMasterDTO
					.getFinishGoodId());
			finishedGoodsMasterEntity
					.setFinishedGoodsNumber(finishedGoodsMasterDTO
							.getFinishedGoodsNumber());
			finishedGoodsMasterEntity.setFinYear(finishedGoodsMasterDTO
					.getFinYear());
			BranchEntity branchEntity = new BranchEntity();
			branchEntity.setBranchId(1);
			finishedGoodsMasterEntity.setBranchEntity(branchEntity);
			finishedGoodsMasterEntity
					.setFinishedGoodsDetailEntities(finishedGoodDetailEntityList);

			// TO clean blanket production left and right entries
			storageBlanketProductionMasterDAO.cleanDuplicateEntry();
			System.out
					.println("Calling after approving and before inserting in finished good");
			// List<FinishedGoodsMasterEntity>list=finishedGoodsMasterDAO.findByFinishGoodNumber(null,
			// finishedGoodsMasterDTO.getFinishGoodDate());
			// To check duplicate finished good...........
			if (list != null && list.size() > 0) {
				FinishedGoodsMasterEntity fMasterEntity = list.get(0);
				fMasterEntity.setModifiedUserId(userId);
				fMasterEntity
						.setFinishedGoodsDetailEntities(finishedGoodDetailEntityList);
				if (finishedGoodDetailEntityList != null
						&& finishedGoodDetailEntityList.size() > 0) {
					finishedGoodsMasterDAO.update(fMasterEntity);
				}
			} else {

				if (finishedGoodDetailEntityList != null
						&& finishedGoodDetailEntityList.size() > 0) {
					finishedGoodsMasterEntity.setCreatedUserId(userId);
					finishedGoodsMasterDAO.create(finishedGoodsMasterEntity);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// End Finish good entry form

		return null;
	}

	@Override
	public List updateRejectedBlanketProductionLeftRight(
			List<BlanketProductionDetailLeftDTO> left,
			List<BlanketProductionDetailRightDTO> right, Integer userId) {
		try {
			if (left != null && left.size() > 0) {
				for (int i = 0; i < left.size(); i++) {
					BlanketProductionDetailLeftDTO pbl = left.get(i);
					BlanketProductionDetailLeftEntity leftEntity = new BlanketProductionDetailLeftEntity();
					copyObject(pbl, leftEntity);
					leftEntity.setSno(pbl.getSno());
					leftEntity.setRejectedUserId(userId);

					if (pbl.getRejItemId() != null) {
						ItemDTO itemDTO = new ItemDTO();
						itemDTO.setItemId(pbl.getRejItemId());
						ItemInputMessage inputMessage = new ItemInputMessage();
						inputMessage.setItemDTO(itemDTO);
						ItemOutMessage itemOutMessage = itemService
								.findItemById(inputMessage);
						List<ItemDTO> list = itemOutMessage.getItemDTOList();

						if (list != null && list.size() > 0) {
							itemDTO = list.get(0);

							leftEntity.setRejDensityl(itemDTO.getItemDensity());
							leftEntity.setRejItemId(itemDTO.getItemId());
							leftEntity.setRejLengthl(itemDTO.getItemLength());
							leftEntity.setRejThickl(itemDTO.getItemHeight());
							leftEntity.setRejWeightl(itemDTO.getGrossWeight());
							leftEntity.setRejWidthl(itemDTO.getItemWidth());
						}
					}

					leftEntity.setStatusLeft(pbl.getStatusLeft());
					storageBlanketProductionMasterDAO
							.updateBlanketProductionLeft(leftEntity,
									"updateRejection");

				}
			}
		} catch (Exception e) {
		}

		try {
			if (right != null && right.size() > 0) {
				for (int i = 0; i < right.size(); i++) {
					BlanketProductionDetailRightDTO pbr = right.get(i);
					BlanketProductionDetailRightEntity rightEntity = new BlanketProductionDetailRightEntity();
					copyObject(pbr, rightEntity);
					rightEntity.setSno(pbr.getSno());
					rightEntity.setRejectedUserId(userId);

					if (pbr.getRejItemId() != null) {
						ItemDTO itemDTO = new ItemDTO();
						itemDTO.setItemId(pbr.getRejItemId());
						ItemInputMessage inputMessage = new ItemInputMessage();
						inputMessage.setItemDTO(itemDTO);
						ItemOutMessage itemOutMessage = itemService
								.findItemById(inputMessage);
						List<ItemDTO> list = itemOutMessage.getItemDTOList();

						if (list != null && list.size() > 0) {
							itemDTO = list.get(0);
							rightEntity
									.setRejDensityr(itemDTO.getItemDensity());
							rightEntity.setRejItemId(itemDTO.getItemId());
							rightEntity.setRejLengthr(itemDTO.getItemLength());
							rightEntity.setRejThickr(itemDTO.getItemHeight());
							rightEntity.setRejWeightr(itemDTO.getGrossWeight());
							rightEntity.setRejWidthr(itemDTO.getItemWidth());
						}
					}

					rightEntity.setStatusRight(pbr.getStatusRight());
					storageBlanketProductionMasterDAO
							.updateBlanketProductionRight(rightEntity,
									"updateRejection");

				}
			}
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public List getCheckDuplicatRollNoL(String batchNo, Integer rollNoL) {
		return storageBlanketProductionMasterDAO.getCheckDuplicatRollNoL(
				batchNo, rollNoL);
	}

	@Override
	public List getCheckDuplicatRollNoR(String batchNo, Integer rollNoR) {
		return storageBlanketProductionMasterDAO.getCheckDuplicatRollNoR(
				batchNo, rollNoR);
	}

	@Override
	public void updateBlanketProduction(Date date, String runNo,
			Integer shiftName, String batchNo, Integer status) {
		storageBlanketProductionMasterDAO.updateBlanket(date, runNo, shiftName,
				status);
	}

	@Override
	public Boolean checkFinishedGood(String finishedGoodNumber) {
		// TODO Auto-generated method stub
		return storageBlanketProductionMasterDAO
				.checkFinishedGood(finishedGoodNumber);
	}

	@Override
	public Double getBlanketWeightRecord(Character position,
			Integer createdUserId) {
		Double amt = 0.0;
		try {
			amt = storageBlanketProductionMasterDAO.getBlanketWeightRecord(
					position, createdUserId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return amt;

	}

	@Override
	public Timestamp getLastBlanketEntryDate() {
		Timestamp date = zoneService.getFirstDayOfFinYear();
		List list = storageBlanketProductionMasterDAO.getLastBlanketEntryDate();
		if (list != null && list.size() > 0) {
			if (list.get(0) != null)
				date = new Timestamp(((Date) list.get(0)).getTime());
		}
		return date;
	}
}