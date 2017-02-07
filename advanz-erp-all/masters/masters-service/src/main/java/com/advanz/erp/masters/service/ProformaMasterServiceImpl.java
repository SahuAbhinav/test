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
import com.advanz.erp.masters.entity.jpa.EmployeeEntity;
import com.advanz.erp.masters.entity.jpa.PartyEntity;
import com.advanz.erp.masters.entity.jpa.ProformaMasterEntity;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.ProformaMasterDTO;
import com.advanz.erp.masters.model.msg.ProformaMasterInputMessage;
import com.advanz.erp.masters.model.msg.ProformaMasterOutMessage;
import com.advanz.erp.masters.service.business.IProformaMasterService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageProformaMasterDAO;

public class ProformaMasterServiceImpl implements IProformaMasterService {

	public static final String CREATE_BILL = "Bill";
	public static final String UPDATE_BILL = "UpdateBill";
	public static final String ADD_BILL = "AddBill";
	public static final String DELETE_BILL = "DeleteBill";
	public static final String FIND_BILL_BY_ID = "FindBillById";
	public static final String FIND_ALL_BILL = "FindAllBills";
	public static final String FIND_LAST_BY_INVOICE_ID = "FindLastByInvoiceId";
	public static final String INVOICE_SEARCH = "SearchInvoice";
	public static final String FIND_MAX_INVOICE_ID = "FindMaxInvoiceId";
	public static final String FIND_BILL_BY_INVOICE_NO = "FindBillByInvoiceNo";
	
	public static final String FIN_YEAR = "FinYear";
	public static final String NEW_PROFORMA_MASTER_SERIES_NO = "NewProformaMastersSeriesNo";
	public static final String PROFOMA_LIST_WITH_PAGINATION = "ProformaListWithPagination";
	public String flowId = "";
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();

	@Autowired
	public IStorageProformaMasterDAO storageProformaMAsterDAO;

	public ProformaMasterInputMessage proformaMasterInputMessage;

	public ProformaMasterOutMessage proformaMasterOutMessage;
	
	@Autowired
	public IZoneService zoneService;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ProformaMasterServiceImpl.class);

	@Override
	public ProformaMasterOutMessage createBill(ProformaMasterInputMessage proformaMasterInputMessage) {
		// TODO Auto-generated method stub
		flowId = ADD_BILL;
		// assign the message to the class level variable.
		this.proformaMasterInputMessage = proformaMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return proformaMasterOutMessage;
	}

	@Override
	public ProformaMasterOutMessage updateBill(ProformaMasterInputMessage proformaMasterInputMessage) {
		// TODO Auto-generated method stub
		flowId = UPDATE_BILL;
		this.proformaMasterInputMessage = proformaMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return proformaMasterOutMessage;
	}

	@Override
	public ProformaMasterOutMessage deleteBill(ProformaMasterInputMessage proformaMasterInputMessage) {
		// TODO Auto-generated method stub
		flowId = DELETE_BILL;
		this.proformaMasterInputMessage = proformaMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return proformaMasterOutMessage;
	}

	@Override
	public ProformaMasterOutMessage findBillById(ProformaMasterInputMessage proformaMasterInputMessage) {
		// TODO Auto-generated method stub
		flowId = FIND_BILL_BY_ID;
		this.proformaMasterInputMessage = proformaMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return proformaMasterOutMessage;
	}

	@Override
	public ProformaMasterOutMessage findAllBills() {
		// TODO Auto-generated method stub
		flowId = FIND_ALL_BILL;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return proformaMasterOutMessage;
	}

	public ProformaMasterOutMessage getLastByInvoiceId() {
		flowId = FIND_LAST_BY_INVOICE_ID;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return proformaMasterOutMessage;
	}

	@Override
	public ProformaMasterOutMessage searchInvoice(ProformaMasterInputMessage proformaMasterInputMessage) {
		flowId = INVOICE_SEARCH;
		this.proformaMasterInputMessage = proformaMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return proformaMasterOutMessage;
	}
	@Override
	public ProformaMasterOutMessage getMaxInvoiceId() {
		flowId =FIND_MAX_INVOICE_ID;
		this.proformaMasterInputMessage = proformaMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return proformaMasterOutMessage;
	}
	
	@Override
	public ProformaMasterOutMessage findBillByInvoiceNo(
			ProformaMasterInputMessage proformaMasterInputMessage) {
		flowId = FIND_BILL_BY_INVOICE_NO;
		this.proformaMasterInputMessage = proformaMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return proformaMasterOutMessage;
	}

	
	@Override
	public ProformaMasterOutMessage getFinacialYear() {
		flowId =FIN_YEAR;
		this.proformaMasterInputMessage = proformaMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return proformaMasterOutMessage;
	}
	@Override
	public ProformaMasterOutMessage getNewProformaMasterSeriesNo(
			ProformaMasterInputMessage proformaMasterInputMessage) {
		flowId = NEW_PROFORMA_MASTER_SERIES_NO;
		this.proformaMasterInputMessage = proformaMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return proformaMasterOutMessage;
	}
	
	@Override
	public ProformaMasterOutMessage getProformaListWithPagination(ProformaMasterInputMessage proformaMasterInputMessage){
		
		flowId = PROFOMA_LIST_WITH_PAGINATION;
		this.proformaMasterInputMessage = proformaMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return proformaMasterOutMessage;
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
		else if (FIND_BILL_BY_INVOICE_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIN_YEAR.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (NEW_PROFORMA_MASTER_SERIES_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (PROFOMA_LIST_WITH_PAGINATION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		
		return false;
	}

	@Override
	public void performBusinessLogic() {
		// TODO Auto-generated method stub

		ProformaMasterEntity proformaMasterEntity = new ProformaMasterEntity();
		if (proformaMasterInputMessage != null) {
			ProformaMasterDTO proformaMasterDTO = proformaMasterInputMessage.getProformaMasterDTO();
			if (proformaMasterDTO != null) {
				BeanUtils.copyProperties(proformaMasterDTO, proformaMasterEntity);
				PartyDTO partyDTO = proformaMasterDTO.getPartyDTO();
				logger.info("Party = " + partyDTO);
				if (partyDTO != null) {
					PartyEntity partyEntity = new PartyEntity();
					copyObject(partyDTO, partyEntity);
					proformaMasterEntity.setPartyEntity(partyEntity);
				}
				EmployeeDTO employeeDTO = proformaMasterDTO.getEmployeeDTO();
				if (employeeDTO != null) {
					EmployeeEntity employeeEntity = new EmployeeEntity();
					copyObject(employeeDTO, employeeEntity);
					proformaMasterEntity.setEmployeeEntity(employeeEntity);
				}
				
				
				BranchDTO branchDTO = proformaMasterDTO.getBranchDTO();
				if (branchDTO != null) {
					BranchEntity branchEntity = new BranchEntity();
					copyObject(branchDTO, branchEntity);
					proformaMasterEntity.setBranchEntity(branchEntity);
				}
				
				
			}
		}

		if (ADD_BILL.equals(flowId)) {
			// List<BillEntity> list =
			// storageBillDAO.findById(billEntity.getInvoiceAutoId());
			List<ProformaMasterEntity> list = storageProformaMAsterDAO.findByInvoiceSeries(proformaMasterEntity.getInvoiceNumber());
			logger.info(flowId + ": " + list);
			proformaMasterOutMessage = new ProformaMasterOutMessage();
			if (list != null && list.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("1", proformaMasterEntity.getInvoiceNumber()+" Invoice Number is already exist,it can't be duplicate");
				errorDTO.setErrorCode(proformaMasterEntity.getInvoiceNumber());
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				proformaMasterOutMessage.setErrorListDTO(errorListDTO);
			} else {
				proformaMasterOutMessage.setErrorListDTO(null);
				storageProformaMAsterDAO.create(proformaMasterEntity);
			}
		} else if (UPDATE_BILL.equals(flowId)) {
			// batchEntity.setDeletedFlag(0);
			storageProformaMAsterDAO.update(proformaMasterEntity);
		} else if (DELETE_BILL.equals(flowId)) {
			storageProformaMAsterDAO.delete(proformaMasterEntity);
		} else if (FIND_BILL_BY_ID.equals(flowId)) {
			ProformaMasterDTO proformaMasterDTO = proformaMasterInputMessage.getProformaMasterDTO();

			List<ProformaMasterEntity> list = storageProformaMAsterDAO.findById(proformaMasterDTO.getInvoiceAutoId());

			popUpList(list);
			
			logger.info("************---: " + list);
		}
		else if (FIND_BILL_BY_INVOICE_NO.equals(flowId)) {
			ProformaMasterDTO proformaMasterDTO = proformaMasterInputMessage.getProformaMasterDTO();
			List<ProformaMasterEntity> list = storageProformaMAsterDAO.findByExsiceInvoiceNo(proformaMasterDTO.getExciseInvoiceNo());
			popUpList(list);
			logger.info("************---: " + list);
		}
		
		
		
		
		else if (FIND_ALL_BILL.equals(flowId)) {
			
			List<ProformaMasterEntity> list = storageProformaMAsterDAO.load();
			proformaMasterOutMessage = new ProformaMasterOutMessage();
			// set the data to the output message.

			popUpList(list);
			/*
			 * if (list != null) { List<BillDTO> resultList =
			 * convertBillEntityListTOBillDtoList(list);
			 * billOutMessage.setBillDTOList(resultList); }
			 */

		} else if (FIND_LAST_BY_INVOICE_ID.equals(flowId)) {
			
			List<ProformaMasterEntity> list = storageProformaMAsterDAO.getLastByInvoiceId();
			proformaMasterOutMessage = new ProformaMasterOutMessage();
			// set the data to the output message.
			popUpList(list);
		
		} 
		else if (FIND_MAX_INVOICE_ID.equals(flowId)) {
		
			List<ProformaMasterEntity> list = storageProformaMAsterDAO.getMaxInvoiceId();
			proformaMasterOutMessage = new ProformaMasterOutMessage();
			// set the data to the output message.
			popUpList(list);
		
		}
		
		
		else if (FIN_YEAR.equals(flowId)) {
		
			List list = storageProformaMAsterDAO.getFinacialYear();
			proformaMasterOutMessage = new ProformaMasterOutMessage();
			
			proformaMasterOutMessage.setList(list);
		   }
		 else if (NEW_PROFORMA_MASTER_SERIES_NO.equals(flowId)) {
			 Integer seriesNo=0;
			 Timestamp date = zoneService.getFirstDayOfFinYear();
			 List list= storageProformaMAsterDAO
						.getNewSeriesNo(proformaMasterEntity.getFinyr());
				if (list != null && list.size() > 0) {
					Object[] obj=(Object[]) list.get(0);
					
					Number n = (Number) obj[0];
					if (n != null)
						seriesNo = n.intValue();
					if(obj[1]!=null && obj[1]!="")
						date=(Timestamp)obj[1];
				}
				seriesNo++;
				proformaMasterOutMessage = new ProformaMasterOutMessage();
				
				proformaMasterOutMessage.setProformaSeries(seriesNo);
				proformaMasterOutMessage.setProformaSeriesDate(date);
			}
		 else if (PROFOMA_LIST_WITH_PAGINATION.equals(flowId)) {
			 ProformaMasterDTO billDTO = proformaMasterInputMessage.getProformaMasterDTO();
			 List<ProformaMasterEntity> list = storageProformaMAsterDAO.getProformaListWithPagination(billDTO.getNext());
			 popUpList(list);
		 }
		
		
		
		else if (INVOICE_SEARCH.equals(flowId)) {

			// billOutMessage = new BillOutMessage();
			ProformaMasterDTO billDTO = proformaMasterInputMessage.getProformaMasterDTO();

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
			
			List<ProformaMasterEntity> list = storageProformaMAsterDAO.search(fromDate,toDate,
					partyName, invoiceNumber,itemName);

			popUpList(list);

			
		}
	}



	@Override
	public void formatOutput() {
		// TODO Auto-generated method stub

	}

	void popUpList(List<ProformaMasterEntity> list) {
		logger.info("SOM Entity List  :" + list);
		proformaMasterOutMessage = new ProformaMasterOutMessage();
		// set the data to the output message.
		if (list != null) {
			List<ProformaMasterDTO> resultList = new ArrayList<ProformaMasterDTO>();
			ProformaMasterDTO proformaMasterDTO;
			for (ProformaMasterEntity entity : list) {
				proformaMasterDTO = new ProformaMasterDTO();
				// Spring
				BeanUtils.copyProperties(entity, proformaMasterDTO);

				PartyEntity partyEntity = entity.getPartyEntity();
				if (partyEntity != null) {
					PartyDTO partyDTO = new PartyDTO();
					copyObject(partyEntity, partyDTO);
					proformaMasterDTO.setPartyDTO(partyDTO);
				}
				EmployeeEntity employeeEntity = entity.getEmployeeEntity();
				if (employeeEntity != null) {
					EmployeeDTO employeeDTO = new EmployeeDTO();
					copyObject(employeeEntity, employeeDTO);
					proformaMasterDTO.setEmployeeDTO(employeeDTO);
				}
				
BranchEntity branchEntity = entity.getBranchEntity();
				if (branchEntity != null) {
					BranchDTO branchDTO = new BranchDTO();
					copyObject(branchEntity, branchDTO);
					proformaMasterDTO.setBranchDTO(branchDTO);
				}
				
				resultList.add(proformaMasterDTO);
			}
			proformaMasterOutMessage.setProformaMasterDTOList(resultList);
		}
	}

	private List<ProformaMasterDTO> convertProformaMasterEntityListTOProformaMasterDtoList(
			List<ProformaMasterEntity> list) {

		proformaMasterOutMessage = new ProformaMasterOutMessage();
		List<ProformaMasterDTO> resultList = null;
		// set the data to the outputput message.
		if (list != null) {
			ProformaMasterDTO proformaMasterDTO;
			resultList = new ArrayList<ProformaMasterDTO>();
			for (ProformaMasterEntity proformaMasterEntity : list) {
				proformaMasterDTO = new ProformaMasterDTO();
				if (proformaMasterEntity != null) {
					BeanUtils.copyProperties(proformaMasterEntity, proformaMasterDTO);
					resultList.add(proformaMasterDTO);
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

	

	

	

	
}
