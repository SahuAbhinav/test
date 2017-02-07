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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.InvoiceReportFrom;
import com.advanz.erp.client.http.controller.form.IssueReportFrom;
import com.advanz.erp.client.http.controller.form.PurchaseReportFrom;
import com.advanz.erp.client.http.controller.form.SalesReportForm;
import com.advanz.erp.masters.model.AreaDTO;
import com.advanz.erp.masters.model.IssueMasterDTO;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.msg.AreaOutputMessage;
import com.advanz.erp.masters.model.msg.IssueOutputMessage;
import com.advanz.erp.masters.model.msg.ItemCategoryOutMessage;
import com.advanz.erp.masters.model.msg.ItemGroupFlagOutMessage;
import com.advanz.erp.masters.model.msg.ItemGroupOutMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.service.business.IAreaService;
import com.advanz.erp.masters.service.business.IIssueMasterService;
import com.advanz.erp.masters.service.business.IItemCategoryService;
import com.advanz.erp.masters.service.business.IItemGroupFlagService;
import com.advanz.erp.masters.service.business.IItemGroupService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;

/**
 * Handles and retrieves download request
 */
@Controller
public class PurchaseSummuryReportController {

	protected static Logger logger = Logger
			.getLogger(PurchaseSummuryReportController.class);
	@Autowired
	DataSource dataSource;

	@Autowired
	IItemGroupService itemgroupService;
	@Autowired
	IItemGroupFlagService itemGroupFlagService;
	
	@Autowired
	IMastersService mastersService; 
	@Autowired
	IItemCategoryService itemcategoryService;
	@Autowired
	IItemService itemService;
	public static String ALL = "All";

	@RequestMapping(value = "/purchase_summury_report", method = RequestMethod.GET)
	public String getDownloadPage(@ModelAttribute("purchaseReportFrom") PurchaseReportFrom purchaseReportFrom,Model model) {
		logger.debug("Received request to show download page");
		ItemGroupOutMessage itemgroupOutMessage = itemgroupService.findAllItemGroup();
		ArrayList<ItemGroupDTO> itemgroupList = (ArrayList<ItemGroupDTO>) itemgroupOutMessage.getItemGroupDTOList();
		
	
	MastersInputMessage mastersInputMessage = new MastersInputMessage();
	MastersDTO dto = new MastersDTO();
	
	mastersInputMessage.setFormId(16);
	MastersOutputMessage mastersOutputMessage = mastersService.findFormById(mastersInputMessage);
    ArrayList<MastersDTO> masterList=(ArrayList<MastersDTO>)mastersOutputMessage.getMastersDTOList();
    ItemOutMessage itemOutMessage= itemService.getItemIdAndItemNameList();
	ArrayList<ItemDTO>  itemList= (ArrayList<ItemDTO>)itemOutMessage.getItemDTOList();
	ItemCategoryOutMessage itemcategoryOutMessage = itemcategoryService.findAllItemCategories();
	ArrayList<ItemCategoryDTO> itemcategoryList = (ArrayList<ItemCategoryDTO>) itemcategoryOutMessage.getItemCategoryDTOList();
	
	
     purchaseReportFrom = new PurchaseReportFrom();
     model.addAttribute("itemcategoryList", itemcategoryList);
	model.addAttribute("purchaseReportFrom", purchaseReportFrom);
	model.addAttribute("itemGroupList",itemgroupList);
	model.addAttribute("itemList", itemList);
	model.addAttribute("masterList",masterList);
	
	return "purchase_summury_report_view";
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "/purchase_summury_report/xls", method = RequestMethod.GET)
	public ModelAndView doSalesReportXLS(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("itemGroupFlagName") String itemGroupFlagName,@RequestParam("name") String name,@RequestParam("itemName") String itemName,@RequestParam("itemCategoryName") String itemCategoryName, 
			HttpServletResponse response) {
		logger.debug("Received request to download Excel report");
		
		response.setHeader("filename", "purchase_summury_report.xls");
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("FromDatePrompt", fromDate);
		parameterMap.put("ToDatePrompt", toDate);
		
		if(itemGroupFlagName!=null && !itemGroupFlagName.equals("All")){
		parameterMap.put("GroupNamePrompt", itemGroupFlagName);
		}if(name!=null && !name.equals("All")){
		parameterMap.put("GradePrompt", name);
		}if(itemName!=null && !itemName.equals("All")){
			parameterMap.put("ItemNamePrompt", itemName);
		}
		if(itemCategoryName!=null && !itemCategoryName.equals("All")){
			parameterMap.put("ItemCategoryPrompt", itemCategoryName);
		}
		modelAndView = new ModelAndView("xlsPurchaseSummury", parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/purchase_summury_report/pdf", method = RequestMethod.GET)
	public ModelAndView doSalesReportPDF(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("itemGroupFlagName") String itemGroupFlagName,@RequestParam("name") String name,@RequestParam("itemName") String itemName,@RequestParam("itemCategoryName") String itemCategoryName,
			HttpServletResponse response, HttpServletRequest request) {
		logger.debug("Received request to download PDF report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "purchase_summury_report.pdf");

		response.setContentType("application/pdf");
		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("FromDatePrompt", fromDate);
		parameterMap.put("ToDatePrompt", toDate);
		
		if(itemGroupFlagName!=null && !itemGroupFlagName.equals("All")){
			parameterMap.put("GroupNamePrompt", itemGroupFlagName);
			}if(name!=null && !name.equals("All")){
			parameterMap.put("GradePrompt", name);
			}if(itemName!=null && !itemName.equals("All")){
				parameterMap.put("ItemNamePrompt", itemName);
			}if(itemCategoryName!=null && !itemCategoryName.equals("All")){
				parameterMap.put("ItemCategoryPrompt", itemCategoryName);
			}
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
		modelAndView = new ModelAndView("pdfPurchaseSummury", parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/purchase_summury_report/csv", method = RequestMethod.GET)
	public ModelAndView doSalesReportCSV(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("itemGroupFlagName") String itemGroupFlagName,@RequestParam("name") String name,@RequestParam("itemName") String itemName,@RequestParam("itemCategoryName") String itemCategoryName,
			HttpServletResponse response) {
		logger.debug("Received request to download CSV report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "purchase_summury_report.csv");

		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("FromDatePrompt", fromDate);
		parameterMap.put("ToDatePrompt", toDate);
		
		if(itemGroupFlagName!=null && !itemGroupFlagName.equals("All")){
			parameterMap.put("GroupNamePrompt", itemGroupFlagName);
			}if(name!=null && !name.equals("All")){
			parameterMap.put("GradePrompt", name);
			}if(itemName!=null && !itemName.equals("All")){
				parameterMap.put("ItemNamePrompt", itemName);
			}if(itemCategoryName!=null && !itemCategoryName.equals("All")){
				parameterMap.put("ItemCategoryPrompt", itemCategoryName);
			}
			
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("csvPurchaseSummury", parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves HTML and show it in the screen
	 * 
	 * @return
	 */
	@RequestMapping(value = "/purchase_summury_report/html", method = RequestMethod.POST)
	public @ResponseBody
	Map showHTMLReport(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("itemGroupFlagName") String itemGroupFlagName,@RequestParam("name") String name,@RequestParam("itemName") String itemName,@RequestParam("itemCategoryName") String itemCategoryName) {
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
					"/reports/purchase_summary.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(reportStream);

			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("FromDatePrompt", fromDate);
			parameterMap.put("ToDatePrompt", toDate);
			
			if(itemGroupFlagName!=null && !itemGroupFlagName.equals("All")){
				parameterMap.put("GroupNamePrompt", itemGroupFlagName);
				}if(name!=null && !name.equals("All")){
				parameterMap.put("GradePrompt", name);
				}if(itemName!=null && !itemName.equals("All")){
				parameterMap.put("ItemNamePrompt", itemName);
				}
				if(itemCategoryName!=null && !itemCategoryName.equals("All")){
					parameterMap.put("ItemCategoryPrompt", itemCategoryName);
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