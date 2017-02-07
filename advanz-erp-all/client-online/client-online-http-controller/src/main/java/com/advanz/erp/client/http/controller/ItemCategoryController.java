package com.advanz.erp.client.http.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.ItemCategoryForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.msg.ItemCategoryInputMessage;
import com.advanz.erp.masters.model.msg.ItemCategoryOutMessage;
import com.advanz.erp.masters.model.msg.ItemGroupOutMessage;
import com.advanz.erp.masters.model.msg.TransporterOutMessage;
import com.advanz.erp.masters.service.business.IItemCategoryService;
import com.advanz.erp.masters.service.business.IItemGroupService;

@Controller
public class ItemCategoryController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(ItemCategoryController.class);

	@Autowired
	public IItemCategoryService itemCategoryService;
	@Autowired
	public IItemGroupService itemGroupService;
	
	@RequestMapping(value = "/show_itemCategory_form", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("itemCategoryForm")ItemCategoryForm itemCategoryForm,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
		if(itemCategoryForm==null){
			itemCategoryForm=new ItemCategoryForm();
		}
		ModelAndView mav = new ModelAndView("itemCategory-list");
		ItemCategoryOutMessage itemCategoryOutMessage = null;
		ItemCategoryInputMessage itemCategoryInputMessage = new ItemCategoryInputMessage();
		itemCategoryOutMessage = itemCategoryService.findAllItemCategories();
		ArrayList<ItemCategoryDTO>list = (ArrayList<ItemCategoryDTO>) itemCategoryOutMessage.getItemCategoryDTOList();
		mav.addObject("catList", list);
		logger.info("Cat List : "+list);
		return mav;
	}
	
	
	@RequestMapping(value = "/search_item_category", method = RequestMethod.POST)
	public ModelAndView getItemCategoryData(
			@ModelAttribute("itemCategoryForm")ItemCategoryForm itemCategoryForm,Model model) {
		ModelAndView mav=new ModelAndView("itemCategory-list");
		ArrayList<ItemCategoryDTO>list = null;
		ItemCategoryOutMessage itemCategoryOutMessage = null;
		String catName=itemCategoryForm.getItemCategoryDTO().getItemCategoryName();
		String categoryCode=itemCategoryForm.getItemCategoryDTO().getItemCategoryCode();
		if (StringUtils.hasText(categoryCode)|| StringUtils.hasText(catName)){			
			ItemCategoryInputMessage itemCategoryInputMessage = new ItemCategoryInputMessage();
			ItemCategoryDTO itemCategoryDTO = new ItemCategoryDTO();
			itemCategoryDTO.setItemCategoryCode(categoryCode);
			itemCategoryDTO.setItemCategoryName(catName);
			itemCategoryInputMessage.setItemCategoryDTO(itemCategoryDTO);
			itemCategoryOutMessage = itemCategoryService.findItemCategory(itemCategoryInputMessage);
			list = (ArrayList<ItemCategoryDTO>) itemCategoryOutMessage.getItemCategoryDTOList();
		} else {
			itemCategoryOutMessage = itemCategoryService.findAllItemCategories();
			list = (ArrayList<ItemCategoryDTO>) itemCategoryOutMessage.getItemCategoryDTOList();
		}
		list = (ArrayList<ItemCategoryDTO>) itemCategoryOutMessage.getItemCategoryDTOList();
		String succ="Blk";
		if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}
		mav.addObject("catList", list);
		return mav;
	}

	@RequestMapping(value = "/get_itemCategory", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView getItemCategoryData(
			@ModelAttribute("itemCategoryForm") ItemCategoryForm itemCategoryForm,
			@ModelAttribute("itemCategoryId") String itemCategoryId) {
		logger.info("Get ItemCategory : " + itemCategoryId);
		ItemCategoryOutMessage itemCategoryOutMessage = null;
		if (StringUtils.hasText(itemCategoryId)) {
			int id = NumberUtils.parseNumber(itemCategoryId, Integer.class);
			ItemCategoryInputMessage itemCategoryInputMessage = new ItemCategoryInputMessage();
			ItemCategoryDTO itemCategoryDTO = new ItemCategoryDTO();
			itemCategoryDTO.setItemCategoryId(id);
			itemCategoryInputMessage.setItemCategoryDTO(itemCategoryDTO);
			itemCategoryOutMessage = itemCategoryService
					.findItemCategoryByItemCategoryId(itemCategoryInputMessage);
			ArrayList<ItemCategoryDTO> list = (ArrayList<ItemCategoryDTO>) itemCategoryOutMessage
					.getItemCategoryDTOList();
			if (list.size() == 1) {
				itemCategoryForm = new ItemCategoryForm();
				itemCategoryForm.setItemCategoryDTO(list.get(0));
			}
		}
		ModelAndView mav = new ModelAndView("itemCategory_edit");
		mav.addObject("itemCategoryForm", itemCategoryForm);
		logger.info(itemCategoryForm.getItemCategoryDTO().toString());
		return mav;
	}

	@RequestMapping(value = "/edit_itemCategory", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView getItemCategoryForEdit(@RequestParam ("itemCategoryId") String itemCategoryId,@RequestParam("opr")String opr) {
		logger.info("Operation : "+opr);
		logger.info("Edit ItemCategory : " + itemCategoryId);
		ModelAndView mav=new ModelAndView("itemCategory-edit");
		ItemCategoryForm itemCategoryForm = new ItemCategoryForm();
		ItemCategoryOutMessage itemCategoryOutMessage = null;
		if (StringUtils.hasText(itemCategoryId)) {
			int id = NumberUtils.parseNumber(itemCategoryId, Integer.class);
			ItemCategoryInputMessage itemCategoryInputMessage = new ItemCategoryInputMessage();
			ItemCategoryDTO itemCategoryDTO = new ItemCategoryDTO();
			itemCategoryDTO.setItemCategoryId(id);
			itemCategoryInputMessage.setItemCategoryDTO(itemCategoryDTO);
			
			if("R".equals(opr)){
				logger.info("RRRRRRRRRRRRRRR");
			    itemCategoryOutMessage = itemCategoryService.checkBeforeRemove(itemCategoryInputMessage);
				if(itemCategoryOutMessage!=null){
				ErrorListDTO errorListDTO=itemCategoryOutMessage.getErrorListDTO();
				if (errorListDTO != null && errorListDTO.hasErrors()) {
					ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
					mav= new ModelAndView("forward:show_itemCategory_form");
					mav.addObject("errorList", errorDTO);					
					return mav;
			  }}
			}
			itemCategoryOutMessage = itemCategoryService.findItemCategoryByItemCategoryId(itemCategoryInputMessage);
			ArrayList<ItemCategoryDTO> list = (ArrayList<ItemCategoryDTO>) itemCategoryOutMessage.getItemCategoryDTOList();
			if (list.size() == 1) {
				itemCategoryForm.setItemCategoryDTO(list.get(0));
			}
		}
		mav.addObject("itemCategoryForm", itemCategoryForm);
		mav.addObject("opr",opr);
		logger.info(itemCategoryForm.getItemCategoryDTO().toString());
		return mav;
	}
	
	
	@RequestMapping(value = "/show_new_icategory_form", method = RequestMethod.GET)
	public ModelAndView showNewItemCategoryForm(@ModelAttribute("itemCategoryForm") ItemCategoryForm itemCategoryForm) {
		ModelAndView mav = new ModelAndView("itemCategory-detail");
		if (itemCategoryForm == null) {
			itemCategoryForm = new ItemCategoryForm();
		}
		mav.addObject("itemCategoryForm", itemCategoryForm);
		return mav;
	}

	
	@RequestMapping(value = "/save_itemCategory", method = RequestMethod.POST)
	public String saveItemCategory(@ModelAttribute("itemCategoryForm") ItemCategoryForm itemCategoryForm,Model model) {
		logger.info("Saveing.......itemCategoryForm = " + itemCategoryForm);
		logger.info(itemCategoryForm.getItemCategoryDTO().toString());
		ItemCategoryInputMessage itemCategoryInputMessage = new ItemCategoryInputMessage();
		ItemCategoryDTO itemCategoryDTO= itemCategoryForm.getItemCategoryDTO();
		itemCategoryDTO.setCreatedUserId(getCreatedUserId());
		itemCategoryInputMessage.setItemCategoryDTO(itemCategoryDTO);
		String succ="";
		ItemCategoryOutMessage itemCategoryOutMessage=itemCategoryService.createItemCategory(itemCategoryInputMessage);
		
		ErrorListDTO errorListDTO=itemCategoryOutMessage.getErrorListDTO();
		
		if(errorListDTO!=null && errorListDTO.hasErrors()){
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("succ", succ);
			model.addAttribute("errorList",errorDTO);
			return "itemCategory-detail";
		}
		succ="Ad";
		model.addAttribute("succ", succ);
		return "redirect:/show_itemCategory_form";
	}
	
	@RequestMapping(value = "/update_itemCategory", method = RequestMethod.POST)
	public String updateItemCategory(
			@ModelAttribute("itemCategoryForm") ItemCategoryForm itemCategoryForm,Model model) {
		String succ="";
		logger.info("updating.......itemCategoryForm = " + itemCategoryForm);
		logger.info(itemCategoryForm.getItemCategoryDTO().toString());
		ItemCategoryInputMessage itemCategoryInputMessage = new ItemCategoryInputMessage();
		ItemCategoryDTO itemCategoryDTO= itemCategoryForm.getItemCategoryDTO();
		itemCategoryDTO.setModifiedUserId(getCreatedUserId());
		itemCategoryInputMessage.setItemCategoryDTO(itemCategoryDTO);
		
		ItemCategoryOutMessage itemCategoryOutMessage=itemCategoryService.updateItemCategory(itemCategoryInputMessage);
		
		ErrorListDTO errorListDTO=itemCategoryOutMessage.getErrorListDTO();
		
		if(errorListDTO!=null && errorListDTO.hasErrors()){
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errorList", errorDTO);
			model.addAttribute("opr","E");
			return "itemCategory-edit";
		}
		 succ="Up";
		 model.addAttribute("succ",succ);
		return "redirect:/show_itemCategory_form";
	}
	
	
	@RequestMapping(value = "/remove_itemCategory", method = RequestMethod.GET)
	public String removeItemCategory(@RequestParam ("itemCategoryId") String itemCategoryId,Model model) {
		logger.info("Removing..........itemCategoryId = " + itemCategoryId);
		ItemCategoryOutMessage itemCategoryOutMessage = null;
		if (StringUtils.hasText(itemCategoryId)) {
			int id = NumberUtils.parseNumber(itemCategoryId, Integer.class);
			ItemCategoryInputMessage itemCategoryInputMessage = new ItemCategoryInputMessage();
			ItemCategoryDTO itemCategoryDTO = new ItemCategoryDTO();
			itemCategoryDTO.setItemCategoryId(id);
			itemCategoryDTO.setModifiedUserId(getCreatedUserId());
			itemCategoryInputMessage.setItemCategoryDTO(itemCategoryDTO);
			itemCategoryOutMessage = itemCategoryService
					.deleteItemCategory(itemCategoryInputMessage);
		}
		String succ="Dl";
		  model.addAttribute("succ", succ);
		return "redirect:/show_itemCategory_form";

	}
	

	@ModelAttribute("itemGroupss")
	public List<ItemGroupDTO> itemGroupList() {
		List<ItemGroupDTO> items = new ArrayList<ItemGroupDTO>();
		ItemGroupOutMessage itemGroupOutMessage = itemGroupService.findItemGroupForItemCategory();
		return itemGroupOutMessage.getItemGroupDTOList();
	}


	private static final String dateFormat = "yyyy-MM-dd";

	@InitBinder
	public void allowEmptyDateBinding(WebDataBinder binder) {
		// Allow for null values in date fields.
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat(dateFormat), true));
		// tell spring to set empty values as null instead of empty string.
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}
