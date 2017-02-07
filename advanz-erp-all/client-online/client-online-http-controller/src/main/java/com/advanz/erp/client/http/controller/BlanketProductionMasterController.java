package com.advanz.erp.client.http.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.DataUtility;
import com.advanz.erp.client.http.controller.form.BlanketProductionMasterForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.BlanketProductionDetailLeftDTO;
import com.advanz.erp.masters.model.BlanketProductionDetailNewDTO;
import com.advanz.erp.masters.model.BlanketProductionDetailRightDTO;
import com.advanz.erp.masters.model.BlanketProductionMasterDTO;
import com.advanz.erp.masters.model.BlanketProductionMasterNewDTO;
import com.advanz.erp.masters.model.CompanyDTO;
import com.advanz.erp.masters.model.FinishedGoodsMasterDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.ShiftReportMasterDTO;
import com.advanz.erp.masters.model.TransactionTypeDTO;
import com.advanz.erp.masters.model.criteria.BlanketProductionSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterInputMessage;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterNewInputMessage;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterNewOutputMessage;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterOutputMessage;
import com.advanz.erp.masters.model.msg.CompanyOutMessage;
import com.advanz.erp.masters.model.msg.FinishedGoodsMasterInputMessage;
import com.advanz.erp.masters.model.msg.FinishedGoodsMasterOutputMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeInputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeOutputMessage;
import com.advanz.erp.masters.service.business.IBlanketProductionMasterNewService;
import com.advanz.erp.masters.service.business.IBlanketProductionMasterService;
import com.advanz.erp.masters.service.business.ICompanyService;
import com.advanz.erp.masters.service.business.IFinishedGoodsMasterService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.ITransactionTypeService;

@Controller
@SessionAttributes({ "shiftMastersList", "gradeMastersList" })
public class BlanketProductionMasterController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(BlanketProductionMasterController.class);

	@Autowired
	private IBlanketProductionMasterService blanketProductionMasterService;

	@Autowired
	private IMastersService mastersService;

	@Autowired
	private IItemService itemService;
	@Autowired
	public IFinishedGoodsMasterService finishedGoodsMasterService;

	@Autowired
	public ITransactionTypeService transactionTypeService;

	@Autowired
	ICompanyService companyService;

	@Autowired
	private IBlanketProductionMasterNewService blanketProductionMasterNewService;

	@RequestMapping(value = "/new_blanketProduction", method = RequestMethod.GET)
	public ModelAndView newBlanketProduction(ModelMap model) {
		ModelAndView mav = new ModelAndView("blanketProduction-entry");
		BlanketProductionMasterForm bpmForm = new BlanketProductionMasterForm();
		int leftbpListSize = 0;
		int rightbpListSize = 0;
		BlanketProductionMasterDTO bpmDTO = new BlanketProductionMasterDTO();

		List<BlanketProductionDetailLeftDTO> bpdDTOLeftList = new ArrayList<BlanketProductionDetailLeftDTO>();
		BlanketProductionDetailLeftDTO blpl = new BlanketProductionDetailLeftDTO();

		ArrayList<Integer> as = blanketProductionMasterService
				.getMaxRollNoL(null);
		int rollNoL = as.get(0);
		blpl.setRollNoLeft(rollNoL);
		bpdDTOLeftList.add(blpl);
		leftbpListSize = bpdDTOLeftList.size();
		bpmDTO.setBlanketProductionDetailLeftDTOList(bpdDTOLeftList);

		List<BlanketProductionDetailRightDTO> bpdDTORightList = new ArrayList<BlanketProductionDetailRightDTO>();
		BlanketProductionDetailRightDTO blpr = new BlanketProductionDetailRightDTO();

		ArrayList<Integer> as1 = blanketProductionMasterService
				.getMaxRollNoR(null);
		int rollNoR = as1.get(0);
		blpr.setRollNoRight(rollNoR);

		bpdDTORightList.add(blpr);
		bpmDTO.setBlanketProductionDetailRightDTOList(bpdDTORightList);
		rightbpListSize = bpdDTORightList.size();
		/* bpmDTO.setBlanketProductionDate(new Date()); */

		// set Last entry date validation
		Timestamp timestamp = blanketProductionMasterService
				.getLastBlanketEntryDate();
		System.out.println("timestamp" + timestamp);
		SimpleDateFormat ft = new SimpleDateFormat("yyyy,MM,dd");
		if (timestamp != null) {
			bpmDTO.setBlanketProductionDate(new Date(timestamp.getTime()));
			bpmForm.setLastBlanketEntryDate(ft.format(new Date(timestamp
					.getTime())));
		}
		CompanyOutMessage outCompanyOutMessage = companyService
				.findAllCompanies();
		List<CompanyDTO> cList = outCompanyOutMessage.getCompanyDTOList();
		CompanyDTO companyDTO = null;
		if (cList != null && cList.size() > 0) {
			companyDTO = (CompanyDTO) cList.get(0);
			// System.out.println("dto.getStockLockFlag()"+dto.getStockLockFlag());
		}
		if (companyDTO != null) {
			if (companyDTO.getBlanketCutoffDate() != null) {
				bpmForm.setBlanketCutoffDate(ft.format(new Date(companyDTO
						.getBlanketCutoffDate().getTime())));
			}
		}

		bpmForm.setBlanketProductionMasterDTO(bpmDTO);
		mav.addObject("shiftMastersList", getShiftMastersList());
		mav.addObject("gradeMastersList", getGradeMastersList());
		mav.addObject("blanketProductionMasterForm", bpmForm);
		mav.addObject("leftbpListSize", leftbpListSize);
		mav.addObject("rightbpListSize", rightbpListSize);
		return mav;
	}

	@RequestMapping(value = "saveBlanketMaster")
	public ModelAndView saveBlanketMaster(
			@ModelAttribute("blanketProductionMasterForm") BlanketProductionMasterForm blanketProductionMasterForm,
			@RequestParam(value = "operation", required = false) String operation) {
		ModelAndView mav = new ModelAndView("blanketProduction-entry");
		BlanketProductionMasterDTO blanketProductionMasterDTO = blanketProductionMasterForm
				.getBlanketProductionMasterDTO();
		Integer blankeProductionId = 0;
		if (blanketProductionMasterDTO.getBlanketProductionId() != null
				&& blanketProductionMasterDTO.getBlanketProductionId() > 0) {
			blankeProductionId = blanketProductionMasterDTO
					.getBlanketProductionId();
			BlanketProductionMasterInputMessage blanketProductionMasterInputMessage = new BlanketProductionMasterInputMessage();

			List<BlanketProductionDetailLeftDTO> ll = blanketProductionMasterForm
					.getBlanketProductionMasterDTO()
					.getBlanketProductionDetailLeftDTOList();
			if (ll != null) {
				Iterator<BlanketProductionDetailLeftDTO> itrLeft = ll
						.iterator();
				while (itrLeft.hasNext()) {
					BlanketProductionDetailLeftDTO bpdLeftDto = itrLeft.next();
					bpdLeftDto.setStatusLeft(bpdLeftDto.getBlanketType());
					bpdLeftDto.setRejStatus(bpdLeftDto.getBlanketType());
					logger.info("***** : " + bpdLeftDto);
					if (bpdLeftDto.getDensityLeft() == null) {
						itrLeft.remove();
					}
				}
			}

			List<BlanketProductionDetailRightDTO> rl = blanketProductionMasterForm
					.getBlanketProductionMasterDTO()
					.getBlanketProductionDetailRightDTOList();
			if (rl != null) {
				Iterator<BlanketProductionDetailRightDTO> itrLeft = rl
						.iterator();
				while (itrLeft.hasNext()) {
					BlanketProductionDetailRightDTO bpdLeftDto = itrLeft.next();
					bpdLeftDto.setStatusRight(bpdLeftDto.getBlanketType());
					bpdLeftDto.setRejStatusRight(bpdLeftDto.getBlanketType());
					logger.info("***** : " + bpdLeftDto);
					if (bpdLeftDto.getDensityRight() == null) {
						itrLeft.remove();
					}
				}
			}

			blanketProductionMasterInputMessage
					.setBlanketProductionMasterDTO(blanketProductionMasterDTO);
			blanketProductionMasterService
					.updateBlanketProductionMaster(blanketProductionMasterInputMessage);
		}/*
		 * else{ BlanketProductionMasterInputMessage
		 * blanketProductionMasterInputMessage=new
		 * BlanketProductionMasterInputMessage();
		 * blanketProductionMasterInputMessage
		 * .setBlanketProductionMasterDTO(blanketProductionMasterDTO);
		 * blanketProductionMasterService
		 * .createBlanketProductionMaster(blanketProductionMasterInputMessage);
		 * //blankeProductionId=
		 * blanketProductionMasterService.getMaxBlanketProdId(); }
		 */
		blanketProductionMasterDTO.setBlanketProductionId(blankeProductionId);

		List<BlanketProductionDetailLeftDTO> bpdLeftDTOList = blanketProductionMasterForm
				.getBlanketProductionMasterDTO()
				.getBlanketProductionDetailLeftDTOList();
		List<BlanketProductionDetailRightDTO> bpdRightDTOList = blanketProductionMasterForm
				.getBlanketProductionMasterDTO()
				.getBlanketProductionDetailRightDTOList();
		int leftbpListSize = 0;
		int rightbpListSize = 0;

		try {
			leftbpListSize = bpdLeftDTOList.size();
		} catch (Exception e) {
		}
		try {
			rightbpListSize = bpdRightDTOList.size();
		} catch (Exception e) {
		}

		mav.addObject("blanketProductionMasterForm",
				blanketProductionMasterForm);
		mav.addObject("leftbpListSize", leftbpListSize);
		mav.addObject("rightbpListSize", rightbpListSize);
		// mav.addObject("opr", opr);
		mav.addObject("opr", "E");

		return mav;
	}

	@RequestMapping(value = "/save_blanketProduction")
	public String saveBlanketProduction(
			@ModelAttribute("blanketProductionMasterForm") BlanketProductionMasterForm blanketProductionMasterForm,
			Model model,
			@RequestParam(value = "operation", required = false) String operation) {
		List<BlanketProductionDetailLeftDTO> bpdLeftDTOList = blanketProductionMasterForm
				.getBlanketProductionMasterDTO()
				.getBlanketProductionDetailLeftDTOList();
		if (bpdLeftDTOList != null) {
			Iterator<BlanketProductionDetailLeftDTO> itrLeft = bpdLeftDTOList
					.iterator();
			while (itrLeft.hasNext()) {
				BlanketProductionDetailLeftDTO bpdLeftDto = itrLeft.next();
				logger.info("***** : " + bpdLeftDto);
				if (bpdLeftDto.getRollNoLeft() == null) {
					itrLeft.remove();
				}
			}
		}
		List<BlanketProductionDetailRightDTO> bpdRightDTOList = blanketProductionMasterForm
				.getBlanketProductionMasterDTO()
				.getBlanketProductionDetailRightDTOList();
		if (bpdRightDTOList != null) {
			Iterator<BlanketProductionDetailRightDTO> itrRight = bpdRightDTOList
					.iterator();
			while (itrRight.hasNext()) {
				BlanketProductionDetailRightDTO bpdRightDto = itrRight.next();
				if (bpdRightDto.getRollNoRight() == null) {
					itrRight.remove();
				}
			}
		}

		if ((bpdLeftDTOList == null || bpdLeftDTOList.size() == 0)
				&& (bpdRightDTOList == null || bpdRightDTOList.size() == 0)) {
			ErrorDTO error = new ErrorDTO();
			error.setErrorMsg("Error : Without Left/Right Record");
			model.addAttribute("errors", error);
			// add a default empty row
			if (bpdLeftDTOList == null) {
				bpdLeftDTOList = new ArrayList<BlanketProductionDetailLeftDTO>();
				blanketProductionMasterForm.getBlanketProductionMasterDTO()
						.setBlanketProductionDetailLeftDTOList(bpdLeftDTOList);
			}
			if (bpdRightDTOList == null) {
				bpdRightDTOList = new ArrayList<BlanketProductionDetailRightDTO>();
				blanketProductionMasterForm
						.getBlanketProductionMasterDTO()
						.setBlanketProductionDetailRightDTOList(bpdRightDTOList);
			}

			if (bpdLeftDTOList.size() == 0)
				bpdLeftDTOList.add(new BlanketProductionDetailLeftDTO());
			if (bpdRightDTOList.size() == 0)
				bpdRightDTOList.add(new BlanketProductionDetailRightDTO());
			return "blanketProduction-entry";
		}

		String succ = "";
		BlanketProductionMasterInputMessage blanketProductionMasterInputMessage = new BlanketProductionMasterInputMessage();
		BlanketProductionMasterDTO blanketProductionMasterDTO = blanketProductionMasterForm
				.getBlanketProductionMasterDTO();

		blanketProductionMasterInputMessage
				.setBlanketProductionMasterDTO(blanketProductionMasterDTO);
		BlanketProductionMasterOutputMessage blanketProductionMasterOutputMessage = null;
		Integer id = blanketProductionMasterForm
				.getBlanketProductionMasterDTO().getBlanketProductionId();
		if (id != null && !id.equals(0)) {
			blanketProductionMasterDTO.setModifiedUserId(getCreatedUserId());
			blanketProductionMasterOutputMessage = blanketProductionMasterService
					.updateBlanketProductionMaster(blanketProductionMasterInputMessage);
			succ = "Up";
			ErrorListDTO errorListDTO = blanketProductionMasterOutputMessage
					.getErrorListDTO();
			if (errorListDTO != null && errorListDTO.hasErrors()) {
				ErrorDTO errorDTO = errorListDTO.getErrorList().get(0);
				logger.info(" adding Error ");
				model.addAttribute("errors", errorDTO);
				if (bpdLeftDTOList == null) {
					bpdLeftDTOList = new ArrayList<BlanketProductionDetailLeftDTO>();
					blanketProductionMasterForm.getBlanketProductionMasterDTO()
							.setBlanketProductionDetailLeftDTOList(
									bpdLeftDTOList);
				}
				if (bpdRightDTOList == null) {
					bpdRightDTOList = new ArrayList<BlanketProductionDetailRightDTO>();
					blanketProductionMasterForm.getBlanketProductionMasterDTO()
							.setBlanketProductionDetailRightDTOList(
									bpdRightDTOList);
				}

				if (bpdLeftDTOList.size() == 0)
					bpdLeftDTOList.add(new BlanketProductionDetailLeftDTO());
				if (bpdRightDTOList.size() == 0)
					bpdRightDTOList.add(new BlanketProductionDetailRightDTO());
				model.addAttribute("opr", "E");
				return "blanketProduction-entry";
			}
		} else {
			blanketProductionMasterDTO.setCreatedUserId(getCreatedUserId());
			blanketProductionMasterOutputMessage = blanketProductionMasterService
					.createBlanketProductionMaster(blanketProductionMasterInputMessage);
			succ = "Ad";
			ErrorListDTO errorListDTO = blanketProductionMasterOutputMessage
					.getErrorListDTO();
			if (errorListDTO != null && errorListDTO.hasErrors()) {
				ErrorDTO errorDTO = errorListDTO.getErrorList().get(0);
				logger.info(" adding Error ");
				model.addAttribute("errors", errorDTO);
				if (bpdLeftDTOList == null) {
					bpdLeftDTOList = new ArrayList<BlanketProductionDetailLeftDTO>();
					blanketProductionMasterForm.getBlanketProductionMasterDTO()
							.setBlanketProductionDetailLeftDTOList(
									bpdLeftDTOList);
				}
				if (bpdRightDTOList == null) {
					bpdRightDTOList = new ArrayList<BlanketProductionDetailRightDTO>();
					blanketProductionMasterForm.getBlanketProductionMasterDTO()
							.setBlanketProductionDetailRightDTOList(
									bpdRightDTOList);
				}

				if (bpdLeftDTOList.size() == 0)
					bpdLeftDTOList.add(new BlanketProductionDetailLeftDTO());
				if (bpdRightDTOList.size() == 0)
					bpdRightDTOList.add(new BlanketProductionDetailRightDTO());

				return "blanketProduction-entry";
			}
		}

		model.addAttribute("succ", succ);
		return "redirect:/get_blanketProduction_list";
	}

	@RequestMapping(value = "/remove_blanketProduction", method = RequestMethod.GET)
	public String removeBlanketProduction(@RequestParam("id") Integer id,
			Model model) {
		logger.info("Removing..........blanketProduction = " + id);
		BlanketProductionMasterOutputMessage blanketProductionMasterOutputMessage = null;
		if (id != null && !id.equals(0)) {
			BlanketProductionMasterInputMessage blanketProductionMasterInputMessage = new BlanketProductionMasterInputMessage();
			BlanketProductionMasterDTO blanketProductionMasterDTO = new BlanketProductionMasterDTO();
			blanketProductionMasterDTO.setBlanketProductionId(id);
			blanketProductionMasterDTO.setModifiedUserId(getCreatedUserId());
			blanketProductionMasterInputMessage
					.setBlanketProductionMasterDTO(blanketProductionMasterDTO);
			blanketProductionMasterOutputMessage = blanketProductionMasterService
					.deleteBlanketProductionMaster(blanketProductionMasterInputMessage);
		}
		String succ = "Dl";
		model.addAttribute("succ", succ);
		return "redirect:/get_blanketProduction_list";
	}

	@RequestMapping(value = "/get_blanketProduction_list")
	public ModelAndView getBlanketProductionList(
			@ModelAttribute("bpmSearchCriteria") BlanketProductionSearchCriteriaDTO searchCriteria,
			@ModelAttribute("blanketProductionMasterForm") BlanketProductionMasterForm blanketProductionMasterForm,
			Model model,
			@RequestParam(value = "menuId", required = false) String menuId,
			@RequestParam(value = "operation", required = false) String operation,
			@RequestParam(value = "next", required = false) Integer next,
			HttpSession session) {
		if (menuId != null) {
			session.setAttribute("menuId", menuId);
		}
		BlanketProductionMasterInputMessage blanketProductionMasterInputMessage = new BlanketProductionMasterInputMessage();
		blanketProductionMasterInputMessage.setSearchCriteria(searchCriteria);
		BlanketProductionMasterOutputMessage blanketProductionMasterOutputMessage = new BlanketProductionMasterOutputMessage();
		if ("Search".equalsIgnoreCase(operation)) {
			blanketProductionMasterOutputMessage = blanketProductionMasterService
					.search(blanketProductionMasterInputMessage);
		} else {
			if (next == null || next < 0) {
				next = 0;
				blanketProductionMasterInputMessage.setNext(next);
				blanketProductionMasterOutputMessage = blanketProductionMasterService
						.findBlanketProductionPagination(blanketProductionMasterInputMessage);
			} else {
				blanketProductionMasterInputMessage.setNext(next);
				blanketProductionMasterOutputMessage = blanketProductionMasterService
						.findBlanketProductionPagination(blanketProductionMasterInputMessage);
			}

		}
		blanketProductionMasterForm.setNext(next);
		blanketProductionMasterForm.setPrevious(next);

		ModelAndView mav = new ModelAndView("blanketProduction-list");
		mav.addObject("gradeList", getGradeMastersList());
		ArrayList<BlanketProductionMasterDTO> list = (ArrayList<BlanketProductionMasterDTO>) blanketProductionMasterOutputMessage
				.getBlanketProductionMasterDTOList();
		// from
		BlanketProductionMasterDTO blanketProductionMasterDTO = null;

		for (int i = 0; i < list.size(); i++) {
			Double blanketProdRightWeight = 0.0;
			Double blanketProdLeftWeight = 0.0;
			blanketProductionMasterDTO = list.get(i);

			for (int j = 0; j < list.get(i)
					.getBlanketProductionDetailLeftDTOList().size(); j++) {
				if (list.get(i).getBlanketProductionDetailLeftDTOList().get(j)
						.getWeightLeft() != null) {
					blanketProdLeftWeight += list.get(i)
							.getBlanketProductionDetailLeftDTOList().get(j)
							.getWeightLeft();
				}

			}
			for (int k = 0; k < list.get(i)
					.getBlanketProductionDetailRightDTOList().size(); k++) {
				if (list.get(i).getBlanketProductionDetailRightDTOList().get(k)
						.getWeightRight() != null) {
					blanketProdRightWeight += list.get(i)
							.getBlanketProductionDetailRightDTOList().get(k)
							.getWeightRight();
				}

			}
			blanketProductionMasterDTO
					.setBlnktRightWeight(blanketProdRightWeight);
			blanketProductionMasterDTO
					.setBlnktLeftWeight(blanketProdLeftWeight);
		}
		// to

		mav.addObject("bpmList", list);
		mav.addObject("blanketProductionMasterForm",
				blanketProductionMasterForm);
		String succ = "Blk";
		if (list.equals(null) || list.size() == 0) {
			model.addAttribute("succ", succ);
		}
		return mav;
	}

	@RequestMapping(value = "/get_blanketProduction", method = RequestMethod.GET)
	public ModelAndView getBlanketProductionData(
			@RequestParam("id") Integer id, @RequestParam("opr") String opr,
			ModelMap model) {
		BlanketProductionMasterForm blanketProductionMasterForm = new BlanketProductionMasterForm();
		logger.info("Opr : " + opr);
		int leftbpListSize = 0;
		int rightbpListSize = 0;
		BlanketProductionMasterOutputMessage blanketProductionMasterOutMessage = null;
		if (id != null && !id.equals(0)) {
			BlanketProductionMasterInputMessage blanketProductionMasterInputMessage = new BlanketProductionMasterInputMessage();
			BlanketProductionMasterDTO blanketProductionMasterDTO = new BlanketProductionMasterDTO();
			blanketProductionMasterDTO.setBlanketProductionId(id);
			blanketProductionMasterInputMessage
					.setBlanketProductionMasterDTO(blanketProductionMasterDTO);
			blanketProductionMasterOutMessage = blanketProductionMasterService
					.findBlanketProductionMasterById(blanketProductionMasterInputMessage);
			ArrayList<BlanketProductionMasterDTO> list = (ArrayList<BlanketProductionMasterDTO>) blanketProductionMasterOutMessage
					.getBlanketProductionMasterDTOList();
			if (list != null && list.size() > 0) {
				blanketProductionMasterDTO = list.get(0);
				List<BlanketProductionDetailLeftDTO> bpdLeftList = blanketProductionMasterDTO
						.getBlanketProductionDetailLeftDTOList();
				if (bpdLeftList != null && bpdLeftList.size() > 0) {
					for (int i = 0; i < bpdLeftList.size(); i++) {
						BlanketProductionDetailLeftDTO bpdd = bpdLeftList
								.get(i);
						if (bpdd.getItemId() != null) {
							ItemDTO itemDTO = new ItemDTO();
							itemDTO.setItemId(bpdd.getItemId());

							ItemInputMessage itemInputMessage = new ItemInputMessage();
							itemInputMessage.setItemDTO(itemDTO);
							ItemOutMessage itemOutMessage = itemService
									.findItemById(itemInputMessage);
							List iteml = itemOutMessage.getItemDTOList();
							if (iteml != null) {
								ItemDTO iDTO = (ItemDTO) iteml.get(0);
								bpdd.setItemName(iDTO.getItemName());
							}
						}
					}
				}

				List<BlanketProductionDetailRightDTO> bpdRightList = blanketProductionMasterDTO
						.getBlanketProductionDetailRightDTOList();
				if (bpdRightList != null && bpdRightList.size() > 0) {
					for (int i = 0; i < bpdRightList.size(); i++) {
						BlanketProductionDetailRightDTO bprd = bpdRightList
								.get(i);
						if (bprd.getItemId() != null) {
							ItemDTO itemDTO = new ItemDTO();
							itemDTO.setItemId(bprd.getItemId());
							ItemInputMessage itemInputMessage = new ItemInputMessage();
							itemInputMessage.setItemDTO(itemDTO);
							ItemOutMessage itemOutMessage = itemService
									.findItemById(itemInputMessage);
							List iteml = itemOutMessage.getItemDTOList();
							if (iteml != null) {
								ItemDTO iDTO = (ItemDTO) iteml.get(0);
								bprd.setItemName(iDTO.getItemName());
							}
						}

					}
				}

				try {
					leftbpListSize = bpdLeftList.size();
				} catch (Exception e) {
				}

				try {
					rightbpListSize = bpdRightList.size();
				} catch (Exception e) {
				}

				/*
				 * if(bpdLeftList.size()==0){ bpdLeftList.add(new
				 * BlanketProductionDetailLeftDTO()); }
				 * if(bpdRightList.size()==0){ bpdRightList.add(new
				 * BlanketProductionDetailRightDTO()); }
				 */

				blanketProductionMasterForm
						.setBlanketProductionMasterDTO(blanketProductionMasterDTO);
			}
		}

		model.put("blanketProductionMasterForm", blanketProductionMasterForm);
		model.put("shiftMastersList", getShiftMastersList());
		model.put("gradeMastersList", getGradeMastersList());
		model.put("opr", opr);

		model.put("leftbpListSize", leftbpListSize);
		model.put("rightbpListSize", rightbpListSize);

		ModelAndView mav = new ModelAndView("blanketProduction-entry");
		// mav.addObject("partyList",partyList());
		logger.info("IN newBlanketProduction() blanketProductionMasterForm-->"
				+ blanketProductionMasterForm);
		return mav;
	}

	@RequestMapping(value = "add_row_in_bp")
	// public ModelAndView
	// addRow(@ModelAttribute("blanketProductionMasterForm")BlanketProductionMasterForm
	// blanketProductionMasterForm,@RequestParam("rt")String
	// recordType,@RequestParam(value="gradeId",required=false)Integer
	// gradeId,@RequestParam(value="lenght",required=false)Double
	// lenght,@RequestParam(value="width",required=false)Double
	// width,@RequestParam(value="thickness",required=false)Double
	// thickness,@RequestParam(value="weight",required=false)Double weight){
	public @ResponseBody JsonResponse addRow(
			@ModelAttribute("blanketProductionMasterForm") BlanketProductionMasterForm blanketProductionMasterForm,
			@RequestParam("rt") String recordType,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "lenght", required = false) Double lenght,
			@RequestParam(value = "width", required = false) Double width,
			@RequestParam(value = "thickness", required = false) Double thickness,
			@RequestParam(value = "weight", required = false) Double weight) {
		JsonResponse res = new JsonResponse();
		BlanketProductionMasterDTO blanketProductionMasterDTO = blanketProductionMasterForm
				.getBlanketProductionMasterDTO();
		// blanketProductionMasterDTO.setCreatedUserId(getCreatedUserId());
		System.out.println("Lenght :" + lenght + "gradeId:" + gradeId
				+ "width :" + width + "thickness:" + thickness + " weight:"
				+ weight);

		/*
		 * get Weight from Weighting machine.
		 */
		if (weight == null || weight.isNaN())
			weight = blanketProductionMasterService.getBlanketWeightRecord(
					recordType.charAt(0), getCreatedUserId());

		System.out.println(weight);

		int leftbpListSize = 0;
		int rightbpListSize = 0;
		ItemDTO itemDTO = new ItemDTO();
		MastersDTO mastersDTO = new MastersDTO();
		mastersDTO.setMastersId(gradeId);
		itemDTO.setMasterGrade(mastersDTO);
		itemDTO.setItemWidth(width);
		itemDTO.setItemLength(lenght);
		itemDTO.setItemHeight(thickness);
		itemDTO.setGrossWeight(weight);

		itemDTO = itemService.getItemIdAndDencity(itemDTO);
		if (itemDTO.getItemId() == null) {
			// List<BlanketProductionDetailLeftDTO>
			// bpdLeftDTOList=blanketProductionMasterDTO.getBlanketProductionDetailLeftDTOList();
			// List<BlanketProductionDetailRightDTO>
			// bpdRightDTOList=blanketProductionMasterDTO.getBlanketProductionDetailRightDTOList();
			// leftbpListSize=bpdLeftDTOList.size();
			// rightbpListSize=bpdRightDTOList.size();
			ModelAndView mav = new ModelAndView("blanketProduction-entry");
			mav.addObject("leftbpListSize", leftbpListSize);
			mav.addObject("rightbpListSize", rightbpListSize);
			mav.addObject("errormsg",
					"Record is not available for this combination");
			res.setResult(null);
			return res;
		}

		res.setResult1(itemDTO.getItemId());
		res.setResult(itemDTO.getItemDensity());
		res.setResult2(itemDTO.getItemName());
		res.setResult3(weight);
		/*
		 * Integer blankeProductionId=0;
		 * if(blanketProductionMasterDTO.getBlanketProductionId()!=null &&
		 * blanketProductionMasterDTO.getBlanketProductionId()>0){
		 * blankeProductionId
		 * =blanketProductionMasterDTO.getBlanketProductionId();
		 * BlanketProductionMasterInputMessage
		 * blanketProductionMasterInputMessage=new
		 * BlanketProductionMasterInputMessage();
		 * blanketProductionMasterInputMessage
		 * .setBlanketProductionMasterDTO(blanketProductionMasterDTO);
		 * //blanketProductionMasterService
		 * .updateBlanketProductionMaster(blanketProductionMasterInputMessage);
		 * }else{ BlanketProductionMasterInputMessage
		 * blanketProductionMasterInputMessage=new
		 * BlanketProductionMasterInputMessage();
		 * blanketProductionMasterInputMessage
		 * .setBlanketProductionMasterDTO(blanketProductionMasterDTO);
		 * blanketProductionMasterService
		 * .createBlanketProductionMaster(blanketProductionMasterInputMessage);
		 * blankeProductionId=
		 * blanketProductionMasterService.getMaxBlanketProdId(); }
		 * blanketProductionMasterDTO
		 * .setBlanketProductionId(blankeProductionId);
		 * BlanketProductionMasterInputMessage
		 * blanketProductionMasterInputMessage=null;
		 * List<BlanketProductionDetailLeftDTO>
		 * bpdLeftDTOList=blanketProductionMasterDTO
		 * .getBlanketProductionDetailLeftDTOList(); if("L".equals(recordType)){
		 * 
		 * int i= bpdLeftDTOList.size(); BlanketProductionDetailLeftDTO
		 * blanketProductionDetailLeftDTO =bpdLeftDTOList.get(i-1);
		 * blanketProductionDetailLeftDTO
		 * .setBlanketProductionId(blankeProductionId);
		 * blanketProductionDetailLeftDTO.setItemId(itemDTO.getItemId());
		 * if(itemDTO.getItemDensity()!=null){
		 * blanketProductionDetailLeftDTO.setDensityLeft
		 * (itemDTO.getItemDensity()); }else{
		 * blanketProductionDetailLeftDTO.setDensityLeft(0.0); }
		 * blanketProductionDetailLeftDTO.setCreatedUserId(getCreatedUserId());
		 * blanketProductionDetailLeftDTO
		 * .setStatusLeft(blanketProductionDetailLeftDTO.getBlanketType());
		 * 
		 * blanketProductionDetailLeftDTO.setRejStatus(
		 * blanketProductionDetailLeftDTO.getBlanketType());
		 * blanketProductionMasterInputMessage=new
		 * BlanketProductionMasterInputMessage();
		 * blanketProductionMasterInputMessage
		 * .setBlanketProductionDetailLeftDTO(blanketProductionDetailLeftDTO);
		 * //blanketProductionMasterService.createBlanketProductionLeft(
		 * blanketProductionMasterInputMessage);
		 * 
		 * if(bpdLeftDTOList==null){ bpdLeftDTOList=new
		 * ArrayList<BlanketProductionDetailLeftDTO>();
		 * blanketProductionMasterDTO
		 * .setBlanketProductionDetailLeftDTOList(bpdLeftDTOList); }
		 * BlanketProductionDetailLeftDTO bpdl=new
		 * BlanketProductionDetailLeftDTO(); int rollNoL=
		 * blanketProductionMasterService.getMaxRollNoL();
		 * bpdl.setRollNoLeft(rollNoL);
		 * bpdl.setLengthLeft(blanketProductionDetailLeftDTO.getLengthLeft());
		 * bpdl.setWidthLeft(blanketProductionDetailLeftDTO.getWidthLeft());
		 * bpdl.setThickLeft(blanketProductionDetailLeftDTO.getThickLeft());
		 * //bpdLeftDTOList.add(bpdl);
		 * 
		 * } List<BlanketProductionDetailRightDTO>
		 * bpdRightDTOList=blanketProductionMasterDTO
		 * .getBlanketProductionDetailRightDTOList();
		 * if("R".equals(recordType)){
		 * 
		 * int i= bpdRightDTOList.size(); BlanketProductionDetailRightDTO
		 * blanketProductionDetailRightDTO= bpdRightDTOList.get(i-1);
		 * blanketProductionDetailRightDTO
		 * .setBlanketProductionId(blankeProductionId);
		 * blanketProductionDetailRightDTO.setItemId(itemDTO.getItemId());
		 * if(itemDTO.getItemDensity()!=null){
		 * blanketProductionDetailRightDTO.setDensityRight
		 * (itemDTO.getItemDensity()); }else{
		 * blanketProductionDetailRightDTO.setDensityRight(0.0); }
		 * blanketProductionDetailRightDTO.setCreatedUserId(getCreatedUserId());
		 * blanketProductionDetailRightDTO
		 * .setStatusRight(blanketProductionDetailRightDTO.getBlanketType());
		 * blanketProductionDetailRightDTO
		 * .setRejStatusRight(blanketProductionDetailRightDTO.getBlanketType());
		 * blanketProductionMasterInputMessage=new
		 * BlanketProductionMasterInputMessage();
		 * blanketProductionMasterInputMessage
		 * .setBlanketProductionDetailRightDTO(blanketProductionDetailRightDTO);
		 * // blanketProductionMasterService.createBlanketProductionRight(
		 * blanketProductionMasterInputMessage);
		 * 
		 * if(bpdRightDTOList==null){ bpdRightDTOList=new
		 * ArrayList<BlanketProductionDetailRightDTO>();
		 * blanketProductionMasterDTO
		 * .setBlanketProductionDetailRightDTOList(bpdRightDTOList); }
		 * BlanketProductionDetailRightDTO blpr=new
		 * BlanketProductionDetailRightDTO(); int rollNoR=
		 * blanketProductionMasterService.getMaxRollNoR();
		 * blpr.setRollNoRight(rollNoR);
		 * blpr.setLengthRight(blanketProductionDetailRightDTO
		 * .getLengthRight());
		 * blpr.setWidthRight(blanketProductionDetailRightDTO.getWidthRight());
		 * blpr.setThickRight(blanketProductionDetailRightDTO.getThickRight());
		 * //bpdRightDTOList.add(blpr); } leftbpListSize=bpdLeftDTOList.size();
		 * rightbpListSize=bpdRightDTOList.size(); ModelAndView mav=new
		 * ModelAndView("blanketProduction-entry");
		 * mav.addObject("blanketProductionMasterForm"
		 * ,blanketProductionMasterForm); mav.addObject("leftbpListSize",
		 * leftbpListSize); mav.addObject("rightbpListSize", rightbpListSize);
		 */
		return res;
	}

	// New way to save Blanket production entry.................
	@RequestMapping(value = "save_blanket_in_bp")
	public ModelAndView saveBlanket(
			@ModelAttribute("blanketProductionMasterForm") BlanketProductionMasterForm blanketProductionMasterForm,
			@RequestParam("rt") String recordType,
			@RequestParam(value = "opr", required = false) String opr) {
		BlanketProductionMasterDTO blanketProductionMasterDTO = blanketProductionMasterForm
				.getBlanketProductionMasterDTO();
		blanketProductionMasterDTO.setCreatedUserId(getCreatedUserId());
		int leftbpListSize = 0;
		int rightbpListSize = 0;
		int flag = 0;
		Integer blankeProductionId = 0;
		if (blanketProductionMasterDTO.getBlanketProductionId() != null
				&& blanketProductionMasterDTO.getBlanketProductionId() > 0) {
			blankeProductionId = blanketProductionMasterDTO
					.getBlanketProductionId();
			BlanketProductionMasterInputMessage blanketProductionMasterInputMessage = new BlanketProductionMasterInputMessage();
			blanketProductionMasterInputMessage
					.setBlanketProductionMasterDTO(blanketProductionMasterDTO);
			// blanketProductionMasterService.updateBlanketProductionMaster(blanketProductionMasterInputMessage);
		} else {
			BlanketProductionMasterInputMessage blanketProductionMasterInputMessage = new BlanketProductionMasterInputMessage();
			blanketProductionMasterInputMessage
					.setBlanketProductionMasterDTO(blanketProductionMasterDTO);
			blanketProductionMasterService
					.createBlanketProductionMaster(blanketProductionMasterInputMessage);
			blankeProductionId = blanketProductionMasterService
					.getMaxBlanketProdId();
		}
		blanketProductionMasterDTO.setBlanketProductionId(blankeProductionId);

		BlanketProductionMasterInputMessage blanketProductionMasterInputMessage = null;
		List<BlanketProductionDetailRightDTO> bpdRightDTOList = blanketProductionMasterDTO
				.getBlanketProductionDetailRightDTOList();
		List<BlanketProductionDetailLeftDTO> bpdLeftDTOList = blanketProductionMasterDTO
				.getBlanketProductionDetailLeftDTOList();
		if ("L".equals(recordType)) {

			int i = 0;
			try {
				i = bpdLeftDTOList.size();
			} catch (Exception e) {
				e.printStackTrace();
			}
			BlanketProductionDetailLeftDTO blanketProductionDetailLeftDTO = bpdLeftDTOList
					.get(i - 1);

			blanketProductionDetailLeftDTO.setCreatedUserId(getCreatedUserId());
			blanketProductionDetailLeftDTO
					.setStatusLeft(blanketProductionDetailLeftDTO
							.getBlanketType());

			blanketProductionDetailLeftDTO
					.setBlanketProductionId(blankeProductionId);
			blanketProductionDetailLeftDTO
					.setRejStatus(blanketProductionDetailLeftDTO
							.getBlanketType());
			blanketProductionMasterInputMessage = new BlanketProductionMasterInputMessage();
			blanketProductionMasterInputMessage
					.setBlanketProductionDetailLeftDTO(blanketProductionDetailLeftDTO);
			blanketProductionMasterService
					.createBlanketProductionLeft(blanketProductionMasterInputMessage);
			ArrayList<Integer> leftSNO = blanketProductionMasterService
					.getMaxRollNoL(blanketProductionMasterDTO.getBatchNumber());

			try {
				Integer lsno = leftSNO.get(1);
				blanketProductionDetailLeftDTO.setSno(lsno);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (bpdLeftDTOList == null) {
				bpdLeftDTOList = new ArrayList<BlanketProductionDetailLeftDTO>();
				blanketProductionMasterDTO
						.setBlanketProductionDetailLeftDTOList(bpdLeftDTOList);
			}
			if ("E" != opr) {

				BlanketProductionDetailRightDTO blpr = null;
				if (bpdRightDTOList.size() == 1
						&& bpdRightDTOList.get(0).getDensityRight() == null) {
					blpr = bpdRightDTOList.get(0);
				} else {
					blpr = new BlanketProductionDetailRightDTO();
				}

				/*
				 * if (bpdRightDTOList.size() == 1 ||
				 * (bpdRightDTOList.get(i-1).getDensityRight() == null ||
				 * bpdRightDTOList.get(i-1).getWeightRight() == null)) { blpr =
				 * bpdRightDTOList.get(i-1); } else { blpr = new
				 * BlanketProductionDetailRightDTO(); }
				 */

				ArrayList<Integer> as = blanketProductionMasterService
						.getMaxRollNoR(blanketProductionMasterDTO
								.getBatchNumber());
				int rollNoR = as.get(0);
				blpr.setRollNoRight(rollNoR);
				blpr.setLengthRight(blanketProductionDetailLeftDTO
						.getLengthLeft());
				blpr.setWidthRight(blanketProductionDetailLeftDTO
						.getWidthLeft());
				blpr.setThickRight(blanketProductionDetailLeftDTO
						.getThickLeft());
				/*
				 * if (bpdRightDTOList.size() >= 1 &&
				 * bpdRightDTOList.get(0).getDensityRight() != null) {
				 * bpdRightDTOList.add(blpr); }
				 */
				System.out.println("list size L" + i);
				if (bpdRightDTOList.size() >= 1
						&& bpdRightDTOList.get(bpdRightDTOList.size() - 1)
								.getDensityRight() != null) {
					bpdRightDTOList.add(blpr);
				}
			}
			flag = 2;
		}

		if ("R".equals(recordType)) {

			int i = 0;
			try {
				i = bpdRightDTOList.size();
			} catch (Exception e) {
				e.printStackTrace();
			}
			BlanketProductionDetailRightDTO blanketProductionDetailRightDTO = bpdRightDTOList
					.get(i - 1);

			blanketProductionDetailRightDTO
					.setBlanketProductionId(blankeProductionId);
			blanketProductionDetailRightDTO
					.setCreatedUserId(getCreatedUserId());
			blanketProductionDetailRightDTO
					.setStatusRight(blanketProductionDetailRightDTO
							.getBlanketType());
			blanketProductionDetailRightDTO
					.setRejStatusRight(blanketProductionDetailRightDTO
							.getBlanketType());
			blanketProductionMasterInputMessage = new BlanketProductionMasterInputMessage();
			blanketProductionMasterInputMessage
					.setBlanketProductionDetailRightDTO(blanketProductionDetailRightDTO);
			blanketProductionMasterService
					.createBlanketProductionRight(blanketProductionMasterInputMessage);

			ArrayList<Integer> rightSNO = blanketProductionMasterService
					.getMaxRollNoR(blanketProductionMasterDTO.getBatchNumber());
			try {
				Integer rsno = rightSNO.get(1);
				blanketProductionDetailRightDTO.setSno(rsno);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (bpdRightDTOList == null) {
				bpdRightDTOList = new ArrayList<BlanketProductionDetailRightDTO>();
				blanketProductionMasterDTO
						.setBlanketProductionDetailRightDTOList(bpdRightDTOList);
			}
			if ("E" != opr) {
				/*
				 * BlanketProductionDetailRightDTO blpr=new
				 * BlanketProductionDetailRightDTO(); int rollNoR=
				 * blanketProductionMasterService.getMaxRollNoR();
				 * blpr.setRollNoRight(rollNoR);
				 * blpr.setLengthRight(blanketProductionDetailRightDTO
				 * .getLengthRight());
				 * blpr.setWidthRight(blanketProductionDetailRightDTO
				 * .getWidthRight());
				 * blpr.setThickRight(blanketProductionDetailRightDTO
				 * .getThickRight()); bpdRightDTOList.add(blpr);
				 */

				BlanketProductionDetailLeftDTO bpdl = null;
				if (bpdLeftDTOList.size() == 1
						&& bpdLeftDTOList.get(0).getDensityLeft() == null) {
					bpdl = bpdLeftDTOList.get(0);
				} else {
					bpdl = new BlanketProductionDetailLeftDTO();

				}

				/*
				 * if (bpdLeftDTOList.size() == 1 ||
				 * (bpdLeftDTOList.get(i-1).getDensityLeft() == null ||
				 * bpdLeftDTOList.get(i-1).getWeightLeft() == null)) { bpdl =
				 * bpdLeftDTOList.get(i-1); } else { bpdl = new
				 * BlanketProductionDetailLeftDTO(); }
				 */

				ArrayList<Integer> as = blanketProductionMasterService
						.getMaxRollNoL(blanketProductionMasterDTO
								.getBatchNumber());
				int rollNoL = as.get(0);
				bpdl.setRollNoLeft(rollNoL);
				bpdl.setLengthLeft(blanketProductionDetailRightDTO
						.getLengthRight());
				bpdl.setWidthLeft(blanketProductionDetailRightDTO
						.getWidthRight());
				bpdl.setThickLeft(blanketProductionDetailRightDTO
						.getThickRight());
				/*
				 * if (bpdLeftDTOList.size() >= 1 &&
				 * bpdLeftDTOList.get(0).getDensityLeft() != null) {
				 * bpdLeftDTOList.add(bpdl); }
				 */
				System.out.println("list size R" + i);
				if (bpdLeftDTOList.size() >= 1
						&& bpdLeftDTOList.get(bpdLeftDTOList.size() - 1)
								.getDensityLeft() != null) {
					bpdLeftDTOList.add(bpdl);
				}
			}
			flag = 1;
		}
		System.out.println("leftbpListSize" + leftbpListSize
				+ "rightbpListSize" + rightbpListSize);
		try {
			leftbpListSize = bpdLeftDTOList.size();
		} catch (Exception e) {
		}
		try {
			rightbpListSize = bpdRightDTOList.size();
		} catch (Exception e) {
		}

		System.out.println("leftbpListSize" + leftbpListSize
				+ "rightbpListSize" + rightbpListSize);
		ModelAndView mav = new ModelAndView("blanketProduction-entry");
		mav.addObject("blanketProductionMasterForm",
				blanketProductionMasterForm);
		mav.addObject("leftbpListSize", leftbpListSize);
		mav.addObject("rightbpListSize", rightbpListSize);
		mav.addObject("flag", flag);
		mav.addObject("opr", opr);
		return mav;
	}

	@RequestMapping(value = "add_new_row_in_bp")
	public ModelAndView addNewRow(
			@ModelAttribute("blanketProductionMasterForm") BlanketProductionMasterForm blanketProductionMasterForm,
			@RequestParam("rt") String recordType,
			@RequestParam("opr") String opr) {
		BlanketProductionMasterDTO blanketProductionMasterDTO = blanketProductionMasterForm
				.getBlanketProductionMasterDTO();
		int leftbpListSize = 0;
		int rightbpListSize = 0;
		int flag = 0;
		List<BlanketProductionDetailLeftDTO> bpdLeftDTOList = blanketProductionMasterDTO
				.getBlanketProductionDetailLeftDTOList();
		if ("L".equals(recordType)) {
			int i = 0;
			try {
				i = bpdLeftDTOList.size();
			} catch (Exception e) {
			}
			BlanketProductionDetailLeftDTO blanketProductionDetailLeftDTO = null;
			if (bpdLeftDTOList != null && bpdLeftDTOList.size() > 0) {
				blanketProductionDetailLeftDTO = bpdLeftDTOList.get(i - 1);
			} else {
				blanketProductionDetailLeftDTO = new BlanketProductionDetailLeftDTO();
			}
			if (bpdLeftDTOList == null) {
				bpdLeftDTOList = new ArrayList<BlanketProductionDetailLeftDTO>();
				blanketProductionMasterDTO
						.setBlanketProductionDetailLeftDTOList(bpdLeftDTOList);
			}
			BlanketProductionDetailLeftDTO bpdl = new BlanketProductionDetailLeftDTO();

			ArrayList<Integer> as = blanketProductionMasterService
					.getMaxRollNoL(blanketProductionMasterDTO.getBatchNumber());
			int rollNoL = as.get(0);
			bpdl.setRollNoLeft(rollNoL);
			bpdl.setLengthLeft(blanketProductionDetailLeftDTO.getLengthLeft());
			bpdl.setWidthLeft(blanketProductionDetailLeftDTO.getWidthLeft());
			bpdl.setThickLeft(blanketProductionDetailLeftDTO.getThickLeft());
			bpdl.setBlanketProductionId(blanketProductionMasterDTO
					.getBlanketProductionId());
			bpdLeftDTOList.add(bpdl);
			flag = 2;
		}
		List<BlanketProductionDetailRightDTO> bpdRightDTOList = blanketProductionMasterDTO
				.getBlanketProductionDetailRightDTOList();
		if ("R".equals(recordType)) {
			int i = 0;
			try {
				i = bpdRightDTOList.size();
			} catch (Exception e) {
			}
			BlanketProductionDetailRightDTO blanketProductionDetailRightDTO = null;
			if (bpdRightDTOList != null && bpdRightDTOList.size() > 0) {
				blanketProductionDetailRightDTO = bpdRightDTOList.get(i - 1);
			} else {
				blanketProductionDetailRightDTO = new BlanketProductionDetailRightDTO();
			}
			if (bpdRightDTOList == null) {
				bpdRightDTOList = new ArrayList<BlanketProductionDetailRightDTO>();
				blanketProductionMasterDTO
						.setBlanketProductionDetailRightDTOList(bpdRightDTOList);
			}
			BlanketProductionDetailRightDTO blpr = new BlanketProductionDetailRightDTO();

			ArrayList<Integer> as = blanketProductionMasterService
					.getMaxRollNoR(blanketProductionMasterDTO.getBatchNumber());
			int rollNoR = as.get(0);

			blpr.setRollNoRight(rollNoR);
			blpr.setLengthRight(blanketProductionDetailRightDTO
					.getLengthRight());
			blpr.setWidthRight(blanketProductionDetailRightDTO.getWidthRight());
			blpr.setThickRight(blanketProductionDetailRightDTO.getThickRight());
			blpr.setBlanketProductionId(blanketProductionMasterDTO
					.getBlanketProductionId());
			bpdRightDTOList.add(blpr);
			flag = 1;
		}
		try {
			leftbpListSize = bpdLeftDTOList.size();
		} catch (Exception e) {
		}
		try {
			rightbpListSize = bpdRightDTOList.size();
		} catch (Exception e) {
		}
		ModelAndView mav = new ModelAndView("blanketProduction-entry");
		mav.addObject("blanketProductionMasterForm",
				blanketProductionMasterForm);
		mav.addObject("leftbpListSize", leftbpListSize);
		mav.addObject("rightbpListSize", rightbpListSize);
		// mav.addObject("opr", opr);
		mav.addObject("flag", flag);
		mav.addObject("opr", "Add");
		return mav;
	}

	@RequestMapping(value = "edite_row_from_bp", method = RequestMethod.POST)
	public ModelAndView editRow(
			@ModelAttribute("blanketProductionMasterForm") BlanketProductionMasterForm blanketProductionMasterForm,
			@RequestParam("rs") String removeStr,
			@RequestParam(value = "opr", required = false) String opr) {
		int leftbpListSize = 0;
		int rightbpListSize = 0;
		if (removeStr != null) {
			int index = -1;
			try {
				index = Integer.parseInt(removeStr.substring(1));
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

			BlanketProductionMasterDTO blanketProductionMasterDTO = blanketProductionMasterForm
					.getBlanketProductionMasterDTO();
			List<BlanketProductionDetailLeftDTO> bpdLeftDTOList = blanketProductionMasterDTO
					.getBlanketProductionDetailLeftDTOList();
			if (removeStr.startsWith("L") && index >= 0) {
				if (bpdLeftDTOList != null && bpdLeftDTOList.size() > index) {
					BlanketProductionDetailLeftDTO dto = bpdLeftDTOList
							.get(index);
					// To check Data is available or not for this record

					ItemDTO itemDTO = new ItemDTO();
					MastersDTO mastersDTO = new MastersDTO();
					mastersDTO.setMastersId(blanketProductionMasterDTO
							.getGradeMasterDTO().getMastersId());
					itemDTO.setMasterGrade(mastersDTO);
					itemDTO.setItemWidth(dto.getWidthLeft());
					itemDTO.setItemLength(dto.getLengthLeft());
					itemDTO.setItemHeight(dto.getThickLeft());
					itemDTO.setGrossWeight(dto.getWeightLeft());

					itemDTO = itemService.getItemIdAndDencity(itemDTO);
					if (itemDTO.getItemId() == null) {
						List<BlanketProductionDetailLeftDTO> bpdLeftDTOList1 = blanketProductionMasterDTO
								.getBlanketProductionDetailLeftDTOList();
						List<BlanketProductionDetailRightDTO> bpdRightDTOList = blanketProductionMasterDTO
								.getBlanketProductionDetailRightDTOList();
						leftbpListSize = bpdLeftDTOList1.size();
						rightbpListSize = bpdRightDTOList.size();
						ModelAndView mav = new ModelAndView(
								"blanketProduction-entry");
						mav.addObject("leftbpListSize", leftbpListSize);
						mav.addObject("rightbpListSize", rightbpListSize);
						mav.addObject("errormsg",
								"Record is not available for this combination");
						mav.addObject("opr", "E");
						return mav;
					}
					dto.setModifiedUserId(getCreatedUserId());
					// End
					// blanketProductionMasterService.deleteLeftBlanketProduction(dto);
					if (itemDTO.getItemDensity() != null) {
						dto.setDensityLeft(itemDTO.getItemDensity());
					}
					// int sno=dto.getSno();
					BlanketProductionMasterInputMessage blanketProductionMasterInputMessage = new BlanketProductionMasterInputMessage();
					// dto.setSno(null);
					dto.setStatusLeft(dto.getBlanketType());
					dto.setRejStatus(dto.getBlanketType());
					blanketProductionMasterInputMessage
							.setBlanketProductionDetailLeftDTO(dto);
					blanketProductionMasterService
							.createBlanketProductionLeft(blanketProductionMasterInputMessage);
					// dto.setSno(sno);
					// bpdLeftDTOList.set(index, dto);
				}
			}
			List<BlanketProductionDetailRightDTO> bpdRightDTOList = blanketProductionMasterDTO
					.getBlanketProductionDetailRightDTOList();
			if (removeStr.startsWith("R") && index >= 0) {
				if (bpdRightDTOList != null && bpdRightDTOList.size() > index) {
					BlanketProductionDetailRightDTO dto = bpdRightDTOList
							.get(index);

					// To check Data is available or not for this record

					ItemDTO itemDTO = new ItemDTO();
					MastersDTO mastersDTO = new MastersDTO();
					mastersDTO.setMastersId(blanketProductionMasterDTO
							.getGradeMasterDTO().getMastersId());
					itemDTO.setMasterGrade(mastersDTO);
					itemDTO.setItemWidth(dto.getWidthRight());
					itemDTO.setItemLength(dto.getLengthRight());
					itemDTO.setItemHeight(dto.getThickRight());
					itemDTO.setGrossWeight(dto.getWeightRight());

					itemDTO = itemService.getItemIdAndDencity(itemDTO);
					if (itemDTO.getItemId() == null) {
						List<BlanketProductionDetailLeftDTO> bpdLeftDTOList1 = blanketProductionMasterDTO
								.getBlanketProductionDetailLeftDTOList();
						// List<BlanketProductionDetailRightDTO>
						// bpdRightDTOList=blanketProductionMasterDTO.getBlanketProductionDetailRightDTOList();
						leftbpListSize = bpdLeftDTOList1.size();
						rightbpListSize = bpdRightDTOList.size();
						ModelAndView mav = new ModelAndView(
								"blanketProduction-entry");
						mav.addObject("leftbpListSize", leftbpListSize);
						mav.addObject("rightbpListSize", rightbpListSize);
						mav.addObject("errormsg",
								"Record is not available for this combination");
						mav.addObject("opr", "E");
						return mav;
					}
					dto.setModifiedUserId(getCreatedUserId());
					// End
					// blanketProductionMasterService.deleteRightBlanketProduction(dto);
					// bpdRightDTOList.remove(index);

					if (itemDTO.getItemDensity() != null) {
						dto.setDensityRight(itemDTO.getItemDensity());
					}
					// int sno=dto.getSno();
					BlanketProductionMasterInputMessage blanketProductionMasterInputMessage = new BlanketProductionMasterInputMessage();
					// dto.setSno(null);
					dto.setStatusRight(dto.getBlanketType());
					dto.setRejStatusRight(dto.getBlanketType());
					blanketProductionMasterInputMessage
							.setBlanketProductionDetailRightDTO(dto);
					blanketProductionMasterService
							.createBlanketProductionRight(blanketProductionMasterInputMessage);
					// dto.setSno(sno+1);
					// bpdRightDTOList.set(index, dto);
				}

			}
			try {
				leftbpListSize = bpdLeftDTOList.size();
			} catch (Exception e) {
			}
			try {
				rightbpListSize = bpdRightDTOList.size();
			} catch (Exception e) {
			}
		}

		ModelAndView mav = new ModelAndView("blanketProduction-entry");
		mav.addObject("blanketProductionMasterForm",
				blanketProductionMasterForm);
		mav.addObject("leftbpListSize", leftbpListSize);
		mav.addObject("rightbpListSize", rightbpListSize);
		mav.addObject("opr", "E");
		return mav;
	}

	@RequestMapping(value = "remove_row_from_bp", method = RequestMethod.POST)
	public ModelAndView removeRow(
			@ModelAttribute("blanketProductionMasterForm") BlanketProductionMasterForm blanketProductionMasterForm,
			@RequestParam("rs") String removeStr,
			@RequestParam("opr") String opr) {
		int leftbpListSize = 0;
		int rightbpListSize = 0;
		String deleteStatusL = null;
		String deleteStatusR = null;
		if (removeStr != null) {
			int index = -1;
			try {
				index = Integer.parseInt(removeStr.substring(1));
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

			BlanketProductionMasterDTO blanketProductionMasterDTO = blanketProductionMasterForm
					.getBlanketProductionMasterDTO();
			List<BlanketProductionDetailLeftDTO> bpdLeftDTOList = blanketProductionMasterDTO
					.getBlanketProductionDetailLeftDTOList();

			if (removeStr.startsWith("L") && index >= 0) {
				if (bpdLeftDTOList != null && bpdLeftDTOList.size() > index) {
					BlanketProductionDetailLeftDTO dto = bpdLeftDTOList
							.get(index);
					Boolean b = blanketProductionMasterService
							.deleteLeftBlanketProduction(dto);
					if (b == false) {
						deleteStatusL = "You can not delete record as it is approved";
					} else {
						bpdLeftDTOList.remove(index);
					}
				}
			}
			List<BlanketProductionDetailRightDTO> bpdRightDTOList = blanketProductionMasterDTO
					.getBlanketProductionDetailRightDTOList();
			if (removeStr.startsWith("R") && index >= 0) {
				if (bpdRightDTOList != null && bpdRightDTOList.size() > index) {

					BlanketProductionDetailRightDTO dto = bpdRightDTOList
							.get(index);
					Boolean b = blanketProductionMasterService
							.deleteRightBlanketProduction(dto);
					if (b == false) {
						deleteStatusR = "You can not delete record as it is approved";
					} else {
						bpdRightDTOList.remove(index);
					}
				}

			}
			try {
				leftbpListSize = bpdLeftDTOList.size();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				rightbpListSize = bpdRightDTOList.size();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		ModelAndView mav = new ModelAndView("blanketProduction-entry");
		mav.addObject("blanketProductionMasterForm",
				blanketProductionMasterForm);

		mav.addObject("deleteStatusL", deleteStatusL);
		mav.addObject("deleteStatusR", deleteStatusR);
		mav.addObject("leftbpListSize", leftbpListSize);
		mav.addObject("rightbpListSize", rightbpListSize);
		mav.addObject("opr", opr);
		return mav;
	}

	private List<MastersDTO> getShiftMastersList() {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		// formid=11 --> Shift Master
		mastersInputMessage.setFormId(11);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

	private List<MastersDTO> getGradeMastersList() {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		// formid=16 --> Item Grade
		mastersInputMessage.setFormId(16);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

	@RequestMapping(value = "/checkDuplicateRecordInBPMaster", method = RequestMethod.POST)
	public @ResponseBody JsonResponse checkDuplicateRecordInBPMaster(
			@RequestParam("date") Date date,
			@RequestParam("runNo") String runNo,
			@RequestParam("grade") Integer grade,
			@RequestParam("shift") Integer shift,
			@RequestParam("batchNo") String batchNo, ModelMap model) {
		JsonResponse res = new JsonResponse();
		BlanketProductionMasterDTO blanketProductionMasterDTO = new BlanketProductionMasterDTO();
		blanketProductionMasterDTO.setBlanketProductionDate(date);
		blanketProductionMasterDTO.setRunNo(runNo);
		MastersDTO mastersDTO = new MastersDTO();
		mastersDTO.setMastersId(shift);
		blanketProductionMasterDTO.setShiftMasterDTO(mastersDTO);
		blanketProductionMasterDTO.setBatchNumber(batchNo);
		// String flag=null;
		String flag = blanketProductionMasterService
				.checkDuplicateRecordInBPMaster(blanketProductionMasterDTO);
		if ("Duplicate".equalsIgnoreCase(flag)) {
			flag = "Duplicate";
		} else {
			flag = null;
		}
		res.setResult(flag);
		return res;
	}

	// Migration Form
	@RequestMapping(value = "/getAnnealingOverMigration", method = RequestMethod.GET)
	public ModelAndView getAnnealingOverMigration(ModelMap model) {
		BlanketProductionMasterForm blanketProductionMasterForm = new BlanketProductionMasterForm();

		List<BlanketProductionDetailLeftDTO> listLeft = blanketProductionMasterService
				.getBlanketProductionLeft(new Date(), 0);
		List<BlanketProductionDetailRightDTO> listRight = blanketProductionMasterService
				.getBlanketProductionRight(new Date(), 0);

		blanketProductionMasterForm.setBlanketProductionLeftList(listLeft);
		blanketProductionMasterForm.setBlanketProductionRightList(listRight);
		blanketProductionMasterForm.setOnDate(new Date());
		model.put("blanketProductionMasterForm", blanketProductionMasterForm);
		ModelAndView mav = new ModelAndView("annealing_oven_migration");
		mav.addObject("shiftMastersList", getShiftMastersList());
		return mav;
	}

	// End Migration Form
	@RequestMapping(value = "/updateBlanketLeftRight")
	public ModelAndView updateBlanketLeftRight(
			@ModelAttribute("blanketProductionMasterForm") BlanketProductionMasterForm blanketProductionMasterForm,
			ModelMap model,
			@RequestParam(value = "operation", required = false) String operation) {

		List<BlanketProductionDetailLeftDTO> listLeft = null;
		List<BlanketProductionDetailRightDTO> listRight = null;

		Date date = blanketProductionMasterForm.getOnDate();
		Integer shiftId = blanketProductionMasterForm
				.getBlanketProductionMasterDTO().getShiftMasterDTO()
				.getMastersId();

		if (operation.equalsIgnoreCase("Update")) {

			List<BlanketProductionDetailLeftDTO> bpl = blanketProductionMasterForm
					.getBlanketProductionLeftList();
			List<BlanketProductionDetailRightDTO> bpr = blanketProductionMasterForm
					.getBlanketProductionRightList();
			blanketProductionMasterService.updateBlanketProductionLeftRight(
					bpl, bpr, getCreatedUserId());

			listLeft = blanketProductionMasterService.getBlanketProductionLeft(
					date, shiftId);
			listRight = blanketProductionMasterService
					.getBlanketProductionRight(date, shiftId);
		}

		if (operation.equalsIgnoreCase("Serarch")) {
			listLeft = blanketProductionMasterService.getBlanketProductionLeft(
					date, shiftId);
			listRight = blanketProductionMasterService
					.getBlanketProductionRight(date, shiftId);
		}

		blanketProductionMasterForm.setBlanketProductionLeftList(listLeft);
		blanketProductionMasterForm.setBlanketProductionRightList(listRight);

		model.put("blanketProductionMasterForm", blanketProductionMasterForm);

		ModelAndView mav = new ModelAndView("annealing_oven_migration");
		mav.addObject("shiftMastersList", getShiftMastersList());
		return mav;
	}

	// .............Rejection Migration..............................//

	// Migration Form
	@RequestMapping(value = "/getRejectionMigration", method = RequestMethod.GET)
	public ModelAndView getRejectionMigration(ModelMap model) {
		BlanketProductionMasterForm blanketProductionMasterForm = new BlanketProductionMasterForm();

		List<BlanketProductionDetailLeftDTO> listLeft = blanketProductionMasterService
				.getRejBlanketProductionLeftList("Rejection", new Date(), 0);
		List<BlanketProductionDetailRightDTO> listRight = blanketProductionMasterService
				.getRejBlanketProductionRightList("Rejection", new Date(), 0);
		blanketProductionMasterForm.setBlanketProductionLeftList(listLeft);
		blanketProductionMasterForm.setBlanketProductionRightList(listRight);

		ItemInputMessage itemInputMessage = new ItemInputMessage();
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemGroupName("FINISH GOODS");
		itemInputMessage.setItemDTO(itemDTO);
		ItemOutMessage itemOutMessage = itemService
				.findItemGroupNameForAllReports(itemInputMessage);
		List<ItemDTO> itemList = itemOutMessage.getItemDTOList();
		blanketProductionMasterForm.setOnDate(new Date());
		model.put("itemList", itemList);
		model.put("blanketProductionMasterForm", blanketProductionMasterForm);
		ModelAndView mav = new ModelAndView("rejection_migration");
		mav.addObject("shiftMastersList", getShiftMastersList());
		return mav;
	}

	// End Migration Form

	// Update Rejection Migration Form............
	@RequestMapping(value = "/updateRejectionBlanketLeftRight")
	public ModelAndView updateRejectionBlanketLeftRight(
			@ModelAttribute("blanketProductionMasterForm") BlanketProductionMasterForm blanketProductionMasterForm,
			ModelMap model,
			@RequestParam(value = "operation", required = false) String operation) {

		Date date = blanketProductionMasterForm.getOnDate();
		Integer shiftId = blanketProductionMasterForm
				.getBlanketProductionMasterDTO().getShiftMasterDTO()
				.getMastersId();

		if (operation.equalsIgnoreCase("Update")) {
			List<BlanketProductionDetailLeftDTO> bpl = blanketProductionMasterForm
					.getBlanketProductionLeftList();
			List<BlanketProductionDetailRightDTO> bpr = blanketProductionMasterForm
					.getBlanketProductionRightList();
			blanketProductionMasterService
					.updateRejectedBlanketProductionLeftRight(bpl, bpr,
							getCreatedUserId());
		}
		List<BlanketProductionDetailLeftDTO> listLeft = null;
		List<BlanketProductionDetailRightDTO> listRight = null;
		if (operation.equalsIgnoreCase("Serarch")) {
			listLeft = blanketProductionMasterService
					.getRejBlanketProductionLeftList("Rejection", date, shiftId);
			listRight = blanketProductionMasterService
					.getRejBlanketProductionRightList("Rejection", date,
							shiftId);
		}

		blanketProductionMasterForm.setBlanketProductionLeftList(listLeft);
		blanketProductionMasterForm.setBlanketProductionRightList(listRight);

		ItemInputMessage itemInputMessage = new ItemInputMessage();
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemGroupName("FINISH GOODS");
		itemInputMessage.setItemDTO(itemDTO);
		ItemOutMessage itemOutMessage = itemService
				.findItemGroupNameForAllReports(itemInputMessage);
		List<ItemDTO> itemList = itemOutMessage.getItemDTOList();

		model.put("itemList", itemList);
		model.put("blanketProductionMasterForm", blanketProductionMasterForm);
		ModelAndView mav = new ModelAndView("rejection_migration");
		mav.addObject("shiftMastersList", getShiftMastersList());
		return mav;
	}

	// Update Rejection Migration Form............END..

	// Approved Blanket Form
	@RequestMapping(value = "/getApprovedBlanket", method = RequestMethod.GET)
	public ModelAndView getApprovedBlanket(ModelMap model) {
		BlanketProductionMasterForm blanketProductionMasterForm = new BlanketProductionMasterForm();
		List<BlanketProductionDetailLeftDTO> listLeft = blanketProductionMasterService
				.getRejBlanketProductionLeftList("OK','A Grade", new Date(), 0);
		List<BlanketProductionDetailRightDTO> listRight = blanketProductionMasterService
				.getRejBlanketProductionRightList("OK','A Grade", new Date(), 0);
		blanketProductionMasterForm.setBlanketProductionLeftList(listLeft);
		blanketProductionMasterForm.setBlanketProductionRightList(listRight);

		/*
		 * ItemInputMessage itemInputMessage=new ItemInputMessage(); ItemDTO
		 * itemDTO=new ItemDTO(); itemDTO.setItemGroupName("FINISH GOODS");
		 * itemInputMessage.setItemDTO(itemDTO); ItemOutMessage itemOutMessage=
		 * itemService.findItemGroupNameForAllReports(itemInputMessage);
		 * List<ItemDTO> itemList= itemOutMessage.getItemDTOList();
		 * 
		 * model.put("itemList", itemList);
		 */
		blanketProductionMasterForm.setOnDate(new Date());
		model.put("blanketProductionMasterForm", blanketProductionMasterForm);
		ModelAndView mav = new ModelAndView("approved_blanket");
		mav.addObject("shiftMastersList", getShiftMastersList());
		return mav;
	}

	// End Approved Blanket Form
	// Method to increase number of requests or records which we are submiting
	// from jsp to action
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAutoGrowCollectionLimit(2048);
	}

	// Update Approved Blanket Form............
	@RequestMapping(value = "/updateApprovedBlanket", method = RequestMethod.POST)
	public ModelAndView updateApprovedBlanket(
			@ModelAttribute("blanketProductionMasterForm") BlanketProductionMasterForm blanketProductionMasterForm,
			@RequestParam(value = "operation", required = false) String operation,
			ModelMap model) {

		// To get new finished good number...............
		List<BlanketProductionDetailLeftDTO> listLeft = null;
		List<BlanketProductionDetailRightDTO> listRight = null;
		Date onDate = null;
		onDate = blanketProductionMasterForm.getOnDate();
		Integer shiftId = blanketProductionMasterForm
				.getBlanketProductionMasterDTO().getShiftMasterDTO()
				.getMastersId();

		System.out.println("SHIFT ID IS..................." + shiftId
				+ "s.........." + onDate);
		/*
		 * if("Serarch".equalsIgnoreCase(operation)){
		 * 
		 * 
		 * System.out.println("AND DATE IS:::::::::::::::::::"+date); listLeft=
		 * blanketProductionMasterService
		 * .getRejBlanketProductionLeftList("OK",date);
		 * listRight=blanketProductionMasterService
		 * .getRejBlanketProductionRightList("OK",date);
		 * 
		 * }else{
		 */

		// To insert or update record in finish good form
		FinishedGoodsMasterDTO finishedGoodsMasterDTO = new FinishedGoodsMasterDTO();
		String series = getFinishedGoodsTransactionSeries();
		finishedGoodsMasterDTO.setFinYear(getFinYear());
		finishedGoodsMasterDTO.setTransactionSeries(series);

		FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage = new FinishedGoodsMasterInputMessage();
		finishedGoodsMasterInputMessage
				.setFinishedGoodsMasterDTO(finishedGoodsMasterDTO);
		FinishedGoodsMasterOutputMessage finishedGoodsMasterOutputMessage = finishedGoodsMasterService
				.getNewFinishedGoodsSeriesNo(finishedGoodsMasterInputMessage);
		Integer orderID = finishedGoodsMasterOutputMessage
				.getFinishedGoodsSeriesNo();
		finishedGoodsMasterDTO.setFinishGoodId(orderID);
		finishedGoodsMasterDTO.setOrderSeries(finishedGoodsMasterDTO
				.getTransactionSeries()
				+ "/"
				+ finishedGoodsMasterDTO.getFinYear());
		finishedGoodsMasterDTO.setFinishedGoodsNumber(finishedGoodsMasterDTO
				.getTransactionSeries()
				+ "/"
				+ finishedGoodsMasterDTO.getFinYear()
				+ "/"
				+ finishedGoodsMasterDTO.getFinishGoodId());
		Date date = null;
		try {
			date = DataUtility.getDateSimpleFormate(new Date());
		} catch (Exception e) {
			// TODO: handle exception
		}
		finishedGoodsMasterDTO.setFinishGoodDate(date);

		// END..................

		List<BlanketProductionDetailLeftDTO> bpl = blanketProductionMasterForm
				.getBlanketProductionLeftList();
		List<BlanketProductionDetailRightDTO> bpr = blanketProductionMasterForm
				.getBlanketProductionRightList();
		blanketProductionMasterService.updateApprovedBlanketProduction(bpl,
				bpr, getCreatedUserId(), finishedGoodsMasterDTO);

		listLeft = blanketProductionMasterService
				.getRejBlanketProductionLeftList("OK','A Grade", onDate,
						shiftId);
		listRight = blanketProductionMasterService
				.getRejBlanketProductionRightList("OK','A Grade", onDate,
						shiftId);

		// }

		blanketProductionMasterForm.setBlanketProductionLeftList(listLeft);

		blanketProductionMasterForm.setBlanketProductionRightList(listRight);

		/*
		 * ItemInputMessage itemInputMessage=new ItemInputMessage(); ItemDTO
		 * itemDTO=new ItemDTO(); itemDTO.setItemGroupName("FINISH GOODS");
		 * itemInputMessage.setItemDTO(itemDTO); ItemOutMessage itemOutMessage=
		 * itemService.findItemGroupNameForAllReports(itemInputMessage);
		 * List<ItemDTO> itemList= itemOutMessage.getItemDTOList();
		 * 
		 * model.put("itemList", itemList);
		 */
		model.put("blanketProductionMasterForm", blanketProductionMasterForm);
		ModelAndView mav = new ModelAndView("approved_blanket");
		mav.addObject("shiftMastersList", getShiftMastersList());
		return mav;
	}

	// Update Approved Blanket Form............END..

	@RequestMapping(value = "/isItemAvailable", method = RequestMethod.POST)
	public @ResponseBody JsonResponse isItemAvailable(
			@RequestParam("itemId") Integer itemId, ModelMap model) {
		JsonResponse res = new JsonResponse();
		String flag = null;
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemId(itemId);
		ItemInputMessage inputMessage = new ItemInputMessage();
		inputMessage.setItemDTO(itemDTO);
		ItemOutMessage itemOutMessage = itemService.findItemById(inputMessage);
		List<ItemDTO> list = itemOutMessage.getItemDTOList();

		if (list != null && list.size() > 0) {
			itemDTO = list.get(0);
			System.out.println("getItemWidth :" + itemDTO.getItemWidth()
					+ "getItemLength :" + itemDTO.getItemLength()
					+ "ItemHeight :" + itemDTO.getItemHeight()
					+ "getItemDensity:" + itemDTO.getItemDensity()
					+ "getGrossWeight :" + itemDTO.getGrossWeight());
			if (itemDTO.getItemWidth() == null) {
				flag = "Duplicate";
			}
			if (itemDTO.getItemLength() == null) {
				flag = "Duplicate";
			}
			if (itemDTO.getItemHeight() == null) {
				flag = "Duplicate";
			}
			if (itemDTO.getItemDensity() == null) {
				flag = "Duplicate";
			}
			if (itemDTO.getGrossWeight() == null) {
				flag = "Duplicate";
			}
		}
		res.setResult(flag);
		return res;
	}

	@RequestMapping(value = "/unApproveBlanket", method = RequestMethod.POST)
	public @ResponseBody JsonResponse unApproveBlanket(
			@RequestParam("date") Date date,
			@RequestParam("runNo") String runNo,
			@RequestParam("shiftId") Integer shiftId, ModelMap model) {

		/**
		 * @author Abhinav This code write for unapprove the data of Blanket
		 *        Blanket service after the Cutoff date from New Balanket and
		 *         Before the Cutoff Date from old blanket Table.
		 */
		CompanyOutMessage outCompanyOutMessage = companyService
				.findAllCompanies();
		List<CompanyDTO> cList = outCompanyOutMessage.getCompanyDTOList();
		CompanyDTO companyDTO = null;
		if (cList != null && cList.size() > 0) {
			companyDTO = (CompanyDTO) cList.get(0);
			// System.out.println("dto.getStockLockFlag()"+dto.getStockLockFlag());
		}

		if (companyDTO != null) {
			if (companyDTO.getBlanketCutoffDate() != null
					&& date.after(new Date(companyDTO.getBlanketCutoffDate()
							.getTime()))) {
				blanketProductionMasterNewService.updateBlanketProduction(date,
						runNo, shiftId, "", 0);
			} else {
				blanketProductionMasterService.updateBlanketProduction(date,
						runNo, shiftId, "", 0);
			}
		}
		return null;
	}

	@RequestMapping(value = "/approveBlanket", method = RequestMethod.POST)
	public @ResponseBody JsonResponse approveBlanket(
			@RequestParam("date") Date date,
			@RequestParam("runNo") String runNo,
			@RequestParam("shiftId") Integer shiftId, ModelMap model) {

		/**
		 * @author Abhinav This code write for approve the data of Blanket from
		 *        Blanket service after the Cutoff date from New Balanket and
		 *         Before the Cutoff Date from old blanket Table.
		 */
		CompanyOutMessage outCompanyOutMessage = companyService
				.findAllCompanies();
		List<CompanyDTO> cList = outCompanyOutMessage.getCompanyDTOList();
		CompanyDTO companyDTO = null;
		if (cList != null && cList.size() > 0) {
			companyDTO = (CompanyDTO) cList.get(0);
			// System.out.println("dto.getStockLockFlag()"+dto.getStockLockFlag());
		}

		if (companyDTO != null) {
			if (companyDTO.getBlanketCutoffDate() != null
					&& date.after(new Date(companyDTO.getBlanketCutoffDate()
							.getTime()))) {
				blanketProductionMasterNewService.updateBlanketProduction(date,
						runNo, shiftId, "", 1);
			} else {

				blanketProductionMasterService.updateBlanketProduction(date,
						runNo, shiftId, "", 1);
			}
		}
		return null;
	}

	@RequestMapping(value = "/getBlanketProductionData", method = RequestMethod.POST)
	public @ResponseBody JsonResponse getBlanketProductionData(
			@RequestParam("date") Date date,
			@RequestParam("runNo") String runNo,
			@RequestParam("shiftId") Integer shiftId, ModelMap model) {
		JsonResponse res = new JsonResponse();
		ShiftReportMasterDTO shiftReportMasterDTO = new ShiftReportMasterDTO();
		List<ShiftReportMasterDTO> shiftList = new ArrayList<ShiftReportMasterDTO>();
		BlanketProductionMasterDTO bpmDTO = new BlanketProductionMasterDTO();
		BlanketProductionMasterInputMessage bpmInputMessage = new BlanketProductionMasterInputMessage();
		MastersDTO mastersDTO = new MastersDTO();
		bpmDTO.setBlanketProductionDate(date);
		bpmDTO.setRunNo(runNo);
		mastersDTO.setMastersId(shiftId);
		bpmDTO.setShiftMasterDTO(mastersDTO);
		bpmInputMessage.setBlanketProductionMasterDTO(bpmDTO);

		/**
		 * @author Abhinav This code write for get the data of Shift report from
		 *         Blanket service after the Cutoff date from New Balanket and
		 *         Before the Cutoff Date from old blanket Table.
		 */

		BlanketProductionMasterNewDTO newBpmDTO = new BlanketProductionMasterNewDTO();
		BlanketProductionMasterNewInputMessage NewBpmInputMessage = new BlanketProductionMasterNewInputMessage();
		newBpmDTO.setShiftMasterDTO(mastersDTO);
		newBpmDTO.setBlanketProductionDate(date);
		newBpmDTO.setRunNo(runNo);
		NewBpmInputMessage.setBlanketProductionMasterNewDTO(newBpmDTO);

		CompanyOutMessage outCompanyOutMessage = companyService
				.findAllCompanies();
		List<CompanyDTO> cList = outCompanyOutMessage.getCompanyDTOList();
		CompanyDTO companyDTO = null;
		if (cList != null && cList.size() > 0) {
			companyDTO = (CompanyDTO) cList.get(0);
			// System.out.println("dto.getStockLockFlag()"+dto.getStockLockFlag());
		}

		if (companyDTO != null) {
			if (companyDTO.getBlanketCutoffDate() != null
					&& date.after(new Date(companyDTO.getBlanketCutoffDate()
							.getTime()))) {
				System.out.println("date"
						+ date
						+ "-"
						+ new Date(companyDTO.getBlanketCutoffDate().getTime())
						+ ":- "
						+ date.after(new Date(companyDTO.getBlanketCutoffDate()
								.getTime())));

				BlanketProductionMasterNewOutputMessage newBpmOutputmessage = blanketProductionMasterNewService
						.getDataForShiftReport(NewBpmInputMessage);
				List<BlanketProductionMasterNewDTO> list = newBpmOutputmessage
						.getBlanketProductionMasterNewDTOList();
				if (list != null && list.size() > 0) {
					newBpmDTO = list.get(0);

					int noOfBlankets = newBpmDTO
							.getBlanketProductionDetailNewDTOList().size();
					shiftReportMasterDTO.setNoOfBlankets(noOfBlankets);
					shiftReportMasterDTO.setShortLengthBlanketsWeight(newBpmDTO
							.getShortLenght());
					shiftReportMasterDTO.setEdgeTrimWeight(newBpmDTO
							.getEdgeTrim());
					shiftReportMasterDTO.setBulkFibreWeight(newBpmDTO
							.getBulkFiber());

					Double weightL = 0.0;
					Double totalweightL = 0.0;
					for (int i = 0; i < newBpmDTO
							.getBlanketProductionDetailNewDTOList().size(); i++) {
						BlanketProductionDetailNewDTO bplDTO = newBpmDTO
								.getBlanketProductionDetailNewDTOList().get(i);
						weightL += bplDTO.getWeight();
						// if(bplDTO.getBlanketType()!="Rejection"){
						totalweightL += bplDTO.getWeight();
						// }

					}

					Double edgrTrim = 0.0;
					if (newBpmDTO.getEdgeTrim() != null) {
						edgrTrim = newBpmDTO.getEdgeTrim();
					}
					Double bulkFiber = 0.0;
					if (newBpmDTO.getBulkFiber() != null) {
						bulkFiber = newBpmDTO.getBulkFiber();
					}
					Double shortLenght = 0.0;
					if (newBpmDTO.getShortLenght() != null) {
						shortLenght = newBpmDTO.getShortLenght();
					}

					Double total = totalweightL + shortLenght + bulkFiber
							+ edgrTrim;
					shiftReportMasterDTO.setBlanketsWeigth(totalweightL);
					shiftReportMasterDTO.setTotalWeight(total);
					shiftList.add(shiftReportMasterDTO);

				}

			} else {

				BlanketProductionMasterOutputMessage bpmOutputmessage = blanketProductionMasterService
						.getDataForShiftReport(bpmInputMessage);
				List<BlanketProductionMasterDTO> list = bpmOutputmessage
						.getBlanketProductionMasterDTOList();
				if (list != null && list.size() > 0) {
					bpmDTO = list.get(0);

					int noOfBlankets = bpmDTO
							.getBlanketProductionDetailLeftDTOList().size()
							+ bpmDTO.getBlanketProductionDetailRightDTOList()
									.size();
					shiftReportMasterDTO.setNoOfBlankets(noOfBlankets);
					shiftReportMasterDTO.setShortLengthBlanketsWeight(bpmDTO
							.getShortLenght());
					shiftReportMasterDTO
							.setEdgeTrimWeight(bpmDTO.getEdgeTrim());
					shiftReportMasterDTO.setBulkFibreWeight(bpmDTO
							.getBulkFiber());

					Double weightL = 0.0;
					Double totalweightL = 0.0;
					for (int i = 0; i < bpmDTO
							.getBlanketProductionDetailLeftDTOList().size(); i++) {
						BlanketProductionDetailLeftDTO bplDTO = bpmDTO
								.getBlanketProductionDetailLeftDTOList().get(i);
						weightL += bplDTO.getWeightLeft();
						// if(bplDTO.getBlanketType()!="Rejection"){
						totalweightL += bplDTO.getWeightLeft();
						// }

					}
					Double weightR = 0.0;
					Double totalweightR = 0.0;
					for (int i = 0; i < bpmDTO
							.getBlanketProductionDetailRightDTOList().size(); i++) {
						BlanketProductionDetailRightDTO bprDTO = bpmDTO
								.getBlanketProductionDetailRightDTOList()
								.get(i);
						weightR += bprDTO.getWeightRight();
						// if(bprDTO.getBlanketType()!="Rejection"){
						totalweightR += bprDTO.getWeightRight();
						// }
					}
					Double edgrTrim = 0.0;
					if (bpmDTO.getEdgeTrim() != null) {
						edgrTrim = bpmDTO.getEdgeTrim();
					}
					Double bulkFiber = 0.0;
					if (bpmDTO.getBulkFiber() != null) {
						bulkFiber = bpmDTO.getBulkFiber();
					}
					Double shortLenght = 0.0;
					if (bpmDTO.getShortLenght() != null) {
						shortLenght = bpmDTO.getShortLenght();
					}

					Double total = totalweightL + totalweightR + shortLenght
							+ bulkFiber + edgrTrim;
					shiftReportMasterDTO.setBlanketsWeigth(totalweightL
							+ totalweightR);
					shiftReportMasterDTO.setTotalWeight(total);
					shiftList.add(shiftReportMasterDTO);

				}

			}
		}

		res.setResult(shiftReportMasterDTO);
		return res;
	}

	private String getFinishedGoodsTransactionSeries() {
		TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
		transactionTypeDTO.setSno(1);
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

	@RequestMapping(value = "/getMaxRollNo", method = RequestMethod.POST)
	public @ResponseBody JsonResponse getMaxRollNo(
			@RequestParam("batchNo") String batchNo, ModelMap model) {
		JsonResponse res = new JsonResponse();
		BlanketProductionMasterDTO bpmDTO = new BlanketProductionMasterDTO();

		ArrayList<Integer> as = blanketProductionMasterService
				.getMaxRollNoR(batchNo);
		int rollNoR = as.get(0);

		ArrayList<Integer> as1 = blanketProductionMasterService
				.getMaxRollNoL(batchNo);
		int rollNoL = as1.get(0);

		bpmDTO.setRollNoL(rollNoL);
		bpmDTO.setRollNoR(rollNoR);
		res.setResult(bpmDTO);
		return res;
	}

	@RequestMapping(value = "/checkDuplicatRollNoL", method = RequestMethod.POST)
	public @ResponseBody JsonResponse checkDuplicatRollNoL(
			@RequestParam("batchNo") String batchNo,
			@RequestParam("rollnumberL") Integer rollnumberL, ModelMap model) {
		JsonResponse res = new JsonResponse();
		boolean flag = false;
		List list = blanketProductionMasterService.getCheckDuplicatRollNoL(
				batchNo, rollnumberL);
		if (list != null && list.size() > 0) {
			flag = true;
		}
		res.setResult(flag);
		return res;
	}

	@RequestMapping(value = "/checkDuplicatRollNoR", method = RequestMethod.POST)
	public @ResponseBody JsonResponse checkDuplicatRollNoR(
			@RequestParam("batchNo") String batchNo,
			@RequestParam("rollnumberR") Integer rollnumberR, ModelMap model) {
		JsonResponse res = new JsonResponse();
		boolean flag = false;
		List list = blanketProductionMasterService.getCheckDuplicatRollNoR(
				batchNo, rollnumberR);
		if (list != null && list.size() > 0) {
			flag = true;
		}
		res.setResult(flag);
		return res;
	}

	@RequestMapping(value = "get_weight")
	public @ResponseBody JsonResponse getWeight(
			@ModelAttribute("blanketProductionMasterForm") BlanketProductionMasterForm blanketProductionMasterForm,
			@RequestParam("rt") String recordType,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "lenght", required = false) Double lenght,
			@RequestParam(value = "width", required = false) Double width,
			@RequestParam(value = "thickness", required = false) Double thickness,
			@RequestParam(value = "weight", required = false) Double weight) {
		JsonResponse res = new JsonResponse();
		BlanketProductionMasterDTO blanketProductionMasterDTO = blanketProductionMasterForm
				.getBlanketProductionMasterDTO();
		// blanketProductionMasterDTO.setCreatedUserId(getCreatedUserId());
		System.out.println("Lenght :" + lenght + "gradeId:" + gradeId
				+ "width :" + width + "thickness:" + thickness + " weight:"
				+ weight);

		/*
		 * get Weight from Weighting machine.
		 */
		weight = blanketProductionMasterService.getBlanketWeightRecord(
				recordType.charAt(0), getCreatedUserId());

		res.setResult(weight);

		return res;
	}
}