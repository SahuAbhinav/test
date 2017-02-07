package com.advanz.erp.masters.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.entity.jpa.GetPassDetailEntity;
import com.advanz.erp.masters.entity.jpa.GetPassMasterEntity;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.PartyEntity;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.GetPassDetailDTO;
import com.advanz.erp.masters.model.GetPassMasterDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.msg.GetPassMasterInputMessage;
import com.advanz.erp.masters.model.msg.GetPassMasterOutputMessage;
import com.advanz.erp.masters.service.business.IGetPassMasterService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageGetPassMasterDAO;

public class GetPassMasterServiceImpl implements IGetPassMasterService {
	
	public static final String CREATE_GET_PASS_MASTER = "CreateGetPassMaster";
	public static final String UPDATE_GET_PASS_MASTER = "UpdateGetPassMaster";
	public static final String DELETE_GET_PASS_MASTER = "DeleteGetPassMaster";
	public static final String FIND_GET_PASS_MASTER_BY_ID = "FindGetPassMasterById";
	public static final String FIND_ALL_GET_PASS_MASTER = "FindAllGetPassMasters";
	public static final String SEARCH_GET_PASS_MASTER = "SearchGetPassMasters";
	public static final String NEW_GET_PASS_MASTER_SERIES_NO = "NewGetPassMastersSeriesNo";
	public static final String FIND_BY_GATE_PASS_TYPE = "FindByGatePassType";
	public static final String FIND_GET_PASS_PAGINATION = "FindGetPassPagination";
	public String flowId = "";

	
	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
	
	@Autowired
	public IStorageGetPassMasterDAO storageGetPassMasterDAO;

	public GetPassMasterInputMessage getPassInputMessage;

	public GetPassMasterOutputMessage getPassOutputMessage;
	
	@Autowired
	public IZoneService zoneService;
	
	@Override
	public GetPassMasterOutputMessage createGetPassMaster(
			GetPassMasterInputMessage getPassInputMessage) {

		flowId = CREATE_GET_PASS_MASTER;
		// assign the message to the class level variable.
		this.getPassInputMessage = getPassInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return getPassOutputMessage;
	}

	@Override
	public GetPassMasterOutputMessage updateGetPassMaster(
			GetPassMasterInputMessage getPassInputMessage) {
		flowId = UPDATE_GET_PASS_MASTER;
		// assign the message to the class level variable.
		this.getPassInputMessage = getPassInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return getPassOutputMessage;
	}

	@Override
	public GetPassMasterOutputMessage deleteGetPassMaster(
			GetPassMasterInputMessage getPassInputMessage) {
		flowId = DELETE_GET_PASS_MASTER;
		// assign the message to the class level variable.
		this.getPassInputMessage = getPassInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return getPassOutputMessage;

	}

	@Override
	public GetPassMasterOutputMessage findGetPassMasterById(
			GetPassMasterInputMessage getPassInputMessage) {
		flowId = FIND_GET_PASS_MASTER_BY_ID;
		// assign the message to the class level variable.
		this.getPassInputMessage = getPassInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return getPassOutputMessage;

	}

	@Override
	public GetPassMasterOutputMessage findAllGetPassMaster() {
		flowId = FIND_ALL_GET_PASS_MASTER;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return getPassOutputMessage;
	}

	@Override
	public GetPassMasterOutputMessage search(
			GetPassMasterInputMessage getPassInputMessage) {
		flowId = SEARCH_GET_PASS_MASTER;
		this.getPassInputMessage = getPassInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return getPassOutputMessage;
	}

	@Override
	public GetPassMasterOutputMessage getNewGetPassMasterSeriesNo(
			GetPassMasterInputMessage getPassInputMessage) {
		flowId = NEW_GET_PASS_MASTER_SERIES_NO;
		this.getPassInputMessage = getPassInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return getPassOutputMessage;
	}
	@Override
	public GetPassMasterOutputMessage findByGetPassType(
			GetPassMasterInputMessage getPassInputMessage) {
		
		flowId = FIND_BY_GATE_PASS_TYPE;
		this.getPassInputMessage = getPassInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return getPassOutputMessage;
	}
	@Override
	public GetPassMasterOutputMessage findGetPassPagination(
			GetPassMasterInputMessage getPassInputMessage) {
		flowId = FIND_GET_PASS_PAGINATION;
		this.getPassInputMessage = getPassInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return getPassOutputMessage;
	}

	@Override
	public boolean validateInput() {
		if (CREATE_GET_PASS_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_GET_PASS_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_GET_PASS_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_GET_PASS_MASTER_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_ALL_GET_PASS_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (SEARCH_GET_PASS_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (NEW_GET_PASS_MASTER_SERIES_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_BY_GATE_PASS_TYPE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_GET_PASS_PAGINATION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		
		return false;
	}

	@Override
	public void performBusinessLogic() {
		GetPassMasterEntity getPassMasterEntity = new GetPassMasterEntity();
		getPassOutputMessage = new GetPassMasterOutputMessage();
		
		GetPassMasterDTO getPassMasterDTO = null;
		List<GetPassDetailDTO> getPassDetailDTO =null;
		if (getPassInputMessage != null) {
			getPassMasterDTO = getPassInputMessage.getGetPassMasterDTO();
				if (getPassMasterDTO != null) {
					BeanUtils.copyProperties(getPassMasterDTO, getPassMasterEntity);
					PartyDTO partyDTO = getPassMasterDTO.getPartyDTO();
					if (partyDTO != null) {
						PartyEntity partyEntity = new PartyEntity();
						copyObject(partyDTO, partyEntity);
						getPassMasterEntity.setPartyEntity(partyEntity);
					}

					BranchDTO branchDTO = getPassMasterDTO.getBranchDTO();
					if (branchDTO != null) {
						BranchEntity branchEntity = new BranchEntity();
						copyObject(branchDTO, branchEntity);
						getPassMasterEntity.setBranchEntity(branchEntity);
					}
					
					getPassDetailDTO = getPassMasterDTO.getGetPassDetailDTOList();
					if (getPassDetailDTO != null && getPassDetailDTO.size() > 0) {
						List<GetPassDetailEntity> getPassDetailEntity = convertGetPassDetailDtoTOEntity(getPassDetailDTO,getPassMasterDTO.getGatePassNumber(),getPassMasterDTO.getTransactionSeries(),getPassMasterDTO);
						getPassMasterEntity.setGetPassDetailEntitiesList(getPassDetailEntity);
					}
			
		}}
		
		
		if (CREATE_GET_PASS_MASTER.equals(flowId)) {
			
			//if (grnMasterEntity.getGrnDetailEntity() != null && grnMasterEntity.getGrnDetailEntity().size() > 0) {
			List<GetPassMasterEntity> list =  storageGetPassMasterDAO.findByGetPassNumber(getPassMasterEntity.getGatePassNumber());
			if (list != null && list.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO("1",getPassMasterEntity.getGatePassNumber()+" Gate Pass Number is already exist,it can't be duplicate ");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				getPassOutputMessage.setErrorListDTO(errorListDTO);
			} else {
				getPassOutputMessage.setErrorListDTO(null);
				storageGetPassMasterDAO.create(getPassMasterEntity);
			}} 
		if (UPDATE_GET_PASS_MASTER.equals(flowId)) {
		
			storageGetPassMasterDAO.update(getPassMasterEntity);
		}else if (DELETE_GET_PASS_MASTER.equals(flowId)) {
			storageGetPassMasterDAO.delete(getPassMasterEntity);
		}
		else if (FIND_GET_PASS_MASTER_BY_ID.equals(flowId)) {
			List<GetPassMasterEntity> list = storageGetPassMasterDAO
					.findById(getPassMasterEntity.getGatePassAutoId());
			popUpList(list);
		}
		else if (FIND_ALL_GET_PASS_MASTER.equals(flowId)) {
			List<GetPassMasterEntity> list = storageGetPassMasterDAO.load();
			popUpList(list);
		}
		else if (SEARCH_GET_PASS_MASTER.equals(flowId)) {
			String itemName=null;
			String gatePassNumber=null;
			Date gatePassDate=null;
			String partyName=null;
			String gatePassType=null;
			Date toDate=null;
			if(getPassMasterDTO.getGatePassNumber()!=null){
				gatePassNumber=getPassMasterDTO.getGatePassNumber();
			}
			if(getPassMasterDTO.getGatePassDate()!=null){
				gatePassDate=getPassMasterDTO.getGatePassDate();
			}
			if(getPassMasterDTO.getItemName()!=null){
				itemName=getPassMasterDTO.getItemName();
			}if(getPassMasterDTO.getPartyDTO().getPartyName()!=null){
				partyName=getPassMasterDTO.getPartyDTO().getPartyName();
			}if(getPassMasterDTO.getGatePassType()!=null){
				gatePassType=getPassMasterDTO.getGatePassType();
			}if(getPassMasterDTO.getToDate()!=null){
				toDate=getPassMasterDTO.getToDate();
			}
			List<GetPassMasterEntity> list = storageGetPassMasterDAO.search(
					gatePassNumber, gatePassDate, partyName,itemName,gatePassType,toDate);
			popUpList(list);
		}
		else if (NEW_GET_PASS_MASTER_SERIES_NO.equals(flowId)) {
			
			Integer seriesNo=0;
			Timestamp date = zoneService.getFirstDayOfFinYear();
			List list=storageGetPassMasterDAO.getNewSeriesNo(getPassMasterEntity.getFinyr());
			if (list != null && list.size() > 0) {
				Object[] obj=(Object[]) list.get(0);
				
				Number n = (Number) obj[0];
				if (n != null)
					seriesNo = n.intValue();
				if(obj[1]!=null && obj[1]!="")
					date=(Timestamp)obj[1];
			}
			seriesNo++;
			
			getPassOutputMessage = new GetPassMasterOutputMessage();
			getPassOutputMessage.setGetPassSeriesNo(seriesNo);
			getPassOutputMessage.setGetPassSeriesDate(date);
		}
	/*	else if (NEW_GET_PASS_MASTER_SERIES_NO.equals(flowId)) {
			Integer seriesNo = storageGetPassMasterDAO.getNewSeriesNo(getPassMasterEntity.getFinyr());
			getPassOutputMessage = new GetPassMasterOutputMessage();
			getPassOutputMessage.setGetPassSeriesNo(seriesNo);
		}*/
		else if (FIND_BY_GATE_PASS_TYPE.equals(flowId)) {
			List<GetPassMasterEntity> list = storageGetPassMasterDAO.findByGatePassType(getPassMasterDTO.getGatePassType(),getPassInputMessage.getNext());
			popUpList(list);
		}
		else if (FIND_GET_PASS_PAGINATION.equals(flowId)) {
			List<GetPassMasterEntity> list = storageGetPassMasterDAO.findGatePassPagination(getPassInputMessage.getNext());
			popUpList(list);
		}
		
	}
	@Override
	public void formatOutput() {
		// TODO Auto-generated method stub
		
	}

	private List<GetPassDetailEntity> convertGetPassDetailDtoTOEntity(
			List<GetPassDetailDTO> dtoList ,String getPassNumber,String transactionSeries,GetPassMasterDTO getPassMasterDTO) {
		List<GetPassDetailEntity> entityList = new ArrayList<GetPassDetailEntity>();
		for (GetPassDetailDTO dto : dtoList) {
			GetPassDetailEntity entity = new GetPassDetailEntity();

			ItemDTO item = dto.getItemDTO();
			if (item != null) {
				ItemEntity itemEntity = new ItemEntity();
				copyObject(item, itemEntity);

				entity.setItemEntity(itemEntity);
			}
			copyObject(dto, entity);
			if(getPassMasterDTO.getCreatedUserId()!=null){
				entity.setCreatedUserId(getPassMasterDTO.getCreatedUserId());
			}if(getPassMasterDTO.getModifiedUserId()!=null){
				entity.setModifiedUserId(getPassMasterDTO.getModifiedUserId());
			}
			entity.setTransactionSeries(transactionSeries);
			entity.setGatePassNumber(getPassNumber);
			entityList.add(entity);
		}
		return entityList;
	}
	
	
	private List<GetPassDetailDTO> convertGetPassDetailEntityToDto(
			List<GetPassDetailEntity> entityList) {
		List<GetPassDetailDTO> dtoList = new ArrayList<GetPassDetailDTO>();
		for (GetPassDetailEntity entity : entityList) {
			GetPassDetailDTO dto = new GetPassDetailDTO();

			ItemEntity itemEntity = entity.getItemEntity();
			if (itemEntity != null) {
				ItemDTO itemDTO = new ItemDTO();
				copyObject(itemEntity, dto);
				copyObject(itemEntity, itemDTO);
				dto.setItemDTO(itemDTO);
				if(itemEntity.getMasterUnitEntity()!=null){
					dto.setMeasurementUnitId(itemEntity.getMasterUnitEntity().getMastersId());
					dto.setMeasurementUnitName(itemEntity.getMasterUnitEntity().getName());
					
				}
			}
			
			copyObject(entity, dto);
			dtoList.add(dto);
		}
		return dtoList;

	}

	void popUpList(List<GetPassMasterEntity> list) {
		getPassOutputMessage = new GetPassMasterOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<GetPassMasterDTO> resultList = new ArrayList<GetPassMasterDTO>();
			GetPassMasterDTO getPassMasterDTO;
			for (GetPassMasterEntity getPassMasterEntity : list) {
				getPassMasterDTO = new GetPassMasterDTO();
				// Spring

				if (getPassMasterEntity != null) {
					BeanUtils.copyProperties(getPassMasterEntity, getPassMasterDTO);
					PartyEntity partyEntity = getPassMasterEntity.getPartyEntity();
					if (partyEntity != null) {
						PartyDTO partyDTO = new PartyDTO();
						copyObject(partyEntity, partyDTO);
						getPassMasterDTO.setPartyDTO(partyDTO);
					}
					
					
					BranchEntity branchEntity = getPassMasterEntity
							.getBranchEntity();
					if (branchEntity != null) {
						BranchDTO branchDTO = new BranchDTO();
						copyObject(branchEntity, branchDTO);
						getPassMasterDTO.setBranchDTO(branchDTO);
					}
					List<GetPassDetailEntity> getPassDetailEntity = getPassMasterEntity
							.getGetPassDetailEntitiesList();
					if (getPassDetailEntity != null && getPassDetailEntity.size() > 0) {
						List<GetPassDetailDTO> getPassDetailDTO = convertGetPassDetailEntityToDto(getPassDetailEntity);
						getPassMasterDTO.setGetPassDetailDTOList(getPassDetailDTO);
					}}
				resultList.add(getPassMasterDTO);
			}
			getPassOutputMessage.setGetPassMasterDTOList(resultList);
		   }}


	
	
	private void copyObject(Object source, Object target) {
		try {
			BeanUtils.copyProperties(source, target);
		   } catch (Exception e) {
			e.printStackTrace();
		}}

	@Override
	public List getPassDetailList(String PassNumber) {
		List<GetPassDetailEntity> detail=(List<GetPassDetailEntity>)storageGetPassMasterDAO.findByPassNo(PassNumber);
		return convertGetPassDetailEntityToDto(detail);
	}

	@Override
	public void updateGatePassDetail(GetPassDetailDTO detailDTO ,String operation) {
	List<GetPassDetailEntity> gdList=storageGetPassMasterDAO.findByGatePassNoAndItemId(detailDTO.getGatePassNumber(), detailDTO.getItemDTO().getItemId());
	if(gdList!=null && gdList.size()>0){
		GetPassDetailEntity detailEntity=gdList.get(0);
		if(operation!=null && "delete".equalsIgnoreCase(operation)){
			detailEntity.setPendingQuantity(detailEntity.getPendingQuantity()-detailDTO.getGatePassQuantity());
		}else{
		detailEntity.setPendingQuantity(detailEntity.getGatePassQuantity()-detailDTO.getGatePassQuantity());
		}
		storageGetPassMasterDAO.updateGatePassDetail(detailEntity);
	}}

}
