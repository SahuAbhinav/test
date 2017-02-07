package com.advanz.erp.client.http.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.advanz.erp.masters.model.CompanyDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.msg.CompanyOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.service.business.ICompanyService;
import com.advanz.erp.masters.service.business.IMastersService;

@Controller
public class BlanketProductionSumuryReportController {
	protected static Logger logger = Logger
			.getLogger(RegionListReportController.class);
	@Autowired
	private IMastersService mastersService;
	@Autowired
	DataSource dataSource;
	@Autowired
	ICompanyService companyService;

	@RequestMapping(value = "blanket_production_sumury_report", method = RequestMethod.GET)
	public ModelAndView showReportData(Model model) {
		ModelAndView mv= new ModelAndView("balket_production_sumury_report");
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(11);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		List<MastersDTO> masterList = mastersOutputMessage.getMastersDTOList();
		MenufacturingReportForm menufacturingReportForm = new MenufacturingReportForm();
		mv.addObject("menufacturingReportForm", menufacturingReportForm);
		mv.addObject("masterList", masterList);
		
		
		SimpleDateFormat ft = new SimpleDateFormat("yyyy,MM,dd");
		CompanyOutMessage outCompanyOutMessage = companyService
				.findAllCompanies();
		List<CompanyDTO> cList = outCompanyOutMessage.getCompanyDTOList();
		CompanyDTO companyDTO = null;
		if (cList != null && cList.size() > 0) {
			companyDTO = (CompanyDTO) cList.get(0);
			// System.out.println("dto.getStockLockFlag()"+dto.getStockLockFlag());
		}
		if (companyDTO != null) {
			if (companyDTO.getBlanketCutoffDate() != null) {

				mv.addObject("cutoffDate",(ft.format(new Date(companyDTO
						.getBlanketCutoffDate().getTime()))));
			}
		}
		
		return mv;
	}
	
	/**
	 * @author Abhinav Sahu
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "blanket_production_sumury_report_new", method = RequestMethod.GET)
	public ModelAndView showNewReportData(Model model) {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		ModelAndView mv= new ModelAndView("balket_production_sumury_report_new");
		mastersInputMessage.setFormId(11);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		List<MastersDTO> masterList = mastersOutputMessage.getMastersDTOList();
		MenufacturingReportForm menufacturingReportForm = new MenufacturingReportForm();
		model.addAttribute("menufacturingReportForm", menufacturingReportForm);
		model.addAttribute("masterList", masterList);
		
		SimpleDateFormat ft = new SimpleDateFormat("yyyy,MM,dd");
		CompanyOutMessage outCompanyOutMessage = companyService
				.findAllCompanies();
		List<CompanyDTO> cList = outCompanyOutMessage.getCompanyDTOList();
		CompanyDTO companyDTO = null;
		if (cList != null && cList.size() > 0) {
			companyDTO = (CompanyDTO) cList.get(0);
			// System.out.println("dto.getStockLockFlag()"+dto.getStockLockFlag());
		}
		if (companyDTO != null) {
			if (companyDTO.getBlanketCutoffDate() != null) {
				mv.addObject("cutoffDate",(ft.format(new Date(companyDTO
						.getBlanketCutoffDate().getTime()))));
			}
		}
		
		return mv;
	}

	@RequestMapping(value = "/blanket_production_sumury_report/xls", method = RequestMethod.GET)
	public ModelAndView doSalesReportXLS(
			@RequestParam("fromDate") Date fromDate,
			@RequestParam("runNo") String runNo,
			@RequestParam("toDate") Date toDate,
			@RequestParam("shiftName") String shiftName, String branchName,
			ModelAndView modelAndView, HttpServletResponse response,
			HttpServletRequest request) {
		logger.debug("Received request to download Excel report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "blanket_production_report.xls");
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);

		if (runNo.equals("")) {
			runNo = null;
		}
		if (!shiftName.equals("All")) {
			parameterMap.put("Shift", shiftName);
		}
		parameterMap.put("FromDate", fromDate);
		parameterMap.put("ToDate", toDate);
		parameterMap.put("RunNo", runNo);
		modelAndView = new ModelAndView("xlsBlanketProductionSummuryReport",
				parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	}
	
	/**
	 * @author Abhinav
	 * @param fromDate
	 * @param runNo
	 * @param toDate
	 * @param shiftName
	 * @param branchName
	 * @param modelAndView
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/blanket_production_sumury_report_new/xls", method = RequestMethod.GET)
	public ModelAndView doNewSalesReportXLS(
			@RequestParam("fromDate") Date fromDate,
			@RequestParam("runNo") String runNo,
			@RequestParam("toDate") Date toDate,
			@RequestParam("shiftName") String shiftName, String branchName,
			ModelAndView modelAndView, HttpServletResponse response,
			HttpServletRequest request) {
		logger.debug("Received request to download Excel report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "blanket_production_report.xls");
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);

		if (runNo.equals("")) {
			runNo = null;
		}
		if (!shiftName.equals("All")) {
			parameterMap.put("Shift", shiftName);
		}
		parameterMap.put("FromDate", fromDate);
		parameterMap.put("ToDate", toDate);
		parameterMap.put("RunNo", runNo);
		modelAndView = new ModelAndView("xlsBlanketProductionSummuryReportNew",
				parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/blanket_production_sumury_report/pdf", method = RequestMethod.GET)
	public ModelAndView doSalesReportPDF(
			@RequestParam(value = "fromDate", required = false) Date fromDate,
			@RequestParam("runNo") String runNo,
			@RequestParam(value = "toDate", required = false) Date toDate,
			@RequestParam("shiftName") String shiftName,
			ModelAndView modelAndView, HttpServletResponse response,
			HttpServletRequest request) {

		logger.debug("Received request to download PDF report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "BlanketProductionSummuryReport.pdf");

		response.setContentType("application/pdf");
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);

		if (runNo.equals("")) {
			runNo = null;
		}
		if (!shiftName.equals("All")) {
			parameterMap.put("Shift", shiftName);
		}

		parameterMap.put("FromDate", fromDate);
		parameterMap.put("ToDate", toDate);
		parameterMap.put("RunNo", runNo);
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
		modelAndView = new ModelAndView("pdfBlanketProductionSummuryReport",
				parameterMap);
		return modelAndView;
	}
	
	/**
	 * @author Abhinav
	 * @param fromDate
	 * @param runNo
	 * @param toDate
	 * @param shiftName
	 * @param modelAndView
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/blanket_production_sumury_report_new/pdf", method = RequestMethod.GET)
	public ModelAndView doNewSalesReportPDF(
			@RequestParam(value = "fromDate", required = false) Date fromDate,
			@RequestParam("runNo") String runNo,
			@RequestParam(value = "toDate", required = false) Date toDate,
			@RequestParam("shiftName") String shiftName,
			ModelAndView modelAndView, HttpServletResponse response,
			HttpServletRequest request) {

		logger.debug("Received request to download PDF report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "BlanketProductionSummuryReport.pdf");

		response.setContentType("application/pdf");
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);

		if (runNo.equals("")) {
			runNo = null;
		}
		if (!shiftName.equals("All")) {
			parameterMap.put("Shift", shiftName);
		}

		parameterMap.put("FromDate", fromDate);
		parameterMap.put("ToDate", toDate);
		parameterMap.put("RunNo", runNo);
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
		modelAndView = new ModelAndView("pdfBlanketProductionSummuryReportNew",
				parameterMap);
		return modelAndView;
	}

	@RequestMapping(value = "/blanket_production_sumury_report/csv", method = RequestMethod.GET)
	public ModelAndView doSalesReportCSV(
			@RequestParam("fromDate") Date fromDate,
			@RequestParam("runNo") String runNo,
			@RequestParam("toDate") Date toDate, ModelAndView modelAndView,
			HttpServletResponse response, HttpServletRequest request,
			@RequestParam("shiftName") String shiftName) {
		logger.debug("Received request to download CSV report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "BlanketProductionSummuryReport.csv");
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);

		if (runNo.equals("")) {
			runNo = null;
		}
		if (!shiftName.equals("All")) {
			parameterMap.put("Shift", shiftName);
		}
		parameterMap.put("FromDate", fromDate);
		parameterMap.put("ToDate", toDate);
		parameterMap.put("RunNo", runNo);
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("csvBlanketProductionSummuryReport",
				parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}
	
	/**
	 * @author Abhinav
	 * @param fromDate
	 * @param runNo
	 * @param toDate
	 * @param modelAndView
	 * @param response
	 * @param request
	 * @param shiftName
	 * @return
	 */
	@RequestMapping(value = "/blanket_production_sumury_report_new/csv", method = RequestMethod.GET)
	public ModelAndView doNewSalesReportCSV(
			@RequestParam("fromDate") Date fromDate,
			@RequestParam("runNo") String runNo,
			@RequestParam("toDate") Date toDate, ModelAndView modelAndView,
			HttpServletResponse response, HttpServletRequest request,
			@RequestParam("shiftName") String shiftName) {
		logger.debug("Received request to download CSV report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "BlanketProductionSummuryReport.csv");
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);

		if (runNo.equals("")) {
			runNo = null;
		}
		if (!shiftName.equals("All")) {
			parameterMap.put("Shift", shiftName);
		}
		parameterMap.put("FromDate", fromDate);
		parameterMap.put("ToDate", toDate);
		parameterMap.put("RunNo", runNo);
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("csvBlanketProductionSummuryReportNew",
				parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves HTML and show it in the screen
	 * 
	 * @return
	 */
	@RequestMapping(value = "/blanket_production_sumury_report/html", method = RequestMethod.POST)
	public @ResponseBody
	Map showHTMLReport(@RequestParam("fromDate") Date fromDate,
			@RequestParam("runNo") String runNo,
			@RequestParam("toDate") Date toDate,
			@RequestParam("shiftName") String shiftName,
			ModelAndView modelAndView, HttpServletResponse response,
			HttpServletRequest request) {

		logger.debug("Received request to download HTML Shift report");
		Map result = new HashMap();
		try {
			Connection conn = dataSource.getConnection();
			// Get the jasper report object
			InputStream reportStream = this.getClass().getResourceAsStream(
					"/reports/Blanket_Production_Summary.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(reportStream);

			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("datasource", dataSource);

			if (runNo.equals("")) {
				runNo = null;
			}
			if (!shiftName.equals("All")) {
				parameterMap.put("Shift", shiftName);
			}

			parameterMap.put("FromDate", fromDate);
			parameterMap.put("ToDate", toDate);
			parameterMap.put("RunNo", runNo);
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
	
	@RequestMapping(value = "/blanket_production_sumury_report_new/html", method = RequestMethod.POST)
	public @ResponseBody
	Map showNewHTMLReport(@RequestParam("fromDate") Date fromDate,
			@RequestParam("runNo") String runNo,
			@RequestParam("toDate") Date toDate,
			@RequestParam("shiftName") String shiftName,
			ModelAndView modelAndView, HttpServletResponse response,
			HttpServletRequest request) {

		logger.debug("Received request to download HTML Shift report");
		Map result = new HashMap();
		try {
			Connection conn = dataSource.getConnection();
			// Get the jasper report object
			InputStream reportStream = this.getClass().getResourceAsStream(
					"/reports/Blanket_Production_Summary_new.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(reportStream);

			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("datasource", dataSource);

			if (runNo.equals("")) {
				runNo = null;
			}
			if (!shiftName.equals("All")) {
				parameterMap.put("Shift", shiftName);
			}

			parameterMap.put("FromDate", fromDate);
			parameterMap.put("ToDate", toDate);
			parameterMap.put("RunNo", runNo);
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
