package com.advanz.erp.client.http.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.advanz.erp.client.http.controller.form.BlanketProductionMasterNewForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.BlanketProductionDetailNewDTO;
import com.advanz.erp.masters.model.BlanketProductionMasterNewDTO;
import com.advanz.erp.masters.model.CompanyDTO;
import com.advanz.erp.masters.model.FinishedGoodsMasterDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.ShiftReportMasterDTO;
import com.advanz.erp.masters.model.TransactionTypeDTO;
import com.advanz.erp.masters.model.criteria.BlanketProductionSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterNewInputMessage;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterNewOutputMessage;
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
import com.advanz.erp.masters.service.business.ICompanyService;
import com.advanz.erp.masters.service.business.IFinishedGoodsMasterService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.ITransactionTypeService;

@Controller
@SessionAttributes({ "shiftMastersList", "gradeMastersList" })
public class BlanketProductionMasterNewController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(BlanketProductionMasterNewController.class);

	@Autowired
	private IBlanketProductionMasterNewService blanketProductionMasterNewService;

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

	@RequestMapping(value = "/new_blanketProduction_new", method = RequestMethod.GET)
	public ModelAndView newBlanketProduction(ModelMap model) {
		ModelAndView mav = new ModelAndView("blanketProduction-entry_new");
		BlanketProductionMasterNewForm bpmForm = new BlanketProductionMasterNewForm();
		int leftbpListSize = 0;
		int rightbpListSize = 0;
		BlanketProductionMasterNewDTO bpmDTO = new BlanketProductionMasterNewDTO();

		List<BlanketProductionDetailNewDTO> bpdDTOLeftList = new ArrayList<BlanketProductionDetailNewDTO>();
		BlanketProductionDetailNewDTO blpl = new BlanketProductionDetailNewDTO();

		ArrayList<Integer> as = blanketProductionMasterNewService.getMaxRollNo(
				null, null, null, null);
		int rollNoL = as.get(0);
		blpl.setRollNo(rollNoL);
		blpl.setSpliterType(BlanketProductionDetailNewDTO.SPLITER_TYPE_A);
		bpdDTOLeftList.add(blpl);
		leftbpListSize = bpdDTOLeftList.size();
		bpmDTO.setBlanketProductionDetailNewDTOList(bpdDTOLeftList);

		/*
		 * BlanketProductionDetailNewDTO blpr = new
		 * BlanketProductionDetailNewDTO();
		 * 
		 * ArrayList<Integer> as1 = blanketProductionMasterNewService
		 * .getMaxRollNo(null); int rollNoR = as1.get(0);
		 * blpr.setRollNo(rollNoR);
		 * 
		 * bpdDTOLeftList.add(blpr);
		 */
		bpmDTO.setBlanketProductionDetailNewDTOList(bpdDTOLeftList);
		// rightbpListSize = bpdDTOLeftList.size();
		bpmDTO.setBlanketProductionDate(new Date());
		bpmDTO.setSpliterCount(2);
		bpmForm.setBlanketProductionMasterNewDTO(bpmDTO);

		// set Last entry date validation
		Timestamp timestamp = blanketProductionMasterNewService
				.getLastBlanketEntryDate();
		System.out.println("timestamp" + timestamp);
		SimpleDateFormat ft = new SimpleDateFormat("yyyy,MM,dd");
		if (timestamp != null) {
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
		mav.addObject("shiftMastersList", getShiftMastersList());
		mav.addObject("gradeMastersList", getGradeMastersList());
		mav.addObject("blanketProductionMasterNewForm", bpmForm);
		mav.addObject("leftbpListSize", leftbpListSize);
		mav.addObject("rightbpListSize", rightbpListSize);
		mav.addObject("opr", null);
		return mav;
	}

	@RequestMapping(value = "saveBlanketMaster_new")
	public ModelAndView saveBlanketMaster(
			@ModelAttribute("blanketProductionMasterNewForm") BlanketProductionMasterNewForm blanketProductionMasterNewForm,
			@RequestParam(value = "operation", required = false) String operation) {
		ModelAndView mav = new ModelAndView("blanketProduction-entry_new");
		BlanketProductionMasterNewDTO blanketProductionMasterDTO = blanketProductionMasterNewForm
				.getBlanketProductionMasterNewDTO();
		Integer blankeProductionId = 0;
		
		if (validateBlanketStatus(blanketProductionMasterDTO)) {
			return new ModelAndView("redirect:/get_blanketProduction_list_new");
		}
		

		if (blanketProductionMasterDTO.getBlanketProductionId() != null
				&& blanketProductionMasterDTO.getBlanketProductionId() > 0) {
			blankeProductionId = blanketProductionMasterDTO
					.getBlanketProductionId();
			System.out.println("blankeProductionId" + blankeProductionId);
			BlanketProductionMasterNewInputMessage blanketProductionMasterInputMessage = new BlanketProductionMasterNewInputMessage();

			List<BlanketProductionDetailNewDTO> ll = blanketProductionMasterNewForm
					.getBlanketProductionMasterNewDTO()
					.getBlanketProductionDetailNewDTOList();
			if (ll != null) {
				Iterator<BlanketProductionDetailNewDTO> itrLeft = ll.iterator();
				while (itrLeft.hasNext()) {
					BlanketProductionDetailNewDTO bpdLeftDto = itrLeft.next();
					bpdLeftDto.setStatus(bpdLeftDto.getBlanketType());
					bpdLeftDto.setRejStatus(bpdLeftDto.getBlanketType());
					logger.info("***** : " + bpdLeftDto);
					if (bpdLeftDto.getDensity() == null) {
						itrLeft.remove();
					}
					// System.out.println("bpdLeftDto  bpId:"+bpdLeftDto.getBlanketProductionId());
				}
			}

			/*
			 * List<BlanketProductionDetailNewDTO> rl =
			 * blanketProductionMasterNewForm
			 * .getBlanketProductionMasterNewDTO()
			 * .getBlanketProductionDetailNewDTOList(); if (rl != null) {
			 * Iterator<BlanketProductionDetailNewDTO> itrLeft = rl.iterator();
			 * while (itrLeft.hasNext()) { BlanketProductionDetailNewDTO
			 * bpdLeftDto = itrLeft.next();
			 * bpdLeftDto.setStatus(bpdLeftDto.getBlanketType());
			 * bpdLeftDto.setRejStatus(bpdLeftDto.getBlanketType());
			 * logger.info("***** : " + bpdLeftDto); if (bpdLeftDto.getDensity()
			 * == null) { itrLeft.remove(); } } }
			 */

			blanketProductionMasterInputMessage
					.setBlanketProductionMasterNewDTO(blanketProductionMasterDTO);
			blanketProductionMasterNewService
					.updateBlanketProductionMaster(blanketProductionMasterInputMessage);
		}/*
		 * else{ BlanketProductionMasterInputMessage
		 * blanketProductionMasterInputMessage=new
		 * BlanketProductionMasterInputMessage();
		 * blanketProductionMasterInputMessage
		 * .setBlanketProductionMasterNewDTO(blanketProductionMasterDTO);
		 * blanketProductionMasterService
		 * .createBlanketProductionMaster(blanketProductionMasterInputMessage);
		 * //blankeProductionId=
		 * blanketProductionMasterService.getMaxBlanketProdId(); }
		 */
		blanketProductionMasterDTO.setBlanketProductionId(blankeProductionId);

		List<BlanketProductionDetailNewDTO> bpdLeftDTOList = blanketProductionMasterNewForm
				.getBlanketProductionMasterNewDTO()
				.getBlanketProductionDetailNewDTOList();

		int leftbpListSize = 0;
		int rightbpListSize = 0;

		try {
			leftbpListSize = bpdLeftDTOList.size();
		} catch (Exception e) {
		}

		mav.addObject("blanketProductionMasterNewForm",
				blanketProductionMasterNewForm);
		mav.addObject("leftbpListSize", leftbpListSize);
		// mav.addObject("rightbpListSize", rightbpListSize);
		// mav.addObject("opr", opr);
		mav.addObject("opr", "E");

		return mav;
	}

	@RequestMapping(value = "/save_blanketProduction_new")
	public String saveBlanketProduction(
			@ModelAttribute("blanketProductionMasterNewForm") BlanketProductionMasterNewForm blanketProductionMasterNewForm,
			Model model,
			@RequestParam(value = "operation", required = false) String operation) {
		List<BlanketProductionDetailNewDTO> bpdLeftDTOList = blanketProductionMasterNewForm
				.getBlanketProductionMasterNewDTO()
				.getBlanketProductionDetailNewDTOList();
		BlanketProductionMasterNewInputMessage blanketProductionMasterInputMessage = new BlanketProductionMasterNewInputMessage();
		BlanketProductionMasterNewDTO blanketProductionMasterDTO = blanketProductionMasterNewForm
				.getBlanketProductionMasterNewDTO();
		
		if (validateBlanketStatus(blanketProductionMasterDTO)) {
			return "redirect:/get_blanketProduction_list_new";
		}
		
		if (bpdLeftDTOList != null) {
			Iterator<BlanketProductionDetailNewDTO> itrLeft = bpdLeftDTOList
					.iterator();
			while (itrLeft.hasNext()) {
				BlanketProductionDetailNewDTO bpdLeftDto = itrLeft.next();
				logger.info("***** : " + bpdLeftDto);
				if (bpdLeftDto.getRollNo() == null) {
					itrLeft.remove();
				}
			}
		}
		List<BlanketProductionDetailNewDTO> bpdDTOList = blanketProductionMasterNewForm
				.getBlanketProductionMasterNewDTO()
				.getBlanketProductionDetailNewDTOList();
		if (bpdDTOList != null) {
			Iterator<BlanketProductionDetailNewDTO> itr = bpdDTOList.iterator();
			while (itr.hasNext()) {
				BlanketProductionDetailNewDTO bpdDto = itr.next();
				if (bpdDto.getRollNo() == null) {
					itr.remove();
				}
			}
		}

		if ((bpdLeftDTOList == null || bpdLeftDTOList.size() == 0)
				&& (bpdDTOList == null || bpdDTOList.size() == 0)) {
			ErrorDTO error = new ErrorDTO();
			error.setErrorMsg("Error : Without Left/ Record");
			model.addAttribute("errors", error);
			// add a default empty row
			if (bpdLeftDTOList == null) {
				bpdLeftDTOList = new ArrayList<BlanketProductionDetailNewDTO>();
				blanketProductionMasterNewForm
						.getBlanketProductionMasterNewDTO()
						.setBlanketProductionDetailNewDTOList(bpdLeftDTOList);
			}
			if (bpdDTOList == null) {
				bpdDTOList = new ArrayList<BlanketProductionDetailNewDTO>();
				blanketProductionMasterNewForm
						.getBlanketProductionMasterNewDTO()
						.setBlanketProductionDetailNewDTOList(bpdDTOList);
			}

			if (bpdLeftDTOList.size() == 0)
				bpdLeftDTOList.add(new BlanketProductionDetailNewDTO());
			if (bpdDTOList.size() == 0)
				bpdDTOList.add(new BlanketProductionDetailNewDTO());
			return "blanketProduction-entry_new";
		}

		String succ = "";
		

		blanketProductionMasterInputMessage
				.setBlanketProductionMasterNewDTO(blanketProductionMasterDTO);
		BlanketProductionMasterNewOutputMessage blanketProductionMasterOutputMessage = null;
		Integer id = blanketProductionMasterNewForm
				.getBlanketProductionMasterNewDTO().getBlanketProductionId();
		if (id != null && !id.equals(0)) {
			blanketProductionMasterDTO.setModifiedUserId(getCreatedUserId());
			blanketProductionMasterOutputMessage = blanketProductionMasterNewService
					.updateBlanketProductionMaster(blanketProductionMasterInputMessage);
			succ = "Up";
			ErrorListDTO errorListDTO = blanketProductionMasterOutputMessage
					.getErrorListDTO();
			if (errorListDTO != null && errorListDTO.hasErrors()) {
				ErrorDTO errorDTO = errorListDTO.getErrorList().get(0);
				logger.info(" adding Error ");
				model.addAttribute("errors", errorDTO);
				if (bpdLeftDTOList == null) {
					bpdLeftDTOList = new ArrayList<BlanketProductionDetailNewDTO>();
					blanketProductionMasterNewForm
							.getBlanketProductionMasterNewDTO()
							.setBlanketProductionDetailNewDTOList(
									bpdLeftDTOList);
				}
				if (bpdDTOList == null) {
					bpdDTOList = new ArrayList<BlanketProductionDetailNewDTO>();
					blanketProductionMasterNewForm
							.getBlanketProductionMasterNewDTO()
							.setBlanketProductionDetailNewDTOList(bpdDTOList);
				}

				if (bpdLeftDTOList.size() == 0)
					bpdLeftDTOList.add(new BlanketProductionDetailNewDTO());
				if (bpdDTOList.size() == 0)
					bpdDTOList.add(new BlanketProductionDetailNewDTO());
				model.addAttribute("opr", "E");
				return "blanketProduction-entry_new";
			}
		} else {
			blanketProductionMasterDTO.setCreatedUserId(getCreatedUserId());
			blanketProductionMasterOutputMessage = blanketProductionMasterNewService
					.createBlanketProductionMaster(blanketProductionMasterInputMessage);
			succ = "Ad";
			ErrorListDTO errorListDTO = blanketProductionMasterOutputMessage
					.getErrorListDTO();
			if (errorListDTO != null && errorListDTO.hasErrors()) {
				ErrorDTO errorDTO = errorListDTO.getErrorList().get(0);
				logger.info(" adding Error ");
				model.addAttribute("errors", errorDTO);
				if (bpdLeftDTOList == null) {
					bpdLeftDTOList = new ArrayList<BlanketProductionDetailNewDTO>();
					blanketProductionMasterNewForm
							.getBlanketProductionMasterNewDTO()
							.setBlanketProductionDetailNewDTOList(
									bpdLeftDTOList);
				}
				if (bpdDTOList == null) {
					bpdDTOList = new ArrayList<BlanketProductionDetailNewDTO>();
					blanketProductionMasterNewForm
							.getBlanketProductionMasterNewDTO()
							.setBlanketProductionDetailNewDTOList(bpdDTOList);
				}

				if (bpdLeftDTOList.size() == 0)
					bpdLeftDTOList.add(new BlanketProductionDetailNewDTO());
				if (bpdDTOList.size() == 0)
					bpdDTOList.add(new BlanketProductionDetailNewDTO());

				return "blanketProduction-entry_new";
			}
		}

		model.addAttribute("succ", succ);
		return "redirect:/get_blanketProduction_list_new";
	}

	@RequestMapping(value = "/remove_blanketProduction_new", method = RequestMethod.GET)
	public String removeBlanketProduction(@RequestParam("id") Integer id,
			Model model) {
		logger.info("Removing..........blanketProduction = " + id);
		
		BlanketProductionMasterNewDTO dto = new BlanketProductionMasterNewDTO();
		dto.setBlanketProductionId(id);
		if (validateBlanketStatus(dto)) {
			return "redirect:/get_blanketProduction_list_new";
		}
		
		BlanketProductionMasterNewOutputMessage blanketProductionMasterOutputMessage = null;
		if (id != null && !id.equals(0)) {
			BlanketProductionMasterNewInputMessage blanketProductionMasterInputMessage = new BlanketProductionMasterNewInputMessage();
			BlanketProductionMasterNewDTO blanketProductionMasterDTO = new BlanketProductionMasterNewDTO();
			blanketProductionMasterDTO.setBlanketProductionId(id);
			blanketProductionMasterDTO.setModifiedUserId(getCreatedUserId());
			blanketProductionMasterInputMessage
					.setBlanketProductionMasterNewDTO(blanketProductionMasterDTO);
			blanketProductionMasterOutputMessage = blanketProductionMasterNewService
					.deleteBlanketProductionMaster(blanketProductionMasterInputMessage);
		}
		String succ = "Dl";
		model.addAttribute("succ", succ);
		return "redirect:/get_blanketProduction_list_new";
	}

	@RequestMapping(value = "/get_blanketProduction_list_new")
	public ModelAndView getBlanketProductionList(
			@ModelAttribute("bpmSearchCriteria") BlanketProductionSearchCriteriaDTO searchCriteria,
			@ModelAttribute("blanketProductionMasterNewForm") BlanketProductionMasterNewForm blanketProductionMasterNewForm,
			Model model,
			@RequestParam(value = "menuId", required = false) String menuId,
			@RequestParam(value = "operation", required = false) String operation,
			@RequestParam(value = "next", required = false) Integer next,
			HttpSession session) {
		if (menuId != null) {
			session.setAttribute("menuId", menuId);
		}
		BlanketProductionMasterNewInputMessage blanketProductionMasterInputMessage = new BlanketProductionMasterNewInputMessage();
		blanketProductionMasterInputMessage.setSearchCriteria(searchCriteria);
		BlanketProductionMasterNewOutputMessage blanketProductionMasterOutputMessage = new BlanketProductionMasterNewOutputMessage();
		if ("Search".equalsIgnoreCase(operation)) {
			blanketProductionMasterOutputMessage = blanketProductionMasterNewService
					.search(blanketProductionMasterInputMessage);
		} else {
			if (next == null || next < 0) {
				next = 0;
				blanketProductionMasterInputMessage.setNext(next);
				blanketProductionMasterOutputMessage = blanketProductionMasterNewService
						.findBlanketProductionPagination(blanketProductionMasterInputMessage);
			} else {
				blanketProductionMasterInputMessage.setNext(next);
				blanketProductionMasterOutputMessage = blanketProductionMasterNewService
						.findBlanketProductionPagination(blanketProductionMasterInputMessage);
			}

		}
		blanketProductionMasterNewForm.setNext(next);
		blanketProductionMasterNewForm.setPrevious(next);

		ModelAndView mav = new ModelAndView("blanketProduction-list_new");
		mav.addObject("gradeList", getGradeMastersList());
		ArrayList<BlanketProductionMasterNewDTO> list = (ArrayList<BlanketProductionMasterNewDTO>) blanketProductionMasterOutputMessage
				.getBlanketProductionMasterNewDTOList();
		// from
		BlanketProductionMasterNewDTO blanketProductionMasterDTO = null;

		for (int i = 0; i < list.size(); i++) {
			Double blanketProdLeftWeight = 0.0;
			StringBuffer splitedCounts = new StringBuffer("");
			blanketProductionMasterDTO = list.get(i);

			for (int j = 0; j < list.get(i)
					.getBlanketProductionDetailNewDTOList().size(); j++) {
				if (list.get(i).getBlanketProductionDetailNewDTOList().get(j)
						.getWeight() != null) {
					blanketProdLeftWeight += list.get(i)
							.getBlanketProductionDetailNewDTOList().get(j)
							.getWeight();
				}
				if (list.get(i).getBlanketProductionDetailNewDTOList().get(j)
						.getSpliterType() != null) {
					splitedCounts.append(list.get(i)
							.getBlanketProductionDetailNewDTOList().get(j)
							.getSpliterType());
				}

			}

			blanketProductionMasterDTO
					.setSplitedCounts(getSplitedCounts(splitedCounts.toString()));
			blanketProductionMasterDTO.setBlnktWeight(blanketProdLeftWeight);
		}
		// to

		mav.addObject("bpmList", list);
		mav.addObject("blanketProductionMasterNewForm",
				blanketProductionMasterNewForm);
		String succ = "Blk";
		if (list.equals(null) || list.size() == 0) {
			model.addAttribute("succ", succ);
		}
		return mav;
	}

	public String getSplitedCounts(String s) {

		String result = "";
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		s = new String(chars);
		// System.out.println(s);

		StringBuilder sb = new StringBuilder(s);

		while (sb.length() != 0) {
			int count = 0;
			char test = sb.charAt(0);
			while (sb.indexOf(test + "") != -1) {
				sb.deleteCharAt(sb.indexOf(test + ""));
				count++;
			}
			// System.out.println(test+" is repeated "+count+" number of times");
			result = result + "," + test + "(" + count + ")";
		}
		return result.replaceFirst(",", "");
	}

	@RequestMapping(value = "/get_blanketProduction_new", method = RequestMethod.GET)
	public ModelAndView getBlanketProductionData(
			@RequestParam("id") Integer id, @RequestParam("opr") String opr,
			ModelMap model) {
		BlanketProductionMasterNewForm blanketProductionMasterNewForm = new BlanketProductionMasterNewForm();
		logger.info("Opr : " + opr);
		BlanketProductionMasterNewDTO dto = new BlanketProductionMasterNewDTO();
		dto.setBlanketProductionId(id);
		if (validateBlanketStatus(dto) && !opr.equalsIgnoreCase("V")) {
			return new ModelAndView("redirect:/get_blanketProduction_list_new");
		}
		int leftbpListSize = 0;
		int rightbpListSize = 0;
		BlanketProductionMasterNewOutputMessage blanketProductionMasterOutMessage = null;
		if (id != null && !id.equals(0)) {
			BlanketProductionMasterNewInputMessage blanketProductionMasterInputMessage = new BlanketProductionMasterNewInputMessage();
			BlanketProductionMasterNewDTO blanketProductionMasterDTO = new BlanketProductionMasterNewDTO();
			blanketProductionMasterDTO.setBlanketProductionId(id);
			blanketProductionMasterInputMessage
					.setBlanketProductionMasterNewDTO(blanketProductionMasterDTO);
			blanketProductionMasterOutMessage = blanketProductionMasterNewService
					.findBlanketProductionMasterById(blanketProductionMasterInputMessage);
			ArrayList<BlanketProductionMasterNewDTO> list = (ArrayList<BlanketProductionMasterNewDTO>) blanketProductionMasterOutMessage
					.getBlanketProductionMasterNewDTOList();
			if (list != null && list.size() > 0) {
				blanketProductionMasterDTO = list.get(0);
				List<BlanketProductionDetailNewDTO> bpdLeftList = blanketProductionMasterDTO
						.getBlanketProductionDetailNewDTOList();
				if (bpdLeftList != null && bpdLeftList.size() > 0) {
					for (int i = 0; i < bpdLeftList.size(); i++) {
						BlanketProductionDetailNewDTO bpdd = bpdLeftList.get(i);
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

				/*
				 * List<BlanketProductionDetailNewDTO> bpdList =
				 * blanketProductionMasterDTO
				 * .getBlanketProductionDetailNewDTOList(); if (bpdList != null
				 * && bpdList.size() > 0) { for (int i = 0; i < bpdList.size();
				 * i++) { BlanketProductionDetailNewDTO bprd = bpdList.get(i);
				 * if (bprd.getItemId() != null) { ItemDTO itemDTO = new
				 * ItemDTO(); itemDTO.setItemId(bprd.getItemId());
				 * ItemInputMessage itemInputMessage = new ItemInputMessage();
				 * itemInputMessage.setItemDTO(itemDTO); ItemOutMessage
				 * itemOutMessage = itemService .findItemById(itemInputMessage);
				 * List iteml = itemOutMessage.getItemDTOList(); if (iteml !=
				 * null) { ItemDTO iDTO = (ItemDTO) iteml.get(0);
				 * bprd.setItemName(iDTO.getItemName()); } }
				 * 
				 * } }
				 */

				try {
					leftbpListSize = bpdLeftList.size();
				} catch (Exception e) {
				}

				try {
					// rightbpListSize = bpdList.size();
				} catch (Exception e) {
				}

				/*
				 * if(bpdLeftList.size()==0){ bpdLeftList.add(new
				 * BlanketProductionDetailNewDTO()); } if(bpdList.size()==0){
				 * bpdList.add(new BlanketProductionDetailNewDTO()); }
				 */

				blanketProductionMasterNewForm
						.setBlanketProductionMasterNewDTO(blanketProductionMasterDTO);
			}
		}

		model.put("blanketProductionMasterNewForm",
				blanketProductionMasterNewForm);
		model.put("shiftMastersList", getShiftMastersList());
		model.put("gradeMastersList", getGradeMastersList());
		model.put("opr", opr);

		model.put("leftbpListSize", leftbpListSize);
		model.put("rightbpListSize", rightbpListSize);

		ModelAndView mav = new ModelAndView("blanketProduction-entry_new");
		// mav.addObject("partyList",partyList());
		logger.info("IN newBlanketProduction() blanketProductionMasterNewForm-->"
				+ blanketProductionMasterNewForm);
		return mav;
	}

	@RequestMapping(value = "add_row_in_bp_new")
	// public ModelAndView
	// addRow(@ModelAttribute("blanketProductionMasterNewForm")BlanketProductionMasterNewForm
	// blanketProductionMasterNewForm,@RequestParam("rt")String
	// recordType,@RequestParam(value="gradeId",required=false)Integer
	// gradeId,@RequestParam(value="lenght",required=false)Double
	// lenght,@RequestParam(value="width",required=false)Double
	// width,@RequestParam(value="thickness",required=false)Double
	// thickness,@RequestParam(value="weight",required=false)Double weight){
	public @ResponseBody JsonResponse addRow(
			@ModelAttribute("blanketProductionMasterNewForm") BlanketProductionMasterNewForm blanketProductionMasterNewForm,
			@RequestParam("rt") String recordType,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "lenght", required = false) Double lenght,
			@RequestParam(value = "width", required = false) Double width,
			@RequestParam(value = "thickness", required = false) Double thickness,
			@RequestParam(value = "weight", required = false) Double weight,
			@RequestParam(value = "spliterType", required = false) String spliterType) {
		JsonResponse res = new JsonResponse();
		BlanketProductionMasterNewDTO blanketProductionMasterDTO = blanketProductionMasterNewForm
				.getBlanketProductionMasterNewDTO();
		// blanketProductionMasterDTO.setCreatedUserId(getCreatedUserId());
		System.out.println("Lenght :" + lenght + "gradeId:" + gradeId
				+ "width :" + width + "thickness:" + thickness + " weight:"
				+ weight + " spliterType:" + spliterType);

		/*
		 * get Weight from Weighting machine.
		 */
		if (weight == null || weight.isNaN())
			weight = blanketProductionMasterNewService.getBlanketWeightRecord(
					spliterType.charAt(0), getCreatedUserId());

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
			// List<BlanketProductionDetailNewDTO>
			// bpdLeftDTOList=blanketProductionMasterDTO.getBlanketProductionDetailNewDTOList();
			// List<BlanketProductionDetailNewDTO>
			// bpdDTOList=blanketProductionMasterDTO.getBlanketProductionDetailNewDTOList();
			// leftbpListSize=bpdLeftDTOList.size();
			// rightbpListSize=bpdDTOList.size();
			ModelAndView mav = new ModelAndView("blanketProduction-entry_new");
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
		 * BlanketProductionMasterNewInputMessage
		 * blanketProductionMasterInputMessage=new
		 * BlanketProductionMasterNewInputMessage();
		 * blanketProductionMasterInputMessage
		 * .setBlanketProductionMasterNewDTO(blanketProductionMasterDTO);
		 * //blanketProductionMasterService
		 * .updateBlanketProductionMaster(blanketProductionMasterInputMessage);
		 * }else{ BlanketProductionMasterNewInputMessage
		 * blanketProductionMasterInputMessage=new
		 * BlanketProductionMasterNewInputMessage();
		 * blanketProductionMasterInputMessage
		 * .setBlanketProductionMasterNewDTO(blanketProductionMasterDTO);
		 * blanketProductionMasterService
		 * .createBlanketProductionMaster(blanketProductionMasterInputMessage);
		 * blankeProductionId=
		 * blanketProductionMasterService.getMaxBlanketProdId(); }
		 * blanketProductionMasterDTO
		 * .setBlanketProductionId(blankeProductionId);
		 * BlanketProductionMasterNewInputMessage
		 * blanketProductionMasterInputMessage=null;
		 * List<BlanketProductionDetailNewDTO>
		 * bpdLeftDTOList=blanketProductionMasterDTO
		 * .getBlanketProductionDetailNewDTOList(); if("L".equals(recordType)){
		 * 
		 * int i= bpdLeftDTOList.size(); BlanketProductionDetailNewDTO
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
		 * BlanketProductionMasterNewInputMessage();
		 * blanketProductionMasterInputMessage
		 * .setBlanketProductionDetailNewDTO(blanketProductionDetailLeftDTO);
		 * //blanketProductionMasterService.createBlanketProductionLeft(
		 * blanketProductionMasterInputMessage);
		 * 
		 * if(bpdLeftDTOList==null){ bpdLeftDTOList=new
		 * ArrayList<BlanketProductionDetailNewDTO>();
		 * blanketProductionMasterDTO
		 * .setBlanketProductionDetailNewDTOList(bpdLeftDTOList); }
		 * BlanketProductionDetailNewDTO bpdl=new
		 * BlanketProductionDetailNewDTO(); int rollNoL=
		 * blanketProductionMasterService.getMaxRollNoL();
		 * bpdl.setRollNoLeft(rollNoL);
		 * bpdl.setLengthLeft(blanketProductionDetailLeftDTO.getLengthLeft());
		 * bpdl.setWidthLeft(blanketProductionDetailLeftDTO.getWidthLeft());
		 * bpdl.setThickLeft(blanketProductionDetailLeftDTO.getThickLeft());
		 * //bpdLeftDTOList.add(bpdl);
		 * 
		 * } List<BlanketProductionDetailNewDTO>
		 * bpdDTOList=blanketProductionMasterDTO
		 * .getBlanketProductionDetailNewDTOList(); if("R".equals(recordType)){
		 * 
		 * int i= bpdDTOList.size(); BlanketProductionDetailNewDTO
		 * blanketProductionDetailDTO= bpdDTOList.get(i-1);
		 * blanketProductionDetailDTO
		 * .setBlanketProductionId(blankeProductionId);
		 * blanketProductionDetailDTO.setItemId(itemDTO.getItemId());
		 * if(itemDTO.getItemDensity()!=null){
		 * blanketProductionDetailDTO.setDensity (itemDTO.getItemDensity());
		 * }else{ blanketProductionDetailDTO.setDensity(0.0); }
		 * blanketProductionDetailDTO.setCreatedUserId(getCreatedUserId());
		 * blanketProductionDetailDTO
		 * .setStatus(blanketProductionDetailDTO.getBlanketType());
		 * blanketProductionDetailDTO
		 * .setRejStatus(blanketProductionDetailDTO.getBlanketType());
		 * blanketProductionMasterInputMessage=new
		 * BlanketProductionMasterNewInputMessage();
		 * blanketProductionMasterInputMessage
		 * .setBlanketProductionDetailNewDTO(blanketProductionDetailDTO); //
		 * blanketProductionMasterService.createBlanketProduction(
		 * blanketProductionMasterInputMessage);
		 * 
		 * if(bpdDTOList==null){ bpdDTOList=new
		 * ArrayList<BlanketProductionDetailNewDTO>();
		 * blanketProductionMasterDTO
		 * .setBlanketProductionDetailNewDTOList(bpdDTOList); }
		 * BlanketProductionDetailNewDTO blpr=new
		 * BlanketProductionDetailNewDTO(); int rollNoR=
		 * blanketProductionMasterService.getMaxRollNoR();
		 * blpr.setRollNo(rollNoR); blpr.setLength(blanketProductionDetailDTO
		 * .getLength()); blpr.setWidth(blanketProductionDetailDTO.getWidth());
		 * blpr.setThick(blanketProductionDetailDTO.getThick());
		 * //bpdDTOList.add(blpr); } leftbpListSize=bpdLeftDTOList.size();
		 * rightbpListSize=bpdDTOList.size(); ModelAndView mav=new
		 * ModelAndView("blanketProduction-entry");
		 * mav.addObject("blanketProductionMasterNewForm"
		 * ,blanketProductionMasterNewForm); mav.addObject("leftbpListSize",
		 * leftbpListSize); mav.addObject("rightbpListSize", rightbpListSize);
		 */
		return res;
	}

	// New way to save Blanket production entry.................on select type
	// in Blanket this save will call
	@RequestMapping(value = "save_blanket_in_bp_new")
	public ModelAndView saveBlanket(
			@ModelAttribute("blanketProductionMasterNewForm") BlanketProductionMasterNewForm blanketProductionMasterNewForm,
			@RequestParam(value = "rt", required = false) String recordType,
			@RequestParam(value = "opr", required = false) String opr) {
		BlanketProductionMasterNewDTO blanketProductionMasterDTO = blanketProductionMasterNewForm
				.getBlanketProductionMasterNewDTO();
		
		if (validateBlanketStatus(blanketProductionMasterDTO)) {
			return new ModelAndView("redirect:/get_blanketProduction_list_new");
		}
		
		blanketProductionMasterDTO.setCreatedUserId(getCreatedUserId());
		int leftbpListSize = 0;
		int rightbpListSize = 0;
		int flag = 0;
		Integer blankeProductionId = 0;
		Integer rollNoL = 0;
		if (blanketProductionMasterDTO.getBlanketProductionId() != null
				&& blanketProductionMasterDTO.getBlanketProductionId() > 0) {
			blankeProductionId = blanketProductionMasterDTO
					.getBlanketProductionId();
			BlanketProductionMasterNewInputMessage blanketProductionMasterInputMessage = new BlanketProductionMasterNewInputMessage();
			blanketProductionMasterInputMessage
					.setBlanketProductionMasterNewDTO(blanketProductionMasterDTO);
			// blanketProductionMasterService.updateBlanketProductionMaster(blanketProductionMasterInputMessage);
		} else {
			BlanketProductionMasterNewInputMessage blanketProductionMasterInputMessage = new BlanketProductionMasterNewInputMessage();
			blanketProductionMasterInputMessage
					.setBlanketProductionMasterNewDTO(blanketProductionMasterDTO);
			blanketProductionMasterNewService
					.createBlanketProductionMaster(blanketProductionMasterInputMessage);
			blankeProductionId = blanketProductionMasterNewService
					.getMaxBlanketProdId();
		}
		blanketProductionMasterDTO.setBlanketProductionId(blankeProductionId);

		BlanketProductionMasterNewInputMessage blanketProductionMasterInputMessage = null;
		List<BlanketProductionDetailNewDTO> bpdLeftDTOList = blanketProductionMasterDTO
				.getBlanketProductionDetailNewDTOList();

		int i = 0;
		try {
			i = bpdLeftDTOList.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		BlanketProductionDetailNewDTO blanketProductionDetailLeftDTO = bpdLeftDTOList
				.get(i - 1);

		blanketProductionDetailLeftDTO.setCreatedUserId(getCreatedUserId());
		blanketProductionDetailLeftDTO.setStatus(blanketProductionDetailLeftDTO
				.getBlanketType());

		blanketProductionDetailLeftDTO
				.setBlanketProductionId(blankeProductionId);
		blanketProductionDetailLeftDTO
				.setRejStatus(blanketProductionDetailLeftDTO.getBlanketType());
		blanketProductionMasterInputMessage = new BlanketProductionMasterNewInputMessage();
		//System.out.println("856:"+blanketProductionMasterDTO);
		blanketProductionMasterInputMessage.setBlanketProductionMasterNewDTO(blanketProductionMasterDTO);
		blanketProductionMasterInputMessage
				.setBlanketProductionDetailNewDTO(blanketProductionDetailLeftDTO);
		blanketProductionMasterNewService
				.createBlanketProductionDetail(blanketProductionMasterInputMessage);
		ArrayList<Integer> leftSNO = blanketProductionMasterNewService
				.getMaxRollNo(blanketProductionMasterDTO.getBatchNumber(),
						blanketProductionMasterDTO.getSpliterCount(),
						blanketProductionMasterDTO.getBlanketProductionId(),
						blanketProductionDetailLeftDTO.getRollNo());

		try {
			Integer lsno = leftSNO.get(1);
			blanketProductionDetailLeftDTO.setSno(lsno);
			rollNoL = leftSNO.get(2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (bpdLeftDTOList == null) {
			bpdLeftDTOList = new ArrayList<BlanketProductionDetailNewDTO>();
			blanketProductionMasterDTO
					.setBlanketProductionDetailNewDTOList(bpdLeftDTOList);
		}
		if ("E" != opr) {

			BlanketProductionDetailNewDTO blpr = null;
			if (bpdLeftDTOList.size() == 1
					&& bpdLeftDTOList.get(0).getDensity() == null) {
				blpr = bpdLeftDTOList.get(0);
			} else {
				blpr = new BlanketProductionDetailNewDTO();
			}

			/*
			 * if (bpdDTOList.size() == 1 || (bpdDTOList.get(i-1).getDensity()
			 * == null || bpdDTOList.get(i-1).getWeight() == null)) { blpr =
			 * bpdDTOList.get(i-1); } else { blpr = new
			 * BlanketProductionDetailNewDTO(); }
			 */

			/*
			 * ArrayList<Integer> as = blanketProductionMasterNewService
			 * .getMaxRollNo(blanketProductionMasterDTO.getBatchNumber(),
			 * blanketProductionMasterDTO.getSpliterCount(),
			 * blanketProductionMasterDTO.getBlanketProductionId()); int rollNoR
			 * = as.get(0);
			 */
			blpr.setRollNo(rollNoL);
			Character c = BlanketProductionDetailNewDTO.SPLITER_TYPE_A;

			if (blanketProductionMasterDTO.getSpliterCount() > 1) {

				c = getNextChar(blanketProductionMasterDTO.getSpliterCount(),
						blanketProductionDetailLeftDTO.getSpliterType());
			}
			System.out.println("char" + c);
			// set spliter type
			blpr.setSpliterType(c);

			blpr.setLength(blanketProductionDetailLeftDTO.getLength());
			blpr.setWidth(blanketProductionDetailLeftDTO.getWidth());
			blpr.setThick(blanketProductionDetailLeftDTO.getThick());
			/*
			 * if (bpdDTOList.size() >= 1 && bpdDTOList.get(0).getDensity() !=
			 * null) { bpdDTOList.add(blpr); }
			 */
			System.out.println("list size L" + i);
			if (bpdLeftDTOList.size() >= 1
					&& bpdLeftDTOList.get(bpdLeftDTOList.size() - 1)
							.getDensity() != null) {
				bpdLeftDTOList.add(blpr);
			}
		}

		System.out.println("leftbpListSize" + leftbpListSize
				+ "rightbpListSize" + rightbpListSize);
		try {
			leftbpListSize = bpdLeftDTOList.size();
		} catch (Exception e) {
		}

		System.out.println("leftbpListSize" + leftbpListSize
				+ "rightbpListSize" + rightbpListSize);
		ModelAndView mav = new ModelAndView("blanketProduction-entry_new");
		mav.addObject("blanketProductionMasterNewForm",
				blanketProductionMasterNewForm);
		mav.addObject("leftbpListSize", leftbpListSize);
		mav.addObject("rightbpListSize", rightbpListSize);
		mav.addObject("flag", flag);
		mav.addObject("opr", opr);
		return mav;
	}

	public char getNextChar(Integer count, Character ch) {

		List<Character> list = BlanketProductionDetailNewDTO.SPLITER_LIST;
		Character c = BlanketProductionDetailNewDTO.SPLITER_TYPE_A;
		int index = list.indexOf(ch);
		System.out.println("index" + index + " & count" + count);
		if (count > 1 && index + 1 < count)
			c = list.get(index + 1);
		// if()

		return c;
	}

	@RequestMapping(value = "add_new_row_in_bp_new")
	public ModelAndView addNewRow(
			@ModelAttribute("blanketProductionMasterNewForm") BlanketProductionMasterNewForm blanketProductionMasterNewForm,
			@RequestParam("rt") String recordType,
			@RequestParam("opr") String opr) {
		BlanketProductionMasterNewDTO blanketProductionMasterDTO = blanketProductionMasterNewForm
				.getBlanketProductionMasterNewDTO();

		if (validateBlanketStatus(blanketProductionMasterDTO)) {
			return new ModelAndView("redirect:/get_blanketProduction_list_new");
		}

		int leftbpListSize = 0;
		int rightbpListSize = 0;
		int flag = 0;
		List<BlanketProductionDetailNewDTO> bpdLeftDTOList = blanketProductionMasterDTO
				.getBlanketProductionDetailNewDTOList();

		int i = 0;
		try {
			i = bpdLeftDTOList.size();
		} catch (Exception e) {
		}
		BlanketProductionDetailNewDTO blanketProductionDetailLeftDTO = null;
		if (bpdLeftDTOList != null && bpdLeftDTOList.size() > 0) {
			blanketProductionDetailLeftDTO = bpdLeftDTOList.get(i - 1);
		} else {
			blanketProductionDetailLeftDTO = new BlanketProductionDetailNewDTO();
		}
		if (bpdLeftDTOList == null) {
			bpdLeftDTOList = new ArrayList<BlanketProductionDetailNewDTO>();
			blanketProductionMasterDTO
					.setBlanketProductionDetailNewDTOList(bpdLeftDTOList);
		}
		BlanketProductionDetailNewDTO bpdl = new BlanketProductionDetailNewDTO();

		ArrayList<Integer> as = blanketProductionMasterNewService.getMaxRollNo(
				blanketProductionMasterDTO.getBatchNumber(),
				blanketProductionMasterDTO.getSpliterCount(),
				blanketProductionMasterDTO.getBlanketProductionId(),
				blanketProductionDetailLeftDTO.getRollNo());
		int rollNoL = 0;// = as.get(0);
		rollNoL = as.get(2);
		bpdl.setRollNo(rollNoL);
		Character c = BlanketProductionDetailNewDTO.SPLITER_TYPE_A;

		if (blanketProductionMasterDTO.getSpliterCount() > 1) {

			c = getNextChar(blanketProductionMasterDTO.getSpliterCount(),
					blanketProductionDetailLeftDTO.getSpliterType());
		}
		System.out.println("char" + c);
		// set spliter type
		bpdl.setSpliterType(c);

		bpdl.setLength(blanketProductionDetailLeftDTO.getLength());
		bpdl.setWidth(blanketProductionDetailLeftDTO.getWidth());
		bpdl.setThick(blanketProductionDetailLeftDTO.getThick());
		bpdl.setBlanketProductionId(blanketProductionMasterDTO
				.getBlanketProductionId());
		bpdLeftDTOList.add(bpdl);

		try {
			leftbpListSize = bpdLeftDTOList.size();
		} catch (Exception e) {
		}

		ModelAndView mav = new ModelAndView("blanketProduction-entry_new");
		mav.addObject("blanketProductionMasterNewForm",
				blanketProductionMasterNewForm);
		mav.addObject("leftbpListSize", leftbpListSize);
		mav.addObject("rightbpListSize", rightbpListSize);
		// mav.addObject("opr", opr);
		mav.addObject("flag", flag);
		mav.addObject("opr", "Add");
		return mav;
	}

	@RequestMapping(value = "change_spliter")
	public ModelAndView changeSpliterCount(
			@ModelAttribute("blanketProductionMasterNewForm") BlanketProductionMasterNewForm blanketProductionMasterNewForm) {
		BlanketProductionMasterNewDTO blanketProductionMasterDTO = blanketProductionMasterNewForm
				.getBlanketProductionMasterNewDTO();
		int leftbpListSize = 0;
		int rightbpListSize = 0;
		int flag = 0;
		List<BlanketProductionDetailNewDTO> bpdLeftDTOList = blanketProductionMasterDTO
				.getBlanketProductionDetailNewDTOList();
		List<BlanketProductionDetailNewDTO> resultDTOList = null;
		if (blanketProductionMasterDTO.getSpliterCount() != null
				&& blanketProductionMasterDTO.getSpliterCount() > 0) {
			int spliterCount = 1; // always will be one
			try {
				// spliterCount =blanketProductionMasterDTO.getSpliterCount();
			} catch (Exception e) {
			}
			BlanketProductionDetailNewDTO blanketProductionDetailLeftDTO = null;

			if (bpdLeftDTOList == null) {
				resultDTOList = new ArrayList<BlanketProductionDetailNewDTO>();

			} else {
				resultDTOList = new ArrayList<BlanketProductionDetailNewDTO>();
				for (int i = 0; i < spliterCount; i++) {
					BlanketProductionDetailNewDTO bpdl = new BlanketProductionDetailNewDTO();

					ArrayList<Integer> as = blanketProductionMasterNewService
							.getMaxRollNo(blanketProductionMasterDTO
									.getBatchNumber(),
									blanketProductionMasterDTO
											.getSpliterCount(),
									blanketProductionMasterDTO
											.getBlanketProductionId(), 0);
					int rollNoL = as.get(0);
					bpdl.setRollNo(rollNoL);
					bpdl.setSpliterType(BlanketProductionDetailNewDTO.SPLITER_TYPE_A);

					/*
					 * bpdl.setLength(blanketProductionDetailLeftDTO.getLength())
					 * ;
					 * bpdl.setWidth(blanketProductionDetailLeftDTO.getWidth());
					 * bpdl.setThick(blanketProductionDetailLeftDTO.getThick());
					 */
					bpdl.setBlanketProductionId(blanketProductionMasterDTO
							.getBlanketProductionId());
					resultDTOList.add(bpdl);
				}
			}

			try {
				leftbpListSize = resultDTOList.size();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			blanketProductionMasterDTO
					.setBlanketProductionDetailNewDTOList(resultDTOList);
		}
		blanketProductionMasterNewForm
				.setBlanketProductionMasterNewDTO(blanketProductionMasterDTO);

		ModelAndView mav = new ModelAndView("blanketProduction-entry_new");
		mav.addObject("blanketProductionMasterNewForm",
				blanketProductionMasterNewForm);
		mav.addObject("leftbpListSize", leftbpListSize);
		// mav.addObject("rightbpListSize", rightbpListSize);
		// mav.addObject("opr", opr);
		mav.addObject("flag", flag);
		mav.addObject("opr", "Add");
		return mav;
	}

	@RequestMapping(value = "edite_row_from_bp_new", method = RequestMethod.POST)
	public ModelAndView editRow(
			@ModelAttribute("blanketProductionMasterNewForm") BlanketProductionMasterNewForm blanketProductionMasterNewForm,
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

			BlanketProductionMasterNewDTO blanketProductionMasterDTO = blanketProductionMasterNewForm
					.getBlanketProductionMasterNewDTO();
			List<BlanketProductionDetailNewDTO> bpdLeftDTOList = blanketProductionMasterDTO
					.getBlanketProductionDetailNewDTOList();
			
			if (validateBlanketStatus(blanketProductionMasterDTO)) {
				return new ModelAndView("redirect:/get_blanketProduction_list_new");
			}
			
			
			if (removeStr.startsWith("L") && index >= 0) {
				if (bpdLeftDTOList != null && bpdLeftDTOList.size() > index) {
					BlanketProductionDetailNewDTO dto = bpdLeftDTOList
							.get(index);
					// To check Data is available or not for this record

					ItemDTO itemDTO = new ItemDTO();
					MastersDTO mastersDTO = new MastersDTO();
					mastersDTO.setMastersId(blanketProductionMasterDTO
							.getGradeMasterDTO().getMastersId());
					itemDTO.setMasterGrade(mastersDTO);
					itemDTO.setItemWidth(dto.getWidth());
					itemDTO.setItemLength(dto.getLength());
					itemDTO.setItemHeight(dto.getThick());
					itemDTO.setGrossWeight(dto.getWeight());

					itemDTO = itemService.getItemIdAndDencity(itemDTO);
					if (itemDTO.getItemId() == null) {
						List<BlanketProductionDetailNewDTO> bpdLeftDTOList1 = blanketProductionMasterDTO
								.getBlanketProductionDetailNewDTOList();
						List<BlanketProductionDetailNewDTO> bpdDTOList = blanketProductionMasterDTO
								.getBlanketProductionDetailNewDTOList();
						leftbpListSize = bpdLeftDTOList1.size();
						rightbpListSize = bpdDTOList.size();
						ModelAndView mav = new ModelAndView(
								"blanketProduction-entry_new");
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
						dto.setDensity(itemDTO.getItemDensity());
					}
					// int sno=dto.getSno();
					BlanketProductionMasterNewInputMessage blanketProductionMasterInputMessage = new BlanketProductionMasterNewInputMessage();
					// dto.setSno(null);
					dto.setStatus(dto.getBlanketType());
					dto.setRejStatus(dto.getBlanketType());
					blanketProductionMasterInputMessage
							.setBlanketProductionDetailNewDTO(dto);
					blanketProductionMasterInputMessage.setBlanketProductionMasterNewDTO(blanketProductionMasterDTO);
					blanketProductionMasterNewService
							.createBlanketProductionDetail(blanketProductionMasterInputMessage);
					// dto.setSno(sno);
					// bpdLeftDTOList.set(index, dto);
				}
			}
			/*
			 * List<BlanketProductionDetailNewDTO> bpdDTOList =
			 * blanketProductionMasterDTO
			 * .getBlanketProductionDetailNewDTOList(); if
			 * (removeStr.startsWith("R") && index >= 0) { if (bpdDTOList !=
			 * null && bpdDTOList.size() > index) {
			 * BlanketProductionDetailNewDTO dto = bpdDTOList.get(index);
			 * 
			 * // To check Data is available or not for this record
			 * 
			 * ItemDTO itemDTO = new ItemDTO(); MastersDTO mastersDTO = new
			 * MastersDTO(); mastersDTO.setMastersId(blanketProductionMasterDTO
			 * .getGradeMasterDTO().getMastersId());
			 * itemDTO.setMasterGrade(mastersDTO);
			 * itemDTO.setItemWidth(dto.getWidth());
			 * itemDTO.setItemLength(dto.getLength());
			 * itemDTO.setItemHeight(dto.getThick());
			 * itemDTO.setGrossWeight(dto.getWeight());
			 * 
			 * itemDTO = itemService.getItemIdAndDencity(itemDTO); if
			 * (itemDTO.getItemId() == null) {
			 * List<BlanketProductionDetailNewDTO> bpdLeftDTOList1 =
			 * blanketProductionMasterDTO
			 * .getBlanketProductionDetailNewDTOList(); //
			 * List<BlanketProductionDetailNewDTO> //
			 * bpdDTOList=blanketProductionMasterDTO
			 * .getBlanketProductionDetailNewDTOList(); leftbpListSize =
			 * bpdLeftDTOList1.size(); rightbpListSize = bpdDTOList.size();
			 * ModelAndView mav = new ModelAndView(
			 * "blanketProduction-entry_new"); mav.addObject("leftbpListSize",
			 * leftbpListSize); mav.addObject("rightbpListSize",
			 * rightbpListSize); mav.addObject("errormsg",
			 * "Record is not available for this combination");
			 * mav.addObject("opr", "E"); return mav; }
			 * dto.setModifiedUserId(getCreatedUserId()); // End //
			 * blanketProductionMasterService.deleteBlanketProduction(dto); //
			 * bpdDTOList.remove(index);
			 * 
			 * if (itemDTO.getItemDensity() != null) {
			 * dto.setDensity(itemDTO.getItemDensity()); } // int
			 * sno=dto.getSno(); BlanketProductionMasterNewInputMessage
			 * blanketProductionMasterInputMessage = new
			 * BlanketProductionMasterNewInputMessage(); // dto.setSno(null);
			 * dto.setStatus(dto.getBlanketType());
			 * dto.setRejStatus(dto.getBlanketType());
			 * blanketProductionMasterInputMessage
			 * .setBlanketProductionDetailNewDTO(dto);
			 * blanketProductionMasterNewService
			 * .createBlanketProductionDetail(blanketProductionMasterInputMessage
			 * ); // dto.setSno(sno+1); // bpdDTOList.set(index, dto); }
			 * 
			 * }
			 */
			try {
				leftbpListSize = bpdLeftDTOList.size();
			} catch (Exception e) {
			}
			try {
				// rightbpListSize = bpdDTOList.size();
			} catch (Exception e) {
			}
		}

		ModelAndView mav = new ModelAndView("blanketProduction-entry_new");
		mav.addObject("blanketProductionMasterNewForm",
				blanketProductionMasterNewForm);
		mav.addObject("leftbpListSize", leftbpListSize);
		mav.addObject("rightbpListSize", rightbpListSize);
		mav.addObject("opr", "E");
		return mav;
	}

	@RequestMapping(value = "remove_row_from_bp_new", method = RequestMethod.POST)
	public ModelAndView removeRow(
			@ModelAttribute("blanketProductionMasterNewForm") BlanketProductionMasterNewForm blanketProductionMasterNewForm,
			@RequestParam("rs") String removeStr,
			@RequestParam("opr") String opr,
			@RequestParam("spliterCount") int spliterCount) {
		int leftbpListSize = 0;
		int rightbpListSize = 0;
		String deleteStatusL = null;

		if (removeStr != null) {
			int index = -1;
			try {
				index = Integer.parseInt(removeStr.substring(1));
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

			BlanketProductionMasterNewDTO blanketProductionMasterDTO = blanketProductionMasterNewForm
					.getBlanketProductionMasterNewDTO();
			List<BlanketProductionDetailNewDTO> bpdLeftDTOList = blanketProductionMasterDTO
					.getBlanketProductionDetailNewDTOList();
			
			if (validateBlanketStatus(blanketProductionMasterDTO)) {
				return new ModelAndView("redirect:/get_blanketProduction_list_new");
			}
			

			logger.info("spliterCount:" + spliterCount + ", and Index -"
					+ index);

			if ((index / spliterCount) == Math.round((index / spliterCount))) {

				if (removeStr.startsWith("L") && index >= 0) {
					if (bpdLeftDTOList != null && bpdLeftDTOList.size() > index) {

						BlanketProductionDetailNewDTO dto = bpdLeftDTOList
								.get(index);
						if (dto.getBlanketType() == null
								|| dto.getBlanketType() == "") {
							bpdLeftDTOList.remove(index);

						} else { // else start for remove Complete filled Entry.
							Boolean b = blanketProductionMasterNewService
									.deleteBlanketProductionDetail(dto);
							if (b == false) {
								deleteStatusL = "You can not delete record as it is approved";
							} else {
								bpdLeftDTOList.remove(index);
							}
						}// else end

					}
				}
				/*
				 * List<BlanketProductionDetailNewDTO> bpdDTOList =
				 * blanketProductionMasterDTO
				 * .getBlanketProductionDetailNewDTOList(); if
				 * (removeStr.startsWith("R") && index >= 0) { if (bpdDTOList !=
				 * null && bpdDTOList.size() > index) {
				 * 
				 * BlanketProductionDetailNewDTO dto = bpdDTOList.get(index);
				 * Boolean b = blanketProductionMasterNewService
				 * .deleteBlanketProductionDetail(dto); if (b == false) {
				 * deleteStatusR =
				 * "You can not delete record as it is approved"; } else {
				 * bpdDTOList.remove(index); } }
				 * 
				 * }
				 */
				try {
					leftbpListSize = bpdLeftDTOList.size();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// rightbpListSize = bpdDTOList.size();
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				deleteStatusL = "You can not delete this entry before deleting all latest entries for current Roll No.";
			}
		}

		ModelAndView mav = new ModelAndView("blanketProduction-entry_new");
		mav.addObject("blanketProductionMasterNewForm",
				blanketProductionMasterNewForm);

		mav.addObject("deleteStatusL", deleteStatusL);
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

	@RequestMapping(value = "/checkDuplicateRecordInBPMaster_new", method = RequestMethod.POST)
	public @ResponseBody JsonResponse checkDuplicateRecordInBPMaster(
			@RequestParam("date") Date date,
			@RequestParam("runNo") String runNo,
			@RequestParam("grade") Integer grade,
			@RequestParam("shift") Integer shift,
			@RequestParam("batchNo") String batchNo, ModelMap model) {
		JsonResponse res = new JsonResponse();
		BlanketProductionMasterNewDTO blanketProductionMasterDTO = new BlanketProductionMasterNewDTO();
		blanketProductionMasterDTO.setBlanketProductionDate(date);
		blanketProductionMasterDTO.setRunNo(runNo);
		MastersDTO mastersDTO = new MastersDTO();
		mastersDTO.setMastersId(shift);
		blanketProductionMasterDTO.setShiftMasterDTO(mastersDTO);
		blanketProductionMasterDTO.setBatchNumber(batchNo);
		// String flag=null;
		String flag = blanketProductionMasterNewService
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
	@RequestMapping(value = "/getAnnealingOverMigration_new", method = RequestMethod.GET)
	public ModelAndView getAnnealingOverMigration(ModelMap model) {
		BlanketProductionMasterNewForm blanketProductionMasterNewForm = new BlanketProductionMasterNewForm();

		List<BlanketProductionDetailNewDTO> listLeft = blanketProductionMasterNewService
				.getBlanketProductionDetail(new Date(), 0);

		blanketProductionMasterNewForm
				.setBlanketProductionDetailNewList(listLeft);
		blanketProductionMasterNewForm.setOnDate(new Date());
		model.put("blanketProductionMasterNewForm",
				blanketProductionMasterNewForm);
		ModelAndView mav = new ModelAndView("annealing_oven_migration_new");
		mav.addObject("shiftMastersList", getShiftMastersList());
		return mav;
	}

	// End Migration Form
	@RequestMapping(value = "/updateBlanketAnnealing_new")
	public ModelAndView updateBlanketLeft(
			@ModelAttribute("blanketProductionMasterNewForm") BlanketProductionMasterNewForm blanketProductionMasterNewForm,
			ModelMap model,
			@RequestParam(value = "operation", required = false) String operation) {

		List<BlanketProductionDetailNewDTO> listLeft = null;

		Date date = blanketProductionMasterNewForm.getOnDate();
		Integer shiftId = blanketProductionMasterNewForm
				.getBlanketProductionMasterNewDTO().getShiftMasterDTO()
				.getMastersId();

		if (operation.equalsIgnoreCase("Update")) {

			List<BlanketProductionDetailNewDTO> bpl = blanketProductionMasterNewForm
					.getBlanketProductionDetailNewList();

			blanketProductionMasterNewService.updateBlanketProductionDetail(
					bpl, getCreatedUserId());

			listLeft = blanketProductionMasterNewService
					.getBlanketProductionDetail(date, shiftId);

		}

		if (operation.equalsIgnoreCase("Serarch")) {
			listLeft = blanketProductionMasterNewService
					.getBlanketProductionDetail(date, shiftId);

		}

		blanketProductionMasterNewForm
				.setBlanketProductionDetailNewList(listLeft);

		model.put("blanketProductionMasterNewForm",
				blanketProductionMasterNewForm);

		ModelAndView mav = new ModelAndView("annealing_oven_migration_new");
		mav.addObject("shiftMastersList", getShiftMastersList());
		return mav;
	}

	// .............Rejection Migration..............................//

	// Migration Form
	@RequestMapping(value = "/getRejectionMigration_new", method = RequestMethod.GET)
	public ModelAndView getRejectionMigration(ModelMap model) {
		BlanketProductionMasterNewForm blanketProductionMasterNewForm = new BlanketProductionMasterNewForm();

		List<BlanketProductionDetailNewDTO> listLeft = blanketProductionMasterNewService
				.getRejBlanketProductionDetailList("Rejection", new Date(), 0);
		blanketProductionMasterNewForm
				.setBlanketProductionDetailNewList(listLeft);

		ItemInputMessage itemInputMessage = new ItemInputMessage();
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemGroupName("FINISH GOODS");
		itemInputMessage.setItemDTO(itemDTO);
		ItemOutMessage itemOutMessage = itemService
				.findItemGroupNameForAllReports(itemInputMessage);
		List<ItemDTO> itemList = itemOutMessage.getItemDTOList();
		blanketProductionMasterNewForm.setOnDate(new Date());
		model.put("itemList", itemList);
		model.put("blanketProductionMasterNewForm",
				blanketProductionMasterNewForm);
		ModelAndView mav = new ModelAndView("rejection_migration_new");
		mav.addObject("shiftMastersList", getShiftMastersList());
		return mav;
	}

	// End Migration Form

	// Update Rejection Migration Form............
	@RequestMapping(value = "/updateRejectionBlanket_new")
	public ModelAndView updateRejectionBlanketLeft(
			@ModelAttribute("blanketProductionMasterNewForm") BlanketProductionMasterNewForm blanketProductionMasterNewForm,
			ModelMap model,
			@RequestParam(value = "operation", required = false) String operation) {

		Date date = blanketProductionMasterNewForm.getOnDate();
		Integer shiftId = blanketProductionMasterNewForm
				.getBlanketProductionMasterNewDTO().getShiftMasterDTO()
				.getMastersId();

		List<BlanketProductionDetailNewDTO> listLeft = null;

		if (operation.equalsIgnoreCase("Update")) {
			List<BlanketProductionDetailNewDTO> bpl = blanketProductionMasterNewForm
					.getBlanketProductionDetailNewList();

			blanketProductionMasterNewService
					.updateRejectedBlanketProductionDetail(bpl,
							getCreatedUserId());

			listLeft = blanketProductionMasterNewService
					.getRejBlanketProductionDetailList("Rejection", date,
							shiftId);

		}

		if (operation.equalsIgnoreCase("Serarch")) {
			listLeft = blanketProductionMasterNewService
					.getRejBlanketProductionDetailList("Rejection", date,
							shiftId);

		}

		blanketProductionMasterNewForm
				.setBlanketProductionDetailNewList(listLeft);

		ItemInputMessage itemInputMessage = new ItemInputMessage();
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemGroupName("FINISH GOODS");
		itemInputMessage.setItemDTO(itemDTO);
		ItemOutMessage itemOutMessage = itemService
				.findItemGroupNameForAllReports(itemInputMessage);
		List<ItemDTO> itemList = itemOutMessage.getItemDTOList();

		model.put("itemList", itemList);
		model.put("blanketProductionMasterNewForm",
				blanketProductionMasterNewForm);
		ModelAndView mav = new ModelAndView("rejection_migration_new");
		mav.addObject("shiftMastersList", getShiftMastersList());
		return mav;
	}

	// Update Rejection Migration Form............END..

	// Approved Blanket Form
	@RequestMapping(value = "/getApprovedBlanket_new", method = RequestMethod.GET)
	public ModelAndView getApprovedBlanket(ModelMap model) {
		BlanketProductionMasterNewForm blanketProductionMasterNewForm = new BlanketProductionMasterNewForm();
		List<BlanketProductionDetailNewDTO> listLeft = blanketProductionMasterNewService
				.getRejBlanketProductionDetailList("OK','A Grade", new Date(),
						0);
		blanketProductionMasterNewForm
				.setBlanketProductionDetailNewList(listLeft);

		/*
		 * ItemInputMessage itemInputMessage=new ItemInputMessage(); ItemDTO
		 * itemDTO=new ItemDTO(); itemDTO.setItemGroupName("FINISH GOODS");
		 * itemInputMessage.setItemDTO(itemDTO); ItemOutMessage itemOutMessage=
		 * itemService.findItemGroupNameForAllReports(itemInputMessage);
		 * List<ItemDTO> itemList= itemOutMessage.getItemDTOList();
		 * 
		 * model.put("itemList", itemList);
		 */
		blanketProductionMasterNewForm.setOnDate(new Date());
		model.put("blanketProductionMasterNewForm",
				blanketProductionMasterNewForm);
		ModelAndView mav = new ModelAndView("approved_blanket_new");
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
	@RequestMapping(value = "/updateApprovedBlanket_new", method = RequestMethod.POST)
	public ModelAndView updateApprovedBlanket(
			@ModelAttribute("blanketProductionMasterNewForm") BlanketProductionMasterNewForm blanketProductionMasterNewForm,
			@RequestParam(value = "operation", required = false) String operation,
			ModelMap model) {

		// To get new finished good number...............
		List<BlanketProductionDetailNewDTO> listLeft = null;
		List<BlanketProductionDetailNewDTO> list = null;
		Date onDate = null;
		onDate = blanketProductionMasterNewForm.getOnDate();
		Integer shiftId = blanketProductionMasterNewForm
				.getBlanketProductionMasterNewDTO().getShiftMasterDTO()
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
		 * list=blanketProductionMasterService
		 * .getRejBlanketProductionList("OK",date);
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

		List<BlanketProductionDetailNewDTO> bpl = blanketProductionMasterNewForm
				.getBlanketProductionDetailNewList();

		blanketProductionMasterNewService
				.updateApprovedBlanketProductionDetail(bpl, getCreatedUserId(),
						finishedGoodsMasterDTO);

		listLeft = blanketProductionMasterNewService
				.getRejBlanketProductionDetailList("OK','A Grade", onDate,
						shiftId);

		// }

		blanketProductionMasterNewForm
				.setBlanketProductionDetailNewList(listLeft);

		/*
		 * ItemInputMessage itemInputMessage=new ItemInputMessage(); ItemDTO
		 * itemDTO=new ItemDTO(); itemDTO.setItemGroupName("FINISH GOODS");
		 * itemInputMessage.setItemDTO(itemDTO); ItemOutMessage itemOutMessage=
		 * itemService.findItemGroupNameForAllReports(itemInputMessage);
		 * List<ItemDTO> itemList= itemOutMessage.getItemDTOList();
		 * 
		 * model.put("itemList", itemList);
		 */
		model.put("blanketProductionMasterNewForm",
				blanketProductionMasterNewForm);
		ModelAndView mav = new ModelAndView("approved_blanket_new");
		mav.addObject("shiftMastersList", getShiftMastersList());
		return mav;
	}

	// Update Approved Blanket Form............END..

	@RequestMapping(value = "/isItemAvailable_new", method = RequestMethod.POST)
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

	@RequestMapping(value = "/unApproveBlanket_new", method = RequestMethod.POST)
	public @ResponseBody JsonResponse unApproveBlanket(
			@RequestParam("date") Date date,
			@RequestParam("runNo") String runNo,
			@RequestParam("shiftId") Integer shiftId, ModelMap model) {
		blanketProductionMasterNewService.updateBlanketProduction(date, runNo,
				shiftId, "", 0);
		return null;
	}

	@RequestMapping(value = "/approveBlanket_new", method = RequestMethod.POST)
	public @ResponseBody JsonResponse approveBlanket(
			@RequestParam("date") Date date,
			@RequestParam("runNo") String runNo,
			@RequestParam("shiftId") Integer shiftId, ModelMap model) {
		blanketProductionMasterNewService.updateBlanketProduction(date, runNo,
				shiftId, "", 1);
		return null;
	}

	@RequestMapping(value = "/getBlanketProductionData_new", method = RequestMethod.POST)
	public @ResponseBody JsonResponse getBlanketProductionData(
			@RequestParam("date") Date date,
			@RequestParam("runNo") String runNo,
			@RequestParam("shiftId") Integer shiftId, ModelMap model) {
		JsonResponse res = new JsonResponse();
		ShiftReportMasterDTO shiftReportMasterDTO = new ShiftReportMasterDTO();
		List<ShiftReportMasterDTO> shiftList = new ArrayList<ShiftReportMasterDTO>();
		BlanketProductionMasterNewDTO bpmDTO = new BlanketProductionMasterNewDTO();
		BlanketProductionMasterNewInputMessage bpmInputMessage = new BlanketProductionMasterNewInputMessage();
		MastersDTO mastersDTO = new MastersDTO();
		bpmDTO.setBlanketProductionDate(date);
		bpmDTO.setRunNo(runNo);
		mastersDTO.setMastersId(shiftId);
		bpmDTO.setShiftMasterDTO(mastersDTO);
		bpmInputMessage.setBlanketProductionMasterNewDTO(bpmDTO);
		BlanketProductionMasterNewOutputMessage bpmOutputmessage = blanketProductionMasterNewService
				.getDataForShiftReport(bpmInputMessage);
		List<BlanketProductionMasterNewDTO> list = bpmOutputmessage
				.getBlanketProductionMasterNewDTOList();
		if (list != null && list.size() > 0) {
			bpmDTO = list.get(0);

			int noOfBlankets = bpmDTO.getBlanketProductionDetailNewDTOList()
					.size()
					+ bpmDTO.getBlanketProductionDetailNewDTOList().size();
			shiftReportMasterDTO.setNoOfBlankets(noOfBlankets);
			shiftReportMasterDTO.setShortLengthBlanketsWeight(bpmDTO
					.getShortLenght());
			shiftReportMasterDTO.setEdgeTrimWeight(bpmDTO.getEdgeTrim());
			shiftReportMasterDTO.setBulkFibreWeight(bpmDTO.getBulkFiber());

			Double weightL = 0.0;
			Double totalweightL = 0.0;
			for (int i = 0; i < bpmDTO.getBlanketProductionDetailNewDTOList()
					.size(); i++) {
				BlanketProductionDetailNewDTO bplDTO = bpmDTO
						.getBlanketProductionDetailNewDTOList().get(i);
				weightL += bplDTO.getWeight();
				// if(bplDTO.getBlanketType()!="Rejection"){
				totalweightL += bplDTO.getWeight();
				// }

			}
			Double weightR = 0.0;
			Double totalweightR = 0.0;
			for (int i = 0; i < bpmDTO.getBlanketProductionDetailNewDTOList()
					.size(); i++) {
				BlanketProductionDetailNewDTO bprDTO = bpmDTO
						.getBlanketProductionDetailNewDTOList().get(i);
				weightR += bprDTO.getWeight();
				// if(bprDTO.getBlanketType()!="Rejection"){
				totalweightR += bprDTO.getWeight();
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
			shiftReportMasterDTO.setBlanketsWeigth(totalweightL + totalweightR);
			shiftReportMasterDTO.setTotalWeight(total);
			shiftList.add(shiftReportMasterDTO);

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

	@RequestMapping(value = "/getMaxRollNo_new", method = RequestMethod.POST)
	public @ResponseBody JsonResponse getMaxRollNo(
			@RequestParam("batchNo") String batchNo, ModelMap model) {
		JsonResponse res = new JsonResponse();
		BlanketProductionMasterNewDTO bpmDTO = new BlanketProductionMasterNewDTO();

		/*
		 * ArrayList<Integer> as = blanketProductionMasterNewService
		 * .getMaxRollNo(batchNo,null); int rollNoR = as.get(0);
		 */

		ArrayList<Integer> as1 = blanketProductionMasterNewService
				.getMaxRollNo(batchNo, null, null, null);
		int rollNoL = as1.get(0);

		bpmDTO.setRollNo(rollNoL);
		// bpmDTO.setRollNo(rollNoR);
		res.setResult(bpmDTO);
		return res;
	}

	@RequestMapping(value = "/checkDuplicatRollNo_new", method = RequestMethod.POST)
	public @ResponseBody JsonResponse checkDuplicatRollNoL(
			@RequestParam("batchNo") String batchNo,
			@RequestParam("rollnumberL") Integer rollnumberL, ModelMap model) {
		JsonResponse res = new JsonResponse();
		boolean flag = false;
		List list = blanketProductionMasterNewService.getCheckDuplicatRollNo(
				batchNo, rollnumberL);
		if (list != null && list.size() > 0) {
			flag = true;
		}
		res.setResult(flag);
		return res;
	}

	@RequestMapping(value = "get_weight_new")
	public @ResponseBody JsonResponse getWeight(
			@ModelAttribute("blanketProductionMasterNewForm") BlanketProductionMasterNewForm blanketProductionMasterNewForm,
			@RequestParam("rt") String recordType,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "lenght", required = false) Double lenght,
			@RequestParam(value = "width", required = false) Double width,
			@RequestParam(value = "thickness", required = false) Double thickness,
			@RequestParam(value = "weight", required = false) Double weight) {
		JsonResponse res = new JsonResponse();
		BlanketProductionMasterNewDTO blanketProductionMasterDTO = blanketProductionMasterNewForm
				.getBlanketProductionMasterNewDTO();
		// blanketProductionMasterDTO.setCreatedUserId(getCreatedUserId());
		System.out.println("Lenght :" + lenght + "gradeId:" + gradeId
				+ "width :" + width + "thickness:" + thickness + " weight:"
				+ weight);

		/*
		 * get Weight from Weighting machine.
		 */
		weight = blanketProductionMasterNewService.getBlanketWeightRecord(
				recordType.charAt(0), getCreatedUserId());

		res.setResult(weight);

		return res;
	}

	public boolean validateBlanketStatus(BlanketProductionMasterNewDTO dto) {
		BlanketProductionMasterNewInputMessage blanketProductionMasterNewInputMessage = new BlanketProductionMasterNewInputMessage();
		BlanketProductionMasterNewDTO blanketProductionMasterNewDTO = new BlanketProductionMasterNewDTO();
		BlanketProductionMasterNewOutputMessage blanketProductionMasterNewOutMessage = new BlanketProductionMasterNewOutputMessage();
		blanketProductionMasterNewDTO.setBlanketProductionId(dto
				.getBlanketProductionId());
		//System.out.println("blanketProductionMasterNewDTO"+blanketProductionMasterNewDTO);
		blanketProductionMasterNewInputMessage
				.setBlanketProductionMasterNewDTO(blanketProductionMasterNewDTO);
		blanketProductionMasterNewOutMessage = blanketProductionMasterNewService
				.findBlanketProductionMasterById(blanketProductionMasterNewInputMessage);
		ArrayList<BlanketProductionMasterNewDTO> list = (ArrayList<BlanketProductionMasterNewDTO>) blanketProductionMasterNewOutMessage
				.getBlanketProductionMasterNewDTOList();
		if (list != null && list.size() > 0) {
			blanketProductionMasterNewDTO = list.get(0);
			if (blanketProductionMasterNewDTO.getApproveStatus() != null
					&& blanketProductionMasterNewDTO.getApproveStatus() == 1) {
				return true;
			}
		}
		return false;

	}
}