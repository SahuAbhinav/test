package com.advanz.erp.client.http.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.ItemForm;
import com.advanz.erp.client.http.controller.form.SalesOrderMasterForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.CityDTO;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.QuotationDetailDTO;
import com.advanz.erp.masters.model.QuotationMasterDTO;
import com.advanz.erp.masters.model.SalesOrderDetailDTO;
import com.advanz.erp.masters.model.SalesOrderMasterDTO;
import com.advanz.erp.masters.model.TransactionTypeDTO;
import com.advanz.erp.masters.model.criteria.ItemSearchCriteriaDTO;
import com.advanz.erp.masters.model.criteria.SalesOrderMasterSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.model.msg.CityInputMessage;
import com.advanz.erp.masters.model.msg.CityOutputMessage;
import com.advanz.erp.masters.model.msg.FinishedGoodsMasterInputMessage;
import com.advanz.erp.masters.model.msg.ItemCategoryInputMessage;
import com.advanz.erp.masters.model.msg.ItemCategoryOutMessage;
import com.advanz.erp.masters.model.msg.ItemGroupFlagOutMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.model.msg.PartyInputMessage;
import com.advanz.erp.masters.model.msg.PartyOutMessage;
import com.advanz.erp.masters.model.msg.QuotationMasterInputMessage;
import com.advanz.erp.masters.model.msg.QuotationMasterOutputMessage;
import com.advanz.erp.masters.model.msg.SalesOrderMasterInputMessage;
import com.advanz.erp.masters.model.msg.SalesOrderMasterOutputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeInputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeOutputMessage;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.service.business.ICityService;
import com.advanz.erp.masters.service.business.IItemGroupFlagService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IPartyService;
import com.advanz.erp.masters.service.business.IQuotationMasterService;
import com.advanz.erp.masters.service.business.ISalesOrderMasterService;
import com.advanz.erp.masters.service.business.ITransactionTypeService;

@Controller
@SessionAttributes({ "salesOrderMasterForm", "partyList", "branchList",
		"itemsMap", "opr" })
public class SalesOrderController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(SalesOrderController.class);
	@Autowired
	DataSource dataSource;
	@Autowired
	public IItemService itemService;
	@Autowired
	public IPartyService partyService;
	@Autowired
	public IBranchService branchService;
	@Autowired
	public ITransactionTypeService transactionTypeService;
	@Autowired
	public ISalesOrderMasterService salesOrderMasterService;

	@Autowired
	public IQuotationMasterService quotationMasterService;
	@Autowired
	public ICityService cityService;
	@Autowired
	public IMastersService mastersetService;
	@Autowired
	IItemGroupFlagService itemGroupFlagService;

	@RequestMapping(value = "/get_salesOrder_list")
	public ModelAndView searchSalesOrder(
			@ModelAttribute("soSearchCriteria") SalesOrderMasterSearchCriteriaDTO searchCriteria,
			Model model,
			@ModelAttribute("salesOrderMasterForms") SalesOrderMasterForm salesOrderMasterForm,
			@RequestParam(value = "menuId", required = false) String menuId,
			@RequestParam(value = "operation", required = false) String operation,
			@RequestParam(value = "next", required = false) Integer next,
			HttpSession session) {
		if (menuId != null) {
			session.setAttribute("menuId", menuId);
		}
		List<SalesOrderMasterDTO> list = new ArrayList<SalesOrderMasterDTO>();
		SalesOrderMasterOutputMessage salesOrderOutputMessage = null;
		SalesOrderMasterInputMessage salesOrderInputMessage = new SalesOrderMasterInputMessage();

		if ("Search".equalsIgnoreCase(operation)) {
			salesOrderInputMessage.setSearchCriteria(searchCriteria);
			salesOrderOutputMessage = salesOrderMasterService
					.search(salesOrderInputMessage);

		} else {

			salesOrderInputMessage = new SalesOrderMasterInputMessage();
			if (next == null || next < 0) {
				next = 0;
				salesOrderInputMessage.setNext(next);
				salesOrderOutputMessage = salesOrderMasterService
						.findSalesOrderMasterPagination(salesOrderInputMessage);
			} else {
				salesOrderInputMessage.setNext(next);
				salesOrderOutputMessage = salesOrderMasterService
						.findSalesOrderMasterPagination(salesOrderInputMessage);
			}
			salesOrderMasterForm.setNext(next);
			salesOrderMasterForm.setPrevious(next);
		}
		list = (ArrayList<SalesOrderMasterDTO>) salesOrderOutputMessage
				.getSalesOrderMasterDTOList();
		// /list =isSalesOrderUseInInvoice(list);

		ModelAndView mav = new ModelAndView("salesOrder-list");
		mav.addObject("somList", list);
		String succ = "Blk";
		if (list.equals(null) || list.size() == 0) {
			model.addAttribute("succ", succ);
		}
		mav.addObject("salesOrderMasterForms", salesOrderMasterForm);
		return mav;
	}

	@RequestMapping(value = "/get_item_by_id")
	public @ResponseBody ItemDTO getItemById(
			@RequestParam("id") Integer itemId,
			@ModelAttribute("itemsMap") Map<Integer, ItemDTO> itemMap) {
		logger.info("Getting Item for  : " + itemId);
		ItemDTO itemDTO = null;
		if (itemId != 0) {
			itemDTO = itemMap.get(itemId);
		}
		return itemDTO;
	}

	@RequestMapping(value = "/show_item_list")
	public ModelAndView showItemSelectionForm(
			@ModelAttribute("salesOrderMasterForm") SalesOrderMasterForm salesOrderMasterForm,
			@ModelAttribute("searchCriteria") ItemSearchCriteriaDTO searchCriteria,
			@RequestParam(value = "itemID", required = false) Integer itemId,
			@RequestParam(value = "btn", required = false) String btn,
			@ModelAttribute("itemsMap") Map<Integer, ItemDTO> itemMap,
			@RequestParam(value = "operation", required = false) String operation,
			@RequestParam(value = "next", required = false) Integer next) {
		if (btn != null) {
			return addItemInSalesOrder(salesOrderMasterForm, itemId, itemMap);
		}
		// ModelAndView mav = new ModelAndView("item-list-for-so");
		ModelAndView mav = preloadedData();
		mav.setViewName("item-list-for-so");
		if (searchCriteria == null) {
			searchCriteria = new ItemSearchCriteriaDTO();
		}

		ItemOutMessage itemOutMessage = null; // itemService.findAllItem();
		ItemInputMessage itemInputMessage = new ItemInputMessage();
		ItemDTO itemDTO = new ItemDTO();
		SalesOrderMasterDTO salesOrderMasterDTO = salesOrderMasterForm
				.getSalesOrderMasterDTO();
		itemDTO.setItemGroupFlagId(salesOrderMasterDTO.getItemGroupFlagId());
		if ("search".equalsIgnoreCase(operation)) {
			if (searchCriteria != null) {
				itemDTO.setInvoiceName(searchCriteria.getInvoiceName());
				itemDTO.setItemCode(searchCriteria.getItemCode());
			}
			itemInputMessage.setItemDTO(itemDTO);
			// itemOutMessage = itemService.findItem(itemInputMessage);
			itemOutMessage = itemService
					.searchFinishedFoodItems(itemInputMessage);

		} else {
			// Pagination start

			if (next == null || next < 0) {
				next = 0;
				itemDTO.setNext(next);
				itemInputMessage.setItemDTO(itemDTO);
				itemOutMessage = itemService
						.finishGoodItemListWithPagination(itemInputMessage);
				next = 13;
			} else {

				itemDTO.setNext(next);
				itemInputMessage.setItemDTO(itemDTO);
				itemOutMessage = itemService
						.finishGoodItemListWithPagination(itemInputMessage);
				next = next + 13;
			}

			// Pagination end

		}
		ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage
				.getItemDTOList();
		// To remove duplicate item when we add items in sales order
		/*
		 * if (salesOrderMasterForm.getSalesOrderMasterDTO() != null) {
		 * List<SalesOrderDetailDTO> salesOrderDetailDTOList =
		 * salesOrderMasterForm
		 * .getSalesOrderMasterDTO().getSalesOrderDetailDTOList(); if
		 * (salesOrderDetailDTOList != null) { for (SalesOrderDetailDTO
		 * salesOrderDetailDTO : salesOrderDetailDTOList) { Iterator<ItemDTO>
		 * itr = list.iterator(); while (itr.hasNext()) { ItemDTO temp =
		 * itr.next(); if (!salesOrderDetailDTO.getDeletedFlag() &&
		 * temp.getItemId().equals( salesOrderDetailDTO.getItemId())) {
		 * itr.remove(); } } } } }
		 */

		mav.addObject("searchCriteria", searchCriteria);
		mav.addObject("itemList", list);
		itemMap.clear();
		for (ItemDTO e : list) {
			itemMap.put(e.getItemId(), e);
		}
		ItemForm itemForm = new ItemForm();
		try {
			itemDTO.setNext(next);
			itemDTO.setPrevious(next - 26);
		} catch (Exception e1) {

		}
		itemForm.setItemDTO(itemDTO);
		mav.addObject("itemForm", itemForm);
		return mav;
	}

	private void calcSalesOrder(SalesOrderMasterDTO salesOrderMasterDTO) {
		List<SalesOrderDetailDTO> list = salesOrderMasterDTO
				.getSalesOrderDetailDTOList();
		double totalItemValue = 0;
		double totalExciseAmt = 0;
		double totalDiscountAmt = 0;
		double totalTaxAmt = 0;
		Double q, p, itemValue, excisePerc, exciseAmt, discPerc, discAmt, taxPerc, taxAmt, netAmt;
		if (list != null) {
			for (SalesOrderDetailDTO dto : list) {
				if (dto.getDeletedFlag())
					continue;
				q = dto.getQuantity();
				p = dto.getSalesRate();
				itemValue = p * q;
				dto.setItemValue(itemValue);

				excisePerc = dto.getExcisePerc();
				exciseAmt = itemValue * excisePerc / 100.0;
				dto.setExciseAmount(exciseAmt);

				discPerc = dto.getDiscountPer();
				discAmt = itemValue * discPerc / 100.0;
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

			double eduCessAmt = totalExciseAmt
					* salesOrderMasterDTO.getEducationCessPerc() / 100.0;
			double highEduCessAmt = totalExciseAmt
					* salesOrderMasterDTO.getHighEducationCessPerc() / 100.0;
			double otherAmt = salesOrderMasterDTO.getOtherAmount();
			double taxableAmount = totalItemValue + totalExciseAmt + eduCessAmt
					+ highEduCessAmt - totalDiscountAmt;
			double netAmount = totalItemValue + totalExciseAmt + eduCessAmt
					+ highEduCessAmt - totalDiscountAmt + totalTaxAmt
					+ otherAmt;
			salesOrderMasterDTO.setExciseDutyAmount(totalExciseAmt);
			salesOrderMasterDTO.setItemValue(totalItemValue);
			salesOrderMasterDTO.setDiscountAmount(totalDiscountAmt);
			salesOrderMasterDTO.setEducationCessAmount(eduCessAmt);
			salesOrderMasterDTO.setHighEducationCessAmount(highEduCessAmt);
			salesOrderMasterDTO.setTaxableAmount(taxableAmount);
			salesOrderMasterDTO.setTaxTotal(totalTaxAmt);
			salesOrderMasterDTO.setSoNetAmount(netAmount);

			if ("VAT".equals(salesOrderMasterDTO.getTaxType()))
				salesOrderMasterDTO.setVatAmount(totalTaxAmt);

			if ("CST".equals(salesOrderMasterDTO.getTaxType()))
				salesOrderMasterDTO.setCstAmount(totalTaxAmt);
		}
	}

	private String getSalesOrderTransactionSeries() {
		TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
		transactionTypeDTO.setSno(3);
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

	@RequestMapping(value = "/new_salesOrder")
	public ModelAndView newSalesOrder(ModelMap model,
			@ModelAttribute("qid") String qidStr) {
		logger.info("qid=" + qidStr);
		ModelAndView mav = preloadedData();
		SalesOrderMasterForm salesOrderMasterForm = new SalesOrderMasterForm();
		SalesOrderMasterDTO salesOrderMasterDTO = new SalesOrderMasterDTO();
		String series = getSalesOrderTransactionSeries();
		salesOrderMasterDTO.setFinYear(getFinYear());
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 90);
		date = cal.getTime();

		salesOrderMasterDTO.setSoValidUptoDate(date);
		salesOrderMasterDTO.setItemGroupFlagId(3);
		// salesOrderMasterDTO.setTransactionSeries(series + "-" +
		// getFinYear());
		salesOrderMasterDTO.setTransactionSeries(series);
		// salesOrderMasterDTO.setOrderSeries(series + "-" + getFinYear());
		salesOrderMasterDTO.getOrderSeries();
		SalesOrderMasterInputMessage salesOrderMasterInputMessage = new SalesOrderMasterInputMessage();
		salesOrderMasterInputMessage
				.setSalesOrderMasterDTO(salesOrderMasterDTO);
		SalesOrderMasterOutputMessage salesOrderMasterOutputMessage = salesOrderMasterService
				.getNewSalesOrderSeriesNo(salesOrderMasterInputMessage);
		Integer orderID = salesOrderMasterOutputMessage.getSalesOrderSeriesNo();
		
		Timestamp timestamp= salesOrderMasterOutputMessage.getSalesOrderSeriesDate();
		//System.out.println("Last Finished good time"+new Date(timestamp.getTime()));
		SimpleDateFormat ft =new SimpleDateFormat ("yyyy,MM,dd");
		
		salesOrderMasterDTO.setSalesOrderId(orderID);
		salesOrderMasterDTO.setSalesOrderNumber(salesOrderMasterDTO
				.getTransactionSeries()
				+ "/"
				+ salesOrderMasterDTO.getFinYear()
				+ "/"
				+ salesOrderMasterDTO.getSalesOrderId());
		salesOrderMasterDTO.setSalesOrderDate(new Date());
		List<PartyDTO> partyDTOList = partyList();
		if (partyDTOList != null && partyDTOList.size() > 0) {
			PartyDTO p = new PartyDTO();
			BeanUtils.copyProperties(partyDTOList.get(0), p);
			salesOrderMasterDTO.setPartyDTO(p);
		}
		//salesOrderMasterDTO.setConsigneeId(331);
		salesOrderMasterForm.setSalesOrderMasterDTO(salesOrderMasterDTO);
		// salesOrderMasterForm.setSalesOrderMasterDTO(new
		// SalesOrderMasterDTO());
		if (StringUtils.hasText(qidStr)) {
			Integer qid = NumberUtils.parseNumber(qidStr, Integer.class);
			QuotationMasterInputMessage quotationMasterInputMessage = new QuotationMasterInputMessage();
			QuotationMasterDTO quotationMasterDTO = new QuotationMasterDTO();
			quotationMasterDTO.setQuotationAutoId(qid);
			quotationMasterInputMessage
					.setQuotationMasterDTO(quotationMasterDTO);
			QuotationMasterOutputMessage quotationMasterOutputMessage = quotationMasterService
					.findQuotationMasterById(quotationMasterInputMessage);
			List<QuotationMasterDTO> quotationMasterDTOList = quotationMasterOutputMessage
					.getQuotationMasterDTOList();
			if (quotationMasterDTOList != null
					&& quotationMasterDTOList.size() > 0) {
				quotationMasterDTO = quotationMasterDTOList.get(0);
				List<QuotationDetailDTO> quotationDetailDTOList = quotationMasterDTO
						.getQuotationDetailDTOList();
				logger.info("quotationDetailDTOList : "
						+ quotationDetailDTOList);
				if (quotationDetailDTOList != null
						&& quotationDetailDTOList.size() > 0) {
					logger.info("quotationDetailDTOList : "
							+ quotationDetailDTOList.size());

					List<SalesOrderDetailDTO> salesOrderDetailDTOList = salesOrderMasterDTO
							.getSalesOrderDetailDTOList();
					if (salesOrderDetailDTOList == null) {
						salesOrderDetailDTOList = new ArrayList<SalesOrderDetailDTO>();
						salesOrderMasterDTO
								.setSalesOrderDetailDTOList(salesOrderDetailDTOList);
					}
					for (QuotationDetailDTO qdto : quotationDetailDTOList) {
						SalesOrderDetailDTO sdto = new SalesOrderDetailDTO();
						BeanUtils.copyProperties(qdto, sdto);

						// Get UMO...........
						ItemDTO itemDTO = new ItemDTO();
						itemDTO.setItemId(qdto.getItemId());
						ItemInputMessage itemInputMessage = new ItemInputMessage();
						itemInputMessage.setItemDTO(itemDTO);
						ItemOutMessage itemOutMessage = itemService
								.findItemById(itemInputMessage);
						List<ItemDTO> itList = itemOutMessage.getItemDTOList();
						if (itList != null && itList.size() > 0) {
							itemDTO = itList.get(0);
						}
						MastersDTO mastersDTO = itemDTO.getMasterUnit();
						if (mastersDTO != null) {
							//system.out.println("in masterDTO");
							sdto.setMasterUnit(mastersDTO);
						}

						sdto.setSno(null);
						sdto.setSalesOrderNumber(salesOrderMasterDTO
								.getSalesOrderNumber());
						sdto.setTransactionSeries(salesOrderMasterDTO
								.getTransactionSeries());
						salesOrderDetailDTOList.add(sdto);
					}
				}
				salesOrderMasterDTO.setQuotationNumber(quotationMasterDTO
						.getQuotationNumber());

				salesOrderMasterDTO.setPartyDTO(findPartyDTO(quotationMasterDTO
						.getPartyDTO().getPartyId()));
				calcSalesOrder(salesOrderMasterDTO);

			}
		}
		salesOrderMasterForm.setLastSalesOrderDate(ft.format(new Date(timestamp.getTime())));
		model.put("salesOrderMasterForm", salesOrderMasterForm);
		model.put("partyList", partyDTOList);
		model.put("branchList", branchList());
		model.put("itemsMap", itemsMap());
		model.put("opr", "N");
		/*
		 * desable for New Item group List
		 */
		// ModelAndView mav = new ModelAndView("salesOrder-entry");
		mav.setViewName("salesOrder-entry");
		// mav.addObject("partyList",partyList());

		return mav;
	}

	/**
	 * @author Abhinav
	 *
	 *         02-01-2015
	 */

	private ModelAndView preloadedData() {
		//system.out.println("in preLoad data function");
		ModelAndView mav = new ModelAndView();
		ItemGroupFlagOutMessage itemGroupFlagOutMessage = itemGroupFlagService
				.findAllItemGroupFlag();
		ArrayList<ItemGroupFlagDTO> itemGroupFlagList = (ArrayList<ItemGroupFlagDTO>) itemGroupFlagOutMessage
				.getItemGroupFlagDTOList();
		//system.out.println("itemGroupFlagList size" + itemGroupFlagList.size());
		mav.addObject("itemGroupFlagList", itemGroupFlagList);
		return mav;
	}

	@RequestMapping(value = "/backto_salesOrder", method = RequestMethod.GET)
	public ModelAndView backToSalesOrder(
			@ModelAttribute("salesOrderMasterForm") SalesOrderMasterForm salesOrderMasterForm) {
		ModelAndView mav = preloadedData();
		calcSalesOrder(salesOrderMasterForm.getSalesOrderMasterDTO());
		// ModelAndView mav = new ModelAndView("salesOrder-entry");
		mav.setViewName("salesOrder-entry");
		return mav;
	}

	@RequestMapping(value = "/get_party_by_id", method = RequestMethod.GET)
	public @ResponseBody PartyDTO getPartyById(
			@RequestParam("id") Integer partyId,
			@ModelAttribute("salesOrderMasterForm") SalesOrderMasterForm salesOrderMasterForm) {

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
			salesOrderMasterForm.getSalesOrderMasterDTO().setPartyDTO(partyDTO);
			List<SalesOrderDetailDTO> detailDTOs = salesOrderMasterForm
					.getSalesOrderMasterDTO().getSalesOrderDetailDTOList();
			if (detailDTOs != null) {
				String partyInvoiceType = partyDTO.getInvoiceType();
				for (SalesOrderDetailDTO detailDTO : detailDTOs) {
					detailDTO.setPartyInvoiceType(partyInvoiceType);
				}
			}
		}
		logger.info("Part DTO :" + partyDTO);
		logger.info("Sales Order Form : " + salesOrderMasterForm);
		return partyDTO;

	}

	@RequestMapping(value = "/party_changed", method = RequestMethod.POST)
	public ModelAndView partyChanged(
			@ModelAttribute("salesOrderMasterForm") SalesOrderMasterForm salesOrderMasterForm,
			@RequestParam("partyId") Integer partyId,
			@ModelAttribute("partyList") List<PartyDTO> partyList) {

		ModelAndView mav = preloadedData();
		// public ModelAndView
		// partyChanged(@ModelAttribute("salesOrderMasterForm")
		// SalesOrderMasterForm salesOrderMasterForm) {
		// Integer
		// partyId=salesOrderMasterForm.getSalesOrderMasterDTO().getPartyDTO().getPartyId();
		logger.info("Party Id=" + partyId);
		for (PartyDTO p : partyList) {
			logger.info("In Party Changed" + p.getPartyId() + " : "
					+ p.getPartyName());
		}
		PartyDTO partyDTO = findPartyDTO(partyId);
		if (partyDTO != null) {
			salesOrderMasterForm.getSalesOrderMasterDTO().setPartyDTO(partyDTO);
			List<SalesOrderDetailDTO> detailDTOs = salesOrderMasterForm
					.getSalesOrderMasterDTO().getSalesOrderDetailDTOList();
			if (detailDTOs != null) {
				String partyInvoiceType = partyDTO.getInvoiceType();
				for (SalesOrderDetailDTO detailDTO : detailDTOs) {
					detailDTO.setPartyInvoiceType(partyInvoiceType);
				}
			}
			calcSalesOrder(salesOrderMasterForm.getSalesOrderMasterDTO());
		}
		logger.info("Part DTO :" + partyDTO);
		logger.info("-------Sales Order Form : " + salesOrderMasterForm);
		// ModelAndView mav = new ModelAndView("salesOrder-entry");
		mav.setViewName("salesOrder-entry");
		return mav;
	}

	@RequestMapping("/removeItemFromSO")
	public String removeItemFrom(
			@ModelAttribute("salesOrderMasterForm") SalesOrderMasterForm salesOrderMasterForm,
			@RequestParam("index") Integer index,
			@ModelAttribute("opr") String opr) {

		List<SalesOrderDetailDTO> detailDTOList = salesOrderMasterForm
				.getSalesOrderMasterDTO().getSalesOrderDetailDTOList();
		if (detailDTOList != null && detailDTOList.size() > index) {
			SalesOrderDetailDTO dto = detailDTOList.get(index);
			if (StringUtils.hasText(opr) && "E".equals(opr)
					&& !dto.isTransientObject()) {
				dto.setDeletedFlag(true);
			} else
				detailDTOList.remove(dto);
		}
		calcSalesOrder(salesOrderMasterForm.getSalesOrderMasterDTO());
		// ModelAndView mav = new ModelAndView("salesOrder-entry");
		return "salesOrder-entry";
	}

	@RequestMapping("/addItemInSO")
	public ModelAndView addItemInSalesOrder(
			@ModelAttribute("salesOrderMasterForm") SalesOrderMasterForm salesOrderMasterForm,
			@RequestParam("itemID") Integer itemId,
			@ModelAttribute("itemsMap") Map<Integer, ItemDTO> itemMap) {
		ModelAndView mav = preloadedData();
		logger.info("IN addItem()  salesOrderMasterForm-->"
				+ salesOrderMasterForm);
		logger.info("Add Item : " + itemId);
		SalesOrderMasterDTO salesOrderMasterDTO = salesOrderMasterForm
				.getSalesOrderMasterDTO();
		if (itemId != null) {
			// if (!isDuplicateItem(salesOrderMasterDTO, itemId)) {
			ItemDTO itemDTO = itemMap.get(itemId);
			if (itemDTO != null) {
				SalesOrderDetailDTO salesOrderDetailDTO = new SalesOrderDetailDTO();
				BeanUtils.copyProperties(itemDTO, salesOrderDetailDTO);
				salesOrderDetailDTO.setSalesOrderNumber(salesOrderMasterDTO
						.getSalesOrderNumber());
				salesOrderDetailDTO.setTransactionSeries(salesOrderMasterDTO
						.getTransactionSeries());
				salesOrderDetailDTO.setItemName(itemDTO.getInvoiceName());

				if ("VAT".equals(salesOrderMasterDTO.getTaxType())) {
					salesOrderDetailDTO.setTaxPerc(salesOrderDetailDTO
							.getVatPerc());
				}
				if ("CST".equals(salesOrderMasterDTO.getTaxType())) {
					salesOrderDetailDTO.setTaxPerc(salesOrderDetailDTO
							.getCstPerc());
				}

				salesOrderDetailDTO.setPartyInvoiceType(salesOrderMasterDTO
						.getPartyDTO().getInvoiceType());
				salesOrderDetailDTO.setQuantity(1.0); // default Quantity
				// set default UOM
				salesOrderDetailDTO.setPrimaryUOM(itemDTO
						.getMasterPrimaryConverUnit());
				if (!"excisable".equals(itemDTO.getExciseTypeFlag())) {
					salesOrderDetailDTO.setExcisePerc(0.0);
				}

				if (salesOrderMasterDTO.getSalesOrderDetailDTOList() == null) {
					salesOrderMasterForm.getSalesOrderMasterDTO()
							.setSalesOrderDetailDTOList(
									new ArrayList<SalesOrderDetailDTO>());
				}
				salesOrderDetailDTO.setTransientObject(true);
				salesOrderMasterDTO.getSalesOrderDetailDTOList().add(
						salesOrderDetailDTO);
				// logger.info("After add Item Size="
				// + salesOrderMasterForm.getSalesOrderMasterDTO()
				// .getSalesOrderDetailDTOList().size());
				calcSalesOrder(salesOrderMasterForm.getSalesOrderMasterDTO());
			}

			// }
		}
		// return "redirect:/new_salesOrder";
		// ModelAndView mav = new ModelAndView("salesOrder-entry");

		mav.setViewName("salesOrder-entry");
		// mav.addObject("salesOrderMasterForm1",salesOrderMasterForm);
		return mav;
	}

	@RequestMapping(value = "/processSalesOrder", method = RequestMethod.POST)
	public String saveSalesOrder(
			@ModelAttribute("salesOrderMasterForm") SalesOrderMasterForm salesOrderMasterForm,
			Model model, @ModelAttribute("opr") String opr,
			@RequestParam(value = "remItemBtn", required = false) String btn,
			HttpSession session, HttpServletResponse response,
			HttpServletRequest request) {
		String succ = "";
		logger.info("Opr : " + opr);
		logger.info("btn : " + btn);
		logger.info("salesOrderForm = " + salesOrderMasterForm);
		List<ItemGroupFlagDTO> list = itemGroupList();
		model.addAttribute("itemGroupFlagList", list);
		if (btn != null) {
			int index = Integer.parseInt(btn);
			return removeItemFrom(salesOrderMasterForm, index, opr);
		}
		Date soDate = salesOrderMasterForm.getSalesOrderMasterDTO()
				.getSalesOrderDate();

		ErrorListDTO errorListDTO = new ErrorListDTO();
		if (soDate != null) {
			Date curDate = new Date();

			Date recDate = salesOrderMasterForm.getSalesOrderMasterDTO()
					.getOrderReceiptDate();
			Date planDelDate = salesOrderMasterForm.getSalesOrderMasterDTO()
					.getPlannedDeliveryDate();
			Date desireDelDate = salesOrderMasterForm.getSalesOrderMasterDTO()
					.getDesireDeliveryDate();
			Date partyPoDate = salesOrderMasterForm.getSalesOrderMasterDTO()
					.getPartyPoDate();
			if (soDate.after(curDate)) {
				ErrorDTO error = new ErrorDTO();
				error.setErrorMsg("Date should not be greater than current date");
				errorListDTO.addError(error);
			}

			if (recDate != null) {
				if (recDate.after(soDate)) {
					ErrorDTO error = new ErrorDTO();
					error.setErrorMsg("Order Rec. Date should not be Greater than Date");
					errorListDTO.addError(error);
				}
			}
			if (planDelDate != null) {
				if (planDelDate.before(soDate)) {
					ErrorDTO error = new ErrorDTO();
					error.setErrorMsg("Planned Delivery Date should not be Less than Date");
					errorListDTO.addError(error);
				}
			}
			if (desireDelDate != null) {
				if (desireDelDate.before(soDate)) {
					ErrorDTO error = new ErrorDTO();
					error.setErrorMsg("Desire Delivery Date should not be Less than Date");
					errorListDTO.addError(error);
				}
			}
			if (partyPoDate != null) {
				if (partyPoDate.after(soDate)) {
					ErrorDTO error = new ErrorDTO();
					error.setErrorMsg("Party PO Date should not be Greater than Date");
					errorListDTO.addError(error);
				}
			}
		}

		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO = errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors", errorDTO);
			return "salesOrder-entry";
		}

		int itemCount = 0;
		if (salesOrderMasterForm.getSalesOrderMasterDTO()
				.getSalesOrderDetailDTOList() != null
				&& salesOrderMasterForm.getSalesOrderMasterDTO()
						.getSalesOrderDetailDTOList().size() > 0) {
			for (SalesOrderDetailDTO d : salesOrderMasterForm
					.getSalesOrderMasterDTO().getSalesOrderDetailDTOList()) {

				if (!d.getDeletedFlag()) {
					if (d.getQuantity().doubleValue() == 0.0) {
						ErrorDTO error = new ErrorDTO();
						error.setErrorMsg("Item Quantity value can not be 0 (Zero)");
						model.addAttribute("errors", error);
						return "salesOrder-entry";
					}
					itemCount++;
				}

			}
		}
		if (itemCount < 0) {
			ErrorDTO error = new ErrorDTO();
			error.setErrorMsg("Warning - Add at least one Item");
			model.addAttribute("errors", error);
			return "salesOrder-entry";
		}
		SalesOrderMasterDTO salesOrderMasterDTO = salesOrderMasterForm
				.getSalesOrderMasterDTO();
		SalesOrderMasterInputMessage salesOrderMasterInputMessage = new SalesOrderMasterInputMessage();
		List<SalesOrderDetailDTO> salesOrderDetailDTOList = salesOrderMasterDTO
				.getSalesOrderDetailDTOList();
		for (int c = 0; c < salesOrderDetailDTOList.size(); c++) {
			SalesOrderDetailDTO salesOrderDetailDTO = salesOrderDetailDTOList
					.get(c);
			salesOrderDetailDTO
					.setPendingQty(salesOrderDetailDTO.getQuantity());
			//System.out.println("1" + salesOrderDetailDTO);
			// salesOrderDetailDTO.setPrimaryUnit(salesOrderDetailDTO.getPrimaryUnit()*salesOrderDetailDTO.getQuantity());

			//system.out.println("salesOrderDetailDTO-----getPrimaryUOM"+ salesOrderDetailDTO.getPrimaryUOM());

			// set Primary Unit based on Secondary
			if (salesOrderDetailDTO.getBookedBy() != null
					&& salesOrderDetailDTO.getBookedBy() == 2
					&& salesOrderDetailDTO.getSecondaryConvUnit() != null) {
				//System.out.println("if");
				salesOrderDetailDTO.setPrimaryUnit(salesOrderDetailDTO
						.getQuantity()
						/ salesOrderDetailDTO.getSecondaryConvUnit());
				salesOrderDetailDTO.setPrimaryPendingQty(salesOrderDetailDTO
						.getQuantity()
						/ salesOrderDetailDTO.getSecondaryConvUnit());
				
			}else if(salesOrderDetailDTO.getBookedBy() != null
					&& salesOrderDetailDTO.getBookedBy() == 1
					&& salesOrderDetailDTO.getSecondaryConvUnit() != null) {
				//System.out.println("else if ");
				salesOrderDetailDTO.setPrimaryUnit(salesOrderDetailDTO
						.getQuantity());
				salesOrderDetailDTO.setPrimaryPendingQty(salesOrderDetailDTO
						.getQuantity());
				

			}else{
				//System.out.println("else");
				salesOrderDetailDTO.setPrimaryUnit(salesOrderDetailDTO
						.getQuantity());
				salesOrderDetailDTO.setPrimaryPendingQty(salesOrderDetailDTO
						.getQuantity());
			}
			/*if (salesOrderDetailDTO.getMasterUnit()== null) {
				salesOrderDetailDTO.setMeasurementUnitId(salesOrderDetailDTO
						.getPrimaryUOM());
			}*/
			// salesOrderDetailDTO.setMeasurementUnitId(measurementUnitId);
			//system.out.println("4" + salesOrderDetailDTO.getMeasurementUnitId());

			// Set Meausrement unit id
			if(salesOrderDetailDTO.getMeasurementUnitId()!=null)
			 {
				MastersInputMessage mastersInputMessage = new MastersInputMessage();
				MastersDTO mastersDTO = new MastersDTO();
				
				mastersDTO.setMastersId(salesOrderDetailDTO.getMeasurementUnitId());

				mastersInputMessage.setMastersDTO(mastersDTO);

				MastersOutputMessage mastersOutputMessage = mastersetService
						.findMastersById(mastersInputMessage);
				List<MastersDTO> masterList = mastersOutputMessage
						.getMastersDTOList();
				if (masterList != null && masterList.size() > 0) {
					mastersDTO = masterList.get(0);
					//system.out.println("in Master Unit " + mastersDTO);

				}

				salesOrderDetailDTO.setMasterUnit(mastersDTO);
			} else{
				
				logger.info("in Else of Item mesurment...............");
			}

			salesOrderDetailDTOList.set(c, salesOrderDetailDTO);
		}
		salesOrderMasterDTO.setSalesOrderDetailDTOList(salesOrderDetailDTOList);
		SalesOrderMasterOutputMessage salesOrderMasterOutputMessage;
		if (salesOrderMasterForm.getSalesOrderMasterDTO().getSalesOrderAutoId() == null
				|| salesOrderMasterForm.getSalesOrderMasterDTO()
						.getSalesOrderAutoId().equals(0)) {
			if (salesOrderMasterDTO.getTaxType().equalsIgnoreCase("CST")) {
				salesOrderMasterDTO.setCstAmount(salesOrderMasterDTO
						.getTaxTotal());
			}
			if (salesOrderMasterDTO.getTaxType().equalsIgnoreCase("VAT")) {
				salesOrderMasterDTO.setVatAmount(salesOrderMasterDTO
						.getTaxTotal());
			}
			salesOrderMasterDTO.setCreatedUserId(getCreatedUserId());
			salesOrderMasterInputMessage
					.setSalesOrderMasterDTO(salesOrderMasterDTO);
			salesOrderMasterOutputMessage = salesOrderMasterService
					.createSalesOrderMaster(salesOrderMasterInputMessage);

			if (salesOrderMasterOutputMessage != null) {
				errorListDTO = salesOrderMasterOutputMessage.getErrorListDTO();
				if (errorListDTO != null && errorListDTO.hasErrors()) {
					ErrorDTO errorDTO = errorListDTO.getErrorList().get(0);
					model.addAttribute("errors", errorDTO);

					SalesOrderMasterDTO masterDTO = salesOrderMasterForm
							.getSalesOrderMasterDTO();

					int orderID = masterDTO.getSalesOrderId();
					orderID = orderID + 1;
					masterDTO.setSalesOrderId(orderID);
					masterDTO
							.setSalesOrderNumber(getSalesOrderTransactionSeries()
									+ "/" + getFinYear() + "/" + orderID);
					ArrayList<SalesOrderDetailDTO> salesDeatilList = (ArrayList<SalesOrderDetailDTO>) masterDTO
							.getSalesOrderDetailDTOList();
					for (int i = 0; i < salesDeatilList.size(); i++) {
						SalesOrderDetailDTO detailDTO = salesDeatilList.get(i);

						detailDTO
								.setSalesOrderNumber(getSalesOrderTransactionSeries()
										+ "/" + getFinYear() + "/" + orderID);
						detailDTO.setCreatedUserId(createdUserId);
					}

					salesOrderMasterForm.setSalesOrderMasterDTO(masterDTO);
					model.addAttribute("salesOrderMasterForm",
							salesOrderMasterForm);
					return "salesOrder-entry";
				}
			}
			try {
				/* sendSalesPdfMail(salesOrderMasterDTO.getSalesOrderNumber(),
				 response, request);*/
			} catch (Exception e) {
				// TODO: handle exception
			}

			succ = "Ad";
		} else {

			SalesOrderMasterDTO salesOrder = salesOrderMasterForm
					.getSalesOrderMasterDTO();
			salesOrder.setSalesOrderAutoId(salesOrderMasterForm
					.getSalesOrderMasterDTO().getSalesOrderAutoId());
			SalesOrderMasterInputMessage salesOrderInputMessage = new SalesOrderMasterInputMessage();
			salesOrderInputMessage.setSalesOrderMasterDTO(salesOrder);

			SalesOrderMasterOutputMessage outputMessage = salesOrderMasterService
					.findSalesOrderMasterById(salesOrderInputMessage);

			List<SalesOrderMasterDTO> salesOrderMasterDTOList = outputMessage
					.getSalesOrderMasterDTOList();

			SalesOrderMasterDTO sDto = salesOrderMasterDTOList.get(0);
			List<SalesOrderDetailDTO> soDbDtlList = sDto
					.getSalesOrderDetailDTOList();

			List<SalesOrderDetailDTO> soItemFormDtlList = salesOrderMasterDTO
					.getSalesOrderDetailDTOList();
			try {

				for (int s = 0; s < soDbDtlList.size(); s++) {

					for (int p = 0; p < soItemFormDtlList.size(); p++) {
						if (soItemFormDtlList.get(p).getItemId().intValue() == soDbDtlList
								.get(s).getItemId().intValue()) {
							double soFormItemQty = soItemFormDtlList.get(p)
									.getQuantity().doubleValue();
							double soDbPendingQty = soDbDtlList.get(s)
									.getPendingQty().doubleValue();
							double soDbItemQty = soDbDtlList.get(s)
									.getQuantity().doubleValue();
							SalesOrderDetailDTO detailDTO = soItemFormDtlList
									.get(p);
							double balanceQty = (soFormItemQty - soDbItemQty)
									+ soDbPendingQty;
							detailDTO.setPendingQty(balanceQty);
							detailDTO.setModifiedUserId(getCreatedUserId());
							soItemFormDtlList.set(p, detailDTO);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			if (salesOrderMasterDTO.getTaxType().equalsIgnoreCase("CST")) {
				salesOrderMasterDTO.setCstAmount(salesOrderMasterDTO
						.getTaxTotal());
			}
			if (salesOrderMasterDTO.getTaxType().equalsIgnoreCase("VAT")) {
				salesOrderMasterDTO.setVatAmount(salesOrderMasterDTO
						.getTaxTotal());
			}
			salesOrderMasterDTO.setModifiedUserId(getCreatedUserId());
			salesOrderMasterDTO.setSalesOrderDetailDTOList(soItemFormDtlList);
			salesOrderMasterInputMessage = new SalesOrderMasterInputMessage();
			salesOrderMasterInputMessage.setSalesOrderMasterDTO(salesOrder);
			// double balaceQty=(listQty-dbQty)+pending;
			salesOrderMasterOutputMessage = salesOrderMasterService
					.updateSalesOrderMaster(salesOrderMasterInputMessage);
			succ = "Up";
		}
		if (salesOrderMasterOutputMessage != null)
			errorListDTO = salesOrderMasterOutputMessage.getErrorListDTO();

		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO = errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors", errorDTO);
			return "salesOrder_entry";
		}
		SalesOrderMasterDTO orderMasterDTO = salesOrderMasterForm
				.getSalesOrderMasterDTO();
		orderMasterDTO.setSalesOrderDetailDTOList(null);
		model.addAttribute("salesOrderMasterForm", salesOrderMasterForm);
		model.addAttribute("succ", succ);

		return "redirect:/get_salesOrder_list";
	}

	@RequestMapping(value = "/get_salesOrder", method = RequestMethod.GET)
	public ModelAndView getSalesOrderData(@RequestParam("id") Integer id,
			@RequestParam("opr") String opr, ModelMap model) {
		SalesOrderMasterForm salesOrderMasterForm = new SalesOrderMasterForm();
		ModelAndView mav = preloadedData();
		logger.info("Get Sales Order : " + id);
		logger.info("Opr : " + opr);

		SalesOrderMasterOutputMessage salesOrderMasterOutMessage = null;
		if (id != null && !id.equals(0)) {
			SalesOrderMasterInputMessage salesOrderMasterInputMessage = new SalesOrderMasterInputMessage();
			SalesOrderMasterDTO salesOrderMasterDTO = new SalesOrderMasterDTO();
			salesOrderMasterDTO.setSalesOrderAutoId(id);
			salesOrderMasterInputMessage
					.setSalesOrderMasterDTO(salesOrderMasterDTO);
			if ("R".equals(opr)) {
				salesOrderMasterOutMessage = salesOrderMasterService
						.checkBeforeRemove(salesOrderMasterInputMessage);
				if (salesOrderMasterOutMessage != null) {
					ErrorListDTO errorListDTO = salesOrderMasterOutMessage
							.getErrorListDTO();
					if (errorListDTO != null && errorListDTO.hasErrors()) {
						ErrorDTO errorDTO = errorListDTO.getErrorList().get(0);
						model.addAttribute("errors", errorDTO);
						/*
						 * ModelAndView mav = new ModelAndView(
						 * "forward:get_salesOrder_list");
						 */
						mav.setViewName("forward:get_salesOrder_list");
						return mav;
					}
				}
			}
			salesOrderMasterOutMessage = salesOrderMasterService
					.findSalesOrderMasterById(salesOrderMasterInputMessage);
			ArrayList<SalesOrderMasterDTO> list = (ArrayList<SalesOrderMasterDTO>) salesOrderMasterOutMessage
					.getSalesOrderMasterDTOList();
			if (list != null && list.size() > 0) {
				salesOrderMasterDTO = list.get(0);
				List<SalesOrderDetailDTO> detailDTOList = salesOrderMasterDTO
						.getSalesOrderDetailDTOList();
				if (detailDTOList != null) {
					for (SalesOrderDetailDTO salesOrderDetailDTO : detailDTOList) {
						if ("VAT".equals(salesOrderMasterDTO.getTaxType())) {
							salesOrderDetailDTO.setTaxPerc(salesOrderDetailDTO
									.getVatPerc());
						}
						if ("CST".equals(salesOrderMasterDTO.getTaxType())) {
							salesOrderDetailDTO.setTaxPerc(salesOrderDetailDTO
									.getCstPerc());
						}
					}
				}
				// GATE PARTY CITY STATE INFO START
				PartyInputMessage partyInputMessage = new PartyInputMessage();
				PartyDTO partyDTO = new PartyDTO();
				partyDTO.setPartyId(salesOrderMasterDTO.getConsigneeId());
				partyInputMessage.setPartyDTO(partyDTO);

				PartyOutMessage partyOutMessage = partyService
						.findPartyById(partyInputMessage);
				List<PartyDTO> pList = new ArrayList<PartyDTO>();
				pList = (ArrayList<PartyDTO>) partyOutMessage.getPartyDTOList();
				try {
					partyDTO = pList.get(0);
				} catch (Exception e) {
				}
				// Get state name
				CityDTO cityDTO = new CityDTO();
				CityInputMessage cityInputMessage = new CityInputMessage();
				cityDTO.setCityId(partyDTO.getBillingCityId());
				cityInputMessage.setCityDTO(cityDTO);

				CityOutputMessage cityOutputMessage = cityService
						.findCityById(cityInputMessage);
				ArrayList<CityDTO> cityList = (ArrayList<CityDTO>) cityOutputMessage
						.getCityDTOList();
				if (cityList != null) {
					try {
						cityDTO = cityList.get(0);
					} catch (Exception e) {
					}
				}

				salesOrderMasterForm.setConsigneeCityName(partyDTO
						.getCityName());
				try {
					salesOrderMasterForm.setConsigneeState(cityDTO.getAreaDTO()
							.getRegionDTO().getStateDTO().getStateName());
				} catch (Exception e) {

				}
				salesOrderMasterForm.setConsigneeBillingAddress(partyDTO
						.getBillingAddress());
				salesOrderMasterForm.setConsigneeContactPerson(partyDTO
						.getContactPerson1());
				salesOrderMasterForm.setConsigneePhonNo(partyDTO.getPhoneO1());
				// GATE PARTY CITY STATE INFO END
				// To check is sales order is used or not in invoice
				List<SalesOrderDetailDTO> l = salesOrderMasterDTO
						.getSalesOrderDetailDTOList();
				Boolean flag = false;
				if (l != null && l.size() > 0) {
					for (int i = 0; i < l.size(); i++) {
						SalesOrderDetailDTO sod = l.get(i);
						String invoiceNumber = sod.getInvoiceNumber();
						if (invoiceNumber != null) {
							//system.out.println("IN SIDE CONDITION >>>>>>>>>>>>>>>>>>>>>>>");
							flag = true;
							salesOrderMasterDTO.setIsUsedInInvoice(flag);
						} else {
							salesOrderMasterDTO.setIsUsedInInvoice(flag);
						}
					}
				}
				// End

				salesOrderMasterForm
						.setSalesOrderMasterDTO(salesOrderMasterDTO);
				calcSalesOrder(salesOrderMasterDTO);
			}
		}

		List<PartyDTO> partyDTOList = partyList();
		if (partyDTOList != null
				&& salesOrderMasterForm.getSalesOrderMasterDTO() != null) {
			PartyDTO partyDTO = salesOrderMasterForm.getSalesOrderMasterDTO()
					.getPartyDTO();
			if (partyDTO != null) {
				Integer partyId = partyDTO.getPartyId();
				for (PartyDTO d : partyDTOList) {
					if (d.getPartyId().equals(partyId)) {
						partyDTO.setCityName(d.getCityName());
						partyDTO.setState(d.getState());
						partyDTO.setCountry(d.getCountry());
					}
				}
			}
		}
		//system.out.println("ItemGroupId Flag Id"+ salesOrderMasterForm.getSalesOrderMasterDTO().getItemGroupFlagId());
		model.put("salesOrderMasterForm", salesOrderMasterForm);
		model.put("partyList", partyDTOList);
		model.put("branchList", branchList());
		model.put("itemsMap", itemsMap());
		model.put("opr", opr);
		// ModelAndView mav = new ModelAndView("salesOrder-entry");
		mav.setViewName("salesOrder-entry");
		// mav.addObject("partyList",partyList());
		logger.info("IN newSalesOrder() salesOrderMasterForm-->"
				+ salesOrderMasterForm);
		return mav;
	}

	@RequestMapping(value = "/remove_salesOrder", method = RequestMethod.GET)
	public String removeSalesOrder(@RequestParam("id") Integer id, Model model) {
		logger.info("Removing..........salesOrder = " + id);
		SalesOrderMasterOutputMessage salesOrderMasterOutputMessage = null;
		if (id != null && !id.equals(0)) {
			SalesOrderMasterInputMessage salesOrderMasterInputMessage = new SalesOrderMasterInputMessage();
			SalesOrderMasterDTO salesOrderMasterDTO = new SalesOrderMasterDTO();
			salesOrderMasterDTO.setSalesOrderAutoId(id);
			salesOrderMasterInputMessage
					.setSalesOrderMasterDTO(salesOrderMasterDTO);
			salesOrderMasterOutputMessage = salesOrderMasterService
					.checkBeforeRemove(salesOrderMasterInputMessage);
			if (salesOrderMasterOutputMessage != null) {
				ErrorListDTO errorListDTO = salesOrderMasterOutputMessage
						.getErrorListDTO();
				if (errorListDTO != null && errorListDTO.hasErrors()) {
					ErrorDTO errorDTO = errorListDTO.getErrorList().get(0);
					model.addAttribute("errors", errorDTO);
					return "salesOrder-entry";
				}
			}
			salesOrderMasterDTO.setModifiedUserId(getCreatedUserId());
			salesOrderMasterOutputMessage = salesOrderMasterService
					.deleteSalesOrderMaster(salesOrderMasterInputMessage);

		}
		String succ = "Dl";
		model.addAttribute("succ", succ);

		return "redirect:/get_salesOrder_list";
	}

	private boolean isDuplicateItem(SalesOrderMasterDTO salesOrderMasterDTO,
			Integer itemId) {
		if (salesOrderMasterDTO != null
				&& salesOrderMasterDTO.getSalesOrderDetailDTOList() != null) {
			List<SalesOrderDetailDTO> list = salesOrderMasterDTO
					.getSalesOrderDetailDTOList();
			logger.info("list-----" + list);
			for (SalesOrderDetailDTO e : list) {
				if (itemId.equals(e.getItemId()) && !e.getDeletedFlag())
					return true;
			}
		}
		return false;
	}

	private PartyDTO findPartyDTO(Integer partyId) {
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
		} else {
			partyDTO = null;
		}
		return partyDTO;
	}

	// @ModelAttribute("partyList")
	public List<PartyDTO> partyList() {
		// PartyOutMessage partyOutMessage = partyService.findAllPartys();
		PartyOutMessage partyOutMessage = partyService
				.findDebtorPartyShortInfoList();
		ArrayList<PartyDTO> list = (ArrayList<PartyDTO>) partyOutMessage
				.getPartyDTOList();
		return list;
	}

	// @ModelAttribute("branchList")
	public List<BranchDTO> branchList() {
		BranchOutMessage branchOutMessage = branchService.findAllBranches();
		ArrayList<BranchDTO> list = (ArrayList<BranchDTO>) branchOutMessage
				.getBranchDTOList();
		return list;
	}

	public List<ItemGroupFlagDTO> itemGroupList() {
		ItemGroupFlagOutMessage itemGroupFlagOutMessage = itemGroupFlagService
				.findAllItemGroupFlag();
		ArrayList<ItemGroupFlagDTO> itemGroupFlagList = (ArrayList<ItemGroupFlagDTO>) itemGroupFlagOutMessage
				.getItemGroupFlagDTOList();
		return itemGroupFlagList;
	}

	public Map<Integer, ItemDTO> itemsMap() {
		return new TreeMap<Integer, ItemDTO>();
	}

	@RequestMapping(value = "/getPendingSalesOrderDate", method = RequestMethod.POST)
	public @ResponseBody JsonResponse addUser(
			@RequestParam("salesOrderDate") Date salesOrderDate) {
		JsonResponse res = new JsonResponse();
		Calendar cal = Calendar.getInstance();
		cal.setTime(salesOrderDate);
		cal.add(Calendar.DATE, 60);
		Date date = cal.getTime();
		res.setResult(date);
		res.setStatus(date.toString());
		return res;
	}

	@RequestMapping(value = "/sales_order_print_report/pdf", method = RequestMethod.GET)
	public ModelAndView doPurchaseOrderReportPDF(
			@RequestParam("SONoPrompt") String SONoPrompt,
			ModelAndView modelAndView, HttpServletResponse response,
			HttpServletRequest request) {
		logger.debug("Received request to download PDF report");
		response.setHeader("filename", "sales_order_print_report.pdf");
		response.setContentType("application/pdf");
		// response.setHeader("Content-Disposition",
		// "attachment:filename=_blank‌​");
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("SONoPrompt", SONoPrompt);

		String realPath = request
				.getSession()
				.getServletContext()
				.getRealPath(
						System.getProperty("file.separator") + "WEB-INF"
								+ System.getProperty("file.separator")
								+ "images"
								+ System.getProperty("file.separator")
								+ "shree_logo.JPG");
		parameterMap.put("Image_Loc", realPath.toString());

		modelAndView = new ModelAndView("pdfSalesOrderPrintView", parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	}

	public List isSalesOrderUseInInvoice(List<SalesOrderMasterDTO> list) {
		// ...................
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				SalesOrderMasterDTO sm = list.get(i);
				List l = sm.getSalesOrderDetailDTOList();
				Boolean flag = false;
				if (l != null && l.size() > 0) {
					for (int j = 0; j < l.size(); j++) {
						SalesOrderDetailDTO sod = (SalesOrderDetailDTO) l
								.get(j);
						String invoiceNumber = sod.getInvoiceNumber();
						if (invoiceNumber != null) {
							flag = true;
							sm.setIsUsedInInvoice(flag);
						} else {
							sm.setIsUsedInInvoice(flag);
						}
					}
				}
			}
		}
		// ...................
		return list;
	}

	@RequestMapping(value = "/getUnitName", method = RequestMethod.POST)
	public @ResponseBody JsonResponse getItemCatById(
			@RequestParam("itemId") Integer itemId,
			@RequestParam("UOM") Integer UOMId) {
		ItemDTO itemDTO = new ItemDTO();
		JsonResponse res = new JsonResponse();
		itemDTO.setItemId(itemId);
		ItemInputMessage itemInputMessage = new ItemInputMessage();
		itemInputMessage.setItemDTO(itemDTO);
		ItemOutMessage itemOutMessage = itemService
				.findItemById(itemInputMessage);
		List<ItemDTO> itList = itemOutMessage.getItemDTOList();
		if (itList != null && itList.size() > 0) {
			itemDTO = itList.get(0);
		}
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		MastersDTO mastersDTO = new MastersDTO();
		if (UOMId == 1) {
			mastersDTO.setMastersId(itemDTO.getMasterPrimaryConverUnit());
			res.setResult2(itemDTO.getSecondaryConversion());
			res.setResult3(itemDTO.getMasterPrimaryConverUnit());
		} else {
			mastersDTO.setMastersId(itemDTO.getMasterSecondaryConverUnit());
			res.setResult2(itemDTO.getSecondaryConversion());
			res.setResult3(itemDTO.getMasterSecondaryConverUnit());
		}
		mastersInputMessage.setMastersDTO(mastersDTO);

		MastersOutputMessage mastersOutputMessage = mastersetService
				.findMastersById(mastersInputMessage);
		List<MastersDTO> masterList = mastersOutputMessage.getMastersDTOList();
		if (masterList != null && masterList.size() > 0) {
			mastersDTO = masterList.get(0);
			//system.out.println(mastersDTO);
			res.setStatus("SUCCESS");
		}

		//system.out.println(mastersDTO);
		// res.setStatus("SUCCESS");
		res.setResult(mastersDTO.getName());

		// set Primary UOM
		res.setResult1(itemDTO.getMasterPrimaryConverUnit());
		return res;

	}

	/*
	 * @RequestMapping(value = "/changeMasterUnit", method = RequestMethod.POST)
	 * public String changeMasterUnit(
	 * 
	 * @ModelAttribute("salesOrderMasterForm") SalesOrderMasterForm
	 * salesOrderMasterForm, Model model, @ModelAttribute("index") int index) {
	 * 
	 * List<SalesOrderDetailDTO> list =salesOrderMasterForm
	 * .getSalesOrderMasterDTO().getSalesOrderDetailDTOList(); if (list!=null &&
	 * list.size()>0) { SalesOrderDetailDTO salesOrderDetailDTO=
	 * list.get(index);
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * return "success"; }
	 */
	public void sendSalesPdfMail(String salesOrderNumber,
			HttpServletResponse response, HttpServletRequest request) {
		//system.out.println("CALLING...............................");
		File pdffile = null;
		try {
			Connection conn = dataSource.getConnection();
			// Get the jasper report object
			// Load it
			InputStream reportStream = this.getClass().getResourceAsStream(
					"/reports/Order_Confirmation.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(reportStream);

			File directory = new File(".");
			String reportName = "Order_Acknowledgement.pdf";

			pdffile = new File(directory.getCanonicalPath() + File.separator
					+ reportName);

			Map<String, Object> parameterMap = new HashMap<String, Object>();

			parameterMap.put("SoMedPrompt", salesOrderNumber);
			String realPath = request
					.getSession()
					.getServletContext()
					.getRealPath(
							System.getProperty("file.separator") + "WEB-INF"
									+ System.getProperty("file.separator")
									+ "images"
									+ System.getProperty("file.separator")
									+ "shree_logo.JPG");
			parameterMap.put("Image_Loc", realPath.toString());

			String realPath1 = request
					.getSession()
					.getServletContext()
					.getRealPath(
							System.getProperty("file.separator") + "WEB-INF"
									+ System.getProperty("file.separator")
									+ "images"
									+ System.getProperty("file.separator")
									+ "ShreeCera.jpg");
			parameterMap.put("Image_Loc_1", realPath1.toString());

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameterMap, conn);
			// Create report exporter to be in Html
			JRExporter exporter;
			exporter = new JRPdfExporter();
			FileOutputStream fos = new FileOutputStream(pdffile);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
			exporter.exportReport();
			// Export the report, store to sb
			exporter.exportReport();

			conn.close();

			String emailId = null;
			try {
				emailId = partyService.getEmailId(salesOrderNumber,
						"Sales_Order");
				emailId.trim();
			} catch (Exception e) {
			}

			String[] emailIds = null;
			try {
				emailIds = emailId.split(",");
			} catch (Exception e) {
			}
			// String[] emailIds = new String[1];
			// emailIds[0]=emailId;

			MailPdf mail = new MailPdf(pdffile, reportName);

			String body = "Dear Sir,"
					+ "\n"
					+ "\n"
					+ "Please find below attached PDF report, auto generated from ERP system."
					+ "\n"
					+ "\n"

					+ "\n"
					+ "\n"
					+ "Thanks & Regards"
					+ "\n"
					+ "Shree Ceramic Fiber Pvt Ltd."
					+ "\n"
					+ "\n"
					+ "***This is an auto generated email. Please don't reply on this email id.Please contact your system administrator for any query.";

			mail.sendSSLMessage(emailIds, pdffile.getName(), body,
					"abc@gmail.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
