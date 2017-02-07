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

import com.advanz.erp.client.http.controller.form.GetPassForm;
import com.advanz.erp.client.http.controller.form.InvoiceForm;
import com.advanz.erp.client.http.controller.form.ItemForm;
import com.advanz.erp.client.http.controller.form.ReturnGetPassForm;
import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.GetPassDetailDTO;
import com.advanz.erp.masters.model.GetPassMasterDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.ProformaDetailDTO;
import com.advanz.erp.masters.model.ReturnGetPassDetailDTO;
import com.advanz.erp.masters.model.ReturnGetPassMasterDTO;
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
public class ReturnGetPassItemListController {

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
	
	
	@RequestMapping(value = "/showItemListForReturnGetPass", method = RequestMethod.GET)
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

		mav.setViewName("return_get_pass_item_list");
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

	@RequestMapping(value = "/submitItemListForReturnGetPass", method = RequestMethod.GET)
	public ModelAndView submit(@ModelAttribute("itemForm") ItemForm itemForm,
			@RequestParam String operation, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Integer itemId = itemForm.getItemId();
		ItemInputMessage itemInputMessage = null;
		ReturnGetPassForm returnGetPassForm = (ReturnGetPassForm) session.getAttribute("returnGetPass");
		
		if (operation.equals("Cancel")) {
			ModelAndView mv = new ModelAndView(new RedirectView(
					"show_return_get_pass?operation=show"));
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
			ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
			itemForm.setItemList(list);
			mav.setViewName("return_get_pass_item_list");
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
				ReturnGetPassMasterDTO getPassMasterDTO= returnGetPassForm.getReturnGetPassMasterDTO();
				List<ReturnGetPassDetailDTO> detailList =null;
if(getPassMasterDTO.getReturnGetPassDetailDTOList()!=null){
	 detailList=(List<ReturnGetPassDetailDTO>)getPassMasterDTO.getReturnGetPassDetailDTOList();
}else{
	detailList = new ArrayList<ReturnGetPassDetailDTO>();
}
				//List<GetPassDetailDTO> detailList=new ArrayList<GetPassDetailDTO>();
ReturnGetPassDetailDTO detailDTO = new ReturnGetPassDetailDTO();
			   detailDTO.setItemDTO(dto);
			   detailDTO.setItemName(dto.getItemName());
				MastersDTO mastersDTO = dto.getMasterUnit();
				
				
				if (mastersDTO != null) {
					detailDTO.setMeasurementUnitName(mastersDTO.getName());
					detailDTO.setMeasurementUnitId(mastersDTO.getMastersId());
				}
			detailList.add(detailDTO);
			getPassMasterDTO.setReturnGetPassDetailDTOList(detailList);
			returnGetPassForm.getReturnGetPassMasterDTO().setReturnGetPassDetailDTOList(detailList);
			session.setAttribute("returnGetPass", returnGetPassForm);
			ModelAndView mv = new ModelAndView(new RedirectView("show_return_get_pass?operation=show"));
			mv.addObject("returnGetPass", returnGetPassForm);
			return mv;
		}
		}
		return mav;
	    }

	

 }
