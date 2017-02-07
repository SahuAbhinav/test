package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.LeaveTypeMastEntity;


public interface IStorageLeaveTypeMastDAO extends IStorageDAO<LeaveTypeMastEntity>{
	public List<LeaveTypeMastEntity> load();
	public List<String> getLeaveType();
    public <E> List<LeaveTypeMastEntity> findById(E id);
	public List<LeaveTypeMastEntity> findByNameAndCode(String leaveName,String leaveCode);
	public List<LeaveTypeMastEntity> search(String leaveName,String leaveCode,String leaveType);
	public boolean isInUsed(Integer  salaryHeadId);
	public List<LeaveTypeMastEntity> findByLeaveName(String leaveName);

}
