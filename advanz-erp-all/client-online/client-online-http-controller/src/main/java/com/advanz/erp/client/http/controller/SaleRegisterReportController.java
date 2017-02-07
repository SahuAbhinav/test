package com.advanz.erp.client.http.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
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

import com.advanz.erp.client.http.controller.form.SalesReportForm;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.msg.PartyOutMessage;
import com.advanz.erp.masters.service.business.IAreaService;
import com.advanz.erp.masters.service.business.IPartyService;

/**
 * Handles and retrieves download request
 */
@Controller
public class SaleRegisterReportController {

	protected static Logger logger = Logger
			.getLogger(SaleRegisterReportController.class);
	@Autowired
	DataSource dataSource;

	@Autowired
	IAreaService areaService;

	@Autowired 
	IPartyService partyService;
	
	public static String ALL = "All";

	/**
	 * Handles and retrieves the reports page. The zones will be pre-populated
	 * with this page.
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/sales_register_report", method = RequestMethod.GET)
	public String getDownloadPage(Model model) {
		logger.debug("Received request to show download page");

		PartyOutMessage partyOutMessage= partyService.findAllPartys();
		ArrayList<PartyDTO> partyList=(ArrayList<PartyDTO>)partyOutMessage.getPartyDTOList();
		model.addAttribute("partyList", partyList);
		SalesReportForm salesReportForm = new SalesReportForm();
		model.addAttribute("salesReportForm",salesReportForm);
		
	return "sales_register_report_view";
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sales_register_report/xls", method = RequestMethod.GET)
	public ModelAndView doSalesReportXLS(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("partyName") String partyName,
			HttpServletResponse response) {
		logger.debug("Received request to download Excel report");
		
		response.setHeader("filename", "sales_register_report.xls");
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("FromDatePrompt", fromDate);
		parameterMap.put("ToDatePrompt", toDate);
		if(partyName!=null && !partyName.equals("All")){
			parameterMap.put("PartyNamePrompt", partyName);
		}
		
		modelAndView = new ModelAndView("xlsSalesRegister", parameterMap);

		
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sales_register_report/pdf", method = RequestMethod.GET)
	public ModelAndView doSalesReportPDF(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("partyName") String partyName,
			HttpServletResponse response, HttpServletRequest request) {
	System.out.println(" Party Name is ::::::::: " + partyName);
		logger.debug("Received request to download PDF report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "sales_register_report.pdf");

		response.setContentType("application/pdf");
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("FromDatePrompt", fromDate);
		parameterMap.put("ToDatePrompt", toDate);
		if(partyName!=null && !partyName.equals("All")){
			System.out.println("PARTY NAME IS  ::::::::::::::::::" + partyName);
			parameterMap.put("PartyNamePrompt", partyName);
			}
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

		modelAndView = new ModelAndView("pdfSalesRegister", parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sales_register_report/csv", method = RequestMethod.GET)
	public ModelAndView doSalesReportCSV(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("partyName") String partyName,
			HttpServletResponse response) {
		logger.debug("Received request to download CSV report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "sales_register_report.csv");

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("FromDatePrompt", fromDate);
		parameterMap.put("ToDatePrompt", toDate);
		if(partyName!=null && !partyName.equals("All")){
			parameterMap.put("PartyNamePrompt", partyName);
			}
		modelAndView = new ModelAndView("csvSalesRegister", parameterMap);

		return modelAndView;
	}

	/**
	 * Retrieves HTML and show it in the screen
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sales_register_report/html", method = RequestMethod.POST)
	public @ResponseBody
	Map showHTMLReport(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("partyName") String partyName) {
		logger.debug("Received request to download HTML report");
		
		Map result = new HashMap();
		try {
			Connection conn = dataSource.getConnection();

			// Get the jasper report object
			// Load it
			InputStream reportStream = this.getClass().getResourceAsStream(
					"/reports/Sales_Register.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(reportStream);

			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("FromDatePrompt", fromDate);
			parameterMap.put("ToDatePrompt", toDate);
			if(partyName!=null && !partyName.equals("All")){
				parameterMap.put("PartyNamePrompt", partyName);
				}
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
			
			allowedHtml.addTags("table", "style", "cellpadding", "cellspacing","border", "bgcolor", "width");
			allowedHtml.addAttributes("tr", "valign");
			allowedHtml.addAttributes("td", "colspan", "style");
			//allowedHtml.addProtocols("a", "href", "ftp", "http", "https");
			
			//allowedHtml.addAttributes("span", "style");
			String html = Jsoup.clean(sb.toString(), allowedHtml);
			// Add report to map
			result.put("reportHtml", html);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}