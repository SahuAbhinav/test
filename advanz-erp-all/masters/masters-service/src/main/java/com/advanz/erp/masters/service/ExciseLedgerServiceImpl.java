package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.ExciseLedgerEntity;
import com.advanz.erp.masters.model.ExciseLedgerDTO;
import com.advanz.erp.masters.model.msg.ExciseLedgerInputMessage;
import com.advanz.erp.masters.model.msg.ExciseLedgerOutputMessage;
import com.advanz.erp.masters.service.business.IExciseLedgerService;
import com.advanz.erp.masters.storage.IStorageExciseLedgerDAO;

public class ExciseLedgerServiceImpl implements IExciseLedgerService {
	
	public static final String CREATE_EXCISE_LEDGER = "CreateExciseLedger";
	public static final String UPDATE_EXCISE_LEDGER = "UpdateExciseLedger";
	public static final String DELETE_EXCISE_LEDGER_BY_GRN = "DeleteExciseLedgerByGrn";
	public static final String DELETE_EXCISE_LEDGER_BY_INVOICE = "DeleteExciseLedgerByInvoice";
	public static final String DELETE_EXCISE_LEDGER_BY_ISSUE = "DeleteExciseLedgerByIssue";
	public static final String FIND_ALL_EXCISE_LEDGER = "FindAllExciseLedger";
	public static final String UPDATE_EXCISE_LEDGER_BY_ISSUE = "updateExciseByIssue";
	public static final String UPDATE_EXCISE_LEDGER_BY_GRN = "updateExciseByGrn";
	public static final String UPDATE_EXCISE_LEDGER_BY_INVOICE = "updateExciseByInvoice";
	
	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
														
	@Autowired
	private IStorageExciseLedgerDAO iStorageExciseLedgerDAO;
	
	private ExciseLedgerInputMessage exciseLedgerInputMessage;
	private ExciseLedgerOutputMessage exciseLedgerOutputMessage;
 	
	
	@Override
	public ExciseLedgerOutputMessage findAllExciseLedger() {
	    flowId=FIND_ALL_EXCISE_LEDGER;
		advanzErpServiceTemplate.execute(this);
		return exciseLedgerOutputMessage;
	}

	@Override
	public ExciseLedgerOutputMessage createExciseLedger(ExciseLedgerInputMessage exciseLedgerInputMessage) {
		this.exciseLedgerInputMessage=exciseLedgerInputMessage;
		flowId=CREATE_EXCISE_LEDGER;
		advanzErpServiceTemplate.execute(this);
		return exciseLedgerOutputMessage;
	}

	@Override
	public ExciseLedgerOutputMessage updateExciseLedger(ExciseLedgerInputMessage exciseLedgerInputMessage) {
		this.exciseLedgerInputMessage=exciseLedgerInputMessage;
		flowId=UPDATE_EXCISE_LEDGER;
		advanzErpServiceTemplate.execute(this);
		return exciseLedgerOutputMessage;
	}

	@Override
	public ExciseLedgerOutputMessage removeExciseLedgerByGrn(ExciseLedgerInputMessage exciseLedgerInputMessage) {
		this.exciseLedgerInputMessage=exciseLedgerInputMessage;
		flowId=DELETE_EXCISE_LEDGER_BY_GRN;
		advanzErpServiceTemplate.execute(this);
		return exciseLedgerOutputMessage;
	}
	
	
	@Override
	public ExciseLedgerOutputMessage removeExciseLedgerByInvoice(ExciseLedgerInputMessage exciseLedgerInputMessage) {
		this.exciseLedgerInputMessage=exciseLedgerInputMessage;
		flowId=DELETE_EXCISE_LEDGER_BY_INVOICE;
		advanzErpServiceTemplate.execute(this);
		return exciseLedgerOutputMessage;
	}

	@Override
	public ExciseLedgerOutputMessage removeExciseLedgerByIssue(ExciseLedgerInputMessage exciseLedgerInputMessage) {
		this.exciseLedgerInputMessage=exciseLedgerInputMessage;
		
		flowId=DELETE_EXCISE_LEDGER_BY_ISSUE;
		advanzErpServiceTemplate.execute(this);
		return exciseLedgerOutputMessage;
	}

	
	@Override
	public ExciseLedgerOutputMessage updateExciseLedgerByIssue(ExciseLedgerInputMessage exciseLedgerInputMessage) {
		this.exciseLedgerInputMessage=exciseLedgerInputMessage;
		flowId=UPDATE_EXCISE_LEDGER_BY_ISSUE;
		advanzErpServiceTemplate.execute(this);
		return exciseLedgerOutputMessage;
	}


	@Override
	public ExciseLedgerOutputMessage updateExciseLedgerByGrn(ExciseLedgerInputMessage exciseLedgerInputMessage) {
		this.exciseLedgerInputMessage=exciseLedgerInputMessage;
		flowId=UPDATE_EXCISE_LEDGER_BY_GRN;
		advanzErpServiceTemplate.execute(this);
		return exciseLedgerOutputMessage;
	}

	@Override
	public ExciseLedgerOutputMessage updateExciseLedgerByInvoice(ExciseLedgerInputMessage exciseLedgerInputMessage) {
		this.exciseLedgerInputMessage=exciseLedgerInputMessage;
		flowId=UPDATE_EXCISE_LEDGER_BY_INVOICE;
		advanzErpServiceTemplate.execute(this);
		return exciseLedgerOutputMessage;
	}

	
	
	@Override
	public void performBusinessLogic() {
		ExciseLedgerEntity exciseLedgerEntity=new ExciseLedgerEntity();
		if(exciseLedgerInputMessage!=null)
		{
		 try
		 {
		  ExciseLedgerDTO exciseLedgerDTO=exciseLedgerInputMessage.getExciseLedgerDTO();
		  BeanUtils.copyProperties(exciseLedgerDTO, exciseLedgerEntity); 
		 }
		 catch (Exception e) {
			e.printStackTrace();
		 } 
		}
		
		if (CREATE_EXCISE_LEDGER.equals(flowId)) {
			List<ExciseLedgerEntity> entity=iStorageExciseLedgerDAO.findExciseByItemId(exciseLedgerInputMessage.getExciseLedgerDTO().getItemId());
			
			if(entity!=null && entity.size()>0)
			{
			int transactionSrNo=entity.size();
			transactionSrNo=transactionSrNo+1;
			exciseLedgerEntity.setTransactionSrNo(transactionSrNo);
			}
			else
			{
			 exciseLedgerEntity.setTransactionSrNo(1);
			}
			iStorageExciseLedgerDAO.create(exciseLedgerEntity);
		} 
		else if (UPDATE_EXCISE_LEDGER.equals(flowId)) {
			iStorageExciseLedgerDAO.update(exciseLedgerEntity);
		}
		else if (DELETE_EXCISE_LEDGER_BY_GRN.equals(flowId)) {
			List<ExciseLedgerEntity> entities=iStorageExciseLedgerDAO.findSnoByGrnNumber(exciseLedgerEntity.getGrnNumber());
			
			ExciseLedgerEntity exciseLedgerEntity2=new ExciseLedgerEntity();
		   for(int i=0;i<entities.size();i++)
			{
			exciseLedgerEntity2=entities.get(i);
			iStorageExciseLedgerDAO.delete(exciseLedgerEntity2);
		  }
		   //iStorageExciseLedgerDAO.delete(exciseLedgerEntity);
		}
		
		else if (DELETE_EXCISE_LEDGER_BY_INVOICE.equals(flowId)) {
			List<ExciseLedgerEntity> entities=iStorageExciseLedgerDAO.findSnoByInvoiceNumber(exciseLedgerEntity.getInvoiceNumber());
			ExciseLedgerEntity exciseLedgerEntity2=new ExciseLedgerEntity();
			for(int i=0;i<entities.size();i++)
			{
			exciseLedgerEntity2=entities.get(i);
			iStorageExciseLedgerDAO.delete(exciseLedgerEntity2);
		  }
		}
	
		else if (DELETE_EXCISE_LEDGER_BY_ISSUE.equals(flowId)) {
			List<ExciseLedgerEntity> entities=iStorageExciseLedgerDAO.findSnoByIssueNumber(exciseLedgerEntity.getIssueNumber());
			ExciseLedgerEntity exciseLedgerEntity2=new ExciseLedgerEntity();
			for(int i=0;i<entities.size();i++)
			{
			exciseLedgerEntity2=entities.get(i);
			iStorageExciseLedgerDAO.delete(exciseLedgerEntity2);
		  }
		}
		
	  else if (UPDATE_EXCISE_LEDGER_BY_ISSUE.equals(flowId)) {
		List<ExciseLedgerEntity> entities=iStorageExciseLedgerDAO.findSnoByIssueNumber(exciseLedgerEntity.getIssueNumber());
		for(int i=0;i<entities.size();i++)
		 {
		  exciseLedgerEntity.setTransactionSrNo(entities.get(i).getTransactionSrNo());
		  exciseLedgerEntity.setSno(entities.get(i).getSno());
		  iStorageExciseLedgerDAO.update(exciseLedgerEntity);
		 }
		}

	 else if (UPDATE_EXCISE_LEDGER_BY_GRN.equals(flowId)) {
		List<ExciseLedgerEntity> entities=iStorageExciseLedgerDAO.findSnoByGrnNumber(exciseLedgerEntity.getGrnNumber());
		for(int i=0;i<entities.size();i++)
		{
		exciseLedgerEntity.setTransactionSrNo(entities.get(i).getTransactionSrNo());	
		exciseLedgerEntity.setSno(entities.get(i).getSno());
		iStorageExciseLedgerDAO.update(exciseLedgerEntity);
		}	 	
	   }

   else if (UPDATE_EXCISE_LEDGER_BY_INVOICE.equals(flowId)) {
	 List<ExciseLedgerEntity> entities=iStorageExciseLedgerDAO.findSnoByInvoiceNumber(exciseLedgerEntity.getInvoiceNumber());
	 for(int i=0;i<entities.size();i++)
		{
		exciseLedgerEntity.setTransactionSrNo(entities.get(i).getTransactionSrNo());
		exciseLedgerEntity.setSno(entities.get(i).getSno());
		iStorageExciseLedgerDAO.update(exciseLedgerEntity);
		}	 	
	   }

		else if (FIND_ALL_EXCISE_LEDGER.equals(flowId)) {
		} 
	}


	@Override
	public boolean validateInput() {
		if (CREATE_EXCISE_LEDGER.equals(flowId)) {
			return true;
		} 
		else if (UPDATE_EXCISE_LEDGER.equals(flowId)) {
			return true;
		}
		else if (DELETE_EXCISE_LEDGER_BY_GRN.equals(flowId)) {
			return true;
		}
		else if (DELETE_EXCISE_LEDGER_BY_INVOICE.equals(flowId)) {
			return true;
		}
		else if (DELETE_EXCISE_LEDGER_BY_ISSUE.equals(flowId)) {
			return true;
		}
		else if (FIND_ALL_EXCISE_LEDGER.equals(flowId)) {
			return true;
		}
		else if (UPDATE_EXCISE_LEDGER_BY_ISSUE.equals(flowId)) {
			return true;
		}
		else if (UPDATE_EXCISE_LEDGER_BY_GRN.equals(flowId)) {
			return true;
		}
		else if (UPDATE_EXCISE_LEDGER_BY_INVOICE.equals(flowId)) {
			return true;
		}
		return false;
	}


	@Override
	public void formatOutput() {
		if (CREATE_EXCISE_LEDGER.equals(flowId)) {
		} 
		else if (UPDATE_EXCISE_LEDGER.equals(flowId)) {
		}
		else if (DELETE_EXCISE_LEDGER_BY_GRN.equals(flowId)) {
		}
		else if (FIND_ALL_EXCISE_LEDGER.equals(flowId)) {
		}
		else if (DELETE_EXCISE_LEDGER_BY_INVOICE.equals(flowId)) {
			}
		else if (DELETE_EXCISE_LEDGER_BY_ISSUE.equals(flowId)) {
			}
		else if (UPDATE_EXCISE_LEDGER_BY_ISSUE.equals(flowId)) {
		}
		else if (UPDATE_EXCISE_LEDGER_BY_GRN.equals(flowId)) {
		}
		else if (UPDATE_EXCISE_LEDGER_BY_INVOICE.equals(flowId)) {
		}
	}
	
	public List<ExciseLedgerDTO> convertExciseLedgerEntityToExciseLedgerDTO(List<ExciseLedgerEntity> list)
	{
	List<ExciseLedgerDTO> resuList=new ArrayList<ExciseLedgerDTO>();
    ExciseLedgerDTO exciseLedgerDTO=null;	
	try
	{
	for (ExciseLedgerEntity entity:list)
	{
	  exciseLedgerDTO=new ExciseLedgerDTO();		
	  BeanUtils.copyProperties(entity, exciseLedgerDTO);
	  resuList.add(exciseLedgerDTO);
	}
	}
	catch (Exception e) {
		e.printStackTrace(); 
	}
   return resuList;
  }

	
	
}
