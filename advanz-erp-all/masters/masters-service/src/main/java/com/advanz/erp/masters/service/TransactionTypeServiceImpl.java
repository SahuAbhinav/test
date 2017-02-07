package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.TransactionTypeEntity;
import com.advanz.erp.masters.model.TransactionTypeDTO;
import com.advanz.erp.masters.model.msg.TransactionTypeInputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeOutputMessage;
import com.advanz.erp.masters.service.business.ITransactionTypeService;
import com.advanz.erp.masters.storage.IStorageTransactionTypeDAO;

public class TransactionTypeServiceImpl implements ITransactionTypeService {

	public static final String CREATE_TRANSACTION_TYPE = "CreateTransactionType";
	public static final String UPDATE_TRANSACTION_TYPE = "UpdateTransactionType";
	public static final String DELETE_TRANSACTION_TYPE = "DeleteTransactionType";
	public static final String FIND_ALL_TRANSACTION_TYPE = "FindAllTransactionTypes";
	public static final String SEARCH_TRANSACTION_TYPE = "SearchTransactionTypes";
	public static final String FIND_TRANSACTION_TYPE_BY_ID = "FindTransactionTypeById";
	public static final String FIND_TRANSACTION_TYPE_BY_NAME = "FindTransactionTypeByName";
	public static final String FIND_TRANSACTION_TYPE_BY_SERIES = "FindTransactionTypeBySeries";

	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageTransactionTypeDAO storageTransactionTypeDAO;

	public TransactionTypeInputMessage transactionTypeInputMessage;

	public TransactionTypeOutputMessage transactionTypeOutputMessage;
	@Override
	public TransactionTypeOutputMessage findTransactionTypeById(
			TransactionTypeInputMessage transactionTypeInputMessage) {
		flowId = FIND_TRANSACTION_TYPE_BY_ID;
		// assign the message to the class level variable.
		this.transactionTypeInputMessage = transactionTypeInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return transactionTypeOutputMessage;

	}

	@Override
	public TransactionTypeOutputMessage findTransactionTypeByName(
			TransactionTypeInputMessage areaInputMessage) {
		flowId = FIND_TRANSACTION_TYPE_BY_NAME;
		// assign the message to the class level variable.
		this.transactionTypeInputMessage = areaInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return transactionTypeOutputMessage;
	}

	@Override
	public TransactionTypeOutputMessage findTransactionTypeBySeries(
			TransactionTypeInputMessage areaInputMessage) {
		flowId = FIND_TRANSACTION_TYPE_BY_SERIES;
		// assign the message to the class level variable.
		this.transactionTypeInputMessage = transactionTypeInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return transactionTypeOutputMessage;
	}


	@Override
	public boolean validateInput() {

		if (CREATE_TRANSACTION_TYPE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_TRANSACTION_TYPE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_TRANSACTION_TYPE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_TRANSACTION_TYPE_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_TRANSACTION_TYPE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_TRANSACTION_TYPE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_TRANSACTION_TYPE_BY_NAME.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_TRANSACTION_TYPE_BY_SERIES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {

		TransactionTypeEntity transactionTypeEntity = new TransactionTypeEntity();
		TransactionTypeDTO transactionTypeDTO =null;
		if (transactionTypeInputMessage != null) {
			 transactionTypeDTO = transactionTypeInputMessage.getTransactionTypeDTO();
			if (transactionTypeDTO != null) {
				BeanUtils.copyProperties(transactionTypeDTO,
						transactionTypeEntity);
			}
		}
		if (FIND_TRANSACTION_TYPE_BY_ID.equals(flowId)) {
			List<TransactionTypeEntity> list = storageTransactionTypeDAO
					.findById(transactionTypeEntity.getSno());
			popUpList(list);
		} else if (FIND_TRANSACTION_TYPE_BY_NAME.equals(flowId)) {
			List<TransactionTypeEntity> list = storageTransactionTypeDAO
					.findByName(transactionTypeDTO.getName());
			popUpList(list);
		} else if (FIND_TRANSACTION_TYPE_BY_SERIES.equals(flowId)) {
			List<TransactionTypeEntity> list = storageTransactionTypeDAO
					.findById(transactionTypeEntity.getSeries());
			popUpList(list);
		}
	}

	void popUpList(List<TransactionTypeEntity> list) {
		transactionTypeOutputMessage = new TransactionTypeOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<TransactionTypeDTO> resultList = new ArrayList<TransactionTypeDTO>();
			TransactionTypeDTO transactionTypeDTO;
			for (TransactionTypeEntity entity : list) {
				transactionTypeDTO = new TransactionTypeDTO();
				BeanUtils.copyProperties(entity, transactionTypeDTO);
				resultList.add(transactionTypeDTO);
			}
			transactionTypeOutputMessage.setTransactionTypeDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (CREATE_TRANSACTION_TYPE.equals(flowId)) {

		} else if (UPDATE_TRANSACTION_TYPE.equals(flowId)) {

		} else if (DELETE_TRANSACTION_TYPE.equals(flowId)) {

		} else if (FIND_TRANSACTION_TYPE_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_TRANSACTION_TYPE.equals(flowId)) {

		} else if (SEARCH_TRANSACTION_TYPE.equals(flowId)) {

		} else if (FIND_TRANSACTION_TYPE_BY_NAME.equals(flowId)) {

		} else if (FIND_TRANSACTION_TYPE_BY_SERIES.equals(flowId)) {

		}
	}

}
