package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.SalaryNoteEntity;

public interface IStorageSalaryNoteDAO extends IStorageDAO<SalaryNoteEntity>
{
  public List<SalaryNoteEntity> loadSalaryNote();
  public Integer getNoteID();
  public List<SalaryNoteEntity> findByNoteID(Integer noteID);
  public List<SalaryNoteEntity> search(Date fromDate,Integer status);
  public List<SalaryNoteEntity> findByNoteIDEmpID(Integer noteId,Integer empId);

}
