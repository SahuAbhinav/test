package com.advanz.erp.client.http.controller;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.IndentMasterForm;
import com.advanz.erp.client.http.controller.form.ItemForm;
import com.advanz.erp.client.http.controller.form.PurchaseOrderMasterForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.IndentDetailDTO;
import com.advanz.erp.masters.model.IndentMasterDTO;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.PurchaseOrderDetailDTO;
import com.advanz.erp.masters.model.PurchaseOrderMasterDTO;
import com.advanz.erp.masters.model.TransactionTypeDTO;
import com.advanz.erp.masters.model.TransporterDTO;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.model.msg.IndentInputMessage;
import com.advanz.erp.masters.model.msg.IndentOutputMessage;
import com.advanz.erp.masters.model.msg.ItemGroupFlagOutMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.model.msg.PartyInputMessage;
import com.advanz.erp.masters.model.msg.PartyOutMessage;
import com.advanz.erp.masters.model.msg.PurchaseOrderMasterInputMessage;
import com.advanz.erp.masters.model.msg.PurchaseOrderMasterOutputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeInputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeOutputMessage;
import com.advanz.erp.masters.model.msg.TransporterOutMessage;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.service.business.IGrnMasterService;
import com.advanz.erp.masters.service.business.IIndentMasterService;
import com.advanz.erp.masters.service.business.IItemGroupFlagService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IPartyService;
import com.advanz.erp.masters.service.business.IPurchaseOrderMasterService;
import com.advanz.erp.masters.service.business.ITransactionTypeService;
import com.advanz.erp.masters.service.business.ITransporterService;

@Controller
@SessionAttributes({ "purchaseOrderMasterForm", "partyList" })
public class PurchaseOrderController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);
	@Autowired
	DataSource dataSource;
	@Autowired
	public IItemService itemService;

	@Autowired
	public IBranchService branchService;

	@Autowired
	public IPartyService partyService;

	@Autowired
	public IItemGroupFlagService itemGroupFlagService;

	@Autowired
	public ITransporterService transporterService;

	@Autowired
	public ITransactionTypeService transactionTypeService;

	@Autowired
	public IPurchaseOrderMasterService purchaseOrderMasterService;

	@Autowired
	public IMastersService mastersService;

	@Autowired
	public IGrnMasterService iGrnMasterService;

	@Autowired
	public IIndentMasterService indentMasterService;
	@RequestMapping(value = "/savePurchaseOrder", method = RequestMethod.POST)
	public String saveSalesOrder(@ModelAttribute("purchaseOrderMasterForm") PurchaseOrderMasterForm purchaseOrderMasterForm,Model model) {
		
		String succ="";
		logger.info("purchaseOrderMasterForm = " + purchaseOrderMasterForm);

		PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage = new PurchaseOrderMasterInputMessage();
		
		
		PurchaseOrderMasterDTO purchaseOrderMasterDTO=	purchaseOrderMasterForm.getPurchaseOrderMasterDTO();
		
		List<PurchaseOrderDetailDTO> pol= purchaseOrderMasterDTO.getPurchaseOrderDetailDTOList();
		for(int i=0;i<pol.size();i++)
		{
			PurchaseOrderDetailDTO pod=	pol.get(i);
			if (purchaseOrderMasterDTO.getVatCstType().equals("vat")) {
				pod.setVatPerc(pod.getTaxPerc());
				pod.setCstPerc(0.0);
			}if (purchaseOrderMasterDTO.getVatCstType().equals("cst with c form")) {
				pod.setCstPerc(pod.getTaxPerc());
				pod.setVatPerc(0.0);
			}if (purchaseOrderMasterDTO.getVatCstType().equals("cst w/o c form")) {
				pod.setVatPerc(pod.getTaxPerc());
				pod.setCstPerc(0.0);
			}
			
			
		}
		
		try {
			if (purchaseOrderMasterDTO.getVatCstType().equals("vat")) {
				purchaseOrderMasterDTO.setVatAmount(purchaseOrderMasterDTO.getTaxVatCstTotal());
			}if (purchaseOrderMasterDTO.getVatCstType().equals("cst with c form")) {
				purchaseOrderMasterDTO.setCstAmount(purchaseOrderMasterDTO.getTaxVatCstTotal());
			}if (purchaseOrderMasterDTO.getVatCstType().equals("cst w/o c form")) {
				purchaseOrderMasterDTO.setVatAmount(purchaseOrderMasterDTO.getTaxVatCstTotal());
			}
			} catch (Exception e) {
			}
		purchaseOrderMasterInputMessage.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
		PurchaseOrderMasterOutputMessage purchaseOrderMasterOutputMessage = null;
		if (purchaseOrderMasterForm.getPurchaseOrderMasterDTO().getPoAutoId() == null || purchaseOrderMasterForm.getPurchaseOrderMasterDTO().getPoAutoId().equals(0)) {
			purchaseOrderMasterDTO.setCreatedUserId(getCreatedUserId());
			purchaseOrderMasterOutputMessage = purchaseOrderMasterService.createPurchaseOrderMaster(purchaseOrderMasterInputMessage);
			succ="Ad";
		} else {
			purchaseOrderMasterDTO.setModifiedUserId(getCreatedUserId());
			purchaseOrderMasterOutputMessage = purchaseOrderMasterService.updatePurchaseOrderMaster(purchaseOrderMasterInputMessage);
			succ="Up";
		}

		ErrorListDTO errorListDTO = purchaseOrderMasterOutputMessage.getErrorListDTO();
		PurchaseOrderMasterDTO orderMasterDTO=	purchaseOrderMasterForm.getPurchaseOrderMasterDTO();
		Integer purchaseOrderId= orderMasterDTO.getPurchaseOrderId()+1;
		orderMasterDTO.setPurchaseOrderId(purchaseOrderId);
		
		orderMasterDTO.setPurchaseOrderNumber(getPurchaseOrderTransactionSeries()+"/"+getFinYear()+"/"+purchaseOrderId);
		if (errorListDTO != null && errorListDTO.hasErrors()) {
			logger.info(" adding Error ");
			ErrorDTO errorDTO=purchaseOrderMasterOutputMessage.getErrorListDTO().getErrorList().get(0);
			model.addAttribute("purchaseOrderMasterForm",purchaseOrderMasterForm);
			model.addAttribute("errors",errorDTO);
			return "purchaseOrder_add";
		}
		
		orderMasterDTO.setPurchaseOrderDetailDTOList(null);
		model.addAttribute("purchaseOrderMasterForm",purchaseOrderMasterForm);
		model.addAttribute("succ",succ);
		return "redirect:/get_purchaseOrder_list";

	}

	@RequestMapping(value = "/show_item_list_po")
	public ModelAndView showItemSelectionForm(
			@ModelAttribute("purchaseOrderMasterForm") PurchaseOrderMasterForm purchaseOrderMasterForm,
			@ModelAttribute("itemForm") ItemForm itemForm,
			@RequestParam(value="opr",required=false) String opr,@RequestParam(value="next",required=false) Integer next) {
		ModelAndView mav = new ModelAndView("item_list_po");
		if (itemForm == null) {
			itemForm = new ItemForm();
		}
	
		
		//System.out.println("    -=========== "+ purchaseOrderMasterForm.getPurchaseOrderMasterDTO().getItemGroupFlagDTO().getItemGroupFlagId());
		Integer groupId = purchaseOrderMasterForm.getPurchaseOrderMasterDTO().getItemGroupFlagDTO().getItemGroupFlagId();
		ItemOutMessage itemOutMessage = null;

		ItemInputMessage itemInputMessage = new ItemInputMessage();
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemCategoryDTO(new ItemCategoryDTO());
		itemDTO.getItemCategoryDTO().setItemGroupDTO(new ItemGroupDTO());
		itemDTO.getItemCategoryDTO().getItemGroupDTO().setItemGroupFlagId(purchaseOrderMasterForm.getPurchaseOrderMasterDTO().getItemGroupFlagDTO().getItemGroupFlagId());
		itemDTO.setActiveStatus(1);
		
		
		//itemOutMessage = itemService.findItem(itemInputMessage);

		//Pagination start
		//ItemDTO dto=new ItemDTO();
		 itemOutMessage=new ItemOutMessage();
		//ItemInputMessage inputMessage=new ItemInputMessage();
		if(next==null ||next<0)
		{
			next=0;
			itemDTO.setNext(next);
			itemInputMessage.setItemDTO(itemDTO);
			itemOutMessage= itemService.getListByItemGroupName(itemInputMessage);
			next=13;
		}
		else
		{
			
			itemDTO.setNext(next);
			itemInputMessage.setItemDTO(itemDTO);
			itemOutMessage= itemService.getListByItemGroupName(itemInputMessage);
			next=next+13;
		}
		
		//Pagination end
		
		
		
		ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
		if (purchaseOrderMasterForm.getPurchaseOrderMasterDTO() != null) {
			List<PurchaseOrderDetailDTO> purchaseOrderDetailDTOList = purchaseOrderMasterForm
					.getPurchaseOrderMasterDTO()
					.getPurchaseOrderDetailDTOList();
			if (purchaseOrderDetailDTOList != null) {
				for (PurchaseOrderDetailDTO purchaseOrderDetailDTO : purchaseOrderDetailDTOList) {
					Iterator<ItemDTO> itr = list.iterator();
					while (itr.hasNext()) {
						ItemDTO temp = itr.next();
						if (temp.getItemId()
								.equals(purchaseOrderDetailDTO.getItemDTO()
										.getItemId())) {
							itr.remove();
						}
					}
				}
			}
		}
		itemDTO.setNext(next);
		itemDTO.setPrevious(next-26);
		itemForm.setItemDTO(itemDTO);
		mav.addObject("itemForm", itemForm);
		mav.addObject("itemList", list);
		mav.addObject("opr", opr);
		mav.addObject("groupId", groupId);

		return mav;
	}

	@RequestMapping(value = "/get_purchaseOrder_by_id")
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

	@RequestMapping("/addItemInPO")
	public ModelAndView addItemInPurchaseOrder(
			@ModelAttribute("purchaseOrderMasterForm") PurchaseOrderMasterForm purchaseOrderMasterForm,
			@RequestParam("itemID") Integer itemId,
			@RequestParam("opr") String opr, ModelMap model) {
		
		logger.info("IN addItem()  purchaseOrderMasterForm-->"+ purchaseOrderMasterForm);
		logger.info("Add Item : " + itemId);
		logger.info("Add opr : " + opr);

		if (!isDuplicateItem(purchaseOrderMasterForm.getPurchaseOrderMasterDTO(), itemId)) {
			ItemInputMessage itemInputMessage = new ItemInputMessage();
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setItemId(itemId);
			itemInputMessage.setItemDTO(itemDTO);
			List<ItemDTO> itemDtoList = itemService.findItemById(
					itemInputMessage).getItemDTOList();
			if (itemDtoList != null && itemDtoList.size() > 0) {
				itemDTO = itemDtoList.get(0);
				PurchaseOrderDetailDTO purchaseOrderDetailDTO = new PurchaseOrderDetailDTO();
				BeanUtils.copyProperties(itemDTO, purchaseOrderDetailDTO);
				purchaseOrderDetailDTO.setItemDTO(itemDTO);
				if (itemDTO.getMasterUnit() != null) {
					purchaseOrderDetailDTO.setMeasurementUnitId(itemDTO
							.getMasterUnit().getMastersId());
					purchaseOrderDetailDTO.setMeasurementUnitName(itemDTO
							.getMasterUnit().getName());
				}
				
				PartyDTO partyDTO = purchaseOrderMasterForm.getPurchaseOrderMasterDTO().getPartyDTO();
				if (partyDTO != null) {
					
					if ("saleswithInState".equals(partyDTO.getInvoiceType()) || "localPurchase".equals(partyDTO.getInvoiceType()))
						purchaseOrderDetailDTO.setTaxPerc(itemDTO.getVatPerc());
					if ("salesOutsideState".equals(partyDTO.getInvoiceType())||"importPurchase".equals(partyDTO.getInvoiceType()))
						purchaseOrderDetailDTO.setTaxPerc(itemDTO.getCstPerc());
				
				/*if("vat".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
					purchaseOrderDetailDTO.setTaxPerc(itemDTO.getVatPerc());
				}if("cst with c form".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
					purchaseOrderDetailDTO.setTaxPerc(itemDTO.getCstPerc());
				}if("cst w/o c form".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
					purchaseOrderDetailDTO.setTaxPerc(itemDTO.getVatPerc());
				}*/
				}    
				
				
				if (purchaseOrderMasterForm.getPurchaseOrderMasterDTO() == null) {
					PurchaseOrderMasterDTO purchaseOrderMasterDTO = new PurchaseOrderMasterDTO();
					purchaseOrderMasterDTO.setPurchaseOrderDetailDTOList(new ArrayList<PurchaseOrderDetailDTO>());
				}
				if (purchaseOrderMasterForm.getPurchaseOrderMasterDTO()
						.getPurchaseOrderDetailDTOList() == null) {
					purchaseOrderMasterForm.getPurchaseOrderMasterDTO()
							.setPurchaseOrderDetailDTOList(
									new ArrayList<PurchaseOrderDetailDTO>());
				}
				purchaseOrderMasterForm.getPurchaseOrderMasterDTO()
						.getPurchaseOrderDetailDTOList()
						.add(purchaseOrderDetailDTO);
				logger.info("After add Item Size="
						+ purchaseOrderMasterForm.getPurchaseOrderMasterDTO()
								.getPurchaseOrderDetailDTOList().size());
			}
		}
		//calculationPurchaseOrder(purchaseOrderMasterForm.getPurchaseOrderMasterDTO());
		
		model.put("opr", opr);
		ModelAndView mav = new ModelAndView("purchaseOrder_add");
		return mav;
	}

	@RequestMapping(value = "/purchaseCalc", method = RequestMethod.POST)
	public ModelAndView calcPurchaseOrder(
			@ModelAttribute("purchaseOrderMasterForm") PurchaseOrderMasterForm purchaseOrderMasterForm,
			@RequestParam("opr") String opr, ModelMap model) {
		PurchaseOrderMasterDTO purchaseOrderMasterDTO = purchaseOrderMasterForm
				.getPurchaseOrderMasterDTO();
		//calculationPurchaseOrder(purchaseOrderMasterDTO);
		model.put("opr", opr);
		ModelAndView mav = new ModelAndView("purchaseOrder_add");
		return mav;
	}

	/*private void calculationPurchaseOrder(
			PurchaseOrderMasterDTO purchaseOrderMasterDTO) {
		List<PurchaseOrderDetailDTO> list = purchaseOrderMasterDTO
				.getPurchaseOrderDetailDTOList();
		double totalItemValue = 0;
		double totalDiscountAmt = 0;
		if (list != null) {
			for (PurchaseOrderDetailDTO dto : list) {
				totalItemValue += dto.getItemValue();
				totalDiscountAmt += dto.getDiscountAmount();
			}
			double netAmount = totalItemValue + -totalDiscountAmt;

			purchaseOrderMasterDTO.setItemValue(totalItemValue);
			purchaseOrderMasterDTO.setDiscountAmount(totalDiscountAmt);
			purchaseOrderMasterDTO.setPoNetAmount(netAmount);
			purchaseOrderMasterDTO.setCount(list.size());
		}
	}*/

	private String getPurchaseOrderTransactionSeries() {
		TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
		transactionTypeDTO.setSno(11);
		TransactionTypeInputMessage transactionTypeInputMessage = new TransactionTypeInputMessage();
		transactionTypeInputMessage.setTransactionTypeDTO(transactionTypeDTO);
		TransactionTypeOutputMessage transactionTypeOutputMessage = transactionTypeService
				.findTransactionTypeById(transactionTypeInputMessage);
		List<TransactionTypeDTO> list = transactionTypeOutputMessage
				.getTransactionTypeDTOList();
		if (list != null && list.size() == 1)
			return list.get(0).getSeries();
		return null;

	}

	@RequestMapping(value = "/purchaseOrder_add", method = RequestMethod.GET)
	public ModelAndView addPurchaseOrder(ModelMap model,@RequestParam(value="opr",required=false) String opr) {
		PurchaseOrderMasterForm purchaseOrderMasterForm = new PurchaseOrderMasterForm();
		PurchaseOrderMasterDTO purchaseOrderMasterDTO = new PurchaseOrderMasterDTO();
		String series = getPurchaseOrderTransactionSeries();
		purchaseOrderMasterDTO.setFinYear(getFinYear());
		
		Calendar cal = Calendar.getInstance();  
		Date date=new Date();
		cal.setTime(new Date());  
		cal.add(Calendar.DATE,60); 
		date = cal.getTime();  
		 
		purchaseOrderMasterDTO.setPoValidUptoDate(date);
		PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage = new PurchaseOrderMasterInputMessage();
		purchaseOrderMasterInputMessage
				.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
		PurchaseOrderMasterOutputMessage purchaseOrderMasterOutputMessage = purchaseOrderMasterService
				.getNewPurchaseOrderSeriesNo(purchaseOrderMasterInputMessage);
		Integer orderID = purchaseOrderMasterOutputMessage
				.getPurchaseOrderSeriesNo();
		Timestamp timestamp= purchaseOrderMasterOutputMessage.getPurchaseOrderSeriesDate();
		//System.out.println("Last purchase order time"+new Date(timestamp.getTime()));
		SimpleDateFormat ft =new SimpleDateFormat ("yyyy,MM,dd");
		
		purchaseOrderMasterDTO.setTransactionSeries(series);
		
		
		purchaseOrderMasterDTO.setPurchaseOrderIdSeries(series+ "/"+ getFinYear());
		
		purchaseOrderMasterDTO.setPurchaseOrderId(orderID);
		purchaseOrderMasterDTO.setFormReqFlag(0);
		
		purchaseOrderMasterDTO.setPurchaseOrderNumber(purchaseOrderMasterDTO.getTransactionSeries()+ "/"
				+ getFinYear()
				+ "/"
				+ purchaseOrderMasterDTO.getPurchaseOrderId());
		
		
		
		
		
		purchaseOrderMasterDTO.setPurchaseOrderDate(new Date());

		purchaseOrderMasterForm
				.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
		// purchaseOrderMasterForm.setPurchaseOrderMasterDTO(new
		// PurchaseOrderMasterDTO());
		purchaseOrderMasterForm.setLastPurchaseOrderDate(ft.format(new Date(timestamp.getTime())));
		model.put("purchaseOrderMasterForm", purchaseOrderMasterForm);
		model.put("partyList", partyList());
		model.put("opr", opr);
		
		ModelAndView mav = new ModelAndView("purchaseOrder_add");
		// mav.addObject("partyList",partyList());
		logger.info("IN newPurchaseOrder() purchaseOrderMasterForm-->"
				+ purchaseOrderMasterForm);
		return mav;
	}

	@RequestMapping(value = "/backto_purchaseOrder", method = RequestMethod.GET)
	public ModelAndView backToPurchaseOrder(ModelMap model,
			@RequestParam("opr") String opr) {
		ModelAndView mav = new ModelAndView("purchaseOrder_add");
		mav.addObject("opr", opr);
		return mav;
	}

	@RequestMapping(value = "/get_item_data_po", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView getItemData(
			@ModelAttribute("itemName") String itemName,
			@ModelAttribute("groupId") Integer groupId,
			@ModelAttribute("itemCode") String itemCode,
			@ModelAttribute("purchaseOrderMasterForm") PurchaseOrderMasterForm purchaseOrderMasterForm) {
		ModelAndView mav = new ModelAndView("item_list_po");
		ArrayList<ItemDTO> list = null;
		ItemOutMessage itemOutMessage = null;

		ItemInputMessage itemInputMessage = new ItemInputMessage();
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemCategoryDTO(new ItemCategoryDTO());
		itemDTO.getItemCategoryDTO().setItemGroupDTO(new ItemGroupDTO());
		itemDTO.getItemCategoryDTO().getItemGroupDTO()
				.setItemGroupFlagId(groupId);
		itemDTO.setItemName(itemName);
		itemDTO.setItemCode(itemCode);
		itemDTO.setActiveStatus(1);
		itemInputMessage.setItemDTO(itemDTO);
		itemOutMessage = itemService.findItem(itemInputMessage);
		list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();

		if (purchaseOrderMasterForm.getPurchaseOrderMasterDTO() != null) {
			List<PurchaseOrderDetailDTO> purchaseOrderDetailDTOList = purchaseOrderMasterForm
					.getPurchaseOrderMasterDTO()
					.getPurchaseOrderDetailDTOList();
			if (purchaseOrderDetailDTOList != null) {
				for (PurchaseOrderDetailDTO purchaseOrderDetailDTO : purchaseOrderDetailDTOList) {
					Iterator<ItemDTO> itr = list.iterator();
					while (itr.hasNext()) {
						ItemDTO temp = itr.next();
						if (temp.getItemId()
								.equals(purchaseOrderDetailDTO.getItemDTO()
										.getItemId())) {
							itr.remove();
						}
					}
				}
			}
		}

		mav.addObject("itemList", list);
		return mav;

	}

	@RequestMapping(value = "/getPartyBy_id", method = RequestMethod.GET)
	public @ResponseBody
	PartyDTO getPartyById(
			@RequestParam("id") Integer partyId,
			@ModelAttribute("purchaseOrderMasterForm") PurchaseOrderMasterForm purchaseOrderMasterForm) {
		logger.info("Party Id=" + partyId);
		PartyInputMessage partyInputMessage = new PartyInputMessage();
		PartyDTO partyDTO = new PartyDTO();
		partyDTO.setPartyId(partyId);
		partyInputMessage.setPartyDTO(partyDTO);
		PartyOutMessage partyOutMessage = partyService
				.findPartyById(partyInputMessage);

		ArrayList<PartyDTO> list = (ArrayList<PartyDTO>) partyOutMessage
				.getPartyDTOList();

		if (list.size() == 1) {
			partyDTO = list.get(0);
			purchaseOrderMasterForm.getPurchaseOrderMasterDTO().setPartyDTO(
					partyDTO);
		}
		logger.info("Part DTO :" + partyDTO);
		return partyDTO;

	}

	@RequestMapping(value = "/get_purchaseOrder_list")
	public ModelAndView searchPurchaseOrder(
			@ModelAttribute("purchaseOrderMasterForm1") PurchaseOrderMasterForm purchaseOrderMasterForm,
			@ModelAttribute("redirect") String redirect,@ModelAttribute("opr") String opr,Model model
			,@RequestParam(value="menuId",required=false) String menuId,HttpSession session,@RequestParam(value="next",required=false) Integer next) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
	
		List<PurchaseOrderMasterDTO> list = new ArrayList<PurchaseOrderMasterDTO>();
		PurchaseOrderMasterOutputMessage purchaseOrderMasterOutputMessage = null;
		ModelAndView mav = null;
		if (!StringUtils.hasText(redirect))

			mav = new ModelAndView("purchaseOrder_list");
		else {
			mav = new ModelAndView("purchaseOrderGrn_list");
			//		 mav.addObject("grnDtoList", grnDtoList);
			
			mav.addObject("opr", opr);
		}
		if (purchaseOrderMasterForm != null && opr.equalsIgnoreCase("search")) {
			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage = new PurchaseOrderMasterInputMessage();
			PurchaseOrderMasterDTO purchaseOrderMasterDTO = purchaseOrderMasterForm
					.getPurchaseOrderMasterDTO();
			if (purchaseOrderMasterDTO == null)
				purchaseOrderMasterDTO = new PurchaseOrderMasterDTO();
			if (purchaseOrderMasterDTO.getPartyDTO() == null)
				purchaseOrderMasterDTO.setPartyDTO(new PartyDTO());

			purchaseOrderMasterInputMessage.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
			
			try {
				purchaseOrderMasterOutputMessage = purchaseOrderMasterService.search(purchaseOrderMasterInputMessage);
			} catch (Exception e) {
				
			}
			
			list = (ArrayList<PurchaseOrderMasterDTO>) purchaseOrderMasterOutputMessage.getPurchaseOrderMasterDTOList();
			String succ="Blk";
			if(list.equals(null) || list.size()==0)
			{
			  model.addAttribute("succ", succ);
			}
		} else {
			
			
			//purchaseOrderMasterOutputMessage = purchaseOrderMasterService.findAllPurchaseOrderMasters();
			
			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage = new PurchaseOrderMasterInputMessage();
			if(next==null ||next<0)
			{
			next=0;
			purchaseOrderMasterInputMessage.setNext(next);
			purchaseOrderMasterOutputMessage = purchaseOrderMasterService.findPurchaseOrderForPagination(purchaseOrderMasterInputMessage);
			}
			else
			{
				purchaseOrderMasterInputMessage.setNext(next);
				purchaseOrderMasterOutputMessage = purchaseOrderMasterService.findPurchaseOrderForPagination(purchaseOrderMasterInputMessage);
			}
			purchaseOrderMasterForm.setNext(next);
			purchaseOrderMasterForm.setPrevious(next);
			
			list = (ArrayList<PurchaseOrderMasterDTO>) purchaseOrderMasterOutputMessage.getPurchaseOrderMasterDTOList();
		}
	if(StringUtils.hasText(redirect))
	 {	
		PurchaseOrderMasterDTO purchaseOrderMasterDTO=new PurchaseOrderMasterDTO();
		List<PurchaseOrderDetailDTO> purchaseOrderDetailDTOlst=new ArrayList<PurchaseOrderDetailDTO>();
		for(int j=0;j<list.size();j++)
		{double blQty=0.0;
		 double pendingQty=0.0;
		 int itemCount=0;
		purchaseOrderMasterDTO=list.get(j);
		
		 for(int i=0;i<purchaseOrderMasterDTO.getPurchaseOrderDetailDTOList().size();i++) {
		   
			 purchaseOrderDetailDTOlst=purchaseOrderMasterDTO.getPurchaseOrderDetailDTOList();
			try{
			 pendingQty=purchaseOrderDetailDTOlst.get(i).getPendingQuantity();
			}catch (Exception e) {
			}
			 if(pendingQty<0)
			 {
			  pendingQty=0.0; 
			 }
		    blQty=blQty+pendingQty;
		    itemCount=itemCount+1;
		    purchaseOrderMasterDTO.setItemCount(itemCount);
		    purchaseOrderMasterDTO.setBalanceQuantity(blQty);
		    list.set(j, purchaseOrderMasterDTO);
		 }
		}
		//purchaseOrderMasterDTO.setPurchaseOrderDetailDTOList(purchaseOrderDetailDTOlst);
		//purchaseOrderMasterForm=new PurchaseOrderMasterForm();
		purchaseOrderMasterForm.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
		mav.addObject("purchaseOrderMasterForm",purchaseOrderMasterForm);
	 }
	mav.addObject("pomList", list);
	return mav;
	}

	@RequestMapping(value = "/remove_grid_item", method = RequestMethod.GET)
	public ModelAndView getRemoveSalesOrderData(@ModelAttribute("purchaseOrderMasterForm") PurchaseOrderMasterForm purchaseOrderMasterForm,@RequestParam("id") Integer id,
			@RequestParam(value="opr",required=false) String opr, ModelMap model) {
		//public ModelAndView getSalesOrderData(@ModelAttribute("purchaseOrderMasterForm") PurchaseOrderMasterForm purchaseOrderMasterForm,@RequestParam("id") Integer id,
	     //PurchaseOrderMasterForm purchaseOrderMasterForm = new PurchaseOrderMasterForm();
		logger.info("Get puchase Order : " + id);
		logger.info("Opr : " + opr);
		ModelAndView mav = null;
		PurchaseOrderMasterOutputMessage purchaseOrderMasterOutputMessage = null;

		if (id != null) {
			List<PurchaseOrderDetailDTO> detailDTOList=	purchaseOrderMasterForm.getPurchaseOrderMasterDTO().getPurchaseOrderDetailDTOList();
			if(detailDTOList!=null){
				PurchaseOrderDetailDTO dto=detailDTOList.get(id);
			    detailDTOList.remove(dto);
			}
			//PurchaseOrderMasterDTO	purchaseOrderMasterDTO= new PurchaseOrderMasterDTO();
			
			PurchaseOrderMasterDTO	purchaseOrderMasterDTO= purchaseOrderMasterForm.getPurchaseOrderMasterDTO();
			purchaseOrderMasterDTO.setPurchaseOrderDetailDTOList(detailDTOList);
			purchaseOrderMasterForm.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
	        
			model.put("purchaseOrderMasterForm", purchaseOrderMasterForm);
			mav = new ModelAndView("purchaseOrder_add");
			mav.addObject("opr", opr);
			
			return mav;
			}
		return mav;
	}
	
	
	
	@RequestMapping(value = "/get_purchaseOrder", method = RequestMethod.GET)
	public ModelAndView getSalesOrderData(@ModelAttribute("purchaseOrderMasterForm1") PurchaseOrderMasterForm purchaseOrderMasterForm,@RequestParam("id") Integer id,
			@RequestParam("opr") String opr, ModelMap model) {
		//public ModelAndView getSalesOrderData(@ModelAttribute("purchaseOrderMasterForm") PurchaseOrderMasterForm purchaseOrderMasterForm,@RequestParam("id") Integer id,
	     //PurchaseOrderMasterForm purchaseOrderMasterForm = new PurchaseOrderMasterForm();
		logger.info("Get puchase Order : " + id);
		logger.info("Opr : " + opr);
		ModelAndView mav = null;
		PurchaseOrderMasterOutputMessage purchaseOrderMasterOutputMessage = null;

		if (id != null  && opr.equals("Remove")) {
			List<PurchaseOrderDetailDTO> detailDTOList=	purchaseOrderMasterForm.getPurchaseOrderMasterDTO().getPurchaseOrderDetailDTOList();
			if(detailDTOList!=null){
				PurchaseOrderDetailDTO dto=detailDTOList.get(id);
			    detailDTOList.remove(dto);
			}
			//PurchaseOrderMasterDTO	purchaseOrderMasterDTO= new PurchaseOrderMasterDTO();
			
			PurchaseOrderMasterDTO	purchaseOrderMasterDTO= purchaseOrderMasterForm.getPurchaseOrderMasterDTO();
			purchaseOrderMasterDTO.setPurchaseOrderDetailDTOList(detailDTOList);
			purchaseOrderMasterForm.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
	        
			model.put("purchaseOrderMasterForm", purchaseOrderMasterForm);
			mav = new ModelAndView("purchaseOrder_add");
			mav.addObject("opr", opr);
			
			return mav;
			}
		
		if (id != null && !id.equals(0) && opr.equals("R")) {
			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage = new PurchaseOrderMasterInputMessage();
			PurchaseOrderMasterDTO purchaseOrderMasterDTO = new PurchaseOrderMasterDTO();
			purchaseOrderMasterDTO.setPoAutoId(id);
			purchaseOrderMasterInputMessage.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
			purchaseOrderMasterInputMessage.setDeletedFlag(false);
			purchaseOrderMasterDTO.setModifiedUserId(getCreatedUserId());
			purchaseOrderMasterOutputMessage = purchaseOrderMasterService.deletePurchaseOrderMaster(purchaseOrderMasterInputMessage);

			if (purchaseOrderMasterOutputMessage.getErrorListDTO() != null
					&& purchaseOrderMasterOutputMessage.getErrorListDTO().hasErrors()) {
				mav = new ModelAndView("purchaseOrder_list");
				ErrorDTO errorDTO=purchaseOrderMasterOutputMessage.getErrorListDTO().getErrorList().get(0);
				mav.addObject("errors",errorDTO);
				purchaseOrderMasterOutputMessage = purchaseOrderMasterService.findAllPurchaseOrderMasters();
				ArrayList<PurchaseOrderMasterDTO> list = (ArrayList<PurchaseOrderMasterDTO>) purchaseOrderMasterOutputMessage
						.getPurchaseOrderMasterDTOList();
				                              
				mav.addObject("pomList", list);
				model.put("purchaseOrderMasterForm1", purchaseOrderMasterForm);
				model.put("partyList", partyList());
				model.put("branchList", branchList());
				model.put("opr", "R");
				return mav;
			}
		    }

		if (id != null && !id.equals(0)) {

			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage = new PurchaseOrderMasterInputMessage();
			PurchaseOrderMasterDTO purchaseOrderMasterDTO = new PurchaseOrderMasterDTO();
			purchaseOrderMasterDTO.setPoAutoId(id);
			purchaseOrderMasterInputMessage
					.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
			purchaseOrderMasterOutputMessage = purchaseOrderMasterService.findPurchaseOrderMasterById(purchaseOrderMasterInputMessage);
			ArrayList<PurchaseOrderMasterDTO> list = (ArrayList<PurchaseOrderMasterDTO>) purchaseOrderMasterOutputMessage
					.getPurchaseOrderMasterDTOList();
			if (list != null && list.size() > 0) {
				 purchaseOrderMasterDTO = new PurchaseOrderMasterDTO();
				 purchaseOrderMasterDTO=list.get(0);
				 PartyInputMessage partyInputMessage = new PartyInputMessage();
				 PartyDTO partyDTO = new PartyDTO();
				 partyDTO.setPartyId(purchaseOrderMasterDTO.getPartyDTO().getPartyId());
				 partyInputMessage.setPartyDTO(partyDTO);
				PartyOutMessage partyOutMessage= partyService.findPartyById(partyInputMessage);
				ArrayList<PartyDTO> partyList=(ArrayList<PartyDTO>)partyOutMessage.getPartyDTOList();
				if(partyList!=null && partyList.size()>0){
				partyDTO = partyList.get(0);
				}
				
				ArrayList<PurchaseOrderDetailDTO> purchaseDeatilList=(ArrayList<PurchaseOrderDetailDTO>)purchaseOrderMasterDTO.getPurchaseOrderDetailDTOList();
				for(int i=0;i<purchaseDeatilList.size();i++){
					PurchaseOrderDetailDTO purchaseOrderDetailDTO=	purchaseDeatilList.get(i);
					
					if (partyDTO.getVatCstTaxType().equalsIgnoreCase("vat")) {
						
						purchaseOrderDetailDTO.setTaxPerc(purchaseOrderDetailDTO.getVatPerc());
						
					}if (partyDTO.getVatCstTaxType().equalsIgnoreCase("cst with c form")) {
						purchaseOrderDetailDTO.setTaxPerc(purchaseOrderDetailDTO.getCstPerc());
					}if (partyDTO.getVatCstTaxType().equalsIgnoreCase("cst w/o c form")) {
						purchaseOrderDetailDTO.setTaxPerc(purchaseOrderDetailDTO.getVatPerc());
					}
					
				}
				 try {
						if (partyDTO.getVatCstTaxType().equalsIgnoreCase("vat")) {
							purchaseOrderMasterDTO.setTaxVatCstTotal(purchaseOrderMasterDTO.getVatAmount());
						}if (partyDTO.getVatCstTaxType().equalsIgnoreCase("cst with c form")) {
							purchaseOrderMasterDTO.setTaxVatCstTotal(purchaseOrderMasterDTO.getCstAmount());
						}if (partyDTO.getVatCstTaxType().equalsIgnoreCase("cst w/o c form")) {
							purchaseOrderMasterDTO.setTaxVatCstTotal(purchaseOrderMasterDTO.getVatAmount());
						}
						} catch (Exception e) {
						}
				/*ArrayList<PurchaseOrderDetailDTO> detailList=(ArrayList<PurchaseOrderDetailDTO>)purchaseOrderMasterDTO.getPurchaseOrderDetailDTOList();
				
				purchaseOrderMasterDTO.setPurchaseOrderDetailDTOList(detailList);*/
				purchaseOrderMasterDTO.setPurchaseOrderIdSeries(getPurchaseOrderTransactionSeries()+ "/"+ purchaseOrderMasterDTO.getFinYear());
				purchaseOrderMasterForm.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
				
				
			}
		}

		model.put("purchaseOrderMasterForm", purchaseOrderMasterForm);
		model.put("partyList", partyList());
		model.put("branchList", branchList());
		model.put("opr", opr);
		mav = new ModelAndView("purchaseOrder_add");
		return mav;

	}

	@RequestMapping(value = "/remove_purchaseOrder", method = RequestMethod.GET)
	public ModelAndView removeSalesOrder(@RequestParam("id") Integer id,
			ModelMap model,Model model2) {
		logger.info("id id = " + id);
		PurchaseOrderMasterOutputMessage purchaseOrderMasterOutputMessage = null;
		List<PurchaseOrderMasterDTO> list = new ArrayList<PurchaseOrderMasterDTO>();
		PurchaseOrderMasterForm purchaseOrderMasterForm = new PurchaseOrderMasterForm();
		ModelAndView mav = null;
		if (id != null && !id.equals(0)) {
			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage = new PurchaseOrderMasterInputMessage();
			PurchaseOrderMasterDTO purchaseOrderMasterDTO = new PurchaseOrderMasterDTO();
			purchaseOrderMasterDTO.setPoAutoId(id);
			purchaseOrderMasterInputMessage.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
			purchaseOrderMasterInputMessage.setDeletedFlag(true);
			purchaseOrderMasterOutputMessage = purchaseOrderMasterService.deletePurchaseOrderMaster(purchaseOrderMasterInputMessage);
		}

		if (purchaseOrderMasterOutputMessage.getErrorListDTO() != null && purchaseOrderMasterOutputMessage.getErrorListDTO().hasErrors()) {
			mav = new ModelAndView("purchaseOrder_add");
			ErrorDTO errorDTO=purchaseOrderMasterOutputMessage.getErrorListDTO().getErrorList().get(0);
			mav.addObject("errors",errorDTO);
			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage = new PurchaseOrderMasterInputMessage();
			PurchaseOrderMasterDTO purchaseOrderMasterDTO = new PurchaseOrderMasterDTO();
			purchaseOrderMasterDTO.setPoAutoId(id);
			purchaseOrderMasterInputMessage.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
			purchaseOrderMasterOutputMessage = purchaseOrderMasterService.findPurchaseOrderMasterById(purchaseOrderMasterInputMessage);
			ArrayList<PurchaseOrderMasterDTO> list2 = (ArrayList<PurchaseOrderMasterDTO>) purchaseOrderMasterOutputMessage.getPurchaseOrderMasterDTOList();
			if (list2 != null && list2.size() > 0) {
				purchaseOrderMasterForm.setPurchaseOrderMasterDTO(list2.get(0));
			}
			model.put("purchaseOrderMasterForm", purchaseOrderMasterForm);
			model.put("partyList", partyList());
			model.put("branchList", branchList());
			model.put("opr", "R");
		} else {
			mav = new ModelAndView("purchaseOrder_list");
			purchaseOrderMasterOutputMessage = purchaseOrderMasterService.findAllPurchaseOrderMasters();
			list = (ArrayList<PurchaseOrderMasterDTO>) purchaseOrderMasterOutputMessage.getPurchaseOrderMasterDTOList();
			mav.addObject("purchaseOrderMasterForm1", purchaseOrderMasterForm);
			mav.addObject("pomList", list);
		}
		ErrorDTO errorDTO=new ErrorDTO();
		errorDTO.setErrorMsg("Successfully Record Deleted !!!");
		
		mav.addObject("errorDl", errorDTO);	
		return mav;
	}

	private boolean isDuplicateItem(PurchaseOrderMasterDTO purchaseOrderMasterDTO, Integer itemId) {

		if (purchaseOrderMasterDTO != null && purchaseOrderMasterDTO.getPurchaseOrderDetailDTOList() != null) {
			List<PurchaseOrderDetailDTO> list = purchaseOrderMasterDTO.getPurchaseOrderDetailDTOList();
			logger.info("list-----" + list.size());
			for (PurchaseOrderDetailDTO e : list) {

				if (itemId.equals(e.getItemDTO().getItemId()))
					return true;
			}
		}
		return false;
	}

	// @ModelAttribute("partyList")
	public List<PartyDTO> partyList() {
		//PartyOutMessage partyOutMessage = partyService.findAllPartys();
		PartyOutMessage partyOutMessage = partyService.findCreditorPartyList();
		ArrayList<PartyDTO> list = (ArrayList<PartyDTO>) partyOutMessage
				.getPartyDTOList();
		return list;
	}

	@ModelAttribute("branchList")
	public List<BranchDTO> branchList() {
		BranchOutMessage branchOutMessage = branchService.findAllBranches();
		ArrayList<BranchDTO> list = (ArrayList<BranchDTO>) branchOutMessage
				.getBranchDTOList();
		return list;
	}

	@ModelAttribute("itemGroupList")
	public List<ItemGroupFlagDTO> itemGroupList() {
		ItemGroupFlagOutMessage itemGroupFlagOutMessage = itemGroupFlagService
				.findAllItemGroupFlag();
		ArrayList<ItemGroupFlagDTO> list = (ArrayList<ItemGroupFlagDTO>) itemGroupFlagOutMessage
				.getItemGroupFlagDTOList();
		return list;
	}

	@ModelAttribute("transporterList")
	public List<TransporterDTO> transporterList() {
		TransporterOutMessage transporterOutMessage = transporterService
				.preload();
		ArrayList<TransporterDTO> list = (ArrayList<TransporterDTO>) transporterOutMessage
				.getTransporterDTOList();
		return list;
	}

	@ModelAttribute("formType")
	public List<MastersDTO> formTypeList() {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(12);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

	

	  
	   
	   
	   //Get Indent List for purchase order
	   
		@RequestMapping(value = "/get_indent_list_for_purchase")
		public ModelAndView searchIssue(
		@ModelAttribute("indtDTO") IndentMasterDTO indtDTO,
		@ModelAttribute("indentMasterForms") IndentMasterForm indentMasterForm,
		Model model,
		@RequestParam(value = "menuId", required = false) String menuId,
		HttpSession session,@RequestParam(value="next",required=false) Integer next) {
		if (menuId != null) {
		session.setAttribute("menuId", menuId);
		}

		List<IndentMasterDTO> list = new ArrayList<IndentMasterDTO>();
		IndentMasterDTO indentDTO=new IndentMasterDTO();
		logger.info("searchIssue : ");
		IndentOutputMessage indentOutputMessage = null;
		IndentInputMessage indentInputMessage = new IndentInputMessage();

		
		try{
		if(indtDTO.getFromDate()!=null || indtDTO.getToDate()!=null||
				indtDTO.getIndentNumber()!=null
		||indtDTO.getItemName()!=null){
			if(indtDTO.getFromDate()!=null){
				indtDTO.setFromDate(indtDTO.getFromDate());
			}if(indtDTO.getToDate()!=null){
				indtDTO.setToDate(indtDTO.getToDate());
			}if(indtDTO.getItemName()!=null){
				indtDTO.setItemName(indtDTO.getItemName());
			}
			
		indentInputMessage.setIndentMasterDTO(indtDTO);
		indentOutputMessage = indentMasterService.search(indentInputMessage);

		list = (ArrayList<IndentMasterDTO>) indentOutputMessage.getIndentMasterDTOList();

		
		
		}
		else{
		if(next==null ||next<0)
		{
		next=0;
		indentDTO.setNext(next);
		indentInputMessage.setIndentMasterDTO(indentDTO);
		indentOutputMessage=indentMasterService.findIndentMasterPagination(indentInputMessage);
		}
		else
		{
		indentDTO.setNext(next);
		indentInputMessage.setIndentMasterDTO(indentDTO);
		indentOutputMessage=indentMasterService.findIndentMasterPagination(indentInputMessage);
		}

		indentDTO.setNext(next);
		indentDTO.setPrevious(next);
		list = (ArrayList<IndentMasterDTO>) indentOutputMessage.getIndentMasterDTOList();
		}
		}
		catch (Exception e) {
		}

		if (list != null && list.size() > 0) {
		for (int i = 0; i < list.size(); i++) {
		IndentMasterDTO indentMasterDTO = list.get(i);

		
		
		
		
		
		List<IndentDetailDTO> detailList= indentMasterDTO.getIndentDetailDTO();
		for(int j=0;j<detailList.size();j++){
			IndentDetailDTO detailDTO=detailList.get(j);
			//System.out.println("PPPPPPPPPPPPPENDING QTY :"+detailDTO.getPendingQty());
			try {
				if(detailDTO.getPendingQty()<=0 || detailDTO.getFullFill()==0){
					detailList.remove(detailDTO);
					j=j-1;
				}
				if(indentMasterDTO.getIndentDetailDTO().size()==0){
					list.remove(indentMasterDTO);
					i=i-1;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		Integer depertmentId = indentMasterDTO.getDepartmentId();

		// For Department Name
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		MastersDTO mastersDTO = new MastersDTO();
		mastersDTO.setMastersId(depertmentId);
		mastersInputMessage.setMastersDTO(mastersDTO);
		MastersOutputMessage mastersOutputMessage = mastersService
		.findMastersById(mastersInputMessage);

		ArrayList<MastersDTO> masterList = (ArrayList<MastersDTO>) mastersOutputMessage
		.getMastersDTOList();

		if (masterList != null && masterList.size() > 0) {
		mastersDTO = masterList.get(0);
		}
		indentDTO.setDepartmentName(mastersDTO.getName());
		
		}

		}
		
		ModelAndView mav = new ModelAndView("indent-list_for_purchase");
		mav.addObject("indentList", list);
		indentMasterForm.setIndentMasterDTO(indentDTO);
		mav.addObject("indentMasterForm", indentMasterForm);

		
		return mav;
		}

	   //Get And Add Item in Purchase Order From Indent
	   
		@RequestMapping(value = "/getIndentItem_for_purchaseOrder", method = RequestMethod.GET)
		public @ResponseBody
		ModelAndView getIndentItemForPurchase(
				@ModelAttribute("indentNumber") String indentNumber, ModelMap model,
				@ModelAttribute("purchaseOrderMasterForm") PurchaseOrderMasterForm purchaseOrderMasterForm) {
			PurchaseOrderMasterDTO purchaseOrderMasterDTO=purchaseOrderMasterForm.getPurchaseOrderMasterDTO();
			 if(purchaseOrderMasterDTO==null){
				 purchaseOrderMasterDTO=new PurchaseOrderMasterDTO();
			 }
			
			List<PurchaseOrderDetailDTO> purchaseDetailList=null;
			purchaseDetailList=purchaseOrderMasterDTO.getPurchaseOrderDetailDTOList();
			if(purchaseDetailList==null){
				purchaseDetailList=new ArrayList<PurchaseOrderDetailDTO>();
			}
			IndentInputMessage indentInputMessage = new IndentInputMessage();
			IndentMasterDTO indentMasterDTO = new IndentMasterDTO();
			indentMasterDTO.setIndentNumber(indentNumber);
			indentInputMessage.setIndentMasterDTO(indentMasterDTO);
			IndentOutputMessage indentOutputMessage = indentMasterService.findIndentMasterByIndentNumber(indentInputMessage);
			List<IndentMasterDTO> list= indentOutputMessage.getIndentMasterDTOList();
			if(list!=null && list.size()>0){
				indentMasterDTO=list.get(0);
				purchaseOrderMasterDTO.setIndentDate(indentMasterDTO.getIndentDate());
				purchaseOrderMasterDTO.setIndentNumber(indentMasterDTO.getIndentNumber());
				
				
				ArrayList<IndentDetailDTO> detailList=(ArrayList<IndentDetailDTO>)indentMasterDTO.getIndentDetailDTO();
				for(int i=0;i<detailList.size();i++){
					IndentDetailDTO detailDTO= detailList.get(i);
					PurchaseOrderDetailDTO purchaseOrderDetailDTO = new PurchaseOrderDetailDTO();
					
					
					int fullFill=1;
					try{
					if(detailDTO.getFullFill()==0)
					{
						fullFill=0;
					}else{
						 fullFill=1;
					}
					}catch(Exception e){
						e.printStackTrace();
					}
					//To prevent item whose quantity less than zero
					if((detailDTO.getPendingQty()>0 && fullFill!=0) ){
		
						
					//To Add Item Detail Start
					ItemInputMessage itemInputMessage = new ItemInputMessage();
					ItemDTO itemDTO = new ItemDTO();
					itemDTO=detailDTO.getItemDTO();
					itemDTO.setItemId(detailDTO.getItemId());
					itemInputMessage.setItemDTO(itemDTO);
					ItemOutMessage itemOutMessage = itemService.findItemById(itemInputMessage);
					 List<ItemDTO> itemDtoList=itemOutMessage.getItemDTOList();
					 if (itemDtoList != null && itemDtoList.size() > 0) {
						itemDTO = itemDtoList.get(0);
						BeanUtils.copyProperties(itemDTO, purchaseOrderDetailDTO);
						purchaseOrderDetailDTO.setItemDTO(itemDTO);
						if (itemDTO.getMasterUnit() != null) {
							purchaseOrderDetailDTO.setMeasurementUnitId(itemDTO
									.getMasterUnit().getMastersId());
							purchaseOrderDetailDTO.setMeasurementUnitName(itemDTO
									.getMasterUnit().getName());
						}
						
						PartyDTO partyDTO = purchaseOrderMasterForm.getPurchaseOrderMasterDTO().getPartyDTO();
						if (partyDTO != null) {
							if ("saleswithInState".equals(partyDTO.getInvoiceType()) || "localPurchase".equals(partyDTO.getInvoiceType()))
								purchaseOrderDetailDTO.setTaxPerc(itemDTO.getVatPerc());
							if ("salesOutsideState".equals(partyDTO.getInvoiceType())||"importPurchase".equals(partyDTO.getInvoiceType()))
								purchaseOrderDetailDTO.setTaxPerc(itemDTO.getCstPerc());
						}}
		//End Add Item Detail End
					
					
					
					
					
					purchaseOrderDetailDTO.setItemDTO(detailDTO.getItemDTO());
					purchaseOrderDetailDTO.setMeasurementUnitId(detailDTO.getMeasurementUnitId().getMastersId());
					purchaseOrderDetailDTO.setMeasurementUnitName(detailDTO.getMeasurementUnitId().getName());
					purchaseOrderDetailDTO.setItemQuantity(detailDTO.getIndentQty());
					purchaseOrderDetailDTO.setIndentNumber(detailDTO.getIndentNumber()); 
					purchaseDetailList.add(purchaseOrderDetailDTO);
				    }
				}
				
				purchaseOrderMasterDTO.setPurchaseOrderDetailDTOList(purchaseDetailList);
				
			}
			//End to prevent item whose quantity less than zero or status is full fill
			purchaseOrderMasterForm.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
			String opr="add";
			try {
				if(purchaseOrderMasterDTO!=null && purchaseOrderMasterDTO.getPoAutoId()!=null){
					opr="E";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.put("opr", opr);
			ModelAndView mav = new ModelAndView("purchaseOrder_add");
			model.put("purchaseOrderMasterForm", purchaseOrderMasterForm);
			return mav;
		}
		/*public static void main(String[] args) throws Exception{
			BufferedReader reader = new BufferedReader(new FileReader("F://abc.txt"));
			List<String> lines = new ArrayList<String>();
			String line = null;
			int i=0;
			while ((line = reader.readLine()) != null) {
			    lines.add(line);
			    i++;
			}
		System.out.println("Data is in file:"+lines.get(i-2) +"   "+lines.get(i-3)+"   "+lines.get(i-4));
		File file=new File("F://abc.txt"); 

		}*/
		
		public static void main(String[] args) {

		    File file = new File("F://gaurav.ht");
		    FileInputStream fis = null;
		    BufferedInputStream bis = null;
		    DataInputStream dis = null;

		    try {
		      fis = new FileInputStream(file);

		      // Here BufferedInputStream is added for fast reading.
		      bis = new BufferedInputStream(fis);
		      dis = new DataInputStream(bis);

		      // dis.available() returns 0 if the file does not have more lines.
		      while (dis.available() != 0) {

		      // this statement reads the line from the file and print it to
		        // the console.
		        System.out.println(dis.readLine());
		      }

		      // dispose all the resources after using them.
		      fis.close();
		      bis.close();
		      dis.close();

		    } catch (FileNotFoundException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		  }
		 @RequestMapping(value = "/purchase_order_print_report/pdf", method = RequestMethod.GET )
		    public  ModelAndView doPurchaseOrderReportPDF(@RequestParam("PurchaseOrderNoPrompt") String PONoPrompt,ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request) 
				 {
				logger.debug("Received request to download PDF report");
				response.setHeader("filename","purchase_order_print_report.pdf");
				response.setContentType("application/pdf");
		        //response.setHeader("Content-Disposition", "attachment:filename=_blank‌​");
				Map<String,Object> parameterMap = new HashMap<String,Object>();
				parameterMap.put("datasource", dataSource);
				parameterMap.put("PONoPrompt", PONoPrompt);
				
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
				modelAndView = new ModelAndView("pdfPurchaseOrderPrintView", parameterMap);
				// Return the View and the Model combined
				return modelAndView;
			   }
		   
}