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

import com.advanz.erp.client.http.controller.form.ItemStockReportFrom;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.msg.ItemCategoryInputMessage;
import com.advanz.erp.masters.model.msg.ItemCategoryOutMessage;
import com.advanz.erp.masters.model.msg.ItemGroupOutMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.PartyOutMessage;
import com.advanz.erp.masters.service.business.IItemCategoryService;
import com.advanz.erp.masters.service.business.IItemGroupService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IPartyService;
@Controller
public class ItemWiseGrnDetailReportController {

	protected static Logger logger = Logger.getLogger(ItemWiseGrnDetailReportController.class);
	@Autowired
	DataSource dataSource;
	
	
	@Autowired
	IItemGroupService itemGroupService;
	
	@Autowired
	IPartyService partyService;
	@Autowired
	IItemService itemService;
	@Autowired
	IItemCategoryService itemcategoryService;
	public static String ALL = "All";
	
	/**
	 * Handles and retrieves the reports page. The zones will be pre-populated with this page.
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/item_wise_grn_detail_report", method = RequestMethod.GET)
    public String getDownloadPage(Model model) {
    	logger.debug("Received request to show download page");
    	
    	ItemGroupOutMessage itemGroupOutMessage=itemGroupService.findAllItemGroup();
    	ArrayList<ItemGroupDTO> itemGroupList=(ArrayList<ItemGroupDTO>)itemGroupOutMessage.getItemGroupDTOList();
       
    	PartyOutMessage partyOutMessage= partyService.findPartyNameAndId();
    	ArrayList<PartyDTO> partyList=(ArrayList<PartyDTO>)partyOutMessage.getPartyDTOList();
    	
    	 ItemOutMessage itemOutMessage=itemService.getItemIdAndItemNameList();
 		ArrayList<ItemDTO>  itemList= (ArrayList<ItemDTO>)itemOutMessage.getItemDTOList();
 		ItemCategoryOutMessage itemcategoryOutMessage = itemcategoryService.findAllItemCategories();
		ArrayList<ItemCategoryDTO> itemcategoryList = (ArrayList<ItemCategoryDTO>) itemcategoryOutMessage.getItemCategoryDTOList();
		
        ItemStockReportFrom itemReportFrom = new ItemStockReportFrom();
    	model.addAttribute("itemReportFrom", itemReportFrom);
    	model.addAttribute("itemGroupList",itemGroupList);
    	model.addAttribute("itemList", itemList);
    	model.addAttribute("partyList",partyList);
    	model.addAttribute("itemcategoryList", itemcategoryList);
    	return "item_wise_grn_detail_report_view";
	}
    /**
     * Retrieves the download file in XLS format
     * 
     * @return
     */
    @RequestMapping(value = "/item_wise_grn_detail_report/xls", method = RequestMethod.GET)
    public ModelAndView doSalesReportXLS(@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("itemGroupName") String itemGroupName,@RequestParam("approvedStatus") String approvedStatus,@RequestParam("supplierName") String supplierName,@RequestParam("itemName") String itemName,@RequestParam("itemcategory") String itemcategory ,@RequestParam("activeStatus") String activeStatus,  ModelAndView modelAndView, HttpServletResponse response) 
		 {
		logger.debug("Received request to download Excel report");
		//response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename","item_wise_grn_detail_report.xls");
		
		// In order to use Spring's built-in Jasper support, 
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("FromDatePrompt", fromDate);
		parameterMap.put("ToDatePrompt", toDate);
		if(itemGroupName!=null && !itemGroupName.equals("All")){
		parameterMap.put("GroupNamePrompt", itemGroupName);
		}
		
		if(itemName!=null && !itemName.equals("All")){
			parameterMap.put("ItemNamePrompt", itemName);
			}
		if(approvedStatus!=null && !approvedStatus.equals("All")){
			parameterMap.put("ApprovalStatusPrompt", approvedStatus);
			}
		if(supplierName!=null && !supplierName.equals("All")){
		parameterMap.put("SupplierNamePrompt", supplierName);
		}if(itemcategory!=null && !itemcategory.equals("All")){
			Integer categoryId=Integer.parseInt(itemcategory);
			itemcategory=getCategoryName(categoryId);
		parameterMap.put("ItemCategoryPrompt", itemcategory);
		}if(!activeStatus.equals("All")&&activeStatus!=null){
			int i=Integer.parseInt(activeStatus);
			parameterMap.put("Item_Status_Prompt", i);
		}
		
		// xlsReport is the View of our application
		// This is declared inside the views xml file
		modelAndView = new ModelAndView("xlsItemWiseGrnDetail", parameterMap);
		
		// Return the View and the Model combined
		return modelAndView;
	}
    
    /**
     * Retrieves the download file in XLS format
     * 
     * @return
     */
    @RequestMapping(value = "/item_wise_grn_detail_report/pdf", method = RequestMethod.GET )
    public  ModelAndView doSalesReportPDF(@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("itemGroupName") String itemGroupName,@RequestParam("approvedStatus") String approvedStatus,@RequestParam("supplierName") String supplierName,@RequestParam("itemName") String itemName ,@RequestParam("itemcategory") String itemcategory,@RequestParam("activeStatus") String activeStatus,  ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request) 
		 {
		logger.debug("Received request to download PDF report");
	    //response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename","item_wise_grn_detail_report.pdf");
		
		response.setContentType("application/pdf");
		
		
		
		// In order to use Spring's built-in Jasper support, 
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("FromDatePrompt", fromDate);
		parameterMap.put("ToDatePrompt", toDate);
		
		if(itemGroupName!=null && !itemGroupName.equals("All")){
			parameterMap.put("GroupNamePrompt", itemGroupName);
			}
			
			if(itemName!=null && !itemName.equals("All")){
				parameterMap.put("ItemNamePrompt", itemName);
				}
			if(approvedStatus!=null && !approvedStatus.equals("All")){
				parameterMap.put("ApprovalStatusPrompt", approvedStatus);
				}
			if(supplierName!=null && !supplierName.equals("All")){
			parameterMap.put("SupplierNamePrompt", supplierName);
			}if(itemcategory!=null && !itemcategory.equals("All")){
				Integer categoryId=Integer.parseInt(itemcategory);
				itemcategory=getCategoryName(categoryId);
			parameterMap.put("ItemCategoryPrompt", itemcategory);
			}if(!activeStatus.equals("All")&&activeStatus!=null){
				int i=Integer.parseInt(activeStatus);
				parameterMap.put("Item_Status_Prompt", i);
			}
		String realPath  = request.getSession().getServletContext().getRealPath(System.getProperty("file.separator")+"WEB-INF"+System.getProperty("file.separator")+"images"+System.getProperty("file.separator")+"shree_logo.JPG");
		parameterMap.put("Image_Loc", realPath.toString());
		
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("pdfItemWiseGrnDetail", parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	   }
    /**
     * Retrieves the download file in XLS format 
     * 
     * @return
     */
    @RequestMapping(value = "/item_wise_grn_detail_report/csv", method = RequestMethod.GET)
    public ModelAndView doSalesReportCSV(@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("itemGroupName") String itemGroupName,@RequestParam("approvedStatus") String approvedStatus,@RequestParam("supplierName") String supplierName,@RequestParam("itemName") String itemName,@RequestParam("itemcategory") String itemcategory ,@RequestParam("activeStatus") String activeStatus,  ModelAndView modelAndView, HttpServletResponse response) 
		 {
		logger.debug("Received request to download CSV report");
		//response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename","item_wise_grn_detail_report.csv");
		
		// In order to use Spring's built-in Jasper support, 
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("FromDatePrompt", fromDate);
		parameterMap.put("ToDatePrompt", toDate);
		
		if(itemGroupName!=null && !itemGroupName.equals("All")){
			parameterMap.put("GroupNamePrompt", itemGroupName);
			}
			
			if(itemName!=null && !itemName.equals("All")){
				parameterMap.put("ItemNamePrompt", itemName);
				}
			if(approvedStatus!=null && !approvedStatus.equals("All")){
				parameterMap.put("ApprovalStatusPrompt", approvedStatus);
				}
			if(supplierName!=null && !supplierName.equals("All")){
			parameterMap.put("SupplierNamePrompt", supplierName);
			}if(itemcategory!=null && !itemcategory.equals("All")){
				Integer categoryId=Integer.parseInt(itemcategory);
				itemcategory=getCategoryName(categoryId);
			parameterMap.put("ItemCategoryPrompt", itemcategory);
			}if(!activeStatus.equals("All")&&activeStatus!=null){
				int i=Integer.parseInt(activeStatus);
				parameterMap.put("Item_Status_Prompt", i);
			}
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("csvItemWiseGrnDetail", parameterMap);
		
		// Return the View and the Model combined
		return modelAndView;
	}
    
    /**
     * Retrieves HTML and show it in the screen
     * 
     * @return
     */
    @RequestMapping(value = "/item_wise_grn_detail_report/html", method = RequestMethod.POST)
    public  @ResponseBody Map showHTMLReport(@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("itemGroupName") String itemGroupName,@RequestParam("approvedStatus") String approvedStatus,@RequestParam("supplierName") String supplierName,@RequestParam("itemName") String itemName,@RequestParam("itemcategory") String itemcategory,@RequestParam("activeStatus") String activeStatus ,  ModelAndView modelAndView, HttpServletResponse response) 
		 {
		logger.debug("Received request to download HTML report");
		
		Map result = new HashMap();
		try
		{
			Connection conn = dataSource.getConnection();
	
	        // Get the jasper report object 
	        // Load it 
	        InputStream reportStream = this.getClass().getResourceAsStream("/reports/Item_wise_GRN_Detail.jasper");
	        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);
	        
	        Map<String,Object> parameterMap = new HashMap<String,Object>();
	        parameterMap.put("FromDatePrompt", fromDate);
			parameterMap.put("ToDatePrompt", toDate);
			
			if(itemGroupName!=null && !itemGroupName.equals("All")){
				parameterMap.put("GroupNamePrompt", itemGroupName);
				}
				
				if(itemName!=null && !itemName.equals("All")){
					parameterMap.put("ItemNamePrompt", itemName);
					}
				if(approvedStatus!=null && !approvedStatus.equals("All")){
					parameterMap.put("ApprovalStatusPrompt", approvedStatus);
					}
				if(supplierName!=null && !supplierName.equals("All")){
				parameterMap.put("SupplierNamePrompt", supplierName);
				}if(itemcategory!=null && !itemcategory.equals("All")){
					Integer categoryId=Integer.parseInt(itemcategory);
					itemcategory=getCategoryName(categoryId);
				parameterMap.put("ItemCategoryPrompt", itemcategory);
				}if(!activeStatus.equals("All")&&activeStatus!=null){
					int i=Integer.parseInt(activeStatus);
					parameterMap.put("Item_Status_Prompt", i);
				}
	        // Populate report with data
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameterMap, conn);
	        // Create report exporter to be in Html
	        JRExporter exporter = new JRHtmlExporter();
	
	        // Create string buffer to store completed report
	        StringBuffer sb = new StringBuffer();
	
	        // Setup report, no header, no footer, no images for layout
	        exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
	        exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
	        exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
	
	        // When report is exported send to string buffer
	        exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sb);
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	
	        // Export the report, store to sb
	        exporter.exportReport();
	       
	        // Use Jsoup to clean the report table html to output to browser
	        Whitelist allowedHtml = new Whitelist();
	        allowedHtml.addTags("table", "tr", "td", "span","div");
	        allowedHtml.addTags("table", "style", "cellpadding", "cellspacing", "border", "colgroup");
	        allowedHtml.addAttributes("tr", "valign");
	        allowedHtml.addAttributes("td", "colspan","bgcolor","col","img", "style");
	        allowedHtml.addAttributes("span", "style");
	        allowedHtml.addAttributes("a","accesskey","dir","lang","style","tabindex","title","href");

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
    
    @RequestMapping(value = "/get_item_name_for_grn", method = RequestMethod.POST)
    @ResponseBody JsonResponse getItemName()
    {
        ItemOutMessage itemOutMessage=itemService.getItemIdAndItemNameList();
    	JsonResponse jsonResponse=new JsonResponse();
    	jsonResponse.setStatus("SUCCESS");
    	jsonResponse.setResult(itemOutMessage.getItemDTOList());
        return jsonResponse;
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