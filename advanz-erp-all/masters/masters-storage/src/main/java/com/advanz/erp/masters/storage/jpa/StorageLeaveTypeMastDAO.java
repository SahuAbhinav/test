package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.LeaveTypeMastEntity;
import com.advanz.erp.masters.entity.jpa.SalaryHeadEntity;
import com.advanz.erp.masters.storage.IStorageLeaveTypeMastDAO;





@Component
public class StorageLeaveTypeMastDAO extends JpaDaoSupport implements IStorageLeaveTypeMastDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(LeaveTypeMastEntity entity) {

		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		try{
		for(SalaryHeadEntity e:entity.encashmentList)
		System.out.print("StorageLeaveTypeMastDAO encashmentList :-"+e.getSalaryHeadId());
		}catch (Exception e) {
		e.fillInStackTrace();
		}
		getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public LeaveTypeMastEntity read(Integer uid) {
		if (uid == null) {
			throw new IllegalArgumentException("uid must not be null");
		}
		return getJpaTemplate().find(LeaveTypeMastEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public LeaveTypeMastEntity update(LeaveTypeMastEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		// entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(LeaveTypeMastEntity entity1) {
		if (entity1 == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		LeaveTypeMastEntity	entity = getJpaTemplate().find(LeaveTypeMastEntity.class,entity1.getLeaveId());
		entity.setDeletedFlag(true);
		entity.setModifiedUserId(entity1.getModifiedUserId());
		getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<LeaveTypeMastEntity> load() {

		return getJpaTemplate()
				.find("FROM LeaveTypeMastEntity e where e.deletedFlag=?1",false);
	}

	 public List<String> getLeaveType() {
		    return getJpaTemplate().find("SELECT e.leaveType FROM LeaveTypeMastEntity e where e.deletedFlag=?1 GROUP BY e.leaveType",false);
			}
	@Override
	@Transactional(readOnly = true)
	public <E> List<LeaveTypeMastEntity> findById(E uid) {
		return getJpaTemplate()
				.find("FROM LeaveTypeMastEntity e WHERE e.leaveId =?1 and e.deletedFlag=?2",uid, false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<LeaveTypeMastEntity> search(String leaveName,
			String leaveCode, String leaveType) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM LeaveTypeMastEntity e");
		boolean first = true;

		if (StringUtils.hasText(leaveName)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.leaveName like :leaveName");
			params.put("leaveName", "%" + leaveName.trim() + "%");
			first = false;
		}
		if (StringUtils.hasText(leaveCode)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.leaveCode like :leaveCode");
			params.put("leaveCode", "%" + leaveCode.trim() + "%");
			first = false;
		}
		if (StringUtils.hasText(leaveType)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.leaveType like :leaveType");
			params.put("leaveType", "%" + leaveType.trim() + "%");
			first = false;
		}

		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0 ORDER BY e.leaveName");

		first = false;

		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	@Transactional(readOnly = true)
	public List<LeaveTypeMastEntity> findByNameAndCode(String leaveName,
			String leaveCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM LeaveTypeMastEntity e");
		boolean first = true;

		if (StringUtils.hasText(leaveName)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.leaveName = :leaveName");
			params.put("leaveName", leaveName.trim());
			first = false;
		}
		if (StringUtils.hasText(leaveCode)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.leaveCode = :leaveCode");
			params.put("leaveCode", leaveCode.trim());
			first = false;
		}

		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0 ORDER BY e.leaveName");

		first = false;

		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}
	
	@Override
	public boolean isInUsed(Integer id) {	
		List<String> list=getJpaTemplate().find("Select employeeId FROM EmployeeEntity e WHERE e.employee.leaveTypeMastEntity.leaveId =?1 and e.deletedFlag=0",id);
		if(list!=null && list.size()>0){
			//logger.info("so number :" +list.get(0));			
				return true;			
		}
		return false;
	}

	@Override
	public List<LeaveTypeMastEntity> findByLeaveName(String leaveName) {
		// TODO Auto-generated method stub
		return getJpaTemplate().find("FROM LeaveTypeMastEntity e WHERE e.leaveName =?1 and e.deletedFlag=?2",leaveName, false);
	}

	

}
