package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.ProfessionalTaxDeductTypeEntity;
import com.advanz.erp.masters.model.ProfessionalTaxDeductTypeDTO;
import com.advanz.erp.masters.model.msg.ProfessionalTaxDeductTypeInputMessage;
import com.advanz.erp.masters.model.msg.ProfessionalTaxDeductTypeOutputMessage;
import com.advanz.erp.masters.service.business.IProfessionalTaxDeductTypeService;
import com.advanz.erp.masters.storage.IStorageProfessionalTaxDeductTypeDAO;
@Service
public class ProfessionalTaxDeductTypeServiceImpl implements IProfessionalTaxDeductTypeService {
	

		public static final String FIND_ALL_DEDUCT_TYPE = "FindAllDeductType";
		public String flowId = "";

		 //@Autowired
		public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																									// do
		private static final Logger logger = LoggerFactory.getLogger(ItemGroupFlagServiceImpl.class);																						// autowiring

		@Autowired
		public IStorageProfessionalTaxDeductTypeDAO professionalTaxDeductTypeDAO;

		public ProfessionalTaxDeductTypeInputMessage professionalTaxDeductTypeInputMessage;

		public ProfessionalTaxDeductTypeOutputMessage professionalTaxDeductTypeOutputMessage;

		
		@Override
		public ProfessionalTaxDeductTypeOutputMessage findAllDeductType() {
			System.out.println("------------------------------------Loading All");
			flowId = FIND_ALL_DEDUCT_TYPE;
			// call the template method
			advanzErpServiceTemplate.execute(this);
			return professionalTaxDeductTypeOutputMessage;
		}
		
		@Override
		public void performBusinessLogic() {
		if (FIND_ALL_DEDUCT_TYPE.equals(flowId)) {
				System.out.println("------------------------------------Loading All");
				List<ProfessionalTaxDeductTypeEntity> list = professionalTaxDeductTypeDAO.load();
				popUpList(list);
			}
		}

		void popUpList(List<ProfessionalTaxDeductTypeEntity> list) {
			professionalTaxDeductTypeOutputMessage = new ProfessionalTaxDeductTypeOutputMessage();
			// set the data to the output message.
			if (list != null) {
				List<ProfessionalTaxDeductTypeDTO> resultList = new ArrayList<ProfessionalTaxDeductTypeDTO>();
				ProfessionalTaxDeductTypeDTO professionalTaxDeductTypeDTO;
				for (ProfessionalTaxDeductTypeEntity entity : list) {
					professionalTaxDeductTypeDTO = new ProfessionalTaxDeductTypeDTO();
					//Spring				
					BeanUtils.copyProperties(entity, professionalTaxDeductTypeDTO);
					
					resultList.add(professionalTaxDeductTypeDTO);
				}
				
				professionalTaxDeductTypeOutputMessage.setProfessionalTaxDeductTypeDTOList(resultList);
			}
		}

		@Override
		public boolean validateInput() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public void formatOutput() {
			// TODO Auto-generated method stub
			
		}

		

	}


