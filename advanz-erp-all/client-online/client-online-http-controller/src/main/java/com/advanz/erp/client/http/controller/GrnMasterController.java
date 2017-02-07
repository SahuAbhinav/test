package com.advanz.erp.client.http.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.advanz.DataUtility;
import com.advanz.erp.client.http.controller.form.GrnMasterForm;
import com.advanz.erp.client.http.controller.form.ItemForm;
import com.advanz.erp.client.http.controller.form.PurchaseOrderMasterForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.GrnDetailDTO;
import com.advanz.erp.masters.model.GrnMasterDTO;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.PurchaseOrderDetailDTO;
import com.advanz.erp.masters.model.PurchaseOrderMasterDTO;
import com.advanz.erp.masters.model.TransactionTypeDTO;
import com.advanz.erp.masters.model.TransporterDTO;
import com.advanz.erp.masters.model.criteria.ItemSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.model.msg.GrnMasterInputMessage;
import com.advanz.erp.masters.model.msg.GrnMasterOutputMessage;
import com.advanz.erp.masters.model.msg.ItemGroupFlagOutMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.model.msg.PartyInputMessage;
import com.advanz.erp.masters.model.msg.PartyOutMessage;
import com.advanz.erp.masters.model.msg.PurchaseOrderMasterInputMessage;
import com.advanz.erp.masters.model.msg.PurchaseOrderMasterOutputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeInputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeOutputMessage;
import com.advanz.erp.masters.model.msg.TransporterOutMessage;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.service.business.IGrnMasterService;
import com.advanz.erp.masters.service.business.IItemGroupFlagService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IPartyService;
import com.advanz.erp.masters.service.business.IPurchaseOrderMasterService;
import com.advanz.erp.masters.service.business.ITransactionTypeService;
import com.advanz.erp.masters.service.business.ITransporterService;

@Controller
@SessionAttributes({ "grnMasterForm", "partyList","allPartyList","branchList" })
public class GrnMasterController extends BaseController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(GrnMasterController.class);

	@Autowired
	DataSource dataSource;
	
	@Autowired
	public IItemService itemService;

	@Autowired
	public IBranchService branchService;

	@Autowired
	public IPartyService partyService;

	@Autowired
	public IItemGroupFlagService itemGroupFlagService;

	@Autowired
	public ITransporterService transporterService;

	@Autowired
	public ITransactionTypeService transactionTypeService;

	@Autowired
	public IGrnMasterService grnMasterService;

	@Autowired
	public IMastersService mastersService;

	@Autowired
	public IPurchaseOrderMasterService purchaseOrderMasterService;

	@RequestMapping(value = "/saveGrn", method = RequestMethod.POST)
	public String saveSalesOrder(@ModelAttribute("grnMasterForm") GrnMasterForm grnMasterForm,@RequestParam(value="opration",required=false) String opration,Model model,HttpSession session)
	{
		
		String succ="";
		GrnMasterInputMessage grnMasterInputMessage = new GrnMasterInputMessage();
		GrnMasterDTO grnDTO=grnMasterForm.getGrnMasterDTO();
		String series = getGrnTransactionSeries();
		grnDTO.setTransactionSeries(series);
		
		session.removeAttribute("opera");
	if(grnDTO.getFormReqFlag()==0){
		grnDTO.setFormDate(null);	
	}		
		
		Date date= grnDTO.getGrnDate();
		date= DataUtility.getDate(date);
		grnDTO.setGrnDate(date);
		
		PartyDTO partyDTO=grnDTO.getPartyDTO();
		ArrayList<GrnDetailDTO> grnDetailList=(ArrayList<GrnDetailDTO>)grnDTO.getGrnDetailDTOList();
		
		if(grnDetailList!=null && grnDetailList.size()>0){
			for(int i=0;i<grnDetailList.size();i++){
				GrnDetailDTO grnDetailDTO =grnDetailList.get(i);
				if("vat".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
					grnDetailDTO.setVatPerc(grnDetailDTO.getVatCstPerc());
					grnDetailDTO.setVatAmount(grnDetailDTO.getVatCstAmount());
				}
		        if("cst with c form".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
		        	grnDetailDTO.setCstPerc(grnDetailDTO.getVatCstPerc());
					grnDetailDTO.setCstAmount(grnDetailDTO.getVatCstAmount());
				}if("cst w/o c form".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
					grnDetailDTO.setVatPerc(grnDetailDTO.getVatCstPerc());
					grnDetailDTO.setVatAmount(grnDetailDTO.getVatCstAmount());
				}
			}
		}
	
		if("vat".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
			grnDTO.setVatAmount(grnDTO.getVatAmount());
			grnDTO.setCstAmount(0.0);
		}                                              
        if("cst with c form".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
        	grnDTO.setCstAmount(grnDTO.getVatAmount());
        	grnDTO.setVatAmount(0.0);
		}if("cst w/o c form".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
			grnDTO.setVatAmount(grnDTO.getVatAmount());
			grnDTO.setCstAmount(0.0);
		}
		if(grnDTO.getAproved()!=null && grnDTO.getAproved()>0){
			grnDTO.setAprovedDate(new Date());
		}
		
		
		
		grnMasterInputMessage.setGrnMasterDTO(grnDTO);
		GrnMasterOutputMessage grnMasterOutputMessage = null;
	if (grnMasterForm.getGrnMasterDTO().getGrnAutoId() == null || grnMasterForm.getGrnMasterDTO().getGrnAutoId().equals(0)) {
		grnDTO.setCreatedUserId(getCreatedUserId());
		grnMasterOutputMessage = grnMasterService.createGrnMaster(grnMasterInputMessage);
			
	 if(grnDetailList!=null && grnDetailList.size()>0){
		 Double poPendingQty=0.0;
	  for(int i=0;i<grnDetailList.size();i++)
	    {
	    if(grnDetailList.get(i).getPoNumber()!=null)
	    {
	      int item=grnDetailList.get(i).getItemDTO().getItemId().intValue();
		  String poNumberD=grnDetailList.get(i).getPoNumber();
		  PurchaseOrderMasterDTO purchaseOrderMasterDTO=new PurchaseOrderMasterDTO();
		  purchaseOrderMasterDTO.setPurchaseOrderNumber(poNumberD); 
		  PurchaseOrderMasterInputMessage inputMessage=new PurchaseOrderMasterInputMessage();
		  inputMessage.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
		  PurchaseOrderMasterOutputMessage purchaseOrderMasterOutputMessage=new PurchaseOrderMasterOutputMessage();
		  purchaseOrderMasterOutputMessage=purchaseOrderMasterService.findPurchaseOrderMasterByPoNumber(inputMessage);
		  PurchaseOrderMasterDTO poMasterDto=purchaseOrderMasterOutputMessage.getPurchaseOrderMasterDTOList().get(0);
		  List<PurchaseOrderDetailDTO> poDtlDTOLst=poMasterDto.getPurchaseOrderDetailDTOList();
		  for(int d=0;d<poDtlDTOLst.size();d++)
		  {
		    	
		  if(poDtlDTOLst.get(d).getPurchaseOrderNumber().equals(poNumberD) && poDtlDTOLst.get(d).getItemDTO().getItemId().intValue()==item)
		  {
			  PurchaseOrderDetailDTO detailDTO=poDtlDTOLst.get(d);
			  poPendingQty=detailDTO.getPendingQuantity();
			  poPendingQty=poPendingQty-grnDetailList.get(i).getReceivedQty();
			  detailDTO.setPendingQuantity(poPendingQty);
		   }
		  }
		  poMasterDto.setPurchaseOrderDetailDTOList(poDtlDTOLst);
		  inputMessage=new PurchaseOrderMasterInputMessage();
		  inputMessage.setPurchaseOrderMasterDTO(poMasterDto);
		  purchaseOrderMasterService.updatePurchaseOrderMaster(inputMessage);
		 }}}
	
		succ="Ad";
		}
		else {
			
			GrnMasterInputMessage inputMessage=new GrnMasterInputMessage();
			  inputMessage.setGrnMasterDTO(grnMasterForm.getGrnMasterDTO());
			 GrnMasterOutputMessage grnMstOutputMsg=grnMasterService.findGrnMasterById(inputMessage); 
			 List<GrnDetailDTO> grnDetailDbList=grnMstOutputMsg.getGrnMasterDTOList().get(0).getGrnDetailDTOList();
			 List<GrnDetailDTO> dbDtlList=new ArrayList<GrnDetailDTO>();
				List<GrnDetailDTO>  formDtlList=new ArrayList<GrnDetailDTO>();
		try
		{
	    for(int n=0;n<grnDetailDbList.size();n++)
		 {
		  if(grnDetailDbList.get(n).getPoNumber()!=null)
			  dbDtlList.add(grnDetailDbList.get(n));
		 }
		for(int n=0;n<grnDetailList.size();n++)
		 {
		  if(grnDetailList.get(n).getPoNumber()!=null)
			  formDtlList.add(grnDetailList.get(n));
		 }
		 }catch (Exception e) {
			// TODO: handle exception
		}		
		 for(int i=0;i<grnDetailDbList.size();i++){
		 try{
		 if(grnDetailList.get(i).getPoNumber()!=null && grnDetailList.get(i).getPoNumber().length()>0){
				GrnDetailDTO grnDtlDb=grnDetailDbList.get(i);
		  for(int j=0;j<grnDetailList.size();j++){
			  if(grnDetailList.get(j).getPoNumber()!=null && grnDetailList.get(j).getPoNumber().length()>0)
			  {
				GrnDetailDTO grnDtlForm=grnDetailList.get(j);
				
		if(grnDtlForm.getPoNumber().equals(grnDtlDb.getPoNumber()) &&  
				grnDtlForm.getItemDTO().getItemId().intValue()==grnDtlDb.getItemDTO().getItemId().intValue())
			{
			    	 Double poPendingQty=0.0;
				      int item=grnDetailList.get(j).getItemDTO().getItemId().intValue();
					  String poNumberD=grnDetailList.get(j).getPoNumber();
					  PurchaseOrderMasterDTO purchaseOrderMasterDTO=new PurchaseOrderMasterDTO();
					  purchaseOrderMasterDTO.setPurchaseOrderNumber(poNumberD); 
					  PurchaseOrderMasterInputMessage poInputMessage=new PurchaseOrderMasterInputMessage();
					  poInputMessage.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
					  PurchaseOrderMasterOutputMessage purchaseOrderMasterOutputMessage=new PurchaseOrderMasterOutputMessage();
					  purchaseOrderMasterOutputMessage=purchaseOrderMasterService.findPurchaseOrderMasterByPoNumber(poInputMessage);
					  PurchaseOrderMasterDTO poMasterDto=purchaseOrderMasterOutputMessage.getPurchaseOrderMasterDTOList().get(0);
					  List<PurchaseOrderDetailDTO> poDtlDTOLst=poMasterDto.getPurchaseOrderDetailDTOList();
					  for(int d=0;d<poDtlDTOLst.size();d++)
					  {
					    	
					  if(poDtlDTOLst.get(d).getPurchaseOrderNumber().equals(poNumberD) && poDtlDTOLst.get(d).getItemDTO().getItemId().intValue()==item)
					  {
						  PurchaseOrderDetailDTO detailDTO=poDtlDTOLst.get(d);
						  poPendingQty=detailDTO.getPendingQuantity()+grnDetailDbList.get(i).getReceivedQty();
						  poPendingQty=poPendingQty-grnDetailList.get(j).getReceivedQty();
						  detailDTO.setPendingQuantity(poPendingQty);
					   }
					  }
					  poMasterDto.setPurchaseOrderDetailDTOList(poDtlDTOLst);
					  poInputMessage=new PurchaseOrderMasterInputMessage();
					  poInputMessage.setPurchaseOrderMasterDTO(poMasterDto);
					  purchaseOrderMasterService.updatePurchaseOrderMaster(poInputMessage);
			dbDtlList.remove(grnDtlDb);
			formDtlList.remove(grnDtlForm);   
			}
		  }
		  }
		  
		 }
		 } catch (Exception e) {
			// TODO: handle exception
		}} 
		 for(int k=0;k<dbDtlList.size();k++)
		 {
			 Double poPendingQty=0.0;
		      int item=dbDtlList.get(k).getItemDTO().getItemId().intValue();
			  String poNumberD=dbDtlList.get(k).getPoNumber();
			  PurchaseOrderMasterDTO purchaseOrderMasterDTO=new PurchaseOrderMasterDTO();
			  purchaseOrderMasterDTO.setPurchaseOrderNumber(poNumberD); 
			  PurchaseOrderMasterInputMessage poInputMessage=new PurchaseOrderMasterInputMessage();
			  poInputMessage.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
			  PurchaseOrderMasterOutputMessage purchaseOrderMasterOutputMessage=new PurchaseOrderMasterOutputMessage();
			  purchaseOrderMasterOutputMessage=purchaseOrderMasterService.findPurchaseOrderMasterByPoNumber(poInputMessage);
			  PurchaseOrderMasterDTO poMasterDto=purchaseOrderMasterOutputMessage.getPurchaseOrderMasterDTOList().get(0);
			  List<PurchaseOrderDetailDTO> poDtlDTOLst=poMasterDto.getPurchaseOrderDetailDTOList();
			  for(int d=0;d<poDtlDTOLst.size();d++)
			  {
			  if(poDtlDTOLst.get(d).getPurchaseOrderNumber().equals(poNumberD) && poDtlDTOLst.get(d).getItemDTO().getItemId().intValue()==item)
			  {
				  PurchaseOrderDetailDTO detailDTO=poDtlDTOLst.get(d);
				  poPendingQty=detailDTO.getPendingQuantity();
				  poPendingQty=poPendingQty+dbDtlList.get(k).getReceivedQty();
				  detailDTO.setPendingQuantity(poPendingQty);
			   }
			  }
			  poMasterDto.setPurchaseOrderDetailDTOList(poDtlDTOLst);
			  poInputMessage=new PurchaseOrderMasterInputMessage();
			  poInputMessage.setPurchaseOrderMasterDTO(poMasterDto);
			  purchaseOrderMasterService.updatePurchaseOrderMaster(poInputMessage);
		 }
		 for(int k=0;k<formDtlList.size();k++)
		 {
			 Double poPendingQty=0.0;
		      int item=formDtlList.get(k).getItemDTO().getItemId().intValue();
			  String poNumberD=formDtlList.get(k).getPoNumber();
			  PurchaseOrderMasterDTO purchaseOrderMasterDTO=new PurchaseOrderMasterDTO();
			  purchaseOrderMasterDTO.setPurchaseOrderNumber(poNumberD); 
			  PurchaseOrderMasterInputMessage poInputMessage=new PurchaseOrderMasterInputMessage();
			  poInputMessage.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
			  PurchaseOrderMasterOutputMessage purchaseOrderMasterOutputMessage=new PurchaseOrderMasterOutputMessage();
			  purchaseOrderMasterOutputMessage=purchaseOrderMasterService.findPurchaseOrderMasterByPoNumber(poInputMessage);
			  PurchaseOrderMasterDTO poMasterDto=purchaseOrderMasterOutputMessage.getPurchaseOrderMasterDTOList().get(0);
			  List<PurchaseOrderDetailDTO> poDtlDTOLst=poMasterDto.getPurchaseOrderDetailDTOList();
			  for(int d=0;d<poDtlDTOLst.size();d++)
			  {
			  if(poDtlDTOLst.get(d).getPurchaseOrderNumber().equals(poNumberD) && poDtlDTOLst.get(d).getItemDTO().getItemId().intValue()==item)
			  {
				  PurchaseOrderDetailDTO detailDTO=poDtlDTOLst.get(d);
				  poPendingQty=detailDTO.getPendingQuantity();
				  poPendingQty=poPendingQty-formDtlList.get(k).getReceivedQty();
				  detailDTO.setPendingQuantity(poPendingQty);
			   }
			  }
			  poMasterDto.setPurchaseOrderDetailDTOList(poDtlDTOLst);
			  poInputMessage=new PurchaseOrderMasterInputMessage();
			  poInputMessage.setPurchaseOrderMasterDTO(poMasterDto);
			  purchaseOrderMasterService.updatePurchaseOrderMaster(poInputMessage);
		 }
		 grnDTO.setModifiedUserId(getCreatedUserId());
			grnMasterOutputMessage = grnMasterService.updateGrnMaster(grnMasterInputMessage);
			succ="Up";
		}
		ErrorListDTO errorListDTO = grnMasterOutputMessage.getErrorListDTO();

		if (errorListDTO != null && errorListDTO.hasErrors()) {
			logger.info(" adding Error ");
			ErrorDTO errorDTO=grnMasterOutputMessage.getErrorListDTO().getErrorList().get(0);
			model.addAttribute("errors",errorDTO);
			 
			GrnMasterDTO grnMasterDTO = grnMasterForm.getGrnMasterDTO();
			
			Integer GrnId= grnMasterDTO.getGrnId();
			int i=GrnId.intValue()+1;
			 grnMasterDTO.setGrnNumber(grnMasterDTO.getTransactionSeries() + "/"
						+ getFinYear() + "/" + i);
			 
			 int grid= grnMasterDTO.getGrnId();
			 grnMasterDTO.setGrnId(grid+1);
			
			 grnMasterForm.setGrnMasterDTO(grnMasterDTO);
			 model.addAttribute("opr","Add");
			model.addAttribute("grnMasterForm",grnMasterForm);
			model.addAttribute("deptTypeList", getDeptTypeList());
			return "grn_add";
		}
		GrnMasterDTO masterDTO= grnMasterForm.getGrnMasterDTO();
		masterDTO.setGrnDetailDTOList(null);
		if(opration!=null && opration.equals("Print View")){
					model.addAttribute("printView",grnDTO.getGrnNumber());
					grnMasterForm.setPrintView(grnDTO.getGrnNumber());
				}
		model.addAttribute("grnMasterForm",grnMasterForm);
		model.addAttribute("succ",succ);
		
		  
		return "redirect:/get_grn_list";

	}

	@RequestMapping(value = "/show_item_list_grn")
	public ModelAndView showItemSelectionGrn(
			@ModelAttribute("searchCriteria") ItemSearchCriteriaDTO searchCriteria,
			@RequestParam(value="opr",required=false) String opr,
			@ModelAttribute("itemForm") ItemForm itemForm, HttpSession session,
			@ModelAttribute("grnMasterForm") GrnMasterForm grnMasterForm,@RequestParam(value="next",required=false) Integer next) {
		ModelAndView mav = new ModelAndView("item_list_grn");
		//FOR PURCHASE ORDER START
		try{
		if(grnMasterForm.getGrnMasterDTO().getGrnAutoId()!=null){
			session.setAttribute("opera","E");
		}else{
			session.setAttribute("opera","Add");
		}
		}catch (Exception e) {
		}
		if("GetPO".equals(opr)){
				 mav = new ModelAndView("purchaseOrderGrn_list");
				 List<PurchaseOrderMasterDTO> list = new ArrayList<PurchaseOrderMasterDTO>();
				PurchaseOrderMasterInputMessage inputMessage = new PurchaseOrderMasterInputMessage();
				PurchaseOrderMasterDTO purchaseOrderMasterDTO=new PurchaseOrderMasterDTO();
				PartyDTO partyDTO=new PartyDTO();
				partyDTO.setPartyId(grnMasterForm.getGrnMasterDTO().getPartyDTO().getPartyId());
				purchaseOrderMasterDTO.setPartyDTO(partyDTO);
				inputMessage.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
				
				PurchaseOrderMasterOutputMessage purchaseOrderMasterOutputMessage = purchaseOrderMasterService.findPurchaseOrderMasterBySupplierId(inputMessage);
				list = (ArrayList<PurchaseOrderMasterDTO>) purchaseOrderMasterOutputMessage.getPurchaseOrderMasterDTOList();
				
				
				List<PurchaseOrderDetailDTO> purchaseOrderDetailDTOlst=new ArrayList<PurchaseOrderDetailDTO>();
				for(int j=0;j<list.size();j++)
				{double blQty=0.0;
				 double pendingQty=0.0;
				 int itemCount=0;
				purchaseOrderMasterDTO=list.get(j);
				for(int i=0;i<purchaseOrderMasterDTO.getPurchaseOrderDetailDTOList().size();i++) {
				   
					 purchaseOrderDetailDTOlst=purchaseOrderMasterDTO.getPurchaseOrderDetailDTOList();
					try{
					 pendingQty=purchaseOrderDetailDTOlst.get(i).getPendingQuantity();
					}catch (Exception e) {
					}
					 if(pendingQty<0)
					 {
					  pendingQty=0.0; 
					 }
				    blQty=blQty+pendingQty;
				    itemCount=itemCount+1;
				    purchaseOrderMasterDTO.setItemCount(itemCount);
				    purchaseOrderMasterDTO.setBalanceQuantity(blQty);
				    list.set(j, purchaseOrderMasterDTO);
				 }
				 PurchaseOrderMasterForm	purchaseOrderMasterForm=new PurchaseOrderMasterForm();
				purchaseOrderMasterForm.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
				mav.addObject("grnMasterForm",grnMasterForm);
				session.setAttribute("grnMasterForm",grnMasterForm);
			 }
			mav.addObject("pomList", list);
			mav.addObject("opr", opr);
		}
		//TO PURCHASE ORDER END
		
		
		
		   //Pagination start
		    ItemDTO dto=new ItemDTO();
		    ItemOutMessage	 itemOutMessage=new ItemOutMessage();
		    Object[] objects= getPaginationItemList(next);
			itemOutMessage=(ItemOutMessage) objects[0];
			next=(Integer)objects[1];
			ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
			mav.addObject("itemList", list);
			dto.setNext(next);
			dto.setPrevious(next-30);
			itemForm.setItemDTO(dto);
			mav.addObject("itemForm", itemForm);
		mav.addObject("itemForm", itemForm);
		mav.addObject("opr", opr);
		mav.addObject("itemFor", "G");

		return mav;
	}
	

	@RequestMapping(value = "/get_grn_by_id")
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
			ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage
					.getItemDTOList();
			if (list.size() == 1) {
				itemDTO = list.get(0);
			}
		}
		return itemDTO;
	}
	                  
	@RequestMapping("/addItemInGrn")
	public ModelAndView addItemInGrn(
			@ModelAttribute("grnMasterForm") GrnMasterForm grnMasterForm, HttpSession session,
			@RequestParam("itemID") Integer itemId,
			@RequestParam("opr") String opr,@RequestParam(value="Remove",required=false) String Remove, ModelMap model) {

		try{
		if("Remove".equalsIgnoreCase(Remove)){
			List<GrnDetailDTO> detailDTOList=	grnMasterForm.getGrnMasterDTO().getGrnDetailDTOList();
			if(detailDTOList!=null && detailDTOList.size()>itemId){GrnDetailDTO dto=detailDTOList.get(itemId);
			detailDTOList.remove(dto);
			}
			
			String oper=(String) session.getAttribute("opera");
			if(oper!=null){
				model.put("opr", oper);
				}else{
					model.put("opr", opr);
				}
			session.removeAttribute("opera");
			ModelAndView mav = new ModelAndView("grn_add");	
			model.addAttribute("deptTypeList", getDeptTypeList());
			return mav;
		}
		}catch(Exception e){}
		//end remove
		
		Integer id=null;
		List list= grnMasterForm.getGrnMasterDTO().getGrnDetailDTOList();
		 if(list!=null && list.size()>0){
		 GrnDetailDTO detailDTO=(GrnDetailDTO) list.get(0);
		   id=  detailDTO.getItemDTO().getItemId();
		 
		 }
		 // if (!isDuplicateItem(grnMasterForm.getGrnMasterDTO(), itemId)) {
		if("Add".equalsIgnoreCase(opr)){
		 
			//if(id == null  || id.equals(itemId)){
				
				ItemInputMessage itemInputMessage = new ItemInputMessage();
				ItemDTO itemDTO = new ItemDTO();
				itemDTO.setItemId(itemId);
				itemInputMessage.setItemDTO(itemDTO);
				List<ItemDTO> itemDtoList = itemService.findItemById(itemInputMessage).getItemDTOList();
				//if (itemDtoList != null && itemDtoList.size() > 0) {
					itemDTO = itemDtoList.get(0);
					GrnDetailDTO grnDetailDTO = new GrnDetailDTO();
					
					BeanUtils.copyProperties(itemDTO, grnDetailDTO);
					grnDetailDTO.setItemDTO(itemDTO);
					if(itemDTO.getMasterUnit()!=null){
					grnDetailDTO.setMeasurementUnitId(itemDTO.getMasterUnit().getMastersId());
					grnDetailDTO.setMeasurementUnitName(itemDTO.getMasterUnit().getName());
					grnDetailDTO.setPurchaseRate(itemDTO.getPurchaseRate());	
					}
					
					PartyDTO partyDTO = grnMasterForm.getGrnMasterDTO().getPartyDTO();
					if("vat".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
						grnDetailDTO.setVatCstPerc(itemDTO.getVatPerc());
					}
                    if("cst with c form".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
                    	grnDetailDTO.setVatCstPerc(itemDTO.getCstPerc());
					}if("cst w/o c form".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
						grnDetailDTO.setVatCstPerc(itemDTO.getVatPerc());
					}
					
					
					if (grnMasterForm.getGrnMasterDTO() == null) {
						GrnMasterDTO grnMasterDTO = new GrnMasterDTO();
						grnMasterDTO.setGrnDetailDTOList(new ArrayList<GrnDetailDTO>());
					}
					if (grnMasterForm.getGrnMasterDTO().getGrnDetailDTOList() == null) {
						grnMasterForm.getGrnMasterDTO().setGrnDetailDTOList(new ArrayList<GrnDetailDTO>());
					}
					grnMasterForm.getGrnMasterDTO().getGrnDetailDTOList().add(grnDetailDTO);
					grnMasterForm.getGrnMasterDTO().setCount(grnMasterForm.getGrnMasterDTO().getGrnDetailDTOList().size());
					
					
				}
				
			/*}else{
				model.put("duplicateMsg", "You cannot add different item");
				model.put("opr", opr);
				ModelAndView mav = new ModelAndView("grn_add");
				return mav;
			 }
			                                                   
			ModelAndView mv = new ModelAndView(new RedirectView("addItemInGrn?itemID="+itemId+"&opr=A"));
			//model.put("opr", opr);
			return mv;
		}*/
			
		//}
		/* calculationGrn(grnMasterForm.getGrnMasterDTO()); */
		session.setAttribute("grnMasterForm",grnMasterForm);
		String oper=(String) session.getAttribute("opera");
		if(oper!=null){
			opr=oper;
		//model.put("opr", oper);
		}else{
			opr=opr;
			//model.put("opr", opr);
		}
		//session.removeAttribute("opera");
		//ModelAndView mav = new ModelAndView("grn_add");
		//return mav;
		
		

		ModelAndView mv = new ModelAndView(new RedirectView(
		"redirectGRNMaster?opr="+opr));
		mv.addObject("grnMasterForm",grnMasterForm);
	   return mv;
	}
	                  
	@RequestMapping("/addPoInGrn")
	public ModelAndView addPoInGrn(
			@ModelAttribute("grnMasterForm") GrnMasterForm grnMasterForm,
			@RequestParam("poID") Integer poAutoId,@RequestParam(value="poNumber",required=false) String poNumber,
			@RequestParam("opr") String opr, ModelMap model) {
		
// start
		
  ArrayList<PurchaseOrderDetailDTO> detailList=new ArrayList<PurchaseOrderDetailDTO>();
		
		// end
		
		
		PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage = new PurchaseOrderMasterInputMessage();
		PurchaseOrderMasterDTO purchaseOrderMasterDTO = new PurchaseOrderMasterDTO();
		purchaseOrderMasterDTO.setPoAutoId(poAutoId);
		purchaseOrderMasterInputMessage.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
		List<PurchaseOrderMasterDTO> purchaseOrderMasterDTOList = purchaseOrderMasterService.findPurchaseOrderMasterById(purchaseOrderMasterInputMessage).getPurchaseOrderMasterDTOList();
		Integer suplyerId=null; 
		if (purchaseOrderMasterDTOList != null && purchaseOrderMasterDTOList.size() > 0) {
			purchaseOrderMasterDTO = purchaseOrderMasterDTOList.get(0);
			suplyerId= purchaseOrderMasterDTO.getPartyDTO().getPartyId();
			if (grnMasterForm.getGrnMasterDTO() == null) {
				grnMasterForm.setGrnMasterDTO(new GrnMasterDTO());
			}
			grnMasterForm.getGrnMasterDTO().setPurchaseOrderDTO(purchaseOrderMasterDTO);
			if (purchaseOrderMasterDTO.getPurchaseOrderDetailDTOList() != null
					&& purchaseOrderMasterDTO.getPurchaseOrderDetailDTOList().size() > 0) {
				
				
				ArrayList<GrnDetailDTO> grnDetailDTOList = new ArrayList<GrnDetailDTO>();
				for (PurchaseOrderDetailDTO detailDTO : purchaseOrderMasterDTO.getPurchaseOrderDetailDTOList()) {
					GrnDetailDTO dto = new GrnDetailDTO();
					dto.setItemDTO(detailDTO.getItemDTO());
					dto.setItemValue(detailDTO.getItemValue());
					dto.setPoNumber(purchaseOrderMasterDTO.getPurchaseOrderNumber());
					model.put("poNumber", purchaseOrderMasterDTO.getPurchaseOrderNumber());
					// get UMO name
				   ItemInputMessage inputMessage = new ItemInputMessage();
				   inputMessage.setItemDTO(detailDTO.getItemDTO());
				    ItemOutMessage itemOutMessage=  itemService.findItemById(inputMessage);
				    ArrayList<ItemDTO> itemList =(ArrayList<ItemDTO>)itemOutMessage.getItemDTOList();
				    if(itemList!=null){
				    	ItemDTO itemDTO= itemList.get(0);
				    	dto.setMeasurementUnitName(itemDTO.getMasterGrade().getName());
				    }
				   // grnDetailDTOList.add(dto);
				    detailList.add(detailDTO);
				}
				//grnMasterForm.getGrnMasterDTO().setGrnDetailDTOList(grnDetailDTOList);
				grnMasterForm.getGrnMasterDTO().setCount(grnDetailDTOList.size());
			}
			grnMasterForm.getGrnMasterDTO().setItemGroupFlagDTO(purchaseOrderMasterDTO.getItemGroupFlagDTO());
		   }
		// calculationGrn(grnMasterForm.getGrnMasterDTO());
		model.put("opr", opr);
		//ModelAndView mav = new ModelAndView("grn_add");
		ModelAndView mav = new ModelAndView("grn_po_item");
		//mav.addObject("grnMasterForm",grnMasterForm);
		mav.addObject("suplyerId", suplyerId);
		mav.addObject("detailList", detailList);
		return mav;
	}

	
	
	
	@RequestMapping("/addPoInGrn1")
	public ModelAndView addPoInGrn1(
			@ModelAttribute("grnMasterForm") GrnMasterForm grnMasterForm1,
			@RequestParam("poID") Integer itemId,@RequestParam(value= "poQty",required =false) Double poQty,@RequestParam(value= "suplyerId",required =false) Integer suplyerId,@RequestParam(value= "poNumber",required =false) String poNumber, HttpSession session, 
			@RequestParam("opr") String opr,@RequestParam(value="itemIds",required=false) int[]  itemIds, ModelMap model) {
		GrnMasterForm grnMasterForm=(GrnMasterForm)session.getAttribute("grnMasterForm");
		//if (grnMasterForm.getGrnMasterDTO() == null) {
			//grnMasterForm.setGrnMasterDTO(new GrnMasterDTO());
			//}
		 GrnMasterDTO grnMasterDTO=	grnMasterForm1.getGrnMasterDTO();
		if(grnMasterDTO==null){
			grnMasterDTO=new GrnMasterDTO();
		}
		ArrayList<GrnDetailDTO> grnDetailDTOList =(ArrayList<GrnDetailDTO>)grnMasterDTO.getGrnDetailDTOList();
		if(grnDetailDTOList==null){
			grnDetailDTOList=new ArrayList<GrnDetailDTO>();
			
		}
		
		//ArrayList<GrnDetailDTO> grnDetailDTOList =(ArrayList<GrnDetailDTO>)grnMasterForm.getGrnMasterDTO().getGrnDetailDTOList();
		//grnMasterForm.getGrnMasterDTO().getGrnDetailDTOList();
			try{
		for(int i=0;i<itemIds.length;i++){
		ItemInputMessage inputMessage = new ItemInputMessage();
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemId(itemIds[i]);
		
		inputMessage.setItemDTO(itemDTO);
	    ItemOutMessage itemOutMessage=	itemService.findItemById(inputMessage);
	    ArrayList<ItemDTO> itemList =(ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
		if(itemList!=null && itemList.size()>0){
		itemDTO =itemList.get(0);
		}
					GrnDetailDTO dto = new GrnDetailDTO();
					dto.setItemDTO(itemDTO);
					
					if(itemDTO.getMasterUnit()!=null){
						dto.setMeasurementUnitId(itemDTO.getMasterUnit().getMastersId());
						dto.setMeasurementUnitName(itemDTO.getMasterUnit().getName());
						}
					//Purchase quantity and rate start
					PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage=new PurchaseOrderMasterInputMessage();
					PurchaseOrderMasterDTO orderMasterDTO =new PurchaseOrderMasterDTO();
					orderMasterDTO.setPurchaseOrderNumber(poNumber);
					purchaseOrderMasterInputMessage.setPurchaseOrderMasterDTO(orderMasterDTO);
					purchaseOrderMasterInputMessage.setItemId(itemDTO.getItemId());
				    PurchaseOrderMasterOutputMessage purchaseOrderMasterOutputMessage=purchaseOrderMasterService.FindPurchaseOrderByItemAndPoNumber(purchaseOrderMasterInputMessage);
				    List<PurchaseOrderDetailDTO> detailList= purchaseOrderMasterOutputMessage.getPurchaseOrderDetailDTOList();
				    PurchaseOrderDetailDTO detailDTO=new PurchaseOrderDetailDTO();
				    if(detailList!=null && detailList.size()>0){
					detailDTO= detailList.get(0);
					}
				    //Purchase quantity and rate end
				    
					if(detailDTO.getItemQuantity()!=null){
						dto.setBillQty(detailDTO.getItemQuantity());
						dto.setReceivedQty(detailDTO.getItemQuantity());
						dto.setPoQty(detailDTO.getItemQuantity());
					}
					if(itemDTO.getPurchaseRate()!=null){
					dto.setPurchaseRate(itemDTO.getPurchaseRate());
					}
					PartyDTO partyDTO = grnMasterForm.getGrnMasterDTO().getPartyDTO();
					if("vat".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
						dto.setVatCstPerc(itemDTO.getVatPerc());
					}
                    if("cst with c form".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
                    	dto.setVatCstPerc(itemDTO.getCstPerc());
					}if("cst w/o c form".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
						dto.setVatCstPerc(itemDTO.getVatPerc());
					}
					dto.setPoNumber(poNumber);
					grnDetailDTOList.add(dto);
		            }
			}catch (Exception e) {
			}
				// To get Item group flag	
				//GrnMasterDTO grnMasterDTO=	grnMasterForm.getGrnMasterDTO();
				grnMasterDTO.getPartyDTO().setPartyId(suplyerId);
				grnMasterDTO.setGrnDetailDTOList(grnDetailDTOList);
				grnMasterForm.setGrnMasterDTO(grnMasterDTO);
				//grnMasterForm.getGrnMasterDTO().setGrnDetailDTOList(grnDetailDTOList);
				//grnMasterForm.getGrnMasterDTO().setCount(grnDetailDTOList.size());
				String oper=(String)session.getAttribute("opera");
				if(oper!=null){
					opr=oper;
					//model.put("opr", oper);
				}else{
					opr=opr;
				//model.put("opr", opr);
				}
				
				/*
				ModelAndView mav = new ModelAndView("grn_add");
				mav.addObject("grnMasterForm",grnMasterForm);
				return mav;*/
			   
				ModelAndView mv = new ModelAndView(new RedirectView(
				"redirectGRNMaster?opr="+opr));
				mv.addObject("grnMasterForm",grnMasterForm);
			   return mv;
	    }

	
	
	

@RequestMapping("/redirectGRNMaster")
public ModelAndView redirectGRNMaster(
		@ModelAttribute("grnMasterForm") GrnMasterForm grnMasterForm, HttpSession session, 
		@RequestParam("opr") String opr, ModelMap model){
	
	String oper=(String)session.getAttribute("opera");
	if(oper!=null){
		opr=oper;
		model.put("opr", oper);
	}else{
	model.put("opr", opr);
	}
	//session.removeAttribute("opera");
	ModelAndView mav = new ModelAndView("grn_add");
	mav.addObject("grnMasterForm",grnMasterForm);
	model.addAttribute("deptTypeList", getDeptTypeList());
	return mav;
}




	private String getGrnTransactionSeries() {
		TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
		transactionTypeDTO.setSno(10);
		TransactionTypeInputMessage transactionTypeInputMessage = new TransactionTypeInputMessage();
		transactionTypeInputMessage.setTransactionTypeDTO(transactionTypeDTO);
		TransactionTypeOutputMessage transactionTypeOutputMessage = transactionTypeService.findTransactionTypeById(transactionTypeInputMessage);
		List<TransactionTypeDTO> list = transactionTypeOutputMessage.getTransactionTypeDTOList();
		if (list != null && list.size() == 1)
			return list.get(0).getSeries();
		return null;

	}

	@RequestMapping(value = "/grn_add", method = RequestMethod.GET)
	public ModelAndView addGrn(ModelMap model,@RequestParam(value="opr",required=false) String opr) {
		
		Date date= grnMasterService.getMaxDate();
		
		
		
		
		GrnMasterForm grnMasterForm = new GrnMasterForm();
		GrnMasterDTO grnMasterDTO = new GrnMasterDTO();
		grnMasterDTO.setGrnMaxDate(date);
		
		String series = getGrnTransactionSeries();
		grnMasterDTO.setFinYear(getFinYear());
		grnMasterDTO.setTransactionSeries(series + "/"	+ getFinYear());
		GrnMasterInputMessage grnMasterInputMessage = new GrnMasterInputMessage();
		grnMasterInputMessage.setGrnMasterDTO(grnMasterDTO);
		GrnMasterOutputMessage grnMasterOutputMessage = grnMasterService.getNewGrnMasterSeriesNo(grnMasterInputMessage);
		Integer orderID = grnMasterOutputMessage.getGrnSeriesNo();
		
		Timestamp timestamp= grnMasterOutputMessage.getGrnSeriesDate();
		//System.out.println("Last Finished good time"+new Date(timestamp.getTime()));
		SimpleDateFormat ft =new SimpleDateFormat ("yyyy,MM,dd");
		
		
		grnMasterDTO.setGrnId(orderID);
		grnMasterDTO.setGrnNumber(series + "/"	+ getFinYear() + "/" + grnMasterDTO.getGrnId());
		grnMasterDTO.setGrnDate(new Date());
		grnMasterDTO.setFormReqFlag(0);
		grnMasterDTO.setQaCheckRequired(0);
		
		Date approveDate=new Date();
		Calendar cal = Calendar.getInstance();  
		cal.setTime(new Date());  
		cal.add(Calendar.DATE, 60);
		approveDate = cal.getTime();
		grnMasterDTO.setFormDate(approveDate);
		grnMasterForm.setGrnMasterDTO(grnMasterDTO);
		// grnMasterForm.setGrnMasterDTO(new
		// GrnMasterDTO());
		
		grnMasterForm.setLastGrnDate(ft.format(new Date(timestamp.getTime())));
		model.put("grnMasterForm", grnMasterForm);
		model.put("partyList", partyList());
		model.put("allPartyList", allPartyList());
		model.put("branchList", branchList());
		//model.put("itemsMap", itemsMap());
		model.put("opr", opr);
		
		ModelAndView mav = new ModelAndView("grn_add");
		model.addAttribute("deptTypeList", getDeptTypeList());
		// mav.addObject("partyList",partyList());
		return mav;
	}

	@RequestMapping(value = "/backto_grn", method = RequestMethod.GET)
	public ModelAndView backToGrn(ModelMap model,
			@RequestParam("opr") String opr,@ModelAttribute("grnMasterForm") GrnMasterForm grnMasterForm,HttpSession session) {
		
/*		try {
			grnMasterForm.getGrnMasterDTO().getGrnDetailDTOList().clear();
		} catch (Exception e) {
			
		}*/
		grnMasterForm.getGrnMasterDTO().setCount(0);
		ModelAndView mav = new ModelAndView("grn_add");
		model.addAttribute("deptTypeList", getDeptTypeList());
		String oper=(String)session.getAttribute("opera");
		if(oper!=null){
			opr=oper;
			model.put("opr", oper);
		}else{
		model.put("opr", opr);
		}
		mav.addObject("grnMasterForm",grnMasterForm);
		return mav;
	}

	@RequestMapping(value = "/get_item_data_grn", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView getItemData(@ModelAttribute("itemCode") String itemCode,
			@ModelAttribute("invoiceName") String invoiceName,
			/*@ModelAttribute("groupId") Integer groupId,*/
			@ModelAttribute("grnMasterForm") GrnMasterForm grnMasterForm) {
		
	
		
		
		ModelAndView mav = new ModelAndView("item_list_grn");
		ArrayList<ItemDTO> list = null;
		ItemOutMessage itemOutMessage = null;
		

		ItemInputMessage itemInputMessage = new ItemInputMessage();
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemCategoryDTO(new ItemCategoryDTO());
		itemDTO.getItemCategoryDTO().setItemGroupDTO(new ItemGroupDTO());
		/*itemDTO.getItemCategoryDTO().getItemGroupDTO()
				.setItemGroupFlagId(groupId);*/
		itemDTO.setItemName(invoiceName);
		itemDTO.setItemCode(itemCode);
		itemDTO.setActiveStatus(1);
		itemInputMessage.setItemDTO(itemDTO);
		itemOutMessage = itemService.findItem(itemInputMessage);
		list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
		if (grnMasterForm.getGrnMasterDTO() != null) {
			List<GrnDetailDTO> grnDetailDTOList = grnMasterForm
					.getGrnMasterDTO()
					.getGrnDetailDTOList();
			if (grnDetailDTOList != null) {
				for (GrnDetailDTO grnDetailDTO : grnDetailDTOList) {
					Iterator<ItemDTO> itr = list.iterator();
					while (itr.hasNext()) {
						ItemDTO temp = itr.next();
						if (temp.getItemId().equals(grnDetailDTO.getItemDTO().getItemId())) {
							itr.remove();
						}
					}
				}
			}
		}
		
		mav.addObject("itemList", list);
		return mav;
 	}

	@RequestMapping(value = "/getPartyById", method = RequestMethod.GET)
	public @ResponseBody
	PartyDTO getPartyById(@RequestParam("id") Integer partyId,
			@ModelAttribute("grnMasterForm") GrnMasterForm grnMasterForm) {
		PartyDTO partyDTO = new PartyDTO();
		/*	PartyInputMessage partyInputMessage = new PartyInputMessage();
		PartyDTO partyDTO = new PartyDTO();
		partyDTO.setPartyId(partyId);
		partyInputMessage.setPartyDTO(partyDTO);
		PartyOutMessage partyOutMessage = partyService
				.findPartyById(partyInputMessage);

		ArrayList<PartyDTO> list = (ArrayList<PartyDTO>) partyOutMessage
				.getPartyDTOList();*/

		ArrayList<PartyDTO> list = (ArrayList<PartyDTO>)getPartyListById(partyId);
		
		
		if (list.size() == 1) {
			partyDTO = list.get(0);
			grnMasterForm.getGrnMasterDTO().setPartyDTO(partyDTO);
		}
		return partyDTO;

	}
	
	public List<PartyDTO> getPartyListById(Integer id){
		
		PartyInputMessage partyInputMessage = new PartyInputMessage();
		PartyDTO partyDTO = new PartyDTO();
		partyDTO.setPartyId(id);
		partyInputMessage.setPartyDTO(partyDTO);
		PartyOutMessage partyOutMessage = partyService
				.findPartyById(partyInputMessage);

		ArrayList<PartyDTO> list = (ArrayList<PartyDTO>) partyOutMessage
				.getPartyDTOList();
		return list;
	}

	@RequestMapping(value = "/get_po_data_grn", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView getPoData(@ModelAttribute("poNo") String poNo,
			@ModelAttribute("supplierName") String supplierName) {
		ModelAndView mav = new ModelAndView("purchaseOrderGrn_list");
		List<PurchaseOrderMasterDTO> list = null;
		PurchaseOrderMasterOutputMessage purchaseOrderMasterOutputMessage = null;
		if (StringUtils.hasText(poNo) || StringUtils.hasText(supplierName)) {

			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage = new PurchaseOrderMasterInputMessage();
			PurchaseOrderMasterDTO purchaseOrderMasterDTO = new PurchaseOrderMasterDTO();
			purchaseOrderMasterDTO.setPartyDTO(new PartyDTO());
			purchaseOrderMasterDTO.getPartyDTO().setPartyName(supplierName);
			purchaseOrderMasterDTO.setPurchaseOrderNumber(poNo);

			purchaseOrderMasterInputMessage
					.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
			purchaseOrderMasterOutputMessage = purchaseOrderMasterService
					.search(purchaseOrderMasterInputMessage);
			list = (ArrayList<PurchaseOrderMasterDTO>) purchaseOrderMasterOutputMessage
					.getPurchaseOrderMasterDTOList();
		} else {
			purchaseOrderMasterOutputMessage = purchaseOrderMasterService
					.findAllPurchaseOrderMasters();
			list = (ArrayList<PurchaseOrderMasterDTO>) purchaseOrderMasterOutputMessage
					.getPurchaseOrderMasterDTOList();
		}

		mav.addObject("pomList", list);
		return mav;

	}
	
	

	@RequestMapping(value = "/get_grn_list")
	public ModelAndView searchGrn(@ModelAttribute("grnMasterForm1") GrnMasterForm grnMasterForm,Model model,@RequestParam(value="menuId",required=false) String menuId,HttpSession session,@RequestParam(value="next",required=false) Integer next) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
		session.removeAttribute("opera");
		String succ="Blk";
		
		
		List<GrnMasterDTO> list = new ArrayList<GrnMasterDTO>();
		GrnMasterOutputMessage grnMasterOutputMessage = new GrnMasterOutputMessage();
		GrnMasterInputMessage grnMasterInputMessage = new GrnMasterInputMessage();
		GrnMasterDTO grnMasterDTO=null;
		ModelAndView mav = new ModelAndView("grn_list");
		if (grnMasterForm.getGrnMasterDTO() != null) {
		grnMasterDTO = grnMasterForm.getGrnMasterDTO();
		grnMasterInputMessage.setGrnMasterDTO(grnMasterDTO);
		grnMasterOutputMessage = grnMasterService.search(grnMasterInputMessage);
		list = (ArrayList<GrnMasterDTO>) grnMasterOutputMessage .getGrnMasterDTOList();
		}
		else {
		grnMasterDTO = grnMasterForm.getGrnMasterDTO();
		if (grnMasterDTO == null)
		grnMasterDTO = new GrnMasterDTO();
		if (grnMasterDTO.getPartyDTO() == null)
		grnMasterDTO.setPartyDTO(new PartyDTO());

		if(next==null ||next<0)
		{
		next=0;
		grnMasterInputMessage.setNext(next);
		grnMasterOutputMessage = grnMasterService.findGrnMasterForPagination(grnMasterInputMessage);
		}
		else
		{
		grnMasterInputMessage.setNext(next);
		grnMasterOutputMessage = grnMasterService.findGrnMasterForPagination(grnMasterInputMessage);
		}
		grnMasterForm.setNext(next);
		grnMasterForm.setPrevious(next);
		list = (ArrayList<GrnMasterDTO>) grnMasterOutputMessage .getGrnMasterDTOList();
		}

		
		
		if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}
		mav.addObject("grnList", list);
		return mav;
	}

	@RequestMapping(value = "/get_grn", method = RequestMethod.GET)
	public ModelAndView getSalesOrderData(@RequestParam("id") Integer id,
			@RequestParam("opr") String opr,@RequestParam(value="aproved", required=false) Integer aproved, HttpSession session, ModelMap model) {
		GrnMasterForm grnMasterForm = new GrnMasterForm();
		session.setAttribute("opera", opr);
		GrnMasterOutputMessage grnMasterOutputMessage = null;
		if (id != null && !id.equals(0)) {

			GrnMasterInputMessage grnMasterInputMessage = new GrnMasterInputMessage();
			GrnMasterDTO grnMasterDTO = new GrnMasterDTO();
			grnMasterDTO.setGrnAutoId(id);
			grnMasterInputMessage.setGrnMasterDTO(grnMasterDTO);
			grnMasterOutputMessage = grnMasterService
					.findGrnMasterById(grnMasterInputMessage);
			ArrayList<GrnMasterDTO> list = (ArrayList<GrnMasterDTO>) grnMasterOutputMessage
					.getGrnMasterDTOList();
			
			if (list != null && list.size() > 0) {
				grnMasterDTO = new GrnMasterDTO();
				grnMasterDTO=list.get(0);
				GrnDetailDTO detailDTO=null;
				try {
					detailDTO = grnMasterDTO.getGrnDetailDTOList().get(0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
				detailDTO.setTempReciveQty(detailDTO.getReceivedQty());

				
				
				PartyDTO partyDTO = new PartyDTO();
				/*partyDTO.setPartyId(grnMasterDTO.getPartyDTO().getPartyId());
				PartyInputMessage partyInputMessage = new PartyInputMessage();
				partyInputMessage.setPartyDTO(partyDTO);
				PartyOutMessage partyOutMessage = partyService.findPartyById(partyInputMessage);
*/
		        ArrayList<PartyDTO> pList =(ArrayList<PartyDTO>)getPartyListById(grnMasterDTO.getPartyDTO().getPartyId());
				if(pList!=null && pList.size()>0){
				  partyDTO=pList.get(0);
				  
				  if("vat".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
					  grnMasterDTO.setVatAmount(grnMasterDTO.getVatAmount());
					}
			        if("cst with c form".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
			        	grnMasterDTO.setVatAmount(0.0); 
			        	grnMasterDTO.setVatAmount(grnMasterDTO.getCstAmount());
					}if("cst w/o c form".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
						grnMasterDTO.setVatAmount(grnMasterDTO.getVatAmount());
					}
					
				  
				  
				  
				  ArrayList<GrnDetailDTO> grnDeatilList=(ArrayList<GrnDetailDTO>)grnMasterDTO.getGrnDetailDTOList();
				  for(int i=0;i<grnDeatilList.size();i++){
					  GrnDetailDTO grnDetailDTO=  grnDeatilList.get(i);
				  if("vat".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
						grnDetailDTO.setVatCstPerc(grnDetailDTO.getVatPerc());
					}
                  if("cst with c form".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
                  	grnDetailDTO.setVatCstPerc(grnDetailDTO.getCstPerc());
					}if("cst w/o c form".equalsIgnoreCase(partyDTO.getVatCstTaxType())){
						grnDetailDTO.setVatCstPerc(grnDetailDTO.getVatPerc());
					}
				  }
				  
				  
				  
				}
				grnMasterDTO.setTransactionSeries(grnMasterDTO.getTransactionSeries() + "/"	+ grnMasterDTO.getFinYear());
				grnMasterForm.setGrnMasterDTO(grnMasterDTO);
			}
		}

		model.put("grnMasterForm", grnMasterForm);
		model.put("partyList", partyList());
		
		model.put("allPartyList", allPartyList());
		model.put("branchList", branchList());
		model.put("opr", opr);
		model.put("aproved", aproved);
		
		ModelAndView mav = new ModelAndView("grn_add");
		model.addAttribute("deptTypeList", getDeptTypeList());
		return mav;

	}

	@RequestMapping(value = "/remove_grn", method = RequestMethod.GET)
	public String removeGrnMaster(@RequestParam("id") Integer id,Model model) {
	GrnMasterOutputMessage grnMasterOutputMessage = null;
	GrnMasterInputMessage grnMasterInputMessage = new GrnMasterInputMessage();
	GrnMasterDTO grnMasterDTO = new GrnMasterDTO();
	grnMasterDTO.setGrnAutoId(id);
	grnMasterInputMessage.setGrnMasterDTO(grnMasterDTO);

	if (id != null && !id.equals(0)) {

	GrnMasterOutputMessage grnOutputMessage=grnMasterService.findGrnMasterById(grnMasterInputMessage);
	List<GrnDetailDTO> grnDetailList=grnOutputMessage.getGrnMasterDTOList().get(0).getGrnDetailDTOList();
	
	try
	{
	if(grnOutputMessage.getGrnMasterDTOList().get(0).getPurchaseOrderDTO().getPoAutoId()!=null){
		if(grnDetailList!=null && grnDetailList.size()>0){
 			 Double poPendingQty=0.0;
			  for(int i=0;i<grnDetailList.size();i++)
			    {
			    if(grnDetailList.get(i).getPoNumber()!=null)
			    {
			      int item=grnDetailList.get(i).getItemDTO().getItemId().intValue();
				  String poNumberD=grnDetailList.get(i).getPoNumber();
				  PurchaseOrderMasterDTO purchaseOrderMasterDTO=new PurchaseOrderMasterDTO();
				  purchaseOrderMasterDTO.setPurchaseOrderNumber(poNumberD); 
				  PurchaseOrderMasterInputMessage inputMessage=new PurchaseOrderMasterInputMessage();
				  inputMessage.setPurchaseOrderMasterDTO(purchaseOrderMasterDTO);
				  PurchaseOrderMasterOutputMessage purchaseOrderMasterOutputMessage=new PurchaseOrderMasterOutputMessage();
				  purchaseOrderMasterOutputMessage=purchaseOrderMasterService.findPurchaseOrderMasterByPoNumber(inputMessage);
				  PurchaseOrderMasterDTO poMasterDto=purchaseOrderMasterOutputMessage.getPurchaseOrderMasterDTOList().get(0);
				  List<PurchaseOrderDetailDTO> poDtlDTOLst=poMasterDto.getPurchaseOrderDetailDTOList();
				  for(int d=0;d<poDtlDTOLst.size();d++)
				  {
				  if(poDtlDTOLst.get(d).getPurchaseOrderNumber().equals(poNumberD) && poDtlDTOLst.get(d).getItemDTO().getItemId().intValue()==item)
				  {
					  PurchaseOrderDetailDTO detailDTO=poDtlDTOLst.get(d);
					  poPendingQty=detailDTO.getPendingQuantity();
					  poPendingQty=poPendingQty+grnDetailList.get(i).getReceivedQty();
					  detailDTO.setPendingQuantity(poPendingQty);
				   }
				  }
				  poMasterDto.setPurchaseOrderDetailDTOList(poDtlDTOLst);
				  inputMessage=new PurchaseOrderMasterInputMessage();
				  inputMessage.setPurchaseOrderMasterDTO(poMasterDto);
				  purchaseOrderMasterService.updatePurchaseOrderMaster(inputMessage);
				 }}}
           	}}
	catch (Exception e) {
			e.printStackTrace();
	}
	grnMasterDTO.setModifiedUserId(getCreatedUserId());
	grnMasterOutputMessage = grnMasterService.deleteGrnMaster(grnMasterInputMessage);
	}

	String succ="Dl";
	model.addAttribute("succ", succ);
	return "redirect:/get_grn_list";

	}



	private boolean isDuplicateItem(GrnMasterDTO grnMasterDTO, Integer itemId) {

		if (grnMasterDTO != null && grnMasterDTO.getGrnDetailDTOList() != null) {
			List<GrnDetailDTO> list = grnMasterDTO.getGrnDetailDTOList();
			for (GrnDetailDTO e : list) {

				if (itemId.equals(e.getItemDTO().getItemId()))
					return true;
			}
		}
		return false;
	}

	// @ModelAttribute("partyList")
	public List<PartyDTO> partyList() {
		PartyOutMessage partyOutMessage = partyService.findCreditorPartyList();
		ArrayList<PartyDTO> list = (ArrayList<PartyDTO>) partyOutMessage
				.getPartyDTOList();
		return list;
	}
	
	public List<PartyDTO> allPartyList() {
		PartyOutMessage partyOutMessage = partyService.preloadedPartys();
		ArrayList<PartyDTO> list = (ArrayList<PartyDTO>) partyOutMessage
				.getPartyDTOList();
		return list;
	}
	
	//@ModelAttribute("branchList")
	public List<BranchDTO> branchList() {
		BranchOutMessage branchOutMessage = branchService.findAllBranches();
		ArrayList<BranchDTO> list = (ArrayList<BranchDTO>) branchOutMessage
				.getBranchDTOList();
		return list;
	}

	@ModelAttribute("itemGroupList")
	public List<ItemGroupFlagDTO> itemGroupList() {
		ItemGroupFlagOutMessage itemGroupFlagOutMessage = itemGroupFlagService
				.findAllItemGroupFlag();
		ArrayList<ItemGroupFlagDTO> list = (ArrayList<ItemGroupFlagDTO>) itemGroupFlagOutMessage
				.getItemGroupFlagDTOList();
		return list;
	}

	@ModelAttribute("transporterList")
	public List<TransporterDTO> transporterList() {
		TransporterOutMessage transporterOutMessage = transporterService
				.preload();
		ArrayList<TransporterDTO> list = (ArrayList<TransporterDTO>) transporterOutMessage
				.getTransporterDTOList();
		return list;
	}

	@ModelAttribute("formType")
	public List<MastersDTO> formTypeList() {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(12);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

	public Map<Integer, ItemDTO> itemsMap() {
		return new TreeMap<Integer, ItemDTO>();
	}

	
	
	   @RequestMapping(value = "/grn_print_report/pdf", method = RequestMethod.GET )
	    public  ModelAndView doSalesReportPDF(@RequestParam("GRNNoPrompt") String GRNNoPrompt,ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request) 
			 {
			logger.debug("Received request to download PDF report");
			response.setHeader("filename","grn_print_report.pdf");
			response.setContentType("application/pdf");
	        //response.setHeader("Content-Disposition", "attachment:filename=_blank‌​");
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			parameterMap.put("datasource", dataSource);
			parameterMap.put("GRNNoPrompt", GRNNoPrompt);
			
			String realPath  = request.getSession().getServletContext().getRealPath(System.getProperty("file.separator")+"WEB-INF"+System.getProperty("file.separator")+"images"+System.getProperty("file.separator")+"shree_logo.JPG");
			parameterMap.put("Image_Loc", realPath.toString());
			/*try {
				is = new FileInputStream(realPath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			
			// pdfReport is the View of our application
			// This is declared inside the /WEB-INF/jasper-views.xml
			modelAndView = new ModelAndView("pdfGrnPrintView", parameterMap);
			// Return the View and the Model combined
			return modelAndView;
		   }
	   
	   public Object[] getPaginationItemList(Integer next){
			
			
			ItemDTO dto=new ItemDTO();
			ItemOutMessage itemOutMessage=new ItemOutMessage();
			ItemInputMessage inputMessage=new ItemInputMessage();
			Object[] objects=new Object[2];
			if(next==null ||next<0)
			{
				next=0;
				dto.setNext(next);
				inputMessage.setItemDTO(dto);
				itemOutMessage= itemService.findItemForPagination(inputMessage);
				next=15;
				objects[0]=itemOutMessage;
				objects[1]=next;
			}
			else
			{
				dto.setNext(next);
				inputMessage.setItemDTO(dto);
				itemOutMessage= itemService.findItemForPagination(inputMessage);
				next=next+15;
				objects[0]=itemOutMessage;
				objects[1]=next;
			}
			return objects;
				
		}
		
		public List<MastersDTO> getDeptTypeList() {
			MastersOutputMessage outMsg = null;
			MastersInputMessage inMsg = new MastersInputMessage();
			inMsg.setFormId(8);
			outMsg = mastersService.findFormById(inMsg);
			List<MastersDTO> deptTypeList = outMsg.getMastersDTOList();
			return deptTypeList;
		}

}
