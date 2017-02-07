package com.advanz.erp.client.http.controller;

import java.sql.Timestamp;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.advanz.DataUtility;
import com.advanz.erp.client.http.controller.form.GetPassForm;
import com.advanz.erp.client.http.controller.form.ItemForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.CompanyDTO;
import com.advanz.erp.masters.model.GetPassDetailDTO;
import com.advanz.erp.masters.model.GetPassMasterDTO;
import com.advanz.erp.masters.model.IssueDetailMasterDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.StockLedgerDTO;
import com.advanz.erp.masters.model.TransactionTypeDTO;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.model.msg.CompanyOutMessage;
import com.advanz.erp.masters.model.msg.GetPassMasterInputMessage;
import com.advanz.erp.masters.model.msg.GetPassMasterOutputMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.PartyOutMessage;
import com.advanz.erp.masters.model.msg.StockLedgerInputMessage;
import com.advanz.erp.masters.model.msg.StockLedgerOutMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeInputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeOutputMessage;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.service.business.ICompanyService;
import com.advanz.erp.masters.service.business.IGetPassMasterService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IPartyService;
import com.advanz.erp.masters.service.business.IReturnGetPassMasterService;
import com.advanz.erp.masters.service.business.IStockLedgerService;
import com.advanz.erp.masters.service.business.ITransactionTypeService;

@Controller
@SessionAttributes({ "getPassForm" })
public class GetPassController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);
	
	@Autowired
	DataSource dataSource;
	@Autowired
	public IPartyService partyService;
	
	@Autowired
	public IBranchService branchService;
	@Autowired
	public IStockLedgerService stockLedgerService;

	@Autowired
	public ITransactionTypeService transactionTypeService;
	
	@Autowired
	public IGetPassMasterService getPassMasterService;
	@Autowired
	public IReturnGetPassMasterService returnGetPassMasterService;
	@Autowired
	public IItemService itemService;
	
	@Autowired
	ICompanyService companyService; 
	
	@RequestMapping(value = "/show_get_pass", method = RequestMethod.GET)
	public ModelAndView display(@ModelAttribute("getPass") GetPassForm getPassForm,@RequestParam String operation, HttpSession session) {
		ModelAndView mav = preloadedData();
	String finecialYear= getFinYear();
	String series= getGetPassTransactionSeries();
	GetPassMasterInputMessage getPassInputMessage =null;
	SimpleDateFormat ft =null;
	Timestamp timestamp=null;
	if(operation.equals("Delete") || "Edite".equals(operation)|| "V".equals(operation)){
		Integer gatePassAutoId= getPassForm.getGatePassAutoId();
		GetPassMasterDTO dto = new GetPassMasterDTO();
		getPassInputMessage =new GetPassMasterInputMessage();
		dto.setGatePassAutoId(gatePassAutoId);
		getPassInputMessage.setGetPassMasterDTO(dto);
	    GetPassMasterOutputMessage getPassMasterOutputMessage=getPassMasterService.findGetPassMasterById(getPassInputMessage);
	    dto= getPassMasterOutputMessage.getGetPassMasterDTOList().get(0);
	    series=dto.getTransactionSeries();
	    finecialYear=dto.getFinyr();
	    dto.setGetPassYearId(series+"/"+finecialYear);
	    try{
	    getPassForm.setTimeToshow(dto.getGatePassIssueTime().toString());
	    }catch (Exception e) {
		}
	    getPassForm.setGetPassMasterDTO(dto);
	    session.setAttribute("getPass",getPassForm);
	    mav.setViewName("get_pass_add");
		mav.addObject("getPass", getPassForm);
		mav.addObject("operation", operation);
		
		return mav;
	}
	
	if (session.getAttribute("getPass") == null) {
	GetPassMasterDTO dto = new GetPassMasterDTO();
	getPassInputMessage =new GetPassMasterInputMessage();
	dto.setGatePassDate(new Date());
	dto.setFinyr(finecialYear);
	getPassInputMessage.setGetPassMasterDTO(dto);
	GetPassMasterOutputMessage getPassMasterOutputMessage= getPassMasterService.getNewGetPassMasterSeriesNo(getPassInputMessage);
	Integer seriesNo=getPassMasterOutputMessage.getGetPassSeriesNo();
	dto.setGatePassId(seriesNo);
	dto.setGetPassYearId(series+"/"+finecialYear);
	dto.setTransactionSeries(series);
	dto.setFinyr(finecialYear);
	dto.setGatePassNumber(series+"/"+finecialYear+"/"+seriesNo);
	getPassForm.setGetPassMasterDTO(dto);
	timestamp=getPassMasterOutputMessage.getGetPassSeriesDate();
	//System.out.println("Last get pass time 1"+new Date(timestamp.getTime()));
	ft =new SimpleDateFormat ("yyyy,MM,dd");
	}else{
		getPassForm=(GetPassForm)session.getAttribute("getPass");
		GetPassMasterDTO dto = getPassForm.getGetPassMasterDTO();
		
		getPassInputMessage =new GetPassMasterInputMessage();
		dto.setFinyr(finecialYear);
		getPassInputMessage.setGetPassMasterDTO(dto);
		GetPassMasterOutputMessage getPassMasterOutputMessage= getPassMasterService.getNewGetPassMasterSeriesNo(getPassInputMessage);
		Integer seriesNo=getPassMasterOutputMessage.getGetPassSeriesNo();
		timestamp=getPassMasterOutputMessage.getGetPassSeriesDate();
		//System.out.println("Last get pass time 2"+new Date(timestamp.getTime()));
		 ft =new SimpleDateFormat ("yyyy,MM,dd");
		dto.setGatePassId(seriesNo);
		dto.setGetPassYearId(series+"/"+finecialYear);
		dto.setTransactionSeries(series);
		dto.setFinyr(finecialYear);
		dto.setGatePassNumber(series+"/"+finecialYear+"/"+seriesNo);
		getPassForm.setGetPassMasterDTO(dto);	
		
	}
	if(ft!=null && timestamp!=null)
	getPassForm.setLastGetPassDate(ft.format(new Date(timestamp.getTime())));
	mav.setViewName("get_pass_add");
	mav.addObject("getPass", getPassForm);
	return mav;
	}
	
	@RequestMapping(value = "/save_get_pass", method = RequestMethod.POST)
	public ModelAndView submit(@ModelAttribute("getPass") GetPassForm getPassForm,@RequestParam String operation, HttpSession session) {
		ModelAndView mav = preloadedData();
    session.setAttribute("getPass",getPassForm);
    
    if("Item Name".equals(operation)){
    	ModelAndView mv = new ModelAndView(new RedirectView("showItemListForGetPass?operation=show"));

return mv;
    }
    
    if("remove".equals(operation)){
    	 getPassForm=(GetPassForm)session.getAttribute("getPass");
         List<GetPassDetailDTO> detailList= getPassForm.getGetPassMasterDTO().getGetPassDetailDTOList();
         detailList.remove(getPassForm.getIndexNo().intValue());
         
         session.setAttribute("getPass",getPassForm);
         mav.setViewName("get_pass_add");
     	mav.addObject("getPass", getPassForm);
     	return mav;
    }
    if("Cancel".equals(operation)){
    	session.removeAttribute("getPass");
		ModelAndView mv = new ModelAndView(new RedirectView("show_get_pass_list?operation="+operation));
		mv.addObject("error", null);
		return mv;
    }
if("Save".equals(operation)){
		GetPassMasterDTO dto=getPassForm.getGetPassMasterDTO();
		GetPassMasterInputMessage getPassMasterInputMessage = new GetPassMasterInputMessage();
		Date date=DataUtility.getDate(dto.getGatePassDate());
		dto.setGatePassDate(date);
		if(dto.getApproved()!=null && dto.getApproved()>0){
			dto.setApprovedDate(date);
		}
		
		CompanyOutMessage outCompanyOutMessage= companyService.findAllCompanies();
		List<CompanyDTO> cList=outCompanyOutMessage.getCompanyDTOList();
		CompanyDTO cdto = null;
		if(cList!=null && cList.size()>0){
			cdto=(CompanyDTO)cList.get(0);
			//System.out.println("dto.getIssueLockFlag()"+cdto.getIssueLockFlag());
		}
		
		List gpDetailList1 =dto.getGetPassDetailDTOList();
		
		
		if(cdto!=null && cdto.getIssueLockFlag() && checkStockForGp(gpDetailList1)){
			//System.out.println("Product out of Stock");
			ErrorDTO error = new ErrorDTO();
			error.setErrorCode("OOS");
			error.setErrorMsg("You can not create Gate Pass Entry beacuse one of the item is out of stock");
			
		session.removeAttribute("getPass");
		//ModelAndView mv = new ModelAndView(new RedirectView("show_get_pass_list?operation="+operation));
		mav.addObject("error", error);
		mav.setViewName("get_pass_add");
		return mav;
			
			
		}
		
		
		getPassMasterInputMessage.setGetPassMasterDTO(dto);
		if(dto.getGatePassAutoId()!=null && dto.getGatePassAutoId()>0){
			dto.setModifiedUserId(getCreatedUserId());
			Double quantity=0.0;
			//To get quantity from return gate pass detail
			List<GetPassDetailDTO> gpList= dto.getGetPassDetailDTOList();
			for(int i=0;i<gpList.size();i++){
				GetPassDetailDTO gpdDTO=gpList.get(i);
			try {
		List l=returnGetPassMasterService.getPendingQty(gpdDTO.getGatePassNumber(), gpdDTO.getItemDTO().getItemId());
		for(int j=0;j<l.size();j++){
		Double quant=(Double)l.get(j);
		quantity +=quant;
		}} catch (Exception e) {
				  e.printStackTrace();
		}
		        gpdDTO.setPendingQuantity(gpdDTO.getGatePassQuantity()-quantity);
			}
	//END ....To get quantity from return gate pass detail
			
		getPassMasterService.updateGetPassMaster(getPassMasterInputMessage);
		operation="updated";
		}else{
			
			//To store default gate pass qty into pending qty
			List<GetPassDetailDTO> gpList= dto.getGetPassDetailDTOList();
			for(int i=0;i<gpList.size();i++){
				GetPassDetailDTO gpdDTO=gpList.get(i);
				gpdDTO.setPendingQuantity(gpdDTO.getGatePassQuantity());
			}
			dto.setCreatedUserId(getCreatedUserId());
		 getPassMasterService.createGetPassMaster(getPassMasterInputMessage);
		}
		
		session.removeAttribute("getPass");
		ModelAndView mv = new ModelAndView(new RedirectView("show_get_pass_list?operation="+operation));
		mv.addObject("error", null);
		return mv;
		
			
		}
	return mav;
	}
	
	@RequestMapping(value = "/submitItemListForGetPass", method = RequestMethod.GET)
	public ModelAndView submit(@ModelAttribute("itemForm") ItemForm itemForm,
			@RequestParam String operation, HttpSession session) {
		
		ModelAndView mav = preloadedData();
		Integer itemId = itemForm.getItemId();
		ItemInputMessage itemInputMessage = null;
		GetPassForm getPassForm = (GetPassForm) session.getAttribute("getPass");
		
		if (operation.equals("Cancel")) {
			ModelAndView mv = new ModelAndView(new RedirectView(
					"show_get_pass?operation=show"));
			return mv;

		}

		if ("GetItem".equals(operation)) {
			itemInputMessage = new ItemInputMessage();
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setItemId(itemId);
			itemInputMessage.setItemDTO(itemDTO);
			ItemOutMessage itemOutMessage = itemService.findItemById(itemInputMessage);
			ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
			if (list.size() == 1) {
				itemForm = new ItemForm();
				itemForm.setItemDTO(list.get(0));
			}

		} else if ("Search".equals(operation)) {

			itemInputMessage = new ItemInputMessage();
			ItemDTO itemDTO = new ItemDTO();

			itemDTO.setItemCode(itemForm.getItemDTO().getItemCode());
			itemDTO.setItemName(itemForm.getItemDTO().getInvoiceName());
			itemInputMessage.setItemDTO(itemDTO);
			ItemOutMessage itemOutMessage = itemService.findItem(itemInputMessage);
			ArrayList<ItemDTO> l = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
			
			ArrayList<ItemDTO> list=removeDuplicatItem(session, l);
			
			
			
			
			
			
			itemForm.setItemList(list);
			mav.setViewName("get_pass_item_list");
			mav.addObject("itemForm", itemForm);
			return mav;
		} else if ("OK".equals(operation)) {
			if (itemForm.getItemDTO().getItemId() != null) {

				ItemDTO dto = itemForm.getItemDTO();
				itemInputMessage = new ItemInputMessage();
				itemInputMessage.setItemDTO(dto);
				ItemOutMessage itemOutMessage = itemService.findItemById(itemInputMessage);
				ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
				dto = (ItemDTO) list.get(0);
				GetPassMasterDTO getPassMasterDTO= getPassForm.getGetPassMasterDTO();
				List<GetPassDetailDTO> detailList=null;
if(getPassMasterDTO.getGetPassDetailDTOList()!=null){
	 detailList=(List<GetPassDetailDTO>)getPassMasterDTO.getGetPassDetailDTOList();
}else{
	detailList = new ArrayList<GetPassDetailDTO>();
}
				//List<GetPassDetailDTO> detailList=new ArrayList<GetPassDetailDTO>();
			   GetPassDetailDTO detailDTO = new GetPassDetailDTO();
			   detailDTO.setItemDTO(dto);
			   detailDTO.setItemName(dto.getItemName());
				MastersDTO mastersDTO = dto.getMasterUnit();
				
				
				if (mastersDTO != null) {
					detailDTO.setMeasurementUnitName(mastersDTO.getName());
					detailDTO.setMeasurementUnitId(mastersDTO.getMastersId());
				}
			detailList.add(detailDTO);
			getPassMasterDTO.setGetPassDetailDTOList(detailList);
			getPassForm.getGetPassMasterDTO().setGetPassDetailDTOList(detailList);
			session.setAttribute("getPass", getPassForm);
			 mav.setViewName("get_pass_add");
			 mav.addObject("getPass", getPassForm);
			return mav;
		}
			return mav;
		}
		return mav;
	    }
	
	
	private String getGetPassTransactionSeries() {
		TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
		transactionTypeDTO.setName("Returnable");
		TransactionTypeInputMessage transactionTypeInputMessage = new TransactionTypeInputMessage();
		transactionTypeInputMessage.setTransactionTypeDTO(transactionTypeDTO);
		TransactionTypeOutputMessage transactionTypeOutputMessage = transactionTypeService.findTransactionTypeByName(transactionTypeInputMessage);
		List<TransactionTypeDTO> list = transactionTypeOutputMessage
				.getTransactionTypeDTOList();
		if (list != null && list.size() == 1)
		return list.get(0).getSeries();
		return null;
	}
	
	protected ModelAndView preloadedData() {
		ModelAndView mav = new ModelAndView();
		BranchOutMessage branchOutMessage = branchService.preloaded();
		List<BranchDTO> branchList = (ArrayList<BranchDTO>) branchOutMessage.getBranchDTOList();
		 PartyOutMessage partyOutMessage = partyService.preloadedPartys();
		//PartyOutMessage partyOutMessage = partyService.findDebtorPartyList();
		List<PartyDTO> partyList = (ArrayList<PartyDTO>) partyOutMessage
				.getPartyDTOList();
		mav.addObject("partyList", partyList);
		mav.addObject("branchList", branchList);
		return mav;
	}
	@RequestMapping(value = "/getTransactionSeries", method = RequestMethod.POST)
	public @ResponseBody JsonResponse addUser(@RequestParam String gatePassType,HttpSession session) {
		JsonResponse res = new JsonResponse();
		TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
		transactionTypeDTO.setName(gatePassType);
		TransactionTypeInputMessage transactionTypeInputMessage = new TransactionTypeInputMessage();
		transactionTypeInputMessage.setTransactionTypeDTO(transactionTypeDTO);
		TransactionTypeOutputMessage transactionTypeOutputMessage = transactionTypeService.findTransactionTypeByName(transactionTypeInputMessage);
		List<TransactionTypeDTO> list = transactionTypeOutputMessage
				.getTransactionTypeDTOList();
		String series= null;
		if (list != null && list.size() == 1){
		series= list.get(0).getSeries();
		res.setResult(series);
		}
		return res;
	}
	
	
	
	
	
	
	
	@RequestMapping(value = "/showItemListForGetPass", method = RequestMethod.GET)
	public ModelAndView display(@ModelAttribute("itemForm") ItemForm itemForm,
			@RequestParam(value="operation",required=false) String operation, HttpSession session,@RequestParam(value="next",required=false) Integer next) {

		
		ModelAndView mav = new ModelAndView();
		// ItemOutMessage itemOutMessage = itemService.findAllItem();

		//ItemOutMessage itemOutMessage = itemService.finishGoodItemList();
		
		//Pagination Start
		if(itemForm == null) {
			itemForm = new ItemForm();
		}
	
		ItemDTO dto=new ItemDTO();
		ItemOutMessage itemOutMessage=new ItemOutMessage();
		ItemInputMessage inputMessage=new ItemInputMessage();
		if(next==null ||next<0)
		{
			next=0;
			dto.setNext(next);
			inputMessage.setItemDTO(dto);
			itemOutMessage= itemService.finishGoodItemListWithPagination(inputMessage);
			next=13;
		}
		else
		{
			next=next+13;
			dto.setNext(next);
			inputMessage.setItemDTO(dto);
			itemOutMessage= itemService.finishGoodItemListWithPagination(inputMessage);
		}
		//Pagination End
		
		
		// ArrayList<ItemDTO> list = getItemList(session);
		
		ArrayList<ItemDTO> l = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
		ArrayList<ItemDTO> list=removeDuplicatItem(session, l);
		
		
		/*for (int i = 0; i < list.size(); i++) {
			ItemDTO itemDTO = list.get(i);
			StockLedgerInputMessage stockLedgerInputMessage = new StockLedgerInputMessage();
			StockLedgerDTO dto1 = new StockLedgerDTO();
			dto1.setItemId(itemDTO.getItemId());
			stockLedgerInputMessage.setStockLedgerDTO(dto1);
			StockLedgerOutMessage ledgerOutMessage = stockLedgerService.countStockByItemId(stockLedgerInputMessage);
			itemDTO.setStockTotal(ledgerOutMessage.getStockCount());
		}*/
		
		dto.setNext(next);
		dto.setPrevious(next-26);
		itemForm.setItemDTO(dto);
		itemForm.setItemList(list);

		mav.setViewName("get_pass_item_list");
		mav.addObject("itemForm", itemForm);
		return mav;
	}
	
	
	
	
	
	
	
	
	 @RequestMapping(value = "/gate_pass_print_report/pdf", method = RequestMethod.GET )
	    public  ModelAndView doSalesReportPDF(@RequestParam("GatePassNoPrompt") String GatePassNoPrompt,ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request) 
			 {
			logger.debug("Received request to download PDF report");
			response.setHeader("filename","grn_print_report.pdf");
			response.setContentType("application/pdf");
	        //response.setHeader("Content-Disposition", "attachment:filename=_blank‌​");
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			parameterMap.put("datasource", dataSource);
			parameterMap.put("GatePassNoPrompt", GatePassNoPrompt);
			
			String realPath  = request.getSession().getServletContext().getRealPath(System.getProperty("file.separator")+"WEB-INF"+System.getProperty("file.separator")+"images"+System.getProperty("file.separator")+"shree_logo.JPG");
			parameterMap.put("Image_Loc", realPath.toString());
			
			
			// pdfReport is the View of our application
			// This is declared inside the /WEB-INF/jasper-views.xml
			modelAndView = new ModelAndView("pdfGatePassPrintView", parameterMap);
			// Return the View and the Model combined
			return modelAndView;
		   }
	   public ArrayList<ItemDTO> removeDuplicatItem(HttpSession session,ArrayList<ItemDTO> list){
		   GetPassForm	getPassForm =(GetPassForm)session.getAttribute("getPass");
			//TO remove Duplicate Item from list
			if (getPassForm.getGetPassMasterDTO() != null) {
				List<GetPassDetailDTO> issueDetailMasterDTOList = getPassForm.getGetPassMasterDTO().getGetPassDetailDTOList();
				if (issueDetailMasterDTOList != null) {
					for (GetPassDetailDTO issueDetailMasterDTO : issueDetailMasterDTOList) {
						Iterator<ItemDTO> itr = list.iterator();
						while (itr.hasNext()) {
							ItemDTO temp = itr.next();
							if (!issueDetailMasterDTO.getDeletedFlag()
									&& temp.getItemId().equals(
											issueDetailMasterDTO.getItemDTO().getItemId())) {
								itr.remove();
							}}}}}
		   
		   
		   return list;
	   }
	   private boolean checkStockForGp(List issueDetailList){
			
			boolean b=false;
			GetPassDetailDTO gpDetailDto= new GetPassDetailDTO();
			StockLedgerInputMessage stockInputmessage = new StockLedgerInputMessage();
			StockLedgerOutMessage stockOutputmessage = new StockLedgerOutMessage();
			StockLedgerDTO ledgerDTO = new StockLedgerDTO();
			
			// To get Item Name
						for (int i = 0; i < issueDetailList.size(); i++) {
							Double stock=0.0;
							gpDetailDto = (GetPassDetailDTO) issueDetailList.get(i);
							//System.out.println("item Id"+gpDetailDto.getItemDTO().getItemId());
							ledgerDTO.setItemId(gpDetailDto.getItemDTO().getItemId());
							stockInputmessage.setStockLedgerDTO(ledgerDTO);
							stockOutputmessage=stockLedgerService.countStockByItemId(stockInputmessage);
							ledgerDTO=stockOutputmessage.getStockLedgerDTO();
							stock=Math.max(stockOutputmessage.getStockCount(),0);
							//System.out.println("stock"+stock+"- Qty"+gpDetailDto.getGatePassQuantity());
							if(stock<gpDetailDto.getGatePassQuantity()){
								b=true;
								break;
							}
						}
			
		    return b;
			}
}
