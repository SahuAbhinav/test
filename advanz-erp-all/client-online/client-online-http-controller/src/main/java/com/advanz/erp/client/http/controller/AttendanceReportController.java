package com.advanz.erp.client.http.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
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

import com.advanz.erp.client.http.controller.form.SalaryMasterForm;
import com.advanz.erp.masters.model.SalaryMasterDTO;

@Controller
public class AttendanceReportController {
	protected static Logger logger = Logger.getLogger(MastersListReportController.class);
	@Autowired
	DataSource dataSource;
	
	@RequestMapping(value = "/attendance_report", method = RequestMethod.GET)
	public String getSalary(Model model)
	{
		SalaryMasterForm salaryMasterForm=new SalaryMasterForm();
		
		logger.debug("Received request to show download page");
		System.out.println("Salary Calculation Reprot....................");
		model.addAttribute("salaryMasterForm", salaryMasterForm);
		return "attendance_report";
	}
	
	
	@RequestMapping(value = "/attendance_report/xls", method = RequestMethod.GET)
	public ModelAndView getSalaryXML(ModelAndView modelAndView,
			@RequestParam("monthName") String monthName,@RequestParam("yearName") String yearName,
			HttpServletResponse response)
	{
		logger.debug("Received request to download Excel report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "Attendance_Calculation.xls");
		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);
		if(!monthName.equals("All")){
		parameterMap.put("SingleMandatoryMonthPrompt", monthName);
		}
		if(yearName!=null){
			parameterMap.put("YearPrompt", yearName);
		 }
		// xlsReport is the View of our application
		// This is declared inside the views xml file
		modelAndView = new ModelAndView("xlsAttendance", parameterMap);

		// Return the View and the Model combined
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/attendance_report/pdf", method = RequestMethod.GET)
	public ModelAndView doSalesReportPDF(ModelAndView modelAndView,
			@RequestParam("monthName") String monthName,@RequestParam("yearName") String yearName,
			HttpServletResponse response, HttpServletRequest request) {
		logger.debug("Received request to download PDF report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "Attendance_Calculation.pdf");

		response.setContentType("application/pdf");
		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);
		
		if(!monthName.equals("All")){
			parameterMap.put("SingleMandatoryMonthPrompt", monthName);
			}
			if(yearName!=null){
				parameterMap.put("YearPrompt", yearName);
			 }
	
		String realPath = request.getSession().getServletContext()
				.getRealPath(System.getProperty("file.separator") + "WEB-INF"
								+ System.getProperty("file.separator")+ "images"
								+ System.getProperty("file.separator")+ "shree_logo.JPG");
		parameterMap.put("Image_Loc", realPath.toString());

		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("pdfAttendance", parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/attendance_report/csv", method = RequestMethod.GET)
	public ModelAndView doSalesReportCSV(ModelAndView modelAndView,HttpServletResponse response,
			@RequestParam("monthName") String monthName,@RequestParam("yearName") String yearName) {
		logger.debug("Received request to download CSV report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "Attendance_Calculation.csv");

		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		if(!monthName.equals("All")){
			parameterMap.put("SingleMandatoryMonthPrompt", monthName);
			}
		if(yearName!=null){
				parameterMap.put("YearPrompt", yearName);
			 }
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("csvAttendance", parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}
	
	@RequestMapping(value = "/attendance_report/html", method = RequestMethod.POST)
	public @ResponseBody
	Map showHTMLReport(ModelAndView modelAndView,@RequestParam("monthName") String monthName,@RequestParam("yearName") String yearName) {
		logger.debug("Received request to download HTML report");
		Map result = new HashMap();
		try {
			Connection conn = dataSource.getConnection();
			// Get the jasper report object
			// Load it
			InputStream reportStream = this.getClass().getResourceAsStream("/reports/attendance_summary.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);

			Map<String, Object> parameterMap = new HashMap<String, Object>();
			if(!monthName.equals("All")){
				parameterMap.put("SingleMandatoryMonthPrompt", monthName);
				}
			if(yearName!=null){
					parameterMap.put("YearPrompt", yearName);
				 }
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, conn);
			// Create report exporter to be in Html
			JRExporter exporter = new JRHtmlExporter();

			// Create string buffer to store completed report
			StringBuffer sb = new StringBuffer();

			// Setup report, no header, no footer, no images for layout
			exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
			exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
			exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,Boolean.FALSE);

			// When report is exported send to string buffer
			exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sb);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

			// Export the report, store to sb
			exporter.exportReport();

			// Use Jsoup to clean the report table html to output to browser
			Whitelist allowedHtml = new Whitelist();
			allowedHtml.addTags("table", "tr", "td", "span");
			allowedHtml.addTags("table", "style", "cellpadding", "cellspacing","border", "bgcolor");
			allowedHtml.addAttributes("tr", "valign");
			allowedHtml.addAttributes("td", "colspan", "style");
			allowedHtml.addAttributes("span", "style");
			String html = Jsoup.clean(sb.toString(), allowedHtml);
			// Add report to map
			result.put("htmlAttendance", html);
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
