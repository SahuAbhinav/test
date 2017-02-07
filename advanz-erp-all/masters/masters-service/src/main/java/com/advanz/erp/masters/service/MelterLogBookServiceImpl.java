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
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.entity.jpa.MelterLogBookEntity;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.MelterLogBookDTO;
import com.advanz.erp.masters.model.msg.MelterLogBookInputMessage;
import com.advanz.erp.masters.model.msg.MelterLogBookOutputMessage;
import com.advanz.erp.masters.service.business.IMelterLogBookService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageMelterLogBookDAO;

public class MelterLogBookServiceImpl implements IMelterLogBookService {
	public static final String ADD_MELTER_LOG_BOOK = "createNewMelterLogBook";
	public static final String FIND_ALL_MELTER_LOG_BOOK = "findAllMelterLogBook";
	public static final String LAST_MELTER_LOG_BOOK_DATE = "lastMelterLogBookDate";
	public static final String FIND_FOR_SEARCH_RECORD = "findForSearchRecord";
	public static final String FIND_BY_ID = "findById";
	public static final String DELETE_MELTER_RECORD = "deleteMelterRecord";
	public static final String UPDATE_MELTER_RECORD = "updateMelterTable";
	public static final String FIND_BY_DATE_RUNNO_SHIFT_TIME = "findByDateRunNoShiftTime";
	public String flowId = "";
	String ignoreProperties[] = { "servTaxDate,vatDate,cstDate" };

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO

	private static final Logger logger = LoggerFactory
			.getLogger(MelterLogBookServiceImpl.class); // do
	// autowiring

	@Autowired
	public IStorageMelterLogBookDAO iStorageMelterLogBookDAO;
	public MelterLogBookInputMessage melterLogBookInputMessage;
	public MelterLogBookOutputMessage melterLogBookOutputMessage;

	@Autowired
	public IZoneService zoneService;

	@Override
	public MelterLogBookOutputMessage createMelterLogBook(
			MelterLogBookInputMessage melterLogBookInputMessage) {
		flowId = ADD_MELTER_LOG_BOOK;
		this.melterLogBookInputMessage = melterLogBookInputMessage;
		advanzErpServiceTemplate.execute(this);
		return melterLogBookOutputMessage;
	}

	@Override
	public MelterLogBookOutputMessage findAllMelterLogBook() {
		flowId = FIND_ALL_MELTER_LOG_BOOK;
		advanzErpServiceTemplate.execute(this);
		return melterLogBookOutputMessage;
	}

	@Override
	public MelterLogBookOutputMessage findForSearchRecord(
			MelterLogBookInputMessage melterLogBookInputMessage) {

		flowId = FIND_FOR_SEARCH_RECORD;
		this.melterLogBookInputMessage = melterLogBookInputMessage;
		advanzErpServiceTemplate.execute(this);
		return melterLogBookOutputMessage;
	}

	@Override
	public MelterLogBookOutputMessage findMelterById(
			MelterLogBookInputMessage melterLogBookInputMessage) {
		flowId = FIND_BY_ID;
		this.melterLogBookInputMessage = melterLogBookInputMessage;
		advanzErpServiceTemplate.execute(this);
		return melterLogBookOutputMessage;
	}

	@Override
	public MelterLogBookOutputMessage deleteMelterRecord(
			MelterLogBookInputMessage melterLogBookInputMessage) {
		flowId = DELETE_MELTER_RECORD;
		this.melterLogBookInputMessage = melterLogBookInputMessage;
		advanzErpServiceTemplate.execute(this);
		return melterLogBookOutputMessage;
	}

	@Override
	public MelterLogBookOutputMessage updateMelterRecord(
			MelterLogBookInputMessage melterLogBookInputMessage) {
		flowId = UPDATE_MELTER_RECORD;
		this.melterLogBookInputMessage = melterLogBookInputMessage;
		advanzErpServiceTemplate.execute(this);
		return melterLogBookOutputMessage;
	}

	@Override
	public MelterLogBookOutputMessage getLastRecordDate(
			MelterLogBookInputMessage melterLogBookInputMessage) {
		flowId = LAST_MELTER_LOG_BOOK_DATE;
		this.melterLogBookInputMessage = melterLogBookInputMessage;
		advanzErpServiceTemplate.execute(this);
		return melterLogBookOutputMessage;
	}

	@Override
	public boolean validateInput() {
		if (ADD_MELTER_LOG_BOOK.equals(flowId)) {
			return true;
		}

		else if (FIND_ALL_MELTER_LOG_BOOK.equals(flowId)) {
			return true;
		}

		else if (FIND_BY_ID.equals(flowId)) {
			return true;
		}

		else if (DELETE_MELTER_RECORD.equals(flowId)) {
			return true;
		}

		else if (UPDATE_MELTER_RECORD.equals(flowId)) {
			return true;
		}

		else if (FIND_FOR_SEARCH_RECORD.equals(flowId)) {
			return true;
		}

		else if (LAST_MELTER_LOG_BOOK_DATE.equals(flowId)) {
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {
		MelterLogBookEntity melterLogBookEntity = new MelterLogBookEntity();
		if (melterLogBookInputMessage != null) {
			MelterLogBookDTO melterLogBookDTO = melterLogBookInputMessage
					.getMelterLogBookDTO();
			BeanUtils.copyProperties(melterLogBookDTO, melterLogBookEntity);
			MastersEntity mastersEntity = new MastersEntity();
			if (melterLogBookDTO.getMastersDto() != null) {
				mastersEntity.setMastersId(melterLogBookDTO.getMastersDto()
						.getMastersId());
				melterLogBookEntity.setMastersEntity(mastersEntity);
			}
		}

		if (ADD_MELTER_LOG_BOOK.equals(flowId)) {
			try {
				List<MelterLogBookEntity> findForDuplicate = iStorageMelterLogBookDAO
						.findByDateRunNoShiftTime(melterLogBookEntity
								.getLogDate(), melterLogBookEntity.getRunNo(),
								melterLogBookEntity.getMastersEntity()
										.getMastersId(), melterLogBookEntity
										.getLogTime());

				if (findForDuplicate != null && findForDuplicate.size() > 0) {
					logger.info("melter Log Book find for duplicate");
					ErrorDTO errorDTO = new ErrorDTO("1",
							"Sorry, Record already exist, Duplicate entries are not allowed.");
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					melterLogBookOutputMessage = new MelterLogBookOutputMessage();
					melterLogBookOutputMessage.setErrorListDTO(errorListDTO);
				} else {
					melterLogBookOutputMessage = new MelterLogBookOutputMessage();
					melterLogBookOutputMessage.setErrorListDTO(null);
					logger.info("Creating Melter Log BookEntity"
							+ melterLogBookEntity);
					iStorageMelterLogBookDAO.create(melterLogBookEntity);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (FIND_FOR_SEARCH_RECORD.equals(flowId)) {
			MelterLogBookDTO melterLogBookDTO = melterLogBookInputMessage
					.getMelterLogBookDTO();
			List<MelterLogBookEntity> melterLogBookEntitieList = iStorageMelterLogBookDAO
					.search(melterLogBookDTO.getFromDate(),
							melterLogBookDTO.getToDate(),
							melterLogBookDTO.getRunNo(),
							melterLogBookDTO.getOperatorName());
			melterLogBookOutputMessage = new MelterLogBookOutputMessage();
			if (melterLogBookEntitieList != null) {
				List<MelterLogBookDTO> resultList = convertMelterLogBookEntityToMelterLogBookDTO(melterLogBookEntitieList);
				melterLogBookOutputMessage.setMelterLogBookDTOList(resultList);
			}
		}

		else if (FIND_ALL_MELTER_LOG_BOOK.equals(flowId)) {
			List<MelterLogBookEntity> melterLogBookEntitieList = iStorageMelterLogBookDAO
					.load();

			melterLogBookOutputMessage = new MelterLogBookOutputMessage();
			if (melterLogBookEntitieList != null) {
				List<MelterLogBookDTO> resultList = convertMelterLogBookEntityToMelterLogBookDTO(melterLogBookEntitieList);
				melterLogBookOutputMessage.setMelterLogBookDTOList(resultList);
			}
		} else if (LAST_MELTER_LOG_BOOK_DATE.equals(flowId)) {
		

			melterLogBookOutputMessage = new MelterLogBookOutputMessage();
			

			Timestamp date = zoneService.getFirstDayOfFinYear();
			List list = iStorageMelterLogBookDAO.getLastRecordDate();
			if (list != null && list.size() > 0) {
				if(list.get(0)!=null)
				date = (Timestamp) list.get(0);
			}
			
			melterLogBookOutputMessage.setLastRecordDate(date);

		}

		else if (FIND_BY_ID.equals(flowId)) {
			List<MelterLogBookEntity> melterLogBookEntityList = iStorageMelterLogBookDAO
					.findById(melterLogBookEntity.getSno());
			melterLogBookOutputMessage = new MelterLogBookOutputMessage();
			if (melterLogBookEntityList != null) {
				List<MelterLogBookDTO> resultList = convertMelterLogBookEntityToMelterLogBookDTO(melterLogBookEntityList);
				melterLogBookOutputMessage.setMelterLogBookDTOList(resultList);

			}
		}

		else if (DELETE_MELTER_RECORD.equals(flowId)) {
			// logger.info("Deleting Melter Log BookEntity"+melterLogBookEntity);
			iStorageMelterLogBookDAO.delete(melterLogBookEntity);
		}

		else if (UPDATE_MELTER_RECORD.equals(flowId)) {
			List<MelterLogBookEntity> findForDuplicate = iStorageMelterLogBookDAO
					.findByDateRunNoShiftTime(melterLogBookEntity.getLogDate(),
							melterLogBookEntity.getRunNo(), melterLogBookEntity
									.getMastersEntity().getMastersId(),
							melterLogBookEntity.getLogTime());

			if (findForDuplicate != null
					&& findForDuplicate.size() > 0
					&& !findForDuplicate.get(0).getSno()
							.equals(melterLogBookEntity.getSno())) {
				// logger.info("melter Log Book find for duplicate");
				ErrorDTO errorDTO = new ErrorDTO("1",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				melterLogBookOutputMessage = new MelterLogBookOutputMessage();
				melterLogBookOutputMessage.setErrorListDTO(errorListDTO);
			} else {

				melterLogBookOutputMessage = new MelterLogBookOutputMessage();
				melterLogBookOutputMessage.setErrorListDTO(null);
				logger.info("Updating Melter Log BookEntity"
						+ melterLogBookEntity);
				iStorageMelterLogBookDAO.update(melterLogBookEntity);
			}
		}
	}

	@Override
	public void formatOutput() {
		if (ADD_MELTER_LOG_BOOK.equals(flowId)) {
		} else if (FIND_FOR_SEARCH_RECORD.equals(flowId)) {
		} else if (FIND_ALL_MELTER_LOG_BOOK.equals(flowId)) {
		} else if (DELETE_MELTER_RECORD.equals(flowId)) {
		} else if (UPDATE_MELTER_RECORD.equals(flowId)) {
		} else if (FIND_FOR_SEARCH_RECORD.equals(flowId)) {
		}
	}

	private List<MelterLogBookDTO> convertMelterLogBookEntityToMelterLogBookDTO(
			List<MelterLogBookEntity> list) {
		List<MelterLogBookDTO> resultList = new ArrayList<MelterLogBookDTO>();
		MelterLogBookDTO melterLogBookDTO;
		for (MelterLogBookEntity entity : list) {
			melterLogBookDTO = new MelterLogBookDTO();
			melterLogBookDTO.setMastersDto(new MastersDTO());
			try {
				BeanUtils.copyProperties(entity, melterLogBookDTO);
			} catch (Exception e) {
				e.getMessage();
			}
			if (entity.getMastersEntity() != null) {
				try {
					BeanUtils.copyProperties(entity.getMastersEntity(),
							melterLogBookDTO.getMastersDto());
				} catch (Exception e) {
					e.getMessage();
				}
			}
			resultList.add(melterLogBookDTO);
		}
		return resultList;
	}

}
