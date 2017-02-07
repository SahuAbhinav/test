package com.advanz.erp.masters.model;

import java.util.ArrayList;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

public class ModuleMenuMasterDTO extends BaseDTO{
	
    private Integer menuId;
	private String moduleName;
	private String subModuleName;
	private String menuName;
	private String urlLink;
	private Integer activeStatus;
	
	private String loginUserName;
	private String subMenuName;
	private String description;
	private List<ModuleMenuMasterDTO> subMlist;
	private Integer menuSeqNo;
	
	
	
	public List<ModuleMenuMasterDTO> getSubMlist() {
		return subMlist;
	}
	public void setSubMlist(List<ModuleMenuMasterDTO> subMlist) {
		this.subMlist = subMlist;
	}
	
	
	public void addSubmenu(String submenu){
		ModuleMenuMasterDTO dto = new ModuleMenuMasterDTO();
		if(subMlist==null){
			subMlist=new ArrayList<ModuleMenuMasterDTO>();
			dto.setSubMenuName(submenu);
			subMlist.add(dto);
			return;
		}
		subMlist.add(dto);
	}
	
	public void addSubmenu(ModuleMenuMasterDTO dto){
		if(subMlist==null){
			subMlist=new ArrayList<ModuleMenuMasterDTO>();
			
			subMlist.add(dto);
			return;
		}
		subMlist.add(dto);
		
	}
	
	
	
	public String getSubMenuName() {
		return subMenuName;
	}
	public void setSubMenuName(String subMenuName) {
		this.subMenuName = subMenuName;
	}
	public String getLoginUserName() {
		return loginUserName;
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	public Integer getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getSubModuleName() {
		return subModuleName;
	}
	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUrlLink() {
		return urlLink;
	}
	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}
	public Integer getMenuSeqNo() {
		return menuSeqNo;
	}
	public void setMenuSeqNo(Integer menuSeqNo) {
		this.menuSeqNo = menuSeqNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
   }
