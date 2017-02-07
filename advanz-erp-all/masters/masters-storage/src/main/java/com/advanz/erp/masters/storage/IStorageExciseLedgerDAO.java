package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.ExciseLedgerEntity;

public interface IStorageExciseLedgerDAO extends IStorageDAO<ExciseLedgerEntity>
{
  public List<ExciseLedgerEntity> load();
  public List<ExciseLedgerEntity> findLastUserMasterId();
  public List<ExciseLedgerEntity> findSnoByGrnNumber(String grnNumber);
  public List<ExciseLedgerEntity> findSnoByIssueNumber(String issueNumber);
  public List<ExciseLedgerEntity> findSnoByInvoiceNumber(String invoiceNumber);
  
  public List<ExciseLedgerEntity> findExciseByItemId(Integer itemId);
}
