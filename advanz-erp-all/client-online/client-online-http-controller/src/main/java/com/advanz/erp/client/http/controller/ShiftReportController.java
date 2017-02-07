package com.advanz.erp.client.http.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.advanz.erp.ControllerUtil;
import com.advanz.erp.client.http.controller.form.ShiftReportMasterForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.ShiftConsumedDetailDTO;
import com.advanz.erp.masters.model.ShiftEngInterruptionDetailDTO;
import com.advanz.erp.masters.model.ShiftReportMasterDTO;
import com.advanz.erp.masters.model.ShiftSpinInterruptionDetailDTO;
import com.advanz.erp.masters.model.criteria.ItemSearchCriteriaDTO;
import com.advanz.erp.masters.model.criteria.ShiftReportMasterSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.ItemGroupFlagOutMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.model.msg.ShiftReportMasterInputMessage;
import com.advanz.erp.masters.model.msg.ShiftReportMasterOutputMessage;
import com.advanz.erp.masters.service.business.IItemGroupFlagService;
import com.advanz.erp.masters.service.business.IItemGroupService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IShiftReportMasterService;
@Controller
@SessionAttributes({ "shiftReportMasterForm","shift" })
public class ShiftReportController extends BaseController{

	private static final Logger logger = LoggerFactory
			.getLogger(ShiftReportController.class);
	@Autowired
	DataSource dataSource;
	@Autowired
	public IShiftReportMasterService shiftReportMasterService;
	
	@Autowired
	public IMastersService mastersService;
	
	@Autowired
	public IItemGroupService itemGroupService;
	
	@Autowired
	public IItemGroupFlagService itemGroupFlagService;
	
	@Autowired
	public IItemService itemService;
	
	
	@RequestMapping(value = "/get_shift_report_list")
	
		public  ModelAndView searchShiftMaster(@ModelAttribute("searchCriteria") ShiftReportMasterSearchCriteriaDTO searchCriteria ,@RequestParam(value="operation",required=false) String operation,@RequestParam(value="next",required=false) Integer next) {
			List<ShiftReportMasterDTO> list = new ArrayList<ShiftReportMasterDTO>();
			
			ShiftReportMasterOutputMessage shiftReportMasterOutputMessage = null;
				ShiftReportMasterInputMessage shiftReportMasterInputMessage = new ShiftReportMasterInputMessage();
				if("Search".equalsIgnoreCase(operation)){
				shiftReportMasterInputMessage.setSearchCriteria(searchCriteria);
				shiftReportMasterOutputMessage = shiftReportMasterService.search(shiftReportMasterInputMessage);
				}else{
					shiftReportMasterInputMessage = new ShiftReportMasterInputMessage();
					if(next==null ||next<0)
					{
					next=0;
					shiftReportMasterInputMessage.setNext(next);
					shiftReportMasterOutputMessage = shiftReportMasterService.findIShiftReportPagination(shiftReportMasterInputMessage);
					}
					else
					{
						shiftReportMasterInputMessage.setNext(next);
						shiftReportMasterOutputMessage = shiftReportMasterService.findIShiftReportPagination(shiftReportMasterInputMessage);
					}
				}
				searchCriteria.setNext(next);
				searchCriteria.setPrevious(next);
				//	finishedGoodsOutputMessage = finishedGoodsMasterService.findAllFinishedGoodsMasters();
				list = (ArrayList<ShiftReportMasterDTO>) shiftReportMasterOutputMessage.getShiftReportMasterDTOList();	
				ModelAndView mav=new ModelAndView("shiftReport-list");
				mav.addObject("shiftList", list);
				mav.addObject("searchCriteria", searchCriteria);
				
			//	mav.addObject("finishedGoodsSearchCriteria",searchCriteria);
				String succ="Blk";
				if(list.equals(null) || list.size()==0)
				{
					mav.addObject("succ", succ);
				}
			return mav;
		}
	
	@RequestMapping(value = "/shift_add", method = RequestMethod.GET)
	public ModelAndView addShift(ModelMap model) {
		ShiftReportMasterForm shiftReportMasterForm = new ShiftReportMasterForm();
		ShiftReportMasterDTO shiftReportMasterDTO = new ShiftReportMasterDTO();	
		ModelAndView mav = new ModelAndView("shiftReport-detail");
		
		ShiftReportMasterOutputMessage shiftReportMasterOutputMessage = null;
		ShiftReportMasterInputMessage shiftReportMasterInputMessage = new ShiftReportMasterInputMessage();
	
		
		
		List<ShiftEngInterruptionDetailDTO> shiftEnglist = new ArrayList<ShiftEngInterruptionDetailDTO>();
		shiftEnglist.add(new  ShiftEngInterruptionDetailDTO());
		shiftReportMasterDTO.setShiftEngInterruptionDetailDTOList(shiftEnglist);
		
		List<ShiftSpinInterruptionDetailDTO> shiftSpinlist = new ArrayList<ShiftSpinInterruptionDetailDTO>();
		shiftSpinlist.add(new ShiftSpinInterruptionDetailDTO());
		shiftReportMasterDTO.setShiftSpinInterruptionDetailDTOList(shiftSpinlist);
		shiftReportMasterForm.setShiftReportMasterDTO(shiftReportMasterDTO);
		
		shiftReportMasterOutputMessage=shiftReportMasterService.getLastShiftDate(shiftReportMasterInputMessage);
		Timestamp timestamp= shiftReportMasterOutputMessage.getLastShiftDate();
		//System.out.println("Last Finished good time"+new Date(timestamp.getTime()));
		SimpleDateFormat ft =new SimpleDateFormat ("yyyy,MM,dd");
		shiftReportMasterForm.setLastShiftDate(ft.format(new Date(timestamp.getTime())));
		
		
		
		//shiftReportMasterForm.setShiftReportMasterDTO(shiftReportMasterDTO);
		model.put("shiftReportMasterForm", shiftReportMasterForm);
		model.put("shift", shiftList());
		model.put("itemGroupList", itemGroupList());
		return mav;
	}
	@RequestMapping(value = "/show_shift_form2")
	public ModelAndView addShiftForm(
			@ModelAttribute("shiftReportMasterForm") ShiftReportMasterForm shiftReportMasterForm, Model model) {
		ModelAndView mav = null;
		ShiftReportMasterOutputMessage shiftReportMasterOutputMessage = null;
		ShiftReportMasterInputMessage shiftReportMasterInputMessage = new ShiftReportMasterInputMessage();
	
		if (shiftReportMasterForm == null) {
			shiftReportMasterForm = new ShiftReportMasterForm();
		}
		ShiftReportMasterDTO shiftReportMasterDTO = new ShiftReportMasterDTO();	
		try {
			new ControllerUtil().copyObjectWithoutNull(shiftReportMasterForm.getShiftReportMasterDTO(), shiftReportMasterDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (shiftReportMasterDTO.getUpdate() != null && shiftReportMasterDTO.getUpdate().equals("update")) {
			mav = new ModelAndView("shiftReport-edit");
		} else {
			mav = new ModelAndView("shiftReport-detail");
		}
		try{
		if(shiftReportMasterDTO!=null){
			List<ShiftConsumedDetailDTO> list= shiftReportMasterDTO.getShiftConsumedDetailDTOList();
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
				ShiftConsumedDetailDTO consumedDetailDTO=list.get(i);
				if(consumedDetailDTO.getItemDTO().getItemId()!=null)
				consumedDetailDTO.getItemDTO().getItemId();
				Integer itemId=consumedDetailDTO.getItemDTO().getItemId();
				
			ItemInputMessage itemInputMessage = new ItemInputMessage();
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setItemId(itemId);
			itemInputMessage.setItemDTO(itemDTO);
			List<ItemDTO> itemDtoList = itemService.findItemById(
					itemInputMessage).getItemDTOList();
			if(itemDtoList!=null && itemDtoList.size()>0){}
			itemDTO=itemDtoList.get(0);
			
			
			MastersDTO mastersDTO = itemDTO.getMasterUnit();
			consumedDetailDTO.setMeasurementUnitId(mastersDTO.getMastersId());
			consumedDetailDTO.setMeasurementUnitName(mastersDTO.getName());
			
			consumedDetailDTO.setItemName(itemDTO.getItemName());
			consumedDetailDTO.setItemId(itemDTO.getItemId());
			
		      }
			}}
		
		shiftReportMasterOutputMessage=shiftReportMasterService.getLastShiftDate(shiftReportMasterInputMessage);
		Timestamp timestamp= shiftReportMasterOutputMessage.getLastShiftDate();
		//System.out.println("Last Finished good time"+new Date(timestamp.getTime()));
		SimpleDateFormat ft =new SimpleDateFormat ("yyyy,MM,dd");
		shiftReportMasterForm.setLastShiftDate(ft.format(new Date(timestamp.getTime())));
		
		}catch (Exception e) {}
		
		shiftReportMasterForm.setShiftReportMasterDTO(shiftReportMasterDTO);
		mav.addObject("shiftReportMasterForm", shiftReportMasterForm);
		model.addAttribute("step2", "2");
	return mav;
	}
	
	@RequestMapping(value = "/show_item_list_shift")
	public ModelAndView showItemSelectionForm(@ModelAttribute("shiftReportMasterForm") ShiftReportMasterForm shiftReportMasterForm,
			@RequestParam(value="next",required=false) Integer next,
			@ModelAttribute("searchCriteria") ItemSearchCriteriaDTO searchCriteria) {
		ModelAndView mav = new ModelAndView("item-list-for-shift");
	
		Integer flagId=shiftReportMasterForm.getShiftReportMasterDTO().getShiftConsumedDetailDTO().getItemGroupFlagId();
		ItemOutMessage itemOutMessage = null;  //itemService.findAllItem();
		ItemInputMessage itemInputMessage = new ItemInputMessage();
		ItemDTO itemDTO = new ItemDTO();
		if(searchCriteria.getInvoiceName()==null && searchCriteria.getItemCode()==null){
			ItemGroupDTO dto=new ItemGroupDTO();
			ItemCategoryDTO categoryDTO=new ItemCategoryDTO();
			dto.setItemGroupFlagId(flagId);
			categoryDTO.setItemGroupDTO(dto);
			itemDTO.setItemCategoryDTO(categoryDTO)	;
			itemDTO.setActiveStatus(1);
		
			if(next==null ||next<0)
			{
				next=0;
				itemDTO.setNext(next);
				itemInputMessage.setItemDTO(itemDTO);
				itemOutMessage= itemService.getListByItemGroupName(itemInputMessage);
			}
			else
			{
				itemDTO.setNext(next);
				itemInputMessage.setItemDTO(itemDTO);
				itemOutMessage= itemService.getListByItemGroupName(itemInputMessage);
			}
			searchCriteria = new ItemSearchCriteriaDTO();
		}
		else
		{ 	
			itemDTO.setInvoiceName(searchCriteria.getInvoiceName());
			itemDTO.setItemCode(searchCriteria.getItemCode());			
			itemInputMessage.setItemDTO(itemDTO);
			itemOutMessage = itemService.findItem(itemInputMessage);
		}
		shiftReportMasterForm.setNext(next);
		shiftReportMasterForm.setPrevious(next);
		ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage
				.getItemDTOList();
		mav.addObject("shiftReportMasterForm", shiftReportMasterForm);
		mav.addObject("searchCriteria", searchCriteria);
		mav.addObject("itemList", list);
		
		return mav;
	}
	
	@RequestMapping(value = "/get_item_by_shift")
	public @ResponseBody
	ItemDTO getItemById(@RequestParam("id") Integer itemId) {
		logger.info("Getting Item for  : " + itemId);
		ItemDTO itemDTO = null;
		ItemOutMessage itemOutMessage = null;
		if (itemId != 0) {
			ItemInputMessage itemInputMessage = new ItemInputMessage();
			itemDTO = new ItemDTO();
			itemDTO.setItemId(itemId);
			itemInputMessage.setItemDTO(itemDTO);
			itemOutMessage = itemService.findItemById(itemInputMessage);
			ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage
					.getItemDTOList();
			if (list.size() == 1) {
				itemDTO = list.get(0);
			}
		}
		return itemDTO;
	}
	
	
	@RequestMapping(value = "/addItemInShift", method = RequestMethod.POST)
	public ModelAndView addItemInShift(
			@ModelAttribute("shiftReportMasterForm") ShiftReportMasterForm shiftReportMasterForm,Model model,
			@RequestParam("itemID") Integer itemId) {
		logger.info("IN addItem()  shiftReportMasterForm-->"
				+ shiftReportMasterForm);
		logger.info("Add Item : " + itemId);
		ShiftReportMasterDTO shiftReportMasterDTO=shiftReportMasterForm.getShiftReportMasterDTO();
		
if(itemId!=null){
		if (!isDuplicateItem(shiftReportMasterDTO,itemId)) {
			ItemInputMessage itemInputMessage = new ItemInputMessage();
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setItemId(itemId);
			itemInputMessage.setItemDTO(itemDTO);
			List<ItemDTO> itemDtoList = itemService.findItemById(
					itemInputMessage).getItemDTOList();
			if (itemDtoList != null && itemDtoList.size() > 0) {				
				itemDTO = itemDtoList.get(0);
				ShiftConsumedDetailDTO shiftConsumedDetailDTO = new ShiftConsumedDetailDTO();
				BeanUtils.copyProperties(itemDTO, shiftConsumedDetailDTO);
				//issueDetailMasterDTO.setTransactionSeries(issueMasterDTO.getTransactionSeries());
				shiftConsumedDetailDTO.setItemName(itemDTO.getInvoiceName());
				try{
					MastersDTO mastersDTO = itemDTO.getMasterUnit();
				shiftConsumedDetailDTO.setMeasurementUnitId(mastersDTO.getMastersId());
				shiftConsumedDetailDTO.setMeasurementUnitName(mastersDTO.getName());
				}catch (Exception e) {e.printStackTrace();
				}
				if (shiftReportMasterDTO.getShiftConsumedDetailDTOList() == null) {
					shiftReportMasterForm.getShiftReportMasterDTO().setShiftConsumedDetailDTOList(new ArrayList<ShiftConsumedDetailDTO>());
					//issueMasterForm.getIssueMasterDTO().setIssueDetailMasterDTOList(new ArrayList<IssueDetailMasterDTO>());					
				}				
				shiftReportMasterDTO.getShiftConsumedDetailDTOList().add(shiftConsumedDetailDTO);
				
			}
		}
		
        }
		
		ModelAndView mav = new ModelAndView(new RedirectView("show_shift_form2#tabs-2"));	
		mav.addObject("shiftReportMasterForm", shiftReportMasterForm);
		//mav.addObject("step2", "2");
		//model.addAttribute("step2", "2");
		
		return mav;
		}
	
	private boolean isDuplicateItem(ShiftReportMasterDTO shiftReportMasterDTO,
			Integer itemId) {

		if (shiftReportMasterDTO != null && shiftReportMasterDTO.getShiftConsumedDetailDTOList()!= null) {
			List<ShiftConsumedDetailDTO> list = shiftReportMasterDTO.getShiftConsumedDetailDTOList();
					
			logger.info("list-----" + list);
			for (ShiftConsumedDetailDTO e : list) {

				if (itemId.equals(e.getItemId()))
					return true;
			}
		}
		return false;
	}
	
	 @RequestMapping(value="add_row_in_shift")
 	public ModelAndView addRow(@ModelAttribute("shiftReportMasterForm") ShiftReportMasterForm shiftReportMasterForm,@RequestParam("operation")String operation){
		 ModelAndView mav = new ModelAndView(new RedirectView("show_shift_form2#tabs-2"));	
 
   ShiftReportMasterDTO shiftReportMasterDTO=  shiftReportMasterForm.getShiftReportMasterDTO();
   if("spin row".equals(operation)){
	List<ShiftSpinInterruptionDetailDTO> shiftSpinInterruptionDetailList=(List<ShiftSpinInterruptionDetailDTO>) shiftReportMasterDTO.getShiftSpinInterruptionDetailDTOList();

	if(shiftSpinInterruptionDetailList==null){
	   shiftSpinInterruptionDetailList = new ArrayList<ShiftSpinInterruptionDetailDTO>();
	   shiftReportMasterDTO.setShiftSpinInterruptionDetailDTOList(shiftSpinInterruptionDetailList);
     }
List<ShiftConsumedDetailDTO> l	=(List<ShiftConsumedDetailDTO>)shiftReportMasterDTO.getShiftConsumedDetailDTOList();
	if(l!=null && l.size()>0){
		for(int i=0;i<l.size();i++){
			ShiftConsumedDetailDTO detailDTO=	 l.get(0);
			//System.out.println(" IIIIIIIIIIIII" + detailDTO.getItemName() +"DDDDDDDDDDDD" +detailDTO.getItemId() );
		}}
	
     shiftSpinInterruptionDetailList.add(new ShiftSpinInterruptionDetailDTO());
    /* shiftReportMasterDTO.setShiftSpinInterruptionDetailDTOList(shiftSpinInterruptionDetailList);
     shiftReportMasterForm.setShiftReportMasterDTO(shiftReportMasterDTO);*/
    
  	//mav.addObject("shiftReportMasterForm", shiftReportMasterForm);
  	return mav ;
   }
  	if("eng row".equals(operation)){
		mav = new ModelAndView(new RedirectView("show_shift_form2#tabs-2"));	
		 
		  
		  
		   List<ShiftEngInterruptionDetailDTO> shiftEngInterruptionDetailList=(List<ShiftEngInterruptionDetailDTO>) shiftReportMasterDTO.getShiftEngInterruptionDetailDTOList();
		   if(shiftEngInterruptionDetailList==null){
			   shiftEngInterruptionDetailList = new ArrayList<ShiftEngInterruptionDetailDTO>();
			   shiftReportMasterDTO.setShiftEngInterruptionDetailDTOList(shiftEngInterruptionDetailList);
		   }
		   shiftEngInterruptionDetailList.add(new ShiftEngInterruptionDetailDTO());
		   /*shiftReportMasterDTO.setShiftEngInterruptionDetailDTOList(shiftEngInterruptionDetailList);
		   shiftReportMasterForm.setShiftReportMasterDTO(shiftReportMasterDTO);*/
		   //mav.addObject("shiftReportMasterForm", shiftReportMasterForm);
		  return mav ;
    }
 	  return mav ;
 }
	
	
	@RequestMapping(value = "/item_remove", method = RequestMethod.GET)
	public 	ModelAndView removeRecord(@ModelAttribute("shiftReportMasterForm") ShiftReportMasterForm shiftReportMasterForm,@RequestParam("index") Integer index,ModelMap model,@RequestParam(value= "operation" ,required=false) String operation) {
		
		ShiftReportMasterOutputMessage shiftReportMasterOutputMessage = null;
		ModelAndView mav = new ModelAndView(new RedirectView("show_shift_form2#tabs-2"));	
		if("spin row".equals(operation)){
			
			if (index!=null ) {
				if(shiftReportMasterForm!=null){
					List<ShiftSpinInterruptionDetailDTO> list=	shiftReportMasterForm.getShiftReportMasterDTO().getShiftSpinInterruptionDetailDTOList();
					list.remove(index.intValue());
					
					ShiftReportMasterDTO shiftReportMasterDTO=	shiftReportMasterForm.getShiftReportMasterDTO();
					shiftReportMasterDTO.setShiftSpinInterruptionDetailDTOList(list);
					shiftReportMasterForm.setShiftReportMasterDTO(shiftReportMasterDTO);
					mav.addObject("shiftReportMasterForm", shiftReportMasterForm);
					model.addAttribute("step2", "2");
			     } }}
		
		if("eng row".equals(operation)){
			if (index!=null ) {
				if(shiftReportMasterForm!=null){
					List<ShiftEngInterruptionDetailDTO> list=	shiftReportMasterForm.getShiftReportMasterDTO().getShiftEngInterruptionDetailDTOList();
					list.remove(index.intValue());
					
					ShiftReportMasterDTO shiftReportMasterDTO=	shiftReportMasterForm.getShiftReportMasterDTO();
					shiftReportMasterDTO.setShiftEngInterruptionDetailDTOList(list);
					shiftReportMasterForm.setShiftReportMasterDTO(shiftReportMasterDTO);
					mav.addObject("shiftReportMasterForm", shiftReportMasterForm);
					model.addAttribute("step2", "2");
			     } }}
		
		if("removeItem".equals(operation)){
		if (index!=null ) {
				if(shiftReportMasterForm!=null){
				List<ShiftConsumedDetailDTO> list=	shiftReportMasterForm.getShiftReportMasterDTO().getShiftConsumedDetailDTOList();
				list.remove(index.intValue());
				
				ShiftReportMasterDTO shiftReportMasterDTO=	shiftReportMasterForm.getShiftReportMasterDTO();
				shiftReportMasterDTO.setShiftConsumedDetailDTOList(list);
				shiftReportMasterForm.setShiftReportMasterDTO(shiftReportMasterDTO);
				mav.addObject("shiftReportMasterForm", shiftReportMasterForm);
				model.addAttribute("step2", "2");
		     }}
		}
		
		
		model.put("shiftReportMasterForm", shiftReportMasterForm);
		return mav;
	}
	
	
	@RequestMapping(value = "/save_shift_form", method = RequestMethod.POST)
	public ModelAndView saveShiftForm(
			@ModelAttribute("shiftReportMasterForm") ShiftReportMasterForm employee, Model model,@RequestParam(value= "operation",required= false)String operation,HttpSession session) {
		logger.info("shiftReportMasterForm -----" + employee.getShiftReportMasterDTO());
		ModelAndView mav =	 new ModelAndView();
		
		if("spin row".equals(operation)){
			mav = new ModelAndView(new RedirectView("show_shift_form2#tabs-2"));	
			 
			   ShiftReportMasterDTO shiftReportMasterDTO=employee.getShiftReportMasterDTO();
			  
			   List<ShiftSpinInterruptionDetailDTO> shiftSpinInterruptionDetailList=(List<ShiftSpinInterruptionDetailDTO>) shiftReportMasterDTO.getShiftSpinInterruptionDetailDTOList();
			   if(shiftSpinInterruptionDetailList==null){
				   shiftSpinInterruptionDetailList = new ArrayList<ShiftSpinInterruptionDetailDTO>();
				   shiftReportMasterDTO.setShiftSpinInterruptionDetailDTOList(shiftSpinInterruptionDetailList);
			   }
			     shiftSpinInterruptionDetailList.add(new ShiftSpinInterruptionDetailDTO());
			   
			   shiftReportMasterDTO.setShiftSpinInterruptionDetailDTOList(shiftSpinInterruptionDetailList);
					 
			     employee.setShiftReportMasterDTO(shiftReportMasterDTO);

			  	
			  	mav.addObject("shiftReportMasterForm", employee);
			 	return mav ;
		             }
		
		if("eng row".equals(operation)){
			mav = new ModelAndView(new RedirectView("show_shift_form2#tabs-2"));	
			 
			   ShiftReportMasterDTO shiftReportMasterDTO=  employee.getShiftReportMasterDTO();
			  
			   List<ShiftEngInterruptionDetailDTO> shiftEngInterruptionDetailList=(List<ShiftEngInterruptionDetailDTO>) shiftReportMasterDTO.getShiftEngInterruptionDetailDTOList();
			   if(shiftEngInterruptionDetailList==null){
				   shiftEngInterruptionDetailList = new ArrayList<ShiftEngInterruptionDetailDTO>();
				   shiftReportMasterDTO.setShiftEngInterruptionDetailDTOList(shiftEngInterruptionDetailList);
			   }
			     shiftEngInterruptionDetailList.add(new ShiftEngInterruptionDetailDTO());
			   
			   shiftReportMasterDTO.setShiftEngInterruptionDetailDTOList(shiftEngInterruptionDetailList);
					 
			     employee.setShiftReportMasterDTO(shiftReportMasterDTO);

			  	
			  	mav.addObject("shiftReportMasterForm", employee);
			 	return mav ;
		             }
		
		if (employee == null) {
			employee = new ShiftReportMasterForm();
		}
		ShiftReportMasterInputMessage shiftReportMasterInputMessage =new ShiftReportMasterInputMessage();
		ShiftReportMasterDTO shiftReportMasterDTO = new ShiftReportMasterDTO();	
		try {
			new ControllerUtil().copyObjectWithoutNull(employee.getShiftReportMasterDTO(), shiftReportMasterDTO);
			//System.out.println("ssssssssssssssssss:::::::::"+shiftReportMasterDTO.getSpiningTime());
			//System.out.println("ssssssssssssssssss:::::::::"+shiftReportMasterDTO.getPoutingTime());
			shiftReportMasterInputMessage.setShiftReportMasterDTO(shiftReportMasterDTO);
			
			 List<ShiftEngInterruptionDetailDTO> shiftEngInteList = (List<ShiftEngInterruptionDetailDTO>)shiftReportMasterDTO.getShiftEngInterruptionDetailDTOList();
			if(shiftEngInteList!=null){
				ShiftEngInterruptionDetailDTO detailDTO= shiftEngInteList.get(0);
				
			
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	/*	if(!"Delete".equals(operation)){
		ShiftReportMasterOutputMessage masterOutputMessage = shiftReportMasterService.findAllShiftReportMasters();
		ArrayList<ShiftReportMasterDTO> shiftReportMasertList =(ArrayList<ShiftReportMasterDTO>)masterOutputMessage.getShiftReportMasterDTOList();
		String error =null;
		for(int i=0;i<shiftReportMasertList.size();i++){
			ShiftReportMasterDTO masterDTO=	shiftReportMasertList.get(i);
			String s2=null;
	    	try{
	    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		    	String s = df.format(shiftReportMasterDTO.getShifReportDate());
	        	Date d = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S")).parse(s);
	        	 s2 = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")).format(d);
	        	} catch(Exception e) {
	        	  e.printStackTrace();
	        	}
			if(masterDTO.getRunNo().trim().equalsIgnoreCase(shiftReportMasterDTO.getRunNo().trim()) && masterDTO.getShiftId().toString().trim().equalsIgnoreCase(shiftReportMasterDTO.getShiftId().toString().trim()) && masterDTO.getShifReportDate().toString().trim().equalsIgnoreCase(s2.trim())){
				error="Shif Report Date,Run No and Shift Id can not be duplicate";
				ErrorDTO errorDTO = new ErrorDTO();
				errorDTO.setErrorMsg(error);
				mav = new ModelAndView("shiftReport-detail");
				employee.setShiftReportMasterDTO(shiftReportMasterDTO);
				mav.addObject("errorDTO", errorDTO);
				mav.addObject("shiftReportMasterForm", employee);
				return mav;
			  }break;
		}}*/
		
		 String succ="";
			 
			if (shiftReportMasterDTO.getShiftReportId() != null && shiftReportMasterDTO.getShiftReportId()>0) {
			
			
				if("Delete".equals(operation)){
					shiftReportMasterDTO.setModifiedUserId(getCreatedUserId());
				shiftReportMasterService.deleteShiftReportMaster(shiftReportMasterInputMessage);
				  succ="Dl";
			    }else{
			    	shiftReportMasterDTO.setModifiedUserId(getCreatedUserId());
				 shiftReportMasterService.updateShiftReportMaster(shiftReportMasterInputMessage);
				 succ="Up";
			    }
				
			mav = new ModelAndView(new RedirectView("get_shift_report_list"));	
			mav.addObject("succ", succ);
			return mav;
		} else {
			// to check duplicate value for shift id,date and run Number
		//	ShiftReportMasterOutputMessage masterOutputMessage = shiftReportMasterService.findAllShiftReportMasters();
			
			ShiftReportMasterInputMessage inputMessage =new ShiftReportMasterInputMessage();
			inputMessage.setShiftReportMasterDTO(shiftReportMasterDTO);
			ShiftReportMasterOutputMessage masterOutputMessage = shiftReportMasterService.checkDuplicateEntry(shiftReportMasterInputMessage);
			//ShiftReportMasterOutputMessage masterOutputMessage = shiftReportMasterService.getDuplicateCheckList();
			ArrayList<ShiftReportMasterDTO> shiftReportMasertList =(ArrayList<ShiftReportMasterDTO>)masterOutputMessage.getShiftReportMasterDTOList();
			
			String error =null;
			/*if(shiftReportMasertList!=null && shiftReportMasertList.size()>0){
			for(int i=0;i<shiftReportMasertList.size();i++){
				ShiftReportMasterDTO masterDTO=	shiftReportMasertList.get(i);
				
				String s2=null;
		    	try{
		    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			    	String s = df.format(shiftReportMasterDTO.getShifReportDate());
		        	Date d = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S")).parse(s);
		        	 s2 = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")).format(d);
		        	} catch(Exception e) {
		        	  e.printStackTrace();
		        	}
				if(masterDTO.getRunNo().trim().equalsIgnoreCase(shiftReportMasterDTO.getRunNo().trim()) && masterDTO.getMastersDTO().getMastersId().toString().trim().equalsIgnoreCase(shiftReportMasterDTO.getMastersDTO().getMastersId().toString().trim()) && masterDTO.getShifReportDate().toString().trim().equalsIgnoreCase(s2.trim())){
					error="Shif Report Date,Run No and Shift Id can not be duplicate";
					ErrorDTO errorDTO = new ErrorDTO();
					errorDTO.setErrorMsg(error);
					mav = new ModelAndView("shiftReport-detail");
					employee.setShiftReportMasterDTO(shiftReportMasterDTO);
					mav.addObject("errorDTO", errorDTO);
					mav.addObject("shiftReportMasterForm", employee);
					return mav;
				  }break;
			}
			
			//to check duplicate value for shift id,date and run Number end
			
			}*/
			if(shiftReportMasertList!=null && shiftReportMasertList.size()>0){
			error="Shif Report Date,Run No and Shift Id can not be duplicate";
			ErrorDTO errorDTO = new ErrorDTO();
			errorDTO.setErrorMsg(error);
			mav = new ModelAndView("shiftReport-detail");
			employee.setShiftReportMasterDTO(shiftReportMasterDTO);
			mav.addObject("errorDTO", errorDTO);
			mav.addObject("shiftReportMasterForm", employee);
			return mav;
			}
			
			mav = new ModelAndView("shiftReport-list");
			String finishGoodNumber=(String)session.getAttribute("finishGoodNumber");
			if(finishGoodNumber!=null){
				shiftReportMasterInputMessage.getShiftReportMasterDTO().setFinishGoodNumber(finishGoodNumber);
			}
			shiftReportMasterDTO.setCreatedUserId(getCreatedUserId());
			shiftReportMasterService.createShiftReportMaster(shiftReportMasterInputMessage);
            succ="Ad";
		    }
		employee.setShiftReportMasterDTO(shiftReportMasterDTO);
		mav.addObject("shiftReportMasterForm", employee);
		model.addAttribute("step2", "2");
		model.addAttribute("succ", succ);
		//return mav;
		//return "redirect:/get_shift_report_list";
		mav = new ModelAndView(new RedirectView("get_shift_report_list"));
		return mav;
	   }
	
	
	@RequestMapping(value = "/get_shiftReport", method = RequestMethod.GET)
	public 	ModelAndView getShiftReportData(@RequestParam("shiftReportId") Integer id,@RequestParam("opr")String opr,ModelMap model) {
		ShiftReportMasterForm shiftReportMasterForm = new ShiftReportMasterForm();
		logger.info("Get shiftReport : " + id);
		logger.info("Opr : " + opr);

		ShiftReportMasterOutputMessage shiftReportMasterOutputMessage = null;
		if (id!=null && !id.equals(0)) {
		
			ShiftReportMasterInputMessage shiftReportMasterInputMessage = new ShiftReportMasterInputMessage();
			ShiftReportMasterDTO shiftReportMasterDTO = new ShiftReportMasterDTO();
			shiftReportMasterDTO.setShiftReportId(id);
			shiftReportMasterInputMessage.setShiftReportMasterDTO(shiftReportMasterDTO);
			logger.info("shiftReportMasterInputMessage getShiftReportId------ " + shiftReportMasterInputMessage.getShiftReportMasterDTO().getShiftReportId());
			shiftReportMasterOutputMessage = shiftReportMasterService.findShiftReportMasterById(shiftReportMasterInputMessage);
			ArrayList<ShiftReportMasterDTO> list = (ArrayList<ShiftReportMasterDTO>) shiftReportMasterOutputMessage.getShiftReportMasterDTOList();
			
			if (list!=null && list.size()>0) {
				ShiftConsumedDetailDTO detailDTO=null;
				try {
					detailDTO = list.get(0).getShiftConsumedDetailDTOList().get(0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ShiftReportMasterDTO masterDTO=list.get(0);
				masterDTO.setShiftConsumedDetailDTO(detailDTO);
				shiftReportMasterForm.setShiftReportMasterDTO(masterDTO);						
			}
		}
		if("R".equals(opr)){
		 shiftReportMasterForm.setOperation("remove");
		}if("V".equals(opr)){
		 shiftReportMasterForm.setOperation("V");
		}
		model.put("operation", "remove");
		model.put("shiftReportMasterForm", shiftReportMasterForm);
		
		//model.put("branchList", branchList());
		model.put("opr", opr);
		ModelAndView mav = new ModelAndView("shiftReport-edit");
		// mav.addObject("partyList",partyList());
		logger.info("end shiftReportMasterForm-->"+ shiftReportMasterForm);
		return mav;
	
	}


	
	
	
	@ModelAttribute("shift")
	public List<MastersDTO> shiftList() {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(11);
		MastersOutputMessage mastersOutputMessage = mastersService.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}
	
	@ModelAttribute("itemGroupList")
	public List<ItemGroupFlagDTO> itemGroupList() {
		ItemGroupFlagOutMessage itemGroupFlagOutMessage = itemGroupFlagService.findAllItemGroupFlag();
		ArrayList<ItemGroupFlagDTO> list = (ArrayList<ItemGroupFlagDTO>) itemGroupFlagOutMessage
				.getItemGroupFlagDTOList();
		return list;
	}
	
	@RequestMapping(value = "/shift_print_report/pdf", method = RequestMethod.GET )
    public  ModelAndView shiftReportPDF(@RequestParam("RUNNoPrompt") String RUNNoPrompt
    		,@RequestParam("datePrompt") Date datePrompt,@RequestParam("shiftPrompt") String shiftPrompt
    		,ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request) 
		 {
		
		logger.debug("Received request to download PDF report");
		response.setHeader("filename","grn_print_report.pdf");
		response.setContentType("application/pdf");
        //response.setHeader("Content-Disposition", "attachment:filename=_blank‌​");
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("Date_Prompt", datePrompt);
		parameterMap.put("Shift_Prompt", shiftPrompt);
		parameterMap.put("RUNNoPrompt", RUNNoPrompt);
			//System.out.println("SSSSSSSSSSSSSS"+datePrompt+"  "+shiftPrompt+"   "+RUNNoPrompt);
		String realPath  = request.getSession().getServletContext().getRealPath(System.getProperty("file.separator")+"WEB-INF"+System.getProperty("file.separator")+"images"+System.getProperty("file.separator")+"shree_logo.JPG");
		parameterMap.put("Image_Loc", realPath.toString());
		/*try {
			is = new FileInputStream(realPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("pdfShiftReportPrintView", parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	   }
}
