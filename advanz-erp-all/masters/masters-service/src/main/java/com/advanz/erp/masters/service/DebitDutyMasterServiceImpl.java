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
import com.advanz.erp.masters.entity.jpa.BillEntity;
import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.entity.jpa.DebitDutyMasterEntity;
import com.advanz.erp.masters.entity.jpa.EmployeeEntity;
import com.advanz.erp.masters.entity.jpa.PartyEntity;
import com.advanz.erp.masters.model.BillDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.DebitDutyMasterDTO;
import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.ExciseLedgerDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.msg.BillInputMessage;
import com.advanz.erp.masters.model.msg.BillOutMessage;
import com.advanz.erp.masters.model.msg.DebitDutyMasterInputMessage;
import com.advanz.erp.masters.model.msg.DebitDutyMasterOutPutMessage;
import com.advanz.erp.masters.model.msg.ExciseLedgerInputMessage;
import com.advanz.erp.masters.service.business.IDebitDutyMasterService;
import com.advanz.erp.masters.service.business.IExciseLedgerService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageDebitDutyMasterDAO;
import com.advanz.erp.masters.storage.IStorageExciseLedgerDAO;

public class DebitDutyMasterServiceImpl implements IDebitDutyMasterService{

	
	public static final String CREATE_DEBIT = "Debit";
	public static final String UPDATE_DEBIT = "UpdateDebit";
	public static final String ADD_DEBIT = "AddDebit";
	public static final String DELETE_DEBIT = "DeleteBill";
	public static final String FIND_DEBIT_BY_ID = "FindDebitById";
	public static final String FIND_ALL_DEBIT = "FindAllDebit";
	public static final String FIND_LAST_BY_DEBIT_ID = "FindLastByDebitId";
	public static final String SEARCH = "Search";

	public static final String FIND_MAX_DEBIT_ID = "FindMaxDebitId";
	public static final String FIN_YEAR = "FinYear";
	public String flowId = "";
    public static final String NEW_DEBIT_MASTER_SERIES_NO = "NewDebitMastersSeriesNo";
	
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();

	
	
	@Autowired
	public IStorageDebitDutyMasterDAO storageDebitDutyMasterDAO;
	
	@Autowired
	public IExciseLedgerService exciseLedgerService; 
	@Autowired
	public IZoneService zoneService;
	
	public DebitDutyMasterInputMessage debitDutyMasterInputMessage;
	public DebitDutyMasterOutPutMessage debitDutyMasterOutPutMessage;
	
	private static final Logger logger = LoggerFactory.getLogger(BillServiceImpl.class);
	
	@Override
	public DebitDutyMasterOutPutMessage createDebitDuty(
			DebitDutyMasterInputMessage debitDutyMasterInputMessage) {
		// TODO Auto-generated method stub
		flowId = ADD_DEBIT;
		// assign the message to the class level variable.
		this.debitDutyMasterInputMessage = debitDutyMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return debitDutyMasterOutPutMessage;
	}

	@Override
	public DebitDutyMasterOutPutMessage updateDebitDuty(
			DebitDutyMasterInputMessage debitDutyMasterInputMessage) {
		// TODO Auto-generated method stub
		flowId = UPDATE_DEBIT;
		this.debitDutyMasterInputMessage = debitDutyMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return debitDutyMasterOutPutMessage;
	}

	@Override
	public DebitDutyMasterOutPutMessage deleteDebitDuty(DebitDutyMasterInputMessage debitDutyMasterInputMessage) {
		flowId = DELETE_DEBIT;
		this.debitDutyMasterInputMessage = debitDutyMasterInputMessage;
		//call the template method
		advanzErpServiceTemplate.execute(this);
		return debitDutyMasterOutPutMessage;
	}

	@Override
	public DebitDutyMasterOutPutMessage findDebitDutyById(
			DebitDutyMasterInputMessage debitDutyMasterInputMessage) {
		// TODO Auto-generated method stub
		flowId = FIND_DEBIT_BY_ID;
		this.debitDutyMasterInputMessage = debitDutyMasterInputMessage;
		//call the template method
		advanzErpServiceTemplate.execute(this);
		return debitDutyMasterOutPutMessage;
	}

	@Override
	public DebitDutyMasterOutPutMessage findAllDebit() {
		flowId = FIND_ALL_DEBIT;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return debitDutyMasterOutPutMessage;
	}

	@Override
	public DebitDutyMasterOutPutMessage getLastByDebitDutyId() {
		flowId = FIND_LAST_BY_DEBIT_ID;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return debitDutyMasterOutPutMessage;
	}

	@Override
	public DebitDutyMasterOutPutMessage search(DebitDutyMasterInputMessage debitDutyMasterInputMessage) {
		flowId = SEARCH;
		this.debitDutyMasterInputMessage = debitDutyMasterInputMessage;
		//call the template method
		advanzErpServiceTemplate.execute(this);
		return debitDutyMasterOutPutMessage;
	}

	@Override
	public DebitDutyMasterOutPutMessage getMaxDebitId() {
		flowId =FIND_MAX_DEBIT_ID;
		this.debitDutyMasterInputMessage = debitDutyMasterInputMessage;
		//call the template method
		advanzErpServiceTemplate.execute(this);
		return debitDutyMasterOutPutMessage;
	}

	@Override
	public DebitDutyMasterOutPutMessage getFinacialYear() {
		flowId =FIN_YEAR;
		this.debitDutyMasterInputMessage = debitDutyMasterInputMessage;
		//call the template method
		advanzErpServiceTemplate.execute(this);
		return debitDutyMasterOutPutMessage;
	}
	@Override
	public DebitDutyMasterOutPutMessage getNewDebitDutyMasterSeriesNo(
			DebitDutyMasterInputMessage debitDutyMasterInputMessage) {
		flowId =NEW_DEBIT_MASTER_SERIES_NO;
		this.debitDutyMasterInputMessage = debitDutyMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return debitDutyMasterOutPutMessage;
	}

	
	@Override
	public boolean validateInput() {
		// TODO Auto-generated method stub
		if (ADD_DEBIT.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_DEBIT.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_DEBIT.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_DEBIT_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_DEBIT.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_LAST_BY_DEBIT_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_MAX_DEBIT_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIN_YEAR.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (NEW_DEBIT_MASTER_SERIES_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		return false;
	}

	@Override
	public void performBusinessLogic() {
		// TODO Auto-generated method stub
		DebitDutyMasterEntity debitDutyMasterEntity = new DebitDutyMasterEntity();
		DebitDutyMasterDTO debitDutyMasterDTO =null;
		if (debitDutyMasterInputMessage != null) {
			 debitDutyMasterDTO = debitDutyMasterInputMessage.getDebitDutyMasterDTO();
			if (debitDutyMasterDTO != null) {
				BeanUtils.copyProperties(debitDutyMasterDTO, debitDutyMasterEntity);
				BranchDTO branchDTO = debitDutyMasterDTO.getBranchDTO();
				
				if (branchDTO != null) {
					BranchEntity branchEntityEntity = new BranchEntity();
					copyObject(branchDTO, branchEntityEntity);
					debitDutyMasterEntity.setBranchEntity(branchEntityEntity);
				}
			}
			}
		
		if (ADD_DEBIT.equals(flowId)) {
			// List<BillEntity> list =
			// storageBillDAO.findById(billEntity.getInvoiceAutoId());
			List<DebitDutyMasterEntity> list =null; //storageDebitDutyMasterDAO.findById(debitDutyMasterEntity.getDebitDutyNumber());
			logger.info(flowId + ": " + list);
			debitDutyMasterOutPutMessage = new DebitDutyMasterOutPutMessage();
			if (list != null && list.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("1", debitDutyMasterEntity.getDebitDutyNumber()+" Debit Number is already exist,it can't be duplicate");
				errorDTO.setErrorCode(debitDutyMasterEntity.getDebitDutyNumber());
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				debitDutyMasterOutPutMessage.setErrorListDTO(errorListDTO);
			} else {
				if(debitDutyMasterDTO.getApprovedFlag()!=null && debitDutyMasterDTO.getApprovedFlag()>0){
					
					ExciseLedgerInputMessage exciseLedgerInputMessage = new ExciseLedgerInputMessage();
					ExciseLedgerDTO exciseLedgerDTO = new ExciseLedgerDTO();
					
					exciseLedgerDTO.setApprovedDate(new Date());
					exciseLedgerDTO.setTransactionDate(debitDutyMasterDTO.getDebitDutyDate());
					exciseLedgerDTO.setTransactionSeries(debitDutyMasterDTO.getTransactionSeries());
					exciseLedgerDTO.setBranchId(debitDutyMasterDTO.getBranchDTO().getBranchId());
					exciseLedgerDTO.setExciseAmount(debitDutyMasterDTO.getExciseAmount());
					exciseLedgerDTO.setCessAmount(debitDutyMasterDTO.getEduCessAmount());
					exciseLedgerDTO.sethEduCessAmount(debitDutyMasterDTO.gethEduCessAmount());
					
					exciseLedgerDTO.setReceivedBillExciseAmount(debitDutyMasterDTO.getExciseAmount());
					exciseLedgerDTO.setReceivedEducationCessAmount(debitDutyMasterDTO.getEduCessAmount());
					exciseLedgerDTO.setReceivedHighEducationCessAmount(debitDutyMasterDTO.gethEduCessAmount());
					
					exciseLedgerDTO.setCreatedUserId(debitDutyMasterDTO.getCreatedUserId());
					exciseLedgerInputMessage.setExciseLedgerDTO(exciseLedgerDTO);
					exciseLedgerService.createExciseLedger(exciseLedgerInputMessage);
					
				}
				
				
				debitDutyMasterOutPutMessage.setErrorListDTO(null);
				storageDebitDutyMasterDAO.create(debitDutyMasterEntity);
			}
		} else if (UPDATE_DEBIT.equals(flowId)) {
			// batchEntity.setDeletedFlag(0);
			
			if(debitDutyMasterDTO.getApprovedFlag()!=null && debitDutyMasterDTO.getApprovedFlag()>0){
				
				ExciseLedgerInputMessage exciseLedgerInputMessage = new ExciseLedgerInputMessage();
				ExciseLedgerDTO exciseLedgerDTO = new ExciseLedgerDTO();
				
				exciseLedgerDTO.setApprovedDate(new Date());
				exciseLedgerDTO.setTransactionDate(debitDutyMasterDTO.getDebitDutyDate());
				exciseLedgerDTO.setTransactionSeries(debitDutyMasterDTO.getTransactionSeries());
				exciseLedgerDTO.setBranchId(debitDutyMasterDTO.getBranchDTO().getBranchId());
				exciseLedgerDTO.setExciseAmount(debitDutyMasterDTO.getExciseAmount());
				exciseLedgerDTO.setCessAmount(debitDutyMasterDTO.getEduCessAmount());
				exciseLedgerDTO.sethEduCessAmount(debitDutyMasterDTO.gethEduCessAmount());
				
				exciseLedgerDTO.setReceivedBillExciseAmount(debitDutyMasterDTO.getExciseAmount());
				exciseLedgerDTO.setReceivedEducationCessAmount(debitDutyMasterDTO.getEduCessAmount());
				exciseLedgerDTO.setReceivedHighEducationCessAmount(debitDutyMasterDTO.gethEduCessAmount());
				
				exciseLedgerDTO.setCreatedUserId(exciseLedgerDTO.getCreatedUserId());
				exciseLedgerInputMessage.setExciseLedgerDTO(exciseLedgerDTO);
				exciseLedgerService.createExciseLedger(exciseLedgerInputMessage);
				
			}
			storageDebitDutyMasterDAO.update(debitDutyMasterEntity);
			
			
			
		} else if (DELETE_DEBIT.equals(flowId)) {
			storageDebitDutyMasterDAO.delete(debitDutyMasterEntity);
		}else if (FIND_DEBIT_BY_ID.equals(flowId)) {
			DebitDutyMasterDTO debitDutyMasterDTO1 = debitDutyMasterInputMessage.getDebitDutyMasterDTO();

			List<DebitDutyMasterEntity> list = storageDebitDutyMasterDAO.findById(debitDutyMasterDTO1.getDebitDutyAutoId());

			popUpList(list);
			
			logger.info("************---: " + list);
		}
		else if (FIN_YEAR.equals(flowId)) {
			
			List list = storageDebitDutyMasterDAO.getFinacialYear();
			debitDutyMasterOutPutMessage = new DebitDutyMasterOutPutMessage();
			debitDutyMasterOutPutMessage.setList(list);
		   }
		
		
else if (FIND_ALL_DEBIT.equals(flowId)) {
			
			List<DebitDutyMasterEntity> list = storageDebitDutyMasterDAO.load();
			debitDutyMasterOutPutMessage = new DebitDutyMasterOutPutMessage();
			popUpList(list);
			

		} else if (FIND_LAST_BY_DEBIT_ID.equals(flowId)) {
			
			List<DebitDutyMasterEntity> list = storageDebitDutyMasterDAO.getLastByDebitDutyId();
			debitDutyMasterOutPutMessage = new DebitDutyMasterOutPutMessage();
			// set the data to the output message.
			popUpList(list);
		
		} 
		else if (FIND_MAX_DEBIT_ID.equals(flowId)) {
		
			List<DebitDutyMasterEntity> list = storageDebitDutyMasterDAO.getMaxDebitId();
			debitDutyMasterOutPutMessage = new DebitDutyMasterOutPutMessage();
			// set the data to the output message.
			popUpList(list);
		
		}
		
		 else if (NEW_DEBIT_MASTER_SERIES_NO.equals(flowId)) {
			 Integer seriesNo=0;
			 Timestamp date = zoneService.getFirstDayOfFinYear();
				List list=storageDebitDutyMasterDAO.getNewSeriesNo(debitDutyMasterEntity.getFinyr().toString());
				if (list != null && list.size() > 0) {
					Object[] obj=(Object[]) list.get(0);
					
					Number n = (Number) obj[0];
					if (n != null)
						seriesNo = n.intValue();
					if(obj[1]!=null && obj[1]!="")
						date=(Timestamp)obj[1];
				}
				seriesNo++;
				
				debitDutyMasterOutPutMessage = new DebitDutyMasterOutPutMessage();
				debitDutyMasterOutPutMessage.setDebitDutySeries(seriesNo);
				debitDutyMasterOutPutMessage.setDebitDutySeriesDate(date);
			}
		else if (SEARCH.equals(flowId)) {

			// billOutMessage = new BillOutMessage();
			DebitDutyMasterDTO debitDutyMasterDTO1 = debitDutyMasterInputMessage.getDebitDutyMasterDTO();

			Date debitDutyDate = null;
			Date fromDate = null;
			Date toDate = null;
            Integer approvedFlag=null;
			try {
				if (debitDutyMasterDTO1.getDebitDutyDate() != null) {
					debitDutyDate = debitDutyMasterDTO1.getDebitDutyDate();

				}
				if (debitDutyMasterDTO1.getFromDate()!= null) {
					fromDate = debitDutyMasterDTO1.getFromDate();
				}
				if (debitDutyMasterDTO1.getToDate() != null) {
					toDate = debitDutyMasterDTO1.getToDate();
				}if (debitDutyMasterDTO1.getApprovedFlag() != null && debitDutyMasterDTO1.getApprovedFlag()>0) {
					approvedFlag = debitDutyMasterDTO1.getApprovedFlag();
				}
				
			} catch (Exception e) {

			}
			List<DebitDutyMasterEntity> list = storageDebitDutyMasterDAO.search(fromDate,toDate,approvedFlag);
			popUpList(list);

		}
		
	}
		private void copyObject(Object source, Object target) {
			try {
				BeanUtils.copyProperties(source, target);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	@Override
	public void formatOutput() {
		// TODO Auto-generated method stub
		
	}
	void popUpList(List<DebitDutyMasterEntity> list) {
		logger.info("SOM Entity List  :" + list);
		debitDutyMasterOutPutMessage = new DebitDutyMasterOutPutMessage();
		// set the data to the output message.
		if (list != null) {
			List<DebitDutyMasterDTO> resultList = new ArrayList<DebitDutyMasterDTO>();
			DebitDutyMasterDTO debitDutyMasterDTO;
			for (DebitDutyMasterEntity entity : list) {
				debitDutyMasterDTO = new DebitDutyMasterDTO();
				// Spring
				BeanUtils.copyProperties(entity, debitDutyMasterDTO);
				
               BranchEntity branchEntity = entity.getBranchEntity();
				if (branchEntity != null) {
					BranchDTO branchDTO = new BranchDTO();
					copyObject(branchEntity, branchDTO);
					debitDutyMasterDTO.setBranchDTO(branchDTO);
				}
				
				resultList.add(debitDutyMasterDTO);
			}
			debitDutyMasterOutPutMessage.setDebitDutyMasterDTOList(resultList);
		}
	}

	
	
	
	
		
}
