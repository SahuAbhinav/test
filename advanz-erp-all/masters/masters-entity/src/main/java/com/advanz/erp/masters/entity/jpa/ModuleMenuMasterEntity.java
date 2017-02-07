package com.advanz.erp.masters.entity.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@javax.persistence.Entity
@Table(name = "m_module_menu_master")
public class ModuleMenuMasterEntity implements Serializable {

	@Id 
	@GeneratedValue(generator="system-incr")
	@GenericGenerator(name="system-incr", strategy = "increment")
	@Column(name="menu_id")
	private Integer menuId; 
	
	@Column(name="module_name")
	private String moduleName;
	
	@Column(name="sub_module_name")
	private String subModuleName;
	
	@Column(name="menu_name")
	private String menuName;
	
	@Column(name="url_link")
	private String urlLink;
	
	@Column(name="active_status")
	private Integer activeStatus;
	
	@Column(name="sub_menu_name")
	private String subMenuName;
	
	@Column(name="menu_seq_no")
	private Integer menuSeqNo;
	
	@Column(name="description")
	private String description;
	
	
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

	public String getSubMenuName() {
		return subMenuName;
	}

	public void setSubMenuName(String subMenuName) {
		this.subMenuName = subMenuName;
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
