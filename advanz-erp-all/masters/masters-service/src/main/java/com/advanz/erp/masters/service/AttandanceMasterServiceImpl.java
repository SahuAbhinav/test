package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.AttandanceMasterEntity;
import com.advanz.erp.masters.model.AttandanceMasterDTO;
import com.advanz.erp.masters.model.msg.AttandanceMasterInputMessage;
import com.advanz.erp.masters.model.msg.AttandanceMasterOutputMessage;
import com.advanz.erp.masters.service.business.IAttandanceMasterService;
import com.advanz.erp.masters.storage.IStorageAttandanceMasterDAO;

public class AttandanceMasterServiceImpl implements IAttandanceMasterService {
	private static final Logger logger = LoggerFactory
	.getLogger(AttandanceMasterServiceImpl.class);
	
	public static final String CREATE_ATTANDANCE = "CreateAttandanceMaster";
	public static final String UPDATE_ATTANDANCE = "UpdateAttandanceMaster";
	public static final String DELETE_ATTANDANCE = "DeleteAttandanceMaster";
	public static final String FIND_ATTANDANCE_BY_ID = "FindAttandanceMasterById";
	public static final String FIND_ALL_ATTANDANCE = "FindAllAttandanceMasters";
	public static final String SEARCH_ATTANDANCE = "SearchAttandanceMasters";
	public static final String DELETE_ATTANDANCE_BY_EMPID = "DeleteAttandanceByEmpId";
	public static final String FIND_ALL_ATTANDANCE_BY_EMPID = "FindAllAttandanceMastersByEmp";
	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageAttandanceMasterDAO storageAttandanceMasterDAO;

	public AttandanceMasterInputMessage attandanceMasterInputMessage;

	public AttandanceMasterOutputMessage attandanceMasterOutputMessage;

	@Override
	public AttandanceMasterOutputMessage createAttandanceMaster(AttandanceMasterInputMessage attandanceMasterInputMessage) {

		flowId = CREATE_ATTANDANCE;
		// assign the message to the class level variable.
		this.attandanceMasterInputMessage = attandanceMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return attandanceMasterOutputMessage;
	}

	@Override
	public AttandanceMasterOutputMessage updateAttandanceMaster(AttandanceMasterInputMessage attandanceMasterInputMessage) {

		flowId = UPDATE_ATTANDANCE;
		// assign the message to the class level variable.
		this.attandanceMasterInputMessage = attandanceMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return attandanceMasterOutputMessage;
	}

	@Override
	public AttandanceMasterOutputMessage deleteAttandanceMaster(AttandanceMasterInputMessage attandanceMasterInputMessage) {
		flowId = DELETE_ATTANDANCE;
		// assign the message to the class level variable.
		this.attandanceMasterInputMessage = attandanceMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return attandanceMasterOutputMessage;

	}


	@Override
	public AttandanceMasterOutputMessage findAllAttandanceMasters() {
		flowId = FIND_ALL_ATTANDANCE;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return attandanceMasterOutputMessage;
	}

	@Override
	public AttandanceMasterOutputMessage search(AttandanceMasterInputMessage attandanceMasterInputMessage) {
		flowId = SEARCH_ATTANDANCE;
		this.attandanceMasterInputMessage=attandanceMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return attandanceMasterOutputMessage;

	}
	@Override
	public AttandanceMasterOutputMessage deleteAttandanceBYEmp(AttandanceMasterInputMessage attandanceMasterInputMessage)
	{
		flowId=DELETE_ATTANDANCE_BY_EMPID;
		this.attandanceMasterInputMessage= attandanceMasterInputMessage;
		advanzErpServiceTemplate.execute(this);
		return attandanceMasterOutputMessage;
	}
	@Override
	public AttandanceMasterOutputMessage findAttandance(AttandanceMasterInputMessage attandanceMasterInputMessage) {
		flowId = FIND_ALL_ATTANDANCE_BY_EMPID;
		// call the template method
		this.attandanceMasterInputMessage=attandanceMasterInputMessage;
	    advanzErpServiceTemplate.execute(this);	
		return attandanceMasterOutputMessage;
		}
	
	@Override
	public boolean validateInput() {

		if (CREATE_ATTANDANCE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_ATTANDANCE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_ATTANDANCE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ATTANDANCE_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_ATTANDANCE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_ATTANDANCE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (DELETE_ATTANDANCE_BY_EMPID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_ALL_ATTANDANCE_BY_EMPID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		
		return false;
	}

	@Override
	public void performBusinessLogic() {

		AttandanceMasterEntity attandanceMasterEntity = new AttandanceMasterEntity();
		AttandanceMasterDTO attandanceMasterDTO =new AttandanceMasterDTO();
		if (attandanceMasterInputMessage != null) {
			attandanceMasterDTO = attandanceMasterInputMessage.getAttandanceMasterDTO();
			if (attandanceMasterDTO != null) {
				BeanUtils.copyProperties(attandanceMasterDTO, attandanceMasterEntity);
		}
		}

		if (CREATE_ATTANDANCE.equals(flowId)) {
			
				storageAttandanceMasterDAO.update(attandanceMasterEntity);	
			} else if (DELETE_ATTANDANCE.equals(flowId)) {
			storageAttandanceMasterDAO.delete(attandanceMasterEntity);
		} else if (FIND_ATTANDANCE_BY_ID.equals(flowId)) {
		List<AttandanceMasterEntity>list=storageAttandanceMasterDAO.findById(attandanceMasterEntity.getSno());
		
		popUpList(list);			
		}
		else if (FIND_ALL_ATTANDANCE_BY_EMPID.equals(flowId)) 
		{	
			attandanceMasterOutputMessage=new AttandanceMasterOutputMessage();
			
			List<AttandanceMasterEntity> list=storageAttandanceMasterDAO.getAttandanceByMonthYearEmpId(attandanceMasterDTO.getMonth(),attandanceMasterDTO.getYear(),attandanceMasterDTO.getEmployeeId());
			if(list!=null)
		    {
			List<AttandanceMasterDTO> resultList=new ArrayList<AttandanceMasterDTO>();
			AttandanceMasterDTO attandanceMasterDTO1;
			for(AttandanceMasterEntity entity:list)
			{
				attandanceMasterDTO1 = new AttandanceMasterDTO();
				BeanUtils.copyProperties(entity, attandanceMasterDTO1);
				resultList.add(attandanceMasterDTO1);
			}
		      attandanceMasterOutputMessage.setAttandanceMasterDTOList(resultList);
		}}
		else if (FIND_ALL_ATTANDANCE.equals(flowId)) {
			List<AttandanceMasterEntity> list = storageAttandanceMasterDAO.load();
			popUpList(list);			
		}
		else if (SEARCH_ATTANDANCE.equals(flowId)) {
			attandanceMasterOutputMessage=new AttandanceMasterOutputMessage();
			 attandanceMasterDTO = attandanceMasterInputMessage.getAttandanceMasterDTO();
			String orderBy=null;
			if(attandanceMasterDTO.getOrderBy()!=null){
				orderBy=attandanceMasterDTO.getOrderBy();
			}
			List list = storageAttandanceMasterDAO.search(attandanceMasterDTO.getDate(),orderBy);
			List<AttandanceMasterDTO> resultList = new ArrayList<AttandanceMasterDTO>();
		for(int i=0;i<list.size();i++){
		AttandanceMasterDTO masterDTO =new AttandanceMasterDTO();
		Object[] obj=(Object[])list.get(i);
		String empCode=(String)obj[0];
		int empId=Integer.parseInt(obj[1].toString());
		String employeeName=(String)obj[2];
		String attandanceFlag=(String)obj[3];
		String dayStatus=(String)obj[4];
		String date=(String)obj[5].toString();
		String dayOfDate=(String)obj[6];
		masterDTO.setDayStatus(dayStatus);
		masterDTO.setEmployeeName(employeeName);
		masterDTO.setEmployeeCode(empCode);
		masterDTO.setEmployeeId(empId);
		masterDTO.setAttandanceFlag(attandanceFlag);
		masterDTO.setDayOfDate(dayOfDate);
		resultList.add(masterDTO);		
			}
			
			
			
		attandanceMasterOutputMessage.setAttandanceMasterDTOList(resultList);
			//popUpList(list);			
		}
		
	}
	void popUpList(List<AttandanceMasterEntity> list) {
		logger.info("BFM Entity List  :"+list);
		attandanceMasterOutputMessage = new AttandanceMasterOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<AttandanceMasterDTO> resultList = new ArrayList<AttandanceMasterDTO>();
			AttandanceMasterDTO attandanceMasterDTO;
			for (AttandanceMasterEntity entity : list) {
				attandanceMasterDTO = new AttandanceMasterDTO();
				// Spring				
				BeanUtils.copyProperties(entity, attandanceMasterDTO);
				resultList.add(attandanceMasterDTO);
			}
			attandanceMasterOutputMessage.setAttandanceMasterDTOList(resultList);
		}

	}

	
	private void copyObject(Object source,Object target){
		
		try {
			BeanUtils.copyProperties(source, target);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void formatOutput() {

		if (CREATE_ATTANDANCE.equals(flowId)) {

		} else if (UPDATE_ATTANDANCE.equals(flowId)) {

		} else if (DELETE_ATTANDANCE.equals(flowId)) {

		} else if (FIND_ATTANDANCE_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_ATTANDANCE.equals(flowId)) {

		} else if (SEARCH_ATTANDANCE.equals(flowId)) {

		}
	}

	@Override
	public AttandanceMasterOutputMessage findAttandanceMasterById(
			AttandanceMasterInputMessage attandanceMasterInputMessage) {
		
		
		
		return null;
	}

	@Override
	public Double coutLeaves(Integer employeeId, String leaveType,
			Date fromDate, Date toDate) {
		Double d=storageAttandanceMasterDAO.coutLeaves(employeeId, leaveType, fromDate, toDate);
		return d;
	}

	@Override
	public Double countByHalfDay(Integer employeeId, String leaveType, Date fromDate,
			Date toDate) {
		Double d=storageAttandanceMasterDAO.countByHalfDay(employeeId, leaveType, fromDate, toDate);
		return d;
		
	}
	@Override
	public Double countByFullDay(Integer employeeId, String leaveType, Date fromDate,
			Date toDate) {
		Double d=storageAttandanceMasterDAO.countByFullDay(employeeId, leaveType, fromDate, toDate);
		return d;
		
	}
}
