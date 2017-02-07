package com.advanz.erp.masters.storage.jpa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.storage.IStorageMastersDAO;

@Component
public class StorageMastersDAO extends JpaDaoSupport implements IStorageMastersDAO{
	@Autowired
	EntityManagerFactory entityManagerFactory;
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(MastersEntity entity) {		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
       int id=0;
        List list=getJpaTemplate().find("SELECT max(e.id) FROM MastersEntity e WHERE e.formId=?1",entity.getFormId());
        if(list!=null && list.size()>0){
        	Number n=(Number)list.get(0);
        	if(n!=null)
        	id=n.intValue();
        }
        id++;
        entity.setId(id);
        Integer mastersId= Integer.parseInt(entity.getFormId()*1000+""+id);
        entity.setMastersId(mastersId);
        getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public MastersEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(MastersEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MastersEntity update(MastersEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
	       // entity.setDeletedFlag(false);
	        return getJpaTemplate().merge(entity);
		}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(MastersEntity entity1) {
		if (entity1 == null) {
            throw new IllegalArgumentException("Cannot remove a null entity");
        }
		MastersEntity entity=getJpaTemplate().find(MastersEntity.class, entity1.getMastersId());			
	  entity.setDeletedFlag(true);
	  entity.setModifiedUserId(entity1.getModifiedUserId());
      getJpaTemplate().merge(entity);				
	}

	@Override
    @Transactional(readOnly = true)
	public List<MastersEntity> load() {
		
        return getJpaTemplate().find("FROM MastersEntity e where e.deletedFlag=?1  ORDER BY e.formName,e.name",false);
	}
    public List<String> getFormName() {
    return getJpaTemplate().find("SELECT e.formName  FROM MastersEntity e where e.deletedFlag=?1 GROUP BY e.formName  ORDER BY e.formName,e.name",false);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<MastersEntity> search(String formName,String name,String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM MastersEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(name)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.name like :name");
		    params.put("name", "%"+name.trim()+"%");
		    first=false;
		}
		//logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		if (StringUtils.hasText(code)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.code like :code");
		    params.put("code", "%"+code.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(formName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.formName like :formName");
		    params.put("formName", "%"+formName.trim()+"%");
		    first=false;
		}
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ");
	   
	    first=false;
		hql.append(" ORDER BY e.formName,e.name");
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MastersEntity> findByNameAndCode(String name,String code, Integer formId) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM MastersEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(name)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.name = :name");
		    params.put("name", name.trim());
		    first=false;
		}
	
		if (StringUtils.hasText(code)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.code = :code");
		    params.put("code", code.trim());
		    first=false;
		}
			
		if (formId!=null) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.formId = :formId");
		    params.put("formId", formId);
		    first=false;
		}
			
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ");
	   
	    first=false;
		
	
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MastersEntity> findById(Integer id, Integer formId) {
		
		return getJpaTemplate().find("FROM MastersEntity e WHERE e.mastersId =?1 and e.formId=?2 and e.deletedFlag=?3 ORDER BY name", id,formId,false);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MastersEntity> findById(Integer id) {
		return getJpaTemplate().find("FROM MastersEntity e WHERE e.mastersId=?1 and e.deletedFlag=?2 ORDER BY name", id,false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MastersEntity> findByFormId(Integer formId) {
		return getJpaTemplate().find("FROM MastersEntity e WHERE e.formId=?1 and e.deletedFlag=?2  ORDER BY name", formId,false);
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<MastersEntity> findByFormIdForMelterLogBook(Integer formId) {
		return getJpaTemplate().find("Select  e.mastersId,e.name FROM MastersEntity e WHERE e.formId=?1 and e.deletedFlag=?2  ORDER BY name", formId,false);
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<MastersEntity> findByFormName(String formName) {
		return getJpaTemplate().find("FROM MastersEntity e WHERE e.formName=?1 and e.deletedFlag=?2 ORDER BY name", formName,false);		
	}

	@Override
    @Transactional(readOnly = true)
	public List<MastersEntity> findSubdepartment(Integer id, Integer formId) {
		
        return getJpaTemplate().find("FROM MastersEntity e WHERE e.deptId =?1 and e.formId=?2 and e.deletedFlag=?3 ORDER BY name", id,formId,false);
	}

	@Override
	public boolean isInUsed(Integer id) {
		EntityManager em=getJpaTemplate().getEntityManagerFactory().createEntityManager();
	
		Query q=em.createQuery("Select employeeId FROM EmployeeEntity e WHERE e.deletedFlag=0 and (e.masterEntitReligion=:id or e.masterEntitCast=:id or e.masterEntitLanguage=:id or e.qualificationId=:id or e.masterEntityGrade=:id or e.employeeType=:id or e.designation=:id or e.masterEntityDepartment=:id or e.masterSubEntityDepartment=:id or e.masterEntitShift=:id)");
		q.setParameter("id", id);
		List<Integer> list=q.getResultList();
		//List<Integer> list=getJpaTemplate().find("Select employeeId FROM EmployeeEntity e WHERE e.deletedFlag=0 and e. ="+id);
		if(list!=null && list.size()>0){
			return true;
		}
		
		q=em.createQuery("Select itemId FROM ItemEntity e WHERE e.deletedFlag=0 and (e.masterGradeEntity.mastersId=:id or e.masterPackEntity.mastersId=:id or e.masterUnitEntity.mastersId=:id or e.masterPrimaryUnit=:id or e.masterSecondaryUnit=:id or e.masterPrimaryConverUnit=:id or e.masterSecondaryConverUnit=:id or e.masterGrossWeightUnit=:id or e.masterNetWeightUnit=:id or e.masterLengthUnit=:id or e.masterWidthUnit=:id or e.masterThiknessUnit=:id or e.masterDensityUnit=:id)");
		q.setParameter("id", id);
		list=q.getResultList();
		if(list!=null && list.size()>0){
			return true;
		}
		
		q=em.createQuery("Select partyId FROM PartyEntity e WHERE e.deletedFlag=0 and e.formId=:id");
		q.setParameter("id", id);
		list=q.getResultList();
		if(list!=null && list.size()>0){
			return true;
		}
		q=em.createQuery("Select poAutoId FROM PurchaseOrderMasterEntity e WHERE e.deletedFlag=0 and e.formTypeId=:id");
		q.setParameter("id", id);
		list=q.getResultList();
		if(list!=null && list.size()>0){
			return true;
		}
		q=em.createQuery("Select grnAutoId FROM GrnMasterEntity e WHERE e.deletedFlag=0 and e.formTypeId=:id");
		q.setParameter("id", id);
		list=q.getResultList();
		if(list!=null && list.size()>0){
			return true;
		}
		q=em.createQuery("Select invoiceAutoId FROM BillEntity e WHERE e.deletedFlag=0 and e.formTypeId=:id");
		q.setParameter("id", id);
		list=q.getResultList();
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public List findByDate(Date date) {
		DateFormat readFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String dt= readFormat.format(date);
	    /*
		String s=null;
		List l=getJpaTemplate().find("SELECT b.name from MastersEntity b where b.holidayFromDate <= '"+dt+"' and b.holidayToDate >= '"+dt+"' and b.deletedFlag=0 ");
	     if(l!=null && l.size()>0){
	    	s=(String)l.get(0);
	     }
		return s;*/
		
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Object object=null;
		try {
			object = (Object) entityManager.createNativeQuery("SELECT NAME FROM `m_masters`  WHERE `holiday_from_date`<= '"+dt+"' AND `holiday_to_date`>='"+dt+"' and deleted_flag=0").getSingleResult();;
		} catch (Exception e) {
			
		}
		List list = new ArrayList();
		list.add(object);
		entityManager.close();
		
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List findByMasterNameById(Integer masterId) {
		EntityManager em=getJpaTemplate().getEntityManagerFactory().createEntityManager();
		Query q=em.createQuery("Select e.name FROM MastersEntity e WHERE e.deletedFlag=0 and e.mastersId=:id");
		q.setParameter("id", masterId);
		Object object =q.getSingleResult();
		
		List list = new ArrayList();
		list.add(object);
		em.close();
		return list;
	}

	
	
	

}
