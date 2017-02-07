package com.advanz.erp.masters.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.MelterLogSummaryEntity;
import com.advanz.erp.masters.model.MelterLogSummaryDTO;
import com.advanz.erp.masters.model.msg.MelterLogSummaryInputMessage;
import com.advanz.erp.masters.model.msg.MelterLogSummaryOutputMessage;
import com.advanz.erp.masters.service.business.IMelterLogSummaryService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageMelterLogSummaryDAO;

public class MelterLogSummaryServiceImpl implements IMelterLogSummaryService {
	public static String ADD_MELTER_LOG_SUMMARY = "createNewMelterSummary";
	public static String FIND_ALL_MELTER_LOG_SUMMARY = "findAllMelterLogSummary";
	public static String FIND_FOR_SEARCH_RECORD = "findForSearchRecord";
	public static String FIND_BY_ID = "findById";
	public static String DELETE_MELTER_RECORD = "deleteMelterRecord";
	public static String UPDATE_MELTER_RECORD = "updateMelterTable";
	public static String SEARCH_MELTER_SUMMARY_BY_DATE = "searchMelterSummaryByDate";
	public static String GET_LAST_MELTER_SUMMARY_DATE = "getLastMelterSummaryDate";

	public String flowId = "";
	String ignoreProperties[] = { "servTaxDate,vatDate,cstDate" };

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO

	private static final Logger logger = LoggerFactory
			.getLogger(MelterLogSummaryServiceImpl.class); // do

	@Autowired
	public IStorageMelterLogSummaryDAO iMelterLogSummaryDAO = null;
	public MelterLogSummaryOutputMessage melterLogSummaryOutputMessage = null;
	public MelterLogSummaryInputMessage melterLogSummaryInputMessage = null;

	@Autowired
	public IZoneService zoneService;

	@Override
	public MelterLogSummaryOutputMessage findAllMelterSummary() {
		flowId = FIND_ALL_MELTER_LOG_SUMMARY;
		advanzErpServiceTemplate.execute(this);
		return melterLogSummaryOutputMessage;
	}

	@Override
	public MelterLogSummaryOutputMessage addNewMelterLogSummary(
			MelterLogSummaryInputMessage melterLogSummaryInputMessage) {

		flowId = ADD_MELTER_LOG_SUMMARY;

		this.melterLogSummaryInputMessage = melterLogSummaryInputMessage;

		advanzErpServiceTemplate.execute(this);
		return melterLogSummaryOutputMessage;
	}

	@Override
	public MelterLogSummaryOutputMessage deleteMelterLogSummary(
			MelterLogSummaryInputMessage melterLogSummaryInputMessage) {
		flowId = DELETE_MELTER_RECORD;
		this.melterLogSummaryInputMessage = melterLogSummaryInputMessage;
		advanzErpServiceTemplate.execute(this);
		return melterLogSummaryOutputMessage;
	}

	@Override
	public MelterLogSummaryOutputMessage updateMelterLogSummary(
			MelterLogSummaryInputMessage melterLogSummaryInputMessage) {
		flowId = UPDATE_MELTER_RECORD;
		this.melterLogSummaryInputMessage = melterLogSummaryInputMessage;
		advanzErpServiceTemplate.execute(this);
		return melterLogSummaryOutputMessage;
	}

	@Override
	public MelterLogSummaryOutputMessage findById(
			MelterLogSummaryInputMessage melterLogSummaryInputMessage) {
		flowId = FIND_BY_ID;
		this.melterLogSummaryInputMessage = melterLogSummaryInputMessage;
		advanzErpServiceTemplate.execute(this);
		return melterLogSummaryOutputMessage;
	}

	@Override
	public MelterLogSummaryOutputMessage searchMelterSummaryByDate(
			MelterLogSummaryInputMessage melterLogSummaryInputMessage) {
		flowId = SEARCH_MELTER_SUMMARY_BY_DATE;
		this.melterLogSummaryInputMessage = melterLogSummaryInputMessage;
		advanzErpServiceTemplate.execute(this);
		return melterLogSummaryOutputMessage;
	}

	@Override
	public MelterLogSummaryOutputMessage getLastMelterLogSummaryDate(
			MelterLogSummaryInputMessage melterLogSummaryInputMessage) {
		flowId = GET_LAST_MELTER_SUMMARY_DATE;
		this.melterLogSummaryInputMessage = melterLogSummaryInputMessage;
		advanzErpServiceTemplate.execute(this);
		return melterLogSummaryOutputMessage;
	}

	@Override
	public boolean validateInput() {
		if (ADD_MELTER_LOG_SUMMARY.equals(flowId)) {
			return true;
		} else if (FIND_ALL_MELTER_LOG_SUMMARY.equals(flowId)) {
			return true;
		} else if (FIND_BY_ID.equals(flowId)) {
			return true;
		} else if (DELETE_MELTER_RECORD.equals(flowId)) {
			return true;
		} else if (UPDATE_MELTER_RECORD.equals(flowId)) {
			return true;
		}

		else if (SEARCH_MELTER_SUMMARY_BY_DATE.equals(flowId)) {
			return true;
		} else if (GET_LAST_MELTER_SUMMARY_DATE.equals(flowId)) {
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {
		if (FIND_ALL_MELTER_LOG_SUMMARY.equals(flowId)) {
			List<MelterLogSummaryEntity> list = iMelterLogSummaryDAO.load();
			melterLogSummaryOutputMessage = new MelterLogSummaryOutputMessage();
			List<MelterLogSummaryDTO> resuList = convertMelterLogSummaryEntityToMelterLogBookDTO(list);
			melterLogSummaryOutputMessage.setMelterLogSummaryDTOList(resuList);
		}

		else if (ADD_MELTER_LOG_SUMMARY.equals(flowId)) {
			MelterLogSummaryEntity melterLogSummaryEntity = new MelterLogSummaryEntity();
			MelterLogSummaryDTO melterLogSummaryDTO = new MelterLogSummaryDTO();
			try {

				if (melterLogSummaryInputMessage != null) {
					melterLogSummaryOutputMessage = new MelterLogSummaryOutputMessage();
					melterLogSummaryDTO = melterLogSummaryInputMessage
							.getMelterLogSummaryDTO();
					BeanUtils.copyProperties(melterLogSummaryDTO,
							melterLogSummaryEntity);
				}
				List<MelterLogSummaryEntity> list = iMelterLogSummaryDAO
						.checkDuplicateSummary(
								melterLogSummaryEntity.getLogDate(),
								melterLogSummaryEntity.getLogTime());
				if (list != null && list.size() > 0) {
					ErrorDTO errorDTO = new ErrorDTO("1",
							"Sorry, Record already exist, Duplicate entries are not allowed.");
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					melterLogSummaryOutputMessage = new MelterLogSummaryOutputMessage();
					melterLogSummaryOutputMessage.setErrorListDTO(errorListDTO);
				} else {
					melterLogSummaryOutputMessage = new MelterLogSummaryOutputMessage();
					melterLogSummaryOutputMessage.setErrorListDTO(null);
					iMelterLogSummaryDAO.create(melterLogSummaryEntity);
				}
			} catch (Exception e) {
				e.getMessage();
				// TODO: handle exception
			}
		}

		else if (FIND_BY_ID.equals(flowId)) {
			MelterLogSummaryEntity melterLogSummaryEntity = new MelterLogSummaryEntity();
			if (melterLogSummaryInputMessage != null) {
				MelterLogSummaryDTO melterLogSummaryDTO = melterLogSummaryInputMessage
						.getMelterLogSummaryDTO();
				BeanUtils.copyProperties(melterLogSummaryDTO,
						melterLogSummaryEntity);
			}
			List<MelterLogSummaryEntity> list = iMelterLogSummaryDAO
					.findBySno(melterLogSummaryEntity.getSno());
			melterLogSummaryOutputMessage = new MelterLogSummaryOutputMessage();
			if (list != null) {
				List<MelterLogSummaryDTO> melterLogSummaryDTOList = convertMelterLogSummaryEntityToMelterLogBookDTO(list);
				melterLogSummaryOutputMessage
						.setMelterLogSummaryDTOList(melterLogSummaryDTOList);
			}
		}

		else if (DELETE_MELTER_RECORD.equals(flowId)) {

			MelterLogSummaryEntity entity = new MelterLogSummaryEntity();
			if (melterLogSummaryInputMessage != null) {
				MelterLogSummaryDTO melterLogSummaryDTO = melterLogSummaryInputMessage
						.getMelterLogSummaryDTO();
				BeanUtils.copyProperties(melterLogSummaryDTO, entity);
				iMelterLogSummaryDAO.delete(entity);
			}
		}

		else if (UPDATE_MELTER_RECORD.equals(flowId)) {
			MelterLogSummaryEntity entity = new MelterLogSummaryEntity();
			if (melterLogSummaryInputMessage != null) {
				melterLogSummaryOutputMessage = new MelterLogSummaryOutputMessage();
				MelterLogSummaryDTO melterLogSummaryDTO = new MelterLogSummaryDTO();
				melterLogSummaryDTO = melterLogSummaryInputMessage
						.getMelterLogSummaryDTO();
				BeanUtils.copyProperties(melterLogSummaryDTO, entity);

				List<MelterLogSummaryEntity> list = iMelterLogSummaryDAO
						.checkDuplicateSummary(entity.getLogDate(),
								entity.getLogTime());
				if (list != null && list.size() > 0
						&& !list.get(0).getSno().equals(entity.getSno())) {
					ErrorDTO errorDTO = new ErrorDTO("1",
							"Sorry, Record already exist, Duplicate entries are not allowed.");
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					melterLogSummaryOutputMessage.setErrorListDTO(errorListDTO);
				} else {
					iMelterLogSummaryDAO.update(entity);
					melterLogSummaryOutputMessage.setErrorListDTO(null);
				}
			}
		}

		else if (SEARCH_MELTER_SUMMARY_BY_DATE.equals(flowId)) {
			MelterLogSummaryEntity entity = new MelterLogSummaryEntity();
			if (melterLogSummaryInputMessage != null) {
				MelterLogSummaryDTO melterLogSummaryDTO = melterLogSummaryInputMessage
						.getMelterLogSummaryDTO();

				BeanUtils.copyProperties(melterLogSummaryDTO, entity);
				List<MelterLogSummaryEntity> list = null;
				if (melterLogSummaryDTO.getFromDate() != null
						|| melterLogSummaryDTO.getToDate() != null) {
					list = iMelterLogSummaryDAO
							.searchMelterSummaryRecordByDate(
									melterLogSummaryDTO.getFromDate(),
									melterLogSummaryDTO.getToDate());
				}

				if (list != null) {
					melterLogSummaryOutputMessage = new MelterLogSummaryOutputMessage();
					List<MelterLogSummaryDTO> melterLogSummaryDTOList = convertMelterLogSummaryEntityToMelterLogBookDTO(list);
					melterLogSummaryOutputMessage
							.setMelterLogSummaryDTOList(melterLogSummaryDTOList);

				}
			}
		} else if (GET_LAST_MELTER_SUMMARY_DATE.equals(flowId)) {

			MelterLogSummaryEntity entity = new MelterLogSummaryEntity();

			Timestamp date = zoneService.getFirstDayOfFinYear();
			Timestamp date1 = iMelterLogSummaryDAO
					.getLastMelterLogSummaryDate();
			if (date1 != null) {
				date = date1;

			}

			melterLogSummaryOutputMessage = new MelterLogSummaryOutputMessage();
			melterLogSummaryOutputMessage.setLastMelterLogSummaryDate(date);

		}
	}

	@Override
	public void formatOutput() {
		if (ADD_MELTER_LOG_SUMMARY.equals(flowId)) {
		} else if (FIND_ALL_MELTER_LOG_SUMMARY.equals(flowId)) {
		} else if (FIND_BY_ID.equals(flowId)) {
		} else if (DELETE_MELTER_RECORD.equals(flowId)) {
		} else if (UPDATE_MELTER_RECORD.equals(flowId)) {
		} else if (SEARCH_MELTER_SUMMARY_BY_DATE.equals(flowId)) {
		}
	}

	private List<MelterLogSummaryDTO> convertMelterLogSummaryEntityToMelterLogBookDTO(
			List<MelterLogSummaryEntity> list) {
		List<MelterLogSummaryDTO> resultList = new ArrayList<MelterLogSummaryDTO>();
		MelterLogSummaryDTO melterLogSummaryDTO;
		for (MelterLogSummaryEntity entity : list) {
			melterLogSummaryDTO = new MelterLogSummaryDTO();
			try {
				BeanUtils.copyProperties(entity, melterLogSummaryDTO,
						ignoreProperties);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resultList.add(melterLogSummaryDTO);
		}
		return resultList;
	}

}
