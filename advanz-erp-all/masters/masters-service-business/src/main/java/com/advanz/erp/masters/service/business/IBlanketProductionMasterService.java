package com.advanz.erp.masters.service.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.BlanketProductionDetailLeftDTO;
import com.advanz.erp.masters.model.BlanketProductionDetailRightDTO;
import com.advanz.erp.masters.model.BlanketProductionMasterDTO;
import com.advanz.erp.masters.model.FinishedGoodsMasterDTO;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterInputMessage;
import com.advanz.erp.masters.model.msg.BlanketProductionMasterOutputMessage;

public interface IBlanketProductionMasterService extends IAdvanzErpBaseSerivce{	
	public BlanketProductionMasterOutputMessage createBlanketProductionMaster(BlanketProductionMasterInputMessage blanketProductionMasterInputMessage);
	
	public BlanketProductionMasterOutputMessage updateBlanketProductionMaster(BlanketProductionMasterInputMessage blanketProductionMasterInputMessage);
	
	public BlanketProductionMasterOutputMessage deleteBlanketProductionMaster(BlanketProductionMasterInputMessage blanketProductionMasterInputMessage);
	
	public BlanketProductionMasterOutputMessage findBlanketProductionMasterById(BlanketProductionMasterInputMessage blanketProductionMasterInputMessage);
	
	public BlanketProductionMasterOutputMessage findAllBlanketProductionMasters();
	public BlanketProductionMasterOutputMessage search(BlanketProductionMasterInputMessage blanketProductionMasterInputMessage);
    public Integer getMaxBlanketProdId();
    
    
    public BlanketProductionMasterOutputMessage createBlanketProductionLeft(BlanketProductionMasterInputMessage blanketProductionMasterInputMessage);
    public BlanketProductionMasterOutputMessage createBlanketProductionRight(BlanketProductionMasterInputMessage blanketProductionMasterInputMessage);
	
    public Boolean deleteLeftBlanketProduction(BlanketProductionDetailLeftDTO dto);
    public Boolean deleteRightBlanketProduction(BlanketProductionDetailRightDTO dto);
    
    public String checkDuplicateRecordInBPMaster(BlanketProductionMasterDTO blanketProductionMasterDTO);
	public ArrayList<Integer> getMaxRollNoL(String batchNo);
	public ArrayList<Integer> getMaxRollNoR(String batchNo);
	
	public List getBlanketProductionLeft(Date date,Integer shiftId);
	public List getBlanketProductionRight(Date date,Integer shiftId);

	public List updateBlanketProductionLeftRight(List<BlanketProductionDetailLeftDTO> left, List<BlanketProductionDetailRightDTO> right,Integer userId);

	public List getRejBlanketProductionLeftList(String status,Date date,Integer shiftId);
	public List getRejBlanketProductionRightList(String status,Date date,Integer shiftId);
	public List updateRejectedBlanketProductionLeftRight(List<BlanketProductionDetailLeftDTO> left, List<BlanketProductionDetailRightDTO> right,Integer userId);
	public List updateApprovedBlanketProduction(List<BlanketProductionDetailLeftDTO> left, List<BlanketProductionDetailRightDTO> right,Integer userId,FinishedGoodsMasterDTO finishedGoodsMasterDTO);
	
	public BlanketProductionMasterOutputMessage findBlanketProductionPagination(BlanketProductionMasterInputMessage blanketProductionMasterInputMessage);
	public BlanketProductionMasterOutputMessage getDataForShiftReport(BlanketProductionMasterInputMessage blanketProductionMasterInputMessage);
	
	public List getCheckDuplicatRollNoL(String batchNo,Integer rollNoL);
    public List getCheckDuplicatRollNoR(String batchNo,Integer rollNoR);
    public void updateBlanketProduction(Date date,String runNo,Integer shiftName,String batchNo,Integer status);
    public Boolean checkFinishedGood(String finishedGoodNumber);
    public Double getBlanketWeightRecord(Character position,Integer createdUserId);
    public Timestamp getLastBlanketEntryDate();
}
