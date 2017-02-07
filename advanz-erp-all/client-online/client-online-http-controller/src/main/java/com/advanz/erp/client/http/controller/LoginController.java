package com.advanz.erp.client.http.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.exolab.castor.types.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.advanz.erp.client.http.authentication.AdvanzUserServiceImpl;
import com.advanz.erp.client.http.authentication.util.UserMenuOptions;
import com.advanz.erp.client.http.authentication.util.UserRoleUtil;
import com.advanz.erp.masters.model.LoggerDTO;
import com.advanz.erp.masters.model.ModuleMenuMasterDTO;
import com.advanz.erp.masters.model.UserMasterRoleDTO;
import com.advanz.erp.masters.model.UserRoleAndRightsDTO;
import com.advanz.erp.masters.model.msg.ModuleMenuMasterInputMessage;
import com.advanz.erp.masters.model.msg.ModuleMenuMasterOutMessage;
import com.advanz.erp.masters.model.msg.UserMasterRoleInputMassage;
import com.advanz.erp.masters.model.msg.UserMasterRoleOutputMessage;
import com.advanz.erp.masters.model.msg.UserRoleAndRightsInputMessage;
import com.advanz.erp.masters.model.msg.UserRoleAndRightsOutputMessage;
import com.advanz.erp.masters.service.business.ICompanyService;
import com.advanz.erp.masters.service.business.IModuleMenuMasterService;
import com.advanz.erp.masters.service.business.IUserMasterRoleService;
import com.advanz.erp.masters.service.business.IUserRoleAndRightsService;

@Controller
public class LoginController extends BaseController{
	protected static Logger logger = Logger.getLogger(RegionListReportController.class);
	@Autowired
	AdvanzUserServiceImpl advanzUserServiceImpl;
	
	@Autowired 
	public IModuleMenuMasterService menuMasterService;
	
	@Autowired
	public IUserRoleAndRightsService iUserRoleAndRightsService;
	
	@Autowired
	public IUserMasterRoleService userMasterRoleService;
	
	@Autowired
	public ICompanyService companyService;
	
	@RequestMapping( value = "/login" , method=RequestMethod.GET )
	public String showLoginForm(Model model,@RequestParam(value="operation",required=false) String operation, HttpServletRequest request) {
		try{
		if(operation.equals("invalidate")){
			model.addAttribute("error", "Invalidate loginId/Password");
		}
		}catch (Exception e) {
		}
		
		
		request.getHeader("VIA");
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		       if (ipAddress == null) {
		            ipAddress = request.getRemoteAddr();
		       }
		       System.out.println(" .........." +ipAddress);
		    Date date=   new Date();
				   logger.getLogger("My first log" +ipAddress +" Date is : "+date);
		           logger.info("My first log IP: ..."+ipAddress +" Date is : "+date);  
		           logger.info("Hi How r u?");  
		       
		return "login";
	}
	
	@RequestMapping(value="/error404")
	public String get404() {
		return "welcome_page";
	}
	@RequestMapping(value="/error500")
	public String get500() {
		return "welcome_page1";
	}
	
	@RequestMapping( value="/welcome" , method=RequestMethod.GET )
	public String showWelcomeForm(Model model , HttpSession session,HttpServletRequest request) {
		System.out.println(getLoggedInUserName());
		
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
	       if (ipAddress == null) {
	            ipAddress = request.getRemoteAddr();
	       }
	       
		LoggerDTO logDTO= new LoggerDTO();
		logDTO.setLoginTime(new Timestamp(new java.util.Date().getTime()));
		logDTO.setRemoteAddress(ipAddress);
		
		System.out.println("created_user_id"+getCreatedUserId()+"-"+getLoggedInUserName());
		
		//Find the user and the roles and depending on that find the access and put that in session.
		Map<String, Map<String, Map<String, Boolean>>> menuOptions = getUserMenuOptions();
		
		if(getLoggedInUserName().equalsIgnoreCase("anonymousUser")){
			return "redirect:/login?operation=invalidate";
		}
		
		// Add Dynamicaly link
		
		ModuleMenuMasterInputMessage inputMessage = new ModuleMenuMasterInputMessage();
		ModuleMenuMasterDTO dto = new ModuleMenuMasterDTO();
		
		dto.setLoginUserName(getLoggedInUserName());
		dto.setModuleName("Masters");
	
		session.setAttribute("loginUserName", getLoggedInUserName());
		inputMessage.setModuleMenuMasterDTO(dto);
		ModuleMenuMasterOutMessage masterOutMessage= menuMasterService.findModuleMenuMasterByModuleName(inputMessage);
		List<ModuleMenuMasterDTO> moduleList = masterOutMessage.getModuleMenuMasterDTOList();
		moduleList=getMenuList(moduleList);
		session.setAttribute("moduleList", moduleList);
		
		dto.setModuleName("Sales & Distribution");
		inputMessage.setModuleMenuMasterDTO(dto);
	    masterOutMessage= menuMasterService.findModuleMenuMasterByModuleName(inputMessage);
		List<ModuleMenuMasterDTO> salesAndDistribution = masterOutMessage.getModuleMenuMasterDTOList();
		salesAndDistribution=getMenuList(salesAndDistribution);
		session.setAttribute("salesAndDistribution", salesAndDistribution);
		
		
		dto.setModuleName("Purchase & Inventory");
		inputMessage.setModuleMenuMasterDTO(dto);
	    masterOutMessage= menuMasterService.findModuleMenuMasterByModuleName(inputMessage);
		List<ModuleMenuMasterDTO> purchaseAndInventory = masterOutMessage.getModuleMenuMasterDTOList();
		purchaseAndInventory=getMenuList(purchaseAndInventory);
		
		session.setAttribute("purchaseAndInventory", purchaseAndInventory);
		
		dto.setModuleName("Manufacturing Planning");
		inputMessage.setModuleMenuMasterDTO(dto);
	    masterOutMessage= menuMasterService.findModuleMenuMasterByModuleName(inputMessage);
		List<ModuleMenuMasterDTO> manufacturingPlanning = masterOutMessage.getModuleMenuMasterDTOList();
		manufacturingPlanning=getMenuList(manufacturingPlanning);
		
		session.setAttribute("manufacturingPlanning", manufacturingPlanning);
		
		
		dto.setModuleName("Human Resource");
		inputMessage.setModuleMenuMasterDTO(dto);
	    masterOutMessage= menuMasterService.findModuleMenuMasterByModuleName(inputMessage);
		List<ModuleMenuMasterDTO> humanResource = masterOutMessage.getModuleMenuMasterDTOList();
		humanResource=getMenuList(humanResource);
		session.setAttribute("humanResource", humanResource);
		
		
		dto.setModuleName("Reports");
		inputMessage.setModuleMenuMasterDTO(dto);
		//masterOutMessage= menuMasterService.findModuleMenuMasterBySubModuleName(inputMessage);
		masterOutMessage= menuMasterService.findModuleMenuMasterBySubModuleName(inputMessage);
		
		List<ModuleMenuMasterDTO> reportList3 = masterOutMessage.getModuleMenuMasterDTOList();
		
		List<ModuleMenuMasterDTO> reportList2 = getMenuList(reportList3);
		
		
		UserRoleAndRightsInputMessage andRightsInputMessage=new UserRoleAndRightsInputMessage();
		UserRoleAndRightsOutputMessage userRoleAndRightsOutputMessage=new UserRoleAndRightsOutputMessage();
		UserRoleAndRightsDTO userRoleAndRightsDTO=new UserRoleAndRightsDTO();
		userRoleAndRightsDTO.setLoginUserName(getLoggedInUserName());
		andRightsInputMessage.setUserRoleAndRightsDTO(userRoleAndRightsDTO);
		userRoleAndRightsOutputMessage=iUserRoleAndRightsService.findRoleAndRightsByUserName(andRightsInputMessage);
		List<UserRoleAndRightsDTO> roleAndRightsDTOList=userRoleAndRightsOutputMessage.getUserRoleAndRightsDTOList();
		
		System.out.println("Controller-----------------------------------------"+roleAndRightsDTOList);
		session.setAttribute("roleAndRightsDTOList", roleAndRightsDTOList);
		//masterOutMessage= menuMasterService.findModuleMenuMasterByModuleName(inputMessage);
		//List<ModuleMenuMasterDTO> reportList = masterOutMessage.getModuleMenuMasterDTOList();
		
		UserMasterRoleDTO masterRoleDTO=new UserMasterRoleDTO();
		masterRoleDTO.setUserLoginId(getLoggedInUserName());
		UserMasterRoleInputMassage userMasterRoleInputMassage=new UserMasterRoleInputMassage();
		userMasterRoleInputMassage.setUserMasterRoleDTO(masterRoleDTO);
		UserMasterRoleOutputMessage masterRoleOutputMessage=userMasterRoleService.getUserInfo(userMasterRoleInputMassage);
		List<UserMasterRoleDTO> userList= masterRoleOutputMessage.getUserMasterRoleDTOList();
		if(userList!=null){
			UserMasterRoleDTO userMasterRoleDTO=null;
			try {
				userMasterRoleDTO = userList.get(0);
			} catch (Exception e) {
			}
			session.setAttribute("userId", userMasterRoleDTO.getUserId());
			session.setAttribute("hotKeyStatus", userMasterRoleDTO.getRoleMasterDTO().getHotKeyActive());
			logDTO.setCreatedUserId(userMasterRoleDTO.getUserId());
			logDTO.setModifiedUserId(userMasterRoleDTO.getUserId());
			logDTO.setUserId(userMasterRoleDTO.getUserId());
			companyService.createLoggerRecord(logDTO);
		}
		
		//List<ModuleMenuMasterDTO> reportList1 = getMenuList(reportList);
		session.setAttribute("reportList", reportList2);
		session.setAttribute("menuOptions", menuOptions);
		return "welcome_page";
		
	    }
	
	public Map<String, Map<String, Map<String, Boolean>>> getUserMenuOptions() {
		 Map<String, Map<String, Map<String, Boolean>>> module = null;
		String userRole = "";
		
		// get the current user name and user role.
		List<String> list = UserRoleUtil.getLoggedInUserRoles();
		if (!CollectionUtils.isEmpty(list)) {
			
			userRole = list.get(0);
			//TODO if no role assigned throw an exception and show message.
			// fetch the men options for the role.
			List<UserMenuOptions> menuList = advanzUserServiceImpl.getUserMenuOptionsList(userRole);
			module = createMenuMap(menuList);

		}
		return module;
	}
	
	
	private List getMenuList(List<ModuleMenuMasterDTO> menuList){
		try {
			Iterator<ModuleMenuMasterDTO> it = menuList.iterator();

			while (it.hasNext()) {
				ModuleMenuMasterDTO masterDTO = (ModuleMenuMasterDTO) it.next();
				
			   }

			List templist = new ArrayList();
			ModuleMenuMasterDTO mainDTO = null;
			for (int j = 0; j < menuList.size(); j++) {
				ModuleMenuMasterDTO temp = (ModuleMenuMasterDTO) menuList.get(j);
				if (j == 0) {
					mainDTO = temp;
					if(mainDTO.getSubMenuName()!=null){
					ModuleMenuMasterDTO dto = new ModuleMenuMasterDTO();
					dto.setMenuName(mainDTO.getMenuName());
					dto.setUrlLink(mainDTO.getUrlLink());
					dto.setSubMenuName(mainDTO.getSubMenuName());
					mainDTO.addSubmenu(dto);
					}
					
				} else {
					
					if (mainDTO.getMenuName().equals(temp.getMenuName())) {
						if(temp.getSubMenuName()!=null){
						ModuleMenuMasterDTO dto = new ModuleMenuMasterDTO();
						dto.setMenuName(temp.getMenuName());
						dto.setUrlLink(temp.getUrlLink());
						dto.setSubMenuName(temp.getSubMenuName());
						mainDTO.addSubmenu(dto);
						}
					} else {
						
						templist.add(mainDTO);
						mainDTO = temp;
						if(mainDTO.getSubMenuName()!=null){
						ModuleMenuMasterDTO dto = new ModuleMenuMasterDTO();
						dto.setMenuName(mainDTO.getMenuName());
						dto.setUrlLink(mainDTO.getUrlLink());
						dto.setSubMenuName(mainDTO.getSubMenuName());
						mainDTO.addSubmenu(dto);
					  }
					}
					
					}
		
			  }
			templist.add(mainDTO);
			

			return templist;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return menuList;
	}
	
	
	public static Map<String, Map<String, Map<String, Boolean>>> createMenuMap( List<UserMenuOptions> menuList ) {
		// map for the flags
		Map<String, Boolean> flags = new HashMap<String, Boolean>();
		Map<String, Map<String, Boolean>> menuMap = new HashMap<String, Map<String, Boolean>>();
		Map<String, Map<String, Map<String, Boolean>>> module = new HashMap<String, Map<String, Map<String, Boolean>>>();

		String moduleStr = "";
		String mainMenuStr = "";

		for (UserMenuOptions menuOption : menuList) {
			flags = new HashMap<String, Boolean>();

			flags.put("visibleFlag", menuOption.isVisibleFlag());
			flags.put("addFlag", menuOption.isAddFlag());
			flags.put("editFlag", menuOption.isEditFlag());
			flags.put("deleteFlag", menuOption.isDeleteFlag());

			if (moduleStr.equalsIgnoreCase(menuOption.getModuleName())) { 
				
					
					if ( menuOption.getMenuName()!=null  && !mainMenuStr.equalsIgnoreCase(menuOption.getMenuName())) {
							
							if(!"".equals(menuOption.getMenuName())) {
								mainMenuStr = menuOption.getMenuName();
								menuMap.put(mainMenuStr, flags);
							}
						}


			} else { // if menu option is different
				menuMap = new HashMap<String, Map<String, Boolean>>();
				moduleStr = menuOption.getModuleName();
				mainMenuStr = menuOption.getMenuName();
				if( menuOption.getMenuName()!=null  && !"".equals(menuOption.getMenuName())) {
					
				menuMap.put(mainMenuStr, flags);
				}
				module.put(moduleStr, menuMap);
			}
		}
		return module;
	}
	
}
