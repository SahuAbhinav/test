package com.advanz.erp.client.http.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

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
import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.BillDTO;
import com.advanz.erp.masters.model.BillDetailDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.CityDTO;
import com.advanz.erp.masters.model.CompanyDTO;
import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.ExciseLedgerDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.ProformaDetailDTO;
import com.advanz.erp.masters.model.ProformaMasterDTO;
import com.advanz.erp.masters.model.SalesOrderDetailDTO;
import com.advanz.erp.masters.model.SalesOrderMasterDTO;
import com.advanz.erp.masters.model.StockLedgerDTO;
import com.advanz.erp.masters.model.TransactionTypeDTO;
import com.advanz.erp.masters.model.TransporterDTO;
import com.advanz.erp.masters.model.msg.BatchInputMessage;
import com.advanz.erp.masters.model.msg.BatchOutMessage;
import com.advanz.erp.masters.model.msg.BillDetailInputMessage;
import com.advanz.erp.masters.model.msg.BillDetailOutMessage;
import com.advanz.erp.masters.model.msg.BillInputMessage;
import com.advanz.erp.masters.model.msg.BillOutMessage;
import com.advanz.erp.masters.model.msg.BranchInputMessage;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.model.msg.CityInputMessage;
import com.advanz.erp.masters.model.msg.CityOutputMessage;
import com.advanz.erp.masters.model.msg.CompanyOutMessage;
import com.advanz.erp.masters.model.msg.EmployeeOutputMessage;
import com.advanz.erp.masters.model.msg.ExciseLedgerInputMessage;
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
import com.advanz.erp.masters.model.msg.SalesOrderMasterInputMessage;
import com.advanz.erp.masters.model.msg.SalesOrderMasterOutputMessage;
import com.advanz.erp.masters.model.msg.StockLedgerInputMessage;
import com.advanz.erp.masters.model.msg.StockLedgerOutMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeInputMessage;
import com.advanz.erp.masters.model.msg.TransactionTypeOutputMessage;
import com.advanz.erp.masters.model.msg.TransporterOutMessage;
import com.advanz.erp.masters.service.business.IBatchService;
import com.advanz.erp.masters.service.business.IBillDetailService;
import com.advanz.erp.masters.service.business.IBillService;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.service.business.ICityService;
import com.advanz.erp.masters.service.business.ICompanyService;
import com.advanz.erp.masters.service.business.IEmployeeService;
import com.advanz.erp.masters.service.business.IExciseLedgerService;
import com.advanz.erp.masters.service.business.IItemGroupFlagService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IPartyService;
import com.advanz.erp.masters.service.business.IProformaDetailService;
import com.advanz.erp.masters.service.business.IProformaMasterService;
import com.advanz.erp.masters.service.business.ISalesOrderMasterService;
import com.advanz.erp.masters.service.business.IStockLedgerService;
import com.advanz.erp.masters.service.business.ITransactionTypeService;
import com.advanz.erp.masters.service.business.ITransporterService;

@Controller
public class InvoiceController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);
	@Autowired
	DataSource dataSource;
	@Autowired
	public IEmployeeService employeeService;
	@Autowired
	public IPartyService partyService;

	@Autowired
	public ITransporterService transporterService;
	@Autowired
	public IItemService itemService;
	@Autowired
	public IBillService billService;

	@Autowired
	public IBillDetailService billDetailService;

	@Autowired
	public IStockLedgerService stockLedgerService;
	
	@Autowired
	public IExciseLedgerService exciseLedgerService;

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
	public IProformaMasterService proformaMasterService;

	@Autowired
	public IProformaDetailService proformaDetailService;
	
	@Autowired
	public ISalesOrderMasterService iSalesOrderMasterService;
	
	@Autowired
	IItemGroupFlagService itemGroupFlagService;
	
	@Autowired
	ICompanyService companyService; 
	
	@RequestMapping(value = "/show_invoice", method = RequestMethod.GET)
	public ModelAndView display(@ModelAttribute("invoice") InvoiceForm invoiceForm,@RequestParam String operation, HttpSession session) {

		ModelAndView mav = preloadedData();

		ErrorDTO error = new ErrorDTO();
		if("Delete".equals(operation)){
		      error.setErrorCode("Invoice  Remove Confirmation");
		      error.setOperationName(operation);
		}/*if("Get_Invoice".equals(operation)){
		      error.setErrorCode("edite invoice confermation");
		}*/
		
		
		
		
		String finecialYear= getFinYear();
		String series= getInvoiceTransactionSeries();
		if ("Get_Invoice".equals(operation) || "Delete".equals(operation)) {
			
			
			/*if("Delete".equals(operation)){
				
			 ProformaMasterInputMessage proformaMasterInputMessage = new ProformaMasterInputMessage();
			 ProformaMasterDTO proformaMasterDTO = new ProformaMasterDTO();
			 proformaMasterDTO.setExciseInvoiceNo(invoiceForm.getInvoiceNumber());
			 proformaMasterInputMessage.setProformaMasterDTO(proformaMasterDTO);
			 ProformaMasterOutMessage proformaMasterOutMessage = proformaMasterService.findBillByInvoiceNo(proformaMasterInputMessage);
		     ArrayList<ProformaMasterDTO> masterList=(ArrayList<ProformaMasterDTO>) proformaMasterOutMessage.getProformaMasterDTOList();
			 
		     if(masterList!=null && masterList.size()>0){
				 proformaMasterDTO = new ProformaMasterDTO();
				 proformaMasterDTO= masterList.get(0);
				 
			 }
		     
			    try {
					proformaMasterDTO.setExciseInvoiceNo(null);
					proformaMasterInputMessage.setProformaMasterDTO(proformaMasterDTO);
					proformaMasterService.updateBill(proformaMasterInputMessage);
				} catch (Exception e) {
					
				}
			}*/
			
			Integer invoiceAutoId = invoiceForm.getInvoiceAutoId();
			BillInputMessage billInputMessage = new BillInputMessage();
			BillDTO billDTO = new BillDTO();
			billDTO.setInvoiceAutoId(invoiceAutoId);
			billInputMessage.setBillDTO(billDTO);
			BillOutMessage billOutMessage = billService.findBillById(billInputMessage);
			List billList = billOutMessage.getBillDTOList();
			if (billList != null && billList.size() > 0) {
				billDTO = (BillDTO) billList.get(0);
				try{
					invoiceForm.setTimeToshow(billDTO.getTimeOfRemoval().toString());
					}catch(Exception e){}
				billDTO.setTransactionSeries(series+"-"+billDTO.getFinyr());
			}

			String invoiceNumber = billDTO.getInvoiceNumber();

			BillDetailDTO billDetailDTO = new BillDetailDTO();
			BillDetailInputMessage billDetailInputMessage = new BillDetailInputMessage();
			billDetailDTO.setInvoiceNumber(billDTO.getInvoiceNumber());
			billDetailInputMessage.setBillDetailDTO(billDetailDTO);
			BillDetailOutMessage billDetailOutMessage = billDetailService.findBillByBillId(billDetailInputMessage);

			List billDtailList = billDetailOutMessage.getBillDetailDTOList();

			// To get Item Name
			for (int i = 0; i < billDtailList.size(); i++) {
				billDetailDTO = (BillDetailDTO) billDtailList.get(i);
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
					if ("vat".equalsIgnoreCase(billDTO.getVatCstTaxType())) {
					    billDetailDTO.setTax(billDetailDTO.getVatPerc());
					}if ("cst with c form".equalsIgnoreCase(billDTO.getVatCstTaxType())) {
						billDetailDTO.setTax(billDetailDTO.getCstPerc());
					}if ("cst w/o c form".equalsIgnoreCase(billDTO.getVatCstTaxType())) {
						billDetailDTO.setTax(billDetailDTO.getVatPerc());
					}
					} catch (Exception e) {
					}

				billDetailDTO.setDiscountAmountPerToShow(billDetailDTO.getDiscountPer());
				// Get MFG Date From Batch
				BatchInputMessage batchInputMessage = new BatchInputMessage();
				BatchDTO batchDTO = new BatchDTO();
				batchInputMessage.setItemId(itemDTO.getItemId());
				BatchOutMessage batchOutMessage = batchService.findAllBatchByItemId(batchInputMessage);
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
				MastersOutputMessage mastersOutputMessage = mastersetService.findMastersById(mastersInputMessage);
				ArrayList<MastersDTO> masterList = (ArrayList<MastersDTO>) mastersOutputMessage.getMastersDTOList();
				if (masterList != null && masterList.size() > 0) {
					mastersDTO = masterList.get(0);
				}
				billDetailDTO.setUmoName(mastersDTO.getName());
			    }

			invoiceForm.setBillDetailList(billDtailList);

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
				invoiceForm.setBillDTO(billDTO);

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
		BillDTO dto = null;
		if (session.getAttribute("invoice") == null) {
			if (operation == null || operation.trim().length() == 0) {
				if (invoiceForm.getId() > 0) {
					BillInputMessage message = new BillInputMessage();
					dto.setInvoiceAutoId((int) invoiceForm.getId());
					message.setBillDTO(dto);
					BillOutMessage output = billService.findBillById(message);
					dto = output.getBillDTO();
					dto.setInvoiceId(dto.getInvoiceAutoId());
					invoiceForm.setBillDTO(dto);
					session.setAttribute("invoice", invoiceForm);
				}
			}
			
			
			
			
			BillInputMessage inputMessage = new BillInputMessage();
			BillDTO billDTO = new BillDTO();
			billDTO.setFinyr(getFinYear());
			billDTO.setTransactionSeries(series + "/"	+ getFinYear());
			inputMessage.setBillDTO(billDTO);
			BillOutMessage billOutMessage = billService.getNewBillMasterSeriesNo(inputMessage);
		    Integer invoiceId=	billOutMessage.getBillSeries();
		
			BillDTO billDTO2 = new BillDTO();
			billDTO2.setInvoiceNumber(series+"/"+finecialYear+"/" + invoiceId);
			billDTO2.setInvoiceId(invoiceId);
			billDTO2.setTransactionSeries(series+"/"+finecialYear);
			billDTO2.setFinyr(finecialYear);
			billDTO2.setInvoiceDate(new Date());
			
			
			
			invoiceForm.setBillDTO(billDTO2);
		} else {
			// Get Party Information From Sales Order Detail
			invoiceForm = (InvoiceForm) session.getAttribute("invoice");
			if (invoiceForm.getPartyId() != null) {
				BillDTO billDTO = invoiceForm.getBillDTO();

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
				billDTO.getPartyDTO().setPartyId(invoiceForm.getPartyId());
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

				invoiceForm.setBillDTO(billDTO);
			}
			// invoiceForm = (InvoiceForm) session.getAttribute("invoice");

		}
		mav.addObject("error", error);
		mav.setViewName("invoice_add");
		mav.addObject("invoice", invoiceForm);
		return mav;
	}


	
	
	@RequestMapping(value = "/save_invoice", method = RequestMethod.POST)
	public ModelAndView submit(
			@ModelAttribute("invoice") InvoiceForm invoiceForm,
			@RequestParam String operation, HttpSession session,HttpServletResponse response, HttpServletRequest request) {
		String finecialYear= getFinYear();
		String series= getInvoiceTransactionSeries();
		ModelAndView mav = preloadedData();
		session.setAttribute("invoice", invoiceForm);
		ErrorDTO error = new ErrorDTO();
	
		if (operation.equalsIgnoreCase("Save")) {
	//To check duplicate invoice number
			BillDTO bd= invoiceForm.getBillDTO();
			
			BillInputMessage bin=new BillInputMessage();
			bd.setFinyr(getFinYear());
			bin.setBillDTO(bd);
			BillOutMessage bom = billService.getNewBillMasterSeriesNo(bin);
		    Integer invoiceId=	bom.getBillSeries();
			
			bd.setInvoiceNumber(invoiceForm.getBillDTO().getInvoiceNumber());
			
			bin.setBillDTO(bd);
		     bom=billService.checkDuplicateInvoiceNum(bin);
		    List<BillDTO> invoiceList=  bom.getBillDTOList();
			if(invoiceList!=null && invoiceList.size()>0){
				String invoiceSeries=bd.getTransactionSeries();
				String fnyr=bd.getFinyr();
				bd.setInvoiceId(invoiceId);
				bd.setInvoiceNumber(invoiceSeries+"/"+invoiceId);
				invoiceForm.setBillDTO(bd);
				error.setErrorMsg("Invoice number already exist it can not be duplicate");
				error.setOperationName("ConvertToInvoice");
				mav.addObject("error", error);
				mav.setViewName("invoice_add");
				mav.addObject("invoice", invoiceForm);
				return mav;	
			}
			//End
			
			
			
			boolean flag=false;
			//To check Duplicate Proforma Number
			 flag= billService.checkDuplicateProformaNum(invoiceForm.getBillDTO().getProformaNumber());
			if(flag==true){
			error = new ErrorDTO();
			operation="Duplicate Proforma In Bill";
			session.removeAttribute("invoice");
			ModelAndView mv = new ModelAndView(new RedirectView("show_proforma_list?operation="+operation+"&invoiceNumberToPrint="+invoiceForm.getInvoiceNumberToPrint()));
			mv.addObject("error", error);
			return mv;
		    }
			//
			if (invoiceForm.getBillDetailList().size() > 0 && invoiceForm.getBillDetailList() != null) {
				BillDTO billDTO = new BillDTO();
				// Save Bill Master
				billDTO = new BillDTO();
				BillInputMessage billInputMessage = new BillInputMessage();
				billDTO = invoiceForm.getBillDTO();
				Date date=	billDTO.getInvoiceDate();
				date=DataUtility.getDate(date);
				billDTO.setInvoiceDate(date);
				billDTO.setTransactionSeries(series);
				billDTO.setFinyr(getFinYear());
				
				ConvertNumberIntoWord convertNumberIntoWord=new ConvertNumberIntoWord();	
				//Bill Net Amount into word
				if(billDTO.getBillNetAmount()!=null && billDTO.getBillNetAmount()!=0.0){
				billDTO.setBillNetAmountInwords(convertNumberIntoWord.convert(billDTO.getBillNetAmount().intValue()));
				}
				
				
				//Excise Duty Amount into word
				if(billDTO.getExciseDutyAmount()!=null && billDTO.getExciseDutyAmount()!=0.0){
					billDTO.setExciseDutyAmountInwords(convertDoubleToWord(billDTO.getExciseDutyAmount()));
					//billDTO.setExciseDutyAmountInwords(convertNumberIntoWord.convert(billDTO.getExciseDutyAmount().intValue()));
				}
				
				
				//To Convert Cess Amoutn In word
				Double cessAmountInwords=(double)Math.round(billDTO.getEducationCessAmount());
				Double hcessAmountInwords=(double)Math.round(billDTO.getHighEducationCessAmount());
				
				if(cessAmountInwords!=null && cessAmountInwords!=0.0){
					billDTO.setCessAmountInwords(convertDoubleToWord(billDTO.getEducationCessAmount()));
				//billDTO.setCessAmountInwords(convertNumberIntoWord.convert(cessAmountInwords.intValue()));
				}
				
				if(hcessAmountInwords!=null && hcessAmountInwords!=0.0){
					 billDTO.setHighEducationCessAmountInwords(convertDoubleToWord(billDTO.getHighEducationCessAmount()));
					//billDTO.setHighEducationCessAmountInwords(convertNumberIntoWord.convert(hcessAmountInwords.intValue()));
				}
				
				
				if (invoiceForm.getTaxType().equalsIgnoreCase("VAT")) {
					billDTO.setVatAmount(billDTO.getVatAmount());
					billDTO.setCstAmount(0.00);
				}if (invoiceForm.getTaxType().equalsIgnoreCase("CST")) {
					billDTO.setCstAmount(billDTO.getVatAmount());
					billDTO.setVatAmount(0.00);
				}
				billInputMessage.setBillDTO(billDTO);
				
			  Integer invoiceAutoId= (Integer)session.getAttribute("invoiceAutoId");
			                                  session.removeAttribute("invoiceAutoId");
			                                  billDTO.setCreatedUserId(getCreatedUserId());
				 error.setErrorMsg("Invoice save successfully");
				 BillOutMessage billOutMessage=billService.createBill(billInputMessage);
				
				
				 
				//To update supply quantity in sales order when invoice is create
				 ProformaMasterDTO proformaMasterDTO = new ProformaMasterDTO();
				 proformaMasterDTO.setInvoiceAutoId(invoiceAutoId);
				
				 ProformaMasterInputMessage proformaMasterInputMessage = new ProformaMasterInputMessage();
				 proformaMasterInputMessage.setProformaMasterDTO(proformaMasterDTO);
				 
				    ProformaMasterOutMessage proformaMasterOutMessage=	proformaMasterService.findBillById(proformaMasterInputMessage);
					ArrayList<ProformaMasterDTO> billList=(ArrayList<ProformaMasterDTO>) proformaMasterOutMessage.getProformaMasterDTOList();	
					if(billList!=null && billList.size()>0){
						proformaMasterDTO=billList.get(0);
					}
			

			if(proformaMasterDTO.getSalesOrderNumber()!=null && proformaMasterDTO.getSalesOrderDate()!=null)
			{
			try
				 {
					//billDTO = (ProformaMasterDTO) billList.get(0);
					ProformaDetailDTO billDetailDTO = new ProformaDetailDTO();
					ProformaDetailInputMessage billDetailInputMessage = new ProformaDetailInputMessage();
					billDetailDTO.setInvoiceNumber(proformaMasterDTO.getInvoiceNumber());
					billDetailInputMessage.setProformaDetailDTO(billDetailDTO);
					ProformaDetailOutMessage billDetailOutMessage = proformaDetailService.findBillByBillId(billDetailInputMessage);
					List<ProformaDetailDTO> proformaDetailDTOList=billDetailOutMessage.getProformaDetailDTOList();
					
					SalesOrderMasterInputMessage salesOrderMasterInputMessage=new SalesOrderMasterInputMessage();
					SalesOrderMasterDTO salesOrderMasterDTO=new SalesOrderMasterDTO();
					String salesOrderNo=proformaMasterOutMessage.getProformaMasterDTOList().get(0).getSalesOrderNumber();
					salesOrderMasterDTO.setSalesOrderNumber(salesOrderNo);
					salesOrderMasterInputMessage.setSalesOrderMasterDTO(salesOrderMasterDTO);
					SalesOrderMasterOutputMessage salesOrderMasterOutputMessage=iSalesOrderMasterService.findBySalesOrderNo(salesOrderMasterInputMessage);
					salesOrderMasterDTO=new SalesOrderMasterDTO();
					salesOrderMasterDTO=salesOrderMasterOutputMessage.getSalesOrderMasterDTOList().get(0);
					
					List<SalesOrderDetailDTO> salesOrderDetailDTOList=salesOrderMasterDTO.getSalesOrderDetailDTOList();
					 boolean itemFlag=false;
					try
					{
					for(int s = 0; s <salesOrderDetailDTOList.size(); s++)
					{
						itemFlag=true;
						SalesOrderDetailDTO detailDTO=salesOrderDetailDTOList.get(s);
						//System.out.println("SalesOrderDetailDTO"+detailDTO);
					 for(int p = 0; p <proformaDetailDTOList.size(); p++)
					 {	
						 //System.out.println("proformaDetailDTO"+proformaDetailDTOList.get(p));
					  if((proformaDetailDTOList.get(p).getItemId().intValue() == salesOrderDetailDTOList.get(s).getItemId().intValue()) &&
							  detailDTO.getBookedBy().intValue()==proformaDetailDTOList.get(p).getBookedBy().intValue() &&
							  itemFlag
							  && (proformaDetailDTOList.get(p).isChecked()==false)
							  && proformaDetailDTOList.get(p).getSalesOrderItem().intValue()==1)
					   {
						double soDbPendingQty=salesOrderDetailDTOList.get(s).getPendingQty().doubleValue();
						double soDbPrimaryPendingQty=salesOrderDetailDTOList.get(s).getPrimaryPendingQty().doubleValue();
						double soDbSupplyQty=0.0;
						double soDbPrimarySupplyQty=0.0;
						if(salesOrderDetailDTOList.get(s).getSupplyQty()==null)
						{
						   soDbSupplyQty=0.0;
						}
						else
						{
							soDbSupplyQty=salesOrderDetailDTOList.get(s).getSupplyQty().doubleValue();
						}
						if(salesOrderDetailDTOList.get(s).getPrimarySupplyQty()==null)
						{
							soDbPrimarySupplyQty=0.0;
						}
						else
						{
							soDbPrimarySupplyQty=salesOrderDetailDTOList.get(s).getPrimarySupplyQty().doubleValue();
						}
						
						if(salesOrderDetailDTOList.get(s).getInvoiceNumber()!=null)
						 {
						  String invoiceNo=salesOrderDetailDTOList.get(s).getInvoiceNumber()+","+billDTO.getInvoiceId();
						  detailDTO.setInvoiceNumber(invoiceNo);
						 }
						else
						{
						 detailDTO.setInvoiceNumber(""+billDTO.getInvoiceId());
					    }
						
						double supplyQty=proformaDetailDTOList.get(p).getQuantity().doubleValue();
						double primarySupplyQty=proformaDetailDTOList.get(p).getPrimaryUnit().doubleValue();
						//double supplyPrimQty=proformaDetailDTOList.get(s).getPrimaryUnit().doubleValue();
						double balanceQty=soDbPendingQty-supplyQty;
						double primarybalanceQty=soDbPrimaryPendingQty-primarySupplyQty;
					    double suppQty=supplyQty+soDbSupplyQty;
					    detailDTO.setSupplyQty(suppQty);
					    
					    /*set Primary Supply quantity in SO */
					    double suppPrimQty=primarySupplyQty+soDbPrimarySupplyQty;
					    /*System.out.println("supplyQty"+supplyQty+"primarySupplyQty"+primarySupplyQty+
					    		"balanceQty"+balanceQty+"soDbPrimaryPendingQty"+soDbPrimaryPendingQty
					    		+"suppPrimQty"+suppPrimQty);*/
					    detailDTO.setPrimarySupplyQty(suppPrimQty);
					 	
					  //Set old pending Qty
					    detailDTO.setPendingQty(balanceQty);
					    
					    //Set Primary pending Qty
					    detailDTO.setPrimaryPendingQty(primarybalanceQty);
					    if(balanceQty<=0){
					    	detailDTO.setActiveStatus(0);
					    	detailDTO.setFullFillByInvoice(1);
					      }
					    proformaDetailDTOList.get(p).setChecked(true);
					    itemFlag=false;
					   }
					  
					 
					  }
					
			if(detailDTO.getCstPerc()>0){
				detailDTO.setTaxPerc(detailDTO.getCstPerc());
			}
			if(detailDTO.getVatPerc()>0){
				detailDTO.setTaxPerc(detailDTO.getVatPerc());
			}
			if(detailDTO.getVatAmount()>0){
				detailDTO.setTaxAmount(detailDTO.getVatAmount());
			}if(detailDTO.getCstAmount()>0){
				detailDTO.setTaxAmount(detailDTO.getCstAmount());
			}
				
 salesOrderDetailDTOList.set(s, detailDTO);
					}
					}catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
					
					salesOrderMasterDTO.setSalesOrderDetailDTOList(salesOrderDetailDTOList);
					salesOrderMasterInputMessage=new SalesOrderMasterInputMessage();
					salesOrderMasterInputMessage.setSalesOrderMasterDTO(salesOrderMasterDTO);
					iSalesOrderMasterService.updateSalesOrderMaster(salesOrderMasterInputMessage);
				 }catch (Exception e) {
					e.printStackTrace();
				}}	
			// End update supply quantity in sales order master	
			
			//Update Proforma Master With Excise Invoice Number
					proformaMasterDTO.setExciseInvoiceNo(billDTO.getInvoiceNumber());
					proformaMasterInputMessage.setProformaMasterDTO(proformaMasterDTO);
					proformaMasterService.updateBill(proformaMasterInputMessage);
				 
				 
				// Save Bill Details
				BillDetailInputMessage billDetailInputMessage = new BillDetailInputMessage();
				StockLedgerInputMessage stockInputmessage = new StockLedgerInputMessage();
				ExciseLedgerInputMessage exciseLedgerInputMessage=new ExciseLedgerInputMessage();
				StockLedgerDTO ledgerDTO = null;
				ExciseLedgerDTO exciseLedgerDTO=null;
				
				BillDTO billDTOFroInvoiceAutoId = new BillDTO();
				List list = invoiceForm.getBillDetailList();
				for (int i = 0; i < list.size(); i++) {
					BillDetailDTO billDetailDTO = (BillDetailDTO) list.get(i);
					billDetailDTO.setTransactionSeries(series);
					billDetailDTO.setInvoiceNumber(billDTO.getInvoiceNumber());
					
					if (billDTO.getVatCstTaxType().equalsIgnoreCase("vat")) {
						billDetailDTO.setVatPerc(billDetailDTO.getTax());
						billDetailDTO.setVatAmount(billDetailDTO.getTaxAmoForVatOrCst());
						
					}if (billDTO.getVatCstTaxType().equalsIgnoreCase("cst with c form")) {
						billDetailDTO.setCstPerc(billDetailDTO.getTax());
						billDetailDTO.setCstAmount(billDetailDTO.getTaxAmoForVatOrCst());
					}if (billDTO.getVatCstTaxType().equalsIgnoreCase("cst w/o c form")) {
						billDetailDTO.setVatPerc(billDetailDTO.getTax());
						billDetailDTO.setVatAmount(billDetailDTO.getTaxAmoForVatOrCst());
					}
				
						// To get Max Invoice Auto Id
						BillOutMessage outMessage = billService.getMaxInvoiceId();
						ArrayList<BillDTO> bl = (ArrayList<BillDTO>) outMessage.getBillDTOList();
						
						if (bl != null & bl.size() > 0) {
							billDTOFroInvoiceAutoId = bl.get(0);
						}
						billDetailDTO.setCreatedUserId(getCreatedUserId());
						billDetailDTO.setCreatedUserId(getCreatedUserId());
						billDetailDTO.setInvoiceAutoId(billDTOFroInvoiceAutoId.getInvoiceAutoId());
						billDetailInputMessage.setBillDetailDTO(billDetailDTO);
						billDetailService.createBillDetail(billDetailInputMessage);
					

					// Stock Ledger
					exciseLedgerDTO=new ExciseLedgerDTO();
					ledgerDTO = new StockLedgerDTO();
					
					if(billDTO.getBranchDTO().getBranchId()!=null){
					ledgerDTO.setBranchId(billDTO.getBranchDTO().getBranchId());
					exciseLedgerDTO.setBranchId(billDTO.getBranchDTO().getBranchId());
					}
					
					ledgerDTO.setBatchNo(billDetailDTO.getBatchNo());
					ledgerDTO.setTransactionDate(DataUtility.getDate(billDTO.getInvoiceDate()));
					ledgerDTO.setItemId(billDetailDTO.getItemId());
					ledgerDTO.setSalesRate(billDetailDTO.getSalesRate());
					/* set quantity according to primary Unit
					ledgerDTO.setQuantity(0 - billDetailDTO.getQuantity());
					ledgerDTO.setApprovedQuantity(billDetailDTO.getQuantity());*/
					
					ledgerDTO.setQuantity(0 - billDetailDTO.getPrimaryUnit());
					ledgerDTO.setApprovedQuantity(billDetailDTO.getPrimaryUnit());
					
					ledgerDTO.setSalesValue(billDetailDTO.getNetAmount());
					ledgerDTO.setTransactionSeries(series);
					ledgerDTO.setTransactionNumber(billDetailDTO.getInvoiceNumber());
					ledgerDTO.setTransactionId(billDTO.getInvoiceId());
					ledgerDTO.setApprovedDate(new Date());
					
					ledgerDTO.setCreatedUserId(getCreatedUserId());
					stockInputmessage.setStockLedgerDTO(ledgerDTO);
		            stockLedgerService.createStockLedger(stockInputmessage);
				    
		            exciseLedgerDTO.setApprovedDate(new Date());
		            exciseLedgerDTO.setTransactionDate(new Date());
		            exciseLedgerDTO.setItemId(billDetailDTO.getItemId());
		            exciseLedgerDTO.setPartyId(billDTO.getPartyDTO().getPartyId());
		            /* set quantity according to primary Unit
		            exciseLedgerDTO.setInvoiceQty(0 - billDetailDTO.getQuantity());
		            */
		            exciseLedgerDTO.setInvoiceQty(0 - billDetailDTO.getPrimaryUnit());
		            exciseLedgerDTO.setTransactionSeries(series);
		            exciseLedgerDTO.setInvoiceNumber(billDetailDTO.getInvoiceNumber());
		            exciseLedgerDTO.setCessAmount(billDetailDTO.getEduCessAmount());
		            exciseLedgerDTO.sethEduCessAmount(billDetailDTO.getHeduCessAmount());
		            exciseLedgerDTO.setExciseAmount(billDetailDTO.getExciseAmount());
		            exciseLedgerDTO.setNarration(billDTO.getBillRemark());
		            
		            exciseLedgerInputMessage.setExciseLedgerDTO(exciseLedgerDTO);
		            exciseLedgerDTO.setCreatedUserId(getCreatedUserId());
		            exciseLedgerService.createExciseLedger(exciseLedgerInputMessage);
				
				}
				
				
				 //To send mail invoice as a pdf... 
				 try{
				 /*sendInvoicePdfMail(billDTO.getInvoiceNumber(), response,  request);*/
				 System.out.println("..................."+billDTO.getInvoiceNumber());
				 }catch(Exception ex){
					 
				 }
				
				
				session.removeAttribute("invoice");
				/*ModelAndView mv = new ModelAndView(new RedirectView("show_invoice_list?operation="+operation));
				mv.addObject("error", error);
				*/
				
				ModelAndView mv = new ModelAndView(new RedirectView("show_proforma_list?operation=proformaToInvoice"));
				mv.addObject("error", error);
				
				
				return mv;
			} 
			else {
				error = new ErrorDTO();
				error.setErrorMsg("Sorry you can't save invoice without item");
				invoiceForm = (InvoiceForm) session.getAttribute("invoice");

				// mav = new ModelAndView();
				mav.setViewName("invoice_add");
				mav.addObject("error", error);
				mav.addObject("invoice", invoiceForm);
				return mav;
			}
		  }
		
		mav = new ModelAndView();
		mav.setViewName("invoice_add");
		return mav;
	}
	
	
	
	
	
	
	
	

	@RequestMapping(value = "/conevrt_proforma_into_invoice", method = RequestMethod.GET)
	public ModelAndView convertProformaIntoInvoice(@ModelAttribute("invoice") InvoiceForm invoiceForm,@RequestParam String operation, HttpSession session) {

		ModelAndView mav = preloadedData();

		ErrorDTO error = new ErrorDTO();
		if("Delete".equals(operation)){
		      error.setErrorCode("Invoice  Remove Confirmation");
		      error.setOperationName(operation);
		}
		
		String finecialYear= getFinYear();
		String series= getInvoiceTransactionSeries();
		if ("Get_Invoice".equals(operation) || "Delete".equals(operation)) {
			
			Integer invoiceAutoId = invoiceForm.getInvoiceAutoId();
			
			
			
			ProformaMasterInputMessage billInputMessage = new ProformaMasterInputMessage();
			ProformaMasterDTO billDTO = new ProformaMasterDTO();
			billDTO.setInvoiceAutoId(invoiceAutoId);
			billInputMessage.setProformaMasterDTO(billDTO);
			ProformaMasterOutMessage billOutMessage = proformaMasterService.findBillById(billInputMessage);
			List billList = billOutMessage.getProformaMasterDTOList();
	
			session.setAttribute("invoiceAutoId", invoiceAutoId);
			
			BillInputMessage billinputMessage = new BillInputMessage();
			BillDTO billDTO1 = new BillDTO();
			billDTO1.setFinyr(getFinYear());
			billDTO1.setTransactionSeries(series + "/"	+ getFinYear());
			billinputMessage.setBillDTO(billDTO1);
			BillOutMessage billOutMessage1 = billService.getNewBillMasterSeriesNo(billinputMessage);
		    Integer invoiceId=	billOutMessage1.getBillSeries();
		    BillDTO billMasterDTO = new BillDTO();
			
		   
			
			
			if (billList != null && billList.size() > 0) {
				billDTO = (ProformaMasterDTO) billList.get(0);
				try{
					invoiceForm.setTimeToshow(billDTO.getTimeOfRemoval().toString());
					}catch(Exception e){}
				billDTO.setTransactionSeries(series+"/"+billDTO.getFinyr());
			}
			
// To Issue time and removable time
		try{
		Time pt=	billDTO.getTimeOfRemoval();	
		Date pd=	billDTO.getDateOfRemoval();
		
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.set(0, 0, 0);
		String st=dateFormat.format(cal.getTime());
		Time ct = Time.valueOf(st);
		long currentTime = ct.getTime();

		Time tt = Time.valueOf(pt.toString());
		long l = tt.getTime();
		// Date curDate = new Date();
		String checkTime=null;
		 
		 Calendar ca = Calendar.getInstance();
		 ca.setTime(new Date());
		 ca.set(Calendar.HOUR_OF_DAY, 0);
		 ca.set(Calendar.MINUTE, 0);
		 ca.set(Calendar.SECOND, 0);
		 ca.set(Calendar.MILLISECOND, 0);
		 if(pd.getTime()>=ca.getTimeInMillis()){
			  if(currentTime<l ){
				  checkTime="Yes";	
			}else{
				  checkTime="No";	
			} 
		  }else{
			      checkTime="No";	
		  }
		  mav.addObject("checkTime", checkTime);
		}catch(Exception ex){
		 ex.printStackTrace();
		}
		
		//Check Stock
		ProformaDetailDTO billDetailDTO1= new ProformaDetailDTO();
		ProformaDetailInputMessage billDetailInputMessage1 = new ProformaDetailInputMessage();
		//System.out.println("invoice number"+billDTO.getInvoiceNumber());
		billDetailDTO1.setInvoiceNumber(billDTO.getInvoiceNumber());
		billDetailInputMessage1.setProformaDetailDTO(billDetailDTO1);
		ProformaDetailOutMessage billDetailOutMessage1 = proformaDetailService.findBillByBillId(billDetailInputMessage1);
		
		CompanyOutMessage outCompanyOutMessage= companyService.findAllCompanies();
		List<CompanyDTO> cList=outCompanyOutMessage.getCompanyDTOList();
		CompanyDTO dto = null;
		if(cList!=null && cList.size()>0){
			dto=(CompanyDTO)cList.get(0);
			//System.out.println("dto.getStockLockFlag()"+dto.getStockLockFlag());
		}
		
		
		
		List billDtailList1 = billDetailOutMessage1.getProformaDetailDTOList();
		
			
			try {
				if(dto!=null && dto.getStockLockFlag() && checkStockForInvoice(billDtailList1)){
					//System.out.println("Product out of Stock");
					
					error = new ErrorDTO();
					error.setErrorCode("OOS");
					error.setErrorMsg("Promfarama can not convert into Exice beacuse one of the item is out of stock");
					mav.addObject("error", error);
					//mav.setView(new RedirectView("show_proforma_list?operation=show"));
					mav.setView(new RedirectView("show_proforma_list?operation=stockNotAvail"));
				return mav;
					
					
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	// get Branch Information
		BranchDTO branchDTO = new BranchDTO();
		branchDTO.setBranchId(billDTO.getBranchDTO().getBranchId());
			
		BranchInputMessage branchInputMessage = new BranchInputMessage();
		branchInputMessage.setBranchDTO(branchDTO);
		BranchOutMessage branchOutMessage=	branchService.findBranchById(branchInputMessage);
		ArrayList<BranchDTO> branchList = (ArrayList<BranchDTO>)branchOutMessage.getBranchDTOList();
		if(branchList!=null && branchList.size()>0){
		   branchDTO =branchList.get(0);
		   invoiceForm.setDutyVideNotification(branchDTO.getDutyVideNotification());
		   }
			
			//Set ItemGroup Id
			billMasterDTO.setItemGroupFlagId(billDTO.getItemGroupFlagId());
			
			// To set Excise type
			billMasterDTO.setExciseType(billDTO.getExciseType());
			
			// Set Bill Master Information
		    billMasterDTO.setProformaNumber(billDTO.getInvoiceNumber());
		    billMasterDTO.setTransactionSeries(series+"/"+billDTO.getFinyr());
			billMasterDTO.setInvoiceNumber(series+"/"+billDTO.getFinyr()+"/"+invoiceId);
			billMasterDTO.setInvoiceId(invoiceId);
		
			billMasterDTO.setInvoiceAutoId(billDTO.getInvoiceAutoId());
			billMasterDTO.setInvoiceDate(new Date());
			//billMasterDTO.setInvoiceDate(billDTO.getInvoiceDate());
			billMasterDTO.setBranchDTO(billDTO.getBranchDTO());
			billMasterDTO.setBranchId(billDTO.getBranchDTO().getBranchId());
			billMasterDTO.setSalesOrderNumber(billDTO.getSalesOrderNumber());
			billMasterDTO.setSalesOrderDate(billDTO.getSalesOrderDate());
			billMasterDTO.setPartyDTO(billDTO.getPartyDTO());
			billMasterDTO.setConsigneeId(billDTO.getConsigneeId());
			billMasterDTO.setRoadPermitDate(billDTO.getRoadPermitDate());
			billMasterDTO.setRoadPermitNo(billDTO.getRoadPermitNo());
			billMasterDTO.setBuyerPoDate(billDTO.getBuyerPoDate());
			billMasterDTO.setBuyerPoNo(billDTO.getBuyerPoNo());
			billMasterDTO.setTransportId(billDTO.getTransportId());
			billMasterDTO.setFormNo(billDTO.getFormNo());
			billMasterDTO.setFormDate(billDTO.getFormDate());
			billMasterDTO.setFormTypeId(billDTO.getFormTypeId());
			billMasterDTO.setFormReqFlag(billDTO.getFormReqFlag());
			billMasterDTO.setLrDate(billDTO.getLrDate());
			billMasterDTO.setLrNo(billDTO.getLrNo());
			billMasterDTO.setEmployeeDTO(billDTO.getEmployeeDTO());
			billMasterDTO.setDeliveryNoteDate(billDTO.getDeliveryNoteDate());
			billMasterDTO.setDeliveryNoteNo(billDTO.getDeliveryNoteNo());
			billMasterDTO.setMotorVehicleNo(billDTO.getMotorVehicleNo());
			billMasterDTO.setTermsOfPayment(billDTO.getTermsOfPayment());
			billMasterDTO.setDespatchDocumentNo(billDTO.getDespatchDocumentNo());
			billMasterDTO.setDespatchThrough(billDTO.getDespatchThrough());
			billMasterDTO.setDateOfRemoval(billDTO.getDateOfRemoval());
			billMasterDTO.setTimeOfRemoval(billDTO.getTimeOfRemoval());
			billMasterDTO.setVatCstTaxType(billDTO.getVatCstTaxType());
			billMasterDTO.setPacketTotal(billDTO.getPacketTotal());
			billMasterDTO.setBillRemark(billDTO.getBillRemark());
			
			//set H. Cess and Cess %(on Excise)
			billMasterDTO.setHighEducationCessPerc(billDTO.getHighEducationCessPerc());
			billMasterDTO.setEducationCessperc(billDTO.getEducationCessperc());
			
			
			//
			billMasterDTO.setPackingForwarding(billDTO.getPackingForwarding());
			billMasterDTO.setOthersDetail(billDTO.getOthersDetail());
			billMasterDTO.setOtherAmount(billDTO.getOtherAmount());
			billMasterDTO.setFreightAmt(billDTO.getFreightAmt());
			
			billMasterDTO.setAssessableValue(billDTO.getAssessableValue());
			String invoiceNumber = billDTO.getInvoiceNumber();
			ProformaDetailDTO billDetailDTO = new ProformaDetailDTO();
			
			ProformaDetailInputMessage billDetailInputMessage = new ProformaDetailInputMessage();
			billDetailDTO.setInvoiceNumber(billDTO.getInvoiceNumber());
			billDetailInputMessage.setProformaDetailDTO(billDetailDTO);
			ProformaDetailOutMessage billDetailOutMessage = proformaDetailService.findBillByBillId(billDetailInputMessage);

			List billDtailList = billDetailOutMessage.getProformaDetailDTOList();

			// To get Item Name
			for (int i = 0; i < billDtailList.size(); i++) {
				billDetailDTO = (ProformaDetailDTO) billDtailList.get(i);
				Integer itemId = billDetailDTO.getItemId();
				ItemDTO itemDTO = new ItemDTO();
				itemDTO.setItemId(itemId);
				ItemInputMessage inputMessage = new ItemInputMessage();
				inputMessage.setItemDTO(itemDTO);
				ItemOutMessage itemOutMessage = itemService.findItemById(inputMessage);
				ArrayList<ItemDTO> itemList = (ArrayList<ItemDTO>) itemOutMessage.getItemDTOList();
				try{
				itemDTO = itemList.get(0);
				}catch (Exception e) {
					// TODO: handle exception
				}
				String itemName = itemDTO.getItemName();
				billDetailDTO.setItemName(itemName);

				try {
					if (billDTO.getVatCstTaxType().equals("vat")) {
					    billDetailDTO.setTax(billDetailDTO.getVatPerc());
					}if (billDTO.getVatCstTaxType().equals("cst with c form")) {
						billDetailDTO.setTax(billDetailDTO.getCstPerc());
					}if (billDTO.getVatCstTaxType().equals("cst w/o c form")) {
						billDetailDTO.setTax(billDetailDTO.getVatPerc());
					}
					} catch (Exception e) {
					}

				billDetailDTO.setDiscountAmountPerToShow(billDetailDTO.getDiscountPer());
				// Get MFG Date From Batch
				BatchInputMessage batchInputMessage = new BatchInputMessage();
				BatchDTO batchDTO = new BatchDTO();
				batchInputMessage.setItemId(itemDTO.getItemId());
				BatchOutMessage batchOutMessage = batchService.findAllBatchByItemId(batchInputMessage);
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

			invoiceForm.setBillDetailList(billDtailList);

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
				
				invoiceForm.setBillDTO(billMasterDTO);
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
				invoiceForm.setBuyerState(cityDTO.getAreaDTO().getRegionDTO().getStateDTO().getStateName());
				invoiceForm.setBuyerCityName(partyDTO.getCityName());
				invoiceForm.setBuyerContactPerson(partyDTO.getContactPerson1());
				invoiceForm.setBuyerBillingAddress(partyDTO.getBillingAddress());
				invoiceForm.setBuyerPhonNo(partyDTO.getPhoneO1());
				// End Buyer Information
				} catch (Exception e) {
				}
			session.setAttribute("invoice", invoiceForm);
		    }
		 error.setOperationName("ConvertToInvoice");
		 Boolean flag=(Boolean)session.getAttribute("hotKeyStatus");
		 mav.addObject("hotKeyStatus", flag);
		mav.addObject("error", error);
		mav.setViewName("invoice_add");
		mav.addObject("invoice", invoiceForm);
		return mav;
	    }
	
	protected ModelAndView preloadedData() {
		ModelAndView mav = new ModelAndView();

		EmployeeOutputMessage employeeOutputMessage = employeeService
				.findAllEmployee();
		List<EmployeeDTO> employeeList = (ArrayList<EmployeeDTO>) employeeOutputMessage
				.getEmployeeDTOList();

		// PartyOutMessage partyOutMessage = partyService.findAllPartys();
		PartyOutMessage partyOutMessage = partyService.findDebtorPartyList();
		List<PartyDTO> partyList = (ArrayList<PartyDTO>) partyOutMessage
				.getPartyDTOList();
		TransporterOutMessage transporterOutMessage = transporterService.preload();
		List<TransporterDTO> transporterList = (ArrayList<TransporterDTO>) transporterOutMessage
				.getTransporterDTOList();

		BranchOutMessage branchOutMessage = branchService.findAllBranches();
		List<BranchDTO> branchList = (ArrayList<BranchDTO>) branchOutMessage
				.getBranchDTOList();

		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		MastersDTO dto = new MastersDTO();
		dto.setFormId(12);
		mastersInputMessage.setMastersDTO(dto);

		mastersInputMessage.setFormId(12);
		MastersOutputMessage mastersOutputMessage = mastersetService.findFormById(mastersInputMessage);

		ArrayList<MastersDTO> masterFormList = (ArrayList<MastersDTO>) mastersOutputMessage.getMastersDTOList();

		//Item Group list
		ItemGroupFlagOutMessage itemGroupFlagOutMessage= itemGroupFlagService.findAllItemGroupFlag();
		ArrayList<ItemGroupFlagDTO> itemGroupFlagList=(ArrayList<ItemGroupFlagDTO>)itemGroupFlagOutMessage.getItemGroupFlagDTOList();
		//System.out.println("itemGroupFlagList size"+itemGroupFlagList.size());
		mav.addObject("itemGroupFlagList", itemGroupFlagList);
		mav.addObject("employeeList", employeeList);
		mav.addObject("partyList", partyList);
		mav.addObject("transporterList", transporterList);
		mav.addObject("masterFormList", masterFormList);
		mav.addObject("branchList", branchList);

		return mav;
	}

	@RequestMapping(value = "/getPartyInfo", method = RequestMethod.POST)
	public @ResponseBody JsonResponse addUser(@ModelAttribute(value = "partyForm") PartyForm partyForm,
			BindingResult result, @RequestParam Integer partyId,HttpSession session) {
		JsonResponse res = new JsonResponse();
		PartyInputMessage partyInputMessage = new PartyInputMessage();
		PartyDTO partyDTO = new PartyDTO();
		partyDTO.setPartyId(partyId);
		partyInputMessage.setPartyDTO(partyDTO);

		PartyOutMessage partyOutMessage = partyService.findPartyById(partyInputMessage);
		List<PartyDTO> list = new ArrayList<PartyDTO>();
		list = (ArrayList<PartyDTO>) partyOutMessage.getPartyDTOList();
		partyDTO = list.get(0);

		// Get state name
		CityDTO cityDTO = new CityDTO();
		CityInputMessage cityInputMessage = new CityInputMessage();
		cityDTO.setCityId(partyDTO.getBillingCityId());
		cityInputMessage.setCityDTO(cityDTO);

		CityOutputMessage cityOutputMessage = cityService.findCityById(cityInputMessage);
		ArrayList<CityDTO> cityList = (ArrayList<CityDTO>) cityOutputMessage
				.getCityDTOList();
		if (cityList != null) {
			cityDTO = cityList.get(0);
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
	
	private String getInvoiceTransactionSeries() {
		TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
		transactionTypeDTO.setSno(4);
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
private String convertDoubleToWord(Double value){
		
	int intRuprr = (int) value.doubleValue(); // here's the first part
	double f = value - (double)intRuprr;
	f = Math.round(f * 100);
	int intPaise = (int) f;  // here's the second part
	
    ConvertNumberIntoWord convertNumberIntoWord=new ConvertNumberIntoWord();
    String rupee= convertNumberIntoWord.convertWTOnly(intRuprr);
    String  paise= convertNumberIntoWord.convertWTOnly(intPaise);
    
    String amount=null;
    if(intPaise>0){
     amount=rupee+" Rupees & "+paise +" Paise "+ " Only/-";
    }else{
    	amount=rupee+" Rupees Only/-";
    }
    return amount;
	}

private boolean checkStockForInvoice(List billDtailList){
	
	boolean b=false;
	ProformaDetailDTO billDetailDTO= new ProformaDetailDTO();
	
	// To get Item Name
				for (int i = 0; i < billDtailList.size(); i++) {
					Double stock=0.0;
					billDetailDTO = (ProformaDetailDTO) billDtailList.get(i);
					//System.out.println("item Id"+billDetailDTO.getItemId());
					/*ledgerDTO.setItemId(billDetailDTO.getItemId());
					stockInputmessage.setStockLedgerDTO(ledgerDTO);
					stockOutputmessage=stockLedgerService.countStockByItemId(stockInputmessage);*/
					ItemDTO itemDTO = new ItemDTO();
					itemDTO.setItemId(billDetailDTO.getItemId());
					ItemInputMessage inputMessage = new ItemInputMessage();
					inputMessage.setItemDTO(itemDTO);
					ItemOutMessage itemOutMessage = itemService.findItemById(inputMessage);
					
					//ledgerDTO=stockOutputmessage.getStockLedgerDTO();
					List<ItemDTO> resultList=itemOutMessage.getItemDTOList();
					//System.out.println("stock"+stock+"- Qty"+billDetailDTO.getPrimaryUnit());
					if(resultList!=null && resultList.size()>0){
						itemDTO=resultList.get(0);
						//System.out.println("itemDTO"+itemDTO+"-Sotck"+itemDTO.getStockTotal());
					if(itemDTO.getStockTotal()<billDetailDTO.getPrimaryUnit()){
						b=true;
						break;
					}
					}
				}
	
    return b;
	}


public void sendInvoicePdfMail(String invoiceNumber,HttpServletResponse response, HttpServletRequest request){
	System.out.println("CALLING...............................");
	File pdffile = null;
	try {
		Connection conn = dataSource.getConnection();
		// Get the jasper report object
		// Load it
		InputStream reportStream = this.getClass().getResourceAsStream(
				"/reports/Despatch_Intimation.jasper");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);

		
		File directory = new File(".");
		String reportName="Despatch_Intimation.pdf";
		

		pdffile = new File(directory.getCanonicalPath() + File.separator
				+ reportName);
		
		    Map<String,Object> parameterMap = new HashMap<String,Object>();
	       
	        
	        parameterMap.put("InvNoMedPrompt", invoiceNumber);
	        String realPath  = request.getSession().getServletContext().getRealPath(System.getProperty("file.separator")+"WEB-INF"+System.getProperty("file.separator")+"images"+System.getProperty("file.separator")+"shree_logo.JPG");
			parameterMap.put("Image_Loc", realPath.toString());
			
			String realPath1  = request.getSession().getServletContext().getRealPath(System.getProperty("file.separator")+"WEB-INF"+System.getProperty("file.separator")+"images"+System.getProperty("file.separator")+"ShreeCera.jpg");
			parameterMap.put("Image_Loc_1", realPath1.toString());
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, conn);
		// Create report exporter to be in Html
		JRExporter exporter;
		exporter = new JRPdfExporter();
		FileOutputStream fos = new FileOutputStream(pdffile);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
		exporter.exportReport();
		// Export the report, store to sb
		exporter.exportReport();
		
		conn.close();
		
		String emailId=null;
 		try{
 		 emailId=partyService.getEmailId(invoiceNumber, "Invoice");
 		emailId.trim();
 		}catch (Exception e) {
		}
 		
 		String []emailIds=null;
 		try{
 		emailIds=emailId.split(",");
 		}catch (Exception e) {
		}
		 //String[] emailIds = new String[1];
		 //emailIds[0]=emailId;
			 
		 
		 MailPdf mail = new MailPdf(pdffile, reportName);
					 
			String body = "Dear Sir," + "\n" + "\n"
		    +"Please find below attached PDF report, auto generated from ERP system."
		    + "\n" + "\n"
		    
			+ "\n"
			+ "\n"
			+ "Thanks & Regards"
			+ "\n"
			+ "ERP TEAM"
			+ "\n"
			+ "\n"
			+ "***This is an auto generated email. Please don't reply on this email id.Please contact your system administrator for any query.";
		 
		mail.sendSSLMessage(emailIds, pdffile.getName(), body,"abc@gmail.com");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}




public static void main(String[] args) throws Exception{
	/*InvoiceController invoiceController=new InvoiceController();
	String s= invoiceController.convertDoubleToWord(193055.0);
	
	
	System.out.println(s);*/
	
	/*SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-M-dd");
	String sd= timeFormat.format(new Date());
	Date dd= timeFormat.parse(sd);
	
	DateFormat df = new SimpleDateFormat("yyyy-M-dd");
	String ss = df.format(new Date());
	Date d = (new SimpleDateFormat("yyyy-M-dd")).parse(sd);
	System.out.println(dd);*/
	
	
	//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

//Date date=new Date();
	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	cal.set(0, 0, 0);
	String st=dateFormat.format(cal.getTime());
	Time ct = Time.valueOf(st);
	long currentTime = ct.getTime();
    System.out.println(ct);

}
 }
