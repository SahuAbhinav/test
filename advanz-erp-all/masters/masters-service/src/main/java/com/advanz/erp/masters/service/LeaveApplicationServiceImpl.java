package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.EmployeeEntity;
import com.advanz.erp.masters.entity.jpa.LeaveApplicationEntity;
import com.advanz.erp.masters.entity.jpa.LeaveTypeMastEntity;
import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.LeaveApplicationDTO;
import com.advanz.erp.masters.model.LeaveTypeMastDTO;
import com.advanz.erp.masters.model.msg.LeaveApplicationInputMessage;
import com.advanz.erp.masters.model.msg.LeaveApplicationOutputMessage;
import com.advanz.erp.masters.service.business.ILeaveApplicationService;
import com.advanz.erp.masters.storage.IStorageLeaveApplicationDAO;

public class LeaveApplicationServiceImpl implements ILeaveApplicationService {
	private static final Logger logger = LoggerFactory
	.getLogger(LeaveApplicationServiceImpl.class);
	
	public static final String CREATE_ATTANDANCE = "CreateAttandanceMaster";
	public static final String UPDATE_ATTANDANCE = "UpdateAttandanceMaster";
	public static final String DELETE_ATTANDANCE = "DeleteAttandanceMaster";
	public static final String FIND_ATTANDANCE_BY_ID = "FindAttandanceMasterById";
	public static final String FIND_ALL_ATTANDANCE = "FindAllAttandanceMasters";
	public static final String SEARCH_ATTANDANCE = "SearchAttandanceMasters";
	public static final String FIND_LEAVE_BY_EMPLOYEE_ID_AND_DATE = "FindLeaveByEmployeeAndDate";
	public String flowId = "";

	
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring
	@Autowired
	public IStorageLeaveApplicationDAO storageLeaveApplicationDAO;
	
	public LeaveApplicationInputMessage leaveApplicationInputMessage;

	public LeaveApplicationOutputMessage leaveApplicationOutputMessage;

	@Override
	public LeaveApplicationOutputMessage createLeaveApplication(LeaveApplicationInputMessage leaveApplicationInputMessage) {

		flowId = CREATE_ATTANDANCE;
		// assign the message to the class level variable.
		this.leaveApplicationInputMessage = leaveApplicationInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return leaveApplicationOutputMessage;
	}

	@Override
	public LeaveApplicationOutputMessage updateLeaveApplication(LeaveApplicationInputMessage leaveApplicationInputMessage) {

		flowId = UPDATE_ATTANDANCE;
		// assign the message to the class level variable.
		this.leaveApplicationInputMessage = leaveApplicationInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return leaveApplicationOutputMessage;
	}

	@Override
	public LeaveApplicationOutputMessage deleteLeaveApplication(LeaveApplicationInputMessage leaveApplicationInputMessage) {
		flowId = DELETE_ATTANDANCE;
		// assign the message to the class level variable.
		// assign the message to the class level variable.
		this.leaveApplicationInputMessage = leaveApplicationInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return leaveApplicationOutputMessage;

	}


	@Override
	public LeaveApplicationOutputMessage findAllLeaveApplication() {
		flowId = FIND_ALL_ATTANDANCE;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return leaveApplicationOutputMessage;
	}

	@Override
	public LeaveApplicationOutputMessage search(LeaveApplicationInputMessage leaveApplicationInputMessage) {
		flowId = SEARCH_ATTANDANCE;
		this.leaveApplicationInputMessage = leaveApplicationInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return leaveApplicationOutputMessage;

	}
	@Override
	public LeaveApplicationOutputMessage findLeaveApplicationById(
			LeaveApplicationInputMessage leaveApplicationInputMessage) {
		flowId =FIND_ATTANDANCE_BY_ID;
		this.leaveApplicationInputMessage = leaveApplicationInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return leaveApplicationOutputMessage;
	}
	@Override
	public LeaveApplicationOutputMessage findLeaveByEmployeeIdAndDate(
			LeaveApplicationInputMessage leaveApplicationInputMessage) {
		// TODO Auto-generated method stub
		flowId =FIND_LEAVE_BY_EMPLOYEE_ID_AND_DATE;
		this.leaveApplicationInputMessage = leaveApplicationInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return leaveApplicationOutputMessage;
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
		} else if (FIND_LEAVE_BY_EMPLOYEE_ID_AND_DATE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		return false;
	}

	@Override
	public void performBusinessLogic() {

		LeaveApplicationEntity leaveApplicationEntity = new LeaveApplicationEntity();
		LeaveApplicationDTO leaveApplicationDTO =null;
		if (leaveApplicationInputMessage != null) {
			 leaveApplicationDTO = leaveApplicationInputMessage.getLeaveApplicationDTO();
			if (leaveApplicationDTO != null) {
				copyObject(leaveApplicationDTO, leaveApplicationEntity);
			EmployeeDTO employeeDTO=	leaveApplicationDTO.getEmployeeDTO();
			if(employeeDTO!=null){
			EmployeeEntity employeeEntity = new EmployeeEntity();	
			copyObject(employeeDTO, employeeEntity);
			leaveApplicationEntity.setEmployeeEntity(employeeEntity);
			}
			LeaveTypeMastDTO leaveTypeMastDTO= leaveApplicationDTO.getLeaveTypeMastDTO();
		if(leaveTypeMastDTO!=null){
			LeaveTypeMastEntity leaveTypeMastEntity  =new LeaveTypeMastEntity();
			copyObject(leaveTypeMastDTO, leaveTypeMastEntity);
			leaveApplicationEntity.setLeaveTypeMastEntity(leaveTypeMastEntity);
		}
			
			}
		}

		if (CREATE_ATTANDANCE.equals(flowId)) {
			
			storageLeaveApplicationDAO.update(leaveApplicationEntity);	
			} else if (DELETE_ATTANDANCE.equals(flowId)) {
				storageLeaveApplicationDAO.delete(leaveApplicationEntity);
		} else if (FIND_ATTANDANCE_BY_ID.equals(flowId)) {
		List<LeaveApplicationEntity>list=storageLeaveApplicationDAO.findById(leaveApplicationEntity.getSno());
		
		popUpList(list);			
		} else if (FIND_ALL_ATTANDANCE.equals(flowId)) {
			List<LeaveApplicationEntity> list = storageLeaveApplicationDAO.load();
			popUpList(list);			
		}
		else if (FIND_LEAVE_BY_EMPLOYEE_ID_AND_DATE.equals(flowId)) {
			List<LeaveApplicationEntity> list = storageLeaveApplicationDAO.findLeaveByEmployeeIdAndDate(leaveApplicationDTO.getEmployeeDTO().getEmployeeId(), leaveApplicationDTO.getDate());
			popUpList(list);			
		}
		else if (SEARCH_ATTANDANCE.equals(flowId)) {
			/*leaveApplicationOutputMessage=new LeaveApplicationOutputMessage();
			LeaveApplicationDTO leaveApplicationMasterDTO = leaveApplicationInputMessage.getAttandanceMasterDTO();
			String orderBy=null;
			List list = storageAttandanceMasterDAO.search(attandanceMasterDTO.getDate(),orderBy);
			List<AttandanceMasterDTO> resultList = new ArrayList<AttandanceMasterDTO>();
		attandanceMasterOutputMessage.setAttandanceMasterDTOList(resultList);
			//popUpList(list);			
*/		}
	}
	void popUpList(List<LeaveApplicationEntity> list) {
		logger.info("BFM Entity List  size is ;;;:"+list.size());
		leaveApplicationOutputMessage = new LeaveApplicationOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<LeaveApplicationDTO> resultList = new ArrayList<LeaveApplicationDTO>();
			LeaveApplicationDTO leaveApplicationDTO;
			for (LeaveApplicationEntity entity : list) {
				leaveApplicationDTO = new LeaveApplicationDTO();
				// Spring				
				copyObject(entity, leaveApplicationDTO);
				EmployeeEntity employeeEntity= entity.getEmployeeEntity();
				if(employeeEntity!=null){
					EmployeeDTO employeeDTO = new EmployeeDTO();
					copyObject(employeeEntity, employeeDTO);
					leaveApplicationDTO.setEmployeeDTO(employeeDTO);
				}
				LeaveTypeMastEntity leaveTypeMastEntity= entity.getLeaveTypeMastEntity();
				
				if(leaveTypeMastEntity!=null){
					LeaveTypeMastDTO leaveTypeMastDTO = new LeaveTypeMastDTO();
					copyObject(leaveTypeMastEntity, leaveTypeMastDTO);
					leaveApplicationDTO.setLeaveTypeMastDTO(leaveTypeMastDTO);
				}
				resultList.add(leaveApplicationDTO);
			}
			leaveApplicationOutputMessage.setLeaveApplicationDTOList(resultList);
		}}
	private void copyObject(Object source, Object target) {
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

	

	
	

	
}
