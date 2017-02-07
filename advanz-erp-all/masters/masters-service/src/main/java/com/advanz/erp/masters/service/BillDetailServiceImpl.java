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
import com.advanz.erp.masters.model.BillDetailDTO;
import com.advanz.erp.masters.model.msg.BillDetailInputMessage;
import com.advanz.erp.masters.model.msg.BillDetailOutMessage;
import com.advanz.erp.masters.service.business.IBillDetailService;
import com.advanz.erp.masters.storage.IStorageBillDetailDAO;

public class BillDetailServiceImpl implements IBillDetailService{

	public static final String UPDATE_BILL_DETAIL = "UpdateBillDetail";
	public static final String ADD_BILL_DETAIL = "AddBillDetail";
	public static final String DELETE_BILL_DETAIL = "DeleteBillDetail";
	public static final String FIND_BILL_DETAIL_BY_ID = "FindBillDetailById";
	public static final String FIND_ALL_BILL_DETAIL = "FindAllBillDetail";
	public static final String FIND_CATEGORIES = "FindCategories";
	public static final String FIND_BILL_DETAIL_BY_NAME = "FindBillDetailByName";
	public static final String FIND_BILL_DETAIL = "FindBillDetail";
	
	public static final String FIND_BILL_DETAIL_BY_ITEM_ID = "FindBillDetailByItemId";
	public String flowId = "";
	
	// @Autowired
		public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																									// do
		private static final Logger logger = LoggerFactory
		.getLogger(BillDetailServiceImpl.class);
		
	@Autowired
	public IStorageBillDetailDAO storageBillDetailDAO;
	public BillDetailInputMessage billDetailInputMessage;
	public BillDetailOutMessage billDetailOutMessage;
		
	@Override
	public boolean validateInput() {
		if (UPDATE_BILL_DETAIL.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (ADD_BILL_DETAIL.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_BILL_DETAIL.equals(flowId)) {
			
			return true;
		} else if (FIND_BILL_DETAIL_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_BILL_DETAIL.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_CATEGORIES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_BILL_DETAIL_BY_NAME.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_BILL_DETAIL_BY_ITEM_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		
		return false;
	}

	@Override
	public void performBusinessLogic() {

		BillDetailEntity billDetailEntity = new BillDetailEntity();
		if(billDetailInputMessage!=null){
			BillDetailDTO billDetailDTO= billDetailInputMessage.getBillDetailDTO();
			if(billDetailDTO!=null){
				
				BeanUtils.copyProperties(billDetailInputMessage.getBillDetailDTO(),billDetailEntity);
				
			}
			
		}
		
		
		if (ADD_BILL_DETAIL.equals(flowId)) {
			// PartyDTO compDto = partyInputMessage.getPartyDTO();
			// partyEntity.setPartyName(compDto.getPartyName());
		/*	
			try {
				BeanUtils.copyProperties(invoiceInputMessage.getInvoiceDTO(),invoiceEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//invoiceOutputMessage.setErrorListDTO(null);
			storageBillDetailDAO.create(billDetailEntity);
			
		} else if (UPDATE_BILL_DETAIL.equals(flowId)) {
			try {
				BeanUtils.copyProperties(billDetailInputMessage.getBillDetailDTO(),billDetailEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		storageBillDetailDAO.update(billDetailEntity);
						
		} else if (DELETE_BILL_DETAIL.equals(flowId)) {
			try {
				BeanUtils.copyProperties(billDetailInputMessage.getBillDetailDTO(),billDetailEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			storageBillDetailDAO.delete(billDetailEntity);
		} else if (FIND_BILL_DETAIL_BY_ID.equals(flowId)) {
			BillDetailDTO billDetailDTO = billDetailInputMessage.getBillDetailDTO();
			List<BillDetailEntity> list = storageBillDetailDAO.findById(billDetailDTO.getInvoiceNumber());
			billDetailOutMessage = new BillDetailOutMessage();
			if (list != null) {
				List<BillDetailDTO> resultList = convertBillDetailEntityListTOBillDetailDtoList(list);
				System.out.println("resultList : " + resultList.size());
				billDetailOutMessage.setBillDetailDTOList(resultList);
			}
		} else if (FIND_ALL_BILL_DETAIL.equals(flowId)) {
			List<BillDetailEntity> list = storageBillDetailDAO.load();
			billDetailOutMessage = new BillDetailOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<BillDetailDTO> resultList = convertBillDetailEntityListTOBillDetailDtoList(list);
				billDetailOutMessage.setBillDetailDTOList(resultList);
			}
		} else if (FIND_BILL_DETAIL.equals(flowId)) {
			BillDetailDTO billDetailDTO = billDetailInputMessage.getBillDetailDTO();
			
			List<BillDetailEntity> list = storageBillDetailDAO.load();
			billDetailOutMessage = new BillDetailOutMessage();
			if (list != null) {
				List<BillDetailDTO> resultList = convertBillDetailEntityListTOBillDetailDtoList(list);
				billDetailOutMessage.setBillDetailDTOList(resultList);
			}
			}
		 else if (FIND_BILL_DETAIL_BY_ITEM_ID.equals(flowId)) {
				BillDetailDTO billDetailDTO = billDetailInputMessage.getBillDetailDTO();
				
				List<BillDetailEntity> list = storageBillDetailDAO.findByItemId(billDetailDTO.getItemId());
				billDetailOutMessage = new BillDetailOutMessage();
				if (list != null) {
					List<BillDetailDTO> resultList = convertBillDetailEntityListTOBillDetailDtoList(list);
					billDetailOutMessage.setBillDetailDTOList(resultList);
				}
				
				}
		
		
		    }

	@Override
	public void formatOutput() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public BillDetailOutMessage createBillDetail(
			BillDetailInputMessage billDetailInputMessage) {
		flowId = ADD_BILL_DETAIL;

		// assign the message to the class level variable.
		this.billDetailInputMessage = billDetailInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return billDetailOutMessage;
	}

	@Override
	public BillDetailOutMessage updateBillDetail(
			BillDetailInputMessage billDetailInputMessage) {
		flowId = UPDATE_BILL_DETAIL;
		this.billDetailInputMessage = billDetailInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return billDetailOutMessage;
	}

	@Override
	public BillDetailOutMessage deleteBillDetail(
			BillDetailInputMessage billDetailInputMessage) {
		flowId = DELETE_BILL_DETAIL;
		this.billDetailInputMessage = billDetailInputMessage;
		
		// call the template method
				advanzErpServiceTemplate.execute(this);
				return billDetailOutMessage;
	}

	@Override
	public BillDetailOutMessage findBillByBillId(
			BillDetailInputMessage billDetailInputMessage) {
		flowId=FIND_BILL_DETAIL_BY_ID;
		this.billDetailInputMessage = billDetailInputMessage;
				
		 // call the template method
		 advanzErpServiceTemplate.execute(this);
		 return billDetailOutMessage;
	}

	@Override
	public BillDetailOutMessage findAllBillDetail() {
		flowId=FIND_ALL_BILL_DETAIL;
		this.billDetailInputMessage = billDetailInputMessage;
		
		 // call the template method
		 advanzErpServiceTemplate.execute(this);
		 return billDetailOutMessage;
	}

	@Override
	public BillDetailOutMessage findBillDetail(
			BillDetailInputMessage billDetailInputMessage) {
		flowId = FIND_BILL_DETAIL;
		this.billDetailInputMessage = billDetailInputMessage;
		
		 // call the template method
		 advanzErpServiceTemplate.execute(this);
		 return billDetailOutMessage;
	}

	@Override
	public BillDetailOutMessage findBillByBillName(
			BillDetailInputMessage billDetailInputMessage) {
		flowId = FIND_BILL_DETAIL_BY_ITEM_ID;
		this.billDetailInputMessage = billDetailInputMessage;
		
		 // call the template method
		 advanzErpServiceTemplate.execute(this);
		 return billDetailOutMessage;
	}
	
	
	
	private List<BillDetailDTO> convertBillDetailEntityListTOBillDetailDtoList(
			List<BillDetailEntity> list) {

		billDetailOutMessage = new BillDetailOutMessage();
		List<BillDetailDTO> resultList = null;
		// set the data to the outputput message.
		if (list != null) {
			BillDetailDTO billDetailDTO;
			resultList = new ArrayList<BillDetailDTO>();
			for (BillDetailEntity billDetailEntity : list) {
				billDetailDTO = new BillDetailDTO();
				
				if (billDetailEntity != null) {
					BeanUtils.copyProperties(billDetailEntity, billDetailDTO);
				resultList.add(billDetailDTO);
				}
			}
		}

		return resultList;
	}

	@Override
	public BillDetailOutMessage findBillByItemId(BillDetailInputMessage billDetailInputMessage) {
		flowId = FIND_BILL_DETAIL_BY_ITEM_ID;
		this.billDetailInputMessage = billDetailInputMessage;
		
		 // call the template method
		 advanzErpServiceTemplate.execute(this);
		 return billDetailOutMessage;
	}

	
	
}
