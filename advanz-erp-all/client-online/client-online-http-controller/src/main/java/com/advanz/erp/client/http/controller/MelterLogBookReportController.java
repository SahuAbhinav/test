package com.advanz.erp.client.http.controller;

import java.io.InputStream;
import java.sql.Connection;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.advanz.erp.client.http.controller.form.MenufacturingReportForm;
import com.advanz.erp.client.http.controller.form.Rg23APartIForm;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.model.msg.ItemGroupOutMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.service.business.IBranchService;
import com.advanz.erp.masters.service.business.IItemGroupService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;

@Controller
public class MelterLogBookReportController {
	protected static Logger logger = Logger.getLogger(RegionListReportController.class);
    @Autowired
    private IMastersService mastersService;
	@Autowired
	DataSource dataSource;
	
	@RequestMapping(value="melter_log_book_report",method=RequestMethod.GET) 
	public String showReportData(Model model){
		MastersInputMessage mastersInputMessage =new MastersInputMessage();
    	//formid=16   --> Item Grade
    	mastersInputMessage.setFormId(11);
    	MastersOutputMessage mastersOutputMessage=	mastersService.findFormById(mastersInputMessage);
        List<MastersDTO> masterList=mastersOutputMessage.getMastersDTOList();
        MenufacturingReportForm menufacturingReportForm=new MenufacturingReportForm();
        model.addAttribute("menufacturingReportForm", menufacturingReportForm);
		model.addAttribute("masterList",masterList);
        return "melter_log_book_report";
	}
	 @RequestMapping(value = "/melter_log_book_report/xls", method = RequestMethod.GET)
	    public ModelAndView doSalesReportXLS(@RequestParam("fromDate") Date fromDate,@RequestParam("shiftName") String shiftName,@RequestParam("runNo") String runNo,@RequestParam("runNoNullPrompt") String runNoNullPrompt,      
	    		String branchName,ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request) 
	    {
			logger.debug("Received request to download Excel report");
			//response.setHeader("Content-Disposition","attachment");
			response.setHeader("filename","melter_log_book_report.xls");
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			parameterMap.put("datasource", dataSource);
			
			if(shiftName!=null && !shiftName.equals("All")){
				parameterMap.put("Shift_Prompt", shiftName);
				}if(runNo!=null && !runNo.equals("All")){
				parameterMap.put("Run_No_Prompt", runNo);
				}if(runNoNullPrompt!=null && !runNoNullPrompt.equals("All")){
				parameterMap.put("Run_No_NULL_Prompt", runNoNullPrompt);
				}
			
			parameterMap.put("Date_Prompt", fromDate);
			modelAndView = new ModelAndView("xlsMelterLogBook", parameterMap);
			// Return the View and the Model combined
			return modelAndView;
		}
	    
	    /**
	     * Retrieves the download file in XLS format
	     * 
	     * @return
	     */
	    @RequestMapping(value = "/melter_log_book_report/pdf", method = RequestMethod.GET )
	    public  ModelAndView doSalesReportPDF(@RequestParam("fromDate") Date fromDate,@RequestParam("shiftName") String shiftName,@RequestParam("runNo") String runNo,@RequestParam("runNoNullPrompt") String runNoNullPrompt,
	    		ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request) 
			 {
	    
			logger.debug("Received request to download PDF report");
		    //response.setHeader("Content-Disposition","attachment");
			response.setHeader("filename","MelterLogBook.pdf");
			
			response.setContentType("application/pdf");
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			parameterMap.put("datasource", dataSource);
			if(shiftName!=null && !shiftName.equals("All")){
				parameterMap.put("Shift_Prompt", shiftName);
				}if(runNo!=null && !runNo.equals("All")){
				parameterMap.put("Run_No_Prompt", runNo);
				}if(runNoNullPrompt!=null && !runNoNullPrompt.equals("All")){
				parameterMap.put("Run_No_NULL_Prompt", runNoNullPrompt);
				}
			parameterMap.put("Date_Prompt", fromDate);
	
			String realPath  = request.getSession().getServletContext().getRealPath(System.getProperty("file.separator")+"WEB-INF"+System.getProperty("file.separator")+"images"+System.getProperty("file.separator")+"shree_logo.JPG");
			parameterMap.put("Image_Loc", realPath.toString());
			modelAndView = new ModelAndView("pdfMelterLogBook", parameterMap);
			return modelAndView;
		   }
	    
	    @RequestMapping(value = "/melter_log_book_report/csv", method = RequestMethod.GET)
	    public ModelAndView doSalesReportCSV(@RequestParam("fromDate") Date fromDate,@RequestParam("shiftName") String shiftName,@RequestParam("runNo") String runNo,@RequestParam("runNoNullPrompt") String runNoNullPrompt
	    		,ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request) 
			 {
			logger.debug("Received request to download CSV report");
			//response.setHeader("Content-Disposition","attachment");
			response.setHeader("filename","MelterLogBook.csv");
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			parameterMap.put("datasource", dataSource);
			if(shiftName!=null && !shiftName.equals("All")){
				parameterMap.put("Shift_Prompt", shiftName);
				}if(runNo!=null && !runNo.equals("All")){
				parameterMap.put("Run_No_Prompt", runNo);
				}if(runNoNullPrompt!=null && !runNoNullPrompt.equals("All")){
				parameterMap.put("Run_No_NULL_Prompt", runNoNullPrompt);
				}
			parameterMap.put("Date_Prompt", fromDate);
			// pdfReport is the View of our application
			// This is declared inside the /WEB-INF/jasper-views.xml
			modelAndView = new ModelAndView("csvMelterLogBook", parameterMap);
			
			// Return the View and the Model combined
			return modelAndView;
		}
	    
	    /**
	     * Retrieves HTML and show it in the screen
	     * 
	     * @return
	     */
	    @RequestMapping(value = "/melter_log_book_report/html", method = RequestMethod.POST)
	    public  @ResponseBody Map showHTMLReport(@RequestParam("fromDate") Date fromDate,@RequestParam("shiftName") String shiftName,@RequestParam("runNo") String runNo,@RequestParam("runNoNullPrompt") String runNoNullPrompt
	    		,ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request) 
			 {
	    	
	    	logger.debug("Received request to download HTML report");
			Map result = new HashMap();
			try
			{
				Connection conn = dataSource.getConnection();
		        // Get the jasper report object 
		        InputStream reportStream = this.getClass().getResourceAsStream("/reports/Melter_logbook.jasper");
		        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);
		        
		        Map<String,Object> parameterMap = new HashMap<String,Object>();
		      
		        parameterMap.put("datasource", dataSource);
		        if(shiftName!=null && !shiftName.equals("All")){
					parameterMap.put("Shift_Prompt", shiftName);
					}if(runNo!=null && !runNo.equals("All")){
					parameterMap.put("Run_No_Prompt", runNo);
					}if(runNoNullPrompt!=null && !runNoNullPrompt.equals("All")){
					parameterMap.put("Run_No_NULL_Prompt", runNoNullPrompt);
					}
		        parameterMap.put("Date_Prompt", fromDate);
			
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
