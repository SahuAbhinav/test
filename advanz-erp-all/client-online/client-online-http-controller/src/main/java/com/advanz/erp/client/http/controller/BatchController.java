package com.advanz.erp.client.http.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.BatchForm;
import com.advanz.erp.client.http.controller.form.InvoiceForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.ProformaDetailDTO;
import com.advanz.erp.masters.model.criteria.BatchSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.BatchInputMessage;
import com.advanz.erp.masters.model.msg.BatchOutMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.StockLedgerInputMessage;
import com.advanz.erp.masters.model.msg.StockLedgerOutMessage;
import com.advanz.erp.masters.service.business.IBatchService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IStockLedgerService;

@Controller
@SessionAttributes({"items"})
public class BatchController extends BaseController{

	private static final Logger logger = LoggerFactory
			.getLogger(BatchController.class);

	@Autowired
	public IBatchService batchService;
	@Autowired
	public IItemService itemService;
	@Autowired
	public IMastersService mastersService;
	@Autowired
	public IStockLedgerService stockLedgerService;


	@RequestMapping(value = "/get_batch_list")
	public  ModelAndView searchBatch(@ModelAttribute("batchSearchCriteria") BatchSearchCriteriaDTO searchCriteria,@ModelAttribute("batchForm") BatchForm batchForm,Model model,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
		List<BatchDTO> list = new ArrayList<BatchDTO>();
		
			BatchOutMessage batchOutMessage = null;
			BatchInputMessage batchInputMessage = new BatchInputMessage();
			batchInputMessage.setBatchSearchCritecia(searchCriteria);
			if(searchCriteria!=null){
			batchOutMessage = batchService.searchBatch(batchInputMessage);
			}else{
				batchOutMessage = batchService.findAllBatches();
			}
			list = (ArrayList<BatchDTO>) batchOutMessage.getBatchDTOList();	
			
			
			ModelAndView mav=new ModelAndView("batch-list");
			String succ="Blk";
			if(list.equals(null) || list.size()==0)
			{
			  model.addAttribute("succ", succ);
			}
			mav.addObject("batchForm", batchForm);
			mav.addObject("batchList", list);
			mav.addObject("batchSearchCriteria",searchCriteria);
		return mav;
	}

	@RequestMapping(value = "/get_batch", method = RequestMethod.GET)
	public 	ModelAndView getBatchData(@RequestParam(value="batch_no",required=false) String batchNo,@RequestParam(value= "sno",required=false) Integer sno,@RequestParam("opr")String opr) {
		logger.info("Get Batch : " + batchNo);
		logger.info("Opr : " + opr);
		BatchForm batchForm=null;
		BatchOutMessage batchOutMessage = null;
		List<ItemDTO> ilist=new ArrayList<ItemDTO>();
		List<ItemDTO> selectItemList=new ArrayList<ItemDTO>();
		if (StringUtils.hasText(batchNo) || sno!=null) {
			BatchInputMessage batchInputMessage = new BatchInputMessage();
			BatchDTO batchDTO = new BatchDTO();
			batchDTO.setBatchNo(batchNo);
			batchDTO.setSno(sno);
			batchInputMessage.setBatchDTO(batchDTO);
			
			if("R".equals(opr)){
				logger.info("RRRRRRRRRRRRRRR");
			
				batchOutMessage = batchService.checkBeforeRemove(batchInputMessage);
				if(batchOutMessage!=null){
				ErrorListDTO errorListDTO=batchOutMessage.getErrorListDTO();
				ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
				if (errorListDTO != null && errorListDTO.hasErrors()) {		
				    ModelAndView mav=new ModelAndView("forward:get_batch_list");
					mav.addObject("errors", errorDTO);					
					return mav;
				}
				}
			}

			batchOutMessage = batchService.findBatchById(batchInputMessage);
			ArrayList<BatchDTO> list = (ArrayList<BatchDTO>) batchOutMessage.getBatchDTOList();
			
			batchForm = new BatchForm();
			batchDTO=list.get(0);
			
			 ItemOutMessage itemOutMessage = null;
			if (list!=null && list.size()>0) {
				for(int i=0;i<list.size();i++){
					BatchDTO batchDTO2=new BatchDTO();
					 batchDTO2 =	list.get(i);
					 ItemDTO itemDTO = new ItemDTO();
					 
					 Integer itemId= batchDTO2.getItemDTO().getItemId();
					 itemDTO.setItemId(itemId);
					  
					   ItemInputMessage itemInputMessage = new ItemInputMessage();
					   itemDTO.setItemId(itemId);
					   itemInputMessage.setItemDTO(itemDTO);
					   itemOutMessage = itemService.findItemById(itemInputMessage);
					   List<ItemDTO> itemList=	itemOutMessage.getItemDTOList();
					   itemDTO = itemList.get(0);
					   selectItemList.add(itemDTO);
				}
				
				batchDTO.setItemDTOList(selectItemList);
				setMasterPackDTO(batchDTO);
				batchForm.setBatchDTO(batchDTO);
				
				itemOutMessage=itemService.findItemsForBatch();
				ilist=(List<ItemDTO>)itemOutMessage.getItemDTOList();
				
			}
		}

		ModelAndView mav = new ModelAndView("batch-edit");
		mav.addObject("batchForm", batchForm);
		mav.addObject("selectItemList",selectItemList);
		mav.addObject("items",ilist);
		mav.addObject("opr",opr);
		logger.info(batchForm.getBatchDTO().toString());
		return mav;
	}
	

	private ArrayList<ItemDTO> getItemList(List<ItemDTO> itemList) {
		// ItemOutMessage itemOutMessage = itemService.findAllItem();
		
		ItemOutMessage itemOutMessage=itemService.findItemsForBatch();
		ArrayList<ItemDTO>	list=(ArrayList<ItemDTO>)itemOutMessage.getItemDTOList();


		int size = list.size();
		// itemForm.setItemList(list);
		Iterator<ItemDTO> e = null;
		ItemDTO itemDto = null;

		
		if (itemList != null) {
			for (ItemDTO billDto : itemList) {
				
				for (int i = 0; i < size; i++) {
					itemDto = list.get(i);
					
					if (billDto.getItemId().equals(itemDto.getItemId())) {
						
						list.remove(i);
						break;
					}
				}}}
		
				return list;
			    }


	@RequestMapping(value = "/show_new_batch_form", method = RequestMethod.GET)
	public ModelAndView showNewBatchForm(
			@ModelAttribute("batchForm") BatchForm batchForm,
			@ModelAttribute("itemId") String paramItemId) {
		logger.info("show_new_batch_form");
		if(batchForm==null)
			batchForm = new BatchForm();
		
		ModelAndView mav = new ModelAndView("batch-detail");
		Integer itemId = 0;
		List<ItemDTO> iList=null;
		BatchOutMessage batchOutMessage = null;
		if (StringUtils.hasText(paramItemId)) {
			itemId = NumberUtils.parseNumber(paramItemId, Integer.class);
		} else {
			 iList=itemList();
			if(iList!=null && iList.size()>0){
				itemId = iList.get(0).getItemId();
				mav.addObject("items", iList);
			}
		}
		BatchInputMessage batchInputMessage = new BatchInputMessage();
		batchInputMessage.setItemId(itemId);
		batchOutMessage = batchService.findBatchByBatchItemNo(batchInputMessage);
		ArrayList<BatchDTO> list = (ArrayList<BatchDTO>) batchOutMessage.getBatchDTOList();
	
		if (list!=null && list.size() != 0) {	
			BatchDTO batchDTO=
			list.get(0);
			batchDTO.setOpeningStock(0.0);
			batchDTO.setClosingStock(0.0);
			batchForm.setBatchDTO(list.get(0));
			setMasterPackDTO(batchDTO);
			batchDTO.setItemDTOList(iList);
			batchForm.setBatchDTO(batchDTO);
			logger.info(batchForm.getBatchDTO().toString());
		}
		mav.addObject("batchForm", batchForm);
		return mav;
	}
	

	@RequestMapping(value = "/get_batch_by_item", method = RequestMethod.GET)
	public ModelAndView getBatchByItem(@ModelAttribute("batchForm") BatchForm batchForm,
			@ModelAttribute("itemId") String paramItemId) {
		logger.info("get_batch_by_item");
		logger.info("Get Batch for Item  : " + paramItemId);
		ModelAndView mav = new ModelAndView("batch-detail");

		BatchOutMessage batchOutMessage = null;
		Integer itemId = null;
		if (StringUtils.hasText(paramItemId)) {
			itemId = NumberUtils.parseNumber(paramItemId, Integer.class);
		}
		if (itemId != null) {
			BatchInputMessage batchInputMessage = new BatchInputMessage();
			batchInputMessage.setItemId(itemId);
			batchOutMessage = batchService.findBatchByBatchItemNo(batchInputMessage);
			ArrayList<BatchDTO> list = (ArrayList<BatchDTO>) batchOutMessage.getBatchDTOList();
			logger.info("---*********---------list=" + list);
			if (list.size() != 0) {
				batchForm = new BatchForm();
				BatchDTO batchDTO=list.get(0);
				setMasterPackDTO(batchDTO);
				batchForm.setBatchDTO(batchDTO);
				logger.info(batchForm.getBatchDTO().toString());
			}
		}
		if (batchForm == null) {
			batchForm = new BatchForm();
		}
		mav.addObject("batchForm", batchForm);
		return mav;
	}

//	@ModelAttribute("items")
	public List<ItemDTO> itemList() {	
		logger.info("************************Item List");
		ItemOutMessage itemOutMessage=itemService.findItemsForBatch();
		if(itemOutMessage!=null){
		List<ItemDTO> items= itemOutMessage.getItemDTOList();	
		return items;
		}
		return null;
	}

	

	@RequestMapping(value = "/save_batch", method = RequestMethod.POST)
	public String saveBatch(@ModelAttribute("batchForm") BatchForm batchForm,BindingResult result,Model model) {
		String succ="";
		logger.info("batchForm = " + batchForm);
		logger.info(batchForm.getBatchDTO().toString());
		
		ArrayList<ItemDTO> itemDTOsList=(ArrayList<ItemDTO>)batchForm.getBatchDTO().getItemDTOList();
	
	
	
		BatchDTO batchDTO=batchForm.getBatchDTO();
		Date mfgDate=batchDTO.getMfgDate();
		Date exDate=batchDTO.getExpiryDate();
		ErrorListDTO errorListDTO = new ErrorListDTO();
		
		if(mfgDate!=null && exDate!=null){
			Date date=new Date();
			if(mfgDate.after(date)){
				ErrorDTO error = new ErrorDTO();
				error.setErrorMsg("Mfg Date should not be greater than current date");
				errorListDTO.addError(error);
			}
			if(exDate.before(mfgDate)){
				ErrorDTO error = new ErrorDTO();
				error.setErrorMsg("Expiry Date should not be less than Mfg Date");
				errorListDTO.addError(error);
			}			
		}
		if (errorListDTO != null && errorListDTO.hasErrors()) {
		   ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
		    
			logger.info(" adding Error ");
			model.addAttribute("errors",errorDTO);
			return "batch-detail";
		}
		
		if(result.hasErrors()){
			logger.info("Error : "+result);
			return "batch-detail";
		}
			
		BatchInputMessage batchInputMessage = new BatchInputMessage();
		BatchOutMessage batchOutMessage=null;
		for(int i=0;i<itemDTOsList.size();i++){
			ItemDTO itemDTO= itemDTOsList.get(i);
		    Integer itemId=itemDTO.getItemClassId();
		   
		   if(itemId!=null && itemId>0){
		   ItemInputMessage itemInputMessage = new ItemInputMessage();
		   itemDTO.setItemId(itemId);
		   itemInputMessage.setItemDTO(itemDTO);
		   ItemOutMessage itemOutMessage = itemService.findItemById(itemInputMessage);
		   List<ItemDTO> itemList=	itemOutMessage.getItemDTOList();
		   itemDTO = itemList.get(0);
		   BatchDTO batchDTO2= batchForm.getBatchDTO();
		   batchDTO2.setInvoiceRate(itemDTO.getSalesRate());
		   batchDTO2.setExcisePerc(itemDTO.getExcisePerc());
		   
		   
		   batchDTO2.setItemDTO(itemDTO);
		   batchDTO2.setTradeRate(itemDTO.getTradeRate());
		   batchDTO2.setVatPerc(itemDTO.getVatPerc());
		   batchDTO2.setMrp(itemDTO.getMrp());
		   batchDTO2.setCstPerc(itemDTO.getCstPerc());
		   batchDTO2.setPurchaseRate(itemDTO.getPurchaseRate());
		   batchDTO2.setDiscountPerc(itemDTO.getDiscountPer());
		   batchDTO2.setNetRate(itemDTO.getNetRate());
		   batchDTO2.setClosingStock(itemDTO.getClosingStock());
		   batchDTO2.setOpeningStock(itemDTO.getOpeningStock());
		   batchDTO2.setCreatedUserId(getCreatedUserId());
		 batchInputMessage.setBatchDTO(batchDTO2);
		 batchOutMessage=batchService.createBatch(batchInputMessage);
		   }
		 
		}
		
		try{
		errorListDTO=batchOutMessage.getErrorListDTO();
		}catch(Exception e){}
		
		if(errorListDTO!=null && errorListDTO.hasErrors()){
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors", errorDTO);
			return "batch-detail";
		}
	
		succ="Ad";
		model.addAttribute("succ", succ);
		return "redirect:/get_batch_list";

	}


	@RequestMapping(value = "/remove_batch", method = RequestMethod.GET)
	public String removeBatch(@RequestParam("batchNo") String batchNo,@RequestParam("sno") Integer sno,@ModelAttribute("batchForm") BatchForm batchForm,Model model) {
		logger.info("Removing..........batchNo = " + batchNo);
		BatchOutMessage batchOutMessage = null;
		if (sno!=null) {
			BatchInputMessage batchInputMessage = new BatchInputMessage();
			BatchDTO batchDTO = new BatchDTO();
			batchDTO.setSno(sno);
			batchInputMessage.setBatchDTO(batchDTO);
			
			batchOutMessage = batchService.findBySno(batchInputMessage);
			List<BatchDTO> batchList=	batchOutMessage.getBatchDTOList();
				if(batchList!=null && batchList.size()>0){
					batchDTO=	batchList.get(0);	
				}
				batchDTO.setModifiedUserId(getCreatedUserId());
				batchInputMessage.setBatchDTO(batchDTO);
			
			batchOutMessage = batchService.deleteBatch(batchInputMessage);
		}
		String succ="Dl";
		model.addAttribute("succ", succ);
		return  "redirect:/get_batch_list";

	}

/*	@RequestMapping(value = "/update_batch", method = RequestMethod.POST)
	public String updateBatch(@ModelAttribute("batchForm") BatchForm batchForm,BindingResult result,Model model,@RequestParam(value="id",required=false) Integer id) {
		
		
		String succ="";
		BatchDTO batchDTO=batchForm.getBatchDTO();
		System.out.println("ID FFFFFFFFF IS :::::::" + id);
		if(batchForm.getId()!=null){
			
		 return "batch-edit";
		}
		
		Date mfgDate=batchDTO.getMfgDate();
		Date exDate=batchDTO.getExpiryDate();
		ErrorListDTO errorListDTO = new ErrorListDTO();
		if(mfgDate!=null && exDate!=null){
			Date date=new Date();
			if(mfgDate.after(date)){
				ErrorDTO error = new ErrorDTO();
				error.setErrorMsg("Mfg Date should not be greater than current date");
				errorListDTO.addError(error);
			}
			if(exDate.before(mfgDate)){
				ErrorDTO error = new ErrorDTO();
				error.setErrorMsg("Expiry Date should not be less than Mfg Date");
				errorListDTO.addError(error);
			}			
		}
		if (errorListDTO != null && errorListDTO.hasErrors()) {
			logger.info(" adding Error ");
			model.addAttribute("errors",errorListDTO);
			model.addAttribute("opr", "E");
			return "batch-edit";
		}
		if(result.hasErrors()){			
			model.addAttribute("opr","E");
			return "batch-edit";
		}
		
		BatchInputMessage batchInputMessage = new BatchInputMessage();
	 
	    ArrayList<ItemDTO> itemDTOsList=(ArrayList<ItemDTO>) batchDTO.getItemDTOList();
	    BatchOutMessage batchOutMessage=null;
	    for(int i=0;i<itemDTOsList.size();i++){
	    	ItemDTO itemDTO=	itemDTOsList.get(i);
	    	itemDTO.setItemId(itemDTO.getItemClassId());
	    	batchDTO.setItemDTO(itemDTO);
	    	batchInputMessage.setBatchDTO(batchDTO);
	 		batchOutMessage=	batchService.updateBatch(batchInputMessage);
	    }
	   
		
		if(batchOutMessage!=null){
		errorListDTO=batchOutMessage.getErrorListDTO();
	
		if(errorListDTO!=null && errorListDTO.hasErrors()){
			logger.info(" adding Error ");
			model.addAttribute("errors", batchOutMessage.getErrorListDTO());
			model.addAttribute("opr","E");
			return "batch-edit";
		}
		}
		 succ="Up";
		 model.addAttribute("succ",succ);
		return "redirect:/get_batch_list";
	}*/
	@RequestMapping(value = "/update_batch", method = RequestMethod.POST)
	public ModelAndView  updateBatch(@ModelAttribute("batchForm") BatchForm batchForm,BindingResult result,Model model,@RequestParam(value="id",required=false) Integer id) {
		ModelAndView mav=new ModelAndView();
		
		String succ="";
		BatchDTO batchDTO=batchForm.getBatchDTO();
		BatchInputMessage batchInputMessage1 = new BatchInputMessage();
		/*if(batchForm.getId()!=null){
			 mav=new ModelAndView();
			 mav.setViewName("batch-edit");
			List<ItemDTO> itemList= batchForm.getBatchDTO().getSelectItemDTOList();
			System.out.println("BEFOR ADD SIZE IS ::::::::::::" + itemList.size());
			
			
			
			ItemDTO dto = new ItemDTO();
			dto.setItemClassId(id);
			itemList.add(dto);
			System.out.println("AFTER ADD ADD SIZE IS ::::::::::::" + itemList.size());
		 mav.addObject("opr","E");
		 batchForm.setId(null);
		 mav.addObject("batchForm", batchForm);
		 mav.addObject("selectItemList",itemList);
		 return mav;
		}
		*/
		
		// Batch Delete
		batchInputMessage1.setBatchDTO(batchDTO);
		BatchOutMessage batchOutMessage1= batchService.findBatchByBatchNo(batchInputMessage1);
	   ArrayList<BatchDTO> batchList=(ArrayList<BatchDTO>)batchOutMessage1.getBatchDTOList();
		if(batchList!=null && batchList.size()>0){
		for(int i=0;i<batchList.size();i++){
			BatchDTO	batchDTO1= batchList.get(i);
			batchDTO1.setModifiedUserId(getCreatedUserId());
			batchInputMessage1.setBatchDTO(batchDTO1);
			batchService.deleteBatch(batchInputMessage1);
		}
		}
		
		Date mfgDate=batchDTO.getMfgDate();
		Date exDate=batchDTO.getExpiryDate();
		ErrorListDTO errorListDTO = new ErrorListDTO();
		if(mfgDate!=null && exDate!=null){
			Date date=new Date();
			if(mfgDate.after(date)){
				ErrorDTO error = new ErrorDTO();
				error.setErrorMsg("Mfg Date should not be greater than current date");
				errorListDTO.addError(error);
			}
			if(exDate.before(mfgDate)){
				ErrorDTO error = new ErrorDTO();
				error.setErrorMsg("Expiry Date should not be less than Mfg Date");
				errorListDTO.addError(error);
			}			
		}
		if (errorListDTO != null && errorListDTO.hasErrors()) {
			mav=new ModelAndView();
			 mav.setViewName("batch-edit");
			 mav.addObject("errors", errorListDTO);	
			 mav.addObject("opr", "E");	
				
		 return mav;
		}
		if(result.hasErrors()){			
			
			mav=new ModelAndView();
			 mav.setViewName("batch-edit");
		
			 mav.addObject("opr", "E");	
				
		 return mav;
		}
		
		
	 
	    //ArrayList<ItemDTO> itemDTOsList=(ArrayList<ItemDTO>) batchDTO.getSelectItemDTOList();
	    ArrayList<ItemDTO> itemDTOsList=(ArrayList<ItemDTO>) batchDTO.getItemDTOList();
	    BatchOutMessage batchOutMessage=null;
	    BatchInputMessage batchInputMessage = new BatchInputMessage();
	    try {
			List<Integer>  integerList=	batchForm.getBatchDTO().getItemIdsList();
			  
			if(integerList.size()>0){
				
				for(int i=0;i<integerList.size();i++){
				Integer itemId=	integerList.get(i);
				ItemDTO itemDTO = new ItemDTO();	
				itemDTO.setItemId(itemId);
				batchDTO.setItemDTO(itemDTO);
				batchDTO.setCreatedUserId(getCreatedUserId());
		    	batchInputMessage.setBatchDTO(batchDTO);
		 		batchOutMessage=	batchService.createBatch(batchInputMessage);
				
				}
				
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	    
	   /* BatchOutMessage batchOutMessage=null;
	    for(int i=0;i<itemDTOsList.size();i++){
	    	ItemDTO itemDTO=	itemDTOsList.get(i);
	    	if(itemDTO.getItemClassId()!=null){
	    	itemDTO.setItemId(itemDTO.getItemClassId());
	    	batchDTO.setItemDTO(itemDTO);
	    	batchInputMessage.setBatchDTO(batchDTO);
	 		batchOutMessage=	batchService.updateBatch(batchInputMessage);
	    	}
	   
	    }*/
	   
		
		if(batchOutMessage!=null){
		errorListDTO=batchOutMessage.getErrorListDTO();
	
		if(errorListDTO!=null && errorListDTO.hasErrors()){
			
			 mav=new ModelAndView();
			 mav.setViewName("batch-edit");
			 mav.addObject("errors",  batchOutMessage.getErrorListDTO());
			 mav.addObject("opr", "E");	
				
		 return mav;
		}
		}
		
	 mav=new ModelAndView("forward:get_batch_list");
							
		return mav;
		 /*succ="Up";
		 model.addAttribute("succ",succ);
		return "redirect:/get_batch_list";*/
	}
	
	
	@RequestMapping("/get_batch_stock")
	public @ResponseBody Double getBatchStock(@RequestParam("batchNo")String batchNo){
		Double q=0.0;
		if(StringUtils.hasText(batchNo)){
		StockLedgerInputMessage stockLedgerInputMessage=new StockLedgerInputMessage();
		//StockLedgerDTO stockLedgerDTO=new StockLedgerDTO();
		//stockLedgerDTO.setItemId(itemId);
	//	stockLedgerInputMessage.setStockLedgerDTO(stockLedgerDTO);
		stockLedgerInputMessage.setBatchNo(batchNo);
		StockLedgerOutMessage stockLedgerOutMessage=stockLedgerService.countStockByBatchNo(stockLedgerInputMessage);
		q=stockLedgerOutMessage.getStockCount();	
		if(q==null)
			q=0.0;
		}
		logger.info("Quantity for Batch ("+batchNo+")="+q);
		return q;
	}
	
	
private void setMasterPackDTO(BatchDTO batchDTO){
	
	if(batchDTO!=null && batchDTO.getItemDTO()!=null){
		logger.info("-----------------------"+batchDTO.getItemDTO().getMasterPack());
		MastersDTO  mastersDTO = batchDTO.getItemDTO().getMasterPack();
		if(mastersDTO!=null){
			batchDTO.getItemDTO().setMasterPackDTO(mastersDTO);
		}
		
	}
	
}
}
