package com.advanz.erp.client.http.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.advanz.DataUtility;
import com.advanz.erp.client.http.controller.form.IssueMasterForm;
import com.advanz.erp.client.http.controller.form.ItemForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.CompanyDTO;
import com.advanz.erp.masters.model.IssueDetailMasterDTO;
import com.advanz.erp.masters.model.IssueMasterDTO;
import com.advanz.erp.masters.model.IssueTypeMasterDTO;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.ProformaDetailDTO;
import com.advanz.erp.masters.model.StockLedgerDTO;
import com.advanz.erp.masters.model.StoreLocationDTO;
import com.advanz.erp.masters.model.TransactionTypeDTO;
import com.advanz.erp.masters.model.criteria.IssueMasterSearchCriteriaDTO;
import com.advanz.erp.masters.model.criteria.ItemSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.model.msg.CompanyOutMessage;
import com.advanz.erp.masters.model.msg.IssueInputMessage;
import com.advanz.erp.masters.model.msg.IssueOutputMessage;
import com.advanz.erp.masters.model.msg.IssueTypeMasterInputMessage;
import com.advanz.erp.masters.model.msg.IssueTypeMasterOutputMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.model.msg.StockLedgerInputMessage;
import com.advanz.erp.masters.model.msg.StockLedgerOutMessage;
import com.advanz.erp.masters.model.msg.StoreLocationOutMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeInputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeOutputMessage;
import com.advanz.erp.masters.service.business.IBatchService;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.service.business.ICompanyService;
import com.advanz.erp.masters.service.business.IGrnMasterService;
import com.advanz.erp.masters.service.business.IIssueMasterService;
import com.advanz.erp.masters.service.business.IIssueReturnService;
import com.advanz.erp.masters.service.business.IIssueTypeMasterService;
import com.advanz.erp.masters.service.business.IItemGroupFlagService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IStockLedgerService;
import com.advanz.erp.masters.service.business.IStoreLocationService;
import com.advanz.erp.masters.service.business.ITransactionTypeService;

@Controller
@SessionAttributes({ "issueMasterForm", "branchList", "itemsMap", "opr" })
public class MaterialIssueController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(MaterialIssueController.class);

	@Autowired
	DataSource dataSource;
	
	@Autowired
	public IItemService itemService;

	@Autowired
	public IBranchService branchService;
	@Autowired
	public ITransactionTypeService transactionTypeService;
	@Autowired
	public IIssueMasterService issueMasterService;

	@Autowired
	public IBatchService batchService;

	@Autowired
	public IStoreLocationService storeLocationService;

	@Autowired
	public IItemGroupFlagService itemGroupService;

	@Autowired
	public IMastersService mastersService;

	@Autowired
	public IIssueTypeMasterService issueTypeMasterService;

	@Autowired
	public IIssueReturnService issueReturnService;
	@Autowired
	public IGrnMasterService grnMasterService;
	@Autowired
	public IStockLedgerService stockLedgerService;
	
	@Autowired
	ICompanyService companyService; 
	
	@RequestMapping(value = "/get_issue_list")
	public ModelAndView searchIssue(
	@ModelAttribute("searchCriteria") IssueMasterSearchCriteriaDTO searchCriteria,
	@ModelAttribute("issueMasterForms") IssueMasterForm issueMasterForm,
	Model model,
	@RequestParam(value = "menuId", required = false) String menuId,
	HttpSession session,@RequestParam(value="next",required=false) Integer next) {
	if (menuId != null) {
	session.setAttribute("menuId", menuId);
	}

	List<IssueMasterDTO> list = new ArrayList<IssueMasterDTO>();
	IssueMasterDTO issueDTO=new IssueMasterDTO();
	IssueOutputMessage issueOutputMessage = null;
	IssueInputMessage issueInputMessage = new IssueInputMessage();

	
	try{
	if(searchCriteria.getFromDate()!=null || searchCriteria.getToDate()!=null||
	searchCriteria.getIssueType()!=null ||searchCriteria.getIssueNumber()!=null
	||searchCriteria.getItemName()!=null ||searchCriteria.getRaisedBy()!=null)
	{
	issueInputMessage.setSearchCriteria(searchCriteria);
	issueOutputMessage = issueMasterService.search(issueInputMessage);

	list = (ArrayList<IssueMasterDTO>) issueOutputMessage.getIssueMasterDTOList();

	String succ="Blk";
	if(list.equals(null) || list.size()==0)
	{
	model.addAttribute("succ", succ);
	}
	}
	else{
	if(next==null ||next<0)
	{
	next=0;
	issueDTO.setNext(next);
	issueInputMessage.setIssueMasterDTO(issueDTO);
	issueOutputMessage=issueMasterService.findIssueMasterPagination(issueInputMessage);
	}
	else
	{
	issueDTO.setNext(next);
	issueInputMessage.setIssueMasterDTO(issueDTO);
	issueOutputMessage=issueMasterService.findIssueMasterPagination(issueInputMessage);
	}

	issueDTO.setNext(next);
	issueDTO.setPrevious(next);
	list = (ArrayList<IssueMasterDTO>) issueOutputMessage.getIssueMasterDTOList();
	}
	}
	catch (Exception e) {
	}

	if (list != null && list.size() > 0) {
	for (int i = 0; i < list.size(); i++) {
	IssueMasterDTO issueMasterDTO = list.get(i);

	Integer issutypeId = issueMasterDTO.getIssueTypeId();
	Integer depertmentId = issueMasterDTO.getDepartmentId();

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
	issueMasterDTO.setDepartmentName(mastersDTO.getName());

	IssueTypeMasterInputMessage inputMessage = new IssueTypeMasterInputMessage();
	IssueTypeMasterDTO typeMasterDTO = new IssueTypeMasterDTO();
	typeMasterDTO.setSno(issutypeId);

	inputMessage.setIssueTypeMasterDTO(typeMasterDTO);
	IssueTypeMasterOutputMessage masterOutputMessage = issueTypeMasterService
	.findIssuesBySno(inputMessage);
	ArrayList<IssueTypeMasterDTO> arrayList = (ArrayList<IssueTypeMasterDTO>) masterOutputMessage
	.getIssueTypeMasterDTOList();
	if (arrayList != null && arrayList.size() > 0) {
	typeMasterDTO = arrayList.get(0);
	}
	issueMasterDTO.setIssueTypeName(typeMasterDTO.getIssueType());
	}

	}

	ModelAndView mav = new ModelAndView("issue-list");
	mav.addObject("issueList", list);
	issueMasterForm.setIssueMasterDTO(issueDTO);
	mav.addObject("issueMasterForm", issueMasterForm);

	if(session.getAttribute("issueNumber")!=null){
	mav.addObject("PrintView", "PrintView");
	}
	// mav.addObject("finishedGoodsSearchCriteria",searchCriteria);
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
	@ModelAttribute("masterHeadList")
	public List<MastersDTO> masterHeadList() {
		MastersInputMessage inputMessage=new MastersInputMessage();
		inputMessage.setFormId(18);
		 MastersOutputMessage mastersOutputMessage= mastersService.findFormById(inputMessage);
		 ArrayList<MastersDTO> list = (ArrayList<MastersDTO>) mastersOutputMessage.getMastersDTOList();
		return list;
	}
	@ModelAttribute("masterSectionList")
	public List<MastersDTO> masterSectionList() {
		MastersInputMessage inputMessage=new MastersInputMessage();
		inputMessage.setFormId(19);
		 MastersOutputMessage mastersOutputMessage= mastersService.findFormById(inputMessage);
		 ArrayList<MastersDTO> list = (ArrayList<MastersDTO>) mastersOutputMessage.getMastersDTOList();
		return list;
	}
	
	
	@RequestMapping(value = "/show_new_issue_form", method = RequestMethod.GET)
	public ModelAndView newIssueMaterial(ModelMap model) {
		IssueMasterForm issueMasterForm = new IssueMasterForm();
		IssueMasterDTO issueMasterDTO = new IssueMasterDTO();
		String series = getIssueTransactionSeries();

		issueMasterDTO.setTransactionSeries(series);
		issueMasterDTO.setFinYear(getFinYear());
		// finishedGoodsMasterDTO.setOrderSeries(series + "-" + getFinYear());
		// finishedGoodsMasterDTO.getOrderSeries();
		IssueInputMessage issueInputMessage = new IssueInputMessage();
		issueInputMessage.setIssueMasterDTO(issueMasterDTO);
		IssueOutputMessage issueOutputMessage = null;
		issueOutputMessage = issueMasterService
				.getNewIssueSeriesNo(issueInputMessage);
		Integer issueId = issueOutputMessage.getIssueSeriesNo();
		issueMasterDTO.setIssueId(issueId);
		
		Timestamp timestamp= issueOutputMessage.getIssueSeriesDate();
		//System.out.println("Last Finished good time"+new Date(timestamp.getTime()));
		SimpleDateFormat ft =new SimpleDateFormat ("yyyy,MM,dd");
		
		issueMasterDTO.setOrderSeries(issueMasterDTO.getTransactionSeries()
				+ "/" + getFinYear());
		issueMasterDTO.setIssueNumber(issueMasterDTO.getTransactionSeries()
				+ "/" + getFinYear() + "/" + issueMasterDTO.getIssueId());
		issueMasterDTO.setIssueDate(new Date());

		issueMasterForm.setIssueMasterDTO(issueMasterDTO);
		model.put("lastDate", ft.format(new Date(timestamp.getTime())));
		model.put("issueMasterForm", issueMasterForm);
		model.put("lastDate", ft.format(new Date(timestamp.getTime())));

		ModelAndView mav = new ModelAndView("issue-detail");
		// mav.addObject("partyList",partyList());
		return mav;
	}

	private String getIssueTransactionSeries() {
		TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
		transactionTypeDTO.setSno(12);
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

/*	@ModelAttribute("itemGroupList")
	public @ResponseBody
	List<ItemGroupFlagDTO> getItemGroup(
			@ModelAttribute("rows") ArrayList<ItemGroupFlagDTO> list) {

		ItemGroupFlagOutMessage itemGroupMessage = itemGroupService
				.findAllItemGroupFlag();
		list = (ArrayList<ItemGroupFlagDTO>) itemGroupMessage
				.getItemGroupFlagDTOList();
		return list;
	}*/

	@RequestMapping(value = "/new_issues", method = RequestMethod.GET)
	public ModelAndView newIssue(ModelMap model) {
		//System.out.println("New isse good time");
		IssueMasterForm issueMasterForm = new IssueMasterForm();
		IssueMasterDTO issueMasterDTO = new IssueMasterDTO();
		// FinishedGoodsMasterDTO finishedGoodsMasterDTO = new
		// FinishedGoodsMasterDTO();
		String series = getIssueTransactionSeries();
		issueMasterDTO.setFinYear(getFinYear());
		// finishedGoodsMasterDTO.setTransactionSeries(series + "-" +
		// getFinYear());
		issueMasterDTO.setTransactionSeries(series);
		// finishedGoodsMasterDTO.setOrderSeries(series + "-" + getFinYear());
		// finishedGoodsMasterDTO.getOrderSeries();
		IssueInputMessage issueInputMessage = new IssueInputMessage();
		// FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage = new
		// FinishedGoodsMasterInputMessage();
		issueInputMessage.setIssueMasterDTO(issueMasterDTO);
		IssueOutputMessage issueOutputMessage = issueMasterService
				.getNewIssueSeriesNo(issueInputMessage);
		// FinishedGoodsMasterOutputMessage finishedGoodsMasterOutputMessage =
		// finishedGoodsMasterService.getNewFinishedGoodsSeriesNo(finishedGoodsMasterInputMessage);
		
		Timestamp timestamp= issueOutputMessage.getIssueSeriesDate();
		//System.out.println("Last Finished good time"+new Date(timestamp.getTime()));
		SimpleDateFormat ft =new SimpleDateFormat ("yyyy,MM,dd");
		
		Integer orderID = issueOutputMessage.getIssueSeriesNo();
		issueMasterDTO.setIssueId(orderID);
		issueMasterDTO.setOrderSeries(issueMasterDTO.getTransactionSeries()
				+ "/" + issueMasterDTO.getFinYear());
		issueMasterDTO.setIssueNumber(issueMasterDTO.getTransactionSeries()
				+ "/" + issueMasterDTO.getFinYear() + "/"
				+ issueMasterDTO.getIssueId());
		issueMasterDTO.setIssueDate(new Date());

		issueMasterForm.setIssueMasterDTO(issueMasterDTO);
		
		issueMasterForm.setLastIssueDate(ft.format(new Date(timestamp.getTime())));
		
		model.put("issueMasterForm", issueMasterForm);
		model.put("branchList", branchList());
		model.put("deptTypeList", getDeptTypeList());
		model.put("issueTypeList", getIssueTypeList());
		model.put("opr", "N");
		model.put("itemsMap", itemsMap());
		ModelAndView mav = new ModelAndView("issue-detail");
		// mav.addObject("partyList",partyList());
		return mav;
	}

	// @ModelAttribute("branchList")
	public List<BranchDTO> branchList() {
		BranchOutMessage branchOutMessage = branchService.findAllBranches();
		ArrayList<BranchDTO> list = (ArrayList<BranchDTO>) branchOutMessage
				.getBranchDTOList();
		return list;
	}

	@RequestMapping(value = "/backto_issue", method = RequestMethod.GET)
	public ModelAndView backToFinishGood(
			@ModelAttribute("issueMasterForm") IssueMasterForm IssueMasterForm) {
		// calcSalesOrder(salesOrderMasterForm.getSalesOrderMasterDTO());
		ModelAndView mav = new ModelAndView("issue-detail");
		return mav;
	}

	@RequestMapping(value = "/show_item_list_issue")
	public @ResponseBody
	ModelAndView showItemSelectionForm(
			@ModelAttribute("issueMasterForm") IssueMasterForm issueMasterForm,
			@RequestParam(value = "itemID", required = false) Integer itemId,
			@RequestParam(value = "operation", required = false) String operation,
			@ModelAttribute("searchCriteria") ItemSearchCriteriaDTO searchCriteria,
			@RequestParam(value = "btn", required = false) String btn,
			ModelMap model,
			@RequestParam(value = "next", required = false) Integer next) {
		ModelAndView mav = new ModelAndView("item-list-for-issue");
		if (searchCriteria == null) {
			searchCriteria = new ItemSearchCriteriaDTO();
		}


		if ("Cancel".equals(operation)) {
			mav = new ModelAndView("issue-detail");
			mav.addObject("issueMasterForm", issueMasterForm);

			return mav;
		}

		if (btn != null) {
			return addItemInIssue(issueMasterForm, model, itemId);
		}

		ItemOutMessage itemOutMessage = null; // itemService.findAllItem();
		ItemInputMessage itemInputMessage = new ItemInputMessage();
		ItemDTO itemDTO = new ItemDTO();

		if ("search".equalsIgnoreCase(operation)) {

			if (searchCriteria != null) {
				itemDTO.setItemName(searchCriteria.getInvoiceName());
				// ItemGroupFlagDTO itemGroupFlagDTO=new ItemGroupFlagDTO();

				itemDTO.setItemCode(searchCriteria.getItemCode());
				itemDTO.setItemCategoryDTO(new ItemCategoryDTO());
				// itemGroupFlagDTO.setItemGroupFlagId(issueMasterForm.getIssueMasterDTO().getItemGroupDTO().getItemGroupFlagId());
				itemDTO.getItemCategoryDTO()
						.setItemGroupDTO(new ItemGroupDTO());
				/*itemDTO.getItemCategoryDTO()
						.getItemGroupDTO()
						.setItemGroupFlagId(
								issueMasterForm.getIssueMasterDTO()
										.getItemGroupDTO().getItemGroupFlagId());*/
				// itemDTO.setItemGroupName(issueMasterForm.getIssueMasterDTO().getItemGroupDTO().getItemGroupFlagName());
			}
			itemInputMessage.setItemDTO(itemDTO);
			itemOutMessage = itemService.searchMaterialIssuesItems(itemInputMessage);

		} else {

			// Pagination Start

			try {
				/*itemDTO.setItemCategoryDTO(new ItemCategoryDTO());
				itemDTO.getItemCategoryDTO()
						.setItemGroupDTO(new ItemGroupDTO());
				itemDTO.getItemCategoryDTO()
						.getItemGroupDTO()
						.setItemGroupFlagId(
								issueMasterForm.getIssueMasterDTO()
										.getItemGroupDTO().getItemGroupFlagId());
				itemDTO.setActiveStatus(1);*/
			} catch (Exception e) {

				e.printStackTrace();
			}

			itemOutMessage = new ItemOutMessage();
			// ItemInputMessage inputMessage=new ItemInputMessage();
			
			if (next == null || next < 0) {
				next = 0;
				itemDTO.setNext(next);
				itemInputMessage.setItemDTO(itemDTO);
				/*itemOutMessage = itemService
						.getListByItemGroupName(itemInputMessage);*/
				itemOutMessage = itemService
				.findItemForPagination(itemInputMessage);
				next = 13;
			} else {
				itemDTO.setNext(next);
				itemInputMessage.setItemDTO(itemDTO);
			/*	itemOutMessage = itemService
						.getListByItemGroupName(itemInputMessage);*/
				itemOutMessage = itemService
				.findItemForPagination(itemInputMessage);
				next = next + 13;
			}
			// Pagination end
		}

		// ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage
		// .getItemDTOList();
		ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage
				.getItemDTOList();

		if (issueMasterForm.getIssueMasterDTO() != null) {
			List<IssueDetailMasterDTO> issueDetailMasterDTOList = issueMasterForm
					.getIssueMasterDTO().getIssueDetailMasterDTOList();
			if (issueDetailMasterDTOList != null) {
				for (IssueDetailMasterDTO issueDetailMasterDTO : issueDetailMasterDTOList) {
					Iterator<ItemDTO> itr = list.iterator();
					while (itr.hasNext()) {
						ItemDTO temp = itr.next();
						if (!issueDetailMasterDTO.getDeletedFlag()
								&& temp.getItemId().equals(
										issueDetailMasterDTO.getItemId())) {
							itr.remove();
						}
					}
				}
			}
		}
		mav.addObject("searchCriteria", searchCriteria);
		mav.addObject("itemList", list);
		ItemForm itemForm = new ItemForm();
		try {
			itemDTO.setNext(next);
			itemDTO.setPrevious(next - 26);
		} catch (Exception e) {
		}
		itemForm.setItemDTO(itemDTO);
		mav.addObject("itemForm", itemForm);
		return mav;
	}

	@RequestMapping(value = "/get_item_by_idr")
	public @ResponseBody
	ItemDTO getItemById(@RequestParam("id") Integer itemId) {
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

	@RequestMapping("/addItemInIssues")
	public ModelAndView addItemInIssue(
			@ModelAttribute("issueMasterForm") IssueMasterForm issueMasterForm,
			ModelMap model, @RequestParam("itemID") Integer itemId) {
		IssueMasterDTO issueMasterDTO = issueMasterForm.getIssueMasterDTO();

		if (itemId != null) {
			if (!isDuplicateItem(issueMasterDTO, itemId)) {
				ItemInputMessage itemInputMessage = new ItemInputMessage();
				ItemDTO itemDTO = new ItemDTO();
				itemDTO.setItemId(itemId);
				itemInputMessage.setItemDTO(itemDTO);
				List<ItemDTO> itemDtoList = itemService.findItemById(itemInputMessage).getItemDTOList();
				if (itemDtoList != null && itemDtoList.size() > 0) {
					itemDTO = itemDtoList.get(0);
					Double grnRate= grnMasterService.getPuchaseRateByIssueItemId(itemId);
					IssueDetailMasterDTO issueDetailMasterDTO = new IssueDetailMasterDTO();
					BeanUtils.copyProperties(itemDTO, issueDetailMasterDTO);
					
					issueDetailMasterDTO.setGrnRate(grnRate);
					issueDetailMasterDTO.setTransactionSeries(issueMasterDTO
							.getTransactionSeries());
					issueDetailMasterDTO.setItemName(itemDTO.getInvoiceName());
					issueDetailMasterDTO.setMeasurementUnitName(itemDTO.getMasterUnit().getName());
					issueDetailMasterDTO.setRequiredQuantity(1.0);
					issueDetailMasterDTO.setMeasurementUnitId(itemDTO
							.getMasterUnit());
					issueDetailMasterDTO.setIssueNumber(issueMasterDTO
							.getIssueNumber());
					if (issueMasterDTO.getIssueDetailMasterDTOList() == null) {
						issueMasterForm.getIssueMasterDTO()
								.setIssueDetailMasterDTOList(
										new ArrayList<IssueDetailMasterDTO>());
					}
					issueMasterDTO.getIssueDetailMasterDTOList().add(
							issueDetailMasterDTO);

				}
			}

		}
		ModelAndView mav = new ModelAndView("issue-detail");
		model.put("deptTypeList", getDeptTypeList());
		model.put("issueTypeList", getIssueTypeList());
		mav.addObject("itemFor", "M");
		mav.addObject("issueMasterForm", issueMasterForm);
		return mav;
	}

	@RequestMapping("/removeItemFromIssues")
	public ModelAndView removeItemFrom(
			@ModelAttribute("issueMasterForm") IssueMasterForm issueMasterForm,
			@RequestParam("index") Integer index,
			@ModelAttribute("opr") String opr, ModelMap model) {

		List<IssueDetailMasterDTO> detailDTOList = issueMasterForm.getIssueMasterDTO().getIssueDetailMasterDTOList();
		if (detailDTOList != null ) {
			IssueDetailMasterDTO dto = detailDTOList.get(index);
			
		Boolean flag=false;
		try {
			flag = issueReturnService.getIssueRetunDeatilByIssueNumberAndItemId(dto.getIssueNumber(), dto.getItemId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			if(flag==true){
				model.addAttribute("msg", "notRemove");
			}else{
				detailDTOList.remove(dto);
			}
			/*if (StringUtils.hasText(opr) && "E".equals(opr)
					&& !dto.isTransientObject()) {
				dto.setDeletedFlag(true);
			} else
*/
				
		}

		//
		IssueMasterDTO masterDTO = new IssueMasterDTO();
		masterDTO.setIssueDetailMasterDTOList(detailDTOList);
		ArrayList<IssueMasterDTO> issueMasterList = new ArrayList<IssueMasterDTO>();
		issueMasterList.add(masterDTO);
		issueMasterForm.setIssueMasterList(issueMasterList);
		model.put("issueMasterForm", issueMasterForm);
		// calcSalesOrder(salesOrderMasterForm.getSalesOrderMasterDTO());
		ModelAndView mav = new ModelAndView("issue-detail");
		String succ = "Dl";
		model.addAttribute("succ", succ);
		model.put("deptTypeList", getDeptTypeList());
		model.put("issueTypeList", getIssueTypeList());
		return mav;
	}

	private boolean isDuplicateItem(IssueMasterDTO issueMasterDTO,
			Integer itemId) {

		if (issueMasterDTO != null
				&& issueMasterDTO.getIssueDetailMasterDTOList() != null) {
			List<IssueDetailMasterDTO> list = issueMasterDTO
					.getIssueDetailMasterDTOList();

			for (IssueDetailMasterDTO e : list) {

				if (itemId.equals(e.getItemId()))
					return true;
			}
		}

		return false;
	}

	@RequestMapping(value = "/saveMaterialIssue", method = RequestMethod.POST)
	public String saveFinishedGoods(
			@ModelAttribute("issueMasterForm") IssueMasterForm issueMasterForm,@RequestParam(value="opration",required=false) String opration,
			ModelMap model,HttpSession session) {
		
		IssueMasterDTO issueMasterDTO = issueMasterForm.getIssueMasterDTO();
		//issueMasterDTO.setItemGroupFlagId(issueMasterForm.getIssueMasterDTO().getItemGroupDTO().getItemGroupFlagId());
		// issueMasterDTO.setDepartmentId(issueMasterForm.getIssueMasterDTO().getMastersDTO().getId());
		issueMasterDTO.setDepartmentId(issueMasterForm.getIssueMasterDTO()
				.getDepartmentId());

		// issueMasterDTO.setIssueTypeId(issueMasterForm.getIssueMasterDTO().getIssueTypeMasterDTO().getSno());
		issueMasterDTO.setIssueTypeId(issueMasterForm.getIssueMasterDTO()
				.getIssueTypeId());

		int itemCount = 0;
		if (issueMasterForm.getIssueMasterDTO().getIssueDetailMasterDTOList() != null
				&& issueMasterForm.getIssueMasterDTO()
						.getIssueDetailMasterDTOList().size() > 0) {
			for (IssueDetailMasterDTO d : issueMasterForm.getIssueMasterDTO()
					.getIssueDetailMasterDTOList()) {
				if (!d.getDeletedFlag()) {
					itemCount++;
				}
			}
		}
		if (itemCount == 0) {
			ErrorDTO error = new ErrorDTO();
			error.setErrorMsg("Warning :You can not save Material Issues Without Items");
			model.addAttribute("errors", error);
			model.put("issueMasterForm", issueMasterForm);
			return "issue-detail";
		}

		IssueInputMessage issueInputMessage = new IssueInputMessage();
		// issueMasterForm.setIssueMasterDTO.(issueMasterForm.getIssueMasterDTO().setTransactionSeries("MI"));
		IssueMasterDTO issueMasterDTO1 = issueMasterForm.getIssueMasterDTO();
		Date date = issueMasterDTO1.getIssueDate();
		Date issueDate = DataUtility.getDate(date);
		issueMasterDTO1.setIssueDate(issueDate);
		if (issueMasterDTO1.getApproved() != null
				&& issueMasterDTO1.getApproved() > 0) {
			issueMasterDTO1.setApprovedDate(new Date());
		}
		
		CompanyOutMessage outCompanyOutMessage= companyService.findAllCompanies();
		List<CompanyDTO> cList=outCompanyOutMessage.getCompanyDTOList();
		CompanyDTO dto = null;
		if(cList!=null && cList.size()>0){
			dto=(CompanyDTO)cList.get(0);
			//System.out.println("dto.getIssueLockFlag()"+dto.getIssueLockFlag());
		}
		
		List billDtailList1 =issueMasterDTO1.getIssueDetailMasterDTOList();
		
		
		if(dto!=null && dto.getIssueLockFlag() && checkStockForMaterialIssue(billDtailList1)){
			//System.out.println("Product out of Stock");
			ErrorDTO error = new ErrorDTO();
			error.setErrorCode("OOS");
			error.setErrorMsg("You can not create Material Issue beacuse one of the issue item is out of stock");
			model.addAttribute("errors", error);
			//mav.setView(new RedirectView("show_proforma_list?operation=show"));
			return "issue-detail";
			
			
		}

		issueInputMessage.setIssueMasterDTO(issueMasterDTO1);
		IssueOutputMessage issueOutputMessage;
		String succ = "";
		if (issueMasterForm.getIssueMasterDTO().getIssueAutoId() == null || issueMasterForm.getIssueMasterDTO().getIssueAutoId().equals(0)) {
			issueMasterDTO1.setCreatedUserId(getCreatedUserId());
		    List<IssueDetailMasterDTO> issueDetailList=issueMasterDTO1.getIssueDetailMasterDTOList();
			for(int i=0;i<issueDetailList.size();i++){
				IssueDetailMasterDTO issueDetailDTO=issueDetailList.get(i);
				if(issueDetailDTO.getIssueQuantity()!=null && issueDetailDTO.getIssueType().equalsIgnoreCase("Returnable")){
					issueDetailDTO.setPendingQuantity(issueDetailDTO.getIssueQuantity());
				}}
			
	    issueOutputMessage = issueMasterService.createIssueMaster(issueInputMessage);
		succ = "Ad";
		}else {
			
			List<IssueDetailMasterDTO> issueDetailList=issueMasterDTO1.getIssueDetailMasterDTOList();
			for(int i=0;i<issueDetailList.size();i++){
				IssueDetailMasterDTO issueDetailDTO=issueDetailList.get(i);
				if(issueDetailDTO.getIssueQuantity()!=null && issueDetailDTO.getIssueType().equalsIgnoreCase("Returnable")){
				Double quantity=0.0;
				
				try {
					List<Double> d=issueReturnService.findReturnQuantity(issueMasterDTO1.getIssueNumber(), issueDetailDTO.getItemId());
					for(int j=0;j<issueDetailList.size();j++){
						Double quant=d.get(j);
						quantity +=quant;
				}
				} catch (Exception e) {
				  e.printStackTrace();
				}
				issueDetailDTO.setPendingQuantity(issueDetailDTO.getIssueQuantity()-quantity);
				}}
		issueMasterDTO1.setModifiedUserId(getCreatedUserId());
		issueOutputMessage = issueMasterService.updateIssueMaster(issueInputMessage);
		succ = "Up";
		}
		if (issueOutputMessage != null) {
		ErrorListDTO errorListDTO = issueOutputMessage.getErrorListDTO();
		if (errorListDTO != null && errorListDTO.hasErrors()) {
		ErrorDTO errorDTO = issueOutputMessage.getErrorListDTO().getErrorList().get(0);
				logger.info(" adding Error ");
				model.addAttribute("errors", errorDTO);

				IssueMasterDTO issueMasterDTO2 = issueMasterForm
						.getIssueMasterDTO();
				int i = issueMasterDTO2.getIssueId().intValue() + 1;
				issueMasterDTO2.setIssueId(issueMasterDTO2.getIssueId()
						.intValue() + 1);
				issueMasterDTO2.setOrderSeries(getIssueTransactionSeries()
						+ "/" + getFinYear());
				issueMasterDTO2.setIssueNumber(getIssueTransactionSeries()
						+ "/" + getFinYear() + "/" + i);
				ArrayList<IssueDetailMasterDTO> issueDetaiList = (ArrayList<IssueDetailMasterDTO>) issueMasterDTO2
						.getIssueDetailMasterDTOList();
				for (int j = 0; j < issueDetaiList.size(); j++) {
					IssueDetailMasterDTO detailMasterDTO = issueDetaiList
							.get(j);
					detailMasterDTO.setIssueNumber(getIssueTransactionSeries()
							+ "/" + getFinYear() + "/" + i);
				}
				issueMasterForm.setIssueMasterDTO(issueMasterDTO2);
				// model.addAttribute("issueMasterForm", issueMasterForm);
				return "issue-detail";
			}
		}
		IssueMasterDTO masterDTO = issueMasterForm.getIssueMasterDTO();
		masterDTO.setIssueDetailMasterDTOList(null);
		
		if(opration!=null && opration.equals("Print View")){
			//issueMasterForm.setIssueDate(masterDTO.getIssueDate());
			issueMasterForm.setPrintView(masterDTO.getIssueNumber());
			//model.addAttribute("issueDate", masterDTO.getIssueDate());
			model.addAttribute("issueNumber", masterDTO.getIssueNumber());
			session.setAttribute("issueNumber", masterDTO.getIssueNumber());
			session.setAttribute("issueDate", masterDTO.getIssueDate());
		}
		model.addAttribute("issueMasterForm", issueMasterForm);
		model.addAttribute("succ", succ);
		return "redirect:/get_issue_list";

	}

	@RequestMapping(value = "/get_issue", method = RequestMethod.GET)
	public ModelAndView getIssueData(
			@RequestParam("issueAutoId") Integer id,
			@RequestParam("opr") String opr,
			@RequestParam(value = "approved", required = false) Integer approved,
			ModelMap model) {
		IssueMasterForm issueMasterForm = new IssueMasterForm();

		IssueOutputMessage issueOutputMessage = null;
		if (id != null && !id.equals(0)) {

			IssueInputMessage issueInputMessage = new IssueInputMessage();
			IssueMasterDTO issueMasterDTO = new IssueMasterDTO();
			issueMasterDTO.setIssueAutoId(id);
			issueInputMessage.setIssueMasterDTO(issueMasterDTO);
			issueOutputMessage = issueMasterService.findIssueMasterById(issueInputMessage);
			ArrayList<IssueMasterDTO> list = (ArrayList<IssueMasterDTO>) issueOutputMessage.getIssueMasterDTOList();
			if (list != null && list.size() > 0) {
				issueMasterForm.setIssueMasterDTO(list.get(0));
			}
		}

		model.put("issueMasterForm", issueMasterForm);

		model.put("branchList", branchList());
		model.put("opr", opr);
		model.put("approved", approved);
		model.put("deptTypeList", getDeptTypeList());
		model.put("issueTypeList", getIssueTypeList());
		ModelAndView mav = new ModelAndView("issue-detail");
		// mav.addObject("partyList",partyList());
		return mav;

	}

	@RequestMapping(value = "/remove_issue", method = RequestMethod.GET)
	public String removeFinishedGoods(@RequestParam("id") Integer id,
			ModelMap model) {
		IssueOutputMessage issueOutputMessage = null;
		if (id != null && !id.equals(0)) {
			IssueInputMessage issueInputMessage = new IssueInputMessage();
			IssueMasterDTO issueMasterDTO = new IssueMasterDTO();
			issueMasterDTO.setIssueAutoId(id);
			issueMasterDTO.setModifiedUserId(getCreatedUserId());
			issueInputMessage.setIssueMasterDTO(issueMasterDTO);
			issueOutputMessage = issueMasterService
					.deleteIssueMaster(issueInputMessage);

		}
		ErrorListDTO errorListDTO=null;
		try {
			errorListDTO = issueOutputMessage.getErrorListDTO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String succ =null;
		if(errorListDTO!=null){
			succ = "NDl";
	}
		else{
			succ = "Dl";
		}
		model.put("deptTypeList", getDeptTypeList());
		model.put("issueTypeList", getIssueTypeList());
		model.addAttribute("succ", succ);
		return "redirect:/get_issue_list";

	}

	public List<MastersDTO> getDeptTypeList() {
		MastersOutputMessage outMsg = null;
		MastersInputMessage inMsg = new MastersInputMessage();
		inMsg.setFormId(8);
		outMsg = mastersService.findFormById(inMsg);
		List<MastersDTO> deptTypeList = outMsg.getMastersDTOList();
		return deptTypeList;
	}

	public List<IssueTypeMasterDTO> getIssueTypeList() {

		IssueTypeMasterOutputMessage outMsg = null;

		IssueTypeMasterInputMessage inMsg = new IssueTypeMasterInputMessage();

		outMsg = issueTypeMasterService.findAllIssueTypeMasters();
		List<IssueTypeMasterDTO> deptTypeList = outMsg
				.getIssueTypeMasterDTOList();
		return deptTypeList;
	}

	public Map<Integer, ItemDTO> itemsMap() {
		return new TreeMap<Integer, ItemDTO>();
	}

	
	  @RequestMapping(value = "/issue_print_report/pdf", method = RequestMethod.GET )
	    public  ModelAndView doSalesReportPDF(ModelAndView modelAndView, @RequestParam(value="issueNo",required=false) String issueNo,HttpServletResponse response, HttpServletRequest request,HttpSession session) 
			 {
			logger.debug("Received request to download PDF report");
			response.setHeader("filename","issue_print_report.pdf");
			String issueNoPrompt=null;
			if(issueNo!=null){
				issueNoPrompt=issueNo;
			}else{
		    issueNoPrompt=(String)session.getAttribute("issueNumber");
			}
		   
			response.setContentType("application/pdf");
	        //response.setHeader("Content-Disposition", "attachment:filename=_blank‌​");
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			parameterMap.put("datasource", dataSource);
			parameterMap.put("IssueNoPrompt", issueNoPrompt);
			
			String realPath  = request.getSession().getServletContext().getRealPath(System.getProperty("file.separator")+"WEB-INF"+System.getProperty("file.separator")+"images"+System.getProperty("file.separator")+"shree_logo.JPG");
			parameterMap.put("Image_Loc", realPath.toString());
			
		    session.removeAttribute("issueNumber");
		
			modelAndView = new ModelAndView("pdfIssuePrintView", parameterMap);
			// Return the View and the Model combined
			return modelAndView;
		   }
	  private boolean checkStockForMaterialIssue(List issueDetailList){
			
		  boolean b=false;
		  IssueDetailMasterDTO billDetailDTO= new IssueDetailMasterDTO();
			
			// To get Item Name
						for (int i = 0; i < issueDetailList.size(); i++) {
							Double stock=0.0;
							billDetailDTO = (IssueDetailMasterDTO) issueDetailList.get(i);
							//System.out.println("item Id"+billDetailDTO.getItemId());
							/*ledgerDTO.setItemId(billDetailDTO.getItemId());
							stockInputmessage.setStockLedgerDTO(ledgerDTO);
							stockOutputmessage=stockLedgerService.countStockByItemId(stockInputmessage);*/
							ItemDTO itemDTO = new ItemDTO();
							itemDTO.setItemId(billDetailDTO.getItemId());
							ItemInputMessage inputMessage = new ItemInputMessage();
							inputMessage.setItemDTO(itemDTO);
							ItemOutMessage itemOutMessage = itemService.findItemById(inputMessage);
							
							//ledgerDTO=stockOutputmessage.getStockLedgerDTO();
							List<ItemDTO> resultList=itemOutMessage.getItemDTOList();
							//System.out.println("stock"+stock+"- Qty"+billDetailDTO.getIssueQuantity());
							if(resultList!=null && resultList.size()>0){
								itemDTO=resultList.get(0);
								//System.out.println("itemDTO"+itemDTO+"-Sotck"+itemDTO.getStockTotal());
							if(itemDTO.getStockTotal()<billDetailDTO.getIssueQuantity()){
								b=true;
								break;
							}
							}
						}
			
		    return b;
			}
}
