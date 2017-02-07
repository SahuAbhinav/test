package com.advanz.erp.client.http.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.IssueReturnMasterForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.IssueDetailMasterDTO;
import com.advanz.erp.masters.model.IssueMasterDTO;
import com.advanz.erp.masters.model.IssueReturnDetailDTO;
import com.advanz.erp.masters.model.IssueReturnMasterDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.StoreLocationDTO;
import com.advanz.erp.masters.model.TransactionTypeDTO;
import com.advanz.erp.masters.model.criteria.IssueReturnSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.model.msg.IssueReturnMasterInputMessage;
import com.advanz.erp.masters.model.msg.IssueReturnMasterOutputMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.model.msg.StoreLocationOutMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeInputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeOutputMessage;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.service.business.IIssueMasterService;
import com.advanz.erp.masters.service.business.IIssueReturnService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IStoreLocationService;
import com.advanz.erp.masters.service.business.ITransactionTypeService;

@Controller
@SessionAttributes({ "issueReturnMasterForm" })
public class IssueReturnController extends BaseController {
	public static final Logger logger = LoggerFactory
			.getLogger(IssueReturnController.class);
	@Autowired
	DataSource dataSource;
	@Autowired
	IIssueReturnService iIssueReturnService;
	@Autowired
	IIssueMasterService iIssueMasterService;
	@Autowired
	ITransactionTypeService transactionTypeService;

	@Autowired
	IStoreLocationService storeLocationService;
	@Autowired
	IBranchService branchService;

	@Autowired
	IMastersService mastersService;

	@Autowired
	IItemService itemService;

	@ModelAttribute("issueReturnMasterForm")
	public IssueReturnMasterForm populateForm() {
		return new IssueReturnMasterForm(); // populates form for the first time
											// if its null
	}

	@RequestMapping(value = "/show_issue_return_list", method = RequestMethod.GET)
	public ModelAndView showIssueReturn(
			@ModelAttribute("issueReturnSearchCriteriaDTO") IssueReturnSearchCriteriaDTO issueReturnSearchCriteriaDTO,
			@ModelAttribute("issueReturnMasterForm") IssueReturnMasterForm issueReturnMasterForm,
			@RequestParam(value = "menuId", required = false) String menuId,
			Model model,
			@RequestParam(value = "next", required = false) Integer next,
			HttpSession session) {
		if (menuId != null) {
			session.setAttribute("menuId", menuId);
		}
		ModelAndView mav = new ModelAndView("issue_return_list");
		// IssueReturnMasterOutputMessage
		// issueReturnMasterOutputMessage=iIssueReturnService.findAllIssueReturn();
		// List<IssueReturnMasterDTO>
		// issueReturnMasterDTOList=issueReturnMasterOutputMessage.getIssueReturnMasterDTOList();
		IssueReturnMasterOutputMessage issueReturnMasterOutputMessage = new IssueReturnMasterOutputMessage();

		IssueReturnMasterInputMessage returnMasterInputMessage = new IssueReturnMasterInputMessage();
		if (next == null || next < 0) {
			next = 0;
			returnMasterInputMessage.setNext(next);
			issueReturnMasterOutputMessage = iIssueReturnService
					.findIssueReturnPagination(returnMasterInputMessage);
		} else {
			returnMasterInputMessage.setNext(next);
			issueReturnMasterOutputMessage = iIssueReturnService
					.findIssueReturnPagination(returnMasterInputMessage);
		}

		if (issueReturnMasterForm == null) {
			issueReturnMasterForm = new IssueReturnMasterForm();
		}
		issueReturnMasterForm.setNext(next);
		issueReturnMasterForm.setPrevious(next);
		List<IssueReturnMasterDTO> issueReturnMasterDTOList = issueReturnMasterOutputMessage
				.getIssueReturnMasterDTOList();
		mav.addObject("issueReturnMasterForm", issueReturnMasterForm);
		mav.addObject("menuId", menuId);
		mav.addObject("issueReturnMasterDTOList", issueReturnMasterDTOList);
		return mav;
	}

	@RequestMapping(value = "/search_issue_return", method = RequestMethod.POST)
	public ModelAndView searchIssueReturn(
			@ModelAttribute("issueReturnSearchCriteriaDTO") IssueReturnSearchCriteriaDTO issueReturnSearchCriteriaDTO,
			@ModelAttribute("issueReturnMasterForm") IssueReturnMasterForm issueReturnMasterForm,
			Model model) {
		ModelAndView mav = new ModelAndView("issue_return_list");

		IssueReturnMasterInputMessage inputMessage = new IssueReturnMasterInputMessage();
		IssueReturnMasterDTO issueReturnMasterDTO = new IssueReturnMasterDTO();
		IssueReturnMasterOutputMessage issueReturnMasterOutputMessage = new IssueReturnMasterOutputMessage();
		issueReturnMasterDTO.setIssueReturnNumber(issueReturnSearchCriteriaDTO
				.getReturnNumber());
		issueReturnMasterDTO
				.setToDate(issueReturnSearchCriteriaDTO.getToDate());
		issueReturnMasterDTO.setFromDate(issueReturnSearchCriteriaDTO
				.getFromDate());
		MastersDTO dto = new MastersDTO();
		dto.setDeptId(issueReturnSearchCriteriaDTO.getDepartmentId());
		issueReturnMasterDTO.setMastersDTO(dto);

		inputMessage.setIssueReturnMasterDTO(issueReturnMasterDTO);
		issueReturnMasterOutputMessage = iIssueReturnService
				.search(inputMessage);
		List<IssueReturnMasterDTO> issueReturnMasterDTOList = issueReturnMasterOutputMessage
				.getIssueReturnMasterDTOList();
		String opr = "";
		if (issueReturnMasterDTOList.size() < 0) {
			opr = "Blk";
		}
		if (issueReturnMasterForm == null) {
			issueReturnMasterForm = new IssueReturnMasterForm();
		}
		model.addAttribute("opr", opr);
		mav.addObject("issueReturnMasterForm", issueReturnMasterForm);
		mav.addObject("issueReturnMasterDTOList", issueReturnMasterDTOList);
		return mav;
	}

	@RequestMapping(value = "/show_new_issue_return", method = RequestMethod.GET)
	public ModelAndView addNewIssueReturn(
			@ModelAttribute("issueReturnMasterForm") IssueReturnMasterForm issueReturnMasterForm) {
		if (issueReturnMasterForm == null) {
			issueReturnMasterForm = new IssueReturnMasterForm();
		}
		IssueReturnMasterDTO issueReturnMasterDTO = new IssueReturnMasterDTO();

		String series = getIssueTransactionSeries();

		issueReturnMasterDTO.setTransactionSeries(series);
		issueReturnMasterDTO.setFinYear(getFinYear());

		IssueReturnMasterInputMessage issueReturnMasterInputMessage = new IssueReturnMasterInputMessage();
		IssueReturnMasterOutputMessage issueReturnMasterOutputMessage = new IssueReturnMasterOutputMessage();

		issueReturnMasterInputMessage
				.setIssueReturnMasterDTO(issueReturnMasterDTO);
		issueReturnMasterOutputMessage = iIssueReturnService
				.newIssueReturnSeriesNo(issueReturnMasterInputMessage);

		Timestamp timestamp = issueReturnMasterOutputMessage
				.getIssueReturnSeriesDate();
		//System.out.println("Last Finished good time"+ new Date(timestamp.getTime()));
		SimpleDateFormat ft = new SimpleDateFormat("yyyy,MM,dd");

		Integer issueReturnId = issueReturnMasterOutputMessage
				.getIssueReturnSeriesNo();

		issueReturnMasterDTO.setIssueReturnId(issueReturnId);

		issueReturnMasterDTO.setOrderSeries(issueReturnMasterDTO
				.getTransactionSeries() + "/" + getFinYear());
		issueReturnMasterDTO.setIssueReturnNumber(issueReturnMasterDTO
				.getTransactionSeries()
				+ "/"
				+ getFinYear()
				+ "/"
				+ issueReturnMasterDTO.getIssueReturnId());
		issueReturnMasterDTO.setIssueReturnDate(new Date());

		issueReturnMasterForm.setIssueReturnMasterDTO(issueReturnMasterDTO);
		issueReturnMasterForm.setLastIssueReturnDate(ft.format(new Date(
				timestamp.getTime())));

		ModelAndView mav = new ModelAndView("issue_return_entry");
		mav.addObject("issueReturnMasterForm", issueReturnMasterForm);
		return mav;
	}

	@RequestMapping(value = "/save_issue_return", method = RequestMethod.POST)
	public String createIssueReturn(
			@ModelAttribute("issueReturnMasterForm") IssueReturnMasterForm issueReturnMasterForm,
			Model model) {

		String opr = "";
		IssueReturnMasterInputMessage issueReturnMasterInputMessage = new IssueReturnMasterInputMessage();
		IssueReturnMasterDTO returnMasterDTO = issueReturnMasterForm
				.getIssueReturnMasterDTO();
		issueReturnMasterInputMessage.setIssueReturnMasterDTO(returnMasterDTO);
		IssueReturnMasterOutputMessage issueReturnMasterOutputMessage = new IssueReturnMasterOutputMessage();

		if (issueReturnMasterForm.getIssueReturnMasterDTO()
				.getIssueReturnAutoId() != null) {
			returnMasterDTO.setModifiedUserId(getCreatedUserId());
			issueReturnMasterOutputMessage = iIssueReturnService
					.updateIssueReturn(issueReturnMasterInputMessage);
			opr = "Up";
		} else {
			returnMasterDTO.setCreatedUserId(getCreatedUserId());
			issueReturnMasterOutputMessage = iIssueReturnService
					.createIssueReturn(issueReturnMasterInputMessage);

			opr = "Ad";
		}

		if (issueReturnMasterOutputMessage.getErrorListDTO() != null) {
			ErrorListDTO errorListDTO = issueReturnMasterOutputMessage
					.getErrorListDTO();
			ErrorDTO errorDTO = errorListDTO.getErrorList().get(0);
			model.addAttribute("errorList", errorDTO);
			return "issue_return_entry";
		}
		model.addAttribute("opr", opr);
		return "redirect:/show_issue_return_list";
	}

	@RequestMapping(value = "/get_issue_return", method = RequestMethod.GET)
	public ModelAndView getIssueReturn(
			@ModelAttribute("issueReturnMasterForm") IssueReturnMasterForm issueReturnMasterForm,
			@RequestParam("issueId") Integer issueId,
			@RequestParam("opration") String opr, ModelMap model) {
		ModelAndView mav = new ModelAndView("issue_return_entry");
		IssueReturnMasterInputMessage issueReturnMasterInputMessage = new IssueReturnMasterInputMessage();
		IssueReturnMasterDTO issueReturnMasterDTO = new IssueReturnMasterDTO();
		issueReturnMasterDTO.setIssueReturnAutoId(issueId);
		issueReturnMasterInputMessage
				.setIssueReturnMasterDTO(issueReturnMasterDTO);

		IssueReturnMasterOutputMessage issueReturnMasterOutputMessage = iIssueReturnService
				.findById(issueReturnMasterInputMessage);
		IssueReturnMasterDTO issueReturnMasterDTO2 = issueReturnMasterOutputMessage
				.getIssueReturnMasterDTOList().get(0);
		// To show issueDate and issueQuantity on form
		List<IssueReturnDetailDTO> detailList = issueReturnMasterDTO2
				.getIssueReturnDetailDTOList();
		for (int i = 0; i < detailList.size(); i++) {
			IssueReturnDetailDTO detailDTO = detailList.get(i);

			List<IssueDetailMasterDTO> issueDetailList = iIssueMasterService
					.findByIssueNoAndItemId(detailDTO.getIssueNumber(),
							detailDTO.getItemId());
			if (issueDetailList != null && issueDetailList.size() > 0) {
				for (int j = 0; j < issueDetailList.size(); j++) {
					Date issueDate = iIssueMasterService
							.findIssueDateByIssueNo(detailDTO.getIssueNumber());
					IssueDetailMasterDTO detailMasterDTO = issueDetailList
							.get(j);
					detailDTO.setIssueQuantity(detailMasterDTO
							.getIssueQuantity());
					detailDTO.setIssueDate(issueDate);
				}
			}

		}

		// End

		issueReturnMasterDTO2.setOrderSeries(issueReturnMasterDTO2
				.getTransactionSeries()
				+ "/"
				+ issueReturnMasterDTO2.getFinYear());
		issueReturnMasterForm.setIssueReturnMasterDTO(issueReturnMasterDTO2);
		mav.addObject("issueReturnMasterForm", issueReturnMasterForm);
		model.put("opr", opr);
		return mav;
	}

	@RequestMapping(value = "/remove_issue_return", method = RequestMethod.GET)
	public String removeIssueReturn(
			@ModelAttribute("issueReturnMasterForm") IssueReturnMasterForm issueReturnMasterForm,
			@RequestParam("issueId") Integer issueId,
			@RequestParam("issueReturnNumber") String issueReturnNumber,
			Model model) {
		IssueReturnMasterInputMessage issueReturnMasterInputMessage = new IssueReturnMasterInputMessage();
		IssueReturnMasterDTO issueReturnMasterDTO = new IssueReturnMasterDTO();
		if (issueId != null) {
			issueReturnMasterDTO.setIssueReturnAutoId(issueId);
			issueReturnMasterDTO.setIssueReturnNumber(issueReturnNumber);

			issueReturnMasterDTO.setModifiedUserId(getCreatedUserId());
			issueReturnMasterInputMessage
					.setIssueReturnMasterDTO(issueReturnMasterDTO);
			iIssueReturnService
					.deleteIssueReturn(issueReturnMasterInputMessage);
		}
		String opr = "Dl";
		model.addAttribute("opr", opr);
		return "redirect:/show_issue_return_list";
	}

	@RequestMapping(value = "/show_item_list_issue_return")
	public ModelAndView showItemSelectionForm(
			@ModelAttribute("issueReturnMasterForm") IssueReturnMasterForm issueReturnMasterForm,
			@RequestParam(value = "itemID", required = false) Integer itemId,
			@RequestParam(value = "operation", required = false) String operation,
			@RequestParam(value = "issueNumber", required = false) String issueNumber,
			@RequestParam(value = "issueQuantity", required = false) String issueQuantity,
			@RequestParam(value = "issueDate", required = false) String issueDate,
			@RequestParam(value = "btn", required = false) String btn,
			ModelMap model,
			@RequestParam(value = "next", required = false) Integer next) {

		ModelAndView mav = new ModelAndView("item_list_for_issue_return");
		if ("Cancel".equals(operation)) {
			mav = new ModelAndView("issue_return_entry");
			mav.addObject("issueReturnMasterForm", issueReturnMasterForm);
			return mav;
		}
		if (btn != null) {
			return addItemForIssueReturn(issueReturnMasterForm, model, itemId,
					issueNumber, issueQuantity, issueDate);
		}

		IssueReturnMasterDTO dto = issueReturnMasterForm
				.getIssueReturnMasterDTO();
		try {
			if ("1".equalsIgnoreCase(issueReturnMasterForm.getOperationFlage())) {
				dto.setOperationFlage("All");
			}
			if ("0".equalsIgnoreCase(issueReturnMasterForm.getOperationFlage())) {
				dto.setOperationFlage("Returnable");
			}
		} catch (Exception ex) {
		}
		List<IssueMasterDTO> issueList = null;
		// iIssueReturnService.getIssueList();
		if ("search".equalsIgnoreCase(operation)) {
			if ("1".equalsIgnoreCase(issueReturnMasterForm.getOperationFlage())) {
				dto.setOperationFlage("All");
			}
			if ("0".equalsIgnoreCase(issueReturnMasterForm.getOperationFlage())) {
				dto.setOperationFlage("Returnable");
			}

			if (issueReturnMasterForm.getItemName() != null) {
				dto.setItemName(issueReturnMasterForm.getItemName());
			}
			if (issueReturnMasterForm.getItemCode() != null) {
				dto.setItemCode(issueReturnMasterForm.getItemCode());
			}
			if (issueReturnMasterForm.getIssueNo() != null) {
				dto.setIssueNumber(issueReturnMasterForm.getIssueNo());
			}

			if (issueReturnMasterForm.getLoanType() != null) {
				dto.setLoanType(issueReturnMasterForm.getLoanType());
			}
			if (issueReturnMasterForm.getRaisedBy() != null) {
				dto.setRisedBy(issueReturnMasterForm.getRaisedBy());
			}

			issueList = iIssueReturnService.searchIssueList(dto);

			// itemInputMessage.setItemDTO(itemDTO);

			// itemOutMessage = itemService.findItem(itemInputMessage);
		} else {

			if (next == null || next < 0) {
				next = 0;
				dto.setNext(next);
				issueList = iIssueReturnService.getIssueList(dto);
				next = 15;
			} else {
				dto.setNext(next);

				issueList = iIssueReturnService.getIssueList(dto);
				next = next + 15;
			}
		}

		if (issueReturnMasterForm.getIssueReturnMasterDTO() != null) {
			List<IssueReturnDetailDTO> issueReturnDetailDTOList = issueReturnMasterForm
					.getIssueReturnMasterDTO().getIssueReturnDetailDTOList();
			if (issueReturnDetailDTOList != null) {
				for (IssueReturnDetailDTO issueReturnDetailDTO : issueReturnDetailDTOList) {
					Iterator<IssueMasterDTO> itr = issueList.iterator();
					while (itr.hasNext()) {
						IssueMasterDTO temp = itr.next();
						if (!issueReturnDetailDTO.getDeletedFlag()
								&& temp.getItemId().equals(
										issueReturnDetailDTO.getItemId())) {
							itr.remove();
						}
					}
				}
			}
		}
		mav.addObject("itemList", issueList);

		try {
			dto.setNext(next);
			dto.setPrevious(next - 30);
		} catch (Exception e) {
		}
		issueReturnMasterForm.setIssueReturnMasterDTO(dto);
		mav.addObject("issueReturnMasterForm", issueReturnMasterForm);
		return mav;
	}

	@RequestMapping("/add_Item_for_Issues_return")
	public ModelAndView addItemForIssueReturn(
			@ModelAttribute("issueReturnMasterForm") IssueReturnMasterForm issueReturnMasterForm,
			ModelMap model, @RequestParam("itemID") Integer itemId,
			@RequestParam("issueNumber") String issueNumber,
			@RequestParam("issueQuantity") String issueQuantity,
			@RequestParam("issueDate") String issueDate) {
		IssueReturnMasterDTO issueReturnMasterDTO = issueReturnMasterForm
				.getIssueReturnMasterDTO();
		if (itemId != null) {
			if (!isDuplicateItem(issueReturnMasterDTO, itemId)) {
				ItemInputMessage itemInputMessage = new ItemInputMessage();
				ItemDTO itemDTO = new ItemDTO();
				itemDTO.setItemId(itemId);
				itemInputMessage.setItemDTO(itemDTO);
				List<ItemDTO> itemDtoList = itemService.findItemById(
						itemInputMessage).getItemDTOList();
				if (itemDtoList != null && itemDtoList.size() > 0) {
					itemDTO = itemDtoList.get(0);
					logger.info("Add Item : " + itemId);
					IssueReturnDetailDTO issueReturnDetailDTO = new IssueReturnDetailDTO();
					BeanUtils.copyProperties(itemDTO, issueReturnDetailDTO);
					issueReturnDetailDTO
							.setTransactionSeries(issueReturnMasterDTO
									.getTransactionSeries());
					issueReturnDetailDTO.setItemName(itemDTO.getInvoiceName());
					/* issueReturnDetailDTO.setRequiredQuantity(1.0); */
					issueReturnDetailDTO.setMeasurmentUnitId(itemDTO
							.getMasterUnit().getMastersId());
					issueReturnDetailDTO.setMeasurementUnitName(itemDTO
							.getMasterUnit().getName());

					issueReturnDetailDTO
							.setIssueReturnNumber(issueReturnMasterDTO
									.getIssueReturnNumber());
					issueReturnDetailDTO.setIssueNumber(issueNumber);
					Double issueQuantity1 = Double.parseDouble(issueQuantity);
					issueReturnDetailDTO.setIssueQuantity(issueQuantity1);
					SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
					Date issueDate1 = null;
					try {
						issueDate1 = sd.parse(issueDate);
					} catch (ParseException e) {

					}
					issueReturnDetailDTO.setIssueDate(issueDate1);
					if (issueReturnMasterDTO.getIssueReturnDetailDTOList() == null) {
						issueReturnMasterForm.getIssueReturnMasterDTO()
								.setIssueReturnDetailDTOList(
										new ArrayList<IssueReturnDetailDTO>());
					}
					issueReturnMasterDTO.getIssueReturnDetailDTOList().add(
							issueReturnDetailDTO);

				}
			}

		}
		ModelAndView mav = new ModelAndView("issue_return_entry");
		model.put("deptTypeList", getDeptTypeList());
		mav.addObject("itemFor", "M");
		mav.addObject("issueReturnMasterForm", issueReturnMasterForm);
		return mav;
	}

	private boolean isDuplicateItem(IssueReturnMasterDTO issueReturnMasterDTO,
			Integer itemId) {

		if (issueReturnMasterDTO != null
				&& issueReturnMasterDTO.getIssueReturnDetailDTOList() != null) {
			List<IssueReturnDetailDTO> list = issueReturnMasterDTO
					.getIssueReturnDetailDTOList();

			logger.info("list-----" + list);
			for (IssueReturnDetailDTO e : list) {

				if (itemId.equals(e.getItemId()))
					return true;
			}
		}

		return false;
	}

	@RequestMapping("/remove_item_from_issues_return")
	public ModelAndView removeItemIssurReturnFrom(
			@ModelAttribute("issueReturnMasterForm") IssueReturnMasterForm issueReturnMasterForm,
			@RequestParam("index") Integer index,
			@ModelAttribute("opr") String opr, ModelMap model) {

		List<IssueReturnDetailDTO> detailDTOList = issueReturnMasterForm
				.getIssueReturnMasterDTO().getIssueReturnDetailDTOList();

		if (detailDTOList != null) {
			IssueReturnDetailDTO dto = detailDTOList.get(index);
			detailDTOList.remove(dto);
		}
		IssueReturnMasterDTO issueReturnMasterDTO = new IssueReturnMasterDTO();
		issueReturnMasterDTO.setIssueReturnDetailDTOList(detailDTOList);
		ArrayList<IssueReturnMasterDTO> issueMasterList = new ArrayList<IssueReturnMasterDTO>();
		issueMasterList.add(issueReturnMasterDTO);
		issueReturnMasterForm.setIssueReturnMasterDTOList(issueMasterList);
		model.put("issueReturnMasterForm", issueReturnMasterForm);
		// calcSalesOrder(salesOrderMasterForm.getSalesOrderMasterDTO());
		ModelAndView mav = new ModelAndView("issue_return_entry");
		return mav;
	}

	@RequestMapping(value = "/backto_issue_return", method = RequestMethod.GET)
	public ModelAndView backToFinishGood(
			@ModelAttribute("issueReturnMasterForm") IssueReturnMasterForm issueReturnMasterForm) {
		// calcSalesOrder(salesOrderMasterForm.getSalesOrderMasterDTO());
		ModelAndView mav = new ModelAndView("issue_return_entry");
		return mav;
	}

	private String getIssueTransactionSeries() {
		TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
		transactionTypeDTO.setSno(15);
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

	@ModelAttribute("storeLocationList")
	public List<StoreLocationDTO> storeLcationList() {
		StoreLocationOutMessage storeLocationOutMessage = storeLocationService
				.findAllStoreLocations();
		ArrayList<StoreLocationDTO> list = (ArrayList<StoreLocationDTO>) storeLocationOutMessage
				.getStoreLocationDTOList();
		return list;
	}

	@ModelAttribute("branchList")
	public List<BranchDTO> branchList() {
		BranchOutMessage branchOutMessage = branchService.findAllBranches();
		ArrayList<BranchDTO> list = (ArrayList<BranchDTO>) branchOutMessage
				.getBranchDTOList();
		return list;
	}

	@ModelAttribute("deptTypeList")
	public List<MastersDTO> getDeptTypeList() {
		MastersOutputMessage outMsg = null;
		MastersInputMessage inMsg = new MastersInputMessage();
		inMsg.setFormId(8);
		outMsg = mastersService.findFormById(inMsg);
		List<MastersDTO> deptTypeList = outMsg.getMastersDTOList();
		return deptTypeList;
	}

	@RequestMapping(value = "/issue_return_print_report/pdf", method = RequestMethod.GET)
	public ModelAndView doSalesReportPDF(
			@RequestParam("IssueReturnNoPrompt") String IssueReturnNoPrompt,
			ModelAndView modelAndView, HttpServletResponse response,
			HttpServletRequest request) {
		logger.debug("Received request to download PDF report");
		response.setHeader("filename", "grn_print_report.pdf");
		response.setContentType("application/pdf");
		// response.setHeader("Content-Disposition",
		// "attachment:filename=_blank‌​");
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("IssueReturnNoPrompt", IssueReturnNoPrompt);

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
		/*
		 * try { is = new FileInputStream(realPath); } catch
		 * (FileNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("pdfIssueReturnPrintView", parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	}
}
