package com.advanz.erp.client.http.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
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

import com.advanz.erp.client.http.controller.form.ProformaReportFrom;
import com.advanz.erp.masters.model.ModuleMenuMasterDTO;
import com.advanz.erp.masters.model.ProformaMasterDTO;
import com.advanz.erp.masters.model.msg.ProformaMasterInputMessage;
import com.advanz.erp.masters.model.msg.ProformaMasterOutMessage;
import com.advanz.erp.masters.service.business.IProformaMasterService;


/**
 * Handles and retrieves download request
 */
@Controller
public class ProformaReportController {

	protected static Logger logger = Logger.getLogger(ProformaReportController.class);
	@Autowired
	DataSource dataSource;
	
	
	@Autowired
	IProformaMasterService proformaMasterService;
	
	public static String ALL = "All";
	
	/**
	 * Handles and retrieves the reports page. The zones will be pre-populated with this page.
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/proforma_report", method = RequestMethod.GET)
    public String getDownloadPage(Model model) {
    	logger.debug("Received request to show download page");
    	
    	
    	
    	ProformaMasterOutMessage proformaMasterOutMessage = proformaMasterService.findAllBills();
    	//ArrayList<ProformaMasterDTO> proformaList =(ArrayList<ProformaMasterDTO>)proformaMasterOutMessage.getProformaMasterDTOList();
    	
    	 proformaMasterOutMessage=	proformaMasterService.getFinacialYear();
    	 List<String> list=	 proformaMasterOutMessage.getList();
         ArrayList<ProformaMasterDTO> proformaList =new ArrayList<ProformaMasterDTO>();
    
    
    
    	if(list!=null && list.size()>0){
    		for(int i=0;i<list.size();i++){
    			ProformaMasterDTO masterDTO = new ProformaMasterDTO();
    			String fnyera =	list.get(i);
    	         masterDTO.setFinyearTransactionSeris("PI/"+fnyera);
    	         proformaList.add(masterDTO);
    		   }
    	    }
    	
    	
    	//ModelAndView mav = new ModelAndView();
    	//mav.addObject("billList",billList);
    	ProformaReportFrom proformaReportFrom = new ProformaReportFrom();
    	model.addAttribute("proformaReportFrom", proformaReportFrom);
    	model.addAttribute("proformaList",proformaList);
    	return "proforma_report_view";
	}
   
    /**
     * Retrieves the download file in XLS format
     * 
     * @return
     */
    
    
    @RequestMapping(value = "/checkData", method = RequestMethod.GET)
  public Boolean checkData(@RequestParam("invoiceNumber") String invoiceNumber,@RequestParam("proformaId") Integer proformaId , ModelAndView modelAndView, HttpServletResponse response) 
 {
		String invoiceNum=invoiceNumber+"/"+proformaId;
		ProformaMasterInputMessage inputMessage = new ProformaMasterInputMessage();
		ProformaMasterDTO masterDTO = new ProformaMasterDTO();
		masterDTO.setInvoiceNumber(invoiceNum);
		inputMessage.setProformaMasterDTO(masterDTO);
		
	ProformaMasterOutMessage masterOutMessage=	proformaMasterService.findBillByInvoiceNo(inputMessage);
    ArrayList<ProformaMasterDTO> proformaList=(ArrayList<ProformaMasterDTO>)masterOutMessage.getProformaMasterDTOList();	
	if(proformaList!=null){
		  return true;
	}
      return false;
	}
    
    
    @RequestMapping(value = "/proforma_report/xls", method = RequestMethod.GET)
    public ModelAndView doSalesReportXLS(@RequestParam("invoiceNumber") String invoiceNumber,@RequestParam("proformaId") Integer proformaId , ModelAndView modelAndView, HttpServletResponse response) 
		 {
		logger.debug("Received request to download Excel report");
		//response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename","proforma_report.xls");
		invoiceNumber=invoiceNumber+"/"+proformaId;
		// In order to use Spring's built-in Jasper support, 
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("InvoiceNoPrompt", invoiceNumber);
         
		// xlsReport is the View of our application
		// This is declared inside the views xml file
		modelAndView = new ModelAndView("xlsProforma", parameterMap);
		
		// Return the View and the Model combined
		return modelAndView;
	}
    
    /**
     * Retrieves the download file in XLS format
     * 
     * @return
     */
    @RequestMapping(value = "/proforma_report/pdf", method = RequestMethod.GET )
    public  ModelAndView doSalesReportPDF(@RequestParam("invoiceNumber") String invoiceNumber ,@RequestParam(value = "proformaId", required = false) Integer proformaId,ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request) 
		 {
		logger.debug("Received request to download PDF report");
		response.setHeader("filename","proforma_report.pdf");
		
		response.setContentType("application/pdf");
        //response.setHeader("Content-Disposition", "attachment:filename=_blank‌​");
        if(proformaId!=null && proformaId>0){
        	invoiceNumber=invoiceNumber+"/"+proformaId;	
        	
        }
        
		
		
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("InvoiceNoPrompt", invoiceNumber);
		
		String realPath  = request.getSession().getServletContext().getRealPath(System.getProperty("file.separator")+"WEB-INF"+System.getProperty("file.separator")+"images"+System.getProperty("file.separator")+"shree_logo.JPG");
		parameterMap.put("Image_Loc", realPath.toString());
		/*try {
			is = new FileInputStream(realPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("pdfProforma", parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	   }
    
    /**
     * Retrieves the download file in XLS format 
     * 
     * @return
     */
    @RequestMapping(value = "/proforma_report/csv", method = RequestMethod.GET)
    public ModelAndView doSalesReportCSV(@RequestParam("invoiceNumber") String invoiceNumber ,ModelAndView modelAndView, HttpServletResponse response) 
		 {
		logger.debug("Received request to download CSV report");
		//response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename","proforma_report.csv");
		
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
    @RequestMapping(value = "/proforma_report/html", method = RequestMethod.POST)
    public  @ResponseBody Map showHTMLReport(@RequestParam("invoiceNumber") String invoiceNumber ,@RequestParam("proformaId") Integer proformaId, ModelAndView modelAndView) 
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
	        InputStream reportStream = this.getClass().getResourceAsStream("/reports/Proforma_Invoice_report.jasper");
	        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);
	        
	        Map<String,Object> parameterMap = new HashMap<String,Object>();
	       /* parameterMap.put("ZoneNamePrompt", map.get("zone"));
	        parameterMap.put("StateNamePrompt", map.get("state"));
	        parameterMap.put("RegionNamePrompt", map.get("region"));
	        parameterMap.put("AreaNamePrompt", map.get("area"));*/
	        invoiceNumber=invoiceNumber+"/"+proformaId;
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
}
