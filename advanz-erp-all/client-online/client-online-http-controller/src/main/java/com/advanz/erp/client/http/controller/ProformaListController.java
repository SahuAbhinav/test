package com.advanz.erp.client.http.controller;

import java.util.ArrayList;
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

import com.advanz.erp.client.http.controller.form.DispatchForm;
import com.advanz.erp.client.http.controller.form.InvoiceForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.masters.model.DispatchDetailDTO;
import com.advanz.erp.masters.model.ProformaDetailDTO;
import com.advanz.erp.masters.model.ProformaMasterDTO;
import com.advanz.erp.masters.model.StockLedgerDTO;
import com.advanz.erp.masters.model.msg.DispatchDetailOutMessage;
import com.advanz.erp.masters.model.msg.ProformaDetailInputMessage;
import com.advanz.erp.masters.model.msg.ProformaDetailOutMessage;
import com.advanz.erp.masters.model.msg.ProformaMasterInputMessage;
import com.advanz.erp.masters.model.msg.ProformaMasterOutMessage;
import com.advanz.erp.masters.model.msg.StockLedgerInputMessage;
import com.advanz.erp.masters.service.business.IDispatchDetailService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IProformaDetailService;
import com.advanz.erp.masters.service.business.IProformaMasterService;
import com.advanz.erp.masters.service.business.IStockLedgerService;

@Controller
public class ProformaListController extends BaseController{
	@Autowired
	public IProformaMasterService proformaMasterService;
	
	@Autowired
	public IProformaDetailService proformadeDetailService;
 
	@Autowired
	public IStockLedgerService stockLedgerService;
	
	@Autowired
	public IItemService itemService;
	
	 @Autowired
	public IDispatchDetailService dispatchDetailService;
	@RequestMapping(value = "/show_proforma_list", method = RequestMethod.GET)
	public ModelAndView display(@ModelAttribute("invoice") InvoiceForm invoiceForm,@RequestParam(value="operation",required=false) String operation,@RequestParam(value="invoiceNumberToPrint",required=false) String invoiceNumberToPrint,HttpSession session,@RequestParam(value="next",required=false) Integer next) {
		
		session.removeAttribute("invoice");
		ErrorDTO errorDTO = new ErrorDTO();
		if(operation.equals("Save")){
			errorDTO.setErrorMsg("Record Save Successfully !!!");
		}
		if(operation.equals("Update")){
			errorDTO.setErrorMsg("Record Updated successfully !!!");
		}if(operation.equals("Duplicate Proforma In Bill")){
			errorDTO.setErrorMsg("This proforma has been already converted by other one !!!");
		}if(operation.equals("converted")){
			errorDTO.setErrorMsg("This proforma has been already converted you can not edite/delete it. !!!");
		}if(operation.equals("proformaToInvoice")){
			errorDTO.setErrorMsg("This proforma has been successfully converted into invoice. !!!");
		}if(operation.equals("stockNotAvail")){
			errorDTO.setErrorCode("OOS");
			errorDTO.setErrorMsg("Promarama can not convert into Exice beacuse one of the item is out of stock");
		}
		
		List<ProformaMasterDTO> list = new ArrayList<ProformaMasterDTO>();
		if (invoiceForm == null) {
			invoiceForm = new InvoiceForm();
		}
		ProformaMasterOutMessage proformaMasterOutMessage = new ProformaMasterOutMessage();
		ProformaMasterInputMessage proformaMasterInputMessage = new ProformaMasterInputMessage();
		//proformaMasterOutMessage = proformaMasterService.findAllBills();
		
		// Pagination is started
		
		ProformaMasterDTO dto = new ProformaMasterDTO();
		if(next==null ||next<0)
		{
			next=0;
			dto.setNext(next);
			proformaMasterInputMessage.setProformaMasterDTO(dto);
			proformaMasterOutMessage= proformaMasterService.getProformaListWithPagination(proformaMasterInputMessage);
			next=13;
		}
		else
		{
			
			dto.setNext(next);
			proformaMasterInputMessage.setProformaMasterDTO(dto);
			proformaMasterOutMessage= proformaMasterService.getProformaListWithPagination(proformaMasterInputMessage);
			next=next+13;
		}
		// Pagination is end
		
		try {
			
			list = (ArrayList<ProformaMasterDTO>) proformaMasterOutMessage.getProformaMasterDTOList();
			
			//For Count total Item
			for(int i=0;i<list.size();i++){
				ProformaMasterDTO proformaMasterDTO=(ProformaMasterDTO)list.get(i);
				ProformaDetailInputMessage proformaDetailInputMessage = new ProformaDetailInputMessage();
				ProformaDetailDTO proformaDetailDTO = new ProformaDetailDTO();
				proformaDetailDTO.setInvoiceNumber(proformaMasterDTO.getInvoiceNumber());
				proformaDetailInputMessage.setProformaDetailDTO(proformaDetailDTO);
				ProformaDetailOutMessage proformaDetailOutMessage=	proformadeDetailService.findBillByBillId(proformaDetailInputMessage);
				 
			   ArrayList<ProformaDetailDTO> proformaDetailList = (ArrayList<ProformaDetailDTO>) proformaDetailOutMessage.getProformaDetailDTOList();
			   Integer totalItem =	 proformaDetailList.size();
			 
			   proformaMasterDTO.setTotalItem(totalItem); 
			  }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
		ModelAndView mav = new ModelAndView("proforma_list");
		dto.setNext(next);
		dto.setPrevious(next-26);
		invoiceForm.setProformaMasterDTO(dto);
		mav.addObject("invoiceForm", invoiceForm);
		mav.addObject("invoiceList", list);
		mav.addObject("error", errorDTO);
		mav.addObject("invoiceNumberToPrint",invoiceNumberToPrint);
		
		// logger.info("Invoice List : "+list);
		return mav;
	}

	@RequestMapping(value = "/submitProformaList", method = RequestMethod.GET)
	public ModelAndView submit(@ModelAttribute("invoice") InvoiceForm invoiceForm,
			@RequestParam String operation, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		
		if (operation.equals("Delete")) {
		
			ProformaMasterDTO dto = new ProformaMasterDTO();
			 dto.setInvoiceAutoId(invoiceForm.getInvoiceAutoId());
			 ProformaMasterInputMessage proformaMasterInputMessage = new ProformaMasterInputMessage();
			 dto.setInvoiceNumber(invoiceForm.getInvoiceNumber());
			 proformaMasterInputMessage.setProformaMasterDTO(dto);
			 
			//To check Proforma is converted or not into invoice
			ProformaMasterOutMessage bom= 	proformaMasterService.findBillById(proformaMasterInputMessage);
			List<ProformaMasterDTO> ml= bom.getProformaMasterDTOList();
			ProformaMasterDTO md=ml.get(0);
			if(md.getExciseInvoiceNo()!=null){
			ModelAndView mv = new ModelAndView(new RedirectView("show_proforma_list?operation=converted"));
		       return mv;
			}
			//end
			 
			 
			 
			 ProformaMasterOutMessage proformaMasterOutMessage1 = proformaMasterService.findBillById(proformaMasterInputMessage);
			 ArrayList<ProformaMasterDTO> bilList= ( ArrayList<ProformaMasterDTO>)proformaMasterOutMessage1.getProformaMasterDTOList(); 
			 if(bilList!=null && bilList.size()>0){
				 dto= bilList.get(0);
			 }
			
			 proformaMasterInputMessage.setProformaMasterDTO(dto);
			
			 ProformaMasterOutMessage proformaMasterOutMessage = proformaMasterService.findAllBills();
			ArrayList<ProformaMasterDTO> proformaMasterList1 =(ArrayList<ProformaMasterDTO>) proformaMasterOutMessage.getProformaMasterDTOList();
			
			// To check invoice number use or not in dispatch
		DispatchDetailOutMessage detailOutMessage= dispatchDetailService.findAllDispatchDetail();
		ArrayList<DispatchDetailDTO> disList=(ArrayList<DispatchDetailDTO>)	detailOutMessage.getDispatchDetailDTOList();
		
		for(int i=0;i<disList.size();i++){
			DispatchDetailDTO detailDTO=disList.get(i);
			if(detailDTO.getInvoiceNumber().equals(invoiceForm.getInvoiceNumber())){
				mav.setViewName("proforma_list");
				mav.addObject("invoiceForm", invoiceForm);
				mav.addObject("invoiceList", proformaMasterList1);
				ErrorDTO error = new ErrorDTO();
				error.setErrorMsg("Sorry you can't delete this invoice because it is used by dispatch");
				mav.addObject("error", error);
				mav.addObject("invoiceNumberToPrint","null");
				return mav;
			 }
		   }
		
		
		proformaMasterService.deleteBill(proformaMasterInputMessage);
		
		proformaMasterOutMessage = proformaMasterService.findAllBills();
		    
			ArrayList<ProformaMasterDTO> proformaMasterList =(ArrayList<ProformaMasterDTO>) proformaMasterOutMessage.getProformaMasterDTOList();
				//For Count total Item
				//for(int i=0;i<billList.size();i++){
				//BillDTO billDTO=(BillDTO)billList.get(i);
				
			ProformaDetailInputMessage proformaDetailInputMessage = new ProformaDetailInputMessage();
			ProformaDetailDTO proformaDetailDTO = new ProformaDetailDTO();
				
				//billDetailDTO.setInvoiceNumber(billDTO.getInvoiceNumber());
			proformaDetailDTO.setInvoiceNumber(invoiceForm.getInvoiceNumber());
			proformaDetailInputMessage.setProformaDetailDTO(proformaDetailDTO);
			ProformaDetailOutMessage proformaDetailOutMessage=	proformadeDetailService.findBillByBillId(proformaDetailInputMessage);
				 
			   ArrayList<ProformaDetailDTO> proformaDetailList = (ArrayList<ProformaDetailDTO>) proformaDetailOutMessage.getProformaDetailDTOList();
			   
			   // To delete Bill Detail
			 //  if(billDetailList!=null && billDetailList.size()>0){
			   
			for(int i=0;i<proformaDetailList.size();i++) {
				   
				proformaDetailDTO=proformaDetailList.get(i);
				proformaDetailInputMessage.setProformaDetailDTO(proformaDetailDTO);
			 
				proformadeDetailService.deleteBillDetail(proformaDetailInputMessage);
			   }
			   // To Delete StockLedger
			   StockLedgerInputMessage stockLedgerInputMessage = new StockLedgerInputMessage();
			   StockLedgerDTO stockLedgerDTO = new StockLedgerDTO();
			  
			   stockLedgerDTO.setTransactionNumber(invoiceForm.getInvoiceNumber());
			   //stockLedgerDTO.setTransactionNumber(billDetailDTO.getInvoiceNumber());
			   stockLedgerInputMessage.setStockLedgerDTO(stockLedgerDTO);
			/* StockLedgerOutMessage stockLedgerOutMessage =   stockLedgerService.findStockLedgerByTransactionId(stockLedgerInputMessage);
                    ArrayList<StockLedgerDTO>  stockLedgerList=(ArrayList<StockLedgerDTO>)stockLedgerOutMessage.getStockLedgerDTOList();
			    	for(int i=0;i<stockLedgerList.size();i++) {
			    	stockLedgerDTO = stockLedgerList.get(i);
			    	stockLedgerInputMessage.setStockLedgerDTO(stockLedgerDTO);
			    	stockLedgerService.deleteStockLedger(stockLedgerInputMessage);
			       }*/
				ErrorDTO error= new ErrorDTO();
			error.setErrorMsg("Successfully Record removed");
			mav.addObject("error", error);
			mav.setViewName("proforma_list");
			mav.addObject("invoiceForm", invoiceForm);
			mav.addObject("invoiceList", proformaMasterList);
			return mav;
		   }
		
		if (operation.equals("GetInvoice") || operation.equals("V") || operation.equals("V1")) {
			 // if(session.getAttribute("dispatch")!=null){ 
				 DispatchForm dispatchForm =(DispatchForm)session.getAttribute("dispatch");
			     DispatchDetailDTO detailDTO = new DispatchDetailDTO();
			     try {
					ArrayList<DispatchDetailDTO> l=(ArrayList<DispatchDetailDTO>)dispatchForm.getDispatchDetailList();
					 
					 if (l == null) {
							l = new ArrayList<DispatchDetailDTO>();
							dispatchForm.setDispatchDetailList(l);
						}
					 detailDTO.setInvoiceNumber(invoiceForm.getInvoiceNumber());
					 l.add(detailDTO);
					 dispatchForm.setDispatchDetailList(l);
				} catch (Exception e) {
					
				}
			     
	             mav = new ModelAndView(new RedirectView("show_dispatch?operation=show"));
	             mav.addObject("dispatch", dispatchForm);
	             return mav;
	           // }
			  }
		if (operation.equals("Search")) {
			System.out.println("Sale Order List Controller Search Method :");
			ProformaMasterDTO dto = invoiceForm.getProformaMasterDTO();
			ProformaMasterInputMessage proformaMasterInputMessage = new ProformaMasterInputMessage();
			
			String itemName = dto.getItemName();
			//Item
		 
			 ArrayList<ProformaMasterDTO> list=new ArrayList<ProformaMasterDTO>();
		   
		  // else{
			 proformaMasterInputMessage.setProformaMasterDTO(dto);
			 ProformaMasterOutMessage outputMessage = proformaMasterService.searchInvoice(proformaMasterInputMessage);
				list = (ArrayList<ProformaMasterDTO>) outputMessage.getProformaMasterDTOList();
		
			//For Count total Item
			for(int i=0;i<list.size();i++){
				ProformaMasterDTO billDTO=(ProformaMasterDTO)list.get(i);
				ProformaDetailInputMessage billDetailInputMessage = new ProformaDetailInputMessage();
				ProformaDetailDTO billDetailDTO = new ProformaDetailDTO();
				billDetailDTO.setInvoiceNumber(billDTO.getInvoiceNumber());
				billDetailInputMessage.setProformaDetailDTO(billDetailDTO);
				ProformaDetailOutMessage billDetailOutMessage=	proformadeDetailService.findBillByBillId(billDetailInputMessage);
				 
			   ArrayList<ProformaDetailDTO> billDetailList = (ArrayList<ProformaDetailDTO>) billDetailOutMessage.getProformaDetailDTOList();
			   Integer totalItem =	 billDetailList.size();
			
			   billDTO.setTotalItem(totalItem);
			   
			  }
			//
			ErrorDTO  errorDTO=new ErrorDTO();
			if(list.equals(null) || list.size()==0)
			{
				errorDTO.setErrorMsg("Record Not Found !!!");
			}	
			
			mav.setViewName("proforma_list");
			mav.addObject("invoiceForm", invoiceForm);
			mav.addObject("invoiceList", list);
			mav.addObject("error123", errorDTO);
			return mav;
		}
		
		if (operation.equals("Cancel")) {
			ModelAndView mv = new ModelAndView(new RedirectView(
					"show_proforma_list"));
			return mv;
			
			
		}
		if (operation.equals("Add")) {
			session.removeAttribute("invoice");
			ModelAndView mv = new ModelAndView(new RedirectView(
					"show_proforma?operation=Show"));
		
			return mv;
		}
		ModelAndView mv = new ModelAndView(new RedirectView(
				"show_proforma?operation=Show"));
		return mv;
		
		/*InvoiceController inController = new InvoiceController();
		return inController.display(invoiceForm, operation, session);*/
		
		}
	

	    }
