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
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.entity.jpa.PartyEntity;
import com.advanz.erp.masters.entity.jpa.SalesOrderDetailEntity;
import com.advanz.erp.masters.entity.jpa.SalesOrderMasterEntity;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.SalesOrderDetailDTO;
import com.advanz.erp.masters.model.SalesOrderMasterDTO;
import com.advanz.erp.masters.model.criteria.SalesOrderMasterSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.SalesOrderMasterInputMessage;
import com.advanz.erp.masters.model.msg.SalesOrderMasterOutputMessage;
import com.advanz.erp.masters.service.business.ISalesOrderMasterService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageSalesOrderMasterDAO;

public class SalesOrderMasterServiceImpl implements ISalesOrderMasterService {

	public static final String CREATE_SALES_ORDER_MASTER = "CreateSalesOrderMaster";
	public static final String UPDATE_SALES_ORDER_MASTER = "UpdateSalesOrderMaster";
	public static final String DELETE_SALES_ORDER_MASTER = "DeleteSalesOrderMaster";
	public static final String FIND_SALES_ORDER_MASTER_BY_ID = "FindSalesOrderMasterById";
	public static final String FIND_ALL_SALES_ORDER_MASTER = "FindAllSalesOrderMasters";
	public static final String SEARCH_SALES_ORDER_MASTER = "SearchSalesOrderMasters";
	public static final String NEW_SALES_ORDER_MASTER_SERIES_NO = "NewSalesOrderMastersSeriesNo";
	public static final String PRE_REMOVE_CHECK="PreRemoveCheck";

	public static final String FIND_BY_SALES_ORDER_NUMBER = "FindBySalesOrder";
	
	
	public static final String FIND_SALES_ORDER_MASTER_PAGINATION = "FindSalesOrderMasterPagination";
	public String flowId = "";
	private static final Logger logger = LoggerFactory
	.getLogger(SalesOrderMasterServiceImpl.class);
	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
	// do
	// autowiring

	@Autowired
	public IStorageSalesOrderMasterDAO storageSalesOrderMasterDAO;

	public SalesOrderMasterInputMessage salesOrderMasterInputMessage;

	public SalesOrderMasterOutputMessage salesOrderMasterOutputMessage;
	
	@Autowired
	public IZoneService zoneService;
	
	@Override
	public SalesOrderMasterOutputMessage createSalesOrderMaster(
			SalesOrderMasterInputMessage salesOrderMasterInputMessage) {

		flowId = CREATE_SALES_ORDER_MASTER;
		// assign the message to the class level variable.
		this.salesOrderMasterInputMessage = salesOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salesOrderMasterOutputMessage;
	}

	@Override
	public SalesOrderMasterOutputMessage updateSalesOrderMaster(
			SalesOrderMasterInputMessage salesOrderMasterInputMessage) {

		flowId = UPDATE_SALES_ORDER_MASTER;
		// assign the message to the class level variable.
		this.salesOrderMasterInputMessage = salesOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return salesOrderMasterOutputMessage;
	}

	@Override
	public SalesOrderMasterOutputMessage deleteSalesOrderMaster(
			SalesOrderMasterInputMessage salesOrderMasterInputMessage) {
		flowId = DELETE_SALES_ORDER_MASTER;
		// assign the message to the class level variable.
		this.salesOrderMasterInputMessage = salesOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return salesOrderMasterOutputMessage;

	}

	@Override
	public SalesOrderMasterOutputMessage findSalesOrderMasterById(
			SalesOrderMasterInputMessage salesOrderMasterInputMessage) {
		flowId = FIND_SALES_ORDER_MASTER_BY_ID;
		// assign the message to the class level variable.
		this.salesOrderMasterInputMessage = salesOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salesOrderMasterOutputMessage;

	}

	@Override
	public SalesOrderMasterOutputMessage findAllSalesOrderMasters() {
		flowId = FIND_ALL_SALES_ORDER_MASTER;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salesOrderMasterOutputMessage;
	}

	@Override
	public SalesOrderMasterOutputMessage search(
			SalesOrderMasterInputMessage salesOrderMasterInputMessage) {
		flowId = SEARCH_SALES_ORDER_MASTER;
		this.salesOrderMasterInputMessage = salesOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salesOrderMasterOutputMessage;

	}

	@Override
	public SalesOrderMasterOutputMessage getNewSalesOrderSeriesNo(
			SalesOrderMasterInputMessage salesOrderMasterInputMessage) {
		flowId = NEW_SALES_ORDER_MASTER_SERIES_NO;
		this.salesOrderMasterInputMessage = salesOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salesOrderMasterOutputMessage;
	}

	@Override
	public SalesOrderMasterOutputMessage findBySalesOrderNo(
			SalesOrderMasterInputMessage salesOrderMasterInputMessage) {
		flowId = FIND_BY_SALES_ORDER_NUMBER;

		this.salesOrderMasterInputMessage = salesOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salesOrderMasterOutputMessage;
	}
	@Override
	public SalesOrderMasterOutputMessage checkBeforeRemove(
			SalesOrderMasterInputMessage salesOrderMasterInputMessage) {
		flowId = PRE_REMOVE_CHECK;
		this.salesOrderMasterInputMessage = salesOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salesOrderMasterOutputMessage;
	}
	@Override
	public SalesOrderMasterOutputMessage findSalesOrderMasterPagination(
			SalesOrderMasterInputMessage salesOrderMasterInputMessage) {
		
		flowId = FIND_SALES_ORDER_MASTER_PAGINATION;
		this.salesOrderMasterInputMessage = salesOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salesOrderMasterOutputMessage;
	}

	@Override
	public boolean validateInput() {

		if (CREATE_SALES_ORDER_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_SALES_ORDER_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_SALES_ORDER_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_SALES_ORDER_MASTER_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_SALES_ORDER_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_SALES_ORDER_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (NEW_SALES_ORDER_MASTER_SERIES_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_BY_SALES_ORDER_NUMBER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (PRE_REMOVE_CHECK.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_SALES_ORDER_MASTER_PAGINATION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		
		return false;
	}

	@Override
	public void performBusinessLogic() {

		SalesOrderMasterEntity salesOrderMasterEntity = new SalesOrderMasterEntity();
		if (salesOrderMasterInputMessage != null) {
			SalesOrderMasterDTO salesOrderMasterDTO = salesOrderMasterInputMessage.getSalesOrderMasterDTO();
			if (salesOrderMasterDTO != null) {
				BeanUtils.copyProperties(salesOrderMasterDTO,
						salesOrderMasterEntity);
				PartyDTO partyDTO = salesOrderMasterDTO.getPartyDTO();
				logger.info("PArty = " + partyDTO);
				if (partyDTO != null) {
					PartyEntity partyEntity = new PartyEntity();
					copyObject(partyDTO, partyEntity);
					salesOrderMasterEntity.setPartyEntity(partyEntity);
				}

				BranchDTO branchDTO = salesOrderMasterDTO.getBranchDTO();
				if (branchDTO != null) {
					BranchEntity branchEntity = new BranchEntity();
					copyObject(branchDTO, branchEntity);
					salesOrderMasterEntity.setBranchEntity(branchEntity);
				}

				List<SalesOrderDetailDTO> salesOrderDetailDTOList = salesOrderMasterDTO
				.getSalesOrderDetailDTOList();
				if (salesOrderDetailDTOList != null && salesOrderDetailDTOList.size() > 0) {
					for(int i=0;i<salesOrderDetailDTOList.size();i++){
						SalesOrderDetailDTO sd=	salesOrderDetailDTOList.get(i);
						if(sd.getActiveStatus()==null){
							sd.setActiveStatus(1);
						}
						
					if(salesOrderMasterDTO.getTaxType().equalsIgnoreCase("CST")){
						sd.setCstAmount(sd.getTaxAmount());
						sd.setCstPerc(sd.getTaxPerc());
					
						sd.setVatAmount(0.00);
						sd.setVatPerc(0.00);
					}if(salesOrderMasterDTO.getTaxType().equalsIgnoreCase("VAT")){
						sd.setVatAmount(sd.getTaxAmount());
						sd.setVatPerc(sd.getTaxPerc());
						
						sd.setCstAmount(0.00);
						sd.setCstPerc(0.00);
					}
					
					}
					
					
					List<SalesOrderDetailEntity> salesOrderDetailEntities = convertSalesOrderDetailDtoTOSalesOrderDetailEntity(salesOrderDetailDTOList,salesOrderMasterDTO);
					salesOrderMasterEntity.setSalesOrderDetailEntities(salesOrderDetailEntities);
				}
			}
		}

		if (CREATE_SALES_ORDER_MASTER.equals(flowId)) {
		//	storageSalesOrderMasterDAO.create(salesOrderMasterEntity);
			
			List<SalesOrderMasterEntity> list = storageSalesOrderMasterDAO.findBySalesOrderNo(salesOrderMasterEntity.getSalesOrderNumber());
			if (list != null && list.size() > 0) {
				salesOrderMasterOutputMessage=new SalesOrderMasterOutputMessage();
				ErrorDTO errorDTO = new ErrorDTO("1", salesOrderMasterEntity.getSalesOrderNumber()+" Sales Order Number is already exist,it can't be duplicate ");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				salesOrderMasterOutputMessage.setErrorListDTO(errorListDTO);
				
			}
			
			else{
			String son=storageSalesOrderMasterDAO.save(salesOrderMasterEntity);
			if(son!=null){
				salesOrderMasterOutputMessage=new SalesOrderMasterOutputMessage();
				ErrorDTO errorDTO = new ErrorDTO("1", "Sales Order added successfully with SalesOrderNumber : '"+son+"'");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				salesOrderMasterOutputMessage.setErrorListDTO(errorListDTO);
			   }
			}
			
			
			

		} else if (UPDATE_SALES_ORDER_MASTER.equals(flowId)) {
			storageSalesOrderMasterDAO.update(salesOrderMasterEntity);
		} else if (DELETE_SALES_ORDER_MASTER.equals(flowId)) {
			storageSalesOrderMasterDAO.delete(salesOrderMasterEntity);
		} else if (FIND_SALES_ORDER_MASTER_BY_ID.equals(flowId)) {
			List<SalesOrderMasterEntity> list = storageSalesOrderMasterDAO
			.findById(salesOrderMasterEntity.getSalesOrderAutoId());
			if (list != null && list.size() > 0) {
				logger.info("Detail List"+ list.get(0).getSalesOrderDetailEntities());
			}
			popUpList(list);

		} else if (FIND_ALL_SALES_ORDER_MASTER.equals(flowId)) {
			List<SalesOrderMasterEntity> list = storageSalesOrderMasterDAO
			.load();
			popUpList(list);
		} else if (SEARCH_SALES_ORDER_MASTER.equals(flowId)) {
			SalesOrderMasterSearchCriteriaDTO searchCriteria = salesOrderMasterInputMessage
			.getSearchCriteria();
			List<SalesOrderMasterEntity> list = storageSalesOrderMasterDAO
			.search(searchCriteria.getSoNumber(),
					searchCriteria.getFromDate(),searchCriteria.getToDate(),
					searchCriteria.getPartyName(),searchCriteria.getItemName());
			popUpList(list);
		} else if (NEW_SALES_ORDER_MASTER_SERIES_NO.equals(flowId)) {
			Integer seriesNo = storageSalesOrderMasterDAO
			.getNewSeriesNo(salesOrderMasterEntity.getFinYear());
			Timestamp date = zoneService.getFirstDayOfFinYear();
			
			Timestamp timestamp = storageSalesOrderMasterDAO
					.getNewSeriesDate(salesOrderMasterEntity.getFinYear());
			if(timestamp!=null)
				date=timestamp;
		
			salesOrderMasterOutputMessage = new SalesOrderMasterOutputMessage();
			salesOrderMasterOutputMessage.setSalesOrderSeriesNo(seriesNo);
			salesOrderMasterOutputMessage.setSalesOrderSeriesDate(date);
		}
		else if(FIND_BY_SALES_ORDER_NUMBER.equals(flowId)){
			SalesOrderMasterDTO salesOrderMasterDTO = salesOrderMasterInputMessage.getSalesOrderMasterDTO();
			List<SalesOrderMasterEntity> list = storageSalesOrderMasterDAO.findBySalesOrderNo(salesOrderMasterDTO.getSalesOrderNumber());
			popUpList(list);
		}if(PRE_REMOVE_CHECK.equals(flowId)){
			if(storageSalesOrderMasterDAO.isInUsed(salesOrderMasterInputMessage.getSalesOrderMasterDTO().getSalesOrderAutoId())){
				logger.info("TRUE");		
				salesOrderMasterOutputMessage=new SalesOrderMasterOutputMessage();
				ErrorDTO errorDTO = new ErrorDTO("1", "Sales Order can not be Removed,Used in Invoice");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				salesOrderMasterOutputMessage.setErrorListDTO(errorListDTO);
			}else{
				salesOrderMasterOutputMessage=null;
			}
		}
		else if (FIND_SALES_ORDER_MASTER_PAGINATION.equals(flowId)) {
			List<SalesOrderMasterEntity> list = storageSalesOrderMasterDAO.findSalesOrderMasterPagination(salesOrderMasterInputMessage.getNext());
			popUpList(list);
		}
		
		
	}

	void popUpList(List<SalesOrderMasterEntity> list) {
		logger.info("SOM Entity List  :" + list);
		salesOrderMasterOutputMessage = new SalesOrderMasterOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<SalesOrderMasterDTO> resultList = new ArrayList<SalesOrderMasterDTO>();
			SalesOrderMasterDTO salesOrderMasterDTO;
			for (SalesOrderMasterEntity entity : list) {
				salesOrderMasterDTO = new SalesOrderMasterDTO();
				// Spring
				BeanUtils.copyProperties(entity, salesOrderMasterDTO);

				PartyEntity partyEntity = entity.getPartyEntity();
				if (partyEntity != null) {
					PartyDTO partyDTO = new PartyDTO();
					copyObject(partyEntity, partyDTO);
					salesOrderMasterDTO.setPartyDTO(partyDTO);
				}

				BranchEntity branchEntity = entity.getBranchEntity();
				if (branchEntity != null) {
					BranchDTO branchDTO = new BranchDTO();
					copyObject(branchEntity, branchDTO);
					salesOrderMasterDTO.setBranchDTO(branchDTO);
				}


				List<SalesOrderDetailEntity> salesOrderDetailEntityList=entity.getSalesOrderDetailEntities();
				List<SalesOrderDetailDTO> salesOrderDetailDTOList=new ArrayList<SalesOrderDetailDTO>();	
				if(salesOrderDetailEntityList!=null && salesOrderDetailEntityList.size()>0){
					double qty=0;
					for(SalesOrderDetailEntity salesOrderDetailEntity:salesOrderDetailEntityList){
						if(salesOrderDetailEntity.getDeletedFlag())
							continue;
						SalesOrderDetailDTO salesOrderDetailDTO=new SalesOrderDetailDTO();
						copyObject(salesOrderDetailEntity,salesOrderDetailDTO);	
						qty=qty+salesOrderDetailEntity.getQuantity();
						if(salesOrderDetailEntity.getItemEntity()!=null){
							salesOrderDetailDTO.setItemId(salesOrderDetailEntity.getItemEntity().getItemId());
							salesOrderDetailDTO.setItemName(salesOrderDetailEntity.getItemEntity().getInvoiceName());
							salesOrderDetailDTO.setPartyInvoiceType(partyEntity.getInvoiceType());

						}
						if(salesOrderDetailEntity.getMeasurementUnitMasterEntity()!=null){
							MastersDTO mesurementUntiDTO=new MastersDTO();
							copyObject(salesOrderDetailEntity.getMeasurementUnitMasterEntity(), mesurementUntiDTO);
							salesOrderDetailDTO.setMasterUnit(mesurementUntiDTO);
						}
						salesOrderDetailDTOList.add(salesOrderDetailDTO);
					}
					salesOrderMasterDTO.setTotalQuantity(qty);
				}
				salesOrderMasterDTO
				.setSalesOrderDetailDTOList(salesOrderDetailDTOList);
				resultList.add(salesOrderMasterDTO);
			}
			salesOrderMasterOutputMessage
			.setSalesOrderMasterDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (CREATE_SALES_ORDER_MASTER.equals(flowId)) {

		} else if (UPDATE_SALES_ORDER_MASTER.equals(flowId)) {

		} else if (DELETE_SALES_ORDER_MASTER.equals(flowId)) {

		} else if (FIND_SALES_ORDER_MASTER_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_SALES_ORDER_MASTER.equals(flowId)) {

		} else if (SEARCH_SALES_ORDER_MASTER.equals(flowId)) {

		} else if (NEW_SALES_ORDER_MASTER_SERIES_NO.equals(flowId)) {

		}

	}

	private List<SalesOrderDetailEntity> convertSalesOrderDetailDtoTOSalesOrderDetailEntity(
			List<SalesOrderDetailDTO> dtoList,SalesOrderMasterDTO salesOrderMasterDTO) {
		List<SalesOrderDetailEntity> entityList = new ArrayList<SalesOrderDetailEntity>();
		for (SalesOrderDetailDTO dto : dtoList) {
			SalesOrderDetailEntity entity = new SalesOrderDetailEntity();
			if (dto != null) {
				copyObject(dto, entity);

				ItemEntity itemEntity = new ItemEntity();
				itemEntity.setItemId(dto.getItemId());
				entity.setItemEntity(itemEntity);

				MastersDTO mesurementUnitDTO=dto.getMasterUnit();
				if(mesurementUnitDTO!=null){
					MastersEntity  mesurementUnitEntity=new MastersEntity();
					mesurementUnitEntity.setMastersId(mesurementUnitDTO.getMastersId());
					entity.setMeasurementUnitMasterEntity(mesurementUnitEntity);
				}
if(salesOrderMasterDTO.getCreatedUserId()!=null){
	entity.setCreatedUserId(salesOrderMasterDTO.getCreatedUserId());
}
if(salesOrderMasterDTO.getModifiedUserId()!=null){
	entity.setModifiedUserId(salesOrderMasterDTO.getModifiedUserId());
}
				entityList.add(entity);
			}
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
	public List getSalesDetailToEmail(String date) {
		List list=	storageSalesOrderMasterDAO.getSalesDetailToEmail(date);
		return list;
	}

	@Override
	public List getBookedSalesDetailToEmail(String date) {
		List list=	storageSalesOrderMasterDAO.getBookedSalesDetailToEmail(date);
		return list;
	}

	@Override
	public Date getMinimumPendingDate() {
		// TODO Auto-generated method stub
		return storageSalesOrderMasterDAO.getMinimumPendingDate();
	}




}
