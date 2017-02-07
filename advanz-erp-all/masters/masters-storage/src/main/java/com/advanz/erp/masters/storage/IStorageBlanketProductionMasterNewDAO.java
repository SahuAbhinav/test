package com.advanz.erp.masters.storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.BlanketProductionDetailNewEntity;
import com.advanz.erp.masters.entity.jpa.BlanketProductionMasterNewEntity;

public interface IStorageBlanketProductionMasterNewDAO extends
		IStorageDAO<BlanketProductionMasterNewEntity> {
	public List<BlanketProductionMasterNewEntity> load();

	public <E> List<BlanketProductionMasterNewEntity> findById(E id);

	public List<BlanketProductionMasterNewEntity> search(Date fromDate,
			Date toDate, String runNo, Integer gradeId);

	public List<BlanketProductionMasterNewEntity> findByDateAndRunNo(Date date,
			String runNo, Integer shiftName, String batchNo, Integer status);

	public void createBlanketProductionDetail(
			BlanketProductionDetailNewEntity blanketProductionDetailEntity);

	public Boolean deleteBlanketProductionDetail(
			BlanketProductionDetailNewEntity blanketProductionDetailEntity);

	public Integer getMaxBlanketProdId();

	public ArrayList<Integer> getMaxRollNo(String batchNo,
			Integer BlanketMasterId, Integer rNo);

	public List getBlanketProductionDetail(Date date, Integer shiftId);

	public void updateBlanketProductionDetail(
			BlanketProductionDetailNewEntity detailEntity, String flag);

	public List<BlanketProductionDetailNewEntity> getRejBlanketProductionDetail(
			String status, Date date, Integer shiftId);

	public List<BlanketProductionMasterNewEntity> findBlanketProductionPagination(
			Integer next);

	public List<BlanketProductionMasterNewEntity> getDataForShiftReport(
			Date date, String runNo, Integer shiftName);

	public List getCheckDuplicatRollNo(String batchNo, Integer rollNoL);

	public void updateBlanket(Date date, String runNo, Integer shiftName,
			Integer status);

	public Boolean checkFinishedGood(String finishedGoodNumber);

	public void cleanDuplicateEntry();

	public Double getBlanketWeightRecord(Character position,
			Integer createdUserId) throws Exception;

	public List getLastBlanketEntryDate();

	public int getMaxDetailId(int bpMasterId);
}
