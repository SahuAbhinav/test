package com.advanz.erp.masters.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.BarcodeLedgerEntity;
import com.advanz.erp.masters.entity.jpa.BlanketProductionDetailNewEntity;
import com.advanz.erp.masters.entity.jpa.BlanketProductionMasterNewEntity;
import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.entity.jpa.FinishedGoodsDetailEntity;
import com.advanz.erp.masters.entity.jpa.FinishedGoodsMasterEntity;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.model.BarcodeLedgerDTO;
import com.advanz.erp.masters.model.BlanketProductionDetailNewDTO;
import com.advanz.erp.masters.model.BlanketProductionMasterNewDTO;
import com.advanz.erp.masters.model.FinishedGoodsMasterDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.criteria.BlanketProductionSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.BarcodeLedgerInputMessage;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterNewInputMessage;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterNewOutputMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.service.business.IBarcodeLedgerService;
import com.advanz.erp.masters.service.business.IBlanketProductionMasterNewService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageBarcodeLedgerDAO;
import com.advanz.erp.masters.storage.IStorageBlanketProductionMasterNewDAO;
import com.advanz.erp.masters.storage.IStorageFinishedGoodsMasterDAO;
import com.advanz.erp.masters.storage.IStorageItemDAO;

@Service
public class BlanketProductionMasterNewServiceImpl implements
		IBlanketProductionMasterNewService {
	private static final Logger logger = LoggerFactory
			.getLogger(BlanketProductionMasterNewServiceImpl.class);

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
	public IStorageBlanketProductionMasterNewDAO storageBlanketProductionMasterNewDAO;

	public BlanketProductionMasterNewInputMessage blanketProductionMasterNewInputMessage;

	public BlanketProductionMasterNewOutputMessage blanketProductionMasterNewOutputMessage;

	@Autowired
	public IZoneService zoneService;

	@Autowired
	public IStorageFinishedGoodsMasterDAO finishedGoodsMasterDAO;
	
	@Autowired
	public IBarcodeLedgerService barcodeLedgerService;

	@Override
	public BlanketProductionMasterNewOutputMessage createBlanketProductionMaster(
			BlanketProductionMasterNewInputMessage blanketProductionMasterInputMessage) {

		flowId = CREATE_BPM;
		// assign the message to the class level variable.
		this.blanketProductionMasterNewInputMessage = blanketProductionMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return blanketProductionMasterNewOutputMessage;
	}

	@Override
	public BlanketProductionMasterNewOutputMessage updateBlanketProductionMaster(
			BlanketProductionMasterNewInputMessage blanketProductionMasterInputMessage) {

		flowId = UPDATE_BPM;
		// assign the message to the class level variable.
		this.blanketProductionMasterNewInputMessage = blanketProductionMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return blanketProductionMasterNewOutputMessage;
	}

	@Override
	public BlanketProductionMasterNewOutputMessage deleteBlanketProductionMaster(
			BlanketProductionMasterNewInputMessage blanketProductionMasterInputMessage) {
		flowId = DELETE_BPM;
		// assign the message to the class level variable.
		this.blanketProductionMasterNewInputMessage = blanketProductionMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return blanketProductionMasterNewOutputMessage;

	}

	@Override
	public BlanketProductionMasterNewOutputMessage findBlanketProductionMasterById(
			BlanketProductionMasterNewInputMessage blanketProductionMasterInputMessage) {
		flowId = FIND_BPM_BY_ID;
		// assign the message to the class level variable.
		this.blanketProductionMasterNewInputMessage = blanketProductionMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return blanketProductionMasterNewOutputMessage;

	}

	@Override
	public BlanketProductionMasterNewOutputMessage findAllBlanketProductionMasters() {
		flowId = FIND_ALL_BPM;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return blanketProductionMasterNewOutputMessage;
	}

	@Override
	public BlanketProductionMasterNewOutputMessage search(
			BlanketProductionMasterNewInputMessage blanketProductionMasterInputMessage) {
		flowId = SEARCH_BPM;
		this.blanketProductionMasterNewInputMessage = blanketProductionMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return blanketProductionMasterNewOutputMessage;

	}

	@Override
	public BlanketProductionMasterNewOutputMessage createBlanketProductionDetail(
			BlanketProductionMasterNewInputMessage blanketProductionMasterInputMessage) {
		flowId = CREATE_BPL;
		this.blanketProductionMasterNewInputMessage = blanketProductionMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return blanketProductionMasterNewOutputMessage;
	}


	@Override
	public BlanketProductionMasterNewOutputMessage findBlanketProductionPagination(
			BlanketProductionMasterNewInputMessage blanketProductionMasterInputMessage) {
		flowId = FIND_BPM_PAGINATION;
		this.blanketProductionMasterNewInputMessage = blanketProductionMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return blanketProductionMasterNewOutputMessage;
	}

	@Override
	public BlanketProductionMasterNewOutputMessage getDataForShiftReport(
			BlanketProductionMasterNewInputMessage blanketProductionMasterInputMessage) {
		flowId = FIND_BPM_FOR_SHIFT_REPORT;
		this.blanketProductionMasterNewInputMessage = blanketProductionMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return blanketProductionMasterNewOutputMessage;
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

		BlanketProductionMasterNewEntity blanketProductionMasterNewEntity = new BlanketProductionMasterNewEntity();
		if (blanketProductionMasterNewInputMessage != null) {
			BlanketProductionMasterNewDTO blanketProductionMasterNewDTO = blanketProductionMasterNewInputMessage
					.getBlanketProductionMasterNewDTO();
			System.out.println("blanketProductionMasterNewDTO:---------"+blanketProductionMasterNewDTO);
			if (blanketProductionMasterNewDTO != null) {
				BeanUtils.copyProperties(blanketProductionMasterNewDTO,
						blanketProductionMasterNewEntity);
				if (blanketProductionMasterNewDTO.getGradeMasterDTO() != null) {
					MastersEntity gradeEntity = new MastersEntity();
					gradeEntity.setMastersId(blanketProductionMasterNewDTO
							.getGradeMasterDTO().getMastersId());
					blanketProductionMasterNewEntity
							.setGradeMasterEntity(gradeEntity);
				}
				if (blanketProductionMasterNewDTO.getShiftMasterDTO() != null) {
					MastersEntity shiftEntity = new MastersEntity();
					shiftEntity.setMastersId(blanketProductionMasterNewDTO
							.getShiftMasterDTO().getMastersId());
					blanketProductionMasterNewEntity
							.setShiftMasterEntity(shiftEntity);
				}
				List<BlanketProductionDetailNewDTO> blanketProductionDetailNewDTOList = blanketProductionMasterNewDTO
						.getBlanketProductionDetailNewDTOList();
				if (blanketProductionDetailNewDTOList != null
						&& blanketProductionDetailNewDTOList.size() > 0) {
					List<BlanketProductionDetailNewEntity> blanketProductionDetailLeftEntityList = new ArrayList<BlanketProductionDetailNewEntity>();
					for (BlanketProductionDetailNewDTO dto : blanketProductionDetailNewDTOList) {
						BlanketProductionDetailNewEntity entity = new BlanketProductionDetailNewEntity();
						copyObject(dto, entity);
						entity.setModifiedUserId(blanketProductionMasterNewDTO
								.getModifiedUserId());
						blanketProductionDetailLeftEntityList.add(entity);
					}
					blanketProductionMasterNewEntity
							.setBlanketProductionDetailNewEntityList(blanketProductionDetailLeftEntityList);
				}


			}
		}

		if (CREATE_BPM.equals(flowId)) {

			List<BlanketProductionMasterNewEntity> list = storageBlanketProductionMasterNewDAO
					.findByDateAndRunNo(blanketProductionMasterNewEntity
							.getBlanketProductionDate(),
							blanketProductionMasterNewEntity.getRunNo(),
							blanketProductionMasterNewEntity
									.getShiftMasterEntity().getMastersId(),
							blanketProductionMasterNewEntity.getBatchNumber(),
							null);

			blanketProductionMasterNewOutputMessage = new BlanketProductionMasterNewOutputMessage();
			if (list != null && list.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("1",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				blanketProductionMasterNewOutputMessage
						.setErrorListDTO(errorListDTO);
			} else {
				blanketProductionMasterNewOutputMessage.setErrorListDTO(null);
				blanketProductionMasterNewEntity
						.setBlanketProductionDetailNewEntityList(null);
				storageBlanketProductionMasterNewDAO
						.create(blanketProductionMasterNewEntity);
			}
		}
		if (CREATE_BPL.equals(flowId)) {
			BlanketProductionDetailNewDTO blanketProductionDetailLeftDTO = blanketProductionMasterNewInputMessage
					.getBlanketProductionDetailNewDTO();
			BlanketProductionDetailNewEntity blanketProductionDetailLeftEntity = new BlanketProductionDetailNewEntity();
			copyObject(blanketProductionDetailLeftDTO,
					blanketProductionDetailLeftEntity);
			storageBlanketProductionMasterNewDAO
					.createBlanketProductionDetail(blanketProductionDetailLeftEntity);
			System.out.println("blanketProductionMasterNewEntity"+blanketProductionMasterNewEntity);
			try{
				int sno;
				if(blanketProductionDetailLeftEntity.getBlanketType().equalsIgnoreCase("OK")){
				BarcodeLedgerDTO barcodeLedgerDTO = new BarcodeLedgerDTO();
				BarcodeLedgerInputMessage barcodeLedgerInputMessage= new BarcodeLedgerInputMessage();
				barcodeLedgerDTO.setItemId(blanketProductionDetailLeftEntity.getItemId());
				//System.out.println("blanketProductionDetailLeftEntity.getSno()"+blanketProductionDetailLeftEntity.getSno());
				if(blanketProductionDetailLeftEntity.getSno()==null || blanketProductionDetailLeftEntity.getSno()==0)
				sno=	storageBlanketProductionMasterNewDAO.getMaxDetailId(blanketProductionDetailLeftEntity.getBlanketProductionId());
				else
					sno=blanketProductionDetailLeftEntity.getSno();
				//System.out.println("sno"+sno);
				barcodeLedgerDTO.setTransactionId(sno);
				barcodeLedgerDTO.setTransactionType(BarcodeLedgerDTO.BLANKET);
				barcodeLedgerDTO.setBarcode(blanketProductionMasterNewEntity.getBatchNumber()+""+blanketProductionDetailLeftEntity.getRollNo());
				barcodeLedgerInputMessage.setBarcodeLedgerDTO(barcodeLedgerDTO);
				barcodeLedgerDTO.setCreatedUserId(blanketProductionMasterNewEntity.getCreatedUserId());
				barcodeLedgerDTO.setModifiedUserId(blanketProductionMasterNewEntity.getCreatedUserId());
				barcodeLedgerService.createBarcode(barcodeLedgerInputMessage);
				}
			}catch(Exception e){
				e.printStackTrace();
				logger.error(this.getClass().getName(),e);
			}
		}
		if (CREATE_BPR.equals(flowId)) {
			BlanketProductionDetailNewDTO blanketProductionDetailRightDTO = blanketProductionMasterNewInputMessage
					.getBlanketProductionDetailNewDTO();
			BlanketProductionDetailNewEntity blanketProductionDetailRightEntity = new BlanketProductionDetailNewEntity();
			copyObject(blanketProductionDetailRightDTO,
					blanketProductionDetailRightEntity);
			storageBlanketProductionMasterNewDAO
					.createBlanketProductionDetail(blanketProductionDetailRightEntity);
		} else if (UPDATE_BPM.equals(flowId)) {
			// blanketProductionMasterEntity.setBlanketProductionDetailNewEntityList(null);
			// blanketProductionMasterEntity.setBlanketProductionDetailRightEntityList(null);
			List<BlanketProductionMasterNewEntity> list = storageBlanketProductionMasterNewDAO
					.findByDateAndRunNo(blanketProductionMasterNewEntity
							.getBlanketProductionDate(),
							blanketProductionMasterNewEntity.getRunNo(),
							blanketProductionMasterNewEntity
									.getShiftMasterEntity().getMastersId(),
							blanketProductionMasterNewEntity.getBatchNumber(),
							null);

			blanketProductionMasterNewOutputMessage = new BlanketProductionMasterNewOutputMessage();
			if (list != null
					&& list.size() > 0
					&& !list.get(0)
							.getBlanketProductionId()
							.equals(blanketProductionMasterNewEntity
									.getBlanketProductionId())) {
				ErrorDTO errorDTO = new ErrorDTO("1",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				blanketProductionMasterNewOutputMessage
						.setErrorListDTO(errorListDTO);
			} else {
				BlanketProductionMasterNewEntity me = storageBlanketProductionMasterNewDAO
						.findById(
								blanketProductionMasterNewEntity
										.getBlanketProductionId()).get(0);
				List<BlanketProductionDetailNewEntity> lde = me
						.getBlanketProductionDetailNewEntityList();
				blanketProductionMasterNewEntity
						.getBlanketProductionDetailNewEntityList().clear();
				blanketProductionMasterNewEntity
						.setBlanketProductionDetailNewEntityList(lde);
				
				
				/*
				 * if(lde!=null && blanketProductionMasterEntity.
				 * getBlanketProductionDetailNewEntityList()!=null ){ for(int
				 * i=0;i<lde.size();i++){ if(!blanketProductionMasterEntity.
				 * getBlanketProductionDetailNewEntityList
				 * ().contains(lde.get(i))){ lde.get(i).setDeletedFlag(true);
				 * 
				 * blanketProductionMasterEntity.
				 * getBlanketProductionDetailNewEntityList().add(i,
				 * lde.get(i)); }}}
				 */

				/*List<BlanketProductionDetailNewEntity> rde = me
						.getBlanketProductionDetailNewEntityList();
				blanketProductionMasterNewEntity
						.getBlanketProductionDetailNewEntityList().clear();
				blanketProductionMasterNewEntity
						.setBlanketProductionDetailNewEntityList(rde);*/
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

				storageBlanketProductionMasterNewDAO
						.update(blanketProductionMasterNewEntity);
			}
		} else if (DELETE_BPM.equals(flowId)) {
			storageBlanketProductionMasterNewDAO
					.delete(blanketProductionMasterNewEntity);
		} else if (FIND_BPM_BY_ID.equals(flowId)) {
			List<BlanketProductionMasterNewEntity> list = storageBlanketProductionMasterNewDAO
					.findById(blanketProductionMasterNewEntity
							.getBlanketProductionId());
			logger.info("BlanketProductionMasterNewEntity for id("
					+ blanketProductionMasterNewEntity.getBlanketProductionId()
					+ ") :" + list);
			popUpList(list);
		} else if (FIND_ALL_BPM.equals(flowId)) {

			List<BlanketProductionMasterNewEntity> list = storageBlanketProductionMasterNewDAO
					.load();
			popUpList(list);
		} else if (SEARCH_BPM.equals(flowId)) {
			BlanketProductionSearchCriteriaDTO searchCriteria = blanketProductionMasterNewInputMessage
					.getSearchCriteria();
			List<BlanketProductionMasterNewEntity> list = storageBlanketProductionMasterNewDAO
					.search(searchCriteria.getFromDate(),
							searchCriteria.getToDate(),
							searchCriteria.getRunNo(),
							searchCriteria.getGradeId());
			popUpList(list);
		} else if (FIND_BPM_PAGINATION.equals(flowId)) {
			// Before Load List cleane blanket production
			storageBlanketProductionMasterNewDAO.cleanDuplicateEntry();
			System.out
					.println("Calling before uploading complete blanket list list1111................");
			List<BlanketProductionMasterNewEntity> list = storageBlanketProductionMasterNewDAO
					.findBlanketProductionPagination(blanketProductionMasterNewInputMessage
							.getNext());
			popUpList(list);
		} else if (FIND_BPM_FOR_SHIFT_REPORT.equals(flowId)) {
			BlanketProductionMasterNewDTO bpmDTO = blanketProductionMasterNewInputMessage
					.getBlanketProductionMasterNewDTO();
			List<BlanketProductionMasterNewEntity> list = storageBlanketProductionMasterNewDAO
					.getDataForShiftReport(bpmDTO.getBlanketProductionDate(),
							bpmDTO.getRunNo(), bpmDTO.getShiftMasterDTO()
									.getMastersId());
			popUpList(list);
		}
	}

	void popUpList(List<BlanketProductionMasterNewEntity> list) {
		logger.info("BPM Entity List  :" + list);
		blanketProductionMasterNewOutputMessage = new BlanketProductionMasterNewOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<BlanketProductionMasterNewDTO> resultList = new ArrayList<BlanketProductionMasterNewDTO>();
			BlanketProductionMasterNewDTO blanketProductionMasterNewDTO;
			for (BlanketProductionMasterNewEntity entity : list) {
				blanketProductionMasterNewDTO = new BlanketProductionMasterNewDTO();
				// Spring
				BeanUtils.copyProperties(entity, blanketProductionMasterNewDTO);
				if (entity.getShiftMasterEntity() != null) {
					MastersDTO shiftMasterDTO = new MastersDTO();
					BeanUtils.copyProperties(entity.getShiftMasterEntity(),
							shiftMasterDTO);
					blanketProductionMasterNewDTO
							.setShiftMasterDTO(shiftMasterDTO);
				}
				if (entity.getGradeMasterEntity() != null) {
					MastersDTO gradeMasterDTO = new MastersDTO();
					BeanUtils.copyProperties(entity.getGradeMasterEntity(),
							gradeMasterDTO);
					blanketProductionMasterNewDTO
							.setGradeMasterDTO(gradeMasterDTO);
				}

				List<BlanketProductionDetailNewEntity> blanketProductionDetailLeftEntityList = entity
						.getBlanketProductionDetailNewEntityList();
				List<BlanketProductionDetailNewDTO> blanketProductionDetailLeftDTOList = new ArrayList<BlanketProductionDetailNewDTO>();
				if (blanketProductionDetailLeftEntityList != null
						&& blanketProductionDetailLeftEntityList.size() > 0) {
					for (BlanketProductionDetailNewEntity blanketProductionDetailLeftEntity : blanketProductionDetailLeftEntityList) {
						if (blanketProductionDetailLeftEntity.getDeletedFlag())
							continue;
						BlanketProductionDetailNewDTO blanketProductionDetailLeftDTO = new BlanketProductionDetailNewDTO();
						copyObject(blanketProductionDetailLeftEntity,
								blanketProductionDetailLeftDTO);
						blanketProductionDetailLeftDTOList
								.add(blanketProductionDetailLeftDTO);
					}
				}
				blanketProductionMasterNewDTO
						.setBlanketProductionDetailNewDTOList(blanketProductionDetailLeftDTOList);
				
				resultList.add(blanketProductionMasterNewDTO);
			}
			blanketProductionMasterNewOutputMessage
					.setBlanketProductionMasterNewDTOList(resultList);
		}

	}

	private void copyObject(Object source, Object target) {

		try {
			BeanUtils.copyProperties(source, target);
		} catch (Exception e) {
			logger.error("Exception in Approved Blanket Production", e);
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
		Integer blanketProdId = storageBlanketProductionMasterNewDAO
				.getMaxBlanketProdId();
		return blanketProdId;
	}

	@Override
	public String checkDuplicateRecordInBPMaster(
			BlanketProductionMasterNewDTO blanketProductionMasterNewDTO) {
		String flag = null;
		List list = storageBlanketProductionMasterNewDAO.findByDateAndRunNo(
				blanketProductionMasterNewDTO.getBlanketProductionDate(),
				blanketProductionMasterNewDTO.getRunNo(),
				blanketProductionMasterNewDTO.getShiftMasterDTO().getMastersId(),
				blanketProductionMasterNewDTO.getBatchNumber(), null);
		if (list != null && list.size() > 0) {
			flag = "Duplicate";
		}
		return flag;
	}

	@Override
	public ArrayList<Integer> getMaxRollNo(String batchNo,Integer spliterCount,Integer BlanketMasterId,Integer rNo) {
		
		ArrayList<Integer> list=storageBlanketProductionMasterNewDAO.getMaxRollNo(batchNo, BlanketMasterId,rNo);
		Integer maxRno=1;
		if(list!=null && list.size()>0){
			
			for(int i=0;i<list.size();i++){
				Integer object = (Integer) list.get(i);
				logger.info(i+":"+object);
				
			}
			
			System.out.println("------start------");
			if(list.get(0)!=null){
				maxRno=list.get(0);
			}
			logger.info("spliterCount:"+spliterCount);
			logger.info("maxRno:"+maxRno);
			logger.info("No of record for this runNo:"+list.get(2));
		if(list.get(2)!=null && spliterCount!=null){
			/*if(spliterCount>list.get(2)){
				maxRno=list.get(2)-1;
			}*/
			
			
			
			if(spliterCount==list.get(2)){
				maxRno=list.get(0);
			}
			/*if(spliterCount<list.get(2)){
				maxRno=list.get(0)+1;
			}*/
			if(spliterCount>list.get(2)){
				maxRno=maxRno-1;
			}
			logger.info("maxRno:"+maxRno);
			
			
			list.set(2, maxRno);
		}
		
		logger.info("------end------");
			for(int i=0;i<list.size();i++){
				Integer object = (Integer) list.get(i);
				logger.info(i+":"+object);
				
			}
		
		}
		
		
		return list;
	}



	@Override
	public Boolean deleteBlanketProductionDetail(
			BlanketProductionDetailNewDTO dto) {
		BlanketProductionDetailNewEntity blanketProductionDetailLefttEntity = new BlanketProductionDetailNewEntity();
		copyObject(dto, blanketProductionDetailLefttEntity);
		return storageBlanketProductionMasterNewDAO
				.deleteBlanketProductionDetail(blanketProductionDetailLefttEntity);

	}


	@Override
	public List getBlanketProductionDetail(Date date, Integer shiftId) {
		List list = storageBlanketProductionMasterNewDAO.getBlanketProductionDetail(
				date, shiftId);
		ArrayList<BlanketProductionDetailNewDTO> arrayList = new ArrayList<BlanketProductionDetailNewDTO>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				BlanketProductionDetailNewDTO leftDTO = new BlanketProductionDetailNewDTO();
				Object[] obj = (Object[]) list.get(i);
				Integer sno = (Integer) obj[0];
				String runNo = (String) obj[1];
				// for date
				String shift = (String) obj[3];
				String batchNo = (String) obj[4];
				Integer rollNo = (Integer) obj[5];
				String blanketType = (String) obj[6];
				String statusLeft = (String) obj[7];
				Character spliterType =null;
				if(obj[8]!=null)
					spliterType=((String)obj[8]).charAt(0);

				leftDTO.setSno(sno);
				leftDTO.setRunNo(runNo);
				leftDTO.setShift(shift);
				leftDTO.setBatchNo(batchNo);
				leftDTO.setBlanketType(blanketType);
				leftDTO.setRollNo(rollNo);
				leftDTO.setStatus(statusLeft);
				leftDTO.setSpliterType(spliterType);
				arrayList.add(leftDTO);

			}
		}

		return arrayList;
	}

	

	@Override
	public List getRejBlanketProductionDetailList(String status, Date date,
			Integer shiftId) {
		List<BlanketProductionDetailNewEntity> list = null;
		try {
			list = storageBlanketProductionMasterNewDAO
					.getRejBlanketProductionDetail(status, date, shiftId);
		} catch (Exception e) {
			logger.error("Exception in Approved Blanket Production", e);
			e.printStackTrace();
		}
		List<BlanketProductionDetailNewDTO> leftList = null;
		try {
			leftList = new ArrayList<BlanketProductionDetailNewDTO>();
		} catch (Exception e) {
			logger.error("Exception in Approved Blanket Production", e);
			e.printStackTrace();
		}
		for (BlanketProductionDetailNewEntity entity : list) {
			BlanketProductionDetailNewDTO leftDTO = new BlanketProductionDetailNewDTO();
			BeanUtils.copyProperties(entity, leftDTO);
			leftList.add(leftDTO);
		}

		return leftList;
	}

	

	@Override
	public List updateBlanketProductionDetail(
			List<BlanketProductionDetailNewDTO> left,
			 Integer userId) {
		try {
			if (left != null && left.size() > 0) {
				for (int i = 0; i < left.size(); i++) {
					BlanketProductionDetailNewDTO pbl = left.get(i);
					BlanketProductionDetailNewEntity leftEntity = new BlanketProductionDetailNewEntity();
					leftEntity.setSno(pbl.getSno());
					leftEntity.setStatus(pbl.getStatus());
					leftEntity.setRaUserId(userId);
					storageBlanketProductionMasterNewDAO
							.updateBlanketProductionDetail(leftEntity, "R.A.");

				}
			}
		} catch (Exception e) {
			logger.error("Exception in Approved Blanket Production", e);
		}

		

		return null;
	}

	@Override
	public List updateApprovedBlanketProductionDetail(
			List<BlanketProductionDetailNewDTO> left,
			 Integer userId,
			FinishedGoodsMasterDTO finishedGoodsMasterDTO) {
		List<FinishedGoodsDetailEntity> fDetailList = null;
		String finishedGoodNumber = null;
		Integer finishedGoodAutoId = null;
		List<FinishedGoodsMasterEntity> list = null;
		boolean isAnyApproved=false;

		try {
			list = finishedGoodsMasterDAO.findByFinishGoodNumber(null,
					finishedGoodsMasterDTO.getFinishGoodDate());
			if (list != null && list.size() > 0) {
				FinishedGoodsMasterEntity mastreEntity = list.get(0);
				fDetailList = mastreEntity.getFinishedGoodsDetailEntities();
				finishedGoodNumber = mastreEntity.getFinishedGoodsNumber();
				finishedGoodAutoId = mastreEntity.getFinishedGoodsAutoId();
				finishedGoodsMasterDTO
				.setFinishedGoodsNumber(finishedGoodNumber);
				
			} else {
				fDetailList = new ArrayList<FinishedGoodsDetailEntity>();
			}
		} catch (Exception e) {
			logger.error("Exception in Approved Blanket Production old record", e);
		}

		try {
			if (left != null && left.size() > 0) {

				for (int j = 0; j < left.size(); j++) {

					BlanketProductionDetailNewDTO pbl = left.get(j);
					BlanketProductionDetailNewEntity leftEntity = new BlanketProductionDetailNewEntity();
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
						leftEntity.setStatus("Rejection");
						leftEntity.setApprovedStatus(0);
					} else {
						leftEntity.setStatus("OK");
					}

					try {
						System.out.println("STATUS IS........."
								+ pbl.getApprovedStatus()
								+ " FINISHED GOODS NUMBERS................ "
								+ finishedGoodsMasterDTO
										.getFinishedGoodsNumber());
						if (pbl.getApprovedStatus()!=null &&  pbl.getApprovedStatus() == 1) {
							isAnyApproved=true;
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
						logger.error("Exception in Approved Blanket Production 2", e);
						//e.printStackTrace();
					}
					storageBlanketProductionMasterNewDAO
							.updateBlanketProductionDetail(leftEntity, "Approved");
				}
			}
		} catch (Exception e) {
			logger.error("Exception in Approved Blanket Production 3", e);
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
					detailEntity.setModifiedUserId(userId);
				} catch (Exception e) {
					logger.error("Exception in Approved Blanket Production 4", e);
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
					logger.error("Exception in Approved Blanket Production 5", e);
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
			storageBlanketProductionMasterNewDAO.cleanDuplicateEntry();
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
				/**
				 * update the approve status false when we update finished good entry by approve blanket
				 * if any item is approved then it will change status else not
				 */
				if(isAnyApproved){
				fMasterEntity.setApprovalFlag(0);
				fMasterEntity.setAprovedDate(null);
				}
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
			logger.error("Exception in Approved Blanket Production 6", e);
		}

		// End Finish good entry form

		return null;
	}

	@Override
	public List updateRejectedBlanketProductionDetail(
			List<BlanketProductionDetailNewDTO> left,
			 Integer userId) {
		try {
			if (left != null && left.size() > 0) {
				for (int i = 0; i < left.size(); i++) {
					BlanketProductionDetailNewDTO pbl = left.get(i);
					BlanketProductionDetailNewEntity leftEntity = new BlanketProductionDetailNewEntity();
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

							leftEntity.setRejDensity(itemDTO.getItemDensity());
							leftEntity.setRejItemId(itemDTO.getItemId());
							leftEntity.setRejLength(itemDTO.getItemLength());
							leftEntity.setRejThick(itemDTO.getItemHeight());
							leftEntity.setRejWeight(itemDTO.getGrossWeight());
							leftEntity.setRejWidth(itemDTO.getItemWidth());
						}
					}

					leftEntity.setStatus(pbl.getStatus());
					storageBlanketProductionMasterNewDAO
							.updateBlanketProductionDetail(leftEntity,
									"updateRejection");

				}
			}
		} catch (Exception e) {
			logger.error("Exception in Approved Blanket Production", e);
		}

		
		return null;
	}

	@Override
	public List getCheckDuplicatRollNo(String batchNo, Integer rollNoL) {
		return storageBlanketProductionMasterNewDAO.getCheckDuplicatRollNo(
				batchNo, rollNoL);
	}

	

	@Override
	public void updateBlanketProduction(Date date, String runNo,
			Integer shiftName, String batchNo, Integer status) {
		storageBlanketProductionMasterNewDAO.updateBlanket(date, runNo, shiftName,
				status);
	}

	@Override
	public Boolean checkFinishedGood(String finishedGoodNumber) {
		// TODO Auto-generated method stub
		return storageBlanketProductionMasterNewDAO
				.checkFinishedGood(finishedGoodNumber);
	}

	@Override
	public Double getBlanketWeightRecord(Character position,
			Integer createdUserId) {
		Double amt = 0.0;
		try {
			amt = storageBlanketProductionMasterNewDAO.getBlanketWeightRecord(
					position, createdUserId);
		} catch (Exception e) {
			logger.error("Exception in Approved Blanket Production", e);
		}
		return amt;

	}

	@Override
	public Timestamp getLastBlanketEntryDate() {
		Timestamp date = zoneService.getFirstDayOfFinYear();
		List list = storageBlanketProductionMasterNewDAO.getLastBlanketEntryDate();
		if (list != null && list.size() > 0) {
			if (list.get(0) != null)
				date = new Timestamp(((Date) list.get(0)).getTime());
		}
		return date;
	}
}