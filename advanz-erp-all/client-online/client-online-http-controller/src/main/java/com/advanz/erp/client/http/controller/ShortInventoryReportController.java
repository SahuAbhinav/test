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

import com.advanz.erp.client.http.controller.form.ShortInventoryReportForm;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.msg.ItemCategoryOutMessage;
import com.advanz.erp.masters.model.msg.ItemGroupOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.service.business.IItemCategoryService;
import com.advanz.erp.masters.service.business.IItemGroupService;
import com.advanz.erp.masters.service.business.IMastersService;

@Controller
public class ShortInventoryReportController {
	protected static Logger logger = Logger.getLogger(ShortInventoryReportController.class);
	@Autowired
	DataSource dataSource;
	@Autowired
	IItemGroupService itemGroupService;
	@Autowired
	public IMastersService mastersService;
	
	@Autowired
	IItemCategoryService itemcategoryService;
	
	public static String ALL = "All";

	@RequestMapping(value="/show_short_inventory_report",method=RequestMethod.GET)
	public String showInventryReport(Model model)
	{
	 logger.debug("Received request to show download page");	
		
 	ItemGroupOutMessage itemGroupOutMessage=itemGroupService.findAllItemGroup();
 	ArrayList<ItemGroupDTO> itemGroupList=(ArrayList<ItemGroupDTO>)itemGroupOutMessage.getItemGroupDTOList();
	MastersInputMessage mastersInputMessage = new MastersInputMessage();
	mastersInputMessage.setFormId(13);
	MastersOutputMessage mastersOutputMessage = mastersService.findFormByIdForMelterLog(mastersInputMessage);
	List<MastersDTO> masterList=mastersOutputMessage.getMastersDTOList();
	ShortInventoryReportForm shortInventoryReportForm=new ShortInventoryReportForm();

	ItemCategoryOutMessage itemcategoryOutMessage = itemcategoryService.findAllItemCategories();
	ArrayList<ItemCategoryDTO> itemcategoryList = (ArrayList<ItemCategoryDTO>) itemcategoryOutMessage.getItemCategoryDTOList();
	
	
	model.addAttribute("shortInventoryReportForm",shortInventoryReportForm);
	model.addAttribute("itemGroupList",itemGroupList);
	model.addAttribute("masterList",masterList);
	model.addAttribute("itemcategoryList",itemcategoryList);
 	return "short_inventory_report";
	}
	
	
	 @RequestMapping(value = "/show_short_inventory_report/xls", method = RequestMethod.GET)
	    public ModelAndView doSalesReportXLS(@RequestParam("fromDate") Date fromDate,@RequestParam("itemGroupName")String itemGroupName
	    		,@RequestParam("reorderName") String reorderName,@RequestParam("itemClass") String itemClass,@RequestParam("status") 
	    		String status,ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request,@RequestParam("itemCategoryName")String itemCategoryName,@RequestParam("activeStatus")String activeStatus) 
	    {
			logger.debug("Received request to download Excel report");
			//response.setHeader("Content-Disposition","attachment");
			response.setHeader("filename","item_stock_report.xls");
			
			// In order to use Spring's built-in Jasper support, 
			// We are required to pass our datasource as a map parameter
			// parameterMap is the Model of our application
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			parameterMap.put("datasource", dataSource);
			parameterMap.put("DatePrompt", fromDate);
		
			if(itemCategoryName!=null && !itemCategoryName.equals("All")){
				parameterMap.put("ItemCategoryPrompt", itemCategoryName);
				}
			if(itemGroupName!=null && !itemGroupName.equals("All")){
				parameterMap.put("GroupNamePrompt", itemGroupName);
				}
	
				parameterMap.put("ReorderPrompt", reorderName);
	
			if(itemClass!=null && !itemClass.equals("All")){
				parameterMap.put("ClassPrompt", itemClass);
				}
	
			parameterMap.put("StatusPrompts", status);
			int i=Integer.parseInt(activeStatus);
			parameterMap.put("Item_Status_Prompt", i);
			modelAndView = new ModelAndView("xlsShortInventory", parameterMap);
			// Return the View and the Model combined
			return modelAndView;
		}
	    
	    /**
	     * Retrieves the download file in XLS format
	     * 
	     * @return
	     */
	    @RequestMapping(value = "/show_short_inventory_report/pdf", method = RequestMethod.GET )
	    public  ModelAndView doSalesReportPDF(@RequestParam("fromDate") Date fromDate,@RequestParam("itemGroupName")String itemGroupName
	    		,@RequestParam("reorderName") String reorderName,@RequestParam("itemClass") String itemClass,@RequestParam("status") 
	    		String status,ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request,@RequestParam("itemCategoryName")String itemCategoryName,@RequestParam("activeStatus")String activeStatus) 
			 {
	    
			logger.debug("Received request to download PDF report");
		    //response.setHeader("Content-Disposition","attachment");
			response.setHeader("filename","item_stock_report.pdf");
			
			response.setContentType("application/pdf");
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			parameterMap.put("datasource", dataSource);
			parameterMap.put("DatePrompt", fromDate);
			if(itemCategoryName!=null && !itemCategoryName.equals("All")){
				parameterMap.put("ItemCategoryPrompt", itemCategoryName);
				}
			if(itemGroupName!=null && !itemGroupName.equals("All")){
				parameterMap.put("GroupNamePrompt", itemGroupName);
				}
				parameterMap.put("ReorderPrompt", reorderName);
	
			if(itemClass!=null && !itemClass.equals("All")){
				parameterMap.put("ClassPrompt", itemClass);
				}
		
				parameterMap.put("StatusPrompts", status);
				int i=Integer.parseInt(activeStatus);
				parameterMap.put("Item_Status_Prompt", i);
	
			String realPath  = request.getSession().getServletContext().getRealPath(System.getProperty("file.separator")+"WEB-INF"+System.getProperty("file.separator")+"images"+System.getProperty("file.separator")+"shree_logo.JPG");
			parameterMap.put("Image_Loc", realPath.toString());
			modelAndView = new ModelAndView("pdfShortInventory", parameterMap);
			return modelAndView;
		   }
	    
	    @RequestMapping(value = "/show_short_inventory_report/csv", method = RequestMethod.GET)
	    public ModelAndView doSalesReportCSV(@RequestParam("fromDate") Date fromDate,@RequestParam("itemGroupName")String itemGroupName
	    		,@RequestParam("reorderName") String reorderName,@RequestParam("itemClass") String itemClass,@RequestParam("status") 
	    		String status,ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request,@RequestParam("itemCategoryName")String itemCategoryName,@RequestParam("activeStatus")String activeStatus) 
			 {
			logger.debug("Received request to download CSV report");
			//response.setHeader("Content-Disposition","attachment");
			response.setHeader("filename","item_stock_report.csv");
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			parameterMap.put("datasource", dataSource);
			parameterMap.put("DatePrompt", fromDate);
		
			
			if(itemCategoryName!=null && !itemCategoryName.equals("All")){
				parameterMap.put("ItemCategoryPrompt", itemCategoryName);
				}
			if(itemGroupName!=null && !itemGroupName.equals("All")){
				parameterMap.put("GroupNamePrompt", itemGroupName);
				}
	
				parameterMap.put("ReorderPrompt", reorderName);
	
			if(itemClass!=null && !itemClass.equals("All")){
				parameterMap.put("ClassPrompt", itemClass);
				}
		
				parameterMap.put("StatusPrompts", status);
				int i=Integer.parseInt(activeStatus);
				parameterMap.put("Item_Status_Prompt", i);
			// pdfReport is the View of our application
			// This is declared inside the /WEB-INF/jasper-views.xml
			modelAndView = new ModelAndView("csvShortInventory", parameterMap);
			
			// Return the View and the Model combined
			return modelAndView;
		}
	    
	    /**
	     * Retrieves HTML and show it in the screen
	     * 
	     * @return
	     */
	    @RequestMapping(value = "/show_short_inventory_report/html", method = RequestMethod.POST)
	    public  @ResponseBody Map showHTMLReport(@RequestBody Map map,@RequestParam("fromDate") Date fromDate,@RequestParam("itemGroupName")String itemGroupName
	    		,@RequestParam("reorderName") String reorderName,@RequestParam("itemClass") String itemClass,@RequestParam("status") 
	    		String status,ModelAndView modelAndView, HttpServletResponse response, HttpServletRequest request,@RequestParam("itemCategoryName")String itemCategoryName,@RequestParam("activeStatus")String activeStatus) 
			 {
	    	
	    	logger.debug("Received request to download HTML report");
			Map result = new HashMap();
			try
			{
				Connection conn = dataSource.getConnection();
		        // Get the jasper report object 
		        InputStream reportStream = this.getClass().getResourceAsStream("/reports/Short_Inventory_Report.jasper");
		        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);
		        
		        Map<String,Object> parameterMap = new HashMap<String,Object>();
		        parameterMap.put("datasource", dataSource);
							
			        // Populate report with data
			     //   if( map.get("fromDate")!=null && ! map.get("fromDate").equals("All")){
			        
		            parameterMap.put("DatePrompt", fromDate);
			    	//	}
		            if(itemCategoryName!=null && !itemCategoryName.equals("All")){
						parameterMap.put("ItemCategoryPrompt", itemCategoryName);
						}
			        if( map.get("itemGroupName")!=null && ! map.get("itemGroupName").equals("All")){
			        	parameterMap.put("GroupNamePrompt", map.get("itemGroupName"));
			    		}
			        if( map.get("reorderName")!=null && ! map.get("reorderName").equals("All")){
			        	parameterMap.put("ReorderPrompt", map.get("reorderName"));
			    		}
			        if( map.get("itemClass")!=null && ! map.get("itemClass").equals("All")){
			        	parameterMap.put("ClassPrompt", map.get("itemClass"));
			    		}
			        if( map.get("status")!=null){
			        	parameterMap.put("StatusPrompts", map.get("status"));
			    		}
			        int i=Integer.parseInt(activeStatus);
					parameterMap.put("Item_Status_Prompt", i);
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
		        allowedHtml.addTags("table", "tr", "td", "span");
		        allowedHtml.addTags("table", "style", "cellpadding", "cellspacing", "border", "bgcolor");
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
