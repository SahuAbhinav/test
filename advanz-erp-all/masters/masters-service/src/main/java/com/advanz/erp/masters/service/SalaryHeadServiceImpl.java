package com.advanz.erp.masters.service;

import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.SalaryHeadEntity;
import com.advanz.erp.masters.model.SalaryHeadDTO;
import com.advanz.erp.masters.model.criteria.SalaryHeadSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.LeaveTypeMastOutputMessage;
import com.advanz.erp.masters.model.msg.SalaryHeadInputMessage;
import com.advanz.erp.masters.model.msg.SalaryHeadOutputMessage;
import com.advanz.erp.masters.service.business.ISalaryHeadService;
import com.advanz.erp.masters.storage.IStorageEmployeeDAO;
import com.advanz.erp.masters.storage.IStorageLeaveTypeMastDAO;
import com.advanz.erp.masters.storage.IStorageSalaryHeadDAO;

public class SalaryHeadServiceImpl implements ISalaryHeadService {

	public static final String CREATE_SALARY_HEAD = "CreateSalaryHead";
	public static final String UPDATE_SALARY_HEAD = "UpdateSalaryHead";
	public static final String DELETE_SALARY_HEAD = "DeleteSalaryHead";
	public static final String FIND_SALARY_HEAD_BY_ID = "FindSalaryHeadById";
	public static final String FIND_ALL_SALARY_HEAD = "FindAllSalaryHeads";
	public static final String SEARCH_SALARY_HEAD = "SearchSalaryHeads";
	public static final String GET_Head_Type ="GetHeadType";

	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageSalaryHeadDAO storageSalaryHeadDAO;
	
	@Autowired
	public IStorageLeaveTypeMastDAO leaveTypeMastDAO;
	
	@Autowired
	public IStorageEmployeeDAO employeeDAO;
	

	public SalaryHeadInputMessage salaryHeadInputMessage;

	public SalaryHeadOutputMessage salaryHeadOutputMessage;

	@Override
	public SalaryHeadOutputMessage createSalaryHead(
			SalaryHeadInputMessage salaryHeadInputMessage) {

		flowId = CREATE_SALARY_HEAD;
		// assign the message to the class level variable.
		System.out.print("**************************************************************:-");
		this.salaryHeadInputMessage = salaryHeadInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salaryHeadOutputMessage;
	}

	@Override
	public SalaryHeadOutputMessage updateSalaryHead(
			SalaryHeadInputMessage salaryHeadInputMessage) {

		flowId = UPDATE_SALARY_HEAD;
		// assign the message to the class level variable.
		this.salaryHeadInputMessage = salaryHeadInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return salaryHeadOutputMessage;
	}

	@Override
	public SalaryHeadOutputMessage deleteSalaryHead(
			SalaryHeadInputMessage salaryHeadInputMessage) {
		flowId = DELETE_SALARY_HEAD;
		// assign the message to the class level variable.
		this.salaryHeadInputMessage = salaryHeadInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return salaryHeadOutputMessage;

	}

	@Override
	public SalaryHeadOutputMessage findSalaryHeadById(
			SalaryHeadInputMessage salaryHeadInputMessage) {
		flowId = FIND_SALARY_HEAD_BY_ID;
		// assign the message to the class level variable.
		this.salaryHeadInputMessage = salaryHeadInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salaryHeadOutputMessage;

	}

	@Override
	public SalaryHeadOutputMessage findAllSalaryHeads() {
		flowId = FIND_ALL_SALARY_HEAD;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salaryHeadOutputMessage;
	}

	@Override
	public SalaryHeadOutputMessage search(
			SalaryHeadInputMessage salaryHeadInputMessage) {
		flowId = SEARCH_SALARY_HEAD;
		this.salaryHeadInputMessage = salaryHeadInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salaryHeadOutputMessage;

	}
	
	@Override
	public SalaryHeadOutputMessage getHeadType() {
		flowId =GET_Head_Type;
		advanzErpServiceTemplate.execute(this);
		return salaryHeadOutputMessage;
	}

	@Override
	public Integer findSalaryHeadByType(String headType) {
		Integer i= storageSalaryHeadDAO.findSalaryHeadByType(headType);
		return i;
	}
	@Override
	public boolean validateInput() {

		if (CREATE_SALARY_HEAD.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_SALARY_HEAD.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_SALARY_HEAD.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_SALARY_HEAD_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_SALARY_HEAD.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_SALARY_HEAD.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		
		else if (GET_Head_Type.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		
		return false;
	}

	@Override
	public void performBusinessLogic() {

     SalaryHeadEntity salaryHeadEntity = new SalaryHeadEntity();
	 // System.out.print("----test  --salaryHeadInputMessage 1 :-"+salaryHeadInputMessage.getSalaryHeadDTO());
	if (salaryHeadInputMessage != null) {
	 //System.out.print("----test  --salaryHeadInputMessage 1 :-"	+ salaryHeadInputMessage.getSalaryHeadDTO());
	  
	 
	 
	 SalaryHeadDTO salaryHeadDTO = salaryHeadInputMessage.getSalaryHeadDTO();
		 if (salaryHeadDTO != null) {
		 BeanUtils.copyProperties(salaryHeadDTO, salaryHeadEntity);

		 
		List<SalaryHeadDTO> basHeads = salaryHeadInputMessage.getSalaryHeadDTO().getBaseHeads();
		if (basHeads != null) {
			List<SalaryHeadEntity> baseHeadsEntityes = new ArrayList<SalaryHeadEntity>();
		for (SalaryHeadDTO dt : basHeads) {
		SalaryHeadEntity e = new SalaryHeadEntity();
		//System.out.print("------------dt salary head idsss 1 :-"+ dt.getSalaryHeadId());
		e.setSalaryHeadId(dt.getSalaryHeadId());
		baseHeadsEntityes.add(e);
		 }
		 salaryHeadEntity.setBaseHeadsList(baseHeadsEntityes);
		}
	   }
	  } else {
		//	System.out.print("salaryHeadInputMessage isnull :-");
		}

	if (CREATE_SALARY_HEAD.equals(flowId)) {
		List<SalaryHeadEntity> list1 = storageSalaryHeadDAO.findSalaryHeadByName(salaryHeadEntity.getSalaryHeadName());
		// check duplicate salaryHeadCode
		List<SalaryHeadEntity> list2 = storageSalaryHeadDAO.findSalaryHeadByCode(salaryHeadEntity.getSalaryHeadCode());
		List<SalaryHeadEntity> list3 =storageSalaryHeadDAO.findSalaryHeadByTypeAndSeqNo(salaryHeadEntity.getHeadType(), salaryHeadEntity.getSalaryHeadSequence());
		salaryHeadOutputMessage = new SalaryHeadOutputMessage();
	
	//List<SalaryHeadEntity> list3=storageSalaryHeadDAO.findBasicSalaryFlag();
	//System.out.print("///////////////////////////////////////////////////////////:-"+ list3.size());
	boolean errors = false;
	if (list1 != null && list1.size() > 0) {
	//System.out.print("==========list1 not null ================= :-"+ list1.size());
	ErrorDTO errorDTO = new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed.");
	ErrorListDTO errorListDTO = new ErrorListDTO();
	errorListDTO.addError(errorDTO);
	salaryHeadOutputMessage.setErrorListDTO(errorListDTO);
	errors = true;
	}
	
	if (list3 != null && list3.size() > 0) {
		//System.out.print("==========list1 not null ================= :-"+ list1.size());
		ErrorDTO errorDTO = new ErrorDTO("1","Sorry, Salary head sequence no is already exist with this head type, Duplicate entries are not allowed.");
		ErrorListDTO errorListDTO = new ErrorListDTO();
		errorListDTO.addError(errorDTO);
		salaryHeadOutputMessage.setErrorListDTO(errorListDTO);
		errors = true;
		}
	
	
	if (list2 != null && list2.size() > 0) {
		System.out.print("==========list2 not null================= :-"+ list2.size());
		ErrorDTO errorDTO = new ErrorDTO("2","Sorry, Record already exist, Duplicate entries are not allowed.");
	
	if (!errors) {
			ErrorListDTO errorListDTO = new ErrorListDTO();
			errorListDTO.addError(errorDTO);
			salaryHeadOutputMessage.setErrorListDTO(errorListDTO);
		}
	else {
			salaryHeadOutputMessage.getErrorListDTO().addError(errorDTO);
		}
			errors = true;
	 	}
	
	/*if(list3!=null  && list3.size() > 0)
	{
	//	System.out.print("==========list3 not null================= :-"+ list3.size());
	 ErrorDTO errorDTOs = new ErrorDTO("3","Sorry, Record already exist, Duplicate entries are not allowed.");
	 if (!errors) {
			ErrorListDTO errorListDTO = new ErrorListDTO();
			errorListDTO.addError(errorDTOs);
			salaryHeadOutputMessage.setErrorListDTO(errorListDTO);
		}
	else {
			salaryHeadOutputMessage.getErrorListDTO().addError(errorDTOs);
		}
		 errors = true;
	  }
	*/
	
	
	
	if (!errors) {
			salaryHeadOutputMessage.setErrorListDTO(null);
			System.out.print("Create Salary head go through dao :-"	+ salaryHeadEntity);
				storageSalaryHeadDAO.create(salaryHeadEntity);
			}

		}
	
	else if (UPDATE_SALARY_HEAD.equals(flowId)) {
			// check duplicate salaryHead name
			List<SalaryHeadEntity> list1 = storageSalaryHeadDAO.findByNameAndCode(salaryHeadEntity.getSalaryHeadName(),null);
			List<SalaryHeadEntity> list3 =storageSalaryHeadDAO.findSalaryHeadByTypeAndSeqNo(salaryHeadEntity.getHeadType(), salaryHeadEntity.getSalaryHeadSequence());
			salaryHeadOutputMessage = new SalaryHeadOutputMessage();
			System.out.println("Salary Head FORM ID....................."+salaryHeadEntity.getSalaryHeadId());
			System.out.println("Salary Head DB ID...................."+list1.get(0).getSalaryHeadId());
			boolean errors = false;
			if (list1 != null && list1.size() > 0 && !list1.get(0).getSalaryHeadId().equals(salaryHeadEntity.getSalaryHeadId())) {
				ErrorDTO errorDTO = new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				salaryHeadOutputMessage.setErrorListDTO(errorListDTO);
				errors = true;
			}
			
			if (list3 != null && list3.size() > 0 && !list1.get(0).getSalaryHeadId().equals(salaryHeadEntity.getSalaryHeadId())) {
				ErrorDTO errorDTO = new ErrorDTO("1","Sorry, Salary head sequence no is already exist with this head type, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				salaryHeadOutputMessage.setErrorListDTO(errorListDTO);
				errors = true;
			}
			if (!errors) {
				salaryHeadOutputMessage.setErrorListDTO(null);
				System.out.println("salary head id fg == ");
				// salaryHeadEntity.setBaseHeadsList(null);
				storageSalaryHeadDAO.update(salaryHeadEntity);
			}
		} else if (DELETE_SALARY_HEAD.equals(flowId)) {
			SalaryHeadDTO salaryHeadDTO = salaryHeadInputMessage.getSalaryHeadDTO();
			
			BeanUtils.copyProperties(salaryHeadDTO,salaryHeadEntity);
				storageSalaryHeadDAO.delete(salaryHeadEntity);
			
		} else if (FIND_SALARY_HEAD_BY_ID.equals(flowId)) {
			List<SalaryHeadEntity> list = storageSalaryHeadDAO.findById(salaryHeadEntity.getSalaryHeadId());
			popUpList(list);
			
		} else if (FIND_ALL_SALARY_HEAD.equals(flowId)) {
			List<SalaryHeadEntity> list = storageSalaryHeadDAO.load();
			popUpList(list);
		} else if (SEARCH_SALARY_HEAD.equals(flowId)) {
			SalaryHeadSearchCriteriaDTO searchCriteria = salaryHeadInputMessage
					.getSearchCriteria();
			List<SalaryHeadEntity> list = storageSalaryHeadDAO.search(
					searchCriteria.getSalaryHeadName(),
					searchCriteria.getSalaryHeadCode(),
					searchCriteria.getHeadType(),
					searchCriteria.getCurrentSalaryHeadId());
			// List<SalaryHeadEntity> list = storageSalaryHeadDAO.load();
			popUpList(list);
		}
	
		else if (GET_Head_Type.equals(flowId)) {			
			salaryHeadOutputMessage = new SalaryHeadOutputMessage();
			List <String> list = storageSalaryHeadDAO.getHeadType();
			salaryHeadOutputMessage.setList(list);
		}
	
	
	}

	void popUpList(List<SalaryHeadEntity> list) {
	salaryHeadOutputMessage = new SalaryHeadOutputMessage();
	// set the data to the output message.
	if (list != null) {
		List<SalaryHeadDTO> resultList = new ArrayList<SalaryHeadDTO>();
		SalaryHeadDTO salaryHeadDTO = null;
		for (SalaryHeadEntity entity : list) {
  		salaryHeadDTO = new SalaryHeadDTO();
		// Spring
			System.out.println("**** lii------------"+ entity.getBaseHeadsList());
			// salaryHeadDTO.setRegionDTO(new RegionDTO());
			List<SalaryHeadDTO> obj = new ArrayList<SalaryHeadDTO>();
				// List<SalaryHeadDTO> list2 = new ArrayList<SalaryHeadDTO>();
				for (SalaryHeadEntity entity1 : entity.getBaseHeadsList()) {
					SalaryHeadDTO obj1 = new SalaryHeadDTO();
					if (entity1 != null) {
						BeanUtils.copyProperties(entity1, obj1);
						obj1.setSalaryHeadId(entity1.getSalaryHeadId());
						obj.add(obj1);
					}

				}
				salaryHeadDTO.setBaseHeads(obj);
				BeanUtils.copyProperties(entity, salaryHeadDTO);

				resultList.add(salaryHeadDTO);

			}
			salaryHeadOutputMessage.setSalaryHeadDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (CREATE_SALARY_HEAD.equals(flowId)) {

		} else if (UPDATE_SALARY_HEAD.equals(flowId)) {

		} else if (DELETE_SALARY_HEAD.equals(flowId)) {

		} else if (FIND_SALARY_HEAD_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_SALARY_HEAD.equals(flowId)) {

		} else if (SEARCH_SALARY_HEAD.equals(flowId)) {

		}
	}

	
}
