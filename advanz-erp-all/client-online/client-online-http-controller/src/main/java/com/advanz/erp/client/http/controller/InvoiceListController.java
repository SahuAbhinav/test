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
import com.advanz.erp.masters.model.BillDTO;
import com.advanz.erp.masters.model.BillDetailDTO;
import com.advanz.erp.masters.model.DispatchDetailDTO;
import com.advanz.erp.masters.model.ProformaMasterDTO;
import com.advanz.erp.masters.model.SalesOrderDetailDTO;
import com.advanz.erp.masters.model.SalesOrderMasterDTO;
import com.advanz.erp.masters.model.msg.BillDetailInputMessage;
import com.advanz.erp.masters.model.msg.BillDetailOutMessage;
import com.advanz.erp.masters.model.msg.BillInputMessage;
import com.advanz.erp.masters.model.msg.BillOutMessage;
import com.advanz.erp.masters.model.msg.DispatchDetailOutMessage;
import com.advanz.erp.masters.model.msg.ProformaMasterInputMessage;
import com.advanz.erp.masters.model.msg.ProformaMasterOutMessage;
import com.advanz.erp.masters.model.msg.SalesOrderMasterInputMessage;
import com.advanz.erp.masters.model.msg.SalesOrderMasterOutputMessage;
import com.advanz.erp.masters.service.business.IBillDetailService;
import com.advanz.erp.masters.service.business.IBillService;
import com.advanz.erp.masters.service.business.IDispatchDetailService;
import com.advanz.erp.masters.service.business.IExciseLedgerService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IProformaMasterService;
import com.advanz.erp.masters.service.business.ISalesOrderMasterService;
import com.advanz.erp.masters.service.business.IStockLedgerService;

@Controller
public class InvoiceListController extends BaseController{
	@Autowired
	public IBillService billService;
	
	@Autowired
	public IBillDetailService billdeDetailService;
 
	@Autowired
	public IStockLedgerService stockLedgerService;

	@Autowired
	public IExciseLedgerService exciseLedgerService;
	@Autowired
	public IProformaMasterService proformaMasterService;
	
	@Autowired
	public IItemService itemService;
	
	 @Autowired
	public IDispatchDetailService dispatchDetailService;
	 
	 @Autowired
	public ISalesOrderMasterService iSalesOrderMasterService;
	@RequestMapping(value = "/show_invoice_list", method = RequestMethod.GET)
	public ModelAndView display(@ModelAttribute("invoice") InvoiceForm invoiceForm,@RequestParam String operation,HttpSession session,@RequestParam(value="menuId",required=false) String menuId,@RequestParam(value="next",required=false) Integer next) {
	  if(menuId!=null)
   	  {
  		session.setAttribute("menuId", menuId);
  	  }
	
		session.removeAttribute("invoice");
		ErrorDTO errorDTO = new ErrorDTO();
		if("Save".equals(operation)){
			errorDTO.setErrorMsg("Record Save Successfully !!!");
		}
		if("Update".equals(operation)){
			errorDTO.setErrorMsg("Record Updated successfully !!!");
		}
		
		List<BillDTO> list = new ArrayList<BillDTO>();
		if (invoiceForm == null) {
			invoiceForm = new InvoiceForm();
		}
		BillOutMessage billOutMessage = new BillOutMessage();
		BillInputMessage billInputMessage = new BillInputMessage();
		BillDTO billDTOs=new BillDTO();
		if(next==null ||next<0)
		{
			next=0;
			billDTOs.setNext(next);
			billInputMessage.setBillDTO(billDTOs);
			billOutMessage=billService.findInvoiceForPagination(billInputMessage);
		}
		else
		{
			billDTOs.setNext(next);
			billInputMessage.setBillDTO(billDTOs);
			billOutMessage=billService.findInvoiceForPagination(billInputMessage);
		}
		//	billOutMessage = billService.findAllBills();
		try {
			list = (ArrayList<BillDTO>) billOutMessage.getBillDTOList();
			
			//For Count total Item
			for(int i=0;i<list.size();i++){
				BillDTO billDTO=(BillDTO)list.get(i);
				BillDetailInputMessage billDetailInputMessage = new BillDetailInputMessage();
				BillDetailDTO billDetailDTO = new BillDetailDTO();
				billDetailDTO.setInvoiceNumber(billDTO.getInvoiceNumber());
				billDetailInputMessage.setBillDetailDTO(billDetailDTO);
			    BillDetailOutMessage billDetailOutMessage=	billdeDetailService.findBillByBillId(billDetailInputMessage);
				 
			   ArrayList<BillDetailDTO> billDetailList = (ArrayList<BillDetailDTO>) billDetailOutMessage.getBillDetailDTOList();
			   Integer totalItem =	 billDetailList.size();
			  
			   billDTO.setTotalItem(totalItem); 
			  }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		billDTOs.setNext(next);
		billDTOs.setPrevious(next);
		invoiceForm.setBillDTO(billDTOs);
		Boolean flag=(Boolean)session.getAttribute("hotKeyStatus");
		ModelAndView mav = new ModelAndView("invoice_list");
		mav.addObject("invoiceForm", invoiceForm);
		mav.addObject("invoiceList", list);
		mav.addObject("error", errorDTO);
		mav.addObject("hotKeyStatus", flag);
		// logger.info("Invoice List : "+list);
		return mav;
	}

	@RequestMapping(value = "/submitInvoiceList", method = RequestMethod.GET)
	public ModelAndView submit(@ModelAttribute("invoice") InvoiceForm invoiceForm,@RequestParam(value="next",required=false) Integer next,
			@RequestParam String operation, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		
		if (operation.equals("Delete")) {
		
			 BillDTO dto = new BillDTO();
			 dto.setInvoiceAutoId(invoiceForm.getInvoiceAutoId());
			 BillInputMessage billInputMessage = new BillInputMessage();
			 dto.setInvoiceNumber(invoiceForm.getInvoiceNumber());
			 
			 billInputMessage.setBillDTO(dto);
			 BillOutMessage billOutMessage1 = billService.findBillById(billInputMessage);
			 ArrayList<BillDTO> bilList= ( ArrayList<BillDTO>)billOutMessage1.getBillDTOList(); 
			 if(bilList!=null && bilList.size()>0){
				 dto= bilList.get(0);
			 }
			
			 billInputMessage.setBillDTO(dto);
			
			BillOutMessage billOutMessage = billService.findAllBills();
			ArrayList<BillDTO> billList1 =(ArrayList<BillDTO>) billOutMessage.getBillDTOList();
			
			// To check invoice number use or not in dispatch
		DispatchDetailOutMessage detailOutMessage= dispatchDetailService.findAllDispatchDetail();
		ArrayList<DispatchDetailDTO> disList=(ArrayList<DispatchDetailDTO>)	detailOutMessage.getDispatchDetailDTOList();
		
		for(int i=0;i<disList.size();i++){
			DispatchDetailDTO detailDTO=disList.get(i);
			if(detailDTO.getInvoiceNumber().equals(invoiceForm.getInvoiceNumber())){
				mav.setViewName("invoice_list");
				mav.addObject("invoiceForm", invoiceForm);
				mav.addObject("invoiceList", billList1);
				ErrorDTO error = new ErrorDTO();
				error.setErrorMsg("Sorry you can't delete this invoice because it is used by dispatch");
				mav.addObject("error", error);
				
				return mav;
			 }
		   }
		
		
		//Update Proforma Master
		ProformaMasterInputMessage proformaMasterInputMessage = new ProformaMasterInputMessage();
		 ProformaMasterDTO proformaMasterDTO = new ProformaMasterDTO();
		 proformaMasterDTO.setExciseInvoiceNo(invoiceForm.getInvoiceNumber());
		 proformaMasterInputMessage.setProformaMasterDTO(proformaMasterDTO);
		 ProformaMasterOutMessage proformaMasterOutMessage = proformaMasterService.findBillByInvoiceNo(proformaMasterInputMessage);
	     ArrayList<ProformaMasterDTO> masterList=(ArrayList<ProformaMasterDTO>) proformaMasterOutMessage.getProformaMasterDTOList();
		 
	     if(masterList!=null && masterList.size()>0){
			 proformaMasterDTO = new ProformaMasterDTO();
			 proformaMasterDTO= masterList.get(0);
			 
		 }
	     
		    try {
				proformaMasterDTO.setExciseInvoiceNo(null);
				proformaMasterInputMessage.setProformaMasterDTO(proformaMasterDTO);
				proformaMasterService.updateBill(proformaMasterInputMessage);
			} catch (Exception e) {
				
			}
		//End Update proforma master
		
			
//To update supply quantity in sales order when invoice is create
			
			BillDetailInputMessage billDetailInputMessage = new BillDetailInputMessage();
			BillDetailDTO billDetailDTO = new BillDetailDTO();
			billDetailDTO.setInvoiceNumber(invoiceForm.getInvoiceNumber());
			billDetailInputMessage.setBillDetailDTO(billDetailDTO);
		    BillDetailOutMessage billDetailOutMessage=	billdeDetailService.findBillByBillId(billDetailInputMessage);
		    ArrayList<BillDetailDTO> billDetailList = (ArrayList<BillDetailDTO>) billDetailOutMessage.getBillDetailDTOList();
		if(dto.getSalesOrderNumber()!=null)
		{
		try
			 {
				SalesOrderMasterInputMessage salesOrderMasterInputMessage=new SalesOrderMasterInputMessage();
				SalesOrderMasterDTO salesOrderMasterDTO=new SalesOrderMasterDTO();
				String salesOrderNo=proformaMasterOutMessage.getProformaMasterDTOList().get(0).getSalesOrderNumber();
				salesOrderMasterDTO.setSalesOrderNumber(salesOrderNo);
				salesOrderMasterInputMessage.setSalesOrderMasterDTO(salesOrderMasterDTO);
				SalesOrderMasterOutputMessage salesOrderMasterOutputMessage=iSalesOrderMasterService.findBySalesOrderNo(salesOrderMasterInputMessage);
				salesOrderMasterDTO=new SalesOrderMasterDTO();
				salesOrderMasterDTO=salesOrderMasterOutputMessage.getSalesOrderMasterDTOList().get(0);
				List<SalesOrderDetailDTO> salesOrderDetailDTOList=salesOrderMasterDTO.getSalesOrderDetailDTOList();
				try
				{
				for(int s = 0; s <salesOrderDetailDTOList.size(); s++)
				{
				 SalesOrderDetailDTO detailDTO=salesOrderDetailDTOList.get(s);
				 for(int p = 0; p <billDetailList.size(); p++)
				 {
				  if(billDetailList.get(p).getItemId().intValue() == salesOrderDetailDTOList.get(s).getItemId().intValue())
				   {
					double soDbPendingQty=salesOrderDetailDTOList.get(s).getPendingQty().doubleValue();
					double soDbSupplyQty=0.0;
					if(salesOrderDetailDTOList.get(s).getSupplyQty()==null)
					{
					   soDbSupplyQty=0.0;
					}
					else
					{
						soDbSupplyQty=salesOrderDetailDTOList.get(s).getSupplyQty().doubleValue();
					}
					
					if(salesOrderDetailDTOList.get(s).getInvoiceNumber()!=null)
					 {
						String ss= salesOrderDetailDTOList.get(s).getInvoiceNumber();
						
						
						
						StringBuffer buffer =new StringBuffer();
						String arr[]= ss.split(",");
						if(arr.length==1){
							ss=null;
							
						}else{
							for(int i=0;i<arr.length;i++){
							String	sn= arr[i];
						
								if(!sn.trim().equals(dto.getInvoiceId().toString().trim())){
									//String numToReplace=","+dto.getInvoiceId().toString();
									//String as= sn.replaceAll(numToReplace, "");
									if(buffer!=null && buffer.length()>0){
										buffer.append(","+sn);
									}else{
										buffer.append(sn);	
										
									}
									
								}
								
							}
						}
					 detailDTO.setInvoiceNumber(buffer.toString());
					 }
					
					double supplyQty=billDetailList.get(p).getQuantity().doubleValue();
					double balanceQty=soDbPendingQty+supplyQty;
				    double suppQty=soDbSupplyQty-supplyQty;
				 	detailDTO.setSupplyQty(suppQty);
				 	
				    detailDTO.setPendingQty(balanceQty);
				   }
				  }
				 salesOrderDetailDTOList.set(s, detailDTO);
				}
				}catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				
				salesOrderMasterDTO.setSalesOrderDetailDTOList(salesOrderDetailDTOList);
				salesOrderMasterInputMessage=new SalesOrderMasterInputMessage();
				salesOrderMasterInputMessage.setSalesOrderMasterDTO(salesOrderMasterDTO);
				iSalesOrderMasterService.updateSalesOrderMaster(salesOrderMasterInputMessage);
			 }catch (Exception e) {
				e.printStackTrace();
			}}	
		// End update supply quantity in sales order master	
			
		
		
		    billService.deleteBill(billInputMessage);
		    
		    
		    
		    //..........
		    
		    BillDTO billDTOs=new BillDTO();
			if(next==null ||next<0)
			{
				next=0;
				billDTOs.setNext(next);
				billInputMessage.setBillDTO(billDTOs);
				billOutMessage=billService.findInvoiceForPagination(billInputMessage);
			}
			else
			{
				billDTOs.setNext(next);
				billInputMessage.setBillDTO(billDTOs);
				billOutMessage=billService.findInvoiceForPagination(billInputMessage);
			}
		    
		    
		    
		    //billOutMessage = billService.findAllBills();
		    
			ArrayList<BillDTO> billList =(ArrayList<BillDTO>) billOutMessage.getBillDTOList();
				//For Count total Item
				//for(int i=0;i<billList.size();i++){
				//BillDTO billDTO=(BillDTO)billList.get(i);
				
				
			
			
			
			
			/*BillDetailInputMessage billDetailInputMessage = new BillDetailInputMessage();
				BillDetailDTO billDetailDTO = new BillDetailDTO();
				
				//billDetailDTO.setInvoiceNumber(billDTO.getInvoiceNumber());
				billDetailDTO.setInvoiceNumber(invoiceForm.getInvoiceNumber());
				billDetailInputMessage.setBillDetailDTO(billDetailDTO);
			    BillDetailOutMessage billDetailOutMessage=	billdeDetailService.findBillByBillId(billDetailInputMessage);
				 
			   ArrayList<BillDetailDTO> billDetailList = (ArrayList<BillDetailDTO>) billDetailOutMessage.getBillDetailDTOList();*/
			   
			
			
			
			
			
			
			   // To delete Bill Detail
			 //  if(billDetailList!=null && billDetailList.size()>0){
			   
			for(int i=0;i<billDetailList.size();i++) {
				   
			   billDetailDTO=billDetailList.get(i);
			   billDetailInputMessage.setBillDetailDTO(billDetailDTO);
			 
			   billdeDetailService.deleteBillDetail(billDetailInputMessage);
			   }
			  /* // To Delete StockLedger
			   StockLedgerInputMessage stockLedgerInputMessage = new StockLedgerInputMessage();
			   StockLedgerDTO stockLedgerDTO = new StockLedgerDTO();
			  
			   stockLedgerDTO.setTransactionNumber(invoiceForm.getInvoiceNumber());
			   stockLedgerDTO.setTransactionNumber(billDetailDTO.getInvoiceNumber());
			   stockLedgerInputMessage.setStockLedgerDTO(stockLedgerDTO);
			   StockLedgerOutMessage stockLedgerOutMessage =   stockLedgerService.findStockLedgerByTransactionId(stockLedgerInputMessage);

               ArrayList<StockLedgerDTO>  stockLedgerList=(ArrayList<StockLedgerDTO>)stockLedgerOutMessage.getStockLedgerDTOList();
			 
			    	for(int i=0;i<stockLedgerList.size();i++) {
			    	stockLedgerDTO = stockLedgerList.get(i);
			    	stockLedgerInputMessage.setStockLedgerDTO(stockLedgerDTO);
			    	stockLedgerService.deleteStockLedger(stockLedgerInputMessage);
			       }
			    	
			    	ExciseLedgerInputMessage exciseLedgerInputMessage=new ExciseLedgerInputMessage();
			    	ExciseLedgerDTO exciseLedgerDTO=new ExciseLedgerDTO();
			    	exciseLedgerDTO.setInvoiceNumber(invoiceForm.getInvoiceNumber());
			    	exciseLedgerDTO.setInvoiceNumber(billDetailDTO.getInvoiceNumber());
			    	exciseLedgerInputMessage.setExciseLedgerDTO(exciseLedgerDTO);
			    	exciseLedgerService.removeExciseLedgerByInvoice(exciseLedgerInputMessage);
			    	*/
	
			billDTOs.setNext(next);
			billDTOs.setPrevious(next);
			invoiceForm.setBillDTO(billDTOs);
			ErrorDTO error= new ErrorDTO();
			error.setErrorMsg("Successfully Record removed");
			mav.addObject("error", error);
			mav.setViewName("invoice_list");
			mav.addObject("invoiceForm", invoiceForm);
			mav.addObject("invoiceList", billList);
			return mav;
		   }
		
		if (operation.equals("GetInvoice")) {
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
			
			BillDTO dto = invoiceForm.getBillDTO();
			BillInputMessage billInputMessage = new BillInputMessage();
			
			String itemName = dto.getItemName();
			
			
			//Item
			
		   
		   
			 ArrayList<BillDTO> list=new ArrayList<BillDTO>();
		   
		   
		  // else{
			   billInputMessage.setBillDTO(dto);
				BillOutMessage outputMessage = billService.searchInvoice(billInputMessage);
				list = (ArrayList<BillDTO>) outputMessage.getBillDTOList();
		      // }
			
			
		
		
			//For Count total Item
			for(int i=0;i<list.size();i++){
				BillDTO billDTO=(BillDTO)list.get(i);
				BillDetailInputMessage billDetailInputMessage = new BillDetailInputMessage();
				BillDetailDTO billDetailDTO = new BillDetailDTO();
				billDetailDTO.setInvoiceNumber(billDTO.getInvoiceNumber());
				billDetailInputMessage.setBillDetailDTO(billDetailDTO);
			    BillDetailOutMessage billDetailOutMessage=	billdeDetailService.findBillByBillId(billDetailInputMessage);
				 
			   ArrayList<BillDetailDTO> billDetailList = (ArrayList<BillDetailDTO>) billDetailOutMessage.getBillDetailDTOList();
			   Integer totalItem =	 billDetailList.size();
			
			   billDTO.setTotalItem(totalItem);
			   
			  }
			//
			ErrorDTO  errorDTO=new ErrorDTO();
			if(list.equals(null) || list.size()==0)
			{
				errorDTO.setErrorMsg("Record Not Found !!!");
			}	
			
			mav.setViewName("invoice_list");
			mav.addObject("invoiceForm", invoiceForm);
			mav.addObject("invoiceList", list);
			mav.addObject("error123", errorDTO);
			return mav;
		}
		
		if (operation.equals("Cancel")) {
			ModelAndView mv = new ModelAndView(new RedirectView(
					"show_invoice_list"));
			return mv;
			
			
		}
		if (operation.equals("Add")) {
			session.removeAttribute("invoice");
			ModelAndView mv = new ModelAndView(new RedirectView(
					"show_invoice?operation=Show"));
		
			return mv;
		}
		ModelAndView mv = new ModelAndView(new RedirectView(
				"show_invoice?operation=Show"));
		return mv;
		
		/*InvoiceController inController = new InvoiceController();
		return inController.display(invoiceForm, operation, session);*/
		
		}
	
	
	    }