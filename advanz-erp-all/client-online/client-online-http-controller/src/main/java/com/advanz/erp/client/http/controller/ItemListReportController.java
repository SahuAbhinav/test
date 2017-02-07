package com.advanz.erp.client.http.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
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

import com.advanz.erp.client.http.controller.form.ItemReportFrom;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.msg.ItemCategoryInputMessage;
import com.advanz.erp.masters.model.msg.ItemCategoryOutMessage;
import com.advanz.erp.masters.model.msg.ItemGroupFlagInputMessage;
import com.advanz.erp.masters.model.msg.ItemGroupFlagOutMessage;
import com.advanz.erp.masters.model.msg.ItemGroupInputMessage;
import com.advanz.erp.masters.model.msg.ItemGroupOutMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.service.business.IItemCategoryService;
import com.advanz.erp.masters.service.business.IItemGroupFlagService;
import com.advanz.erp.masters.service.business.IItemGroupService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;

/**
 * Handles and retrieves download request
 */
@Controller
public class ItemListReportController {

	protected static Logger logger = Logger
			.getLogger(ItemListReportController.class);
	@Autowired
	DataSource dataSource;

	@Autowired
	IItemGroupService itemgroupService;

	@Autowired
	IItemCategoryService itemcategoryService;

	@Autowired
	IItemGroupFlagService itemGroupFlagService;
	public static String ALL = "All";

	@Autowired
	IMastersService mastersService;
	@Autowired
	IItemGroupService itemGroupService;
	@Autowired
	IItemService itemService;

	@Autowired
	IItemCategoryService itemCategoryService;

	/**
	 * Handles and retrieves the reports page. The zones will be pre-populated
	 * with this page.
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/item_list_report", method = RequestMethod.GET)
	public String getDownloadPage(Model model) {
		logger.debug("Received request to show download page");

		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(16);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		ArrayList<MastersDTO> masterList = (ArrayList<MastersDTO>) mastersOutputMessage
				.getMastersDTOList();

		ItemGroupFlagOutMessage flagOutMessage = itemGroupFlagService
				.findAllItemGroupFlag();
		ArrayList<ItemGroupFlagDTO> itemGroupFlagList = (ArrayList<ItemGroupFlagDTO>) flagOutMessage
				.getItemGroupFlagDTOList();

		ItemGroupOutMessage itemgroupOutMessage = itemgroupService
				.findAllItemGroup();
		ArrayList<ItemGroupDTO> itemgroupList = (ArrayList<ItemGroupDTO>) itemgroupOutMessage
				.getItemGroupDTOList();

		ItemCategoryOutMessage itemcategoryOutMessage = itemcategoryService
				.findAllItemCategories();
		ArrayList<ItemCategoryDTO> itemcategoryList = (ArrayList<ItemCategoryDTO>) itemcategoryOutMessage
				.getItemCategoryDTOList();

		MastersInputMessage mastersInputMessage1 = new MastersInputMessage();
		mastersInputMessage1.setFormId(13);
		MastersOutputMessage mastersOutputMessage2 = mastersService
				.findFormByIdForMelterLog(mastersInputMessage1);
		List<MastersDTO> masterList2 = mastersOutputMessage2
				.getMastersDTOList();

		ItemReportFrom itemReportFrom = new ItemReportFrom();

		model.addAttribute("itemReportFrom", itemReportFrom);

		model.addAttribute("itemgroupList", itemgroupList);
		model.addAttribute("itemcategoryList", itemcategoryList);
		model.addAttribute("masterList", masterList);
		model.addAttribute("itemGroupFlagList", itemGroupFlagList);
		model.addAttribute("masterList2", masterList2);
		return "item_list_report_view";
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/item_list_report/xls", method = RequestMethod.GET)
	public ModelAndView doSalesReportXLS(ModelAndView modelAndView,
			@RequestParam("itemGroup") String itemGroup,
			@RequestParam("itemcategory") String itemcategory,
			@RequestParam("itemGroupFlag") String itemGroupFlag,
			@RequestParam("grade") String grade,
			@RequestParam("classType") String classType,
			@RequestParam("activeStatus") String activeStatus,
			HttpServletResponse response) {
		logger.debug("Received request to download Excel report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "item_list_report.xls");
		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);

		if (!itemGroup.equals("All")) {
			parameterMap.put("Item_Group", itemGroup);
		}
		if (!itemcategory.equals("All")) {
			parameterMap.put("Category", itemcategory);
		}
		if (!itemGroupFlag.equals("All") && itemGroupFlag != null) {
			parameterMap.put("Item_Group_Flag", itemGroupFlag);
		}
		if (!grade.equals("All") && grade != null) {
			parameterMap.put("Grade", grade);
		}
		if (!classType.equals("All") && classType != null) {
			parameterMap.put("Class", classType);
		}
		if (!activeStatus.equals("All") && activeStatus != null) {
			int i = Integer.parseInt(activeStatus);
			parameterMap.put("Item_Status_Prompt", i);
		}

		// xlsReport is the View of our application
		// This is declared inside the views xml file
		modelAndView = new ModelAndView("xlsItem", parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/item_list_report/pdf", method = RequestMethod.GET)
	public ModelAndView doSalesReportPDF(ModelAndView modelAndView,
			@RequestParam("itemGroup") String itemGroup,
			@RequestParam("itemcategory") String itemcategory,
			@RequestParam("itemGroupFlag") String itemGroupFlag,
			@RequestParam("grade") String grade,
			@RequestParam("classType") String classType,
			@RequestParam("activeStatus") String activeStatus,
			HttpServletResponse response, HttpServletRequest request) {
		logger.debug("Received request to download PDF report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "item_list_report.pdf");

		response.setContentType("application/pdf");
		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);
		if (itemGroup != null && !itemGroup.equals("All")) {
			parameterMap.put("Item_Group", itemGroup);
		}
		if (itemcategory != null && !itemcategory.equals("All")) {
			parameterMap.put("Category", itemcategory);
		}
		if (!itemGroupFlag.equals("All") && itemGroupFlag != null) {
			parameterMap.put("Item_Group_Flag", itemGroupFlag);
		}
		if (!grade.equals("All") && grade != null) {
			parameterMap.put("Grade", grade);
		}
		if (!classType.equals("All") && classType != null) {
			parameterMap.put("Class", classType);
		}
		if (!activeStatus.equals("All") && activeStatus != null) {
			int i = Integer.parseInt(activeStatus);
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
		modelAndView = new ModelAndView("pdfItem", parameterMap);
		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/item_list_report/csv", method = RequestMethod.GET)
	public ModelAndView doSalesReportCSV(ModelAndView modelAndView,
			@RequestParam("itemGroup") String itemGroup,
			@RequestParam("itemcategory") String itemcategory,
			@RequestParam("itemGroupFlag") String itemGroupFlag,
			@RequestParam("grade") String grade,
			@RequestParam("classType") String classType,
			@RequestParam("activeStatus") String activeStatus,
			HttpServletResponse response) {
		logger.debug("Received request to download CSV report");
		// response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename", "item_list_report.csv");

		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		if (!itemGroup.equals("All")) {
			parameterMap.put("Item_Group", itemGroup);
		}
		if (!itemcategory.equals("All")) {
			parameterMap.put("Category", itemcategory);
		}
		if (!itemGroupFlag.equals("All") && itemGroupFlag != null) {
			parameterMap.put("Item_Group_Flag", itemGroupFlag);
		}
		if (!grade.equals("All") && grade != null) {
			parameterMap.put("Grade", grade);
		}
		if (!classType.equals("All") && classType != null) {
			parameterMap.put("Class", classType);
		}
		if (!activeStatus.equals("All") && activeStatus != null) {
			int i = Integer.parseInt(activeStatus);
			parameterMap.put("Item_Status_Prompt", i);
		}

		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("csvItem", parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves HTML and show it in the screen
	 * 
	 * @return
	 */
	@RequestMapping(value = "/item_list_report/html", method = RequestMethod.POST)
	public @ResponseBody Map showHTMLReport(ModelAndView modelAndView,
			@RequestParam("itemGroup") String itemGroup,
			@RequestParam("itemcategory") String itemcategory,
			@RequestParam("itemGroupFlag") String itemGroupFlag,
			@RequestParam("grade") String grade,
			@RequestParam("classType") String classType,
			@RequestParam("activeStatus") String activeStatus) {
		logger.debug("Received request to download HTML report");

		Map result = new HashMap();
		try {
			Connection conn = dataSource.getConnection();

			// Get the jasper report object
			// Load it
			InputStream reportStream = this.getClass().getResourceAsStream(
					"/reports/Item_List.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(reportStream);

			Map<String, Object> parameterMap = new HashMap<String, Object>();
			if (itemGroup != null && !itemGroup.equals("All")) {
				parameterMap.put("Item_Group", itemGroup);
			}
			if (itemcategory != null && !itemcategory.equals("All")) {
				parameterMap.put("Category", itemcategory);
			}

			if (!itemGroupFlag.equals("All") && itemGroupFlag != null) {
				parameterMap.put("Item_Group_Flag", itemGroupFlag);
			}
			if (!grade.equals("All") && grade != null) {
				parameterMap.put("Grade", grade);
			}
			if (!classType.equals("All") && classType != null) {
				parameterMap.put("Class", classType);
			}
			if (!activeStatus.equals("All") && activeStatus != null) {
				int i = Integer.parseInt(activeStatus);
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

	@RequestMapping(value = "/get_item_category_by_group", method = RequestMethod.POST)
	@ResponseBody
	JsonResponse getItemName(@RequestParam("itemGroupId") Integer itemGroupId) {
		ItemCategoryInputMessage categoryInputMessage = new ItemCategoryInputMessage();
		ItemCategoryDTO categoryDTO = new ItemCategoryDTO();
		ItemGroupDTO itemGroupDTO = new ItemGroupDTO();
		itemGroupDTO.setItemGroupId(itemGroupId);
		categoryDTO.setItemGroupDTO(itemGroupDTO);
		categoryInputMessage.setItemCategoryDTO(categoryDTO);
		ItemCategoryOutMessage categoryOutMessage = itemcategoryService
				.findItemCategoryByItemGroup(categoryInputMessage);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setStatus("SUCCESS");
		jsonResponse.setResult(categoryOutMessage.getItemCategoryDTOList());
		return jsonResponse;
	}

	@RequestMapping(value = "/get_category_name_for_report", method = RequestMethod.POST)
	@ResponseBody
	JsonResponse getItemName() {
		ItemCategoryOutMessage categoryOutMessage = itemcategoryService
				.findAllItemCategories();
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setStatus("SUCCESS");
		jsonResponse.setResult(categoryOutMessage.getItemCategoryDTOList());
		return jsonResponse;
	}

	@RequestMapping(value = "/get_item_group_name", method = RequestMethod.POST)
	@ResponseBody
	JsonResponse getItemGroupName(
			@RequestParam("itemGroupFlagName") String itemGroupFlagName) {
		ItemGroupFlagInputMessage flagInputMessage = new ItemGroupFlagInputMessage();
		ItemGroupFlagDTO dto = new ItemGroupFlagDTO();
		dto.setItemGroupFlagName(itemGroupFlagName);
		flagInputMessage.setItemGroupFlagDTO(dto);
		ItemGroupFlagOutMessage flagOutMessage = itemGroupFlagService
				.findItemGroupFlagByName(flagInputMessage);
		Integer flagId = flagOutMessage.getItemGroupFlagDTOList().get(0)
				.getItemGroupFlagId();

		ItemGroupInputMessage itemGroupInputMessage = new ItemGroupInputMessage();
		ItemGroupDTO itemGroupDTO = new ItemGroupDTO();
		itemGroupDTO.setItemGroupFlagId(flagId);
		itemGroupInputMessage.setItemGroupDTO(itemGroupDTO);
		ItemGroupOutMessage groupOutMessage = itemGroupService
				.findItemGroupByFlagId(itemGroupInputMessage);

		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setStatus("SUCCESS");
		jsonResponse.setResult(groupOutMessage.getItemGroupDTOList());

		return jsonResponse;
	}

	@RequestMapping(value = "/get_category_group_flag_name", method = RequestMethod.POST)
	@ResponseBody
	JsonResponse getItemCategoryByGroupFlagName(
			@RequestParam("itemGroupFlagName") String itemGroupFlagName) {
		ItemGroupFlagInputMessage flagInputMessage = new ItemGroupFlagInputMessage();
		JsonResponse jsonResponse = new JsonResponse();

		ItemGroupFlagDTO dto = new ItemGroupFlagDTO();
		dto.setItemGroupFlagName(itemGroupFlagName);
		flagInputMessage.setItemGroupFlagDTO(dto);
		ItemGroupFlagOutMessage flagOutMessage = itemGroupFlagService
				.findItemGroupFlagByName(flagInputMessage);
		if (flagOutMessage.getItemGroupFlagDTOList() != null
				&& flagOutMessage.getItemGroupFlagDTOList().size() > 0) {
		Integer flagId = flagOutMessage.getItemGroupFlagDTOList().get(0)
				.getItemGroupFlagId();
		
			ItemGroupInputMessage itemGroupInputMessage = new ItemGroupInputMessage();
			ItemGroupDTO itemGroupDTO = new ItemGroupDTO();
			itemGroupDTO.setItemGroupFlagId(flagId);
			itemGroupInputMessage.setItemGroupDTO(itemGroupDTO);
			ItemGroupOutMessage groupOutMessage = itemGroupService
					.findItemGroupByFlagId(itemGroupInputMessage);

			if (groupOutMessage.getItemGroupDTOList() != null
					&& groupOutMessage.getItemGroupDTOList().size() > 0) {
				Integer itemGroupId = groupOutMessage.getItemGroupDTOList()
						.get(0).getItemGroupId();
				ItemCategoryInputMessage categoryInputMessage = new ItemCategoryInputMessage();
				ItemCategoryDTO categoryDTO = new ItemCategoryDTO();
				ItemGroupDTO itemGroupDTO1 = new ItemGroupDTO();
				itemGroupDTO1.setItemGroupId(itemGroupId);
				categoryDTO.setItemGroupDTO(itemGroupDTO1);
				categoryInputMessage.setItemCategoryDTO(categoryDTO);

				ItemCategoryOutMessage categoryOutMessage = itemCategoryService
						.findItemCategoryByItemGroup(categoryInputMessage);

				jsonResponse.setStatus("SUCCESS");
				ArrayList<ItemCategoryDTO> itemcategoryList = (ArrayList<ItemCategoryDTO>) categoryOutMessage
						.getItemCategoryDTOList();
				jsonResponse.setResult(itemcategoryList);
			}
		}
		return jsonResponse;
	}

	@ResponseBody
	@RequestMapping(value = "/get_item_group_list", method = RequestMethod.POST)
	public JsonResponse getItemGroupName(Model model) {
		logger.debug("Received request to show download page");

		ItemGroupOutMessage itemGroupOutMessage = itemGroupService
				.findAllItemGroup();
		ArrayList<ItemGroupDTO> itemGroupList = (ArrayList<ItemGroupDTO>) itemGroupOutMessage
				.getItemGroupDTOList();

		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setStatus("SUCCESS");
		jsonResponse.setResult(itemGroupList);

		return jsonResponse;
	}

	@RequestMapping(value = "/get_item_category_by_ItemGroupflagId", method = RequestMethod.POST)
	@ResponseBody
	JsonResponse getItemCategoryByItemGroupFlagId(
			@RequestParam("itemGroupflagId") Integer itemGroupFlagId) {
		ItemCategoryInputMessage categoryInputMessage = new ItemCategoryInputMessage();
		ItemCategoryDTO categoryDTO = new ItemCategoryDTO();
		ItemGroupDTO itemGroupDTO = new ItemGroupDTO();
		itemGroupDTO.setItemGroupId(itemGroupFlagId);
		categoryDTO.setItemGroupDTO(itemGroupDTO);
		categoryInputMessage.setItemCategoryDTO(categoryDTO);

		ItemCategoryOutMessage categoryOutMessage = itemCategoryService
				.findItemCategoryByItemGroup(categoryInputMessage);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setStatus("SUCCESS");
		ArrayList<ItemCategoryDTO> itemcategoryList = (ArrayList<ItemCategoryDTO>) categoryOutMessage
				.getItemCategoryDTOList();
		jsonResponse.setResult(itemcategoryList);

		return jsonResponse;
	}

	@RequestMapping(value = "/get_item_by_ItemGroupflagId", method = RequestMethod.POST)
	@ResponseBody
	JsonResponse getItemByItemGroupFlagId(
			@RequestParam("itemGroupflagId") Integer itemGroupFlagId) {

		ItemInputMessage itemInputMessage = new ItemInputMessage();
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemGroupFlagId(itemGroupFlagId);
		itemInputMessage.setItemDTO(itemDTO);
		ItemOutMessage itemOutMessage = itemService
				.findItemForReportByGroupName(itemInputMessage);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setStatus("SUCCESS");
		jsonResponse.setResult(itemOutMessage.getItemDTOList());
		return jsonResponse;
	}

	// ............
	@RequestMapping(value = "/get_item_by_ItemGroupflagName", method = RequestMethod.POST)
	@ResponseBody
	JsonResponse getItemByItemGroupFlageName(
			@RequestParam("itemGroupflagName") String itemGroupflagName) {
		ItemGroupFlagDTO flagDTO = new ItemGroupFlagDTO();
		flagDTO.setItemGroupFlagName(itemGroupflagName);
		ItemGroupFlagInputMessage flagInputMessage = new ItemGroupFlagInputMessage();
		flagInputMessage.setItemGroupFlagDTO(flagDTO);
		ItemGroupFlagOutMessage itemGroupFlagId = itemGroupFlagService
				.findItemGroupFlagByName(flagInputMessage);
		List l = itemGroupFlagId.getItemGroupFlagDTOList();
		if (l != null && l.size() > 0) {
			flagDTO = (ItemGroupFlagDTO) l.get(0);
		}

		ItemInputMessage itemInputMessage = new ItemInputMessage();
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemGroupFlagId(flagDTO.getItemGroupFlagId());
		itemInputMessage.setItemDTO(itemDTO);
		ItemOutMessage itemOutMessage = itemService
				.findItemForReportByGroupName(itemInputMessage);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setStatus("SUCCESS");
		jsonResponse.setResult(itemOutMessage.getItemDTOList());
		return jsonResponse;
	}

	@RequestMapping(value = "/get_item_by_ItemGroupId", method = RequestMethod.POST)
	@ResponseBody
	JsonResponse getItemByItemGroupId(
			@RequestParam("itemGroupId") Integer itemGroupId) {
		ItemGroupDTO itemGroupDTO = new ItemGroupDTO();
		itemGroupDTO.setItemGroupId(itemGroupId);
		ItemGroupInputMessage itemGroupInputMessage = new ItemGroupInputMessage();
		itemGroupInputMessage.setItemGroupDTO(itemGroupDTO);
		ItemGroupOutMessage groupOutMessage = itemGroupService
				.findItemGroupById(itemGroupInputMessage);
		List l = groupOutMessage.getItemGroupDTOList();
		if (l != null && l.size() > 0) {
			itemGroupDTO = (ItemGroupDTO) l.get(0);
		}
		//System.out.println("GROUP FLAGE ID :"+ itemGroupDTO.getItemGroupFlagId());
		ItemInputMessage itemInputMessage = new ItemInputMessage();
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemGroupFlagId(itemGroupDTO.getItemGroupFlagId());
		itemInputMessage.setItemDTO(itemDTO);
		ItemOutMessage itemOutMessage = itemService
				.findItemForReportByGroupName(itemInputMessage);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setStatus("SUCCESS");
		jsonResponse.setResult(itemOutMessage.getItemDTOList());
		return jsonResponse;
	}

	// ..................

	@RequestMapping(value = "/get_item_by_ItemCategoryId", method = RequestMethod.POST)
	@ResponseBody
	JsonResponse getItemByItemCategoryId(
			@RequestParam("itemCategoryId") Integer itemCategoryId) {

		ItemInputMessage itemInputMessage = new ItemInputMessage();
		ItemDTO itemDTO = new ItemDTO();
		ItemCategoryDTO categoryDTO = new ItemCategoryDTO();
		categoryDTO.setItemCategoryId(itemCategoryId);
		itemDTO.setItemCategoryDTO(categoryDTO);
		itemInputMessage.setItemDTO(itemDTO);
		ItemOutMessage itemOutMessage = itemService
				.getListByItemCategory(itemInputMessage);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setStatus("SUCCESS");
		jsonResponse.setResult(itemOutMessage.getItemDTOList());
		return jsonResponse;
	}

}