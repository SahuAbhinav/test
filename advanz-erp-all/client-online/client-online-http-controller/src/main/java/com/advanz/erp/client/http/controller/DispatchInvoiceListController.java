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

import com.advanz.erp.client.http.controller.form.DispatchForm;
import com.advanz.erp.client.http.controller.form.InvoiceForm;
import com.advanz.erp.masters.model.BillDTO;
import com.advanz.erp.masters.model.BillDetailDTO;
import com.advanz.erp.masters.model.DispatchDetailDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.msg.BillDetailInputMessage;
import com.advanz.erp.masters.model.msg.BillDetailOutMessage;
import com.advanz.erp.masters.model.msg.BillInputMessage;
import com.advanz.erp.masters.model.msg.BillOutMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.service.business.IBillDetailService;
import com.advanz.erp.masters.service.business.IBillService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;

@Controller
public class DispatchInvoiceListController {
	@Autowired
	public IBillService billService;

	@Autowired
	public IBillDetailService billdeDetailService;

	@Autowired
	public IItemService itemService;

	@Autowired
	public IMastersService mastersService;

	@RequestMapping(value = "/show_dispatch_invoice_list", method = RequestMethod.GET)
	public ModelAndView display(
			@ModelAttribute("invoice") InvoiceForm invoiceForm,
			HttpSession session) {

		session.removeAttribute("invoice");
		List<BillDTO> list = new ArrayList<BillDTO>();
		if (invoiceForm == null) {
			invoiceForm = new InvoiceForm();
		}
		BillOutMessage billOutMessage = new BillOutMessage();
		BillInputMessage billInputMessage = new BillInputMessage();
		
		billOutMessage = billService.findAllBills();
		
		
		try {
			
	//list = (ArrayList<BillDTO>) billOutMessage.getBillDTOList();
			
			list=getInvoiceList( session);
			
			
			// For Count total Item
			for (int i = 0; i < list.size(); i++) {
				BillDTO billDTO = (BillDTO) list.get(i);
				BillDetailInputMessage billDetailInputMessage = new BillDetailInputMessage();
				BillDetailDTO billDetailDTO = new BillDetailDTO();
				billDetailDTO.setInvoiceNumber(billDTO.getInvoiceNumber());
				billDetailInputMessage.setBillDetailDTO(billDetailDTO);
				BillDetailOutMessage billDetailOutMessage = billdeDetailService.findBillByBillId(billDetailInputMessage);

				ArrayList<BillDetailDTO> billDetailList = (ArrayList<BillDetailDTO>) billDetailOutMessage.getBillDetailDTOList();
				Integer totalItem = billDetailList.size();
				billDTO.setTotalItem(totalItem);
			}
			//
			} catch (Exception ex) {
			  ex.printStackTrace();
			}
		ModelAndView mav = new ModelAndView("dispatch_invoice_list");
		// mav.addObject("command", new InvoiceDTO());
		mav.addObject("invoiceForm", invoiceForm);
		mav.addObject("invoiceList", list);
		// logger.info("Invoice List : "+list);
		return mav;
	}

	@RequestMapping(value = "/submitDispatchInvoiceList", method = RequestMethod.GET)
	public ModelAndView submit(
			@ModelAttribute("invoice") InvoiceForm invoiceForm,
			@RequestParam String operation, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		if (operation.equals("Delete")) {
			BillDTO dto = new BillDTO();
			dto.setInvoiceAutoId(invoiceForm.getInvoiceAutoId());
			BillInputMessage billInputMessage = new BillInputMessage();
			billInputMessage.setBillDTO(dto);
			billService.deleteBill(billInputMessage);
			BillOutMessage billOutMessage = billService.findAllBills();
			ArrayList<BillDTO> billList = (ArrayList<BillDTO>) billOutMessage
					.getBillDTOList();

			mav.setViewName("dispatch_invoice_list");
			mav.addObject("invoiceForm", invoiceForm);
			mav.addObject("invoiceList", billList);
			return mav;
		}

		if (operation.equals("GetInvoice")) {
			BillDetailInputMessage billDetailInputMessage = new BillDetailInputMessage();
			BillDetailDTO billDetailDTO = new BillDetailDTO();
			billDetailDTO.setInvoiceNumber(invoiceForm.getInvoiceNumber());
			billDetailInputMessage.setBillDetailDTO(billDetailDTO);

			// if(session.getAttribute("dispatch")!=null){
			DispatchForm dispatchForm = (DispatchForm) session
					.getAttribute("dispatch");
			DispatchDetailDTO detailDTO = new DispatchDetailDTO();
			try {
				ArrayList<DispatchDetailDTO> l = (ArrayList<DispatchDetailDTO>) dispatchForm
						.getDispatchDetailList();
				if (l == null) {
					l = new ArrayList<DispatchDetailDTO>();
					dispatchForm.setDispatchDetailList(l);
				}
				// detailDTO.setInvoiceNumber(invoiceForm.getInvoiceNumber());

				// To Get Item Information
				BillDetailOutMessage detailOutMessage = billdeDetailService
						.findBillByBillId(billDetailInputMessage);
				ArrayList<BillDetailDTO> billDetailList = (ArrayList<BillDetailDTO>) detailOutMessage
						.getBillDetailDTOList();

				for (int i = 0; i < billDetailList.size(); i++) {
					detailDTO = new DispatchDetailDTO();
					billDetailDTO = billDetailList.get(i);
					Integer itemId = billDetailDTO.getItemId();
					ItemDTO itemDTO = new ItemDTO();
					itemDTO.setItemId(itemId);
					ItemInputMessage inputMessage = new ItemInputMessage();
					inputMessage.setItemDTO(itemDTO);
					ItemOutMessage itemOutMessage = itemService
							.findItemById(inputMessage);
					ArrayList<ItemDTO> itemList = (ArrayList<ItemDTO>) itemOutMessage
							.getItemDTOList();
					itemDTO = itemList.get(0);
					String itemName = itemDTO.getItemName();

					try {

						MastersDTO mastersDTO = itemDTO.getMasterUnit();
						if (mastersDTO != null) {
							detailDTO.setUmoName(mastersDTO.getName());
						}
						/*
						 * MastersInputMessage mastersInputMessage = new
						 * MastersInputMessage(); MastersDTO mastersDTO = new
						 * MastersDTO();
						 * mastersDTO.setMastersId(itemDTO.getMasterUnit());
						 * mastersInputMessage.setMastersDTO(mastersDTO);
						 * MastersOutputMessage mastersOutputMessage=
						 * mastersService.findMastersById(mastersInputMessage);
						 * ArrayList<MastersDTO> masterList
						 * =(ArrayList<MastersDTO>)
						 * mastersOutputMessage.getMastersDTOList();
						 * System.out.println("LIST  SSSS" + masterList.size());
						 * if(masterList!=null && masterList.size()>0){
						 * mastersDTO=masterList.get(0); }
						 * billDetailDTO.setUmoName(mastersDTO.getName());
						 * detailDTO.setUmoName(mastersDTO.getName());
						 */
					} catch (Exception e) {
					}
					detailDTO.setItemName(itemName);
					detailDTO.setQuantity(billDetailDTO.getQuantity());
					detailDTO.setInvoiceNumber(invoiceForm.getInvoiceNumber());
					detailDTO.setItemId(itemDTO.getItemId());
					detailDTO.setBatchNo(billDetailDTO.getBatchNo());
					detailDTO.setNoOfPacket(billDetailDTO.getNoOfPacket());
					detailDTO.setQtyPerPacket(billDetailDTO.getQtyPerPacket());
					
					l.add(detailDTO);

				}

				// detailDTO.setInvoiceNumber(invoiceForm.getInvoiceNumber());
				// l.add(detailDTO);

				dispatchForm.setDispatchDetailList(l);
				
			//	session.setAttribute("dispatch",dispatchForm);
				
				

			} catch (Exception e) {

			}
			if (operation.equals("Cancel")) {

				mav = new ModelAndView(new RedirectView(
						"show_dispatch?operation=show"));
				mav.addObject("dispatch", dispatchForm);
				return mav;
			}

			mav = new ModelAndView(new RedirectView(
					"show_dispatch?operation=show"));
			mav.addObject("dispatch", dispatchForm);
			return mav;
			// }
		}
		if (operation.equals("Search")) {
			System.out.println("Sale Order List Controller Search Method :");
			BillDTO dto = invoiceForm.getBillDTO();
			BillInputMessage billInputMessage = new BillInputMessage();
			billInputMessage.setBillDTO(dto);
			BillOutMessage outputMessage = billService
					.searchInvoice(billInputMessage);
			ArrayList<BillDTO> list = (ArrayList<BillDTO>) outputMessage
					.getBillDTOList();

			// For Count total Item
			for (int i = 0; i < list.size(); i++) {
				BillDTO billDTO = (BillDTO) list.get(i);
				BillDetailInputMessage billDetailInputMessage = new BillDetailInputMessage();
				BillDetailDTO billDetailDTO = new BillDetailDTO();
				billDetailDTO.setInvoiceNumber(billDTO.getInvoiceNumber());
				billDetailInputMessage.setBillDetailDTO(billDetailDTO);
				BillDetailOutMessage billDetailOutMessage = billdeDetailService
						.findBillByBillId(billDetailInputMessage);

				ArrayList<BillDetailDTO> billDetailList = (ArrayList<BillDetailDTO>) billDetailOutMessage
						.getBillDetailDTOList();
				Integer totalItem = billDetailList.size();
			
				billDTO.setTotalItem(totalItem);

			}
			//

			mav.setViewName("dispatch_invoice_list");
			mav.addObject("invoiceForm", invoiceForm);
			mav.addObject("invoiceList", list);
			return mav;
		}
		/*
		 * if (operation.equals("Add")) { ModelAndView mv = new ModelAndView(new
		 * RedirectView( "show_invoice?operation=Show"));
		 * System.out.println("INSIDE ADD OPERATION :"); return mv; }
		 */
		/*
		 * ModelAndView mv = new ModelAndView(new RedirectView(
		 * "show_invoice?operation=Show"));
		 */

		ModelAndView mv = new ModelAndView(new RedirectView(
				"show_dispatch?operation=Show"));

		return mv;

	}

	private ArrayList<BillDTO> getInvoiceList(HttpSession session) {
	
		// ItemOutMessage itemOutMessage = itemService.findAllItem();
		BillOutMessage billOutMessage = new BillOutMessage();
		BillInputMessage billInputMessage = new BillInputMessage();
		//billOutMessage = billService.findAllBills();
		
		

		Integer partyId=(Integer)session.getAttribute("partyId");
		BillDTO partyBillDTO = new BillDTO();
		partyBillDTO.setPartyId(partyId);
		billInputMessage.setBillDTO(partyBillDTO);
		billOutMessage = billService.findBillByEmployeeId(billInputMessage);
		
		
		
		ArrayList<BillDTO> list = (ArrayList<BillDTO>) billOutMessage.getBillDTOList();
		
		
		for (int i = 0; i < list.size(); i++) {
			BillDTO dto = new BillDTO();
			dto = list.get(i);
		}
		
		
		
		int size = list.size();
		// itemForm.setItemList(list);
		Iterator<BillDTO> e = null;
		BillDTO billDTO = new BillDTO();
		DispatchForm dispatchForm = (DispatchForm) session.getAttribute("dispatch");
	
		if (dispatchForm != null) {
			for (DispatchDetailDTO dispatchDetailDTO : dispatchForm.getDispatchDetailList()) {
				for (int i = 0; i < list.size(); i++) {
					billDTO = list.get(i);
					if (dispatchDetailDTO.getInvoiceNumber().equals(billDTO.getInvoiceNumber())) {
						list.remove(i);
						break;
					}
				}
			}
		}
		return list;
	   }

}