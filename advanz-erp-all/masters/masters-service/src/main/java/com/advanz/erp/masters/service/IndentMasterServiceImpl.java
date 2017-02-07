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
import com.advanz.erp.masters.entity.jpa.IndentDetailEntity;
import com.advanz.erp.masters.entity.jpa.IndentMasterEntity;
import com.advanz.erp.masters.entity.jpa.IssueDetailMasterEntity;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.IndentDetailDTO;
import com.advanz.erp.masters.model.IndentMasterDTO;
import com.advanz.erp.masters.model.IssueDetailMasterDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.msg.IndentInputMessage;
import com.advanz.erp.masters.model.msg.IndentOutputMessage;
import com.advanz.erp.masters.service.business.IIndentMasterService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IStockLedgerService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageIndentDAO;
import com.advanz.erp.masters.storage.IStoragePurchaseOrderMasterDAO;

public class IndentMasterServiceImpl implements IIndentMasterService {

	public static final String CREATE_INDENT_MASTER = "CreateIssueMaster";
	public static final String UPDATE_INDENT_MASTER = "UpdateIssueMaster";
	public static final String DELETE_INDENT_MASTER = "DeleteIssueMaster";
	public static final String FIND_INDENT_MASTER_BY_ID = "FindIssueMasterById";
	public static final String FIND_INDENT_MASTER_BY_INDENT_NUMBER = "FindIssueMasterByIndentNumber";
	public static final String FIND_ALL_INDENT_MASTER = "FindAllIssueMasters";
	public static final String SEARCH_INDENT_MASTER = "SearchIssueMasters";
	public static final String NEW_INDENT_MASTER_SERIES_NO = "NewIssueMastersSeriesNo";
	public static final String FIND_INDENT_MASTER_PAGINATION = "FindIssueMasterPagination";
	
	public String flowId = "";
	private static final Logger logger = LoggerFactory.getLogger(IndentMasterServiceImpl.class);
	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do

	@Autowired
	public IStorageIndentDAO storageIndentDAO;

	public IndentInputMessage indentInputMessage;

	public IndentOutputMessage indentOutputMessage;

	@Autowired
	public IItemService itemService;
	
	@Autowired
	public IStockLedgerService stockLedgerService; 
	
	@Autowired
	public IStoragePurchaseOrderMasterDAO purchaseOrderMasterDAO;
	
	@Autowired
	public IZoneService zoneService;
	
	@Override
	public IndentOutputMessage createIndentMaster(IndentInputMessage indentInputMessage) {

		flowId = CREATE_INDENT_MASTER;
		// assign the message to the class level variable.
		this.indentInputMessage = indentInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return indentOutputMessage;
	}

	@Override
	public IndentOutputMessage updateIndentMaster(IndentInputMessage indentInputMessage) {

		flowId = UPDATE_INDENT_MASTER;
		// assign the message to the class level variable.
		this.indentInputMessage = indentInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return indentOutputMessage;
	}

	@Override
	public IndentOutputMessage deleteIndentMaster(IndentInputMessage indentInputMessage) {
		flowId = DELETE_INDENT_MASTER;
		// assign the message to the class level variable.
		this.indentInputMessage = indentInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return indentOutputMessage;

	}

	@Override
	public IndentOutputMessage findIndentMasterById(IndentInputMessage indentInputMessage) {
		flowId = FIND_INDENT_MASTER_BY_ID;
		// assign the message to the class level variable.
		this.indentInputMessage = indentInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return indentOutputMessage;

	}

	@Override
	public IndentOutputMessage findAllIndentMasters() {
		flowId = FIND_ALL_INDENT_MASTER;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return indentOutputMessage;
	}
	
	@Override
	public IndentOutputMessage search(IndentInputMessage indentInputMessage) {
		flowId = SEARCH_INDENT_MASTER;
		this.indentInputMessage = indentInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return indentOutputMessage;

	}
	
	@Override
	public IndentOutputMessage getNewIndentSeriesNo(IndentInputMessage indentInputMessage) {
		flowId = NEW_INDENT_MASTER_SERIES_NO;
		this.indentInputMessage = indentInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return indentOutputMessage;
	}
	@Override
	public IndentOutputMessage findIndentMasterPagination(
			IndentInputMessage indentInputMessage) {
	flowId = FIND_INDENT_MASTER_PAGINATION;
	this.indentInputMessage = indentInputMessage;
	// call the template method
	advanzErpServiceTemplate.execute(this);
	return indentOutputMessage;
	}
	@Override
	public IndentOutputMessage findIndentMasterByIndentNumber(
			IndentInputMessage indentInputMessage) {
		flowId =FIND_INDENT_MASTER_BY_INDENT_NUMBER;
		// TODO Auto-generated method stub
		this.indentInputMessage = indentInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return indentOutputMessage;
	}
	@Override
	public boolean validateInput() {

		if (CREATE_INDENT_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_INDENT_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_INDENT_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_INDENT_MASTER_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_INDENT_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_INDENT_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (NEW_INDENT_MASTER_SERIES_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_INDENT_MASTER_PAGINATION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
			}else if (FIND_INDENT_MASTER_BY_INDENT_NUMBER.equals(flowId)) {
				// TODO add business validation on the input.
				return true;
				}
		
		return false;
	}

	@Override
	public void performBusinessLogic() {

		IndentMasterEntity indentMasterEntity = new IndentMasterEntity();
		
		List<IndentDetailDTO> indentDetailDTOList=null;
		IndentMasterDTO indentMasterDTO =null;
		if (indentInputMessage != null) {
			 indentMasterDTO = indentInputMessage.getIndentMasterDTO();
			if (indentMasterDTO != null) {
				BeanUtils.copyProperties(indentMasterDTO, indentMasterEntity);
			//	PartyDTO partyDTO=issueMasterDTO.getPartyDTO();
				BranchDTO branchDTO=indentMasterDTO.getBranchDTO();
				if(branchDTO!=null){
					BranchEntity branchEntity=new BranchEntity();
					copyObject(branchDTO, branchEntity);
					indentMasterEntity.setBranchEntity(branchEntity);
				}
				 indentDetailDTOList=indentMasterDTO.getIndentDetailDTO();
				if(indentDetailDTOList!=null && indentDetailDTOList.size()>0){
					IndentDetailDTO detailMasterDTO=  indentDetailDTOList.get(0);
					List<IndentDetailEntity>indentDetailEntity=convertIndentDetailDtoTOIndentDetailEntity(indentDetailDTOList,indentMasterDTO);	
				indentMasterEntity.setIndentDetailEntity(indentDetailEntity);
			
	        }
		 }
		}

		if (CREATE_INDENT_MASTER.equals(flowId)) {
			// check duplicate finishedGoodsMaster name
			List<IndentMasterEntity> list = storageIndentDAO.findByIndentNumber(indentMasterEntity.getIndentNumber());
		
			
		//	issueOutputMessage = new IssueOutputMessage();
			if (list != null && list.size() > 0) {
				
				ErrorDTO errorDTO = new ErrorDTO("1", indentMasterEntity.getIndentNumber()+" Indent Number is already exist,it can't be duplicate ");
			    ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				indentOutputMessage = new IndentOutputMessage();
				indentOutputMessage.setErrorListDTO(errorListDTO);
			  
			}else{
				 storageIndentDAO.create(indentMasterEntity);
			}
			
			
		} else if (UPDATE_INDENT_MASTER.equals(flowId)) {
			
			storageIndentDAO.update(indentMasterEntity);
	
		} 
		else if (DELETE_INDENT_MASTER.equals(flowId)) {
		List purchaseOrderList=	purchaseOrderMasterDAO.findByPurchaseOrderByIndentNumber(indentMasterEntity.getIndentNumber());
		if (purchaseOrderList != null && purchaseOrderList.size() > 0) {
			
			ErrorDTO errorDTO = new ErrorDTO("1", "Sorry you can not delete this record as it is used by purchase order");
		    ErrorListDTO errorListDTO = new ErrorListDTO();
			errorListDTO.addError(errorDTO);
			indentOutputMessage = new IndentOutputMessage();
			indentOutputMessage.setErrorListDTO(errorListDTO);
		  
		}else{
			
			storageIndentDAO.delete(indentMasterEntity);
		}
		}
		
		else if (FIND_INDENT_MASTER_BY_ID.equals(flowId)) {
		List<IndentMasterEntity>list=	storageIndentDAO.findById(indentMasterEntity.getIndentAutoId());
		popUpList(list);
		
		
		} else if (FIND_ALL_INDENT_MASTER.equals(flowId)) {
			List<IndentMasterEntity> list = storageIndentDAO.load();
			popUpList(list);			
		}
		else if (SEARCH_INDENT_MASTER.equals(flowId)) {
			IndentMasterDTO inentDTO=indentInputMessage.getIndentMasterDTO();
			List<IndentMasterEntity> list = storageIndentDAO.search(inentDTO.getIndentNumber(),inentDTO.getFromDate(),inentDTO.getToDate(),inentDTO.getItemName(),inentDTO.getRaisedBy(),inentDTO.getDepartmentName());
			popUpList(list);			
		}
		else if(NEW_INDENT_MASTER_SERIES_NO.equals(flowId)){
			
			Integer seriesNo=0;
			Timestamp date = zoneService.getFirstDayOfFinYear();
			List list=storageIndentDAO.getNewSeriesNo(indentMasterEntity.getFinYear());
			if (list != null && list.size() > 0) {
				Object[] obj=(Object[]) list.get(0);
				
				Number n = (Number) obj[0];
				if (n != null)
					seriesNo = n.intValue();
				if(obj[1]!=null && obj[1]!="")
					date=(Timestamp)obj[1];
			}
			seriesNo++;
			
			indentOutputMessage = new IndentOutputMessage();
			indentOutputMessage.setIndentSeriesNo(seriesNo);
			indentOutputMessage.setIndentSeriesDate(date);
		}
		else if (FIND_INDENT_MASTER_PAGINATION.equals(flowId)) {
			List<IndentMasterEntity> list = storageIndentDAO.findIndentPagination(indentInputMessage.getIndentMasterDTO().getNext());
			popUpList(list);
			}
		else if (FIND_INDENT_MASTER_BY_INDENT_NUMBER.equals(flowId)) {
			List<IndentMasterEntity> list = storageIndentDAO.findByIndentNumber(indentMasterEntity.getIndentNumber());
			popUpList(list);
			}
		
	}
	void popUpList(List<IndentMasterEntity> list) {
		logger.info("SOM Entity List  :"+list);
		indentOutputMessage = new IndentOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<IndentMasterDTO> resultList = new ArrayList<IndentMasterDTO>();
			IndentMasterDTO indentMasterDTO;
			for (IndentMasterEntity entity : list) {
				indentMasterDTO = new IndentMasterDTO();
				// Spring				
				BeanUtils.copyProperties(entity, indentMasterDTO);
				

				
				BranchEntity branchEntity=entity.getBranchEntity();
				if(branchEntity!=null){
					BranchDTO branchDTO=new BranchDTO();
					copyObject(branchEntity, branchDTO);
					indentMasterDTO.setBranchDTO(branchDTO);
				}
				
				
				
				
				List<IndentDetailEntity> indentDetailEntityList=entity.getIndentDetailEntity();
				List<IndentDetailDTO> indentDetailDTOList=new ArrayList<IndentDetailDTO>();
				
				if(indentDetailEntityList!=null && indentDetailEntityList.size()>0){
					for(IndentDetailEntity indentDetailEntity:indentDetailEntityList){
						IndentDetailDTO indentDetailDTO=new IndentDetailDTO();
						
						
						copyObject(indentDetailEntity,indentDetailDTO);	
						if(indentDetailEntity.getItemEntity()!=null){
							indentDetailDTO.setItemId(indentDetailEntity.getItemEntity().getItemId());
							indentDetailDTO.setItemName(indentDetailEntity.getItemEntity().getInvoiceName());
						indentDetailDTOList.add(indentDetailDTO);
						}
						
						ItemEntity itemEntity= indentDetailEntity.getItemEntity();
						if(itemEntity!=null){
							ItemDTO itemDTO=new ItemDTO();
							copyObject(itemEntity, itemDTO);
							indentDetailDTO.setItemDTO(itemDTO);
						}
						
						
						if(indentDetailEntity.getMeasurementUnitMasterEntity()!=null){
							MastersDTO mastersDTO = new MastersDTO();
							mastersDTO.setMastersId(indentDetailEntity.getMeasurementUnitMasterEntity().getMastersId());
							mastersDTO.setName(indentDetailEntity.getMeasurementUnitMasterEntity().getName());
							indentDetailDTO.setMeasurementUnitId(mastersDTO);
						}
					}
					
				}
				
				
				
				indentMasterDTO.setIndentDetailDTO(indentDetailDTOList);				
				resultList.add(indentMasterDTO);
			}
			indentOutputMessage.setIndentMasterDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		
	}
	
	private List<IndentDetailEntity> convertIndentDetailDtoTOIndentDetailEntity(List<IndentDetailDTO>dtoList,IndentMasterDTO indentMasterDTO){
		logger.info("convertIssueDetailDtoTOIssueDetailMasterEntity       Deatil dtoList  = "+ dtoList);	
		List<IndentDetailEntity> entityList=new ArrayList<IndentDetailEntity>();
		for(IndentDetailDTO dto:dtoList){
			IndentDetailEntity entity=new IndentDetailEntity();
			copyObject(dto, entity);
			if(dto!=null){
				ItemEntity itemEntity=new ItemEntity();
				itemEntity.setItemId(dto.getItemId());
				entity.setItemEntity(itemEntity);		
			
			}
			if(dto.getMeasurementUnitId()!=null){
				MastersEntity measurementUnitMasterEntity=new MastersEntity();
				copyObject(dto.getMeasurementUnitId(), measurementUnitMasterEntity);
				entity.setMeasurementUnitMasterEntity(measurementUnitMasterEntity); 
			}
			if(indentMasterDTO.getCreatedUserId()!=null){
				entity.setCreatedUserId(indentMasterDTO.getCreatedUserId());
			}if(indentMasterDTO.getModifiedUserId()!=null){
				entity.setModifiedUserId(indentMasterDTO.getModifiedUserId());
			}
			entityList.add(entity);
		}
		return entityList;
		
	}
	
	private List<IssueDetailMasterDTO> convertIssueDetailEntityListTOIssueDetailDtoList(
			List<IssueDetailMasterEntity> list) {

		indentOutputMessage = new IndentOutputMessage();
		List<IssueDetailMasterDTO> resultList = null;
		// set the data to the outputput message.
		if (list != null) {
			IssueDetailMasterDTO issueDetailDTO;
			resultList = new ArrayList<IssueDetailMasterDTO>();
			for (IssueDetailMasterEntity issueDetailEntity : list) {
				issueDetailDTO = new IssueDetailMasterDTO();
				if (issueDetailEntity != null) {
					BeanUtils.copyProperties(issueDetailEntity,issueDetailDTO);
					resultList.add(issueDetailDTO);
				}
			}
		}

		return resultList;
	}
	
	private void copyObject(Object source,Object target){
		
		try {
			BeanUtils.copyProperties(source, target);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
