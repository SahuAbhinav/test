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

import com.advanz.erp.client.http.controller.form.ReorderReportFrom;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.msg.ItemGroupFlagOutMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.service.business.IItemGroupFlagService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;

/**
 * Handles and retrieves download request
 */
@Controller
public class ReorderListReportController {

	protected static Logger logger = Logger
			.getLogger(ReorderListReportController.class);
	@Autowired
	DataSource dataSource;
	
	@Autowired
	IItemGroupFlagService itemGroupFlagService;
	
	@Autowired
	IMastersService mastersService;
	
	@Autowired
	IItemService itemService;
	
	
	public static String ALL = "All";
    
	 
	/**
	 * Handles and retrieves the reports page. The zones will be pre-populated
	 * with this page.
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/reorder_list_report", method = RequestMethod.GET)
	public String getDownloadPage(Model model) {
		logger.debug("Received request to show download page");

		ItemGroupFlagOutMessage flagOutMessage= itemGroupFlagService.findAllItemGroupFlag();
		ArrayList<ItemGroupFlagDTO> itemGroupList=(ArrayList<ItemGroupFlagDTO>)flagOutMessage.getItemGroupFlagDTOList();
			
		ItemOutMessage itemOutMessage= itemService.getItemIdAndItemNameList();
		ArrayList<ItemDTO> itemList=(ArrayList<ItemDTO>)itemOutMessage.getItemDTOList();
		
		
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		
		mastersInputMessage.setFormId(16);
		MastersOutputMessage mastersOutputMessage = mastersService.findFormById(mastersInputMessage);
	    ArrayList<MastersDTO> masterList=(ArrayList<MastersDTO>)mastersOutputMessage.getMastersDTOList();
		
		
	    ReorderReportFrom reorderReportFrom = new ReorderReportFrom();
	    System.out.println("Data is ---------------------------");
		model.addAttribute("reorderReportFrom", reorderReportFrom);
		model.addAttribute("itemGroupList",itemGroupList);
		model.addAttribute("masterList",masterList);
		model.addAttribute("itemList",itemList);
				
		return "reorder_list_report_view";
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/reorder_list_report/xls", method = RequestMethod.GET)
	public ModelAndView doSalesReportXLS(ModelAndView modelAndView,
			@RequestParam("toDate") Date toDate, 
			@RequestParam("itemGroupFlagName") String itemGroupFlagName,
			@RequestParam("name") String name,
			@RequestParam("ItemNamePrompt") String ItemNamePrompt,@RequestParam("activeStatus") String activeStatus,
			HttpServletResponse response) {
		logger.debug("Received request to download Excel report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "reorder_list_report.xls");
		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);
		
		parameterMap.put("DatePrompt", toDate);
				
		if(itemGroupFlagName!=null && !itemGroupFlagName.equals("All")){
		parameterMap.put("GroupNamePrompt", itemGroupFlagName);
		}if(name!=null && !name.equals("All")){
		parameterMap.put("GradePrompt", name);
		}
		if(ItemNamePrompt!=null && !ItemNamePrompt.equals("All")){
			parameterMap.put("ItemNamePrompt", ItemNamePrompt);
			}
		if(!activeStatus.equals("All")&&activeStatus!=null){
			int i=Integer.parseInt(activeStatus);
			parameterMap.put("Item_Status_Prompt", i);
		}
		// xlsReport is the View of our application
		// This is declared inside the views xml file
		modelAndView = new ModelAndView("xlsReorder", parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}

	/*
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/reorder_list_report/pdf", method = RequestMethod.GET)
	public ModelAndView doSalesReportPDF(ModelAndView modelAndView,
			@RequestParam("toDate") Date toDate,
			@RequestParam("itemGroupFlagName") String itemGroupFlagName,
			@RequestParam("name") String name,
			@RequestParam("ItemNamePrompt") String ItemNamePrompt,@RequestParam("activeStatus") String activeStatus,
			HttpServletResponse response, HttpServletRequest request) {
		logger.debug("Received request to download PDF report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "reorder_list_report.pdf");

		response.setContentType("application/pdf");
		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);
		/*parameterMap.put("DatePrompt", new Date());
		parameterMap.put("ItemNamePrompt", "");
		parameterMap.put("GroupNamePrompt", "");
		parameterMap.put("GradePrompt", "");*/
		
		
		parameterMap.put("DatePrompt", toDate);
		
		if(itemGroupFlagName!=null && !itemGroupFlagName.equals("All")){
		parameterMap.put("GroupNamePrompt", itemGroupFlagName);
		}if(name!=null && !name.equals("All")){
		parameterMap.put("GradePrompt", name);
		}
		if(ItemNamePrompt!=null && !ItemNamePrompt.equals("All")){
			parameterMap.put("ItemNamePrompt", ItemNamePrompt);
			}if(!activeStatus.equals("All")&&activeStatus!=null){
				int i=Integer.parseInt(activeStatus);
				parameterMap.put("Item_Status_Prompt", i);
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

		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("pdfReorder", parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/reorder_list_report/csv", method = RequestMethod.GET)
	public ModelAndView doSalesReportCSV(ModelAndView modelAndView,
			@RequestParam("toDate") Date toDate,
			@RequestParam("itemGroupFlagName") String itemGroupFlagName,
			@RequestParam("name") String name,
			@RequestParam("ItemNamePrompt") String ItemNamePrompt,@RequestParam("activeStatus") String activeStatus,
			HttpServletResponse response) {
		logger.debug("Received request to download CSV report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "reorder_list_report.csv");

		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();

        parameterMap.put("DatePrompt", toDate);
		
		if(itemGroupFlagName!=null && !itemGroupFlagName.equals("All")){
		parameterMap.put("GroupNamePrompt", itemGroupFlagName);
		}if(name!=null && !name.equals("All")){
		parameterMap.put("GradePrompt", name);
		}
		if(ItemNamePrompt!=null && !ItemNamePrompt.equals("All")){
			parameterMap.put("ItemNamePrompt", ItemNamePrompt);
			}if(!activeStatus.equals("All")&&activeStatus!=null){
				int i=Integer.parseInt(activeStatus);
				parameterMap.put("Item_Status_Prompt", i);
			}
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("csvReorder", parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves HTML and show it in the screen
	 * 
	 * @return
	 */

	
	@RequestMapping(value = "/reorder_list_report/html", method = RequestMethod.POST)
	public @ResponseBody
	Map showHTMLReport(ModelAndView modelAndView,
			@RequestParam("toDate") Date toDate,
			@RequestParam("itemGroupFlagName") String itemGroupFlagName,
			@RequestParam("name") String name,
			@RequestParam("ItemNamePrompt") String ItemNamePrompt,@RequestParam("activeStatus") String activeStatus) {
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
			"/reports/Reorder_List.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(reportStream);

			Map<String, Object> parameterMap = new HashMap<String, Object>();
			// Populate report with data
			
			parameterMap.put("datasource", dataSource);
		/*	parameterMap.put("DatePrompt", new Date());
			parameterMap.put("ItemNamePrompt", "");
			parameterMap.put("GroupNamePrompt", "");
			parameterMap.put("GradePrompt", "");
		*/
			
			parameterMap.put("DatePrompt", toDate);
			
			if(itemGroupFlagName!=null && !itemGroupFlagName.equals("All")){
			parameterMap.put("GroupNamePrompt", itemGroupFlagName);
			}if(name!=null && !name.equals("All")){
			parameterMap.put("GradePrompt", name);
			}
			if(ItemNamePrompt!=null && !ItemNamePrompt.equals("All")){
				parameterMap.put("ItemNamePrompt", ItemNamePrompt);
				}if(!activeStatus.equals("All")&&activeStatus!=null){
					int i=Integer.parseInt(activeStatus);
					parameterMap.put("Item_Status_Prompt", i);
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