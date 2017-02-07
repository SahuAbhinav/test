package com.advanz.erp.client.http.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
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
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.advanz.erp.client.http.controller.form.BlanketProductionMasterForm;
import com.advanz.erp.client.http.controller.form.Rg23APartIForm;
import com.advanz.erp.masters.model.BlanketProductionDetailLeftDTO;
import com.advanz.erp.masters.model.BlanketProductionDetailRightDTO;
import com.advanz.erp.masters.model.BlanketProductionMasterDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterInputMessage;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterOutputMessage;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.model.msg.ItemGroupOutMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.service.business.IBlanketProductionMasterService;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.service.business.IItemGroupService;
import com.advanz.erp.masters.service.business.IItemService;

@Controller
public class AnnealingOverReportController {
	protected static Logger logger = Logger.getLogger(RegionListReportController.class);
	@Autowired
	IItemService iItemService;
	
	@Autowired
	IBranchService iBranchService;
	@Autowired
	DataSource dataSource;
	  @Autowired
	    private IBlanketProductionMasterService blanketProductionMasterService;
	    
	@RequestMapping(value="annealing_over_report",method=RequestMethod.GET) 
	public String showReportData(Model model){
		
		return "annealing_over_report";
	}

	 @RequestMapping(value = "/annealing_over_report/xls", method = RequestMethod.GET)
	    public ModelAndView doSalesReportXLS(@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,
	    		String branchName,ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request) 
	    {
			logger.debug("Received request to download Excel report");
			//response.setHeader("Content-Disposition","attachment");
			response.setHeader("filename","blanket_production_report.xls");
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			parameterMap.put("datasource", dataSource);
			
			parameterMap.put("FromDatePrompt", fromDate);
			parameterMap.put("ToDatePrompt", toDate);
			modelAndView = new ModelAndView("xlsAnnealingOver", parameterMap);
			// Return the View and the Model combined
			return modelAndView;
		}
	    
	    /**
	     * Retrieves the download file in XLS format
	     * 
	     * @return
	     */
	    @RequestMapping(value = "/annealing_over_report/pdf", method = RequestMethod.GET )
	    public  ModelAndView doSalesReportPDF(@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,
	    		ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request) 
			 {
	    
			logger.debug("Received request to download PDF report");
		    //response.setHeader("Content-Disposition","attachment");
			response.setHeader("filename","item_stock_report.pdf");
			
			response.setContentType("application/pdf");
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			parameterMap.put("datasource", dataSource);
		
			parameterMap.put("FromDatePrompt", fromDate);
			parameterMap.put("ToDatePrompt", toDate);
	
			String realPath  = request.getSession().getServletContext().getRealPath(System.getProperty("file.separator")+"WEB-INF"+System.getProperty("file.separator")+"images"+System.getProperty("file.separator")+"shree_logo.JPG");
			parameterMap.put("Image_Loc", realPath.toString());
			modelAndView = new ModelAndView("pdfAnnealingOver", parameterMap);
			return modelAndView;
		   }
	    
	    @RequestMapping(value = "/annealing_over_report/csv", method = RequestMethod.GET)
	    public ModelAndView doSalesReportCSV(@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate
	    		,ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request) 
			 {
			logger.debug("Received request to download CSV report");
			//response.setHeader("Content-Disposition","attachment");
			response.setHeader("filename","item_stock_report.csv");
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			parameterMap.put("datasource", dataSource);
			parameterMap.put("FromDatePrompt", fromDate);
			parameterMap.put("ToDatePrompt", toDate);
			// pdfReport is the View of our application
			// This is declared inside the /WEB-INF/jasper-views.xml
			modelAndView = new ModelAndView("csvAnnealingOver", parameterMap);
			
			// Return the View and the Model combined
			return modelAndView;
		}
	    
	    /**
	     * Retrieves HTML and show it in the screen
	     * 
	     * @return
	     */
	    @RequestMapping(value = "/annealing_over_report/html", method = RequestMethod.POST)
	    public  @ResponseBody Map showHTMLReport(@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate
	    		,ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request) 
			 {
	    	System.out.println("fromDate.........."+fromDate+"toDate............"+toDate);
	    	logger.debug("Received request to download HTML report");
			Map result = new HashMap();
			try
			{
				Connection conn = dataSource.getConnection();
		        // Get the jasper report object 
		        InputStream reportStream = this.getClass().getResourceAsStream("/reports/Annealing_Oven_Detail_Reports.jasper");
		        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);
		        Map<String,Object> parameterMap = new HashMap<String,Object>();
		        parameterMap.put("datasource", dataSource);
		        parameterMap.put("FromDatePrompt", fromDate);
				parameterMap.put("ToDatePrompt", toDate);
			
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
