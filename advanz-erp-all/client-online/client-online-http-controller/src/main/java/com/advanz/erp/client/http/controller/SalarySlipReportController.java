package com.advanz.erp.client.http.controller;

import java.io.InputStream;
import java.sql.Connection;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.SalaryMasterForm;
import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.msg.EmployeeOutputMessage;
import com.advanz.erp.masters.service.business.IEmployeeService;

@Controller
public class SalarySlipReportController {
  private static Logger logger=Logger.getLogger(SalarySlipReportController.class);
  
  @Autowired
  private IEmployeeService employeeService;
  
  @Autowired
  private DataSource dataSource;
  
  @RequestMapping(value="/salary_slip",method=RequestMethod.GET)
  public String createSalarySlip(Model model)
  {
   SalaryMasterForm salaryMasterForm=new SalaryMasterForm();
   	logger.debug("Received request to show download page");
	System.out.println("Salary Calculation Reprot....................");
	 EmployeeOutputMessage message=employeeService.findAllActivatedEmployee();
	 model.addAttribute("salaryEmployeeList",message.getEmployeeDTOList());
	model.addAttribute("salaryMasterForm", salaryMasterForm);
	return "salary_slip_report";
  }
  
  @RequestMapping(value="/salary_slip/xls",method=RequestMethod.GET)
  public ModelAndView salarySlipXls(ModelAndView modelAndView,@RequestParam("monthName") String monthName,
			@RequestParam("yearName") String yearName,HttpServletResponse response,HttpServletRequest request)
  	{
	  logger.debug("Received request to show download page Excel");
	  response.setHeader("filename", "Salary_Slip.xls");
	  Map<String, Object> parameterMap=new HashMap<String, Object>();
	  parameterMap.put("datasource",dataSource);
	  
	  if(!monthName.equals("All")){
	 	parameterMap.put("SingleMandatoryMonthPrompt", monthName);
		}
	  if(yearName!=null){
			parameterMap.put("YearPrompt", yearName);
		 }
	  String realreport = request.getSession().getServletContext()
				.getRealPath(System.getProperty("file.separator") + "WEB-INF"
						+ System.getProperty("file.separator")
						+ "subReports"+ System.getProperty("file.separator"));
	 
	 parameterMap.put("SUBREPORT_DIR", realreport);
		modelAndView = new ModelAndView("xlsSalarySlip", parameterMap);
		return modelAndView;
  	}

   @RequestMapping(value="/salary_slip/pdf",method=RequestMethod.GET)
   public ModelAndView salarySlipPdf(ModelAndView modelAndView,@RequestParam("monthName") String monthName,
			@RequestParam("yearName") String yearName,@RequestParam("empId") String empId,HttpServletResponse response,HttpServletRequest request)
		{
	   
	   	logger.debug("Received request to show download page PDF");
	   	 response.setHeader("filename", "Salary_Slip.pdf");
	   	 Map<String, Object> parameterMap=new HashMap<String, Object>();
	   	 parameterMap.put("datasource", dataSource);
	   	
	   	 if(!monthName.equals("All")){
	   		 parameterMap.put("SingleMandatoryMonthPrompt", monthName);
			}
		 if(yearName!=null){
			 parameterMap.put("YearPrompt", yearName);
			}
		 if(!empId.equals("All")){
			 parameterMap.put("Employee", empId);
			}
		 
			
		 String realPath = request.getSession().getServletContext()
					.getRealPath(System.getProperty("file.separator") + "WEB-INF"
									+ System.getProperty("file.separator")
									+ "images"+ System.getProperty("file.separator")
									+ "advanz_logo.JPG");
		 
		 
		 
		 String realreport = request.getSession().getServletContext()
					.getRealPath(System.getProperty("file.separator") + "WEB-INF"
							+ System.getProperty("file.separator")
							+ "subReports"+ System.getProperty("file.separator"));
		 
		 parameterMap.put("SUBREPORT_DIR", realreport);
		 
			parameterMap.put("Image_Loc", realPath.toString());
			
		 
		 modelAndView = new ModelAndView("pdfSalarySlip", parameterMap);
		 return modelAndView;
		}
   
   @RequestMapping(value="/salary_slip/csv",method=RequestMethod.GET)
   public ModelAndView salarySlipCsv(ModelAndView modelAndView,@RequestParam("monthName") String monthName,
			@RequestParam("yearName") String yearName,HttpServletResponse response,HttpServletRequest request)
		{
			logger.debug("Received request to show download page CSV");
	   		response.setHeader("filename", "Salary_Slip.csv");
	   		Map<String, Object> parameterMap=new HashMap<String, Object>();
	   		parameterMap.put("datasource", dataSource);
	   		if(!monthName.equals("All")){
		   		 parameterMap.put("SingleMandatoryMonthPrompt", monthName);
				}
			 if(yearName!=null){
				 parameterMap.put("YearPrompt", yearName);
				}
			 
			 String realreport = request.getSession().getServletContext()
						.getRealPath(System.getProperty("file.separator") + "WEB-INF"
								+ System.getProperty("file.separator")
								+ "subReports"+ System.getProperty("file.separator"));
			 
			 parameterMap.put("SUBREPORT_DIR", realreport);
			 
	   	 modelAndView = new ModelAndView("csvSalarySlip", parameterMap);
		 return modelAndView;
		}
   
   @RequestMapping(value = "/salary_slip/html", method = RequestMethod.POST)
   public @ResponseBody
	Map showHTMLReport(ModelAndView modelAndView,HttpServletRequest request,
		@RequestParam("monthName") String monthName,@RequestParam("yearName") String yearName) {
		logger.debug("Received request to download HTML report");
		System.out.println("Received request to download HTML report");
		Map result = new HashMap();
		try {
			Connection conn = dataSource.getConnection();
			// Get the jasper report object
			// Load it
			InputStream reportStream = this.getClass().getResourceAsStream(	"/reports/salary_slip.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);

			Map<String, Object> parameterMap = new HashMap<String, Object>();
			if(!monthName.equals("All")){
				parameterMap.put("SingleMandatoryMonthPrompt", monthName);
				}
			if(yearName!=null){
				 parameterMap.put("YearPrompt", yearName);
				 }
			 String realreport = request.getSession().getServletContext()
						.getRealPath(System.getProperty("file.separator") + "WEB-INF"
								+ System.getProperty("file.separator")
								+ "subReports"+ System.getProperty("file.separator"));
			 
			 parameterMap.put("SUBREPORT_DIR", realreport);
			 
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
			result.put("htmlSalarySlip", html);
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
   
    
  }
