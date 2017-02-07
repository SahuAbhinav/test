package com.advanz.erp.masters.service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.mail.MessagingException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.advanz.erp.masters.service.business.IBillService;
import com.advanz.erp.masters.service.business.ICompanyService;
import com.advanz.erp.masters.service.business.IFinishedGoodsMasterService;
import com.advanz.erp.masters.service.business.IGrnMasterService;
import com.advanz.erp.masters.service.business.ISalesOrderMasterService;
import com.advanz.erp.masters.service.business.IStockLedgerService;

public class EmailService implements Job {
	private JavaMailSenderImpl mailSender = null;

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
	
	@Autowired
	public ICompanyService companyService;
	
	private static final Logger logger = LoggerFactory
	.getLogger(DispatchMasterServiceImpl.class);
	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public void sendEmail() throws MessagingException {
		companyService.sendEmail(null,null);
		
		/*//1 Create Body for Invoice alert (Alert 1)
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		// Date startDate= c.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(c.getTime());
		String endDate = sdf.format(new Date());
		ArrayList<List> arrList =(ArrayList<List>) billService.getCountInvoiceToEmail(startDate, endDate);
		List list=arrList.get(0);
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

		//2 Create Body for Sales Order alert(Alert 2)
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

		//3 Create Body for Finish Good alert(Alert 3)
	List finishGoodList=	finishedGoodsMasterService.getFinishGoodInfoToEmail(endDate);
	Object[] finishGoodObjects = (Object[])	finishGoodList.get(0);
	BigInteger totalFinishedGood=(BigInteger)finishGoodObjects[0];
	Double totalFinishedGoodsStock1=(Double)finishGoodObjects[1];
	int totalFinishedGoodsStock=0;
	if(totalFinishedGoodsStock1!=null){
		totalFinishedGoodsStock=totalFinishedGoodsStock1.intValue();
	}
	Double totalPengingSalesOrderAmnt1=(Double)finishGoodObjects[2];
	int totalPengingSalesOrderAmnt=0;
	if(totalPengingSalesOrderAmnt1!=null){
		totalPengingSalesOrderAmnt=totalPengingSalesOrderAmnt1.intValue();
	}
	Double totalPendingProductionAmnt1=(Double)finishGoodObjects[3];
	int totalPendingProductionAmnt=0;
	if(totalPendingProductionAmnt1!=null){
		totalPendingProductionAmnt=totalPendingProductionAmnt1.intValue();
	}
	
	// Finish Good Email Body End

		//5 Create Body for grn(Alert 5)
		List grnList =	grnMasterService.getEmailAlertData(endDate);
		Object[] grnObjects = (Object[]) grnList.get(0);
	    BigInteger totalGrn=(BigInteger)grnObjects[0];
	    BigInteger totalGrnUpTo=(BigInteger)grnObjects[1];
	    BigInteger totalPendingGrn=(BigInteger)grnObjects[2];
		//Grn Body is complete
		
		
		//4 Create Body Message for Minimum Inventry(Alert 4)
	    List milList = service.ItemCountDateWise(endDate);
	    Object[] milObjects = (Object[]) milList.get(0);
	    BigInteger totalItem=(BigInteger)milObjects[0];
	    BigInteger totalPendingItem=(BigInteger)milObjects[1];
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		Date date = new Date();
		String month = new SimpleDateFormat("MMM").format(date);
		String formatedDate = cal.get(Calendar.DATE) + " " + month + " "
				+ cal.get(Calendar.YEAR);

		//6 Create body for excise alert(excise amount,cess and h.cess amount)(alert6)
		List list6 =arrList.get(1);
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
		
		
		
		//End alert body
		 String[] sendToArray = new String[4];
		 sendToArray[0]="amit@shreecera.in";
		 sendToArray[1]="erp101system@gmail.com";
		 
		 sendToArray[2]="sales@shreecera.co.in";
		 sendToArray[3]="stores@shreecera.com";
		 
		 
		String[] sendToArray = new String[1];
		//sendToArray[0]="anildhakad26@yahoo.in";
		sendToArray[0] ="anildhkd26@gmail.com";
	 

		MailWithAttachment attachment = new MailWithAttachment();
		String body = "Dear Sir," + "\n" + "\n"
		        +"Please find below few key informations, auto generated from ERP system on "+ formatedDate
		        + "\n" + "\n"
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
				+ total15DaysPendingItemValue+"/-"
				+ "\n"
				+ "\n"
				+"3) Finished Goods - On "+formatedDate+", "+ totalFinishedGood+" entries were received in Finished Goods, now total stock of finished goods is  Rs "+totalFinishedGoodsStock+"/- out of this, Rs "+totalPengingSalesOrderAmnt+"/- is booked in various sales orders. and Rs "+totalPendingProductionAmnt+"/- is pending for production."
				+ "\n"
				+ "\n"
				+ "4)MIL - On "+formatedDate+", "+totalItem+" items  are below MIL. Out of these, PO are pending for "+totalPendingItem+" items."
				+ "\n"
				+ "\n"
				+ "5)GRN - On "+ formatedDate+", total  "+totalGrn+"  GRN  were made, as on date, "+totalGrnUpTo+" GRN  are pending for approval, out of which, "+totalPendingGrn+" are pending since more than 4  days."
				+ "\n"
				+ "\n"
				+ "6)On "+ formatedDate+", for current month, exsice total as per invoices are Excise total Rs."+totalExcise+"/- ,Cess Rs."+totalCess+"/- and Heiger Eduction Cess Rs."+totalHcess+"/-"
				+ "\n"
				+ "\n"
				+ "Thanks & Regards"
				+ "\n"
				+ "ERP TEAM"
				+ "\n"
				+ "\n"
				+ "***This is an auto generated email. Please don't reply on this email id.Please contact your system administrator for any query.";
		
		String subject="Summary Alerts from Shree Cera ERP dated "+formatedDate;
		
		
		Boolean b= companyService.getEmailFlag();
		if(b==true){
		attachment.sendSSLMessage(sendToArray, subject, body,"anildhkd26@gmail.com");
		}
		// mailSender.send(mimeMessage);
		
		service.createEmailDetal(body, new Date());
*/
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

	}

	public static void main(String[] args) throws Exception {
		/*Calendar c = Calendar.getInstance(); // this takes current date
		c.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println(c.getTime());

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf.format(cal.getTime());
		System.out.println("Current date in String Format: " + strDate);

		SimpleDateFormat sdf1 = new SimpleDateFormat();
		sdf1.applyPattern("dd/MM/yyyy HH:mm:ss.SS");
		Date date = sdf1.parse(strDate);
		System.out.println("Current date in Date Format: " + date);*/
	
		
	
	}
	
	}