package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.criteria.MastersSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.storage.IStorageMastersDAO;

public class MastersServiceImpl implements IMastersService {
	private static final Logger logger = LoggerFactory.getLogger(MastersServiceImpl.class);
	public static final String CREATE_MASTERS = "CreateMasters";
	public static final String UPDATE_MASTERS = "UpdateMasters";
	public static final String DELETE_MASTERS = "DeleteMasters";
	public static final String FIND_MASTERS_BY_ID = "FindMastersById";
	public static final String FIND_ALL_MASTERS = "FindAllMasterss";
	public static final String SEARCH_MASTERS = "SearchMasterss";
	public static final String FIND_FORM_BY_ID = "FindFormById";
	public static final String FIND_MAP_BY_FORMID = "FindMapByFormId";
	public static final String FIND_SUBDEPARTMENT="Subdepartment";
	public static final String PRE_REMOVE_CHECK="PreRemoveCheck";
	public static final String GET_FORM_NAME="GetFormName";
	public static final String FIND_MASTERS_BY_ID_AND_FORM_ID = "FindMastersByIdAndFormId";
	public static final String FIND_FORM_BY_ID_FOR_MELTER_LOG_BOOK="FindFormByIdForMelterLog";
	public static final String FIND_BY_DATE="FindByDate";
	
	
	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageMastersDAO storageMastersDAO;

	public MastersInputMessage mastersInputMessage;

	public MastersOutputMessage mastersOutputMessage;

	@Override
	public MastersOutputMessage createMasters(MastersInputMessage mastersInputMessage) {

		flowId = CREATE_MASTERS;
		// assign the message to the class level variable.
		this.mastersInputMessage = mastersInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return mastersOutputMessage;
	}

	@Override
	public MastersOutputMessage updateMasters(MastersInputMessage mastersInputMessage) {

		flowId = UPDATE_MASTERS;
		// assign the message to the class level variable.
		this.mastersInputMessage = mastersInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return mastersOutputMessage;
	}

	@Override
	public MastersOutputMessage deleteMasters(MastersInputMessage mastersInputMessage) {
		flowId = DELETE_MASTERS;
		// assign the message to the class level variable.
		this.mastersInputMessage = mastersInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return mastersOutputMessage;

	}

	@Override
	public MastersOutputMessage findMastersById(MastersInputMessage mastersInputMessage) {
		flowId = FIND_MASTERS_BY_ID;
		// assign the message to the class level variable.
		this.mastersInputMessage = mastersInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return mastersOutputMessage;

	}

	@Override
	public MastersOutputMessage findAllMasterss() {
		flowId = FIND_ALL_MASTERS;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return mastersOutputMessage;
	}

	@Override
	public MastersOutputMessage searchMasters(MastersInputMessage mastersInputMessage) {
		flowId = SEARCH_MASTERS;
		this.mastersInputMessage=mastersInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return mastersOutputMessage;

	}
	
	@Override
	public MastersOutputMessage findFormById(MastersInputMessage mastersInputMessage) {
		flowId = FIND_FORM_BY_ID;
		this.mastersInputMessage=mastersInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return mastersOutputMessage;

	}
	
	@Override
	public MastersOutputMessage findMapForIdName(MastersInputMessage mastersInputMessage) {
		flowId = FIND_MAP_BY_FORMID;
		this.mastersInputMessage=mastersInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return mastersOutputMessage;

	}
	
	@Override
	public MastersOutputMessage findSubdepartment(MastersInputMessage mastersInputMessage) {
			flowId = FIND_SUBDEPARTMENT;
			this.mastersInputMessage=mastersInputMessage;
			// call the template method
			advanzErpServiceTemplate.execute(this);
			return mastersOutputMessage;
	}
	
	@Override
	public MastersOutputMessage checkBeforeRemove(
			MastersInputMessage mastersInputMessage) {
		flowId = PRE_REMOVE_CHECK;
		this.mastersInputMessage=mastersInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return mastersOutputMessage;
	}
	
	
	@Override
	public MastersOutputMessage getFormName() {
		flowId =GET_FORM_NAME;
		advanzErpServiceTemplate.execute(this);
		return mastersOutputMessage;
	}
	
	@Override
	public MastersOutputMessage findFormByIdForMelterLog(MastersInputMessage mastersInputMessage) {
		flowId = FIND_FORM_BY_ID_FOR_MELTER_LOG_BOOK;
		this.mastersInputMessage=mastersInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return mastersOutputMessage;
	}

	@Override
	public MastersOutputMessage findMastersByIdAndFormId(
			MastersInputMessage mastersInputMessage) {
		flowId = FIND_MASTERS_BY_ID_AND_FORM_ID;
		this.mastersInputMessage=mastersInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return mastersOutputMessage;
	}
	@Override
	public List findByDate(Date date) {
		List list=storageMastersDAO.findByDate(date);
		return list;
	}

	
	
	@Override
	public boolean validateInput() {

		if (CREATE_MASTERS.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_MASTERS.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_MASTERS.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_MASTERS_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_MASTERS.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_MASTERS.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_FORM_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_MAP_BY_FORMID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_SUBDEPARTMENT.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (PRE_REMOVE_CHECK.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_FORM_BY_ID_FOR_MELTER_LOG_BOOK.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		
		else if (GET_FORM_NAME.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_MASTERS_BY_ID_AND_FORM_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_BY_DATE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		
		return false;
	}

	@Override
	public void performBusinessLogic() {

		MastersEntity mastersEntity = new MastersEntity();
		if (mastersInputMessage != null) {
			MastersDTO mastersDTO = mastersInputMessage.getMastersDTO();
			if (mastersDTO != null) {
				BeanUtils.copyProperties(mastersDTO, mastersEntity);				
		}
		}
		if (CREATE_MASTERS.equals(flowId)) {
			// check duplicate masters name
			List<MastersEntity> list1 = storageMastersDAO.findByNameAndCode(mastersEntity.getName(), null,mastersEntity.getFormId());
			// check duplicate mastersCode
			List<MastersEntity> list2 = storageMastersDAO.findByNameAndCode(null, mastersEntity.getCode(),mastersEntity.getFormId());
			mastersOutputMessage = new MastersOutputMessage();
			boolean errors = false;
			if (list1 != null && list1.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("1", "Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				mastersOutputMessage.setErrorListDTO(errorListDTO);
				errors = true;
			}
			if (list2 != null && list2.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("2", "Sorry, Record already exist, Duplicate entries are not allowed.");
				if (!errors) {
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					mastersOutputMessage.setErrorListDTO(errorListDTO);
				} else {
					mastersOutputMessage.getErrorListDTO().addError(errorDTO);
				}
				errors = true;
			}
			if (!errors) {
				mastersOutputMessage.setErrorListDTO(null);
				storageMastersDAO.create(mastersEntity);
			}
			
		} else if (UPDATE_MASTERS.equals(flowId)) {
			// check duplicate masters name
			List<MastersEntity> list1 = storageMastersDAO.findByNameAndCode(mastersEntity.getName(), null,mastersEntity.getFormId());
			// check duplicate mastersCode
			List<MastersEntity> list2 = storageMastersDAO.findByNameAndCode(null, mastersEntity.getCode(),mastersEntity.getFormId());
			mastersOutputMessage = new MastersOutputMessage();
			boolean errors = false;
			if (list1 != null && list1.size() > 0 && !(list1.get(0).getId().equals(mastersEntity.getId()) && list1.get(0).getFormId().equals(mastersEntity.getFormId()))) {
				ErrorDTO errorDTO = new ErrorDTO("1", "Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				mastersOutputMessage.setErrorListDTO(errorListDTO);
				errors = true;
			}
			if (list2 != null && list2.size() > 0 && !(list2.get(0).getId().equals(mastersEntity.getId()) && list2.get(0).getFormId().equals(mastersEntity.getFormId()))) {
				ErrorDTO errorDTO = new ErrorDTO("2", "Sorry, Record already exist, Duplicate entries are not allowed.");
				if (!errors) {
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					mastersOutputMessage.setErrorListDTO(errorListDTO);
				} else {
					mastersOutputMessage.getErrorListDTO().addError(errorDTO);
				}
				errors = true;
			}
			if (!errors) {
				mastersOutputMessage.setErrorListDTO(null);
				storageMastersDAO.update(mastersEntity);
			}
		} else if (DELETE_MASTERS.equals(flowId)) {
			storageMastersDAO.delete(mastersEntity);
		} else if (FIND_MASTERS_BY_ID.equals(flowId)) {
	//	List<MastersEntity>list=	storageMastersDAO.findById(mastersEntity.getId(),mastersEntity.getFormId());
			//System.out.println("mastersEntity.getMastersId()"+mastersEntity.getMastersId());
			List<MastersEntity>list=	storageMastersDAO.findById(mastersEntity.getMastersId());
			if(list.size()>0){
			System.out.println(list.get(0));
			}
			popUpList(list);
		} else if (FIND_ALL_MASTERS.equals(flowId)) {
			List<MastersEntity> list = storageMastersDAO.load();
			popUpList(list);			
		}else if (SEARCH_MASTERS.equals(flowId)) {
			MastersSearchCriteriaDTO searchCriteria=mastersInputMessage.getSearchCriteria();			
			List<MastersEntity> list = storageMastersDAO.search(searchCriteria.getFormName(),searchCriteria.getName(),searchCriteria.getCode());
			popUpList(list);
		}else if (FIND_FORM_BY_ID.equals(flowId)) {			
			//List<MastersEntity> list = storageMastersDAO.findByFormId(mastersEntity.getFormId());
			List<MastersEntity> list = storageMastersDAO.findByFormId(mastersInputMessage.getFormId());
			popUpList(list);
		}
		
		else if (FIND_MASTERS_BY_ID_AND_FORM_ID.equals(flowId)) {			
			List<MastersEntity>list=storageMastersDAO.findById(mastersEntity.getMastersId(),mastersInputMessage.getFormId());
			
			popUpList(list);
		}
		
		
	else if (FIND_FORM_BY_ID_FOR_MELTER_LOG_BOOK.equals(flowId)) {			
		//List<MastersEntity> list = storageMastersDAO.findByFormId(mastersEntity.getFormId());
		List list = storageMastersDAO.findByFormIdForMelterLogBook(mastersInputMessage.getFormId());
								  
		List<MastersDTO> resultList=new ArrayList<MastersDTO>();	
		
		for(int i=0;i<list.size();i++)
		{
		MastersDTO mastersDTO=new MastersDTO();
		Object[] objects=(Object[])list.get(i);
		int id=(Integer)objects[0];
		String name=(String)objects[1];
		 mastersDTO.setMastersId(id);
		 mastersDTO.setName(name);
		 resultList.add(mastersDTO);
		}
		mastersOutputMessage=new MastersOutputMessage();
		mastersOutputMessage.setMastersDTOList(resultList);

	}

		
		
		else if (FIND_MAP_BY_FORMID.equals(flowId)) {			
			List<MastersEntity> list = storageMastersDAO.findByFormId(mastersInputMessage.getFormId());			
			mastersOutputMessage = new MastersOutputMessage();
			// set the data to the output message.
			if (list != null) {
				Map<Integer,String> resultMap=new HashMap<Integer,String>();
				for (MastersEntity entity : list) {
				resultMap.put(entity.getMastersId(),entity.getName());
				}
				mastersOutputMessage.setMastersIdNameMap(resultMap);
				}
		}else if (FIND_SUBDEPARTMENT.equals(flowId)) {			
			//List<MastersEntity> list = storageMastersDAO.findByFormId(mastersEntity.getFormId());
			System.out.println("value = "+mastersEntity.getDeptId() +"  "+ mastersEntity.getFormId());
			List<MastersEntity> list = storageMastersDAO.findSubdepartment(mastersEntity.getDeptId(), mastersEntity.getFormId());
			popUpList(list);
		}
		else if (GET_FORM_NAME.equals(flowId)) {			
			mastersOutputMessage = new MastersOutputMessage();
			List<String> list = storageMastersDAO.getFormName();
			mastersOutputMessage.setList(list);
		}
		if(PRE_REMOVE_CHECK.equals(flowId)){			
			if(storageMastersDAO.isInUsed( mastersInputMessage.getMastersDTO().getMastersId())){
				logger.info("TRUE");		
				mastersOutputMessage = new MastersOutputMessage();
				ErrorDTO errorDTO = new ErrorDTO("1", "Master can not be Removed");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				mastersOutputMessage.setErrorListDTO(errorListDTO);
			}else{
				mastersOutputMessage=null;
			}
		}
	}
	void popUpList(List<MastersEntity> list) {
		mastersOutputMessage = new MastersOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<MastersDTO> resultList = new ArrayList<MastersDTO>();
			MastersDTO mastersDTO;
			for(MastersEntity entity : list) {
				mastersDTO = new MastersDTO();
				BeanUtils.copyProperties(entity, mastersDTO);
				resultList.add(mastersDTO);
			}
			mastersOutputMessage.setMastersDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (CREATE_MASTERS.equals(flowId)) {

		} else if (UPDATE_MASTERS.equals(flowId)) {

		} else if (DELETE_MASTERS.equals(flowId)) {

		} else if (FIND_MASTERS_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_MASTERS.equals(flowId)) {

		} else if (SEARCH_MASTERS.equals(flowId)) {

		}
		else if (FIND_FORM_BY_ID .equals(flowId)) {

		}
		else if (FIND_MAP_BY_FORMID.equals(flowId)) {

		}
		else if (FIND_FORM_BY_ID_FOR_MELTER_LOG_BOOK.equals(flowId)) {

		}

	}

	@Override
	public String getMasterNameById(Integer masterId) {
		List l=storageMastersDAO.findByMasterNameById(masterId);
		String name=null;
		if(l!=null && l.size()>0){
			name =(String)l.get(0);
		}
		return name;
	}

	
	
	






}
