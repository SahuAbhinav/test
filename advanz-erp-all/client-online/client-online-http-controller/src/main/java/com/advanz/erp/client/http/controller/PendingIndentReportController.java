package com.advanz.erp.client.http.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
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
import net.sf.jasperreports.engine.export.JRPdfExporter;
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

import com.advanz.erp.client.http.controller.form.SalesReportForm;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.msg.ItemCategoryInputMessage;
import com.advanz.erp.masters.model.msg.ItemCategoryOutMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.model.msg.PartyOutMessage;
import com.advanz.erp.masters.service.business.IItemCategoryService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IPartyService;
import com.advanz.erp.masters.service.business.ISalesOrderMasterService;

/**
 * Handles and retrieves download request
 */
@Controller
public class PendingIndentReportController extends BaseController {

	protected static Logger logger = Logger
			.getLogger(PendingIndentReportController.class);
	@Autowired
	DataSource dataSource;

	@Autowired
	IItemService itemService;
	@Autowired 
	IPartyService partyService;
	@Autowired
	IItemCategoryService itemcategoryService;
	@Autowired
	ISalesOrderMasterService salesOrderMasterService;
	@Autowired
	public IMastersService mastersService;
	public static String ALL = "All";
	@RequestMapping(value = "/pending_indent_report", method = RequestMethod.GET)
	public String getDownloadPage(@ModelAttribute("salesReportForm") SalesReportForm salesReportForm,Model model) {
		logger.debug("Received request to show download page");
		Date date= salesOrderMasterService.getMinimumPendingDate();
		salesReportForm=new SalesReportForm();
		ItemOutMessage itemOutMessage= itemService.getItemIdAndItemNameList();
		ArrayList<ItemDTO>  itemList= (ArrayList<ItemDTO>)itemOutMessage.getItemDTOList();
		
		PartyOutMessage partyOutMessage= partyService.findAllPartys();
		ArrayList<PartyDTO> partyList=(ArrayList<PartyDTO>)partyOutMessage.getPartyDTOList();
		ItemCategoryOutMessage itemcategoryOutMessage = itemcategoryService.findAllItemCategories();
		ArrayList<ItemCategoryDTO> itemcategoryList = (ArrayList<ItemCategoryDTO>) itemcategoryOutMessage.getItemCategoryDTOList();
		salesReportForm.setFromDate(date);
		model.addAttribute("salesReportForm",salesReportForm);
		model.addAttribute("partyList", partyList);
		model.addAttribute("itemList", itemList);
		model.addAttribute("itemcategoryList", itemcategoryList);
		
		MastersOutputMessage outMsg = null;
		MastersInputMessage inMsg = new MastersInputMessage();
		inMsg.setFormId(8);
		outMsg = mastersService.findFormById(inMsg);
		List<MastersDTO> deptTypeList = outMsg.getMastersDTOList();
		
		model.addAttribute("deptTypeList", deptTypeList);
		model.addAttribute("date", date);
		return "pending_indent_report_view";
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pending_indent_report/xls", method = RequestMethod.GET)
	public ModelAndView doSalesReportXLS(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("itemName") String itemName,@RequestParam("pendingStatus") String pendingStatus,@RequestParam("itemcategory") String itemcategory,@RequestParam("activeStatus") String activeStatus,@RequestParam("raisedBy") String raisedBy,@RequestParam("departmentName") String departmentName,   
			HttpServletResponse response) {
		logger.debug("Received request to download Excel report");
		
		response.setHeader("filename", "pending_indent_report.xls");
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("FromDatePrompt", fromDate);
		parameterMap.put("ToDatePrompt", toDate);
		if(itemName!=null && !itemName.equals("All")){
			parameterMap.put("ItemNamePrompt", itemName);
			}
			if(pendingStatus!=null && !pendingStatus.equals("All")){
				parameterMap.put("PendingPrompt", pendingStatus);
				}
			if(itemcategory!=null && !itemcategory.equals("All")){
				Integer categoryId=Integer.parseInt(itemcategory);
				itemcategory=getCategoryName(categoryId);
				parameterMap.put("ItemCategoryPrompt", itemcategory);
				}
			if(activeStatus!=null && !activeStatus.equals("All")){
				parameterMap.put("Item_Status_Prompt", Integer.parseInt(activeStatus));
				}
			if(departmentName!=null && !departmentName.equals("All")){
				parameterMap.put("DepartmentPrompt", departmentName);
				}
			if(raisedBy!=null && !raisedBy.equals("All")){
				parameterMap.put("RaisedByPrompt", raisedBy);
				}
				
		modelAndView = new ModelAndView("xlsPendingIndent", parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pending_indent_report/pdf", method = RequestMethod.GET)
	public ModelAndView doSalesReportPDF(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("itemName") String itemName,@RequestParam("pendingStatus") String pendingStatus,@RequestParam("itemcategory") String itemcategory,@RequestParam("activeStatus") String activeStatus,@RequestParam("raisedBy") String raisedBy,@RequestParam("departmentName") String departmentName,   
			HttpServletResponse response, HttpServletRequest request) {
		logger.debug("Received request to download PDF report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "pending_gate_pass.pdf");
		response.setContentType("application/pdf");
		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		MailReport pdf=new MailReport();
		try{
		//mailSend();
		}catch (Exception e) {
		}
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("FromDatePrompt", fromDate);
		parameterMap.put("ToDatePrompt", toDate);
		if(itemName!=null && !itemName.equals("All")){
			parameterMap.put("ItemNamePrompt", itemName);
			}
			if(pendingStatus!=null && !pendingStatus.equals("All")){
				parameterMap.put("PendingPrompt", pendingStatus);
				}
			if(itemcategory!=null && !itemcategory.equals("All")){
				Integer categoryId=Integer.parseInt(itemcategory);
				itemcategory=getCategoryName(categoryId);
				parameterMap.put("ItemCategoryPrompt", itemcategory);
				}
			if(activeStatus!=null && !activeStatus.equals("All")){
				parameterMap.put("Item_Status_Prompt", Integer.parseInt(activeStatus));
				}
			if(departmentName!=null && !departmentName.equals("All")){
				parameterMap.put("DepartmentPrompt", departmentName);
				}
			if(raisedBy!=null && !raisedBy.equals("All")){
				parameterMap.put("RaisedByPrompt", raisedBy);
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
		modelAndView = new ModelAndView("pdfPendingIndent", parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pending_indent_report/csv", method = RequestMethod.GET)
	public ModelAndView doSalesReportCSV(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("itemName") String itemName,@RequestParam("pendingStatus") String pendingStatus,@RequestParam("itemcategory") String itemcategory,@RequestParam("activeStatus") String activeStatus,@RequestParam("raisedBy") String raisedBy,@RequestParam("departmentName") String departmentName,   
			HttpServletResponse response) {
		logger.debug("Received request to download CSV report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "pending_gate_pass.csv");

		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("FromDatePrompt", fromDate);
		parameterMap.put("ToDatePrompt", toDate);
		if(itemName!=null && !itemName.equals("All")){
			parameterMap.put("ItemNamePrompt", itemName);
			}
			if(pendingStatus!=null && !pendingStatus.equals("All")){
				parameterMap.put("PendingPrompt", pendingStatus);
				}
			if(itemcategory!=null && !itemcategory.equals("All")){
				Integer categoryId=Integer.parseInt(itemcategory);
				itemcategory=getCategoryName(categoryId);
				parameterMap.put("ItemCategoryPrompt", itemcategory);
				}
			if(activeStatus!=null && !activeStatus.equals("All")){
				parameterMap.put("Item_Status_Prompt", Integer.parseInt(activeStatus));
				}
			if(departmentName!=null && !departmentName.equals("All")){
				parameterMap.put("DepartmentPrompt", departmentName);
				}
			if(raisedBy!=null && !raisedBy.equals("All")){
				parameterMap.put("RaisedByPrompt", raisedBy);
				}
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("csvPendingIndent", parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves HTML and show it in the screen
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pending_indent_report/html", method = RequestMethod.POST)
	public @ResponseBody
	Map showHTMLReport(ModelAndView modelAndView,@RequestParam("fromDate") Date fromDate,@RequestParam("toDate") Date toDate,@RequestParam("itemName") String itemName,@RequestParam("pendingStatus") String pendingStatus,@RequestParam("itemcategory") String itemcategory,@RequestParam("activeStatus") String activeStatus,@RequestParam("raisedBy") String raisedBy,@RequestParam("departmentName") String departmentName) {
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
					"/reports/Pending_Indent_Report.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(reportStream);

			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("FromDatePrompt", fromDate);
			parameterMap.put("ToDatePrompt", toDate);
			if(itemName!=null && !itemName.equals("All")){
				parameterMap.put("ItemNamePrompt", itemName);
				}
				if(pendingStatus!=null && !pendingStatus.equals("All")){
					parameterMap.put("PendingPrompt", pendingStatus);
					}
				if(itemcategory!=null && !itemcategory.equals("All")){
					Integer categoryId=Integer.parseInt(itemcategory);
					itemcategory=getCategoryName(categoryId);
					parameterMap.put("ItemCategoryPrompt", itemcategory);
					}
					if(activeStatus!=null && !activeStatus.equals("All")){
						
					parameterMap.put("Item_Status_Prompt",Integer.parseInt(activeStatus) );
					}
					
					if(departmentName!=null && !departmentName.equals("All")){
						parameterMap.put("DepartmentPrompt", departmentName);
						}
					if(raisedBy!=null && !raisedBy.equals("All")){
						parameterMap.put("RaisedByPrompt", raisedBy);
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
			exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,Boolean.FALSE);

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
	 
	 
	 public void mailSend() {
			File pdffile = null;
			
			Date fromDate=null;
			Date toDate=null;
			String itemName=null;
			String pendingStatus=null;
			String itemcategory=null;
			String activeStatus=null;
			
			
			try {
				Connection conn = dataSource.getConnection();
				// Get the jasper report object
				// Load it
				InputStream reportStream = this.getClass().getResourceAsStream(
						"/reports/Pending_Indent_Report.jasper");
				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);

				
				File directory = new File(".");
				String reportName="ABC.pdf";
				URL reportFileURL = getClass().getResource(
						File.separator + "Reports" + File.separator + reportName
								+ "_schedule.jasper");

				pdffile = new File(directory.getCanonicalPath() + File.separator
						+ reportName + ".pdf");
				
				
				Map<String, Object> parameterMap = new HashMap<String, Object>();
				parameterMap.put("FromDatePrompt", fromDate);
				parameterMap.put("ToDatePrompt", toDate);
				if(itemName!=null && !itemName.equals("All")){
					parameterMap.put("ItemNamePrompt", itemName);
					}
					if(pendingStatus!=null && !pendingStatus.equals("All")){
						parameterMap.put("PendingPrompt ", pendingStatus);
						}
					
						if(activeStatus!=null && !activeStatus.equals("All")){
						parameterMap.put("Item_Status_Prompt", Integer.parseInt(activeStatus));
						}
							
				JasperPrint jasperPrint = JasperFillManager.fillReport(
						jasperReport, parameterMap, conn);
				// Create report exporter to be in Html
				JRExporter exporter;
				exporter = new JRPdfExporter();
				FileOutputStream fos = new FileOutputStream(pdffile);
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
				exporter.exportReport();
				// Export the report, store to sb
				exporter.exportReport();

				conn.close();
				 String []emailIds=new String[1]; 
				 emailIds[0]="anildhkd26@gmail.com";
				//MailPdf mail = new MailPdf(pdffile.getName());
				 MailPdf mail = new MailPdf(pdffile,reportName);
				mail.sendSSLMessage(emailIds, pdffile.getName(), "","abc@gmail.com");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//return result;
		}
	 
}