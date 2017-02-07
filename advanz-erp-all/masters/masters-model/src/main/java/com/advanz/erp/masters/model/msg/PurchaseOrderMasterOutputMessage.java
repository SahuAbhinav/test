package com.advanz.erp.masters.model.msg;

import java.sql.Timestamp;
import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.PurchaseOrderDetailDTO;
import com.advanz.erp.masters.model.PurchaseOrderMasterDTO;

@SuppressWarnings("serial")
public class PurchaseOrderMasterOutputMessage extends
		AdvanzErpBaseOutputMessage {

	private PurchaseOrderMasterDTO purchaseOrderMasterDTO;

	private List<PurchaseOrderMasterDTO> purchaseOrderMasterDTOList;

	private Integer purchaseOrderSeriesNo;

	private List<PurchaseOrderDetailDTO> purchaseOrderDetailDTOList;

	private Timestamp purchaseOrderSeriesDate;

	public Timestamp getPurchaseOrderSeriesDate() {
		return purchaseOrderSeriesDate;
	}

	public void setPurchaseOrderSeriesDate(Timestamp purchaseOrderSeriesDate) {
		this.purchaseOrderSeriesDate = purchaseOrderSeriesDate;
	}

	public List<PurchaseOrderDetailDTO> getPurchaseOrderDetailDTOList() {
		return purchaseOrderDetailDTOList;
	}

	public void setPurchaseOrderDetailDTOList(
			List<PurchaseOrderDetailDTO> purchaseOrderDetailDTOList) {
		this.purchaseOrderDetailDTOList = purchaseOrderDetailDTOList;
	}

	public Integer getPurchaseOrderSeriesNo() {
		return purchaseOrderSeriesNo;
	}

	public void setPurchaseOrderSeriesNo(Integer purchaseOrderSeriesNo) {
		this.purchaseOrderSeriesNo = purchaseOrderSeriesNo;
	}

	public PurchaseOrderMasterDTO getPurchaseOrderMasterDTO() {
		return purchaseOrderMasterDTO;
	}

	public void setPurchaseOrderMasterDTO(
			PurchaseOrderMasterDTO purchaseOrderMasterDTO) {
		this.purchaseOrderMasterDTO = purchaseOrderMasterDTO;
	}

	public List<PurchaseOrderMasterDTO> getPurchaseOrderMasterDTOList() {
		return purchaseOrderMasterDTOList;
	}

	public void setPurchaseOrderMasterDTOList(
			List<PurchaseOrderMasterDTO> purchaseOrderMasterDTOList) {
		this.purchaseOrderMasterDTOList = purchaseOrderMasterDTOList;
	}

}
