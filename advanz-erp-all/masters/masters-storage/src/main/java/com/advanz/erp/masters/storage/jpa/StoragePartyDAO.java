package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.BillEntity;
import com.advanz.erp.masters.entity.jpa.PartyEntity;
import com.advanz.erp.masters.storage.IStoragePartyDAO;

@Component
public class StoragePartyDAO extends JpaDaoSupport implements IStoragePartyDAO{
	@Autowired
	EntityManagerFactory entityManagerFactory;
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(PartyEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);
        
	}

	@Override
	@Transactional(readOnly = true)
	public PartyEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(PartyEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public PartyEntity update(PartyEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
        return getJpaTemplate().merge(entity);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(PartyEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
		 entity=getJpaTemplate().find(PartyEntity.class, entity.getPartyId());
		 entity.setDeletedFlag(true);
	     getJpaTemplate().merge(entity);		
	}

	@Override
	@Transactional(readOnly = true)
	public List<PartyEntity> findById(Integer uid) {	
		System.out.println("DAO uid : "+uid);
		return getJpaTemplate().find("FROM PartyEntity e WHERE e.partyId = ?1 and e.deletedFlag = ?2  ORDER BY e.partyName", uid,false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PartyEntity> findByName(String partyName) {
		if(partyName!=null)
			partyName=partyName.trim();
		return getJpaTemplate().find("FROM PartyEntity e WHERE e.partyName = ?1 and deletedFlag=?2 AND e.activeStatus=1 ORDER BY e.partyName", partyName,false);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PartyEntity> findByCode(String partyCode) {
		
		return getJpaTemplate().find("FROM PartyEntity e WHERE e.partyCode=?1 and deletedFlag=?2 AND e.activeStatus=1 ORDER BY e.partyName", partyCode,false);
		
	}
	
	@Override
    @Transactional(readOnly = true)
	public List<PartyEntity> load() {	
		//return getJpaTemplate().find("FROM PartyEntity e WHERE e.deletedFlag = ?1 ORDER BY e.partyId DESC",false);
		return getJpaTemplate().find("FROM PartyEntity e WHERE e.deletedFlag = ?1 ORDER BY e.partyName ",false);
	}

	@Override
	public List<PartyEntity> preLoad() {
		// TODO Auto-generated method stub
		return getJpaTemplate().find("FROM PartyEntity e WHERE e.deletedFlag = ?1 and e.activeStatus=1 ORDER BY e.partyName",false);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PartyEntity> search(String partyName,String partyCode,Integer partyTypeId,String balanceType) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM PartyEntity e");
		boolean first = true;
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+balanceType);
		if (StringUtils.hasText(partyName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.partyName like :partyName");
		    params.put("partyName", "%"+partyName.trim()+"%");
		    first=false;
		}
	//	logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		if (StringUtils.hasText(partyCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.partyCode like :partyCode");
		    params.put("partyCode", "%"+partyCode.trim()+"%");
		    first=false;
		}
	//	logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		if (partyTypeId!=null) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.partyTypeEntity.partyTypeId =:partyTypeId");
		    params.put("partyTypeId", partyTypeId);
		    first=false;
		}
	//	logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		if (balanceType!=null) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.partyTypeEntity.partyTypeFlag =:partyTypeFlag");
		    params.put("partyTypeFlag", balanceType);
		    first=false;
		}
	//	logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		hql.append(first ? " where " : " and ");
		hql.append("e.deletedFlag=:flag");
		params.put("flag", false);
		logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		hql.append(" order by e.partyName");
		
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	    }

	@Override
	@Transactional(readOnly = true)
	public boolean isInUsed(Integer id) {		
		List<Integer> list=getJpaTemplate().find("Select salesOrderAutoId FROM SalesOrderMasterEntity e WHERE e.deletedFlag=0 and e.partyEntity.partyId =?1",id);
		if(list!=null && list.size()>0){
			return true;
		}	
		list=getJpaTemplate().find("Select quotationAutoId FROM QuotationMasterEntity e WHERE e.deletedFlag=0 and e.partyEntity.partyId ="+id);
		if(list!=null && list.size()>0){
			return true;
		}
		list=getJpaTemplate().find("Select invoiceAutoId FROM BillEntity e WHERE e.deletedFlag=0 and e.partyEntity.partyId ="+id);
		if(list!=null && list.size()>0){
			return true;
		}
		list=getJpaTemplate().find("Select dispatchAutoId FROM DispatchMasterEntity e WHERE e.deletedFlag=0 and e.partyEntity.partyId ="+id);
		if(list!=null && list.size()>0){
			return true;
		}
		list=getJpaTemplate().find("Select poAutoId FROM PurchaseOrderMasterEntity e WHERE e.deletedFlag=0 and e.partyEntity.partyId ="+id);
		if(list!=null && list.size()>0){
			return true;
		}
		list=getJpaTemplate().find("Select grnAutoId FROM GrnMasterEntity e WHERE e.deletedFlag=0 and e.partyEntity.partyId ="+id);
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}
	
	@Override
    @Transactional(readOnly = true)
	public List<PartyEntity> findDebtorPartyList() {	
		return getJpaTemplate().find("Select e FROM PartyEntity e , PartyTypeEntity p WHERE  e.partyTypeEntity.partyTypeId=p.partyTypeId  AND e.partyTypeEntity.partyTypeFlag = 'Debtor' AND  e.deletedFlag=0 AND e.activeStatus=1 ORDER BY e.partyName");
	}
	
	@Override
    @Transactional(readOnly = true)
	public List<PartyEntity> findCreditorPartyList() {	
		return getJpaTemplate().find("Select e FROM PartyEntity e , PartyTypeEntity p WHERE  e.partyTypeEntity.partyTypeId=p.partyTypeId  AND e.partyTypeEntity.partyTypeFlag = 'Creditor' AND  e.deletedFlag=0 AND e.activeStatus=1 ORDER BY e.partyName");
	}

	@Override
	@Transactional(readOnly = true)
	public List<PartyEntity> findDebtorPartyShortInfoList(){
		return getJpaTemplate().find("Select e FROM PartyEntity e , PartyTypeEntity p WHERE  e.partyTypeEntity.partyTypeId = p.partyTypeId AND e.partyTypeEntity.partyTypeFlag = 'Debtor' AND  e.deletedFlag=0 AND e.activeStatus=1 ORDER BY e.partyName");
	}
	
	
	@Override
	public List<PartyEntity> loadPartyNameAndId() {
	
//		return getJpaTemplate().find("FROM ItemEntity e where e.deletedFlag=0 and e.batchFlag=1 and e.actioveStatus=1 ORDER BY e.itemName ");
		List<PartyEntity> il= getJpaTemplate().find("SELECT new PartyEntity(e.partyId,e.partyName) FROM PartyEntity e where e.deletedFlag=0 AND e.activeStatus=1 ORDER BY e.partyName ");
		
		return il;

		
	}

	@Override
	public String getEmailId(String billNo, String flag) {
		String emailId=null;
		Object object = null;
		
			if(flag.equalsIgnoreCase("Invoice")){
				EntityManager entityManager=entityManagerFactory.createEntityManager();
			 object = (Object)entityManager.createNativeQuery("SELECT contact_1_e_mail FROM m_party WHERE party_id= (SELECT party_id FROM t_bill_mast WHERE invoice_number='"+billNo+"') LIMIT 1").getSingleResult();
			System.out.println("Email isd in Storage ...............");
			entityManager.close();
			}if(flag.equalsIgnoreCase("Sales_Order")){
			EntityManager entityManager=entityManagerFactory.createEntityManager();
				 object = (Object)entityManager.createNativeQuery("SELECT contact_1_e_mail FROM m_party WHERE party_id= (SELECT party_id FROM t_sales_order_mast WHERE sales_order_number='"+billNo+"') LIMIT 1").getSingleResult();
			entityManager.close();
			}
		if(object!=null ){
			emailId=(String)object;
		}
		return emailId;
	}

	
}