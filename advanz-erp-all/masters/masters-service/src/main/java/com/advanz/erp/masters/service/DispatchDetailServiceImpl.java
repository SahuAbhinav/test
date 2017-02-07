package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.BillDetailEntity;
import com.advanz.erp.masters.entity.jpa.DispatchDetailEntity;
import com.advanz.erp.masters.model.BillDetailDTO;
import com.advanz.erp.masters.model.DispatchDetailDTO;
import com.advanz.erp.masters.model.msg.BillDetailInputMessage;
import com.advanz.erp.masters.model.msg.BillDetailOutMessage;
import com.advanz.erp.masters.model.msg.DispatchDetailInputMessage;
import com.advanz.erp.masters.model.msg.DispatchDetailOutMessage;
import com.advanz.erp.masters.service.business.IDispatchDetailService;
import com.advanz.erp.masters.storage.IStorageBillDetailDAO;
import com.advanz.erp.masters.storage.IStorageDispatchDetailDAO;

public class DispatchDetailServiceImpl implements IDispatchDetailService {

	public static final String UPDATE_DISPATCH_DETAIL = "UpdateDispatchDetail";
	public static final String ADD_DISPATCH_DETAIL = "AddDispatchDetail";
	public static final String DELETE_DISPATCH_DETAIL = "DeleteDispatchDetail";
	public static final String FIND_BY_ID = "FindById";
	public static final String FIND_ALL_DISPATCH_DETAIL = "FindAllDispatchDetail";
	public static final String FIND_CATEGORIES = "FindCategories";
	public static final String FIND_DISPATCH_DETAIL_BY_NAME = "FindDispatchDetailByName";
	public static final String FIND_DISPATCH_DETAIL = "FindDispatchDetail";
	public static final String FIND_DISPATCH_DETAIL_BY_NUMBER = "FindDispatchDetailByNumber";

	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
	private static final Logger logger = LoggerFactory
			.getLogger(DispatchDetailServiceImpl.class);

	@Autowired
	public IStorageDispatchDetailDAO storageDispatchDetailDAO;
	public DispatchDetailInputMessage dispatchDetailInputMessage;
	public DispatchDetailOutMessage dispatchDetailOutMessage;

	@Override
	public boolean validateInput() {
		if (UPDATE_DISPATCH_DETAIL.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (ADD_DISPATCH_DETAIL.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_DISPATCH_DETAIL.equals(flowId)) {

			return true;
		} else if (FIND_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_DISPATCH_DETAIL.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_CATEGORIES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_DISPATCH_DETAIL_BY_NAME.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_DISPATCH_DETAIL_BY_NUMBER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {

		DispatchDetailEntity dispatchDetailEntity = new DispatchDetailEntity();
		if (dispatchDetailInputMessage != null) {
			DispatchDetailDTO dispatchDetailDTO = dispatchDetailInputMessage
					.getDispatchDetailDTO();
			if (dispatchDetailDTO != null) {
				BeanUtils.copyProperties(
						dispatchDetailInputMessage.getDispatchDetailDTO(),
						dispatchDetailEntity);
			}

		}

		if (ADD_DISPATCH_DETAIL.equals(flowId)) {
			// PartyDTO compDto = partyInputMessage.getPartyDTO();
			// partyEntity.setPartyName(compDto.getPartyName());
			/*
			 * try {
			 * BeanUtils.copyProperties(invoiceInputMessage.getInvoiceDTO()
			 * ,invoiceEntity); } catch (BeansException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
			// invoiceOutputMessage.setErrorListDTO(null);
			storageDispatchDetailDAO.create(dispatchDetailEntity);

		} else if (UPDATE_DISPATCH_DETAIL.equals(flowId)) {
			try {
				BeanUtils.copyProperties(
						dispatchDetailInputMessage.getDispatchDetailDTO(),
						dispatchDetailEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			storageDispatchDetailDAO.update(dispatchDetailEntity);

		} else if (DELETE_DISPATCH_DETAIL.equals(flowId)) {
			try {
				BeanUtils.copyProperties(
						dispatchDetailInputMessage.getDispatchDetailDTO(),
						dispatchDetailEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			storageDispatchDetailDAO.delete(dispatchDetailEntity);
		} else if (FIND_BY_ID.equals(flowId)) {
			DispatchDetailDTO dispatchDetailDTO = dispatchDetailInputMessage
					.getDispatchDetailDTO();
			List<DispatchDetailEntity> list = storageDispatchDetailDAO
					.findById(dispatchDetailDTO.getSno());
			dispatchDetailOutMessage = new DispatchDetailOutMessage();
			if (list != null) {
				List<DispatchDetailDTO> resultList = convertDispatchDetailEntityListTODispatchDetailDtoList(list);

				dispatchDetailOutMessage.setDispatchDetailDTOList(resultList);
			}
		} else if (FIND_ALL_DISPATCH_DETAIL.equals(flowId)) {
			List<DispatchDetailEntity> list = storageDispatchDetailDAO.load();
			dispatchDetailOutMessage = new DispatchDetailOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<DispatchDetailDTO> resultList = convertDispatchDetailEntityListTODispatchDetailDtoList(list);
				dispatchDetailOutMessage.setDispatchDetailDTOList(resultList);
			}
		} else if (FIND_DISPATCH_DETAIL.equals(flowId)) {
			DispatchDetailDTO dispatchDetailDTO = dispatchDetailInputMessage
					.getDispatchDetailDTO();

			List<DispatchDetailEntity> list = storageDispatchDetailDAO.load();
			dispatchDetailOutMessage = new DispatchDetailOutMessage();
			if (list != null) {
				List<DispatchDetailDTO> resultList = convertDispatchDetailEntityListTODispatchDetailDtoList(list);
				dispatchDetailOutMessage.setDispatchDetailDTOList(resultList);
			}
		}
		else if (FIND_DISPATCH_DETAIL_BY_NUMBER.equals(flowId)) {
			DispatchDetailDTO dispatchDetailDTO = dispatchDetailInputMessage
					.getDispatchDetailDTO();
			List<DispatchDetailEntity> list = storageDispatchDetailDAO
					.findByDispatchId(dispatchDetailDTO.getDispatchNumber());
			dispatchDetailOutMessage = new DispatchDetailOutMessage();
			if (list != null) {
				List<DispatchDetailDTO> resultList = convertDispatchDetailEntityListTODispatchDetailDtoList(list);

				dispatchDetailOutMessage.setDispatchDetailDTOList(resultList);
			}
		} 
		
		
	}

	@Override
	public void formatOutput() {
		// TODO Auto-generated method stub

	}

	@Override
	public DispatchDetailOutMessage createDispatchDetail(
			DispatchDetailInputMessage dispatchDetailInputMessage) {
		flowId = ADD_DISPATCH_DETAIL;

		// assign the message to the class level variable.
		this.dispatchDetailInputMessage = dispatchDetailInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return dispatchDetailOutMessage;
	}

	@Override
	public DispatchDetailOutMessage updateDispatchDetail(
			DispatchDetailInputMessage dispatchDetailInputMessage) {
		flowId = UPDATE_DISPATCH_DETAIL;
		this.dispatchDetailInputMessage = dispatchDetailInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return dispatchDetailOutMessage;
	}

	@Override
	public DispatchDetailOutMessage deleteDispatchDetail(
			DispatchDetailInputMessage dispatchDetailInputMessage) {
		flowId = DELETE_DISPATCH_DETAIL;
		this.dispatchDetailInputMessage = dispatchDetailInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return dispatchDetailOutMessage;
	}

	@Override
	public DispatchDetailOutMessage findById(
			DispatchDetailInputMessage dispatchDetailInputMessage) {
		flowId = FIND_BY_ID;
		this.dispatchDetailInputMessage = dispatchDetailInputMessage;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return dispatchDetailOutMessage;
	}

	@Override
	public DispatchDetailOutMessage findAllDispatchDetail() {
		flowId = FIND_ALL_DISPATCH_DETAIL;
		this.dispatchDetailInputMessage = dispatchDetailInputMessage;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return dispatchDetailOutMessage;
	}

	@Override
	public DispatchDetailOutMessage findDispatchDetail(
			DispatchDetailInputMessage dispatchDetailInputMessage) {
		flowId = FIND_DISPATCH_DETAIL;
		this.dispatchDetailInputMessage = dispatchDetailInputMessage;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return dispatchDetailOutMessage;
	}

	@Override
	public DispatchDetailOutMessage findByDispatchId(
			DispatchDetailInputMessage dispatchDetailInputMessage) {
		flowId = FIND_DISPATCH_DETAIL_BY_NUMBER;

		this.dispatchDetailInputMessage = dispatchDetailInputMessage;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return dispatchDetailOutMessage;
	}

	@Override
	public DispatchDetailOutMessage findDispatchByBillName(
			DispatchDetailInputMessage dispatchDetailInputMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<DispatchDetailDTO> convertDispatchDetailEntityListTODispatchDetailDtoList(
			List<DispatchDetailEntity> list) {

		dispatchDetailOutMessage = new DispatchDetailOutMessage();
		List<DispatchDetailDTO> resultList = null;
		// set the data to the outputput message.
		if (list != null) {
			DispatchDetailDTO dispatchDetailDTO;
			resultList = new ArrayList<DispatchDetailDTO>();
			for (DispatchDetailEntity dispatchDetailEntity : list) {
				dispatchDetailDTO = new DispatchDetailDTO();

				if (dispatchDetailEntity != null) {
					BeanUtils.copyProperties(dispatchDetailEntity,
							dispatchDetailDTO);
					resultList.add(dispatchDetailDTO);
				}
			}
		}

		return resultList;
	}

}
