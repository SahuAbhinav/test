package com.advanz.erp.masters.service;

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
import com.advanz.erp.masters.entity.jpa.BlanketProductionDetailLeftEntity;
import com.advanz.erp.masters.entity.jpa.BlanketProductionDetailRightEntity;
import com.advanz.erp.masters.entity.jpa.BlanketProductionMasterEntity;
import com.advanz.erp.masters.entity.jpa.BulkFiberDetailEntity;
import com.advanz.erp.masters.entity.jpa.BulkFiberMasterEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.model.BlanketProductionDetailLeftDTO;
import com.advanz.erp.masters.model.BlanketProductionDetailRightDTO;
import com.advanz.erp.masters.model.BlanketProductionMasterDTO;
import com.advanz.erp.masters.model.BulkFiberDetailDTO;
import com.advanz.erp.masters.model.BulkFiberMasterDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.criteria.BlanketProductionSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterInputMessage;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterOutputMessage;
import com.advanz.erp.masters.model.msg.BulkFiberMasterInputMessage;
import com.advanz.erp.masters.model.msg.BulkFiberMasterOutputMessage;
import com.advanz.erp.masters.service.business.IBlanketProductionMasterService;
import com.advanz.erp.masters.service.business.IBulkFiberMasterService;
import com.advanz.erp.masters.storage.IStorageBlanketProductionMasterDAO;
import com.advanz.erp.masters.storage.IStorageBulkFiberMasterDAO;

public class BulkFiberMasterServiceImpl implements IBulkFiberMasterService {
	private static final Logger logger = LoggerFactory
	.getLogger(BulkFiberMasterServiceImpl.class);
	
	public static final String CREATE_BFM = "CreateBulkFIberMaster";
	public static final String UPDATE_BFM = "UpdateBulkFIberMaster";
	public static final String DELETE_BFM = "DeleteBulkFIberMaster";
	public static final String FIND_BFM_BY_ID = "FindBulkFIberMasterById";
	public static final String FIND_ALL_BFM = "FindAllBulkFIberMasters";
	public static final String SEARCH_BFM = "SearchBulkFIberMasters";
	public static final String FIND_ALL_BFM_WITH_PAGINATION = "FindAllBulkFiberMastersWithPagination";
	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageBulkFiberMasterDAO storageBulkFIberMasterDAO;

	public BulkFiberMasterInputMessage bulkFIberMasterInputMessage;

	public BulkFiberMasterOutputMessage bulkFIberMasterOutputMessage;

	@Override
	public BulkFiberMasterOutputMessage createBulkFiberMaster(BulkFiberMasterInputMessage bulkFIberMasterInputMessage) {

		flowId = CREATE_BFM;
		// assign the message to the class level variable.
		this.bulkFIberMasterInputMessage = bulkFIberMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return bulkFIberMasterOutputMessage;
	}

	@Override
	public BulkFiberMasterOutputMessage updateBulkFiberMaster(BulkFiberMasterInputMessage bulkFiberMasterInputMessage) {

		flowId = UPDATE_BFM;
		// assign the message to the class level variable.
		this.bulkFIberMasterInputMessage = bulkFiberMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return bulkFIberMasterOutputMessage;
	}

	@Override
	public BulkFiberMasterOutputMessage deleteBulkFiberMaster(BulkFiberMasterInputMessage bulkFiberMasterInputMessage) {
		flowId = DELETE_BFM;
		// assign the message to the class level variable.
		this.bulkFIberMasterInputMessage = bulkFiberMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return bulkFIberMasterOutputMessage;

	}

	@Override
	public BulkFiberMasterOutputMessage findBulkFiberMasterById(BulkFiberMasterInputMessage bulkFiberMasterInputMessage) {
		flowId = FIND_BFM_BY_ID;
		// assign the message to the class level variable.
		this.bulkFIberMasterInputMessage = bulkFiberMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return bulkFIberMasterOutputMessage;

	}

	@Override
	public BulkFiberMasterOutputMessage findAllBulkFiberMasters() {
		flowId = FIND_ALL_BFM;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return bulkFIberMasterOutputMessage;
	}

	@Override
	public BulkFiberMasterOutputMessage search(BulkFiberMasterInputMessage bulkFiberMasterInputMessage) {
		flowId = SEARCH_BFM;
		this.bulkFIberMasterInputMessage=bulkFiberMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return bulkFIberMasterOutputMessage;

	}
	
	@Override
	public BulkFiberMasterOutputMessage getListWithPagination(
			BulkFiberMasterInputMessage bulkFiberMasterInputMessage) {
		// TODO Auto-generated method stub 
		flowId = FIND_ALL_BFM_WITH_PAGINATION;
		this.bulkFIberMasterInputMessage=bulkFiberMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return bulkFIberMasterOutputMessage;
	}
	@Override
	public boolean validateInput() {

		if (CREATE_BFM.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_BFM.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_BFM.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_BFM_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_BFM.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_BFM.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_ALL_BFM_WITH_PAGINATION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		
		return false;
	}

	@Override
	public void performBusinessLogic() {

		BulkFiberMasterEntity bulkFiberMasterEntity = new BulkFiberMasterEntity();
		if (bulkFIberMasterInputMessage != null) {
			BulkFiberMasterDTO bulkFiberMasterDTO = bulkFIberMasterInputMessage.getBulkFiberMasterDTO();
			if (bulkFiberMasterDTO != null) {
				BeanUtils.copyProperties(bulkFiberMasterDTO, bulkFiberMasterEntity);
				if(bulkFiberMasterDTO.getGradeMasterDTO()!=null){
					MastersEntity gradeEntity=new MastersEntity();
					gradeEntity.setMastersId(bulkFiberMasterDTO.getGradeMasterDTO().getMastersId());
					bulkFiberMasterEntity.setGradeMasterEntity(gradeEntity);
				}
				if(bulkFiberMasterDTO.getShiftMasterDTO()!=null){
					MastersEntity shiftEntity=new MastersEntity();
					shiftEntity.setMastersId(bulkFiberMasterDTO.getShiftMasterDTO().getMastersId());
					bulkFiberMasterEntity.setShiftMasterEntity(shiftEntity);
				}
				List<BulkFiberDetailDTO> bulkFiberDetailDTOList=bulkFiberMasterDTO.getBulkFiberDetailDTOList();
				if(bulkFiberDetailDTOList!=null && bulkFiberDetailDTOList.size()>0){					
					List<BulkFiberDetailEntity>bulkFiberDetailEntityList=new ArrayList<BulkFiberDetailEntity>();
					for(BulkFiberDetailDTO dto:bulkFiberDetailDTOList){
						BulkFiberDetailEntity entity=new BulkFiberDetailEntity();
						copyObject(dto, entity);
						entity.setModifiedUserId(bulkFiberMasterDTO.getModifiedUserId());
						bulkFiberDetailEntityList.add(entity);
					}
					bulkFiberMasterEntity.setBulkFiberDetailEntityList(bulkFiberDetailEntityList);
				}
				

		}
		}

		if (CREATE_BFM.equals(flowId)) {
			List<BulkFiberMasterEntity> list = storageBulkFIberMasterDAO.findByDateAndRunNo(bulkFiberMasterEntity.getBulkFiberDate(),bulkFiberMasterEntity.getRunNo(),bulkFiberMasterEntity.getShiftMasterEntity().getMastersId());

			bulkFIberMasterOutputMessage=new BulkFiberMasterOutputMessage();
			if(list!=null && list.size()>0){
				ErrorDTO errorDTO = new ErrorDTO("1", "Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				bulkFIberMasterOutputMessage.setErrorListDTO(errorListDTO);
			}else{	
				bulkFIberMasterOutputMessage.setErrorListDTO(null);		
					storageBulkFIberMasterDAO.create(bulkFiberMasterEntity);
			}
		} else if (UPDATE_BFM.equals(flowId)) {
			List<BulkFiberMasterEntity> list = storageBulkFIberMasterDAO.findByDateAndRunNo(bulkFiberMasterEntity.getBulkFiberDate(),bulkFiberMasterEntity.getRunNo(),bulkFiberMasterEntity.getShiftMasterEntity().getMastersId());

			bulkFIberMasterOutputMessage=new BulkFiberMasterOutputMessage();
			if(list!=null && list.size()>0 && !list.get(0).getBulkFiberId().equals(bulkFiberMasterEntity.getBulkFiberId())){
				ErrorDTO errorDTO = new ErrorDTO("1", "Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				bulkFIberMasterOutputMessage.setErrorListDTO(errorListDTO);
			}else{
				BulkFiberMasterEntity me=storageBulkFIberMasterDAO.findById(bulkFiberMasterEntity.getBulkFiberId()).get(0);
			List<BulkFiberDetailEntity> lde=me.getBulkFiberDetailEntityList();
			if(lde!=null && bulkFiberMasterEntity.getBulkFiberDetailEntityList()!=null ){				
			for(int i=0;i<lde.size();i++){
				if(!bulkFiberMasterEntity.getBulkFiberDetailEntityList().contains(lde.get(i))){
					lde.get(i).setDeletedFlag(true);
					bulkFiberMasterEntity.getBulkFiberDetailEntityList().add(i, lde.get(i));
				}				
			}
			}
			
				storageBulkFIberMasterDAO.update(bulkFiberMasterEntity);	
		}
			} else if (DELETE_BFM.equals(flowId)) {
			storageBulkFIberMasterDAO.delete(bulkFiberMasterEntity);
		} else if (FIND_BFM_BY_ID.equals(flowId)) {
		List<BulkFiberMasterEntity>list=storageBulkFIberMasterDAO.findById(bulkFiberMasterEntity.getBulkFiberId());
		
		popUpList(list);			
		} else if (FIND_ALL_BFM.equals(flowId)) {
			List<BulkFiberMasterEntity> list = storageBulkFIberMasterDAO.load();
			popUpList(list);			
		}
		else if (SEARCH_BFM.equals(flowId)) {
			BulkFiberMasterDTO bulkFiberMasterDTO = bulkFIberMasterInputMessage.getBulkFiberMasterDTO();
			List<BulkFiberMasterEntity> list = storageBulkFIberMasterDAO.search(bulkFiberMasterDTO.getFromDate(),bulkFiberMasterDTO.getToDate(),bulkFiberMasterDTO.getRunNo(),bulkFiberMasterDTO.getGradeMasterDTO().getMastersId());
			popUpList(list);			
		}
		else if (FIND_ALL_BFM_WITH_PAGINATION.equals(flowId)) {
			BulkFiberMasterDTO bulkFiberMasterDTO = bulkFIberMasterInputMessage.getBulkFiberMasterDTO();
			List<BulkFiberMasterEntity> list = storageBulkFIberMasterDAO.getListWithPagination(bulkFiberMasterDTO.getNext());
			popUpList(list);			
		}
		
	}
	void popUpList(List<BulkFiberMasterEntity> list) {
		logger.info("BFM Entity List  :"+list);
		bulkFIberMasterOutputMessage = new BulkFiberMasterOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<BulkFiberMasterDTO> resultList = new ArrayList<BulkFiberMasterDTO>();
			BulkFiberMasterDTO bulkFiberMasterDTO;
			for (BulkFiberMasterEntity entity : list) {
				bulkFiberMasterDTO = new BulkFiberMasterDTO();
				// Spring				
				BeanUtils.copyProperties(entity, bulkFiberMasterDTO);
				if(entity.getShiftMasterEntity()!=null){
					MastersDTO shiftMasterDTO=new MastersDTO();
					BeanUtils.copyProperties(entity.getShiftMasterEntity(), shiftMasterDTO);
					bulkFiberMasterDTO.setShiftMasterDTO(shiftMasterDTO);
				}
				if(entity.getGradeMasterEntity()!=null){
					MastersDTO gradeMasterDTO=new MastersDTO();
					BeanUtils.copyProperties(entity.getGradeMasterEntity(), gradeMasterDTO);
					bulkFiberMasterDTO.setGradeMasterDTO(gradeMasterDTO);
				}	
				
				
				List<BulkFiberDetailEntity> bulkFiberDetailEntityList=entity.getBulkFiberDetailEntityList();
				List<BulkFiberDetailDTO> bulkFiberDetailDTOList=new ArrayList<BulkFiberDetailDTO>();	
				if(bulkFiberDetailEntityList!=null && bulkFiberDetailEntityList.size()>0){
					for(BulkFiberDetailEntity bulkFiberDetailEntity:bulkFiberDetailEntityList){
						if(bulkFiberDetailEntity.getDeletedFlag())
							continue;
						BulkFiberDetailDTO bulkFiberDetailDTO=new BulkFiberDetailDTO();
						copyObject(bulkFiberDetailEntity,bulkFiberDetailDTO);	
						bulkFiberDetailDTOList.add(bulkFiberDetailDTO);
					}
				}						
				bulkFiberMasterDTO.setBulkFiberDetailDTOList(bulkFiberDetailDTOList);	
				

				resultList.add(bulkFiberMasterDTO);
			}
			bulkFIberMasterOutputMessage.setBulkFiberMasterDTOList(resultList);
		}

	}

	
	private void copyObject(Object source,Object target){
		
		try {
			BeanUtils.copyProperties(source, target);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void formatOutput() {

		if (CREATE_BFM.equals(flowId)) {

		} else if (UPDATE_BFM.equals(flowId)) {

		} else if (DELETE_BFM.equals(flowId)) {

		} else if (FIND_BFM_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_BFM.equals(flowId)) {

		} else if (SEARCH_BFM.equals(flowId)) {

		}
	}

	
}
