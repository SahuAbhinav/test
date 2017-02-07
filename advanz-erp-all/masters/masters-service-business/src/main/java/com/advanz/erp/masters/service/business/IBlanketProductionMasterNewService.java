package com.advanz.erp.masters.service.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.BlanketProductionDetailNewDTO;
import com.advanz.erp.masters.model.BlanketProductionMasterNewDTO;
import com.advanz.erp.masters.model.FinishedGoodsMasterDTO;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterNewInputMessage;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterNewOutputMessage;

public interface IBlanketProductionMasterNewService extends
		IAdvanzErpBaseSerivce {
	public BlanketProductionMasterNewOutputMessage createBlanketProductionMaster(
			BlanketProductionMasterNewInputMessage BlanketProductionMasterNewInputMessage);

	public BlanketProductionMasterNewOutputMessage updateBlanketProductionMaster(
			BlanketProductionMasterNewInputMessage BlanketProductionMasterNewInputMessage);

	public BlanketProductionMasterNewOutputMessage deleteBlanketProductionMaster(
			BlanketProductionMasterNewInputMessage BlanketProductionMasterNewInputMessage);

	public BlanketProductionMasterNewOutputMessage findBlanketProductionMasterById(
			BlanketProductionMasterNewInputMessage BlanketProductionMasterNewInputMessage);

	public BlanketProductionMasterNewOutputMessage findAllBlanketProductionMasters();

	public BlanketProductionMasterNewOutputMessage search(
			BlanketProductionMasterNewInputMessage BlanketProductionMasterNewInputMessage);

	public Integer getMaxBlanketProdId();

	public BlanketProductionMasterNewOutputMessage createBlanketProductionDetail(
			BlanketProductionMasterNewInputMessage BlanketProductionMasterNewInputMessage);

	public Boolean deleteBlanketProductionDetail(
			BlanketProductionDetailNewDTO dto);

	public String checkDuplicateRecordInBPMaster(
			BlanketProductionMasterNewDTO blanketProductionMasterNewDTO);

	public ArrayList<Integer> getMaxRollNo(String batchNo,Integer spliterCount,Integer BlanketMasterId,Integer runNo);

	public List getBlanketProductionDetail(Date date, Integer shiftId);

	public List updateBlanketProductionDetail(
			List<BlanketProductionDetailNewDTO> left, Integer userId);

	public List getRejBlanketProductionDetailList(String status, Date date,
			Integer shiftId);

	public List updateRejectedBlanketProductionDetail(
			List<BlanketProductionDetailNewDTO> left, Integer userId);

	public List updateApprovedBlanketProductionDetail(
			List<BlanketProductionDetailNewDTO> left, Integer userId,
			FinishedGoodsMasterDTO finishedGoodsMasterDTO);

	public BlanketProductionMasterNewOutputMessage findBlanketProductionPagination(
			BlanketProductionMasterNewInputMessage BlanketProductionMasterNewInputMessage);

	public BlanketProductionMasterNewOutputMessage getDataForShiftReport(
			BlanketProductionMasterNewInputMessage BlanketProductionMasterNewInputMessage);

	public List getCheckDuplicatRollNo(String batchNo, Integer rollNoL);

	public void updateBlanketProduction(Date date, String runNo,
			Integer shiftName, String batchNo, Integer status);

	public Boolean checkFinishedGood(String finishedGoodNumber);

	public Double getBlanketWeightRecord(Character position,
			Integer createdUserId);
	

	public Timestamp getLastBlanketEntryDate();
}
