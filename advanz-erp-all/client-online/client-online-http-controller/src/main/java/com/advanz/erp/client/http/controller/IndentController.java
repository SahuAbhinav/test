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

import com.advanz.DataUtility;
import com.advanz.erp.client.http.controller.form.IndentMasterForm;
import com.advanz.erp.client.http.controller.form.ItemForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.IndentDetailDTO;
import com.advanz.erp.masters.model.IndentMasterDTO;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.TransactionTypeDTO;
import com.advanz.erp.masters.model.criteria.ItemSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.model.msg.IndentInputMessage;
import com.advanz.erp.masters.model.msg.IndentOutputMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeInputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeOutputMessage;
import com.advanz.erp.masters.service.business.IBatchService;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.service.business.IIndentMasterService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IPurchaseOrderMasterService;
import com.advanz.erp.masters.service.business.ITransactionTypeService;

@Controller
@SessionAttributes({ "indentMasterForm", "branchList", "itemsMap", "opr" })
public class IndentController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(IndentController.class);

	@Autowired
	DataSource dataSource;
	
	@Autowired
	public IItemService itemService;

	@Autowired
	public IBranchService branchService;
	@Autowired
	public ITransactionTypeService transactionTypeService;
	@Autowired
	public IIndentMasterService indentMasterService;

	@Autowired
	public IBatchService batchService;

	@Autowired
	public IMastersService mastersService;

	@Autowired
	public IPurchaseOrderMasterService iPurchaseOrderMasterService;
	
	@RequestMapping(value = "/get_indent_list")
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
	logger.info("searchIssue : Department Name:"+indtDTO.getDepartmentName());
	IndentOutputMessage indentOutputMessage = null;
	IndentInputMessage indentInputMessage = new IndentInputMessage();

	
	try{
	if(indtDTO.getFromDate()!=null || indtDTO.getToDate()!=null||
			indtDTO.getIndentNumber()!=null
	||indtDTO.getItemName()!=null || indtDTO.getRaisedBy()!=null || indtDTO.getDepartmentName()!=null){
		if(indtDTO.getFromDate()!=null){
			indtDTO.setFromDate(indtDTO.getFromDate());
		}if(indtDTO.getToDate()!=null){
			indtDTO.setToDate(indtDTO.getToDate());
		}if(indtDTO.getItemName()!=null){
			indtDTO.setItemName(indtDTO.getItemName());
		}
		if(indtDTO.getRaisedBy()!=null){
			indtDTO.setRaisedBy(indtDTO.getRaisedBy());
		}
		//System.out.println("DEPARTMENT NAME IS ::::::"+indtDTO.getDepartmentName());
		if(indtDTO.getDepartmentName()!=null){
			indtDTO.setDepartmentName(indtDTO.getDepartmentName());
		}
		
	indentInputMessage.setIndentMasterDTO(indtDTO);
	indentOutputMessage = indentMasterService.search(indentInputMessage);

	list = (ArrayList<IndentMasterDTO>) indentOutputMessage.getIndentMasterDTOList();

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

	Integer depertmentId = indentMasterDTO.getDepartmentId();

	// For Depaertment Name
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

	ModelAndView mav = new ModelAndView("indent-list");
	mav.addObject("indentList", list);
	indentMasterForm.setIndentMasterDTO(indentDTO);
	mav.addObject("indentMasterForm", indentMasterForm);

	
	return mav;
	}

	
	
	@RequestMapping(value = "/show_new_indent_form", method = RequestMethod.GET)
	public ModelAndView newIssueMaterial(ModelMap model) {
		IndentMasterForm indentMasterForm = new IndentMasterForm();
		IndentMasterDTO indentMasterDTO = new IndentMasterDTO();
		String series = getIndentTransactionSeries();

		indentMasterDTO.setTransactionSeries(series);
		indentMasterDTO.setFinYear(getFinYear());
		IndentInputMessage indentInputMessage = new IndentInputMessage();
		indentInputMessage.setIndentMasterDTO(indentMasterDTO);
		IndentOutputMessage indentOutputMessage = null;
		indentOutputMessage = indentMasterService
				.getNewIndentSeriesNo(indentInputMessage);
		Integer indentId = indentOutputMessage.getIndentSeriesNo();
		indentMasterDTO.setIndentId(indentId);
		Timestamp timestamp= indentOutputMessage.getIndentSeriesDate();
		//System.out.println("Last indent last time"+new Date(timestamp.getTime()));
		SimpleDateFormat ft =new SimpleDateFormat ("yyyy,MM,dd");
		

		indentMasterDTO.setOrderSeries(indentMasterDTO.getTransactionSeries()
				+ "/" + getFinYear());
		indentMasterDTO.setIndentNumber(indentMasterDTO.getTransactionSeries()
				+ "/" + getFinYear() + "/" + indentMasterDTO.getIndentId());
		indentMasterDTO.setIndentDate(new Date());

		indentMasterForm.setIndentMasterDTO(indentMasterDTO);

		model.put("indentMasterForm", indentMasterForm);
		indentMasterForm.setLastIndentDate(ft.format(new Date(timestamp.getTime())));
		ModelAndView mav = new ModelAndView("indent-detail");
		
		return mav;
	}

	private String getIndentTransactionSeries() {
		TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
		transactionTypeDTO.setSno(19);
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



	@RequestMapping(value = "/new_indent", method = RequestMethod.GET)
	public ModelAndView newIndent(ModelMap model) {

		IndentMasterForm indentMasterForm = new IndentMasterForm();
		IndentMasterDTO indentMasterDTO = new IndentMasterDTO();
		String series = getIndentTransactionSeries();
		indentMasterDTO.setFinYear(getFinYear());
		indentMasterDTO.setTransactionSeries(series);
		IndentInputMessage indentInputMessage = new IndentInputMessage();
		indentInputMessage.setIndentMasterDTO(indentMasterDTO);
		IndentOutputMessage indentOutputMessage = indentMasterService
				.getNewIndentSeriesNo(indentInputMessage);
		Integer orderID = indentOutputMessage.getIndentSeriesNo();
		indentMasterDTO.setIndentId(orderID);
		
		Timestamp timestamp= indentOutputMessage.getIndentSeriesDate();
		//System.out.println("Last indent last time"+new Date(timestamp.getTime()));
		SimpleDateFormat ft =new SimpleDateFormat ("yyyy,MM,dd");
		
		indentMasterDTO.setOrderSeries(indentMasterDTO.getTransactionSeries()
				+ "/" + indentMasterDTO.getFinYear());
		indentMasterDTO.setIndentNumber(indentMasterDTO.getTransactionSeries()
				+ "/" + indentMasterDTO.getFinYear() + "/"
				+ indentMasterDTO.getIndentId());
		indentMasterDTO.setIndentDate(new Date());

		indentMasterForm.setIndentMasterDTO(indentMasterDTO);

		model.put("indentMasterForm", indentMasterForm);
		indentMasterForm.setLastIndentDate(ft.format(new Date(timestamp.getTime())));
		model.put("branchList", branchList());
		model.put("deptTypeList", getDeptTypeList());
		model.put("opr", "N");
		model.put("itemsMap", itemsMap());
		ModelAndView mav = new ModelAndView("indent-detail");
		return mav;
	}

	// @ModelAttribute("branchList")
	public List<BranchDTO> branchList() {
		BranchOutMessage branchOutMessage = branchService.findAllBranches();
		ArrayList<BranchDTO> list = (ArrayList<BranchDTO>) branchOutMessage
				.getBranchDTOList();
		return list;
	}

	@RequestMapping(value = "/backto_indent", method = RequestMethod.GET)
	public ModelAndView backToFinishGood(
			@ModelAttribute("indentMasterForm") IndentMasterForm indentMasterForm) {
		// calcSalesOrder(salesOrderMasterForm.getSalesOrderMasterDTO());
		ModelAndView mav = new ModelAndView("iindent-detail");
		return mav;
	}

	@RequestMapping(value = "/show_item_list_indent")
	public @ResponseBody
	ModelAndView showItemSelectionForm(
			@ModelAttribute("indentMasterForm") IndentMasterForm indentMasterForm,
			@RequestParam(value = "itemID", required = false) Integer itemId,
			@RequestParam(value = "operation", required = false) String operation,
			@ModelAttribute("searchCriteria") ItemSearchCriteriaDTO searchCriteria,
			@RequestParam(value = "btn", required = false) String btn,
			ModelMap model,
			@RequestParam(value = "next", required = false) Integer next) {
		ModelAndView mav = new ModelAndView("item-list-for-indent");
		if (searchCriteria == null) {
			searchCriteria = new ItemSearchCriteriaDTO();
		}
		
		if ("Cancel".equals(operation)) {
			mav = new ModelAndView("indent-detail");
			mav.addObject("indentMasterForm", indentMasterForm);

			return mav;
		}

		if (btn != null) {
			return addItemInIssue(indentMasterForm, model, itemId);
		}

		ItemOutMessage itemOutMessage = null; // itemService.findAllItem();
		ItemInputMessage itemInputMessage = new ItemInputMessage();
		ItemDTO itemDTO = new ItemDTO();

		if ("search".equalsIgnoreCase(operation)) {

			if (searchCriteria != null) {
				itemDTO.setItemName(searchCriteria.getInvoiceName());
				itemDTO.setItemCode(searchCriteria.getItemCode());
				itemDTO.setItemCategoryDTO(new ItemCategoryDTO());
				itemDTO.getItemCategoryDTO().setItemGroupDTO(new ItemGroupDTO());
				
			}
			itemInputMessage.setItemDTO(itemDTO);
			itemOutMessage = itemService.searchMaterialIssuesItems(itemInputMessage);

		} else {

			// Pagination Start

			try {
				
			} catch (Exception e) {

				e.printStackTrace();
			}

			itemOutMessage = new ItemOutMessage();
			
			
			if (next == null || next < 0) {
				next = 0;
				itemDTO.setNext(next);
				itemInputMessage.setItemDTO(itemDTO);
				
				itemOutMessage = itemService
				.findItemForPagination(itemInputMessage);
				next = 13;
			} else {
				itemDTO.setNext(next);
				itemInputMessage.setItemDTO(itemDTO);
		
				itemOutMessage = itemService
				.findItemForPagination(itemInputMessage);
				next = next + 13;
			}
			// Pagination end
		}

	
		ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage
				.getItemDTOList();

		if (indentMasterForm.getIndentMasterDTO() != null) {
			List<IndentDetailDTO> indentDetailDTOList = indentMasterForm
					.getIndentMasterDTO().getIndentDetailDTO();
			if (indentDetailDTOList != null) {
				for (IndentDetailDTO indentDetailDTO : indentDetailDTOList) {
					Iterator<ItemDTO> itr = list.iterator();
					while (itr.hasNext()) {
						ItemDTO temp = itr.next();
						if (!indentDetailDTO.getDeletedFlag()
								&& temp.getItemId().equals(
										indentDetailDTO.getItemId())) {
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

	@RequestMapping(value = "/get_item_by_idrs")
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

	@RequestMapping("/addItemInIndent")
	public ModelAndView addItemInIssue(
			@ModelAttribute("indentMasterForm") IndentMasterForm indentMasterForm,
			ModelMap model, @RequestParam("itemID") Integer itemId) {
		
		IndentMasterDTO indentMasterDTO = indentMasterForm.getIndentMasterDTO();

		if (itemId != null) {
			if (!isDuplicateItem(indentMasterDTO, itemId)) {
				ItemInputMessage itemInputMessage = new ItemInputMessage();
				ItemDTO itemDTO = new ItemDTO();
				itemDTO.setItemId(itemId);
				itemInputMessage.setItemDTO(itemDTO);
				List<ItemDTO> itemDtoList = itemService.findItemById(
						itemInputMessage).getItemDTOList();
				if (itemDtoList != null && itemDtoList.size() > 0) {
					itemDTO = itemDtoList.get(0);
					logger.info("Add Item : " + itemId);
					IndentDetailDTO indentDetailDTO = new IndentDetailDTO();
					BeanUtils.copyProperties(itemDTO, indentDetailDTO);
					indentDetailDTO.setTransactionSeries(indentMasterDTO
							.getTransactionSeries());
					indentDetailDTO.setItemName(itemDTO.getInvoiceName());
					indentDetailDTO.setMeasurementUnitId(itemDTO
							.getMasterUnit());
					indentDetailDTO.setIndentNumber(indentMasterDTO
							.getIndentNumber());
					if (indentMasterDTO.getIndentDetailDTO() == null) {
						indentMasterForm.getIndentMasterDTO()
								.setIndentDetailDTO(
										new ArrayList<IndentDetailDTO>());
					}
					indentMasterDTO.getIndentDetailDTO().add(
							indentDetailDTO);

				}
			}

		}
		ModelAndView mav = new ModelAndView("indent-detail");
		model.put("deptTypeList", getDeptTypeList());
		mav.addObject("itemFor", "M");
		mav.addObject("indentMasterForm", indentMasterForm);
		return mav;
	}

	@RequestMapping("/removeItemFromIndent")
	public ModelAndView removeItemFrom(
			@ModelAttribute("indentMasterForm") IndentMasterForm indentMasterForm,
			@RequestParam("index") Integer index,
			@ModelAttribute("opr") String opr, ModelMap model) {

		List<IndentDetailDTO> detailDTOList = indentMasterForm.getIndentMasterDTO().getIndentDetailDTO();
		if (detailDTOList != null ) {
			IndentDetailDTO dto = detailDTOList.get(index);
			Boolean flag=false;
			try {
				flag = iPurchaseOrderMasterService.findByPurchaseOrderDetailByIndentNumberAndItemId(dto.getIndentNumber(), dto.getItemDTO().getItemId());
			} catch (Exception e) {
				
			}
				if(flag==true){
					model.addAttribute("msg", "notRemove");
				}else{
			      detailDTOList.remove(dto);
				}
		}

		//
		IndentMasterDTO masterDTO = new IndentMasterDTO();
		masterDTO.setIndentDetailDTO(detailDTOList);
		ArrayList<IndentMasterDTO> indentMasterList = new ArrayList<IndentMasterDTO>();
		indentMasterList.add(masterDTO);
		indentMasterForm.setIndentMasterList(indentMasterList);
		model.put("indentMasterForm", indentMasterForm);
		// calcSalesOrder(salesOrderMasterForm.getSalesOrderMasterDTO());
		ModelAndView mav = new ModelAndView("indent-detail");
		String succ = "Dl";
		model.addAttribute("succ", succ);
		model.put("deptTypeList", getDeptTypeList());
		return mav;
	}

	private boolean isDuplicateItem(IndentMasterDTO indentMasterDTO,
			Integer itemId) {

		if (indentMasterDTO != null
				&& indentMasterDTO.getIndentDetailDTO() != null) {
			List<IndentDetailDTO> list = indentMasterDTO
					.getIndentDetailDTO();

			logger.info("list-----" + list);
			for (IndentDetailDTO e : list) {

				if (itemId.equals(e.getItemId()))
					return true;
			}
		}

		return false;
	}

	@RequestMapping(value = "/saveIndent", method = RequestMethod.POST)
	public String saveFinishedGoods(
			@ModelAttribute("indentMasterForm") IndentMasterForm indentMasterForm,@RequestParam(value="opration",required=false) String opration,
			ModelMap model,HttpSession session) {
		
		IndentMasterDTO indentMasterDTO = indentMasterForm.getIndentMasterDTO();
		indentMasterDTO.setDepartmentId(indentMasterForm.getIndentMasterDTO()
				.getDepartmentId());
		

		int itemCount = 0;
		if (indentMasterForm.getIndentMasterDTO().getIndentDetailDTO() != null
				&& indentMasterForm.getIndentMasterDTO()
						.getIndentDetailDTO().size() > 0) {
			for (IndentDetailDTO d : indentMasterForm.getIndentMasterDTO()
					.getIndentDetailDTO()) {
				if (!d.getDeletedFlag()) {
					itemCount++;
				}
			}
		}
		if (itemCount == 0) {
			ErrorDTO error = new ErrorDTO();
			error.setErrorMsg("Warning :You can not save Material Issues Without Items");
			model.addAttribute("errors", error);
			model.put("indentMasterForm", indentMasterForm);
			return "indent-detail";
		}

		IndentInputMessage indentInputMessage = new IndentInputMessage();
		IndentMasterDTO indentMasterDTO1 = indentMasterForm.getIndentMasterDTO();
		Date date = indentMasterDTO1.getIndentDate();
		Date indentDate = DataUtility.getDate(date);
		indentMasterDTO1.setIndentDate(indentDate);

		indentInputMessage.setIndentMasterDTO(indentMasterDTO1);
		IndentOutputMessage indentOutputMessage;
		String succ = "";
		if (indentMasterForm.getIndentMasterDTO().getIndentAutoId() == null || indentMasterForm.getIndentMasterDTO().getIndentAutoId().equals(0)) {
			indentMasterDTO1.setCreatedUserId(getCreatedUserId());
		//To set Default indent quantity to pending Quantity
		    List<IndentDetailDTO> indentDetailList=indentMasterDTO1.getIndentDetailDTO();
			for(int i=0;i<indentDetailList.size();i++){
				IndentDetailDTO indentDetailDTO= indentDetailList.get(i);
				
				if(indentDetailDTO.getIndentQty()!=null && indentDetailDTO.getIndentQty()>0){
					indentDetailDTO.setPendingQty(indentDetailDTO.getIndentQty());
					}else{
						ErrorDTO error = new ErrorDTO();
						error.setErrorMsg("Warning :Item Quantity value can not be 0 (Zero) or null");
						model.addAttribute("errors", error);
						model.put("branchList", branchList());
						model.put("deptTypeList", getDeptTypeList());
						model.put("indentMasterForm", indentMasterForm);
						
						return "indent-detail";
						//indentDetailDTO.setPendingQty(0.0);
					}
				
				}
			
	    indentOutputMessage = indentMasterService.createIndentMaster(indentInputMessage);
		succ = "Ad";
		}else {
			
			List<IndentDetailDTO> indentDetailList=indentMasterDTO1.getIndentDetailDTO();
			
			//To set Default indent quantity to pending Quantity
			for(int i=0;i<indentDetailList.size();i++){
				IndentDetailDTO indentDetailDTO= indentDetailList.get(i);
				//TO get Quantity from purchase order to update pending quantity in indent detail
				Double d=null;
				try{
				 d= iPurchaseOrderMasterService.FindPoQtyByItemAndIndentNumber(indentDetailDTO.getItemDTO().getItemId(), indentDetailDTO.getIndentNumber());
				}catch(Exception e){}
				 if(indentDetailDTO.getIndentQty()>0){
					if(d!=null && d>0){
						indentDetailDTO.setPendingQty(indentDetailDTO.getIndentQty()-d);
						}else{
						indentDetailDTO.setPendingQty(indentDetailDTO.getIndentQty());
				}
				}}
		indentMasterDTO1.setModifiedUserId(getCreatedUserId());
		indentOutputMessage = indentMasterService.updateIndentMaster(indentInputMessage);
		succ = "Up";
		}
		if (indentOutputMessage != null) {
		ErrorListDTO errorListDTO = indentOutputMessage.getErrorListDTO();
		if (errorListDTO != null && errorListDTO.hasErrors()) {
		ErrorDTO errorDTO = indentOutputMessage.getErrorListDTO().getErrorList().get(0);
				logger.info(" adding Error ");
				model.addAttribute("errors", errorDTO);

				IndentMasterDTO indentMasterDTO2 = indentMasterForm.getIndentMasterDTO();
				int i = indentMasterDTO2.getIndentId().intValue() + 1;
				indentMasterDTO2.setIndentId(indentMasterDTO2.getIndentId().intValue() + 1);
				indentMasterDTO2.setOrderSeries(getIndentTransactionSeries()
						+ "/" + getFinYear());
				indentMasterDTO2.setIndentNumber(getIndentTransactionSeries()
						+ "/" + getFinYear() + "/" + i);
				ArrayList<IndentDetailDTO> indentDetaiList = (ArrayList<IndentDetailDTO>) indentMasterDTO2
						.getIndentDetailDTO();
				for (int j = 0; j < indentDetaiList.size(); j++) {
					IndentDetailDTO detailMasterDTO = indentDetaiList
							.get(j);
					detailMasterDTO.setIndentNumber(getIndentTransactionSeries()
							+ "/" + getFinYear() + "/" + i);
				}
				indentMasterForm.setIndentMasterDTO(indentMasterDTO2);
				// model.addAttribute("issueMasterForm", issueMasterForm);
				return "indent-detail";
			}
		}
		IndentMasterDTO masterDTO = indentMasterForm.getIndentMasterDTO();
		masterDTO.setIndentDetailDTO(null);
		model.addAttribute("indentMasterForm", indentMasterForm);
		model.addAttribute("succ", succ);
		return "redirect:/get_indent_list";

	}

	@RequestMapping(value = "/get_indent", method = RequestMethod.GET)
	public ModelAndView getIndentData(
			@RequestParam("indentAutoId") Integer id,
			@RequestParam("opr") String opr,
			ModelMap model) {
		IndentMasterForm indentMasterForm = new IndentMasterForm();

		IndentOutputMessage indentOutputMessage = null;
		if (id != null && !id.equals(0)) {

			IndentInputMessage indentInputMessage = new IndentInputMessage();
			IndentMasterDTO indentMasterDTO = new IndentMasterDTO();
			indentMasterDTO.setIndentAutoId(id);
			indentInputMessage.setIndentMasterDTO(indentMasterDTO);
			indentOutputMessage = indentMasterService
					.findIndentMasterById(indentInputMessage);
			ArrayList<IndentMasterDTO> list = (ArrayList<IndentMasterDTO>) indentOutputMessage
					.getIndentMasterDTOList();
			if (list != null && list.size() > 0) {
				indentMasterForm.setIndentMasterDTO(list.get(0));
			}
		}

		model.put("indentMasterForm", indentMasterForm);

		model.put("branchList", branchList());
		model.put("opr", opr);
		model.put("deptTypeList", getDeptTypeList());
		ModelAndView mav = new ModelAndView("indent-detail");
		// mav.addObject("partyList",partyList());
		return mav;

	}

	@RequestMapping(value = "/remove_indent", method = RequestMethod.GET)
	public String removeFinishedGoods(@RequestParam("id") Integer id,
			ModelMap model) {
		logger.info("Removing..........issue = " + id);
		IndentOutputMessage indentOutputMessage = null;
		if (id != null && !id.equals(0)) {
			IndentInputMessage indentInputMessage = new IndentInputMessage();
			IndentMasterDTO indentMasterDTO = new IndentMasterDTO();
			indentMasterDTO.setIndentAutoId(id);
			indentMasterDTO.setModifiedUserId(getCreatedUserId());
			indentInputMessage.setIndentMasterDTO(indentMasterDTO);
			indentOutputMessage = indentMasterService.deleteIndentMaster(indentInputMessage);
		}
		ErrorListDTO errorListDTO =null;
		 try {
			errorListDTO =indentOutputMessage.getErrorListDTO();
		} catch (Exception e) {
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
		model.addAttribute("succ", succ);
		return "redirect:/get_indent_list";

	}

	public List<MastersDTO> getDeptTypeList() {
		MastersOutputMessage outMsg = null;
		MastersInputMessage inMsg = new MastersInputMessage();
		inMsg.setFormId(8);
		outMsg = mastersService.findFormById(inMsg);
		List<MastersDTO> deptTypeList = outMsg.getMastersDTOList();
		return deptTypeList;
	}


	public Map<Integer, ItemDTO> itemsMap() {
		return new TreeMap<Integer, ItemDTO>();
	}


	  @RequestMapping(value = "/indent_print_report/pdf", method = RequestMethod.GET )
	    public  ModelAndView doSalesReportPDF(ModelAndView modelAndView, @RequestParam(value="indentNo",required=false) String indentNo,HttpServletResponse response, HttpServletRequest request,HttpSession session) 
			 {
			logger.debug("Received request to download PDF report");
			response.setHeader("filename","indent_print_report.pdf");
			String IndentNoPrompt=null;
			if(indentNo!=null){
				IndentNoPrompt=indentNo;
			}
			response.setContentType("application/pdf");
	        //response.setHeader("Content-Disposition", "attachment:filename=_blank‌​");
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			parameterMap.put("datasource", dataSource);
			parameterMap.put("IndentNoPrompt", IndentNoPrompt);
			
			String realPath  = request.getSession().getServletContext().getRealPath(System.getProperty("file.separator")+"WEB-INF"+System.getProperty("file.separator")+"images"+System.getProperty("file.separator")+"shree_logo.JPG");
			parameterMap.put("Image_Loc", realPath.toString());
			
		    session.removeAttribute("issueNumber");
		
			modelAndView = new ModelAndView("pdfIndentPrintView", parameterMap);
			// Return the View and the Model combined
			return modelAndView;
		   }
}
