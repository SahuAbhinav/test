package com.advanz.erp.masters.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.BlanketWeightRecordEntity;
import com.advanz.erp.masters.entity.jpa.CompanyEntity;
import com.advanz.erp.masters.entity.jpa.LoggerEntity;
import com.advanz.erp.masters.model.BlanketWeightRecordDTO;
import com.advanz.erp.masters.model.CompanyDTO;
import com.advanz.erp.masters.model.LoggerDTO;
import com.advanz.erp.masters.model.msg.CompanyInputMessage;
import com.advanz.erp.masters.model.msg.CompanyOutMessage;
import com.advanz.erp.masters.model.msg.ItemCategoryOutMessage;
import com.advanz.erp.masters.service.business.IBillService;
import com.advanz.erp.masters.service.business.ICompanyService;
import com.advanz.erp.masters.service.business.IFinishedGoodsMasterService;
import com.advanz.erp.masters.service.business.IGrnMasterService;
import com.advanz.erp.masters.service.business.ISalesOrderMasterService;
import com.advanz.erp.masters.service.business.IStockLedgerService;
import com.advanz.erp.masters.storage.IStorageCompanyDAO;

@Service
public class CompanyServiceImpl implements ICompanyService {

	public static final String CREATE_COMPANY = "Company";
	public static final String UPDATE_COMPANY = "UpdateCompany";
	public static final String ADD_COMPANY = "AddCompany";
	public static final String DELETE_COMPANY = "DeleteCompany";
	public static final String FIND_COMPANY_BY_ID = "FindCompanyById";
	public static final String FIND_ALL_COMPANIES = "FindAllCompnies";
	public static final String FIND_COMPANIES = "FindCompanies";

	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageCompanyDAO storageCompanyDAO;

	public CompanyInputMessage companyInputMessage;

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyServiceImpl.class);

	public CompanyOutMessage companyOutMessage;

	@Autowired
	public IStockLedgerService service;
	@Autowired
	public IBillService billService;

	@Autowired
	public IFinishedGoodsMasterService finishedGoodsMasterService;

	@Autowired
	public ISalesOrderMasterService salesOrderService;

	@Autowired
	public IGrnMasterService grnMasterService;

	@Override
	public CompanyOutMessage createCompany(
			CompanyInputMessage companyInputMessage) {

		flowId = ADD_COMPANY;
		// assign the message to the class level variable.
		this.companyInputMessage = companyInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return companyOutMessage;
	}

	@Override
	public CompanyOutMessage updateCompany(
			CompanyInputMessage companyInputMessage) {

		flowId = UPDATE_COMPANY;
		// assign the message to the class level variable.
		this.companyInputMessage = companyInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return companyOutMessage;
	}

	@Override
	public CompanyOutMessage deleteCompany(
			CompanyInputMessage companyInputMessage) {
		flowId = DELETE_COMPANY;
		// assign the message to the class level variable.
		this.companyInputMessage = companyInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return companyOutMessage;

	}

	@Override
	public CompanyOutMessage findCompanyById(
			CompanyInputMessage companyInputMessage) {
		flowId = FIND_COMPANY_BY_ID;
		this.companyInputMessage = companyInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return companyOutMessage;

	}

	@Override
	public CompanyOutMessage findAllCompanies() {
		flowId = FIND_ALL_COMPANIES;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return companyOutMessage;
	}

	@Override
	public CompanyOutMessage findCompany(CompanyInputMessage companyInputMessage) {
		flowId = FIND_COMPANIES;
		this.companyInputMessage = companyInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return companyOutMessage;
	}

	@Override
	public boolean validateInput() {

		if (ADD_COMPANY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_COMPANY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_COMPANY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_COMPANY_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_COMPANIES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_COMPANIES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {
		CompanyEntity companyEntity = new CompanyEntity();

		if (ADD_COMPANY.equals(flowId)) {
			// CompanyDto compDto = companyInputMessage.getCompanyDTO();
			// companyEntity.setCompanyName(compDto.getCompanyName());

			try {
				BeanUtils.copyProperties(companyInputMessage.getCompanyDTO(),
						companyEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			List<CompanyEntity> nameList = storageCompanyDAO
					.findByName(companyEntity.getCompanyName());
			List<CompanyEntity> codeList = storageCompanyDAO
					.findByCode(companyEntity.getCompanyCode());
			// logger.info(flowId + ": " + nameList);
			// logger.info(flowId + ": " + codeList);
			companyOutMessage = new CompanyOutMessage();
			if ((nameList != null && nameList.size() > 0)
					|| (codeList != null && codeList.size() > 0)) {
				ErrorDTO errorDTO = new ErrorDTO("1",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				companyOutMessage.setErrorListDTO(errorListDTO);
			} else {
				companyOutMessage.setErrorListDTO(null);
				storageCompanyDAO.create(companyEntity);
			}
		} else if (UPDATE_COMPANY.equals(flowId)) {
			try {
				BeanUtils.copyProperties(companyInputMessage.getCompanyDTO(),
						companyEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<CompanyEntity> nameList = storageCompanyDAO
					.findByName(companyEntity.getCompanyName());
			List<CompanyEntity> codeList = storageCompanyDAO
					.findByCode(companyEntity.getCompanyCode());
			companyOutMessage = new CompanyOutMessage();
			ErrorListDTO errorListDTO = new ErrorListDTO();
			if (nameList != null
					&& nameList.size() > 0
					&& !nameList.get(0).getCompanyId()
							.equals(companyEntity.getCompanyId())) {
				ErrorDTO errorDTO = new ErrorDTO("1",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				errorListDTO.addError(errorDTO);
			}
			if (codeList != null
					&& codeList.size() > 0
					&& !codeList.get(0).getCompanyId()
							.equals(companyEntity.getCompanyId())) {
				ErrorDTO errorDTO = new ErrorDTO("2",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				errorListDTO.addError(errorDTO);
			}
			if (errorListDTO.hasErrors()) {
				companyOutMessage.setErrorListDTO(errorListDTO);
			} else {
				companyOutMessage.setErrorListDTO(null);
				storageCompanyDAO.update(companyEntity);
			}

		} else if (DELETE_COMPANY.equals(flowId)) {
			try {
				BeanUtils.copyProperties(companyInputMessage.getCompanyDTO(),
						companyEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			storageCompanyDAO.delete(companyEntity);
		} else if (FIND_COMPANY_BY_ID.equals(flowId)) {
			CompanyDTO companyDto = companyInputMessage.getCompanyDTO();
			List<CompanyEntity> list = storageCompanyDAO.findById(companyDto
					.getCompanyId());
			companyOutMessage = new CompanyOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<CompanyDTO> resultList = convertCompanyEntityListTOCompanyDtoList(list);
				companyOutMessage.setCompanyDTOList(resultList);
			}
		} else if (FIND_ALL_COMPANIES.equals(flowId)) {
			List<CompanyEntity> list = storageCompanyDAO.load();
			companyOutMessage = new CompanyOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<CompanyDTO> resultList = convertCompanyEntityListTOCompanyDtoList(list);
				companyOutMessage.setCompanyDTOList(resultList);
			}
		} else if (FIND_COMPANIES.equals(flowId)) {
			CompanyDTO companyDto = companyInputMessage.getCompanyDTO();
			// System.out.println("################## Company Service "+
			// companyDto.toString());
			List<CompanyEntity> list = storageCompanyDAO.search(
					companyDto.getCompanyName(), companyDto.getCompanyCity(),
					companyDto.getCompanyCode());
			companyOutMessage = new CompanyOutMessage();
			if (list != null) {
				List<CompanyDTO> resultList = convertCompanyEntityListTOCompanyDtoList(list);
				companyOutMessage.setCompanyDTOList(resultList);
			}
		}
	}

	@Override
	public void formatOutput() {

		if (ADD_COMPANY.equals(flowId)) {

		} else if (UPDATE_COMPANY.equals(flowId)) {

		} else if (DELETE_COMPANY.equals(flowId)) {

		} else if (FIND_COMPANY_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_COMPANIES.equals(flowId)) {

		}
	}

	private List<CompanyDTO> convertCompanyEntityListTOCompanyDtoList(
			List<CompanyEntity> list) {
		List<CompanyDTO> resultList = new ArrayList<CompanyDTO>();
		CompanyDTO companyDto;
		for (CompanyEntity entity : list) {
			companyDto = new CompanyDTO();
			try {
				BeanUtils.copyProperties(entity, companyDto);
			} catch (BeansException e) {
				e.printStackTrace();
			}
			resultList.add(companyDto);

		}
		return resultList;
	}

	@Override
	public Boolean getEmailFlag() {
		// TODO Auto-generated method stub
		return storageCompanyDAO.getEmailFlag();
	}

	@Override
	public void sendEmail(Integer userId, Date sdate) {
		String body = null;
		// 1 Create Body for Invoice alert (Alert 1)
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		// Date startDate= c.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(c.getTime());
		String endDate = sdf.format(new Date());
		
		String Emaildate=startDate;
		try {
			Emaildate = sdf.format(sdate);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		// TO.........
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		Date date = new Date();
		String month = new SimpleDateFormat("MMM").format(date);
		String formatedDate = cal.get(Calendar.DATE) + " " + month + " "
				+ cal.get(Calendar.YEAR);
		//String formatedDate=null;
		if (sdate != null) {
			String dateString = sdf.format(sdate);
			body = service.getEmailByDate(dateString);

			cal.setTime(sdate);
			month = new SimpleDateFormat("MMM").format(sdate);
			 formatedDate = cal.get(Calendar.DATE) + " " + month + " "
					+ cal.get(Calendar.YEAR);
			/*
			 * if(body==null){ body = "Dear Sir," + "\n" + "\n" + "\n" + "\n"
			 * +"Email is not genarated on "+formatedDate+"." +
			 * "Thanks & Regards" + "\n" + "ERP TEAM" + "\n" + "\n" +
			 * "***This is an auto generated email. Please don't reply on this email id.Please contact your system administrator for any query."
			 * ; }
			 */

		} else {

			ArrayList<List> arrList = (ArrayList<List>) billService
					.getCountInvoiceToEmail(startDate, endDate);
			List list = arrList.get(0);
			Object[] objects = (Object[]) list.get(0);
			Long totalInvoice1 = (Long) objects[0];
			int totalInvoice = 0;
			if (totalInvoice1 != null) {
				totalInvoice = totalInvoice1.intValue();
			}
			Double totalInvoiceAmnt1 = (Double) objects[1];
			int totalInvoiceAmnt = 0;
			if (totalInvoiceAmnt1 != null) {
				totalInvoiceAmnt = totalInvoiceAmnt1.intValue();
			}
			Double totalMonthInvoiceAmnt1 = (Double) objects[2];
			int totalMonthInvoiceAmnt = 0;
			if (totalMonthInvoiceAmnt1 != null) {
				totalMonthInvoiceAmnt = totalMonthInvoiceAmnt1.intValue();
			}
			// Body For Invoice end ..................

			// 2 Create Body for Sales Order alert(Alert 2)
			List salesList = salesOrderService.getSalesDetailToEmail(endDate);
			Object[] salesObjects = (Object[]) salesList.get(0);
			BigInteger totalSales1 = (BigInteger) salesObjects[0];
			int totalSales = 0;
			if (totalSales1 != null) {
				totalSales = totalSales1.intValue();
			}
			Double totalPendingItemValue1 = (Double) salesObjects[1];
			int totalPendingItemValue = 0;
			if (totalPendingItemValue1 != null) {
				totalPendingItemValue = totalPendingItemValue1.intValue();
			}
			Double total15DaysPendingItemValue1 = (Double) salesObjects[2];
			int total15DaysPendingItemValue = 0;
			if (total15DaysPendingItemValue1 != null) {
				total15DaysPendingItemValue = total15DaysPendingItemValue1
						.intValue();
			}
			// Sales order body complete

			// 3 Create Body for Finish Good alert(Alert 3)
			List finishGoodList = finishedGoodsMasterService
					.getFinishGoodInfoToEmail(endDate);
			Object[] finishGoodObjects = (Object[]) finishGoodList.get(0);
			BigInteger totalFinishedGood = (BigInteger) finishGoodObjects[0];
			Double totalFinishedGoodsStock1 = (Double) finishGoodObjects[1];
			int totalFinishedGoodsStock = 0;
			if (totalFinishedGoodsStock1 != null) {
				totalFinishedGoodsStock = totalFinishedGoodsStock1.intValue();
			}
			Double totalPengingSalesOrderAmnt1 = (Double) finishGoodObjects[2];
			int totalPengingSalesOrderAmnt = 0;
			if (totalPengingSalesOrderAmnt1 != null) {
				totalPengingSalesOrderAmnt = totalPengingSalesOrderAmnt1
						.intValue();
			}
			Double totalPendingProductionAmnt1 = (Double) finishGoodObjects[3];
			int totalPendingProductionAmnt = 0;
			if (totalPendingProductionAmnt1 != null) {
				totalPendingProductionAmnt = totalPendingProductionAmnt1
						.intValue();
			}

			// Finish Good Email Body End

			// 5 Create Body for grn(Alert 5)
			List grnList = grnMasterService.getEmailAlertData(endDate);
			Object[] grnObjects = (Object[]) grnList.get(0);
			BigInteger totalGrn = (BigInteger) grnObjects[0];
			BigInteger totalGrnUpTo = (BigInteger) grnObjects[1];
			BigInteger totalPendingGrn = (BigInteger) grnObjects[2];
			// Grn Body is complete

			// 4 Create Body Message for Minimum Inventry(Alert 4)
			List milList = service.ItemCountDateWise(endDate);
			Object[] milObjects = (Object[]) milList.get(0);
			BigInteger totalItem = (BigInteger) milObjects[0];
			BigInteger totalPendingItem = (BigInteger) milObjects[1];

			// 6 Create body for excise alert(excise amount,cess and h.cess
			// amount)(alert6)
			List list6 = arrList.get(1);
			Object[] objects1 = (Object[]) list6.get(0);
			Double totalExciseAmnt = (Double) objects1[0];
			int totalExcise = 0;
			if (totalExciseAmnt != null) {
				totalExcise = totalExciseAmnt.intValue();
			}
			Double totalCessAmnt = (Double) objects1[1];
			int totalCess = 0;
			if (totalCessAmnt != null) {
				totalCess = totalCessAmnt.intValue();
			}
			Double totalHcessAmnt = (Double) objects1[2];
			int totalHcess = 0;
			if (totalHcessAmnt != null) {
				totalHcess = totalHcessAmnt.intValue();
			}
			// 7 Create body for count booked sales order
			List bookedSalesList = salesOrderService
					.getBookedSalesDetailToEmail(endDate);
			Object[] bookedSalesObjects = (Object[]) bookedSalesList.get(0);
			BigInteger totalSalesOrder1 = (BigInteger) bookedSalesObjects[0];
			int totalSalesOrder = 0;
			if (totalSalesOrder1 != null) {
				totalSalesOrder = totalSalesOrder1.intValue();
			}
			String description = (String) bookedSalesObjects[1];

			String alert7 = null;
			if (description != null) {
				alert7 = "7)Today's total booked orders are " + totalSalesOrder
						+ " and the details are " + description + ".";
			} else {
				alert7 = "7)Today's total booked orders are " + totalSalesOrder
						+ ".";
			}
			body = "Dear Sir,"
					+ "\n"
					+ "\n"
					+ "Please find below few key informations, auto generated from ERP system on "
					+ formatedDate
					+ "\n"
					+ "\n"
					+ "1) Excise Invoice - On "
					+ formatedDate
					+ ",  "
					+ totalInvoice
					+ "  excise invoices were made of total value Rs "
					+ totalInvoiceAmnt
					+ "/-,  the total of this month's Excise invoices is Rs "
					+ totalMonthInvoiceAmnt
					+ "/-"
					+ "\n"
					+ "\n"
					+ "2) Pending Sales Order - On "
					+ formatedDate
					+ ", total "
					+ totalSales
					+ " items are pending to deliver valued at Rs "
					+ totalPendingItemValue
					+ " The value of items pending from more than 15 days is Rs "
					+ total15DaysPendingItemValue
					+ "/-"
					+ "\n"
					+ "\n"
					+ "3) Finished Goods - On "
					+ formatedDate
					+ ", "
					+ totalFinishedGood
					+ " entries were received in Finished Goods, now total stock of finished goods is  Rs "
					+ totalFinishedGoodsStock
					+ "/- out of this, Rs "
					+ totalPengingSalesOrderAmnt
					+ "/- is booked in various sales orders. and Rs "
					+ totalPendingProductionAmnt
					+ "/- is pending for production."
					+ "\n"
					+ "\n"
					+ "4)MIL - On "
					+ formatedDate
					+ ", "
					+ totalItem
					+ " items  are below MIL. Out of these, PO are pending for "
					+ totalPendingItem
					+ " items."
					+ "\n"
					+ "\n"
					+ "5)GRN - On "
					+ formatedDate
					+ ", total  "
					+ totalGrn
					+ "  GRN  were made, as on date, "
					+ totalGrnUpTo
					+ " GRN  are pending for approval, out of which, "
					+ totalPendingGrn
					+ " are pending since more than 4  days."
					+ "\n"
					+ "\n"
					+ "6)On "
					+ formatedDate
					+ ", for current month, exsice total as per invoices are Excise total Rs."
					+ totalExcise
					+ "/- ,Cess Rs."
					+ totalCess
					+ "/- and Heiger Eduction Cess Rs."
					+ totalHcess
					+ "/-"
					+ "\n"
					+ "\n"
					+ alert7
					+ "\n"
					+ "\n"
					+ "Thanks & Regards"
					+ "\n"
					+ "ERP TEAM"
					+ "\n"
					+ "\n"
					+ "***This is an auto generated email. Please don't reply on this email id.Please contact your system administrator for any query.";

			try {
				service.createEmailDetal(body, new Date(), userId);
				//Thread.sleep(2000);
				 logger.info("in step 1 for insert data");
			} catch (Exception e) {
			}

		}

		// End alert body
		String[] sendToArray = new String[4];
		sendToArray[0] = "amit@shreecera.in";
		sendToArray[1] = "joseph@shreecera.in";
		// sendToArray[1]="erp101system@gmail.com";

		sendToArray[2] = "sales@shreecera.co.in";
		sendToArray[3] = "stores@shreecera.com";
		
		/*String[] sendToArray = new String[1];
		sendToArray[0] = "abhinav7786@gmail.com";*/
		//sendToArray[0] = "abhinav7786@gmail.com";
		/*
		 * String[] sendToArray = new String[1];
		 * //sendToArray[0]="anildhakad26@yahoo.in"; sendToArray[0]
		 * ="anildhkd26@gmail.com";
		 */

		MailWithAttachment attachment = new MailWithAttachment();

		String subject = "Summary Alerts from Shree Cera ERP dated "
				+ formatedDate;

		Boolean b = storageCompanyDAO.getEmailFlag();
		int i = 0;

		if (b == true) {
			try {
				attachment.sendSSLMessage(sendToArray, subject, body,
						"anildhkd26@gmail.com");
			} catch (MessagingException e) {
				e.printStackTrace();
				i = 1;
			} finally {
				if (i == 0) {
					logger.info("in step 2.1 for insert data");
					service.updateEmailDetal(endDate, "UPDATE");
				} else {
					logger.info("in step 2.2 for insert data");
					service.updateEmailDetal(endDate, "FALSE");
				}
			}
		}
		// mailSender.send(mimeMessage);

	}

	public static void main(String[] args) {
		/*Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(c.getTime());
		String endDate = null;

		endDate = sdf.format(new Date());*/
		CompanyServiceImpl s= new CompanyServiceImpl();
		 s.sendEmail(null, null);

	}

	@Override
	public Date getSalaryGenaratingDate() {
		return storageCompanyDAO.getSalaryGenaratingDate();
	}

	@Override
	public void createBlanketWeightRecord(
			BlanketWeightRecordDTO blanketWeightRecordDTO) {
		BlanketWeightRecordEntity weightRecordEntity = new BlanketWeightRecordEntity();

		BeanUtils.copyProperties(blanketWeightRecordDTO, weightRecordEntity);

		storageCompanyDAO.createBlanketWeightRecord(weightRecordEntity);
	}

	@Override
	public void createLoggerRecord(LoggerDTO loggerDTO) {
		LoggerEntity loggerEntity = new LoggerEntity();
		BeanUtils.copyProperties(loggerDTO, loggerEntity);

		storageCompanyDAO.createLoggerRecord(loggerEntity);
	}

	@Override
	public void checkSendEmail(Integer userId, Date date) {
		logger.info("new Date" + new Date().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formatedDate = sdf.format(new Date());
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		//String formatedDate = new SimpleDateFormat("yyyyy-MM-dd").format(tDate);
		String month = new SimpleDateFormat("MMM").format(new Date());
		 String formatedDate1 = cal.get(Calendar.DATE) + " " + month + " "
				+ cal.get(Calendar.YEAR);
		 
		List list = service.getEmailDetal(formatedDate, "TRUE");
		logger.info("List size" + list.size());
		if (list != null && list.size() > 0) {
			//sendReEmail(null, formatedDate,formatedDate1);

		} else {
			sendEmail(null, null);
		}

	}

	/*public void sendReEmail(Integer userId, String sdate,String formateDate) {
		String body = null;

		
		body = service.getEmailByDate(sdate);

		// End alert body
		String[] sendToArray = new String[4];
		sendToArray[0] = "amit@shreecera.in";
		sendToArray[1] = "vinod.mech84@gmail.com";
		// sendToArray[1]="erp101system@gmail.com";

		sendToArray[2] = "sales@shreecera.co.in";
		sendToArray[3] = "stores@shreecera.com";
		
		//String[] sendToArray = new String[1];
		//sendToArray[0] = "abhinav7786@gmail.com";
		
		
		 * String[] sendToArray = new String[1];
		 * //sendToArray[0]="anildhakad26@yahoo.in"; sendToArray[0]
		 * ="anildhkd26@gmail.com";
		 

		MailWithAttachment attachment = new MailWithAttachment();

		String subject = "Summary Alerts from Shree Cera ERP dated "
				+ formateDate;

		Boolean b = storageCompanyDAO.getEmailFlag();
		int i = 0;

		if (b == true) {
			try {
				attachment.sendSSLMessage(sendToArray, subject, body,
						"anildhkd26@gmail.com");
			} catch (MessagingException e) {
				e.printStackTrace();
				i = 1;
			} finally {
				if (i == 0) {
					logger.info("i=0");
					service.updateEmailDetal(sdate, "NATIVE");
				} else {
					logger.info("i=1");
					service.updateEmailDetal(sdate, "FALSE");
				}
			}
		}
		// mailSender.send(mimeMessage);

	}*/

}
