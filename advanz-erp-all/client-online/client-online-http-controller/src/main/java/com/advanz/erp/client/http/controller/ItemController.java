package com.advanz.erp.client.http.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.ItemForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.CityDTO;
import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.StockLedgerDTO;
import com.advanz.erp.masters.model.StoreLocationDTO;
import com.advanz.erp.masters.model.msg.BatchInputMessage;
import com.advanz.erp.masters.model.msg.BatchOutMessage;
import com.advanz.erp.masters.model.msg.EmployeeInputMessage;
import com.advanz.erp.masters.model.msg.EmployeeOutputMessage;
import com.advanz.erp.masters.model.msg.ItemCategoryInputMessage;
import com.advanz.erp.masters.model.msg.ItemCategoryOutMessage;
import com.advanz.erp.masters.model.msg.ItemGroupOutMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.model.msg.PartyOutMessage;
import com.advanz.erp.masters.model.msg.StockLedgerInputMessage;
import com.advanz.erp.masters.model.msg.StockLedgerOutMessage;
import com.advanz.erp.masters.model.msg.StoreLocationOutMessage;
import com.advanz.erp.masters.service.business.IBatchService;
import com.advanz.erp.masters.service.business.IEmployeeService;
import com.advanz.erp.masters.service.business.IItemCategoryService;
import com.advanz.erp.masters.service.business.IItemGroupService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IPartyService;
import com.advanz.erp.masters.service.business.IStockLedgerService;
import com.advanz.erp.masters.service.business.IStoreLocationService;

@Controller
public class ItemController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(ItemController.class);

	@Autowired
	public IItemService itemService;

	@Autowired
	public IMastersService mastersService;

	@Autowired
	public IItemCategoryService itemCategoryService;

	@Autowired
	public IItemGroupService itemGroupService;

	@Autowired
	public IEmployeeService employeeService;

	@Autowired
	public IStoreLocationService storeLocationService;

	@Autowired
	public IPartyService partyService;

	@Autowired
	public IBatchService batchService;

	
	@Autowired
	public IStockLedgerService stockLedgerService;

	@RequestMapping(value = "/show_Item_form", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("itemForm") ItemForm itemForm,@RequestParam(value="menuId",required=false) String menuId,@RequestParam(value="next",required=false) Integer next,HttpSession session) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
		if(itemForm == null) {
			itemForm = new ItemForm();
		}
	
		ItemDTO dto=new ItemDTO();
		ItemOutMessage itemOutMessage=new ItemOutMessage();
		ItemInputMessage inputMessage=new ItemInputMessage();
		/*if(next==null ||next<0)
		{
			next=0;
			dto.setNext(next);
			inputMessage.setItemDTO(dto);
			itemOutMessage= itemService.findItemForPagination(inputMessage);
			dto.setNext(15);
		}
		else
		{
			dto.setNext(next);
			inputMessage.setItemDTO(dto);
			itemOutMessage= itemService.findItemForPagination(inputMessage);
			next=next+15;
		}*/
		
		Object[] objects= getPaginationItemList(next);
		itemOutMessage=(ItemOutMessage) objects[0];
		next=(Integer)objects[1];
		
		ModelAndView mav = new ModelAndView("item");
		ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
		dto.setNext(next);
		dto.setPrevious(next-30);
		itemForm.setItemDTO(dto);
		mav.addObject("itemForm", itemForm);
		mav.addObject("itemList", list);
		return mav;
	}

	@RequestMapping(value = "/get_item_data", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView getItemData(@ModelAttribute("itemForm") ItemForm itemForm,Model model,@RequestParam(value="next",required=false) Integer next) {
		ModelAndView mav = new ModelAndView("item");
	//	System.out.println("=====================>>>>>>>>>>>>>>>>>>>"+next);
		Object[] object=new Object[2];
		ArrayList<ItemDTO> list = null;
		ItemOutMessage itemOutMessage = null;
		if (itemForm != null) {
			ItemDTO obj =new ItemDTO();
			obj =itemForm.getItemDTO();
			if (obj != null) {
				ItemInputMessage itemInputMessage = new ItemInputMessage();
				ItemDTO itemDTO = new ItemDTO();
				itemDTO.setItemCategoryDTO(new ItemCategoryDTO());
				itemDTO.getItemCategoryDTO().setItemGroupDTO(new ItemGroupDTO());
				
				itemDTO.setItemName(obj.getItemName());
				if(obj.getItemCode()!=null)
				{
				itemDTO.setItemCode(obj.getItemCode());
				}if(obj.getActiveStatus()==null){
				itemDTO.setActiveStatus(2);
				}
				else{
				itemDTO.setActiveStatus(obj.getActiveStatus()); //code for Mail of 12-12-14
				}
				if (obj.getMasterGrade()!=null){
					itemDTO.setMasterGrade(obj.getMasterGrade());
				}
				if (obj.getItemCategoryDTO() != null)
				itemDTO.getItemCategoryDTO().setItemCategoryName(obj.getItemCategoryDTO().getItemCategoryName());
				itemDTO.getItemCategoryDTO().getItemGroupDTO().setItemGroupName(obj.getItemGroupName());
				itemInputMessage.setItemDTO(itemDTO);
				itemOutMessage = itemService.findItem(itemInputMessage);
				list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
			}
		} else {
			object=getPaginationItemList(next);
			itemOutMessage = (ItemOutMessage) object[0];
			next=(Integer) object[1];
			
			list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
		}
		String succ="Blk";
		if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}
		if(list!=null){
			Iterator<ItemDTO> it =list.iterator();
			
			while (it.hasNext()) {
				ItemDTO itemDTO = (ItemDTO) it.next();
				//System.out.println("Total Stock"+itemDTO.getStockTotal());
				
			}
		}
		//System.out.println("");
		ItemDTO dto=itemForm.getItemDTO();
		dto.setNext(next);
		itemForm.setItemDTO(dto);
		mav.addObject("itemList", list);
		return mav;
	}

	@RequestMapping(value = "/add_item_form", method = RequestMethod.GET)
	public ModelAndView addItemForm(@ModelAttribute("itemForm") ItemForm item) {
		ModelAndView mav = new ModelAndView("addItem");
		if (item == null) {
			item = new ItemForm();
		}
		ItemDTO itemDto = new ItemDTO();
		itemDto.setActiveStatus(1);
		itemDto.setPrimaryConversion(1.0);
		item.setItemDTO(itemDto);
		mav.addObject("itemForm", item);
		return mav;
	}

	@RequestMapping(value = "/save_Item", method = RequestMethod.POST)
	public String saveItem(@ModelAttribute("itemForm") ItemForm itemForm,
			Model model) {
		String succ="";
		ItemInputMessage branchInputMessage = new ItemInputMessage();
		ItemDTO itemDTO= itemForm.getItemDTO();
	     String itemName=itemDTO.getItemName();
	     itemName.trim();
	     itemDTO.setCreatedUserId(getCreatedUserId());
		branchInputMessage.setItemDTO(itemDTO);

		ItemOutMessage itemOutMessage = itemService.createItem(branchInputMessage);

		ErrorListDTO errorListDTO = itemOutMessage.getErrorListDTO();
		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("succ", succ);
			model.addAttribute("errorList", errorDTO);
			return "addItem";
		}
		succ="Ad";
		model.addAttribute("succ", succ);
		return "redirect:/show_Item_form";

	}

	@RequestMapping(value = "/remove_item", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView removeBatch(@ModelAttribute("itemFrom") ItemForm item,
			@ModelAttribute("next") Integer next,@ModelAttribute("itemId") Integer itemId,Model model) {
		//int next = NumberUtils.parseNumber(nextVal, Integer.class);
		Object[] object=new Object[2];
		ItemOutMessage itemOutMessage = null;
		if (itemId != 0) {
			ItemInputMessage itemInputMessage = new ItemInputMessage();
			ItemDTO batchDTO = new ItemDTO();
			batchDTO.setItemId(itemId);
			batchDTO.setModifiedUserId(getCreatedUserId());
			itemInputMessage.setItemDTO(batchDTO);
			itemInputMessage.setDeletedFlag(true);
			itemOutMessage = itemService.deleteItem(itemInputMessage);
		}
		ModelAndView mav = null;
		if (itemOutMessage.getErrorListDTO() != null && itemOutMessage.getErrorListDTO().hasErrors()) {
			mav = new ModelAndView("item_remove");
			ErrorDTO errorDTO=itemOutMessage.getErrorListDTO().getErrorList().get(0);
			mav.addObject("errorList", errorDTO);
			ItemInputMessage itemInputMessage = new ItemInputMessage();
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setItemId(itemId);
			itemInputMessage.setItemDTO(itemDTO);
			ItemOutMessage itemOutMessage1 = itemService
					.findItemById(itemInputMessage);
			ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage1
					.getItemDTOList();
			if (item == null)
				item = new ItemForm();
			item.setItemDTO(list.get(0));
		} else {
			mav = new ModelAndView("item");
			object=getPaginationItemList(next-15);	
			
			ItemOutMessage itemOutMessage1 = (ItemOutMessage) object[0];
			next=(Integer) object[1];
			ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage1
					.getItemDTOList();
			mav.addObject("itemList", list);
		}
		//System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC"+next);
		ItemDTO dto=new ItemDTO();
		//dto=item.getItemDTO();
		dto.setNext(next);
		item.setItemDTO(dto);
		String succ="Dl";
		model.addAttribute("succ", succ);
		mav.addObject("itemForm", item);
		return mav;	}

	@RequestMapping(value = "/get_item", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView getItem(@ModelAttribute("itemForm") ItemForm itemForm,
			@ModelAttribute("itemId") Integer itemId,@ModelAttribute("next") Integer next,
			@ModelAttribute("opr") String opr,Model model) {
		Object[] objects=new Object[2];
		ItemOutMessage itemOutMessage = null;
		ModelAndView mav = null;

		if (itemId != 0 && opr.equals("R")) {
			ItemInputMessage itemInputMessage = new ItemInputMessage();
			ItemDTO batchDTO = new ItemDTO();
			batchDTO.setItemId(itemId);
			itemInputMessage.setItemDTO(batchDTO);
			itemInputMessage.setDeletedFlag(false);
			itemOutMessage = itemService.deleteItem(itemInputMessage);

			if (itemOutMessage.getErrorListDTO() != null && itemOutMessage.getErrorListDTO().hasErrors()) {
				mav = new ModelAndView("item");
				ErrorDTO errorDTO=itemOutMessage.getErrorListDTO().getErrorList().get(0);
				model.addAttribute("errorList",errorDTO );
				
				objects= getPaginationItemList(next-15);
				itemOutMessage =(ItemOutMessage) objects[0];
				next=(Integer) objects[1];
				
				ItemDTO dto=new ItemDTO();
				dto.setNext(next);
				dto.setPrimaryConversion(1.0);
				itemForm.setItemDTO(dto);
				ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
				mav.addObject("itemForm", itemForm);
				mav.addObject("itemList", list);
				return mav;
			}
		}

		if (itemId != 0) {
			ItemInputMessage itemInputMessage = new ItemInputMessage();
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setItemId(itemId);
			itemInputMessage.setItemDTO(itemDTO);
			itemOutMessage = itemService.findItemById(itemInputMessage);
			ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
			if (list.size() == 1) {
				itemForm = new ItemForm();
				itemForm.setItemDTO(list.get(0));
			}
		}
		if (opr.equals("M") || opr.equals("V")) {
			mav = new ModelAndView("edit_item");
			// Check Batch In Item
			BatchInputMessage batchInputMessage = new BatchInputMessage();
			BatchDTO batchDTO = new BatchDTO();
			batchInputMessage.setItemId(itemId);
			BatchOutMessage batchOutMessage = batchService.findAllBatchByItemId(batchInputMessage);
			ArrayList<BatchDTO> batchList = (ArrayList<BatchDTO>) batchOutMessage.getBatchDTOList();

			if (batchList != null && batchList.size() > 0) {
				batchDTO = batchList.get(0);
				itemForm.setBatchFlag("isExist");
				mav.addObject("itemForm", itemForm);
			   }
			}
			else {
				mav = new ModelAndView("item_remove");
		}
		ItemDTO itemDTO=itemForm.getItemDTO();
		itemDTO.setNext(next);
		itemDTO.setPrimaryConversion(1.0);
		itemForm.setItemDTO(itemDTO);
		mav.addObject("itemForm", itemForm);
		return mav;
	}

	@RequestMapping(value = "/update_item", method = RequestMethod.POST)
	public String updateitem(@ModelAttribute("itemForm") ItemForm itemForm,@ModelAttribute("next") String next,Model model) {
		String succ="";
		ItemInputMessage itemInputMessage = new ItemInputMessage();
		itemForm.getItemDTO().setModifiedUserId(getCreatedUserId());
		itemInputMessage.setItemDTO(itemForm.getItemDTO());
		ItemOutMessage itemOutMessage = itemService.updateItem(itemInputMessage);
		
		ErrorListDTO errorListDTO = itemOutMessage.getErrorListDTO();
		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errorList", errorDTO);
			return "edit_item";
		}
		 succ="Up";
		 ItemDTO dto=new ItemDTO();
		// dto.setNext(next);
		 dto.setPrimaryConversion(1.0);
		 itemForm.setItemDTO(dto);
		 model.addAttribute("itemForm",itemForm);
		 model.addAttribute("succ",succ);
		return "redirect:/show_Item_form";
	}

	@ModelAttribute("itemCategories")
	public List<ItemCategoryDTO> itemCategoryList() {
		List<ItemCategoryDTO> itemCategory = new ArrayList<ItemCategoryDTO>();
		ItemCategoryOutMessage itemCategoryOutMessage = itemCategoryService.findAllItemCategories();
		return itemCategoryOutMessage.getItemCategoryDTOList();
	}

	@ModelAttribute("grade")
	public List<MastersDTO> gradeList() {
		List<MastersDTO> items = new ArrayList<MastersDTO>();
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(16);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

	@ModelAttribute("packType")
	public List<MastersDTO> packTypeList() {
		List<MastersDTO> items = new ArrayList<MastersDTO>();
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(15);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

	@ModelAttribute("MeasurementUnit")
	public List<MastersDTO> MeasurementUnit() {
		List<MastersDTO> items = new ArrayList<MastersDTO>();
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(17);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

	@ModelAttribute("itemClassList")
	public List<MastersDTO> itemClassList() {
		List<MastersDTO> items = new ArrayList<MastersDTO>();
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		// change item class list
		mastersInputMessage.setFormId(13);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

	@ModelAttribute("itemGroups")
	public List<ItemGroupDTO> itemGroupList() {
		List<ItemGroupDTO> items = new ArrayList<ItemGroupDTO>();
		ItemGroupOutMessage itemGroupOutMessage = itemGroupService
				.findAllItemGroup();
		return itemGroupOutMessage.getItemGroupDTOList();
	}

	@ModelAttribute("productManager")
	public List<EmployeeDTO> producManagerList() {
		EmployeeDTO employee = new EmployeeDTO();

		EmployeeInputMessage employeeInputMessage = new EmployeeInputMessage();
		employee.setPmFlag(1);
		employee.setEmployeeCity(new CityDTO());
		employeeInputMessage.setEmployeeDTO(employee);
		EmployeeOutputMessage employeeOutputMessage = employeeService
				.findEmployee(employeeInputMessage);
		return employeeOutputMessage.getEmployeeDTOList();
	}

	@ModelAttribute("storeLocation")
	public List<StoreLocationDTO> storeLocationList() {
		StoreLocationOutMessage storeLocationOutMessage = storeLocationService
				.findAllStoreLocations();
		return storeLocationOutMessage.getStoreLocationDTOList();
	}

	@ModelAttribute("Vendor")
	public List<PartyDTO> VendorList() {
		PartyOutMessage partyOutMessage = partyService.findAllPartys();
		return partyOutMessage.getPartyDTOList();
	}

	@RequestMapping(value = "/getItemCatBy_id", method = RequestMethod.GET)
	public @ResponseBody
	ItemCategoryDTO getItemCatById(@RequestParam("id") Integer itemCatId) {
		ItemCategoryInputMessage itemCategoryInputMessage = new ItemCategoryInputMessage();
		ItemCategoryDTO itemCategory = new ItemCategoryDTO();
		itemCategory.setItemCategoryId(itemCatId);
		itemCategoryInputMessage.setItemCategoryDTO(itemCategory);
		ItemCategoryOutMessage itemCategoryOutMessage = itemCategoryService
				.findItemCategoryByItemCategoryId(itemCategoryInputMessage);

		ArrayList<ItemCategoryDTO> list = (ArrayList<ItemCategoryDTO>) itemCategoryOutMessage
				.getItemCategoryDTOList();

		if (list.size() == 1) {
			itemCategory = list.get(0);
			// employeeForm.getEmployeeDTO().setCity(
			// partyDTO);
		}
		return itemCategory;

	}

	@RequestMapping(value = "/getItemGroupName", method = RequestMethod.POST)
	public @ResponseBody
	String checkEmployee(@ModelAttribute(value = "code") String employeeCode,
			BindingResult result) {
		String returnText = "";

		EmployeeOutputMessage employeeOutputMessage = null;
		EmployeeInputMessage employeeInputMessage = new EmployeeInputMessage();
		ArrayList<EmployeeDTO> list;
		EmployeeDTO employeeDTO = new EmployeeDTO();
		if (StringUtils.hasText(employeeCode)) {
			employeeDTO.setEmployeeCity(new CityDTO());
			employeeDTO.setEmployeeCode(employeeCode);
			employeeInputMessage.setEmployeeDTO(employeeDTO);
			employeeOutputMessage = employeeService.findEmployee(employeeInputMessage);
			list = (ArrayList<EmployeeDTO>) employeeOutputMessage.getEmployeeDTOList();
			if (list != null && list.size() > 0) {
				returnText = "Employee Code already exits";
			}
		}

		return returnText;
	}

	
	@RequestMapping(value = "/get_item_quantity", method = RequestMethod.GET)
	public @ResponseBody
	Double getItemQuantity(HttpServletRequest httpServletRequest,@RequestParam("id") Integer id)
	{
	 StockLedgerDTO stockLedgerDTO=new StockLedgerDTO();
	 StockLedgerInputMessage stockLedgerInputMessage=new StockLedgerInputMessage();
	 stockLedgerDTO.setItemId(id);
	 stockLedgerInputMessage.setStockLedgerDTO(stockLedgerDTO);
	 StockLedgerOutMessage stockLedgerOutMessage=stockLedgerService.findQuantityByItemId(stockLedgerInputMessage);
	 Double quantity=stockLedgerOutMessage.getQuantityCount();
	 	 
	return quantity;
	}
	
	public Object[] getPaginationItemList(Integer next){
		
		
		ItemDTO dto=new ItemDTO();
		ItemOutMessage itemOutMessage=new ItemOutMessage();
		ItemInputMessage inputMessage=new ItemInputMessage();
		Object[] objects=new Object[2];
		if(next==null ||next<0)
		{
			next=0;
			dto.setNext(next);
			inputMessage.setItemDTO(dto);
			itemOutMessage= itemService.findItemForPagination(inputMessage);
			next=15;
			objects[0]=itemOutMessage;
			objects[1]=next;
		}
		else
		{
			dto.setNext(next);
			inputMessage.setItemDTO(dto);
			itemOutMessage= itemService.findItemForPagination(inputMessage);
			next=next+15;
			objects[0]=itemOutMessage;
			objects[1]=next;
		}
		return objects;
			
	}
	
}
