package com.advanz.erp.masters.storage.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.common.DataUtility;
import com.advanz.erp.masters.entity.jpa.SalaryDetailEntity;
import com.advanz.erp.masters.entity.jpa.SalaryLeaveEntity;
import com.advanz.erp.masters.entity.jpa.SalaryMasterEntity;
import com.advanz.erp.masters.storage.IStorageCompanyDAO;
import com.advanz.erp.masters.storage.IStorageSalaryDAO;


@Component
public class StorageSalaryMasterDAO extends JpaDaoSupport implements IStorageSalaryDAO{
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Autowired
	public IStorageCompanyDAO companyDAO;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(SalaryMasterEntity entity) {
		// TODO Auto-generated method stub
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot create a null entity");
	        }
	        getJpaTemplate().persist(entity);	
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public SalaryMasterEntity update(SalaryMasterEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
		 //entity=getJpaTemplate().find(IssueMasterEntity.class, entity.getIssueAutoId());	
		 List<SalaryDetailEntity> detailEntityList=entity.getSalaryDetailEntity();
		 
		 List<SalaryDetailEntity>  l=getJpaTemplate().find("FROM SalaryDetailEntity e where e.deletedFlag=?1  AND e.salaryTranAutoNo='"+entity.getSalaryTranAutoNo()+"'",false);
		
		 for(int i=0;i<l.size();i++){
			 SalaryDetailEntity detailEntity=	l.get(i);
				for(int j=0;j<detailEntityList.size();j++){
					SalaryDetailEntity detailEntity2=detailEntityList.get(j);
					if(detailEntity2.getSalaryTranAutoNo()!=detailEntity.getSalaryTranAutoNo() ){
						detailEntity.setDeletedFlag(true);
						detailEntity.setModifiedUserId(entity.getModifiedUserId());
						entity.getSalaryDetailEntity().add(detailEntity);
					}}}
	        entity.setDeletedFlag(false);
	        return getJpaTemplate().merge(entity);
		}


	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public void delete(SalaryMasterEntity entity1) {
		if (entity1 == null) {
            throw new IllegalArgumentException("Cannot remove a null entity");
        }
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.createQuery("DELETE FROM SalaryDetailEntity e WHERE e.salaryTranAutoNo = '"+entity1.getSalaryTranAutoNo()+"'").executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.getTransaction().begin();
		entityManager.createQuery("DELETE FROM SalaryMasterEntity e WHERE e.salaryTranAutoNo = '"+entity1.getSalaryTranAutoNo()+"'").executeUpdate();
		entityManager.getTransaction().commit();
		
		entityManager.getTransaction().begin();
		entityManager.createQuery("DELETE FROM SalaryAttendanceEntity e WHERE e.salaryTranAutoNo = '"+entity1.getSalaryTranAutoNo()+"'").executeUpdate();
		entityManager.getTransaction().commit();
		
		entityManager.getTransaction().begin();
		entityManager.createQuery("DELETE FROM SalaryLeaveEntity e WHERE e.salaryTranAutoNo = '"+entity1.getSalaryTranAutoNo()+"'").executeUpdate();
		entityManager.getTransaction().commit();
		
	 /* SalaryMasterEntity entity=getJpaTemplate().find(SalaryMasterEntity.class, entity1.getSalaryTranAutoNo());	
	  entity.setDeletedFlag(true);
	  List<SalaryDetailEntity> detailEntities=entity.getSalaryDetailEntity();
      for(SalaryDetailEntity e:detailEntities){
    	  e.setDeletedFlag(true); 
      e.setModifiedUserId(entity1.getModifiedUserId());	  
      }
      entity.setModifiedUserId(entity1.getModifiedUserId());*/
	  }

	@Override
    @Transactional(readOnly = true)
	public List<SalaryMasterEntity> load() {
       return getJpaTemplate().find("FROM SalaryMasterEntity e where e.deletedFlag=?1  ORDER BY e.salaryTranAutoNo DESC",false);
	}
	@Override
	 @Transactional(readOnly = true)
	public List getSalaryList() {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createNativeQuery("SELECT sm.salary_tran_auto_no, sm.salary_month, COUNT(DISTINCT sd.EMPLOYEE_ID) ,SUM(DISTINCT sd.net_salary),sm.salary_year, sm.approved_flag ,(SELECT NAME FROM `m_masters` WHERE masters_id=sm.`department_id`) AS department_name FROM t_salary_detail sd,t_salary_master sm WHERE sd.salary_tran_auto_no=sm.salary_tran_auto_no AND sm.deleted_flag=0  GROUP BY sd.salary_tran_auto_no ORDER BY sd.salary_tran_auto_no");
		List list=query.getResultList();
		
		return list;
	}
	
	@Override
	@Transactional(readOnly = true)
	public<E> List<SalaryMasterEntity> findBySalayrNumber(E id) {
		//logger.info("a service  >>>>>>>>>>>> : "+id);
		return getJpaTemplate().find("FROM SalaryMasterEntity e WHERE e.deletedFlag=?1 and e.deletedFlag=?2", id,false);
	}
	@Override
	@Transactional(readOnly = true)
	public List getSalaryByDepartmentIdAndMonth(Integer deptId, String month) {
		// TODO Auto-generated method stub
		
		return getJpaTemplate().find("FROM SalaryMasterEntity e WHERE e.departmentId='"+deptId+"' and e.salaryMonth='"+month+"' and e.deletedFlag=0");
	}
	@Override
	@Transactional(readOnly = true)
	public <E> List<SalaryMasterEntity> findById(E id) {
		// TODO Auto-generated method stub
		return getJpaTemplate().find("FROM SalaryMasterEntity e WHERE e.salaryTranAutoNo='"+id+"' and e.deletedFlag=0");
	}

	@Override
	@Transactional(readOnly = true)
	public Double getHeadAmount(Integer id,Integer headId,Integer employeeId) {
		// TODO Auto-generated method stub
		List l= getJpaTemplate().find("Select e.headAmount FROM SalaryDetailEntity e WHERE e.salaryTranAutoNo='"+id+"' and e.headId='"+ headId+"' and e.employeeEntity.employeeId='"+employeeId+"' and e.deletedFlag=0");
	Double d=0.0;
	if(l!=null && l.size()>0){
		d=(Double)l.get(0);
	}
	return d;
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List getAdvanceAmount(E id) {
		return getJpaTemplate().find("Select SUM(e.dedAdvanceAmt),SUM(e.balAdvanceAmt),e.employeeEntity.employeeId FROM SalaryDetailEntity e WHERE e.salaryTranAutoNo='"+id+"' and e.deletedFlag=0 group by e.employeeEntity.employeeId ORDER BY e.employeeEntity.employeeName");
	}
	@Override
	@Transactional(readOnly = true)
	public <E> List getAdvanceAmountByMonthNameAndDeartment(String monthName,Integer departmentId) {
		return getJpaTemplate().find("Select e.dedAdvanceAmt,e.balAdvanceAmt,e.employeeEntity.employeeId FROM SalaryDetailEntity e WHERE e.salaryTranAutoNo=(Select sm.salaryTranAutoNo from SalaryMasterEntity sm where sm.salaryMonth='"+monthName+"' and sm.departmentId='"+departmentId+"' and sm.deletedFlag=0) and e.deletedFlag=0 group by e.employeeEntity.employeeId");
	}

	


@Override
public List<SalaryMasterEntity> search(Date fromDate, Date toDate,
		String employeeName) {
	// TODO Auto-generated method stub
	return null;
}



@Override
public SalaryMasterEntity read(Integer id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public <E> List getNetSalary(E id) {
	return getJpaTemplate().find("Select e.netSalary FROM SalaryDetailEntity e WHERE e.salaryTranAutoNo='"+id+"' and e.deletedFlag=0 group by e.employeeEntity.employeeId");
}

public static void main(String[] args) {
	Date date=new Date();
	System.out.println(DataUtility.getMonthName(date.getMinutes()));
}

@Override
public Integer getApprovedFlag(String monthName) {
	List l= getJpaTemplate().find("SELECT e.approvedFlag FROM SalaryMasterEntity e where e.deletedFlag=0");
	Integer i=0;
	if(l!=null && l.size()>0){
	i=(Integer)l.get(0);
	}
	return i;
    }

/*@Override
public List<SalaryMasterEntity> findByMonthEmpIdLeaveId(String monthName,Integer empId,Integer leaveId) {
	return  getJpaTemplate().find("Select e FROM SalaryMasterEntity e, SalaryLeaveEntity sl where e.salaryMonth='"+monthName+"' and sl.employeeId="+empId+" and sl.leaveId="+leaveId+" and e.deletedFlag=0");
}*/
@Override
public List<SalaryLeaveEntity> findByMonthEmpIdLeaveId(String monthName,Integer empId,Integer leaveId) {
	return  getJpaTemplate().find("FROM  SalaryLeaveEntity sl where sl.salaryMonth='"+monthName+"' and sl.employeeId="+empId+" and sl.leaveId="+leaveId+" and sl.deletedFlag=0");
}
}
