package com.advanz.erp.masters.model;

import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class MasterFormulaDetailDTO extends BaseDTO {
	private Integer sno;
	private Integer masterFormulaAutoId;
	private Integer itemGroupFlagId;
	private Integer itemSequenceNumber;
	private ItemDTO itemDTO;
	private Double quantity;
	private String itemRemark;
	private String uom;
	
   public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

private List<ItemGroupFlagDTO> itemGroupFlagDTOList;
   
	public List<ItemGroupFlagDTO> getItemGroupFlagDTOList() {
	return itemGroupFlagDTOList;
}

public void setItemGroupFlagDTOList(List<ItemGroupFlagDTO> itemGroupFlagDTOList) {
	this.itemGroupFlagDTOList = itemGroupFlagDTOList;
}

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public Integer getMasterFormulaAutoId() {
		return masterFormulaAutoId;
	}

	public void setMasterFormulaAutoId(Integer masterFormulaAutoId) {
		this.masterFormulaAutoId = masterFormulaAutoId;
	}

	public Integer getItemGroupFlagId() {
		return itemGroupFlagId;
	}

	public void setItemGroupFlagId(Integer itemGroupFlagId) {
		this.itemGroupFlagId = itemGroupFlagId;
	}

	public Integer getItemSequenceNumber() {
		return itemSequenceNumber;
	}

	public void setItemSequenceNumber(Integer itemSequenceNumber) {
		this.itemSequenceNumber = itemSequenceNumber;
	}

	public ItemDTO getItemDTO() {
		return itemDTO;
	}

	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getItemRemark() {
		return itemRemark;
	}

	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
	}

}
