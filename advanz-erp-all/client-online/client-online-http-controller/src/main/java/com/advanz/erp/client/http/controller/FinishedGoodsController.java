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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.advanz.DataUtility;
import com.advanz.erp.client.http.controller.form.FinishedGoodsMasterForm;
import com.advanz.erp.client.http.controller.form.ItemForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.BarcodeLedgerDTO;
import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.CompanyDTO;
import com.advanz.erp.masters.model.FinishedGoodsDetailDTO;
import com.advanz.erp.masters.model.FinishedGoodsMasterDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.StockLedgerDTO;
import com.advanz.erp.masters.model.StoreLocationDTO;
import com.advanz.erp.masters.model.TransactionTypeDTO;
import com.advanz.erp.masters.model.criteria.BatchSearchCriteriaDTO;
import com.advanz.erp.masters.model.criteria.FinishedGoodsMasterSearchCriteriaDTO;
import com.advanz.erp.masters.model.criteria.ItemSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.BarcodeLedgerInputMessage;
import com.advanz.erp.masters.model.msg.BatchInputMessage;
import com.advanz.erp.masters.model.msg.BatchOutMessage;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.model.msg.CompanyOutMessage;
import com.advanz.erp.masters.model.msg.FinishedGoodsMasterInputMessage;
import com.advanz.erp.masters.model.msg.FinishedGoodsMasterOutputMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.model.msg.StockLedgerInputMessage;
import com.advanz.erp.masters.model.msg.StockLedgerOutMessage;
import com.advanz.erp.masters.model.msg.StoreLocationOutMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeInputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeOutputMessage;
import com.advanz.erp.masters.service.business.IBarcodeLedgerService;
import com.advanz.erp.masters.service.business.IBatchService;
import com.advanz.erp.masters.service.business.IBlanketProductionMasterService;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.service.business.ICompanyService;
import com.advanz.erp.masters.service.business.IFinishedGoodsMasterService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IStockLedgerService;
import com.advanz.erp.masters.service.business.IStoreLocationService;
import com.advanz.erp.masters.service.business.ITransactionTypeService;

@Controller
@SessionAttributes({ "finishedGoodsMasterForm", "branchList", "itemsMap",
		"opr", "shift" })
public class FinishedGoodsController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(FinishedGoodsController.class);

	@Autowired
	public IItemService itemService;

	@Autowired
	public IBranchService branchService;
	@Autowired
	public ITransactionTypeService transactionTypeService;
	@Autowired
	public IFinishedGoodsMasterService finishedGoodsMasterService;

	@Autowired
	public IBatchService batchService;

	@Autowired
	public IStoreLocationService storeLocationService;

	@Autowired
	public IStockLedgerService stockLedgerService;
	@Autowired
	public IMastersService mastersService;
	@Autowired
	public IBlanketProductionMasterService blanketProductionMasterService;

	@Autowired
	ICompanyService companyService;

	@Autowired
	public IBarcodeLedgerService barcodeLedgerService;

	@RequestMapping(value = "/get_finishedGoods_list")
	public ModelAndView searchFinishedGoods(
			@ModelAttribute("soSearchCriteria") FinishedGoodsMasterSearchCriteriaDTO searchCriteria,
			@ModelAttribute("finishedGoodsMasterForms") FinishedGoodsMasterForm finishedGoodsMasterForm,
			Model model,
			@RequestParam(value = "menuId", required = false) String menuId,
			HttpSession session,
			@RequestParam(value = "next", required = false) Integer next,
			@RequestParam(value = "opr", required = false) String opr) {
		if (menuId != null) {
			session.setAttribute("menuId", menuId);
		}
		List<FinishedGoodsMasterDTO> list = new ArrayList<FinishedGoodsMasterDTO>();
		String succ = "Blk";
		ModelAndView mav = new ModelAndView("finishedGoods-list");
		try {
			FinishedGoodsMasterOutputMessage finishedGoodsOutputMessage = null;
			FinishedGoodsMasterInputMessage finishedGoodsInputMessage = new FinishedGoodsMasterInputMessage();

			if ("search".equalsIgnoreCase(opr)) {
				finishedGoodsInputMessage.setSearchCriteria(searchCriteria);
				finishedGoodsOutputMessage = finishedGoodsMasterService
						.search(finishedGoodsInputMessage);
			} else {
				finishedGoodsInputMessage = new FinishedGoodsMasterInputMessage();
				if (next == null || next < 0) {
					next = 0;
					finishedGoodsInputMessage.setNext(next);
					finishedGoodsOutputMessage = finishedGoodsMasterService
							.findFinishedGoodsForPagination(finishedGoodsInputMessage);
				} else {
					finishedGoodsInputMessage.setNext(next);
					finishedGoodsOutputMessage = finishedGoodsMasterService
							.findFinishedGoodsForPagination(finishedGoodsInputMessage);
				}
				finishedGoodsMasterForm.setNext(next);
				finishedGoodsMasterForm.setPrevious(next);
			}
			list = (ArrayList<FinishedGoodsMasterDTO>) finishedGoodsOutputMessage
					.getFinishedGoodsMasterDTOList();
			logger.info("Getting Item for  getTotalQuantity : "
					+ list.get(0).getTotalQuantity());
			mav.addObject("finishedGoodsMasterForms", finishedGoodsMasterForm);
			mav.addObject("fgmList", list);

		} catch (Exception e) {
			logger.error("Exception in Finished Good", e);
		}
		if (list.equals(null) || list.size() == 0) {
			model.addAttribute("succ", succ);
		}

		// mav.addObject("finishedGoodsSearchCriteria",searchCriteria);
		return mav;
	}

	@RequestMapping("/removeItemFromFG")
	public ModelAndView removeItemFrom(
			@ModelAttribute("finishedGoodsMasterForm") FinishedGoodsMasterForm finishedGoodsMasterForm,
			@RequestParam("index") Integer index,
			@ModelAttribute("opr") String opr) {

		List<FinishedGoodsDetailDTO> detailDTOList = finishedGoodsMasterForm
				.getFinishedGoodsMasterDTO().getFinishedGoodsDetailDTOList();
		if (detailDTOList != null && detailDTOList.size() > index) {
			FinishedGoodsDetailDTO dto = detailDTOList.get(index);
			// if(StringUtils.hasText(opr) && "E".equals(opr)){
			// dto.setDeletedFlag(true);
			// }
			// else
			detailDTOList.remove(dto);
			// }
		}
		// calcSalesOrder(salesOrderMasterForm.getSalesOrderMasterDTO());
		ModelAndView mav = new ModelAndView("finishedGoods-entry");
		return mav;
	}

	@RequestMapping(value = "/get_item_by_id1")
	public @ResponseBody ItemDTO getItemById(@RequestParam("id") Integer itemId) {
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

	@RequestMapping(value = "/show_item_list_fg")
	public ModelAndView showItemSelectionForm(
			@ModelAttribute("finishedGoodsMasterForm") FinishedGoodsMasterForm finishedGoodsMasterForm,
			@ModelAttribute("searchCriteria") ItemSearchCriteriaDTO searchCriteria,
			@RequestParam(value = "itemID", required = false) Integer itemId,
			@RequestParam(value = "btn", required = false) String btn,
			@ModelAttribute("itemsMap") Map<Integer, ItemDTO> itemMap,
			@ModelAttribute("searchCriteriaBatch") BatchSearchCriteriaDTO searchCriteriaBatch,
			Model model,
			@RequestParam(value = "next", required = false) Integer next,
			@RequestParam(value = "operation", required = false) String operation) {
		logger.info("----------show_item_list_fg----------------=");
		logger.info("--------------------------Button=" + btn);
		logger.info("--------------------------ItemId=" + itemId);
		if (btn != null) {
			return addItemInFinishedGoods(finishedGoodsMasterForm, itemId,
					itemMap, searchCriteriaBatch, model);
		}

		ModelAndView mav = new ModelAndView("item-list-for-fg");
		if (searchCriteria == null) {
			searchCriteria = new ItemSearchCriteriaDTO();
		}
		ArrayList<ItemDTO> list = null;
		ItemOutMessage itemOutMessage = new ItemOutMessage();
		if ("Search".equals(operation)) {
			// ItemOutMessage itemOutMessage = null; //
			// itemService.findAllItem();
			ItemInputMessage itemInputMessage = new ItemInputMessage();
			ItemDTO itemDTO = new ItemDTO();
			if (searchCriteria != null) {
				itemDTO.setInvoiceName(searchCriteria.getInvoiceName());
				itemDTO.setItemCode(searchCriteria.getItemCode());
			}
			itemInputMessage.setItemDTO(itemDTO);
			itemOutMessage = itemService
					.searchFinishedFoodItems(itemInputMessage);

			list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
		} else {
			// Pagination Start
			ItemForm itemForm = new ItemForm();
			ItemDTO dto = new ItemDTO();

			ItemInputMessage inputMessage = new ItemInputMessage();
			if (next == null || next < 0) {
				next = 0;
				dto.setNext(next);
				inputMessage.setItemDTO(dto);
				itemOutMessage = itemService
						.finishGoodItemListWithPagination(inputMessage);
				next = 13;
			} else {
				next = next + 13;
				dto.setNext(next);
				inputMessage.setItemDTO(dto);
				itemOutMessage = itemService
						.finishGoodItemListWithPagination(inputMessage);
			}
			// Pagination End
			list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
			dto.setNext(next);
			dto.setPrevious(next - 26);
			itemForm.setItemDTO(dto);
			itemForm.setItemList(list);
			FinishedGoodsMasterDTO finishedGoodsMasterDTO = finishedGoodsMasterForm
					.getFinishedGoodsMasterDTO();
			finishedGoodsMasterDTO.setNext(next);
			finishedGoodsMasterDTO.setPrevious(next - 26);
		}

		itemMap.clear();
		for (ItemDTO e : list) {
			itemMap.put(e.getItemId(), e);
		}
		mav.addObject("searchCriteria", searchCriteria);
		mav.addObject("itemList", list);
		mav.addObject("itemFor", "F");
		mav.addObject("btn", null);

		mav.addObject("finishedGoodsMasterForm", finishedGoodsMasterForm);
		return mav;
	}

	private String getFinishedGoodsTransactionSeries() {
		TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
		transactionTypeDTO.setSno(1);
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

	@RequestMapping(value = "/new_finishedGoods", method = RequestMethod.GET)
	public ModelAndView newFinishedGoods(
			ModelMap model,
			@RequestParam(value = "operation", required = false) String operation) {
		FinishedGoodsMasterForm finishedGoodsMasterForm = new FinishedGoodsMasterForm();
		FinishedGoodsMasterDTO finishedGoodsMasterDTO = new FinishedGoodsMasterDTO();
		String series = getFinishedGoodsTransactionSeries();
		finishedGoodsMasterDTO.setFinYear(getFinYear());
		finishedGoodsMasterDTO.setTransactionSeries(series);

		FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage = new FinishedGoodsMasterInputMessage();
		finishedGoodsMasterInputMessage
				.setFinishedGoodsMasterDTO(finishedGoodsMasterDTO);
		FinishedGoodsMasterOutputMessage finishedGoodsMasterOutputMessage = finishedGoodsMasterService
				.getNewFinishedGoodsSeriesNo(finishedGoodsMasterInputMessage);
		Integer orderID = finishedGoodsMasterOutputMessage
				.getFinishedGoodsSeriesNo();
		finishedGoodsMasterDTO.setFinishGoodId(orderID);
		finishedGoodsMasterDTO.setOrderSeries(finishedGoodsMasterDTO
				.getTransactionSeries()
				+ "/"
				+ finishedGoodsMasterDTO.getFinYear());
		finishedGoodsMasterDTO.setFinishedGoodsNumber(finishedGoodsMasterDTO
				.getTransactionSeries()
				+ "/"
				+ finishedGoodsMasterDTO.getFinYear()
				+ "/"
				+ finishedGoodsMasterDTO.getFinishGoodId());
		// set last finished good date
		// FinishedGoodsMasterOutputMessage finishedGoodsMasterOutputMessage2 =
		// finishedGoodsMasterService.getNewFinishedGoodsSeriesDate(finishedGoodsMasterInputMessage);
		Timestamp timestamp = finishedGoodsMasterOutputMessage
				.getFinishedGoodsSeriesDate();
		// System.out.println("Last Finished good time"+new
		// Date(timestamp.getTime()));
		SimpleDateFormat ft = new SimpleDateFormat("yyyy,MM,dd");
		// new Date(timestamp.getTime());
		finishedGoodsMasterDTO.setFinishGoodDate(new Date());

		/*
		 * Temp change finishedGoodsMasterDTO.setFinishGoodDate(new Date());
		 */

		/*
		 * if(operation!=null && operation.equals("shift report")){
		 * finishedGoodsMasterDTO.setCheckShiftReport(operation); }
		 */
		finishedGoodsMasterForm
				.setFinishedGoodsMasterDTO(finishedGoodsMasterDTO);
		// finishedGoodsMasterForm.setFinishedGoodsMasterDTO(new
		// FinishedGoodsMasterDTO());
		finishedGoodsMasterForm.setLastFinishedGoodDate(ft.format(new Date(
				timestamp.getTime())));

		model.put("finishedGoodsMasterForm", finishedGoodsMasterForm);
		model.put("branchList", branchList());
		model.put("itemsMap", itemsMap());
		model.put("opr", "N");
		ModelAndView mav = new ModelAndView("finishedGoods-entry");
		// mav.addObject("partyList",partyList());
		logger.info("IN newFinishedGoods() finishedGoodsMasterForm-->"
				+ finishedGoodsMasterForm);
		return mav;
	}

	@RequestMapping("/addItemInFG")
	public ModelAndView addItemInFinishedGoods(
			@ModelAttribute("finishedGoodsMasterForm") FinishedGoodsMasterForm finishedGoodsMasterForm,
			@RequestParam("itemID") Integer itemId,
			@ModelAttribute("itemsMap") Map<Integer, ItemDTO> itemMap,
			@ModelAttribute("searchCriteriaBatch") BatchSearchCriteriaDTO searchCriteria,
			Model model) {
		logger.info("IN addItem()  finishedGoodsMasterForm-->"
				+ finishedGoodsMasterForm);
		logger.info("Add Item : " + itemId);
		FinishedGoodsMasterDTO finishedGoodsMasterDTO = finishedGoodsMasterForm
				.getFinishedGoodsMasterDTO();
		if (itemId != null) {
			// if (!isDuplicateItem(finishedGoodsMasterDTO, itemId)) {
			ItemOutMessage itemOutputMessage = null;
			ItemInputMessage itemInputMessage = new ItemInputMessage();
			ItemDTO itemDTOTemp = new ItemDTO();
			itemDTOTemp.setItemId(itemId);
			itemInputMessage.setItemDTO(itemDTOTemp);
			itemOutputMessage = itemService.findItemById(itemInputMessage);
			ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutputMessage
					.getItemDTOList();
			if (list.size() == 1) {
				itemDTOTemp = list.get(0);
				Integer batchFlag = itemDTOTemp.getBatchFlag();
				if (batchFlag == 1) {
					ModelAndView mav = new ModelAndView(
							"get-batch-list-for-item");
					BatchOutMessage batchOutMessage = null;
					if (itemId != 0) {
						BatchInputMessage batchInputMessage = new BatchInputMessage();
						// itemDTO = new ItemDTO();
						batchInputMessage.setItemId(itemId);
						// itemDTO.setItemId(itemId);
						// itemInputMessage.setItemDTO(itemDTO);
						logger.info("batchInputMessage msg : "
								+ batchInputMessage.getItemId());
						batchOutMessage = batchService
								.findAllBatchByItemId(batchInputMessage);

						// ArrayList<BatchDTO> listTemp = (ArrayList<BatchDTO>)
						// batchOutMessage.getBatchDTOList();
						ArrayList<BatchDTO> listTemp = getItemList(
								finishedGoodsMasterForm, itemId);
						if (listTemp.size() > 0) {
							mav.addObject("batchList", listTemp);
							mav.addObject("itemId", itemId);
						} else {
							ErrorDTO error = new ErrorDTO();
							error.setErrorMsg("Warning : Please Add Batch For This Item");
							model.addAttribute("errors", error);
						}
					}
					FinishedGoodsMasterSearchCriteriaDTO finishedGoodsMasterSearchCriteriaDTO = new FinishedGoodsMasterSearchCriteriaDTO();
					mav.addObject("finishedGoodsMasterSearchCriteriaDTO",
							finishedGoodsMasterSearchCriteriaDTO);
					mav.addObject("finishedGoodsMasterForm",
							finishedGoodsMasterForm);
					mav.addObject("btn", null);
					return mav;
				}
			}

			// ItemInputMessage itemInputMessage = new ItemInputMessage();
			ItemDTO itemDTO = itemMap.get(itemId);
			if (itemDTO != null) {
				FinishedGoodsDetailDTO finishedGoodsDetailDTO = new FinishedGoodsDetailDTO();
				BeanUtils.copyProperties(itemDTO, finishedGoodsDetailDTO);
				finishedGoodsDetailDTO
						.setTransactionSeries(finishedGoodsMasterDTO
								.getTransactionSeries());
				finishedGoodsDetailDTO.setItemName(itemDTO.getItemName());
				finishedGoodsDetailDTO.setQuantity(1.0);

				MastersDTO mastersDTO = new MastersDTO();
				mastersDTO.setMastersId(itemDTO.getMasterUnit().getMastersId());
				mastersDTO.setName(itemDTO.getMasterUnit().getName());

				finishedGoodsDetailDTO.setMeasurementUnitMasterDTO(mastersDTO);

				// finishedGoodsDetailDTO.setMeasurementUnitMasterDTO(itemDTO.getMasterUnitDTO());
				finishedGoodsDetailDTO
						.setFinishedGoodsNumber(finishedGoodsMasterDTO
								.getFinishedGoodsNumber());
				finishedGoodsDetailDTO.setTransientObject(true);
				finishedGoodsDetailDTO.setEntryStatus("Manual");
				if (finishedGoodsMasterDTO.getFinishedGoodsDetailDTOList() == null) {
					finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
							.setFinishedGoodsDetailDTOList(
									new ArrayList<FinishedGoodsDetailDTO>());
				}
				finishedGoodsMasterDTO.getFinishedGoodsDetailDTOList().add(
						finishedGoodsDetailDTO);
				logger.info("After add Item Size="
						+ finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
								.getFinishedGoodsDetailDTOList().size());
			}
		}
		ModelAndView mav = new ModelAndView("finishedGoods-entry");
		mav.addObject("finishedGoodsMasterForm", finishedGoodsMasterForm);
		// return mav;
		ModelAndView mv = new ModelAndView(new RedirectView(
				"addItemInFGViewEntryForm?finishedGoodsMasterForm="
						+ finishedGoodsMasterForm));
		return mv;
	}

	@RequestMapping("/addItemInFGViewEntryForm")
	public ModelAndView addItemInFinishedGoods1(
			@ModelAttribute("finishedGoodsMasterForm") FinishedGoodsMasterForm finishedGoodsMasterForm) {

		ModelAndView mav = new ModelAndView("finishedGoods-entry");
		mav.addObject("finishedGoodsMasterForm", finishedGoodsMasterForm);
		return mav;
	}

	private ArrayList<BatchDTO> getItemList(
			FinishedGoodsMasterForm finishedGoodsMasterForm, Integer itemId) {

		BatchInputMessage batchInputMessage = new BatchInputMessage();
		// BatchDTO batchDTO = new BatchDTO();
		batchInputMessage.setItemId(itemId);
		BatchOutMessage batchOutMessage = batchService
				.findAllBatchByItemId(batchInputMessage);
		ArrayList<BatchDTO> list = (ArrayList<BatchDTO>) batchOutMessage
				.getBatchDTOList();

		for (int i = 0; i < list.size(); i++) {
			// ItemDTO dto = new ItemDTO();
			BatchDTO dto = new BatchDTO();
			dto = list.get(i);
		}
		int size = list.size();
		// itemForm.setItemList(list);
		Iterator<BatchDTO> e = null;
		BatchDTO batchDTO = null;

		FinishedGoodsMasterDTO finishedGoodsMasterDTO = finishedGoodsMasterForm
				.getFinishedGoodsMasterDTO();

		try {
			if (finishedGoodsMasterForm != null) {
				for (FinishedGoodsDetailDTO billDto : finishedGoodsMasterForm
						.getFinishedGoodsMasterDTO()
						.getFinishedGoodsDetailDTOList()) {

					for (int i = 0; i < size; i++) {
						try {
							batchDTO = list.get(i);
						} catch (Exception e1) {
						}
						if (billDto.getItemId().equals(
								batchDTO.getItemDTO().getItemId())) {
							list.remove(i);
							break;
						}
					}
				}
			}
		} catch (Exception ex) {
			logger.error("Exception in Finished Good", e);
		}
		return list;
	}

	@RequestMapping(value = "/saveFinishedGoods", method = RequestMethod.POST)
	public String saveFinishedGoods(
			@ModelAttribute("finishedGoodsMasterForm") FinishedGoodsMasterForm finishedGoodsMasterForm,
			Model model, ModelMap modelMap, HttpSession session) {
		logger.info("finishedGoodsForm getFinishedGoodsMasterDTO  = "
				+ finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
						.toString());

		int itemCount = 0;
		if (finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
				.getFinishedGoodsDetailDTOList() != null
				&& finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
						.getFinishedGoodsDetailDTOList().size() > 0) {
			for (FinishedGoodsDetailDTO d : finishedGoodsMasterForm
					.getFinishedGoodsMasterDTO()
					.getFinishedGoodsDetailDTOList()) {
				if (!d.getDeletedFlag()) {

					if (d.getQuantity().doubleValue() == 0.0) {
						ErrorDTO error = new ErrorDTO();
						error.setErrorMsg("Error : Item Quantity value can be 0 (Zero)");
						model.addAttribute("errors", error);
						return "finishedGoods-entry";
					}

					itemCount++;
				}
			}
		}
		if (itemCount == 0) {
			ErrorDTO error = new ErrorDTO();
			error.setErrorMsg("Error : Finished Goods Without Items");
			model.addAttribute("errors", error);
			return "finishedGoods-entry";
		}

		StockLedgerInputMessage stockInputmessage = new StockLedgerInputMessage();
		StockLedgerDTO ledgerDTO = null;

		FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage = new FinishedGoodsMasterInputMessage();
		FinishedGoodsMasterDTO finishedGoodsMasterDTO = finishedGoodsMasterForm
				.getFinishedGoodsMasterDTO();
		if (finishedGoodsMasterDTO.getApprovalFlag() != null
				&& finishedGoodsMasterDTO.getApprovalFlag() > 0) {
			finishedGoodsMasterDTO.setAprovedDate(new Date());
		}

		finishedGoodsMasterInputMessage
				.setFinishedGoodsMasterDTO(finishedGoodsMasterDTO);
		String succ = "";
		FinishedGoodsMasterOutputMessage finishedGoodsMasterOutputMessage = null;
		if (finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
				.getFinishedGoodsAutoId() == null
				|| finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
						.getFinishedGoodsAutoId().equals(0)) {
			// Convert Finish Good date into date and time
			Date date = finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
					.getFinishGoodDate();
			date = DataUtility.getDate(date);
			finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
					.setFinishGoodDate(date);
			finishedGoodsMasterForm.getFinishedGoodsMasterDTO().setFinYear(
					getFinYear());
			finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
					.setTransactionSeries(getFinishedGoodsTransactionSeries());
			logger.info("finishedGoodsForm FinishedGoodsAutoId is null ");
			if (finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
					.getCheckShiftReport() != null
					&& finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
							.getCheckShiftReport().equals("shift report")) {
				session.setAttribute("finishGoodNumber",
						finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
								.getFinishedGoodsNumber());
			}
			finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
					.setCreatedUserId(getCreatedUserId());
			finishedGoodsMasterOutputMessage = finishedGoodsMasterService
					.createFinishedGoodsMaster(finishedGoodsMasterInputMessage);

			logger.info("finishedGoodsForm FinishedGoodsAutoId is null 1 ");
			List list = finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
					.getFinishedGoodsDetailDTOList();
			for (int i = 0; i < list.size(); i++) {
				FinishedGoodsDetailDTO finishedGoodsDetailDTO = (FinishedGoodsDetailDTO) list
						.get(i);
				ledgerDTO = new StockLedgerDTO();
				logger.info("finishedGoodsForm FinishedGoodsAutoId is null and entry in stock ");
				ledgerDTO.setBranchId(finishedGoodsMasterDTO.getBranchDTO()
						.getBranchId());
				ledgerDTO.setBatchNo(finishedGoodsDetailDTO.getBatchNo());
				ledgerDTO.setTransactionDate(DataUtility
						.getDate(finishedGoodsMasterDTO.getFinishGoodDate()));
				ledgerDTO.setItemId(finishedGoodsDetailDTO.getItemId());
				ledgerDTO.setQuantity(finishedGoodsDetailDTO.getQuantity());
				ledgerDTO.setTransactionId(finishedGoodsMasterForm
						.getFinishedGoodsMasterDTO().getFinishGoodId());
				ledgerDTO.setTransactionSeries("FG");
				ledgerDTO.setTransactionNumber(finishedGoodsDetailDTO
						.getFinishedGoodsNumber());
				if (finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
						.getApprovalFlag() != null
						&& finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
								.getApprovalFlag() > 0) {
					ledgerDTO.setApprovedDate(new Date());
				}
				ledgerDTO.setApprovedQuantity(finishedGoodsDetailDTO
						.getQuantity());
				stockInputmessage.setStockLedgerDTO(ledgerDTO);
				ledgerDTO.setSno(finishedGoodsDetailDTO.getSno());

				try {
					if (finishedGoodsMasterOutputMessage.getErrorListDTO() == null) {
						stockLedgerService.createStockLedger(stockInputmessage);
					}
				} catch (Exception e) {
					logger.error("Exception in Finished Good", e);
					e.printStackTrace();
				}

				try {
					if (finishedGoodsMasterOutputMessage.getErrorListDTO() == null) {
						BarcodeLedgerDTO barcodeLedgerDTO = new BarcodeLedgerDTO();
						BarcodeLedgerInputMessage barcodeLedgerInputMessage = new BarcodeLedgerInputMessage();
						barcodeLedgerDTO.setItemId(finishedGoodsDetailDTO
								.getItemId());
						System.out.println("getFinishGoodId"
								+ finishedGoodsMasterForm
										.getFinishedGoodsMasterDTO()
										.getFinishGoodId());
						barcodeLedgerDTO
								.setTransactionId(finishedGoodsMasterService
										.findLastFinishedGoodDetail(
												finishedGoodsMasterForm
														.getFinishedGoodsMasterDTO()
														.getFinishGoodId(),
												finishedGoodsDetailDTO
														.getItemId()));
						barcodeLedgerDTO.setNoOfBarcode(finishedGoodsDetailDTO
								.getQuantity().intValue());
						barcodeLedgerDTO
								.setTransactionType(BarcodeLedgerDTO.FINISH_GOODS);
						barcodeLedgerDTO.setBarcode(finishedGoodsDetailDTO
								.getItemId() + "");
						barcodeLedgerDTO.setCreatedUserId(getCreatedUserId());
						barcodeLedgerDTO.setModifiedUserId(getCreatedUserId());

						barcodeLedgerInputMessage
								.setBarcodeLedgerDTO(barcodeLedgerDTO);
						barcodeLedgerService
								.createBarcode(barcodeLedgerInputMessage);

					}
				} catch (Exception e) {
					logger.error("Exception in Finished Good", e);
					e.printStackTrace();
				}

			}
			succ = "Ad";
		}

		/**
		 * This code will work when we update finished good i.e when we update
		 * finished good to Approve which is entry by Blanket Production Approve
		 * form then this code will work and barcode will not generate.
		 * 
		 * @update barcode will generate for new entry in Finished good
		 */
		else {
			logger.info("finishedGoodsForm FinishedGoodsAutoId is not Null ");
			finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
					.setModifiedUserId(getCreatedUserId());

			// I am working on it...........

			List<FinishedGoodsDetailDTO> finishedGoodsDetailEntityList = finishedGoodsMasterForm
					.getFinishedGoodsMasterDTO()
					.getFinishedGoodsDetailDTOList();

			List<FinishedGoodsDetailDTO> oldFinishedGoodsDetailEntityList = new ArrayList<FinishedGoodsDetailDTO>();

			// get Old Finished Good Detail
			FinishedGoodsMasterOutputMessage oldFinishedGoodsMasterOutputMessage = finishedGoodsMasterService
					.findFinishedGoodsMasterById(finishedGoodsMasterInputMessage);
			List<FinishedGoodsMasterDTO> oldFinishedGoodMasterlist = oldFinishedGoodsMasterOutputMessage
					.getFinishedGoodsMasterDTOList();

			if (oldFinishedGoodMasterlist != null
					&& oldFinishedGoodMasterlist.size() > 0) {
				System.out.println("oldFinishedGoodMasterlist.get(0)"
						+ oldFinishedGoodMasterlist.get(0));
				oldFinishedGoodsDetailEntityList = oldFinishedGoodMasterlist
						.get(0).getFinishedGoodsDetailDTOList();
			}

			CompanyOutMessage outCompanyOutMessage = companyService
					.findAllCompanies();
			List<CompanyDTO> cList = outCompanyOutMessage.getCompanyDTOList();
			CompanyDTO dto = null;
			if (cList != null && cList.size() > 0) {
				dto = (CompanyDTO) cList.get(0);
				// System.out.println("dto.getStockLockFlag()"+dto.getStockLockFlag());
			}

			if (dto != null
					&& dto.getStockLockFlag()
					&& checkStockForFinishedGood(finishedGoodsDetailEntityList,
							oldFinishedGoodsDetailEntityList)) {

				ErrorDTO errorDTO = new ErrorDTO(
						"1",
						" sorry you can not update this Finished good beacuse one of the item will be out of stock");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				finishedGoodsMasterOutputMessage = new FinishedGoodsMasterOutputMessage();
				finishedGoodsMasterOutputMessage.setErrorListDTO(errorListDTO);

			} else {
				// if item in stock then then Update it.
				finishedGoodsMasterOutputMessage = finishedGoodsMasterService
						.updateFinishedGoodsMaster(finishedGoodsMasterInputMessage);

				List list = finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
						.getFinishedGoodsDetailDTOList();

				// Delete StockLedger Start
				StockLedgerDTO stockLedgerDTO = new StockLedgerDTO();
				stockLedgerDTO.setTransactionNumber(finishedGoodsMasterDTO
						.getFinishedGoodsNumber());
				stockInputmessage.setStockLedgerDTO(stockLedgerDTO);

				StockLedgerOutMessage stockLedgerOutMessage = stockLedgerService
						.findStockLedgerByTransactionId(stockInputmessage);
				ArrayList<StockLedgerDTO> stockLedgerList1 = (ArrayList<StockLedgerDTO>) stockLedgerOutMessage
						.getStockLedgerDTOList();
				for (int i = 0; i < stockLedgerList1.size(); i++) {
					stockLedgerDTO = stockLedgerList1.get(i);
					stockInputmessage.setStockLedgerDTO(stockLedgerDTO);
					stockLedgerService.deleteStockLedger(stockInputmessage);
				}
				// Delete StockLedger end

				for (int i = 0; i < list.size(); i++) {

					FinishedGoodsDetailDTO finishedGoodsDetailDTO = (FinishedGoodsDetailDTO) list
							.get(i);
					ledgerDTO = new StockLedgerDTO();
					ledgerDTO.setBranchId(finishedGoodsMasterDTO.getBranchDTO()
							.getBranchId());
					ledgerDTO.setBatchNo(finishedGoodsDetailDTO.getBatchNo());
					ledgerDTO
							.setTransactionDate(DataUtility
									.getDate(finishedGoodsMasterDTO
											.getFinishGoodDate()));
					ledgerDTO.setItemId(finishedGoodsDetailDTO.getItemId());
					ledgerDTO.setQuantity(finishedGoodsDetailDTO.getQuantity());
					ledgerDTO.setTransactionId(finishedGoodsMasterForm
							.getFinishedGoodsMasterDTO().getFinishGoodId());
					ledgerDTO.setTransactionSeries("FG");
					if (finishedGoodsMasterForm.getFinishedGoodsMasterDTO()
							.getApprovalFlag() != null
							&& finishedGoodsMasterForm
									.getFinishedGoodsMasterDTO()
									.getApprovalFlag() > 0) {
						ledgerDTO.setApprovedDate(new Date());
					}
					ledgerDTO.setApprovedQuantity(finishedGoodsDetailDTO
							.getQuantity());
					ledgerDTO.setTransactionNumber(finishedGoodsDetailDTO
							.getFinishedGoodsNumber());
					stockInputmessage.setStockLedgerDTO(ledgerDTO);
					// StockLedgerOutMessage ledgerOutMessage =
					// stockLedgerService.findStockLedgerByTransactionId(stockInputmessage);
					// List<StockLedgerDTO> stockLedgerList =
					// ledgerOutMessage.getStockLedgerDTOList();
					// StockLedgerDTO ledgerDTOForUpdate = new StockLedgerDTO();
					// ledgerDTOForUpdate = stockLedgerList.get(i);
					// ledgerDTO.setSno(ledgerDTOForUpdate.getSno());
					// ledgerDTO.setSno(finishedGoodsDetailDTO.getSno());

					stockLedgerService.createStockLedger(stockInputmessage);

				}

				/**
				 * @author Abhinav This code is use to add a new entry in
				 *         barcode ledger for new row(new row have empty sno) in
				 *         Finished good detail items
				 */

				for (int i = 0; i < list.size(); i++) {

					FinishedGoodsDetailDTO finishedGoodsDetailDTO = (FinishedGoodsDetailDTO) list
							.get(i);

					try {
						if (finishedGoodsDetailDTO.getSno() == null
								|| finishedGoodsDetailDTO.getSno() == 0) {
							BarcodeLedgerDTO barcodeLedgerDTO = new BarcodeLedgerDTO();
							BarcodeLedgerInputMessage barcodeLedgerInputMessage = new BarcodeLedgerInputMessage();
							barcodeLedgerDTO.setItemId(finishedGoodsDetailDTO
									.getItemId());
							System.out.println("update getFinishGoodId"
									+ finishedGoodsMasterForm
											.getFinishedGoodsMasterDTO()
											.getFinishGoodId());
							barcodeLedgerDTO
									.setTransactionId(finishedGoodsMasterService
											.findLastFinishedGoodDetail(
													finishedGoodsMasterForm
															.getFinishedGoodsMasterDTO()
															.getFinishGoodId(),
													finishedGoodsDetailDTO
															.getItemId()));
							barcodeLedgerDTO
									.setNoOfBarcode(finishedGoodsDetailDTO
											.getQuantity().intValue());
							barcodeLedgerDTO
									.setTransactionType(BarcodeLedgerDTO.FINISH_GOODS);
							barcodeLedgerDTO.setBarcode(finishedGoodsDetailDTO
									.getItemId() + "");
							barcodeLedgerDTO
									.setCreatedUserId(getCreatedUserId());
							barcodeLedgerDTO
									.setModifiedUserId(getCreatedUserId());
							barcodeLedgerInputMessage
									.setBarcodeLedgerDTO(barcodeLedgerDTO);
							barcodeLedgerService
									.createBarcode(barcodeLedgerInputMessage);

						}
					} catch (Exception e) {
						logger.error(
								"Finished good ctl error at the update portion to update finsihed good for new item: ",
								e);
					}
				}

				succ = "Up";
			}// else item in stock check End

		}
		ErrorListDTO errorListDTO = new ErrorListDTO();
		if (finishedGoodsMasterOutputMessage != null) {
			errorListDTO = finishedGoodsMasterOutputMessage.getErrorListDTO();

			if (errorListDTO != null && errorListDTO.hasErrors()) {
				ErrorDTO errorDTO = errorListDTO.getErrorList().get(0);
				logger.info(" adding Error ");
				model.addAttribute("errors", errorDTO);

				FinishedGoodsMasterDTO goodsMasterDTO = finishedGoodsMasterForm
						.getFinishedGoodsMasterDTO();
				int orderID = goodsMasterDTO.getFinishGoodId();
				orderID = orderID + 1;
				goodsMasterDTO.setFinishGoodId(orderID);
				goodsMasterDTO
						.setOrderSeries(getFinishedGoodsTransactionSeries()
								+ "/" + getFinYear());
				goodsMasterDTO
						.setFinishedGoodsNumber(getFinishedGoodsTransactionSeries()
								+ "/" + getFinYear() + "/" + orderID);

				ArrayList<FinishedGoodsDetailDTO> findetailList = (ArrayList<FinishedGoodsDetailDTO>) goodsMasterDTO
						.getFinishedGoodsDetailDTOList();
				for (int i = 0; i < findetailList.size(); i++) {
					FinishedGoodsDetailDTO detailDTO = findetailList.get(i);
					detailDTO
							.setTransactionSeries(getFinishedGoodsTransactionSeries());
					detailDTO
							.setFinishedGoodsNumber(getFinishedGoodsTransactionSeries()
									+ "/" + getFinYear() + "/" + orderID);
				}

				return "finishedGoods-entry";
			}
		}
		model.addAttribute("finishedGoodsMasterForm",
				new FinishedGoodsMasterForm());
		model.addAttribute("succ", succ);
		return "redirect:/get_finishedGoods_list";
	}

	@RequestMapping(value = "/get_finishedGoods", method = RequestMethod.GET)
	public ModelAndView getFinishedGoodsData(
			@RequestParam("id") Integer id,
			@RequestParam("opr") String opr,
			@RequestParam(value = "token", required = false) String token,
			ModelMap model,
			@RequestParam(value = "approvalFlag", required = false) Integer approvalFlag) {
		FinishedGoodsMasterForm finishedGoodsMasterForm = new FinishedGoodsMasterForm();
		logger.info("Get Sales Order : " + id);
		logger.info("Opr : " + opr);

		FinishedGoodsMasterOutputMessage finishedGoodsMasterOutMessage = null;
		if (id != null && !id.equals(0)) {

			FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage = new FinishedGoodsMasterInputMessage();
			FinishedGoodsMasterDTO finishedGoodsMasterDTO = new FinishedGoodsMasterDTO();
			finishedGoodsMasterDTO.setFinishedGoodsAutoId(id);
			finishedGoodsMasterInputMessage
					.setFinishedGoodsMasterDTO(finishedGoodsMasterDTO);
			finishedGoodsMasterOutMessage = finishedGoodsMasterService
					.findFinishedGoodsMasterById(finishedGoodsMasterInputMessage);
			ArrayList<FinishedGoodsMasterDTO> list = (ArrayList<FinishedGoodsMasterDTO>) finishedGoodsMasterOutMessage
					.getFinishedGoodsMasterDTOList();

			// finishedGoodsMasterForm.setFinishedGoodsMasterDTO(finishedGoodsMasterDTO);
			if (list != null && list.size() > 0) {
				finishedGoodsMasterDTO = list.get(0);
				finishedGoodsMasterDTO
						.setOrderSeries(getFinishedGoodsTransactionSeries()
								+ "/" + finishedGoodsMasterDTO.getFinYear());
				finishedGoodsMasterForm
						.setFinishedGoodsMasterDTO(finishedGoodsMasterDTO);

			}
		}

		model.put("finishedGoodsMasterForm", finishedGoodsMasterForm);

		model.put("branchList", branchList());
		model.put("opr", opr);
		model.put("approvalFlag", approvalFlag);
		model.put("itemsMap", itemsMap());
		model.put("token", token);
		ModelAndView mav = new ModelAndView("finishedGoods-entry");
		// mav.addObject("partyList",partyList());
		logger.info("IN newFinishedGoods() finishedGoodsMasterForm-->"
				+ finishedGoodsMasterForm);
		return mav;

	}

	@RequestMapping(value = "/remove_finishedGoods", method = RequestMethod.GET)
	public ModelAndView removeFinishedGoods(@RequestParam("id") Integer id,
			@RequestParam("finishedGoodsNumber") String finishedGoodsNumber,
			Model model) {
		logger.info("Removing..........finishedGoods = " + id);
		FinishedGoodsMasterOutputMessage finishedGoodsMasterOutputMessage = null;

		Boolean flag = blanketProductionMasterService
				.checkFinishedGood(finishedGoodsNumber);
		if (flag == true) {
			ModelAndView mav = new ModelAndView("finishedGoods-entry");
			model.addAttribute("notDelete",
					"Warning : Can not delete because it is used in blanket");
			return mav;
		}

		if (id != null && !id.equals(0)) {
			FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage = new FinishedGoodsMasterInputMessage();
			FinishedGoodsMasterDTO finishedGoodsMasterDTO = new FinishedGoodsMasterDTO();
			finishedGoodsMasterDTO.setFinishedGoodsAutoId(id);
			finishedGoodsMasterInputMessage
					.setFinishedGoodsMasterDTO(finishedGoodsMasterDTO);
			finishedGoodsMasterOutputMessage = finishedGoodsMasterService
					.deleteFinishedGoodsMaster(finishedGoodsMasterInputMessage);
			finishedGoodsMasterDTO.setFinishedGoodsAutoId(id);
			finishedGoodsMasterInputMessage
					.setFinishedGoodsMasterDTO(finishedGoodsMasterDTO);
			finishedGoodsMasterOutputMessage = finishedGoodsMasterService
					.findFinishedGoodsMasterById(finishedGoodsMasterInputMessage);
			ArrayList<FinishedGoodsMasterDTO> list = (ArrayList<FinishedGoodsMasterDTO>) finishedGoodsMasterOutputMessage
					.getFinishedGoodsMasterDTOList();

			if (finishedGoodsNumber != null && finishedGoodsNumber.length() > 0) {

				StockLedgerInputMessage stockInputmessage = new StockLedgerInputMessage();
				StockLedgerDTO stockLedgerDTO = new StockLedgerDTO();
				stockLedgerDTO.setTransactionNumber(finishedGoodsNumber);
				stockInputmessage.setStockLedgerDTO(stockLedgerDTO);

				// stockLedgerService.deleteByTransactionNumber(stockInputmessage);

				StockLedgerOutMessage stockLedgerOutMessage = stockLedgerService
						.findStockLedgerByTransactionId(stockInputmessage);
				ArrayList<StockLedgerDTO> stockLedgerList = (ArrayList<StockLedgerDTO>) stockLedgerOutMessage
						.getStockLedgerDTOList();
				for (int i = 0; i < stockLedgerList.size(); i++) {
					stockLedgerDTO = stockLedgerList.get(i);
					stockInputmessage.setStockLedgerDTO(stockLedgerDTO);
					stockLedgerService.deleteStockLedger(stockInputmessage);
				}
			}

		}

		String succ = "Dl";
		model.addAttribute("succ", succ);

		ModelAndView mv = new ModelAndView(new RedirectView(
				"get_finishedGoods_list"));
		return mv;
		// return "redirect:/get_finishedGoods_list";
	}

	private boolean isDuplicateItem(
			FinishedGoodsMasterDTO finishedGoodsMasterDTO, Integer itemId) {

		if (finishedGoodsMasterDTO != null
				&& finishedGoodsMasterDTO.getFinishedGoodsDetailDTOList() != null) {
			List<FinishedGoodsDetailDTO> list = finishedGoodsMasterDTO
					.getFinishedGoodsDetailDTOList();
			logger.info("list-----" + list);
			for (FinishedGoodsDetailDTO e : list) {
				if (itemId.equals(e.getItemId()))
					return true;
			}
		}
		return false;
	}

	private boolean isDuplicateBatch(
			FinishedGoodsMasterDTO finishedGoodsMasterDTO, String batchNo) {

		if (finishedGoodsMasterDTO != null
				&& finishedGoodsMasterDTO.getFinishedGoodsDetailDTOList() != null) {
			List<FinishedGoodsDetailDTO> list = finishedGoodsMasterDTO
					.getFinishedGoodsDetailDTOList();
			logger.info("list-----" + list);
			for (FinishedGoodsDetailDTO e : list) {
				if (batchNo.equals(e.getBatchNo()))
					return true;
			}
		}
		return false;
	}

	// @ModelAttribute("branchList")
	public List<BranchDTO> branchList() {
		BranchOutMessage branchOutMessage = branchService.findAllBranches();
		ArrayList<BranchDTO> list = (ArrayList<BranchDTO>) branchOutMessage
				.getBranchDTOList();
		return list;
	}

	@ModelAttribute("shift")
	public List<MastersDTO> shiftList() {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(11);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

	@RequestMapping(value = "/backto_finishGood", method = RequestMethod.GET)
	public ModelAndView backToFinishGood(
			@ModelAttribute("finishedGoodsMasterForm") FinishedGoodsMasterForm finishedGoodsMasterForm) {
		// calcSalesOrder(salesOrderMasterForm.getSalesOrderMasterDTO());
		ModelAndView mav = new ModelAndView("finishedGoods-entry");
		return mav;
	}

	@RequestMapping(value = "/get_batch_for_item")
	public @ResponseBody ModelAndView getAllBatchById(
			@RequestParam("id") Integer itemId,
			@RequestParam("batchNoSearch") String batchNoSearch,
			@ModelAttribute("finishedGoodsMasterForm") FinishedGoodsMasterForm finishedGoodsMasterForm,
			@ModelAttribute("searchCriteriaBatch") BatchSearchCriteriaDTO searchCriteriaBatch,
			@RequestParam(value = "btn", required = false) String btn,
			Model model) {
		logger.info("Getting Item for batch  : " + itemId);
		BatchDTO batchDTO = null;
		FinishedGoodsMasterSearchCriteriaDTO finishedGoodsMasterSearchCriteriaDTO = new FinishedGoodsMasterSearchCriteriaDTO();
		ModelAndView mav = new ModelAndView("get-batch-list-for-item");
		if (btn != null) {
			logger.info("GButton  batch  : " + btn);
			return addBatchInItemInFinishedGoods(finishedGoodsMasterForm,
					batchNoSearch, itemId);
		}
		BatchOutMessage batchOutMessage = new BatchOutMessage();
		if (itemId != null) {
			BatchInputMessage batchInputMessage = new BatchInputMessage();
			// itemDTO = new ItemDTO();
			batchInputMessage.setItemId(itemId);
			// itemDTO.setItemId(itemId);
			// itemInputMessage.setItemDTO(itemDTO);
			logger.info("batchInputMessage msg : "
					+ batchInputMessage.getItemId());
			batchOutMessage = batchService
					.findAllBatchByItemId(batchInputMessage);
			ArrayList<BatchDTO> list = (ArrayList<BatchDTO>) batchOutMessage
					.getBatchDTOList();
			if (list != null) {
				if (list.size() > 0) {
					mav.addObject("batchList", list);
					mav.addObject("itemId", itemId);
				}
			}
		} else {
			ErrorDTO error = new ErrorDTO();
			error.setErrorMsg("Warning : Please Add Batch For This Item");
			model.addAttribute("errors", error);
		}
		mav.addObject("searchCriteriaBatch", searchCriteriaBatch);
		mav.addObject("finishedGoodsMasterForm", finishedGoodsMasterForm);
		return mav;
	}

	@RequestMapping(value = "/get_batch_by_id")
	public @ResponseBody BatchDTO getBatchById(
			@RequestParam("id") String batchNo) {
		logger.info("Getting batch for  : " + batchNo);
		BatchDTO batchDTO = new BatchDTO();
		try {

			BatchOutMessage batchOutMessage = null;
			if (batchNo != null) {
				BatchInputMessage batchInputMessage = new BatchInputMessage();
				batchDTO = new BatchDTO();
				batchDTO.setBatchNo(batchNo);
				batchInputMessage.setBatchDTO(batchDTO);
				batchOutMessage = batchService
						.findBatchByBatchNo(batchInputMessage);
				ArrayList<BatchDTO> list = (ArrayList<BatchDTO>) batchOutMessage
						.getBatchDTOList();
				if (list.size() == 1) {
					batchDTO = list.get(0);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in Finished Good", e);
		}
		return batchDTO;
	}

	@RequestMapping("/addBatchInItemInFG")
	public ModelAndView addBatchInItemInFinishedGoods(
			@ModelAttribute("finishedGoodsMasterForm") FinishedGoodsMasterForm finishedGoodsMasterForm,
			@RequestParam("batchNo") String batchNo,
			@RequestParam("itemId") Integer itemId) {
		logger.info("IN addBatch()  finishedGoodsMasterForm-->"
				+ finishedGoodsMasterForm);
		logger.info("Add Item : " + batchNo);
		FinishedGoodsMasterDTO finishedGoodsMasterDTO = finishedGoodsMasterForm
				.getFinishedGoodsMasterDTO();
		if (batchNo != null) {
			if (!isDuplicateBatch(finishedGoodsMasterDTO, batchNo)) {

				BatchInputMessage batchInputMessage = new BatchInputMessage();
				BatchDTO batchDTO = new BatchDTO();
				batchDTO.setBatchNo(batchNo);
				batchInputMessage.setBatchDTO(batchDTO);
				List<BatchDTO> batchDtoList = batchService.findBatchByBatchNo(
						batchInputMessage).getBatchDTOList();
				// List<ItemDTO> itemDtoList =
				// itemService.findItemById(itemInputMessage).getItemDTOList();
				if (batchDtoList != null && batchDtoList.size() > 0) {
					batchDTO = batchDtoList.get(0);
					FinishedGoodsDetailDTO finishedGoodsDetailDTO = new FinishedGoodsDetailDTO();
					BeanUtils.copyProperties(batchDTO, finishedGoodsDetailDTO);
					finishedGoodsDetailDTO
							.setTransactionSeries(finishedGoodsMasterDTO
									.getTransactionSeries());
					finishedGoodsDetailDTO.setBatchNo(batchDTO.getBatchNo());
					finishedGoodsDetailDTO.setExpiryDate(batchDTO
							.getExpiryDate());
					finishedGoodsDetailDTO.setMfgDate(batchDTO.getMfgDate());
					finishedGoodsDetailDTO
							.setFinishedGoodsNumber(finishedGoodsMasterDTO
									.getFinishedGoodsNumber());

					if (finishedGoodsMasterDTO.getFinishedGoodsDetailDTOList() == null) {
						finishedGoodsMasterForm
								.getFinishedGoodsMasterDTO()
								.setFinishedGoodsDetailDTOList(
										new ArrayList<FinishedGoodsDetailDTO>());
					}
					ItemOutMessage itemOutputMessage = null;

					ItemInputMessage itemInputMessage = new ItemInputMessage();
					ItemDTO itemDTO = new ItemDTO();
					itemDTO.setItemId(itemId);
					itemInputMessage.setItemDTO(itemDTO);
					itemOutputMessage = itemService
							.findItemById(itemInputMessage);
					ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutputMessage
							.getItemDTOList();
					if (list.size() == 1) {
						itemDTO = list.get(0);
						String itemName = itemDTO.getInvoiceName();
						finishedGoodsDetailDTO.setItemName(itemName);
						finishedGoodsDetailDTO.setItemId(itemId);

						logger.info(itemDTO.getMasterUnit() + " : "
								+ itemDTO.getMasterUnit());
						finishedGoodsDetailDTO
								.setMeasurementUnitMasterDTO(itemDTO
										.getMasterUnit());

					}

					finishedGoodsDetailDTO.setQuantity(1.0);

					finishedGoodsMasterDTO.getFinishedGoodsDetailDTOList().add(
							finishedGoodsDetailDTO);
					logger.info("After add Batch Size="
							+ finishedGoodsMasterForm
									.getFinishedGoodsMasterDTO()
									.getFinishedGoodsDetailDTOList().size());
				}
			}

		}
		// return "redirect:/new_finishedGoods";
		ModelAndView mav = new ModelAndView("finishedGoods-entry");
		mav.addObject("finishedGoodsMasterForm", finishedGoodsMasterForm);
		return mav;
	}

	@ModelAttribute("storeLocationList")
	public List<StoreLocationDTO> storeLcationList() {
		StoreLocationOutMessage storeLocationOutMessage = storeLocationService
				.findAllStoreLocations();
		ArrayList<StoreLocationDTO> list = (ArrayList<StoreLocationDTO>) storeLocationOutMessage
				.getStoreLocationDTOList();
		return list;
	}

	public Map<Integer, ItemDTO> itemsMap() {
		return new TreeMap<Integer, ItemDTO>();
	}

	private boolean checkStockForFinishedGood(List finishedGoodsDetailDTOList,
			List oldFinishedGoodsDetailDTOList) {

		boolean b = false;
		FinishedGoodsDetailDTO finishedGoodsDetailDTO = new FinishedGoodsDetailDTO();
		FinishedGoodsDetailDTO oldFinishedGoodsDetailDTO = new FinishedGoodsDetailDTO();

		// To get Item Name
		try {
			for (int i = 0; i < finishedGoodsDetailDTOList.size(); i++) {
				Double stock = 0.0;
				finishedGoodsDetailDTO = (FinishedGoodsDetailDTO) finishedGoodsDetailDTOList
						.get(i);
				// System.out.println("item Id"+billDetailDTO.getItemId());
				/*
				 * ledgerDTO.setItemId(billDetailDTO.getItemId());
				 * stockInputmessage.setStockLedgerDTO(ledgerDTO);
				 * stockOutputmessage
				 * =stockLedgerService.countStockByItemId(stockInputmessage);
				 */
				ItemDTO itemDTO = new ItemDTO();

				itemDTO.setItemId(finishedGoodsDetailDTO.getItemId());
				ItemInputMessage inputMessage = new ItemInputMessage();
				inputMessage.setItemDTO(itemDTO);
				ItemOutMessage itemOutMessage = itemService
						.findItemById(inputMessage);

				// ledgerDTO=stockOutputmessage.getStockLedgerDTO();
				List<ItemDTO> resultList = itemOutMessage.getItemDTOList();
				// System.out.println("stock"+stock+"- Qty"+billDetailDTO.getPrimaryUnit());
				if (resultList != null && resultList.size() > 0) {
					itemDTO = resultList.get(0);
					logger.info("itemDTO" + itemDTO + "-Sotck"
							+ itemDTO.getStockTotal());
					logger.info("finishedGoodsDetailEntity"
							+ finishedGoodsDetailDTO);
					logger.info("index in Old list"
							+ oldFinishedGoodsDetailDTOList
									.indexOf(finishedGoodsDetailDTO));
					if (oldFinishedGoodsDetailDTOList
							.contains(finishedGoodsDetailDTO)) {
						oldFinishedGoodsDetailDTO = (FinishedGoodsDetailDTO) oldFinishedGoodsDetailDTOList
								.get(oldFinishedGoodsDetailDTOList
										.indexOf(finishedGoodsDetailDTO));

						logger.info("oldFinishedGoodsDetailEntity"
								+ oldFinishedGoodsDetailDTO);
					}
					// +oldFinishedGoodsDetailDTO.getQuantity() //Not in use for
					// Finished Good Entries
					if ((itemDTO.getStockTotal()) < (oldFinishedGoodsDetailDTO
							.getQuantity() - finishedGoodsDetailDTO
							.getQuantity())) {
						b = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception in Finished Good", e);
			e.printStackTrace();
		}

		return b;
	}

}
