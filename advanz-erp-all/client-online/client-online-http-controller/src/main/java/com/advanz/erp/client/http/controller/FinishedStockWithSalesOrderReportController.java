package com.advanz.erp.client.http.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.advanz.DataUtility;
import com.advanz.erp.client.http.controller.form.SalesReportForm;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.msg.ItemCategoryOutMessage;
import com.advanz.erp.masters.service.business.IItemCategoryService;

/**
 * Handles and retrieves download request
 */
@Controller
public class FinishedStockWithSalesOrderReportController {

	protected static Logger logger = Logger
			.getLogger(FinishedStockWithSalesOrderReportController.class);
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
	@RequestMapping(value = "/finish_stock_with_sales_order", method = RequestMethod.GET)
	public String getDownloadPage(Model model) {
		logger.debug("Received request to show download page");

		ItemCategoryOutMessage itemcategoryOutMessage = itemcategoryService.findAllItemCategories();
		ArrayList<ItemCategoryDTO> itemcategoryList = (ArrayList<ItemCategoryDTO>) itemcategoryOutMessage.getItemCategoryDTOList();
		model.addAttribute("itemcategoryList", itemcategoryList);
		SalesReportForm salesReportForm = new SalesReportForm();
		model.addAttribute("salesReportForm",salesReportForm);
		
	return "finish_stock_with_sales_order_view";
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/finish_stock_with_sales_order/xls", method = RequestMethod.GET)
	public ModelAndView doSalesReportXLS(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("itemCategoryName") String itemCategoryName,@RequestParam(value="desireDelDate",required=false) String desireDelDate,  
			HttpServletResponse response) {
		logger.debug("Received request to download Excel report");
		
		response.setHeader("filename", "finish_stock_with_sales_order.xls");
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("FromDatePrompt", fromDate);
		parameterMap.put("ToDatePrompt", toDate);
		if(itemCategoryName!=null && !itemCategoryName.equals("All")){
			parameterMap.put("ItemCategoryPrompt", itemCategoryName);
			}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date date=null;
		try {
			date=dateFormat.parse(desireDelDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(date!=null){
		date=DataUtility.getDate(date);
		}
	
		parameterMap.put("DesireDelDatePrompt", date);
		
		modelAndView = new ModelAndView("xlsFinishStockWithSalesOrder", parameterMap);

		
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/finish_stock_with_sales_order/pdf", method = RequestMethod.GET)
	public ModelAndView doSalesReportPDF(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("itemCategoryName") String itemCategoryName,@RequestParam(value="desireDelDate",required=false) String desireDelDate,
			HttpServletResponse response, HttpServletRequest request) {
		logger.debug("Received request to download PDF report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "finish_stock_with_sales_order.pdf");

		response.setContentType("application/pdf");
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("FromDatePrompt", fromDate);
		parameterMap.put("ToDatePrompt", toDate);
		if(itemCategoryName!=null && !itemCategoryName.equals("All")){
			parameterMap.put("ItemCategoryPrompt", itemCategoryName);
			}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date date=null;
		try {
			date=dateFormat.parse(desireDelDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(date!=null){
		date=DataUtility.getDate(date);
		}
	
		parameterMap.put("DesireDelDatePrompt", date);
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

		modelAndView = new ModelAndView("pdfFinishStockWithSalesOrder", parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/finish_stock_with_sales_order/csv", method = RequestMethod.GET)
	public ModelAndView doSalesReportCSV(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("itemCategoryName") String itemCategoryName,@RequestParam(value="desireDelDate",required=false) String desireDelDate,
			HttpServletResponse response) {
		logger.debug("Received request to download CSV report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "finish_stock_with_sales_order.csv");

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("FromDatePrompt", fromDate);
		parameterMap.put("ToDatePrompt", toDate);
		if(itemCategoryName!=null && !itemCategoryName.equals("All")){
			parameterMap.put("ItemCategoryPrompt", itemCategoryName);
			}
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date date=null;
		try {
			date=dateFormat.parse(desireDelDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(date!=null){
		date=DataUtility.getDate(date);
		}
	
		parameterMap.put("DesireDelDatePrompt", date);
		modelAndView = new ModelAndView("csvFinishStockWithSalesOrder", parameterMap);

		return modelAndView;
	}

	/**
	 * Retrieves HTML and show it in the screen
	 * 
	 * @return
	 */
	@RequestMapping(value = "/finish_stock_with_sales_order/html", method = RequestMethod.POST)
	public @ResponseBody
	Map showHTMLReport(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("itemCategoryName") String itemCategoryName,@RequestParam(value="desireDelDate",required=false) String desireDelDate) {
		logger.debug("Received request to download HTML report");
		
		Map result = new HashMap();
		try {
			Connection conn = dataSource.getConnection();

			// Get the jasper report object
			// Load it
			InputStream reportStream = this.getClass().getResourceAsStream(
					"/reports/Finish stock with sales order.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(reportStream);

			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("FromDatePrompt", fromDate);
			parameterMap.put("ToDatePrompt", toDate);
			if(itemCategoryName!=null && !itemCategoryName.equals("All")){
				parameterMap.put("ItemCategoryPrompt", itemCategoryName);
				}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			Date date=null;
			try {
				date=dateFormat.parse(desireDelDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(date!=null){
			date=DataUtility.getDate(date);
			}
			parameterMap.put("DesireDelDatePrompt", date);
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