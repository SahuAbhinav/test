package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.StockLedgerEntity;
import com.advanz.erp.masters.model.GrnMasterDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.StockLedgerDTO;
import com.advanz.erp.masters.model.msg.GrnMasterInputMessage;
import com.advanz.erp.masters.model.msg.GrnMasterOutputMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.StockLedgerInputMessage;
import com.advanz.erp.masters.model.msg.StockLedgerOutMessage;
import com.advanz.erp.masters.service.business.IGrnMasterService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IStockLedgerService;
import com.advanz.erp.masters.storage.IStorageStockLedgerDAO;

public class StockLedgerServiceImpl implements IStockLedgerService {

	public static final String UPDATE_STOCK_LEDGER = "UpdateStockLedger";
	public static final String ADD_STOCK_LEDGER = "AddStockLedger";
	public static final String DELETE_STOCK_LEDGER = "DeleteStockLedger";
	public static final String FIND_STOCK_LEDGER_BY_ID = "FindStockLedgerById";
	public static final String FIND_ALL_STOCK_LEDGER = "FindAllStockLedger";
	public static final String FIND_CATEGORIES = "FindCategories";
	public static final String FIND_STOCK_LEDGER_BY_NAME = "FindStockLedgerByName";
	public static final String COUNT_STOCK_LEDGER = "CountStockLedger";
	public static final String COUNT_STOCK_LEDGER_FOR_BATCH = "CountStockLedgerForBatch";
	public static final String FIND_STOCK_LEDGER = "FindStockLedger";
	public static final String FIND_STOCK_LEDGER_BY_TRANSACTION_NUMBER = "FindStockLedgerByTransactionNumber";
	public static final String FIND_QUANTITY_BY_ITEM_ID = "findQantityByItemId";

	public static final String ITEM_COUNT_DATE_WISE = "ItemCountDAteWise";
	// added by kamal on 8-2-13 for remove finishgoods
	public static final String DELETE_STOCK_LEDGER_BY_TRANSACTION_NUMBER = "DeleteStockLedgerByTransactionNumber";

	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
	private static final Logger logger = LoggerFactory
			.getLogger(StockLedgerServiceImpl.class);

	@Autowired
	public IStorageStockLedgerDAO storageStockLedgerDAO;
	public StockLedgerInputMessage stockLedgerInputMessage;
	public StockLedgerOutMessage stockLedgerOutMessage;

	@Autowired
	IItemService itemService;

	@Override
	public boolean validateInput() {
		if (UPDATE_STOCK_LEDGER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (ADD_STOCK_LEDGER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_STOCK_LEDGER.equals(flowId)) {

			return true;
		} else if (FIND_STOCK_LEDGER_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_STOCK_LEDGER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_CATEGORIES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_STOCK_LEDGER_BY_NAME.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_STOCK_LEDGER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (COUNT_STOCK_LEDGER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_STOCK_LEDGER_BY_TRANSACTION_NUMBER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (COUNT_STOCK_LEDGER_FOR_BATCH.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_STOCK_LEDGER_BY_TRANSACTION_NUMBER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_QUANTITY_BY_ITEM_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {

		StockLedgerEntity stockLedgerEntity = new StockLedgerEntity();
		if (stockLedgerInputMessage != null) {
			StockLedgerDTO stockLedgerDTO = stockLedgerInputMessage
					.getStockLedgerDTO();
			if (stockLedgerDTO != null) {

				BeanUtils.copyProperties(
						stockLedgerInputMessage.getStockLedgerDTO(),
						stockLedgerEntity);

			}

		}

		if (ADD_STOCK_LEDGER.equals(flowId)) {
			if (stockLedgerEntity != null) {
				try {
					stockLedgerEntity = updateSalesRateWeightedRate(stockLedgerEntity);
				} catch (Exception e) {
					logger.error("stockLedgerEntity updateSalesRateWeightedRate ",e);
					e.printStackTrace();
				}
			}
			storageStockLedgerDAO.create(stockLedgerEntity);

		} else if (UPDATE_STOCK_LEDGER.equals(flowId)) {
			try {
				BeanUtils.copyProperties(
						stockLedgerInputMessage.getStockLedgerDTO(),
						stockLedgerEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			storageStockLedgerDAO.update(stockLedgerEntity);

		} else if (DELETE_STOCK_LEDGER.equals(flowId)) {
			try {
				BeanUtils.copyProperties(
						stockLedgerInputMessage.getStockLedgerDTO(),
						stockLedgerEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			storageStockLedgerDAO.delete(stockLedgerEntity);
		} else if (FIND_STOCK_LEDGER_BY_ID.equals(flowId)) {
			StockLedgerDTO stockLedgerDTO = stockLedgerInputMessage
					.getStockLedgerDTO();
			List<StockLedgerEntity> list = storageStockLedgerDAO
					.findById(stockLedgerDTO.getItemId());
			stockLedgerOutMessage = new StockLedgerOutMessage();
			if (list != null) {
				List<StockLedgerDTO> resultList = convertStockLedgerEntityListTOStockLedgerDtoList(list);
				System.out.println("resultList : " + resultList.size());
				stockLedgerOutMessage.setStockLedgerDTOList(resultList);
			}
		} else if (FIND_ALL_STOCK_LEDGER.equals(flowId)) {
			List<StockLedgerEntity> list = storageStockLedgerDAO.load();
			stockLedgerOutMessage = new StockLedgerOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<StockLedgerDTO> resultList = convertStockLedgerEntityListTOStockLedgerDtoList(list);
				stockLedgerOutMessage.setStockLedgerDTOList(resultList);
			}
		} else if (FIND_STOCK_LEDGER.equals(flowId)) {
			StockLedgerDTO stockLedgerDTO = stockLedgerInputMessage
					.getStockLedgerDTO();

			List<StockLedgerEntity> list = storageStockLedgerDAO.load();
			stockLedgerOutMessage = new StockLedgerOutMessage();
			if (list != null) {
				List<StockLedgerDTO> resultList = convertStockLedgerEntityListTOStockLedgerDtoList(list);
				stockLedgerOutMessage.setStockLedgerDTOList(resultList);
			}
		} else if (COUNT_STOCK_LEDGER.equals(flowId)) {
			StockLedgerDTO stockLedgerDTO = stockLedgerInputMessage
					.getStockLedgerDTO();
			List list = storageStockLedgerDAO.countStockByItemId(stockLedgerDTO
					.getItemId());
			stockLedgerOutMessage = new StockLedgerOutMessage();
			stockLedgerOutMessage.setStockCount((Double) list.get(0));
		} else if (FIND_STOCK_LEDGER_BY_TRANSACTION_NUMBER.equals(flowId)) {

			StockLedgerDTO stockLedgerDTO = stockLedgerInputMessage
					.getStockLedgerDTO();
			List<StockLedgerEntity> list = storageStockLedgerDAO
					.findStockByTransactionId(stockLedgerDTO
							.getTransactionNumber());
			stockLedgerOutMessage = new StockLedgerOutMessage();
			if (list != null) {
				List<StockLedgerDTO> resultList = convertStockLedgerEntityListTOStockLedgerDtoList(list);
				System.out.println("resultList : " + resultList.size());
				stockLedgerOutMessage.setStockLedgerDTOList(resultList);
			}
		}

		else if (FIND_QUANTITY_BY_ITEM_ID.equals(flowId)) {
			stockLedgerOutMessage = new StockLedgerOutMessage();
			Integer itemId = stockLedgerInputMessage.getStockLedgerDTO()
					.getItemId();
			List<Double> list = storageStockLedgerDAO
					.countStockForItemByItemId(itemId);

			if (list.get(0) == null) {
				stockLedgerOutMessage.setQuantityCount(0.0);
			} else {

				stockLedgerOutMessage.setQuantityCount((Double) list.get(0));
			}
			System.out.println("rrrrrrrrrr"
					+ stockLedgerOutMessage.getQuantityCount());
		}

		else if (COUNT_STOCK_LEDGER_FOR_BATCH.equals(flowId)) {
			stockLedgerOutMessage = new StockLedgerOutMessage();
			String batchNo = stockLedgerInputMessage.getBatchNo();
			List list = storageStockLedgerDAO.countStockByBatchNo(batchNo);
			stockLedgerOutMessage.setStockCount((Double) list.get(0));
		} else if (DELETE_STOCK_LEDGER_BY_TRANSACTION_NUMBER.equals(flowId)) {
			stockLedgerOutMessage = new StockLedgerOutMessage();
			String transactionNumber = stockLedgerInputMessage
					.getStockLedgerDTO().getTransactionNumber();
			List<StockLedgerEntity> list = storageStockLedgerDAO
					.deleteByTransactionNumber(transactionNumber);
			if (list != null) {
				for (StockLedgerEntity stockLedgerEntityTemp : list) {

					if (stockLedgerEntityTemp != null) {
						storageStockLedgerDAO.delete(stockLedgerEntityTemp);
					}
				}

			}
			// stockLedgerOutMessage.setStockLedgerDTOList(list);
		}

	}

	@Override
	public void formatOutput() {
		// TODO Auto-generated method stub

	}

	@Override
	public StockLedgerOutMessage countStockByBatchNo(
			StockLedgerInputMessage stockLedgerInputMessage) {
		flowId = COUNT_STOCK_LEDGER_FOR_BATCH;

		// assign the message to the class level variable.
		this.stockLedgerInputMessage = stockLedgerInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return stockLedgerOutMessage;
	}

	@Override
	public StockLedgerOutMessage createStockLedger(
			StockLedgerInputMessage stockLedgerInputMessage) {
		flowId = ADD_STOCK_LEDGER;

		// assign the message to the class level variable.
		this.stockLedgerInputMessage = stockLedgerInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return stockLedgerOutMessage;
	}

	@Override
	public StockLedgerOutMessage updateStockLedger(
			StockLedgerInputMessage stockLedgerInputMessage) {
		flowId = UPDATE_STOCK_LEDGER;
		this.stockLedgerInputMessage = stockLedgerInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return stockLedgerOutMessage;
	}

	@Override
	public StockLedgerOutMessage deleteStockLedger(
			StockLedgerInputMessage stockLedgerInputMessage) {
		flowId = DELETE_STOCK_LEDGER;
		this.stockLedgerInputMessage = stockLedgerInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return stockLedgerOutMessage;
	}

	@Override
	public StockLedgerOutMessage findStockLedgerByItemId(
			StockLedgerInputMessage stockLedgerInputMessage) {
		flowId = FIND_STOCK_LEDGER_BY_ID;
		this.stockLedgerInputMessage = stockLedgerInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return stockLedgerOutMessage;
	}

	@Override
	public StockLedgerOutMessage findAllStockLedger() {
		flowId = FIND_ALL_STOCK_LEDGER;
		this.stockLedgerInputMessage = stockLedgerInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return stockLedgerOutMessage;
	}

	@Override
	public StockLedgerOutMessage findStockLedger(
			StockLedgerInputMessage stockLedgerInputMessage) {
		flowId = FIND_STOCK_LEDGER;
		this.stockLedgerInputMessage = stockLedgerInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return stockLedgerOutMessage;
	}

	@Override
	public StockLedgerOutMessage countStockByItemId(
			StockLedgerInputMessage stockLedgerInputMessage) {
		flowId = COUNT_STOCK_LEDGER;
		this.stockLedgerInputMessage = stockLedgerInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return stockLedgerOutMessage;
	}

	@Override
	public StockLedgerOutMessage findStockLedgerByTransactionId(
			StockLedgerInputMessage stockLedgerInputMessage) {

		flowId = FIND_STOCK_LEDGER_BY_TRANSACTION_NUMBER;
		this.stockLedgerInputMessage = stockLedgerInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return stockLedgerOutMessage;
	}

	@Override
	public StockLedgerOutMessage deleteByTransactionNumber(
			StockLedgerInputMessage stockLedgerInputMessage) {

		flowId = DELETE_STOCK_LEDGER_BY_TRANSACTION_NUMBER;
		this.stockLedgerInputMessage = stockLedgerInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return stockLedgerOutMessage;
	}

	@Override
	public StockLedgerOutMessage findStockLedgerByName(
			StockLedgerInputMessage stockLedgerInputMessage) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockLedgerOutMessage findQuantityByItemId(
			StockLedgerInputMessage stockLedgerInputMessage) {
		System.out.println("IN SIDE SERVICE CLASS ::::::::::::::::::");
		flowId = FIND_QUANTITY_BY_ITEM_ID;
		this.stockLedgerInputMessage = stockLedgerInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return stockLedgerOutMessage;
	}

	private List<StockLedgerDTO> convertStockLedgerEntityListTOStockLedgerDtoList(
			List<StockLedgerEntity> list) {

		stockLedgerOutMessage = new StockLedgerOutMessage();
		List<StockLedgerDTO> resultList = null;
		// set the data to the outputput message.
		if (list != null) {
			StockLedgerDTO stockLedgerDTO;
			resultList = new ArrayList<StockLedgerDTO>();
			for (StockLedgerEntity stockLedgerEntity : list) {
				stockLedgerDTO = new StockLedgerDTO();

				if (stockLedgerEntity != null) {
					BeanUtils.copyProperties(stockLedgerEntity, stockLedgerDTO);
					resultList.add(stockLedgerDTO);
				}
			}
		}

		return resultList;
	}

	@Override
	public List ItemCountDateWise(String date) {
		List list = storageStockLedgerDAO.ItemCountDateWise(date);
		return list;
	}

	@Override
	public void createEmailDetal(String emailDetail, Date date, Integer userId) {
		storageStockLedgerDAO.createEmailDetal(emailDetail, date, userId);

	}

	@Override
	public String getEmailByDate(String date) {
		// TODO Auto-generated method stub
		return storageStockLedgerDAO.getEmailByDate(date);
	}

	@Override
	public void updateEmailDetal(String date, String status) {
		storageStockLedgerDAO.updateEmailDetal(date, status);
	}

	@Override
	public List getEmailDetal(String date, String status) {
		return storageStockLedgerDAO.getEmailDetal(date, status);

	}

	public StockLedgerEntity updateSalesRateWeightedRate(
			StockLedgerEntity stockLedgerEntity) {
		String transactionCodeRequired = "%";
		StockLedgerDTO oldStockLedgerRecord = null;
		ItemInputMessage itemInputMessage = new ItemInputMessage();
		ItemOutMessage itemOutMessage = null;
		ItemDTO itemDTO = new ItemDTO();
		Double masterRate = 0.0;
		Double rateForWeighted = 0.0;
		Double closingBalance = 0.0;
		Double transactionAmount = 0.0;
		Double weightedAmount = 0.0;
		Double weightedRate = 0.0;
		Double oldWeightedRate = 0.0;
		Double transactionQty = stockLedgerEntity.getQuantity();

		logger.info("Current StockLedgerEntity--->" + stockLedgerEntity);

		// step 1. set master rate
		if (rateForWeighted == 0.0) {
			itemDTO = new ItemDTO();
			itemDTO.setItemId(stockLedgerEntity.getItemId());
			itemInputMessage.setItemDTO(itemDTO);
			itemOutMessage = itemService.findItemById(itemInputMessage);

			List itemList = itemOutMessage.getItemDTOList();
			if (itemList != null && itemList.size() > 0) {
				if (itemList.get(0) != null) {
					itemDTO = (ItemDTO) itemList.get(0);
					masterRate = doubleFilter(itemDTO.getSalesRate());
				}
			}
		}

		// step 2. set closing balance

		// set default closing balance as quantity
		closingBalance = transactionQty;

		List<StockLedgerEntity> list = storageStockLedgerDAO
				.getLastSalesRate(stockLedgerEntity.getItemId(),
						stockLedgerEntity.getTransactionDate(),
						transactionCodeRequired);

		if (list != null && list.size() > 0) {
			if (list.get(0) != null) {
				oldStockLedgerRecord = new StockLedgerDTO();
				BeanUtils.copyProperties(list.get(0), oldStockLedgerRecord);

			}
		}

		logger.info("oldStockLedgerRecord:----" + oldStockLedgerRecord);
		// if this is the first entry off this item then we will take Clo. Bal.
		// from item master
		if (oldStockLedgerRecord != null) {
			closingBalance = transactionQty
					+ doubleFilter(oldStockLedgerRecord.getClosingBalance());
			oldWeightedRate = doubleFilter(oldStockLedgerRecord
					.getWeightedrate());
		} else {
			closingBalance = doubleFilter(itemDTO.getOpeningStock())
					+ transactionQty;
		}

		// closingBalance=stockLedgerEntity.getQuantity()+stockLedgerEntity.

		// step 3. set rate for weighted
		if (stockLedgerEntity.getTransactionSeries().equalsIgnoreCase("SCF")
				|| stockLedgerEntity.getTransactionSeries().equalsIgnoreCase(
						"GN")) {
			rateForWeighted = doubleFilter(stockLedgerEntity.getSalesRate());
		} else {
			rateForWeighted = doubleFilter(oldWeightedRate);

		}

		if (oldStockLedgerRecord == null) {
			rateForWeighted = doubleFilter(masterRate);
		}

		// step 4. set Transaction amount
		transactionAmount = transactionQty * rateForWeighted;

		// set 5. set weighted amount
		if (closingBalance > 0) {

			if (oldStockLedgerRecord != null) {
				weightedAmount = Math
						.max(((doubleFilter(oldStockLedgerRecord
								.getClosingBalance()) * doubleFilter(oldStockLedgerRecord
								.getWeightedrate())) + transactionAmount), 0);
			} else {
				weightedAmount = Math.max(transactionAmount, 0);
			}

		}

		// step 6. set weighted rate
		if (closingBalance != 0) {
			if (transactionQty >= closingBalance)
				weightedRate = Math.max((weightedAmount / transactionQty), 0);
			else
				weightedRate = Math.max(
						(weightedAmount / Math.max(closingBalance, 0)), 0);

		}
		
		if(isNaN(transactionQty))
			transactionQty=0.0;
		if(isNaN(masterRate))
			masterRate=0.0;
		if(isNaN(rateForWeighted))
			rateForWeighted=0.0;
		if(isNaN(closingBalance))
			closingBalance=0.0;
		if(isNaN(transactionAmount))
			transactionAmount=0.0;
		if(isNaN(weightedAmount))
			weightedAmount=0.0;
		if(isNaN(weightedRate))
			weightedRate=0.0;

		// step 7 set all properties in Stock Entity
		logger.info("transaction no :" + stockLedgerEntity.getTransactionNumber()
				+ ",item_id :" + stockLedgerEntity.getItemId() + ",trnas. Qty :"
				+ transactionQty + ",sales_rate :"
				+ stockLedgerEntity.getSalesRate() + ",master_rate :"
				+ masterRate + ",old_Weighted_Rate :" + oldWeightedRate
				+ ",rate_for_weighted :" + rateForWeighted + ",closing_balance :"
				+ closingBalance + ",trnsaction_amt :" + transactionAmount
				+ ",weighted_amt :" + weightedAmount + ",weighted_rate :"
				+ weightedRate);

		stockLedgerEntity.setMasterRate(doubleFilter(masterRate));
		stockLedgerEntity.setRateForWeighted(doubleFilter(rateForWeighted));
		stockLedgerEntity.setClosingBalance(doubleFilter(closingBalance));
		stockLedgerEntity.setTransactionAmount(doubleFilter(transactionAmount));
		stockLedgerEntity.setWeightedAmount(doubleFilter(weightedAmount));
		stockLedgerEntity.setWeightedrate(doubleFilter(weightedRate));
		
		logger.info("Current StockLedgerEntity after weightedRate update --->" + stockLedgerEntity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stockLedgerEntity;
	}

	public double doubleFilter(Double obj) {
		Double d = 0.0;
		if (obj != null) {
			d = (Double) obj;
		}
		return d;
	}
	
	public boolean isNaN(double v) {
	    return (v != v);
	}
}
