package com.advanz.erp.masters.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
import com.advanz.erp.masters.entity.jpa.IssueDetailMasterEntity;
import com.advanz.erp.masters.entity.jpa.IssueMasterEntity;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.ExciseLedgerDTO;
import com.advanz.erp.masters.model.IssueDetailMasterDTO;
import com.advanz.erp.masters.model.IssueMasterDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.StockLedgerDTO;
import com.advanz.erp.masters.model.criteria.IssueMasterSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.ExciseLedgerInputMessage;
import com.advanz.erp.masters.model.msg.IssueInputMessage;
import com.advanz.erp.masters.model.msg.IssueOutputMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.StockLedgerInputMessage;
import com.advanz.erp.masters.model.msg.StockLedgerOutMessage;
import com.advanz.erp.masters.service.business.IExciseLedgerService;
import com.advanz.erp.masters.service.business.IIssueMasterService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IStockLedgerService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageIssueDAO;
import com.advanz.erp.masters.storage.IStorageIssueReturnDAO;

public class IssueMasterServiceImpl implements IIssueMasterService {

	public static final String CREATE_ISSUE_MASTER = "CreateIssueMaster";
	public static final String UPDATE_ISSUE_MASTER = "UpdateIssueMaster";
	public static final String DELETE_ISSUE_MASTER = "DeleteIssueMaster";
	public static final String FIND_ISSUE_MASTER_BY_ID = "FindIssueMasterById";
	public static final String FIND_ALL_ISSUE_MASTER = "FindAllIssueMasters";
	public static final String SEARCH_ISSUE_MASTER = "SearchIssueMasters";
	public static final String NEW_ISSUE_MASTER_SERIES_NO = "NewIssueMastersSeriesNo";
	public static final String FIND_ISSUE_MASTER_PAGINATION = "FindIssueMasterPagination";
	
	public static final String FIND_BY_ISSUE_NO_AND_ITEM_ID = "FindByIssueNoAndItemId";
	public String flowId = "";
	private static final Logger logger = LoggerFactory.getLogger(IssueMasterServiceImpl.class);
	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
	@Autowired
	public IExciseLedgerService exciseLedgerService;
	// autowiring

	@Autowired
	public IStorageIssueDAO storageIssueDAO;

	public IssueInputMessage issueInputMessage;

	public IssueOutputMessage issueOutputMessage;

	@Autowired
	public IItemService itemService;
	
	@Autowired
	public IStockLedgerService stockLedgerService; 
	
	@Autowired
	public IStorageIssueReturnDAO storageIssueReturnDAO;
	
	@Autowired
	public IZoneService zoneService;
	
	@Override
	public IssueOutputMessage createIssueMaster(IssueInputMessage issueInputMessage) {

		flowId = CREATE_ISSUE_MASTER;
		// assign the message to the class level variable.
		this.issueInputMessage = issueInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return issueOutputMessage;
	}

	@Override
	public IssueOutputMessage updateIssueMaster(IssueInputMessage issueInputMessage) {

		flowId = UPDATE_ISSUE_MASTER;
		// assign the message to the class level variable.
		this.issueInputMessage = issueInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return issueOutputMessage;
	}

	@Override
	public IssueOutputMessage deleteIssueMaster(IssueInputMessage issueInputMessage) {
		flowId = DELETE_ISSUE_MASTER;
		// assign the message to the class level variable.
		this.issueInputMessage = issueInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return issueOutputMessage;

	}

	@Override
	public IssueOutputMessage findIssueMasterById(IssueInputMessage issueInputMessage) {
		flowId = FIND_ISSUE_MASTER_BY_ID;
		// assign the message to the class level variable.
		this.issueInputMessage = issueInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return issueOutputMessage;

	}

	@Override
	public IssueOutputMessage findAllIssueMasters() {
		flowId = FIND_ALL_ISSUE_MASTER;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return issueOutputMessage;
	}
	
	@Override
	public IssueOutputMessage search(IssueInputMessage issueInputMessage) {
		flowId = SEARCH_ISSUE_MASTER;
		this.issueInputMessage=issueInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return issueOutputMessage;

	}
	
	@Override
	public IssueOutputMessage getNewIssueSeriesNo(IssueInputMessage issueInputMessage) {
		flowId = NEW_ISSUE_MASTER_SERIES_NO;
		this.issueInputMessage=issueInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return issueOutputMessage;
	}
	@Override
	public IssueOutputMessage findIssueMasterPagination(
	IssueInputMessage issueInputMessage) {
	flowId = FIND_ISSUE_MASTER_PAGINATION;
	this.issueInputMessage=issueInputMessage;
	// call the template method
	advanzErpServiceTemplate.execute(this);
	return issueOutputMessage;
	}

	@Override
	public boolean validateInput() {

		if (CREATE_ISSUE_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_ISSUE_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_ISSUE_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ISSUE_MASTER_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_ISSUE_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_ISSUE_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (NEW_ISSUE_MASTER_SERIES_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_ISSUE_MASTER_PAGINATION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
			}else if (FIND_BY_ISSUE_NO_AND_ITEM_ID.equals(flowId)) {
				// TODO add business validation on the input.
				return true;
				}
		
		return false;
	}

	@Override
	public void performBusinessLogic() {

		IssueMasterEntity issueMasterEntity = new IssueMasterEntity();
		
		List<IssueDetailMasterDTO> issueDetailMasterDTOList=null;
		IssueMasterDTO issueMasterDTO =null;
		if (issueInputMessage != null) {
			 issueMasterDTO = issueInputMessage.getIssueMasterDTO();
			if (issueMasterDTO != null) {
				BeanUtils.copyProperties(issueMasterDTO, issueMasterEntity);
			//	PartyDTO partyDTO=issueMasterDTO.getPartyDTO();
				BranchDTO branchDTO=issueMasterDTO.getBranchDTO();
				if(branchDTO!=null){
					BranchEntity branchEntity=new BranchEntity();
					copyObject(branchDTO, branchEntity);
					issueMasterEntity.setBranchEntity(branchEntity);
				}
				 issueDetailMasterDTOList=issueMasterDTO.getIssueDetailMasterDTOList();
				logger.info("test Deatil List = "+ issueDetailMasterDTOList);				
				if(issueDetailMasterDTOList!=null && issueDetailMasterDTOList.size()>0){
					IssueDetailMasterDTO detailMasterDTO=  issueDetailMasterDTOList.get(0);
					List<IssueDetailMasterEntity>issueDetailMasterEntity=convertIssueDetailDtoTOIssueDetailMasterEntity(issueDetailMasterDTOList,issueMasterDTO);	
				issueMasterEntity.setIssueDetailMasterEntity(issueDetailMasterEntity);
			
	        }
		 }
		}

		if (CREATE_ISSUE_MASTER.equals(flowId)) {
			// check duplicate finishedGoodsMaster name
			List<IssueMasterEntity> list = storageIssueDAO.findByIssuesNumber(issueMasterEntity.getIssueNumber());
		
			
		//	issueOutputMessage = new IssueOutputMessage();
			if (list != null && list.size() > 0) {
				
				ErrorDTO errorDTO = new ErrorDTO("1", issueMasterEntity.getIssueNumber()+" Issue Number is already exist,it can't be duplicate ");
			    ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				issueOutputMessage = new IssueOutputMessage();
				issueOutputMessage.setErrorListDTO(errorListDTO);
			  
			}else{
				
				
				 if(issueDetailMasterDTOList!=null && issueDetailMasterDTOList.size()>0){
					 storageIssueDAO.create(issueMasterEntity);
					 for(int i=0;i<issueDetailMasterDTOList.size();i++){
						 IssueDetailMasterDTO issueDetailDTO1=(IssueDetailMasterDTO ) issueDetailMasterDTOList.get(i);
						 StockLedgerDTO stockLedgerDTO = new StockLedgerDTO();
						    ItemInputMessage itemInputMessage = new ItemInputMessage();
							ItemDTO itemDTO = new ItemDTO();
							itemDTO.setItemId(issueDetailDTO1.getItemId());
							itemInputMessage.setItemDTO(itemDTO);
							ItemOutMessage itemOutMessage = itemService.findItemById(itemInputMessage);
						    ArrayList<ItemDTO> itemList =(ArrayList<ItemDTO>)itemOutMessage.getItemDTOList();
						    if(itemList!=null && itemList.size()>0){
							itemDTO=itemList.get(0);
							}
						stockLedgerDTO.setSalesRate(itemDTO.getSalesRate());
						try{
						stockLedgerDTO.setSalesValue(0-((itemDTO.getSalesRate())*(issueDetailDTO1.getIssueQuantity())));
						}catch (Exception e) {
						}
						
						 stockLedgerDTO.setTransactionNumber(issueDetailDTO1.getIssueNumber());
						 stockLedgerDTO.setTransactionDate(issueMasterDTO.getIssueDate());
						 stockLedgerDTO.setBranchId(issueMasterDTO.getBranchDTO().getBranchId());
						 stockLedgerDTO.setTransactionSeries(issueDetailDTO1.getTransactionSeries());
						 stockLedgerDTO.setTransactionId(issueMasterDTO.getIssueId());
						 stockLedgerDTO.setItemId(issueDetailDTO1.getItemId());
						 
						 if(issueDetailDTO1.getIssueQuantity()!=null){
						 stockLedgerDTO.setQuantity(0-issueDetailDTO1.getIssueQuantity());
						 }
						 else{
							 stockLedgerDTO.setQuantity(0.0);
						 }
						 if(issueMasterDTO.getApproved()!=null && issueMasterDTO.getApproved()>0){
							 stockLedgerDTO.setApprovedDate(new Date());
						 }
						 stockLedgerDTO.setApprovedQuantity(issueDetailDTO1.getIssueQuantity());
						 
						 StockLedgerInputMessage stockLedgerInputMessage =new StockLedgerInputMessage();
						 stockLedgerInputMessage.setStockLedgerDTO(stockLedgerDTO);
						
						
						 stockLedgerService.createStockLedger(stockLedgerInputMessage);
						 
						ExciseLedgerDTO exciseLedgerDTO=new ExciseLedgerDTO();	
							
						   ItemInputMessage itemInputMessage2 = new ItemInputMessage();
							ItemDTO itemDTO2 = new ItemDTO();
							itemDTO2.setItemId(issueDetailDTO1.getItemId());
							itemInputMessage2.setItemDTO(itemDTO2);
							ItemOutMessage itemOutMessage2 = itemService.findItemById(itemInputMessage);
						    ArrayList<ItemDTO> itemList2 =(ArrayList<ItemDTO>)itemOutMessage2.getItemDTOList();
							
						    if(itemList2!=null && itemList2.size()>0){
							itemDTO2=itemList2.get(0);
							}
						  //exciseLedgerDTO.setTransactionNumber(issueDetailDTO1.getIssueNumber());
						    exciseLedgerDTO.setApprovedDate(new Date());
						    exciseLedgerDTO.setTransactionDate(issueMasterDTO.getIssueDate());
						    exciseLedgerDTO.setBranchId(issueMasterDTO.getBranchDTO().getBranchId());
						    exciseLedgerDTO.setTransactionSeries(issueDetailDTO1.getTransactionSeries());
						    exciseLedgerDTO.setNarration(issueMasterDTO.getIssueRemark());
						    exciseLedgerDTO.setItemId(issueDetailDTO1.getItemId());
						    exciseLedgerDTO.setIssueNumber(issueDetailDTO1.getIssueNumber());
						    if(issueDetailDTO1.getIssueQuantity()!=null){
							 exciseLedgerDTO.setIssueQty(0-issueDetailDTO1.getIssueQuantity());
						 }
						 else{
							 exciseLedgerDTO.setIssueQty(0.0);
						 }
						ExciseLedgerInputMessage exciseLedgerInputMessage=new ExciseLedgerInputMessage();
						exciseLedgerInputMessage.setExciseLedgerDTO(exciseLedgerDTO);
						 if(issueMasterDTO.getApproved()!=null && issueMasterDTO.getApproved()>0){
						exciseLedgerService.createExciseLedger(exciseLedgerInputMessage);
					 }}}
				 
			}
			
			
		} else if (UPDATE_ISSUE_MASTER.equals(flowId)) {
			
			//STOCK DELETE
			// StockLedgerInputMessage stockLedgerInputMessage =new StockLedgerInputMessage();
			 StockLedgerInputMessage sn1 =new StockLedgerInputMessage();
			 StockLedgerDTO sd=new StockLedgerDTO();
			 sd.setTransactionNumber(issueMasterDTO.getIssueNumber());
			 sn1.setStockLedgerDTO(sd);
			 StockLedgerOutMessage ledgerOutMessage1=stockLedgerService.findStockLedgerByTransactionId(sn1);
			 List<StockLedgerDTO> ledgerDTOList1=ledgerOutMessage1.getStockLedgerDTOList();
			 for(int c=0;c<ledgerDTOList1.size();c++)
			 {
				 sd=new StockLedgerDTO();
				 StockLedgerInputMessage sn=new StockLedgerInputMessage();
				 sd= ledgerDTOList1.get(c);
				 sn.setStockLedgerDTO(sd);
				// stockLedgerService.updateStockLedger(stockLedgerInputMessage);
				 stockLedgerService.deleteStockLedger(sn);
			     }
			//STOCK DELETE END
			
			
			
			
			 if(issueDetailMasterDTOList!=null && issueDetailMasterDTOList.size()>0){
				 for(int i=0;i<issueDetailMasterDTOList.size();i++){
					IssueDetailMasterDTO issueDetailDTO1=(IssueDetailMasterDTO ) issueDetailMasterDTOList.get(i);
					StockLedgerDTO stockLedgerDTO = new StockLedgerDTO();
					    ItemInputMessage itemInputMessage = new ItemInputMessage();
						ItemDTO itemDTO = new ItemDTO();
						itemDTO.setItemId(issueDetailDTO1.getItemId());
						itemInputMessage.setItemDTO(itemDTO);
						ItemOutMessage itemOutMessage = itemService.findItemById(itemInputMessage);
					    ArrayList<ItemDTO> itemList =(ArrayList<ItemDTO>)itemOutMessage.getItemDTOList();
					    if(itemList!=null && itemList.size()>0){
						itemDTO=itemList.get(0);
						}
					stockLedgerDTO.setSalesRate(itemDTO.getSalesRate());
					try{
					stockLedgerDTO.setSalesValue(0-((itemDTO.getSalesRate())*(issueDetailDTO1.getIssueQuantity())));
					}catch (Exception e) {
					}
					 stockLedgerDTO.setTransactionNumber(issueDetailDTO1.getIssueNumber());
					 stockLedgerDTO.setTransactionDate(issueMasterDTO.getIssueDate());
					 stockLedgerDTO.setBranchId(issueMasterDTO.getBranchDTO().getBranchId());
					 stockLedgerDTO.setTransactionSeries(issueDetailDTO1.getTransactionSeries());
					 stockLedgerDTO.setTransactionId(issueMasterDTO.getIssueId());
					 stockLedgerDTO.setItemId(issueDetailDTO1.getItemId());
					 
					 if(issueDetailDTO1.getIssueQuantity()!=null){
					 stockLedgerDTO.setQuantity(0-issueDetailDTO1.getIssueQuantity());
					 }
					 else{
						 stockLedgerDTO.setQuantity(0.0);
					 }
					 if(issueMasterDTO.getApproved()!=null && issueMasterDTO.getApproved()>0){
						 stockLedgerDTO.setApprovedDate(new Date());
					 }
					 stockLedgerDTO.setApprovedQuantity(issueDetailDTO1.getIssueQuantity());
					 
					 StockLedgerInputMessage stockLedgerInputMessage =new StockLedgerInputMessage();
					 stockLedgerInputMessage.setStockLedgerDTO(stockLedgerDTO);

						 stockLedgerInputMessage.setStockLedgerDTO(stockLedgerDTO);
						 stockLedgerService.createStockLedger(stockLedgerInputMessage);
					 
					 ExciseLedgerDTO exciseLedgerDTO=new ExciseLedgerDTO();	
						
					   ItemInputMessage itemInputMessage2 = new ItemInputMessage();
						ItemDTO itemDTO2 = new ItemDTO();
						itemDTO2.setItemId(issueDetailDTO1.getItemId());
						itemInputMessage2.setItemDTO(itemDTO2);
						ItemOutMessage itemOutMessage2 = itemService.findItemById(itemInputMessage);
					    ArrayList<ItemDTO> itemList2 =(ArrayList<ItemDTO>)itemOutMessage2.getItemDTOList();
						
					    if(itemList2!=null && itemList2.size()>0){
						itemDTO2=itemList2.get(0);
						}
					  //exciseLedgerDTO.setTransactionNumber(issueDetailDTO1.getIssueNumber());
					    exciseLedgerDTO.setTransactionDate(issueMasterDTO.getIssueDate());
					    exciseLedgerDTO.setApprovedDate(new Date());
					    exciseLedgerDTO.setBranchId(issueMasterDTO.getBranchDTO().getBranchId());
					    exciseLedgerDTO.setTransactionSeries(issueDetailDTO1.getTransactionSeries());
					    exciseLedgerDTO.setNarration(issueMasterDTO.getIssueRemark());
					    exciseLedgerDTO.setItemId(issueDetailDTO1.getItemId());
					    exciseLedgerDTO.setIssueNumber(issueDetailDTO1.getIssueNumber());
					    if(issueDetailDTO1.getIssueQuantity()!=null){
						 exciseLedgerDTO.setIssueQty(0-issueDetailDTO1.getIssueQuantity());
					 }
					 else{
						 exciseLedgerDTO.setIssueQty(0.0);
					 }		    
					ExciseLedgerInputMessage exciseLedgerInputMessage=new ExciseLedgerInputMessage();
					exciseLedgerInputMessage.setExciseLedgerDTO(exciseLedgerDTO);
					 if(issueMasterDTO.getApproved()!=null && issueMasterDTO.getApproved()>0){
					exciseLedgerService.createExciseLedger(exciseLedgerInputMessage);
	
								 }}}
			storageIssueDAO.update(issueMasterEntity);
	
		} 
		else if (DELETE_ISSUE_MASTER.equals(flowId)) {
			
			
			
			
			List<IssueMasterEntity> issueMasterEntityList =storageIssueDAO.findIssueNumber(issueMasterEntity.getIssueAutoId());
			ExciseLedgerInputMessage exciseLedgerInputMessage=new ExciseLedgerInputMessage();
			ExciseLedgerDTO exciseLedgerDTO=new ExciseLedgerDTO();
			exciseLedgerDTO.setIssueNumber(issueMasterEntityList.get(0).getIssueNumber());
			// To check this issue numebr use or not in issue return
		    List issueReturnDetailList=	storageIssueReturnDAO.getIssueDeatilListByIssueNumber(issueMasterEntityList.get(0).getIssueNumber());
			if(issueReturnDetailList!=null && issueReturnDetailList.size()>0){
			
					
					ErrorDTO errorDTO = new ErrorDTO("1","Sorry you can not use this issue number because it is used in issue return");
				    ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					issueOutputMessage = new IssueOutputMessage();
					issueOutputMessage.setErrorListDTO(errorListDTO);
			}
			else{
				
				exciseLedgerInputMessage.setExciseLedgerDTO(exciseLedgerDTO);
				exciseLedgerService.removeExciseLedgerByIssue(exciseLedgerInputMessage);
				StockLedgerInputMessage stockInputmessage = new StockLedgerInputMessage();
				StockLedgerDTO stockLedgerDTO=new StockLedgerDTO();
				stockLedgerDTO.setTransactionNumber(issueMasterEntityList.get(0).getIssueNumber());
				stockInputmessage.setStockLedgerDTO(stockLedgerDTO);
				
				//stockLedgerService.deleteByTransactionNumber(stockInputmessage);
				
				StockLedgerOutMessage stockLedgerOutMessage = stockLedgerService.findStockLedgerByTransactionId(stockInputmessage);
	            ArrayList<StockLedgerDTO>  stockLedgerList=(ArrayList<StockLedgerDTO>)stockLedgerOutMessage.getStockLedgerDTOList();
		    	for(int i=0;i<stockLedgerList.size();i++) {
		    		stockLedgerDTO = stockLedgerList.get(i);
				    stockInputmessage.setStockLedgerDTO(stockLedgerDTO);
				    stockLedgerService.deleteStockLedger(stockInputmessage);
				  }
				storageIssueDAO.delete(issueMasterEntity);
				
			}
			
		}
		
		else if (FIND_ISSUE_MASTER_BY_ID.equals(flowId)) {
			logger.info("issueMasterEntity.getFinishedGoodsAutoId() >>>>>>>>>>>> : "+ issueMasterEntity.getIssueAutoId());
		List<IssueMasterEntity>list=	storageIssueDAO.findById(issueMasterEntity.getIssueAutoId());
		logger.info("IssueMasterEntity for id("+issueMasterEntity.getIssueAutoId()+") :"+list);
		if(list!=null && list.size()>0){
		//logger.info("party Id" +list.get(0).getPartyEntity().getPartyId());
		logger.info("Detail List"+list.get(0).getIssueDetailMasterEntity());
		}
		popUpList(list);
		
		
		} else if (FIND_ALL_ISSUE_MASTER.equals(flowId)) {
			List<IssueMasterEntity> list = storageIssueDAO.load();
			popUpList(list);			
		}
		else if (SEARCH_ISSUE_MASTER.equals(flowId)) {
			IssueMasterSearchCriteriaDTO searchCriteria=issueInputMessage.getSearchCriteria();
			List<IssueMasterEntity> list = storageIssueDAO.search(searchCriteria.getIssueNumber(),searchCriteria.getFromDate(),searchCriteria.getToDate(),searchCriteria.getIssueType(),searchCriteria.getItemName(),searchCriteria.getRaisedBy());
			popUpList(list);			
		}
		else if(NEW_ISSUE_MASTER_SERIES_NO.equals(flowId)){
			Integer seriesNo=0;
			Timestamp date = zoneService.getFirstDayOfFinYear();
			List list=storageIssueDAO.getNewSeriesNo(issueMasterEntity.getFinYear());
			if (list != null && list.size() > 0) {
				Object[] obj=(Object[]) list.get(0);
				
				Number n = (Number) obj[0];
				if (n != null)
					seriesNo = n.intValue();
				if(obj[1]!=null && obj[1]!="")
					date=(Timestamp)obj[1];
			}
			seriesNo++;
			
			issueOutputMessage = new IssueOutputMessage();
			issueOutputMessage.setIssueSeriesNo(seriesNo);
			issueOutputMessage.setIssueSeriesDate(date);
		}
		else if (FIND_ISSUE_MASTER_PAGINATION.equals(flowId)) {
			List<IssueMasterEntity> list = storageIssueDAO.findMaterialIssuePagination(issueInputMessage.getIssueMasterDTO().getNext());
			popUpList(list);
			}


	}
	void popUpList(List<IssueMasterEntity> list) {
		logger.info("SOM Entity List  :"+list);
		issueOutputMessage = new IssueOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<IssueMasterDTO> resultList = new ArrayList<IssueMasterDTO>();
			IssueMasterDTO issueMasterDTO;
			for (IssueMasterEntity entity : list) {
				issueMasterDTO = new IssueMasterDTO();
				// Spring				
				BeanUtils.copyProperties(entity, issueMasterDTO);
				
//				PartyEntity partyEntity=entity.getPartyEntity();
//				if(partyEntity!=null){
//					PartyDTO partyDTO=new PartyDTO();
//					copyObject(partyEntity, partyDTO);
//					issueMasterDTO.setPartyDTO(partyDTO);
//				}
				
				BranchEntity branchEntity=entity.getBranchEntity();
				if(branchEntity!=null){
					BranchDTO branchDTO=new BranchDTO();
					copyObject(branchEntity, branchDTO);
					issueMasterDTO.setBranchDTO(branchDTO);
				}
				
				
				/*IssueTypeMasterEntity  issueTypeMasterEntity =entity.getIssueTypeMasterEntity();
				if(issueTypeMasterEntity!=null){
					IssueTypeMasterDTO typeMasterDTO=new IssueTypeMasterDTO();
					copyObject(issueTypeMasterEntity, typeMasterDTO);
					issueMasterDTO.setIssueTypeMasterDTO(typeMasterDTO);
				}*/
				
				
				
				List<IssueDetailMasterEntity> issueDetailEntityList=entity.getIssueDetailMasterEntity();
				List<IssueDetailMasterDTO> issueDetailMasterDTOList=new ArrayList<IssueDetailMasterDTO>();
				
				if(issueDetailEntityList!=null && issueDetailEntityList.size()>0){
					for(IssueDetailMasterEntity issueDetailEntity:issueDetailEntityList){
						IssueDetailMasterDTO issueGoodsDetailDTO=new IssueDetailMasterDTO();
						
						
						copyObject(issueDetailEntity,issueGoodsDetailDTO);	
						if(issueDetailEntity.getItemEntity()!=null){
							issueGoodsDetailDTO.setItemId(issueDetailEntity.getItemEntity().getItemId());
							issueGoodsDetailDTO.setItemName(issueDetailEntity.getItemEntity().getInvoiceName());
							//	finishedGoodsDetailDTO.setPartyInvoiceType(partyEntity.getInvoiceType());
						issueDetailMasterDTOList.add(issueGoodsDetailDTO);
						}
						if(issueDetailEntity.getMeasurementUnitMasterEntity()!=null){
							MastersDTO mastersDTO = new MastersDTO();
							mastersDTO.setMastersId(issueDetailEntity.getMeasurementUnitMasterEntity().getMastersId());
							issueGoodsDetailDTO.setMeasurementUnitId(mastersDTO);
							issueGoodsDetailDTO.setMeasurementUnitName(issueDetailEntity.getMeasurementUnitMasterEntity().getName());
							
							//issueDetailMasterDTOList.add(issueGoodsDetailDTO);
						}
					}
					
				}
				
				
				
				issueMasterDTO.setIssueDetailMasterDTOList(issueDetailMasterDTOList);				
				resultList.add(issueMasterDTO);
			}
			issueOutputMessage.setIssueMasterDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (CREATE_ISSUE_MASTER.equals(flowId)) {

		} else if (UPDATE_ISSUE_MASTER.equals(flowId)) {

		} else if (DELETE_ISSUE_MASTER.equals(flowId)) {

		} else if (FIND_ISSUE_MASTER_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_ISSUE_MASTER.equals(flowId)) {

		} else if (SEARCH_ISSUE_MASTER.equals(flowId)) {

		}else if (NEW_ISSUE_MASTER_SERIES_NO.equals(flowId)) {

		}
		
	}
	
	private List<IssueDetailMasterEntity> convertIssueDetailDtoTOIssueDetailMasterEntity(List<IssueDetailMasterDTO>dtoList,IssueMasterDTO issueMasterDTO){
		logger.info("convertIssueDetailDtoTOIssueDetailMasterEntity       Deatil dtoList  = "+ dtoList);	
		List<IssueDetailMasterEntity> entityList=new ArrayList<IssueDetailMasterEntity>();
		for(IssueDetailMasterDTO dto:dtoList){
			IssueDetailMasterEntity entity=new IssueDetailMasterEntity();
			logger.debug("DTO Value issue num:---"+dto.getIssueNumber());
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
			if(issueMasterDTO.getCreatedUserId()!=null){
				entity.setCreatedUserId(issueMasterDTO.getCreatedUserId());
			}if(issueMasterDTO.getModifiedUserId()!=null){
				entity.setModifiedUserId(issueMasterDTO.getModifiedUserId());
			}
			entityList.add(entity);
		}
		return entityList;
		
	}
	@Override
	public List findByIssueNoAndItemId(
			String issueNumber,Integer itemId) {
		List<IssueDetailMasterEntity> el= storageIssueDAO.findByIssueNoAndItemId(issueNumber, itemId);
		List<IssueDetailMasterDTO> list=convertIssueDetailEntityListTOIssueDetailDtoList(el);
		return list;
	}
	@Override
	public Date findIssueDateByIssueNo(String issueNumber)
	{
		List l=	storageIssueDAO.findIssueDateByIssueNo(issueNumber);
	    Timestamp issueDate=(Timestamp)l.get(0);
	    java.sql.Date date = new java.sql.Date(issueDate.getTime());
		return date;
	}
	private List<IssueDetailMasterDTO> convertIssueDetailEntityListTOIssueDetailDtoList(
			List<IssueDetailMasterEntity> list) {

		issueOutputMessage = new IssueOutputMessage();
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
	@Override
	public IssueDetailMasterDTO updateIssueDetail(
			IssueDetailMasterDTO detailMasterDTO) {
		//To find Pending quantity from issue detail to update pending quantity
		    List<IssueDetailMasterEntity> detailEntityList=storageIssueDAO.findByIssueNoAndItemId(detailMasterDTO.getIssueNumber(), detailMasterDTO.getItemId());
		    if(detailEntityList!=null && detailEntityList.size()>0){
			for(int i=0;i<detailEntityList.size();i++){
				IssueDetailMasterEntity issueDetailEntity=	detailEntityList.get(i);
				Double pq= 0.0;
				if("Delete".equalsIgnoreCase(detailMasterDTO.getOperationFlag())){
					 pq= issueDetailEntity.getPendingQuantity();
				}else{
					 pq= issueDetailEntity.getIssueQuantity();
				}
				pq=pq-detailMasterDTO.getPendingQuantity();
				issueDetailEntity.setPendingQuantity(pq);
				storageIssueDAO.updateIssueDetail(issueDetailEntity);
			}}
		return null;
	}}
