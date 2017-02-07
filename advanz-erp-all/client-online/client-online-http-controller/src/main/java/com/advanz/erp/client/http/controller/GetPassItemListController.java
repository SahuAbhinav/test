package com.advanz.erp.client.http.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.msg.BatchInputMessage;
import com.advanz.erp.masters.model.msg.BatchOutMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.service.business.IBatchService;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IStockLedgerService;

@Controller
public class GetPassItemListController {

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
	
	
	

	

	@RequestMapping(value = "/get_pass_item_detail_by_id")
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

 }
