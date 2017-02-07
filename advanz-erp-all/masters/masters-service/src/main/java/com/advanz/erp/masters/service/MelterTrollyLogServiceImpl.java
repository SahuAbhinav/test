package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.MelterTrollyLogEntity;
import com.advanz.erp.masters.model.MelterTrollyLogDTO;
import com.advanz.erp.masters.model.msg.MelterTrollyLogInputMessage;
import com.advanz.erp.masters.model.msg.MelterTrollyLogOutputMessage;
import com.advanz.erp.masters.service.business.IMelterTrollyLogService;
import com.advanz.erp.masters.storage.IStorageMelterTrollyLogDAO;

public class MelterTrollyLogServiceImpl implements IMelterTrollyLogService 
{
    public static String FIND_ALL_MELTER_TROLLY_LOG="findAllMelterTrollLog";
    public static String SEARCH_RECORD_BY_LOGDATE_TROLLY_NO="searchBylogDateTrollyNo";
    public static String ADD_NEW_MELTER_TROLLY_LOG="saveMelterTrollyLog";
    public static String UPDATE_MELTER_TROLLY_LOG="updateMelterTrollyLog";
    public static String FIND_MELTER_TROLLY_LOG_BY_ID="findMelterLogById";
    public static final String DELETE_MELTER_TROLLY_LOG="deleteMelterTrollyLog";
    public String flowId="";
    String ignoreProperties[]= {"servTaxDate,vatDate,cstDate"};
    
    public IAdvanzErpServiceTemplate advanzErpServiceTemplate=new AdvanzErpServiceTemplate();
    private static final Logger logger=LoggerFactory.getLogger(MelterTrollyLogServiceImpl.class);
    @Autowired
    public IStorageMelterTrollyLogDAO iStorageMelterTrollyLogDAO=null;
    public MelterTrollyLogInputMessage melterTrollyLogInputMessage=null;
    public MelterTrollyLogOutputMessage melterTrollyLogOutputMessage=null;
   
    
    @Override
	public MelterTrollyLogOutputMessage findAllMelterTrolly() 
    {
	  flowId=FIND_ALL_MELTER_TROLLY_LOG;
	  advanzErpServiceTemplate.execute(this);
	  return melterTrollyLogOutputMessage;
	}

    @Override
    public MelterTrollyLogOutputMessage SearchByLogDateOrTrollyNo(MelterTrollyLogInputMessage melterTrollyLogInputMessage) {
    	flowId=SEARCH_RECORD_BY_LOGDATE_TROLLY_NO;
    	this.melterTrollyLogInputMessage=melterTrollyLogInputMessage;
    	advanzErpServiceTemplate.execute(this);
    	return melterTrollyLogOutputMessage;
    }

    @Override
    public MelterTrollyLogOutputMessage saveMelterTrollyLog(MelterTrollyLogInputMessage melterTrollyLogInputMessage)
    {
    	flowId=ADD_NEW_MELTER_TROLLY_LOG;
    	this.melterTrollyLogInputMessage=melterTrollyLogInputMessage;
    	advanzErpServiceTemplate.execute(this);
    	return melterTrollyLogOutputMessage;
    	
    }

    @Override
    public MelterTrollyLogOutputMessage updateMelterTrollyLog(MelterTrollyLogInputMessage melterTrollyLogInputMessage) {
    	flowId=UPDATE_MELTER_TROLLY_LOG;
    	this.melterTrollyLogInputMessage=melterTrollyLogInputMessage;
    	advanzErpServiceTemplate.execute(this);
    	return melterTrollyLogOutputMessage;
    }


    @Override
    public MelterTrollyLogOutputMessage findMelterTrollyById(MelterTrollyLogInputMessage melterTrollyLogInputMessage) {
    	flowId=FIND_MELTER_TROLLY_LOG_BY_ID;
    	this.melterTrollyLogInputMessage=melterTrollyLogInputMessage;
    	advanzErpServiceTemplate.execute(this);
    	return melterTrollyLogOutputMessage;
    }
    @Override
    public MelterTrollyLogOutputMessage deleteMelterTrolly(MelterTrollyLogInputMessage melterTrollyLogInputMessage) {
    	flowId=DELETE_MELTER_TROLLY_LOG;
    	this.melterTrollyLogInputMessage=melterTrollyLogInputMessage;
    	advanzErpServiceTemplate.execute(this);
    	return melterTrollyLogOutputMessage;
    }

    @Override
	public boolean validateInput() {
    	if(FIND_ALL_MELTER_TROLLY_LOG.equals(flowId))
    	{
    		return  true;
    	}
    	else if(SEARCH_RECORD_BY_LOGDATE_TROLLY_NO.equals(flowId))
    	{
    		return true;
    	}
    	else if(ADD_NEW_MELTER_TROLLY_LOG.equals(flowId))
    	{
    		return true;
    	}
    	else if(UPDATE_MELTER_TROLLY_LOG.contains(flowId))
    	{
    		return true;
    	}
       else if (FIND_MELTER_TROLLY_LOG_BY_ID.endsWith(flowId)) {
    	   return true;	
		}
    	
       else if (DELETE_MELTER_TROLLY_LOG.endsWith(flowId)) {
    	   return true;	
		}

    	return false;
	}

	@Override
	public void performBusinessLogic() {
	if(FIND_ALL_MELTER_TROLLY_LOG.equals(flowId))
     {
	   List<MelterTrollyLogEntity> entityList=iStorageMelterTrollyLogDAO.load();
	   melterTrollyLogOutputMessage=new MelterTrollyLogOutputMessage();
	   List<MelterTrollyLogDTO> list=convertMelterTrollyLogDTOToMelterTrollyLogEntity(entityList);
       melterTrollyLogOutputMessage.setMelterTrollyLogDTOList(list);
	  }
	
	
	else if(SEARCH_RECORD_BY_LOGDATE_TROLLY_NO.equals(flowId))
	{
	MelterTrollyLogDTO melterTrollyLogDTO=new MelterTrollyLogDTO(); 
	if(melterTrollyLogInputMessage!=null)
	  {
	   melterTrollyLogDTO=melterTrollyLogInputMessage.getMelterTrollyLogDTO();
	   List<MelterTrollyLogEntity> list=iStorageMelterTrollyLogDAO.search(melterTrollyLogDTO.getFromDate(),melterTrollyLogDTO.getToDate(),melterTrollyLogDTO.getTrollyNumber());
	   melterTrollyLogOutputMessage=new MelterTrollyLogOutputMessage();
	   if(list!=null)
	    {
	    List<MelterTrollyLogDTO> resultList=convertMelterTrollyLogDTOToMelterTrollyLogEntity(list);
	    melterTrollyLogOutputMessage.setMelterTrollyLogDTOList(resultList);
	   } 
	  }
	 }
	
	
  else if(ADD_NEW_MELTER_TROLLY_LOG.equals(flowId))
	{
	
	try
	 {
	  MelterTrollyLogDTO melterTrollyLogDTO=new MelterTrollyLogDTO();
	  MelterTrollyLogEntity melterTrollyLogEntity=new MelterTrollyLogEntity();
	  melterTrollyLogOutputMessage=new MelterTrollyLogOutputMessage();
	  if(melterTrollyLogInputMessage!=null)
	    {
    	  melterTrollyLogDTO=melterTrollyLogInputMessage.getMelterTrollyLogDTO();
		  BeanUtils.copyProperties(melterTrollyLogDTO, melterTrollyLogEntity); 
	    }
	  List<MelterTrollyLogEntity> list=iStorageMelterTrollyLogDAO.checkDuplicateTrolly(melterTrollyLogEntity.getLogDate(), melterTrollyLogEntity.getLogTime(), melterTrollyLogEntity.getTrollyNumber());
	   
	   logger.info(flowId+":"+list);
	   
	   if(list!=null && list.size()>0){
	
		   ErrorDTO errorDTO=new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed.");
		   ErrorListDTO errorListDTO=new ErrorListDTO();
		   errorListDTO.addError(errorDTO);
		   melterTrollyLogOutputMessage.setErrorListDTO(errorListDTO);
	    }
	  else
	   {	
		  melterTrollyLogOutputMessage=new MelterTrollyLogOutputMessage();
		 melterTrollyLogOutputMessage.setErrorListDTO(null); 
		 iStorageMelterTrollyLogDAO.create(melterTrollyLogEntity);   
	    }
	  }
	 catch (Exception e) {
		  e.getMessage();
	  }
	}
	  
   else if (UPDATE_MELTER_TROLLY_LOG.equals(flowId)) 
	{
	  MelterTrollyLogDTO melterTrollyLogDTO=new MelterTrollyLogDTO();
	  MelterTrollyLogEntity melterTrollyLogEntity=new MelterTrollyLogEntity();
	  if(melterTrollyLogInputMessage!=null)
	  {
		melterTrollyLogDTO=melterTrollyLogInputMessage.getMelterTrollyLogDTO();
		BeanUtils.copyProperties(melterTrollyLogDTO, melterTrollyLogEntity);
	  }
	  List<MelterTrollyLogEntity> list=iStorageMelterTrollyLogDAO.checkDuplicateTrolly(melterTrollyLogEntity.getLogDate(), melterTrollyLogEntity.getLogTime(), melterTrollyLogEntity.getTrollyNumber());
	   
	   logger.info(flowId+":"+list);
	   
	   if(list!=null && list.size()>0 && !list.get(0).getSno().equals(melterTrollyLogEntity.getSno())){
	
		   ErrorDTO errorDTO=new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed.");
		   ErrorListDTO errorListDTO=new ErrorListDTO();
		   errorListDTO.addError(errorDTO);
		   melterTrollyLogOutputMessage=new MelterTrollyLogOutputMessage();
		   melterTrollyLogOutputMessage.setErrorListDTO(errorListDTO);
	    }
	  else
	  {
		 melterTrollyLogOutputMessage=new MelterTrollyLogOutputMessage();
		iStorageMelterTrollyLogDAO.update(melterTrollyLogEntity);
		melterTrollyLogOutputMessage.setErrorListDTO(null);
	  }
	 }
	
	else if(FIND_MELTER_TROLLY_LOG_BY_ID.equals(flowId))
	{
		
 	 MelterTrollyLogEntity melterTrollyLogEntity=new MelterTrollyLogEntity();	
	 MelterTrollyLogDTO melterTrollyLogDTO=new MelterTrollyLogDTO();
	 if(melterTrollyLogInputMessage!=null)
	 {
	  melterTrollyLogDTO=melterTrollyLogInputMessage.getMelterTrollyLogDTO();
	  BeanUtils.copyProperties(melterTrollyLogDTO, melterTrollyLogEntity);
	 }
	 List<MelterTrollyLogEntity> list=iStorageMelterTrollyLogDAO.findBySno(melterTrollyLogEntity.getSno());
	 if(melterTrollyLogEntity!=null)
	 {
		 melterTrollyLogOutputMessage=new MelterTrollyLogOutputMessage();
	   List<MelterTrollyLogDTO> melterTrollyLogDTOList=convertMelterTrollyLogDTOToMelterTrollyLogEntity(list);
	   melterTrollyLogOutputMessage.setMelterTrollyLogDTOList(melterTrollyLogDTOList);
	  }
	 }
	
	else if(DELETE_MELTER_TROLLY_LOG.equals(flowId))
	{
		 MelterTrollyLogEntity melterTrollyLogEntity=new MelterTrollyLogEntity();	
		 MelterTrollyLogDTO melterTrollyLogDTO=new MelterTrollyLogDTO();
		 if(melterTrollyLogInputMessage!=null)
		 {
		  melterTrollyLogDTO=melterTrollyLogInputMessage.getMelterTrollyLogDTO();
		  BeanUtils.copyProperties(melterTrollyLogDTO, melterTrollyLogEntity);
		 }
		iStorageMelterTrollyLogDAO.delete(melterTrollyLogEntity);  
	  }
	}

	
	@Override
	public void formatOutput() {
	if(FIND_ALL_MELTER_TROLLY_LOG.equals(flowId))
	{}	  
	else if(SEARCH_RECORD_BY_LOGDATE_TROLLY_NO.equals(flowId))
	{}
	else if(ADD_NEW_MELTER_TROLLY_LOG.equals(flowId))
	{}
	else if(UPDATE_MELTER_TROLLY_LOG.equals(flowId))
	{}
	else if(FIND_MELTER_TROLLY_LOG_BY_ID.equals(flowId)){
	}
	else if(DELETE_MELTER_TROLLY_LOG.equals(flowId)){
	}
	}
	
  public List<MelterTrollyLogDTO> convertMelterTrollyLogDTOToMelterTrollyLogEntity(List<MelterTrollyLogEntity> mEntities)
   {
	 List<MelterTrollyLogDTO> melterTrollyLogDTOList=new ArrayList<MelterTrollyLogDTO>();
	 MelterTrollyLogDTO melterTrollyLogDTO;
	 for(MelterTrollyLogEntity entity:mEntities)
	{
		 melterTrollyLogDTO=new MelterTrollyLogDTO();
	 try
	 {
	  BeanUtils.copyProperties(entity, melterTrollyLogDTO,ignoreProperties); 
	 }
	  catch (Exception e) {
	   e.printStackTrace();
	  }
  	melterTrollyLogDTOList.add(melterTrollyLogDTO);
	}  
	  return melterTrollyLogDTOList;
   }

}
