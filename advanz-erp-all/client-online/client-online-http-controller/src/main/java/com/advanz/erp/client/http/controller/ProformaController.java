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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.advanz.DataUtility;
import com.advanz.erp.client.http.controller.form.InvoiceForm;
import com.advanz.erp.client.http.controller.form.PartyForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.CityDTO;
import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.ProformaDetailDTO;
import com.advanz.erp.masters.model.ProformaMasterDTO;
import com.advanz.erp.masters.model.StockLedgerDTO;
import com.advanz.erp.masters.model.TransactionTypeDTO;
import com.advanz.erp.masters.model.TransporterDTO;
import com.advanz.erp.masters.model.msg.BatchInputMessage;
import com.advanz.erp.masters.model.msg.BatchOutMessage;
import com.advanz.erp.masters.model.msg.BranchInputMessage;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.model.msg.CityInputMessage;
import com.advanz.erp.masters.model.msg.CityOutputMessage;
import com.advanz.erp.masters.model.msg.EmployeeOutputMessage;
import com.advanz.erp.masters.model.msg.ItemGroupFlagOutMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.model.msg.PartyInputMessage;
import com.advanz.erp.masters.model.msg.PartyOutMessage;
import com.advanz.erp.masters.model.msg.ProformaDetailInputMessage;
import com.advanz.erp.masters.model.msg.ProformaDetailOutMessage;
import com.advanz.erp.masters.model.msg.ProformaMasterInputMessage;
import com.advanz.erp.masters.model.msg.ProformaMasterOutMessage;
import com.advanz.erp.masters.model.msg.StockLedgerInputMessage;
import com.advanz.erp.masters.model.msg.StockLedgerOutMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeInputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeOutputMessage;
import com.advanz.erp.masters.model.msg.TransporterOutMessage;
import com.advanz.erp.masters.service.business.IBatchService;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.service.business.ICityService;
import com.advanz.erp.masters.service.business.IEmployeeService;
import com.advanz.erp.masters.service.business.IItemGroupFlagService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IPartyService;
import com.advanz.erp.masters.service.business.IProformaDetailService;
import com.advanz.erp.masters.service.business.IProformaMasterService;
import com.advanz.erp.masters.service.business.IStockLedgerService;
import com.advanz.erp.masters.service.business.ITransactionTypeService;
import com.advanz.erp.masters.service.business.ITransporterService;

@Controller
public class ProformaController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(ProformaController.class);
	@Autowired
	public IEmployeeService employeeService;
	@Autowired
	public IPartyService partyService;

	@Autowired
	public ITransporterService transporterService;
	@Autowired
	public IItemService itemService;
	@Autowired
	public IProformaMasterService proformaMasterService;

	@Autowired
	public IProformaDetailService proformaDetailService;

	@Autowired
	public IStockLedgerService stockLedgerService;

	@Autowired
	public IMastersService mastersetService;

	@Autowired
	public IBatchService batchService;
	@Autowired
	public ICityService cityService;

	@Autowired
	public IBranchService branchService;

	@Autowired
	public ITransactionTypeService transactionTypeService;

	@Autowired
	IItemGroupFlagService itemGroupFlagService;

	@RequestMapping(value = "/show_proforma", method = RequestMethod.GET)
	public ModelAndView display(
			@ModelAttribute("invoice") InvoiceForm invoiceForm,
			@RequestParam String operation, HttpSession session) {

		ModelAndView mav = preloadedData();

		ErrorDTO error = new ErrorDTO();
		if ("Delete".equals(operation) || "V".equals(operation)
				|| "V1".equals(operation)) {
			error.setErrorCode("Invoice  Remove Confirmation");
			error.setOperationName(operation);
		}/*
		 * if("Get_Invoice".equals(operation)){
		 * error.setErrorCode("edite invoice confermation"); }
		 */
		String finecialYear = getFinYear();
		String series = getInvoiceTransactionSeries();
		if ("Get_Invoice".equals(operation) || "Delete".equals(operation)
				|| "V".equals(operation) || "V1".equals(operation)) {

			Integer invoiceAutoId = invoiceForm.getInvoiceAutoId();
			ProformaMasterInputMessage billInputMessage = new ProformaMasterInputMessage();
			ProformaMasterDTO billDTO = new ProformaMasterDTO();
			billDTO.setInvoiceAutoId(invoiceAutoId);
			billInputMessage.setProformaMasterDTO(billDTO);
			ProformaMasterOutMessage billOutMessage = proformaMasterService
					.findBillById(billInputMessage);
			List billList = billOutMessage.getProformaMasterDTOList();
			if (billList != null && billList.size() > 0) {
				billDTO = (ProformaMasterDTO) billList.get(0);
				try {
					invoiceForm.setTimeToshow(billDTO.getTimeOfRemoval()
							.toString());
				} catch (Exception e) {
				}
				billDTO.setTransactionSeries(series + "/" + billDTO.getFinyr());
			}

			/*
			 * if(billDTO.getExciseInvoiceNo()!=null){ ModelAndView mv = new
			 * ModelAndView(new RedirectView(
			 * "show_proforma_list?operation=converted")); return mv; }
			 */

			// get Branch Information
			BranchDTO branchDTO = new BranchDTO();
			branchDTO.setBranchId(billDTO.getBranchDTO().getBranchId());

			BranchInputMessage branchInputMessage = new BranchInputMessage();
			branchInputMessage.setBranchDTO(branchDTO);
			BranchOutMessage branchOutMessage = branchService
					.findBranchById(branchInputMessage);
			ArrayList<BranchDTO> branchList = (ArrayList<BranchDTO>) branchOutMessage
					.getBranchDTOList();
			if (branchList != null && branchList.size() > 0) {
				branchDTO = branchList.get(0);
				invoiceForm.setDutyVideNotification(branchDTO
						.getDutyVideNotification());

			}

			String invoiceNumber = billDTO.getInvoiceNumber();

			ProformaDetailDTO billDetailDTO = new ProformaDetailDTO();
			ProformaDetailInputMessage billDetailInputMessage = new ProformaDetailInputMessage();
			billDetailDTO.setInvoiceNumber(billDTO.getInvoiceNumber());
			billDetailInputMessage.setProformaDetailDTO(billDetailDTO);
			ProformaDetailOutMessage billDetailOutMessage = proformaDetailService
					.findBillByBillId(billDetailInputMessage);

			List billDtailList = billDetailOutMessage
					.getProformaDetailDTOList();

			// To get Item Name
			for (int i = 0; i < billDtailList.size(); i++) {
				billDetailDTO = (ProformaDetailDTO) billDtailList.get(i);
				Integer itemId = billDetailDTO.getItemId();
				ItemDTO itemDTO = new ItemDTO();
				itemDTO.setItemId(itemId);
				ItemInputMessage inputMessage = new ItemInputMessage();
				inputMessage.setItemDTO(itemDTO);
				ItemOutMessage itemOutMessage = itemService
						.findItemById(inputMessage);
				ArrayList<ItemDTO> itemList = (ArrayList<ItemDTO>) itemOutMessage
						.getItemDTOList();
				itemDTO = itemList.get(0);
				String itemName = itemDTO.getItemName();
				billDetailDTO.setItemName(itemName);

				try {
					if (billDTO.getVatCstTaxType().equals("vat")) {
						billDetailDTO.setTax(billDetailDTO.getVatPerc());
					}
					if (billDTO.getVatCstTaxType().equals("cst with c form")) {
						billDetailDTO.setTax(billDetailDTO.getCstPerc());
					}
					if (billDTO.getVatCstTaxType().equals("cst w/o c form")) {
						billDetailDTO.setTax(billDetailDTO.getVatPerc());
					}
				} catch (Exception e) {
				}

				billDetailDTO.setDiscountAmountPerToShow(billDetailDTO
						.getDiscountPer());
				// Get MFG Date From Batch
				BatchInputMessage batchInputMessage = new BatchInputMessage();
				BatchDTO batchDTO = new BatchDTO();
				batchInputMessage.setItemId(itemDTO.getItemId());
				BatchOutMessage batchOutMessage = batchService
						.findAllBatchByItemId(batchInputMessage);
				ArrayList<BatchDTO> batchList = (ArrayList<BatchDTO>) batchOutMessage
						.getBatchDTOList();
				if (batchList != null && batchList.size() > 0) {
					batchDTO = batchList.get(0);
				}
				billDetailDTO.setMfgDate(batchDTO.getMfgDate());

				// Get UMO Name

				MastersInputMessage mastersInputMessage = new MastersInputMessage();
				MastersDTO mastersDTO = new MastersDTO();
				mastersDTO.setMastersId(billDetailDTO.getMeasurementUnitId());
				mastersInputMessage.setMastersDTO(mastersDTO);
				MastersOutputMessage mastersOutputMessage = mastersetService
						.findMastersById(mastersInputMessage);
				ArrayList<MastersDTO> masterList = (ArrayList<MastersDTO>) mastersOutputMessage
						.getMastersDTOList();
				if (masterList != null && masterList.size() > 0) {
					mastersDTO = masterList.get(0);
				}
				billDetailDTO.setUmoName(mastersDTO.getName());

			}

			invoiceForm.setProformaDetailList(billDtailList);

			// Get Party Information
			PartyInputMessage partyInputMessage = new PartyInputMessage();
			PartyDTO partyDTO = new PartyDTO();
			try {
				partyDTO.setPartyId(billDTO.getPartyDTO().getPartyId());

				partyInputMessage.setPartyDTO(partyDTO);
				PartyOutMessage partyOutMessage = partyService
						.findPartyById(partyInputMessage);
				List<PartyDTO> list = new ArrayList<PartyDTO>();
				list = (ArrayList<PartyDTO>) partyOutMessage.getPartyDTOList();
				if (list != null && list.size() > 0) {
					partyDTO = list.get(0);
				}

				// Get state name
				CityDTO cityDTO = new CityDTO();
				CityInputMessage cityInputMessage = new CityInputMessage();
				cityDTO.setCityId(partyDTO.getBillingCityId());
				cityInputMessage.setCityDTO(cityDTO);
				CityOutputMessage cityOutputMessage = cityService
						.findCityById(cityInputMessage);
				ArrayList<CityDTO> cityList = (ArrayList<CityDTO>) cityOutputMessage
						.getCityDTOList();
				if (cityList != null && cityList.size() > 0) {
					cityDTO = cityList.get(0);
				}

				partyDTO.setState(cityDTO.getAreaDTO().getRegionDTO()
						.getStateDTO().getStateName());

				invoiceForm.setState(cityDTO.getAreaDTO().getRegionDTO()
						.getStateDTO().getStateName());
				invoiceForm.setCityName(partyDTO.getCityName());
				invoiceForm.setContactPerson(partyDTO.getContactPerson1());
				invoiceForm.setBillingAddress(partyDTO.getBillingAddress());
				invoiceForm.setPhonNo(partyDTO.getPhoneO1());
				invoiceForm.setProformaMasterDTO(billDTO);

				// Get Buyer Information Start

				partyDTO.setPartyId(billDTO.getConsigneeId());
				partyInputMessage.setPartyDTO(partyDTO);
				partyOutMessage = partyService.findPartyById(partyInputMessage);
				list = new ArrayList<PartyDTO>();
				list = (ArrayList<PartyDTO>) partyOutMessage.getPartyDTOList();
				if (list != null && list.size() > 0) {
					partyDTO = list.get(0);
				}

				// Get Buyer State Name
				cityDTO = new CityDTO();
				cityInputMessage = new CityInputMessage();
				cityDTO.setCityId(partyDTO.getBillingCityId());
				cityInputMessage.setCityDTO(cityDTO);
				cityOutputMessage = cityService.findCityById(cityInputMessage);
				cityList = (ArrayList<CityDTO>) cityOutputMessage
						.getCityDTOList();
				if (cityList != null && cityList.size() > 0) {
					cityDTO = cityList.get(0);
				}
				invoiceForm.setBuyerState(cityDTO.getAreaDTO().getRegionDTO()
						.getStateDTO().getStateName());
				invoiceForm.setBuyerCityName(partyDTO.getCityName());
				invoiceForm.setBuyerContactPerson(partyDTO.getContactPerson1());
				invoiceForm
						.setBuyerBillingAddress(partyDTO.getBillingAddress());
				invoiceForm.setBuyerPhonNo(partyDTO.getPhoneO1());
				// End Buyer Information
			} catch (Exception e) {
			}

			session.setAttribute("invoice", invoiceForm);
		}

		// ModelAndView mav = new ModelAndView();
		ProformaMasterDTO dto = null;
		Timestamp timestamp = null;
		SimpleDateFormat ft = null;
		if (session.getAttribute("invoice") == null) {
			if (operation == null || operation.trim().length() == 0) {
				if (invoiceForm.getId() > 0) {
					ProformaMasterInputMessage message = new ProformaMasterInputMessage();
					dto.setInvoiceAutoId((int) invoiceForm.getId());
					message.setProformaMasterDTO(dto);
					ProformaMasterOutMessage output = proformaMasterService
							.findBillById(message);
					dto = output.getProformaMasterDTO();
					dto.setInvoiceId(dto.getInvoiceAutoId());
					invoiceForm.setProformaMasterDTO(dto);
					session.setAttribute("invoice", invoiceForm);
				}
			}

			ProformaMasterInputMessage inputMessage = new ProformaMasterInputMessage();
			ProformaMasterDTO billDTO = new ProformaMasterDTO();
			billDTO.setFinyr(getFinYear());
			billDTO.setTransactionSeries(series + "/" + getFinYear());
			inputMessage.setProformaMasterDTO(billDTO);

			ProformaMasterOutMessage billOutMessage = proformaMasterService
					.getNewProformaMasterSeriesNo(inputMessage);

			// ProformaMasterOutMessage billOutMessage =
			// proformaMasterService.getLastByInvoiceId();
			Integer ProformaId = billOutMessage.getProformaSeries();

			/*
			 * ArrayList<ProformaMasterDTO> list =
			 * (ArrayList<ProformaMasterDTO>) billOutMessage
			 * .getProformaMasterDTOList();
			 * 
			 * 
			 * if (list.size() > 0) { billDTO = list.get(0); }
			 */

			timestamp = billOutMessage.getProformaSeriesDate();
			// System.out.println("Last Finished good time"+new
			// Date(timestamp.getTime()));
			ft = new SimpleDateFormat("yyyy,MM,dd");

			ProformaMasterDTO billDTO2 = new ProformaMasterDTO();
			billDTO2.setTransactionSeries(series + "/" + finecialYear);
			billDTO2.setFinyr(finecialYear);

			/*
			 * if (billDTO.getInvoiceId() != null) {
			 * billDTO2.setInvoiceNumber(series+"-"+finecialYear+"-" +
			 * (billDTO.getInvoiceId() + 1));
			 * billDTO2.setInvoiceId(billDTO.getInvoiceId() + 1); } else {
			 * billDTO2.setInvoiceNumber(series+"-"+finecialYear+"-" + 1);
			 * billDTO2.setInvoiceId(1); }
			 */
			billDTO2.setInvoiceNumber(series + "/" + finecialYear + "/"
					+ ProformaId);
			billDTO2.setInvoiceId(ProformaId);
			billDTO2.setItemGroupFlagId(3);
			billDTO2.setInvoiceDate(new Date());
			invoiceForm.setProformaMasterDTO(billDTO2);
		} else {
			// Get Party Information From Sales Order Detail
			invoiceForm = (InvoiceForm) session.getAttribute("invoice");
			if (invoiceForm.getPartyId() != null) {
				ProformaMasterDTO billDTO = invoiceForm.getProformaMasterDTO();

				if (invoiceForm.getSalesOrderDate() != null) {
					billDTO.setSalesOrderDate(invoiceForm.getSalesOrderDate());
				}

				PartyInputMessage partyInputMessage = new PartyInputMessage();
				PartyDTO partyDTO = new PartyDTO();
				partyDTO.setPartyId(invoiceForm.getPartyId());
				partyInputMessage.setPartyDTO(partyDTO);
				PartyOutMessage partyOutMessage = partyService
						.findPartyById(partyInputMessage);
				List<PartyDTO> list = new ArrayList<PartyDTO>();
				list = (ArrayList<PartyDTO>) partyOutMessage.getPartyDTOList();
				if (list != null && list.size() > 0) {
					partyDTO = list.get(0);
				}
				if (partyDTO.getInvoiceType() != null) {
					String invoiceType = partyDTO.getInvoiceType();
					if (invoiceType.equals("salesOutsideState")) {
						invoiceForm.setTaxType("VAT");
						billDTO.setSalesType("Sales with In State");
					} else {
						invoiceForm.setTaxType("CST");
						billDTO.setSalesType("Sales Outside State");
					}
				}

				// Get state name
				CityDTO cityDTO = new CityDTO();
				CityInputMessage cityInputMessage = new CityInputMessage();
				cityDTO.setCityId(partyDTO.getBillingCityId());
				cityInputMessage.setCityDTO(cityDTO);

				CityOutputMessage cityOutputMessage = cityService
						.findCityById(cityInputMessage);
				ArrayList<CityDTO> cityList = (ArrayList<CityDTO>) cityOutputMessage
						.getCityDTOList();
				if (cityList != null) {
					cityDTO = cityList.get(0);
				}
				partyDTO.setState(cityDTO.getAreaDTO().getRegionDTO()
						.getStateDTO().getStateName());
				invoiceForm.setState(cityDTO.getAreaDTO().getRegionDTO()
						.getStateDTO().getStateName());
				try {
					billDTO.getPartyDTO().setPartyId(invoiceForm.getPartyId());
				} catch (Exception e) {

				}
				invoiceForm.setCityName(partyDTO.getCityName());
				invoiceForm.setContactPerson(partyDTO.getContactPerson1());
				invoiceForm.setBillingAddress(partyDTO.getBillingAddress());
				invoiceForm.setPhonNo(partyDTO.getPhoneO1());

				// Get Buyer Information Start
				billDTO.setConsigneeId(billDTO.getConsigneeId());
				partyDTO.setPartyId(billDTO.getConsigneeId());
				partyInputMessage.setPartyDTO(partyDTO);
				partyOutMessage = partyService.findPartyById(partyInputMessage);
				list = new ArrayList<PartyDTO>();
				list = (ArrayList<PartyDTO>) partyOutMessage.getPartyDTOList();
				if (list != null && list.size() > 0) {
					partyDTO = list.get(0);
				}

				// Get Buyer State Name
				cityDTO = new CityDTO();
				cityInputMessage = new CityInputMessage();
				cityDTO.setCityId(partyDTO.getBillingCityId());
				cityInputMessage.setCityDTO(cityDTO);
				cityOutputMessage = cityService.findCityById(cityInputMessage);
				cityList = (ArrayList<CityDTO>) cityOutputMessage
						.getCityDTOList();
				if (cityList != null) {
					cityDTO = cityList.get(0);
				}
				invoiceForm.setBuyerState(cityDTO.getAreaDTO().getRegionDTO()
						.getStateDTO().getStateName());
				invoiceForm.setBuyerCityName(partyDTO.getCityName());
				invoiceForm.setBuyerContactPerson(partyDTO.getContactPerson1());
				invoiceForm
						.setBuyerBillingAddress(partyDTO.getBillingAddress());
				invoiceForm.setBuyerPhonNo(partyDTO.getPhoneO1());
				// End Buyer Information

				invoiceForm.setProformaMasterDTO(billDTO);
			}
			invoiceForm = (InvoiceForm) session.getAttribute("invoice");

		}
		if (ft != null && timestamp != null)
			invoiceForm.setLastInvoiceDate(ft.format(new Date(timestamp
					.getTime())));
		mav.addObject("operation", operation);
		mav.addObject("error", error);
		mav.setViewName("proforma_add");
		mav.addObject("invoice", invoiceForm);
		return mav;
	}

	@RequestMapping(value = "/save_proforma", method = RequestMethod.POST)
	public ModelAndView submit(
			@ModelAttribute("invoice") InvoiceForm invoiceForm,
			@RequestParam(value = "operation", required = false) String operation,
			HttpSession session) {
		String finecialYear = getFinYear();
		String series = getInvoiceTransactionSeries();
		ModelAndView mav = preloadedData();
		session.setAttribute("invoice", invoiceForm);
		ErrorDTO error = new ErrorDTO();

		if (("Get Tax").equals(invoiceForm.getTaxTypeToshow())) {

			/*
			 * ModelAndView mv = new ModelAndView(new RedirectView(
			 * "show_proforma?operation=show")); return mv;
			 */
			invoiceForm = (InvoiceForm) session.getAttribute("invoice");

			List pdl = invoiceForm.getProformaDetailList();
			if (pdl != null && pdl.size() > 0) {
				for (int i = 0; i < pdl.size(); i++) {
					ProformaDetailDTO billDetailDTO = (ProformaDetailDTO) pdl
							.get(i);
					Integer itemId = billDetailDTO.getItemId();
					ItemDTO dto = new ItemDTO();
					dto.setItemId(itemId);
					ItemInputMessage itemInputMessage = new ItemInputMessage();
					itemInputMessage.setItemDTO(dto);
					ItemOutMessage itemOutMessage = itemService
							.findItemById(itemInputMessage);
					ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage
							.getItemDTOList();
					dto = (ItemDTO) list.get(0);
					if (invoiceForm.getProformaMasterDTO().getVatCstTaxType()
							.equalsIgnoreCase("vat")) {
						billDetailDTO.setTax(dto.getVatPerc());
					}
					if (invoiceForm.getProformaMasterDTO().getVatCstTaxType()
							.equalsIgnoreCase("cst with c form")) {
						billDetailDTO.setTax(dto.getCstPerc());
					}
					if (invoiceForm.getProformaMasterDTO().getVatCstTaxType()
							.equalsIgnoreCase("cst w/o c form")) {
						billDetailDTO.setTax(dto.getVatPerc());
					}
					if (invoiceForm.getProformaMasterDTO().getVatCstTaxType()
							.equalsIgnoreCase("Without Tax")) {
						billDetailDTO.setTax(0.00);
					}

				}
			}
			invoiceForm.setTaxTypeToshow(null);
			mav.setViewName("proforma_add");
			mav.addObject("invoice", invoiceForm);
			return mav;
		}

		/**
		 * @author Abhinav This code write for change the calculation of
		 *         Proforma based on Excise Type selection
		 */
		if (("Excise").equals(invoiceForm.getTaxTypeToshow())) {

			/*
			 * ModelAndView mv = new ModelAndView(new RedirectView(
			 * "show_proforma?operation=show")); return mv;
			 */
			// System.out.println("invoiceForm.getTaxTypeToshow()"+invoiceForm.getProformaMasterDTO().getExciseType());
			invoiceForm = (InvoiceForm) session.getAttribute("invoice");

			List pdl = invoiceForm.getProformaDetailList();
			if (pdl != null && pdl.size() > 0) {
				for (int i = 0; i < pdl.size(); i++) {
					ProformaDetailDTO billDetailDTO = (ProformaDetailDTO) pdl
							.get(i);
					Integer itemId = billDetailDTO.getItemId();
					ItemDTO dto = new ItemDTO();
					dto.setItemId(itemId);
					ItemInputMessage itemInputMessage = new ItemInputMessage();
					itemInputMessage.setItemDTO(dto);
					ItemOutMessage itemOutMessage = itemService
							.findItemById(itemInputMessage);
					ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) itemOutMessage
							.getItemDTOList();
					dto = (ItemDTO) list.get(0);
					if (invoiceForm.getProformaMasterDTO().getExciseType()
							.equalsIgnoreCase(ProformaMasterDTO.EXCISABLE)) {
						billDetailDTO.setExcisePerc(dto.getExcisePerc());
					}
					if (invoiceForm.getProformaMasterDTO().getExciseType()
							.equalsIgnoreCase(ProformaMasterDTO.EXEMPTED)) {
						billDetailDTO.setExcisePerc(0.0);
						billDetailDTO.setExciseAmount(0.0);
					}
				}
			}
			invoiceForm.setTaxTypeToshow(null);
			mav.setViewName("proforma_add");
			mav.addObject("invoice", invoiceForm);
			return mav;
		}

		if (operation.equals("Batch No")) {
			invoiceForm = (InvoiceForm) session.getAttribute("invoice");
			ProformaDetailDTO billDetailDTO = null;

			try {
				billDetailDTO = invoiceForm.getProformaDetailList().get(
						invoiceForm.getIndexNo());
			} catch (Exception e) {

			}
			if (invoiceForm.getIndexNo() == null) {
				// mav = new ModelAndView();

				error.setErrorMsg("Please select item");
				mav.addObject("error", error);
				mav.setViewName("proforma_add");
				mav.addObject("invoice", invoiceForm);
				return mav;
			}

			// To check Item Baatch Flage
			Integer itemId = billDetailDTO.getItemId();

			ItemDTO itemDTO = null;
			ItemOutMessage itemOutMessage = null;
			if (itemId != null) {

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

				// Check Item In Batch
				BatchInputMessage batchInputMessage = new BatchInputMessage();
				BatchDTO batchDTO = new BatchDTO();
				batchInputMessage.setItemId(itemId);

				BatchOutMessage batchOutMessage = batchService
						.findAllBatchByItemId(batchInputMessage);
				ArrayList<BatchDTO> batchList = (ArrayList<BatchDTO>) batchOutMessage
						.getBatchDTOList();

				if (batchList != null && batchList.size() > 0) {
					batchDTO = batchList.get(0);
				}

				if (batchDTO.getItemDTO().getItemId() != null) {

					ModelAndView mv = new ModelAndView(new RedirectView(
							"submitInvoiceItemBatchList?operation=show&itemId="
									+ billDetailDTO.getItemId() + "&indexNo="
									+ invoiceForm.getIndexNo().intValue()));
					return mv;

				} else {
					mav = new ModelAndView();
					error = new ErrorDTO();
					error.setErrorMsg("Sorry you can't select invoice for this item");
					mav.addObject("error", error);
					invoiceForm = (InvoiceForm) session.getAttribute("invoice");
					mav.setViewName("proforma_add");
					mav.addObject("invoice", invoiceForm);
					return mav;
				}
			}

			ModelAndView mv = new ModelAndView(new RedirectView(
					"submitInvoiceItemBatchList?operation=show&itemId="
							+ billDetailDTO.getItemId() + "&indexNo="
							+ invoiceForm.getIndexNo().intValue()));
			return mv;
		}

		if (operation.equals("remove")) {

			invoiceForm = (InvoiceForm) session.getAttribute("invoice");
			ProformaDetailDTO billDetailDTO = invoiceForm
					.getProformaDetailList().get(
							invoiceForm.getIndexNo().intValue());
			invoiceForm.getProformaDetailList().remove(
					invoiceForm.getIndexNo().intValue());

			ProformaDetailInputMessage billDetailInputMessage = new ProformaDetailInputMessage();
			billDetailInputMessage.setProformaDetailDTO(billDetailDTO);

			if (billDetailDTO.getSno() != null && billDetailDTO.getSno() > 0) {
				// proformaDetailService.deleteBillDetail(billDetailInputMessage);
			}

			session.setAttribute("invoice", invoiceForm);
			mav.addObject("invoice", invoiceForm);
			mav.setViewName("proforma_add");
			return mav;
		}
		if (operation.equalsIgnoreCase("edite")) {
			ModelAndView mv = new ModelAndView(new RedirectView(
					"showItemList?operation=show"));
			return mv;
		}
		if (operation.equalsIgnoreCase("Item Name")) {
			ProformaMasterDTO m = invoiceForm.getProformaMasterDTO();
			try {
				invoiceForm.setTimeToshow(m.getTimeOfRemoval().toString());
			} catch (Exception e) {
			}
			if (invoiceForm.getProformaDetailList().size() > 0
					&& invoiceForm.getProformaDetailList().size() > 29) {
				// mav = new ModelAndView();

				error.setErrorMsg("You cannot add more than 30 Items");
				mav.addObject("error", error);
				mav.setViewName("proforma_add");
				mav.addObject("invoice", invoiceForm);
				return mav;
			}

			invoiceForm.setIndexNo(null);
			ModelAndView mv = new ModelAndView(new RedirectView(
					"showItemList?operation=show"));

			return mv;
			// return itemListController.display(new ItemForm(),
			// operation,session);
		}
		if (operation.equalsIgnoreCase("Sales Order No")) {

			ModelAndView mv = new ModelAndView(new RedirectView(
					"showSalesOrderList?operation=show"));
			return mv;
		}
		if (operation.equalsIgnoreCase("Cancel")) {

			ModelAndView mv = new ModelAndView(new RedirectView(
					"show_proforma_list"));
			return mv;
		}

		if (operation.equalsIgnoreCase("Save")) {

			if (invoiceForm.getProformaDetailList().size() > 0
					&& invoiceForm.getProformaDetailList() != null) {

				ProformaMasterDTO billDTO = new ProformaMasterDTO();
				// Save Bill Master
				billDTO = new ProformaMasterDTO();
				ProformaMasterInputMessage billInputMessage = new ProformaMasterInputMessage();
				billDTO = invoiceForm.getProformaMasterDTO();

				Date date = billDTO.getInvoiceDate();
				if (date != null) {
					date = DataUtility.getDate(date);
					billDTO.setInvoiceDate(date);
				}
				billDTO.setTransactionSeries(series);
				billDTO.setBranchId(billDTO.getBranchDTO().getBranchId());
				ConvertNumberIntoWord convertNumberIntoWord = new ConvertNumberIntoWord();
				// Bill Net Amount into word
				if (billDTO.getBillNetAmount() != null
						&& billDTO.getBillNetAmount() != 0.0) {
					billDTO.setBillNetAmountInwords(convertNumberIntoWord
							.convert(billDTO.getBillNetAmount().intValue()));
				}

				// Excise Duty Amount into word
				if (billDTO.getExciseDutyAmount() != null
						&& billDTO.getExciseDutyAmount() != 0.0) {
					billDTO.setExciseDutyAmountInwords(convertDoubleToWord(billDTO
							.getExciseDutyAmount()));
					// billDTO.setExciseDutyAmountInwords(convertNumberIntoWord.convert(billDTO.getExciseDutyAmount().intValue()));
				}

				// To Convert Cess Amoutn In word
				Double cessAmountInwords = (double) Math.round(billDTO
						.getEducationCessAmount());
				Double hcessAmountInwords = (double) Math.round(billDTO
						.getHighEducationCessAmount());

				if (cessAmountInwords != null && cessAmountInwords != 0.0) {
					billDTO.setCessAmountInwords(convertDoubleToWord(billDTO
							.getEducationCessAmount()));
					// billDTO.setCessAmountInwords(convertNumberIntoWord.convert(cessAmountInwords.intValue()));
				}

				if (hcessAmountInwords != null && hcessAmountInwords != 0.0) {
					billDTO.setHighEducationCessAmountInwords(convertDoubleToWord(billDTO
							.getHighEducationCessAmount()));
					// billDTO.setHighEducationCessAmountInwords(convertNumberIntoWord.convert(hcessAmountInwords.intValue()));
				}
				if (invoiceForm.getTaxType().equalsIgnoreCase("VAT")) {
					billDTO.setVatAmount(billDTO.getVatAmount());
					billDTO.setCstAmount(0.00);
				}
				if (invoiceForm.getTaxType().equalsIgnoreCase("CST")) {
					billDTO.setCstAmount(billDTO.getVatAmount());
					billDTO.setVatAmount(0.00);
				}

				billInputMessage.setProformaMasterDTO(billDTO);
				// To check Proforma is converted or not into invoice
				ProformaMasterOutMessage bom = proformaMasterService
						.findBillById(billInputMessage);
				List<ProformaMasterDTO> ml = bom.getProformaMasterDTOList();
				ProformaMasterDTO md = null;
				try {
					md = ml.get(0);
					if (md.getExciseInvoiceNo() != null) {
						ModelAndView mv = new ModelAndView(new RedirectView(
								"show_proforma_list?operation=converted"));
						return mv;
					}
				} catch (Exception e) {
				}
				// end
				try {
					if (billDTO.getInvoiceAutoId() != null
							&& billDTO.getInvoiceAutoId() > 0) {
						error.setErrorMsg("Invoice update successfully");
						billDTO.setModifiedUserId(getCreatedUserId());
						proformaMasterService.updateBill(billInputMessage);
						operation = "Update";

					} else {
						billDTO.setCreatedUserId(getCreatedUserId());
						error.setErrorMsg("Invoice save successfully");
						ProformaMasterOutMessage billOutMessage = proformaMasterService
								.createBill(billInputMessage);

						ErrorListDTO erorList = (ErrorListDTO) billOutMessage
								.getErrorListDTO();
						ErrorDTO errorDTO = erorList.getErrorList().get(0);

						if (errorDTO != null) {
							invoiceForm = (InvoiceForm) session
									.getAttribute("invoice");
							// mav = new ModelAndView();
							billDTO = invoiceForm.getProformaMasterDTO();
							String invoiveNo = errorDTO.getErrorCode();
							String[] str = invoiveNo.split("/");

							String inSeries = str[0];
							String infnyear = str[1];
							String inAutoId = str[2];

							int inAuto = Integer.parseInt(inAutoId);
							inAuto = inAuto + 1;
							billDTO.setTransactionSeries(series + "/"
									+ infnyear);
							billDTO.setFinyr(finecialYear);
							billDTO.setInvoiceNumber(series + "/" + infnyear
									+ "/" + inAuto);
							billDTO.setInvoiceId(inAuto);

							invoiceForm.setProformaMasterDTO(billDTO);

							mav.setViewName("proforma_add");
							mav.addObject("error", errorDTO);
							mav.addObject("invoice", invoiceForm);
							return mav;
						}
					}
				} catch (Exception ex) {
				}

				// Save Bill Details
				ProformaDetailInputMessage billDetailInputMessage = new ProformaDetailInputMessage();
				StockLedgerInputMessage stockInputmessage = new StockLedgerInputMessage();
				StockLedgerDTO ledgerDTO = null;

				ProformaMasterDTO billDTOFroInvoiceAutoId = new ProformaMasterDTO();
				List<ProformaDetailDTO> list = invoiceForm
						.getProformaDetailList();
				// START UPDATE PROFORMA DETAIL
				if (billDTO.getInvoiceAutoId() != null
						&& billDTO.getInvoiceAutoId() > 0) {
					ProformaDetailInputMessage detailInputMessage = new ProformaDetailInputMessage();
					ProformaDetailDTO detailDTO = new ProformaDetailDTO();
					detailDTO.setInvoiceNumber(billDTO.getInvoiceNumber());
					detailInputMessage.setProformaDetailDTO(detailDTO);
					ProformaDetailOutMessage detailOutMessage = proformaDetailService
							.findBillByBillId(detailInputMessage);
					List<ProformaDetailDTO> dl = detailOutMessage
							.getProformaDetailDTOList();
					for (int i = 0; i < dl.size(); i++) {
						ProformaDetailDTO detailDTO2 = dl.get(i);
						for (int j = 0; j < list.size(); j++) {
							ProformaDetailDTO detailDTO3 = (ProformaDetailDTO) list
									.get(j);
							if (detailDTO2.getSno() != detailDTO3.getSno()) {
								detailInputMessage
										.setProformaDetailDTO(detailDTO2);
								proformaDetailService
										.deleteBillDetail(detailInputMessage);
							}
						}
					}
				}
				// END UPDATE PROFORMA DETAIL

				for (int i = 0; i < list.size(); i++) {
					ProformaDetailDTO billDetailDTO = (ProformaDetailDTO) list
							.get(i);
					billDetailDTO.setTransactionSeries(series);
					billDetailDTO.setInvoiceNumber(billDTO.getInvoiceNumber());

					/*
					 * if (billDTO.getSalesType().equals("Sales with In State"))
					 * { billDetailDTO.setVatPerc(billDetailDTO.getTax()); } if
					 * (billDTO.getSalesType().equals("Sales Outside State")) {
					 * billDetailDTO.setCstPerc(billDetailDTO.getTax()); }
					 */
					// System.out.println("billDetailDTO"+billDetailDTO);
					// set Primary Unit based on Secondary
					if (billDetailDTO.getBookedBy() != null
							&& billDetailDTO.getBookedBy() == 2
							&& billDetailDTO.getSecondaryConvUnit() != null) {
						// System.out.println("if");
						billDetailDTO.setPrimaryUnit(billDetailDTO
								.getQuantity()
								/ billDetailDTO.getSecondaryConvUnit());

					} else if (billDetailDTO.getBookedBy() != null
							&& billDetailDTO.getBookedBy() == 1
							&& billDetailDTO.getSecondaryConvUnit() != null) {
						// System.out.println("else if ");
						billDetailDTO.setPrimaryUnit(billDetailDTO
								.getQuantity());
					} else {
						// System.out.println("else");
						billDetailDTO.setPrimaryUnit(billDetailDTO
								.getQuantity());
					}

					if ("VAT".equalsIgnoreCase(billDTO.getVatCstTaxType())) {
						billDetailDTO.setVatPerc(billDetailDTO.getTax());
						billDetailDTO.setVatAmount(billDetailDTO
								.getTaxAmoForVatOrCst());

					}
					if ("CST with C Form".equalsIgnoreCase(billDTO
							.getVatCstTaxType())) {
						billDetailDTO.setCstPerc(billDetailDTO.getTax());
						billDetailDTO.setCstAmount(billDetailDTO
								.getTaxAmoForVatOrCst());
					}
					if ("CST w/o C Form".equalsIgnoreCase(billDTO
							.getVatCstTaxType())) {
						billDetailDTO.setVatPerc(billDetailDTO.getTax());
						billDetailDTO.setVatAmount(billDetailDTO
								.getTaxAmoForVatOrCst());
					}

					// set default UOM
					if (billDetailDTO.getPrimaryUOM() == null)
						billDetailDTO.setPrimaryUOM(billDetailDTO
								.getMeasurementUnitId());

					if (billDTO.getInvoiceAutoId() != null
							&& billDTO.getInvoiceAutoId() > 0) {
						billDetailDTO.setModifiedUserId(getCreatedUserId());
						billDetailDTO.setInvoiceAutoId(billDTO
								.getInvoiceAutoId());
						billDetailInputMessage
								.setProformaDetailDTO(billDetailDTO);
						proformaDetailService
								.updateBillDetail(billDetailInputMessage);
					} else {
						// To get Max Invoice Auto Id
						ProformaMasterOutMessage outMessage = proformaMasterService
								.getMaxInvoiceId();
						ArrayList<ProformaMasterDTO> bl = (ArrayList<ProformaMasterDTO>) outMessage
								.getProformaMasterDTOList();

						if (bl != null & bl.size() > 0) {
							billDTOFroInvoiceAutoId = bl.get(0);
						}
						billDetailDTO.setCreatedUserId(getCreatedUserId());
						billDetailDTO.setInvoiceAutoId(billDTOFroInvoiceAutoId
								.getInvoiceAutoId());
						billDetailInputMessage
								.setProformaDetailDTO(billDetailDTO);
						proformaDetailService
								.createBillDetail(billDetailInputMessage);

					}

					// Stock Ledger
					ledgerDTO = new StockLedgerDTO();

					if (billDTO.getBranchDTO().getBranchId() != null) {
						ledgerDTO.setBranchId(billDTO.getBranchDTO()
								.getBranchId());
					}

					ledgerDTO.setBatchNo(billDetailDTO.getBatchNo());
					ledgerDTO.setTransactionDate(billDTO.getInvoiceDate());
					ledgerDTO.setItemId(billDetailDTO.getItemId());
					ledgerDTO.setSalesRate(billDetailDTO.getSalesRate());
					/*
					 * set quantity according to primary Unit
					 * ledgerDTO.setQuantity(0 - billDetailDTO.getQuantity());
					 */

					ledgerDTO.setQuantity(0 - billDetailDTO.getPrimaryUnit());

					ledgerDTO.setSalesValue(billDetailDTO.getNetAmount());
					ledgerDTO.setTransactionSeries(series);
					ledgerDTO.setTransactionNumber(billDetailDTO
							.getInvoiceNumber());
					ledgerDTO.setTransactionId(billDTO.getInvoiceId());
					stockInputmessage.setStockLedgerDTO(ledgerDTO);

					if (billDTO.getInvoiceAutoId() != null
							&& billDTO.getInvoiceAutoId() > 0) {
						StockLedgerOutMessage ledgerOutMessage = stockLedgerService
								.findStockLedgerByTransactionId(stockInputmessage);
						List<StockLedgerDTO> stockLedgerList = ledgerOutMessage
								.getStockLedgerDTOList();
						StockLedgerDTO ledgerDTOForUpdate = new StockLedgerDTO();
						// for (int j = 0; j < stockLedgerList.size(); j++) {
						// ledgerDTOForUpdate = stockLedgerList.get(i);
						// ledgerDTO.setSno(ledgerDTOForUpdate.getSno());
						// stockInputmessage.setStockLedgerDTO(ledgerDTO);
						// stockLedgerService.updateStockLedger(stockInputmessage);

						// }

					} else {
						// stockLedgerService.createStockLedger(stockInputmessage);
					}
				}
				session.removeAttribute("invoice");
				ModelAndView mv = new ModelAndView(new RedirectView(
						"show_proforma_list?operation=" + operation
								+ "&invoiceNumberToPrint="
								+ invoiceForm.getInvoiceNumberToPrint()));

				mv.addObject("error", error);
				return mv;
			} else {
				error = new ErrorDTO();
				error.setErrorMsg("Sorry you can't save invoice without item");
				invoiceForm = (InvoiceForm) session.getAttribute("invoice");

				// mav = new ModelAndView();
				mav.setViewName("proforma_add");
				mav.addObject("error", error);
				mav.addObject("invoice", invoiceForm);
				return mav;
			}

		}

		mav = new ModelAndView();
		mav.setViewName("proforma_add");
		return mav;
	}

	protected ModelAndView preloadedData() {
		ModelAndView mav = new ModelAndView();
		// EmployeeOutputMessage employeeOutputMessage =
		// employeeService.findAllEmployee();
		EmployeeOutputMessage employeeOutputMessage = employeeService.preLoad();
		List<EmployeeDTO> employeeList = (ArrayList<EmployeeDTO>) employeeOutputMessage
				.getEmployeeDTOList();

		// PartyOutMessage partyOutMessage = partyService.findAllPartys();
		PartyOutMessage partyOutMessage = partyService.findDebtorPartyList();
		List<PartyDTO> partyList = (ArrayList<PartyDTO>) partyOutMessage
				.getPartyDTOList();
		// TransporterOutMessage transporterOutMessage =
		// transporterService.findAllTransporters();
		TransporterOutMessage transporterOutMessage = transporterService
				.preload();
		List<TransporterDTO> transporterList = (ArrayList<TransporterDTO>) transporterOutMessage
				.getTransporterDTOList();

		// BranchOutMessage branchOutMessage = branchService.findAllBranches();
		BranchOutMessage branchOutMessage = branchService.preloaded();
		List<BranchDTO> branchList = (ArrayList<BranchDTO>) branchOutMessage
				.getBranchDTOList();

		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		MastersDTO dto = new MastersDTO();
		dto.setFormId(12);
		mastersInputMessage.setMastersDTO(dto);

		mastersInputMessage.setFormId(12);
		MastersOutputMessage mastersOutputMessage = mastersetService
				.findFormById(mastersInputMessage);

		ArrayList<MastersDTO> masterFormList = (ArrayList<MastersDTO>) mastersOutputMessage
				.getMastersDTOList();

		// Item Group list
		ItemGroupFlagOutMessage itemGroupFlagOutMessage = itemGroupFlagService
				.findAllItemGroupFlag();
		ArrayList<ItemGroupFlagDTO> itemGroupFlagList = (ArrayList<ItemGroupFlagDTO>) itemGroupFlagOutMessage
				.getItemGroupFlagDTOList();
		// System.out.println("itemGroupFlagList size"+itemGroupFlagList.size());
		mav.addObject("itemGroupFlagList", itemGroupFlagList);

		mav.addObject("employeeList", employeeList);
		mav.addObject("partyList", partyList);
		mav.addObject("transporterList", transporterList);
		mav.addObject("masterFormList", masterFormList);
		mav.addObject("branchList", branchList);

		return mav;
	}

	@RequestMapping(value = "/getPartyInform", method = RequestMethod.POST)
	public @ResponseBody JsonResponse addUser(
			@ModelAttribute(value = "partyForm") PartyForm partyForm,
			BindingResult result, @RequestParam Integer partyId,
			HttpSession session) {
		JsonResponse res = new JsonResponse();

		PartyInputMessage partyInputMessage = new PartyInputMessage();
		PartyDTO partyDTO = new PartyDTO();
		partyDTO.setPartyId(partyId);
		partyInputMessage.setPartyDTO(partyDTO);

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

		CityOutputMessage cityOutputMessage = cityService
				.findCityById(cityInputMessage);
		ArrayList<CityDTO> cityList = (ArrayList<CityDTO>) cityOutputMessage
				.getCityDTOList();
		if (cityList != null) {
			cityDTO = cityList.get(0);
		}
		partyDTO.setState(cityDTO.getAreaDTO().getRegionDTO().getStateDTO()
				.getStateName());

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

	private String getInvoiceTransactionSeries() {
		TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
		transactionTypeDTO.setSno(13);
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

	private String convertDoubleToWord(Double value) {

		int intRuprr = (int) value.doubleValue(); // here's the first part
		double f = value - (double) intRuprr;
		f = Math.round(f * 100);
		int intPaise = (int) f; // here's the second part

		ConvertNumberIntoWord convertNumberIntoWord = new ConvertNumberIntoWord();
		String rupee = convertNumberIntoWord.convertWTOnly(intRuprr);
		String paise = convertNumberIntoWord.convertWTOnly(intPaise);

		String amount = null;
		if (intPaise > 0) {
			amount = rupee + " Rupees & " + paise + " Paise " + " Only/-";
		} else {
			amount = rupee + " Rupees Only/-";
		}
		return amount;

	}

	public static void main(String[] args) {

		Double value = 8887.676;

		int intRuprr = (int) value.doubleValue(); // here's the first part
		// System.out.println("Int :Rupees:" +intRuprr);
		double f = value - (double) intRuprr;
		f = Math.round(f * 100);
		int intPaise = (int) f; // here's the second part
		// System.out.println("Int :Paisa:"+intPaise);

		ConvertNumberIntoWord convertNumberIntoWord = new ConvertNumberIntoWord();
		String rupee = convertNumberIntoWord.convertWTOnly(intRuprr);
		String paise = convertNumberIntoWord.convertWTOnly(intPaise);

		String amount = null;
		if (intPaise > 0) {
			amount = rupee + " Rupees & " + paise + " Paise " + " Only/-";
		} else {
			amount = rupee + " Rupees Only/-";
		}
		// System.out.println("Converted amount in word :" + amount);

	}
}
