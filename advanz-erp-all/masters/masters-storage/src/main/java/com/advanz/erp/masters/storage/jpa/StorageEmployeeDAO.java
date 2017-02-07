package com.advanz.erp.masters.storage.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.EmployeeEntity;
import com.advanz.erp.masters.entity.jpa.EmployeeLeaveKey;
import com.advanz.erp.masters.entity.jpa.EmployeeLeavesEntity;
import com.advanz.erp.masters.entity.jpa.EmployeeSalaryDepKey;
import com.advanz.erp.masters.entity.jpa.EmployeeSalaryDetEntity;
import com.advanz.erp.masters.storage.IStorageEmployeeDAO;

@Component
public class StorageEmployeeDAO extends JpaDaoSupport implements IStorageEmployeeDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(EmployeeEntity entity) {

		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		entity.setDeletedFlag(false);
		getJpaTemplate().persist(entity);

		List<EmployeeLeavesEntity> obj = entity.getEmployeeLeavesEntityList();
		List<EmployeeSalaryDetEntity> objSalary = entity
				.getEmployeeSalaryDetEntityList();
		if (obj != null) {
			for (EmployeeLeavesEntity list : obj) {
				EmployeeLeaveKey obj1 = new EmployeeLeaveKey();
				obj1.setEmployee_id(entity.getEmployeeId());
				obj1.setLeave_id(list.getId().getLeave_id());
				list.setId(obj1);
				getJpaTemplate().persist(list);
			}
		}

		if (objSalary != null) {
			for (EmployeeSalaryDetEntity list : objSalary) {
				EmployeeSalaryDepKey obj1 = new EmployeeSalaryDepKey();
				obj1.setEmployee_id(entity.getEmployeeId());
				obj1.setSalary_head_id(list.getId().getSalary_head_id());
				list.setId(obj1);
				getJpaTemplate().persist(list);
			}
		}

	}

	@Override
	@Transactional(readOnly = true)
	public EmployeeEntity read(Integer uid) {
		if (uid == null) {
			throw new IllegalArgumentException("uid must not be null");
		}
		return getJpaTemplate().find(EmployeeEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public EmployeeEntity update(EmployeeEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		entity.setDeletedFlag(false);
		List<EmployeeLeavesEntity> obj = entity.getEmployeeLeavesEntityList();
		List<EmployeeSalaryDetEntity> objSalary = entity
				.getEmployeeSalaryDetEntityList();
		if (obj != null) {
			List<EmployeeLeavesEntity> newList = new ArrayList<EmployeeLeavesEntity>();
			for (EmployeeLeavesEntity list : obj) {
				EmployeeLeaveKey obj1 = new EmployeeLeaveKey();
				obj1.setEmployee_id(entity.getEmployeeId());
				obj1.setLeave_id(list.getId().getLeave_id());
				list.setId(obj1);
				newList.add(list);
				getJpaTemplate().merge(list);
			}
			entity.setEmployeeLeavesEntityList(newList);
		}

		if (objSalary != null) {
			List<EmployeeSalaryDetEntity> newList = new ArrayList<EmployeeSalaryDetEntity>();
			for (EmployeeSalaryDetEntity list : objSalary) {
				EmployeeSalaryDepKey obj1 = new EmployeeSalaryDepKey();
				obj1.setEmployee_id(entity.getEmployeeId());
				obj1.setSalary_head_id(list.getId().getSalary_head_id());
				list.setId(obj1);
				newList.add(list);
				getJpaTemplate().merge(list);
			}
			entity.setEmployeeSalaryDetEntityList(newList);
		}
		return getJpaTemplate().merge(entity);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(EmployeeEntity entity1) {
		if (entity1 == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		EmployeeEntity entity = getJpaTemplate().find(EmployeeEntity.class,entity1.getEmployeeId());
		entity.setDeletedFlag(true);
		entity.setModifiedUserId(entity1.getModifiedUserId());
		getJpaTemplate().merge(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeEntity> load() {

		return getJpaTemplate().find("FROM EmployeeEntity e where e.deletedFlag=0 ORDER BY e.employeeName ");
	}
	@Override
	@Transactional(readOnly = true)
	public List<EmployeeEntity> preLoad() {
		return getJpaTemplate().find("SELECT e.employeeName,e.employeeId,e.employeeCode FROM EmployeeEntity e where e.deletedFlag=0 and e.activeStatus=1 ORDER BY e.employeeName ");
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<EmployeeEntity> findById(Integer uid) {

		return getJpaTemplate().find("FROM EmployeeEntity e WHERE e.employeeId = " + uid+ " ORDER BY e.employeeName");
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeEntity> search(String employeeName,
			String employeeCode, String employeeCity, int pmFlag) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM EmployeeEntity e");
		boolean first = true;

		if (StringUtils.hasText(employeeName)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.employeeName like :employeeName");
			params.put("employeeName", "%" + employeeName.trim() + "%");
			first = false;
		}
		if (StringUtils.hasText(employeeCode)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.employeeCode = :employeeCode");
			params.put("employeeCode", employeeCode.trim());
			first = false;
		}
		if (StringUtils.hasText(employeeCity)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.employeeCityEntity.cityName like :employeeCity");
			params.put("employeeCity", "%" + employeeCity.trim() + "%");
			first = false;
		}
		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0 ");
		hql.append(" ORDER BY e.employeeName");

		first = false;

		logger.info("HQL >>>>>>>>>>>> : " + hql.toString());
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeLeavesEntity> findLeaveByEmployeeId(Integer empID) {
		return getJpaTemplate().find("FROM EmployeeLeavesEntity e WHERE e.employee.employeeId = "+ empID);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeSalaryDetEntity> findSalaryByEmployeeId(Integer empID) {
		return getJpaTemplate().find("FROM EmployeeSalaryDetEntity e WHERE e.employee.employeeId = "+ empID);
	}

	@Override
	public List<EmployeeEntity> findAllActivatedEmployee() {
		return getJpaTemplate().find("FROM EmployeeEntity e where e.deletedFlag=0 and e.activeStatus=1 ORDER BY e.employeeName ");
	}

	@Override
	public List<EmployeeEntity> findAllActivatedEmployeeByDeptId(Integer deptId) {
		return getJpaTemplate().find("FROM EmployeeEntity e where e.masterEntityDepartment='"+deptId+"' and e.deletedFlag=0 and e.activeStatus=1 ORDER BY e.employeeName ");
	}

	@Override
	@Transactional(readOnly=true)
	public List<EmployeeEntity> findAllActivatedEmployeeByJoinDate(String joinDate) {
		return getJpaTemplate().find("FROM EmployeeEntity e where cast(e.joinDate as date)<='"+joinDate+"' and e.deletedFlag=0 and e.activeStatus=1 ORDER BY e.employeeName ");	}
	
}
