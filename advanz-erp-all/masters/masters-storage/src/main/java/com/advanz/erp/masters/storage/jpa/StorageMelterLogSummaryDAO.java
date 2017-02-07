package com.advanz.erp.masters.storage.jpa;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.common.DataUtility;
import com.advanz.erp.masters.entity.jpa.MelterLogSummaryEntity;
import com.advanz.erp.masters.storage.IStorageMelterLogSummaryDAO;

public class StorageMelterLogSummaryDAO extends JpaDaoSupport implements
		IStorageMelterLogSummaryDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(MelterLogSummaryEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("can't create null entity");
		}
		getJpaTemplate().persist(entity);
	}

	@Override
	public MelterLogSummaryEntity read(Integer id) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MelterLogSummaryEntity update(MelterLogSummaryEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("can't update null entity");
		}
		return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(MelterLogSummaryEntity entity1) {
		if (entity1 == null) {
			throw new IllegalArgumentException("can't delete null entity");
		}
		MelterLogSummaryEntity entity = getJpaTemplate().find(
				MelterLogSummaryEntity.class, entity1.getSno());
		entity.setDeletedFlag(true);
		entity.setModifiedUserId(entity1.getModifiedUserId());
		getJpaTemplate().merge(entity);
	}

	@Override
	public List<MelterLogSummaryEntity> load() {
		logger.info("loding MelterLogSummary Data");
		return getJpaTemplate()
				.find("FROM MelterLogSummaryEntity e where e.deletedFlag=?1 ORDER BY e.modifiedDate DESC",
						false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MelterLogSummaryEntity> findBySno(Integer sno) {
		if (sno == null) {
			throw new IllegalArgumentException(
					"can't find record if sno is null");
		}

		return getJpaTemplate()
				.find("FROM MelterLogSummaryEntity e where e.sno=?1  ORDER BY e.modifiedDate",
						sno);

	}

	@Override
	public List<MelterLogSummaryEntity> searchMelterSummaryRecordByDate(
			Date fromDate, Date toDate) {
		String fromDate1 = "";
		String toDate1 = null;
		if (fromDate != null || toDate != null) {

			if (fromDate != null) {
				fromDate1 = DataUtility.getDate(fromDate);
			}

			if (toDate != null) {
				toDate1 = DataUtility.getDate(toDate);
			}

			// hql.append("e.logDate >= '"+ fromDate1 +"' AND e.logDate <= '"+
			// toDate1 +"'");

			return getJpaTemplate().find(
					"FROM MelterLogSummaryEntity e where e.logDate >= '"
							+ fromDate1 + "' AND e.logDate <= '" + toDate1
							+ "' AND e.deletedFlag=?1 ORDER BY e.logDate",
					false);
			// return
			// getJpaTemplate().find("FROM MelterLogSummaryEntity e where e.logDate=?1 ORDER BY e.logDate",date);
		}

		return getJpaTemplate()
				.find("FROM MelterLogSummaryEntity e where e.deletedFlag=?1 ORDER BY e.modifiedDate DESC",
						false);
	}

	@Override
	public List<MelterLogSummaryEntity> checkDuplicateSummary(Date logDate,
			Time logTime) {

		return getJpaTemplate()
				.find("FROM MelterLogSummaryEntity e where e.logDate=?1 and e.logTime=?2 and e.deletedFlag=?3 ORDER BY e.modifiedDate",
						logDate, logTime, false);
	}

	@Override
	public Timestamp getLastMelterLogSummaryDate() {
		Timestamp date = null;
		List list = getJpaTemplate()
				.find("select max(e.logDate) FROM MelterLogSummaryEntity e where  e.deletedFlag=0");
		if (list != null && list.size() > 0) {
			date = (Timestamp) list.get(0);
		}
		return date;
	}

}
