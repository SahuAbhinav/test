package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.BatchEntity;
import com.advanz.erp.masters.entity.jpa.BillEntity;
import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.entity.jpa.EmployeeEntity;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.PartyEntity;
import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.BillDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.msg.BatchInputMessage;
import com.advanz.erp.masters.model.msg.BatchOutMessage;
import com.advanz.erp.masters.model.msg.BillOutMessage;
import com.advanz.erp.masters.model.msg.ItemCategoryOutMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.service.business.IBatchService;
import com.advanz.erp.masters.storage.IStorageBatchDAO;
import com.advanz.erp.masters.storage.IStorageItemDAO;
import com.advanz.erp.masters.storage.IStorageLeaveTypeMastDAO;
import com.advanz.erp.masters.storage.IStorageStockLedgerDAO;

@Service
public class BatchServiceImpl implements IBatchService {

	public static final String CREATE_BATCH = "Batch";
	public static final String UPDATE_BATCH = "UpdateBatch";
	public static final String ADD_BATCH = "AddBatch";
	public static final String DELETE_BATCH = "DeleteBatch";
	public static final String FIND_BATCH_BY_NO = "FindBatchByNo";
	public static final String FIND_ALL_BATCHES = "FindAllCompnies";
	public static final String FIND_BATCH_BY_ITEM = "FindBatchByItem";
	public static final String FIND_ALL_BATCH_BY_ITEM = "FindAllBatchByItem";
	public static final String FIND_BATCH_BY_ID = "FindBatchByID";
	public static final String SEARCH_BATCH = "SearchBatch";
	public static final String PRE_REMOVE_CHECK="PreRemoveCheck";
	
	public static final String FIND_BY_SNO="FindBySno";
	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO

	private static final Logger logger = LoggerFactory
			.getLogger(BatchServiceImpl.class); // do
	// autowiring

	@Autowired
	public IStorageBatchDAO storageBatchDAO;
	
	@Autowired
	public IStorageItemDAO storageItemDAO;
	
	@Autowired
	public IStorageStockLedgerDAO storageStockLedgerDAO;

	public BatchInputMessage batchInputMessage;

	public BatchOutMessage batchOutMessage;

	@Override
	public BatchOutMessage createBatch(BatchInputMessage batchInputMessage) {

		flowId = ADD_BATCH;

		// assign the message to the class level variable.
		this.batchInputMessage = batchInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return batchOutMessage;
	}

	@Override
	public BatchOutMessage updateBatch(BatchInputMessage batchInputMessage) {

		flowId = UPDATE_BATCH;
		this.batchInputMessage = batchInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return batchOutMessage;
	}

	@Override
	public BatchOutMessage deleteBatch(BatchInputMessage batchInputMessage) {
		flowId = DELETE_BATCH;
		this.batchInputMessage = batchInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return batchOutMessage;

	}

	@Override
	public BatchOutMessage searchBatch(BatchInputMessage batchInputMessage) {
		flowId = SEARCH_BATCH;
		this.batchInputMessage = batchInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return batchOutMessage;

	}

	@Override
	public BatchOutMessage findBatchByBatchNo(
			BatchInputMessage batchInputMessage) {
		flowId = FIND_BATCH_BY_NO;
		this.batchInputMessage = batchInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return batchOutMessage;

	}

	@Override
	public BatchOutMessage findBatchById(BatchInputMessage batchInputMessage) {
		flowId = FIND_BATCH_BY_ID;
		this.batchInputMessage = batchInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return batchOutMessage;

	}

	@Override
	public BatchOutMessage findAllBatches() {
		flowId = FIND_ALL_BATCHES;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return batchOutMessage;
	}

	
	@Override
	public BatchOutMessage findBatchByBatchItemNo(
			BatchInputMessage batchInputMessage) {
		flowId = FIND_BATCH_BY_ITEM;
		this.batchInputMessage = batchInputMessage;

		// call the template method
		advanzErpServiceTemplate.execute(this);

		return batchOutMessage;
	}

	@Override
	public BatchOutMessage findAllBatchByItemId(
			BatchInputMessage batchInputMessage) {
		flowId = FIND_ALL_BATCH_BY_ITEM;
			this.batchInputMessage = batchInputMessage;
			// call the template method
			advanzErpServiceTemplate.execute(this);
			return batchOutMessage;
	}
	
	@Override
	public BatchOutMessage checkBeforeRemove(BatchInputMessage batchInputMessage) {
		flowId = PRE_REMOVE_CHECK;
		this.batchInputMessage = batchInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return batchOutMessage;
	}
	@Override
	public BatchOutMessage findBySno(
			BatchInputMessage batchInputMessage) {
		flowId = FIND_BY_SNO;
		this.batchInputMessage = batchInputMessage;

		// call the template method
		advanzErpServiceTemplate.execute(this);

		return batchOutMessage;
	}
	@Override
	public boolean validateInput() {

		if (ADD_BATCH.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_BATCH.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_BATCH.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_BATCH_BY_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_BATCH_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} 
		else if (FIND_ALL_BATCH_BY_ITEM.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_BATCHES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_BATCH_BY_ITEM.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_BATCH.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (PRE_REMOVE_CHECK.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_BY_SNO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		
		return false;
	}

	@Override
	public void performBusinessLogic() {
		logger.info("follow ID : " + flowId);
		BatchEntity batchEntity = new BatchEntity();
		if (batchInputMessage != null) {
			BatchDTO batchDTO = batchInputMessage.getBatchDTO();
			if (batchDTO != null) {
				BeanUtils.copyProperties(batchDTO, batchEntity);

				if (batchDTO.getItemDTO() != null) {
					batchEntity.setItem(new ItemEntity());
					BeanUtils.copyProperties(batchDTO.getItemDTO(),
							batchEntity.getItem());
				}
			}
		}
		// BeanUtils.copyProperties(batchInputMessage.getBatchDTO(),
		// batchEntity);

		if (ADD_BATCH.equals(flowId)) {
			
			try{
				storageBatchDAO.create(batchEntity);
				batchOutMessage.setErrorListDTO(null);
			}catch (Exception e) {
			}
			//}
		} else if (UPDATE_BATCH.equals(flowId)) {
			// batchEntity.setDeletedFlag(0);
			try{
			storageBatchDAO.update(batchEntity);
			}catch (Exception e) {
			}
		} else if (DELETE_BATCH.equals(flowId)) {
			storageBatchDAO.delete(batchEntity);
		} else if (FIND_BATCH_BY_NO.equals(flowId)) {
			List<BatchEntity> list = storageBatchDAO.findByBatchNo(batchEntity
					.getBatchNo());
			popUpList(list);
			//logger.info("************---: " + list);
		}else
		if (FIND_BATCH_BY_ID.equals(flowId)) {
		//	System.out.println("------------------------------------Find By Id : "+ batchEntity.getBatchNo());
			List<BatchEntity> list = storageBatchDAO.findById(batchEntity.getBatchNo());
			
			popUpList(list);
		//	logger.info("************---: " + list);
		}
		if (FIND_BY_SNO.equals(flowId)) {
			List<BatchEntity> list = storageBatchDAO.findBySno(batchEntity.getSno());
			popUpList(list);
		//	logger.info("************---: " + list);
		}
		
		else if (FIND_ALL_BATCHES.equals(flowId)) {
			System.out
					.println("------------------------------------Loading All");
			List<BatchEntity> list = storageBatchDAO.load();
			popUpList(list);
		} else if (FIND_BATCH_BY_ITEM.equals(flowId)) {
			Integer itemId = batchInputMessage.getItemId();
			//System.out.println("------------------------------------Find By Item Id : "+ itemId);
			
			List<BatchEntity> list = null;
			List<ItemEntity> itemList= storageItemDAO.findById(itemId);
		//	logger.info("Item List for id="+itemId+" : "+itemList);
			if(itemList!=null && itemList.size()>0){
					ItemEntity itemEntity=itemList.get(0);
					BatchEntity batchEntity1=new BatchEntity();
					batchEntity1.setItem(itemEntity);
					try {
						BeanUtils.copyProperties(itemEntity, batchEntity1);
					} catch (Exception e) {					
						e.printStackTrace();
					}
					list=new ArrayList<BatchEntity>();
					list.add(batchEntity1);
				}
			
			popUpList(list);
			
						
		}  else if (FIND_ALL_BATCH_BY_ITEM.equals(flowId)) {
			Integer itemId = batchInputMessage.getItemId();	
			System.out.print("itemId :-"+itemId);
			List<BatchEntity> list = storageBatchDAO.getLastByItemId(itemId);
			System.out.print("itemId  list :- "+list.size());
			popUpList(list);
		}else if (SEARCH_BATCH.equals(flowId)) {

		//	System.out.println("------------------------------------Search : ");
			List<BatchEntity> list = storageBatchDAO.search(batchInputMessage
					.getBatchSearchCritecia().getBatchNo(), batchInputMessage
					.getBatchSearchCritecia().getItemName(), batchInputMessage
					.getBatchSearchCritecia().getItemCategory());
			popUpList(list);
		}else if(PRE_REMOVE_CHECK.equals(flowId)){
			if(storageBatchDAO.isInUsed(batchInputMessage.getBatchDTO().getBatchNo())){
			//	logger.info("TRUE");
				batchOutMessage=new BatchOutMessage();
				ErrorDTO errorDTO=new ErrorDTO("1","Batch Cannot Be Removed");
				ErrorListDTO errorListDto=new ErrorListDTO();
				 errorListDto.addError(errorDTO);
				 batchOutMessage.setErrorListDTO(errorListDto);
			}else{
				batchOutMessage=null;
				//storageBatchDAO.delete(batchEntity);
			}
		}
	}


	
	
	void popUpList(List<BatchEntity> list) {
		batchOutMessage = new BatchOutMessage();
		// set the data to the output message.
		if (list != null) {
		//	logger.info(">>>>>>Batch List :"+list);
			List<BatchDTO> resultList = new ArrayList<BatchDTO>();
			BatchDTO batchDTO;
			for (BatchEntity entity : list) {

				batchDTO = new BatchDTO();
				try {
					if(entity.getOpeningStock()==null){
						entity.setOpeningStock(0.0);
					 }
					// Spring
					BeanUtils.copyProperties(entity, batchDTO);
					
//					batchDTO.setItemDTO(new ItemDTO());
//					BeanUtils.copyProperties(entity.getItem(),
//							batchDTO.getItemDTO());
					ItemEntity itemEntity=entity.getItem();
					ItemDTO itemDTO=convertItemEntityTOItemDto(itemEntity);
					batchDTO.setItemDTO(itemDTO);
					
					List<Double> stockCountList=storageStockLedgerDAO.countStockByBatchNo(entity.getBatchNo());
					Double stockCount=0.0;
					if( stockCountList!=null &&  stockCountList.size()>0 && stockCountList.get(0)!=null){
						 stockCount=stockCountList.get(0);											 
					}					
					//batchDTO.setClosingStock(batchDTO.getOpeningStock()+stockCount);
					
					batchDTO.setClosingStock(batchDTO.getOpeningStock()+stockCount);
					resultList.add(batchDTO);
				} catch (Exception e) {
					try {
						org.apache.commons.beanutils.BeanUtils.copyProperties(batchDTO, entity);
					} catch (Exception e1) {
						logger.debug(e.toString());
						batchDTO.setBatchNo(entity.getBatchNo());
					}

					logger.debug(e.toString());
				}

			}
			// logger.info("REsult List :" +resultList);
			batchOutMessage.setBatchDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (ADD_BATCH.equals(flowId)) {

		} else if (UPDATE_BATCH.equals(flowId)) {

		} else if (DELETE_BATCH.equals(flowId)) {

		} else if (FIND_BATCH_BY_NO.equals(flowId)) {

		} else if (FIND_ALL_BATCHES.equals(flowId)) {

		}
	}

	private ItemDTO convertItemEntityTOItemDto(ItemEntity entity) {	
		if(entity==null)
			return null;
			
		ItemDTO itemDTO = new ItemDTO();
				// Spring
				itemDTO.setItemCategoryDTO(new ItemCategoryDTO());
				BeanUtils.copyProperties(entity, itemDTO);
				if (entity.getItemCategoryEntity() != null) {
					BeanUtils.copyProperties(entity.getItemCategoryEntity(),
							itemDTO.getItemCategoryDTO());
				}
				itemDTO.getItemCategoryDTO()
						.setItemGroupDTO(new ItemGroupDTO());

				if (entity.getItemCategoryEntity() != null
						&& entity.getItemCategoryEntity().getItemGroupEntity() != null) {
					BeanUtils.copyProperties(entity.getItemCategoryEntity()
							.getItemGroupEntity(), itemDTO.getItemCategoryDTO()
							.getItemGroupDTO());
				}

				if (entity.getMasterPackEntity() != null) {
					MastersDTO mastersDTO = new MastersDTO();
					BeanUtils
							.copyProperties(entity.getMasterPackEntity(), mastersDTO);
					itemDTO.setMasterPack(mastersDTO);
				}

				if (entity.getMasterUnitEntity() != null) {
					MastersDTO mastersDTO = new MastersDTO();
					BeanUtils.copyProperties(entity.getMasterUnitEntity(), mastersDTO);
					itemDTO.setMasterUnit(mastersDTO);
				}

				if (entity.getMasterGradeEntity() != null) {
					MastersDTO mastersDTO = new MastersDTO();
					BeanUtils.copyProperties(entity.getMasterGradeEntity(),
							mastersDTO);
					itemDTO.setMasterGrade(mastersDTO);
				}

				List<Double> sl = storageStockLedgerDAO
						.countStockByItemId(itemDTO.getItemId());
				if (sl != null && sl.size() > 0) {
					itemDTO.setStockTotal(sl.get(0));
				}
				


		return itemDTO;
	}
	private void copyObject(Object source, Object target) {
		try {
			BeanUtils.copyProperties(source, target);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
