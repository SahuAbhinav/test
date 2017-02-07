package com.advanz.erp.client.http.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.BlanketProductionMasterForm;
import com.advanz.erp.client.http.controller.form.CapacitiveConsumptionForm;
import com.advanz.erp.masters.model.CapativeConsuptionDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.msg.CapativeConsuptionInputMessage;
import com.advanz.erp.masters.model.msg.CapativeConsuptionOutputMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.service.business.ICapativeConsuptionService;
import com.advanz.erp.masters.service.business.IItemService;

@Controller
public class CapacitiveConsumptionController extends BaseController{
@Autowired
public ICapativeConsuptionService capativeConsuptionService;
@Autowired
public IItemService itemService;
	@RequestMapping("/show_capative_consumtion")
	public ModelAndView doDisplay(@ModelAttribute("capacitiveConsumptionForm") CapacitiveConsumptionForm capacitiveConsumptionForm){
		ModelAndView mav=new ModelAndView("capative_consumption");
		ItemDTO itemDTO=new ItemDTO();
		ItemInputMessage inputMessage=new ItemInputMessage();
		int listSize = 0;
		inputMessage.setItemDTO(itemDTO);
		inputMessage.setItemOperation("FindByGroup");
		ItemOutMessage itemOutMessage=	itemService.getItemNameAndId(inputMessage);
	List<ItemDTO> itemList= itemOutMessage.getItemDTOList();
	CapativeConsuptionDTO capativeConsuptionDTO=new CapativeConsuptionDTO();
	capativeConsuptionDTO.setEnteredDate(new Date());
	List<CapativeConsuptionDTO> list= new ArrayList<CapativeConsuptionDTO>();
	list.add(capativeConsuptionDTO);
	capacitiveConsumptionForm.setCapativeConsuptionDTOList(list);
	listSize=list.size();
	mav.addObject("itemList", itemList);
	mav.addObject("listSize", listSize);
	mav.addObject("enteredDate", capativeConsuptionDTO.getEnteredDate());
	mav.addObject("capacitiveConsumptionForm", capacitiveConsumptionForm);
	 return mav;
	}

	
	@RequestMapping("/submit_capative_consumtion")
	public String doSubmit(@ModelAttribute("capacitiveConsumptionForm") CapacitiveConsumptionForm capacitiveConsumptionForm,Model model){
		ModelAndView mav=new ModelAndView("capative_consumption");
		
		ItemDTO itemDTO=new ItemDTO();
		ItemInputMessage inputMessage=new ItemInputMessage();
		inputMessage.setItemDTO(itemDTO);
		String succ = "";
		inputMessage.setItemOperation("FindByGroup");
		ItemOutMessage itemOutMessage=	itemService.getItemNameAndId(inputMessage);
	
	List<ItemDTO> itemList= itemOutMessage.getItemDTOList();
	Integer capativeId= capativeConsuptionService.getNewSeriesNo();
	List<CapativeConsuptionDTO> list = capacitiveConsumptionForm.getCapativeConsuptionDTOList();
	if(list!=null && list.size()>0){
		Iterator<CapativeConsuptionDTO> it= list.iterator();
		//System.out.println("list size"+list.size());
		while (it.hasNext()) {
			CapativeConsuptionDTO capativeConsuptionDTO = (CapativeConsuptionDTO) it
					.next();
			//CapativeConsuptionDTO capativeConsuptionDTO= capacitiveConsumptionForm.getCapativeConsuptionDTO();
			capativeConsuptionDTO.setCapativeConsumptionId(capativeId);
			capativeConsuptionDTO.setCapativeConsumptionNumber("CC/"+getFinYear()+"/"+capativeId);
			capativeConsuptionDTO.setCreatedUserId(getCreatedUserId());
			CapativeConsuptionInputMessage capativeConsuptionInputMessage=new CapativeConsuptionInputMessage();
			capativeConsuptionInputMessage.setCapativeConsuptionDTO(capativeConsuptionDTO);
			if(validateString(capativeConsuptionDTO.getSourceItemCode()) && validateString(capativeConsuptionDTO.getTargetItemCode()) &&
					validateDouble(capativeConsuptionDTO.getSourceQuantity()) && validateDouble(capativeConsuptionDTO.getTargetQuantity()))
			capativeConsuptionService.createCapativeConsuption(capativeConsuptionInputMessage);
			succ = "Ad";
		}
	
	}
	mav.addObject("capacitiveConsumptionForm", capacitiveConsumptionForm);
	model.addAttribute("succ", succ);
	return"redirect:/show_capative_consumtion_list";
	// return mav;
 }
	
	@RequestMapping(value = "add_new_row")
	public ModelAndView addNewRow(
			@ModelAttribute("capacitiveConsumptionForm") CapacitiveConsumptionForm capacitiveConsumptionForm
			){
		ModelAndView mav=new ModelAndView("capative_consumption");
		ItemDTO itemDTO=new ItemDTO();
		ItemInputMessage inputMessage=new ItemInputMessage();
		int listSize = 0;
		inputMessage.setItemDTO(itemDTO);
		inputMessage.setItemOperation("FindByGroup");
		ItemOutMessage itemOutMessage=	itemService.getItemNameAndId(inputMessage);
	List<ItemDTO> itemList= itemOutMessage.getItemDTOList();
		List<CapativeConsuptionDTO> list = capacitiveConsumptionForm.getCapativeConsuptionDTOList();
		 listSize = list.size();
		if(list!=null && list.size()>0){
			CapativeConsuptionDTO capativeConsuptionDTO=new CapativeConsuptionDTO();
			capativeConsuptionDTO.setEnteredDate(new Date());
			list.add(capativeConsuptionDTO);
			//capacitiveConsumptionForm.setCapativeConsuptionDTOList(list);
			listSize=list.size();
		}
		mav.addObject("itemList", itemList);
		mav.addObject("listSize", listSize);
		mav.addObject("capacitiveConsumptionForm", capacitiveConsumptionForm);
		
		return mav;
	}
	
	@RequestMapping("/show_capative_consumtion_list")
	public ModelAndView doDisplayList(@ModelAttribute("capacitiveConsumptionForm") CapacitiveConsumptionForm capacitiveConsumptionForm,@RequestParam(value="next",required=false) Integer next){
		ModelAndView mav=new ModelAndView("capative_consumption_list");
		CapativeConsuptionOutputMessage capativeConsuptionOutputMessage=null;
		CapativeConsuptionInputMessage capativeConsuptionInputMessage=new CapativeConsuptionInputMessage();
		if(next==null ||next<0)
		{
		next=0;
		capativeConsuptionInputMessage.setNext(next);
		capativeConsuptionOutputMessage = capativeConsuptionService.findCapativeConsuptionByPagination(capativeConsuptionInputMessage);
		}
		else
		{
			capativeConsuptionInputMessage.setNext(next);
			capativeConsuptionOutputMessage = capativeConsuptionService.findCapativeConsuptionByPagination(capativeConsuptionInputMessage);
		}
		List list= capativeConsuptionOutputMessage.getCapativeConsuptionDTOList();
		List<CapativeConsuptionDTO> capativeList=new ArrayList<CapativeConsuptionDTO>(); 
		
		ItemInputMessage itemInputMessage=null;
		ItemDTO itemDTO=null;
		ItemOutMessage itemOutMessage=null;
		List<ItemDTO> itemList=null;
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				CapativeConsuptionDTO caCDTO=new CapativeConsuptionDTO();
			CapativeConsuptionDTO ccDTO=(CapativeConsuptionDTO)list.get(i);
			caCDTO.setSourceQuantity(ccDTO.getSourceQuantity());
			caCDTO.setTargetQuantity(ccDTO.getTargetQuantity());
			caCDTO.setEnteredDate(ccDTO.getEnteredDate());
			caCDTO.setCapativeConsumptionNumber(ccDTO.getCapativeConsumptionNumber());
			caCDTO.setSno(ccDTO.getSno());
			itemInputMessage=new ItemInputMessage();
			itemDTO=new ItemDTO();
			itemDTO.setItemId(ccDTO.getSourceItemId());
			itemInputMessage.setItemDTO(itemDTO);
			
			itemOutMessage=itemService.getItemNameAndId(itemInputMessage);
		    itemList= itemOutMessage.getItemDTOList();
		if(itemList!=null && itemList.size()>0){
			itemDTO=new ItemDTO();
			itemDTO=itemList.get(0);
			caCDTO.setSourceItemName(itemDTO.getItemName());
			caCDTO.setSourceItemCode(itemDTO.getItemCode());
		}	
			
		itemInputMessage=new ItemInputMessage();
		itemDTO=new ItemDTO();
		itemDTO.setItemId(ccDTO.getTargetItemId());
		itemInputMessage.setItemDTO(itemDTO);
		itemOutMessage=itemService.getItemNameAndId(itemInputMessage);
	    itemList= itemOutMessage.getItemDTOList();
	    if(itemList!=null && itemList.size()>0){
			itemDTO=new ItemDTO();
			itemDTO=itemList.get(0);
			caCDTO.setTargetItemName(itemDTO.getItemName());
			caCDTO.setTargetItemCode(itemDTO.getItemCode());
		}	
	    //System.out.println("Date"+caCDTO.getEnteredDate()+"-id"+caCDTO.getSno());
	    capativeList.add(caCDTO);
	}
		}
		
		capacitiveConsumptionForm.setNext(next);
		capacitiveConsumptionForm.setPrevious(next);
		mav.addObject("capacitiveConsumptionForm", capacitiveConsumptionForm);
		mav.addObject("capativeList", capativeList);
		return mav;
	}
	
	@RequestMapping("/search_capative_consumtion_list")
	public ModelAndView search(@ModelAttribute("capacitiveConsumptionForm") CapacitiveConsumptionForm capacitiveConsumptionForm,@RequestParam(value="next",required=false) Integer next){
		ModelAndView mav=new ModelAndView("capative_consumption_list");
		CapativeConsuptionInputMessage capativeConsuptionInputMessage=new CapativeConsuptionInputMessage();
		CapativeConsuptionDTO dto=new CapativeConsuptionDTO();
		Date enterDate=null;
		String sourceItemCode=null;
		String targetItemCode=null;
if(capacitiveConsumptionForm.getEnteredDate()!=null){
	enterDate=capacitiveConsumptionForm.getEnteredDate();
}if(capacitiveConsumptionForm.getSourceItemCode()!=null){
	sourceItemCode=capacitiveConsumptionForm.getSourceItemCode();
}if(capacitiveConsumptionForm.getTargetItemCode()!=null){
	targetItemCode=capacitiveConsumptionForm.getTargetItemCode();
}

dto.setEnteredDate(enterDate);
dto.setSourceItemCode(sourceItemCode);
dto.setTargetItemCode(targetItemCode);

		capativeConsuptionInputMessage.setCapativeConsuptionDTO(dto);
		CapativeConsuptionOutputMessage	capativeConsuptionOutputMessage=capativeConsuptionService.search(capativeConsuptionInputMessage);
		List list= capativeConsuptionOutputMessage.getCapativeConsuptionDTOList();
		List<CapativeConsuptionDTO> capativeList=new ArrayList<CapativeConsuptionDTO>(); 
		
		ItemInputMessage itemInputMessage=null;
		ItemDTO itemDTO=null;
		ItemOutMessage itemOutMessage=null;
		List<ItemDTO> itemList=null;
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				CapativeConsuptionDTO caCDTO=new CapativeConsuptionDTO();
			CapativeConsuptionDTO ccDTO=(CapativeConsuptionDTO)list.get(i);
			caCDTO.setSourceQuantity(ccDTO.getSourceQuantity());
			caCDTO.setTargetQuantity(ccDTO.getTargetQuantity());
			caCDTO.setEnteredDate(ccDTO.getEnteredDate());
			caCDTO.setCapativeConsumptionNumber(ccDTO.getCapativeConsumptionNumber());
			itemInputMessage=new ItemInputMessage();
			itemDTO=new ItemDTO();
			itemDTO.setItemId(ccDTO.getSourceItemId());
			itemInputMessage.setItemDTO(itemDTO);
			
			itemOutMessage=itemService.getItemNameAndId(itemInputMessage);
		    itemList= itemOutMessage.getItemDTOList();
		if(itemList!=null && itemList.size()>0){
			itemDTO=new ItemDTO();
			itemDTO=itemList.get(0);
			caCDTO.setSourceItemName(itemDTO.getItemName());
			caCDTO.setSourceItemCode(itemDTO.getItemCode());
		}	
			
		itemInputMessage=new ItemInputMessage();
		itemDTO=new ItemDTO();
		itemDTO.setItemId(ccDTO.getTargetItemId());
		itemInputMessage.setItemDTO(itemDTO);
		itemOutMessage=itemService.getItemNameAndId(itemInputMessage);
	    itemList= itemOutMessage.getItemDTOList();
	    if(itemList!=null && itemList.size()>0){
			itemDTO=new ItemDTO();
			itemDTO=itemList.get(0);
			caCDTO.setTargetItemName(itemDTO.getItemName());
			caCDTO.setTargetItemCode(itemDTO.getItemCode());
		}	
	    capativeList.add(caCDTO);
	}
		}
		
		
		
		
		mav.addObject("capacitiveConsumptionForm", capacitiveConsumptionForm);
		mav.addObject("capativeList", capativeList);
		return mav;
	}
	
	@RequestMapping("/get_item_code")
	public @ResponseBody JsonResponse getItemCode(@RequestParam("itemId") Integer itemId){
		
		JsonResponse res = new JsonResponse();
		ItemInputMessage itemInputMessage=new ItemInputMessage();
		ItemDTO itemDTO=new ItemDTO();
		itemDTO.setItemId(itemId);
		itemInputMessage.setItemDTO(itemDTO);
		
		ItemOutMessage	itemOutMessage=itemService.getItemNameAndId(itemInputMessage);
		List<ItemDTO> itemList= itemOutMessage.getItemDTOList();
		String itemCode=null;
	if(itemList!=null && itemList.size()>0){
		itemDTO=new ItemDTO();
		itemDTO=itemList.get(0);
		itemCode=itemDTO.getItemCode();
	}	
	res.setStatus(itemCode);	
		 return res;
	}
	
	private boolean validateString(String str){
		if(str!=null && (str.trim()).length()>0 )
			return true;
		
		return false;
	}
	
	private boolean validateDouble(Double i){
		if(i>0.0)
			return true;
		
		return false;
	}
	
}
