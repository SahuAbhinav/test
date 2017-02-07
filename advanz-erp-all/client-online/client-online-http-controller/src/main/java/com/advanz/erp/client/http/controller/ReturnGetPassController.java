package com.advanz.erp.client.http.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.jfree.data.DataUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.advanz.DataUtility;
import com.advanz.erp.client.http.controller.form.GetPassForm;
import com.advanz.erp.client.http.controller.form.PartyForm;
import com.advanz.erp.client.http.controller.form.ReturnGetPassForm;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.CityDTO;
import com.advanz.erp.masters.model.GetPassDetailDTO;
import com.advanz.erp.masters.model.GetPassMasterDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.ReturnGetPassDetailDTO;
import com.advanz.erp.masters.model.ReturnGetPassMasterDTO;
import com.advanz.erp.masters.model.TransactionTypeDTO;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.model.msg.CityInputMessage;
import com.advanz.erp.masters.model.msg.CityOutputMessage;
import com.advanz.erp.masters.model.msg.GetPassMasterInputMessage;
import com.advanz.erp.masters.model.msg.GetPassMasterOutputMessage;
import com.advanz.erp.masters.model.msg.PartyInputMessage;
import com.advanz.erp.masters.model.msg.PartyOutMessage;
import com.advanz.erp.masters.model.msg.ReturnGetPassMasterInputMessage;
import com.advanz.erp.masters.model.msg.ReturnGetPassMasterOutputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeInputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeOutputMessage;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.service.business.IGetPassMasterService;
import com.advanz.erp.masters.service.business.IPartyService;
import com.advanz.erp.masters.service.business.IReturnGetPassMasterService;
import com.advanz.erp.masters.service.business.ITransactionTypeService;

@Controller
public class ReturnGetPassController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);
	
	@Autowired
	DataSource dataSource;
	@Autowired
	public IPartyService partyService;
	
	@Autowired
	public IBranchService branchService;
	
	@Autowired
	public ITransactionTypeService transactionTypeService;
	
	@Autowired
	public IReturnGetPassMasterService returnGetPassMasterService;
	
	@RequestMapping(value = "/show_return_get_pass", method = RequestMethod.GET)
	public ModelAndView display(@ModelAttribute("returnGetPass") ReturnGetPassForm returnGetPassForm,@RequestParam String operation, HttpSession session) {
		ModelAndView mav = preloadedData();
	String finecialYear= getFinYear();
	String series= getGetPassTransactionSeries();
	ReturnGetPassMasterInputMessage returnGetPassInputMessage =null;
	SimpleDateFormat ft =null;
	Timestamp timestamp=null;
	if(operation.equals("Delete") || "Edite".equals(operation)|| "V".equals(operation)){
		Integer gatePassAutoId= returnGetPassForm.getReturnGatePassAutoId();
		ReturnGetPassMasterDTO dto = new ReturnGetPassMasterDTO();
		returnGetPassInputMessage =new ReturnGetPassMasterInputMessage();
		dto.setReturnGatePassAutoId(gatePassAutoId);
		returnGetPassInputMessage.setReturnGetPassMasterDTO(dto);
		ReturnGetPassMasterOutputMessage returnGetPassMasterOutputMessage=returnGetPassMasterService.findReturnGetPassMasterById(returnGetPassInputMessage);
	    dto= returnGetPassMasterOutputMessage.getReturnGetPassMasterDTOList().get(0);
	    series=dto.getTransactionSeries();
	    finecialYear=dto.getFinyr();
	    dto.setReturnGetPassYearId(series+"/"+finecialYear);
	    try{
	    returnGetPassForm.setTimeToshow(dto.getReturnGatePassTime().toString());
	    }catch (Exception e) {
		}
	    returnGetPassForm.setReturnGetPassMasterDTO(dto);
	    session.setAttribute("returnGetPass",returnGetPassForm);
	    mav.setViewName("return_get_pass_add");
		mav.addObject("returnGetPass", returnGetPassForm);
		mav.addObject("operation", operation);
		
		return mav;
	}
	
	if (session.getAttribute("returnGetPass") == null) {
	ReturnGetPassMasterDTO dto = new ReturnGetPassMasterDTO();
	returnGetPassInputMessage =new ReturnGetPassMasterInputMessage();
	dto.setFinyr(finecialYear);
	returnGetPassInputMessage.setReturnGetPassMasterDTO(dto);
	ReturnGetPassMasterOutputMessage returnGetPassMasterOutputMessage= returnGetPassMasterService.getNewReturnGetPassMasterSeriesNo(returnGetPassInputMessage);
	Integer seriesNo=returnGetPassMasterOutputMessage.getReturnGetPassSeriesNo();
	timestamp=returnGetPassMasterOutputMessage.getReturnGetPassSeriesDate();
	//System.out.println("Last Finished good time"+new Date(timestamp.getTime()));
	ft =new SimpleDateFormat ("yyyy,MM,dd");
	dto.setReturnGatePassDate(new Date());
	dto.setReturnGatePassId(seriesNo);
	dto.setReturnGetPassYearId(series+"/"+finecialYear);
	dto.setTransactionSeries(series);
	dto.setFinyr(finecialYear);
	dto.setReturnGatePassNumber(series+"/"+finecialYear+"/"+seriesNo);
	returnGetPassForm.setReturnGetPassMasterDTO(dto);
	}else{
		returnGetPassForm=(ReturnGetPassForm)session.getAttribute("returnGetPass");
		ReturnGetPassMasterDTO dto = returnGetPassForm.getReturnGetPassMasterDTO();
		
		returnGetPassInputMessage =new ReturnGetPassMasterInputMessage();
		dto.setFinyr(finecialYear);
		returnGetPassInputMessage.setReturnGetPassMasterDTO(dto);
		ReturnGetPassMasterOutputMessage returnGetPassMasterOutputMessage= returnGetPassMasterService.getNewReturnGetPassMasterSeriesNo(returnGetPassInputMessage);
		Integer seriesNo=returnGetPassMasterOutputMessage.getReturnGetPassSeriesNo();
		timestamp=returnGetPassMasterOutputMessage.getReturnGetPassSeriesDate();
		//System.out.println("Last Finished good time"+new Date(timestamp.getTime()));
		 ft =new SimpleDateFormat ("yyyy,MM,dd");
		dto.setReturnGatePassId(seriesNo);
		dto.setReturnGetPassYearId(series+"/"+finecialYear);
		dto.setTransactionSeries(series);
		dto.setFinyr(finecialYear);
		
		dto.setReturnGatePassNumber(series+"/"+finecialYear+"/"+seriesNo);
		returnGetPassForm.setReturnGetPassMasterDTO(dto);	
		
	}
	if(ft!=null && timestamp!=null)
		returnGetPassForm.setLastReturnGetPassDate(ft.format(new Date(timestamp.getTime())));
	
	mav.setViewName("return_get_pass_add");
	mav.addObject("returnGetPass", returnGetPassForm);
	return mav;
	}
	
	@RequestMapping(value = "/save_return_get_pass", method = RequestMethod.POST)
	public ModelAndView submit(@ModelAttribute("returnGetPass") ReturnGetPassForm returnGetPassForm,@RequestParam String operation, HttpSession session) {
		ModelAndView mav = preloadedData();
    session.setAttribute("returnGetPass",returnGetPassForm);
    
    if(operation.equals("Item Name")){
    	ModelAndView mv = new ModelAndView(new RedirectView("showItemListForReturnGetPass?operation=show"));

return mv;
    }
    
    if(operation.equals("remove")){
    	 returnGetPassForm=(ReturnGetPassForm)session.getAttribute("returnGetPass");
         List<ReturnGetPassDetailDTO> detailList= returnGetPassForm.getReturnGetPassMasterDTO().getReturnGetPassDetailDTOList();
         detailList.remove(returnGetPassForm.getIndexNo().intValue());
         
         session.setAttribute("returnGetPass",returnGetPassForm);
         mav.setViewName("return_get_pass_add");
     	mav.addObject("returnGetPass", returnGetPassForm);
     	return mav;
    }
    if(operation.equals("Cancel")){
    	session.removeAttribute("returnGetPass");
		ModelAndView mv = new ModelAndView(new RedirectView("show_return_get_pass_list?operation="+operation));
		mv.addObject("error", null);
		return mv;
    }
    
    if(operation.equals("GatePassNo")){
		ModelAndView mv = new ModelAndView(new RedirectView("show_get_pass_list?operation="+operation));
		mv.addObject("error", null);
		return mv;
    }
    
if(operation.equals("Save")){
	ReturnGetPassMasterDTO dto=returnGetPassForm.getReturnGetPassMasterDTO();
	ReturnGetPassMasterInputMessage getPassMasterInputMessage = new ReturnGetPassMasterInputMessage();
		Date date=DataUtility.getDate(dto.getReturnGatePassDate());
		dto.setReturnGatePassDate(date);
		if(dto.getApproved()!=null && dto.getApproved()>0){
			dto.setApprovedDate(date);
		}
		getPassMasterInputMessage.setReturnGetPassMasterDTO(dto);
		if(dto.getReturnGatePassAutoId()!=null && dto.getReturnGatePassAutoId()>0){
		dto.setModifiedUserId(getCreatedUserId());
			returnGetPassMasterService.updateReturnGetPassMaster(getPassMasterInputMessage);
		operation="updated";
		}else{
			dto.setCreatedUserId(getCreatedUserId());
		 returnGetPassMasterService.createReturnGetPassMaster(getPassMasterInputMessage);
		}
		
		session.removeAttribute("returnGetPass");
		ModelAndView mv = new ModelAndView(new RedirectView("show_return_get_pass_list?operation="+operation));
		mv.addObject("error", null);
		return mv;
		
			
		}
	return mav;
	}
	
	private String getGetPassTransactionSeries() {
		TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
		transactionTypeDTO.setSno(18);
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
	@RequestMapping(value = "/gate_pass_return_print_report/pdf", method = RequestMethod.GET )
    public  ModelAndView doSalesReportPDF(@RequestParam("ReturnGatePassNoPrompt") String ReturnGatePassNoPrompt,ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request) 
		 {
		logger.debug("Received request to download PDF report");
		response.setHeader("filename","grn_print_report.pdf");
		response.setContentType("application/pdf");
        //response.setHeader("Content-Disposition", "attachment:filename=_blank‌​");
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("ReturnGatePassNoPrompt", ReturnGatePassNoPrompt);
		
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
		modelAndView = new ModelAndView("pdfGatePassReturnPrintView", parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	   }
}
