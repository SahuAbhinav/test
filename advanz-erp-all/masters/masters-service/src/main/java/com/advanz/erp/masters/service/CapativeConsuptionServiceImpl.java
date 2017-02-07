package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.CapativeConsuptionEntity;
import com.advanz.erp.masters.entity.jpa.StockLedgerEntity;
import com.advanz.erp.masters.model.CapativeConsuptionDTO;
import com.advanz.erp.masters.model.msg.CapativeConsuptionInputMessage;
import com.advanz.erp.masters.model.msg.CapativeConsuptionOutputMessage;
import com.advanz.erp.masters.service.business.ICapativeConsuptionService;
import com.advanz.erp.masters.storage.IStorageCapativeConsuptionDAO;
import com.advanz.erp.masters.storage.IStorageStockLedgerDAO;

public class CapativeConsuptionServiceImpl implements ICapativeConsuptionService {
	private static final Logger logger = LoggerFactory
	.getLogger(CapativeConsuptionServiceImpl.class);
	
	public static final String CREATE_CAPATIVE_CONSUPTION = "CreateCapativeConsuption";
	public static final String UPDATE_CAPATIVE_CONSUPTION = "UpdateCapativeConsuption";
	public static final String DELETE_CAPATIVE_CONSUPTION = "DeleteCapativeConsuption";
	public static final String FIND_CAPATIVE_CONSUPTION_BY_ID = "FindCapativeConsuptionById";
	public static final String FIND_ALL_CAPATIVE_CONSUPTION = "FindAllCapativeConsuption";
	public static final String SEARCH_CAPATIVE_CONSUPTION = "SearchCapativeConsuption";
	public static final String FIND_CAPATIVE_CONSUPTION_PAGITION = "FindCapativeConsuptionPagination";
	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageCapativeConsuptionDAO storageCapativeConsuptionDAO;
	
	@Autowired
	public IStorageStockLedgerDAO stockLedgerDAO;

	public CapativeConsuptionInputMessage capativeConsuptionInputMessage;

	public CapativeConsuptionOutputMessage capativeConsuptionOutputMessage;

	@Override
	public CapativeConsuptionOutputMessage createCapativeConsuption(CapativeConsuptionInputMessage capativeConsuptionInputMessage) {

		flowId = CREATE_CAPATIVE_CONSUPTION;
		// assign the message to the class level variable.
		this.capativeConsuptionInputMessage = capativeConsuptionInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return capativeConsuptionOutputMessage;
	}

	@Override
	public CapativeConsuptionOutputMessage updateCapativeConsuption(CapativeConsuptionInputMessage capativeConsuptionInputMessage) {

		flowId = UPDATE_CAPATIVE_CONSUPTION;
		// assign the message to the class level variable.
		this.capativeConsuptionInputMessage = capativeConsuptionInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return capativeConsuptionOutputMessage;
	}

	@Override
	public CapativeConsuptionOutputMessage deleteCapativeConsuption(CapativeConsuptionInputMessage capativeConsuptionInputMessage) {
		flowId = DELETE_CAPATIVE_CONSUPTION;
		// assign the message to the class level variable.
		this.capativeConsuptionInputMessage = capativeConsuptionInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return capativeConsuptionOutputMessage;

	}

	@Override
	public CapativeConsuptionOutputMessage findCapativeConsuptionById(CapativeConsuptionInputMessage capativeConsuptionInputMessage) {
		flowId = FIND_CAPATIVE_CONSUPTION_BY_ID;
		// assign the message to the class level variable.
		this.capativeConsuptionInputMessage = capativeConsuptionInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return capativeConsuptionOutputMessage;

	}

	@Override
	public CapativeConsuptionOutputMessage findAllcapativeConsuption() {
		flowId = FIND_ALL_CAPATIVE_CONSUPTION;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return capativeConsuptionOutputMessage;
	}

	@Override
	public CapativeConsuptionOutputMessage search(CapativeConsuptionInputMessage capativeConsuptionInputMessage) {
		flowId = SEARCH_CAPATIVE_CONSUPTION;
		this.capativeConsuptionInputMessage=capativeConsuptionInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return capativeConsuptionOutputMessage;

	}
	@Override
	public CapativeConsuptionOutputMessage findCapativeConsuptionByPagination(
			CapativeConsuptionInputMessage capativeConsuptionInputMessage) {
		flowId = FIND_CAPATIVE_CONSUPTION_PAGITION;
		this.capativeConsuptionInputMessage=capativeConsuptionInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return capativeConsuptionOutputMessage;
	}
	
	@Override
	public boolean validateInput() {

		if (CREATE_CAPATIVE_CONSUPTION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_CAPATIVE_CONSUPTION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_CAPATIVE_CONSUPTION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_CAPATIVE_CONSUPTION_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_CAPATIVE_CONSUPTION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_CAPATIVE_CONSUPTION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}  else if (FIND_CAPATIVE_CONSUPTION_PAGITION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} 
		
		return false;
	}

	@Override
	public void performBusinessLogic() {

		CapativeConsuptionEntity capativeConsuptionEntity = new CapativeConsuptionEntity();
		if (capativeConsuptionInputMessage != null) {
			CapativeConsuptionDTO capativeConsuptionDTO = capativeConsuptionInputMessage.getCapativeConsuptionDTO();
			if (capativeConsuptionDTO != null) {
				BeanUtils.copyProperties(capativeConsuptionDTO, capativeConsuptionEntity);
		}

		if (CREATE_CAPATIVE_CONSUPTION.equals(flowId)) {
			// check duplicate area name
			
			capativeConsuptionOutputMessage = new CapativeConsuptionOutputMessage();
			storageCapativeConsuptionDAO.create(capativeConsuptionEntity);
			
			//To save in stock ledger........strat
			if(capativeConsuptionEntity.getSourceItemId()!=null){
			StockLedgerEntity stockLedgerEntity=new StockLedgerEntity();
			stockLedgerEntity.setItemId(capativeConsuptionEntity.getSourceItemId());
			stockLedgerEntity.setTransactionDate(new Date());
			stockLedgerEntity.setTransactionId(capativeConsuptionEntity.getCapativeConsumptionId());
			stockLedgerEntity.setTransactionNumber(capativeConsuptionEntity.getCapativeConsumptionNumber());
			stockLedgerEntity.setTransactionSeries("CC");
			stockLedgerEntity.setCreatedUserId(capativeConsuptionEntity.getCreatedUserId());
			if(capativeConsuptionEntity.getSourceQuantity()!=null &&capativeConsuptionEntity.getSourceQuantity()>0){
				stockLedgerEntity.setQuantity(capativeConsuptionEntity.getSourceQuantity()*(-1));
			}else{
				stockLedgerEntity.setQuantity(0.0*(-1));
			}
			stockLedgerDAO.create(stockLedgerEntity);
			}
			//Target Items............
			if(capativeConsuptionEntity.getTargetItemId()!=null){
				StockLedgerEntity stockLedgerEntity=new StockLedgerEntity();
				stockLedgerEntity.setItemId(capativeConsuptionEntity.getTargetItemId());
				stockLedgerEntity.setTransactionDate(new Date());
				stockLedgerEntity.setTransactionId(capativeConsuptionEntity.getCapativeConsumptionId());
				stockLedgerEntity.setTransactionNumber(capativeConsuptionEntity.getCapativeConsumptionNumber());
				stockLedgerEntity.setTransactionSeries("CC");
				stockLedgerEntity.setCreatedUserId(capativeConsuptionEntity.getCreatedUserId());
				if(capativeConsuptionEntity.getTargetQuantity()!=null &&capativeConsuptionEntity.getTargetQuantity()>0){
					stockLedgerEntity.setQuantity(capativeConsuptionEntity.getTargetQuantity());
				}else{
					stockLedgerEntity.setQuantity(0.0);
				}
				stockLedgerDAO.create(stockLedgerEntity);
				}
			//END...................
		} else if (UPDATE_CAPATIVE_CONSUPTION.equals(flowId)) {
		   storageCapativeConsuptionDAO.update(capativeConsuptionEntity);
		} else if (DELETE_CAPATIVE_CONSUPTION.equals(flowId)) {
			storageCapativeConsuptionDAO.delete(capativeConsuptionEntity);
		} else if (FIND_CAPATIVE_CONSUPTION_BY_ID.equals(flowId)) {
		List<CapativeConsuptionEntity>list=	storageCapativeConsuptionDAO.findById(capativeConsuptionEntity.getSno());
		popUpList(list);
		} else if (FIND_ALL_CAPATIVE_CONSUPTION.equals(flowId)) {
			List<CapativeConsuptionEntity> list = storageCapativeConsuptionDAO.load();
			popUpList(list);			
		}else if (SEARCH_CAPATIVE_CONSUPTION.equals(flowId)) {
			 capativeConsuptionDTO = capativeConsuptionInputMessage.getCapativeConsuptionDTO();			
			List<CapativeConsuptionEntity> list = storageCapativeConsuptionDAO.search(capativeConsuptionDTO.getEnteredDate(),capativeConsuptionDTO.getSourceItemCode(),capativeConsuptionDTO.getTargetItemCode());
			popUpList(list);
		}
		else if (FIND_CAPATIVE_CONSUPTION_PAGITION.equals(flowId)) {
			List<CapativeConsuptionEntity> list = storageCapativeConsuptionDAO.FindCapativeConsumptionPagination(capativeConsuptionInputMessage.getNext());
			popUpList(list);			
		}
		
		}
	}
	void popUpList(List<CapativeConsuptionEntity> list) {
		capativeConsuptionOutputMessage = new CapativeConsuptionOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<CapativeConsuptionDTO> resultList = new ArrayList<CapativeConsuptionDTO>();
			CapativeConsuptionDTO capativeConsuptionDTO;
			for (CapativeConsuptionEntity entity : list) {
				capativeConsuptionDTO = new CapativeConsuptionDTO();
				// Spring
				BeanUtils.copyProperties(entity, capativeConsuptionDTO);
				resultList.add(capativeConsuptionDTO);
			}
			capativeConsuptionOutputMessage.setCapativeConsuptionDTOList(resultList);
		}
	}

	@Override
	public void formatOutput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getNewSeriesNo() {
		
		return storageCapativeConsuptionDAO.getNewSeriesNo();
	}

	
}
