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

import com.advanz.erp.client.http.controller.form.GetPassForm;
import com.advanz.erp.client.http.controller.form.InvoiceForm;
import com.advanz.erp.client.http.controller.form.ReturnGetPassForm;
import com.advanz.erp.masters.model.GetPassDetailDTO;
import com.advanz.erp.masters.model.GetPassMasterDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.ReturnGetPassDetailDTO;
import com.advanz.erp.masters.model.ReturnGetPassMasterDTO;
import com.advanz.erp.masters.model.msg.GetPassMasterInputMessage;
import com.advanz.erp.masters.model.msg.GetPassMasterOutputMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.service.business.IGetPassMasterService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IReturnGetPassMasterService;

@Controller
public class GetPassListController extends BaseController {
	@Autowired
	public IGetPassMasterService getPassMasterService;
	@Autowired
	public IReturnGetPassMasterService returnGetPassMasterService;
	@Autowired
	public IItemService itemService;

	@RequestMapping(value = "/show_get_pass_list", method = RequestMethod.GET)
	public ModelAndView display(
			@ModelAttribute("getPass") GetPassForm getPassForm,
			@RequestParam(value = "operation", required = false) String operation,
			HttpSession session,
			@RequestParam(value = "next", required = false) Integer next,
			@RequestParam(value = "menuId", required = false) String menuId) {
            session.removeAttribute("getPass");
            
           
		   // GetPassMasterOutputMessage getPassMasterOutputMessage = getPassMasterService.findAllGetPassMaster();
            GetPassMasterOutputMessage getPassMasterOutputMessage = null; 
		    GetPassMasterInputMessage getPassMasterInputMsg  =new GetPassMasterInputMessage();
	 if ("GatePassNo"!=operation) {
		    if(next==null ||next<0)
			{
			next=0;
			getPassMasterInputMsg.setNext(next);
			getPassMasterOutputMessage = getPassMasterService.findGetPassPagination(getPassMasterInputMsg);
			}
			else
			{
				getPassMasterInputMsg.setNext(next);
				getPassMasterOutputMessage = getPassMasterService.findGetPassPagination(getPassMasterInputMsg);
			}
		    getPassForm.setNext(next);
		    getPassForm.setPrevious(next);
            }
		List<GetPassMasterDTO> getPassList = getPassMasterOutputMessage.getGetPassMasterDTOList();
		if (menuId != null) {
			session.setAttribute("menuId", menuId);
		}
		if ("GatePassNo".equals(operation)) {
			GetPassMasterInputMessage getPassMasterInputMessage  =new GetPassMasterInputMessage();
			GetPassMasterDTO dto =new GetPassMasterDTO();
			dto.setGatePassType("Returnable");
			getPassMasterInputMessage.setGetPassMasterDTO(dto);
			
			//getPassMasterOutputMessage = getPassMasterService.findByGetPassType(getPassMasterInputMessage);
		  //  getPassList = getPassMasterOutputMessage.getGetPassMasterDTOList();
			
			
		    if(next==null ||next<0)
			{
			next=0;
			getPassMasterInputMessage.setNext(next);
			getPassMasterOutputMessage = getPassMasterService.findByGetPassType(getPassMasterInputMessage);
			}
			else
			{
				getPassMasterInputMessage.setNext(next);
				getPassMasterOutputMessage = getPassMasterService.findByGetPassType(getPassMasterInputMessage);
			}
		    getPassList = getPassMasterOutputMessage.getGetPassMasterDTOList();
		   for(int i=0;i<getPassList.size();i++){
			   GetPassMasterDTO getPasstMasterDTO=getPassList.get(i);
			  List<GetPassDetailDTO> detaiList= getPasstMasterDTO.getGetPassDetailDTOList();
		    for(int j=0;j<detaiList.size();j++){
		    	GetPassDetailDTO detailDTO=detaiList.get(j);
		    	
		    	if(detailDTO.getPendingQuantity()!=null && detailDTO.getPendingQuantity()<=0){
		    		detaiList.remove(detailDTO);
		    		j=j-1;
		    	}
		    	if(getPasstMasterDTO.getGetPassDetailDTOList().size()==0){
		    		getPassList.remove(getPasstMasterDTO);
		    		i=i-1;
		    	}}}

		    getPassForm.setNext(next);
		    getPassForm.setPrevious(next);
			
			ModelAndView mav = new ModelAndView("get_pass_list_for_return_gate");
			mav.addObject("getPassList", getPassList);
			mav.addObject("getPassForm", getPassForm);
			return mav;
		}
		getPassForm.setMessage(operation);
		ModelAndView mav = new ModelAndView("get_pass_list");
		mav.addObject("getPassList", getPassList);
		mav.addObject("getPassForm", getPassForm);
		return mav;
	}

	@RequestMapping(value = "/submitGetPassList", method = RequestMethod.GET)
	public ModelAndView submit(
			@ModelAttribute("getPass") GetPassForm getPassForm,
			@RequestParam String operation,@RequestParam(value = "next", required = false) Integer next, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if (operation.equals("Delete")) {
			Integer getPassAutoId = getPassForm.getGatePassAutoId();
			GetPassMasterDTO dto = new GetPassMasterDTO();
			dto.setGatePassAutoId(getPassAutoId);
			GetPassMasterInputMessage getPassMasterInputMessage = new GetPassMasterInputMessage();
			dto.setModifiedUserId(getCreatedUserId());
			getPassMasterInputMessage.setGetPassMasterDTO(dto);
			getPassMasterService.deleteGetPassMaster(getPassMasterInputMessage);

			//GetPassMasterOutputMessage getPassMasterOutputMessage = getPassMasterService.findAllGetPassMaster();
			
			
			
			GetPassMasterOutputMessage getPassMasterOutputMessage = null; 
		    GetPassMasterInputMessage getPassMasterInputMsg  =new GetPassMasterInputMessage();
		    if(next==null ||next<0)
			{
			next=0;
			getPassMasterInputMsg.setNext(next);
			getPassMasterOutputMessage = getPassMasterService.findGetPassPagination(getPassMasterInputMsg);
			}
			else
			{
				getPassMasterInputMsg.setNext(next);
				getPassMasterOutputMessage = getPassMasterService.findGetPassPagination(getPassMasterInputMsg);
			}
		    getPassForm.setNext(next);
		    getPassForm.setPrevious(next);
			
			
			
			List<GetPassMasterDTO> getPassList = getPassMasterOutputMessage.getGetPassMasterDTOList();
			
			
			
			mav = new ModelAndView("get_pass_list");
			mav.addObject("getPassList", getPassList);
			mav.addObject("getPassForm", getPassForm);
			return mav;
		}

		if ("Add Item In Return Gate".equals(operation)) {
			GetPassMasterDTO gatePassMaster = getPassForm.getGetPassMasterDTO();
			List<GetPassDetailDTO> detailList1 = getPassMasterService.getPassDetailList(gatePassMaster.getGatePassNumber());
			ReturnGetPassForm returnGetPassForm = (ReturnGetPassForm) session.getAttribute("returnGetPass");
			ReturnGetPassMasterDTO getPassMasterDTO = returnGetPassForm.getReturnGetPassMasterDTO();
			PartyDTO partyDTO =new PartyDTO();
			partyDTO.setPartyId(gatePassMaster.getPartyDTO().getPartyId());
			getPassMasterDTO.setPartyDTO(partyDTO);
			getPassMasterDTO.setGatePassNumber(gatePassMaster.getGatePassNumber());
			List<ReturnGetPassDetailDTO>  detailList = new ArrayList<ReturnGetPassDetailDTO>();
			for (int i = 0; i < detailList1.size(); i++) {
				GetPassDetailDTO detailDTO = detailList1.get(i);
				ItemDTO dto = detailDTO.getItemDTO();

				ItemInputMessage itemInputMessage = new ItemInputMessage();
				itemInputMessage.setItemDTO(dto);
				ItemOutMessage itemOutMessage = itemService.findItemById(itemInputMessage);
				ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
				dto = (ItemDTO) list.get(0);
			
				
				/*if (getPassMasterDTO.getReturnGetPassDetailDTOList() != null) {
					detailList = (List<ReturnGetPassDetailDTO>) getPassMasterDTO.getReturnGetPassDetailDTOList();
				} else {
					detailList = new ArrayList<ReturnGetPassDetailDTO>();
				}*/
				ReturnGetPassDetailDTO detailDTO1 = new ReturnGetPassDetailDTO();
				detailDTO1.setItemDTO(dto);
				detailDTO1.setItemName(dto.getItemName());
				detailDTO1.setRemark(detailDTO.getRemark());
				detailDTO1.setReturnGatePassQuantity(detailDTO.getPendingQuantity());
				
				MastersDTO mastersDTO = dto.getMasterUnit();
				if (mastersDTO != null) {
					detailDTO1.setMeasurementUnitName(mastersDTO.getName());
					detailDTO1.setMeasurementUnitId(mastersDTO.getMastersId());
				}
				detailList.add(detailDTO1);
				getPassMasterDTO.setReturnGetPassDetailDTOList(detailList);
				returnGetPassForm.getReturnGetPassMasterDTO().setReturnGetPassDetailDTOList(detailList);
				
			}
			session.setAttribute("returnGetPass", returnGetPassForm);
			ModelAndView mv = new ModelAndView(new RedirectView(
					"show_return_get_pass?operation=show"));
			mv.addObject("returnGetPass", returnGetPassForm);

			return mv;
		}
		
		if("SearchGetPassReturn".equals(operation)){
	GetPassMasterDTO getPassMasterDTO = getPassForm.getGetPassMasterDTO();
	GetPassMasterInputMessage getPassMasterInputMessage = new GetPassMasterInputMessage();
	getPassMasterDTO.setGatePassType("Returnable");
	getPassMasterInputMessage.setGetPassMasterDTO(getPassMasterDTO);
	GetPassMasterOutputMessage getPassMasterOutputMessage = getPassMasterService.search(getPassMasterInputMessage);
	List<GetPassMasterDTO> getPassList = getPassMasterOutputMessage
			.getGetPassMasterDTOList();
			 mav = new ModelAndView("get_pass_list_for_return_gate");
			mav.addObject("getPassList", getPassList);
			mav.addObject("getPassForm", getPassForm);
			return mav;
		}
		if (operation.equals("Search")) {
			GetPassMasterDTO getPassMasterDTO = getPassForm
					.getGetPassMasterDTO();
			GetPassMasterInputMessage getPassMasterInputMessage = new GetPassMasterInputMessage();
			getPassMasterInputMessage.setGetPassMasterDTO(getPassMasterDTO);
			GetPassMasterOutputMessage getPassMasterOutputMessage = getPassMasterService
					.search(getPassMasterInputMessage);
			List<GetPassMasterDTO> getPassList = getPassMasterOutputMessage
					.getGetPassMasterDTOList();

			mav = new ModelAndView("get_pass_list");
			mav.addObject("getPassList", getPassList);
			mav.addObject("getPassForm", getPassForm);
			return mav;
		}
		if (operation.equals("cancelReturnGatePass")) {
			ModelAndView mv = new ModelAndView(new RedirectView(
					"show_return_get_pass?operation=Show"));

			return mv;
		}
		if (operation.equals("Cancel")) {
			ModelAndView mv = new ModelAndView(new RedirectView(
					"show_get_pass_list?operation=Show"));

			return mv;
		}
		
		if (operation.equals("Add")) {
			session.removeAttribute("getPass");
			ModelAndView mv = new ModelAndView(new RedirectView(
					"show_get_pass?operation=Show"));

			return mv;
		}

		return mav;
	}

}
