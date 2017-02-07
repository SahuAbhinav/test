package com.advanz.erp.client.http.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.advanz.erp.client.http.controller.form.GeographicReportFrom;
import com.advanz.erp.masters.model.AreaDTO;
import com.advanz.erp.masters.model.RegionDTO;
import com.advanz.erp.masters.model.StateDTO;
import com.advanz.erp.masters.model.ZoneDTO;
import com.advanz.erp.masters.model.msg.AreaInputMessage;
import com.advanz.erp.masters.model.msg.AreaOutputMessage;
import com.advanz.erp.masters.model.msg.RegionInputMessage;
import com.advanz.erp.masters.model.msg.RegionOutputMessage;
import com.advanz.erp.masters.model.msg.StateInputMessage;
import com.advanz.erp.masters.model.msg.StateOutputMessage;
import com.advanz.erp.masters.model.msg.ZoneOutputMessage;
import com.advanz.erp.masters.service.business.IAreaService;
import com.advanz.erp.masters.service.business.IRegionService;
import com.advanz.erp.masters.service.business.IStateService;
import com.advanz.erp.masters.service.business.IZoneService;


/**
 * Handles and retrieves download request
 */
@Controller
public class GeographicReportController {

	protected static Logger logger = Logger.getLogger(GeographicReportController.class);
	@Autowired
	DataSource dataSource;
	@Autowired
	IZoneService zoneService;
	@Autowired
	IStateService stateService;
	@Autowired
	IRegionService regionService;
	@Autowired 
	IAreaService areaService;
	
	public static String ALL = "All";
	
	/**
	 * Handles and retrieves the reports page. The zones will be pre-populated with this page.
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/geographic_report", method = RequestMethod.GET)
    public String getDownloadPage(Model model) {
    	logger.debug("Received request to show download page");
    	//Finding all the zones
    	ZoneOutputMessage zonesOutputMsg = zoneService.findAllZones();
    	GeographicReportFrom geographicReportFrom = new GeographicReportFrom();
    	List<ZoneDTO>  list = zonesOutputMsg.getZoneDTOList();
    	model.addAttribute("zones", list);
    	//model.addAttribute("georaphicReportForm",geographicReportFrom );
    	model.addAttribute("zone",new ZoneDTO());
    	model.addAttribute("state",new StateDTO());
    	model.addAttribute("region",new RegionDTO());
    	model.addAttribute("area",new AreaDTO());
    	return "geographic_report_view";
	}
    /**
     * The method is used to fetch all the regions belonging to a state.
     * @param zone
     * @return
     */
    @RequestMapping(value = "/geographic_report/getStates", method = RequestMethod.POST)
    public @ResponseBody List<StateDTO> getStates(@RequestBody Map map ){
    	
    	String zone = (String)map.get("zone");
    	
    	List<StateDTO>   states = new ArrayList<StateDTO>();
    	if(zone !=null && !ALL.equalsIgnoreCase(zone)){
	    	StateInputMessage stateInputMessage = new StateInputMessage();
	    	ZoneDTO zoneDto = new ZoneDTO();
	    	zoneDto.setZoneId(Integer.parseInt((String)map.get("zone")));
	    	StateDTO stateDto = new StateDTO();
	    	stateDto.setZoneDTO(zoneDto) ;
	    	stateInputMessage.setStateDTO(stateDto);
	    	StateOutputMessage stateOutputMessage = stateService.findStatesByZoneId(stateInputMessage); 
	    	states = stateOutputMessage.getStateDTOList();
    	}
    	return states;
    } 
    
    /**
     * The method is used to fetch all the regions belonging to a state.
     * @param zone
     * @return
     */
    @RequestMapping(value = "geographic_report/getRegions", method = RequestMethod.POST)
    public @ResponseBody List<RegionDTO> getRegions(@RequestBody Map map ){
    	
    	String state = (String)map.get("state");
    	List <RegionDTO> regions = new ArrayList<RegionDTO>();
    	
    	if(state != null && !ALL.equalsIgnoreCase(state)) {
	    	RegionInputMessage regionInputMessage = new RegionInputMessage();
	    	RegionDTO region  = new RegionDTO();
	    	try{
	    	region.setStateId(Integer.parseInt(state));
	    	}catch (Exception e) {
			}
	    	regionInputMessage.setRegionDTO(region);
	    	RegionOutputMessage   regionMsg = regionService.findByStateId(regionInputMessage);
	    	regions = regionMsg.getRegionDTOList();
    	}
    	return regions;
    } 
    
    
    /**
     * The method is used to fetch all the regions belonging to a state.
     * @param zone
     * @return
     */
    @RequestMapping(value = "geographic_report/getAreas", method = RequestMethod.POST)
    public @ResponseBody List<AreaDTO> getAreas(@RequestBody Map map  ){
    	String regionStr = (String)map.get("region");
    	List <AreaDTO> areas = new ArrayList<AreaDTO>();
    	if(regionStr != null && !ALL.equalsIgnoreCase(regionStr)&& regionStr.length()>0 ) {
    		
    		int regionId = Integer.parseInt(regionStr);
        	AreaInputMessage areaInputMessage = new AreaInputMessage();
	    	AreaDTO area  = new AreaDTO();
	    	RegionDTO region = new RegionDTO();
	    	region.setRegionId(regionId);
	    	area.setRegionDTO(region);
	    	areaInputMessage.setAreaDTO(area);
	    	AreaOutputMessage   areaMsg = areaService.findAreaByRegionId(areaInputMessage);
	    	areas = areaMsg.getAreaDTOList();
    	}
    	return areas;
    } 
    /**
     * Retrieves the download file in XLS format
     * 
     * @return
     */
    @RequestMapping(value = "/geographic_report/xls", method = RequestMethod.GET)
    public ModelAndView doSalesReportXLS(@RequestParam("region") String region , @RequestParam("zone") String zone , @RequestParam("area") String area ,@RequestParam("state") String state , ModelAndView modelAndView, HttpServletResponse response) 
		 {
		logger.debug("Received request to download Excel report");
		//response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename","geographic_report.xls");
		// In order to use Spring's built-in Jasper support, 
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("datasource", dataSource);
		if( zone!=null && ! zone.equals("All")){
         	parameterMap.put("ZoneNamePrompt", zone);
    		}
        if( state!=null && ! state.equals("All")){
         	parameterMap.put("StateNamePrompt", state);
    		}
        if( region!=null && ! region.equals("All")){
        	
        	parameterMap.put("RegionNamePrompt", region);
    		}
        if( area!=null && !area.equals("All")){
        	
        	parameterMap.put("AreaNamePrompt",area);
    		}
         
		// xlsReport is the View of our application
		// This is declared inside the views xml file
		modelAndView = new ModelAndView("xlsGeographic", parameterMap);
		
		// Return the View and the Model combined
		return modelAndView;
	}
    
    /**
     * Retrieves the download file in XLS format
     * 
     * @return
     */
    @RequestMapping(value = "/geographic_report/pdf", method = RequestMethod.GET )
    public  ModelAndView doSalesReportPDF(@RequestParam("region") String region , @RequestParam("zone") String zone , @RequestParam("area") String area ,@RequestParam("state") String state , ModelAndView modelAndView, HttpServletResponse response) 
		 {
		logger.debug("Received request to download PDF report");
	    //response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename","geographic_report.pdf");
		
		// In order to use Spring's built-in Jasper support, 
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("datasource", dataSource);
		if( zone!=null && ! zone.equals("All")){
         	parameterMap.put("ZoneNamePrompt", zone);
    		}
        if( state!=null && ! state.equals("All")){
         	parameterMap.put("StateNamePrompt", state);
    		}
        if( region!=null && ! region.equals("All")){
        	
        	parameterMap.put("RegionNamePrompt", region);
    		}
        if( area!=null && !area.equals("All")){
        	
        	parameterMap.put("AreaNamePrompt",area);
    		}
        
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("pdfGeographic", parameterMap);
		
		// Return the View and the Model combined
		return modelAndView;
	}

    
    /**
     * Retrieves the download file in XLS format 
     * 
     * @return
     */
    @RequestMapping(value = "/geographic_report/csv", method = RequestMethod.GET)
    public ModelAndView doSalesReportCSV(@RequestParam("region") String region , @RequestParam("zone") String zone , @RequestParam("area") String area ,@RequestParam("state") String state , ModelAndView modelAndView, HttpServletResponse response) 
		 {
		logger.debug("Received request to download CSV report");
		//response.setHeader("Content-Disposition","attachment");
		response.setHeader("filename","geographic_report.csv");
		
		// In order to use Spring's built-in Jasper support, 
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		 if( zone!=null && ! zone.equals("All")){
	         	parameterMap.put("ZoneNamePrompt", zone);
	    		}
	        if( state!=null && ! state.equals("All")){
	         	parameterMap.put("StateNamePrompt", state);
	    		}
	        if( region!=null && ! region.equals("All")){
	        	
	        	parameterMap.put("RegionNamePrompt", region);
	    		}
	        if( area!=null && !area.equals("All")){
	        	
	        	parameterMap.put("AreaNamePrompt",area);
	    		}
       
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("csvGeographic", parameterMap);
		
		// Return the View and the Model combined
		return modelAndView;
	}
    
    /**
     * Retrieves HTML and show it in the screen
     * 
     * @return
     */
    
    @RequestMapping(value = "/geographic_report/html", method = RequestMethod.POST)
    public  @ResponseBody Map showHTMLReport(@RequestBody Map map, ModelAndView modelAndView) 
		 {
		logger.debug("Received request to download HTML report");
		//Map<String,Object> parameterMap = new HashMap<String,Object>();
		//parameterMap.put("datasource", dataSource);
		
		//modelAndView = new ModelAndView("csvGeographic", parameterMap);
		//System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFF"+itemGroupFlagName);
		Map result = new HashMap();
		try
		{
			Connection conn = dataSource.getConnection();
	
	        // Get the jasper report object 
	        // Load it 
	        InputStream reportStream = this.getClass().getResourceAsStream("/reports/Geography_hierarchy_Report.jasper");
	        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);
	        
	        
	        Map<String,Object> parameterMap = new HashMap<String,Object>();
	        // Populate report with data
	        if( map.get("zone")!=null && ! map.get("zone").equals("All")){
	        	parameterMap.put("ZoneNamePrompt", map.get("zone"));
	    		}
	        if( map.get("state")!=null && ! map.get("state").equals("All")){
	        	parameterMap.put("StateNamePrompt", map.get("state"));
	    		}
	        if( map.get("region")!=null && ! map.get("region").equals("All")){
	        	parameterMap.put("RegionNamePrompt", map.get("region"));
	    		}
	        if( map.get("area")!=null && ! map.get("area").equals("All")){
	        	parameterMap.put("AreaNamePrompt", map.get("area"));
	    		}
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
  /*
    
    @RequestMapping(value = "/geographic_report/html", method = RequestMethod.POST)
    public  @ResponseBody Map showHTMLReport(@RequestBody Map map,  ModelAndView modelAndView) 
		 {
    	
		logger.debug("Received request to download HTML report");
		//Map<String,Object> parameterMap = new HashMap<String,Object>();
		//parameterMap.put("datasource", dataSource);
		
		//modelAndView = new ModelAndView("csvGeographic", parameterMap);
		Map result = new HashMap();
		try
		{
			Connection conn = dataSource.getConnection();
	
	        // Get the jasper report object 
	        // Load it 
	        InputStream reportStream = this.getClass().getResourceAsStream("/reports/Geography_hierarchy_Report.jasper");
	        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);
	        
	        Map<String,Object> parameterMap = new HashMap<String,Object>();
	      
	        if( map.get("zone")!=null && ! map.get("zone").equals("All")){
	        	System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZ"+map.get("zone"));
	        	parameterMap.put("ZoneNamePrompt", map.get("zone"));
	    		}
	        if( map.get("state")!=null && ! map.get("state").equals("All")){
	        	System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSS"+map.get("state"));
	        	parameterMap.put("StateNamePrompt", map.get("state"));
	    		}
	        if( map.get("region")!=null && ! map.get("region").equals("All")){
	        	System.out.println("RRRRRRRRRRRRRERRRRRRRRR"+map.get("region"));
	        	parameterMap.put("RegionNamePrompt", map.get("region"));
	    		}
	        if( map.get("area")!=null && ! map.get("area").equals("All")){
	        	System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA"+map.get("area"));
	        	parameterMap.put("AreaNamePrompt", map.get("area"));
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
	        allowedHtml.addTags("table", "tr", "td", "span");
	        allowedHtml.addTags("table", "style", "cellpadding", "cellspacing", "border", "bgcolor");
	        allowedHtml.addAttributes("tr", "valign");
	        allowedHtml.addAttributes("td", "colspan", "style");
	        allowedHtml.addAttributes("span", "style");
	        String html = Jsoup.clean(sb.toString(), allowedHtml);
	        
	        // Add report to map
	        result.put("htmlGeographic", html);
	        conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
*/}
