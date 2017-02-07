package com.advanz.erp.client.http.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.InvoiceReportFrom;
import com.advanz.erp.masters.model.BillDTO;
import com.advanz.erp.masters.model.msg.BillInputMessage;
import com.advanz.erp.masters.model.msg.BillOutMessage;
import com.advanz.erp.masters.service.business.IBillService;
import com.advanz.erp.masters.service.business.IPartyService;


/**
 * Handles and retrieves download request
 */
@Controller
public class InvoiceReportController {

	protected static Logger logger = Logger.getLogger(InvoiceReportController.class);
	@Autowired
	DataSource dataSource;
	
	
	@Autowired
	IBillService billService;
	
	@Autowired
	IPartyService partyService;
	
	public static String ALL = "All";
	
	/**
	 * Handles and retrieves the reports page. The zones will be pre-populated with this page.
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/invoice_report", method = RequestMethod.GET)
    public String getDownloadPage(Model model) {
    	logger.debug("Received request to show download page");
    	
    	BillOutMessage billOutMessage = billService.findAllBills();
    	//ArrayList<BillDTO> billList =(ArrayList<BillDTO>)billOutMessage.getBillDTOList();
    	
    	
    	billOutMessage=	billService.getFinacialYear();
   	   List<String> list=	 billOutMessage.getList();
        ArrayList<BillDTO> billList =new ArrayList<BillDTO>();
   
   
   
   	if(list!=null && list.size()>0){
   		for(int i=0;i<list.size();i++){
   			BillDTO masterDTO = new BillDTO();
   			String fnyera =	list.get(i);
   	         masterDTO.setFinyearTransactionSeris("SCF/"+fnyera);
   	          billList.add(masterDTO);
   		   }
   	    }
    	
    	
    	
    	InvoiceReportFrom invoiceReportFrom = new InvoiceReportFrom();
    	model.addAttribute("invoiceReportFrom", invoiceReportFrom);
    	model.addAttribute("billList",billList);
    	return "invoice_report_view";
	}
   
    /**
     * Retrieves the download file in XLS format
     * 
     * @return
     */
    @RequestMapping(value = "/invoice_report/xls", method = RequestMethod.GET)
    public ModelAndView doSalesReportXLS(@RequestParam("invoiceNumber") String invoiceNumber ,@RequestParam("invoiceId") Integer invoiceId , ModelAndView modelAndView, HttpServletResponse response) 
		 {
		logger.debug("Received request to download Excel report");
		//response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename","invoice_report.xls");
		invoiceNumber=invoiceNumber+"/"+invoiceId;
		// In order to use Spring's built-in Jasper support, 
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("InvoiceNoPrompt", invoiceNumber);
         
		// xlsReport is the View of our application
		// This is declared inside the views xml file
		modelAndView = new ModelAndView("xlsInvoice", parameterMap);
		
		// Return the View and the Model combined
		return modelAndView;
	}
    
    /**
     * Retrieves the download file in XLS format
     * 
     * @return
     */
    @RequestMapping(value = "/invoice_report/pdf", method = RequestMethod.GET )
    public  ModelAndView doSalesReportPDF(@RequestParam("invoiceNumber") String invoiceNumber,@RequestParam("invoiceId") Integer invoiceId ,ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request) 
		 {
		logger.debug("Received request to download PDF report");
	    //response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename","invoice_report.pdf");
		
		response.setContentType("application/pdf");
		invoiceNumber=invoiceNumber+"/"+invoiceId;
		
		
		// In order to use Spring's built-in Jasper support, 
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("InvoiceNoPrompt", invoiceNumber);
		
		String realPath  = request.getSession().getServletContext().getRealPath(System.getProperty("file.separator")+"WEB-INF"+System.getProperty("file.separator")+"images"+System.getProperty("file.separator")+"shree_logo.JPG");
		parameterMap.put("Image_Loc", realPath.toString());
		
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("pdfInvoice", parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	   }
    
    @RequestMapping(value = "/delivery_chalan_report/pdf", method = RequestMethod.GET )
    public  ModelAndView doDeliveryChallanPDF(@RequestParam("invoiceNumber") String invoiceNumber,@RequestParam("invoiceId") Integer invoiceId ,ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request) 
		 {
		logger.debug("Received request to download PDF report");
	    //response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename","Delivery_challan_report.pdf");
		
		response.setContentType("application/pdf");
		invoiceNumber=invoiceNumber+"/"+invoiceId;
		BillInputMessage billInputMessage=new BillInputMessage();
		BillDTO billDTO=new BillDTO();
		billDTO.setInvoiceNumber(invoiceNumber);
		billInputMessage.setBillDTO(billDTO);
		BillOutMessage billOutMessage= billService.checkDuplicateInvoiceNum(billInputMessage);
		List<BillDTO> list= billOutMessage.getBillDTOList();
		if(list!=null && list.size()>0){
			billDTO=list.get(0);
		}
		String proformaNo= billDTO.getProformaNumber();
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("InvoiceNoPrompt", proformaNo);
		
		String realPath  = request.getSession().getServletContext().getRealPath(System.getProperty("file.separator")+"WEB-INF"+System.getProperty("file.separator")+"images"+System.getProperty("file.separator")+"shree_logo.JPG");
		parameterMap.put("Image_Loc", realPath.toString());
		
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("pdfDeliveryChallan", parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	   }
    /**
     * Retrieves the download file in XLS format 
     * 
     * @return
     */
    @RequestMapping(value = "/invoice_report/csv", method = RequestMethod.GET)
    public ModelAndView doSalesReportCSV(@RequestParam("invoiceNumber") String invoiceNumber ,ModelAndView modelAndView, HttpServletResponse response) 
		 {
		logger.debug("Received request to download CSV report");
		//response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename","invoice_report.csv");
		
		// In order to use Spring's built-in Jasper support, 
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("InvoiceNoPrompt", invoiceNumber);
       
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("csvGeographic", parameterMap);
		
		// Return the View and the Model combined
		return modelAndView;
	}
    
    /**
     * Retrieves HTML and show it in the screen
     * 
     * @return
     */
    @RequestMapping(value = "/invoice_report/html", method = RequestMethod.POST)
    public  @ResponseBody Map showHTMLReport(@RequestParam("invoiceNumber") String invoiceNumber,@RequestParam("invoiceId") Integer invoiceId , ModelAndView modelAndView) 
		 {
		logger.debug("Received request to download HTML report");
		//Map<String,Object> parameterMap = new HashMap<String,Object>();
		//parameterMap.put("datasource", dataSource);
		
		//modelAndView = new ModelAndView("csvGeographic", parameterMap);
	
		Map result = new HashMap();
		try
		{
			Connection conn = dataSource.getConnection();
	
	        // Get the jasper report object 
	        // Load it 
	        InputStream reportStream = this.getClass().getResourceAsStream("/reports/Excise_Invoice_report.jasper");
	        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);
	        
	        Map<String,Object> parameterMap = new HashMap<String,Object>();
	        invoiceNumber=invoiceNumber+"/"+invoiceId;
	        parameterMap.put("InvoiceNoPrompt", invoiceNumber);
	        // Populate report with data
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameterMap, conn);
	        // Create report exporter to be in Html
	        JRExporter exporter = new JRHtmlExporter();
	
	        // Create string buffer to store completed report
	        StringBuffer sb = new StringBuffer();
	
	        // Setup report, no header, no footer, no images for layout
	        exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
	        exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
	        exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
	
	        // When report is exported send to string buffer
	        exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sb);
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	
	        // Export the report, store to sb
	        exporter.exportReport();
	       
	        // Use Jsoup to clean the report table html to output to browser
	        Whitelist allowedHtml = new Whitelist();
	        allowedHtml.addTags("table", "tr", "td", "span");
	        allowedHtml.addTags("table", "style", "cellpadding", "cellspacing", "border", "bgcolor");
	        allowedHtml.addAttributes("tr", "valign");
	        allowedHtml.addAttributes("td", "colspan", "style");
	        allowedHtml.addAttributes("span", "style");
	        String html = Jsoup.clean(sb.toString(), allowedHtml);
	        // Add report to map
	        result.put("reportHtml", html);
	        conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   return result;
 }
   /* @RequestMapping(value = "/pdf_mail", method = RequestMethod.POST)
    public void mailSend(@RequestParam("invoiceNumber") String invoiceNumber,@RequestParam("invoiceId") Integer invoiceId,@RequestParam("mailIds") String mailIds, HttpServletResponse response, HttpServletRequest request) {
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
					+ reportName + ".pdf");
			
			    Map<String,Object> parameterMap = new HashMap<String,Object>();
		        invoiceNumber=invoiceNumber+"/"+invoiceId;
		        
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
			
			
			
			mailIds=mailIds.trim();
			 String []emailIds=mailIds.split(",");
			 
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
		//return result;
	} 
    
    @RequestMapping(value = "/getEmailId", method = RequestMethod.POST)
    public @ResponseBody JsonResponse getPartyEmailId(@RequestParam("invoiceNumber") String invoiceNumber,@RequestParam("invoiceId") Integer invoiceId,@RequestParam("flag") String flag){
    	JsonResponse res = new JsonResponse();
    	invoiceNumber=invoiceNumber+"/"+invoiceId;
    	String emailId=partyService.getEmailId(invoiceNumber, flag);
    	res.setResult(emailId);
    	return  res;
 }
    
    @RequestMapping(value = "/CheckValidInvoiceNo", method = RequestMethod.POST)
    public @ResponseBody JsonResponse getCheckValidInvoiceNo(@RequestParam("invoiceNumber") String invoiceNumber,@RequestParam("invoiceId") Integer invoiceId,@RequestParam("flag") String flag){
    	JsonResponse res = new JsonResponse();
    	invoiceNumber=invoiceNumber+"/"+invoiceId;
    	BillInputMessage billInputMessage=new BillInputMessage();
    	BillDTO billDTO=new BillDTO();
    	billDTO.setInvoiceNumber(invoiceNumber);
    	billInputMessage.setBillDTO(billDTO);
    	BillOutMessage billOutMessage= billService.checkDuplicateInvoiceNum(billInputMessage);
    	List<BillDTO> list= billOutMessage.getBillDTOList();
    	Boolean flage=false;
    	if(list!=null && list.size()>0){
    		flage=true;
    	}
    	res.setResult(flage);
    	return  res;
 }
    */
}