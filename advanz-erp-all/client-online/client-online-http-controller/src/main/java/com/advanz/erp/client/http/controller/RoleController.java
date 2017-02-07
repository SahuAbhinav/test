package com.advanz.erp.client.http.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.RoleForm;
import com.advanz.erp.client.http.controller.validator.RoleValidator;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.masters.model.ModuleMenuMasterDTO;
import com.advanz.erp.masters.model.RoleMasterDTO;
import com.advanz.erp.masters.model.UserRoleAndRightsDTO;
import com.advanz.erp.masters.model.UserRoleDTO;
import com.advanz.erp.masters.model.criteria.RoleSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.ModuleMenuMasterInputMessage;
import com.advanz.erp.masters.model.msg.ModuleMenuMasterOutMessage;
import com.advanz.erp.masters.model.msg.RoleInputMessage;
import com.advanz.erp.masters.model.msg.RoleOutMessage;
import com.advanz.erp.masters.model.msg.UserRoleAndRightsInputMessage;
import com.advanz.erp.masters.model.msg.UserRoleAndRightsOutputMessage;
import com.advanz.erp.masters.model.msg.UserRoleInputMessage;
import com.advanz.erp.masters.model.msg.UserRoleOutputMessage;
import com.advanz.erp.masters.service.business.IModuleMenuMasterService;
import com.advanz.erp.masters.service.business.IRoleService;
import com.advanz.erp.masters.service.business.IUserRoleAndRightsService;
import com.advanz.erp.masters.service.business.IUserRoleService;


@Controller
//@RequestMapping(value="/role")
public class RoleController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	IRoleService roleService;
	@Autowired
	RoleValidator roleValidator;
	@Autowired
	IModuleMenuMasterService moduleMenuMasterService;
	@Autowired
	IUserRoleAndRightsService userRoleAndRightsService;
	
	@Autowired
	IUserRoleService iUserRoleService; 
	
	@RequestMapping(value = "/role_add_form", method = RequestMethod.GET)
	public ModelAndView showAddRoleForm(@ModelAttribute("roleForm") RoleForm roleForm, BindingResult result,Model model) {
		ModelAndView mav = new ModelAndView("role-add-edit-screen");
		ModuleMenuMasterOutMessage moduleMenuMasterOutMessage = moduleMenuMasterService.findAllModuleMenuMaster();
		List<ModuleMenuMasterDTO> list=moduleMenuMasterOutMessage.getModuleMenuMasterDTOList();
		if (roleForm == null) {
			roleForm = new RoleForm();
		}
		roleForm.setModuleMenuMasterDTOList(list);
		mav.addObject("roleForm", roleForm);
		model.addAttribute("moduleMenuMasterList", list);
		return mav;
	}

	@RequestMapping(value = "/add_role", method = RequestMethod.POST)
	public ModelAndView addRole(@ModelAttribute("roleForm") RoleForm roleForm, BindingResult result, Model model,ModelMap modelMap ) {
		ModelAndView mav = new ModelAndView("role-add-edit-screen");
		
		// Do front-end validation
		roleValidator.validate(roleForm, result);
		if(result.hasErrors()) {
			mav.addObject("roleForm", roleForm);
			return mav;	
		}
		// Call service
		RoleInputMessage inputMessage = new RoleInputMessage();
		RoleMasterDTO roleMasterDTO= roleForm.getRole();
		roleMasterDTO.setCreatedUserId(getCreatedUserId());
		inputMessage.setRole(roleMasterDTO);
		RoleOutMessage roleOutMessage = roleService.addRole(inputMessage);
		if(isErrorPresent( roleOutMessage )){
			model.addAttribute("roleForm", roleForm);
			
			ErrorDTO errorDTO=roleOutMessage.getErrorListDTO().getErrorList().get(0);
			model.addAttribute( "errorList", errorDTO);
			ModelAndView  mav1=new ModelAndView("forward:role_add");
			return mav1;
		}
		roleOutMessage = roleService.findRoleLastId();
		List<RoleMasterDTO> roleMasterDTO1=roleOutMessage.getRoles();
		RoleMasterDTO dto=roleMasterDTO1.get(0);
		
		Integer roleId=dto.getRoleId();
		
		UserRoleAndRightsDTO roleAndRightsDTO=new UserRoleAndRightsDTO();
		roleAndRightsDTO=roleForm.getUserRoleAndRightsDTO();
		UserRoleAndRightsInputMessage userRoleAndRightsInputMessage=new UserRoleAndRightsInputMessage();
		userRoleAndRightsInputMessage.setUserRoleAndRightsDTO(roleAndRightsDTO);
		
		 ArrayList<ModuleMenuMasterDTO> moduleList=(ArrayList<ModuleMenuMasterDTO>) roleForm.getModuleMenuMasterDTOList();
		 ArrayList<UserRoleAndRightsDTO> roleAndRightsList=(ArrayList<UserRoleAndRightsDTO>) roleForm.getUserRoleAndRightsDTOList();
		if(moduleList!=null && moduleList.size()>0){
			for(int i=0;i<moduleList.size();i++){		
				ModuleMenuMasterDTO masterDTO= moduleList.get(i);
				UserRoleAndRightsDTO userRoleAndRightsDTO=roleAndRightsList.get(i);
				userRoleAndRightsInputMessage=new UserRoleAndRightsInputMessage();
				userRoleAndRightsDTO.setRoleId(roleId);
				userRoleAndRightsDTO.setMenuId(masterDTO.getMenuId());
			//	System.out.println("MenuId--------------------------------->>"+masterDTO.getMenuId());
				userRoleAndRightsInputMessage.setUserRoleAndRightsDTO(userRoleAndRightsDTO);
				userRoleAndRightsService.createUserRoleAndRight(userRoleAndRightsInputMessage);
			}
		}
	//	ErrorDTO errorDTO=new ErrorDTO();
	//	errorDTO.setErrorMsg("Record Successfully Saved !!!!");
	//	modelMap.put("errorList", errorDTO);
		ModelAndView  mav2=new ModelAndView("forward:role_list");
		
		String succ="Ad";
		mav2.addObject("succ", succ);
		return mav2;
	}

	@RequestMapping(value = "/role_list")
	public String searchForm(@ModelAttribute("roleSearchCriteria")RoleSearchCriteriaDTO searchCriteria, Model model,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
		if(menuId!=null)
    	{
    		session.setAttribute("menuId", menuId);
    	}
		// Call service
		String roleName = searchCriteria.getRoleName();
		model.addAttribute("roleSearchCriteria", searchCriteria);
		RoleOutMessage roleOutMessage ;
		if (StringUtils.hasText(roleName)) {
			RoleInputMessage roleInputMessage = new RoleInputMessage();
			RoleMasterDTO roleDTO = new RoleMasterDTO();
			roleDTO.setRoleName(roleName);
			roleInputMessage.setRole(roleDTO);
			roleOutMessage = roleService.findRoleBySearchCriteria(roleInputMessage);
		} else {
			roleOutMessage = roleService.findAllRoles();
		}	
			List<RoleMasterDTO> list = roleOutMessage.getRoles();
			//roleForm.setRoles(list);
			model.addAttribute("roleList", list);
			// Check for errors
			if(isErrorPresent( roleOutMessage )){
				ErrorDTO errorDTO=roleOutMessage.getErrorListDTO().getErrorList().get(0);
				model.addAttribute( "errorList", errorDTO);
				return "role-search-screen";
			}
			String succ="Blk";
			if(list.equals(null) || list.size()==0)
			{
			  model.addAttribute("succ", succ);
			}
		return "role-search-screen";
	}
	@RequestMapping(value = "/get_role", method = RequestMethod.GET)
	public ModelAndView showEditRoleForm(@ModelAttribute("roleForm") RoleForm roleForm,@RequestParam("roleId") Integer roleId,@RequestParam("opr")String opr,Model model) {
	
		ModelAndView mav = new ModelAndView("role-edit-screen");
		logger.info("Get roleId : " + roleId);
		UserRoleDTO userRoleDTO=new UserRoleDTO();
		
		userRoleDTO.setRoleId(roleId);
		UserRoleOutputMessage userRoleOutputMessage=new UserRoleOutputMessage();
		UserRoleInputMessage userRoleInputMessage=new UserRoleInputMessage();

	    userRoleInputMessage.setUserRoleDTO(userRoleDTO);
	    userRoleOutputMessage=iUserRoleService.findUerRoleByRoleId(userRoleInputMessage);
	    
	    List<UserRoleDTO> roleEntities=userRoleOutputMessage.getUserRoleDTOList();
	    
	    if(roleEntities!=null && roleEntities.size()>0 && opr.equals("R") )
	    {
	       	ErrorDTO errorDTO=new ErrorDTO("1","Can not delete this role !!!!!!!");
			ModelAndView mView=new ModelAndView("forward:role_list");
			mView.addObject("errorList", errorDTO);
			return mView;
	    }

		
		List<UserRoleAndRightsDTO> andRightsDTOs=null;
		RoleOutMessage roleOutputMessage = null;
		List<ModuleMenuMasterDTO> list=null;
		UserRoleAndRightsDTO userRoleAndRightsDTO=new UserRoleAndRightsDTO();
		UserRoleAndRightsOutputMessage userRoleAndRightsOutputMessage=null;
		UserRoleAndRightsInputMessage userRoleAndRightsInputMessage=null;
		ModuleMenuMasterDTO moduleMenuMasterDTO=new ModuleMenuMasterDTO();
		ModuleMenuMasterInputMessage moduleMenuMasterInputMessage=null;
	
		RoleInputMessage roleInputMessage = new RoleInputMessage();
		RoleMasterDTO roleDTO = new RoleMasterDTO();
		roleDTO.setRoleId(roleId);
		roleInputMessage.setRole(roleDTO);
		roleOutputMessage = roleService.findRoleById(roleInputMessage);
		roleForm.setRole(roleOutputMessage.getRole());
		
		logger.info("Opr : " + opr);
		
		userRoleAndRightsOutputMessage=new UserRoleAndRightsOutputMessage();
		userRoleAndRightsInputMessage=new UserRoleAndRightsInputMessage();
			
		userRoleAndRightsDTO.setRoleId(roleId);
		userRoleAndRightsInputMessage.setUserRoleAndRightsDTO(userRoleAndRightsDTO);
		  try {
		    	userRoleAndRightsOutputMessage=userRoleAndRightsService.findUserRoleAndRightById(userRoleAndRightsInputMessage);	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			list=new ArrayList<ModuleMenuMasterDTO>();
			
		    Integer mnId;
			List<Integer> mnuId1=userRoleAndRightsOutputMessage.getMenuIdList();
			ModuleMenuMasterOutMessage masterOutMessage=moduleMenuMasterService.findAllModuleMenuMaster();
			List<ModuleMenuMasterDTO> dtos=masterOutMessage.getModuleMenuMasterDTOList();
						
			/*for(int i=0;i<mnuId1.size();i++)
			{
				moduleMenuMasterInputMessage=new ModuleMenuMasterInputMessage();
				mnId=mnuId1.get(i);
				//System.out.println("MENU ID IS  ::::::::::::::::" + mnId);
				moduleMenuMasterDTO.setMenuId(mnId);
				moduleMenuMasterInputMessage.setModuleMenuMasterDTO(moduleMenuMasterDTO);
				ModuleMenuMasterOutMessage moduleMenuMasterOutMessage = moduleMenuMasterService.findModuleMenuMasterByRoleId(moduleMenuMasterInputMessage);
				List l=moduleMenuMasterOutMessage.getModuleMenuMasterDTOList();
				if(l!=null && l.size()>0){
				ModuleMenuMasterDTO masterDTO=(ModuleMenuMasterDTO) l.get(0);
				list.add(masterDTO);
				}
			}
		*/
		  
		  
			UserRoleAndRightsDTO roleAndRightsDTO=new UserRoleAndRightsDTO();
		    UserRoleAndRightsInputMessage andRightsInputMessage=new UserRoleAndRightsInputMessage();
		    roleAndRightsDTO.setRoleId(roleId);
		    andRightsInputMessage.setUserRoleAndRightsDTO(roleAndRightsDTO);
		    userRoleAndRightsOutputMessage=new UserRoleAndRightsOutputMessage();
		  
		    try {                                                                                                
		    	//list2   =userRoleAndRightsService.findUserRoleAndRightsByRoleId(andRightsInputMessage);
		    	userRoleAndRightsOutputMessage=userRoleAndRightsService.findRoleAndRightByRoleId(andRightsInputMessage);
				andRightsDTOs=userRoleAndRightsOutputMessage.getUserRoleAndRightsDTOList();
			} catch (Exception e) {                              
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//roleForm.setModuleMenuMasterDTOList(dtos);
		
		roleForm.setUserRoleAndRightsDTOList(andRightsDTOs);
		roleForm.setModuleMenuMasterDTOList(dtos);
		model.addAttribute("moduleMenuMasterList", dtos);
		mav.addObject("roleForm", roleForm);
		mav.addObject("opr",opr);
		
		return mav;
	}
	
	@RequestMapping(value = "/remove_role", method = RequestMethod.GET)
	public ModelAndView removeRole( @RequestParam("roleId")  String roleId, Model model) {
		
		logger.info("Get roleId : " + roleId);
		RoleForm roleForm=null;
		RoleOutMessage roleOutputMessage = null;
		if (StringUtils.hasText(roleId)) {
			
			int id=NumberUtils.parseNumber(roleId, Integer.class);
			
			RoleInputMessage roleInputMessage = new RoleInputMessage();
			RoleMasterDTO roleDTO = new RoleMasterDTO();
			roleDTO.setRoleId(id);
			roleDTO.setModifiedUserId(getCreatedUserId());
			roleInputMessage.setRole(roleDTO);
			roleOutputMessage = roleService.deleteRole(roleInputMessage);
			roleOutputMessage=new RoleOutMessage();
			
			UserRoleAndRightsOutputMessage userRoleAndRightsOutput=new UserRoleAndRightsOutputMessage();
			UserRoleAndRightsInputMessage andRightsInputMessage=new UserRoleAndRightsInputMessage();
			UserRoleAndRightsDTO andRightsDTO=new UserRoleAndRightsDTO();
			
			andRightsDTO.setRoleId(id);
			andRightsInputMessage.setUserRoleAndRightsDTO(andRightsDTO);
			  try {
				  userRoleAndRightsOutput=userRoleAndRightsService.findSnoFromRoleAndRightByRoleId(andRightsInputMessage);	
				} catch (Exception e) {
					e.printStackTrace();
				}
				UserRoleAndRightsDTO andRightsDTODto=new UserRoleAndRightsDTO();
		    Integer sno;
			List<Integer> snoList=userRoleAndRightsOutput.getMenuIdList();
			
			for(int i=0;i<snoList.size();i++)
			{
				andRightsInputMessage=new UserRoleAndRightsInputMessage();
				sno=snoList.get(i);
				//System.out.println("Sno ID IS  ::::::::::::::::" + sno);
				andRightsDTODto.setSno(sno);
				andRightsInputMessage.setUserRoleAndRightsDTO(andRightsDTODto);
				userRoleAndRightsService.deleteUserRoleAndRight(andRightsInputMessage);
			}		
			if(isErrorPresent( roleOutputMessage )){
				model.addAttribute("roleForm", roleForm);
				ErrorDTO errorDTO=roleOutputMessage.getErrorListDTO().getErrorList().get(0);
				model.addAttribute( "errorList", errorDTO);
				ModelAndView mav=new ModelAndView("forward:role_edit_remove");
				return mav;
			}
		}
		ModelAndView mav=new ModelAndView("forward:role_list");
		String succ="Dl";
		mav.addObject("succ", succ);
		return mav;
	}
	
		
	@RequestMapping(value = "/edit_role" , method = RequestMethod.POST)
	public ModelAndView editRole(@ModelAttribute("roleForm") RoleForm roleForm, Model model,ModelMap modelMap) {
		
		UserRoleAndRightsInputMessage roleAndRightsInputMessage=null;
		UserRoleAndRightsOutputMessage userRoleAndRightsOutputMessage=new UserRoleAndRightsOutputMessage();
		RoleOutMessage roleOutputMessage = null;
		RoleInputMessage roleInputMessage = new RoleInputMessage();
		RoleMasterDTO roleMasterDTO= roleForm.getRole();
		roleMasterDTO.setModifiedUserId(getCreatedUserId());
		roleInputMessage.setRole(roleMasterDTO);
		roleOutputMessage = roleService.updateRole(roleInputMessage);
		// Check for errors
		if(isErrorPresent( roleOutputMessage )){
			model.addAttribute("roleForm", roleForm);
			ErrorDTO errorDTO=roleOutputMessage.getErrorListDTO().getErrorList().get(0);
			model.addAttribute( "errorList", errorDTO);
			ModelAndView mav=new ModelAndView("forward:role_edit_remove");
			return mav;
		}
		
		ArrayList<ModuleMenuMasterDTO> moduleList=(ArrayList<ModuleMenuMasterDTO>) roleForm.getModuleMenuMasterDTOList();
		ArrayList<UserRoleAndRightsDTO> roleAndRightsList=(ArrayList<UserRoleAndRightsDTO>) roleForm.getUserRoleAndRightsDTOList();
		if(moduleList!=null && moduleList.size()>0){
			for(int i=0;i<moduleList.size();i++){		
				ModuleMenuMasterDTO masterDTO= moduleList.get(i);
				UserRoleAndRightsDTO userRoleAndRightsDTO=roleAndRightsList.get(i);
				//System.out.println("Check-------------------------------------"+userRoleAndRightsDTO.getSno());
				roleAndRightsInputMessage=new UserRoleAndRightsInputMessage();
				userRoleAndRightsDTO.setRoleId(roleForm.getRole().getRoleId());
				userRoleAndRightsDTO.setMenuId(masterDTO.getMenuId());
				roleAndRightsInputMessage.setUserRoleAndRightsDTO(userRoleAndRightsDTO);
				userRoleAndRightsService.updateUserRoleAndRight(roleAndRightsInputMessage);
			}
		}
		
		ModelAndView mav=new ModelAndView("forward:role_list");
		String succ="Up";
		mav.addObject("succ", succ);
		return mav;
	 }
	}
