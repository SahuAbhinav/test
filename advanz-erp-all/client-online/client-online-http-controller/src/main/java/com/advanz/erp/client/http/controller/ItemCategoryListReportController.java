package com.advanz.erp.client.http.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.masters.model.CityDTO;
import com.advanz.erp.masters.model.CountryDTO;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.msg.CountryOutMessage;
import com.advanz.erp.masters.model.msg.ItemCategoryOutMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.service.business.ICountryService;
import com.advanz.erp.masters.service.business.IItemCategoryService;

/**
 * Handles and retrieves download request
 */
@Controller
public class ItemCategoryListReportController {

	protected static Logger logger = Logger
			.getLogger(ItemCategoryListReportController.class);
	@Autowired
	DataSource dataSource;

	@Autowired
	IItemCategoryService itemcategoryService;

	public static String ALL = "All";
    
	 
	/**
	 * Handles and retrieves the reports page. The zones will be pre-populated
	 * with this page.
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/item_category_list_report", method = RequestMethod.GET)
	public String getDownloadPage(Model model) {
		logger.debug("Received request to show download page");

		ItemCategoryOutMessage itemcategoryOutMessage = itemcategoryService.findAllItemCategories();
 
		ArrayList<ItemCategoryDTO> itemcategoryList = (ArrayList<ItemCategoryDTO>) itemcategoryOutMessage.getItemCategoryDTOList();

		// ModelAndView mav = new ModelAndView();
		// mav.addObject("billList",billList);
		model.addAttribute("itemcategoryList", itemcategoryList);
		return "item_category_list_report_view";
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/item_category_list_report/xls", method = RequestMethod.GET)
	public ModelAndView doSalesReportXLS(ModelAndView modelAndView,
			HttpServletResponse response) {
		logger.debug("Received request to download Excel report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "item_category_list_report.xls");
		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);

		// xlsReport is the View of our application
		// This is declared inside the views xml file
		modelAndView = new ModelAndView("xlsItemCategory", parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/item_category_list_report/pdf", method = RequestMethod.GET)
	public ModelAndView doSalesReportPDF(ModelAndView modelAndView,
			HttpServletResponse response, HttpServletRequest request) {
		logger.debug("Received request to download PDF report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "item_category_list_report.pdf");

		response.setContentType("application/pdf");
		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
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

		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("pdfItemCategory", parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/item_category_list_report/csv", method = RequestMethod.GET)
	public ModelAndView doSalesReportCSV(ModelAndView modelAndView,
			HttpServletResponse response) {
		logger.debug("Received request to download CSV report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "item_category_list_report.csv");

		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();

		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("csvItemCategory", parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves HTML and show it in the screen
	 * 
	 * @return
	 */
	@RequestMapping(value = "/item_category_list_report/html", method = RequestMethod.POST)
	public @ResponseBody
	Map showHTMLReport(ModelAndView modelAndView) {
		logger.debug("Received request to download HTML report");
		// Map<String,Object> parameterMap = new HashMap<String,Object>();
		// parameterMap.put("datasource", dataSource);

		// modelAndView = new ModelAndView("csvGeographic", parameterMap);

		Map result = new HashMap();
		try {
			Connection conn = dataSource.getConnection();

			// Get the jasper report object
			// Load it
			InputStream reportStream = this.getClass().getResourceAsStream(
					"/reports/Item_Category_List.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(reportStream);

			Map<String, Object> parameterMap = new HashMap<String, Object>();
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