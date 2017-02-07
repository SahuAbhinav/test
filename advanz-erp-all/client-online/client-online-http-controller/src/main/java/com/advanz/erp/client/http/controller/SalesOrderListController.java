package com.advanz.erp.client.http.controller;

import java.util.ArrayList;
import java.util.Iterator;
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

import com.advanz.erp.client.http.controller.form.InvoiceForm;
import com.advanz.erp.client.http.controller.form.SalesOrderForm;
import com.advanz.erp.masters.model.GrnDetailDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.ProformaDetailDTO;
import com.advanz.erp.masters.model.SalesOrderDetailDTO;
import com.advanz.erp.masters.model.SalesOrderMasterDTO;
import com.advanz.erp.masters.model.criteria.SalesOrderMasterSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.PartyInputMessage;
import com.advanz.erp.masters.model.msg.PartyOutMessage;
import com.advanz.erp.masters.model.msg.SalesOrderMasterInputMessage;
import com.advanz.erp.masters.model.msg.SalesOrderMasterOutputMessage;
import com.advanz.erp.masters.service.business.IPartyService;
import com.advanz.erp.masters.service.business.ISalesOrderMasterService;

@Controller
public class SalesOrderListController {

	@Autowired
	public ISalesOrderMasterService salesOrderMasterService;

	@Autowired
	public IPartyService partyService;
	@RequestMapping(value = "/showSalesOrderList", method = RequestMethod.GET)
	public ModelAndView display(
			@ModelAttribute("salesOrderForm") SalesOrderForm salesOrderForm,
			@RequestParam String operation, HttpSession session) {

		ArrayList<SalesOrderMasterDTO> list = new ArrayList<SalesOrderMasterDTO>();

		SalesOrderMasterOutputMessage salesOrderOutputMessage = null;
		SalesOrderMasterInputMessage salesOrderInputMessage = new SalesOrderMasterInputMessage();
		// salesOrderOutputMessage =
		// salesOrderMasterService.search(salesOrderInputMessage);
		//salesOrderOutputMessage = salesOrderMasterService.findAllSalesOrderMasters();
		
		/*list = (ArrayList<SalesOrderMasterDTO>) salesOrderOutputMessage
				.getSalesOrderMasterDTOList();*/

		
       list = getItemList(session);
		ModelAndView mav = new ModelAndView("salesOrder-invoice-list");
		mav.addObject("salesOrderForm", salesOrderForm);
		mav.addObject("salesOrderList", list);
		return mav;
	}

	
	private ArrayList<SalesOrderMasterDTO> getItemList(HttpSession session) {
		ArrayList<SalesOrderMasterDTO> list = new ArrayList<SalesOrderMasterDTO>();

		SalesOrderMasterOutputMessage salesOrderOutputMessage = null;
		SalesOrderMasterInputMessage salesOrderInputMessage = new SalesOrderMasterInputMessage();
		salesOrderOutputMessage = salesOrderMasterService
				.findAllSalesOrderMasters();
		list = (ArrayList<SalesOrderMasterDTO>) salesOrderOutputMessage
				.getSalesOrderMasterDTOList();
		
		for(int i=0;i<list.size(); i++){
		SalesOrderMasterDTO dto = new SalesOrderMasterDTO();	
		dto = list.get(i);
		
		// To removed sales order whose all quantity of all items is used in proforma
		Boolean b=true;
		List<SalesOrderDetailDTO> sdl= dto.getSalesOrderDetailDTOList();
		List<SalesOrderDetailDTO> newSalesDetalList=new ArrayList<SalesOrderDetailDTO>();
		int size1=sdl.size();
		 for (SalesOrderDetailDTO sdd : sdl) {
		/*for(int j=0;j<sdl.size();j++){
			SalesOrderDetailDTO sdd= sdl.get(j);
			try{ 
			if((sdd.getActiveStatus()!=null && sdd.getActiveStatus()==0) || (sdd.getPendingQty()<=0)){
				sdl.remove(sdd);
				//sdl.remove(j);
			}}catch(Exception e){e.printStackTrace();}
			*/
			
			try{
				if( sdd.getActiveStatus()==1 || sdd.getPendingQty()>0){
					newSalesDetalList.add(sdd);
				}}catch(Exception e){e.printStackTrace();}
			//size1=sdl.size();
		if(sdd.getPendingQty()>0){
			b=false;
			break;
		}
		}
		
		dto.getSalesOrderDetailDTOList().clear();
		dto.setSalesOrderDetailDTOList(newSalesDetalList);
		if(b==true){
			list.remove(dto);
		}
		//end.........
		
		}
		
		
		
		int size = list.size();
		// itemForm.setItemList(list);
		Iterator<SalesOrderMasterDTO> e = null;
		SalesOrderMasterDTO salesOrderMasterDTO = null;

		InvoiceForm invoiceForm = (InvoiceForm) session.getAttribute("invoice");
		
		invoiceForm.getProformaDetailList().clear();
		
		//System.out.println("invoiceForm   " + invoiceForm);

		if (invoiceForm != null) {
			for (ProformaDetailDTO billDto : invoiceForm.getProformaDetailList()) {
				//System.out.println("billDto " + billDto.getItemId());
				for (int i = 0; i < size-1; i++) {
					salesOrderMasterDTO = list.get(i);
					
					for (int j = 0; j < salesOrderMasterDTO.getSalesOrderDetailDTOList().size(); j++) {
					
						if (billDto.getItemId().equals(salesOrderMasterDTO.getSalesOrderDetailDTOList().get(j).getItemId())) {
						list.remove(i);
						break;
					}
						
					}
				}
			}
			
		}
			
		return list;
	}
	
	
	@RequestMapping(value = "/submitSalesOrderList", method = RequestMethod.GET)
	public ModelAndView submit(
			@ModelAttribute("salesOrderForm") SalesOrderForm salesOrderForm,
			@RequestParam String operation, HttpSession session) {
		//System.out.println("SSSSSSSSSSS OPERATION VALUE  IN SALES ORDER LIST CONTROLLER : "+operation);
		if (operation.equals("Search")) {
			SalesOrderMasterInputMessage salesOrderMasterInputMessage = new SalesOrderMasterInputMessage();
			SalesOrderMasterSearchCriteriaDTO criteriaDTO = new SalesOrderMasterSearchCriteriaDTO();
			//criteriaDTO.setSoDate(salesOrderForm.getSalesOrderDTO().getSalesOrderDate());
			criteriaDTO.setSoNumber(salesOrderForm.getSalesOrderNumber());
			try {
				criteriaDTO.setPartyName(salesOrderForm.getPartyName());
			} catch (Exception e1) {
				
			}
			
			
			salesOrderMasterInputMessage.setSearchCriteria(criteriaDTO);
			SalesOrderMasterOutputMessage masterOutputMessage = salesOrderMasterService
					.search(salesOrderMasterInputMessage);
			ArrayList<SalesOrderMasterDTO> list = (ArrayList<SalesOrderMasterDTO>) masterOutputMessage
					.getSalesOrderMasterDTOList();
			
			// To removed sales order whose all quantity of all items is used in proforma
			for(int i=0;i<list.size(); i++){
				SalesOrderMasterDTO dto = new SalesOrderMasterDTO();	
				dto = list.get(i);
				Boolean b=true;
				List<SalesOrderDetailDTO> sdl= dto.getSalesOrderDetailDTOList();
				List<SalesOrderDetailDTO> newSalesDetalList=new ArrayList<SalesOrderDetailDTO>();
			    int size=sdl.size();
			    for (SalesOrderDetailDTO sdd : sdl) {
				//for(int j=0;j<sdl.size();j++){
					//SalesOrderDetailDTO sdd= sdl.get(j);
					
					/*try{
						if((sdd.getActiveStatus()!=null && sdd.getActiveStatus()==0) || (sdd.getPendingQty()<=0)){
							sdl.remove(sdd);
							//sdl.remove(j);
						}}catch(Exception e){e.printStackTrace();}*/
					
					try{
						if(sdd.getActiveStatus()==1 || sdd.getPendingQty()>0){
							newSalesDetalList.add(sdd);
						}}catch(Exception e){e.printStackTrace();}
					
					
				//size=sdl.size();
				if(sdd.getPendingQty()>0){
					b=false;
					break;
				}}
				
				
				dto.getSalesOrderDetailDTOList().clear();
				dto.setSalesOrderDetailDTOList(newSalesDetalList);
				
				if(b==true){
					list.remove(dto);
				}
				}
			//end.........
			
			
			
			ModelAndView mav = new ModelAndView("salesOrder-invoice-list");
			mav.addObject("salesOrderForm", salesOrderForm);
			mav.addObject("salesOrderList", list);
			return mav;
		}
		   if(operation.equals("Cancel")){
			InvoiceForm invoiceForm = (InvoiceForm) session.getAttribute("invoice");
			
			invoiceForm.getProformaMasterDTO().setSalesOrderNumber(null);
			
			
			session.setAttribute("invoice",invoiceForm);
			ModelAndView mv = new ModelAndView(new RedirectView("show_proforma?operation=show"));
			mv.addObject("invoiceForm", invoiceForm);
			return mv;
		    }
		   SalesOrderMasterDTO dto =null;
		InvoiceForm invoiceForm = (InvoiceForm) session.getAttribute("invoice");
		if (invoiceForm != null) {
			invoiceForm.getProformaMasterDTO().setSalesOrderNumber(salesOrderForm.getSalesOrderNumber());
			 dto = new SalesOrderMasterDTO();
			dto.setSalesOrderNumber(salesOrderForm.getSalesOrderNumber());
			SalesOrderMasterInputMessage inputMessage = new SalesOrderMasterInputMessage();
			inputMessage.setSalesOrderMasterDTO(dto);
			SalesOrderMasterOutputMessage masterOutputMessage = salesOrderMasterService.findBySalesOrderNo(inputMessage);
			ArrayList<SalesOrderMasterDTO> salesorderMasterList = (ArrayList<SalesOrderMasterDTO>) masterOutputMessage.getSalesOrderMasterDTOList();
			if(salesorderMasterList!=null && salesorderMasterList.size()>0){
			dto = salesorderMasterList.get(0);
			}
			
			PartyDTO partyDTO = new PartyDTO();
			try{
				//System.out.println("DTAE IS................"+dto.getSalesOrderDate());
			invoiceForm.setSalesOrderDate(dto.getSalesOrderDate());
			invoiceForm.getProformaMasterDTO().setSalesOrderDate(dto.getSalesOrderDate());
			invoiceForm.setPartyId(dto.getPartyDTO().getPartyId());
			
			invoiceForm.setBuyerId(dto.getPartyDTO().getPartyId());
			if(dto.getPatyPoNumber()!=null){
			invoiceForm.getProformaMasterDTO().setBuyerPoNo(dto.getPatyPoNumber());
			}if(dto.getPartyPoDate()!=null){
				invoiceForm.getProformaMasterDTO().setBuyerPoDate(dto.getPartyPoDate());
			}
			
			invoiceForm.getProformaMasterDTO().setConsigneeId(dto.getConsigneeId());
			try{
			invoiceForm.getProformaMasterDTO().setPackingForwarding(dto.getPackingForwarding());
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				invoiceForm.getProformaMasterDTO().setFreightAmt(dto.getFreightAmt());
				}catch(Exception e){
					e.printStackTrace();
				}
			try{
				invoiceForm.getProformaMasterDTO().setOtherAmount(dto.getOtherAmount());
				}catch(Exception e){
					e.printStackTrace();
				}
			//Set Group Id String
			invoiceForm.getProformaMasterDTO().setItemGroupFlagId(dto.getItemGroupFlagId());
			
			//Set Cess% and HCess %
			invoiceForm.getProformaMasterDTO().setEducationCessperc(dto.getEducationCessPerc());
			invoiceForm.getProformaMasterDTO().setHighEducationCessPerc(dto.getHighEducationCessPerc());
			
			
			// Get Party Information
			PartyInputMessage partyInputMessage = new PartyInputMessage();
			partyDTO.setPartyId(dto.getPartyDTO().getPartyId());
			partyInputMessage.setPartyDTO(partyDTO);
			PartyOutMessage partyOutMessage = partyService.findPartyById(partyInputMessage);
			List<PartyDTO> list = new ArrayList<PartyDTO>();
			list = (ArrayList<PartyDTO>) partyOutMessage.getPartyDTOList();
			if (list != null && list.size() > 0) {
				partyDTO = list.get(0);
			}
			if(partyDTO.getVatCstTaxType()!=null){
				invoiceForm.getProformaMasterDTO().setVatCstTaxType(partyDTO.getVatCstTaxType());
			}
			}catch(Exception e){}
			ArrayList<ProformaDetailDTO> billDeatilList = (ArrayList<ProformaDetailDTO>) invoiceForm.getProformaDetailList();
			
			try{
			// Sales Order Detail List
			ArrayList<SalesOrderDetailDTO> salesorderDetailList = (ArrayList<SalesOrderDetailDTO>) dto.getSalesOrderDetailDTOList();
			
		
			for (int i = 0; i < salesorderDetailList.size(); i++) {
				SalesOrderDetailDTO detailDTO =salesorderDetailList.get(i);
				if(detailDTO.getActiveStatus()==0){
					salesorderDetailList.remove(detailDTO);
				}}
			
			for (int i = 0; i < salesorderDetailList.size(); i++) {
				
				ProformaDetailDTO billDetailDTO = new ProformaDetailDTO();
				//SalesOrderDetailDTO detailDTO = new SalesOrderDetailDTO();
				SalesOrderDetailDTO detailDTO =salesorderDetailList.get(i);
				if(detailDTO.getActiveStatus()==1 || detailDTO.getPendingQty()>0){
				billDetailDTO.setItemName(detailDTO.getItemName());
				billDetailDTO.setQuantity(detailDTO.getPendingQty());
				//New change after adding no of packet and qty/packet in sales order
				billDetailDTO.setQtyPerPacket(detailDTO.getQtyPerPacket());
				billDetailDTO.setNoOfPacket(detailDTO.getNoOfPacket());
				//
				billDetailDTO.setSalesRate(detailDTO.getSalesRate());
				billDetailDTO.setItemValue(detailDTO.getItemValue());
				billDetailDTO.setExcisePerc(detailDTO.getExcisePerc());
				
				//billDetailDTO.setDiscountAmount(detailDTO.getDiscountAmount());
				billDetailDTO.setDiscountAmountPerToShow(detailDTO.getDiscountPerc());
				
				
				billDetailDTO.setVatPerc(detailDTO.getVatPerc());
				billDetailDTO.setNetAmount(detailDTO.getNetAmount());
				
				billDetailDTO.setMeasurementUnitId(detailDTO.getMasterUnit().getMastersId());
				billDetailDTO.setUmoName(detailDTO.getMasterUnit().getName());
				//set Booked by
				
				billDetailDTO.setBookedBy(detailDTO.getBookedBy());
				//System.out.println("detailDTO.getBookedBy()"+detailDTO.getBookedBy()+"billDetailDTO.getBookedBy"+billDetailDTO.getBookedBy());
				billDetailDTO.setPrimaryUOM(detailDTO.getPrimaryUOM());
				billDetailDTO.setSecondaryConvUnit(detailDTO.getSecondaryConvUnit());
				billDetailDTO.setPrimaryUnit(detailDTO.getPrimaryUnit());
				
				//set sales order value
				billDetailDTO.setSalesOrderItem(1);
				
				try{
					
					/*
					if(partyDTO.getInvoiceType().equals("saleswithInState")){
						invoiceForm.getBillDTO().setSalesType("VAT");
						billDetailDTO.setTax(detailDTO.getVatPerc());
					}if(partyDTO.getInvoiceType().equals("salesOutsideState")){
						invoiceForm.getBillDTO().setSalesType("CST");
						billDetailDTO.setTax(detailDTO.getCstPerc());
					}*/
					
					if(partyDTO.getVatCstTaxType().equals("vat")){
						invoiceForm.getProformaMasterDTO().setSalesType("VAT");
						billDetailDTO.setTax(detailDTO.getVatPerc());
					}if(partyDTO.getVatCstTaxType().equals("cst with c form")){
						invoiceForm.getProformaMasterDTO().setSalesType("CST");
						billDetailDTO.setTax(detailDTO.getCstPerc());
					}if(partyDTO.getVatCstTaxType().equals("cst w/o c form")){
						invoiceForm.getProformaMasterDTO().setSalesType("VAT");
						billDetailDTO.setTax(detailDTO.getVatPerc());
					}
					
					
					
					
				 billDetailDTO.setMeasurementUnitId(detailDTO.getMasterUnit().getMastersId());
				}catch(Exception e){}
				
				billDetailDTO.setPackingDetail(detailDTO.getPackingDetail());
				billDetailDTO.setItemId(detailDTO.getItemId());
				
				billDeatilList.add(billDetailDTO);
				
				}
			}
			
			}catch(Exception e){}
			
			invoiceForm.setProformaDetailList(billDeatilList);
			session.setAttribute("invoice", invoiceForm);
		    }
		
		
		ModelAndView mv = new ModelAndView(new RedirectView("show_proforma?operation=show"));
		mv.addObject("invoiceForm", invoiceForm);
		
		return mv;
	}
}
