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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.ProfessionalTaxForm;
import com.advanz.erp.client.http.controller.form.UserMasterRoleForm;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.MelterTrollyLogDTO;
import com.advanz.erp.masters.model.ProfessionalTaxDTO;
import com.advanz.erp.masters.model.ProfessionalTaxDeductTypeDTO;
import com.advanz.erp.masters.model.RoleDTO;
import com.advanz.erp.masters.model.RoleMasterDTO;
import com.advanz.erp.masters.model.UserMasterRoleDTO;
import com.advanz.erp.masters.model.UserRoleDTO;
import com.advanz.erp.masters.model.msg.MelterTrollyLogInputMessage;
import com.advanz.erp.masters.model.msg.MelterTrollyLogOutputMessage;
import com.advanz.erp.masters.model.msg.ProfessionalTaxDeductTypeOutputMessage;
import com.advanz.erp.masters.model.msg.ProfessionalTaxInputMessage;
import com.advanz.erp.masters.model.msg.ProfessionalTaxOutputMessage;
import com.advanz.erp.masters.model.msg.RoleMasterOutputMessage;
import com.advanz.erp.masters.model.msg.RoleOutMessage;
import com.advanz.erp.masters.model.msg.UserMasterRoleInputMassage;
import com.advanz.erp.masters.model.msg.UserMasterRoleOutputMessage;
import com.advanz.erp.masters.model.msg.UserRoleInputMessage;
import com.advanz.erp.masters.model.msg.UserRoleOutputMessage;
import com.advanz.erp.masters.service.business.IRoleService;
import com.advanz.erp.masters.service.business.IUserMasterRoleService;
import com.advanz.erp.masters.service.business.IUserRoleService;

@Controller
public class UserMasterRoleController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(UserMasterRoleController.class);

	@Autowired
	private IUserMasterRoleService iUserMasterRoleService;

	@Autowired
	private IRoleService iRoleService;
 
	
	@Autowired
	private IUserRoleService iUserRoleService;
	
	
	@RequestMapping(value = "/show_user_master", method = RequestMethod.GET)
	public ModelAndView showUserMasterRoleForm(@ModelAttribute("userMasterForm")UserMasterRoleForm userMasterRoleForm,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
		UserMasterRoleOutputMessage masterRoleOutputMessage = iUserMasterRoleService.findAllUserMaster();
		ArrayList<UserMasterRoleDTO> userMasterRoleDTOList = (ArrayList<UserMasterRoleDTO>) masterRoleOutputMessage.getUserMasterRoleDTOList();
		ModelAndView mav = new ModelAndView("user_master_list");
		mav.addObject("userMasterList", userMasterRoleDTOList);
		logger.info("userMasterList"+ userMasterRoleDTOList);
		return mav;
	}


   @RequestMapping(value="/get_user_master_role_form", method = RequestMethod.POST)
   public ModelAndView searchUserMasterRoleForm(@ModelAttribute("userMasterForm") UserMasterRoleForm userMasterRoleForm,@RequestParam("userFullName") String userFullName,@RequestParam("roleName") String roleName,Model model)
   {
	 RoleMasterDTO roleMasterDTO=new RoleMasterDTO();
	 UserMasterRoleOutputMessage userMasterRoleOutputMessage=new UserMasterRoleOutputMessage();
	 UserMasterRoleInputMassage userMasterRoleInputMassage=new UserMasterRoleInputMassage();
	 
	 if (StringUtils.hasText(userFullName) || StringUtils.hasText(roleName)) {
	    UserMasterRoleDTO uMasterRoleDTO=new UserMasterRoleDTO();    
	    uMasterRoleDTO.setUserFullName(userFullName);
	    roleMasterDTO.setRoleName(roleName);
	    uMasterRoleDTO.setRoleMasterDTO(roleMasterDTO);
	    userMasterRoleInputMassage.setUserMasterRoleDTO(uMasterRoleDTO);
	    userMasterRoleOutputMessage=iUserMasterRoleService.searchUserMasterForm(userMasterRoleInputMassage);
	  }
	 else
	  {
	   userMasterRoleOutputMessage=iUserMasterRoleService.findAllUserMaster();
	  }
	 ArrayList<UserMasterRoleDTO> userMasterRoleDTOList=(ArrayList<UserMasterRoleDTO>) userMasterRoleOutputMessage.getUserMasterRoleDTOList();
	 String succ="Blk";
		if(userMasterRoleDTOList.equals(null) || userMasterRoleDTOList.size()==0)
		{
		  model.addAttribute("succ", succ);
		}
	 ModelAndView mav=new ModelAndView("user_master_list");
	 mav.addObject("userMasterList",userMasterRoleDTOList);
	 logger.info("userMasterList"+userMasterRoleDTOList);
	 return mav;
   }
   
   @RequestMapping(value = "/get_new_user_master_form", method = RequestMethod.GET)
   public ModelAndView getNewUserForm(@ModelAttribute("userMasterForm")UserMasterRoleForm userMasterRoleForm, Model model) {
        
     ModelAndView mav=new ModelAndView("user_master_detail");
     if(userMasterRoleForm==null)
     {
    	 userMasterRoleForm=new UserMasterRoleForm();
     }
     mav.addObject("userMasterForm",userMasterRoleForm);
     return mav;
   }
  
   
   @RequestMapping(value = "/save_user_master_form", method = RequestMethod.POST)
   public String saveUserMasterRoleForm(@ModelAttribute("userMasterForm")UserMasterRoleForm userMasterRoleForm, Model model) {
	   String succ="";
	   UserMasterRoleOutputMessage userMasterRoleOutputMessage=new UserMasterRoleOutputMessage();
       UserMasterRoleInputMassage userMasterRoleInputMassage=new UserMasterRoleInputMassage();
       UserMasterRoleDTO userMasterRoleDTO= userMasterRoleForm.getUserMasterRoleDTO();
       userMasterRoleDTO.setCreatedUserId(getCreatedUserId());
       userMasterRoleInputMassage.setUserMasterRoleDTO(userMasterRoleDTO);
       if(userMasterRoleForm.getUserMasterRoleDTO().getUserId()==null || userMasterRoleForm.getUserMasterRoleDTO().getUserId().equals(0))
       {
    	   userMasterRoleOutputMessage=iUserMasterRoleService.addUserMasterForm(userMasterRoleInputMassage);
    	   ErrorListDTO errorListDTO=userMasterRoleOutputMessage.getErrorListDTO();
    	   if(errorListDTO!=null)
    	   {
    		   logger.info(" adding Error ");
    		   model.addAttribute("errorList", userMasterRoleOutputMessage.getErrorListDTO().getErrorList().get(0));
    		   return "user_master_detail";
    	   }
    	   else
    	   {
    		   //get user id by calling findUserRoleId() method save it to m_user_role table
    		   UserMasterRoleOutputMessage userOutforId=new UserMasterRoleOutputMessage();
    		   UserRoleInputMessage inputMessageId=new UserRoleInputMessage();
    		   userOutforId=iUserMasterRoleService.findUserRoleId();
    		   ArrayList<UserMasterRoleDTO> listForId=(ArrayList<UserMasterRoleDTO>) userOutforId.getUserMasterRoleDTOList();
    		   UserMasterRoleDTO userMasterRoleDTOForId=new UserMasterRoleDTO();
    	    	userMasterRoleDTOForId=listForId.get(0);
    	    	userMasterRoleDTOForId.getRoleMasterDTO().getRoleId();
    	    	UserRoleDTO dtoForId=new UserRoleDTO();
    	    	dtoForId.setRoleId(userMasterRoleForm.getUserMasterRoleDTO().getRoleMasterDTO().getRoleId());
    	    	dtoForId.setUserId(userMasterRoleDTOForId.getUserId());
    	    	dtoForId.setCreatedUserId(getCreatedUserId());
    	    	inputMessageId.setUserRoleDTO(dtoForId);
    	    	iUserRoleService.addNewUserRecord(inputMessageId);
    	    	succ="Ad";
    	   }  
        }
       else
       {
    	   userMasterRoleDTO.setModifiedUserId(getCreatedUserId());
    	userMasterRoleOutputMessage=iUserMasterRoleService.updateUserMasterRole(userMasterRoleInputMassage);
    	UserRoleInputMessage usInputMassage=new UserRoleInputMessage();
    	UserRoleDTO  roleDTO=new UserRoleDTO();
    	roleDTO.setUserId(userMasterRoleForm.getUserMasterRoleDTO().getUserId());
    	roleDTO.setRoleId(userMasterRoleForm.getUserMasterRoleDTO().getRoleMasterDTO().getRoleId());
    	usInputMassage.setUserRoleDTO(roleDTO);
    	iUserRoleService.updateUserRecord(usInputMassage);
    	 ErrorListDTO errorListDTO=userMasterRoleOutputMessage.getErrorListDTO();
       if(errorListDTO!=null)
  	   {
  		   logger.info(" adding Error ");
  		   model.addAttribute("errorList", userMasterRoleOutputMessage.getErrorListDTO().getErrorList().get(0));
  		   return "user_master_detail";
  	   }
          succ="Up";
       }
       model.addAttribute("succ", succ);
      return "redirect:/show_user_master";
   }
 
       //get user master for sending to edit call service get list send it to form
    @RequestMapping(value = "/get_user_master_form", method = RequestMethod.GET)
	public ModelAndView getUserMasterRole(@ModelAttribute("userMasterForm") UserMasterRoleForm userMasterRoleForm,@RequestParam("userId") Integer userId,@RequestParam("opr")String opr,ModelMap model) {
       logger.info("Get User Master Role UserId:"+userId);
  	   logger.info("opr:"+opr);
  	   UserMasterRoleDTO userMasterRoleDTO=new UserMasterRoleDTO();
  	   UserMasterRoleOutputMessage userMasterRoleOutputMessage=new UserMasterRoleOutputMessage();
  	   if(userId!=null || !equals(userId))
  	   {
  		UserMasterRoleInputMassage userMasterRoleInputMassage=new UserMasterRoleInputMassage();
  		userMasterRoleDTO.setUserId(userId);
  		userMasterRoleInputMassage.setUserMasterRoleDTO(userMasterRoleDTO);
  		userMasterRoleOutputMessage=iUserMasterRoleService.findUserMasterRoleById(userMasterRoleInputMassage);
  		ArrayList<UserMasterRoleDTO> list=(ArrayList<UserMasterRoleDTO>)userMasterRoleOutputMessage.getUserMasterRoleDTOList();   
  	   
  		if(list!=null && list.size()>0)
  	    {
  	     userMasterRoleForm.setUserMasterRoleDTO(list.get(0));
  	    }
  	   }
  	   model.put("opr",opr);
  	   model.put("userId",userId);
  	   model.put("userMasterForm", userMasterRoleForm);
  	   ModelAndView mav=new ModelAndView("user_master_detail");
  	   return mav;
	}
   

    @RequestMapping(value="/remove_user_master_role", method=RequestMethod.GET)
    public String removeUserMasterRoleForm(@ModelAttribute("userMasterForm") UserMasterRoleForm userMasterRoleForm,@RequestParam("userId") Integer userId,Model model)
     { 
      UserMasterRoleDTO uMasterRoleDTO=new UserMasterRoleDTO(); 
      UserMasterRoleOutputMessage userMasterRoleOutputMessage=new UserMasterRoleOutputMessage();
      UserMasterRoleInputMassage userMasterRoleInputMassage=new UserMasterRoleInputMassage();
      uMasterRoleDTO.setUserId(userMasterRoleForm.getUserId());
      userMasterRoleInputMassage.setUserMasterRoleDTO(uMasterRoleDTO);

      UserRoleInputMessage userRoleInputMessage=new UserRoleInputMessage();
      UserRoleDTO userRoleDTO=new UserRoleDTO();
      userRoleDTO.setUserId(uMasterRoleDTO.getUserId());
      userRoleDTO.setModifiedUserId(getCreatedUserId());
      userRoleInputMessage.setUserRoleDTO(userRoleDTO);
       
      userMasterRoleOutputMessage=iUserMasterRoleService.deleteUserMasterRole(userMasterRoleInputMassage);
      iUserRoleService.deleteUserRecord(userRoleInputMessage);
      String succ="Dl";
      model.addAttribute("succ",succ);
      return	"redirect:/show_user_master";
    }
    
    

	@ModelAttribute("roleType")
	public List<RoleMasterDTO> roleMasterComboList() {						
		RoleOutMessage roleOutMessage= iRoleService.findAllRoles();
		//ArrayList<RoleMasterDTO> roleType=(ArrayList<RoleMasterDTO>) roleMasterOutputMessage.getRoleMasterDTOList();
		    return roleOutMessage.getRoles();
		}
	}

