package com.advanz.erp.masters.service;

import java.util.ArrayList;
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
import com.advanz.erp.masters.entity.jpa.BillDetailEntity;
import com.advanz.erp.masters.entity.jpa.DispatchDetailEntity;
import com.advanz.erp.masters.entity.jpa.FinishedGoodsDetailEntity;
import com.advanz.erp.masters.entity.jpa.GrnDetailEntity;
import com.advanz.erp.masters.entity.jpa.IssueDetailMasterEntity;
import com.advanz.erp.masters.entity.jpa.ItemCategoryEntity;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.entity.jpa.PurchaseOrderDetailEntity;
import com.advanz.erp.masters.entity.jpa.QuotationDetailEntity;
import com.advanz.erp.masters.entity.jpa.SalesOrderDetailEntity;
import com.advanz.erp.masters.entity.jpa.StockLedgerEntity;
import com.advanz.erp.masters.entity.jpa.StoreLocationEntity;
import com.advanz.erp.masters.model.BatchDTO;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.StoreLocationDTO;
import com.advanz.erp.masters.model.msg.BatchInputMessage;
import com.advanz.erp.masters.model.msg.BatchOutMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.service.business.IBatchService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.storage.IStorageBillDetailDAO;
import com.advanz.erp.masters.storage.IStorageDispatchDetailDAO;
import com.advanz.erp.masters.storage.IStorageFinishedGoodsMasterDAO;
import com.advanz.erp.masters.storage.IStorageGrnMasterDAO;
import com.advanz.erp.masters.storage.IStorageIssueDAO;
import com.advanz.erp.masters.storage.IStorageItemDAO;
import com.advanz.erp.masters.storage.IStorageMastersDAO;
import com.advanz.erp.masters.storage.IStoragePurchaseOrderMasterDAO;
import com.advanz.erp.masters.storage.IStorageQuotationMasterDAO;
import com.advanz.erp.masters.storage.IStorageSalesOrderMasterDAO;
import com.advanz.erp.masters.storage.IStorageStockLedgerDAO;

public class ItemServiceImpl implements IItemService {

	public static final String CREATE_ITEM_GROUP = "Item";
	public static final String UPDATE_ITEM_GROUP = "UpdateItem";
	public static final String ADD_ITEM_GROUP = "AddItem";
	public static final String DELETE_ITEM_GROUP = "DeleteItem";
	public static final String FIND_ITEM_GROUP_BY_ID = "FindItemById";
	public static final String FIND_ALL_ITEM_GROUPES = "FindAllItemes";
	public static final String FIND_ITEM_GROUPES = "FindItemes";
	public static final String FIND_FINISH_GOOD_ITEM = "FindFinishGoodIteme";
	public static final String FIND_GROUP_NAME_FOR_ALL_REPORTS = "findGroupNameForAllReports";
	public static final String SEARCH_FINISHED_GOOD_ITEMS = "SearchFinishedGoodItems";
	public static final String GET_ITEMS_BATCH_ABLE = "LoadItemsForBatch";
	public static final String FIND_ITEM_BY_ITEM_GROUP_NAME = "FindItemByItemGroupName";
	public static final String FIND_ITEM_FOR_REPORT_BY_GROUP_NAME = "FindItemForReportByGroupName";

	public static final String ITEM_ID_AND_ITEM_NAME = "ItemIdAndItemName";
	public static final String FIND_ITEM_FOR_PAGINATION = "FindItemForPagination";
	public static final String FINISH_GOOD_LIST_PAGINATION = "FinishGoodListPagination";
	public static final String PAGINATION_LIST_BY_GROUP_NAME = "PaginationListByGroupName";
	public static final String FIND_ITEM_LIST_BY_ITEM_CODE = "findItemListByItemCode";
	public static final String FIND_FLAG_NAME_BY_ITEM_CODE = "findFlagNameByItemCode";

	public static final String FIND_NAME_AND_ITEM_ID = "findNameAndItemId";
	public static final String FIND_ITEM_BY_ITEM_CATEGORY = "FindItemByItemCategory";
	public String flowId = "";

	private static final Logger logger = LoggerFactory
			.getLogger(ItemServiceImpl.class);

	public void ItemServiceImpl() {
	}

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
	@Autowired
	public IBatchService batchService;

	// autowiring

	@Autowired
	public IStorageItemDAO storageItemDAO;

	@Autowired
	public IStorageMastersDAO storageMastersDAO;

	@Autowired
	public IStorageStockLedgerDAO storageStockLedgerDAO;

	public ItemInputMessage itemInputMessage;

	public ItemOutMessage itemOutMessage;

	@Autowired
	public IStorageBillDetailDAO storageBillDetailDAO;

	@Autowired
	public IStorageDispatchDetailDAO storageDispatchDetailDAO;

	@Autowired
	public IStorageFinishedGoodsMasterDAO storageFinishedGoodsMasterDAO;

	@Autowired
	public IStorageIssueDAO storageIssueDAO;

	@Autowired
	public IStoragePurchaseOrderMasterDAO storagePurchaseOrderMasterDAO;

	@Autowired
	public IStorageQuotationMasterDAO storageQuotationMasterDAO;

	@Autowired
	public IStorageGrnMasterDAO storageGrnMasterDAO;

	@Autowired
	public IStorageSalesOrderMasterDAO storageSalesOrderMasterDAO;

	@Override
	public ItemOutMessage createItem(ItemInputMessage itemInputMessage) {

		flowId = ADD_ITEM_GROUP;
		// assign the message to the class level variable.
		this.itemInputMessage = itemInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return itemOutMessage;
	}

	@Override
	public ItemOutMessage updateItem(ItemInputMessage itemInputMessage) {

		flowId = UPDATE_ITEM_GROUP;
		this.itemInputMessage = itemInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return itemOutMessage;
	}

	@Override
	public ItemOutMessage deleteItem(ItemInputMessage itemInputMessage) {
		flowId = DELETE_ITEM_GROUP;
		this.itemInputMessage = itemInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return itemOutMessage;

	}

	@Override
	public ItemOutMessage findItemById(ItemInputMessage itemInputMessage) {
		flowId = FIND_ITEM_GROUP_BY_ID;
		this.itemInputMessage = itemInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemOutMessage;

	}

	@Override
	public ItemOutMessage findAllItem() {
		flowId = FIND_ALL_ITEM_GROUPES;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemOutMessage;
	}

	@Override
	public ItemOutMessage finishGoodItemList() {
		flowId = FIND_FINISH_GOOD_ITEM;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemOutMessage;
	}

	// @Override
	public ItemOutMessage findItem(ItemInputMessage itemInputMessage) {
		flowId = FIND_ITEM_GROUPES;
		this.itemInputMessage = itemInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemOutMessage;
	}

	@Override
	public ItemOutMessage searchFinishedFoodItems(
			ItemInputMessage itemInputMessage) {
		flowId = SEARCH_FINISHED_GOOD_ITEMS;
		this.itemInputMessage = itemInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemOutMessage;

	}

	@Override
	public ItemOutMessage findItemsForBatch() {
		flowId = GET_ITEMS_BATCH_ABLE;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemOutMessage;
	}

	@Override
	public ItemOutMessage searchMaterialIssuesItems(
			ItemInputMessage itemInputMessage) {
		flowId = FIND_ITEM_BY_ITEM_GROUP_NAME;
		this.itemInputMessage = itemInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemOutMessage;
	}

	@Override
	public ItemOutMessage findItemForReportByGroupName(
			ItemInputMessage itemInputMessage) {
		flowId = FIND_ITEM_FOR_REPORT_BY_GROUP_NAME;
		this.itemInputMessage = itemInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemOutMessage;
	}

	@Override
	public ItemOutMessage getItemIdAndItemNameList() {
		flowId = ITEM_ID_AND_ITEM_NAME;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemOutMessage;
	}

	@Override
	public ItemOutMessage findItemForPagination(
			ItemInputMessage itemInputMessage) {
		flowId = FIND_ITEM_FOR_PAGINATION;
		this.itemInputMessage = itemInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemOutMessage;
	}

	@Override
	public ItemOutMessage finishGoodItemListWithPagination(
			ItemInputMessage itemInputMessage) {
		flowId = FINISH_GOOD_LIST_PAGINATION;
		this.itemInputMessage = itemInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemOutMessage;

	}

	@Override
	public ItemOutMessage getListByItemGroupName(
			ItemInputMessage itemInputMessage) {
		flowId = PAGINATION_LIST_BY_GROUP_NAME;
		this.itemInputMessage = itemInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemOutMessage;

	}

	@Override
	public ItemOutMessage getListByItemCategory(
			ItemInputMessage itemInputMessage) {
		flowId = FIND_ITEM_BY_ITEM_CATEGORY;
		this.itemInputMessage = itemInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemOutMessage;
	}

	@Override
	public ItemOutMessage findItemGroupNameForAllReports(
			ItemInputMessage itemInputMessage) {
		flowId = FIND_GROUP_NAME_FOR_ALL_REPORTS;
		this.itemInputMessage = itemInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemOutMessage;
	}

	@Override
	public ItemOutMessage findItemListByItemCode(
			ItemInputMessage itemInputMessage) {
		flowId = FIND_ITEM_LIST_BY_ITEM_CODE;
		this.itemInputMessage = itemInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemOutMessage;
	}

	@Override
	public ItemOutMessage findtemGroupFlagNameByItemCode(
			ItemInputMessage itemInputMessage) {
		flowId = FIND_FLAG_NAME_BY_ITEM_CODE;
		this.itemInputMessage = itemInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemOutMessage;
	}

	@Override
	public ItemOutMessage getItemNameAndId(ItemInputMessage itemInputMessage) {
		// TODO Auto-generated method stub
		flowId = FIND_NAME_AND_ITEM_ID;
		this.itemInputMessage = itemInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemOutMessage;
	}

	@Override
	public boolean validateInput() {

		if (ADD_ITEM_GROUP.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_ITEM_GROUP.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_ITEM_GROUP.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ITEM_GROUP_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_ITEM_GROUPES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ITEM_GROUPES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_FINISH_GOOD_ITEM.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_FINISHED_GOOD_ITEMS.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (GET_ITEMS_BATCH_ABLE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ITEM_BY_ITEM_GROUP_NAME.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ITEM_FOR_REPORT_BY_GROUP_NAME.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ITEM_FOR_PAGINATION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (ITEM_ID_AND_ITEM_NAME.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FINISH_GOOD_LIST_PAGINATION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (PAGINATION_LIST_BY_GROUP_NAME.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ITEM_LIST_BY_ITEM_CODE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_GROUP_NAME_FOR_ALL_REPORTS.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		else if (FIND_FLAG_NAME_BY_ITEM_CODE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ITEM_BY_ITEM_CATEGORY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_NAME_AND_ITEM_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {
		ItemEntity itemEntity = new ItemEntity();
		// String ignoreProperties[]= {"branchId,servTaxDate,vatDate,cstDate"};

		if (itemInputMessage != null) {
			ItemDTO itemDTO = itemInputMessage.getItemDTO();
			if (itemDTO != null) {
				BeanUtils.copyProperties(itemDTO, itemEntity);
				ItemCategoryEntity itemCategoryEntity = new ItemCategoryEntity();
				if (itemDTO.getMasterGrade() != null) {
					MastersEntity mastersEntity = new MastersEntity();
					mastersEntity.setMastersId(itemDTO.getMasterGrade()
							.getMastersId());
					itemEntity.setMasterGradeEntity(mastersEntity);
				}

				if (itemDTO.getMasterPack() != null) {
					MastersEntity mastersEntity = new MastersEntity();
					mastersEntity.setMastersId(itemDTO.getMasterPack()
							.getMastersId());
					itemEntity.setMasterPackEntity(mastersEntity);
				}

				if (itemDTO.getMasterUnit() != null) {
					MastersEntity mastersEntity = new MastersEntity();
					mastersEntity.setMastersId(itemDTO.getMasterUnit()
							.getMastersId());
					itemEntity.setMasterUnitEntity(mastersEntity);
				}

				if (itemDTO.getStoreLocationDTO() != null) {
					StoreLocationEntity storeLocationEntity = new StoreLocationEntity();
					storeLocationEntity.setStoreLocationId(itemDTO
							.getStoreLocationDTO().getStoreLocationId());
					itemEntity.setStoreLocationEntity(storeLocationEntity);
				}

				if (itemDTO.getItemCategoryDTO() != null)
					itemCategoryEntity.setItemCategoryId(itemDTO
							.getItemCategoryDTO().getItemCategoryId());
				itemEntity.setItemCategoryEntity(itemCategoryEntity);

			}
		}

		if (ADD_ITEM_GROUP.equals(flowId)) {
			try {
				ItemDTO itemDto = itemInputMessage.getItemDTO();
				List<ItemEntity> list = storageItemDAO.getItemByName(itemDto
						.getItemName());
				List<ItemEntity> list1 = storageItemDAO.getItemByCode(itemDto
						.getItemCode());
				logger.info(flowId + ": " + list);
				itemOutMessage = new ItemOutMessage();
				if ((list != null && list.size() > 0)
						|| (list1 != null && list1.size() > 0)) {
					ErrorDTO errorDTO = new ErrorDTO("1",
							"Sorry, Record already exist, Duplicate entries are not allowed.");
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					itemOutMessage.setErrorListDTO(errorListDTO);
				} else {
					itemOutMessage.setErrorListDTO(null);
					itemEntity.setDeletedFlag(false);
					storageItemDAO.create(itemEntity);
				}

			} catch (BeansException e) {
				e.printStackTrace();
			}

		} else if (UPDATE_ITEM_GROUP.equals(flowId)) {
			try {
				/*
				 * Duplicate Item check by Name and Code will not work by this
				 * code because list1.get(0).getItemId() always return same item
				 * id which we try to update itemEntity.getItemId()
				 */
				ItemDTO itemDto = itemInputMessage.getItemDTO();
				List<ItemEntity> list = storageItemDAO.getItemByName(itemDto
						.getItemName());
				List<ItemEntity> list1 = storageItemDAO.getItemByCode(itemDto
						.getItemCode());
				logger.info(flowId + ": " + list);
				itemOutMessage = new ItemOutMessage();
				if ((list != null && list.size() > 0 && !list.get(0)
						.getItemId().equals(itemEntity.getItemId()))
						|| (list1 != null && list1.size() > 0 && !list1.get(0)
								.getItemId().equals(itemEntity.getItemId()))
						|| (list.size() > 1 || list1.size() > 1)) {
					ErrorDTO errorDTO = new ErrorDTO("1",
							"Sorry, Record already exist, Duplicate entries are not allowed.");
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					itemOutMessage.setErrorListDTO(errorListDTO);
				} else {
					itemOutMessage.setErrorListDTO(null);
					itemEntity.setDeletedFlag(false);
					storageItemDAO.update(itemEntity);

					// Update Batch when item is updated
					BatchInputMessage batchInputMessage = new BatchInputMessage();
					BatchDTO batchDTO = new BatchDTO();
					ItemDTO itemDTO = new ItemDTO();
					Integer itemId = itemEntity.getItemId();
					itemDTO.setItemId(itemId);
					batchDTO.setItemDTO(itemDTO);
					batchInputMessage.setBatchDTO(batchDTO);
					BatchOutMessage batchOutMessage = batchService
							.findAllBatchByItemId(batchInputMessage);
					List<BatchDTO> batchList = batchOutMessage
							.getBatchDTOList();

					if (batchList != null && batchList.size() > 0) {
						for (int i = 0; i < batchList.size(); i++) {
							batchDTO = batchList.get(i);

							batchDTO.setInvoiceRate(itemDto.getSalesRate());
							batchDTO.setExcisePerc(itemDto.getExcisePerc());

							batchDTO.setItemDTO(itemDto);
							batchDTO.setTradeRate(itemDto.getTradeRate());
							batchDTO.setVatPerc(itemDto.getVatPerc());
							batchDTO.setMrp(itemDto.getMrp());
							batchDTO.setCstPerc(itemDto.getCstPerc());
							batchDTO.setPurchaseRate(itemDto.getPurchaseRate());
							batchDTO.setDiscountPerc(itemDto.getDiscountPer());
							batchDTO.setNetRate(itemDto.getNetRate());
							batchDTO.setClosingStock(itemDto.getClosingStock());
							batchDTO.setOpeningStock(itemDto.getOpeningStock());
							batchInputMessage = new BatchInputMessage();
							batchInputMessage.setBatchDTO(batchDTO);
							batchService.updateBatch(batchInputMessage);
						}

					}

				}
			} catch (BeansException e) {
				e.printStackTrace();
			}

		} else if (DELETE_ITEM_GROUP.equals(flowId)) {

			try {
				ItemDTO itemDto = itemInputMessage.getItemDTO();
				boolean flag = false;
				String error = null;
				itemOutMessage = new ItemOutMessage();
				List<BillDetailEntity> list = storageBillDetailDAO
						.findByItemId(itemDto.getItemId());
				if ((list != null && list.size() > 0)) {
					error = "Item use in Bill Detail. You can not delete.";
					flag = true;
				} else {
					List<DispatchDetailEntity> list1 = storageDispatchDetailDAO
							.findByItemId(itemDto.getItemId());
					if ((list1 != null && list1.size() > 0)) {
						error = "Item use in Dispatch Detail. You can not delete.";
						flag = true;
					} else {
						List<FinishedGoodsDetailEntity> list2 = storageFinishedGoodsMasterDAO
								.findByItemId(itemDto.getItemId());
						if ((list2 != null && list2.size() > 0)) {
							error = "Item use in Finished Goods Detail. You can not delete.";
							flag = true;
						} else {
							List<IssueDetailMasterEntity> list3 = storageIssueDAO
									.findByItemId(itemDto.getItemId());
							if ((list3 != null && list3.size() > 0)) {
								error = "Item use in Issue Detail. You can not delete.";
								flag = true;
							} else {
								List<PurchaseOrderDetailEntity> list4 = storagePurchaseOrderMasterDAO
										.findByItemId(itemDto.getItemId());
								if ((list4 != null && list4.size() > 0)) {
									error = "Item use in Purchase Order Detail. You can not delete.";
									flag = true;
								} else {
									List<QuotationDetailEntity> list5 = storageQuotationMasterDAO
											.findByItemId(itemDto.getItemId());
									if ((list5 != null && list5.size() > 0)) {
										error = "Item use in Quotation Detail. You can not delete.";
										flag = true;
									} else {
										List<SalesOrderDetailEntity> list6 = storageSalesOrderMasterDAO
												.findByItemId(itemDto
														.getItemId());
										if ((list6 != null && list6.size() > 0)) {
											error = "Item use in Sales Order Detail. You can not delete.";
											flag = true;
										} else {
											List<StockLedgerEntity> list7 = storageStockLedgerDAO
													.findByItemId(itemDto
															.getItemId());
											if ((list7 != null && list7.size() > 0)) {
												error = "Item use in Stock Ledger Detail. You can not delete.";
												flag = true;
											} else {
												List<GrnDetailEntity> list8 = storageGrnMasterDAO
														.findByItemId(itemDto
																.getItemId());
												if ((list8 != null && list8
														.size() > 0)) {
													error = "Item use in GRN  Detail. You can not delete.";
													flag = true;
												}

											}

										}

									}

								}

							}
						}

					}
				}

				if (flag && error != null) {
					ErrorDTO errorDTO = new ErrorDTO("1", error);
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					itemOutMessage.setErrorListDTO(errorListDTO);
				} else {
					if (itemInputMessage.isDeletedFlag()) {
						BeanUtils.copyProperties(itemDto, itemEntity);
						itemOutMessage.setErrorListDTO(null);
						storageItemDAO.delete(itemEntity);
					}
				}

			} catch (BeansException e) {
				e.printStackTrace();
			}

		} else if (FIND_ITEM_GROUP_BY_ID.equals(flowId)) {
			ItemDTO itemDTO = itemInputMessage.getItemDTO();
			List<ItemEntity> list = storageItemDAO
					.findById(itemDTO.getItemId());
			if (list != null) {
				List<ItemDTO> resultList = convertItemEntityListTOItemDtoList(list);
				itemOutMessage.setItemDTOList(resultList);
			}
		} else if (FIND_ITEM_FOR_REPORT_BY_GROUP_NAME.equals(flowId)) {
			ItemDTO itemDTO = itemInputMessage.getItemDTO();
			List<ItemEntity> list = storageItemDAO
					.getListByItemGroupFlagId(itemDTO.getItemGroupFlagId());
			if (list != null) {
				List<ItemDTO> resultList = convertItemEntityListTOItemDtoList(list);
				itemOutMessage = new ItemOutMessage();
				itemOutMessage.setItemDTOList(resultList);
			}
		}

		else if (FIND_ALL_ITEM_GROUPES.equals(flowId)) {
			List<ItemEntity> list = storageItemDAO.load();
			itemOutMessage = new ItemOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<ItemDTO> resultList = convertItemEntityListTOItemDtoList(list);
				itemOutMessage.setItemDTOList(resultList);
			}
		}

		else if (FIND_ITEM_FOR_PAGINATION.equals(flowId)) {
			List<ItemEntity> list = storageItemDAO
					.loadItemPagination(itemInputMessage.getItemDTO().getNext());

			itemOutMessage = new ItemOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<ItemDTO> resultList = convertItemEntityListTOItemDtoList(list);
				itemOutMessage.setItemDTOList(resultList);
			}
		}

		else if (FIND_GROUP_NAME_FOR_ALL_REPORTS.equals(flowId)) {
			List<ItemEntity> list = storageItemDAO
					.findItemByGroupName(itemInputMessage.getItemDTO()
							.getItemGroupName());

			itemOutMessage = new ItemOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<ItemDTO> resultList = convertItemEntityListTOItemDtoList(list);
				itemOutMessage.setItemDTOList(resultList);
			}
		}

		else if (FIND_ITEM_LIST_BY_ITEM_CODE.equals(flowId)) {
			List<ItemEntity> list = storageItemDAO
					.getItemByCode(itemInputMessage.getItemDTO().getItemCode());
			itemOutMessage = new ItemOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<ItemDTO> resultList = convertItemEntityListTOItemDtoList(list);
				itemOutMessage.setItemDTOList(resultList);
			}
		}

		else if (FINISH_GOOD_LIST_PAGINATION.equals(flowId)) {
			List<ItemEntity> list = null;
			if (itemInputMessage.getItemDTO().getExcisePerc() != null
					&& itemInputMessage.getItemDTO().getExcisePerc() > 0) {
				list = storageItemDAO
						.finishGoodItemListWithPaginationAndExcisePerc(
								itemInputMessage.getItemDTO().getNext(),
								itemInputMessage.getItemDTO().getExcisePerc());
			} else if (itemInputMessage.getItemDTO().getItemGroupFlagId() != null
					&& itemInputMessage.getItemDTO().getItemGroupFlagId() > 0) {
				list = storageItemDAO.finishGoodItemListWithPagination(
						itemInputMessage.getItemDTO().getNext(),
						itemInputMessage.getItemDTO().getItemGroupFlagId());
			} else {
				list = storageItemDAO.finishGoodItemListWithPagination(
						itemInputMessage.getItemDTO().getNext(), 3);
			}
			itemOutMessage = new ItemOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<ItemDTO> resultList = convertItemEntityListTOItemDtoList(list);
				itemOutMessage.setItemDTOList(resultList);
			}
		}

		else if (FIND_FINISH_GOOD_ITEM.equals(flowId)) {
			List<ItemEntity> list = storageItemDAO.finishGoodItemList();
			itemOutMessage = new ItemOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<ItemDTO> resultList = convertItemEntityListTOItemDtoList(list);
				itemOutMessage.setItemDTOList(resultList);
			}
		} else if (FIND_ITEM_GROUPES.equals(flowId)) {
			ItemDTO itemDTO = itemInputMessage.getItemDTO();

			String itemName = null;
			String invoiceName = null;
			String itemCode = null;
			String catCode = null;
			String groupName = null;
			Integer groupFlagId = null;
			Integer activeFlag = null;
			Double excisePerc = 0.0;
			Integer gradeId = null;
			if (itemDTO.getExcisePerc() != null && itemDTO.getExcisePerc() > 0) {
				excisePerc = itemDTO.getExcisePerc();
			}
			if (itemDTO != null) {
				itemName = itemDTO.getItemName();
				itemCode = itemDTO.getItemCode();
				invoiceName = itemDTO.getInvoiceName();
				activeFlag = itemDTO.getActiveStatus();
				if (itemDTO.getItemCategoryDTO() != null) {
					catCode = itemDTO.getItemCategoryDTO()
							.getItemCategoryName();
					if (itemDTO.getItemCategoryDTO().getItemGroupDTO() != null)
						groupName = itemDTO.getItemCategoryDTO()
								.getItemGroupDTO().getItemGroupName();
					groupFlagId = itemDTO.getItemCategoryDTO()
							.getItemGroupDTO().getItemGroupFlagId();
				}
				try {
					if (itemDTO.getMasterGrade().getMastersId() != null) {
						gradeId = itemDTO.getMasterGrade().getMastersId();
					}
				} catch (Exception e) {

				}
			}
			List<ItemEntity> list = storageItemDAO.search(itemName,
					invoiceName, itemCode, catCode, groupName, groupFlagId,
					activeFlag, excisePerc, gradeId);
			itemOutMessage = new ItemOutMessage();
			if (list != null) {
				List<ItemDTO> resultList = convertItemEntityListTOItemDtoList(list);
				itemOutMessage.setItemDTOList(resultList);
			}
		} else if (SEARCH_FINISHED_GOOD_ITEMS.equals(flowId)) {
			ItemDTO itemDTO = itemInputMessage.getItemDTO();
			String invoiceName = null;
			String itemCode = null;
			if (itemDTO != null) {
				itemCode = itemDTO.getItemCode();
				invoiceName = itemDTO.getInvoiceName();
			}
			List<ItemEntity> list = storageItemDAO.loadFinishedGoodItems(
					invoiceName, itemCode);

			itemOutMessage = new ItemOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<ItemDTO> resultList = convertItemEntityListTOItemDtoList(list);
				itemOutMessage.setItemDTOList(resultList);
			}

		} else if (GET_ITEMS_BATCH_ABLE.equals(flowId)) {
			List<ItemEntity> list = storageItemDAO.loadItemsForBatch();
			itemOutMessage = new ItemOutMessage();
			// set the data to the output message.
			if (list != null) {
				// List<ItemDTO> resultList =
				// convertItemEntityListTOItemDtoList(list);
				List<ItemDTO> resultList = popUpBatchItems(list);
				itemOutMessage.setItemDTOList(resultList);
			}

		} else if (ITEM_ID_AND_ITEM_NAME.equals(flowId)) {
			List<ItemEntity> list = storageItemDAO.getItemIdAndItemNameList();

			itemOutMessage = new ItemOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<ItemDTO> resultList = convertItemEntityListTOItemDtoListWioutStockCount(list);
				itemOutMessage.setItemDTOList(resultList);
			}
		}

		else if (FIND_FLAG_NAME_BY_ITEM_CODE.equals(flowId)) {

			try {
				Integer itemGroupflagName = storageItemDAO
						.itemGroupFlagId(itemInputMessage.getItemDTO()
								.getItemCode());
				// System.out.println("TTTTTTTTTTTTTTTTTTTTTT"+itemGroupflagName);
				itemOutMessage = new ItemOutMessage();
				ItemDTO itemDTO = new ItemDTO();

				itemDTO.setItemId(itemGroupflagName);
				itemOutMessage.setItemDTO(itemDTO);
				// set the data to the output message.
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		else if (FIND_ITEM_BY_ITEM_GROUP_NAME.equals(flowId)) {
			ItemDTO itemDto = itemInputMessage.getItemDTO();
			List<ItemEntity> list = storageItemDAO.searchByItemGroupNameList(
					itemDto.getItemName(), itemDto.getItemCode(), itemDto
							.getItemCategoryDTO().getItemGroupDTO()
							.getItemGroupFlagId(), 1);
			itemOutMessage = new ItemOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<ItemDTO> resultList = convertItemEntityListTOItemDtoList(list);
				itemOutMessage.setItemDTOList(resultList);
			}
		} else if (FIND_ITEM_BY_ITEM_CATEGORY.equals(flowId)) {
			ItemDTO itemDto = itemInputMessage.getItemDTO();
			List<ItemEntity> list = storageItemDAO
					.getListByItemCategory(itemDto.getItemCategoryDTO()
							.getItemCategoryId());
			itemOutMessage = new ItemOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<ItemDTO> resultList = convertItemEntityListTOItemDtoList(list);
				itemOutMessage.setItemDTOList(resultList);
			}
		} else if (FIND_NAME_AND_ITEM_ID.equals(flowId)) {
			ItemDTO itemDto = itemInputMessage.getItemDTO();
			List<ItemEntity> list = storageItemDAO.getItemNameAndId(
					itemDto.getItemId(), itemInputMessage.getItemOperation());
			itemOutMessage = new ItemOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<ItemDTO> resultList = convertItemEntityListTOItemDtoListWioutStockCount(list);
				itemOutMessage.setItemDTOList(resultList);
			}
		} else if (PAGINATION_LIST_BY_GROUP_NAME.equals(flowId)) {
			ItemDTO itemDTO = itemInputMessage.getItemDTO();
			Integer activeFlag = null;
			Integer groupFlagId = null;
			Integer index = 0;
			try {
				if (itemDTO.getItemCategoryDTO().getItemGroupDTO() != null) {
					groupFlagId = itemDTO.getItemCategoryDTO()
							.getItemGroupDTO().getItemGroupFlagId();
				}
				if (itemDTO.getActiveStatus() != null) {
					activeFlag = itemDTO.getActiveStatus();
				}
				if (itemDTO.getNext() != null) {
					index = itemDTO.getNext();
				}
			} catch (Exception e) {

			}
			List<ItemEntity> list = storageItemDAO.getListByItemGroupName(
					groupFlagId, activeFlag, index);
			if (list != null) {
				List<ItemDTO> resultList = convertItemEntityListTOItemDtoList(list);
				itemOutMessage.setItemDTOList(resultList);
			}
		}

	}

	@Override
	public void formatOutput() {

		if (ADD_ITEM_GROUP.equals(flowId)) {

		} else if (UPDATE_ITEM_GROUP.equals(flowId)) {

		} else if (DELETE_ITEM_GROUP.equals(flowId)) {

		} else if (FIND_ITEM_GROUP_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_ITEM_GROUPES.equals(flowId)) {

		} else if (SEARCH_FINISHED_GOOD_ITEMS.equals(flowId)) {

		} else if (FIND_ITEM_BY_ITEM_GROUP_NAME.equals(flowId)) {

		} else if (FIND_ITEM_LIST_BY_ITEM_CODE.equals(flowId)) {

		}
	}

	private List<ItemDTO> convertItemEntityListTOItemDtoList(
			List<ItemEntity> list) {

		itemOutMessage = new ItemOutMessage();
		List<ItemDTO> resultList = null;
		// set the data to the output message.
		if (list != null) {

			ItemDTO itemDTO;
			resultList = new ArrayList<ItemDTO>();
			for (ItemEntity entity : list) {
				itemDTO = new ItemDTO();
				// Spring
				itemDTO.setItemCategoryDTO(new ItemCategoryDTO());
				BeanUtils.copyProperties(entity, itemDTO);
				if (entity.getItemCategoryEntity() != null) {
					BeanUtils.copyProperties(entity.getItemCategoryEntity(),
							itemDTO.getItemCategoryDTO());
				}
				itemDTO.getItemCategoryDTO()
						.setItemGroupDTO(new ItemGroupDTO());

				if (entity.getItemCategoryEntity() != null
						&& entity.getItemCategoryEntity().getItemGroupEntity() != null) {
					BeanUtils.copyProperties(entity.getItemCategoryEntity()
							.getItemGroupEntity(), itemDTO.getItemCategoryDTO()
							.getItemGroupDTO());
				}

				if (entity.getMasterPackEntity() != null) {
					MastersDTO mastersDTO = new MastersDTO();
					BeanUtils.copyProperties(entity.getMasterPackEntity(),
							mastersDTO);
					itemDTO.setMasterPack(mastersDTO);
				}

				if (entity.getMasterUnitEntity() != null) {
					MastersDTO mastersDTO = new MastersDTO();
					BeanUtils.copyProperties(entity.getMasterUnitEntity(),
							mastersDTO);
					itemDTO.setMasterUnit(mastersDTO);
				}

				if (entity.getMasterGradeEntity() != null) {
					MastersDTO mastersDTO = new MastersDTO();
					BeanUtils.copyProperties(entity.getMasterGradeEntity(),
							mastersDTO);
					itemDTO.setMasterGrade(mastersDTO);
				}

				if (entity.getStoreLocationEntity() != null) {
					StoreLocationDTO mastersDTO = new StoreLocationDTO();
					BeanUtils.copyProperties(entity.getStoreLocationEntity(),
							mastersDTO);
					itemDTO.setStoreLocationDTO(mastersDTO);
				}

				List<Double> sl = storageStockLedgerDAO
						.countStockByItemId(itemDTO.getItemId());
				Double stockTotal = 0.0;
				if (sl != null && sl.size() > 0) {
					if (sl.get(0) != null)
						stockTotal = sl.get(0).doubleValue();
				}
				if (entity.getOpeningStock() != null) {
					stockTotal += entity.getOpeningStock().doubleValue();
				}
				if (stockTotal < 0 && stockTotal > -0.01) {
					System.out.println("in less the 0");
					stockTotal = 0.0;
				}

				itemDTO.setStockTotal(stockTotal);
				resultList.add(itemDTO);
			}
		}

		return resultList;
	}

	private List<ItemDTO> convertItemEntityListTOItemDtoListWioutStockCount(
			List<ItemEntity> list) {

		itemOutMessage = new ItemOutMessage();
		List<ItemDTO> resultList = null;
		// set the data to the output message.
		if (list != null) {

			ItemDTO itemDTO;
			resultList = new ArrayList<ItemDTO>();
			for (ItemEntity entity : list) {
				itemDTO = new ItemDTO();
				// Spring
				itemDTO.setItemCategoryDTO(new ItemCategoryDTO());
				BeanUtils.copyProperties(entity, itemDTO);
				if (entity.getItemCategoryEntity() != null) {
					BeanUtils.copyProperties(entity.getItemCategoryEntity(),
							itemDTO.getItemCategoryDTO());
				}
				itemDTO.getItemCategoryDTO()
						.setItemGroupDTO(new ItemGroupDTO());

				if (entity.getItemCategoryEntity() != null
						&& entity.getItemCategoryEntity().getItemGroupEntity() != null) {
					BeanUtils.copyProperties(entity.getItemCategoryEntity()
							.getItemGroupEntity(), itemDTO.getItemCategoryDTO()
							.getItemGroupDTO());
				}

				if (entity.getMasterPackEntity() != null) {
					MastersDTO mastersDTO = new MastersDTO();
					BeanUtils.copyProperties(entity.getMasterPackEntity(),
							mastersDTO);
					itemDTO.setMasterPack(mastersDTO);
				}

				if (entity.getMasterUnitEntity() != null) {
					MastersDTO mastersDTO = new MastersDTO();
					BeanUtils.copyProperties(entity.getMasterUnitEntity(),
							mastersDTO);
					itemDTO.setMasterUnit(mastersDTO);
				}

				if (entity.getMasterGradeEntity() != null) {
					MastersDTO mastersDTO = new MastersDTO();
					BeanUtils.copyProperties(entity.getMasterGradeEntity(),
							mastersDTO);
					itemDTO.setMasterGrade(mastersDTO);
				}

				if (entity.getStoreLocationEntity() != null) {
					StoreLocationDTO mastersDTO = new StoreLocationDTO();
					BeanUtils.copyProperties(entity.getStoreLocationEntity(),
							mastersDTO);
					itemDTO.setStoreLocationDTO(mastersDTO);
				}
				resultList.add(itemDTO);
			}
		}

		return resultList;
	}

	private List<ItemDTO> popUpBatchItems(List<ItemEntity> list) {
		itemOutMessage = new ItemOutMessage();
		List<ItemDTO> resultList = null;
		// set the data to the output message.
		if (list != null) {
			ItemDTO itemDTO;
			resultList = new ArrayList<ItemDTO>();
			for (ItemEntity entity : list) {
				itemDTO = new ItemDTO();
				itemDTO.setItemId(entity.getItemId());
				itemDTO.setItemName(entity.getItemName());
				// BeanUtils.copyProperties(entity, itemDTO);
				resultList.add(itemDTO);
			}
		}

		return resultList;
	}

	public MastersDTO popUpList(List<MastersEntity> list) {
		MastersDTO mastersDTO = new MastersDTO();
		if (list != null && list.size() > 0) {

			BeanUtils.copyProperties(list.get(0), mastersDTO);

		}
		return mastersDTO;
	}

	@Override
	public ItemDTO getItemIdAndDencity(ItemDTO itemDTO) {
		List list = storageItemDAO.getItemIdAndDencity(itemDTO.getMasterGrade()
				.getMastersId(), itemDTO.getItemLength(), itemDTO
				.getItemWidth(), itemDTO.getItemHeight(), itemDTO
				.getGrossWeight());
		if (list != null && list.size() > 0) {
			Object[] obj = (Object[]) list.get(0);
			Integer itemId = (Integer) obj[0];
			Double itemDensity = (Double) obj[1];
			String itemName = (String) obj[2];
			itemDTO.setItemId(itemId);
			itemDTO.setItemDensity(itemDensity);
			itemDTO.setItemName(itemName);
		}
		return itemDTO;
	}

}
