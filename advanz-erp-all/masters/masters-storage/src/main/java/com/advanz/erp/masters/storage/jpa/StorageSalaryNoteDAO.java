package com.advanz.erp.masters.storage.jpa;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.common.DataUtility;
import com.advanz.erp.masters.entity.jpa.SalaryNoteEntity;
import com.advanz.erp.masters.storage.IStorageSalaryNoteDAO;

public class StorageSalaryNoteDAO extends JpaDaoSupport implements IStorageSalaryNoteDAO
{

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void create(SalaryNoteEntity entity) {
		if(entity==null)
		{
			throw new IllegalArgumentException("Can not create Entity");
		}
		getJpaTemplate().persist(entity);		
	}

	@Override
	public SalaryNoteEntity read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public SalaryNoteEntity update(SalaryNoteEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
			 return getJpaTemplate().merge(entity); 
		}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(SalaryNoteEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot delete a null entity");
	        }
		 List<SalaryNoteEntity> list =getJpaTemplate().find("FROM SalaryNoteEntity e where e.deletedFlag=?1 and e.noteId=?2",false,entity.getNoteId()); 
		 for(SalaryNoteEntity noteEntity:list)
		 {
			 noteEntity.setDeletedFlag(true);
			 noteEntity.setModifiedUserId(entity.getModifiedUserId());
			 getJpaTemplate().merge(noteEntity); 
		 }		  
	}

	@Override
	@Transactional(readOnly=true)
	public List<SalaryNoteEntity> loadSalaryNote() {
		List list= getJpaTemplate().find("SELECT count(*) FROM SalaryNoteEntity e where e.deletedFlag=?1 GROUP BY e.noteId",false);
		List<SalaryNoteEntity> noteList=getJpaTemplate().find("FROM SalaryNoteEntity e where e.deletedFlag=?1 GROUP BY e.noteId ORDER BY e.creationDate ",false);
		for(int i=0;i<list.size();i++)
		{
			noteList.get(i).setAssignToEmp(Integer.parseInt(list.get(i).toString()));
		}
		return noteList;
	}

	@Override
	@Transactional(readOnly=true)
	public Integer getNoteID() {
		 int id=0;
	     List list=getJpaTemplate().find("SELECT max(e.noteId) FROM SalaryNoteEntity e where e.deletedFlag=?1",false);
	     if(list!=null && list.size()>0){
	     	Number n=(Number)list.get(0);
	     	if(n!=null)
	     	id=n.intValue();
	     }
	     id++;
		return id;
	}

	@Override
	@Transactional(readOnly=true)
	public List<SalaryNoteEntity> findByNoteID(Integer noteID) {
		List<SalaryNoteEntity> list=getJpaTemplate().find("FROM SalaryNoteEntity e where e.deletedFlag=?1 and e.noteId=?2",false,noteID);
		return list;
	}
	@Override
	@Transactional(readOnly=true)
	public List<SalaryNoteEntity> search(Date noteDate,Integer status) {
		Map<String, Object> params = new HashMap<String, Object>();
	
		StringBuffer hql = new StringBuffer("FROM SalaryNoteEntity e");
		boolean first = true;
		
		if (noteDate!=null) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.noteDate = :noteDate");
		    params.put("noteDate", noteDate);
		    first=false;
		}
		if (status!=null) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.status = :status");
		    params.put("status", status);
		    first=false;
		}
			
		hql.append(first ? " where " : " and ");
		hql.append("e.deletedFlag=:flag");
		params.put("flag", false);
		hql.append(" GROUP BY e.noteId ORDER BY e.creationDate");
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public List<SalaryNoteEntity> findByNoteIDEmpID(Integer noteId,Integer empId) {
		 List<SalaryNoteEntity> list =getJpaTemplate().find("FROM SalaryNoteEntity e where e.deletedFlag=?1 and e.noteId=?2 and e.assignToEmp=?3",false,noteId,empId);
		return list;
	}
}
