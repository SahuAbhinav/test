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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.EmployeeListReportForm;
import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.msg.EmployeeOutputMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.service.business.IEmployeeService;
import com.advanz.erp.masters.service.business.IMastersService;

/**
 * Handles and retrieves download request
 */
@Controller
public class EmployeeListReportController {

	protected static Logger logger = Logger
			.getLogger(EmployeeListReportController.class);
	@Autowired
	DataSource dataSource;

	@Autowired
	IEmployeeService employeeService;
	
	@Autowired
	IMastersService iMastersService;


	public static String ALL = "All";
    
	 
	/**
	 * Handles and retrieves the reports page. The zones will be pre-populated
	 * with this page.
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/employee_list_report", method = RequestMethod.GET)
	public String getDownloadPage(Model model) {
		logger.debug("Received request to show download page");
		EmployeeListReportForm employeeListReportForm=new EmployeeListReportForm();
		EmployeeOutputMessage employeeOutMessage = employeeService.findAllEmployee();

		ArrayList<EmployeeDTO> employeeList = (ArrayList<EmployeeDTO>) employeeOutMessage.getEmployeeDTOList();

		List<MastersDTO> shiftList = new ArrayList<MastersDTO>();
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(11);
		MastersOutputMessage mastersOutputMessage = iMastersService.findFormById(mastersInputMessage);
		shiftList= mastersOutputMessage.getMastersDTOList();
		
		
		List<MastersDTO> deptList = new ArrayList<MastersDTO>();
		MastersInputMessage mastersInputMessage1 = new MastersInputMessage();
		mastersInputMessage1.setFormId(8);
		MastersOutputMessage mastersOutputMessage1 = iMastersService.findFormById(mastersInputMessage1);
		deptList=mastersOutputMessage1.getMastersDTOList();
		// ModelAndView mav = new ModelAndView();
		// mav.addObject("billList",billList);
		model.addAttribute("deptList", deptList);
		model.addAttribute("shiftList", shiftList);
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("employeeListReportForm", employeeListReportForm);
		
		return "employee_list_report_view";
	}

	
	@RequestMapping(value = "/employee_list_report/xls", method = RequestMethod.GET)
	public ModelAndView doSalesReportXLS(ModelAndView modelAndView,@RequestParam("Dept") String Dept,
			@RequestParam("Shift") String Shift,@RequestParam("Status") String Status,
			HttpServletResponse response) {
		logger.debug("Received request to download Excel report");
		response.setContentType("application/msword");
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);
		if(Dept!=null && !Dept.equals("All")){
			parameterMap.put("Dept", Dept);
			}
		if(Shift!=null && !Shift.equals("All")){
			parameterMap.put("Shift", Shift);
			}
		if(Status!=null && !Status.equals("All")){
			parameterMap.put("Status", Status);
			}
		// xlsReport is the View of our application
		// This is declared inside the views xml file
		modelAndView = new ModelAndView("xlsEmployee", parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/employee_list_report/pdf", method = RequestMethod.GET)
	public ModelAndView doSalesReportPDF(ModelAndView modelAndView,@RequestParam("Dept") String Dept,
			@RequestParam("Shift") String Shift,@RequestParam("Status") String Status,
			HttpServletResponse response, HttpServletRequest request) {
		logger.debug("Received request to download PDF report");
	
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);
		String realPath = request
				.getSession()
				.getServletContext()
				.getRealPath(
						System.getProperty("file.separator") + "WEB-INF"
								+ System.getProperty("file.separator")
								+ "images"
								+ System.getProperty("file.separator")
								+ "shree_logo.JPG");
		parameterMap.put("Image_Loc", realPath.toString());
		if(Dept!=null && !Dept.equals("All")){
			parameterMap.put("Dept", Dept);
			}
		if(Shift!=null && !Shift.equals("All")){
			parameterMap.put("Shift", Shift);
			}
		if(Status!=null && !Status.equals("All")){
			parameterMap.put("Status", Status);
			}
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("pdfEmployee", parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/employee_list_report/csv", method = RequestMethod.GET)
	public ModelAndView doSalesReportCSV(ModelAndView modelAndView,@RequestParam("Dept") String Dept,
			@RequestParam("Shift") String Shift,@RequestParam("Status") String Status,HttpServletResponse response) {
		logger.debug("Received request to download CSV report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "employee_list_report.csv");

		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		if(Dept!=null && !Dept.equals("All")){
			parameterMap.put("Dept", Dept);
			}
		if(Shift!=null && !Shift.equals("All")){
			parameterMap.put("Shift", Shift);
			}
		if(Status!=null && !Status.equals("All")){
			parameterMap.put("Status", Status);
			}
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("csvEmployee", parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves HTML and show it in the screen
	 * 
	 * @return
	 */
	@RequestMapping(value = "/employee_list_report/html", method = RequestMethod.POST)
	public @ResponseBody
	Map showHTMLReport(@RequestBody Map map,ModelAndView modelAndView) {
		logger.debug("Received request to download HTML report");
		
		Map result = new HashMap();
		try {
			Connection conn = dataSource.getConnection();

			// Get the jasper report object
			// Load it
		
			InputStream reportStream = this.getClass().getResourceAsStream(
					"/reports/Employee_List.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(reportStream);

			Map<String, Object> parameterMap = new HashMap<String, Object>();
		
			if( map.get("Dept")!=null && ! map.get("Dept").equals("All")){
	        	parameterMap.put("Dept", map.get("Dept"));
	    		}
			if( map.get("Shift")!=null && ! map.get("Shift").equals("All")){
	        	parameterMap.put("Shift", map.get("Shift"));
	    		}
			if( map.get("Status")!=null && ! map.get("Status").equals("All")){
	        	parameterMap.put("Status", map.get("Status"));
	    		}
			/*
			if(Dept!=null && !Dept.equals("All")){
				parameterMap.put("Dept", Dept);
				}
			if(Shift!=null && !Shift.equals("All")){
				parameterMap.put("Shift", Shift);
				}
			if(Status!=null && !Status.equals("All")){
				parameterMap.put("Status", Status);
				}
*/
			// Populate report with data
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameterMap, conn);
			// Create report exporter to be in Html
			JRExporter exporter = new JRHtmlExporter();

			// Create string buffer to store completed report
			StringBuffer sb = new StringBuffer();

			// Setup report, no header, no footer, no images for layout
			exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
			exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
			exporter.setParameter(
					JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
					Boolean.FALSE);

			// When report is exported send to string buffer
			exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sb);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

			// Export the report, store to sb
			exporter.exportReport();

			// Use Jsoup to clean the report table html to output to browser
			Whitelist allowedHtml = new Whitelist();
			allowedHtml.addTags("table", "tr", "td", "span");
			allowedHtml.addTags("table", "style", "cellpadding", "cellspacing",
					"border", "bgcolor");
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