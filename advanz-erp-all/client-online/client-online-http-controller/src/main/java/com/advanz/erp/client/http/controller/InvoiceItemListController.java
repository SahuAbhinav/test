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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.advanz.erp.client.http.controller.form.InvoiceForm;
import com.advanz.erp.client.http.controller.form.ItemForm;
import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.ProformaDetailDTO;
import com.advanz.erp.masters.model.StockLedgerDTO;
import com.advanz.erp.masters.model.msg.BatchInputMessage;
import com.advanz.erp.masters.model.msg.BatchOutMessage;
import com.advanz.erp.masters.model.msg.BranchInputMessage;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
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
public class InvoiceItemListController {

	@Autowired
	public IItemService itemService;

	@Autowired
	public IMastersService mastersService;

	@Autowired
	public IBatchService batchService;

	@Autowired
	public IStockLedgerService stockLedgerService;

	@Autowired
	public IBranchService branchService;
	
	
	@RequestMapping(value = "/showItemList", method = RequestMethod.GET)
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
		try{
		InvoiceForm invoiceForm = (InvoiceForm) session.getAttribute("invoice");
		List<ProformaDetailDTO> pl= invoiceForm.getProformaDetailList();
		//for cheking purpose
		Iterator it = pl.iterator();
		while (it.hasNext()) {
			ProformaDetailDTO proformaDetailDTO = (ProformaDetailDTO) it.next();
			System.out.println("proformaDetailDTO2:-"+proformaDetailDTO);
			System.out.println("Booked by2:"+proformaDetailDTO.getBookedBy());
			
		}
		ProformaDetailDTO detailDTO=null;
		//set Item group category
		dto.setItemGroupFlagId(invoiceForm.getProformaMasterDTO().getItemGroupFlagId());
		if(pl!=null && pl.size()>0){
			 detailDTO=pl.get(0);
			 dto.setExcisePerc(detailDTO.getExcisePerc());
		}}catch (Exception e) {}
		
		
		
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
		
		
		ArrayList<ItemDTO> finishGoodItem = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
		// ArrayList<ItemDTO> list = getItemList(session);
		
		ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();

		for (int i = 0; i < list.size(); i++) {
			ItemDTO itemDTO = list.get(i);
			StockLedgerInputMessage stockLedgerInputMessage = new StockLedgerInputMessage();
			StockLedgerDTO dto1 = new StockLedgerDTO();
			dto1.setItemId(itemDTO.getItemId());
			stockLedgerInputMessage.setStockLedgerDTO(dto1);
			StockLedgerOutMessage ledgerOutMessage = stockLedgerService.countStockByItemId(stockLedgerInputMessage);
			itemDTO.setStockTotal(ledgerOutMessage.getStockCount());
		}
		
		dto.setNext(next);
		dto.setPrevious(next-26);
		itemForm.setItemDTO(dto);
		itemForm.setItemList(list);

		mav.setViewName("item_invoice_list");
		mav.addObject("itemForm", itemForm);
		return mav;
	}

	private ArrayList<ItemDTO> getItemList(HttpSession session) {
		// ItemOutMessage itemOutMessage = itemService.findAllItem();
		ItemOutMessage itemOutMessage = itemService.finishGoodItemList();
		ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage
				.getItemDTOList();

		for (int i = 0; i < list.size(); i++) {
			ItemDTO dto = new ItemDTO();
			dto = list.get(i);

			// Get Master Name
			MastersDTO mastersDTO = dto.getMasterPack();
			if (mastersDTO != null) {
				dto.setMasterName(mastersDTO.getName());
			}

		}

		int size = list.size();
		// itemForm.setItemList(list);
		Iterator<ItemDTO> e = null;
		ItemDTO itemDto = null;

		InvoiceForm invoiceForm = (InvoiceForm) session.getAttribute("invoice");
		
		if (invoiceForm != null) {
			for (ProformaDetailDTO billDto : invoiceForm.getProformaDetailList()) {
				System.out.println("billDto " + billDto.getItemId());
				for (int i = 0; i < size; i++) {
					itemDto = list.get(i);
					System.out.println("Item ID " + itemDto.getItemId());
					
					if (billDto.getItemId().equals(itemDto.getItemId())) {
						
						list.remove(i);
						break;
					}
				}
				}
				}
				return list;
			    }

	@RequestMapping(value = "/submitItemList", method = RequestMethod.GET)
	public ModelAndView submit(@ModelAttribute("itemForm") ItemForm itemForm,
			@RequestParam String operation, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Integer itemId = itemForm.getItemId();
		ItemInputMessage itemInputMessage = null;
		InvoiceForm invoiceForm = (InvoiceForm) session.getAttribute("invoice");
		
		if (operation.equals("Cancel")) {

			ModelAndView mv = new ModelAndView(new RedirectView(
					"show_proforma?operation=show"));
			return mv;

		}

		if ("GetItem".equals(operation)) {
			itemInputMessage = new ItemInputMessage();
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setItemId(itemId);

			// itemDTO.setItemCode(itemCode);

			itemInputMessage.setItemDTO(itemDTO);
			ItemOutMessage itemOutMessage = itemService.findItemById(itemInputMessage);

			ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage
					.getItemDTOList();
			if (list.size() == 1) {
				itemForm = new ItemForm();
				itemForm.setItemDTO(list.get(0));
			}

		} else if ("Search".equals(operation)) {

			itemInputMessage = new ItemInputMessage();
			ItemDTO itemDTO = new ItemDTO();

            try {
			itemDTO.setExcisePerc(itemForm.getItemDTO().getExcisePerc());
			} catch (Exception e) {
			}
			
            itemDTO.setItemCode(itemForm.getItemDTO().getItemCode());
			itemDTO.setItemName(itemForm.getItemDTO().getInvoiceName());
			itemInputMessage.setItemDTO(itemDTO);
			ItemOutMessage itemOutMessage = itemService.findItem(itemInputMessage);
			
			
	/*		if (itemForm.getItemDTO().getItemCode() == null
					|| itemForm.getItemDTO().getItemCode().length() == 0 || itemForm.getItemDTO().getInvoiceName()==null ) {
				ArrayList<ItemDTO> list = getItemList(session);
				
				System.out.println(" INSIDE IF CONDITION DDDDDDDDDDD " + list.size());
				itemForm.setItemList(list);
			}
		
			else {*/
				ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
				
				itemForm.setItemList(list);
			//}
			
			mav.setViewName("item_invoice_list");
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

				// get Branch Information
				BranchDTO branchDTO = new BranchDTO();
				try{
				branchDTO.setBranchId(invoiceForm.getProformaMasterDTO().getBranchDTO().getBranchId());
				}catch (Exception e) {
				}
			BranchInputMessage branchInputMessage = new BranchInputMessage();
			branchInputMessage.setBranchDTO(branchDTO);
			BranchOutMessage branchOutMessage=	branchService.findBranchById(branchInputMessage);
			ArrayList<BranchDTO> branchList = (ArrayList<BranchDTO>)branchOutMessage.getBranchDTOList();
			if(branchList!=null && branchList.size()>0){
			   branchDTO =branchList.get(0);
			   invoiceForm.setDutyVideNotification(branchDTO.getDutyVideNotification());
			  
			   }
			
				
				ProformaDetailDTO billDetailDTO = new ProformaDetailDTO();
				billDetailDTO.setSalesRate(dto.getSalesRate());
				if(dto.getExciseTypeFlag().equalsIgnoreCase("Excisable")){
				 billDetailDTO.setExcisePerc(dto.getExcisePerc());
				}
				billDetailDTO.setItemName(dto.getItemName());
				billDetailDTO.setItemId(dto.getItemId());
				billDetailDTO.setQuantity(1.0);
				try {
					
				/*if (invoiceForm.getBillDTO().getSalesType().equals("Sales with In State")) {
					billDetailDTO.setTax(dto.getVatPerc());
				}
				if (invoiceForm.getBillDTO().getSalesType().equals("Sales Outside State")) {
					billDetailDTO.setTax(dto.getCstPerc());
				}*/
				if (invoiceForm.getProformaMasterDTO().getVatCstTaxType().equalsIgnoreCase("vat")) {
					billDetailDTO.setTax(dto.getVatPerc());
				}if (invoiceForm.getProformaMasterDTO().getVatCstTaxType().equalsIgnoreCase("cst with c form")) {
					billDetailDTO.setTax(dto.getCstPerc());
				}if (invoiceForm.getProformaMasterDTO().getVatCstTaxType().equalsIgnoreCase("cst w/o c form")) {
					billDetailDTO.setTax(dto.getVatPerc());
				}
				
				
				
				  billDetailDTO.setInvoiceNumber(invoiceForm.getProformaMasterDTO().getInvoiceNumber());
				} catch (Exception e) {
				}

				MastersDTO mastersDTO = dto.getMasterUnit();
				if (mastersDTO != null) {
					billDetailDTO.setUmoName(mastersDTO.getName());
					billDetailDTO.setMeasurementUnitId(mastersDTO
							.getMastersId());
				}
				
				
				//billDetailDTO.setDiscountPer(dto.getDiscountPer());
				
				billDetailDTO.setDiscountAmountPerToShow(dto.getDiscountPer());
				
				
				List billDetailList = invoiceForm.getProformaDetailList();
				
				//for cheking purpose
				Iterator it = billDetailList.iterator();
				while (it.hasNext()) {
					ProformaDetailDTO proformaDetailDTO = (ProformaDetailDTO) it.next();
					System.out.println("proformaDetailDTO ok:-"+proformaDetailDTO);
					System.out.println("Booked by: ok"+proformaDetailDTO.getBookedBy());
					
				}
				if (billDetailList == null) {
					billDetailList = new ArrayList<ProformaDetailDTO>();
					invoiceForm.setProformaDetailList(billDetailList);
				}

				// billDetailList.add(billDetailDTO);
				// invoiceForm.setBillDetailList(billDetailList);

				// Check Item In Batch
				BatchInputMessage batchInputMessage = new BatchInputMessage();
				BatchDTO batchDTO = new BatchDTO();
				batchInputMessage.setItemId(itemForm.getItemDTO().getItemId());

				BatchOutMessage batchOutMessage = batchService.findAllBatchByItemId(batchInputMessage);
				ArrayList<BatchDTO> batchList = (ArrayList<BatchDTO>) batchOutMessage.getBatchDTOList();

				if (batchList != null && batchList.size() > 0) {
					batchDTO = batchList.get(0);
				}
				if (batchDTO.getItemDTO().getItemId() != null) {
					ModelAndView mv = new ModelAndView(new RedirectView(
							"submitInvoiceItemBatchList?operation=show&itemId="
									+ itemForm.getItemDTO().getItemId()));
					session.setAttribute("itemId", itemForm.getItemDTO().getItemId());
					return mv;
				} else {

					billDetailList.add(billDetailDTO);
					invoiceForm.setProformaDetailList(billDetailList);

					ModelAndView mv = new ModelAndView(new RedirectView(
							"show_proforma?operation=show"));
					return mv;
				}

				//

				/*
				 * ModelAndView mv = new ModelAndView(new RedirectView(
				 * "show_proforma?operation=show")); return mv;
				 */

			} else {

				ModelAndView mv = new ModelAndView(new RedirectView(
						"show_proforma?operation=show"));
				return mv;
			}

		}

		ItemOutMessage itemOutMessage = itemService.findAllItem();
		ArrayList<ItemDTO> list = getItemList(session);

		itemForm.setItemList(list);
		mav.setViewName("item_invoice_list");
		// mav.addObject("itemList",list);
		mav.addObject("itemForm", itemForm);
		return mav;
	    }

	@RequestMapping(value = "/get_item_detail_by_id")
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
			ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();

			if (list.size() == 1) {
				itemDTO = list.get(0);
			}

			// Check Item In Batch
			BatchInputMessage batchInputMessage = new BatchInputMessage();
			BatchDTO batchDTO = new BatchDTO();
			batchInputMessage.setItemId(itemId);

			BatchOutMessage batchOutMessage = batchService.findAllBatchByItemId(batchInputMessage);
			ArrayList<BatchDTO> batchList = (ArrayList<BatchDTO>) batchOutMessage.getBatchDTOList();

			if (batchList != null && batchList.size() > 0) {
				batchDTO = batchList.get(0);
			}

			
			// if (batchDTO != null) {
			if (batchDTO.getItemDTO().getItemId() != null) {

				itemDTO.setBatchFlag(1);
			} else {
				itemDTO.setBatchFlag(0);
			}

		}
		return itemDTO;
	}
	
	
	public ArrayList<ItemDTO> itemCheck(HttpSession session){
		
		
		
	 return null;
	}
	

	protected boolean checkDuplicate(List list, Integer id) {
		boolean b = true;
		ItemDTO dto = new ItemDTO();
		for (int i = 0; i < list.size(); i++) {
			dto = (ItemDTO) list.get(i);
			if (dto.getItemId().equals(id)) {
				b = false;
			}
		}
		return b;
	}

 }
