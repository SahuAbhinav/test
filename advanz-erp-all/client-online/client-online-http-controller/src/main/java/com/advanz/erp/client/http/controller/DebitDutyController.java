package com.advanz.erp.client.http.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.advanz.DataUtility;
import com.advanz.erp.client.http.controller.form.DebitDutyForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.DebitDutyMasterDTO;
import com.advanz.erp.masters.model.TransactionTypeDTO;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.model.msg.DebitDutyMasterInputMessage;
import com.advanz.erp.masters.model.msg.DebitDutyMasterOutPutMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeInputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeOutputMessage;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.service.business.IDebitDutyMasterService;
import com.advanz.erp.masters.service.business.ITransactionTypeService;

@Controller
public class DebitDutyController extends BaseController {
	
	@Autowired
	public IBranchService branchService;
	
	@Autowired
	public IDebitDutyMasterService debitDutyMasterService;
	
	@Autowired
	public ITransactionTypeService transactionTypeService;
	
	@RequestMapping(value = "/show_debit_duty", method = RequestMethod.GET)
	public ModelAndView display(
			@ModelAttribute("debitDuty") DebitDutyForm debitDutyForm,
			@RequestParam String operation, HttpSession session,@RequestParam(value="approvedFlag",required=false) String approvedFlag) {
		
		ModelAndView mav = preloadedData();
		Timestamp timestamp=null;
		SimpleDateFormat ft  =new SimpleDateFormat ("yyyy,MM,dd");
		
		ErrorDTO error = new ErrorDTO();
		if("Delete".equals(operation) || operation.equals("V")){
		//	error.setErrorCode("Dispatch Remove Confirmation");
		 error.setOperationName(operation);
		}
		
		String finecialYear= getFinYear();
		String series= getDebitDutyTransactionSeries();
		
		if (operation.equals("Edite") || "Delete".equals(operation)||operation.equals("V")) {
			DebitDutyMasterDTO dto = new DebitDutyMasterDTO();
			dto.setDebitDutyAutoId(debitDutyForm.getDebitDutyAutoId());
			
			DebitDutyMasterInputMessage debitDutyMasterInputMessage = new DebitDutyMasterInputMessage();
			debitDutyMasterInputMessage.setDebitDutyMasterDTO(dto);
			DebitDutyMasterOutPutMessage debitDutyMasterOutPutMessage = debitDutyMasterService.findDebitDutyById(debitDutyMasterInputMessage);
			ArrayList<DebitDutyMasterDTO> list = (ArrayList<DebitDutyMasterDTO>) debitDutyMasterOutPutMessage
					.getDebitDutyMasterDTOList();

			if (list != null && list.size() > 0) {
				dto = list.get(0);
				dto.setTransactionSeries(series+"/"+dto.getFinyr());
				debitDutyForm.setDebitDutyMasterDTO(dto);

			    }

			session.setAttribute("debitDuty", debitDutyForm);
			
		}

		if (session.getAttribute("debitDuty") == null) {
			
			DebitDutyMasterInputMessage inputMessage = new DebitDutyMasterInputMessage();
			DebitDutyMasterDTO masterDTO = new DebitDutyMasterDTO();
			masterDTO.setFinyr(getFinYear());
			masterDTO.setTransactionSeries(series + "-"	+ getFinYear());
			inputMessage.setDebitDutyMasterDTO(masterDTO);
			DebitDutyMasterOutPutMessage debitDutyMasterOutMessage = debitDutyMasterService.getNewDebitDutyMasterSeriesNo(inputMessage);
			//BillOutMessage billOutMessage = billService.getNewBillMasterSeriesNo(inputMessage);
		    Integer debitDutyId=	debitDutyMasterOutMessage.getDebitDutySeries();
			
		    timestamp= debitDutyMasterOutMessage.getDebitDutySeriesDate();
			System.out.println("Last debit duty time"+ft.format(new Date(timestamp.getTime())));
			 
			
			
			
		    DebitDutyMasterDTO masterDTO1 = new DebitDutyMasterDTO();
			masterDTO1.setDebitDutyNumber(series+"/"+finecialYear+"/" + debitDutyId);
			masterDTO1.setDebitDutyId(debitDutyId);
			
			masterDTO1.setTransactionSeries(series+"/"+finecialYear);
			masterDTO1.setFinyr(finecialYear);
			masterDTO1.setDebitDutyDate(new Date());
			debitDutyForm.setDebitDutyMasterDTO(masterDTO1);
			session.setAttribute("debitDuty", debitDutyForm);
		   }
		if(ft!=null && timestamp!=null)
			debitDutyForm.setLastDebitDate(ft.format(new Date(timestamp.getTime())));
		debitDutyForm = (DebitDutyForm) session.getAttribute("debitDuty");
		
		mav.addObject("operation", operation);
		mav.addObject("debitDuty", debitDutyForm);
		mav.addObject("error", error);
		mav.setViewName("debit_duty_add");
		mav.addObject("approvedFlag",approvedFlag);
		return mav;
	    }

	@RequestMapping(value = "/save_debit_duty", method = RequestMethod.POST)
	public ModelAndView submit(
			@ModelAttribute("debitDuty") DebitDutyForm debitDutyForm,
			@RequestParam String operation, HttpSession session) {

		String finecialYear= getFinYear();
		String series= getDebitDutyTransactionSeries();
		
		ModelAndView mav = preloadedData();
		DebitDutyMasterInputMessage masterInputMessage = new DebitDutyMasterInputMessage();
		ErrorDTO error = new ErrorDTO();
		
        if (operation.equalsIgnoreCase("Cancel")) {
			ModelAndView mv = new ModelAndView(new RedirectView("show_debit_duty_list?operation=show"));
			return mv;
		}
		if (operation.equals("Save")) {
			
				DebitDutyMasterDTO masterDTO = new DebitDutyMasterDTO();
				masterDTO = debitDutyForm.getDebitDutyMasterDTO();
				masterDTO.setTransactionSeries(getDebitDutyTransactionSeries());
				masterDTO.setFinyr(finecialYear);
				if(masterDTO.getApprovedFlag()!=null && masterDTO.getApprovedFlag()>0){
					masterDTO.setApprovedDate(new Date());
				}
				
				Date date=	masterDTO.getDebitDutyDate();
				try{
				date= DataUtility.getDate(date);
				}catch (Exception e) {
				}
				masterDTO.setDebitDutyDate(date);
				
				
				masterInputMessage.setDebitDutyMasterDTO(masterDTO);
				if (debitDutyForm.getDebitDutyMasterDTO().getDebitDutyAutoId() != null && debitDutyForm.getDebitDutyMasterDTO().getDebitDutyAutoId() > 0) {
					// masterDTO.setDispatchAutoId(dispatchForm.getDispatchAutoId());
					// masterInputMessage.setDispatchMasterDTO(masterDTO);
					masterDTO.setModifiedUserId(getCreatedUserId());
					debitDutyMasterService.updateDebitDuty(masterInputMessage);
					operation="Update";
					error.setErrorMsg("Debit duty update successfully");
				} else {
					masterDTO.setCreatedUserId(getCreatedUserId());
				   DebitDutyMasterOutPutMessage debitDutyMasterOutMessage =	debitDutyMasterService.createDebitDuty(masterInputMessage);
				   ErrorListDTO erorList= new ErrorListDTO();
				   ErrorDTO errorDTO1 = new ErrorDTO();
				   erorList=(ErrorListDTO)debitDutyMasterOutMessage.getErrorListDTO();
				    try {
						errorDTO1 =  erorList.getErrorList().get(0);
					
				   if(errorDTO1!=null){
					 debitDutyForm = (DebitDutyForm) session.getAttribute("debitDuty");
					 masterDTO= debitDutyForm.getDebitDutyMasterDTO();
					 String debitDutyNo= errorDTO1.getErrorCode();
					 String[] str=  debitDutyNo.split("/");
					
					 
					String dispSeries=str[0];
					String dispfnyear=str[1];
					String dispAutoId=str[2];
					Integer dispAuto=Integer.parseInt(dispAutoId);
					
					
					masterDTO.setDebitDutyNumber(series+"/"+finecialYear+"/"+ (dispAuto+1));
					masterDTO.setDebitDutyId(dispAuto+1);
					masterDTO.setFinyr(finecialYear);
					masterDTO.setTransactionSeries(series+"/"+finecialYear);
					debitDutyForm.setDebitDutyMasterDTO(masterDTO);
					
					mav.addObject("debitDuty", debitDutyForm);
					mav.addObject("error", errorDTO1);
					mav.setViewName("debit_duty_add");
					return mav;
				   }
				   
                    } catch (Exception e) {
						
					}
				error.setErrorMsg("Debit duty save successfully");
				}
		}
				session.removeAttribute("dispatch");
				ModelAndView mv = new ModelAndView(new RedirectView(
						"show_debit_duty_list?operation="+operation));
				/*//mav.addObject("error", error);
				error.setErrorMsg("Dispatch save successfully");
				mv.addObject("error", error);*/
				return mv;
			
	   }

	protected ModelAndView preloadedData() {
		ModelAndView mav = new ModelAndView();
	
		BranchOutMessage branchOutMessage = branchService.findAllBranches();
		List<BranchDTO> branchList = (List<BranchDTO>) branchOutMessage
				.getBranchDTOList();

		mav.addObject("branchList", branchList);
		return mav;
	}

	
	
	
	private String getDebitDutyTransactionSeries() {
		TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
		transactionTypeDTO.setSno(14);
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
}