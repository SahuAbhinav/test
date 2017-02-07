package com.advanz.erp.masters.storage;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.AdvanceAmountEntity;
import com.advanz.erp.masters.entity.jpa.ProfessionalTaxEntity;

public interface IStorageAdvanceAmountDAO  extends IStorageDAO<AdvanceAmountEntity>{
	    public List<AdvanceAmountEntity> load();
	    public  List<AdvanceAmountEntity> findById(Integer id);
	    public  List<AdvanceAmountEntity> findByEmployeeId(Integer employee);
	    public  List<AdvanceAmountEntity> search(Date toDate,Date fromDate,String empName,String designation,String transactionType);
	    public Integer getTransactionNo();
	    public List<AdvanceAmountEntity> findLastDate(Date firstDate,Date lastDate,Integer empId);
	    public Integer findbyEmpIdAndTrnsMonth(Integer month, Integer year,Integer empId);
	}
