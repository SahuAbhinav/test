package com.advanz.erp.masters.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.MasterFormulaDetailEntity;
import com.advanz.erp.masters.entity.jpa.MasterFormulaMasterEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MasterFormulaDetailDTO;
import com.advanz.erp.masters.model.MasterFormulaMasterDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.msg.MasterFormulaMasterInputMessage;
import com.advanz.erp.masters.model.msg.MasterFormulaMasterOutputMessage;
import com.advanz.erp.masters.service.business.IMasterFormulaService;
import com.advanz.erp.masters.storage.IStorageMasterFormulaDAO;
@Service
public class MasterFormulaServiceImpl implements IMasterFormulaService{
	private static final Logger logger = LoggerFactory.getLogger(MasterFormulaServiceImpl.class);
	public static final String FIND_ALL_MASTER_FORMULA = "FindAllMasterFormula";
	public static final String CREATE_NEW_MASTER_FORMULA = "createNewMasterFormula";
	public static final String DELETE_MASTER_FORMULA = "deleteMasterFormula";
	public static final String SEARCH_MASTER_FORMULA = "searchMasterFormula";
	public static final String FIND_MASTER_FORMULA_BY_ID = "findMasterFormulaById";
	public static final String UPDATE_FIND_MASTER_FORMULA = "updateMasterFormula";
	
	public String flowId = "";

	 @Autowired
	 IStorageMasterFormulaDAO iStorageMasterFormulaDAO;
	 
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
	private MasterFormulaMasterOutputMessage masterFormulaMasterOutputMessage;
	private MasterFormulaMasterInputMessage masterFormulaMasterInputMessage;
	
	// do
	@Override
	public MasterFormulaMasterOutputMessage findAllMasterFormula() {
		flowId=FIND_ALL_MASTER_FORMULA;
		advanzErpServiceTemplate.execute(this);
		return masterFormulaMasterOutputMessage;
	}
		

	@Override
	public MasterFormulaMasterOutputMessage createMasterFormula(
			MasterFormulaMasterInputMessage formulaMasterInputMessage) {
	
		flowId=CREATE_NEW_MASTER_FORMULA;
		this.masterFormulaMasterInputMessage=formulaMasterInputMessage;
		advanzErpServiceTemplate.execute(this);
		return masterFormulaMasterOutputMessage;
	}
	

	@Override
	public MasterFormulaMasterOutputMessage deleteMasterFormula(
			MasterFormulaMasterInputMessage masterFormulaMasterInputMessage) {
		flowId=DELETE_MASTER_FORMULA;
		this.masterFormulaMasterInputMessage=masterFormulaMasterInputMessage;
		advanzErpServiceTemplate.execute(this);
		return masterFormulaMasterOutputMessage;
	}

	

	@Override
	public MasterFormulaMasterOutputMessage searchMasterFormula(
			MasterFormulaMasterInputMessage formulaMasterInputMessage) {
		
		flowId=SEARCH_MASTER_FORMULA;
		this.masterFormulaMasterInputMessage=formulaMasterInputMessage;
		advanzErpServiceTemplate.execute(this);
		return masterFormulaMasterOutputMessage;
	}



	@Override
	public MasterFormulaMasterOutputMessage findMasterFormulaById(
			MasterFormulaMasterInputMessage masterFormulaMasterInputMessage) {
		flowId=FIND_MASTER_FORMULA_BY_ID;
		this.masterFormulaMasterInputMessage=masterFormulaMasterInputMessage;
		advanzErpServiceTemplate.execute(this);
		return masterFormulaMasterOutputMessage;
		
	}

	@Override
	public MasterFormulaMasterOutputMessage updateMasterFormula(
			MasterFormulaMasterInputMessage masterFormulaMasterInputMessage) {
		flowId=UPDATE_FIND_MASTER_FORMULA;
		this.masterFormulaMasterInputMessage=masterFormulaMasterInputMessage;
		advanzErpServiceTemplate.execute(this);
		return masterFormulaMasterOutputMessage;
	}

	
	@Override
	public boolean validateInput() {
		if(flowId.equals(FIND_ALL_MASTER_FORMULA))
		{
			return true;
		}
		if(flowId.equals(CREATE_NEW_MASTER_FORMULA))
		{
			return true;
		}
		if(flowId.equals(DELETE_MASTER_FORMULA))
		{
			return true;
		}
		if(flowId.equals(SEARCH_MASTER_FORMULA))
		{
			return true;
		}
		if(flowId.equals(FIND_MASTER_FORMULA_BY_ID))
		{
			return true;
		}
		if(flowId.equals(UPDATE_FIND_MASTER_FORMULA))
		{
			return true;
		}
		return false;
	}

	@Override
	public void performBusinessLogic() {
	MasterFormulaMasterDTO masterFormulaMasterDTO=new MasterFormulaMasterDTO();
	MasterFormulaMasterEntity masterFormulaMasterEntity=null;
	try
	{

	if (masterFormulaMasterInputMessage!=null)
	 {
	   masterFormulaMasterDTO=masterFormulaMasterInputMessage.getMasterFormulaMasterDTO();
	   if(masterFormulaMasterDTO!=null)
	   {
	
	   masterFormulaMasterEntity=new MasterFormulaMasterEntity();
	   BeanUtils.copyProperties(masterFormulaMasterDTO, masterFormulaMasterEntity);
		   
	   ItemDTO itemDTO=new ItemDTO();
	   itemDTO=masterFormulaMasterDTO.getItemDTO();
	   MastersEntity mastersUnitMsEntity=null;
	   MastersEntity masterPackEntity=null;
	  
	   MastersDTO  mastersUnitMsDTO=new MastersDTO();
	   MastersDTO  mastersPackDTO=new MastersDTO();
	   if(itemDTO!=null)
	   {
		   ItemEntity itemEntity=new ItemEntity();
		   BeanUtils.copyProperties(itemDTO, itemEntity);
		   
		   masterPackEntity=new MastersEntity();
		   mastersPackDTO=itemDTO.getMasterPack();
		
		   if(mastersPackDTO!=null){
			   masterPackEntity=new MastersEntity();
		     	  BeanUtils.copyProperties(mastersPackDTO,masterPackEntity);
		     	  itemEntity.setMasterUnitEntity(masterPackEntity);
		     	} 
		   
		    mastersUnitMsDTO=itemDTO.getMasterUnit();
		    
		   if(mastersUnitMsDTO!=null){
		      mastersUnitMsEntity=new MastersEntity();
		      BeanUtils.copyProperties(mastersUnitMsDTO,mastersUnitMsEntity);
		      itemEntity.setMasterUnitEntity(mastersUnitMsEntity);
		     } 
		   masterFormulaMasterEntity.setItemEntity(itemEntity);
	   }
	   List<MasterFormulaDetailDTO> detailDTOList=masterFormulaMasterDTO.getMasterFormulaDetailDTOList();
	   if(detailDTOList!=null)
	   {
		   List<MasterFormulaDetailEntity> detEntities=convertMasterFormulaDetailDTOToEntity(detailDTOList);
		   masterFormulaMasterEntity.setMasterFormulaDetailEntity(detEntities);
	   }
	   }
	 
	  } 
	
	
	

    if(flowId.equals(FIND_ALL_MASTER_FORMULA))
	 {
	  List<MasterFormulaMasterEntity> masterFormulaMasterEntities=iStorageMasterFormulaDAO.load();
	  convertMasterFormulaEntityToDTO(masterFormulaMasterEntities);
	 }
	
	
    if(flowId.equals(CREATE_NEW_MASTER_FORMULA))
	 {
    
     List<MasterFormulaMasterEntity> list=iStorageMasterFormulaDAO.checkDuplicateEntry(masterFormulaMasterEntity.getItemEntity().getItemId());
    
    masterFormulaMasterOutputMessage=new MasterFormulaMasterOutputMessage();
     if(list!=null && list.size()>0)
     {
    	 ErrorDTO errorDTO=new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed.");
    	 ErrorListDTO errorListDTO=new ErrorListDTO();
    	 errorListDTO.addError(errorDTO);
    	 masterFormulaMasterOutputMessage.setErrorListDTO(errorListDTO);
     }
     else
     {
    	 iStorageMasterFormulaDAO.create(masterFormulaMasterEntity);	
    	 masterFormulaMasterOutputMessage.setErrorListDTO(null);
     }
     }
    
    
    if(flowId.equals(DELETE_MASTER_FORMULA))
	 {
      iStorageMasterFormulaDAO.delete(masterFormulaMasterEntity);
	 }
    
    if(flowId.equals(UPDATE_FIND_MASTER_FORMULA))
	 {
    	List<MasterFormulaMasterEntity> list=iStorageMasterFormulaDAO.checkDuplicateEntry(masterFormulaMasterEntity.getItemEntity().getItemId());
        masterFormulaMasterOutputMessage=new MasterFormulaMasterOutputMessage();
        
    	 if(list!=null && list.size()>0 && !list.get(0).getMasterFormulaAutoId().equals(masterFormulaMasterEntity.getMasterFormulaAutoId()))
         {
        	 ErrorDTO errorDTO=new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed.");
        	 ErrorListDTO errorListDTO=new ErrorListDTO();
        	 errorListDTO.addError(errorDTO);
        	 masterFormulaMasterOutputMessage.setErrorListDTO(errorListDTO);
         }
    	 else
    	 {
    		 iStorageMasterFormulaDAO.update(masterFormulaMasterEntity);	 
    	 }
    	 
    	
	 }
    if(flowId.equals(FIND_MASTER_FORMULA_BY_ID))
	 {
     MasterFormulaMasterEntity masterFormulaEntity=new MasterFormulaMasterEntity();
     masterFormulaEntity =iStorageMasterFormulaDAO.read(masterFormulaMasterEntity.getMasterFormulaAutoId());
     List<MasterFormulaDetailEntity> mastDtl=new ArrayList<MasterFormulaDetailEntity>();
     mastDtl= masterFormulaEntity.getMasterFormulaDetailEntity();
     List<MasterFormulaDetailDTO> mastDtlDTO=new ArrayList<MasterFormulaDetailDTO>();
     if(mastDtl!=null)
     {
      mastDtlDTO=convertDetailEntityToDTO(mastDtl);
     }
    
     ItemEntity itemEntity=new ItemEntity();
     
     itemEntity=masterFormulaEntity.getItemEntity();
     ItemDTO itemDTO=new ItemDTO();
     MastersEntity mastersUnitMsEntity=null;
     MastersDTO mastersUnitMsDTO=null;
     MastersEntity masterPackEntity=null;
     MastersDTO masterPackDTO=null;
     
     if(itemEntity!=null)
     {   
   	  BeanUtils.copyProperties(itemEntity, itemDTO);
   	  mastersUnitMsEntity=itemEntity.getMasterUnitEntity();
   	 
   	  if(mastersUnitMsEntity!=null){
     		mastersUnitMsDTO=new MastersDTO();
     		BeanUtils.copyProperties(mastersUnitMsEntity, mastersUnitMsDTO);
     		itemDTO.setMasterUnit(mastersUnitMsDTO);
     	}
   	  
   	  masterPackEntity=itemEntity.getMasterPackEntity();
   	 if(masterPackEntity!=null){
   		masterPackDTO=new MastersDTO();
  		BeanUtils.copyProperties(masterPackEntity, masterPackDTO);
  		itemDTO.setMasterPack(masterPackDTO);
  	}
   	 
     }
     
     MasterFormulaMasterDTO formulaMasterDTO=new MasterFormulaMasterDTO();
     BeanUtils.copyProperties(masterFormulaEntity, formulaMasterDTO);
     formulaMasterDTO.setItemDTO(itemDTO);
     formulaMasterDTO.setMasterFormulaDetailDTOList(mastDtlDTO);
     
     masterFormulaMasterOutputMessage=new MasterFormulaMasterOutputMessage();
     masterFormulaMasterOutputMessage.setMasterFormulaMasterDTO(formulaMasterDTO);
	 }
    
    if(flowId.equals(SEARCH_MASTER_FORMULA))
	 {
    	
     List<MasterFormulaMasterEntity> masterFormulaMasterDTOList=iStorageMasterFormulaDAO.
     search(masterFormulaMasterEntity.getItemEntity().getItemName(),masterFormulaMasterEntity.getItemEntity().getItemCode());
     convertMasterFormulaEntityToDTO(masterFormulaMasterDTOList);     
	 }
    
	}
	catch (Exception e) {
	e.printStackTrace();
	// TODO: handle exception
	}
  }

	
	@Override
	public void formatOutput() {
		if(flowId.equals(FIND_ALL_MASTER_FORMULA))
		{
		}
		if(flowId.equals(CREATE_NEW_MASTER_FORMULA))
		{
		}
		if(flowId.equals(DELETE_MASTER_FORMULA))
		{
		}
		if(flowId.equals(FIND_MASTER_FORMULA_BY_ID))
		{
		}
		if(flowId.equals(UPDATE_FIND_MASTER_FORMULA))
		{
		}
		if(flowId.equals(FIND_ALL_MASTER_FORMULA))
		{
		}
	}

	
  
  
  public List<MasterFormulaDetailEntity> convertMasterFormulaDetailDTOToEntity(List<MasterFormulaDetailDTO> detailDTOs)
  {
   List<MasterFormulaDetailEntity> resultList=new ArrayList<MasterFormulaDetailEntity>();
   
   MasterFormulaDetailEntity entity=null;
   for(MasterFormulaDetailDTO masterDTO:detailDTOs)
   {
	   entity=new MasterFormulaDetailEntity();
	   ItemDTO itemDTO=new ItemDTO();
	   itemDTO=masterDTO.getItemDTO();
	   	  
	   BeanUtils.copyProperties(masterDTO, entity);  
	   MastersDTO  mastersUnitMsDTO=null;
	   MastersEntity mastersUnitMsEntity=null;
	   if(itemDTO!=null)
	   {
		   ItemEntity itemEntity=new ItemEntity();
		   BeanUtils.copyProperties(itemDTO, itemEntity);
		   
		   mastersUnitMsDTO=itemDTO.getMasterUnit();
		  if(mastersUnitMsDTO!=null){
			 mastersUnitMsEntity=new MastersEntity();
			 BeanUtils.copyProperties(mastersUnitMsDTO,mastersUnitMsEntity);
			 itemEntity.setMasterUnitEntity(mastersUnitMsEntity);
		   	} 
		  
		 entity.setItemEntity(itemEntity);
	   }

	   resultList.add(entity);
   }
 return resultList;
  }
	
  
  public List<MasterFormulaDetailDTO>  convertDetailEntityToDTO(List<MasterFormulaDetailEntity> detailDTOList)
  {
	 List<MasterFormulaDetailDTO> dtoList=new ArrayList<MasterFormulaDetailDTO>();
	 MasterFormulaDetailDTO formulaDetailDTO=null; 
     for(MasterFormulaDetailEntity detailEntity:detailDTOList)
     {
    	 ItemEntity itemEntity=detailEntity.getItemEntity();
    	 formulaDetailDTO=new MasterFormulaDetailDTO();
    	 BeanUtils.copyProperties(detailEntity, formulaDetailDTO);
    	 ItemDTO itemDTO=null;   
    	 MastersDTO  mastersUnitMsDTO=null;
    	 MastersEntity mastersUnitMsEntity=null;
    	 
    	 if(itemEntity!=null)
    	    {
    	 	itemDTO=new ItemDTO();
    	   	BeanUtils.copyProperties(itemEntity, itemDTO);
    	    	
    	   	mastersUnitMsEntity=itemEntity.getMasterUnitEntity();
    	  if(mastersUnitMsEntity!=null){
    		  mastersUnitMsDTO=new MastersDTO();
    		 BeanUtils.copyProperties(mastersUnitMsEntity,mastersUnitMsDTO);
    		 itemDTO.setMasterUnit(mastersUnitMsDTO);
    	   	} 
    	   	formulaDetailDTO.setItemDTO(itemDTO);
    	   }
    	  dtoList.add(formulaDetailDTO);
     }
     return dtoList;
  }
  
  
  
  

  public void convertMasterFormulaEntityToDTO(List<MasterFormulaMasterEntity> entityList)
  {
  masterFormulaMasterOutputMessage=new MasterFormulaMasterOutputMessage();
  if(entityList!=null)
   {
   List<MasterFormulaMasterDTO> resultList=new ArrayList<MasterFormulaMasterDTO>();
   MasterFormulaMasterDTO masterDTO=null;
   for(MasterFormulaMasterEntity entity:entityList)
   {
    masterDTO=new MasterFormulaMasterDTO();
   
    BeanUtils.copyProperties(entity, masterDTO);  
    
    ItemEntity itemEntity=entity.getItemEntity();
    MastersEntity mastersPackEntity=null;
    MastersEntity mastersUnitMsEntity=null;
    MastersDTO mastersPackDTO=new MastersDTO();
	MastersDTO mastersUnitMsDTO=new MastersDTO();
    if(itemEntity!=null)
    {
    	ItemDTO itemDTO=new ItemDTO();
    	BeanUtils.copyProperties(itemEntity, itemDTO);
 
    	mastersPackEntity=itemEntity.getMasterPackEntity();
    	
    	if(mastersPackEntity!=null){
    		mastersPackDTO=new MastersDTO();
    		BeanUtils.copyProperties(mastersPackEntity, mastersPackDTO);
    		itemDTO.setMasterPack(mastersPackDTO);
    	}
    	
    	mastersUnitMsEntity=itemEntity.getMasterUnitEntity();
    	if(mastersUnitMsEntity!=null){
    		mastersUnitMsDTO=new MastersDTO();
    		BeanUtils.copyProperties(mastersUnitMsEntity, mastersUnitMsDTO);
    		itemDTO.setMasterUnit(mastersUnitMsDTO);
    	}
    	masterDTO.setItemDTO(itemDTO);
    }
    
    List<MasterFormulaDetailEntity> detailEntityList=entity.getMasterFormulaDetailEntity();
    if(detailEntityList!=null)
    {
     List<MasterFormulaDetailDTO> detailDTOList=convertDetailEntityToDTO(detailEntityList);
     masterDTO.setMasterFormulaDetailDTOList(detailDTOList);
    }
    
    resultList.add(masterDTO); 
   }
   masterFormulaMasterOutputMessage.setFormulaMasterDTOList(resultList);
  }
  
  }







  
}
