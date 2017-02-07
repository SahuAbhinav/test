package com.advanz.erp.client.http.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.export.oasis.Utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.CityForm;
import com.advanz.erp.client.http.controller.form.MasterFormulaForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.MasterFormulaDetailDTO;
import com.advanz.erp.masters.model.MasterFormulaMasterDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.PurchaseOrderDetailDTO;
import com.advanz.erp.masters.model.PurchaseOrderMasterDTO;
import com.advanz.erp.masters.model.msg.ItemGroupFlagOutMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MasterFormulaMasterInputMessage;
import com.advanz.erp.masters.model.msg.MasterFormulaMasterOutputMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.model.msg.PurchaseOrderMasterOutputMessage;
import com.advanz.erp.masters.service.business.IItemGroupFlagService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMasterFormulaService;
import com.advanz.erp.masters.service.business.IMastersService;


@Controller
/*@SessionAttributes({"masterFormulaForm"})*/
public class MasterFormulaController extends BaseController{
	@Autowired
	IMasterFormulaService iMasterFormulaService;
	
	@Autowired
	public IMastersService mastersService;
	
	@Autowired
	public IItemGroupFlagService iItemGroupFlagService;
	@Autowired
	IItemService itemService;
	
	@RequestMapping(value="/show_master_formula_list",method=RequestMethod.GET)
	public ModelAndView showMasterFormulaScreen(@ModelAttribute("masterFormulaForm") MasterFormulaForm masterFormulaForm,
			@RequestParam(value="menuId",required=false) String menuId,HttpSession session)
	{
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
	ModelAndView mav=new ModelAndView("master_formula_list");
	
	if(masterFormulaForm==null)
	{
		masterFormulaForm=new MasterFormulaForm();
	}
	MasterFormulaMasterOutputMessage formulaMasterOutputMessage=iMasterFormulaService.findAllMasterFormula();

	List<MasterFormulaMasterDTO> masterFormulaList=new ArrayList<MasterFormulaMasterDTO>();
	masterFormulaList=formulaMasterOutputMessage.getFormulaMasterDTOList();
	
	masterFormulaForm.setFormulaMasterDTOList(masterFormulaList);
	mav.addObject("masterFormulaForm", masterFormulaForm);
	mav.addObject("formulaMasterDTOList", masterFormulaList);
	return mav;
	}
	

	
	@RequestMapping(value = "/show_new_master_formula", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("masterFormulaForm") MasterFormulaForm masterFormulaForm) {
	 ModelAndView mav = new ModelAndView("master_formula_entry");
	 if (masterFormulaForm == null) {
		masterFormulaForm = new MasterFormulaForm();
	  }
	 
		ItemGroupFlagOutMessage flagOutMessage=new  ItemGroupFlagOutMessage();
		flagOutMessage=iItemGroupFlagService.findAllItemGroupFlag();
		

		MasterFormulaMasterDTO formulaMasterDTO=new MasterFormulaMasterDTO();
		MasterFormulaDetailDTO detailDTO=new MasterFormulaDetailDTO();
		detailDTO.setItemGroupFlagDTOList(flagOutMessage.getItemGroupFlagDTOList());
		List<MasterFormulaDetailDTO> detailDTOList=new ArrayList<MasterFormulaDetailDTO>();
		detailDTOList.add(detailDTO);
		
		formulaMasterDTO.setMasterFormulaDetailDTOList(detailDTOList);
		formulaMasterDTO.setCreationDate(new Date());
		formulaMasterDTO.setModifiedDate(new Date());
		formulaMasterDTO.setCreatedBy(getLoggedInUserName());
	//	mav.addObject("itemGroupFlagList",  flagOutMessage.getItemGroupFlagDTOList());
		masterFormulaForm.setMasterFormulaMasterDTO(formulaMasterDTO);
		String opr="A";
		mav.addObject("opr", opr);
		mav.addObject("masterFormulaForm", masterFormulaForm);
		return mav;
	}
	
	
	@RequestMapping(value = "/add_item_master_formula", method = RequestMethod.POST)
	public ModelAndView addNewItemMasterFormula(@ModelAttribute("masterFormulaForm") MasterFormulaForm masterFormulaForm,
			@RequestParam(value="opr",required=false) String opr) {
	 ModelAndView mav = new ModelAndView("master_formula_entry");
	 if (masterFormulaForm == null) {
		masterFormulaForm = new MasterFormulaForm();
	  }
	 
	 MasterFormulaMasterDTO masterFormulaMasterDTO=new MasterFormulaMasterDTO();
	 masterFormulaMasterDTO=masterFormulaForm.getMasterFormulaMasterDTO();
	 	ItemGroupFlagOutMessage flagOutMessage=new  ItemGroupFlagOutMessage();
		flagOutMessage=iItemGroupFlagService.findAllItemGroupFlag();
		List<MasterFormulaDetailDTO> detailDTOList=masterFormulaMasterDTO.getMasterFormulaDetailDTOList();
		if(detailDTOList!=null)
		{
			MasterFormulaDetailDTO detailDTO=new MasterFormulaDetailDTO();
			detailDTO.setItemGroupFlagDTOList(flagOutMessage.getItemGroupFlagDTOList());
			detailDTOList.add(new MasterFormulaDetailDTO());
		}
		else
		{
			detailDTOList=new ArrayList<MasterFormulaDetailDTO>();
			MasterFormulaDetailDTO detailDTO=new MasterFormulaDetailDTO();
			detailDTO.setItemGroupFlagDTOList(flagOutMessage.getItemGroupFlagDTOList());
			detailDTOList.add(new MasterFormulaDetailDTO());
		}
		masterFormulaMasterDTO.setMasterFormulaDetailDTOList(detailDTOList);
		masterFormulaForm.setMasterFormulaMasterDTO(masterFormulaMasterDTO);
		mav.addObject("opr", opr);
		mav.addObject("masterFormulaForm", masterFormulaForm);
		return mav;
	}
	
	

	@RequestMapping(value = "/save_master_formula", method = RequestMethod.POST)
	public String  saveMasterFormula(@ModelAttribute("masterFormulaForm") MasterFormulaForm masterFormulaForm,Model model) {
	
	MasterFormulaMasterInputMessage masterInputMessage=new MasterFormulaMasterInputMessage();
	MasterFormulaMasterDTO masterFormulaMasterDTO=new MasterFormulaMasterDTO();
	masterFormulaMasterDTO=masterFormulaForm.getMasterFormulaMasterDTO();
	masterInputMessage.setMasterFormulaMasterDTO(masterFormulaMasterDTO);
	MasterFormulaMasterOutputMessage masterOutputMessage=new MasterFormulaMasterOutputMessage();
	if(masterFormulaForm.getMasterFormulaMasterDTO().getMasterFormulaAutoId()==null || masterFormulaMasterDTO.getMasterFormulaAutoId().equals(0))
	{
		masterOutputMessage=iMasterFormulaService.createMasterFormula(masterInputMessage);	
	}
	else
	{
		masterOutputMessage=iMasterFormulaService.updateMasterFormula(masterInputMessage);
	}
	
	ErrorListDTO errorListDTO=masterOutputMessage.getErrorListDTO();
    
    if(errorListDTO!=null)
    {
    	ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
    	
  	 model.addAttribute("errorList",errorDTO);
  	 return "master_formula_entry";
    } 
   
	return "redirect:/show_master_formula_list";
}

	
	
	
	
	@RequestMapping(value = "/get_master_formula", method = RequestMethod.GET)
	public ModelAndView getMasterFormula(@ModelAttribute("masterFormulaForm") MasterFormulaForm masterFormulaForm,ModelMap model,
		@RequestParam("id") Integer id,@RequestParam("opr") String opr) {
		MasterFormulaMasterOutputMessage masterOutputMessage=new MasterFormulaMasterOutputMessage();
		MasterFormulaMasterInputMessage masterFormulaMasterInputMessage=new MasterFormulaMasterInputMessage();
		MasterFormulaMasterDTO masterFormulaMasterDTO=new MasterFormulaMasterDTO();
		
		if (id != null) {
			masterFormulaMasterDTO.setMasterFormulaAutoId(id);
			masterFormulaMasterInputMessage.setMasterFormulaMasterDTO(masterFormulaMasterDTO);
			masterOutputMessage=iMasterFormulaService.findMasterFormulaById(masterFormulaMasterInputMessage);
			masterFormulaMasterDTO=masterOutputMessage.getMasterFormulaMasterDTO();
			
		}
		
		masterFormulaForm.setMasterFormulaMasterDTO(masterFormulaMasterDTO);
		model.put("id",id);
		model.put("opr",opr);
		ModelAndView mav=new ModelAndView("master_formula_entry");
		mav.addObject("masterFormulaForm", masterFormulaForm);
		return mav;
	}
	
	
	
	
	@RequestMapping(value = "/remove_master_formula", method = RequestMethod.GET)
	public String removeMasterFormula(@ModelAttribute("masterFormulaForm") MasterFormulaForm masterFormulaForm,ModelMap model,
		@RequestParam("id") Integer id) {
		
		MasterFormulaMasterInputMessage masterFormulaMasterInputMessage=new MasterFormulaMasterInputMessage();
		MasterFormulaMasterDTO masterFormulaMasterDTO=new MasterFormulaMasterDTO();
		
		if (id != null) {
			masterFormulaMasterDTO.setMasterFormulaAutoId(id);
			masterFormulaMasterInputMessage.setMasterFormulaMasterDTO(masterFormulaMasterDTO);
		    iMasterFormulaService.deleteMasterFormula(masterFormulaMasterInputMessage);	
		}
		return "redirect:/show_master_formula_list";
	}


	
	@RequestMapping(value = "/remove_master_formula_item", method = RequestMethod.POST)
	public ModelAndView removeMasterFormulaItem(@ModelAttribute("masterFormulaForm") MasterFormulaForm masterFormulaForm,ModelMap model,
			@RequestParam("id") Integer id,@RequestParam("opr") String opr) {
		ModelAndView mav = null;
		if (id != null) {
			List<MasterFormulaDetailDTO> masterFormulaDetailList=masterFormulaForm.getMasterFormulaMasterDTO().getMasterFormulaDetailDTOList();
			if(masterFormulaDetailList!=null){
				MasterFormulaDetailDTO dto=masterFormulaDetailList.get(id);
				masterFormulaDetailList.remove(dto);
			}
			
			MasterFormulaMasterDTO	masterFormulaMasterDTO= masterFormulaForm.getMasterFormulaMasterDTO();
			masterFormulaMasterDTO.setMasterFormulaDetailDTOList(masterFormulaDetailList);
			masterFormulaForm.setMasterFormulaMasterDTO(masterFormulaMasterDTO);
			model.put("opr",opr);
			model.put("masterFormulaForm", masterFormulaForm);
			mav = new ModelAndView("master_formula_entry");
			return mav;
			}
		return mav;
	}

	
	@RequestMapping(value = "/find_item_by_id", method = RequestMethod.POST)
	public @ResponseBody JsonResponse showForm(@RequestParam("itemId") Integer itemId,
			@ModelAttribute("masterFormulaForm") MasterFormulaForm masterFormulaForm) {
		ItemInputMessage inputMessage=new ItemInputMessage();
		ItemDTO itemDTO=new ItemDTO();
		itemDTO.setItemId(itemId);
		inputMessage.setItemDTO(itemDTO);
		ItemOutMessage itemOutMessage=itemService.findItemById(inputMessage);
		ArrayList<ItemDTO> arrayList=new ArrayList<ItemDTO>();
		 arrayList=(ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
		 JsonResponse jsonResponse=new JsonResponse();
		jsonResponse.setResult(arrayList);
		jsonResponse.setStatus("SUCCESS");
		
		return jsonResponse;
	}
	
	
	
	
	@RequestMapping(value = "/find_item_by_itemGrupflag", method = RequestMethod.POST)
	public @ResponseBody JsonResponse showItemByItemCategory(@RequestParam("itemGroupFlagId") Integer itemGroupFlagId,
			@ModelAttribute("masterFormulaForm") MasterFormulaForm masterFormulaForm) {
		ItemInputMessage inputMessage=new ItemInputMessage();
		ItemDTO itemDTO=new ItemDTO();
		itemDTO.setItemGroupFlagId(itemGroupFlagId);
		inputMessage.setItemDTO(itemDTO);
		ItemOutMessage itemOutMessage=itemService.findItemForReportByGroupName(inputMessage);
		ArrayList<ItemDTO> arrayList=new ArrayList<ItemDTO>();
		 arrayList=(ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
		
		JsonResponse jsonResponse=new JsonResponse();
		jsonResponse.setResult(arrayList);
		jsonResponse.setStatus("SUCCESS");
		return jsonResponse;
	}


	@RequestMapping(value = "/find_item_by_itemCode", method = RequestMethod.POST)
	public @ResponseBody JsonResponse showItemByItemCode(@RequestParam("itemCode") String itemCode,
			@ModelAttribute("masterFormulaForm") MasterFormulaForm masterFormulaForm) {
		ItemInputMessage inputMessage=new ItemInputMessage();
		ItemDTO itemDTO=new ItemDTO();
		itemDTO.setItemCode(itemCode);
		inputMessage.setItemDTO(itemDTO);
		ItemOutMessage itemOutMessage=itemService.findItemListByItemCode(inputMessage);
		ArrayList<ItemDTO> arrayList=new ArrayList<ItemDTO>();
		 arrayList=(ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
		
		 //System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSS"+arrayList.get(0).getItemGroupFlagId());
		JsonResponse jsonResponse=new JsonResponse();
		jsonResponse.setResult(arrayList);
		jsonResponse.setStatus("SUCCESS");
		return jsonResponse;
	}
	
	
	@RequestMapping(value = "/find_item_group_flag_by_itemCode", method = RequestMethod.POST)
	public @ResponseBody JsonResponse findItemGroupFlagByItemCode(@RequestParam("itemCode") String itemCode,
			@ModelAttribute("masterFormulaForm") MasterFormulaForm masterFormulaForm) {
		ItemInputMessage inputMessage=new ItemInputMessage();
		
		ItemDTO itemDTO=new ItemDTO();
		itemDTO.setItemCode(itemCode);
		inputMessage.setItemDTO(itemDTO);
		ItemDTO itemDTO2=new ItemDTO();
		ArrayList<ItemDTO> arrayList=new ArrayList<ItemDTO>();
		try
		{
		ItemOutMessage itemOutMessage=itemService.findtemGroupFlagNameByItemCode(inputMessage);
		itemDTO2=itemOutMessage.getItemDTO();
		arrayList.add(itemDTO2);
		 //arrayList=(ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		JsonResponse jsonResponse=new JsonResponse();
		jsonResponse.setResult(arrayList);
		jsonResponse.setStatus("SUCCESS");
		return jsonResponse;
	}
	
	
	
	
	
	
	@RequestMapping(value = "/search_master_formula", method = RequestMethod.POST)
	public ModelAndView searchMasterFormula(@ModelAttribute("masterFormulaForm") MasterFormulaForm masterFormulaForm,ModelMap model) {
		ModelAndView  mav=new ModelAndView("master_formula_list");
		MasterFormulaMasterInputMessage formulaMasterInputMessage=new MasterFormulaMasterInputMessage();
		MasterFormulaMasterOutputMessage formulaMasterOutputMessage=new MasterFormulaMasterOutputMessage();
		
		 if(masterFormulaForm.getItemName()!=null || StringUtils.hasText(masterFormulaForm.getItemName())
				 ||masterFormulaForm.getItemCode()!=null || StringUtils.hasText(masterFormulaForm.getItemCode())
				  ||masterFormulaForm.getModifiedDate()!=null)
		 {
		   ItemDTO itemDTO=new ItemDTO();
		   itemDTO.setItemName(masterFormulaForm.getItemName());
		   itemDTO.setItemCode(masterFormulaForm.getItemCode());
		   
		   MasterFormulaMasterDTO dto=new MasterFormulaMasterDTO();
		   
		   dto.setItemDTO(itemDTO);
		   formulaMasterInputMessage.setMasterFormulaMasterDTO(dto);
			 formulaMasterOutputMessage=iMasterFormulaService.searchMasterFormula(formulaMasterInputMessage);
			
		 }
		 else
		 {
			formulaMasterOutputMessage=iMasterFormulaService.findAllMasterFormula();
		 }
		 List<MasterFormulaMasterDTO> masterFormulaList=new ArrayList<MasterFormulaMasterDTO>();
		 masterFormulaList=formulaMasterOutputMessage.getFormulaMasterDTOList();
			
		// masterFormulaForm.setFormulaMasterDTOList(masterFormulaList);
		 mav.addObject("masterFormulaForm", masterFormulaForm);
		 mav.addObject("formulaMasterDTOList", masterFormulaList);
		 
		 return mav;
		}
	  
	
	

	
	
	@ModelAttribute("itemFinishedGoodsList")
	public List<ItemDTO> showItemForFinishedGoods()
	{
	  ItemDTO itemDTO=new ItemDTO();
	  itemDTO.setInvoiceName(null);
	  itemDTO.setItemCode(null);
	  ItemInputMessage inputMessage=new ItemInputMessage();
	  inputMessage.setItemDTO(itemDTO);
		ItemOutMessage itemOutMessage=itemService.searchFinishedFoodItems(inputMessage);
	  	ArrayList<ItemDTO>  itemList= (ArrayList<ItemDTO>)itemOutMessage.getItemDTOList();
	
		return itemList;
	}
	

	@ModelAttribute("itemGroupFlagList")
   public List<ItemGroupFlagDTO> showItemGroupFlagList()
   {
		ItemGroupFlagOutMessage flagOutMessage=new  ItemGroupFlagOutMessage();
		flagOutMessage=iItemGroupFlagService.findAllItemGroupFlag();
		return flagOutMessage.getItemGroupFlagDTOList();
	}
	
	
		@ModelAttribute("itemList")
	   public List<ItemDTO> showItemList()
	   {
			ItemOutMessage itemOutMessage=new ItemOutMessage();
			itemOutMessage=itemService.getItemIdAndItemNameList();
			List<ItemDTO> list=new ArrayList<ItemDTO>();
			list=itemOutMessage.getItemDTOList();
			return list;
		}
	
	
}


