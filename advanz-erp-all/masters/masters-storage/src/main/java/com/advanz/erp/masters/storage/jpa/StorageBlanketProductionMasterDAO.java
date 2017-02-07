package com.advanz.erp.masters.storage.jpa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Time;
import java.sql.Timestamp;
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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.common.DataUtility;
import com.advanz.erp.masters.entity.jpa.BlanketProductionDetailLeftEntity;
import com.advanz.erp.masters.entity.jpa.BlanketProductionDetailRightEntity;
import com.advanz.erp.masters.entity.jpa.BlanketProductionMasterEntity;
import com.advanz.erp.masters.entity.jpa.BlanketWeightRecordEntity;
import com.advanz.erp.masters.storage.IStorageBlanketProductionMasterDAO;
import com.advanz.erp.masters.storage.IStorageCompanyDAO;

@Component
public class StorageBlanketProductionMasterDAO extends JpaDaoSupport implements
		IStorageBlanketProductionMasterDAO {
	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Autowired
	IStorageCompanyDAO company;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(BlanketProductionMasterEntity entity) {

		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createLeftBlanketProduction(
			BlanketProductionDetailLeftEntity blanketProductionDetailLeftEntity) {
		if (blanketProductionDetailLeftEntity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		if (blanketProductionDetailLeftEntity.getItemId() != null
				&& blanketProductionDetailLeftEntity.getDensityLeft() != null) {
			getJpaTemplate().merge(blanketProductionDetailLeftEntity);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createRightBlanketProduction(
			BlanketProductionDetailRightEntity blanketProductionDetailRightEntity) {
		if (blanketProductionDetailRightEntity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		if (blanketProductionDetailRightEntity.getItemId() != null
				&& blanketProductionDetailRightEntity.getDensityRight() != null) {
			getJpaTemplate().merge(blanketProductionDetailRightEntity);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public BlanketProductionMasterEntity read(Integer uid) {
		if (uid == null) {
			throw new IllegalArgumentException("uid must not be null");
		}
		return getJpaTemplate().find(BlanketProductionMasterEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public BlanketProductionMasterEntity update(
			BlanketProductionMasterEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		// entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(BlanketProductionMasterEntity entity1) {
		if (entity1 == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		BlanketProductionMasterEntity entity = getJpaTemplate().find(
				BlanketProductionMasterEntity.class,
				entity1.getBlanketProductionId());
		entity.setDeletedFlag(true);
		entity.setModifiedUserId(entity1.getModifiedUserId());
		if (entity.getBlanketProductionDetailLeftEntityList() != null) {
			for (BlanketProductionDetailLeftEntity le : entity
					.getBlanketProductionDetailLeftEntityList()) {
				le.setDeletedFlag(true);
				le.setModifiedUserId(entity1.getModifiedUserId());
			}
		}

		if (entity.getBlanketProductionDetailRightEntityList() != null) {
			for (BlanketProductionDetailRightEntity re : entity
					.getBlanketProductionDetailRightEntityList()) {
				re.setDeletedFlag(true);
				re.setModifiedUserId(entity1.getModifiedUserId());
			}
		}

		getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public Boolean deleteLeftBlanketProduction(
			BlanketProductionDetailLeftEntity entity) {

		Boolean b = false;
		List list = getJpaTemplate()
				.find("SELECT e.approvedStatus FROM BlanketProductionDetailLeftEntity e WHERE e.deletedFlag=0 and e.sno = '"
						+ entity.getSno() + "'");
		if (list != null && list.size() > 0) {
			Number n = (Number) list.get(0);
			int i = n.intValue();
			if (n != null && i == 0) {
				entity.setDeletedFlag(true);
				EntityManager entityManager = entityManagerFactory
						.createEntityManager();
				entityManager.getTransaction().begin();
				entityManager.createQuery(
						"DELETE FROM BlanketProductionDetailLeftEntity e WHERE  e.sno = '"
								+ entity.getSno() + "'").executeUpdate();
				entityManager.getTransaction().commit();
				entityManager.close();
				b = true;
			}
		}
		return b;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public Boolean deleteRightBlanketProduction(
			BlanketProductionDetailRightEntity entity) {

		Boolean b = false;
		List list = getJpaTemplate()
				.find("SELECT e.approvedStatus FROM BlanketProductionDetailRightEntity e WHERE e.deletedFlag=0 and e.sno = '"
						+ entity.getSno() + "'");
		if (list != null && list.size() > 0) {
			Number n = (Number) list.get(0);
			int i = n.intValue();
			if (n != null && i == 0) {
				entity.setDeletedFlag(true);
				EntityManager entityManager = entityManagerFactory
						.createEntityManager();
				entityManager.getTransaction().begin();
				entityManager.createQuery(
						"DELETE FROM BlanketProductionDetailRightEntity e WHERE  e.sno = '"
								+ entity.getSno() + "'").executeUpdate();
				entityManager.getTransaction().commit();
				entityManager.close();
				b = true;
			}
		}
		return b;
	}

	@Override
	@Transactional(readOnly = true)
	public List<BlanketProductionMasterEntity> load() {

		// return
		// getJpaTemplate().find("FROM BlanketProductionMasterEntity e where e.deletedFlag=?1  ORDER BY e.modifiedDate DESC",false);
		return getJpaTemplate()
				.find("FROM BlanketProductionMasterEntity e where e.deletedFlag=?1  ORDER BY e.blanketProductionId DESC",
						false);
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<BlanketProductionMasterEntity> findById(E uid) {
		return getJpaTemplate()
				.find("FROM BlanketProductionMasterEntity e WHERE e.blanketProductionId =?1 and e.deletedFlag=?2",
						uid, false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BlanketProductionMasterEntity> search(Date fromDate,
			Date toDate, String runNo, Integer gradeId) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer(
				"FROM BlanketProductionMasterEntity e");
		boolean first = true;

		if (StringUtils.hasText(runNo)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.runNo like :runNo");
			params.put("runNo", "%" + runNo.trim() + "%");
			first = false;
		}
		if (gradeId != null) {
			hql.append(first ? " where " : " and ");
			hql.append("e.gradeMasterEntity.mastersId=:gradeId");
			params.put("gradeId", gradeId);
			first = false;
		}
		if (fromDate != null || toDate != null) {
			hql.append(first ? " where " : " and ");
			String fromDate1 = "";
			if (fromDate != null) {
				fromDate1 = DataUtility.getDate(fromDate);
			}
			String toDate1 = null;
			if (toDate != null) {
				toDate1 = DataUtility.getDate(toDate);
			}

			hql.append("e.blanketProductionDate >= '" + fromDate1
					+ "' AND e.blanketProductionDate <= '" + toDate1 + "'");
			first = false;
		}
		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0 ORDER BY e.blanketProductionId DESC");
		first = false;

		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BlanketProductionMasterEntity> findByDateAndRunNo(Date date,
			String runNo, Integer shiftId, String batchNo, Integer status) {

		return getJpaTemplate()
				.find("FROM BlanketProductionMasterEntity e where e.runNo=?1 and e.blanketProductionDate=?2 and e.shiftMasterEntity.mastersId=?3 and e.batchNumber=?4 and e.deletedFlag=0",
						runNo, date, shiftId, batchNo);
	}

	@Override
	public Integer getMaxBlanketProdId() {
		int id = 0;
		List list = getJpaTemplate()
				.find("SELECT max(e.blanketProductionId) FROM BlanketProductionMasterEntity e WHERE e.deletedFlag=0 ");
		if (list != null && list.size() > 0) {
			Number n = (Number) list.get(0);
			if (n != null)
				id = n.intValue();
		}

		return id;
	}

	@Override
	public ArrayList<Integer> getMaxRollNoL(String batchNo) {
		int id = 0;
		ArrayList<Integer> as = new ArrayList<Integer>();

		if (batchNo == null) {
			batchNo = "00011001100";
		}
		int id1 = 0;
		List list1 = getJpaTemplate()
				.find("SELECT max(e.rollNoLeft) FROM BlanketProductionDetailLeftEntity e,BlanketProductionMasterEntity me WHERE me.batchNumber='"
						+ batchNo
						+ "' and e.blanketProductionId=me.blanketProductionId and e.deletedFlag=0 and me.deletedFlag=0");
		if (list1 != null && list1.size() > 0) {
			Number n = (Number) list1.get(0);
			if (n != null)
				id1 = n.intValue();
		}
		id1++;
		as.add(id1);

		List list = getJpaTemplate()
				.find("SELECT max(e.rollNoLeft),max(e.sno) FROM BlanketProductionDetailLeftEntity e WHERE e.deletedFlag=0 ");
		if (list != null && list.size() > 0) {
			Object[] obj = (Object[]) list.get(0);
			int rollNoLeft = Integer.parseInt(obj[0].toString());
			int sno = Integer.parseInt(obj[1].toString());
			// as.add(rollNoLeft+1);
			as.add(sno);
			/*
			 * if(n!=null) id=n.intValue();
			 */
		}

		return as;
	}

	@Override
	public ArrayList<Integer> getMaxRollNoR(String batchNo) {
		int id = 0;

		ArrayList<Integer> as = new ArrayList<Integer>();

		if (batchNo == null) {
			batchNo = "00011001100";
		}
		int id1 = 0;
		List list1 = getJpaTemplate()
				.find("SELECT max(e.rollNoRight) FROM BlanketProductionDetailRightEntity e,BlanketProductionMasterEntity me WHERE me.batchNumber='"
						+ batchNo
						+ "' and e.blanketProductionId=me.blanketProductionId and e.deletedFlag=0 and me.deletedFlag=0");
		if (list1 != null && list1.size() > 0) {
			Number n = (Number) list1.get(0);
			if (n != null)
				id1 = n.intValue();
		}
		id1++;
		as.add(id1);

		List list = getJpaTemplate()
				.find("SELECT max(e.rollNoRight),max(e.sno) FROM BlanketProductionDetailRightEntity e WHERE e.deletedFlag=0 ");

		if (list != null && list.size() > 0) {
			Object[] obj = (Object[]) list.get(0);
			int rollNoLeft = Integer.parseInt(obj[0].toString());
			int sno = Integer.parseInt(obj[1].toString());
			as.add(rollNoLeft + 1);
			as.add(sno);
			/*
			 * if(n!=null) id=n.intValue();
			 */
		}

		return as;
	}

	@Override
	public List getBlanketProductionLeft(Date date, Integer shiftId) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		String sdfate = null;
		if (date != null) {
			sdfate = DataUtility.getDate(date);
		}
		// Query
		// query=entityManager.createNativeQuery("SELECT bpl.sno, bpm.run_no,bpm.blanket_prod_date,(SELECT NAME FROM m_masters WHERE masters_id=bpm.shift_id) AS shift,bpm.batch_number,bpl.roll_no_l,bpl.blanket_type,bpl.status_left FROM t_blanket_production_detail_left bpl,t_blanket_production_mast bpm ,m_masters mm WHERE bpl.status_left='R.A.' AND bpm.blanket_prod_id= bpl.blanket_prod_id AND bpm.deleted_flag=0 AND bpl.deleted_flag=0 ORDER BY  bpl.blanket_prod_id DESC");
		Query query = entityManager
				.createNativeQuery("SELECT bpl.sno, bpm.run_no,bpm.blanket_prod_date,(SELECT NAME FROM m_masters WHERE masters_id=bpm.shift_id) AS shift,bpm.batch_number,bpl.roll_no_l,bpl.blanket_type,bpl.status_left FROM t_blanket_production_detail_left bpl,t_blanket_production_mast bpm ,m_masters mm WHERE bpl.status_left='R.A.' AND CASE WHEN '"
						+ shiftId
						+ "' ='0' THEN 1=1  ELSE mm.masters_id='"
						+ shiftId
						+ "' END AND mm.masters_id=bpm.shift_id  AND CASE WHEN "
						+ sdfate
						+ " IS NULL THEN 1=1 ELSE cast(bpm.blanket_prod_date as date)='"
						+ sdfate
						+ "' END AND bpm.blanket_prod_id= bpl.blanket_prod_id AND bpm.deleted_flag=0 AND bpl.deleted_flag=0 ORDER BY  bpl.blanket_prod_id DESC");
		List list = query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	public List getBlanketProductionRight(Date date, Integer shiftId) {
		List list = null;

		String sdfate = null;
		if (date != null) {
			sdfate = DataUtility.getDate(date);
		}
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Query query = entityManager
				.createNativeQuery("SELECT bpl.sno, bpm.run_no,bpm.blanket_prod_date,(SELECT NAME FROM m_masters WHERE masters_id=bpm.shift_id) AS shift,bpm.batch_number,bpl.roll_no_r,bpl.blanket_type,bpl.status_right FROM t_blanket_production_detail_right bpl,t_blanket_production_mast bpm ,m_masters mm WHERE bpl.status_right='R.A.' AND CASE WHEN '"
						+ shiftId
						+ "'='0' THEN  1=1 ELSE mm.masters_id='"
						+ shiftId
						+ "' END AND mm.masters_id=bpm.shift_id AND CASE WHEN "
						+ sdfate
						+ " IS NULL THEN 1=1 ELSE CAST(bpm.blanket_prod_date AS DATE)='"
						+ sdfate
						+ "' END AND bpm.blanket_prod_id= bpl.blanket_prod_id AND bpm.deleted_flag=0 AND bpl.deleted_flag=0 ORDER BY  bpl.blanket_prod_id DESC");
		list = query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateBlanketProductionLeft(
			BlanketProductionDetailLeftEntity leftEntity, String flag) {
		if ("updateRejection".equalsIgnoreCase(flag)) {
			leftEntity.setRejUpdateDateLeft(new Date());
			getJpaTemplate().merge(leftEntity);
		}
		if ("R.A.".equalsIgnoreCase(flag)) {
			if (leftEntity.getSno() != null) {
				BlanketProductionDetailLeftEntity entity = getJpaTemplate()
						.find(BlanketProductionDetailLeftEntity.class,
								leftEntity.getSno());
				entity.setUpdateDateLeft(new Date());
				entity.setRaUserId(leftEntity.getRaUserId());
				entity.setStatusLeft(leftEntity.getStatusLeft());
				getJpaTemplate().merge(entity);
			}
		}
		if ("Approved".equalsIgnoreCase(flag)) {
			if (leftEntity.getSno() != null) {
				BlanketProductionDetailLeftEntity entity = getJpaTemplate()
						.find(BlanketProductionDetailLeftEntity.class,
								leftEntity.getSno());
				entity.setApprovedDate(new Date());
				entity.setApprovedStatus(leftEntity.getApprovedStatus());
				entity.setApprovedUserId(leftEntity.getApprovedUserId());
				entity.setStatusLeft(leftEntity.getStatusLeft());
				entity.setFinishedGood(leftEntity.getFinishedGood());
				getJpaTemplate().merge(entity);
			}
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateBlanketProductionRight(
			BlanketProductionDetailRightEntity rightEntity, String flag) {
		if ("updateRejection".equalsIgnoreCase(flag)) {
			rightEntity.setRejUpdateDateRight(new Date());
			getJpaTemplate().merge(rightEntity);
		}
		if ("R.A.".equalsIgnoreCase(flag)) {
			if (rightEntity.getSno() != null) {
				BlanketProductionDetailRightEntity entity = getJpaTemplate()
						.find(BlanketProductionDetailRightEntity.class,
								rightEntity.getSno());
				entity.setUpdateDateRight(new Date());
				entity.setStatusRight(rightEntity.getStatusRight());
				entity.setRaUserId(rightEntity.getRaUserId());
				getJpaTemplate().merge(entity);
			}
		}
		if ("Approved".equalsIgnoreCase(flag)) {
			if (rightEntity.getSno() != null) {
				BlanketProductionDetailRightEntity entity = getJpaTemplate()
						.find(BlanketProductionDetailRightEntity.class,
								rightEntity.getSno());
				entity.setApprovedDate(new Date());
				entity.setApprovedStatus(rightEntity.getApprovedStatus());
				entity.setApprovedUserId(rightEntity.getApprovedUserId());
				entity.setStatusRight(rightEntity.getStatusRight());
				entity.setFinishedGood(rightEntity.getFinishedGood());
				getJpaTemplate().merge(entity);
			}
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<BlanketProductionDetailLeftEntity> getRejBlanketProductionLeft(
			String status, Date date, Integer shiftId) {
		List<BlanketProductionDetailLeftEntity> leftDeatlList = new ArrayList<BlanketProductionDetailLeftEntity>();
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		/* if(date!=null){ */
		String sdfate = null;
		if (date != null) {
			sdfate = DataUtility.getDate(date);
		}
		// leftDeatlList=getJpaTemplate().find("FROM BlanketProductionDetailLeftEntity e WHERE e.statusLeft IN('"+status+"') and cast(e.creationDate as date)='"+sdfate+"' and e.approvedStatus=0 and e.deletedFlag=?1",false);
		List list = null;
		/* if(shiftId!=0){ */
		list = entityManager
				.createNativeQuery(
						"SELECT de.roll_no_l,de.length_l,de.width_l,de.thick_l,de.weight_l,de.density_l,de.blanket_type,me.blanket_prod_date,de.status_left,de.sno,de.item_id,de.update_date_left,de.blanket_prod_id FROM t_blanket_production_detail_left de,t_blanket_production_mast me,m_masters mm where de.status_left IN('"
								+ status
								+ "') and CASE WHEN '"
								+ shiftId
								+ "'='0' THEN  1=1 ELSE mm.masters_id='"
								+ shiftId
								+ "' END and  mm.masters_id=me.shift_id and  CASE WHEN "
								+ sdfate
								+ " IS NULL THEN 1=1 ELSE cast(me.blanket_prod_date as date)='"
								+ sdfate
								+ "' END and me.blanket_prod_id=de.blanket_prod_id and de.approved_status=0 and de.deleted_flag=0 and me.deleted_flag=0")
				.getResultList();
		/*
		 * }else{ list = entityManager.createNativeQuery(
		 * "SELECT de.roll_no_l,de.length_l,de.width_l,de.thick_l,de.weight_l,de.density_l,de.blanket_type,me.blanket_prod_date,de.status_left,de.sno,de.item_id,de.update_date_left,de.blanket_prod_id FROM t_blanket_production_detail_left de,t_blanket_production_mast me where de.status_left IN('"
		 * +status+"') and cast(me.blanket_prod_date as date)='"+sdfate+
		 * "' and me.blanket_prod_id=de.blanket_prod_id and de.approved_status=0 and de.deleted_flag=0 and me.deleted_flag=0"
		 * ).getResultList(); }
		 */
		entityManager.close();
		for (int i = 0; i < list.size(); i++) {
			BlanketProductionDetailLeftEntity dle = new BlanketProductionDetailLeftEntity();
			Object[] obj = (Object[]) list.get(i);
			int rollNor = Integer.parseInt(obj[0].toString());
			Double lenghtr = Double.parseDouble(obj[1].toString());
			Double widhtr = Double.parseDouble(obj[2].toString());
			Double thikr = Double.parseDouble(obj[3].toString());
			Double weightr = Double.parseDouble(obj[4].toString());
			// Double dencityr =0.0;
			// try{
			Double dencityr = Double.parseDouble(obj[5].toString());
			// }catch (Exception e) {

			// }
			String blankettypr = (String) obj[6];

			Date createDate = (Date) obj[7];
			String statusr = (String) obj[8];

			int sno = Integer.parseInt(obj[9].toString());
			int itemId = Integer.parseInt(obj[10].toString());
			Date updatetDate = (Date) obj[11];
			int blanketProductionId = Integer.parseInt(obj[12].toString());

			dle.setRollNoLeft(rollNor);
			dle.setLengthLeft(lenghtr);
			dle.setWidthLeft(widhtr);
			dle.setThickLeft(thikr);
			dle.setWeightLeft(weightr);
			dle.setDensityLeft(dencityr);
			dle.setBlanketType(blankettypr);
			dle.setCreationDate(createDate);
			dle.setStatusLeft(statusr);

			dle.setSno(sno);
			dle.setItemId(itemId);
			dle.setUpdateDateLeft(updatetDate);
			dle.setBlanketProductionId(blanketProductionId);

			leftDeatlList.add(dle);
		}

		/*
		 * }else{ leftDeatlList=getJpaTemplate().find(
		 * "FROM BlanketProductionDetailLeftEntity e WHERE e.statusLeft IN('"
		 * +status+"') and e.approvedStatus=0 and e.deletedFlag=?1",false); }
		 */
		return leftDeatlList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<BlanketProductionDetailRightEntity> getRejBlanketProductionRight(
			String status, Date date, Integer shiftId) {
		List<BlanketProductionDetailRightEntity> rightDetailList = new ArrayList<BlanketProductionDetailRightEntity>();

		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		/* if(date!=null){ */
		String sdfate = null;
		if (date != null) {
			sdfate = DataUtility.getDate(date);
		}
		// rightDetailList=getJpaTemplate().find("FROM BlanketProductionDetailRightEntity e WHERE e.statusRight IN('"+status+"') and cast(e.creationDate as date)='"+sdfate+"' and e.approvedStatus=0 and e.deletedFlag=?1",false);
		List list = null;
		/* if(shiftId!=0){ */

		list = entityManager
				.createNativeQuery(
						"SELECT de.roll_no_r,de.length_r,de.width_r,de.thick_r,de.weight_r,de.density_r,de.blanket_type,me.blanket_prod_date,de.status_right,de.sno,de.item_id,de.update_date_right,de.blanket_prod_id FROM t_blanket_production_detail_right de,t_blanket_production_mast me ,m_masters mm where de.status_right IN('"
								+ status
								+ "') and CASE WHEN "
								+ sdfate
								+ " IS NULL THEN 1=1 ELSE  cast(me.blanket_prod_date as date)='"
								+ sdfate
								+ "' END and CASE WHEN '"
								+ shiftId
								+ "'='0' THEN  1=1 ELSE mm.masters_id='"
								+ shiftId
								+ "' END and mm.masters_id=me.shift_id and me.blanket_prod_id=de.blanket_prod_id and de.approved_status=0 and de.deleted_flag=0 and me.deleted_flag=0")
				.getResultList();

		/*
		 * }else{ list = entityManager.createNativeQuery(
		 * "SELECT de.roll_no_r,de.length_r,de.width_r,de.thick_r,de.weight_r,de.density_r,de.blanket_type,me.blanket_prod_date,de.status_right,de.sno,de.item_id,de.update_date_right,de.blanket_prod_id FROM t_blanket_production_detail_right de,t_blanket_production_mast me where de.status_right IN('"
		 * +status+"') and cast(me.blanket_prod_date as date)='"+sdfate+
		 * "' and me.blanket_prod_id=de.blanket_prod_id and de.approved_status=0 and de.deleted_flag=0 and me.deleted_flag=0"
		 * ).getResultList(); }
		 */
		entityManager.close();

		for (int i = 0; i < list.size(); i++) {
			BlanketProductionDetailRightEntity dre = new BlanketProductionDetailRightEntity();
			Object[] obj = (Object[]) list.get(i);
			int rollNor = Integer.parseInt(obj[0].toString());
			Double lenghtr = Double.parseDouble(obj[1].toString());
			Double widhtr = Double.parseDouble(obj[2].toString());
			Double thikr = Double.parseDouble(obj[3].toString());
			Double weightr = Double.parseDouble(obj[4].toString());
			Double dencityr = Double.parseDouble(obj[5].toString());
			String blankettypr = (String) obj[6];

			Date createDate = (Date) obj[7];
			String statusr = (String) obj[8];

			int sno = Integer.parseInt(obj[9].toString());
			int itemId = Integer.parseInt(obj[10].toString());
			Date updatetDate = (Date) obj[11];
			int blanketProductionId = Integer.parseInt(obj[12].toString());

			dre.setRollNoRight(rollNor);
			dre.setLengthRight(lenghtr);
			dre.setWidthRight(widhtr);
			dre.setThickRight(thikr);
			dre.setWeightRight(weightr);
			dre.setDensityRight(dencityr);
			dre.setBlanketType(blankettypr);
			dre.setCreationDate(createDate);
			dre.setStatusRight(statusr);

			dre.setSno(sno);
			dre.setItemId(itemId);
			dre.setUpdateDateRight(updatetDate);
			dre.setBlanketProductionId(blanketProductionId);

			rightDetailList.add(dre);
		}
		//

		/*
		 * }else{ rightDetailList=getJpaTemplate().find(
		 * "FROM BlanketProductionDetailRightEntity e WHERE e.statusRight IN('"
		 * +status+"') and e.approvedStatus=0 and e.deletedFlag=?1",false); }
		 */
		return rightDetailList;
	}

	@Override
	public List<BlanketProductionMasterEntity> findBlanketProductionPagination(
			Integer next) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Query query = entityManager
				.createQuery("FROM BlanketProductionMasterEntity e WHERE e.deletedFlag=0 ORDER BY e.blanketProductionId DESC");
		query.setFirstResult(next);
		query.setMaxResults(15);
		List<BlanketProductionMasterEntity> list = query.getResultList();
		entityManager.close();

		return list;
	}

	@Override
	public List<BlanketProductionMasterEntity> getDataForShiftReport(Date date,
			String runNo, Integer shiftId) {
		return getJpaTemplate()
				.find("FROM BlanketProductionMasterEntity e where e.runNo=?1 and e.blanketProductionDate=?2 and e.shiftMasterEntity.mastersId=?3 and e.deletedFlag=0",
						runNo, date, shiftId);
	}

	@Override
	public List getCheckDuplicatRollNoL(String batchNo, Integer rollNoL) {
		List list1 = getJpaTemplate()
				.find("SELECT e.rollNoLeft FROM BlanketProductionDetailLeftEntity e,BlanketProductionMasterEntity me WHERE me.batchNumber='"
						+ batchNo
						+ "' and e.rollNoLeft="
						+ rollNoL
						+ " and e.blanketProductionId=me.blanketProductionId and e.deletedFlag=0 and me.deletedFlag=0");
		return list1;
	}

	@Override
	public List getCheckDuplicatRollNoR(String batchNo, Integer rollNoR) {
		List list1 = getJpaTemplate()
				.find("SELECT e.rollNoRight FROM BlanketProductionDetailRightEntity e,BlanketProductionMasterEntity me WHERE me.batchNumber='"
						+ batchNo
						+ "' and e.rollNoRight="
						+ rollNoR
						+ " and e.blanketProductionId=me.blanketProductionId and e.deletedFlag=0 and me.deletedFlag=0");
		return list1;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void updateBlanket(Date date, String runNo, Integer shiftName,
			Integer status) {
		System.out.println("STATUS IN BLANKE STORAGE...................");
		List<BlanketProductionMasterEntity> list = getJpaTemplate()
				.find("FROM BlanketProductionMasterEntity e where e.runNo=?1 and e.blanketProductionDate=?2 and e.shiftMasterEntity.mastersId=?3 and e.deletedFlag=0",
						runNo, date, shiftName);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				BlanketProductionMasterEntity entity = list.get(i);
				entity.setApproveDate(new Date());
				entity.setApproveStatus(status);
				getJpaTemplate().merge(entity);
			}
		}
	}

	@Override
	public Boolean checkFinishedGood(String finishedGoodNumber) {
		Boolean flag = false;
		List listL = getJpaTemplate()
				.find("SELECT e.finishedGood FROM BlanketProductionDetailLeftEntity e WHERE e.finishedGood='"
						+ finishedGoodNumber + "' and e.deletedFlag=0 ");
		if (listL != null && listL.size() > 0) {
			flag = true;
		}
		List listR = getJpaTemplate()
				.find("SELECT e.finishedGood FROM BlanketProductionDetailRightEntity e WHERE e.finishedGood='"
						+ finishedGoodNumber + "' and e.deletedFlag=0 ");
		if (listR != null && listR.size() > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public void cleanDuplicateEntry() {

		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager
				.createNativeQuery(
						"UPDATE t_blanket_production_detail_right AS bb,(SELECT COUNT(b.roll_no_r) AS cnt, MAX(b.sno) AS sno, b.roll_no_r, b.blanket_prod_id FROM t_blanket_production_detail_right AS b WHERE b.deleted_flag=0 GROUP BY b.roll_no_r, b.blanket_prod_id HAVING COUNT(b.roll_no_r) >1) AS z SET bb.deleted_flag=1 WHERE bb.blanket_prod_id=z.blanket_prod_id AND bb.roll_no_r=z.roll_no_r AND bb.sno<>z.sno")
				.executeUpdate();
		entityManager.getTransaction().commit();

		entityManager.getTransaction().begin();
		entityManager
				.createNativeQuery(
						"UPDATE t_blanket_production_detail_left AS bb,(SELECT COUNT(b.roll_no_l) AS cnt, MAX(b.sno) AS sno, b.roll_no_l, b.blanket_prod_id FROM t_blanket_production_detail_left AS b WHERE b.deleted_flag=0 GROUP BY b.roll_no_l, b.blanket_prod_id HAVING COUNT(b.roll_no_l) >1) AS z SET bb.deleted_flag=1 WHERE bb.blanket_prod_id=z.blanket_prod_id AND bb.roll_no_l=z.roll_no_l")
				.executeUpdate();
		entityManager.getTransaction().commit();

		entityManager.close();

	}

	@Override
	public Double getBlanketWeightRecord(Character position,
			Integer createdUserId) throws Exception {

		/*
		 * FileInputStream fis = new
		 * FileInputStream("/media/CHITRANSH/AVERYWEIGHT"); BufferedReader br =
		 * new BufferedReader(new InputStreamReader(fis));
		 */

		String filepath = null;
		try {
			filepath = company.getWeightingFilePath();
			System.out.println("filepath" + filepath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file = new File(filepath);
		System.out.println("file.getParentFile()" + file.getParentFile());
		File file1 = new File(filepath.replaceFirst("[.][^.]+$", "")
				+ "_backup");
		File temp = File.createTempFile("file", ".txt", file.getParentFile());
		String charset = "UTF-8";
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), charset));
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(
				new FileOutputStream(temp), charset));

		if (!file1.exists()) {
			file1.createNewFile();
		}

		PrintWriter file1Writer = new PrintWriter(new OutputStreamWriter(
				new FileOutputStream(file1, true), charset));

		String sCurrentLine = "";
		String lastLine = "";
		Double weight = null;
		int i = 0;
		while ((sCurrentLine = reader.readLine()) != null) {
			System.out.println("i" + sCurrentLine);
			if (i == 1) {
				lastLine = sCurrentLine;
				file1Writer.append(sCurrentLine + "-" + position + "\n");

				sCurrentLine = sCurrentLine.replace(sCurrentLine, "");
				// break;
			}
			i++;
			if (sCurrentLine.length() != 0) {
				writer.println(sCurrentLine.trim());
			}
		}
		String s[] = lastLine.split("\\s");
		// System.out.println(s[2]);
		if (s[2] != null) {
			weight = Double.parseDouble(s[2].toString());
		}
		// System.out.println(s[0]+""+s[1]+""+s[2]);

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

		BlanketWeightRecordEntity entity = new BlanketWeightRecordEntity();
		entity.setBlanketType(position);
		entity.setWeight(weight);
		entity.setWeightDate(formatter.parse(s[0]));
		entity.setWeightTime(new Time(sdf.parse(s[1]).getTime()));
		entity.setCreatedUserId(createdUserId);
		entity.setModifiedUserId(createdUserId);
		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		company.createBlanketWeightRecord(entity);

		reader.close();
		writer.close();
		file1Writer.close();
		file.delete();
		temp.renameTo(file);
		return weight;

	}

	public static void main(String[] args) throws Exception {

		/*
		 * FileInputStream fis = new
		 * FileInputStream("/media/CHITRANSH/AVERYWEIGHT"); BufferedReader br =
		 * new BufferedReader(new InputStreamReader(fis));
		 */
		String filePath = "/media/CHITRANSH/AVERYWEIGHT";
		File file = new File(filePath);
		System.out.println(filePath.replaceFirst("[.][^.]+$", ""));
		/*
		 * File temp = File.createTempFile("file", ".txt",
		 * file.getParentFile()); String charset = "UTF-8";
		 * 
		 * BufferedReader reader = new BufferedReader(new InputStreamReader(new
		 * FileInputStream(file), charset)); PrintWriter writer = new
		 * PrintWriter(new OutputStreamWriter(new FileOutputStream(temp),
		 * charset)); String sCurrentLine=""; String lastLine = ""; int i=0;
		 * while ((sCurrentLine = reader.readLine()) != null) { if(i==1){
		 * lastLine = sCurrentLine; sCurrentLine =
		 * sCurrentLine.replace(sCurrentLine, "");
		 * 
		 * 
		 * //break; }
		 * 
		 * i++; if(sCurrentLine.length()!=0){
		 * writer.println(sCurrentLine.trim()); } }
		 * 
		 * 
		 * 
		 * String s[]= lastLine.split("\\s");
		 * 
		 * System.out.println(s[2]);
		 * 
		 * System.out.println(s[0]+""+s[1]+""+s[2]); //lastLine =
		 * lastLine.replace(sCurrentLine, ""); reader.close(); writer.close();
		 * file.delete(); temp.renameTo(file);
		 */
		/*
		 * URL url = new URL("http://10.128.0.1/d:/kiranshare/testout.txt");
		 * 
		 * 
		 * br = new BufferedReader(new InputStreamReader(is)); File file=new
		 * File(url.getFile());
		 */
		// java.io.File myFile = new
		// java.io.File("\\\\10.128.0.1\\kiranshare\\testout.txt");
	}

	@Override
	public List getLastBlanketEntryDate() {
		return getJpaTemplate()
				.find("SELECT max(blanketProductionDate) FROM BlanketProductionMasterEntity e WHERE e.deletedFlag=0 ");
	}

}
