package com.advanz.erp.masters.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.AnnealingOvenDetailEntity;
import com.advanz.erp.masters.entity.jpa.AnnealingOvenMasterEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.model.AnnealingOvenDetailDTO;
import com.advanz.erp.masters.model.AnnealingOvenMasterDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.msg.AnnealingOvenInputMessage;
import com.advanz.erp.masters.model.msg.AnnealingOvenOutputMessage;
import com.advanz.erp.masters.service.business.IAnnealingOvenService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageAnnealingOvenDAO;
import com.advanz.erp.masters.storage.IStorageMastersDAO;

public class AnnealingOvenServiceImpl implements IAnnealingOvenService {

	public static final String UPDATE_ANNEALINGOVEN = "UpdateAnnealingOven";
	public static final String ADD_ANNEALINGOVEN = "AddAnnealingOven";
	public static final String DELETE_ANNEALINGOVEN = "DeleteAnnealingOven";
	public static final String FIND_ANNEALINGOVEN_BY_ID = "FindAnnealingOvenById";
	public static final String FIND_ALL_ANNEALINGOVENES = "FindAllAnnealingOvenes";
	public static final String LAST_ANNEALING_OVENES_DATE = "LastAnnealingOvenesDate";
	public static final String FIND_ANNEALINGOVENES = "FindAnnealingOvenes";
	public static final String CHECK_DUPLICATE_ENTRY = "CheckDuplicateEntry";
	public String flowId = "";

	private static final Logger logger = LoggerFactory
			.getLogger(AnnealingOvenServiceImpl.class);

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
	// do

	@Autowired
	public IStorageAnnealingOvenDAO storageAnnealingOvenDAO;

	@Autowired
	public IStorageMastersDAO storageMastersDAO;

	public AnnealingOvenInputMessage annealingOvenInputMessage;

	public AnnealingOvenOutputMessage annealingOvenOutMessage;

	@Autowired
	public IZoneService zoneService;

	@Override
	public AnnealingOvenOutputMessage createAnnealingOven(
			AnnealingOvenInputMessage annealingOvenInputMessage) {

		flowId = ADD_ANNEALINGOVEN;
		// assign the message to the class level variable.
		this.annealingOvenInputMessage = annealingOvenInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return annealingOvenOutMessage;
	}

	@Override
	public AnnealingOvenOutputMessage updateAnnealingOven(
			AnnealingOvenInputMessage annealingOvenInputMessage) {

		flowId = UPDATE_ANNEALINGOVEN;
		this.annealingOvenInputMessage = annealingOvenInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return annealingOvenOutMessage;
	}

	@Override
	public AnnealingOvenOutputMessage deleteAnnealingOven(
			AnnealingOvenInputMessage annealingOvenInputMessage) {
		flowId = DELETE_ANNEALINGOVEN;
		this.annealingOvenInputMessage = annealingOvenInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return annealingOvenOutMessage;

	}

	@Override
	public AnnealingOvenOutputMessage findAnnealingOvenById(
			AnnealingOvenInputMessage annealingOvenInputMessage) {
		flowId = FIND_ANNEALINGOVEN_BY_ID;
		this.annealingOvenInputMessage = annealingOvenInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return annealingOvenOutMessage;

	}

	@Override
	public AnnealingOvenOutputMessage findAllAnnealingOven() {
		flowId = FIND_ALL_ANNEALINGOVENES;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return annealingOvenOutMessage;
	}

	// @Override
	public AnnealingOvenOutputMessage findAnnealingOven(
			AnnealingOvenInputMessage annealingOvenInputMessage) {
		flowId = FIND_ANNEALINGOVENES;
		this.annealingOvenInputMessage = annealingOvenInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return annealingOvenOutMessage;
	}

	@Override
	public AnnealingOvenOutputMessage checktDuplicateEntry(
			AnnealingOvenInputMessage annealingOvenInputMessage) {

		flowId = CHECK_DUPLICATE_ENTRY;
		this.annealingOvenInputMessage = annealingOvenInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return annealingOvenOutMessage;
	}

	@Override
	public AnnealingOvenOutputMessage lastAnnealingOvenDate(
			AnnealingOvenInputMessage annealingOvenInputMessage) {

		flowId = LAST_ANNEALING_OVENES_DATE;
		this.annealingOvenInputMessage = annealingOvenInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return annealingOvenOutMessage;
	}

	@Override
	public boolean validateInput() {

		if (ADD_ANNEALINGOVEN.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_ANNEALINGOVEN.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_ANNEALINGOVEN.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ANNEALINGOVEN_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_ANNEALINGOVENES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ANNEALINGOVENES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (CHECK_DUPLICATE_ENTRY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (LAST_ANNEALING_OVENES_DATE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {
		AnnealingOvenMasterEntity annealingOvenMasterEntity = new AnnealingOvenMasterEntity();
		AnnealingOvenMasterDTO annealingOvenMasterDTO = null;
		if (annealingOvenInputMessage != null) {
			annealingOvenMasterDTO = annealingOvenInputMessage
					.getAnnealingOvenMasterDTO();

			if (annealingOvenMasterDTO != null) {
				BeanUtils.copyProperties(annealingOvenMasterDTO,
						annealingOvenMasterEntity);

				if (annealingOvenMasterDTO.getShiftMasterDTO() != null) {
					MastersEntity mastersEntity = new MastersEntity();
					BeanUtils.copyProperties(
							annealingOvenMasterDTO.getShiftMasterDTO(),
							mastersEntity);
					annealingOvenMasterEntity.setShiftMasters(mastersEntity);
				}

				if (annealingOvenMasterDTO.getAnnealingOvenDetailDTOList() != null) {
					List<AnnealingOvenDetailEntity> detailDTOList = convertAnnealingOvenDetailDtoToEntity(annealingOvenMasterDTO
							.getAnnealingOvenDetailDTOList());
					annealingOvenMasterEntity
							.setAnnealingOvenDetailEntity(detailDTOList);
				}

			}
		}

		if (ADD_ANNEALINGOVEN.equals(flowId)) {
			try {

				List<AnnealingOvenMasterEntity> list = storageAnnealingOvenDAO
						.checkDuplicateEntry(annealingOvenMasterDTO
								.getOvenDate(), annealingOvenMasterDTO
								.getShiftMasterDTO().getMastersId());

				annealingOvenOutMessage = new AnnealingOvenOutputMessage();
				if ((list != null && list.size() > 0)) {
					ErrorDTO errorDTO = new ErrorDTO("1", "Duplicate entry");
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					annealingOvenOutMessage.setErrorListDTO(errorListDTO);
				} else {
					annealingOvenOutMessage.setErrorListDTO(null);
					annealingOvenMasterEntity.setDeletedFlag(false);
					storageAnnealingOvenDAO.create(annealingOvenMasterEntity);
				}
			} catch (BeansException e) {
				e.printStackTrace();
			}
		}

		else if (UPDATE_ANNEALINGOVEN.equals(flowId)) {
			try {
				annealingOvenOutMessage = new AnnealingOvenOutputMessage();
				annealingOvenOutMessage.setErrorListDTO(null);
				annealingOvenMasterEntity.setDeletedFlag(false);
				storageAnnealingOvenDAO.update(annealingOvenMasterEntity);

			} catch (BeansException e) {
				e.printStackTrace();
			}
		} else if (FIND_ALL_ANNEALINGOVENES.equals(flowId)) {
			List<AnnealingOvenMasterEntity> list = storageAnnealingOvenDAO
					.load();
			annealingOvenOutMessage = new AnnealingOvenOutputMessage();
			// set the data to the output message.
			if (list != null) {
				List<AnnealingOvenMasterDTO> resultList = convertAnnealingOvenEntityListTOAnnealingOvenDtoList(list);
				annealingOvenOutMessage
						.setAnnealingOvenMasterDTOList(resultList);
			}
		} else if (DELETE_ANNEALINGOVEN.equals(flowId)) {
			try {
				annealingOvenMasterDTO = annealingOvenInputMessage
						.getAnnealingOvenMasterDTO();

				try {
					BeanUtils.copyProperties(annealingOvenMasterDTO,
							annealingOvenMasterEntity);
				} catch (BeansException e) {
					e.printStackTrace();
				}
				storageAnnealingOvenDAO.delete(annealingOvenMasterEntity);
			} catch (BeansException e) {
				e.printStackTrace();
			}

		}

		else if (FIND_ANNEALINGOVEN_BY_ID.equals(flowId)) {
			annealingOvenMasterDTO = annealingOvenInputMessage
					.getAnnealingOvenMasterDTO();
			List<AnnealingOvenMasterEntity> list = storageAnnealingOvenDAO
					.findById(annealingOvenMasterDTO.getOvenId());
			if (list != null) {
				List<AnnealingOvenMasterDTO> resultList = convertAnnealingOvenEntityListTOAnnealingOvenDtoList(list);
				annealingOvenOutMessage
						.setAnnealingOvenMasterDTOList(resultList);
			}
		} else if (FIND_ANNEALINGOVENES.equals(flowId)) {
			annealingOvenMasterDTO = annealingOvenInputMessage
					.getAnnealingOvenMasterDTO();
			String date = null;
			String shiftName = null;
			Date fromDate = null;
			Date toDate = null;
			if (annealingOvenMasterEntity.getOvenDate() != null) {
				SimpleDateFormat obj = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				date = obj.format(annealingOvenMasterEntity.getOvenDate());
			}

			try {
				if (annealingOvenMasterDTO.getFromDate() != null) {
					fromDate = annealingOvenMasterDTO.getFromDate();
				}
				if (annealingOvenMasterDTO.getToDate() != null) {
					toDate = annealingOvenMasterDTO.getToDate();
				}
				// System.out.println("FROM DATE IS ::" +fromDate
				// +" TO DATE IS : "+ toDate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (annealingOvenMasterEntity.getShiftMasters() != null) {
				shiftName = annealingOvenMasterEntity.getShiftMasters()
						.getName();
			}

			List<AnnealingOvenMasterEntity> list = storageAnnealingOvenDAO
					.search(fromDate, toDate, shiftName);
			annealingOvenOutMessage = new AnnealingOvenOutputMessage();
			if (list != null) {
				List<AnnealingOvenMasterDTO> resultList = convertAnnealingOvenEntityListTOAnnealingOvenDtoList(list);
				annealingOvenOutMessage
						.setAnnealingOvenMasterDTOList(resultList);
			}
		} else if (LAST_ANNEALING_OVENES_DATE.equals(flowId)) {

			Timestamp date = zoneService.getFirstDayOfFinYear();
			Timestamp date1 = storageAnnealingOvenDAO.lastAnnealingOvenDate();
			if (date1 != null) {
				date = date1;
			}
			annealingOvenOutMessage = new AnnealingOvenOutputMessage();
			annealingOvenOutMessage.setLastAnnealingOvenDate(date);

		}
	}

	@Override
	public void formatOutput() {

		if (ADD_ANNEALINGOVEN.equals(flowId)) {

		} else if (UPDATE_ANNEALINGOVEN.equals(flowId)) {

		} else if (DELETE_ANNEALINGOVEN.equals(flowId)) {

		} else if (FIND_ANNEALINGOVEN_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_ANNEALINGOVENES.equals(flowId)) {

		}
	}

	private List<AnnealingOvenMasterDTO> convertAnnealingOvenEntityListTOAnnealingOvenDtoList(
			List<AnnealingOvenMasterEntity> list) {

		annealingOvenOutMessage = new AnnealingOvenOutputMessage();
		List<AnnealingOvenMasterDTO> resultList = null;
		// set the data to the output message.
		if (list != null) {
			AnnealingOvenMasterDTO annealingOvenDTO;
			resultList = new ArrayList<AnnealingOvenMasterDTO>();
			for (AnnealingOvenMasterEntity entity : list) {
				annealingOvenDTO = new AnnealingOvenMasterDTO();

				BeanUtils.copyProperties(entity, annealingOvenDTO);
				if (entity.getShiftMasters() != null) {
					MastersDTO mastersDTO = new MastersDTO();
					BeanUtils.copyProperties(entity.getShiftMasters(),
							mastersDTO);
					annealingOvenDTO.setShiftMasterDTO(mastersDTO);
				}

				if (entity.getAnnealingOvenDetailEntity() != null) {
					List<AnnealingOvenDetailDTO> detailDTOList = convertAnnealingOvenDetailEntityToDTO(entity
							.getAnnealingOvenDetailEntity());
					annealingOvenDTO
							.setAnnealingOvenDetailDTOList(detailDTOList);
				}
				resultList.add(annealingOvenDTO);
			}
		}
		return resultList;
	}

	private List<AnnealingOvenDetailDTO> convertAnnealingOvenDetailEntityToDTO(
			List<AnnealingOvenDetailEntity> detailEntityList) {
		List<AnnealingOvenDetailDTO> resultDetailList = new ArrayList<AnnealingOvenDetailDTO>();
		AnnealingOvenDetailDTO detailDTO = null;
		for (AnnealingOvenDetailEntity detailEntity : detailEntityList) {
			detailDTO = new AnnealingOvenDetailDTO();
			BeanUtils.copyProperties(detailEntity, detailDTO);
			resultDetailList.add(detailDTO);
		}
		return resultDetailList;
	}

	private List<AnnealingOvenDetailEntity> convertAnnealingOvenDetailDtoToEntity(
			List<AnnealingOvenDetailDTO> detailDTOList) {
		List<AnnealingOvenDetailEntity> resultDetailList = new ArrayList<AnnealingOvenDetailEntity>();
		AnnealingOvenDetailEntity detailEntity = null;
		for (AnnealingOvenDetailDTO detailDTO : detailDTOList) {
			detailEntity = new AnnealingOvenDetailEntity();
			BeanUtils.copyProperties(detailDTO, detailEntity);
			resultDetailList.add(detailEntity);
		}
		return resultDetailList;
	}

	public MastersDTO popUpList(List<MastersEntity> list) {
		MastersDTO mastersDTO = new MastersDTO();
		if (list != null && list.size() > 0) {

			BeanUtils.copyProperties(list.get(0), mastersDTO);

		}
		return mastersDTO;
	}

}
