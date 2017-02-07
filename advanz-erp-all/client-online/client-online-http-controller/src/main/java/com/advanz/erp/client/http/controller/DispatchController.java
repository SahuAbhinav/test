package com.advanz.erp.client.http.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.advanz.erp.client.http.controller.form.DispatchForm;
import com.advanz.erp.client.http.controller.form.InvoiceForm;
import com.advanz.erp.client.http.controller.form.PartyForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.BillDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.CityDTO;
import com.advanz.erp.masters.model.DispatchDetailDTO;
import com.advanz.erp.masters.model.DispatchMasterDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.TransactionTypeDTO;
import com.advanz.erp.masters.model.TransporterDTO;
import com.advanz.erp.masters.model.msg.BillInputMessage;
import com.advanz.erp.masters.model.msg.BillOutMessage;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.model.msg.CityInputMessage;
import com.advanz.erp.masters.model.msg.CityOutputMessage;
import com.advanz.erp.masters.model.msg.DispatchDetailInputMessage;
import com.advanz.erp.masters.model.msg.DispatchDetailOutMessage;
import com.advanz.erp.masters.model.msg.DispatchMasterInputMessage;
import com.advanz.erp.masters.model.msg.DispatchMasterOutMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.PartyInputMessage;
import com.advanz.erp.masters.model.msg.PartyOutMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeInputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeOutputMessage;
import com.advanz.erp.masters.model.msg.TransporterOutMessage;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.service.business.ICityService;
import com.advanz.erp.masters.service.business.IDispatchDetailService;
import com.advanz.erp.masters.service.business.IDispatchMasterService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IPartyService;
import com.advanz.erp.masters.service.business.ITransactionTypeService;
import com.advanz.erp.masters.service.business.ITransporterService;

@Controller
public class DispatchController extends BaseController {
	@Autowired
	public IPartyService partyService;

	@Autowired
	public ITransporterService transporterService;

	@Autowired
	public IDispatchMasterService dispatchMasterService;

	@Autowired
	public IDispatchDetailService dispatchDetailService;

	@Autowired
	public IBranchService branchService;

	@Autowired
	public IItemService itemService;
	@Autowired
	public IMastersService mastersService;
	
	@Autowired
	public ITransactionTypeService transactionTypeService;
	
	@Autowired
	public ICityService cityService;
	@RequestMapping(value = "/show_dispatch", method = RequestMethod.GET)
	public ModelAndView display(
			@ModelAttribute("dispatch") DispatchForm dispatchForm,
			@RequestParam String operation, HttpSession session) {
		
		ModelAndView mav = preloadedData();
		Timestamp timestamp=null;
		SimpleDateFormat ft =null;
		
		ErrorDTO error = new ErrorDTO();
		if("Delete".equals(operation) || operation.equals("V")){
		//	error.setErrorCode("Dispatch Remove Confirmation");
		 error.setOperationName(operation);
		}
		
		String finecialYear= getFinYear();
		String series= getDispatchTransactionSeries();
		
		if (operation.equals("Edite") || "Delete".equals(operation)||operation.equals("V")) {
			if(operation.equals("Edite")){
				session.setAttribute("operation", operation);
			}
			DispatchMasterDTO dto = new DispatchMasterDTO();
			dto.setDispatchAutoId(dispatchForm.getDispatchAutoId());
			DispatchMasterInputMessage dispatchMasterInputMessage = new DispatchMasterInputMessage();
			dispatchMasterInputMessage.setDispatchMasterDTO(dto);
			DispatchMasterOutMessage dispatchMasterOutMessage = dispatchMasterService
					.findById(dispatchMasterInputMessage);
			ArrayList<DispatchMasterDTO> list = (ArrayList<DispatchMasterDTO>) dispatchMasterOutMessage
					.getDispatchMasterDTOList();

			if (list != null && list.size() > 0) {
				dto = list.get(0);
				dto.setTransactionSeries(series+"/"+finecialYear);
				dispatchForm.setDispatchMasterDTO(dto);

				// Get Party Information
				PartyInputMessage partyInputMessage = new PartyInputMessage();
				PartyDTO partyDTO = new PartyDTO();
				partyDTO.setPartyId(dto.getPartyDTO().getPartyId());
				partyInputMessage.setPartyDTO(partyDTO);
				PartyOutMessage partyOutMessage = partyService
						.findPartyById(partyInputMessage);
				List<PartyDTO> list1 = new ArrayList<PartyDTO>();
				list1 = (ArrayList<PartyDTO>) partyOutMessage.getPartyDTOList();
				if (list1 != null && list1.size() > 0) {
					partyDTO = list1.get(0);
				}
				
				// Get state name
				CityDTO cityDTO = new CityDTO();
				CityInputMessage cityInputMessage = new CityInputMessage();
				cityDTO.setCityId(partyDTO.getBillingCityId());
				cityInputMessage.setCityDTO(cityDTO);
				
				CityOutputMessage cityOutputMessage=	cityService.findCityById(cityInputMessage);
				ArrayList<CityDTO> cityList = (ArrayList<CityDTO>)cityOutputMessage.getCityDTOList();
				if(cityList!=null){
					cityDTO=cityList.get(0);
				}
				partyDTO.setState(cityDTO.getAreaDTO().getRegionDTO().getStateDTO().getStateName());
				
				
				
				dispatchForm.setBillingAddress(partyDTO.getBillingAddress());
				dispatchForm.setCityName(partyDTO.getCityName());
				dispatchForm.setContactPerson(partyDTO.getContactPerson1());
				dispatchForm.setPhonNo(partyDTO.getPanNo());

				DispatchDetailInputMessage dispatchDetailInputMessage = new DispatchDetailInputMessage();
				DispatchDetailDTO detailDTO = new DispatchDetailDTO();
				detailDTO.setDispatchNumber(dto.getDispatchNumber());
				dispatchDetailInputMessage.setDispatchDetailDTO(detailDTO);
				DispatchDetailOutMessage detailOutMessage = new DispatchDetailOutMessage();

				detailOutMessage = dispatchDetailService
						.findByDispatchId(dispatchDetailInputMessage);
				ArrayList<DispatchDetailDTO> disapatchList = (ArrayList<DispatchDetailDTO>) detailOutMessage
						.getDispatchDetailDTOList();

				DispatchDetailDTO dispatchDetailDTO = new DispatchDetailDTO();
				
				// To Get Item Information
				for (int j = 0; j < disapatchList.size(); j++) {
					dispatchDetailDTO = disapatchList.get(j);
					Integer itemId = dispatchDetailDTO.getItemId();
					ItemDTO itemDTO = new ItemDTO();
					itemDTO.setItemId(itemId);
					ItemInputMessage inputMessage = new ItemInputMessage();
					inputMessage.setItemDTO(itemDTO);
					ItemOutMessage itemOutMessage = itemService.findItemById(inputMessage);
					ArrayList<ItemDTO> itemList = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
					itemDTO = itemList.get(0);
					String itemName = itemDTO.getItemName();
					dispatchDetailDTO.setItemName(itemName);
					
					// Get UMO name
					try{
						MastersInputMessage mastersInputMessage = new MastersInputMessage();
						MastersDTO  mastersDTO = itemDTO.getMasterUnit();
						if(mastersDTO!=null){
						detailDTO.setUmoName(mastersDTO.getName());
						}
		
						}catch(Exception e){}
					
				    }
				
				// end item info
				//disapatchList.add(dispatchDetailDTO);
				
				dispatchForm.setDispatchDetailList(disapatchList);
			    }

			session.setAttribute("dispatch", dispatchForm);
			/*
			 * mav.setViewName("dispatch_add"); mav.addObject("dispatch",
			 * dispatchForm); return mav;
			 */
		}

		if (session.getAttribute("dispatch") == null) {
			
			
			
			
			
			DispatchMasterInputMessage inputMessage = new DispatchMasterInputMessage();
			DispatchMasterDTO masterDTO = new DispatchMasterDTO();
			masterDTO.setFinyr(getFinYear());
			masterDTO.setTransactionSeries(series + "-"	+ getFinYear());
			inputMessage.setDispatchMasterDTO(masterDTO);
			
			DispatchMasterOutMessage dispatchMasterOutMessage = dispatchMasterService.getNewDispatchMasterSeriesNo(inputMessage);
			//BillOutMessage billOutMessage = billService.getNewBillMasterSeriesNo(inputMessage);
		    Integer dispatchId=	dispatchMasterOutMessage.getDispatchSeries();
			
		     timestamp= dispatchMasterOutMessage.getDispatchSeriesDate();
			//System.out.println("Last Finished good time"+new Date(timestamp.getTime()));
			 ft =new SimpleDateFormat ("yyyy,MM,dd");
			
			
			
			
			/*//DispatchMasterOutMessage dispatchMasterOutMessage = dispatchMasterService.getMaxId();
			ArrayList<DispatchMasterDTO> list = (ArrayList<DispatchMasterDTO>) dispatchMasterOutMessage.getDispatchMasterDTOList();
			if (list.size() > 0) {
				masterDTO = list.get(0);
			}
			
			
			if (masterDTO.getDispatchAutoId() != null) {
				masterDTO1.setDispatchNumber(series+"-"+finecialYear+"-"+ (masterDTO.getDispatchId()+1));
				masterDTO1.setDispatchId(masterDTO.getDispatchId()+1);
			} else {
				masterDTO1.setDispatchNumber(series+"-"+finecialYear+"-" + 1);
				masterDTO1.setDispatchId(1);
			}*/
		    DispatchMasterDTO masterDTO1 = new DispatchMasterDTO();
			masterDTO1.setDispatchNumber(series+"/"+finecialYear+"/" + dispatchId);
			masterDTO1.setDispatchId(dispatchId);
			
			masterDTO1.setTransactionSeries(series+"/"+finecialYear);
			masterDTO1.setFinyr(finecialYear);
			masterDTO1.setDispatchDate(new Date());
			dispatchForm.setDispatchMasterDTO(masterDTO1);
			session.setAttribute("dispatch", dispatchForm);
		   }

		dispatchForm = (DispatchForm) session.getAttribute("dispatch");
		try {
			if(session.getAttribute("operation").equals("Edite")){
			operation=(String)session.getAttribute("operation");
			}
		} catch (Exception e) {
		}
		if(ft!=null && timestamp!=null)
			dispatchForm.setLastDispatchDate(ft.format(new Date(timestamp.getTime())));
		mav.addObject("operation", operation);
		mav.addObject("dispatch", dispatchForm);
		mav.addObject("error", error);
		mav.setViewName("dispatch_add");
		session.removeAttribute(operation);
		return mav;
	    }

	@RequestMapping(value = "/save_dispatch", method = RequestMethod.POST)
	public ModelAndView submit(
			@ModelAttribute("dispatch") DispatchForm dispatchForm,
			@RequestParam String operation, HttpSession session) {
		
		String finecialYear= getFinYear();
		String series= getDispatchTransactionSeries();
		
		ModelAndView mav = preloadedData();
		DispatchMasterInputMessage masterInputMessage = new DispatchMasterInputMessage();
		ErrorDTO error = new ErrorDTO();
		if (operation.equals("Invoice No")) {

			// mav = new ModelAndView(new RedirectView("show_invoice_list"));
			mav = new ModelAndView(new RedirectView("show_dispatch_invoice_list"));

			if (session.getAttribute("dispatch") != null) {
				session.setAttribute("dispatch", dispatchForm);
	}
	return mav;
	}
if (operation.equalsIgnoreCase("Cancel")) {
			ModelAndView mv = new ModelAndView(new RedirectView("show_dispatch_list?operation=show"));
			return mv;
		}
		if (operation.equals("remove")) {
            int indexNo=dispatchForm.getIndexNo();
			dispatchForm = (DispatchForm) session.getAttribute("dispatch");
			DispatchDetailDTO dispatchDetailDTO= dispatchForm.getDispatchDetailList().get(indexNo);
			
	
			dispatchForm.getDispatchDetailList().remove(indexNo);
			
			DispatchDetailInputMessage dispatchDetailInputMessage = new DispatchDetailInputMessage();
			dispatchDetailInputMessage.setDispatchDetailDTO(dispatchDetailDTO);
			
			if (dispatchDetailDTO.getSno() != null && dispatchDetailDTO.getSno() > 0) {
				//dispatchDetailService.deleteDispatchDetail(dispatchDetailInputMessage);
			}
			
			

			session.setAttribute("dispatch", dispatchForm);
			mav.addObject("dispatch", dispatchForm);
			mav.setViewName("dispatch_add");
			return mav;
		}
		if (operation.equals("Save")) {
			
			
			if (dispatchForm.getDispatchDetailList() != null && dispatchForm.getDispatchDetailList().size()>0) {
				DispatchMasterDTO masterDTO = new DispatchMasterDTO();
				masterDTO = dispatchForm.getDispatchMasterDTO();
				masterDTO.setTransactionSeries("DP");
				masterDTO.setFinyr(finecialYear);
				masterInputMessage.setDispatchMasterDTO(masterDTO);
				if (dispatchForm.getDispatchMasterDTO().getDispatchAutoId() != null
						&& dispatchForm.getDispatchMasterDTO().getDispatchAutoId() > 0) {
					

					// masterDTO.setDispatchAutoId(dispatchForm.getDispatchAutoId());
					// masterInputMessage.setDispatchMasterDTO(masterDTO);
					masterDTO.setModifiedUserId(getCreatedUserId());
					dispatchMasterService.updateDispatchMaster(masterInputMessage);
					operation="Update";
					error.setErrorMsg("Dispatch update successfully");
				} else {
					masterDTO.setCreatedUserId(getCreatedUserId());
				   DispatchMasterOutMessage dispatchMasterOutMessage =	dispatchMasterService.createDispatchMaster(masterInputMessage);
				   ErrorListDTO erorList= new ErrorListDTO();
				   ErrorDTO errorDTO1 = new ErrorDTO();
				    erorList=(ErrorListDTO)dispatchMasterOutMessage.getErrorListDTO();
				    try {
						errorDTO1 =  erorList.getErrorList().get(0);
					
				   if(errorDTO1!=null){
					 dispatchForm = (DispatchForm) session.getAttribute("dispatch");
					 masterDTO= dispatchForm.getDispatchMasterDTO();
					 String dispatchNo= errorDTO1.getErrorCode();
					 String[] str=  dispatchNo.split("/");
					
					 
					String dispSeries=str[0];
					String dispfnyear=str[1];
					String dispAutoId=str[2];
					Integer dispAuto=Integer.parseInt(dispAutoId);
					
					
					masterDTO.setDispatchNumber(series+"/"+finecialYear+"/"+ (dispAuto+1));
					masterDTO.setDispatchId(dispAuto+1);
					masterDTO.setFinyr(finecialYear);
					masterDTO.setTransactionSeries(series+"/"+finecialYear);
					dispatchForm.setDispatchMasterDTO(masterDTO);
					
					mav.addObject("dispatch", dispatchForm);
					mav.addObject("error", errorDTO1);
					mav.setViewName("dispatch_add");
					return mav;
				   }
				   
                    } catch (Exception e) {
						
					}
				
				
				error.setErrorMsg("Dispatch save successfully");
				}
				List dispatchDetailList = dispatchForm.getDispatchDetailList();
				
				//Form HERE to update disapatch detail
				
				if (dispatchForm.getDispatchMasterDTO().getDispatchAutoId() != null && dispatchForm.getDispatchMasterDTO().getDispatchAutoId() > 0) {
					DispatchDetailInputMessage detailInputMessage  =new DispatchDetailInputMessage();
					DispatchDetailDTO detailDTO =new DispatchDetailDTO();
					detailDTO.setDispatchNumber(dispatchForm.getDispatchMasterDTO().getDispatchNumber());
					detailInputMessage.setDispatchDetailDTO(detailDTO);
					DispatchDetailOutMessage detailOutMessage= dispatchDetailService.findByDispatchId(detailInputMessage);
					List<DispatchDetailDTO> dl= detailOutMessage.getDispatchDetailDTOList();
					for(int i=0;i<dl.size();i++){
						DispatchDetailDTO detailDTO2= dl.get(i);
						for(int j=0;j<dispatchDetailList.size();j++){
							DispatchDetailDTO detailDTO3=(DispatchDetailDTO)dispatchDetailList.get(j);
							if(detailDTO2.getSno()!=detailDTO3.getSno()){
								detailInputMessage.setDispatchDetailDTO(detailDTO2);
								dispatchDetailService.deleteDispatchDetail(detailInputMessage);
							}}}
				}
				//TO HERE to update disapatch detail
				
				
				for (int i = 0; i < dispatchDetailList.size(); i++) {
					DispatchDetailDTO detailDTO = (DispatchDetailDTO) dispatchDetailList
							.get(i);
					detailDTO.setTransactionSeries(series);
					detailDTO.setDispatchNumber(masterDTO.getDispatchNumber());
					if (masterDTO.getDispatchAutoId() != null) {
						detailDTO.setDispatchAutoId(masterDTO.getDispatchAutoId());
					} else {
						detailDTO.setDispatchAutoId(0);
					}

					DispatchDetailInputMessage detailInputMessage = new DispatchDetailInputMessage();
					detailInputMessage.setDispatchDetailDTO(detailDTO);
					if (dispatchForm.getDispatchMasterDTO().getDispatchAutoId() != null && dispatchForm.getDispatchMasterDTO().getDispatchAutoId() > 0) {
						detailDTO.setModifiedUserId(getCreatedUserId());
						dispatchDetailService.updateDispatchDetail(detailInputMessage);
					} else {
						// To get max Dispatch Auto Id
					 DispatchMasterOutMessage dispatchMasterOutMessage= 	dispatchMasterService.getMaxId();
					 ArrayList<DispatchMasterDTO> dl =(ArrayList<DispatchMasterDTO>) dispatchMasterOutMessage.getDispatchMasterDTOList();
					 if(dl!=null && dl.size()>0){
						 masterDTO= dl.get(0);
						 detailDTO.setDispatchAutoId(masterDTO.getDispatchAutoId());
						 detailInputMessage.setDispatchDetailDTO(detailDTO);	 
				}
				detailDTO.setCreatedUserId(getCreatedUserId());
				dispatchDetailService.createDispatchDetail(detailInputMessage);
				}
				}
				session.removeAttribute("dispatch");
				ModelAndView mv = new ModelAndView(new RedirectView(
						"show_dispatch_list?operation="+operation));
				/*//mav.addObject("error", error);
				error.setErrorMsg("Dispatch save successfully");
				mv.addObject("error", error);*/
				return mv;
			} else {
				
				
				error.setErrorMsg("Sorry you can't save dispatch without item");
				dispatchForm = (DispatchForm) session.getAttribute("dispatch");

				mav.addObject("dispatch", dispatchForm);
				mav.addObject("error", error);
				mav.setViewName("dispatch_add");
				return mav;
			}
			/*
			 * session.removeAttribute("dispatch"); ModelAndView mv = new
			 * ModelAndView(new
			 * RedirectView("show_dispatch_list?operation=show")); return mv;
			 */
		}
		mav.setViewName("dispatch_add");
		return mav;
	   }

	protected ModelAndView preloadedData() {
		ModelAndView mav = new ModelAndView();
		PartyOutMessage partyOutMessage = partyService.preloadedPartys();
		List<PartyDTO> partyList = (ArrayList<PartyDTO>) partyOutMessage
				.getPartyDTOList();

		TransporterOutMessage transporterOutMessage = transporterService
				.preload();
		List<TransporterDTO> transporterList = (ArrayList<TransporterDTO>) transporterOutMessage
				.getTransporterDTOList();
		BranchOutMessage branchOutMessage = branchService.findAllBranches();
		List<BranchDTO> branchList = (List<BranchDTO>) branchOutMessage
				.getBranchDTOList();

		mav.addObject("partyList", partyList);
		mav.addObject("transporterList", transporterList);
		mav.addObject("branchList", branchList);
		return mav;
	}

	@RequestMapping(value = "/getDispatchPartyInfo", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse addUser(
			@ModelAttribute(value = "partyForm") PartyForm partyForm,
			BindingResult result, @RequestParam Integer partyId, HttpSession session) {
		JsonResponse res = new JsonResponse();
		PartyInputMessage partyInputMessage = new PartyInputMessage();
		PartyDTO partyDTO = new PartyDTO();
		partyDTO.setPartyId(partyId);
		partyInputMessage.setPartyDTO(partyDTO);
		
		session.setAttribute("partyId", partyId);
		
		PartyOutMessage partyOutMessage = partyService
				.findPartyById(partyInputMessage);
		List<PartyDTO> list = new ArrayList<PartyDTO>();
		list = (ArrayList<PartyDTO>) partyOutMessage.getPartyDTOList();
		partyDTO = list.get(0);
		
		
		// Get state name
				CityDTO cityDTO = new CityDTO();
				CityInputMessage cityInputMessage = new CityInputMessage();
				cityDTO.setCityId(partyDTO.getBillingCityId());
				cityInputMessage.setCityDTO(cityDTO);
				
			CityOutputMessage cityOutputMessage=	cityService.findCityById(cityInputMessage);
			ArrayList<CityDTO> cityList = (ArrayList<CityDTO>)cityOutputMessage.getCityDTOList();
				if(cityList!=null){
					cityDTO=cityList.get(0);
				}
				partyDTO.setState(cityDTO.getAreaDTO().getRegionDTO().getStateDTO().getStateName());
		
		
		List<PartyDTO> userList = new ArrayList<PartyDTO>();
		if (!result.hasErrors()) {
			userList.add(partyDTO);
			res.setStatus("SUCCESS");
			res.setResult(userList);

		} else {
			res.setStatus("FAIL");
			res.setResult(result.getAllErrors());
		}
		return res;
	}
	
	
	
	private String getDispatchTransactionSeries() {
		TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
		transactionTypeDTO.setSno(5);
		TransactionTypeInputMessage transactionTypeInputMessage = new TransactionTypeInputMessage();
		transactionTypeInputMessage.setTransactionTypeDTO(transactionTypeDTO);
		TransactionTypeOutputMessage transactionTypeOutputMessage = transactionTypeService
				.findTransactionTypeById(transactionTypeInputMessage);
		List<TransactionTypeDTO> list = transactionTypeOutputMessage
				.getTransactionTypeDTOList();
		if (list != null && list.size() == 1)
			return list.get(0).getSeries();
		return null;

	}
}