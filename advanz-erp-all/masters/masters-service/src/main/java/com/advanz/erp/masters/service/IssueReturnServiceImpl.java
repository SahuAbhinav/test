package com.advanz.erp.masters.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.entity.jpa.IssueReturnDetailEntity;
import com.advanz.erp.masters.entity.jpa.IssueReturnMasterEntity;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.IssueDetailMasterDTO;
import com.advanz.erp.masters.model.IssueMasterDTO;
import com.advanz.erp.masters.model.IssueReturnDetailDTO;
import com.advanz.erp.masters.model.IssueReturnMasterDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.StockLedgerDTO;
import com.advanz.erp.masters.model.msg.IssueReturnMasterInputMessage;
import com.advanz.erp.masters.model.msg.IssueReturnMasterOutputMessage;
import com.advanz.erp.masters.model.msg.StockLedgerInputMessage;
import com.advanz.erp.masters.model.msg.StockLedgerOutMessage;
import com.advanz.erp.masters.service.business.IIssueMasterService;
import com.advanz.erp.masters.service.business.IIssueReturnService;
import com.advanz.erp.masters.service.business.IStockLedgerService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageIssueReturnDAO;

@Service
public class IssueReturnServiceImpl implements IIssueReturnService {

	public IssueReturnMasterInputMessage issueReturnMasterInputMessage = null;
	public IssueReturnMasterOutputMessage issueReturnMasterOutputMessage = null;
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();

	@Autowired
	public IStorageIssueReturnDAO iStorageIssueReturnDAO;
	@Autowired
	public IStockLedgerService stockLedgerService;

	StockLedgerInputMessage stockLedgerInputMessage = null;
	@Autowired
	IIssueMasterService issueMasterService;

	@Autowired
	public IZoneService zoneService;
	public static final Logger logger = LoggerFactory
			.getLogger(IssueReturnServiceImpl.class);

	String flowId = "";

	public static final String FIND_ALL_ISSUE_RETURN = "findAllIssueReturn";
	public static final String CREATE_ISSUE_RETURN = "createIssueReturn";
	public static final String UPDATE_ISSUE_RETURN = "updateIssueReturn";
	public static final String DELETE_ISSUE_RETURN = "deleteIssueReturn";
	public static final String FIND_BY_ID_ISSUE_RETRN = "findByIdIssueReturn";
	public static final String SEARCH_ISSUE_RETRN = "searchIssueReturn";
	public static final String NEW_ISSUE_RETURN_SERIES_NO = "newIssueReturnSeriesNo";

	public static final String FIND_ISSUE_RETRN_PAGINATION = "findIssueReturnPagination";

	@Override
	public IssueReturnMasterOutputMessage findAllIssueReturn() {
		flowId = FIND_ALL_ISSUE_RETURN;
		advanzErpServiceTemplate.execute(this);
		return issueReturnMasterOutputMessage;
	}

	@Override
	public IssueReturnMasterOutputMessage createIssueReturn(
			IssueReturnMasterInputMessage issueReturnMasterInputMessage) {
		flowId = CREATE_ISSUE_RETURN;
		this.issueReturnMasterInputMessage = issueReturnMasterInputMessage;
		advanzErpServiceTemplate.execute(this);
		return issueReturnMasterOutputMessage;
	}

	@Override
	public IssueReturnMasterOutputMessage updateIssueReturn(
			IssueReturnMasterInputMessage issueReturnMasterInputMessage) {
		flowId = UPDATE_ISSUE_RETURN;
		this.issueReturnMasterInputMessage = issueReturnMasterInputMessage;
		advanzErpServiceTemplate.execute(this);
		return issueReturnMasterOutputMessage;
	}

	@Override
	public IssueReturnMasterOutputMessage deleteIssueReturn(
			IssueReturnMasterInputMessage issueReturnMasterInputMessage) {
		flowId = DELETE_ISSUE_RETURN;
		this.issueReturnMasterInputMessage = issueReturnMasterInputMessage;
		advanzErpServiceTemplate.execute(this);
		return issueReturnMasterOutputMessage;
	}

	@Override
	public IssueReturnMasterOutputMessage findById(
			IssueReturnMasterInputMessage issueReturnMasterInputMessage) {
		flowId = FIND_BY_ID_ISSUE_RETRN;
		this.issueReturnMasterInputMessage = issueReturnMasterInputMessage;
		advanzErpServiceTemplate.execute(this);
		return issueReturnMasterOutputMessage;
	}

	@Override
	public IssueReturnMasterOutputMessage newIssueReturnSeriesNo(
			IssueReturnMasterInputMessage issueReturnMasterInputMessage) {
		flowId = NEW_ISSUE_RETURN_SERIES_NO;
		this.issueReturnMasterInputMessage = issueReturnMasterInputMessage;
		advanzErpServiceTemplate.execute(this);
		return issueReturnMasterOutputMessage;
	}

	@Override
	public IssueReturnMasterOutputMessage search(
			IssueReturnMasterInputMessage issueReturnMasterInputMessage) {
		flowId = SEARCH_ISSUE_RETRN;
		this.issueReturnMasterInputMessage = issueReturnMasterInputMessage;
		advanzErpServiceTemplate.execute(this);
		return issueReturnMasterOutputMessage;
	}

	@Override
	public IssueReturnMasterOutputMessage findIssueReturnPagination(
			IssueReturnMasterInputMessage issueReturnMasterInputMessage) {

		flowId = FIND_ISSUE_RETRN_PAGINATION;
		this.issueReturnMasterInputMessage = issueReturnMasterInputMessage;
		advanzErpServiceTemplate.execute(this);
		return issueReturnMasterOutputMessage;
	}

	@Override
	public boolean validateInput() {
		if (FIND_ALL_ISSUE_RETURN.equals(flowId)) {
			return true;
		} else if (CREATE_ISSUE_RETURN.equals(flowId)) {
			return true;
		} else if (UPDATE_ISSUE_RETURN.equals(flowId)) {
			return true;
		}

		else if (DELETE_ISSUE_RETURN.equals(flowId)) {
			return true;
		} else if (FIND_BY_ID_ISSUE_RETRN.equals(flowId)) {
			return true;
		} else if (NEW_ISSUE_RETURN_SERIES_NO.equals(flowId)) {
			return true;
		} else if (SEARCH_ISSUE_RETRN.equals(flowId)) {
			return true;
		} else if (FIND_ISSUE_RETRN_PAGINATION.equals(flowId)) {
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {
		IssueReturnMasterEntity issueReturnMasterEntity = new IssueReturnMasterEntity();
		if (issueReturnMasterInputMessage != null) {

			IssueReturnMasterDTO returnMasterDTO = issueReturnMasterInputMessage
					.getIssueReturnMasterDTO();

			if (returnMasterDTO != null) {

				BeanUtils.copyProperties(returnMasterDTO,
						issueReturnMasterEntity);

				if (returnMasterDTO.getMastersDTO() != null) {
					MastersEntity mastersEntity = new MastersEntity();
					BeanUtils.copyProperties(returnMasterDTO.getMastersDTO(),
							mastersEntity);
					issueReturnMasterEntity.setMastersEntity(mastersEntity);
				}

				if (returnMasterDTO.getBranchDTO() != null) {
					BranchEntity branchEntity = new BranchEntity();
					BeanUtils.copyProperties(returnMasterDTO.getBranchDTO(),
							branchEntity);
					issueReturnMasterEntity.setBranchEntity(branchEntity);
				}

				if (returnMasterDTO.getIssueReturnDetailDTOList() != null) {
					List<IssueReturnDetailDTO> detailDTOList = returnMasterDTO
							.getIssueReturnDetailDTOList();
					List<IssueReturnDetailEntity> detailEntityList = convertIssueReturnDetailDtoToEntity(detailDTOList);
					issueReturnMasterEntity
							.setIssueReturnDetailEntities(detailEntityList);
				}
			}
		}

		if (FIND_ALL_ISSUE_RETURN.equals(flowId)) {
			List<IssueReturnMasterEntity> entityList = iStorageIssueReturnDAO
					.load();
			popUpList(entityList);
		} else if (CREATE_ISSUE_RETURN.equals(flowId)) {
			List<IssueReturnMasterEntity> list = iStorageIssueReturnDAO
					.findDuplicate(
							issueReturnMasterEntity.getIssueReturnNumber(),
							issueReturnMasterEntity.getIssueReturnDate());
			if (list != null && list.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO(
						"1",
						issueReturnMasterEntity.getIssueReturnNumber()
								+ "Same Issue Return Number and Issue Return Date is already exist,it can't be duplicate ");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				issueReturnMasterOutputMessage = new IssueReturnMasterOutputMessage();
				issueReturnMasterOutputMessage.setErrorListDTO(errorListDTO);
			} else {
				StockLedgerDTO stockLedgerDTO = null;
				if (issueReturnMasterEntity.getIssueReturnDetailEntities() != null) {
					List<IssueReturnDetailEntity> entities = issueReturnMasterEntity
							.getIssueReturnDetailEntities();
					for (IssueReturnDetailEntity detailEntity : entities) {
						stockLedgerDTO = new StockLedgerDTO();
						stockLedgerDTO.setQuantity(detailEntity
								.getIssueReturnQuantity());
						stockLedgerDTO.setItemId(detailEntity.getItemEntity()
								.getItemId());
						stockLedgerDTO
								.setTransactionSeries(issueReturnMasterEntity
										.getTransactionSeries());
						stockLedgerDTO
								.setTransactionNumber(issueReturnMasterEntity
										.getIssueReturnNumber());
						stockLedgerDTO.setBranchId(issueReturnMasterEntity
								.getBranchEntity().getBranchId());
						stockLedgerDTO
								.setTransactionDate(issueReturnMasterEntity
										.getIssueReturnDate());
						stockLedgerDTO.setTransactionId(issueReturnMasterEntity
								.getIssueReturnId());

						stockLedgerInputMessage = new StockLedgerInputMessage();
						stockLedgerInputMessage
								.setStockLedgerDTO(stockLedgerDTO);
						stockLedgerService
								.createStockLedger(stockLedgerInputMessage);
						// To update pending quantity in issue detail
						try {
							IssueDetailMasterDTO detailMasterDTO = new IssueDetailMasterDTO();
							detailMasterDTO.setIssueNumber(detailEntity
									.getIssueNumber());
							detailMasterDTO.setItemId(detailEntity
									.getItemEntity().getItemId());
							// To check Issue Type
							List<IssueDetailMasterDTO> detailEntityList = issueMasterService
									.findByIssueNoAndItemId(detailEntity
											.getIssueNumber(), detailEntity
											.getItemEntity().getItemId());
							if (detailEntityList != null
									&& detailEntityList.size() > 0) {
								IssueDetailMasterDTO issueDetailEntity = detailEntityList
										.get(0);
								if ("Returnable"
										.equalsIgnoreCase(issueDetailEntity
												.getIssueType())) {

									// To get return sum of retun quantity for
									// same issue number and item id
									List<Double> l = iStorageIssueReturnDAO
											.countIssueReturnQuantity(
													detailEntity
															.getIssueNumber(),
													detailEntity
															.getItemEntity()
															.getItemId(), null);
									Double quantity = 0.0;
									Double qty = detailEntity
											.getIssueReturnQuantity();
									if (l != null && l.size() > 0) {
										quantity = l.get(0);
										if (quantity != null) {
											qty = qty + quantity;
										}

									}
									detailMasterDTO.setPendingQuantity(qty);
									issueMasterService
											.updateIssueDetail(detailMasterDTO);

								}
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						//
					}

				}

				iStorageIssueReturnDAO.create(issueReturnMasterEntity);
				issueReturnMasterOutputMessage = new IssueReturnMasterOutputMessage();
				issueReturnMasterOutputMessage.setErrorListDTO(null);
			}
		} else if (UPDATE_ISSUE_RETURN.equals(flowId)) {
			List<IssueReturnMasterEntity> list = iStorageIssueReturnDAO
					.findDuplicate(
							issueReturnMasterEntity.getIssueReturnNumber(),
							issueReturnMasterEntity.getIssueReturnDate());

			if (list != null
					&& list.size() > 0
					&& !list.get(0)
							.getIssueReturnAutoId()
							.equals(issueReturnMasterEntity
									.getIssueReturnAutoId())) {
				ErrorDTO errorDTO = new ErrorDTO("1",
						"Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				issueReturnMasterOutputMessage = new IssueReturnMasterOutputMessage();
				issueReturnMasterOutputMessage.setErrorListDTO(errorListDTO);
			} else {
				if (issueReturnMasterEntity.getIssueReturnNumber() != null
						&& issueReturnMasterEntity.getIssueReturnNumber()
								.length() > 0) {
					StockLedgerInputMessage stockInputmessage = new StockLedgerInputMessage();
					StockLedgerDTO stockLedgerDTO = new StockLedgerDTO();
					stockLedgerDTO.setTransactionNumber(issueReturnMasterEntity
							.getIssueReturnNumber());
					stockInputmessage.setStockLedgerDTO(stockLedgerDTO);

					StockLedgerOutMessage stockLedgerOutMessage = stockLedgerService
							.findStockLedgerByTransactionId(stockInputmessage);

					ArrayList<StockLedgerDTO> stockLedgerList = (ArrayList<StockLedgerDTO>) stockLedgerOutMessage
							.getStockLedgerDTOList();
					for (int i = 0; i < stockLedgerList.size(); i++) {

						stockLedgerDTO = new StockLedgerDTO();
						stockLedgerDTO = stockLedgerList.get(i);
						stockInputmessage = new StockLedgerInputMessage();
						stockInputmessage.setStockLedgerDTO(stockLedgerDTO);
						stockLedgerService.deleteStockLedger(stockInputmessage);
					}
				}

				StockLedgerDTO stockLedgerDTO = null;

				if (issueReturnMasterEntity.getIssueReturnDetailEntities() != null) {
					List<IssueReturnDetailEntity> entities = issueReturnMasterEntity
							.getIssueReturnDetailEntities();
					for (IssueReturnDetailEntity detailEntity : entities) {

						stockLedgerDTO = new StockLedgerDTO();
						stockLedgerDTO.setQuantity(detailEntity
								.getIssueReturnQuantity());
						stockLedgerDTO.setItemId(detailEntity.getItemEntity()
								.getItemId());
						stockLedgerDTO
								.setTransactionSeries(issueReturnMasterEntity
										.getTransactionSeries());
						stockLedgerDTO
								.setTransactionNumber(issueReturnMasterEntity
										.getIssueReturnNumber());
						stockLedgerDTO.setBranchId(issueReturnMasterEntity
								.getBranchEntity().getBranchId());
						stockLedgerDTO
								.setTransactionDate(issueReturnMasterEntity
										.getIssueReturnDate());
						stockLedgerDTO.setTransactionId(issueReturnMasterEntity
								.getIssueReturnId());

						stockLedgerInputMessage = new StockLedgerInputMessage();
						stockLedgerInputMessage
								.setStockLedgerDTO(stockLedgerDTO);
						stockLedgerService
								.createStockLedger(stockLedgerInputMessage);
						// To update pending quantity in issue detail
						try {
							IssueDetailMasterDTO detailMasterDTO = new IssueDetailMasterDTO();
							detailMasterDTO.setIssueNumber(detailEntity
									.getIssueNumber());
							detailMasterDTO.setItemId(detailEntity
									.getItemEntity().getItemId());
							// To check Issue Type
							List<IssueDetailMasterDTO> detailEntityList = issueMasterService
									.findByIssueNoAndItemId(detailEntity
											.getIssueNumber(), detailEntity
											.getItemEntity().getItemId());
							if (detailEntityList != null
									&& detailEntityList.size() > 0) {
								IssueDetailMasterDTO issueDetailEntity = detailEntityList
										.get(0);
								if ("Returnable"
										.equalsIgnoreCase(issueDetailEntity
												.getIssueType())) {

									// To get return sum of return quantity for
									// same issue number and item id
									List<Double> l = iStorageIssueReturnDAO
											.countIssueReturnQuantity(
													detailEntity
															.getIssueNumber(),
													detailEntity
															.getItemEntity()
															.getItemId(),
													detailEntity
															.getIssueReturnNumber());
									Double qty = detailEntity
											.getIssueReturnQuantity();
									Double quantity = 0.0;
									if (l != null && l.size() > 0) {
										quantity = l.get(0);
										if (quantity != null) {
											qty = qty + quantity;
										}
									}
									detailMasterDTO.setPendingQuantity(qty);
									issueMasterService
											.updateIssueDetail(detailMasterDTO);

								}
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						//

					}

				}
				issueReturnMasterOutputMessage = new IssueReturnMasterOutputMessage();
				issueReturnMasterOutputMessage.setErrorListDTO(null);
				iStorageIssueReturnDAO.update(issueReturnMasterEntity);
			}
		}

		else if (DELETE_ISSUE_RETURN.equals(flowId)) {
			try {
				IssueDetailMasterDTO detailMasterDTO = new IssueDetailMasterDTO();
				Double qty = 0.0;
				List list = iStorageIssueReturnDAO
						.getIssueDeatilList(issueReturnMasterEntity
								.getIssueReturnNumber());
				for (int i = 0; i < list.size(); i++) {
					IssueReturnDetailEntity ird = (IssueReturnDetailEntity) list
							.get(i);
					if (qty != null) {
						qty = ird.getIssueReturnQuantity();
						detailMasterDTO.setPendingQuantity(0 - qty);
					}
					detailMasterDTO.setOperationFlag("Delete");
					detailMasterDTO.setIssueNumber(ird.getIssueNumber());
					detailMasterDTO.setItemId(ird.getItemEntity().getItemId());
					issueMasterService.updateIssueDetail(detailMasterDTO);
				}
			} catch (Exception e) {
			}

			if (issueReturnMasterEntity.getIssueReturnNumber() != null
					&& issueReturnMasterEntity.getIssueReturnNumber().length() > 0) {
				StockLedgerInputMessage stockInputmessage = new StockLedgerInputMessage();
				StockLedgerDTO stockLedgerDTO = new StockLedgerDTO();
				stockLedgerDTO.setTransactionNumber(issueReturnMasterEntity
						.getIssueReturnNumber());
				stockInputmessage.setStockLedgerDTO(stockLedgerDTO);

				StockLedgerOutMessage stockLedgerOutMessage = stockLedgerService
						.findStockLedgerByTransactionId(stockInputmessage);
				ArrayList<StockLedgerDTO> stockLedgerList = (ArrayList<StockLedgerDTO>) stockLedgerOutMessage
						.getStockLedgerDTOList();
				for (int i = 0; i < stockLedgerList.size(); i++) {
					stockLedgerDTO = new StockLedgerDTO();
					stockLedgerDTO = stockLedgerList.get(i);
					stockInputmessage = new StockLedgerInputMessage();
					stockInputmessage.setStockLedgerDTO(stockLedgerDTO);
					stockLedgerService.deleteStockLedger(stockInputmessage);
				}
			}

			iStorageIssueReturnDAO.delete(issueReturnMasterEntity);
		} else if (SEARCH_ISSUE_RETRN.equals(flowId)) {
			List<IssueReturnMasterEntity> entityList = iStorageIssueReturnDAO
					.search(issueReturnMasterInputMessage
							.getIssueReturnMasterDTO().getIssueReturnNumber(),
							issueReturnMasterInputMessage
									.getIssueReturnMasterDTO().getFromDate(),
							issueReturnMasterInputMessage
									.getIssueReturnMasterDTO().getToDate(),
							issueReturnMasterInputMessage
									.getIssueReturnMasterDTO().getMastersDTO()
									.getDeptId());

			popUpList(entityList);
		} else if (FIND_BY_ID_ISSUE_RETRN.equals(flowId)) {
			List<IssueReturnMasterEntity> entityList = iStorageIssueReturnDAO
					.findById(issueReturnMasterEntity.getIssueReturnAutoId());
			popUpList(entityList);
		} else if (NEW_ISSUE_RETURN_SERIES_NO.equals(flowId)) {

			Integer seriesNo = 0;
			Timestamp date = zoneService.getFirstDayOfFinYear();
			List list = iStorageIssueReturnDAO
					.getNewSeriesNo(issueReturnMasterEntity.getFinYear());
			if (list != null && list.size() > 0) {
				Object[] obj = (Object[]) list.get(0);

				Number n = (Number) obj[0];
				if (n != null)
					seriesNo = n.intValue();
				if (obj[1] != null && obj[1] != "")
					date = (Timestamp) obj[1];
			}
			seriesNo++;

			issueReturnMasterOutputMessage = new IssueReturnMasterOutputMessage();
			issueReturnMasterOutputMessage.setIssueReturnSeriesNo(seriesNo);
			issueReturnMasterOutputMessage.setIssueReturnSeriesDate(date);
		} else if (FIND_ISSUE_RETRN_PAGINATION.equals(flowId)) {
			List<IssueReturnMasterEntity> entityList = iStorageIssueReturnDAO
					.findIssueReturnPagination(issueReturnMasterInputMessage
							.getNext());
			popUpList(entityList);
		}

	}

	@Override
	public void formatOutput() {
		if (FIND_ALL_ISSUE_RETURN.equals(flowId)) {
		} else if (CREATE_ISSUE_RETURN.equals(flowId)) {
		} else if (UPDATE_ISSUE_RETURN.equals(flowId)) {
		} else if (DELETE_ISSUE_RETURN.equals(flowId)) {
		} else if (FIND_BY_ID_ISSUE_RETRN.equals(flowId)) {
		} else if (NEW_ISSUE_RETURN_SERIES_NO.equals(flowId)) {
		} else if (SEARCH_ISSUE_RETRN.equals(flowId)) {
		}
	}

	public void popUpList(
			List<IssueReturnMasterEntity> issueReturnMasterEntityList) {
		List<IssueReturnMasterDTO> issueReturnMasterDTOList = new ArrayList<IssueReturnMasterDTO>();
		IssueReturnMasterDTO issueReturnMasterDTO = null;
		for (IssueReturnMasterEntity issueReturnMasterEntity : issueReturnMasterEntityList) {
			issueReturnMasterDTO = new IssueReturnMasterDTO();
			BeanUtils.copyProperties(issueReturnMasterEntity,
					issueReturnMasterDTO);
			if (issueReturnMasterEntity.getBranchEntity() != null) {
				BranchDTO branchDTO = new BranchDTO();
				BeanUtils.copyProperties(
						issueReturnMasterEntity.getBranchEntity(), branchDTO);
				issueReturnMasterDTO.setBranchDTO(branchDTO);
			}

			if (issueReturnMasterEntity.getMastersEntity() != null) {
				MastersDTO mastersDTO = new MastersDTO();
				BeanUtils.copyProperties(
						issueReturnMasterEntity.getMastersEntity(), mastersDTO);
				issueReturnMasterDTO.setMastersDTO(mastersDTO);
			}

			List<IssueReturnDetailEntity> issueReturnDetailEntityList = new ArrayList<IssueReturnDetailEntity>();
			IssueReturnDetailDTO issueReturnDetailDTO = null;
			List<IssueReturnDetailDTO> issueReturnDetailDTOList = new ArrayList<IssueReturnDetailDTO>();
			if (issueReturnMasterEntity.getIssueReturnDetailEntities() != null) {
				issueReturnDetailEntityList = issueReturnMasterEntity
						.getIssueReturnDetailEntities();

				for (IssueReturnDetailEntity issueReturnDetailEntity : issueReturnDetailEntityList) {
					issueReturnDetailDTO = new IssueReturnDetailDTO();
					BeanUtils.copyProperties(issueReturnDetailEntity,
							issueReturnDetailDTO);
					if (issueReturnDetailEntity.getItemEntity() != null) {
						issueReturnDetailDTO.setItemId(issueReturnDetailEntity
								.getItemEntity().getItemId());
						issueReturnDetailDTO
								.setItemName(issueReturnDetailEntity
										.getItemEntity().getItemName());
						issueReturnDetailDTO
								.setMeasurmentUnitId(issueReturnDetailEntity
										.getItemEntity().getMasterUnitEntity()
										.getMastersId());
						issueReturnDetailDTO
								.setMeasurementUnitName(issueReturnDetailEntity
										.getItemEntity().getMasterUnitEntity()
										.getName());
					}

					issueReturnDetailDTOList.add(issueReturnDetailDTO);
				}
				issueReturnMasterDTO
						.setIssueReturnDetailDTOList(issueReturnDetailDTOList);
			}
			issueReturnMasterDTOList.add(issueReturnMasterDTO);
		}
		issueReturnMasterOutputMessage = new IssueReturnMasterOutputMessage();
		issueReturnMasterOutputMessage
				.setIssueReturnMasterDTOList(issueReturnMasterDTOList);
	}

	public List<IssueReturnDetailEntity> convertIssueReturnDetailDtoToEntity(
			List<IssueReturnDetailDTO> detailDTOList) {
		IssueReturnDetailEntity detailEntity = null;
		List<IssueReturnDetailEntity> detailEntityList = new ArrayList<IssueReturnDetailEntity>();
		for (IssueReturnDetailDTO issueReturnDetailDTO : detailDTOList) {
			detailEntity = new IssueReturnDetailEntity();

			BeanUtils.copyProperties(issueReturnDetailDTO, detailEntity);
			if (issueReturnDetailDTO != null) {
				ItemEntity itemEntity = new ItemEntity();
				itemEntity.setItemId(issueReturnDetailDTO.getItemId());
				detailEntity.setItemEntity(itemEntity);
			}

			detailEntityList.add(detailEntity);
		}
		return detailEntityList;
	}

	@Override
	public List<Double> findReturnQuantity(String issueNumber, Integer itemId) {
		List<Double> l = iStorageIssueReturnDAO.findReturnQuantity(issueNumber,
				itemId);
		return l;
	}

	@Override
	public List getIssueList(IssueReturnMasterDTO dto1) {

		List l = iStorageIssueReturnDAO.getIssueList(dto1.getNext(),
				dto1.getOperationFlage());
		List<IssueMasterDTO> list = getConvertedList(l);
		return list;
	}

	@Override
	public List searchIssueList(IssueReturnMasterDTO dto1) {
		String itemName = null;
		String itemCode = null;
		String operationFlage = null;
		String issueNo = null;
		String raisedBy = null;
		String loanType = null;
		if (dto1.getItemName() != null) {
			itemName = dto1.getItemName();
		}
		if (dto1.getItemCode() != null) {
			itemCode = dto1.getItemCode();
		}
		if (dto1.getOperationFlage() != null) {
			operationFlage = dto1.getOperationFlage();
		}
		if (dto1.getIssueNumber() != null) {
			issueNo = dto1.getIssueNumber();
		}

		if (dto1.getRisedBy() != null) {
			raisedBy = dto1.getRisedBy();
		}

		if (dto1.getLoanType() != null) {
			loanType = dto1.getLoanType();
		}
		List l = iStorageIssueReturnDAO.searchIssueList(itemName, itemCode,
				issueNo, operationFlage, raisedBy, loanType);
		List<IssueMasterDTO> list = getConvertedList(l);
		return list;
	}

	public List<IssueMasterDTO> getConvertedList(List l) {

		List<IssueMasterDTO> list = new ArrayList<IssueMasterDTO>();
		for (int i = 0; i < l.size(); i++) {
			IssueMasterDTO dto = new IssueMasterDTO();
			Object[] obj = (Object[]) l.get(i);
			String issueNumber = (String) obj[0];
			Timestamp issueDate = (Timestamp) obj[1];
			java.sql.Date date = new java.sql.Date(issueDate.getTime());
			String issuedBy = (String) obj[2];
			String departmentName = (String) obj[3];
			String itemName1 = (String) obj[4];
			String itemCode1 = (String) obj[5];
			int itemId = Integer.parseInt(obj[6].toString());

			Double issueQuantity = 0.0;
			Double pendingQuantity = 0.0;
			try {

				pendingQuantity = Double.parseDouble(obj[7].toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			issueQuantity = Double.parseDouble(obj[8].toString());
			String loanType = (String) obj[9];
			String riseBy = (String) obj[10];
			dto.setIssueNumber(issueNumber);
			dto.setIssuedBy(issuedBy);
			dto.setIssueDate(date);
			dto.setDepartmentName(departmentName);
			dto.setItemName(itemName1);
			dto.setItemCode(itemCode1);
			dto.setItemId(itemId);
			dto.setIssueQuantity(issueQuantity);
			dto.setPendingQuantity(pendingQuantity);
			dto.setLoanType(loanType);
			dto.setRaisedBy(riseBy);
			list.add(dto);
		}
		return list;
	}

	@Override
	public Boolean getIssueRetunDeatilByIssueNumberAndItemId(
			String issueNumber, Integer itemId) {
		Boolean flag = false;
		List l = null;
		try {
			l = iStorageIssueReturnDAO
					.getIssueRetunDeatilByIssueNumberAndItemId(issueNumber,
							itemId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (l != null && l.size() > 0) {
			flag = true;
		}
		return flag;
	}

}
