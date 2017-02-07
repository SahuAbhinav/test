package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.SalesOrderEntity;
import com.advanz.erp.masters.model.SalesOrderDTO;
import com.advanz.erp.masters.model.msg.SalesOrderInputMessage;
import com.advanz.erp.masters.model.msg.SalesorderOutputMessage;
import com.advanz.erp.masters.service.business.ISalesOrderService;
import com.advanz.erp.masters.storage.IStorageSalesOrderDAO;

public class SalesOrderServiceImpl implements ISalesOrderService {

	public static final String CREATE_SALES_ORDER = "SalesOrder";
	public static final String UPDATE_SALES_ORDER = "UpdateSalesOrder";
	public static final String ADD_SALES_ORDER = "AddSalesOrder";
	public static final String DELETE_SALES_ORDER = "DeleteSalesOrder";
	public static final String FIND_SALES_ORDER_BY_ID = "FindSalesOrderById";
	public static final String FIND_ALL_SALES_ORDER = "FindAllSalesOrder";
	public static final String FIND_SALES_ORDER = "FindSalesOrder";

	private static final Logger logger = LoggerFactory.getLogger(SalesOrderServiceImpl.class);

	public String flowId = "";

	//@Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();
	// TODO

	@Autowired
	public IStorageSalesOrderDAO salesOrderDAO;
	public SalesOrderInputMessage salesOrderInputMessage;
	public SalesorderOutputMessage salesorderOutputMessage;

	
	
	@Override
	public boolean validateInput() {
		
		if (ADD_SALES_ORDER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_SALES_ORDER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_SALES_ORDER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_SALES_ORDER_BY_ID.equals(flowId)) {
			
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_SALES_ORDER.equals(flowId)) {
			System.out.println("PPPPPPPPPPP::::");
			// TODO add business validation on the input.
			return true;
		} else if (FIND_SALES_ORDER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {
		SalesOrderEntity salesOrderEntity = new SalesOrderEntity();
		
		if (salesOrderInputMessage != null) {
			SalesOrderDTO salesOrderDTO = salesOrderInputMessage
					.getSalesOrderDTO();
			if (salesOrderDTO != null) {
				BeanUtils.copyProperties(salesOrderInputMessage.getSalesOrderDTO(), salesOrderEntity);
			}
		}
			
		
			
			
			
			
			if (ADD_SALES_ORDER.equals(flowId)) {
				try {
					/*salesOrderDTO = salesOrderInputMessage.getSalesOrderDTO();
					List<SalesOrderEntity> list = salesOrderDAO.search(
							salesOrderDTO.getSalesOrderNumber(), null, null);
					logger.info(flowId + ": " + list);
					salesorderOutputMessage = new SalesorderOutputMessage();
					if ((list != null && list.size() > 0)) {
						ErrorDTO errorDTO = new ErrorDTO("1",
								"Duplicate Sales Order Number ");
						ErrorListDTO errorListDTO = new ErrorListDTO();
						errorListDTO.addError(errorDTO);
						salesorderOutputMessage.setErrorListDTO(errorListDTO);
					   }else {*/
						salesorderOutputMessage.setErrorListDTO(null);
						salesOrderEntity.setDeletedFlag(false);
						salesOrderDAO.create(salesOrderEntity);
					 // }

				} catch (BeansException e) {
					e.printStackTrace();
				}
			}

			else if (UPDATE_SALES_ORDER.equals(flowId)) {
		/*		try {
				SalesOrderDTO	salesOrderDTO = salesOrderInputMessage.getSalesOrderDTO();
					List<SalesOrderEntity> list = salesOrderDAO.search(
							salesOrderDTO.getSalesOrderNumber(), null, null);
					logger.info(flowId + ": " + list);
					salesorderOutputMessage = new SalesorderOutputMessage();
					if ((list != null && list.size() > 0)) {
						ErrorDTO errorDTO = new ErrorDTO("1",
								"Duplicate Sales Order Number ");
						ErrorListDTO errorListDTO = new ErrorListDTO();
						errorListDTO.addError(errorDTO);
						salesorderOutputMessage.setErrorListDTO(errorListDTO);
					} else {
						salesorderOutputMessage.setErrorListDTO(null);
						salesOrderEntity.setDeletedFlag(false);
						salesOrderDAO.update(salesOrderEntity);
					}

				} catch (BeansException e) {
					e.printStackTrace();
				}*/
			}

			else if (DELETE_SALES_ORDER.equals(flowId)) {
				try {
					SalesOrderDTO	salesOrderDTO = salesOrderInputMessage.getSalesOrderDTO();

					try {
						BeanUtils.copyProperties(salesOrderDTO,
								salesOrderEntity);
					} catch (BeansException e) {
						e.printStackTrace();
					}
					salesOrderDAO.delete(salesOrderEntity);
				} catch (BeansException e) {
					e.printStackTrace();
				}
			}else if (FIND_ALL_SALES_ORDER.equalsIgnoreCase(flowId)) {

				List<SalesOrderEntity> list = salesOrderDAO.load();
				
				salesorderOutputMessage = new SalesorderOutputMessage();
				// set the data to the output message.
				if (list != null) {
					List<SalesOrderDTO> resultList = convertSalesOrderEntityListTOSalesOrderDTOList(list);
					salesorderOutputMessage.setSalesOrderDTOList(resultList);
				}
			} else if (FIND_SALES_ORDER.equals(flowId)) {
				System.out.println("SALES ORDER SERVICE..............."  );
				salesorderOutputMessage = new SalesorderOutputMessage();
				
				SalesOrderDTO	salesOrderDTO = salesOrderInputMessage.getSalesOrderDTO();
				String salesOrderNumber=null;
				Date salesOrderDate=null;
				Integer partyId=null;
		if(salesOrderDTO.getSalesOrderNumber()!=null){
				 salesOrderNumber= salesOrderDTO.getSalesOrderNumber();
		}if(salesOrderDTO.getSalesOrderDate()!=null){
				 salesOrderDate=salesOrderDTO.getSalesOrderDate();
		}if(salesOrderDTO.getPartyId()!=null){
				 partyId= salesOrderDTO.getPartyId();
		}
		List<SalesOrderEntity> list = salesOrderDAO.search(
						salesOrderNumber,salesOrderDate,partyId);
				if (list != null) {
					List<SalesOrderDTO> resultList = convertSalesOrderEntityListTOSalesOrderDTOList(list);
					  System.out.println("resultList size in service Class : " + resultList.size());
					   salesorderOutputMessage.setSalesOrderDTOList(resultList);
				     }
				   }
			     }
	       


	
	
	@Override
	public SalesorderOutputMessage createSalesOrder(
			SalesOrderInputMessage salesOrderInputMessage) {
		// TODO Auto-generated method stub
		flowId = ADD_SALES_ORDER;
		// assign the message to the class level variable.
		this.salesOrderInputMessage = salesOrderInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salesorderOutputMessage;
	}

	@Override
	public SalesorderOutputMessage updateSalesOrder(
			SalesOrderInputMessage salesOrderInputMessage) {
		// TODO Auto-generated method stub
		flowId = UPDATE_SALES_ORDER;
		// assign the message to the class level variable.
		this.salesOrderInputMessage = salesOrderInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salesorderOutputMessage;
	}

	@Override
	public SalesorderOutputMessage deleteSalesOrder(
			SalesOrderInputMessage salesOrderInputMessage) {
		// TODO Auto-generated method stub
		flowId = DELETE_SALES_ORDER;
		// assign the message to the class level variable.
		this.salesOrderInputMessage = salesOrderInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salesorderOutputMessage;
	}

	@Override
	public SalesorderOutputMessage findSalesOrderById(
			SalesOrderInputMessage salesOrderInputMessage) {
		// TODO Auto-generated method stub
		flowId = FIND_SALES_ORDER_BY_ID;
		this.salesOrderInputMessage = salesOrderInputMessage;
		advanzErpServiceTemplate.execute(this);
		return salesorderOutputMessage;
	}

	@Override
	public SalesorderOutputMessage findAllSalesOrder() {
		// TODO Auto-generated method stub
		flowId = FIND_ALL_SALES_ORDER;
		// call the template method
		this.salesOrderInputMessage =salesOrderInputMessage;
		advanzErpServiceTemplate.execute(this);
		return salesorderOutputMessage;
	}

	@Override
	public SalesorderOutputMessage search(
			SalesOrderInputMessage salesOrderInputMessage) {
		// TODO Auto-generated method stub
		flowId = FIND_SALES_ORDER;
		this.salesOrderInputMessage = salesOrderInputMessage;
		advanzErpServiceTemplate.execute(this);

		return salesorderOutputMessage;
	}

		@Override
	public void formatOutput() {
/*System.out.println("flowIdflowIdflowId :::::::" + flowId);
		if (CREATE_SALES_ORDER.equals(flowId)) {

		} else if (UPDATE_SALES_ORDER.equals(flowId)) {

		} else if (DELETE_SALES_ORDER.equals(flowId)) {

		} else if (FIND_SALES_ORDER_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_SALES_ORDER.equals(flowId)) {

		} else if (FIND_SALES_ORDER.equals(flowId)) {

		}*/
		
	}

	private List<SalesOrderDTO> convertSalesOrderEntityListTOSalesOrderDTOList(
			List<SalesOrderEntity> list) {

		salesorderOutputMessage = new SalesorderOutputMessage();
		List<SalesOrderDTO> resultList = null;
		
		// set the data to the output message.
		if (list != null) {
			SalesOrderDTO salesOrderDTO;
			resultList = new ArrayList<SalesOrderDTO>();
			for (SalesOrderEntity salesOrderEntity : list) {
				salesOrderDTO = new SalesOrderDTO();
				
				if (salesOrderEntity != null) {
					BeanUtils.copyProperties(salesOrderEntity, salesOrderDTO);
				resultList.add(salesOrderDTO);
				}
			}

		}
		return resultList;
	}
}
