package com.advanz.erp.client.http.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.ItemForm;
import com.advanz.erp.client.http.controller.form.QuotationMasterForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.QuotationDetailDTO;
import com.advanz.erp.masters.model.QuotationMasterDTO;
import com.advanz.erp.masters.model.TransactionTypeDTO;
import com.advanz.erp.masters.model.criteria.ItemSearchCriteriaDTO;
import com.advanz.erp.masters.model.criteria.QuotationMasterSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.PartyInputMessage;
import com.advanz.erp.masters.model.msg.PartyOutMessage;
import com.advanz.erp.masters.model.msg.QuotationMasterInputMessage;
import com.advanz.erp.masters.model.msg.QuotationMasterOutputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeInputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeOutputMessage;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IPartyService;
import com.advanz.erp.masters.service.business.IQuotationMasterService;
import com.advanz.erp.masters.service.business.ITransactionTypeService;

@Controller
@SessionAttributes({ "quotationMasterForm", "partyMap", "branchList","partyList",
	"itemsMap", "opr" })
	public class QuotationController extends BaseController {
	private static final Logger logger = LoggerFactory
	.getLogger(QuotationController.class);

	@Autowired
	public IItemService itemService;
	@Autowired
	public IPartyService partyService;
	@Autowired
	public IBranchService branchService;
	@Autowired
	public ITransactionTypeService transactionTypeService;
	@Autowired
	public IQuotationMasterService quotationMasterService;
	@Autowired
	public IMastersService mastersService;
	private List<PartyDTO> partyDTOList;
	
	@RequestMapping("/new_quotation")
	public String newQuotation(ModelMap model) {
		QuotationMasterForm quotationMasterForm = new QuotationMasterForm();
		QuotationMasterDTO quotationMasterDTO = new QuotationMasterDTO();
		String series = getQuotationTransactionSeries();
		logger.info("Series=" + series);
		quotationMasterDTO.setFinYear(getFinYear());
		quotationMasterDTO.setTransactionSeries(series);

		QuotationMasterInputMessage quotationMasterInputMessage = new QuotationMasterInputMessage();
		quotationMasterInputMessage.setQuotationMasterDTO(quotationMasterDTO);
		QuotationMasterOutputMessage quotationMasterOutputMessage = quotationMasterService
		.getNewQuotationSeriesNo(quotationMasterInputMessage);
		Integer orderID = quotationMasterOutputMessage.getQuotationSeriesNo();
		Timestamp timestamp= quotationMasterOutputMessage.getQuotationSeriesDate();
		//System.out.println("Last Finished good time"+new Date(timestamp.getTime()));
		SimpleDateFormat ft =new SimpleDateFormat ("yyyy,MM,dd");
		
		quotationMasterDTO.setQuotationId(orderID);
		
		quotationMasterDTO.setQuotationNumber(quotationMasterDTO
				.getTransactionSeries()
				+ "/"
				+ quotationMasterDTO.getFinYear()
				+ "/" + quotationMasterDTO.getQuotationId());
		quotationMasterDTO.setQuotationDate(new Date());
		
		quotationMasterDTO.setPartyDTO(getFirstPartyInList());

		quotationMasterForm.setQuotationMasterDTO(quotationMasterDTO);
		
		quotationMasterForm.setLastQuotationDate(ft.format(new Date(timestamp.getTime())));
		// quotationMasterForm.setQuotationMasterDTO(new
		// QuotationMasterDTO());
		model.put("quotationMasterForm", quotationMasterForm);
		model.put("partyMap", partyMap());
		model.put("partyList", partyList());
		model.put("branchList", branchList());
		model.put("itemsMap", itemsMap());
		model.put("opr", "N");
		logger.info("IN newQuotation() quotationMasterForm-->"
				+ quotationMasterForm);
		return "quotation-entry";
	}

	@RequestMapping(value = "/show_item_list_qo")
	public ModelAndView showItemSelectionForm(@ModelAttribute("quotationMasterForm") QuotationMasterForm quotationMasterForm,
			@ModelAttribute("searchCriteria") ItemSearchCriteriaDTO searchCriteria,
			@RequestParam(value = "itemID", required = false) Integer itemId,
			@RequestParam(value = "btn", required = false) String btn,@ModelAttribute("itemsMap") Map<Integer, ItemDTO> itemMap,@RequestParam(value = "operation", required = false) String operation,@RequestParam(value = "next", required = false) Integer next) {
		logger.info("--------------------------Button=" + btn);
		logger.info("--------------------------ItemId=" + itemId);
		// code to add item in quotation
		if (btn != null) {
			QuotationMasterDTO quotationMasterDTO = quotationMasterForm.getQuotationMasterDTO();
			
			if (itemId != null) {
				if (!isDuplicateItem(quotationMasterDTO, itemId)) {

					ItemDTO itemDTO = itemMap.get(itemId);
					if (itemDTO != null) {
						QuotationDetailDTO quotationDetailDTO = new QuotationDetailDTO();
						BeanUtils.copyProperties(itemDTO, quotationDetailDTO);
						quotationDetailDTO.setQuotationNumber(quotationMasterDTO.getQuotationNumber());
						quotationDetailDTO.setTransactionSeries(quotationMasterDTO.getTransactionSeries());
						quotationDetailDTO.setItemName(itemDTO.getInvoiceName());
						// quotationDetailDTO.setPartyInvoiceType(quotationMasterDTO.getPartyDTO().getInvoiceType());
						PartyDTO partyDTO = new PartyDTO();
						partyDTO.setPartyId(quotationMasterDTO.getPartyDTO().getPartyId());
						PartyInputMessage partyInputMessage = new PartyInputMessage();
						partyInputMessage.setPartyDTO(partyDTO);
						PartyOutMessage partyOutMessage= partyService.findPartyById(partyInputMessage);
						List<PartyDTO> pl= partyOutMessage.getPartyDTOList();
						 partyDTO =pl.get(0);
						
						
						quotationDetailDTO.setQuantity(1.0); // default Quantity
						if (!"excisable".equals(itemDTO.getExciseTypeFlag())) {
							quotationDetailDTO.setExcisePerc(0.0);
						}
						if ("saleswithInState".equals(partyDTO.getInvoiceType()) ||"localPurchase".equals(partyDTO.getInvoiceType())) {
							quotationMasterDTO.setTaxType("VAT");
							quotationDetailDTO.setTaxPerc(quotationDetailDTO.getVatPerc());
						} else {
							quotationMasterDTO.setTaxType("CST");
							quotationDetailDTO.setTaxPerc(quotationDetailDTO.getCstPerc());
						}

						if (quotationMasterDTO.getQuotationDetailDTOList() == null) {
							quotationMasterForm.getQuotationMasterDTO().setQuotationDetailDTOList(new ArrayList<QuotationDetailDTO>());
						}
						logger.info(itemDTO.getMasterUnit()+" : "+ itemDTO.getMasterUnitDTO());
						quotationDetailDTO.setMeasurementUnitMasterDTO(itemDTO.getMasterUnit());
						quotationDetailDTO.setTransientObject(true);
						quotationMasterDTO.getQuotationDetailDTOList().add(quotationDetailDTO);

						calcQuotation(quotationMasterDTO);
					}
				}
				// calcQuotation(quotationMasterForm.getQuotationMasterDTO());
			}
			// return "redirect:/new_quotation";
			ModelAndView mav = new ModelAndView("quotation-entry");
			
			// mav.addObject("quotationMasterForm1",quotationMasterForm);
			return mav;
		}
		//code to show Item List
		ModelAndView mav = new ModelAndView("item-list-for-so");
		if (searchCriteria == null) {
			searchCriteria = new ItemSearchCriteriaDTO();
		}
		ItemOutMessage itemOutMessage = null; // itemService.findAllItem();
		ItemInputMessage itemInputMessage = new ItemInputMessage();
		ItemDTO itemDTO = new ItemDTO();
		if("search".equalsIgnoreCase(operation)){
		if (searchCriteria != null) {
			itemDTO.setInvoiceName(searchCriteria.getInvoiceName());
			itemDTO.setItemCode(searchCriteria.getItemCode());
		}
		itemInputMessage.setItemDTO(itemDTO);
		// itemOutMessage = itemService.findItem(itemInputMessage);
		itemOutMessage = itemService.searchFinishedFoodItems(itemInputMessage);
		
		}else{
			
//Pagination start
			
			if(next==null ||next<0)
			{
				next=0;
				itemDTO.setNext(next);
				itemInputMessage.setItemDTO(itemDTO);
				itemOutMessage= itemService.finishGoodItemListWithPagination(itemInputMessage);
				next=13;
			}
			else
			{
				
				itemDTO.setNext(next);
				itemInputMessage.setItemDTO(itemDTO);
				itemOutMessage= itemService.finishGoodItemListWithPagination(itemInputMessage);
				next=next+13;
			}
			// Pagination end
		}
		
		ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
		itemMap.clear();
	//	Map<Integer, MastersDTO> uomMap=getMesurementUnitMastersMap();
		for (ItemDTO e : list) {
		//	e.setMasterUnitDTO(uomMap.get(e.getMasterUnit()));
			e.setMasterUnitDTO(e.getMasterUnit());
			itemMap.put(e.getItemId(), e);
		}
		if (quotationMasterForm.getQuotationMasterDTO() != null) {
		List<QuotationDetailDTO> quotationDetailDTOList = quotationMasterForm.getQuotationMasterDTO().getQuotationDetailDTOList();
		  if (quotationDetailDTOList != null) {
			for (QuotationDetailDTO quotationDetailDTO : quotationDetailDTOList) {
			Iterator<ItemDTO> itr = list.iterator();
			while (itr.hasNext()) {
			ItemDTO temp = itr.next();
			if (!quotationDetailDTO.getDeletedFlag() && temp.getItemId().equals(quotationDetailDTO.getItemId())) {
				itr.remove();
						}
					}
				}
			}
		}
		mav.addObject("searchCriteria", searchCriteria);
		mav.addObject("itemList", list);
		mav.addObject("itemFor", "Q");
		ItemForm itemForm = new ItemForm();
		
		try {
			itemDTO.setNext(next);
			itemDTO.setPrevious(next-26);
		} catch (Exception e1) {
			
		}
		
		itemForm.setItemDTO(itemDTO);
		mav.addObject("itemForm", itemForm);
		return mav;
	}

	@RequestMapping("/removeItemFromQO")
	public String removeItemFrom(
			@ModelAttribute("quotationMasterForm") QuotationMasterForm quotationMasterForm,
			@RequestParam("index") Integer index,
			@ModelAttribute("opr") String opr) {

		List<QuotationDetailDTO> detailDTOList = quotationMasterForm
		.getQuotationMasterDTO().getQuotationDetailDTOList();
		if (detailDTOList != null && detailDTOList.size() > index) {
			logger.info("index=" + index);
			logger.info("opr=" + opr);
			QuotationDetailDTO dto = detailDTOList.get(index);
			if (StringUtils.hasText(opr) && "E".equals(opr)
					&& !dto.isTransientObject()) {
				dto.setDeletedFlag(true);
			} else {
				detailDTOList.remove(dto);
			}
			logger.info("List : " + detailDTOList);
		}
		calcQuotation(quotationMasterForm.getQuotationMasterDTO());
		//ModelAndView mav = new ModelAndView("quotation-entry");
		return "quotation-entry";
	}

	@RequestMapping("/addItemInQO")
	public ModelAndView addItemInQuotation(
			@ModelAttribute("quotationMasterForm") QuotationMasterForm quotationMasterForm,
			@RequestParam("itemID") Integer itemId,
			@ModelAttribute("itemsMap") Map<Integer, ItemDTO> itemMap) {
		logger.info("IN addItem()  quotationMasterForm-->"
				+ quotationMasterForm);
		logger.info("Add Item : " + itemId);
		QuotationMasterDTO quotationMasterDTO = quotationMasterForm
		.getQuotationMasterDTO();
		if (itemId != null) {
			if (!isDuplicateItem(quotationMasterDTO, itemId)) {

				ItemDTO itemDTO = itemMap.get(itemId);
				if (itemDTO != null) {
					QuotationDetailDTO quotationDetailDTO = new QuotationDetailDTO();
					BeanUtils.copyProperties(itemDTO, quotationDetailDTO);
					quotationDetailDTO.setQuotationNumber(quotationMasterDTO.getQuotationNumber());
					quotationDetailDTO.setTransactionSeries(quotationMasterDTO.getTransactionSeries());
					quotationDetailDTO.setItemName(itemDTO.getInvoiceName());
					// quotationDetailDTO.setPartyInvoiceType(quotationMasterDTO.getPartyDTO().getInvoiceType());
					quotationDetailDTO.setQuantity(1.0); // default Quantity
					if (!"excisable".equals(itemDTO.getExciseTypeFlag())) {
						quotationDetailDTO.setExcisePerc(0.0);
					}
					
					if ("saleswithInState".equals(quotationMasterDTO.getPartyDTO().getInvoiceType())) {
						quotationMasterDTO.setTaxType("VAT");
						quotationDetailDTO.setTaxPerc(quotationDetailDTO.getVatPerc());
					} else {
						quotationMasterDTO.setTaxType("CST");
						quotationDetailDTO.setTaxPerc(quotationDetailDTO.getCstPerc());
					}

					if (quotationMasterDTO.getQuotationDetailDTOList() == null) {
						quotationMasterForm.getQuotationMasterDTO()
						.setQuotationDetailDTOList(
								new ArrayList<QuotationDetailDTO>());
					}
					logger.info(itemDTO.getMasterUnit()+" : "+ itemDTO.getMasterUnitDTO());
					quotationDetailDTO.setMeasurementUnitMasterDTO(itemDTO.getMasterUnit());


					quotationDetailDTO.setTransientObject(true);
					quotationMasterDTO.getQuotationDetailDTOList().add(
							quotationDetailDTO);

					calcQuotation(quotationMasterDTO);
				}
			}
			// calcQuotation(quotationMasterForm.getQuotationMasterDTO());
		}
		// return "redirect:/new_quotation";
		ModelAndView mav = new ModelAndView("quotation-entry");
		// mav.addObject("quotationMasterForm1",quotationMasterForm);
		return mav;
	}

	@RequestMapping(value = "/qoparty_changed", method = RequestMethod.POST)
	public ModelAndView partyChanged(
			@ModelAttribute("quotationMasterForm") QuotationMasterForm quotationMasterForm,
			@RequestParam("partyId") Integer partyId) {
		logger.info("Party Id=" + partyId);
		PartyDTO partyDTO = partyMap().get(partyId);
		if (partyDTO != null) {

			quotationMasterForm.getQuotationMasterDTO().setPartyDTO(partyDTO);
			calcQuotation(quotationMasterForm.getQuotationMasterDTO());
			
		}
		logger.info("Part DTO :" + partyDTO);
		logger.info("-------Sales Order Form : " + quotationMasterForm);
		ModelAndView mav = new ModelAndView("quotation-entry");
		return mav;
	}

	@RequestMapping(value = "/backto_quotation", method = RequestMethod.GET)
	public ModelAndView backToSalesOrder(
			@ModelAttribute("quotationMasterForm") QuotationMasterForm quotationMasterForm) {
		calcQuotation(quotationMasterForm.getQuotationMasterDTO());
		ModelAndView mav = new ModelAndView("quotation-entry");
		return mav;
	}

	@RequestMapping(value = "/processQuotation", method = RequestMethod.POST)
	public String saveQuotation(ModelMap modelMap,
			@ModelAttribute("quotationMasterForm") QuotationMasterForm quotationMasterForm,
			BindingResult result, Model model,@ModelAttribute("opr")String opr,@RequestParam(value="remItemBtn",required=false)String btn) {
		logger.info("Opr : "+opr);
		logger.info("btn : "+btn);
		if(btn!=null){
			int index=Integer.parseInt(btn);
			return removeItemFrom(quotationMasterForm, index, opr);
		}
		logger.info("quotationForm = " + quotationMasterForm);


		Date qdate = quotationMasterForm.getQuotationMasterDTO()
		.getQuotationDate();
		logger.info("QDATE=" + qdate);
		ErrorListDTO errorListDTO = new ErrorListDTO();
		if (qdate != null) {
			Date curDate = new Date();
			Date validUpToDate = quotationMasterForm.getQuotationMasterDTO()
			.getValidUpTo();
			Date refDate = quotationMasterForm.getQuotationMasterDTO()
			.getReferenceDate();
			if (qdate.after(curDate)) {
				ErrorDTO error = new ErrorDTO();
				error.setErrorMsg("Quotation Date should not be greater than current Date");
				errorListDTO.addError(error);
			}
			if (refDate!=null){
				if (refDate.after(qdate)) {
					ErrorDTO error = new ErrorDTO();
					error.setErrorMsg("Reference Date should not be greater than Quotation Date");
					errorListDTO.addError(error);
				}
			}
			if (validUpToDate != null) {
				if (validUpToDate.before(qdate)) {
					ErrorDTO error = new ErrorDTO();
					error.setErrorMsg("Quotation Valid upto Date should not be less than Quotation Date");
					errorListDTO.addError(error);
				}
			}
			if (validUpToDate != null && refDate != null) {
				if (validUpToDate.before(refDate)) {
					ErrorDTO error = new ErrorDTO();
					error.setErrorMsg("Quotation Valid upto Date should not be less than Reference Date");
					errorListDTO.addError(error);
				}
			}
		}

		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors", errorDTO);
			return "quotation-entry";
		}
		int itemCount = 0;
		if (quotationMasterForm.getQuotationMasterDTO().getQuotationDetailDTOList() != null	&& quotationMasterForm.getQuotationMasterDTO().getQuotationDetailDTOList().size() > 0) {
			for (QuotationDetailDTO d : quotationMasterForm.getQuotationMasterDTO().getQuotationDetailDTOList()) {
				if (!d.getDeletedFlag()) {
					if (d.getQuantity().doubleValue() == 0.0) {
						ErrorDTO error = new ErrorDTO();
						error.setErrorMsg("Item Quantity value can not be 0 (Zero)");
						model.addAttribute("errors", error);
						return "quotation-entry";
					}
					if (d.getSalesRate().doubleValue() == 0.0) {
						ErrorDTO error = new ErrorDTO();
						error.setErrorMsg("Item Basic Rate value can be 0 (Zero)");
						model.addAttribute("errors", error);
						return "quotation-entry";
					}
					itemCount++;
				}
			}
		}
		if (itemCount == 0) {
			ErrorDTO error = new ErrorDTO();
			error.setErrorMsg("Warning - Add at least one Item");
			model.addAttribute("errors", error);
			return "quotation-entry";
		}
		QuotationMasterInputMessage quotationMasterInputMessage = new QuotationMasterInputMessage();
		quotationMasterInputMessage.setQuotationMasterDTO(quotationMasterForm.getQuotationMasterDTO());

		QuotationMasterOutputMessage quotationMasterOutputMessage;
		String succ="";
		QuotationMasterDTO dto= quotationMasterForm.getQuotationMasterDTO();
		if (quotationMasterForm.getQuotationMasterDTO().getQuotationAutoId() == null|| quotationMasterForm.getQuotationMasterDTO().getQuotationAutoId().equals(0)) {
		
			ArrayList<QuotationDetailDTO> quoDetaiList= (ArrayList<QuotationDetailDTO>)dto.getQuotationDetailDTOList();
		   Integer quotationId=dto.getQuotationId();
			quotationId=quotationId;
			for(int i=0;i<quoDetaiList.size();i++){
				QuotationDetailDTO quotationDetailDTO= quoDetaiList.get(i);
				quotationDetailDTO.setQuotationNumber(getQuotationTransactionSeries()+"/"+getFinYear()+"/"+quotationId);
				quotationDetailDTO.setCreatedUserId(getCreatedUserId());
			}
			dto.setCreatedUserId(getCreatedUserId());
			quotationMasterOutputMessage = quotationMasterService.createQuotationMaster(quotationMasterInputMessage);
			succ="Ad";
		} else {
			dto.setModifiedUserId(getCreatedUserId());
			quotationMasterOutputMessage = quotationMasterService.updateQuotationMaster(quotationMasterInputMessage);
			succ="Up";
		}
		if(quotationMasterOutputMessage!=null)
		errorListDTO = quotationMasterOutputMessage.getErrorListDTO();
		
		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			QuotationMasterDTO quotationMasterDTO= quotationMasterForm.getQuotationMasterDTO();
		Integer quotationId	=quotationMasterDTO.getQuotationId();
		quotationMasterDTO.setQuotationId(quotationId+1);
		quotationId=quotationId+1;
		quotationMasterDTO.setQuotationNumber(getQuotationTransactionSeries()+"/"+getFinYear()+"/"+quotationId);
			model.addAttribute("quotationMasterForm",quotationMasterForm);
			model.addAttribute("errors",errorDTO);
			return "quotation-entry";
		}
		
		QuotationMasterDTO quotationMasterDTO= quotationMasterForm.getQuotationMasterDTO();
		quotationMasterDTO.setQuotationDetailDTOList(null);
		modelMap.addAttribute("quotationMasterForm", quotationMasterForm);
		modelMap.addAttribute("succ", succ);
		return "redirect:/get_quotation_list";

	}

	@RequestMapping(value = "/get_quotation_list")
	public ModelAndView searchQuotation(@ModelAttribute("qoSearchCriteria") QuotationMasterSearchCriteriaDTO searchCriteria,
			@ModelAttribute("quotationMasterForms") QuotationMasterForm quotationMasterForm,
			@ModelAttribute("RT") String rt,@ModelAttribute("qid")String qidStr,@RequestParam(value="btn",required=false)String btn,Model model
			,@RequestParam(value="menuId",required=false) String menuId,HttpSession session,@RequestParam(value="next",required=false) Integer next,@RequestParam(value="opr",required=false) String opr) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
		logger.info("RT=" + rt);
		logger.info("btn=" + btn);
		if(btn!=null){
			ModelAndView mav=new ModelAndView("forward:new_salesOrder");
			mav.addObject("qid", qidStr);
			return mav;
		}
		List<QuotationMasterDTO> list = new ArrayList<QuotationMasterDTO>();

		QuotationMasterOutputMessage quotationOutputMessage = null;
		QuotationMasterInputMessage quotationInputMessage = new QuotationMasterInputMessage();
		if("search".equalsIgnoreCase(opr)){
		quotationInputMessage.setSearchCriteria(searchCriteria);
		quotationOutputMessage = quotationMasterService.search(quotationInputMessage);
		}else{
			quotationInputMessage = new QuotationMasterInputMessage();
			if(next==null ||next<0)
			{
			next=0;
			quotationInputMessage.setNext(next);
			quotationOutputMessage = quotationMasterService.findQuotationForPagination(quotationInputMessage);
			}
			else
			{
				quotationInputMessage.setNext(next);
				quotationOutputMessage = quotationMasterService.findQuotationForPagination(quotationInputMessage);
			}
			quotationMasterForm.setNext(next);
			quotationMasterForm.setPrevious(next);
			
		}
		list = (ArrayList<QuotationMasterDTO>) quotationOutputMessage.getQuotationMasterDTOList();
		ModelAndView mav = new ModelAndView("quotation-list");
		mav.addObject("qmList", list);
		if (StringUtils.hasText(rt))
			mav.addObject("RT", rt);
		String succ="Blk";
		
		
		if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}
		mav.addObject("quotationMasterForms", quotationMasterForm);
		return mav;
	}

	
	@RequestMapping(value = "/get_quotation", method = RequestMethod.GET)
	public ModelAndView getQuotationData(@RequestParam("id") Integer id,
			@RequestParam("opr") String opr, ModelMap model) {
		QuotationMasterForm quotationMasterForm = new QuotationMasterForm();
		logger.info("Get Quotation : " + id);
		logger.info("Opr : " + opr);

		QuotationMasterOutputMessage quotationMasterOutMessage = null;
		if (id != null && !id.equals(0)) {
			QuotationMasterInputMessage quotationMasterInputMessage = new QuotationMasterInputMessage();
			QuotationMasterDTO quotationMasterDTO = new QuotationMasterDTO();
			quotationMasterDTO.setQuotationAutoId(id);
			quotationMasterInputMessage
			.setQuotationMasterDTO(quotationMasterDTO);
			if("R".equals(opr)){
				quotationMasterOutMessage = quotationMasterService.checkBeforeRemove(quotationMasterInputMessage);
				if(quotationMasterOutMessage!=null){
					ErrorListDTO errorListDTO=quotationMasterOutMessage.getErrorListDTO();
					if (errorListDTO != null && errorListDTO.hasErrors()) {
						ErrorDTO  errorDTO=errorListDTO.getErrorList().get(0);
						model.addAttribute("errors", errorDTO);
						ModelAndView mav = new ModelAndView("forward:get_quotation_list");
						return mav;
					}
				}
			}
			quotationMasterOutMessage = quotationMasterService
			.findQuotationMasterById(quotationMasterInputMessage);
			ArrayList<QuotationMasterDTO> list = (ArrayList<QuotationMasterDTO>) quotationMasterOutMessage
			.getQuotationMasterDTOList();
			if (list != null && list.size() > 0) {
				quotationMasterDTO = list.get(0);
				quotationMasterDTO
				.setPartyDTO(quotationMasterDTO.getPartyDTO());
				calcQuotation(quotationMasterDTO);
				quotationMasterForm.setQuotationMasterDTO(quotationMasterDTO);
			}
		}

		model.put("quotationMasterForm", quotationMasterForm);
		model.put("partyMap", partyMap());
		model.put("partyList", partyList());
		model.put("branchList", branchList());
		model.put("itemsMap", itemsMap());
		model.put("opr", opr);
		ModelAndView mav = new ModelAndView("quotation-entry");
		// mav.addObject("partyList",partyList());
		logger.info("IN newQuotation() quotationMasterForm-->"
				+ quotationMasterForm);
		return mav;

	}

	@RequestMapping(value = "/remove_quotation", method = RequestMethod.GET)
	public String removeQuotation(@RequestParam("id") Integer id,Model model) {
		logger.info("Removing..........quotation = " + id);
		QuotationMasterOutputMessage quotationMasterOutputMessage = null;
		if (id != null && !id.equals(0)) {
			QuotationMasterInputMessage quotationMasterInputMessage = new QuotationMasterInputMessage();
			QuotationMasterDTO quotationMasterDTO = new QuotationMasterDTO();
			quotationMasterDTO.setQuotationAutoId(id);
			quotationMasterInputMessage.setQuotationMasterDTO(quotationMasterDTO);
			quotationMasterOutputMessage = quotationMasterService.deleteQuotationMaster(quotationMasterInputMessage);
			if(quotationMasterOutputMessage!=null){
			ErrorListDTO errorListDTO=quotationMasterOutputMessage.getErrorListDTO();
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			if (errorListDTO != null && errorListDTO.hasErrors()) {
				model.addAttribute("errors", errorDTO);
				return "quotation-entry";
			}
			}
		}
		String succ="Dl";
		model.addAttribute("succ",succ);
		return "redirect:/get_quotation_list";

	}

	@RequestMapping("/check_before_remove")
	public @ResponseBody String checkBeforeRemove(@RequestParam("id")Integer id){
		String r="CONTINUE";
		logger.info("-----------------------------------------id: "+id);
		QuotationMasterInputMessage quotationMasterInputMessage = new QuotationMasterInputMessage();
		QuotationMasterDTO quotationMasterDTO = new QuotationMasterDTO();
		quotationMasterDTO.setQuotationAutoId(id);
		quotationMasterInputMessage.setQuotationMasterDTO(quotationMasterDTO);
		QuotationMasterOutputMessage quotationMasterOutMessage = quotationMasterService.checkBeforeRemove(quotationMasterInputMessage);
		if(quotationMasterOutMessage!=null){
			ErrorListDTO errorListDTO=quotationMasterOutMessage.getErrorListDTO();
			
			if (errorListDTO != null && errorListDTO.hasErrors()) {
				r=	errorListDTO.getErrorList().get(0).getErrorMsg();
			}
		}
		logger.info("----------------------------------------"+r);
		return r;	
	}

	private void calcQuotation(QuotationMasterDTO quotationMasterDTO) {
	
		List<QuotationDetailDTO> list = quotationMasterDTO
		.getQuotationDetailDTOList();
		double totalItemValue = 0;
		double totalExciseAmt = 0;
		double totalDiscountAmt = 0;
		double totalTaxAmt = 0;
		Double q, p, itemValue, excisePerc, exciseAmt, discPerc, discAmt, taxPerc, taxAmt, netAmt;
		if (list != null) {
			for (QuotationDetailDTO dto : list) {
				if (dto.getDeletedFlag())
					continue;
				q = dto.getQuantity();
				p = dto.getSalesRate();
				itemValue = p * q;
				dto.setItemValue(itemValue);

				excisePerc = dto.getExcisePerc();
				exciseAmt = itemValue * excisePerc / 100;
				dto.setExciseAmount(exciseAmt);

				discPerc = dto.getDiscountPer();
				discAmt = itemValue * discPerc / 100;
				dto.setDiscountAmount(discAmt);

				taxPerc = dto.getTaxPerc();
				taxAmt = (itemValue + exciseAmt - discAmt) * taxPerc / 100.0;
				dto.setTaxAmount(taxAmt);

				netAmt = itemValue + exciseAmt - discAmt + taxAmt;
				dto.setNetAmount(netAmt);

				totalItemValue += itemValue;
				totalExciseAmt += exciseAmt;
				totalDiscountAmt += discAmt;
				totalTaxAmt += taxAmt;
			}
			quotationMasterDTO.setExciseDutyAmount(totalExciseAmt);
			quotationMasterDTO.setItemValue(totalItemValue);
			quotationMasterDTO.setDiscountAmount(totalDiscountAmt);

			double eduCessAmt = quotationMasterDTO.getExciseDutyAmount()
			* quotationMasterDTO.getEducationCessPerc() / 100.0;
			double highEduCessAmt = quotationMasterDTO.getExciseDutyAmount()
			* quotationMasterDTO.getHighEducationCessPerc() / 100.0;
			double otherAmt = quotationMasterDTO.getOtherAmount();
			double taxableAmount = totalItemValue + totalExciseAmt + eduCessAmt
			+ highEduCessAmt - totalDiscountAmt;
			double netAmount = totalItemValue + totalExciseAmt + eduCessAmt
			+ highEduCessAmt - totalDiscountAmt + totalTaxAmt+ otherAmt;

			quotationMasterDTO.setEducationCessAmount(eduCessAmt);
			quotationMasterDTO.setHighEducationCessAmount(highEduCessAmt);
			quotationMasterDTO.setTaxableAmount(taxableAmount);
			quotationMasterDTO.setTaxTotal(totalTaxAmt);

			quotationMasterDTO.setQoNetAmount(netAmount);
			int itemCount = list.size();

			if ("VAT".equals(quotationMasterDTO.getTaxType()))
				quotationMasterDTO.setVatAmount(totalTaxAmt);

			if ("CST".equals(quotationMasterDTO.getTaxType()))
				quotationMasterDTO.setCstAmount(totalTaxAmt);
					
			quotationMasterDTO.setItemCount(Double.valueOf(itemCount));
		}
	}

	private boolean isDuplicateItem(QuotationMasterDTO quotationMasterDTO,
			Integer itemId) {

		if (quotationMasterDTO != null
				&& quotationMasterDTO.getQuotationDetailDTOList() != null) {
			List<QuotationDetailDTO> list = quotationMasterDTO.getQuotationDetailDTOList();
			logger.info("list-----" + list);
			for (QuotationDetailDTO e : list) {
				if (itemId.equals(e.getItemId()) && !e.getDeletedFlag())
					return true;
			}
		}
		return false;
	}

	private String getQuotationTransactionSeries() {
		TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
		transactionTypeDTO.setSno(2);
		TransactionTypeInputMessage transactionTypeInputMessage = new TransactionTypeInputMessage();
		transactionTypeInputMessage.setTransactionTypeDTO(transactionTypeDTO);
		TransactionTypeOutputMessage transactionTypeOutputMessage = transactionTypeService.findTransactionTypeById(transactionTypeInputMessage);
		List<TransactionTypeDTO> list = transactionTypeOutputMessage.getTransactionTypeDTOList();
		if (list != null && list.size() == 1)
			return list.get(0).getSeries();
		return null;
	}

	

	public TreeMap<Integer, PartyDTO> partyMap() {
		//PartyOutMessage partyOutMessage = partyService.findAllPartys();
		PartyOutMessage partyOutMessage = partyService.findDebtorPartyShortInfoList();
		partyDTOList = (ArrayList<PartyDTO>) partyOutMessage
		.getPartyDTOList();
		TreeMap<Integer, PartyDTO> map = new TreeMap<Integer, PartyDTO>();
		if (partyDTOList != null && partyDTOList.size() > 0) {
			
			for (PartyDTO p : partyDTOList) {
				
				map.put(p.getPartyId(), p);
			}
		}
		return map;
	}
	public List<PartyDTO> partyList() {
		return partyDTOList;
	}
	private PartyDTO getFirstPartyInList() {

		if (partyDTOList != null && partyDTOList.size() > 0) {
			return partyDTOList.get(0);
		}
		return null;
	}

	public List<BranchDTO> branchList() {
		BranchOutMessage branchOutMessage = branchService.findAllBranches();
		ArrayList<BranchDTO> list = (ArrayList<BranchDTO>) branchOutMessage
		.getBranchDTOList();
		return list;
	}

	
	public Map<Integer, ItemDTO> itemsMap() {
		return new TreeMap<Integer, ItemDTO>();
	}

}
