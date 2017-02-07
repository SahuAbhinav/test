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
import com.advanz.erp.masters.entity.jpa.ProformaDetailEntity;
import com.advanz.erp.masters.model.ProformaDetailDTO;
import com.advanz.erp.masters.model.msg.ProformaDetailInputMessage;
import com.advanz.erp.masters.model.msg.ProformaDetailOutMessage;
import com.advanz.erp.masters.service.business.IProformaDetailService;
import com.advanz.erp.masters.storage.IStorageProformaDetailDAO;

public class ProformaDetailServiceImpl implements IProformaDetailService{

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
		.getLogger(ProformaDetailServiceImpl.class);
		
	@Autowired
	public IStorageProformaDetailDAO storageProformaDetailDAO;
	public ProformaDetailInputMessage proformaDetailInputMessage;
	public ProformaDetailOutMessage proformaDetailOutMessage;
		
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

		ProformaDetailEntity proformaDetailEntity = new ProformaDetailEntity();
		if(proformaDetailInputMessage!=null){
			ProformaDetailDTO proformaDetailDTO= proformaDetailInputMessage.getProformaDetailDTO();
			if(proformaDetailDTO!=null){
				
				BeanUtils.copyProperties(proformaDetailInputMessage.getProformaDetailDTO(),proformaDetailEntity);
				
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
			storageProformaDetailDAO.create(proformaDetailEntity);
			
		} else if (UPDATE_BILL_DETAIL.equals(flowId)) {
			try {
				BeanUtils.copyProperties(proformaDetailInputMessage.getProformaDetailDTO(),proformaDetailEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		storageProformaDetailDAO.update(proformaDetailEntity);
						
		} else if (DELETE_BILL_DETAIL.equals(flowId)) {
			try {
				BeanUtils.copyProperties(proformaDetailInputMessage.getProformaDetailDTO(),proformaDetailEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			storageProformaDetailDAO.delete(proformaDetailEntity);
		} else if (FIND_BILL_DETAIL_BY_ID.equals(flowId)) {
			ProformaDetailDTO proformaDetailDTO = proformaDetailInputMessage.getProformaDetailDTO();
			List<ProformaDetailEntity> list=null;
			try {
				list = storageProformaDetailDAO.findById(proformaDetailDTO.getInvoiceNumber());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			proformaDetailOutMessage = new ProformaDetailOutMessage();
			if (list != null) {
				List<ProformaDetailDTO> resultList = convertProformaDetailEntityListTOProformaDetailDtoList(list);
				System.out.println("resultList : " + resultList.size());
				proformaDetailOutMessage.setProformaDetailDTOList(resultList);
			}
		} else if (FIND_ALL_BILL_DETAIL.equals(flowId)) {
			List<ProformaDetailEntity> list = storageProformaDetailDAO.load();
			proformaDetailOutMessage = new ProformaDetailOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<ProformaDetailDTO> resultList = convertProformaDetailEntityListTOProformaDetailDtoList(list);
				proformaDetailOutMessage.setProformaDetailDTOList(resultList);
			}
		} else if (FIND_BILL_DETAIL.equals(flowId)) {
			ProformaDetailDTO proformaDetailDTO = proformaDetailInputMessage.getProformaDetailDTO();
			List<ProformaDetailEntity> list = storageProformaDetailDAO.load();
			proformaDetailOutMessage = new ProformaDetailOutMessage();
			if (list != null) {
				List<ProformaDetailDTO> resultList = convertProformaDetailEntityListTOProformaDetailDtoList(list);
				proformaDetailOutMessage.setProformaDetailDTOList(resultList);
			}
			}
		 else if (FIND_BILL_DETAIL_BY_ITEM_ID.equals(flowId)) {
			 ProformaDetailDTO proformaDetailDTO = proformaDetailInputMessage.getProformaDetailDTO();
				
			 List<ProformaDetailEntity> list = storageProformaDetailDAO.findByItemId(proformaDetailDTO.getItemId());
			 proformaDetailOutMessage = new ProformaDetailOutMessage();
				if (list != null) {
					List<ProformaDetailDTO> resultList = convertProformaDetailEntityListTOProformaDetailDtoList(list);
					proformaDetailOutMessage.setProformaDetailDTOList(resultList);
				}
				
				}
		
		
		    }

	@Override
	public void formatOutput() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public ProformaDetailOutMessage createBillDetail(
			ProformaDetailInputMessage proformaDetailInputMessage) {
		flowId = ADD_BILL_DETAIL;

		// assign the message to the class level variable.
		this.proformaDetailInputMessage = proformaDetailInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return proformaDetailOutMessage;
	}

	@Override
	public ProformaDetailOutMessage updateBillDetail(
			ProformaDetailInputMessage proformaDetailInputMessage) {
		flowId = UPDATE_BILL_DETAIL;
		this.proformaDetailInputMessage = proformaDetailInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return proformaDetailOutMessage;
	}

	@Override
	public ProformaDetailOutMessage deleteBillDetail(
			ProformaDetailInputMessage proformaDetailInputMessage) {
		flowId = DELETE_BILL_DETAIL;
		this.proformaDetailInputMessage = proformaDetailInputMessage;
		
		// call the template method
				advanzErpServiceTemplate.execute(this);
				return proformaDetailOutMessage;
	}

	@Override
	public ProformaDetailOutMessage findBillByBillId(
			ProformaDetailInputMessage proformaDetailInputMessage) {
		flowId=FIND_BILL_DETAIL_BY_ID;
		this.proformaDetailInputMessage = proformaDetailInputMessage;
				
		 // call the template method
		 advanzErpServiceTemplate.execute(this);
		 return proformaDetailOutMessage;
	}

	@Override
	public ProformaDetailOutMessage findAllBillDetail() {
		flowId=FIND_ALL_BILL_DETAIL;
		this.proformaDetailInputMessage = proformaDetailInputMessage;
		
		 // call the template method
		 advanzErpServiceTemplate.execute(this);
		 return proformaDetailOutMessage;
	}

	@Override
	public ProformaDetailOutMessage findBillDetail(
			ProformaDetailInputMessage proformaDetailInputMessage) {
		flowId = FIND_BILL_DETAIL;
		this.proformaDetailInputMessage = proformaDetailInputMessage;
		
		 // call the template method
		 advanzErpServiceTemplate.execute(this);
		 return proformaDetailOutMessage;
	}

	@Override
	public ProformaDetailOutMessage findBillByBillName(
			ProformaDetailInputMessage proformaDetailInputMessage) {
		flowId = FIND_BILL_DETAIL_BY_ITEM_ID;
		this.proformaDetailInputMessage = proformaDetailInputMessage;
		
		 // call the template method
		 advanzErpServiceTemplate.execute(this);
		 return proformaDetailOutMessage;
	}
	
	
	
	private List<ProformaDetailDTO> convertProformaDetailEntityListTOProformaDetailDtoList(
			List<ProformaDetailEntity> list) {

		proformaDetailOutMessage = new ProformaDetailOutMessage();
		List<ProformaDetailDTO> resultList = null;
		// set the data to the outputput message.
		if (list != null) {
			ProformaDetailDTO proformaDetailDTO;
			resultList = new ArrayList<ProformaDetailDTO>();
			for (ProformaDetailEntity proformaDetailEntity : list) {
				proformaDetailDTO = new ProformaDetailDTO();
				
				if (proformaDetailEntity != null) {
					BeanUtils.copyProperties(proformaDetailEntity, proformaDetailDTO);
				resultList.add(proformaDetailDTO);
				}
			}
		}
		return resultList;
	}
	@Override
	public ProformaDetailOutMessage findBillByItemId(ProformaDetailInputMessage proformaDetailInputMessage) {
		flowId = FIND_BILL_DETAIL_BY_ITEM_ID;
		this.proformaDetailInputMessage = proformaDetailInputMessage;
		
		 // call the template method
		 advanzErpServiceTemplate.execute(this);
		 return proformaDetailOutMessage;
	}

	
	
}
