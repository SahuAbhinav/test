package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.BillEntity;
import com.advanz.erp.masters.entity.jpa.CityEntity;
import com.advanz.erp.masters.entity.jpa.EmployeeEntity;
import com.advanz.erp.masters.entity.jpa.EmployeeLeaveKey;
import com.advanz.erp.masters.entity.jpa.EmployeeLeavesEntity;
import com.advanz.erp.masters.entity.jpa.EmployeeSalaryDepKey;
import com.advanz.erp.masters.entity.jpa.EmployeeSalaryDetEntity;
import com.advanz.erp.masters.entity.jpa.GrnMasterEntity;
import com.advanz.erp.masters.model.CityDTO;
import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.EmployeeLeavesDTO;
import com.advanz.erp.masters.model.EmployeeSalaryDetDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.msg.EmployeeInputMessage;
import com.advanz.erp.masters.model.msg.EmployeeOutputMessage;
import com.advanz.erp.masters.model.msg.PurchaseOrderMasterOutputMessage;
import com.advanz.erp.masters.service.business.IEmployeeService;
import com.advanz.erp.masters.storage.IStorageBillDAO;
import com.advanz.erp.masters.storage.IStorageBillDetailDAO;
import com.advanz.erp.masters.storage.IStorageEmployeeDAO;

public class EmployeeServiceImpl implements IEmployeeService {

	public static final String CREATE_EMPLOYEE_GROUP = "Employee";
	public static final String UPDATE_EMPLOYEE_GROUP = "UpdateEmployee";
	public static final String ADD_EMPLOYEE_GROUP = "AddEmployee";
	public static final String DELETE_EMPLOYEE_GROUP = "DeleteEmployee";
	public static final String FIND_EMPLOYEE_GROUP_BY_ID = "FindEmployeeById";
	public static final String FIND_ALL_EMPLOYEE_GROUPES = "FindAllEmployeees";
	public static final String FIND_EMPLOYEE_GROUPES = "FindEmployeees";
	public static final String PRE_LOAD = "PreLoad";
	public static final String FIND_ALL_ACTIVATED_EMPLOYEE = "FindAllActivatedEmployeees";
	public static final String FIND_ALL_ACTIVATED_EMPLOYEE_BY_DEPT_ID = "FindAllActivatedEmployeeesByDeptId";
	public static final String FIND_ALL_ACTIVATED_EMPLOYEE_BY_JOIN_DATE = "FindAllActivatedEmployeeesByJoinDate";
	public String flowId = "";

	private static final Logger logger = LoggerFactory
			.getLogger(EmployeeServiceImpl.class);

	public void EmployeeServiceImpl() {
	}

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageEmployeeDAO storageEmployeeDAO;

	@Autowired
	public IStorageBillDAO storageBillDAO;

	public EmployeeInputMessage employeeInputMessage;

	public EmployeeOutputMessage employeeOutputMessage;

	@Override
	public EmployeeOutputMessage createEmployee(
			EmployeeInputMessage employeeInputMessage) {

		flowId = ADD_EMPLOYEE_GROUP;
		// assign the message to the class level variable.
		this.employeeInputMessage = employeeInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return employeeOutputMessage;
	}

	@Override
	public EmployeeOutputMessage updateEmployee(
			EmployeeInputMessage employeeInputMessage) {

		flowId = UPDATE_EMPLOYEE_GROUP;
		this.employeeInputMessage = employeeInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return employeeOutputMessage;
	}

	@Override
	public EmployeeOutputMessage deleteEmployee(
			EmployeeInputMessage employeeInputMessage) {
		flowId = DELETE_EMPLOYEE_GROUP;
		this.employeeInputMessage = employeeInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return employeeOutputMessage;

	}

	@Override
	public EmployeeOutputMessage findEmployeeById(
			EmployeeInputMessage employeeInputMessage) {
		flowId = FIND_EMPLOYEE_GROUP_BY_ID;
		this.employeeInputMessage = employeeInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return employeeOutputMessage;

	}

	@Override
	public EmployeeOutputMessage findAllEmployee() {
		flowId = FIND_ALL_EMPLOYEE_GROUPES;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return employeeOutputMessage;
	}
	@Override
	public EmployeeOutputMessage preLoad() {
		
		flowId = PRE_LOAD;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return employeeOutputMessage;
	}
	// @Override
	public EmployeeOutputMessage findEmployee(
			EmployeeInputMessage employeeInputMessage) {
		flowId = FIND_EMPLOYEE_GROUPES;
		this.employeeInputMessage = employeeInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return employeeOutputMessage;
	}
	@Override
	public EmployeeOutputMessage findAllActivatedEmployee() {
		// TODO Auto-generated method stub
		flowId = FIND_ALL_ACTIVATED_EMPLOYEE;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return employeeOutputMessage;
	}
	@Override
	public EmployeeOutputMessage findAllActivatedEmployeeByDeptId(
			EmployeeInputMessage employeeInputMessage) {
		flowId = FIND_ALL_ACTIVATED_EMPLOYEE_BY_DEPT_ID;
		this.employeeInputMessage = employeeInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return employeeOutputMessage;
	}
	

	@Override
	public EmployeeOutputMessage findAllActivatedEmployeeByJoinDate(EmployeeInputMessage employeeInputMessage) {
		flowId = FIND_ALL_ACTIVATED_EMPLOYEE_BY_JOIN_DATE;
		this.employeeInputMessage = employeeInputMessage;
		advanzErpServiceTemplate.execute(this);
		return employeeOutputMessage;
	}

	

	@Override
	public boolean validateInput() {

		if (ADD_EMPLOYEE_GROUP.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_EMPLOYEE_GROUP.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_EMPLOYEE_GROUP.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_EMPLOYEE_GROUP_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_EMPLOYEE_GROUPES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_EMPLOYEE_GROUPES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (PRE_LOAD.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_ALL_ACTIVATED_EMPLOYEE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_ALL_ACTIVATED_EMPLOYEE_BY_DEPT_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_ALL_ACTIVATED_EMPLOYEE_BY_JOIN_DATE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		
		return false;
	}

	@Override
	public void performBusinessLogic() {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		// String ignoreProperties[]= {"branchId,servTaxDate,vatDate,cstDate"};

		if (employeeInputMessage != null) {
			EmployeeDTO employeeDTO = employeeInputMessage.getEmployeeDTO();
			if (employeeDTO != null) {
				BeanUtils.copyProperties(employeeDTO, employeeEntity);
				CityEntity cityEntity = new CityEntity();
				if (employeeDTO.getEmployeeCity() != null)
					cityEntity.setCityId(employeeDTO.getEmployeeCity()
							.getCityId());
				employeeEntity.setEmployeeCityEntity(cityEntity);
				if (employeeDTO.getEmployeeLeavesDTOList() != null
						&& employeeDTO.getEmployeeLeavesDTOList().size() > 0) {
					List<EmployeeLeavesEntity> employeeLeavesEntityList = new ArrayList<EmployeeLeavesEntity>();
					for (EmployeeLeavesDTO obj : employeeDTO
							.getEmployeeLeavesDTOList()) {
						if (obj.getLeaveId() != null) {
							EmployeeLeavesEntity employeeLeavesEntity = new EmployeeLeavesEntity();
							BeanUtils.copyProperties(obj, employeeLeavesEntity);
							EmployeeLeaveKey id = new EmployeeLeaveKey();
							id.setLeave_id(obj.getLeaveId());
							employeeLeavesEntity.setId(id);
							employeeLeavesEntityList.add(employeeLeavesEntity);
						}
					}
					employeeEntity
							.setEmployeeLeavesEntityList(employeeLeavesEntityList);
				}

				if ((employeeDTO.getEmployeeSalaryDetDTOList() != null && employeeDTO
						.getEmployeeSalaryDetDTOList().size() > 0)
						|| (employeeDTO.getEmployeeSalaryDetDTOListDe() != null && employeeDTO
								.getEmployeeSalaryDetDTOListDe().size() > 0)) {
					List<EmployeeSalaryDetEntity> employeeSalaryDetEntityList = new ArrayList<EmployeeSalaryDetEntity>();
					for (EmployeeSalaryDetDTO obj : employeeDTO
							.getEmployeeSalaryDetDTOList()) {
						EmployeeSalaryDetEntity employeeSalaryDetEntity = new EmployeeSalaryDetEntity();
						BeanUtils.copyProperties(obj, employeeSalaryDetEntity);
						EmployeeSalaryDepKey id = new EmployeeSalaryDepKey();
						if (obj.getSalaryId() != null)
							id.setSalary_head_id(Integer.parseInt(obj
									.getSalaryId()));
						employeeSalaryDetEntity.setId(id);
						employeeSalaryDetEntityList
								.add(employeeSalaryDetEntity);
					}
					if (employeeDTO.getEmployeeSalaryDetDTOListDe() != null)
						for (EmployeeSalaryDetDTO obj : employeeDTO
								.getEmployeeSalaryDetDTOListDe()) {
							EmployeeSalaryDetEntity employeeSalaryDetEntity = new EmployeeSalaryDetEntity();
							BeanUtils.copyProperties(obj,
									employeeSalaryDetEntity);
							EmployeeSalaryDepKey id = new EmployeeSalaryDepKey();
							if (obj.getSalaryId() != null)
								id.setSalary_head_id(Integer.parseInt(obj
										.getSalaryId()));
							employeeSalaryDetEntity.setId(id);
							employeeSalaryDetEntityList
									.add(employeeSalaryDetEntity);
						}
					employeeEntity
							.setEmployeeSalaryDetEntityList(employeeSalaryDetEntityList);
				}

			}
		}

		if (ADD_EMPLOYEE_GROUP.equals(flowId)) {
			try {
				EmployeeDTO employeeDto = employeeInputMessage.getEmployeeDTO();
				employeeEntity.setDeletedFlag(false);
				storageEmployeeDAO.create(employeeEntity);

			} catch (BeansException e) {
				e.printStackTrace();
			}

		} else if (UPDATE_EMPLOYEE_GROUP.equals(flowId)) {
			try {

				employeeOutputMessage = new EmployeeOutputMessage();
				employeeEntity.setDeletedFlag(false);
				storageEmployeeDAO.update(employeeEntity);

			} catch (BeansException e) {
				e.printStackTrace();
			}

 	} else if (DELETE_EMPLOYEE_GROUP.equals(flowId)) {
		try {
		EmployeeDTO employeeDto = employeeInputMessage.getEmployeeDTO();
		boolean flag = false;
		String error = null;
		employeeOutputMessage = new EmployeeOutputMessage();
	/*	List<BillEntity> list = storageBillDAO.findByEmployeeId(employeeDto.getEmployeeId());
		if ((list != null && list.size() > 0)) {
			error = "Employee use in Bill Master. You can not delete.";
			flag = true;
			} else {*/
		List<EmployeeLeavesEntity> list1 = storageEmployeeDAO.findLeaveByEmployeeId(employeeDto.getEmployeeId());
			
		if ((list1 != null && list1.size() > 0)) {
		 	error = "Employee use in Employee Leave. You can not delete.";
				flag = true;
			}
		/*else {
			List<EmployeeSalaryDetEntity> list2 = storageEmployeeDAO.findSalaryByEmployeeId(employeeDto.getEmployeeId());
		
		if ((list2 != null && list2.size() > 0)) {
			System.out.println("66666666666666666666666666666666666666666666666666666666666666");
			error = "Employee use in Employee Salary. You can not delete.";
			flag = true;
			}
		  }*/
		//}
				if (flag && error != null) {
					ErrorDTO errorDTO = new ErrorDTO("1", error);
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					employeeOutputMessage.setErrorListDTO(errorListDTO);
				} else {
					if(employeeInputMessage.isDeletedFlag()){
					employeeOutputMessage.setErrorListDTO(null);
					BeanUtils.copyProperties(employeeDto, employeeEntity);
					storageEmployeeDAO.delete(employeeEntity);
					}
				}
			} catch (BeansException e) {
				e.printStackTrace();
			}

		} else if (FIND_EMPLOYEE_GROUP_BY_ID.equals(flowId)) {
			EmployeeDTO employeeDTO = employeeInputMessage.getEmployeeDTO();
			List<EmployeeEntity> list = storageEmployeeDAO.findById(employeeDTO
					.getEmployeeId());
			if (list != null) {
				List<EmployeeDTO> resultList = convertEmployeeEntityListTOEmployeeDtoList(list);
				employeeOutputMessage.setEmployeeDTOList(resultList);
			}
		} else if (FIND_ALL_EMPLOYEE_GROUPES.equals(flowId)) {
			List<EmployeeEntity> list = storageEmployeeDAO.load();
			employeeOutputMessage = new EmployeeOutputMessage();
			// set the data to the outputput message.
			if (list != null) {
				List<EmployeeDTO> resultList = convertEmployeeEntityListTOEmployeeDtoList(list);
				employeeOutputMessage.setEmployeeDTOList(resultList);
			}
		} 
		 else if (FIND_ALL_ACTIVATED_EMPLOYEE.equals(flowId)) {
				List<EmployeeEntity> list = storageEmployeeDAO.findAllActivatedEmployee();
				employeeOutputMessage = new EmployeeOutputMessage();
				// set the data to the outputput message.
				if (list != null) {
					List<EmployeeDTO> resultList = convertEmployeeEntityListTOEmployeeDtoList(list);
					employeeOutputMessage.setEmployeeDTOList(resultList);
				}
			} 
		 else if (FIND_ALL_ACTIVATED_EMPLOYEE_BY_DEPT_ID.equals(flowId)) {
				EmployeeDTO employeeDTO = employeeInputMessage.getEmployeeDTO();
				List<EmployeeEntity> list = storageEmployeeDAO.findAllActivatedEmployeeByDeptId(employeeDTO.getDesignation());
				employeeOutputMessage = new EmployeeOutputMessage();
				// set the data to the outputput message.
				if (list != null && list.size()>0) {
					List<EmployeeDTO> resultList = convertEmployeeEntityListTOEmployeeDtoList(list);
					employeeOutputMessage.setEmployeeDTOList(resultList);
				}
			} 
		
		 else if (FIND_ALL_ACTIVATED_EMPLOYEE_BY_JOIN_DATE.equals(flowId)) {
				EmployeeDTO employeeDTO = employeeInputMessage.getEmployeeDTO();
				List<EmployeeEntity> list = storageEmployeeDAO.findAllActivatedEmployeeByJoinDate(employeeDTO.getJoinDateString());
				employeeOutputMessage = new EmployeeOutputMessage();
				// set the data to the outputput message.
				if (list != null && list.size()>0) {
					List<EmployeeDTO> resultList = convertEmployeeEntityListTOEmployeeDtoList(list);
					employeeOutputMessage.setEmployeeDTOList(resultList);
				}
			} 
		else if (PRE_LOAD.equals(flowId)) {
			List list = storageEmployeeDAO.preLoad();
			employeeOutputMessage = new EmployeeOutputMessage();
			// set the data to the outputput message.
			if (list != null && list.size()>0) {
			//	List<EmployeeDTO> resultList = convertEmployeeEntityListTOEmployeeDtoList(list);
			//	employeeOutputMessage.setEmployeeDTOList(resultList);
				List<EmployeeDTO> employeeList=new ArrayList<EmployeeDTO>();
				for(int i=0;i<list.size();i++){
					Object[] object =	(Object [])list.get(i);
					String employeeName=(String)object[0];
					int employeeId=(Integer)object[1];
					String employeeCode=(String)object[2];
					EmployeeDTO employeeDTO=new EmployeeDTO();
					employeeDTO.setEmployeeName(employeeName);
					employeeDTO.setEmployeeId(employeeId);
					employeeDTO.setEmployeeCode(employeeCode);
					employeeList.add(employeeDTO);
					}
				employeeOutputMessage.setEmployeeDTOList(employeeList);
			}
		}
		else if (FIND_EMPLOYEE_GROUPES.equals(flowId)) {
			EmployeeDTO employeeDTO = employeeInputMessage.getEmployeeDTO();
			String employeeName=null;
			String employeeCode=null;
			String employeeCity=null;
			int pmFlag=0;
			if(employeeDTO.getEmployeeName()!=null){
				employeeName=employeeDTO.getEmployeeName();
			}
			if(employeeDTO.getEmployeeCode()!=null){
				employeeCode=employeeDTO.getEmployeeCode();
			}
			if(employeeDTO.getEmployeeCity().getCityName()!=null){
				employeeCity=employeeDTO.getEmployeeCity().getCityName();
			}if(employeeDTO.getPmFlag()!=null){
				pmFlag=employeeDTO.getPmFlag();
			}
			
			
			List<EmployeeEntity> list = storageEmployeeDAO.search(employeeName, employeeCode,employeeCity, pmFlag);
			employeeOutputMessage = new EmployeeOutputMessage();
			if (list != null) {
				List<EmployeeDTO> resultList = convertEmployeeEntityListTOEmployeeDtoList(list);
				employeeOutputMessage.setEmployeeDTOList(resultList);
			}
		}
	}

	@Override
	public void formatOutput() {

		if (ADD_EMPLOYEE_GROUP.equals(flowId)) {

		} else if (UPDATE_EMPLOYEE_GROUP.equals(flowId)) {

		} else if (DELETE_EMPLOYEE_GROUP.equals(flowId)) {

		} else if (FIND_EMPLOYEE_GROUP_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_EMPLOYEE_GROUPES.equals(flowId)) {

		}
	}

	private List<EmployeeDTO> convertEmployeeEntityListTOEmployeeDtoList(
			List<EmployeeEntity> list) {

		employeeOutputMessage = new EmployeeOutputMessage();
		List<EmployeeDTO> resultList = null;
		// set the data to the outputput message.
		if (list != null) {
			EmployeeDTO employeeDTO;
			resultList = new ArrayList<EmployeeDTO>();
			for (EmployeeEntity employeeEntity : list) {
				employeeDTO = new EmployeeDTO();
				// Spring
				// employeeDTO.setEmpIdProofDTO(new EmpIdProofDTO());
				// BeanUtils.copyProperties(entity, employeeDTO);

				if (employeeEntity != null) {
					BeanUtils.copyProperties(employeeEntity, employeeDTO);
					employeeDTO.setEmployeeCity(new CityDTO());
					if (employeeEntity.getEmployeeCityEntity() != null) {
						BeanUtils.copyProperties(employeeEntity.getEmployeeCityEntity(),employeeDTO.getEmployeeCity());
					}
					employeeDTO.setMastersDTO(new MastersDTO());
					if (employeeEntity.getMastersEntity() != null) {
						BeanUtils.copyProperties(employeeEntity.getMastersEntity(),employeeDTO.getMastersDTO());
					}
					if (employeeEntity.getEmployeeLeavesEntityList() != null
							&& employeeEntity.getEmployeeLeavesEntityList()
									.size() > 0) {
						List<EmployeeLeavesDTO> employeeLeavesDTOList = new ArrayList<EmployeeLeavesDTO>();
						for (EmployeeLeavesEntity obj : employeeEntity
								.getEmployeeLeavesEntityList()) {
							if (obj.getId() != null) {
								EmployeeLeavesDTO employeeLeavesDTO = new EmployeeLeavesDTO();
								BeanUtils.copyProperties(obj, employeeLeavesDTO);
								employeeLeavesDTO.setLeaveId(obj.getId().getLeave_id());
								employeeLeavesDTOList.add(employeeLeavesDTO);
							}
						}
						employeeDTO.setEmployeeLeavesDTOList(employeeLeavesDTOList);
					}

					if ((employeeEntity.getEmployeeSalaryDetEntityList() != null && employeeEntity
							.getEmployeeSalaryDetEntityList().size() > 0)) {
						List<EmployeeSalaryDetDTO> employeeSalaryDetDTOList = new ArrayList<EmployeeSalaryDetDTO>();
						for (EmployeeSalaryDetEntity obj : employeeEntity.getEmployeeSalaryDetEntityList()) {
							EmployeeSalaryDetDTO employeeSalaryDetDTO = new EmployeeSalaryDetDTO();
							BeanUtils.copyProperties(obj, employeeSalaryDetDTO);
							employeeSalaryDetDTO.setSalaryId(obj.getId().getSalary_head_id().toString());
							employeeSalaryDetDTOList.add(employeeSalaryDetDTO);
						}

						employeeDTO.setEmployeeSalaryDetDTOList(employeeSalaryDetDTOList);
					}

				}

				resultList.add(employeeDTO);

			}
		}

		return resultList;
	}
	
}
