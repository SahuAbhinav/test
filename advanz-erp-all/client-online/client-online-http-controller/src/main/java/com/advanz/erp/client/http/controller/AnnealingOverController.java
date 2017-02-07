package com.advanz.erp.client.http.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.AnnealingOvenForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.AnnealingOvenDetailDTO;
import com.advanz.erp.masters.model.AnnealingOvenMasterDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.msg.AnnealingOvenInputMessage;
import com.advanz.erp.masters.model.msg.AnnealingOvenOutputMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.service.business.IAnnealingOvenService;
import com.advanz.erp.masters.service.business.IMastersService;

@Controller
@SessionAttributes({ "shiftMastersList" })
public class AnnealingOverController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(AnnealingOverController.class);

	@Autowired
	public IAnnealingOvenService annealingOvenService;

	@Autowired
	public IMastersService mastersService;

	@RequestMapping(value = "/saveAnnealingOven", method = RequestMethod.POST)
	public String saveSalesOrder(
			@ModelAttribute("annealingOvenForm") AnnealingOvenForm annealingOvenForm,
			Model model) {
		logger.info("annealingOvenForm = " + annealingOvenForm);
		String succ = "";
		AnnealingOvenInputMessage annealingOvenInputMessage = new AnnealingOvenInputMessage();
		AnnealingOvenMasterDTO annealingOvenMasterDTO = annealingOvenForm
				.getAnnealingOvenMasterDTO();
		annealingOvenInputMessage
				.setAnnealingOvenMasterDTO(annealingOvenMasterDTO);

		AnnealingOvenOutputMessage annealingOvenOutputMessage = null;
		if (annealingOvenForm.getAnnealingOvenMasterDTO().getOvenId() == null
				|| annealingOvenForm.getAnnealingOvenMasterDTO().getOvenId()
						.equals(0)) {
			annealingOvenMasterDTO.setCreatedUserId(getCreatedUserId());
			annealingOvenOutputMessage = annealingOvenService
					.createAnnealingOven(annealingOvenInputMessage);
			succ = "Ad";
		} else {
			annealingOvenMasterDTO.setModifiedUserId(getCreatedUserId());
			annealingOvenOutputMessage = annealingOvenService
					.updateAnnealingOven(annealingOvenInputMessage);
			succ = "Up";
		}
		ErrorListDTO errorListDTO = annealingOvenOutputMessage
				.getErrorListDTO();

		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO = errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors", errorDTO);
			return "annealingOven_add";
		}
		model.addAttribute("succ", succ);
		return "redirect:/get_annealingOven_list";
	}

	@RequestMapping(value = "/annealingOven_add", method = RequestMethod.GET)
	public ModelAndView addAnnealingOver(ModelMap model) {
		AnnealingOvenForm annealingOvenForm = new AnnealingOvenForm();
		AnnealingOvenMasterDTO annealingOvenMasterDTO = new AnnealingOvenMasterDTO();
		List<AnnealingOvenDetailDTO> ovenDetailDTOList = new ArrayList<AnnealingOvenDetailDTO>();
		AnnealingOvenOutputMessage annealingOvenOutputMessage = null;
		AnnealingOvenInputMessage annealingOvenInputMessage = new AnnealingOvenInputMessage();
		
		ovenDetailDTOList.add(new AnnealingOvenDetailDTO());
		annealingOvenMasterDTO.setAnnealingOvenDetailDTOList(ovenDetailDTOList);
		annealingOvenMasterDTO.setOvenDate(new Date());
		
		annealingOvenOutputMessage=annealingOvenService.lastAnnealingOvenDate(annealingOvenInputMessage);
		Timestamp timestamp= annealingOvenOutputMessage.getLastAnnealingOvenDate();
		//System.out.println.println("Last Finished good time"+new Date(timestamp.getTime()));
		SimpleDateFormat ft =new SimpleDateFormat ("yyyy,MM,dd");
		annealingOvenForm.setLastAnnealiingLastDate(ft.format(new Date(timestamp.getTime())));
		

		annealingOvenForm.setAnnealingOvenMasterDTO(annealingOvenMasterDTO);
		ModelAndView mav = new ModelAndView("annealingOven_add");
		mav.addObject("shiftMastersList", getShiftMastersList());
		mav.addObject("annealingOvenForm", annealingOvenForm);
		return mav;
	}

	@RequestMapping(value = "/get_annealingOven_list")
	public ModelAndView searchAnnealingOven(
			@ModelAttribute("annealingOvenForm") AnnealingOvenForm annealingOvenForm,
			Model model,
			@RequestParam(value = "menuId", required = false) String menuId,
			HttpSession session) {
		if (menuId != null) {
			session.setAttribute("menuId", menuId);
		}

		List<AnnealingOvenMasterDTO> list = new ArrayList<AnnealingOvenMasterDTO>();
		AnnealingOvenOutputMessage annealingOvenOutputMessage = null;
		ModelAndView mav = new ModelAndView("annealingOven_list");
		String succ = "Blk";
		if (annealingOvenForm.getAnnealingOvenMasterDTO() != null) {
			AnnealingOvenInputMessage annealingOvenInputMessage = new AnnealingOvenInputMessage();
			AnnealingOvenMasterDTO annealingOvenDTO = annealingOvenForm
					.getAnnealingOvenMasterDTO();
			if (annealingOvenDTO == null)
				annealingOvenDTO = new AnnealingOvenMasterDTO();
			annealingOvenInputMessage
					.setAnnealingOvenMasterDTO(annealingOvenDTO);
			annealingOvenOutputMessage = annealingOvenService
					.findAnnealingOven(annealingOvenInputMessage);
			list = (ArrayList<AnnealingOvenMasterDTO>) annealingOvenOutputMessage
					.getAnnealingOvenMasterDTOList();
			if (list.equals(null) || list.size() == 0) {
				model.addAttribute("succ", succ);
			}
		} else {
			annealingOvenOutputMessage = annealingOvenService
					.findAllAnnealingOven();
			list = (ArrayList<AnnealingOvenMasterDTO>) annealingOvenOutputMessage
					.getAnnealingOvenMasterDTOList();
		}

		mav.addObject("annealingOvenMasterDTOList", list);
		return mav;
	}

	@RequestMapping(value = "add_row_in_annealingOven")
	public ModelAndView addRow(
			@ModelAttribute("annealingOvenForm") AnnealingOvenForm annealingOvenForm) {
		AnnealingOvenMasterDTO annealingOvenMasterDTO = annealingOvenForm
				.getAnnealingOvenMasterDTO();

		List<AnnealingOvenDetailDTO> annealingOvenDetailDTOList = annealingOvenMasterDTO
				.getAnnealingOvenDetailDTOList();

		if (annealingOvenDetailDTOList == null) {
			annealingOvenDetailDTOList = new ArrayList<AnnealingOvenDetailDTO>();
			annealingOvenMasterDTO
					.setAnnealingOvenDetailDTOList(annealingOvenDetailDTOList);
		}
		annealingOvenDetailDTOList.add(new AnnealingOvenDetailDTO());

		ModelAndView mav = new ModelAndView("annealingOven_add");

		return mav;
	}

	@RequestMapping(value = "/remove_annealingOven_item", method = RequestMethod.POST)
	public ModelAndView removeAnnealingOvenItem(
			@ModelAttribute("annealingOvenForm") AnnealingOvenForm annealingOvenForm,
			ModelMap model, @RequestParam("id") Integer id) {
		ModelAndView mav = null;
		if (id != null) {
			List<AnnealingOvenDetailDTO> annealingOvenDetailDTOList = annealingOvenForm
					.getAnnealingOvenMasterDTO()
					.getAnnealingOvenDetailDTOList();

			if (annealingOvenDetailDTOList != null) {
				AnnealingOvenDetailDTO dto = annealingOvenDetailDTOList.get(id);
				annealingOvenDetailDTOList.remove(dto);
			}
			mav = new ModelAndView("annealingOven_add");
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/get_annealingOven", method = RequestMethod.GET)
	public ModelAndView getAnnealingOvenData(
			@RequestParam("ovenId") Integer id,
			@RequestParam("opr") String opr, ModelMap model) {
		AnnealingOvenForm annealingOvenForm = new AnnealingOvenForm();
		logger.info("Get AnnealingOven Order : " + id);
		logger.info("Opr : " + opr);

		AnnealingOvenOutputMessage annealingOvenOutputMessage = null;
		if (id != null && !id.equals(0)) {

			AnnealingOvenInputMessage annealingOvenInputMessage = new AnnealingOvenInputMessage();
			AnnealingOvenMasterDTO annealingOvenMasterDTO = new AnnealingOvenMasterDTO();
			annealingOvenMasterDTO.setOvenId(id);

			annealingOvenInputMessage
					.setAnnealingOvenMasterDTO(annealingOvenMasterDTO);
			annealingOvenOutputMessage = annealingOvenService
					.findAnnealingOvenById(annealingOvenInputMessage);
			ArrayList<AnnealingOvenMasterDTO> list = (ArrayList<AnnealingOvenMasterDTO>) annealingOvenOutputMessage
					.getAnnealingOvenMasterDTOList();
			if (list != null && list.size() > 0) {
				annealingOvenForm.setAnnealingOvenMasterDTO(list.get(0));
			}
		}

		model.put("annealingOvenForm", annealingOvenForm);
		model.put("opr", opr);
		ModelAndView mav = new ModelAndView("annealingOven_add");
		return mav;

	}

	@RequestMapping(value = "/remove_annealingOven", method = RequestMethod.GET)
	public String removeAnnealingOven(@RequestParam("ovenId") Integer ovenId,
			Model model) {
		logger.info("id id = " + ovenId);
		AnnealingOvenOutputMessage ovenOutputMessage = null;
		if (ovenId != null && !ovenId.equals(0)) {
			AnnealingOvenInputMessage annealingOvenInputMessage = new AnnealingOvenInputMessage();
			AnnealingOvenMasterDTO annealingOvenMasterDTO = new AnnealingOvenMasterDTO();
			annealingOvenMasterDTO.setOvenId(ovenId);
			annealingOvenMasterDTO.setModifiedUserId(getCreatedUserId());
			annealingOvenInputMessage
					.setAnnealingOvenMasterDTO(annealingOvenMasterDTO);
			ovenOutputMessage = annealingOvenService
					.deleteAnnealingOven(annealingOvenInputMessage);
		}
		String succ = "Dl";
		model.addAttribute("succ", succ);
		return "redirect:/get_annealingOven_list";

	}

	@ModelAttribute("shiftMastersList")
	private List<MastersDTO> getShiftMastersList() {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(11);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

}
