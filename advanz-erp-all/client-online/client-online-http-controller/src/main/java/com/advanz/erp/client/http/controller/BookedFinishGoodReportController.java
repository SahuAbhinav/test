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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.SalesReportForm;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.msg.ItemCategoryInputMessage;
import com.advanz.erp.masters.model.msg.ItemCategoryOutMessage;
import com.advanz.erp.masters.model.msg.ItemGroupFlagOutMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.service.business.IItemCategoryService;
import com.advanz.erp.masters.service.business.IItemGroupFlagService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IPartyService;

/**
 * Handles and retrieves download request
 */
@Controller
public class BookedFinishGoodReportController {

	protected static Logger logger = Logger
			.getLogger(BookedFinishGoodReportController.class);
	@Autowired
	DataSource dataSource;

	@Autowired
	IItemService itemService;
	@Autowired 
	IPartyService partyService;
	
	@Autowired
	IItemGroupFlagService itemGroupFlagService;
	@Autowired
	IItemCategoryService itemcategoryService;
	public static String ALL = "All";
	@RequestMapping(value = "/booked_finish_good_report", method = RequestMethod.GET)
	public String getDownloadPage(Model model) {
		logger.debug("Received request to show download page");

		 
		ItemOutMessage itemOutMessage= itemService.getItemIdAndItemNameList();
		ArrayList<ItemDTO>  itemList= (ArrayList<ItemDTO>)itemOutMessage.getItemDTOList();
		
		ItemGroupFlagOutMessage itemGroupFlagOutMessage= itemGroupFlagService.findAllItemGroupFlag();
		ArrayList<ItemGroupFlagDTO> itemGroupFlagList=(ArrayList<ItemGroupFlagDTO>)itemGroupFlagOutMessage.getItemGroupFlagDTOList();
		ItemCategoryOutMessage itemcategoryOutMessage = itemcategoryService.findAllItemCategories();
		ArrayList<ItemCategoryDTO> itemcategoryList = (ArrayList<ItemCategoryDTO>) itemcategoryOutMessage.getItemCategoryDTOList();
		
		SalesReportForm salesReportForm = new SalesReportForm();
		model.addAttribute("salesReportForm",salesReportForm);
		model.addAttribute("itemGroupFlagList", itemGroupFlagList);
		model.addAttribute("itemList", itemList);
		model.addAttribute("itemcategoryList", itemcategoryList);
	return "booked_finish_good_report_view";
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "/booked_finish_good_report/xls", method = RequestMethod.GET)
	public ModelAndView doSalesReportXLS(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("itemName") String itemName,@RequestParam("itemGroupFlag") String itemGroupFlag,@RequestParam("milShort") String milShort,@RequestParam("orderShort") String orderShort,@RequestParam("itemcategory") String itemcategory,@RequestParam("activeStatus") String activeStatus,
			HttpServletResponse response) {
		logger.debug("Received request to download Excel report");
		
		response.setHeader("filename", "booked_finish_good_report.xls");
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("DatePrompt", fromDate);
		
		if(itemName!=null && !itemName.equals("All")){
			parameterMap.put("ItemNamePrompt", itemName);
			}if(itemGroupFlag!=null && !itemGroupFlag.equals("All")){
			parameterMap.put("GroupFlagPrompt", itemGroupFlag);
			}
			
			if(milShort!=null && milShort.equalsIgnoreCase("yes") ){
				parameterMap.put("MILShortPrompt", milShort);
				}else{
					parameterMap.put("MILShortPrompt", null);	
				}
			
			if(orderShort!=null && orderShort.equalsIgnoreCase("yes")){
				
				parameterMap.put("OrderShortPrompt", orderShort);
				}else{
					parameterMap.put("OrderShortPrompt", null);	
				}if(itemcategory!=null && !itemcategory.equals("All")){
					Integer categoryId=Integer.parseInt(itemcategory);
					itemcategory=getCategoryName(categoryId);
				parameterMap.put("ItemCategoryPrompt", itemcategory);
				}
				if(!activeStatus.equals("All")&&activeStatus!=null){
					int i=Integer.parseInt(activeStatus);
					parameterMap.put("Item_Status_Prompt", i);
				}
		modelAndView = new ModelAndView("xlsBookedFinishGood", parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/booked_finish_good_report/pdf", method = RequestMethod.GET)
	public ModelAndView doSalesReportPDF(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("itemName") String itemName,@RequestParam("itemGroupFlag") String itemGroupFlag,@RequestParam("milShort") String milShort,@RequestParam("orderShort") String orderShort,@RequestParam("itemcategory") String itemcategory,@RequestParam("activeStatus") String activeStatus,
			HttpServletResponse response, HttpServletRequest request) {
		logger.debug("Received request to download PDF report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "booked_finish_good.pdf");

		response.setContentType("application/pdf");
		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("DatePrompt", fromDate);
		if(itemName!=null && !itemName.equals("All")){
			parameterMap.put("ItemNamePrompt", itemName);
			}if(itemGroupFlag!=null && !itemGroupFlag.equals("All")){
			parameterMap.put("GroupFlagPrompt", itemGroupFlag);
			}
			
			if(milShort!=null && milShort.equalsIgnoreCase("yes") ){
				parameterMap.put("MILShortPrompt", milShort);
				}else{
					parameterMap.put("MILShortPrompt", null);	
				}
			
			if(orderShort!=null && orderShort.equalsIgnoreCase("yes")){
				
				parameterMap.put("OrderShortPrompt", orderShort);
				}else{
					parameterMap.put("OrderShortPrompt", null);	
				}if(itemcategory!=null && !itemcategory.equals("All")){
					Integer categoryId=Integer.parseInt(itemcategory);
					itemcategory=getCategoryName(categoryId);
				parameterMap.put("ItemCategoryPrompt", itemcategory);
				}if(!activeStatus.equals("All")&&activeStatus!=null){
					int i=Integer.parseInt(activeStatus);
					parameterMap.put("Item_Status_Prompt", i);
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
		modelAndView = new ModelAndView("pdfBookedFinishGood", parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/booked_finish_good_report/csv", method = RequestMethod.GET)
	public ModelAndView doSalesReportCSV(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("itemName") String itemName,@RequestParam("itemGroupFlag") String itemGroupFlag,@RequestParam("milShort") String milShort,@RequestParam("orderShort") String orderShort,@RequestParam("itemcategory") String itemcategory,@RequestParam("activeStatus") String activeStatus,
			HttpServletResponse response) {
		logger.debug("Received request to download CSV report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "booked_finish_good.csv");

		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("DatePrompt", fromDate);
		if(itemName!=null && !itemName.equals("All")){
			parameterMap.put("ItemNamePrompt", itemName);
			}if(itemGroupFlag!=null && !itemGroupFlag.equals("All")){
			parameterMap.put("GroupFlagPrompt", itemGroupFlag);
			}
			
			if(milShort!=null && milShort.equalsIgnoreCase("yes") ){
				parameterMap.put("MILShortPrompt", milShort);
				}else{
					parameterMap.put("MILShortPrompt", null);	
				}
			
			if(orderShort!=null && orderShort.equalsIgnoreCase("yes")){
				
				parameterMap.put("OrderShortPrompt", orderShort);
				}else{
					parameterMap.put("OrderShortPrompt", null);	
				}
			if(itemcategory!=null && !itemcategory.equals("All")){
				Integer categoryId=Integer.parseInt(itemcategory);
				itemcategory=getCategoryName(categoryId);
			parameterMap.put("ItemCategoryPrompt", itemcategory);
			}if(!activeStatus.equals("All")&&activeStatus!=null){
				int i=Integer.parseInt(activeStatus);
				parameterMap.put("Item_Status_Prompt", i);
			}
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("csvBookedFinishGood", parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves HTML and show it in the screen
	 * 
	 * @return
	 */
	@RequestMapping(value = "/booked_finish_good_report/html", method = RequestMethod.POST)
	public @ResponseBody
	Map showHTMLReport(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("itemName") String itemName,@RequestParam("itemGroupFlag") String itemGroupFlag,@RequestParam("milShort") String milShort,@RequestParam("orderShort") String orderShort,@RequestParam("itemcategory") String itemcategory,@RequestParam("activeStatus") String activeStatus) { 
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
					"/reports/Booked_Finished_Goods_Report.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(reportStream);

			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("DatePrompt", fromDate);
			if(itemName!=null && !itemName.equals("All")){
				parameterMap.put("ItemNamePrompt", itemName);
				}if(itemGroupFlag!=null && !itemGroupFlag.equals("All")){
					parameterMap.put("GroupFlagPrompt", itemGroupFlag);
				}
				
				if(milShort!=null && milShort.equalsIgnoreCase("yes") ){
					parameterMap.put("MILShortPrompt", milShort);
					}else{
						parameterMap.put("MILShortPrompt", null);	
					}
				
				if(orderShort!=null && orderShort.equalsIgnoreCase("yes")){
					
					parameterMap.put("OrderShortPrompt", orderShort);
					}else{
						parameterMap.put("OrderShortPrompt", null);	
					}if(itemcategory!=null && !itemcategory.equals("All")){
						Integer categoryId=Integer.parseInt(itemcategory);
						itemcategory=getCategoryName(categoryId);
					parameterMap.put("ItemCategoryPrompt", itemcategory);
					}
					if(!activeStatus.equals("All")&&activeStatus!=null){
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
	 public String getCategoryName(Integer categoryId){
	 	   ItemCategoryInputMessage itemCategoryInputMessage = new ItemCategoryInputMessage();
	 	   ItemCategoryDTO itemCategoryDTO = new ItemCategoryDTO();
	 	   itemCategoryDTO.setItemCategoryId(categoryId);
	 	   itemCategoryInputMessage.setItemCategoryDTO(itemCategoryDTO);
	 	   ItemCategoryOutMessage categoryOutMessage=itemcategoryService.findItemCategoryByItemCategoryId(itemCategoryInputMessage);
	 	  List<ItemCategoryDTO> categoryList= categoryOutMessage.getItemCategoryDTOList();
	 	   if(categoryList!=null && categoryList.size()>0){
	 		   itemCategoryDTO= categoryList.get(0);
	 	   }
	 	   
	 	   return itemCategoryDTO.getItemCategoryName();
	    }
	
}