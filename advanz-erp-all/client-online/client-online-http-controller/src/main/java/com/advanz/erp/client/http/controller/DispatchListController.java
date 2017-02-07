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
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.masters.model.DispatchDetailDTO;
import com.advanz.erp.masters.model.DispatchMasterDTO;
import com.advanz.erp.masters.model.msg.DispatchDetailInputMessage;
import com.advanz.erp.masters.model.msg.DispatchDetailOutMessage;
import com.advanz.erp.masters.model.msg.DispatchMasterInputMessage;
import com.advanz.erp.masters.model.msg.DispatchMasterOutMessage;
import com.advanz.erp.masters.service.business.IDispatchDetailService;
import com.advanz.erp.masters.service.business.IDispatchMasterService;

@Controller
public class DispatchListController extends BaseController{

	@Autowired
	public IDispatchMasterService dispatchMasterService;

	@Autowired
	public IDispatchDetailService dispatchDetailService;

	@RequestMapping(value = "/show_dispatch_list", method = RequestMethod.GET)
	public ModelAndView display(@ModelAttribute("dispatch") DispatchForm dispatchForm,@RequestParam(value="menuId",required=false) String menuId,
			@RequestParam(value="operation",required=false) String operation, HttpSession session) {
		session.removeAttribute("operation");
		if(menuId!=null)
    	{
    		session.setAttribute("menuId", menuId);
    	}
		
		session.removeAttribute("dispatch");
		
		
		DispatchMasterOutMessage dispatchMasterOutMessage = new DispatchMasterOutMessage();
		dispatchMasterOutMessage = dispatchMasterService.findAllDispatchMaster();
		
		
		List l = dispatchMasterOutMessage.getDispatchMasterDTOList();
		
		// For Count total Item
		for(int i=0;i<l.size();i++){
		DispatchMasterDTO dispatchMasterDTO =	(DispatchMasterDTO)l.get(i);
		String dispatchNumber = dispatchMasterDTO.getDispatchNumber();
		
		DispatchDetailInputMessage detailInputMessage = new DispatchDetailInputMessage();
		DispatchDetailDTO detailDTO = new DispatchDetailDTO();
		detailDTO.setDispatchNumber(dispatchNumber);
		detailInputMessage.setDispatchDetailDTO(detailDTO);
		
		DispatchDetailOutMessage detailOutMessage = dispatchDetailService.findByDispatchId(detailInputMessage);
		
		ArrayList<DispatchDetailDTO> dispatchDetailList =(ArrayList<DispatchDetailDTO>) detailOutMessage.getDispatchDetailDTOList();
		
		Integer totalItem = dispatchDetailList.size();
		dispatchMasterDTO.setTotalItem(totalItem);
		}
		//
		
		
		ErrorDTO error = new ErrorDTO();
		try{
		if(operation.equals("Save")){
		error.setErrorMsg("Dispatch has been save successfully");
		}
		if(operation.equals("Delete")){
			error.setErrorMsg("Dispatch has been remove successfully");
		}
		if(operation.equals("Update")){
			error.setErrorMsg("Dispatch has been update successfully");
		}
		}catch (Exception e) {
			
		}
		
		ModelAndView mav = new ModelAndView("dispatch_list");
		mav.addObject("error", error);
		mav.addObject("dispatchForm", dispatchForm);
		mav.addObject("dispatchList", l);
		return mav;
	    }

	@RequestMapping(value = "/submitDispatchList", method = RequestMethod.GET)
	public ModelAndView submit(@ModelAttribute("dispatch") DispatchForm dispatchForm,
			@RequestParam String operation, HttpSession session) {
		ModelAndView mav = new ModelAndView("dispatch_list");
		if (operation.equals("Add")) {
			
			session.removeAttribute("dispatch");
			ModelAndView mv = new ModelAndView(new RedirectView("show_dispatch?operation=show"));
			return mv;
		}
		if (operation.equals("Delete")) {
			DispatchMasterInputMessage dispatchMasterInputMessage = new DispatchMasterInputMessage();
			System.out.println("DISPATCH AUTO ID : " + dispatchForm.getDispatchAutoId() + " DISPATCH NUMBER IS :::::::::::  " + dispatchForm.getDispatchNumber());
			DispatchMasterDTO masterDTO = new DispatchMasterDTO();
			masterDTO.setModifiedUserId(getCreatedUserId());
			masterDTO.setDispatchAutoId(dispatchForm.getDispatchAutoId());
			dispatchMasterInputMessage.setDispatchMasterDTO(masterDTO);
			
			dispatchMasterService.deleteDispatchMaster(dispatchMasterInputMessage);

			DispatchDetailInputMessage dispatchDetailInputMessage = new DispatchDetailInputMessage();
			DispatchDetailDTO detailDTO = new DispatchDetailDTO();
			
			detailDTO.setDispatchNumber(dispatchForm.getDispatchNumber());
			dispatchDetailInputMessage.setDispatchDetailDTO(detailDTO);
			DispatchDetailOutMessage detailOutMessage = new DispatchDetailOutMessage();
		
			detailOutMessage = dispatchDetailService.findByDispatchId(dispatchDetailInputMessage);
			ArrayList<DispatchDetailDTO> disapatchList = (ArrayList<DispatchDetailDTO>) detailOutMessage.getDispatchDetailDTOList();
			for (int i = 0; i < disapatchList.size(); i++) {
				detailDTO = new DispatchDetailDTO();
				detailDTO = disapatchList.get(i);
				detailDTO.setModifiedUserId(getCreatedUserId());
				dispatchDetailInputMessage = new DispatchDetailInputMessage();
				dispatchDetailInputMessage.setDispatchDetailDTO(detailDTO);
				dispatchDetailService.deleteDispatchDetail(dispatchDetailInputMessage);
			}
			DispatchMasterOutMessage dispatchMasterOutMessage = new DispatchMasterOutMessage();
			dispatchMasterOutMessage = dispatchMasterService
					.findAllDispatchMaster();
			List l = dispatchMasterOutMessage.getDispatchMasterDTOList();
			
			
			//For Count total Item
			for(int i=0;i<l.size();i++){
			DispatchMasterDTO dispatchMasterDTO =	(DispatchMasterDTO)l.get(i);
			String dispatchNumber = dispatchMasterDTO.getDispatchNumber();
			DispatchDetailInputMessage detailInputMessage = new DispatchDetailInputMessage();
			 detailDTO = new DispatchDetailDTO();
			detailDTO.setDispatchNumber(dispatchNumber);
			detailInputMessage.setDispatchDetailDTO(detailDTO);
		    detailOutMessage = dispatchDetailService.findByDispatchId(detailInputMessage);
			ArrayList<DispatchDetailDTO> dispatchDetailList =(ArrayList<DispatchDetailDTO>) detailOutMessage.getDispatchDetailDTOList();
			Integer totalItem = dispatchDetailList.size();
			dispatchMasterDTO.setTotalItem(totalItem);
			}
			//
			
			mav.addObject("dispatchForm", dispatchForm);
			mav.addObject("dispatchList", l);
			ErrorDTO dto=new ErrorDTO();
			dto.setErrorMsg("Dispatch has been remove successfully");
			mav.addObject("error", dto);
			return mav;
		}
		if (operation.equals("Search")) {
			DispatchMasterDTO masterDTO = dispatchForm.getDispatchMasterDTO();
			DispatchMasterInputMessage dispatchMasterInputMessage = new DispatchMasterInputMessage();
			dispatchMasterInputMessage.setDispatchMasterDTO(masterDTO);
			DispatchMasterOutMessage dispatchMasterOutMessage = dispatchMasterService.searchDispatch(dispatchMasterInputMessage);
			ArrayList<DispatchMasterDTO> list = (ArrayList<DispatchMasterDTO>) dispatchMasterOutMessage.getDispatchMasterDTOList();
			
			// For Count total Item
			for(int i=0;i<list.size();i++){
			DispatchMasterDTO dispatchMasterDTO =	(DispatchMasterDTO)list.get(i);
			String dispatchNumber = dispatchMasterDTO.getDispatchNumber();
			DispatchDetailInputMessage detailInputMessage = new DispatchDetailInputMessage();
			DispatchDetailDTO detailDTO = new DispatchDetailDTO();
			detailDTO.setDispatchNumber(dispatchNumber);
			detailInputMessage.setDispatchDetailDTO(detailDTO);
		    DispatchDetailOutMessage   detailOutMessage = dispatchDetailService.findByDispatchId(detailInputMessage);
			ArrayList<DispatchDetailDTO> dispatchDetailList =(ArrayList<DispatchDetailDTO>) detailOutMessage.getDispatchDetailDTOList();
			Integer totalItem = dispatchDetailList.size();
			dispatchMasterDTO.setTotalItem(totalItem);
			}
			//
			ErrorDTO  errorDTO=new ErrorDTO();
			if(list.equals(null) || list.size()==0)
			{
				errorDTO.setErrorMsg("Record Not Found !!!");
			}	
			
			mav.addObject("dispatchForm", dispatchForm);
			mav.addObject("dispatchList", list);
			mav.addObject("error123", errorDTO);
			return mav;
		}
		
		if (operation.equals("Cancel")) {
			ModelAndView mv = new ModelAndView(new RedirectView(
					"show_dispatch_list?operation=show"));
			return mv;
			
			
		}
		if (operation.equals("Edite")) {
			ModelAndView mv = new ModelAndView(new RedirectView("show_dispatch?operation=Edite"));
			mv.addObject("dispatchForm", dispatchForm);
			return mv;
		}
		if (operation.equals("V")) {
			ModelAndView mv = new ModelAndView(new RedirectView("show_dispatch?operation=V"));
			mv.addObject("dispatchForm", dispatchForm);
			return mv;
		}
		mav.addObject("dispatchForm", dispatchForm);
		return mav;
	}
}
