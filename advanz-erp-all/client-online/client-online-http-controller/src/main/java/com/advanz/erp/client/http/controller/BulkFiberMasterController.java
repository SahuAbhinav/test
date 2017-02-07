package com.advanz.erp.client.http.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.BlanketProductionMasterForm;
import com.advanz.erp.client.http.controller.form.BulkFiberMasterForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.BlanketProductionDetailLeftDTO;
import com.advanz.erp.masters.model.BlanketProductionDetailRightDTO;
import com.advanz.erp.masters.model.BlanketProductionMasterDTO;
import com.advanz.erp.masters.model.BulkFiberDetailDTO;
import com.advanz.erp.masters.model.BulkFiberMasterDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.criteria.BlanketProductionSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterInputMessage;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterOutputMessage;
import com.advanz.erp.masters.model.msg.BulkFiberMasterInputMessage;
import com.advanz.erp.masters.model.msg.BulkFiberMasterOutputMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.service.business.IBlanketProductionMasterService;
import com.advanz.erp.masters.service.business.IBulkFiberMasterService;
import com.advanz.erp.masters.service.business.IMastersService;

@Controller
@SessionAttributes({"shiftMastersList","gradeMastersList"})
public class BulkFiberMasterController extends BaseController{
private static final Logger logger = LoggerFactory.getLogger(BulkFiberMasterController.class);
    
    @Autowired
    private IBulkFiberMasterService bulkFiberMasterMasterService;
    
    @Autowired
    private IMastersService mastersService;
    
    @RequestMapping(value = "/new_bulkFiber", method = RequestMethod.GET)
	public ModelAndView newBlanketProduction(ModelMap model) {    	
    	ModelAndView mav=new ModelAndView("bulkFiber-entry");
    	BulkFiberMasterForm bpmForm=new BulkFiberMasterForm();
    	BulkFiberMasterDTO bpmDTO=new BulkFiberMasterDTO();
    	
    	List<BulkFiberDetailDTO> bulkFiberDeatilList=new ArrayList<BulkFiberDetailDTO>();
    	bulkFiberDeatilList.add(new BulkFiberDetailDTO());        	
    	bpmDTO.setBulkFiberDetailDTOList(bulkFiberDeatilList);
    	
    	
    	bpmDTO.setBulkFiberDate(new Date());
    	bpmForm.setBulkFiberMasterDTO(bpmDTO);
    	mav.addObject("shiftMastersList", getShiftMastersList());
    	mav.addObject("gradeMastersList", getGradeMastersList());
    	mav.addObject("bulkFiberMasterForm", bpmForm);
    	return mav;
    }
    
    @RequestMapping(value="/save_bulkFiber")
    public String saveBulkFiber(@ModelAttribute("bulkFiberMasterForm")BulkFiberMasterForm bulkFiberMasterForm,Model model){
    	List<BulkFiberDetailDTO>bpdLeftDTOList=	bulkFiberMasterForm.getBulkFiberMasterDTO().getBulkFiberDetailDTOList();
    	
    	
    	if((bpdLeftDTOList==null || bpdLeftDTOList.size()==0)){
    		ErrorDTO error=new ErrorDTO();
			error.setErrorMsg("Error : Without  Record");
			model.addAttribute("errors", error);	
			//add a default empty row
			if(bpdLeftDTOList==null){
				bpdLeftDTOList=new ArrayList<BulkFiberDetailDTO>();  
				bulkFiberMasterForm.getBulkFiberMasterDTO().setBulkFiberDetailDTOList(bpdLeftDTOList);
			}
			
			
			if(bpdLeftDTOList.size()==0)
			bpdLeftDTOList.add(new BulkFiberDetailDTO());
			return "bulkFiber-entry";
    	}    	
    	String succ="";
    	BulkFiberMasterInputMessage bulkFiberMasterInputMessage  =new BulkFiberMasterInputMessage ();
    	BulkFiberMasterDTO bulkFiberMasterDTO= bulkFiberMasterForm.getBulkFiberMasterDTO();
    	
    	bulkFiberMasterInputMessage.setBulkFiberMasterDTO(bulkFiberMasterDTO);
    	BulkFiberMasterOutputMessage bulkFiberMasterOutputMessage=null;
       	Integer id=bulkFiberMasterForm.getBulkFiberMasterDTO().getBulkFiberId();
       	if(id!=null && !id.equals(0)){
       		bulkFiberMasterDTO.setModifiedUserId(getCreatedUserId());
       		bulkFiberMasterOutputMessage=bulkFiberMasterMasterService.updateBulkFiberMaster(bulkFiberMasterInputMessage);
       		succ="Up";
       		ErrorListDTO errorListDTO=bulkFiberMasterOutputMessage.getErrorListDTO();
       		if(errorListDTO!=null && errorListDTO.hasErrors()){
       			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
    			logger.info(" adding Error ");
    			model.addAttribute("errors",errorDTO);
    			if(bpdLeftDTOList==null){
    				bpdLeftDTOList=new ArrayList<BulkFiberDetailDTO>();  
    				bulkFiberMasterForm.getBulkFiberMasterDTO().setBulkFiberDetailDTOList(bpdLeftDTOList);
    			}
    			

    			if(bpdLeftDTOList.size()==0)
    			bpdLeftDTOList.add(new BulkFiberDetailDTO());
    			
    			model.addAttribute("opr", "E");
    			return "bulkFiber-entry";
    		}
       	}else
    	{
       		bulkFiberMasterDTO.setCreatedUserId(getCreatedUserId());
       		bulkFiberMasterOutputMessage=bulkFiberMasterMasterService.createBulkFiberMaster(bulkFiberMasterInputMessage);
       		succ="Ad";
       		ErrorListDTO errorListDTO=bulkFiberMasterOutputMessage.getErrorListDTO();
       		if(errorListDTO!=null && errorListDTO.hasErrors()){
       			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
    			logger.info(" adding Error ");
    			model.addAttribute("errors",errorDTO);
    			if(bpdLeftDTOList==null){
    				bpdLeftDTOList=new ArrayList<BulkFiberDetailDTO>();  
    				bulkFiberMasterForm.getBulkFiberMasterDTO().setBulkFiberDetailDTOList(bpdLeftDTOList);
    			}
    			

    			if(bpdLeftDTOList.size()==0)
    			bpdLeftDTOList.add(new BulkFiberDetailDTO());
    			
    			return "bulkFiber-entry";
    		}
    	}
       	
       	model.addAttribute("succ",succ);
    	return "redirect:/get_bulkFiber_list";
    }
    
    @RequestMapping(value = "/remove_bulkFiber", method = RequestMethod.GET)
	public String removeBlanketProduction(@RequestParam("id") Integer id,Model model) {
		logger.info("Removing..........BulkFiber = " + id);
		BulkFiberMasterOutputMessage bulkFiberMasterOutputMessage = null;
		if (id!=null && !id.equals(0)) {
			BulkFiberMasterInputMessage bulkFiberMasterInputMessage = new BulkFiberMasterInputMessage();
			BulkFiberMasterDTO bulkFiberMasterDTO = new BulkFiberMasterDTO();
			bulkFiberMasterDTO.setBulkFiberId(id);
			bulkFiberMasterDTO.setModifiedUserId(getCreatedUserId());
			bulkFiberMasterInputMessage.setBulkFiberMasterDTO(bulkFiberMasterDTO);
			bulkFiberMasterOutputMessage = bulkFiberMasterMasterService.deleteBulkFiberMaster(bulkFiberMasterInputMessage);
		}
		String succ="Dl";
		model.addAttribute("succ",succ);
		return  "redirect:/get_bulkFiber_list";
	}
    
    @RequestMapping(value="/get_bulkFiber_list")
    public ModelAndView getBulkFiberList(@ModelAttribute("bulkFiberMasterForm")BulkFiberMasterForm bulkFiberMasterForm,Model model,@RequestParam(value="menuId",required=false) String menuId,HttpSession session,@RequestParam(value="opr",required=false) String opr ,@RequestParam(value="next",required=false) Integer next){
    	if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
    	BulkFiberMasterInputMessage bulkFiberMasterInputMessage=new BulkFiberMasterInputMessage();
    	bulkFiberMasterInputMessage.setBulkFiberMasterDTO(bulkFiberMasterForm.getBulkFiberMasterDTO());
    	BulkFiberMasterOutputMessage bulkFiberMasterOutputMessage=null;
    	BulkFiberMasterDTO bulkFiberMasterDTO = new BulkFiberMasterDTO();
    	if("search".equals(opr)){
    		bulkFiberMasterOutputMessage=bulkFiberMasterMasterService.search(bulkFiberMasterInputMessage);
    	}else{
    	 //bulkFiberMasterOutputMessage=bulkFiberMasterMasterService.findAllBulkFiberMasters();
    		if(next==null ||next<0)
    		{
    		next=0;
    		bulkFiberMasterDTO.setNext(next);
    		bulkFiberMasterInputMessage.setBulkFiberMasterDTO(bulkFiberMasterDTO);
    		bulkFiberMasterOutputMessage=bulkFiberMasterMasterService.getListWithPagination(bulkFiberMasterInputMessage);
    		}else{
        		bulkFiberMasterDTO.setNext(next);
        		bulkFiberMasterInputMessage.setBulkFiberMasterDTO(bulkFiberMasterDTO);
        		bulkFiberMasterOutputMessage=bulkFiberMasterMasterService.getListWithPagination(bulkFiberMasterInputMessage);
    		}
    		
    		
    		}
    	 ModelAndView mav=new ModelAndView("bulkFiber-list");
    	 mav.addObject("gradeList", getGradeMastersList());
    	
    	List list=null;
		try {
			list = (ArrayList<BulkFiberMasterDTO>) bulkFiberMasterOutputMessage.getBulkFiberMasterDTOList();
		} catch (Exception e) {
		}
    	
    	
    	mav.addObject("bpmList",list);
    	mav.addObject("bulkFiberMasterForm",bulkFiberMasterForm);
    	
    	String succ="Blk";
    	if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}
		  
		
    	return mav;
    }
    @RequestMapping(value = "/get_bulkFiber", method = RequestMethod.GET)
	public 	ModelAndView getBulkFiberData(@RequestParam("id") Integer id,@RequestParam("opr")String opr,ModelMap model) {
    	BulkFiberMasterForm bulkFiberMasterForm = new BulkFiberMasterForm();
			logger.info("Opr : " + opr);

			BulkFiberMasterOutputMessage bulkFiberMasterOutMessage = null;
		if (id!=null && !id.equals(0)) {		
			BulkFiberMasterInputMessage bulkFiberMasterInputMessage = new BulkFiberMasterInputMessage();
			BulkFiberMasterDTO bulkFiberMasterDTO = new BulkFiberMasterDTO();
			bulkFiberMasterDTO.setBulkFiberId(id);
			bulkFiberMasterInputMessage.setBulkFiberMasterDTO(bulkFiberMasterDTO);
			bulkFiberMasterOutMessage = bulkFiberMasterMasterService.findBulkFiberMasterById(bulkFiberMasterInputMessage);
			ArrayList<BulkFiberMasterDTO> list = (ArrayList<BulkFiberMasterDTO>) bulkFiberMasterOutMessage
					.getBulkFiberMasterDTOList();
			if (list!=null && list.size()>0) {
				bulkFiberMasterDTO=list.get(0);
			List<BulkFiberDetailDTO>bpdLeftList=bulkFiberMasterDTO.getBulkFiberDetailDTOList();
			if(bpdLeftList.size()==0){
				bpdLeftList.add(new BulkFiberDetailDTO());
			}
			
			
			bulkFiberMasterForm.setBulkFiberMasterDTO(bulkFiberMasterDTO);
			}
		}

		model.put("bulkFiberMasterForm", bulkFiberMasterForm);
		model.put("shiftMastersList", getShiftMastersList());
		model.put("gradeMastersList", getGradeMastersList());
		model.put("opr", opr);
		ModelAndView mav = new ModelAndView("bulkFiber-entry");
		// mav.addObject("partyList",partyList());
		return mav;	
	}
    
    
    
    
    @RequestMapping(value="add_row_in_bf")
    	public ModelAndView addRow(@ModelAttribute("bulkFiberMasterForm")BulkFiberMasterForm bulkFiberMasterForm,@RequestParam("rt")String recordType){
    	BulkFiberMasterDTO bulkFiberMasterDTO=	bulkFiberMasterForm.getBulkFiberMasterDTO();
    	if("L".equals(recordType)){
    	List<BulkFiberDetailDTO> bfdDTOList=bulkFiberMasterDTO.getBulkFiberDetailDTOList();
    		if(bfdDTOList==null){
    			bfdDTOList=new ArrayList<BulkFiberDetailDTO>();
    			bulkFiberMasterDTO.setBulkFiberDetailDTOList(bfdDTOList);
    		}    		
    		bfdDTOList.add(new BulkFiberDetailDTO());
    	}
     	
    	ModelAndView mav=new ModelAndView("bulkFiber-entry");
    	return mav;
    }
    @RequestMapping(value="remove_row_from_bf", method=RequestMethod.POST)
	public ModelAndView removeRow(@ModelAttribute("bulkFiberMasterForm")BulkFiberMasterForm bulkFiberMasterForm,@RequestParam("rs")String removeStr){
    	if(removeStr!=null){
    		int index=-1;
    		try{
    			index=Integer.parseInt(removeStr.substring(1));
    		}catch(Exception e){
    			logger.error(e.getMessage());
    		}
    		BulkFiberMasterDTO bulkFiberMasterDTO=	bulkFiberMasterForm.getBulkFiberMasterDTO();
    			if(removeStr.startsWith("L") && index>=0){    				
    				List<BulkFiberDetailDTO> bfdDTOList=bulkFiberMasterDTO.getBulkFiberDetailDTOList();
    				if(bfdDTOList!=null && bfdDTOList.size()>index){
    					bfdDTOList.remove(index);			
    				}   
    			}
    			
    		
    	}
	ModelAndView mav=new ModelAndView("bulkFiber-entry");
	mav.addObject("bulkFiberMasterForm",bulkFiberMasterForm);
	return mav;
}
    private List<MastersDTO> getShiftMastersList(){
    	MastersInputMessage mastersInputMessage =new MastersInputMessage();
    	//formid=11   --> Shift Master
    	mastersInputMessage.setFormId(11);
    	MastersOutputMessage mastersOutputMessage=	mastersService.findFormById(mastersInputMessage);
    	return mastersOutputMessage.getMastersDTOList();
    }
    private List<MastersDTO> getGradeMastersList(){
    	MastersInputMessage mastersInputMessage =new MastersInputMessage();
    	//formid=16   --> Item Grade
    	mastersInputMessage.setFormId(16);
    	MastersOutputMessage mastersOutputMessage=	mastersService.findFormById(mastersInputMessage);
    	return mastersOutputMessage.getMastersDTOList();
    }
    
    
}
