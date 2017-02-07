package com.advanz.erp.masters.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.entity.jpa.GrnMasterEntity;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.ItemGroupFlagEntity;
import com.advanz.erp.masters.entity.jpa.PartyEntity;
import com.advanz.erp.masters.entity.jpa.PurchaseOrderDetailEntity;
import com.advanz.erp.masters.entity.jpa.PurchaseOrderMasterEntity;
import com.advanz.erp.masters.entity.jpa.TransporterEntity;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.PurchaseOrderDetailDTO;
import com.advanz.erp.masters.model.PurchaseOrderMasterDTO;
import com.advanz.erp.masters.model.TransporterDTO;
import com.advanz.erp.masters.model.msg.PurchaseOrderMasterInputMessage;
import com.advanz.erp.masters.model.msg.PurchaseOrderMasterOutputMessage;
import com.advanz.erp.masters.service.business.IPurchaseOrderMasterService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageGrnMasterDAO;
import com.advanz.erp.masters.storage.IStorageIndentDAO;
import com.advanz.erp.masters.storage.IStoragePurchaseOrderMasterDAO;

public class PurchaseOrderMasterServiceImpl implements
		IPurchaseOrderMasterService {

	public static final String CREATE_PURCHASE_ORDER_MASTER = "CreatePurchaseOrderMaster";
	public static final String UPDATE_PURCHASE_ORDER_MASTER = "UpdatePurchaseOrderMaster";
	public static final String DELETE_PURCHASE_ORDER_MASTER = "DeletePurchaseOrderMaster";
	public static final String PURCHASE_ORDER_UPDATE_FOR_GRN = "PurchaseOrderUpdateForGrn";
	public static final String FIND_PURCHASE_ORDER_BY_PO_NUMBER = "FindPurchaseOrderByPoNumber";
	public static final String FIND_PURCHASE_ORDER_MASTER_BY_ID = "FindPurchaseOrderMasterById";
	public static final String FIND_ALL_PURCHASE_ORDER_MASTER = "FindAllPurchaseOrderMasters";
	public static final String SEARCH_PURCHASE_ORDER_MASTER = "SearchPurchaseOrderMasters";
	public static final String NEW_PURCHASE_ORDER_MASTER_SERIES_NO = "NewPurchaseOrderMastersSeriesNo";
	public static final String FIND_PURCHASE_ORDER_BY_ITEM_AND_PO_ID = "findPurchaseOrderByItemAndPoId";

	public static final String FIND_PURCHASE_ORDER_BY_SUPPLIER_ID = "FindPurchaseOrderBySupplierId";
	public static final String FIND_PURCHASE_ORDER_BY_ITEM_AND_PO_NUMBER = "findPurchaseOrderByItemAndPoNumber";
	public static final String FIND_PURCHASE_ORDER_WITH_PAGINATION = "findPurchaseOrderWithPagination";

	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStoragePurchaseOrderMasterDAO storagePurchaseOrderMasterDAO;

	@Autowired
	public IStorageGrnMasterDAO storageGrnMasterDAO;

	@Autowired
	public IStorageIndentDAO indentDAO;

	@Autowired
	public IZoneService zoneService;

	public PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage;

	public PurchaseOrderMasterOutputMessage purchaseOrderMasterOutputMessage;

	@Override
	public PurchaseOrderMasterOutputMessage createPurchaseOrderMaster(
			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage) {

		flowId = CREATE_PURCHASE_ORDER_MASTER;
		// assign the message to the class level variable.
		this.purchaseOrderMasterInputMessage = purchaseOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return purchaseOrderMasterOutputMessage;
	}

	@Override
	public PurchaseOrderMasterOutputMessage updatePurchaseOrderMaster(
			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage) {

		flowId = UPDATE_PURCHASE_ORDER_MASTER;
		// assign the message to the class level variable.
		this.purchaseOrderMasterInputMessage = purchaseOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return purchaseOrderMasterOutputMessage;
	}

	@Override
	public PurchaseOrderMasterOutputMessage deletePurchaseOrderMaster(
			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage) {
		flowId = DELETE_PURCHASE_ORDER_MASTER;
		// assign the message to the class level variable.
		this.purchaseOrderMasterInputMessage = purchaseOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return purchaseOrderMasterOutputMessage;

	}

	@Override
	public PurchaseOrderMasterOutputMessage findPurchaseOrderMasterById(
			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage) {
		flowId = FIND_PURCHASE_ORDER_MASTER_BY_ID;
		// assign the message to the class level variable.
		this.purchaseOrderMasterInputMessage = purchaseOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return purchaseOrderMasterOutputMessage;

	}

	@Override
	public PurchaseOrderMasterOutputMessage findAllPurchaseOrderMasters() {
		flowId = FIND_ALL_PURCHASE_ORDER_MASTER;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return purchaseOrderMasterOutputMessage;
	}

	@Override
	public PurchaseOrderMasterOutputMessage search(
			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage) {
		flowId = SEARCH_PURCHASE_ORDER_MASTER;
		this.purchaseOrderMasterInputMessage = purchaseOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return purchaseOrderMasterOutputMessage;

	}

	@Override
	public PurchaseOrderMasterOutputMessage getNewPurchaseOrderSeriesNo(
			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage) {
		flowId = NEW_PURCHASE_ORDER_MASTER_SERIES_NO;
		this.purchaseOrderMasterInputMessage = purchaseOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return purchaseOrderMasterOutputMessage;
	}

	@Override
	public PurchaseOrderMasterOutputMessage FindPurchaseOrderByItemAndPoId(
			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage) {
		flowId = FIND_PURCHASE_ORDER_BY_ITEM_AND_PO_ID;
		this.purchaseOrderMasterInputMessage = purchaseOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return purchaseOrderMasterOutputMessage;
	}

	@Override
	public PurchaseOrderMasterOutputMessage PurchaseOrderUpdateForGrn(
			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage) {
		flowId = PURCHASE_ORDER_UPDATE_FOR_GRN;
		this.purchaseOrderMasterInputMessage = purchaseOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return purchaseOrderMasterOutputMessage;
	}

	@Override
	public PurchaseOrderMasterOutputMessage findPurchaseOrderMasterByPoNumber(
			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage) {
		flowId = FIND_PURCHASE_ORDER_BY_PO_NUMBER;
		this.purchaseOrderMasterInputMessage = purchaseOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return purchaseOrderMasterOutputMessage;
	}

	@Override
	public PurchaseOrderMasterOutputMessage findPurchaseOrderMasterBySupplierId(
			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage) {
		flowId = FIND_PURCHASE_ORDER_BY_SUPPLIER_ID;
		this.purchaseOrderMasterInputMessage = purchaseOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return purchaseOrderMasterOutputMessage;
	}

	@Override
	public PurchaseOrderMasterOutputMessage FindPurchaseOrderByItemAndPoNumber(
			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage) {
		flowId = FIND_PURCHASE_ORDER_BY_ITEM_AND_PO_NUMBER;
		this.purchaseOrderMasterInputMessage = purchaseOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return purchaseOrderMasterOutputMessage;
	}

	@Override
	public PurchaseOrderMasterOutputMessage findPurchaseOrderForPagination(
			PurchaseOrderMasterInputMessage purchaseOrderMasterInputMessage) {
		flowId = FIND_PURCHASE_ORDER_WITH_PAGINATION;
		this.purchaseOrderMasterInputMessage = purchaseOrderMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return purchaseOrderMasterOutputMessage;
	}

	@Override
	public boolean validateInput() {

		if (CREATE_PURCHASE_ORDER_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_PURCHASE_ORDER_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_PURCHASE_ORDER_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_PURCHASE_ORDER_MASTER_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_PURCHASE_ORDER_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_PURCHASE_ORDER_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (NEW_PURCHASE_ORDER_MASTER_SERIES_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_PURCHASE_ORDER_BY_ITEM_AND_PO_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (PURCHASE_ORDER_UPDATE_FOR_GRN.equals(flowId)) {
			return true;
		} else if (FIND_PURCHASE_ORDER_BY_PO_NUMBER.equals(flowId)) {
			return true;
		} else if (FIND_PURCHASE_ORDER_BY_SUPPLIER_ID.equals(flowId)) {
			return true;
		} else if (FIND_PURCHASE_ORDER_BY_ITEM_AND_PO_NUMBER.equals(flowId)) {
			return true;
		} else if (FIND_PURCHASE_ORDER_WITH_PAGINATION.equals(flowId)) {
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {

		PurchaseOrderMasterEntity purchaseOrderMasterEntity = new PurchaseOrderMasterEntity();
		purchaseOrderMasterOutputMessage = new PurchaseOrderMasterOutputMessage();
		PurchaseOrderMasterDTO purchaseOrderMasterDTO = null;
		if (purchaseOrderMasterInputMessage != null) {
			purchaseOrderMasterDTO = purchaseOrderMasterInputMessage
					.getPurchaseOrderMasterDTO();
			if (purchaseOrderMasterDTO != null) {
				BeanUtils.copyProperties(purchaseOrderMasterDTO,
						purchaseOrderMasterEntity);
				PartyDTO partyDTO = purchaseOrderMasterDTO.getPartyDTO();
				if (partyDTO != null) {
					PartyEntity partyEntity = new PartyEntity();
					copyObject(partyDTO, partyEntity);
					purchaseOrderMasterEntity.setPartyEntity(partyEntity);
				}

				BranchDTO branchDTO = purchaseOrderMasterDTO.getBranchDTO();
				if (branchDTO != null) {
					BranchEntity branchEntity = new BranchEntity();
					copyObject(branchDTO, branchEntity);
					purchaseOrderMasterEntity.setBranchEntity(branchEntity);
				}

				ItemGroupFlagDTO itemGroupFlagDTO = purchaseOrderMasterDTO
						.getItemGroupFlagDTO();
				if (itemGroupFlagDTO != null) {
					ItemGroupFlagEntity itemGroupFlagEntity = new ItemGroupFlagEntity();
					copyObject(itemGroupFlagDTO, itemGroupFlagEntity);
					purchaseOrderMasterEntity
							.setItemGroupFlagEntity(itemGroupFlagEntity);
				}

				TransporterDTO transporterDTO = purchaseOrderMasterDTO
						.getTransportDTO();
				if (transporterDTO != null) {
					TransporterEntity transporterEntity = new TransporterEntity();
					copyObject(transporterDTO, transporterEntity);
					purchaseOrderMasterEntity
							.setTransportEnttity(transporterEntity);
				}

				List<PurchaseOrderDetailDTO> purchaseOrderDetailDTO = purchaseOrderMasterDTO
						.getPurchaseOrderDetailDTOList();

				if (purchaseOrderDetailDTO != null
						&& purchaseOrderDetailDTO.size() > 0) {
					PurchaseOrderDetailDTO detailDTO = purchaseOrderDetailDTO
							.get(0);

					List<PurchaseOrderDetailEntity> purchaseOrderDetailEntity = convertPurchaseOrderDetailDtoTOEntity(
							purchaseOrderDetailDTO,
							purchaseOrderMasterDTO.getPurchaseOrderNumber(),
							purchaseOrderMasterDTO.getTransactionSeries(),
							purchaseOrderMasterDTO);
					purchaseOrderMasterEntity
							.setPurchaseOrderDetailEntity(purchaseOrderDetailEntity);
				}
			}
		}

		if (CREATE_PURCHASE_ORDER_MASTER.equals(flowId)) {

			List<PurchaseOrderMasterEntity> list = storagePurchaseOrderMasterDAO
					.findByPurchaseOrderNumber(purchaseOrderMasterEntity
							.getPurchaseOrderNumber());
			// if (purchaseOrderMasterEntity.getPurchaseOrderDetailEntity() !=
			// null &&
			// purchaseOrderMasterEntity.getPurchaseOrderDetailEntity().size() >
			// 0) {

			if (list != null && list.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO(
						"1",
						purchaseOrderMasterEntity.getPurchaseOrderNumber()
								+ " Purchase Order Number is already exist,it can't be duplicate");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				purchaseOrderMasterOutputMessage.setErrorListDTO(errorListDTO);
			} else {
				// To update pending Quantity in purchase order
				for (int i = 0; i < purchaseOrderMasterEntity
						.getPurchaseOrderDetailEntity().size(); i++) {
					List<PurchaseOrderDetailEntity> list2 = purchaseOrderMasterEntity
							.getPurchaseOrderDetailEntity();
					for (int j = 0; j < list2.size(); j++) {
						Double balance = list2.get(j).getItemQuantity();
						PurchaseOrderDetailEntity detailEntity = list2.get(j);
						detailEntity.setPendingQuantity(balance);
					}
				}
				purchaseOrderMasterOutputMessage.setErrorListDTO(null);
				storagePurchaseOrderMasterDAO.create(purchaseOrderMasterEntity);
				// Update Pending Quantity In Indent
				PurchaseOrderMasterDTO pMasterDTO = purchaseOrderMasterInputMessage
						.getPurchaseOrderMasterDTO();
				List<PurchaseOrderDetailDTO> pdetailList = pMasterDTO
						.getPurchaseOrderDetailDTOList();
				for (int j = 0; j < pdetailList.size(); j++) {
					Double balance = pdetailList.get(j).getItemQuantity();
					PurchaseOrderDetailDTO detailDTO = pdetailList.get(j);
					detailDTO.setPendingQuantity(balance);
					try {
						indentDAO.updatePendingQtyInIndentDetail(detailDTO
								.getItemDTO().getItemId(), detailDTO
								.getIndentNumber(),
								detailDTO.getItemQuantity(), null);
					} catch (Exception e) {
					}
				}

			}
		} else if (PURCHASE_ORDER_UPDATE_FOR_GRN.equals(flowId)) {

			storagePurchaseOrderMasterDAO.update(purchaseOrderMasterEntity);
		}

		else if (FIND_PURCHASE_ORDER_BY_PO_NUMBER.equals(flowId)) {
			List<PurchaseOrderMasterEntity> list2Entities = storagePurchaseOrderMasterDAO
					.findByPurchaseOrderNumber(purchaseOrderMasterEntity
							.getPurchaseOrderNumber());
			popUpList(list2Entities);
		}

		else if (UPDATE_PURCHASE_ORDER_MASTER.equals(flowId)) {

			List<PurchaseOrderDetailEntity> list2 = purchaseOrderMasterEntity
					.getPurchaseOrderDetailEntity();
			for (int j = 0; j < list2.size(); j++) {
				PurchaseOrderDetailEntity detailEntity = null;
				Double balance = 0.0;
				List<PurchaseOrderDetailEntity> ListDtl = storagePurchaseOrderMasterDAO
						.findByItemIdAndPo(list2.get(j).getItemEntity()
								.getItemId(), list2.get(j).getPoAutoId());

				double pn = 0.0;
				double dbQty = 0.0;
				double listItemQty = 0.0;
				try {
					pn = list2.get(j).getPendingQuantity().doubleValue();
					dbQty = ListDtl.get(0).getQuantityItem().doubleValue();
					listItemQty = list2.get(j).getItemQuantity();
				} catch (Exception e) {
				}

				if (pn == dbQty) {
					balance = list2.get(j).getItemQuantity();
					detailEntity = list2.get(j);
					detailEntity.setPendingQuantity(balance);
				} else {
					balance = (listItemQty - dbQty) + pn;
					detailEntity = list2.get(j);
					detailEntity.setPendingQuantity(balance);
				}
				list2.set(j, detailEntity);
			}

			purchaseOrderMasterEntity.setPurchaseOrderDetailEntity(list2);
			storagePurchaseOrderMasterDAO.update(purchaseOrderMasterEntity);
			// To update pending quantity in indent detail........
			PurchaseOrderMasterDTO pMasterDTO = purchaseOrderMasterInputMessage
					.getPurchaseOrderMasterDTO();
			List<PurchaseOrderDetailDTO> detailList = pMasterDTO
					.getPurchaseOrderDetailDTOList();
			for (int i = 0; i < detailList.size(); i++) {
				PurchaseOrderDetailDTO detailEntity = detailList.get(i);
				try {
					List<Double> l = storagePurchaseOrderMasterDAO
							.findPurchaseQtyByItemIdAndIndentNumber(
									detailEntity.getItemDTO().getItemId(),
									detailEntity.getIndentNumber());
					Double qty = 0.0;
					if (l != null && l.size() > 0) {
						qty = l.get(0);
					}

					indentDAO.updatePendingQtyInIndentDetail(detailEntity
							.getItemDTO().getItemId(), detailEntity
							.getIndentNumber(), qty, "update");
				} catch (Exception e) {
				}
			}

		} else if (DELETE_PURCHASE_ORDER_MASTER.equals(flowId)) {
			boolean flag = false;
			String error = null;
			purchaseOrderMasterOutputMessage = new PurchaseOrderMasterOutputMessage();
			List<GrnMasterEntity> list = storageGrnMasterDAO
					.findByPoId(purchaseOrderMasterEntity.getPoAutoId());
			if ((list != null && list.size() > 0)) {
				error = "Purchase Order use in GRN Master. You can not delete.";
				flag = true;
			}
			if (flag && error != null) {
				ErrorDTO errorDTO = new ErrorDTO("1", error);
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				purchaseOrderMasterOutputMessage.setErrorListDTO(errorListDTO);
			} else {
				if (purchaseOrderMasterInputMessage.isDeletedFlag()) {
					purchaseOrderMasterOutputMessage.setErrorListDTO(null);

					// To update pending quantity in indent detail........
					// PurchaseOrderMasterDTO pMasterDTO =
					// purchaseOrderMasterInputMessage.getPurchaseOrderMasterDTO();
					// List<PurchaseOrderDetailDTO>
					// detailList=pMasterDTO.getPurchaseOrderDetailDTOList();
					try {
						List<PurchaseOrderMasterEntity> entityList = storagePurchaseOrderMasterDAO
								.findById(purchaseOrderMasterEntity
										.getPoAutoId());
						PurchaseOrderMasterEntity me = null;
						List<PurchaseOrderDetailEntity> detailEntities = null;
						if (entityList != null && entityList.size() > 0) {
							me = entityList.get(0);
							detailEntities = me.getPurchaseOrderDetailEntity();

							for (int i = 0; i < detailEntities.size(); i++) {
								PurchaseOrderDetailEntity detailEntity = detailEntities
										.get(i);
								try {
									indentDAO.updatePendingQtyInIndentDetail(
											detailEntity.getItemEntity()
													.getItemId(), detailEntity
													.getIndentNumber(),
											detailEntity.getItemQuantity(),
											"delete");
								} catch (Exception e) {
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					storagePurchaseOrderMasterDAO
							.delete(purchaseOrderMasterEntity);
				}
			}
		}

		else if (FIND_PURCHASE_ORDER_MASTER_BY_ID.equals(flowId)) {
			List<PurchaseOrderMasterEntity> list = storagePurchaseOrderMasterDAO
					.findById(purchaseOrderMasterEntity.getPoAutoId());

			popUpList(list);
		}

		else if (FIND_PURCHASE_ORDER_BY_ITEM_AND_PO_ID.equals(flowId)) {
			List<PurchaseOrderDetailEntity> list = storagePurchaseOrderMasterDAO
					.findByItemIdAndPo(purchaseOrderMasterInputMessage
							.getPurchaseOrderMasterDTO()
							.getPurchaseOrderDetailDTOList().get(0)
							.getItemDTO().getItemId(),
							purchaseOrderMasterInputMessage
									.getPurchaseOrderMasterDTO().getPoAutoId());

			List<PurchaseOrderDetailDTO> detailDTOs = convertPurchaseOrderDetailEntityToDto(list);
			purchaseOrderMasterOutputMessage
					.setPurchaseOrderDetailDTOList(detailDTOs);
		} else if (FIND_PURCHASE_ORDER_BY_ITEM_AND_PO_NUMBER.equals(flowId)) {
			List<PurchaseOrderDetailEntity> list = storagePurchaseOrderMasterDAO
					.findByItemIdAndPoNumber(purchaseOrderMasterInputMessage
							.getItemId(), purchaseOrderMasterInputMessage
							.getPurchaseOrderMasterDTO()
							.getPurchaseOrderNumber());

			List<PurchaseOrderDetailDTO> detailDTOs = convertPurchaseOrderDetailEntityToDto(list);
			purchaseOrderMasterOutputMessage
					.setPurchaseOrderDetailDTOList(detailDTOs);
		}

		else if (FIND_ALL_PURCHASE_ORDER_MASTER.equals(flowId)) {
			List<PurchaseOrderMasterEntity> list = storagePurchaseOrderMasterDAO
					.load();
			popUpList(list);
		} else if (FIND_PURCHASE_ORDER_WITH_PAGINATION.equals(flowId)) {
			List<PurchaseOrderMasterEntity> list = storagePurchaseOrderMasterDAO
					.FindPurchaseOrderPagination(purchaseOrderMasterInputMessage
							.getNext());
			popUpList(list);
		}

		else if (SEARCH_PURCHASE_ORDER_MASTER.equals(flowId)) {
			String date = null;
			if (purchaseOrderMasterEntity.getPurchaseOrderDate() != null) {
				SimpleDateFormat obj = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				date = obj.format(purchaseOrderMasterEntity
						.getPurchaseOrderDate());
			}
			String itemName = null;
			PurchaseOrderMasterDTO orderMasterDTO = purchaseOrderMasterInputMessage
					.getPurchaseOrderMasterDTO();
			if (orderMasterDTO.getItemName() != null) {
				itemName = orderMasterDTO.getItemName();

			}

			Date fromDate = null;
			Date toDate = null;
			if (orderMasterDTO.getFromDate() != null) {
				fromDate = orderMasterDTO.getFromDate();

			}
			if (orderMasterDTO.getToDate() != null) {
				toDate = orderMasterDTO.getToDate();

			}

			List<PurchaseOrderMasterEntity> list = storagePurchaseOrderMasterDAO
					.search(purchaseOrderMasterEntity.getPurchaseOrderNumber(),
							fromDate, toDate, purchaseOrderMasterEntity
									.getPartyEntity().getPartyName(), itemName);
			popUpList(list);
		} else if (NEW_PURCHASE_ORDER_MASTER_SERIES_NO.equals(flowId)) {

			Integer seriesNo = 0;
			Timestamp date = zoneService.getFirstDayOfFinYear();
			List list = storagePurchaseOrderMasterDAO
					.getNewSeriesNo(purchaseOrderMasterEntity.getFinYear());
			if (list != null && list.size() > 0) {
				Object[] obj = (Object[]) list.get(0);

				Number n = (Number) obj[0];
				if (n != null)
					seriesNo = n.intValue();
				if (obj[1] != null && obj[1] != "")
					date = (Timestamp) obj[1];
			}
			seriesNo++;

			purchaseOrderMasterOutputMessage = new PurchaseOrderMasterOutputMessage();
			purchaseOrderMasterOutputMessage.setPurchaseOrderSeriesNo(seriesNo);
			purchaseOrderMasterOutputMessage.setPurchaseOrderSeriesDate(date);
		} else if (FIND_PURCHASE_ORDER_BY_SUPPLIER_ID.equals(flowId)) {
			List<PurchaseOrderMasterEntity> list = storagePurchaseOrderMasterDAO
					.findBySupplierId(purchaseOrderMasterEntity
							.getPartyEntity().getPartyId());
			popUpList(list);
		}

	}

	void popUpList(List<PurchaseOrderMasterEntity> list) {
		purchaseOrderMasterOutputMessage = new PurchaseOrderMasterOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<PurchaseOrderMasterDTO> resultList = new ArrayList<PurchaseOrderMasterDTO>();
			PurchaseOrderMasterDTO purchaseOrderMasterDTO;
			for (PurchaseOrderMasterEntity purchaseOrderMasterEntity : list) {
				purchaseOrderMasterDTO = new PurchaseOrderMasterDTO();
				// Spring

				if (purchaseOrderMasterEntity != null) {
					BeanUtils.copyProperties(purchaseOrderMasterEntity,
							purchaseOrderMasterDTO);
					PartyEntity partyEntity = purchaseOrderMasterEntity
							.getPartyEntity();
					if (partyEntity != null) {
						PartyDTO partyDTO = new PartyDTO();
						copyObject(partyEntity, partyDTO);
						purchaseOrderMasterDTO.setPartyDTO(partyDTO);
					}

					BranchEntity branchEntity = purchaseOrderMasterEntity
							.getBranchEntity();
					if (branchEntity != null) {
						BranchDTO branchDTO = new BranchDTO();
						copyObject(branchEntity, branchDTO);
						purchaseOrderMasterDTO.setBranchDTO(branchDTO);
					}

					ItemGroupFlagEntity itemGroupFlagEntity = purchaseOrderMasterEntity
							.getItemGroupFlagEntity();
					if (itemGroupFlagEntity != null) {
						ItemGroupFlagDTO itemGroupFlagDTO = new ItemGroupFlagDTO();
						copyObject(itemGroupFlagEntity, itemGroupFlagDTO);
						purchaseOrderMasterDTO
								.setItemGroupFlagDTO(itemGroupFlagDTO);
					}

					TransporterEntity transporterEntity = purchaseOrderMasterEntity
							.getTransportEnttity();
					if (transporterEntity != null) {
						TransporterDTO transporterDTO = new TransporterDTO();
						copyObject(transporterEntity, transporterDTO);
						purchaseOrderMasterDTO.setTransportDTO(transporterDTO);
					}

					List<PurchaseOrderDetailEntity> purchaseOrderDetailEntityList = purchaseOrderMasterEntity
							.getPurchaseOrderDetailEntity();

					List<PurchaseOrderDetailDTO> purchaseOrderDetailDTOList = new ArrayList<PurchaseOrderDetailDTO>();

					if (purchaseOrderDetailEntityList != null
							&& purchaseOrderDetailEntityList.size() > 0) {
						for (PurchaseOrderDetailEntity purchaseOrderDetailEntity : purchaseOrderDetailEntityList) {
							PurchaseOrderDetailDTO purchaseOrderDetailDTO = new PurchaseOrderDetailDTO();
							copyObject(purchaseOrderDetailEntity,
									purchaseOrderDetailDTO);
							if (purchaseOrderDetailEntity.getItemEntity() != null) {
								ItemEntity itemEntity = purchaseOrderDetailEntity
										.getItemEntity();
								if (itemEntity != null) {
									ItemDTO itemDTO = new ItemDTO();
									copyObject(itemEntity,
											purchaseOrderDetailDTO);
									copyObject(itemEntity, itemDTO);
									purchaseOrderDetailDTO.setItemDTO(itemDTO);
								}
								if (itemEntity.getMasterUnitEntity() != null)
									purchaseOrderDetailDTO
											.setMeasurementUnitName(itemEntity
													.getMasterUnitEntity()
													.getName());
								copyObject(purchaseOrderDetailEntity,
										purchaseOrderDetailDTO);

								purchaseOrderDetailDTOList
										.add(purchaseOrderDetailDTO);
							}

						}
					}
					purchaseOrderMasterDTO
							.setPurchaseOrderDetailDTOList(purchaseOrderDetailDTOList);
					resultList.add(purchaseOrderMasterDTO);

				}

			}
			purchaseOrderMasterOutputMessage
					.setPurchaseOrderMasterDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (CREATE_PURCHASE_ORDER_MASTER.equals(flowId)) {

		} else if (UPDATE_PURCHASE_ORDER_MASTER.equals(flowId)) {

		} else if (DELETE_PURCHASE_ORDER_MASTER.equals(flowId)) {

		} else if (FIND_PURCHASE_ORDER_MASTER_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_PURCHASE_ORDER_MASTER.equals(flowId)) {

		} else if (SEARCH_PURCHASE_ORDER_MASTER.equals(flowId)) {

		} else if (NEW_PURCHASE_ORDER_MASTER_SERIES_NO.equals(flowId)) {

		} else if (PURCHASE_ORDER_UPDATE_FOR_GRN.equals(flowId)) {

		}

	}

	private List<PurchaseOrderDetailEntity> convertPurchaseOrderDetailDtoTOEntity(
			List<PurchaseOrderDetailDTO> dtoList, String purasceOrderNumber,
			String transactionSeris,
			PurchaseOrderMasterDTO purchaseOrderMasterDTO) {
		List<PurchaseOrderDetailEntity> entityList = new ArrayList<PurchaseOrderDetailEntity>();
		for (PurchaseOrderDetailDTO dto : dtoList) {

			PurchaseOrderDetailEntity entity = new PurchaseOrderDetailEntity();

			ItemDTO item = dto.getItemDTO();
			if (item != null) {
				ItemEntity itemEntity = new ItemEntity();
				copyObject(item, itemEntity);

				entity.setItemEntity(itemEntity);
			}
			copyObject(dto, entity);
			// entity.setPurchaseRate(dto.getPurchaseRate());
			// entity.setItemQuantity(dto.getItemQuantity());
			entity.setPurchaseOrderNumber(purasceOrderNumber);
			entity.setTransactionSeries(transactionSeris);
			if (purchaseOrderMasterDTO.getCreatedUserId() != null) {
				entity.setCreatedUserId(purchaseOrderMasterDTO
						.getCreatedUserId());
			}
			if (purchaseOrderMasterDTO.getModifiedUserId() != null) {
				entity.setModifiedUserId(purchaseOrderMasterDTO
						.getModifiedUserId());
			}

			entityList.add(entity);
		}
		return entityList;
	}

	private List<PurchaseOrderDetailDTO> convertPurchaseOrderDetailEntityToDto(
			List<PurchaseOrderDetailEntity> entityList) {
		List<PurchaseOrderDetailDTO> dtoList = new ArrayList<PurchaseOrderDetailDTO>();
		for (PurchaseOrderDetailEntity entity : entityList) {
			PurchaseOrderDetailDTO dto = new PurchaseOrderDetailDTO();

			ItemEntity itemEntity = entity.getItemEntity();
			if (itemEntity != null) {
				ItemDTO itemDTO = new ItemDTO();
				copyObject(itemEntity, dto);
				copyObject(itemEntity, itemDTO);
				dto.setItemDTO(itemDTO);
			}
			if (itemEntity.getMasterUnitEntity() != null)
				dto.setMeasurementUnitName(itemEntity.getMasterUnitEntity()
						.getName());
			copyObject(entity, dto);
			dtoList.add(dto);
		}

		return dtoList;

	}

	private void copyObject(Object source, Object target) {

		try {
			BeanUtils.copyProperties(source, target);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Double FindPoQtyByItemAndIndentNumber(Integer itemId,
			String indentNumber) {
		List<Double> l = storagePurchaseOrderMasterDAO
				.findPurchaseQtyByItemIdAndIndentNumber(itemId, indentNumber);
		Double d = null;
		if (l != null && l.size() > 0) {
			d = l.get(0);
		}
		return d;
	}

	@Override
	public Boolean findByPurchaseOrderDetailByIndentNumberAndItemId(
			String indentNumber, Integer itemId) {
		List l = storagePurchaseOrderMasterDAO
				.findByPurchaseOrderDetailByIndentNumberAndItemId(indentNumber,
						itemId);
		Boolean flag = false;
		if (l != null && l.size() > 0) {
			flag = true;
		}
		return flag;
	}

}
