package com.advanz.erp.masters.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.AdvanceAmountEntity;
import com.advanz.erp.masters.entity.jpa.EmployeeEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.model.AdvanceAmountDTO;
import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.msg.AdvanceAmountInputMessage;
import com.advanz.erp.masters.model.msg.AdvanceAmountOutputMessage;
import com.advanz.erp.masters.service.business.IAdvanceAmountService;
import com.advanz.erp.masters.storage.IStorageAdvanceAmountDAO;
@Service
public class AdvanceAmountServiceImpl implements IAdvanceAmountService{
	public static final String CREATE_ADVANCE_AMOUNT = "createAdvanceAmount";
	public static final String UPDATE_ADVANCE_AMOUNT = "UpdateAdvanceAmount";
	public static final String SEARCH_ADVANCE_AMOUNT= "searchAdvanceAmount";
	public static final String DELETE_ADVANCE_AMOUNT = "DeleteAdvanceAmount";
	public static final String FIND_ALL_ADVANCE_AMOUNT = "findAllAdvanceAmount";
	public static final String FIND_BY_ID_ADVANCE_AMOUNT = "findByIdAdvanceAmount";
	public static final String FIND_BY_EMPLOYEE_ID_AMOUNT = "findByEmployeeId";
	public static final String FIND_TRANSACTION_ID = "findTransactionId";
	public static final String FIND_BY_LAST_TRANSACTION_DATE = "findLastTransactionDate";
	public static final String FIND_BY_EMPID_TRANSACTION_DATE = "findempIdTransactionDate";
	public String flowId = "";
	
	@Autowired
	IStorageAdvanceAmountDAO storageAdvnaceAmountDao;
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();
	
	private static final Logger logger = LoggerFactory.getLogger(AdvanceAmountServiceImpl.class);
	public AdvanceAmountInputMessage advanceAmountInputMessage;
	public AdvanceAmountOutputMessage advanceAmountOutputMessage;
	
	@Override
	public AdvanceAmountOutputMessage loadAllList()
	{
		flowId=FIND_ALL_ADVANCE_AMOUNT;
		advanzErpServiceTemplate.execute(this);	
		return advanceAmountOutputMessage;
	}
	@Override
	public AdvanceAmountOutputMessage search(AdvanceAmountInputMessage advanceAmountInputMessage) {
		flowId=SEARCH_ADVANCE_AMOUNT;
		this.advanceAmountInputMessage=advanceAmountInputMessage;
		advanzErpServiceTemplate.execute(this);	
		return advanceAmountOutputMessage;
	}
	public AdvanceAmountOutputMessage save(AdvanceAmountInputMessage advanceAmountInputMessage){
		flowId=CREATE_ADVANCE_AMOUNT;
		this.advanceAmountInputMessage=advanceAmountInputMessage;
		advanzErpServiceTemplate.execute(this);	
		return advanceAmountOutputMessage;
		
	}
	
	@Override
	public AdvanceAmountOutputMessage findById(AdvanceAmountInputMessage advanceAmountInputMessage) {
		flowId=FIND_BY_ID_ADVANCE_AMOUNT;
		this.advanceAmountInputMessage=advanceAmountInputMessage;
		advanzErpServiceTemplate.execute(this);	
		return advanceAmountOutputMessage;
	}

	@Override
	public AdvanceAmountOutputMessage update(AdvanceAmountInputMessage advanceAmountInputMessage) {
		flowId=UPDATE_ADVANCE_AMOUNT;
		this.advanceAmountInputMessage=advanceAmountInputMessage;
		advanzErpServiceTemplate.execute(this);	
		return advanceAmountOutputMessage;
	}
	
	public AdvanceAmountOutputMessage delete(AdvanceAmountInputMessage advanceAmountInputMessage){
		flowId=DELETE_ADVANCE_AMOUNT;
		this.advanceAmountInputMessage=advanceAmountInputMessage;
		advanzErpServiceTemplate.execute(this);	
		return advanceAmountOutputMessage;
	}
	
	@Override
	public AdvanceAmountOutputMessage findByEmployeeId(
			AdvanceAmountInputMessage advanceAmountInputMessage) {
		flowId=FIND_BY_EMPLOYEE_ID_AMOUNT;
		this.advanceAmountInputMessage=advanceAmountInputMessage;
		advanzErpServiceTemplate.execute(this);	
		return advanceAmountOutputMessage;
	}
	
	@Override
	public AdvanceAmountOutputMessage getTransactionId() {
		flowId=FIND_TRANSACTION_ID;
		advanzErpServiceTemplate.execute(this);	
		return advanceAmountOutputMessage;
	}
	
	@Override
	public AdvanceAmountOutputMessage getByLastTransactionDate(AdvanceAmountInputMessage advanceAmountInputMessage) {
		flowId=FIND_BY_LAST_TRANSACTION_DATE;
		this.advanceAmountInputMessage=advanceAmountInputMessage;
		advanzErpServiceTemplate.execute(this);	
		return advanceAmountOutputMessage;
	}
	
	@Override
	public AdvanceAmountOutputMessage findbyEmployeeIdAndCurrntMonth(AdvanceAmountInputMessage advanceAmountInputMessage) {
		flowId=FIND_BY_EMPID_TRANSACTION_DATE;
		this.advanceAmountInputMessage=advanceAmountInputMessage;
		advanzErpServiceTemplate.execute(this);	
		return advanceAmountOutputMessage;
	}
	
	@Override
	public void performBusinessLogic() {
		AdvanceAmountEntity advanceAmountEntity=new AdvanceAmountEntity();
		advanceAmountOutputMessage=new AdvanceAmountOutputMessage();
		 AdvanceAmountDTO advanceAmountDTO=new AdvanceAmountDTO();
		if(advanceAmountInputMessage!=null)
		{
		 advanceAmountDTO=advanceAmountInputMessage.getAdvanceAmountDTO();
		 
		 if(advanceAmountDTO!=null)
		 {
			try {
				BeanUtils.copyProperties(advanceAmountDTO,advanceAmountEntity);
				advanceAmountEntity.setEmployeeEntity(new EmployeeEntity());
				if(advanceAmountDTO.getEmployeeDTO()!=null)
				{
					BeanUtils.copyProperties(advanceAmountDTO.getEmployeeDTO(), advanceAmountEntity.getEmployeeEntity());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 }
		}
		
		if(FIND_ALL_ADVANCE_AMOUNT.equals(flowId))
		{
			List<AdvanceAmountEntity> entityList=storageAdvnaceAmountDao.load();
			List<AdvanceAmountDTO> dtoList=convertEntityListToDTO(entityList);
			advanceAmountOutputMessage.setAdvanceAmountDTOList(dtoList);
		}
		if(SEARCH_ADVANCE_AMOUNT.equals(flowId))
		{	 
			Date toDate=advanceAmountDTO.getToDate();
			Date fromDate=advanceAmountDTO.getFromDate();
			String empName=advanceAmountDTO.getEmployeeName();
			String designation=advanceAmountDTO.getEmployeeDesignation();
			String trType=advanceAmountDTO.getTransactionType();
			
			List<AdvanceAmountEntity> entityList=storageAdvnaceAmountDao.search(toDate, fromDate, empName, designation, trType);
			List<AdvanceAmountDTO> dtoList=convertEntityListToDTO(entityList);
			advanceAmountOutputMessage.setAdvanceAmountDTOList(dtoList);
		}
		
		if(FIND_BY_ID_ADVANCE_AMOUNT.equals(flowId))
		{
			List<AdvanceAmountEntity> entityList=storageAdvnaceAmountDao.findById(advanceAmountEntity.getSno());
			List<AdvanceAmountDTO> dtoList=convertEntityListToDTO(entityList);
			advanceAmountOutputMessage.setAdvanceAmountDTOList(dtoList);
		}
		
		if(CREATE_ADVANCE_AMOUNT.equals(flowId))
		{	 
			storageAdvnaceAmountDao.create(advanceAmountEntity);
			advanceAmountOutputMessage.setAdvanceAmountDTOList(null);
		}
		
		if(UPDATE_ADVANCE_AMOUNT.equals(flowId))
		{	 
			storageAdvnaceAmountDao.update(advanceAmountEntity);
			advanceAmountOutputMessage.setAdvanceAmountDTOList(null);
		}
		if(DELETE_ADVANCE_AMOUNT.equals(flowId))
		{	 
			storageAdvnaceAmountDao.delete(advanceAmountEntity);
			advanceAmountOutputMessage.setAdvanceAmountDTOList(null);
		}
		if(FIND_BY_EMPLOYEE_ID_AMOUNT.equals(flowId))
		{	 
			List<AdvanceAmountEntity> entityList=storageAdvnaceAmountDao.findByEmployeeId(advanceAmountEntity.getEmployeeEntity().getEmployeeId());
			List<AdvanceAmountDTO> dtoList=convertEntityListToDTO(entityList);
			advanceAmountOutputMessage.setAdvanceAmountDTOList(dtoList);
		}
		if(FIND_TRANSACTION_ID.equals(flowId))
		{
			Integer transactionId=storageAdvnaceAmountDao.getTransactionNo();
			advanceAmountOutputMessage.setTransactionId(transactionId);
		}
		if(FIND_BY_LAST_TRANSACTION_DATE.equals(flowId))
		{
			Date lastDate=advanceAmountDTO.getToDate();
			Date firstDate=advanceAmountDTO.getFromDate();
			Integer employeeId=advanceAmountDTO.getEmployeeDTO().getEmployeeId();
			List<AdvanceAmountEntity> list=storageAdvnaceAmountDao.findLastDate(firstDate,lastDate, employeeId);
			List<AdvanceAmountDTO> dtoList=convertEntityListToDTO(list);
			advanceAmountOutputMessage.setAdvanceAmountDTOList(dtoList);
		}
		if(FIND_BY_EMPID_TRANSACTION_DATE.equals(flowId))
		{
			Date lstDate=advanceAmountDTO.getTransactionDate();
			Integer employeeId=advanceAmountDTO.getEmployeeDTO().getEmployeeId();
			Calendar cal = Calendar.getInstance();
		    cal.setTime(lstDate);
		    int year = cal.get(Calendar.YEAR);
		    int month = cal.get(Calendar.MONTH);
		    month=month+1;
			Integer transactionId=storageAdvnaceAmountDao.findbyEmpIdAndTrnsMonth(month, year,employeeId);
			advanceAmountOutputMessage.setTransactionId(transactionId);
		}
		
	}

	@Override
	public void formatOutput() {
		// TODO Auto-generated method stub
	}
	@Override
	public boolean validateInput() {
		if(CREATE_ADVANCE_AMOUNT.equals(flowId))
		{
			return true;
		}
		else if(UPDATE_ADVANCE_AMOUNT.equals(flowId))
		{
			return true;
		} 
		else if(SEARCH_ADVANCE_AMOUNT.equals(flowId))
		{
			return true;
		} 
		else if(DELETE_ADVANCE_AMOUNT.equals(flowId))
		{
			return true;
		} 
		else if(FIND_ALL_ADVANCE_AMOUNT.equals(flowId))
		{
			return true;
		}
		else if(FIND_BY_ID_ADVANCE_AMOUNT.equals(flowId))
		{
			return true;
		}
		else if(FIND_BY_EMPLOYEE_ID_AMOUNT.equals(flowId))
		{
			return true;
		}
		else if(FIND_TRANSACTION_ID.equals(flowId))
		{
			return true;
		}
		else if(FIND_BY_LAST_TRANSACTION_DATE.equals(flowId))
		{
			return true;
		}
		else if(FIND_BY_EMPID_TRANSACTION_DATE.equals(flowId))
		{
			return true;
		}
		return false;
	}


	public List<AdvanceAmountDTO> convertEntityListToDTO(List<AdvanceAmountEntity> amountEntityList)
	{
		List<AdvanceAmountDTO> advanceAmountDTOList=new ArrayList<AdvanceAmountDTO>();
		AdvanceAmountDTO amountDTO=null;
		for(AdvanceAmountEntity entity:amountEntityList)
		{
			amountDTO=new AdvanceAmountDTO();
			amountDTO.setEmployeeDTO(new EmployeeDTO());
			try {
			  BeanUtils.copyProperties(entity, amountDTO);
			  if(entity.getEmployeeEntity()!=null)
				{
				  BeanUtils.copyProperties(entity.getEmployeeEntity(),amountDTO.getEmployeeDTO());		
				  amountDTO.getEmployeeDTO().setMastersDTO(new MastersDTO());
				  if(entity.getEmployeeEntity().getMastersEntity()!=null)
				  {
					  BeanUtils.copyProperties(entity.getEmployeeEntity().getMastersEntity(), amountDTO.getEmployeeDTO().getMastersDTO());  
				  }
				  //BeanUtils.getInstance().getConvertUtils().register
				}}
			catch (Exception e) {
				e.printStackTrace();
			}
			advanceAmountDTOList.add(amountDTO);
		}
		return advanceAmountDTOList;
	}
	
}
