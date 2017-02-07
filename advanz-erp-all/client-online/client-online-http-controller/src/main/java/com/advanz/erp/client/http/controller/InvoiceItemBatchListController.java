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

import com.advanz.erp.client.http.controller.form.BatchForm;
import com.advanz.erp.client.http.controller.form.InvoiceForm;
import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.ProformaDetailDTO;
import com.advanz.erp.masters.model.StockLedgerDTO;
import com.advanz.erp.masters.model.msg.BatchInputMessage;
import com.advanz.erp.masters.model.msg.BatchOutMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.StockLedgerInputMessage;
import com.advanz.erp.masters.model.msg.StockLedgerOutMessage;
import com.advanz.erp.masters.service.business.IBatchService;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IStockLedgerService;

@Controller
public class InvoiceItemBatchListController extends BaseController {

	@Autowired
	public IBatchService batchService;

	@Autowired
	public IItemService itemService;

	@Autowired
	public IMastersService mastersService;

	@Autowired
	public IStockLedgerService stockLedgerService;

	@Autowired
	public IBranchService branchService;
	
	
	@RequestMapping(value = "/show_proforma_item_batch_list", method = RequestMethod.GET)
	public ModelAndView display(
			@ModelAttribute("batchForm") BatchForm batchForm,
			HttpSession session) {

		return null;
	}

	@RequestMapping(value = "/submitInvoiceItemBatchList", method = RequestMethod.GET)
	public ModelAndView submit(
			@ModelAttribute("batchForm") BatchForm batchForm,
			@RequestParam String operation, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		
	/*	if (operation.equals("Cancel")) {
			ModelAndView mv = new ModelAndView(new RedirectView(
					"showItemList?operation=show"));
			return mv;
		}*/

		ItemInputMessage itemInputMessage = null;
		InvoiceForm invoiceForm = (InvoiceForm) session.getAttribute("invoice");
		
		if (operation.equals("OK")) {

			
			/*if(invoiceForm.getIndexNo()>0){
				 ArrayList<BillDetailDTO> billList = (ArrayList<BillDetailDTO>)invoiceForm.getBillDetailList();
				 BillDetailDTO  billDetailDTO = billList.get(invoiceForm.getIndexNo());
				batchForm.setIndexNo(invoiceForm.getIndexNo());
				
				ModelAndView mv = new ModelAndView(new RedirectView(
				"show_proforma?operation=show"));
		        return mv;
				}*/
			
				
				if(batchForm.getBatchDTO().getBatchNo()== null){
				Integer itemId=(Integer)session.getAttribute("itemId");
				ModelAndView mv = new ModelAndView(new RedirectView("submitInvoiceItemBatchList?operation=show&itemId="+ itemId));
				return mv;
				}
			
				
				/*// get Branch Information
			BranchDTO branchDTO = new BranchDTO();
			branchDTO.setBranchId(invoiceForm.getProformaMasterDTO().getBranchDTO().getBranchId());
				
			BranchInputMessage branchInputMessage = new BranchInputMessage();
			branchInputMessage.setBranchDTO(branchDTO);
			BranchOutMessage branchOutMessage=	branchService.findBranchById(branchInputMessage);
			ArrayList<BranchDTO> branchList = (ArrayList<BranchDTO>)branchOutMessage.getBranchDTOList();
			if(branchList!=null && branchList.size()>0){
			   branchDTO =branchList.get(0);
			   
			}*/
				
				
				
				
				
			BatchInputMessage batchInputMessage = new BatchInputMessage();
			BatchDTO batchDTO = new BatchDTO();
			//batchInputMessage.setItemId(batchForm.getBatchDTO().getItemDTO().getItemId());
			batchDTO.setBatchNo(batchForm.getBatchDTO().getBatchNo());
				
			batchInputMessage.setBatchDTO(batchDTO);
			
			//BatchOutMessage batchOutMessage = batchService.findAllBatchByItemId(batchInputMessage);
			BatchOutMessage batchOutMessage = batchService.findBatchByBatchNo(batchInputMessage);
			
			
			ArrayList<BatchDTO> batchList = (ArrayList<BatchDTO>) batchOutMessage.getBatchDTOList();
			if (batchList != null && batchList.size() > 0) {
				batchDTO = batchList.get(0);
			}

			ItemDTO dto = new ItemDTO();
			dto.setItemId(batchDTO.getItemDTO().getItemId());
			//dto.setItemId(batchForm.getBatchDTO().getItemDTO().getItemId());
			itemInputMessage = new ItemInputMessage();
			itemInputMessage.setItemDTO(dto);
			ItemOutMessage itemOutMessage = itemService.findItemById(itemInputMessage);
			ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
			dto = (ItemDTO) list.get(0);
			
			
			ProformaDetailDTO billDetailDTO = new ProformaDetailDTO();
			
			billDetailDTO.setSalesRate(batchDTO.getInvoiceRate());
			billDetailDTO.setExcisePerc(batchDTO.getExcisePerc());
			billDetailDTO.setItemName(batchDTO.getItemDTO().getItemName());
			billDetailDTO.setItemId(batchDTO.getItemDTO().getItemId());
			
     try{
	
	
	
	/*// Get tax from bill detail
	if (invoiceForm.getBillDTO().getSalesType().equals("Sales with In State")) {
		billDetailDTO.setTax(batchDTO.getVatPerc());
	}
	if (invoiceForm.getBillDTO().getSalesType().equals("Sales Outside State")) {
		billDetailDTO.setTax(batchDTO.getCstPerc());
	}*/
    	
	
	if (invoiceForm.getProformaMasterDTO().getVatCstTaxType().equals("vat")) {
		billDetailDTO.setTax(batchDTO.getVatPerc());
	}if (invoiceForm.getProformaMasterDTO().getVatCstTaxType().equals("cst with c form")) {
		billDetailDTO.setTax(batchDTO.getCstPerc());
	}if (invoiceForm.getProformaMasterDTO().getVatCstTaxType().equals("cst w/o c form")) {
		billDetailDTO.setTax(batchDTO.getVatPerc());
	}
	
	
	
billDetailDTO.setInvoiceNumber(invoiceForm.getProformaMasterDTO().getInvoiceNumber());
}catch(Exception exception){}

if (dto.getMasterUnit() != null)
				
				
				
			billDetailDTO.setMeasurementUnitId(dto.getMasterUnit().getMastersId());
			billDetailDTO.setDiscountAmount(batchDTO.getDiscountPerc());
			
			billDetailDTO.setBatchNo(batchDTO.getBatchNo());
			
			billDetailDTO.setExpiryDate(batchDTO.getExpiryDate());
			billDetailDTO.setMfgDate(batchDTO.getMfgDate());
			
			
			
			 //billDetailDTO.setDiscountPer(batchDTO.getDiscountPerc());
			 billDetailDTO.setDiscountAmountPerToShow(batchDTO.getDiscountPerc());
			
			
			billDetailDTO.setQuantity(1.0);
			// Get UMO Name
			MastersDTO mastersDTO = dto.getMasterUnit();
			if (mastersDTO != null) {
				billDetailDTO.setUmoName(mastersDTO.getName());
			}
			
			
		    // To add Batch no , MFG date and exp date in item
			if(invoiceForm.getIndexNo()!=null){
				 ArrayList<ProformaDetailDTO> billList = (ArrayList<ProformaDetailDTO>)invoiceForm.getProformaDetailList();
				 
				 ProformaDetailDTO  billDetaDTO = billList.get(invoiceForm.getIndexNo().intValue());
				 billDetaDTO.setBatchNo(batchDTO.getBatchNo());
				 billDetaDTO.setExpiryDate(batchDTO.getExpiryDate());
				 billDetaDTO.setMfgDate(batchDTO.getMfgDate());
				
			
				 if (mastersDTO != null) {
					 billDetaDTO.setUmoName(mastersDTO.getName());
					}
				    invoiceForm.setIndexNo(null);
					ModelAndView mv = new ModelAndView(new RedirectView(
					"show_proforma?operation=show"));
			        return mv;
				}
            //end
			
			
			
			
			List billDetailList = invoiceForm.getProformaDetailList();
			
			if (billDetailList == null) {
				billDetailList = new ArrayList<ProformaDetailDTO>();
				invoiceForm.setProformaDetailList(billDetailList);
			}
			billDetailList.add(billDetailDTO);
			
			invoiceForm.setProformaDetailList(billDetailList);
			ModelAndView mv = new ModelAndView(new RedirectView("show_proforma?operation=show"));
			return mv;
		    }

		BatchInputMessage batchInputMessage = new BatchInputMessage();
		// BatchDTO batchDTO = new BatchDTO();
		batchInputMessage.setItemId(batchForm.getItemId());
		BatchOutMessage batchOutMessage = batchService.findAllBatchByItemId(batchInputMessage);
		
		ArrayList<BatchDTO> batchList = (ArrayList<BatchDTO>) batchOutMessage.getBatchDTOList();
		
		// To RemoveDuplicate ItemFrom List
		//ArrayList<BatchDTO> batchList =getItemList(session, batchForm.getItemId());
		
		if (batchList != null && batchList.size() > 0) {

			for (int i = 0; i < batchList.size(); i++) {
				BatchDTO batchDTO = batchList.get(i);

				StockLedgerInputMessage stockLedgerInputMessage = new StockLedgerInputMessage();
				StockLedgerDTO dto = new StockLedgerDTO();
				dto.setItemId(batchForm.getItemId());
				stockLedgerInputMessage.setStockLedgerDTO(dto);
				StockLedgerOutMessage ledgerOutMessage = stockLedgerService.countStockByItemId(stockLedgerInputMessage);
				batchDTO.setStockTotal(ledgerOutMessage.getStockCount());
			}
			
		   }
		else {
			
			ModelAndView mv = new ModelAndView(new RedirectView(
					"show_proforma?operation=show"));
			return mv;

		}

		if(invoiceForm.getIndexNo()!=null){
			batchForm.setIndexNo(invoiceForm.getIndexNo().intValue());
		}
		
		mav.addObject("batchList", batchList);
		mav.setViewName("batch_invoice_item_list");
		mav.addObject("batchForm", batchForm);
		return mav;
	}
	
	private ArrayList<BatchDTO> getItemList(HttpSession session, Integer itemId) {

		
		BatchInputMessage batchInputMessage = new BatchInputMessage();
		// BatchDTO batchDTO = new BatchDTO();
		batchInputMessage.setItemId(itemId);
		BatchOutMessage batchOutMessage = batchService.findAllBatchByItemId(batchInputMessage);
		ArrayList<BatchDTO> list = (ArrayList<BatchDTO>) batchOutMessage.getBatchDTOList();
		
		
		
		// ItemOutMessage itemOutMessage = itemService.findAllItem();
		//ItemOutMessage itemOutMessage = itemService.finishGoodItemList();
		
		//ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();

		for (int i = 0; i < list.size(); i++) {
			//ItemDTO dto = new ItemDTO();
			 BatchDTO dto = new BatchDTO();
			dto = list.get(i);
		}

		int size = list.size();
		// itemForm.setItemList(list);
		Iterator<BatchDTO> e = null;
		BatchDTO batchDTO = null;

		InvoiceForm invoiceForm = (InvoiceForm) session.getAttribute("invoice");
		System.out.println("invoiceForm   " + invoiceForm);

		if (invoiceForm != null) {
			for (ProformaDetailDTO billDto : invoiceForm.getProformaDetailList()) {
				for (int i = 0; i < size; i++) {
					try{
					batchDTO = list.get(i);
					}catch(Exception e1){}
					if (billDto.getItemId().equals(batchDTO.getItemDTO().getItemId())) {
						list.remove(i);
						break;
					}
				}
			}
		}
		return list;
	}


	
}
