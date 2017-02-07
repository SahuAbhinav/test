package com.advanz.erp.masters.storage.jpa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.common.DataUtility;
import com.advanz.erp.masters.entity.jpa.AttandanceMasterEntity;
import com.advanz.erp.masters.storage.IStorageAttandanceMasterDAO;

@Component
public class StorageAttandanceMasterDAO extends JpaDaoSupport implements IStorageAttandanceMasterDAO{
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(AttandanceMasterEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public AttandanceMasterEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(AttandanceMasterEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public AttandanceMasterEntity update(AttandanceMasterEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
	       // entity.setDeletedFlag(false);
		 return getJpaTemplate().merge(entity);
		}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(AttandanceMasterEntity entity1) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		if (entity1 == null) {
            throw new IllegalArgumentException("Cannot remove a null entity");
        }
		//AttandanceMasterEntity  entity=getJpaTemplate().find(AttandanceMasterEntity.class, entity1.getSno());	
	   	//getJpaTemplate().merge(entity);	
	   
	    DateFormat readFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String s= readFormat.format(entity1.getDate());
	  
		entity1.setDeletedFlag(true);
		entityManager.getTransaction().begin();
		entityManager.createQuery("Delete FROM AttandanceMasterEntity e where e.date = '"+s+"'").executeUpdate();
		entityManager.getTransaction().commit();
	}

	@Override
    @Transactional(readOnly = true)
	public List<AttandanceMasterEntity> load() {
        //return getJpaTemplate().find("FROM BlanketProductionMasterEntity e where e.deletedFlag=?1  ORDER BY e.modifiedDate DESC",false);
		 return getJpaTemplate().find("FROM AttandanceMasterEntity e where e.deletedFlag=?1  ORDER BY e.sno DESC",false);
	}

	@Override
	@Transactional(readOnly = true)
	public<E> List<AttandanceMasterEntity> findById(E uid) {		
		return getJpaTemplate().find("FROM AttandanceMasterEntity e WHERE e.sno =?1 and e.deletedFlag=?2", uid,false);
	}
	

	@Override
	@Transactional(readOnly = true)
	public List<AttandanceMasterEntity> search(Date date,String orderBy){
		 String fromDate1="";
		if (date!=null ) {
		    if (date!=null){
		     fromDate1=DataUtility.getDate(date);
		    }
		}
		int orderB=4;
		try {
			if(orderBy!=null && orderBy.equalsIgnoreCase("1")){
				orderB=3;
			}
		} catch (NumberFormatException e) {
			
		}
		List list =new ArrayList();
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		list = (List)entityManager.createNativeQuery("SELECT m_employee.employee_code ,m_employee.employee_id, CONCAT(IFNULL(m_employee.employee_first_name,''),' ',IFNULL(m_employee.employee_last_name,'')) AS full_name , t_attandance_master.attandance_flag AS attandance_flag , IFNULL(t_attandance_master.day_status,'') AS day_status, IFNULL(t_attandance_master.date,'') AS dat, IFNULL(t_attandance_master.day_of_date,'') AS day_of_date FROM t_attandance_master LEFT JOIN m_employee ON m_employee.employee_id=t_attandance_master.employee_id AND (m_employee.join_date IS NULL OR m_employee.join_date<=CURRENT_DATE)  WHERE m_employee.deleted_flag=0 AND t_attandance_master.date='"+ fromDate1+"' AND m_employee.active_status=1 ORDER BY "+orderB).getResultList();
		//list.add(object);
		entityManager.close();
		return list;
		
	}

	@Override
	public Double coutLeaves(Integer employeeId, String leaveType,
			Date fromDate, Date toDate) {
		
		String fromDate1="";
		String toDate1="";
		
		    if (fromDate!=null){
		     fromDate1=DataUtility.getDate(fromDate);
		    }if (toDate!=null){
		     toDate1=DataUtility.getDate(toDate);
		    }
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		/*Object object = (Object)entityManager.createNativeQuery("SELECT COUNT(`attandance_flag`)  FROM `t_attandance_master` WHERE `attandance_flag`='"+leaveType+"' AND `employee_id`='"+employeeId+"' AND cast(date as date)>='"+fromDate1+"' AND cast(date as date)<='"+toDate1+"'").getSingleResult();
		Double d=Double.parseDouble(object.toString());*/
	
		Object object = (Object)entityManager.createNativeQuery("SELECT COUNT(`attandance_flag`)  FROM `t_attandance_master` WHERE `attandance_flag`='"+leaveType+"' AND `employee_id`='"+employeeId+"' AND cast(date as date)>='"+fromDate1+"' AND cast(date as date)<='"+toDate1+"' AND day_status='Full Day'").getSingleResult();
		Double d=Double.parseDouble(object.toString());
		
		Object object1 = (Object)entityManager.createNativeQuery("SELECT COUNT(`attandance_flag`)  FROM `t_attandance_master` WHERE `attandance_flag`='"+leaveType+"' AND `employee_id`='"+employeeId+"' AND cast(date as date)>='"+fromDate1+"' AND cast(date as date)<='"+toDate1+"' AND day_status='Half Day'").getSingleResult();
		Double halfDay=Double.parseDouble(object1.toString());
		if(halfDay!=null){
			halfDay=halfDay/2;
		}
		d=d+halfDay;
		entityManager.close();
		return d;
	}

	@Override
	public Double countByHalfDay(Integer employeeId, String leaveType,
			Date fromDate, Date toDate) {
		String fromDate1="";
		String toDate1="";
		
		    if (fromDate!=null){
		     fromDate1=DataUtility.getDate(fromDate);
		    }if (toDate!=null){
		     toDate1=DataUtility.getDate(toDate);
		    }
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		
		Object object1 = (Object)entityManager.createNativeQuery("SELECT COUNT(`attandance_flag`)  FROM `t_attandance_master` WHERE `attandance_flag`='"+leaveType+"' AND `employee_id`='"+employeeId+"' AND cast(date as date)>='"+fromDate1+"' AND cast(date as date)<='"+toDate1+"' AND day_status='Half Day'").getSingleResult();
		Double halfDay=Double.parseDouble(object1.toString());
		if(halfDay!=null){
			halfDay=halfDay/2;
		}
		entityManager.close();
		return halfDay;
	}
	
	@Override
	public Double countByFullDay(Integer employeeId, String leaveType,
			Date fromDate, Date toDate) {
		String fromDate1="";
		String toDate1="";
		
		    if (fromDate!=null){
		     fromDate1=DataUtility.getDate(fromDate);
		    }if (toDate!=null){
		     toDate1=DataUtility.getDate(toDate);
		    }
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		
		Object object1 = (Object)entityManager.createNativeQuery("SELECT COUNT(`attandance_flag`)  FROM `t_attandance_master` WHERE `attandance_flag`='"+leaveType+"' AND `employee_id`='"+employeeId+"' AND cast(date as date)>='"+fromDate1+"' AND cast(date as date)<='"+toDate1+"' AND day_status='Full Day'").getSingleResult();
		Double fullDay=Double.parseDouble(object1.toString());
		entityManager.close();
		return fullDay;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void deleteAttendaceByEmp(Integer empId,Date date) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		 DateFormat readFormat = new SimpleDateFormat("yyyy-MM-dd");
		 String empDate= readFormat.format(date);
		 entityManager.getTransaction().begin();
	     entityManager.createQuery("Delete FROM AttandanceMasterEntity e where e.date = '"+empDate+"' and e.employeeId='"+empId+"'").executeUpdate();
		 entityManager.getTransaction().commit();
	}
	@Override
	public List<AttandanceMasterEntity> getAttandanceByMonthYearEmpId(String month, int year,int empid)
			{
		Integer month1=Integer.parseInt(month);
        month1 =month1+1;
        List <AttandanceMasterEntity> list= getJpaTemplate().find("FROM AttandanceMasterEntity e WHERE  e.deletedFlag=?1 and Month(e.date)=?2 and Year(e.date)=?3 and e.employeeId=?4",false,month1,year,empid);
        System.out.println("list is "+list.size());
       return list;
	}
}
