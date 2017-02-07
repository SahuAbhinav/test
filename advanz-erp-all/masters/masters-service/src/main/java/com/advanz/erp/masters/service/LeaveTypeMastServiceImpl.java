package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.LeaveTypeMastEntity;
import com.advanz.erp.masters.entity.jpa.RegionEntity;
import com.advanz.erp.masters.entity.jpa.SalaryHeadEntity;
import com.advanz.erp.masters.model.LeaveTypeMastDTO;
import com.advanz.erp.masters.model.RegionDTO;
import com.advanz.erp.masters.model.SalaryHeadDTO;
import com.advanz.erp.masters.model.criteria.LeaveTypeMastSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.AreaOutputMessage;
import com.advanz.erp.masters.model.msg.LeaveTypeMastInputMessage;
import com.advanz.erp.masters.model.msg.LeaveTypeMastOutputMessage;
import com.advanz.erp.masters.model.msg.RegionInputMessage;
import com.advanz.erp.masters.model.msg.RegionOutputMessage;
import com.advanz.erp.masters.service.business.ILeaveTypeMastService;
import com.advanz.erp.masters.storage.IStorageLeaveTypeMastDAO;

public class LeaveTypeMastServiceImpl implements ILeaveTypeMastService {

	public static final String CREATE_LEAVE_TYPE = "CreateLeaveTypeMast";
	public static final String UPDATE_LEAVE_TYPE = "UpdateLeaveTypeMast";
	public static final String DELETE_LEAVE_TYPE = "DeleteLeaveTypeMast";
	public static final String FIND_LEAVE_TYPE_BY_ID = "FindLeaveTypeMastById";
	public static final String FIND_ALL_LEAVE_TYPE = "FindAllLeaveTypeMasts";
	public static final String SEARCH_LEAVE_TYPE = "SearchLeaveTypeMasts";
	public static final String PRE_REMOVE_CHECK="PreRemoveCheck";
	public static final String GET_Leave_Type ="GetLeaveType";
	public static final String FIND_LEAVE_BY_NAME ="FindLeaveByName";
	

	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageLeaveTypeMastDAO storageLeaveTypeMastDAO;

	public LeaveTypeMastInputMessage leaveTypeMastInputMessage;

	public LeaveTypeMastOutputMessage leaveTypeMastOutputMessage;

	@Override
	public LeaveTypeMastOutputMessage createLeaveTypeMast(LeaveTypeMastInputMessage leaveTypeMastInputMessage) {

		flowId = CREATE_LEAVE_TYPE;
		// assign the message to the class level variable.
		this.leaveTypeMastInputMessage = leaveTypeMastInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return leaveTypeMastOutputMessage;
	}

	@Override
	public LeaveTypeMastOutputMessage updateLeaveTypeMast(LeaveTypeMastInputMessage leaveTypeMastInputMessage) {

		flowId = UPDATE_LEAVE_TYPE;
		// assign the message to the class level variable.
		this.leaveTypeMastInputMessage = leaveTypeMastInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return leaveTypeMastOutputMessage;
	}

	@Override
	public LeaveTypeMastOutputMessage deleteLeaveTypeMast(LeaveTypeMastInputMessage leaveTypeMastInputMessage) {
		flowId = DELETE_LEAVE_TYPE;
		// assign the message to the class level variable.
		this.leaveTypeMastInputMessage = leaveTypeMastInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return leaveTypeMastOutputMessage;

	}

	@Override
	public LeaveTypeMastOutputMessage findLeaveTypeMastById(LeaveTypeMastInputMessage leaveTypeMastInputMessage) {
		flowId = FIND_LEAVE_TYPE_BY_ID;
		// assign the message to the class level variable.
		this.leaveTypeMastInputMessage = leaveTypeMastInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return leaveTypeMastOutputMessage;

	}

	@Override
	public LeaveTypeMastOutputMessage findAllLeaveTypeMasts() {
		flowId = FIND_ALL_LEAVE_TYPE;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return leaveTypeMastOutputMessage;
	}
	
	@Override
	public LeaveTypeMastOutputMessage search(LeaveTypeMastInputMessage leaveTypeMastInputMessage) {
		flowId = SEARCH_LEAVE_TYPE;
		this.leaveTypeMastInputMessage=leaveTypeMastInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return leaveTypeMastOutputMessage;

	}
	@Override
	public LeaveTypeMastOutputMessage checkBeforeRemove(LeaveTypeMastInputMessage leaveTypeMastInputMessage) {
		flowId = PRE_REMOVE_CHECK;
		this.leaveTypeMastInputMessage=leaveTypeMastInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return leaveTypeMastOutputMessage;

	}
	
	@Override
	public LeaveTypeMastOutputMessage getLeaveType() {
		flowId =GET_Leave_Type;
		advanzErpServiceTemplate.execute(this);
		return leaveTypeMastOutputMessage;
	}
	@Override
	public LeaveTypeMastOutputMessage findByLeaveName(
			LeaveTypeMastInputMessage leaveTypeMastInputMessage) {
		flowId = FIND_LEAVE_BY_NAME;
		this.leaveTypeMastInputMessage=leaveTypeMastInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return leaveTypeMastOutputMessage;
	}
	
	@Override
	public boolean validateInput() {

		if (CREATE_LEAVE_TYPE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_LEAVE_TYPE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_LEAVE_TYPE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_LEAVE_TYPE_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_LEAVE_TYPE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_LEAVE_TYPE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (PRE_REMOVE_CHECK.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (GET_Leave_Type.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_LEAVE_BY_NAME.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		
		return false;
	}
	
	@Override
	public void performBusinessLogic() {

		LeaveTypeMastEntity leaveTypeMast = new LeaveTypeMastEntity();
		LeaveTypeMastDTO leaveTypeMastDTO =null;
		if (leaveTypeMastInputMessage != null) {
			 leaveTypeMastDTO = leaveTypeMastInputMessage.getLeaveTypeMastDTO();
			if (leaveTypeMastDTO != null) {
				BeanUtils.copyProperties(leaveTypeMastDTO, leaveTypeMast);
				
				List<SalaryHeadDTO> encashments = leaveTypeMastInputMessage.getLeaveTypeMastDTO().getEncashments();
				if (encashments != null) {
					List<SalaryHeadEntity> encashmentsEntityes= new ArrayList<SalaryHeadEntity>();
					for(SalaryHeadDTO dt:encashments){
						if(dt.getSalaryHeadId() !=null && !dt.getSalaryHeadId().equals(0)){
						SalaryHeadEntity e=new SalaryHeadEntity();
						//System.out.print("------------dt salary head idsss 1 :-"+dt.getSalaryHeadId());
						
						e.setSalaryHeadId(dt.getSalaryHeadId());
						encashmentsEntityes.add(e);
						}
					}
					leaveTypeMast.setEncashmentList(encashmentsEntityes);
					}
				else{
				}
			}
		}else{
		}
		if (CREATE_LEAVE_TYPE.equals(flowId)) {
			// check duplicate salaryHead name
			List<LeaveTypeMastEntity> list1 = storageLeaveTypeMastDAO.findByNameAndCode(leaveTypeMast.getLeaveName(), null);
			// check duplicate salaryHeadCode
			List<LeaveTypeMastEntity> list2 = storageLeaveTypeMastDAO.findByNameAndCode(
					null, leaveTypeMast.getLeaveCode());
			leaveTypeMastOutputMessage = new LeaveTypeMastOutputMessage();
			boolean errors = false;
			if (list1 != null && list1.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("1", "Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				leaveTypeMastOutputMessage.setErrorListDTO(errorListDTO);
				errors = true;
			}
			if (list2 != null && list2.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("2", "Sorry, Record already exist, Duplicate entries are not allowed.");
				if (!errors) {
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					leaveTypeMastOutputMessage.setErrorListDTO(errorListDTO);
				} else {
					leaveTypeMastOutputMessage.getErrorListDTO().addError(errorDTO);
				}
				errors = true;
			}
			if (!errors) {
				leaveTypeMastOutputMessage.setErrorListDTO(null);
				storageLeaveTypeMastDAO.create(leaveTypeMast);
			}
			
		} else if (UPDATE_LEAVE_TYPE.equals(flowId)) {
			// check duplicate salaryHead name
			List<LeaveTypeMastEntity> list1 = storageLeaveTypeMastDAO.findByNameAndCode(leaveTypeMast.getLeaveName(), null);
			leaveTypeMastOutputMessage = new LeaveTypeMastOutputMessage();
			boolean errors = false;
			if (list1 != null && list1.size() > 0 && !list1.get(0).getLeaveId().equals(leaveTypeMast.getLeaveId())) {
				ErrorDTO errorDTO = new ErrorDTO("1", "Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				leaveTypeMastOutputMessage.setErrorListDTO(errorListDTO);
				errors = true;
			}
			if (!errors) {
				leaveTypeMastOutputMessage.setErrorListDTO(null);
				storageLeaveTypeMastDAO.update(leaveTypeMast);
			}
		} else if (DELETE_LEAVE_TYPE.equals(flowId)) {
			storageLeaveTypeMastDAO.delete(leaveTypeMast);
		} else if (FIND_LEAVE_TYPE_BY_ID.equals(flowId)) {
		List<LeaveTypeMastEntity>list=	storageLeaveTypeMastDAO.findById(leaveTypeMast.getLeaveId());
		popUpList(list);
		} else if (FIND_ALL_LEAVE_TYPE.equals(flowId)) {
			List<LeaveTypeMastEntity> list = storageLeaveTypeMastDAO.load();
			popUpList(list);			
		}else if (SEARCH_LEAVE_TYPE.equals(flowId)) {
			LeaveTypeMastSearchCriteriaDTO searchCriteria=leaveTypeMastInputMessage.getSearchCriteria();			
			List<LeaveTypeMastEntity> list = storageLeaveTypeMastDAO.search(searchCriteria.getLeaveName(),searchCriteria.getLeaveCode(),searchCriteria.getLeaveType());
			popUpList(list);
		}
			
			else if (GET_Leave_Type.equals(flowId)) {			
				leaveTypeMastOutputMessage = new LeaveTypeMastOutputMessage();
				List<String> list = storageLeaveTypeMastDAO.getLeaveType();
				leaveTypeMastOutputMessage.setList(list);
			}else if (FIND_LEAVE_BY_NAME.equals(flowId)) {	
				leaveTypeMastDTO = leaveTypeMastInputMessage.getLeaveTypeMastDTO();
				List<LeaveTypeMastEntity> list =  storageLeaveTypeMastDAO.findByLeaveName(leaveTypeMastDTO.getLeaveName());
				popUpList(list);
			}
		
		if(PRE_REMOVE_CHECK.equals(flowId)){
			//logger.info(flowId);
			if(storageLeaveTypeMastDAO.isInUsed(leaveTypeMastInputMessage.getLeaveTypeMastDTO().getLeaveId())){
				//logger.info("TRUE");		
				leaveTypeMastOutputMessage=new LeaveTypeMastOutputMessage();
				ErrorDTO errorDTO = new ErrorDTO("1", "Leave can not be Removed, Used in Employee");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				leaveTypeMastOutputMessage.setErrorListDTO(errorListDTO);
			}else{
				leaveTypeMastOutputMessage=null;
			}
		}
	}
	void popUpList(List<LeaveTypeMastEntity> list) {
		leaveTypeMastOutputMessage = new LeaveTypeMastOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<LeaveTypeMastDTO> resultList = new ArrayList<LeaveTypeMastDTO>();
			LeaveTypeMastDTO leaveTypeMastDTO;
			for (LeaveTypeMastEntity entity : list) {
				leaveTypeMastDTO = new LeaveTypeMastDTO();
				List<SalaryHeadDTO> obj= new ArrayList<SalaryHeadDTO>();
				List<LeaveTypeMastDTO> list2= new ArrayList<LeaveTypeMastDTO>();
				for (SalaryHeadEntity entity1 : entity.getEncashmentList()) {
				//	System.out.println("=====entity.getEncashmentList().size() nandu ****"+entity.getEncashmentList().size());
				//	 System.out.println("=====entity.getEncashmentList().size() kamal ****"+entity.getEncashmentList());
					 SalaryHeadDTO obj1=new SalaryHeadDTO();
						if (entity1 != null) {					
							BeanUtils.copyProperties(entity1,obj1);
						}
						obj.add(obj1);
					}
				leaveTypeMastDTO.setEncashments(obj);
				BeanUtils.copyProperties(entity, leaveTypeMastDTO);
					
				resultList.add(leaveTypeMastDTO);

			}
			leaveTypeMastOutputMessage.setLeaveTypeMastDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (CREATE_LEAVE_TYPE.equals(flowId)) {

		} else if (UPDATE_LEAVE_TYPE.equals(flowId)) {

		} else if (DELETE_LEAVE_TYPE.equals(flowId)) {

		} else if (FIND_LEAVE_TYPE_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_LEAVE_TYPE.equals(flowId)) {

		} else if (SEARCH_LEAVE_TYPE.equals(flowId)) {

		}
	}

	
}
