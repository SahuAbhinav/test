package com.advanz.erp.client.http.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.ItemGroupFrom;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.msg.ItemGroupFlagOutMessage;
import com.advanz.erp.masters.model.msg.ItemGroupInputMessage;
import com.advanz.erp.masters.model.msg.ItemGroupOutMessage;
import com.advanz.erp.masters.service.business.IItemGroupFlagService;
import com.advanz.erp.masters.service.business.IItemGroupService;

@Controller
public class ItemGroupController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(ItemGroupController.class);

	@Autowired
	public IItemGroupService itemGroupService;

	@Autowired
	public IItemGroupFlagService itemGroupFlagService;

	@RequestMapping(value = "/show_ItemGroup_form", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("itemGroupForm") ItemGroupFrom itemGroupForm,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
	
		ModelAndView mav = new ModelAndView("itemGroup");
		if (itemGroupForm == null) {
			itemGroupForm = new ItemGroupFrom();
		}
		ItemGroupOutMessage itemGroupMessage = itemGroupService.findAllItemGroup();
		ArrayList<ItemGroupDTO> list = (ArrayList<ItemGroupDTO>) itemGroupMessage.getItemGroupDTOList();
		mav.addObject("itemGroupForm", itemGroupForm);
		mav.addObject("itemGroupList", list);
		return mav;
	}

	@RequestMapping(value = "/get_itemGroup_data", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView getItemGroupData(
			@ModelAttribute("rows") ArrayList<ItemGroupDTO> list,
			@ModelAttribute("page") String page,
			@ModelAttribute("itemGroupName") String itemGroupName,
			@ModelAttribute("itemGroupCode") String itemGroupCode,Model model) {
		ItemGroupOutMessage batchOutMessage = null;
		ModelAndView mav = new ModelAndView("itemGroup");
		if (StringUtils.hasText(itemGroupName) || StringUtils.hasText(itemGroupCode)) {
			ItemGroupInputMessage itemGroupInputMessage = new ItemGroupInputMessage();
			ItemGroupDTO itemGroupDTO = new ItemGroupDTO();
			itemGroupDTO.setItemGroupName(itemGroupName);
			itemGroupDTO.setItemGroupCode(itemGroupCode);
			itemGroupInputMessage.setItemGroupDTO(itemGroupDTO);
			batchOutMessage = itemGroupService
					.findItemGroup(itemGroupInputMessage);
			list = (ArrayList<ItemGroupDTO>) batchOutMessage.getItemGroupDTOList();
		} else {
			batchOutMessage = itemGroupService.findAllItemGroup();
			list = (ArrayList<ItemGroupDTO>) batchOutMessage.getItemGroupDTOList();
		}
		String succ="Blk";
		if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}

		mav.addObject("itemGroupList", list);
		return mav;
	}

	@RequestMapping(value = "/get_itemGroup", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView getItem(@ModelAttribute("itemGroupForm") ItemGroupFrom itemGroupForm,
			@ModelAttribute("itemGroupId") Integer itemGroupId,
			@RequestParam("opr") String opr, Model model) {
		logger.info("Get item : " + itemGroupId);
		ItemGroupOutMessage itemGroupOutMessage = null;
		if (itemGroupId != 0 && opr.equals("R")) {
			ItemGroupInputMessage itemGroupInputMessage = new ItemGroupInputMessage();
			ItemGroupDTO itemGroupDTO = new ItemGroupDTO();
			itemGroupDTO.setItemGroupId(itemGroupId);
			itemGroupDTO.setModifiedUserId(getCreatedUserId());
			itemGroupInputMessage.setItemGroupDTO(itemGroupDTO);
			itemGroupInputMessage.setDeleteFlag(false);
			itemGroupOutMessage = itemGroupService.deleteItemGroup(itemGroupInputMessage);

			ErrorListDTO errorListDTO = itemGroupOutMessage.getErrorListDTO();
			if (errorListDTO != null && errorListDTO.hasErrors()) {
				ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
				logger.info(" adding Error ");
				model.addAttribute("errors",errorDTO);
				ModelAndView mav = new ModelAndView("itemGroup");
				itemGroupOutMessage = itemGroupService.findAllItemGroup();
				ArrayList<ItemGroupDTO> list = (ArrayList<ItemGroupDTO>) itemGroupOutMessage.getItemGroupDTOList();

				mav.addObject("itemGroupList", list);
				return mav;
			}
		}

		if (itemGroupId != 0) {
			ItemGroupInputMessage itemGroupInputMessage = new ItemGroupInputMessage();
			ItemGroupDTO itemGroupDTO = new ItemGroupDTO();
			itemGroupDTO.setItemGroupId(itemGroupId);
			itemGroupInputMessage.setItemGroupDTO(itemGroupDTO);
			itemGroupOutMessage = itemGroupService.findItemGroupById(itemGroupInputMessage);
			ArrayList<ItemGroupDTO> list = (ArrayList<ItemGroupDTO>) itemGroupOutMessage.getItemGroupDTOList();
			if (list.size() == 1) {
				itemGroupForm = new ItemGroupFrom();
				itemGroupForm.setItemGroupDTO(list.get(0));
			}
		}
		ModelAndView mav = new ModelAndView("edit_itemGroup");
		mav.addObject("itemGroupForm", itemGroupForm);
		mav.addObject("opr", opr);
		logger.info(itemGroupForm.getItemGroupDTO().toString());
		return mav;

	}

	@RequestMapping(value = "/add_itemGroup_form", method = RequestMethod.GET)
	public ModelAndView addItemGroupForm(@ModelAttribute("itemGroupForm") ItemGroupFrom itemGroup) {
		
		ModelAndView mav = new ModelAndView("addItemGroup");
		if (itemGroup == null) {
			itemGroup = new ItemGroupFrom();
		}
		mav.addObject("itemGroupForm", itemGroup);
		return mav;
	}

	@RequestMapping(value = "/save_ItemGroup", method = RequestMethod.POST)
	public String saveItemGroup(@ModelAttribute("itemGroupForm") ItemGroupFrom itemGroupFrom,Model model) {
		String succ="";
		logger.info(itemGroupFrom.getItemGroupDTO().toString());
		ItemGroupInputMessage itemGroupInputMessage = new ItemGroupInputMessage();
		ItemGroupDTO itemGroupDTO= itemGroupFrom.getItemGroupDTO();
		itemGroupDTO.setCreatedUserId(getCreatedUserId());
		itemGroupInputMessage.setItemGroupDTO(itemGroupDTO);
		ItemGroupOutMessage itemGroupOutMessage = itemGroupService.createItemGroup(itemGroupInputMessage);

		ErrorListDTO errorListDTO = itemGroupOutMessage.getErrorListDTO();
		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			succ="Du";
			logger.info(" adding Error ");
			model.addAttribute("errors",errorDTO);	
			return "addItemGroup";
		}
		succ="Ad";
		model.addAttribute("succ", succ);
		return "redirect:/show_ItemGroup_form";

	}

	@RequestMapping(value = "/update_itemGroup", method = RequestMethod.POST)
	public String updateitemGroup(@ModelAttribute("itemGroupForm") ItemGroupFrom itemGroupFrom,Model model) {
		logger.info("itemGroupFrom = " + itemGroupFrom);
		logger.info(itemGroupFrom.getItemGroupDTO().toString());
		ItemGroupInputMessage itemGroupInputMessage = new ItemGroupInputMessage();
		// itemForm.getItemDTO().setd
		ItemGroupDTO itemGroupDTO=itemGroupFrom.getItemGroupDTO();
		itemGroupDTO.setModifiedUserId(getCreatedUserId());
		itemGroupInputMessage.setItemGroupDTO(itemGroupDTO);
		ItemGroupOutMessage itemGroupOutMessage = itemGroupService.updateItemGroup(itemGroupInputMessage);

		ErrorListDTO errorListDTO = itemGroupOutMessage.getErrorListDTO();
		if (errorListDTO != null && errorListDTO.hasErrors()) {
			logger.info(" adding Error ");
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			model.addAttribute("errors",errorDTO);
			return "edit_itemGroup";
		}

		String succ="";
		succ="Up";
		model.addAttribute("succ",succ);
		return "redirect:/show_ItemGroup_form";
	}

	@RequestMapping(value = "/remove_itemGroup", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView removeBatch(@ModelAttribute("itemGroupFrom") ItemGroupFrom itemGroupFrom,
			@ModelAttribute("itemGroupId") Integer itemGroupId, Model model) {
		String succ="Dl";
		model.addAttribute("succ", succ);
		logger.info("Removing..........itemGroupId = " + itemGroupId);
		ItemGroupOutMessage itemGroupOutMessage = null;
		ModelAndView mav = null;

		if (itemGroupId != 0) {
			ItemGroupInputMessage itemGroupInputMessage = new ItemGroupInputMessage();
			ItemGroupDTO batchDTO = new ItemGroupDTO();
			batchDTO.setItemGroupId(itemGroupId);
			itemGroupInputMessage.setItemGroupDTO(batchDTO);
			batchDTO.setModifiedUserId(getCreatedUserId());
			itemGroupInputMessage.setDeleteFlag(true);
			itemGroupOutMessage = itemGroupService.deleteItemGroup(itemGroupInputMessage);
		}
		ErrorListDTO errorListDTO = itemGroupOutMessage.getErrorListDTO();
		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors",	errorDTO);
			ItemGroupInputMessage itemGroupInputMessage = new ItemGroupInputMessage();
			ItemGroupDTO itemGroupDTO = new ItemGroupDTO();
			itemGroupDTO.setItemGroupId(itemGroupId);
			itemGroupInputMessage.setItemGroupDTO(itemGroupDTO);
			itemGroupOutMessage = itemGroupService.findItemGroupById(itemGroupInputMessage);
			ArrayList<ItemGroupDTO> list = (ArrayList<ItemGroupDTO>) itemGroupOutMessage.getItemGroupDTOList();
			if (list.size() == 1) {
				itemGroupFrom = new ItemGroupFrom();
				itemGroupFrom.setItemGroupDTO(list.get(0));
			}

			mav = new ModelAndView("edit_itemGroup");
			mav.addObject("opr", "R");
			mav.addObject("itemGroupForm", itemGroupFrom);
			return mav;
		}
		mav = new ModelAndView("itemGroup");
		mav.addObject("itemGroupFrom", itemGroupFrom);
		ItemGroupOutMessage itemGroupMessage = itemGroupService.findAllItemGroup();
		ArrayList<ItemGroupDTO> list = (ArrayList<ItemGroupDTO>) itemGroupMessage.getItemGroupDTOList();
	
		mav.addObject("itemGroupList", list);
		
		return mav;

	}

	@ModelAttribute("itemGroupFlages")
	public List<ItemGroupFlagDTO> itemGroupFlagList() {
		List<ItemGroupFlagDTO> itemGroupFlag = new ArrayList<ItemGroupFlagDTO>();
		ItemGroupFlagOutMessage itemCategoryOutMessage = itemGroupFlagService.findAllItemGroupFlag();
		return itemCategoryOutMessage.getItemGroupFlagDTOList();
	}

}
