package com.advanz.erp.masters.service;

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
import com.advanz.erp.masters.entity.jpa.BillEntity;
import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.entity.jpa.EmployeeEntity;
import com.advanz.erp.masters.entity.jpa.PartyEntity;
import com.advanz.erp.masters.model.BillDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.msg.BillInputMessage;
import com.advanz.erp.masters.model.msg.BillOutMessage;
import com.advanz.erp.masters.service.business.IBillService;
import com.advanz.erp.masters.storage.IStorageBillDAO;

public class BillServiceImpl implements IBillService {

	public static final String CREATE_BILL = "Bill";
	public static final String UPDATE_BILL = "UpdateBill";
	public static final String ADD_BILL = "AddBill";
	public static final String DELETE_BILL = "DeleteBill";
	public static final String FIND_BILL_BY_ID = "FindBillById";
	public static final String FIND_ALL_BILL = "FindAllBills";
	public static final String FIND_LAST_BY_INVOICE_ID = "FindLastByInvoiceId";
	public static final String INVOICE_SEARCH = "SearchInvoice";

	public static final String FIND_MAX_INVOICE_ID = "FindMaxInvoiceId";
	public static final String FIND_BILL_BY_EMPLOYEE_ID = "FindBillByEmployeeId";
	public static final String FIN_YEAR = "FinYear";
	public static final String FIND_INVOICE_FOR_PAGINATION = "FindInvoiceForPagination";
	public String flowId = "";
	public static final String NEW_BILL_MASTER_SERIES_NO = "NewBillMastersSeriesNo";
	public static final String CHECK_DUPLICATE_INVOICE_NUMBER = "CheckDuplicateInvoiceNumber";
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();

	@Autowired
	public IStorageBillDAO storageBillDAO;

	public BillInputMessage billInputMessage;

	public BillOutMessage billOutMessage;

	private static final Logger logger = LoggerFactory
			.getLogger(BillServiceImpl.class);

	@Override
	public BillOutMessage createBill(BillInputMessage billInputMessage) {
		// TODO Auto-generated method stub
		flowId = ADD_BILL;
		// assign the message to the class level variable.
		this.billInputMessage = billInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return billOutMessage;
	}

	@Override
	public BillOutMessage updateBill(BillInputMessage billInputMessage) {
		// TODO Auto-generated method stub
		flowId = UPDATE_BILL;
		this.billInputMessage = billInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return billOutMessage;
	}

	@Override
	public BillOutMessage deleteBill(BillInputMessage billInputMessage) {
		// TODO Auto-generated method stub
		flowId = DELETE_BILL;
		this.billInputMessage = billInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return billOutMessage;
	}

	@Override
	public BillOutMessage findBillById(BillInputMessage billInputMessage) {
		// TODO Auto-generated method stub
		flowId = FIND_BILL_BY_ID;
		this.billInputMessage = billInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return billOutMessage;
	}

	@Override
	public BillOutMessage findAllBills() {
		// TODO Auto-generated method stub
		flowId = FIND_ALL_BILL;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return billOutMessage;
	}

	public BillOutMessage getLastByInvoiceId() {
		flowId = FIND_LAST_BY_INVOICE_ID;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return billOutMessage;
	}

	@Override
	public BillOutMessage searchInvoice(BillInputMessage billInputMessage) {
		flowId = INVOICE_SEARCH;
		this.billInputMessage = billInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return billOutMessage;
	}
	@Override
	public BillOutMessage getMaxInvoiceId() {
		flowId =FIND_MAX_INVOICE_ID;
		this.billInputMessage = billInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return billOutMessage;
	}
	@Override
	public BillOutMessage findBillByEmployeeId(BillInputMessage billInputMessage) {
		
		flowId =FIND_BILL_BY_EMPLOYEE_ID;
		this.billInputMessage = billInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return billOutMessage;
	}
	
	@Override
	public BillOutMessage getFinacialYear() {
		flowId =FIN_YEAR;
		this.billInputMessage = billInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return billOutMessage;
	}
	
	@Override
	public BillOutMessage getNewBillMasterSeriesNo(
			BillInputMessage billInputMessage) {
		flowId =NEW_BILL_MASTER_SERIES_NO;
		this.billInputMessage = billInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return billOutMessage;
	}

	@Override
	public BillOutMessage findInvoiceForPagination(
			BillInputMessage billInputMessage) {
		flowId =FIND_INVOICE_FOR_PAGINATION;
		this.billInputMessage = billInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return billOutMessage;
	}
	@Override
	public BillOutMessage checkDuplicateInvoiceNum(
			BillInputMessage billInputMessage) {
		flowId =CHECK_DUPLICATE_INVOICE_NUMBER;
		this.billInputMessage = billInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return billOutMessage;
	}
	
	@Override
	public boolean validateInput() {
		// TODO Auto-generated method stub
		if (ADD_BILL.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_BILL.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_BILL.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_BILL_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_BILL.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_LAST_BY_INVOICE_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (INVOICE_SEARCH.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_MAX_INVOICE_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_BILL_BY_EMPLOYEE_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIN_YEAR.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (NEW_BILL_MASTER_SERIES_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_INVOICE_FOR_PAGINATION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (CHECK_DUPLICATE_INVOICE_NUMBER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		
		return false;
	}

	@Override
	public void performBusinessLogic() {
		// TODO Auto-generated method stub

		BillEntity billEntity = new BillEntity();
		if (billInputMessage != null) {
			BillDTO billDTO = billInputMessage.getBillDTO();
			if (billDTO != null) {
				BeanUtils.copyProperties(billDTO, billEntity);
				PartyDTO partyDTO = billDTO.getPartyDTO();
				logger.info("Party = " + partyDTO);
				if (partyDTO != null) {
					PartyEntity partyEntity = new PartyEntity();
					copyObject(partyDTO, partyEntity);
					billEntity.setPartyEntity(partyEntity);
				}
				
				EmployeeDTO employeeDTO = billDTO.getEmployeeDTO();
				if (employeeDTO != null) {
					EmployeeEntity employeeEntity = new EmployeeEntity();
					copyObject(employeeDTO, employeeEntity);
					billEntity.setEmployeeEntity(employeeEntity);
				}
				
				
				BranchDTO branchDTO = billDTO.getBranchDTO();
				if (branchDTO != null) {
					BranchEntity branchEntity = new BranchEntity();
					copyObject(branchDTO, branchEntity);
					billEntity.setBranchEntity(branchEntity);
				}
				
				
			}
		}

		if (ADD_BILL.equals(flowId)) {
			// List<BillEntity> list =
			// storageBillDAO.findById(billEntity.getInvoiceAutoId());
			List<BillEntity> list = storageBillDAO.findByInvoiceSeries(billEntity.getInvoiceNumber());
			logger.info(flowId + ": " + list);
			billOutMessage = new BillOutMessage();
			if (list != null && list.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("1", billEntity.getInvoiceNumber()+" Invoice Number is already exist,it can't be duplicate");
				errorDTO.setErrorCode(billEntity.getInvoiceNumber());
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				billOutMessage.setErrorListDTO(errorListDTO);
			} else {
				billOutMessage.setErrorListDTO(null);
				storageBillDAO.create(billEntity);
			}
		} else if (UPDATE_BILL.equals(flowId)) {
			// batchEntity.setDeletedFlag(0);
			storageBillDAO.update(billEntity);
		} else if (DELETE_BILL.equals(flowId)) {
			storageBillDAO.delete(billEntity);
		} else if (FIND_BILL_BY_ID.equals(flowId)) {
			BillDTO billDTO = billInputMessage.getBillDTO();

			List<BillEntity> list = storageBillDAO.findById(billDTO.getInvoiceAutoId());

			popUpList(list);
			
			logger.info("************---: " + list);
		}
		else if (FIN_YEAR.equals(flowId)) {
			
			List list = storageBillDAO.getFinacialYear();
			billOutMessage = new BillOutMessage();
			billOutMessage.setList(list);
		   }
		 else if (FIND_BILL_BY_EMPLOYEE_ID.equals(flowId)) {
				BillDTO billDTO = billInputMessage.getBillDTO();

				List<BillEntity> list = storageBillDAO.findByEmployeeId(billDTO.getPartyId());

				popUpList(list);
				
				logger.info("************---: " + list);
			} 
		else if (FIND_ALL_BILL.equals(flowId)) {
			
			List<BillEntity> list = storageBillDAO.load();
			billOutMessage = new BillOutMessage();
			popUpList(list);
			

		} else if (FIND_LAST_BY_INVOICE_ID.equals(flowId)) {
			
			List<BillEntity> list = storageBillDAO.getLastByInvoiceId();
			billOutMessage = new BillOutMessage();
			// set the data to the output message.
			popUpList(list);
		
		} 
		else if (FIND_MAX_INVOICE_ID.equals(flowId)) {
		
			List<BillEntity> list = storageBillDAO.getMaxInvoiceId();
			billOutMessage = new BillOutMessage();
			// set the data to the output message.
			popUpList(list);
		
		}
		
		else if (FIND_INVOICE_FOR_PAGINATION.equals(flowId)) {
			
			List<BillEntity> list = storageBillDAO.loadInvoicePagination(billInputMessage.getBillDTO().getNext());
			billOutMessage = new BillOutMessage();
			// set the data to the output message.
			popUpList(list);
		
		}else if (CHECK_DUPLICATE_INVOICE_NUMBER.equals(flowId)) {
			List<BillEntity> list = storageBillDAO.findByInvoiceSeries(billInputMessage.getBillDTO().getInvoiceNumber());
			billOutMessage = new BillOutMessage();
			// set the data to the output message.
			popUpList(list);
		
		}
		
		 else if (NEW_BILL_MASTER_SERIES_NO.equals(flowId)) {
				Integer seriesNo = storageBillDAO.getNewSeriesNo(billEntity.getFinyr());
				billOutMessage = new BillOutMessage();
				billOutMessage.setBillSeries(seriesNo);
			}
		else if (INVOICE_SEARCH.equals(flowId)) {

			// billOutMessage = new BillOutMessage();
			BillDTO billDTO = billInputMessage.getBillDTO();

			Date invoiceDate = null;
			Date fromDate = null;
			Date toDate = null;
			String partyName = null;
			String invoiceNumber = null;
            String itemName=null;
			try {
				if (billDTO.getInvoiceDate() != null) {
					invoiceDate = billDTO.getInvoiceDate();

				}
				if (billDTO.getInvoiceNumber() != null) {
					invoiceNumber = billDTO.getInvoiceNumber();

				}
				if (billDTO.getPartyName() != null) {
					partyName = billDTO.getPartyName();
				}
				if (billDTO.getItemName() != null) {
					itemName = billDTO.getItemName();
				}
				if (billDTO.getFromDate()!= null) {
					fromDate = billDTO.getFromDate();
				}
				if (billDTO.getToDate() != null) {
					toDate = billDTO.getToDate();
				}
			} catch (Exception e) {

			}
			
			List<BillEntity> list = storageBillDAO.search(fromDate,toDate,
					partyName, invoiceNumber,itemName);
			popUpList(list);

			

		}
	}

	
	@Override
	public void formatOutput() {
		// TODO Auto-generated method stub

	}

	void popUpList(List<BillEntity> list) {
		logger.info("SOM Entity List  :" + list);
		billOutMessage = new BillOutMessage();
		// set the data to the output message.
		if (list != null) {
			List<BillDTO> resultList = new ArrayList<BillDTO>();
			BillDTO billDTO;
			for (BillEntity entity : list) {
				billDTO = new BillDTO();
				// Spring
				BeanUtils.copyProperties(entity, billDTO);

				PartyEntity partyEntity = entity.getPartyEntity();
				if (partyEntity != null) {
					PartyDTO partyDTO = new PartyDTO();
					copyObject(partyEntity, partyDTO);
					billDTO.setPartyDTO(partyDTO);
				}
				EmployeeEntity employeeEntity = entity.getEmployeeEntity();
				if (employeeEntity != null) {
					EmployeeDTO employeeDTO = new EmployeeDTO();
					copyObject(employeeEntity, employeeDTO);
					billDTO.setEmployeeDTO(employeeDTO);
				}
				
BranchEntity branchEntity = entity.getBranchEntity();
				if (branchEntity != null) {
					BranchDTO branchDTO = new BranchDTO();
					copyObject(branchEntity, branchDTO);
					billDTO.setBranchDTO(branchDTO);
				}
				
				resultList.add(billDTO);
			}
			billOutMessage.setBillDTOList(resultList);
		}
	}

	private List<BillDTO> convertBillEntityListTOBillDtoList(
			List<BillEntity> list) {

		billOutMessage = new BillOutMessage();
		List<BillDTO> resultList = null;
		// set the data to the outputput message.
		if (list != null) {
			BillDTO billDTO;
			resultList = new ArrayList<BillDTO>();
			for (BillEntity billEntity : list) {
				billDTO = new BillDTO();
				if (billEntity != null) {
					BeanUtils.copyProperties(billEntity, billDTO);
					resultList.add(billDTO);
				}
			}
		}

		return resultList;
	}
	private void copyObject(Object source, Object target) {
		try {
			BeanUtils.copyProperties(source, target);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List getCountInvoiceToEmail(String startDate, String endDate) {
		List list=	storageBillDAO.getCountInvoiceToEmail(startDate, endDate);
		return list;
	}

	@Override
	public boolean checkDuplicateProformaNum(String proformaNumer) {
	String proformaNum=	storageBillDAO.checkDuplicateProformaNumber(proformaNumer);
		if(proformaNum!=null && proformaNum.length()>0){
			return true;
		}
	
	return false;
	}

	

	
	
	

	
}
